/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.bonita2bar.classpath;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.bonita2bar.BarBuilderFactory;
import org.bonitasoft.bonita2bar.BarBuilderFactory.BuildConfig;
import org.bonitasoft.bonita2bar.ClasspathResolver;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry.ArtifactInfo;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry.ConnectorImplementationJar;
import org.bonitasoft.bonita2bar.ProcessRegistry;
import org.bonitasoft.bpm.model.FileUtil;
import org.bonitasoft.bpm.model.MavenUtil;
import org.bonitasoft.bpm.model.process.util.migration.MigrationPolicy;
import org.eclipse.core.runtime.FileLocator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConnectorImplementationArtifactProviderTest {

    private Path projectRoot;
    private MavenProject appProject;

    @BeforeEach
    void setup() throws Exception {
        projectRoot = Files.createTempDirectory("my-project");
        FileUtil.copyDirectory(new File(URLDecoder.decode(
                FileLocator.toFileURL(CustomGroovyArtifactProviderTest.class.getResource("/my-project")).getFile(),
                "UTF-8")).getAbsolutePath(), projectRoot.toFile().getAbsolutePath());

        var appPomFile = projectRoot.resolve("app").resolve("pom.xml").toFile();
        // load maven project
        MavenXpp3Reader reader = new MavenXpp3Reader();
        try (var fileReader = new FileReader(appPomFile)) {
            var model = reader.read(fileReader);
            appProject = new MavenProject(model);
            appProject.setFile(appPomFile);
        }
    }

    @AfterEach
    void after() throws Exception {
        if (Files.exists(projectRoot)) {
            FileUtil.deleteDir(projectRoot);
        }
    }

    @Test
    void should_add_connector_in_bar() throws Exception {
        //given
        var outputFolder = projectRoot.resolve("target");
        var mvnExecutable = MavenUtil.getMvnExecutable();
        // analyze first and ensure extensions are built
        var reportFile = MavenUtil.analyze(projectRoot, mvnExecutable);
        var classpath = MavenUtil.buildClasspath(projectRoot, mvnExecutable);

        var processRegistry = ProcessRegistry.of(projectRoot.resolve("app").resolve("diagrams"),
                MigrationPolicy.NEVER_MIGRATE_POLICY);
        var builder = BarBuilderFactory.create(BuildConfig.builder()
                .connectorImplementationRegistry(createImplementationRegistry(reportFile))
                .formBuilder(id -> new byte[0])
                .workingDirectory(outputFolder)
                .mavenProject(appProject)
                .processRegistry(processRegistry)
                .classpathResolver(ClasspathResolver.of(classpath))
                .build());

        var barOutput = builder.build("ProcessWithConnectors", "1.0", "customEnv");

        assertThat(barOutput.getBusinessArchives()).hasSize(1);
        var businessArchive = barOutput.getBusinessArchives().get(0);

        assertThat(businessArchive.getResource("classpath/bonita-connector-email-1.3.0.jar")).isNotEmpty();
        assertThat(businessArchive.getResource("classpath/javax.mail-1.6.2.jar")).isNotEmpty();
        assertThat(businessArchive.getResource("classpath/javax.mail-api-1.6.2.jar")).isNotEmpty();
        assertThat(businessArchive.getResource("classpath/bonita-connector-rest-1.0.10.jar")).isNotEmpty();

        assertThat(businessArchive.getResource("connector/email-impl-1.3.0.impl")).isNotEmpty();
        assertThat(businessArchive.getResource("connector/rest-get-impl-1.0.10.impl")).isNotEmpty();
    }

    @Test
    void should_build_jarless_bar() throws Exception {
        //given
        var outputFolder = projectRoot.resolve("target");
        var mvnExecutable = MavenUtil.getMvnExecutable();
        // analyze first and ensure extensions are built
        var reportFile = MavenUtil.analyze(projectRoot, mvnExecutable);
        var classpath = MavenUtil.buildClasspath(projectRoot, mvnExecutable);

        var processRegistry = ProcessRegistry.of(projectRoot.resolve("app").resolve("diagrams"),
                MigrationPolicy.NEVER_MIGRATE_POLICY);
        var builder = BarBuilderFactory.create(BuildConfig.builder()
                .connectorImplementationRegistry(createImplementationRegistry(reportFile))
                .formBuilder(id -> new byte[0])
                .workingDirectory(outputFolder)
                .mavenProject(appProject)
                .processRegistry(processRegistry)
                .classpathResolver(ClasspathResolver.of(classpath))
                .withDependencyJars(false)
                .build());

        var barOutput = builder.build("ProcessWithConnectors", "1.0", "customEnv");

        assertThat(barOutput.getBusinessArchives()).hasSize(1);
        var businessArchive = barOutput.getBusinessArchives().get(0);

        assertThat(businessArchive.hasDependencyJars()).isFalse();
        assertThat(businessArchive.getResource("classpath/bonita-connector-email-1.3.0.jar")).isNull();
        assertThat(businessArchive.getResource("classpath/javax.mail-1.6.2.jar")).isNull();
        assertThat(businessArchive.getResource("classpath/javax.mail-api-1.6.2.jar")).isNull();
        assertThat(businessArchive.getResource("classpath/bonita-connector-rest-1.0.10.jar")).isNull();

        assertThat(businessArchive.getResource("connector/email-impl-1.3.0.impl")).isNotEmpty();
        assertThat(businessArchive.getResource("connector/rest-get-impl-1.0.10.impl")).isNotEmpty();
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
