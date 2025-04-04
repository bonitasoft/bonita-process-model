/**
 * Copyright (C) 2025 BonitaSoft S.A.
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
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry.ArtifactInfo;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry.ConnectorImplementationJar;
import org.bonitasoft.bonita2bar.ProcessRegistry;
import org.bonitasoft.bonita2bar.internal.M2eMavenExecutor;
import org.bonitasoft.bonita2bar.process.pomgen.ProcessPomGenerator;
import org.bonitasoft.bpm.model.FileUtil;
import org.bonitasoft.bpm.model.MavenUtil;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.process.util.migration.MigrationPolicy;
import org.bonitasoft.bpm.model.util.ModelLoader;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.engine.bpm.flownode.impl.internal.FlowElementContainerDefinitionImpl;
import org.bonitasoft.engine.bpm.process.impl.internal.DesignProcessDefinitionImpl;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DependenciesArtifactProviderTest {

    private DependenciesArtifactProvider dependenciesArtifactProvider;
    private BusinessArchiveBuilder businessArchiveBuilder;
    private ProcessRegistry processRegistry;
    private Configuration configuration;
    private ProcessPomGenerator pomGenerator;
    private JarArtifactProvider jarArtifactProvider;

    @BeforeEach
    void before() throws Exception {
        Path projectRoot = Files.createTempDirectory("my-project");
        FileUtil.copyDirectory(new File(URLDecoder.decode(
                FileLocator.toFileURL(CustomGroovyArtifactProviderTest.class.getResource("/my-project")).getFile(),
                "UTF-8")).getAbsolutePath(), projectRoot.toFile().getAbsolutePath());
        // given
        processRegistry = ProcessRegistry.of(projectRoot.resolve("app").resolve("diagrams"),
                MigrationPolicy.NEVER_MIGRATE_POLICY);
        dependenciesArtifactProvider = new DependenciesArtifactProvider(new M2eMavenExecutor());
        businessArchiveBuilder = new BusinessArchiveBuilder().createNewBusinessArchive();

        File confFolder = projectRoot.resolve("app").resolve("process_configurations").toFile();
        var configurationResource = ModelLoader.create()
                .loadModel(URI.createFileURI(new File(confFolder, "_xQhDcRxzEeiplJoiu3AUHg.conf").getAbsolutePath()));
        configuration = (Configuration) configurationResource.getContents().get(0);

        var jsonReportFile = MavenUtil.analyze(projectRoot, MavenUtil.getMvnExecutable());
        ConnectorImplementationRegistry connectorImplementationRegistry = createImplementationRegistry(jsonReportFile);

        var appPomFile = projectRoot.resolve("app").resolve("pom.xml").toFile();
        // load maven project
        MavenXpp3Reader reader = new MavenXpp3Reader();
        try (var fileReader = new FileReader(appPomFile)) {
            var model = reader.read(fileReader);
            MavenProject appProject = new MavenProject(model);
            appProject.setFile(appPomFile);
            pomGenerator = ProcessPomGenerator.create(appProject, connectorImplementationRegistry);
        }
    }

    private static ConnectorImplementationRegistry createImplementationRegistry(Path jsonReportFile)
            throws IOException {
        var report = MavenUtil.loadReport(jsonReportFile);
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

    @Test
    void should_export_only_runtime_jars_in_bar() throws Exception {

        var pool = processRegistry.getProcess("SimpleProcessWithContract", "1.0").orElseThrow();

        // when
        pomGenerator.withGeneratedPom(pool, pomAccess -> {
            dependenciesArtifactProvider.build(businessArchiveBuilder, pool, pomAccess, configuration);
            return (Void) null;
        });

        // then
        businessArchiveBuilder.setProcessDefinition(fakeProcessDef());
        var resources = businessArchiveBuilder.done().getResources();
        /*
         * Zip of REST API is ignored, it must be installed independently (only jars are installed).
         * Provided-scoped jars are ignored.
         * In the end, there should be only a few jars left...
         */
        assertThat(resources).allSatisfy((key, value) -> {
            assertThat(key).matches("classpath/(\\Qjackson-\\E.*|\\Qeclipse-collections-\\E.*)\\.jar");
            assertThat(value).isNotNull();
            assertThat(value.length).isPositive();
        });
    }

    @Test
    void should_export_dependency_jars_in_bar() throws Exception {

        var pool = processRegistry.getProcess("SimpleProcessWithContract", "1.0").orElseThrow();

        // when
        pomGenerator.withGeneratedPom(pool, pomAccess -> {
            var pomModel = pomAccess.readPom();
            var dependency = new org.apache.maven.model.Dependency();
            dependency.setGroupId("org.codehaus.plexus");
            dependency.setArtifactId("plexus-testing");
            dependency.setVersion("1.2.0");
            dependency.setScope("runtime");
            pomModel.addDependency(dependency);
            dependency = new org.apache.maven.model.Dependency();
            dependency.setGroupId("org.codehaus.plexus");
            dependency.setArtifactId("plexus-utils");
            dependency.setVersion("4.0.2");
            pomModel.addDependency(dependency);
            pomAccess.writePom(pomModel);
            dependenciesArtifactProvider.build(businessArchiveBuilder, pool, pomAccess, configuration);
            return (Void) null;
        });

        // then
        businessArchiveBuilder.setProcessDefinition(fakeProcessDef());
        var resources = businessArchiveBuilder.done().getResources();
        /*
         * The plexus-testing should be in classpath
         */
        assertThat(resources).containsKey("classpath/plexus-testing-1.2.0.jar");
        assertThat(resources).containsKey("classpath/plexus-utils-4.0.2.jar");
    }

    private static DesignProcessDefinitionImpl fakeProcessDef() {
        DesignProcessDefinitionImpl designProcessDefinitionImpl = new DesignProcessDefinitionImpl();
        FlowElementContainerDefinitionImpl processContainer = new FlowElementContainerDefinitionImpl();
        designProcessDefinitionImpl.setProcessContainer(processContainer);
        return designProcessDefinitionImpl;
    }

}
