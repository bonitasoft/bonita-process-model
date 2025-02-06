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
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
import org.bonitasoft.bonita2bar.BarBuilderFactory;
import org.bonitasoft.bonita2bar.BarBuilderFactory.BuildConfig;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry.ConnectorImplementationJar;
import org.bonitasoft.bonita2bar.ProcessRegistry;
import org.bonitasoft.bpm.model.FileUtil;
import org.bonitasoft.bpm.model.MavenUtil;
import org.bonitasoft.bpm.model.process.util.migration.MigrationPolicy;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.testing.PlexusTest;
import org.eclipse.aether.DefaultRepositorySystemSession;
import org.eclipse.aether.repository.LocalRepository;
import org.eclipse.aether.repository.LocalRepositoryManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

@PlexusTest
class BarBuilderIT {//extends org.apache.maven.AbstractCoreMavenComponentTestCase {

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

    private MavenProject getMavenProject(File pomFile) throws ProjectBuildingException {
        DefaultRepositorySystemSession session = MavenRepositorySystemUtils.newSession();
        LocalRepository localRepo = new LocalRepository("target/local-repo");
        LocalRepositoryManager localRepoManager = repositorySystem.newLocalRepositoryManager(session, localRepo);
        session.setLocalRepositoryManager(localRepoManager);

        ProjectBuildingRequest buildingRequest = new DefaultProjectBuildingRequest();
        buildingRequest.setRepositorySession(session);
        buildingRequest.setProcessPlugins(false);
        buildingRequest.setResolveDependencies(true);

        ProjectBuildingResult result = projectBuilder.build(pomFile, buildingRequest);
        return result.getProject();
    }

    @Test
    void buildProjectBusinessArchives(@TempDir Path tmpDir) throws Exception {
        var projectRoot = tmpDir.resolve("test-respository");
        FileUtil.copyDirectory(new File(BarBuilderIT.class.getResource("/test-repository").getFile()).getAbsolutePath(),
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
        var bonitaConfigurationFile = projectRoot.resolve("target").resolve("test-repository.bconf");
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
        FileUtil.copyDirectory(new File(BarBuilderIT.class.getResource("/test-repository").getFile()).getAbsolutePath(),
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
                .map(map -> ConnectorImplementationJar.of((String) map.get("implementationId"),
                        (String) map.get("implementationVersion"),
                        new File((String) ((Map<String, Object>) map.get("artifact")).get("file")),
                        (String) map.get("jarEntry")))
                .collect(Collectors.toList());
    }

}
