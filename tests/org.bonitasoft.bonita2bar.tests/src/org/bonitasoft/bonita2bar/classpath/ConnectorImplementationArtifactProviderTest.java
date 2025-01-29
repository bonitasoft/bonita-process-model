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
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.artifact.handler.DefaultArtifactHandler;
import org.bonitasoft.bonita2bar.AssemblyExecutionException;
import org.bonitasoft.bonita2bar.BarBuilderFactory;
import org.bonitasoft.bonita2bar.BarBuilderFactory.BuildConfig;
import org.bonitasoft.bonita2bar.ClasspathResolver;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry.ConnectorImplementationJar;
import org.bonitasoft.bonita2bar.ProcessRegistry;
import org.bonitasoft.bonita2bar.SingleAssemblyExecutor;
import org.bonitasoft.bonita2bar.SourcePathProvider;
import org.bonitasoft.bpm.model.FileUtil;
import org.bonitasoft.bpm.model.MavenUtil;
import org.bonitasoft.bpm.model.process.util.migration.MigrationPolicy;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConnectorImplementationArtifactProviderTest {

    private Path projectRoot;

    @BeforeEach
    void setup() throws Exception {
        projectRoot = Files.createTempDirectory("test-repository");
        FileUtil.copyDirectory(new File(URLDecoder.decode(
                FileLocator.toFileURL(CustomGroovyArtifactProviderTest.class.getResource("/test-repository")).getFile(),
                "UTF-8")).getAbsolutePath(), projectRoot.toFile().getAbsolutePath());
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
        var mvnExecutable = Platform.getOS().contains("win") ? "mvn.cmd" : "mvn";
        var classpath = MavenUtil.buildClasspath(projectRoot, mvnExecutable);
        var reportFile = MavenUtil.analyze(projectRoot, mvnExecutable);

        var processRegistry = ProcessRegistry.of(projectRoot.resolve("app").resolve("diagrams"),
                MigrationPolicy.NEVER_MIGRATE_POLICY);
        var builder = BarBuilderFactory.create(BuildConfig.builder()
                .connectorImplementationRegistry(createImplementationRegistry(reportFile))
                .singleAssemblyExecutor(new SingleAssemblyExecutor() {

                    @Override
                    public Path execute(Artifact artifact) throws AssemblyExecutionException {
                        try {
                            return MavenUtil.singleAssembly(projectRoot, mvnExecutable, getGoal(),
                                    getUserProperties(artifact.getGroupId(), artifact.getArtifactId()));
                        } catch (InterruptedException | IOException e) {
                            throw new AssemblyExecutionException("Failed to execute Maven assembly.", e);
                        }
                    }
                })
                .formBuilder(id -> new byte[0])
                .workingDirectory(outputFolder)
                .sourcePathProvider(SourcePathProvider.of(projectRoot.resolve("app")))
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
                        new DefaultArtifact(((Map<String, String>) map.get("artifact")).get("groupId"),
                                ((Map<String, String>) map.get("artifact")).get("artifactId"),
                                ((Map<String, String>) map.get("artifact")).get("version"), "runtime", "jar", null,
                                new DefaultArtifactHandler()),
                        new File((String) ((Map<String, Object>) map.get("artifact")).get("file")),
                        (String) map.get("jarEntry")))
                .collect(Collectors.toList());
    }

}
