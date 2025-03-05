/**
 * Copyright (C) 2023 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.bpm.standalone.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.lang3.SystemUtils;
import org.apache.maven.project.DefaultProjectBuildingRequest;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectBuildingException;
import org.apache.maven.project.ProjectBuildingRequest;
import org.apache.maven.project.ProjectBuildingResult;
import org.apache.maven.repository.internal.MavenRepositorySystemUtils;
import org.apache.maven.settings.Settings;
import org.apache.maven.settings.SettingsUtils;
import org.apache.maven.settings.io.xpp3.SettingsXpp3Reader;
import org.bonitasoft.bonita2bar.BarBuilderFactory;
import org.bonitasoft.bonita2bar.BarBuilderFactory.BuildConfig;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry.ArtifactInfo;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry.ConnectorImplementationJar;
import org.bonitasoft.bonita2bar.MavenExecutor;
import org.bonitasoft.bonita2bar.ProcessRegistry;
import org.bonitasoft.bpm.model.FileUtil;
import org.bonitasoft.bpm.model.MavenUtil;
import org.bonitasoft.bpm.model.process.util.migration.MigrationPolicy;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.testing.PlexusTest;
import org.codehaus.plexus.util.ReaderFactory;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.eclipse.aether.DefaultRepositorySystemSession;
import org.eclipse.aether.repository.LocalRepository;
import org.eclipse.aether.repository.LocalRepositoryManager;
import org.eclipse.aether.util.repository.AuthenticationBuilder;
import org.eclipse.aether.util.repository.DefaultAuthenticationSelector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

@PlexusTest
class BarBuilderIT {

    @Inject
    protected PlexusContainer container;

    @Inject
    protected org.eclipse.aether.RepositorySystem repositorySystem;

    @Inject
    protected org.apache.maven.project.ProjectBuilder projectBuilder;

    @BeforeEach
    void setUp() throws Exception {
        LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.WARNING);
    }

    public static final File DEFAULT_USER_SETTINGS_FILE = new File(System.getProperty("user.home"), ".m2/settings.xml");
    public static final File DEFAULT_GLOBAL_SETTINGS_FILE = new File(
            System.getProperty("maven.home", Optional.ofNullable(System.getenv("M2_HOME")).orElse("")),
            "conf/settings.xml");

    private static Settings readSettingsFile()
            throws IOException, XmlPullParserException {
        File settingsFile = new File(System.getProperty("user.home"), ".m2/settings.xml");
        Settings settings = null;
        if (settingsFile.exists()) {
            try (Reader reader = ReaderFactory.newXmlReader(settingsFile)) {
                SettingsXpp3Reader modelReader = new SettingsXpp3Reader();

                settings = modelReader.read(reader);
            }
        }
        return settings;
    }

    private MavenProject getMavenProject(File pomFile)
            throws ProjectBuildingException, IOException, XmlPullParserException {
        DefaultRepositorySystemSession session = MavenRepositorySystemUtils.newSession();
        LocalRepository localRepo = new LocalRepository("target/local-repo");
        LocalRepositoryManager localRepoManager = repositorySystem.newLocalRepositoryManager(session, localRepo);
        session.setLocalRepositoryManager(localRepoManager);

        ProjectBuildingRequest buildingRequest = new DefaultProjectBuildingRequest();
        buildingRequest.setRepositorySession(session);
        buildingRequest.setProcessPlugins(false);
        buildingRequest.setResolveDependencies(true);
        buildingRequest.setSystemProperties(System.getProperties());

        Settings settings = readSettingsFile();
        settings.getProfiles().stream().map(SettingsUtils::convertFromSettingsProfile)
                .forEach(buildingRequest::addProfile);
        buildingRequest.setActiveProfileIds(settings.getActiveProfiles());

        DefaultAuthenticationSelector authSelector = new DefaultAuthenticationSelector();
        settings.getServers().forEach(server -> {
            AuthenticationBuilder authBuilder = new AuthenticationBuilder();
            authBuilder.addUsername(server.getUsername());
            authBuilder.addPassword(server.getPassword());

            authSelector.add(server.getId(), authBuilder.build());
        });
        session.setAuthenticationSelector(authSelector);

        ProjectBuildingResult result = projectBuilder.build(pomFile, buildingRequest);
        return result.getProject();
    }

    @Test
    void buildProjectBusinessArchives(@TempDir Path tmpDir) throws Exception {
        var projectRoot = tmpDir.resolve("test-respository");
        FileUtil.copyDirectory(new File(BarBuilderIT.class.getResource("/my-project").getFile()).getAbsolutePath(),
                projectRoot.toFile().getAbsolutePath());
        var mvnExecutable = SystemUtils.IS_OS_WINDOWS ? "mvn.cmd" : "mvn";
        MavenProject mavenProject = getMavenProject(projectRoot.resolve("app").resolve("pom.xml").toFile());

        var barBuilder = BarBuilderFactory.create(BuildConfig.builder()
                .formBuilder(formId -> formId.getBytes())
                .workingDirectory(tmpDir.resolve("workdir"))
                .connectorImplementationRegistry(
                        createImplementationRegistry(MavenUtil.analyze(projectRoot, mvnExecutable)))
                .processRegistry(ProcessRegistry.of(projectRoot.resolve("app").resolve("diagrams"),
                        MigrationPolicy.ALWAYS_MIGRATE_POLICY))
                .mavenProject(mavenProject)
                .build());

        var result = barBuilder.buildAll("Local");
        var barOutputFolder = projectRoot.resolve("target").resolve("processes");
        result.writeBusinessArchivesTo(barOutputFolder);
        var bonitaConfigurationFile = projectRoot.resolve("target").resolve("my-project.bconf");
        result.writeBonitaConfigurationTo(bonitaConfigurationFile);

        assertThat(result.getBusinessArchives()).hasSize(12);
        assertThat(bonitaConfigurationFile).exists();
        assertThat(barOutputFolder)
                .exists()
                .isDirectoryContaining(bar -> Objects.equals(bar.getFileName().toString(), "ChildProcess--1.0.bar"))
                .isDirectoryContaining(bar -> Objects.equals(bar.getFileName().toString(), "ParentProcess--1.0.bar"))
                .isDirectoryContaining(
                        bar -> Objects.equals(bar.getFileName().toString(), "ProcessWithAdditionalResource--1.0.bar"))
                .isDirectoryContaining(
                        bar -> Objects.equals(bar.getFileName().toString(), "ProcessWithConnectors--1.0.bar"))
                .isDirectoryContaining(
                        bar -> Objects.equals(bar.getFileName().toString(), "PoolWithDecisionTable--1.0.bar"))
                .isDirectoryContaining(
                        bar -> Objects.equals(bar.getFileName().toString(), "ProcessWithComparisonExpression--1.0.bar"))
                .isDirectoryContaining(
                        bar -> Objects.equals(bar.getFileName().toString(), "ProcessWithCustonGroovyDep--1.0.bar"))
                .isDirectoryContaining(
                        bar -> Objects.equals(bar.getFileName().toString(), "ProcessWithDocument--1.0.bar"))
                .isDirectoryContaining(
                        bar -> Objects.equals(bar.getFileName().toString(), "ProcessWithGroovyConnector--1.0.bar"))
                .isDirectoryContaining(
                        bar -> Objects.equals(bar.getFileName().toString(), "ProcessWithv7Forms--1.0.bar"))
                .isDirectoryContaining(
                        bar -> Objects.equals(bar.getFileName().toString(), "SimpleProcessWithContract--1.0.bar"))
                .isDirectoryContaining(
                        bar -> Objects.equals(bar.getFileName().toString(), "SimpleProcessWithParameters--1.0.bar"));
    }

    @Test
    void buildBusinessArchivesWithMigration(@TempDir Path tmpDir) throws Exception {
        var projectRoot = tmpDir.resolve("test-respository");
        FileUtil.copyDirectory(new File(BarBuilderIT.class.getResource("/my-project").getFile()).getAbsolutePath(),
                projectRoot.toFile().getAbsolutePath());
        var mvnExecutable = SystemUtils.IS_OS_WINDOWS ? "mvn.cmd" : "mvn";
        MavenProject mavenProject = getMavenProject(projectRoot.resolve("app").resolve("pom.xml").toFile());

        var barBuilder = BarBuilderFactory.create(BuildConfig.builder()
                .formBuilder(formId -> formId.getBytes())
                .workingDirectory(tmpDir.resolve("workdir"))
                .connectorImplementationRegistry(
                        createImplementationRegistry(MavenUtil.analyze(projectRoot, mvnExecutable)))
                .processRegistry(ProcessRegistry.of(projectRoot.resolve("app").resolve("diagrams"),
                        MigrationPolicy.ALWAYS_MIGRATE_POLICY))
                .mavenProject(mavenProject)
                .build());

        var result = barBuilder.build("ProcessWithAdditionalResource", "1.0", "Local");

        assertThat(result.getBusinessArchives()).hasSize(1);
    }

    @Test
    void buildBusinessArchivesWithMavenExecutor(@TempDir Path tmpDir) throws Exception {
        var projectRoot = tmpDir.resolve("test-respository");
        FileUtil.copyDirectory(new File(BarBuilderIT.class.getResource("/my-project").getFile()).getAbsolutePath(),
                projectRoot.toFile().getAbsolutePath());
        var mvnExecutable = SystemUtils.IS_OS_WINDOWS ? "mvn.cmd" : "mvn";
        MavenProject mavenProject = getMavenProject(projectRoot.resolve("app").resolve("pom.xml").toFile());

        AtomicBoolean mavenExecutorCalled = new AtomicBoolean(false);
        MavenExecutor executor = (File pomFile, List<String> goals, Map<String, String> properties,
                List<String> activeProfiles, Supplier<String> errorMessageBase) -> {
            try {
                MavenUtil.execute(pomFile,
                        mvnExecutable, goals, properties, activeProfiles);
                mavenExecutorCalled.set(true);
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        };

        var barBuilder = BarBuilderFactory.create(BuildConfig.builder()
                .formBuilder(formId -> formId.getBytes())
                .workingDirectory(tmpDir.resolve("workdir"))
                .connectorImplementationRegistry(
                        createImplementationRegistry(MavenUtil.analyze(projectRoot, mvnExecutable)))
                .processRegistry(ProcessRegistry.of(projectRoot.resolve("app").resolve("diagrams"),
                        MigrationPolicy.ALWAYS_MIGRATE_POLICY))
                .mavenProject(mavenProject)
                .mavenExecutor(executor)
                .build());

        var result = barBuilder.build("SimpleProcessWithParameters", "1.0", "Local");

        assertThat(mavenExecutorCalled).isTrue();
        assertThat(result.getBusinessArchives()).hasSize(1);
    }

    private static ConnectorImplementationRegistry createImplementationRegistry(Path reportFile) throws IOException {
        var report = MavenUtil.loadReport(reportFile);
        var implementations = new ArrayList<ConnectorImplementationJar>();
        implementations.addAll(adapt(
                (List<Map<String, Object>>) report.get("connectorImplementations")));
        implementations.addAll(adapt(
                (List<Map<String, Object>>) report.get("filterImplementations")));
        return ConnectorImplementationRegistry.of(implementations);
    }

    private static List<ConnectorImplementationJar> adapt(List<Map<String, Object>> implementations) {
        return implementations.stream()
                .map(map -> {
                    var artifact = ((Map<String, String>) map.get("artifact"));
                    var artifactInfo = new ArtifactInfo(artifact.get("groupId"), artifact.get("artifactId"),
                            artifact.get("version"), artifact.get("classifier"), artifact.get("file"));
                    return ConnectorImplementationJar.of((String) map.get("implementationId"),
                            (String) map.get("implementationVersion"),
                            artifactInfo,
                            (String) map.get("jarEntry"));
                })
                .collect(Collectors.toList());
    }

}
