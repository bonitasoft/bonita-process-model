/**
 * Copyright (C) 2025 Bonitasoft S.A.
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
package org.bonitasoft.bonita2bar.process.pomgen;

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
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.artifact.handler.DefaultArtifactHandler;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry.ArtifactInfo;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry.ConnectorImplementationJar;
import org.bonitasoft.bonita2bar.ProcessRegistry;
import org.bonitasoft.bpm.model.FileUtil;
import org.bonitasoft.bpm.model.MavenUtil;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.builders.ConfigurationBuilder;
import org.bonitasoft.bpm.model.configuration.builders.FragmentBuilder;
import org.bonitasoft.bpm.model.configuration.builders.FragmentContainerBuilder;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.util.migration.MigrationPolicy;
import org.eclipse.core.runtime.FileLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test {@link ProcessPomGenerator}
 */
class ProcessPomGeneratorTest {

    /** Test the email connector dependency */
    private static final Predicate<Dependency> IS_EMAIL_CONNECTOR = dep -> {
        return "org.bonitasoft.connectors:bonita-connector-email:jar".equals(dep.getManagementKey())
                && "1.3.0".equals(dep.getVersion());
    };
    private Path projectRoot;
    private MavenProject appProject;
    private ProcessRegistry processRegistry;
    private ConnectorImplementationRegistry connectorImplementationRegistry;

    @BeforeEach
    void setUp() throws Exception {
        var mvnExecutable = MavenUtil.getMvnExecutable();

        projectRoot = Files.createTempDirectory("my-project");
        var testRepoRoot = new File(URLDecoder.decode(
                FileLocator.toFileURL(ProcessPomGeneratorTest.class.getResource("/my-project")).getFile(),
                "UTF-8"));
        FileUtil.copyDirectory(testRepoRoot.getAbsolutePath(), projectRoot.toFile().getAbsolutePath());

        var jsonReportFile = MavenUtil.analyze(projectRoot, mvnExecutable);
        connectorImplementationRegistry = createImplementationRegistry(jsonReportFile);

        processRegistry = ProcessRegistry.of(projectRoot.resolve("app").resolve("diagrams"),
                MigrationPolicy.NEVER_MIGRATE_POLICY);
        var appPomFile = projectRoot.resolve("app").resolve("pom.xml").toFile();

        // load maven project
        MavenXpp3Reader reader = new MavenXpp3Reader();
        try (var fileReader = new FileReader(appPomFile)) {
            var model = reader.read(fileReader);
            appProject = new MavenProject(model);
            appProject.setFile(appPomFile);
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
    void should_generate_pom_without_connector_dep() throws Exception {
        Optional<Pool> process = processRegistry.getProcess("SimpleProcessWithParameters", "1.0");
        var gen = ProcessPomGenerator.create(appProject, connectorImplementationRegistry);
        gen.withGeneratedPom(process.get(), pomAccess -> {
            Model processPom = pomAccess.readPom();
            assertThat(processPom.getDependencies()).noneMatch(IS_EMAIL_CONNECTOR);
            return null;
        });
    }

    @Test
    void should_generate_pom_with_connector_dep() throws Exception {
        Optional<Pool> process = processRegistry.getProcess("ProcessWithConnectors", "1.0");
        var gen = ProcessPomGenerator.create(appProject, connectorImplementationRegistry);
        gen.withGeneratedPom(process.get(), pomAccess -> {
            Model processPom = pomAccess.readPom();
            assertThat(processPom.getDependencies()).anyMatch(IS_EMAIL_CONNECTOR);
            return null;
        });
    }

    @Test
    void should_generate_pom_with_app_dep() throws Exception {
        appProject.getDependencies().add(new Dependency() {

            private static final long serialVersionUID = 1L;

            {
                setGroupId("org.eclipse.emf");
                setArtifactId("org.eclipse.emf.common");
                setVersion("2.25.0");
            }
        });
        Optional<Pool> process = processRegistry.getProcess("ProcessWithConnectors", "1.0");
        var gen = ProcessPomGenerator.create(appProject, connectorImplementationRegistry);
        gen.withGeneratedPom(process.get(), pomAccess -> {
            Model processPom = pomAccess.readPom();
            assertThat(processPom.getDependencies())
                    .anyMatch(dep -> "org.eclipse.emf:org.eclipse.emf.common:jar".equals(dep.getManagementKey())
                            && "2.25.0".equals(dep.getVersion()));
            return null;
        });
    }

    @Test
    void should_keep_all_dependencies_in_pom_when_no_configuration() throws Exception {
        appProject.getDependencies().add(createDependency("org.example", "lib1", "1.0.0"));
        appProject.getDependencies().add(createDependency("org.example", "lib2", "2.0.0"));
        appProject.getDependencies().add(createDependency("org.example", "lib3", "3.0.0"));

        Optional<Pool> process = processRegistry.getProcess("SimpleProcessWithParameters", "1.0");
        var gen = ProcessPomGenerator.create(appProject, connectorImplementationRegistry);

        gen.withGeneratedPom(process.get(), pomAccess -> {
            Model processPom = pomAccess.readPom();
            assertThat(processPom.getDependencies())
                    .anyMatch(dep -> "org.example:lib1:jar".equals(dep.getManagementKey()));
            assertThat(processPom.getDependencies())
                    .anyMatch(dep -> "org.example:lib2:jar".equals(dep.getManagementKey()));
            assertThat(processPom.getDependencies())
                    .anyMatch(dep -> "org.example:lib3:jar".equals(dep.getManagementKey()));
            return null;
        });
    }

    @Test
    void should_remove_excluded_dependencies_from_pom_when_configuration_provided() throws Exception {
        appProject.getDependencies().add(createDependency("org.example", "kept-lib", "1.0.0"));
        appProject.getDependencies().add(createDependency("org.example", "excluded-lib", "2.0.0"));
        appProject.getDependencies().add(createDependency("org.example", "untracked-lib", "3.0.0"));

        var configuration = ConfigurationBuilder.aConfiguration()
                .havingProcessDependencies(
                        FragmentContainerBuilder.aFragmentContainer("OTHER")
                                .havingFragments(
                                        FragmentBuilder.aFragment()
                                                .withValue("kept-lib-1.0.0.jar")
                                                .withType("JAR")
                                                .exported(),
                                        FragmentBuilder.aFragment()
                                                .withValue("excluded-lib-2.0.0.jar")
                                                .withType("JAR")
                                                .notExported()))
                .build();

        Optional<Pool> process = processRegistry.getProcess("SimpleProcessWithParameters", "1.0");
        var gen = ProcessPomGenerator.create(appProject, connectorImplementationRegistry);

        gen.withGeneratedPom(process.get(), configuration, pomAccess -> {
            Model processPom = pomAccess.readPom();
            // kept-lib: exported=true → kept
            assertThat(processPom.getDependencies())
                    .anyMatch(dep -> "org.example:kept-lib:jar".equals(dep.getManagementKey()));
            // excluded-lib: exported=false → removed
            assertThat(processPom.getDependencies())
                    .noneMatch(dep -> "org.example:excluded-lib:jar".equals(dep.getManagementKey()));
            // untracked-lib: not in any fragment → kept (don't break untracked deps)
            assertThat(processPom.getDependencies())
                    .anyMatch(dep -> "org.example:untracked-lib:jar".equals(dep.getManagementKey()));
            return null;
        });
    }

    // ---------------------------------------------------------------------------------------------
    // Version pinning: the user selection drives the versions Maven resolves for this process
    // ---------------------------------------------------------------------------------------------

    @Test
    void should_pin_selected_version_in_dependency_management() throws Exception {
        appProject.getDependencies().add(createDependency("org.example", "pinned-lib", "1.0.0"));

        var configuration = aConfigurationWith(
                FragmentBuilder.aFragment().withValue("pinned-lib-2.5.0.jar").withType("JAR").exported());

        withProcessPom(configuration, processPom -> {
            assertThat(managedVersionOf(processPom, "org.example", "pinned-lib")).isEqualTo("2.5.0");
        });
    }

    @Test
    void should_pin_transitive_library_using_group_id_of_resolved_artifact() throws Exception {
        // the library is not declared in the pom, it only exists as a resolved (transitive) artifact
        appProject.setArtifacts(Set.of(anArtifact("org.apache.commons", "commons-text", "1.9")));

        var configuration = aConfigurationWith(
                FragmentBuilder.aFragment().withValue("commons-text-1.12.0.jar").withType("JAR").exported());

        withProcessPom(configuration, processPom -> {
            assertThat(managedVersionOf(processPom, "org.apache.commons", "commons-text")).isEqualTo("1.12.0");
        });
    }

    @Test
    void should_keep_qualifier_when_pinning_version() throws Exception {
        appProject.getDependencies().add(createDependency("com.google.guava", "guava", "30.0-jre"));

        var configuration = aConfigurationWith(
                FragmentBuilder.aFragment().withValue("guava-31.1-jre.jar").withType("JAR").exported());

        withProcessPom(configuration, processPom -> {
            assertThat(managedVersionOf(processPom, "com.google.guava", "guava")).isEqualTo("31.1-jre");
        });
    }

    @Test
    void should_not_pin_when_several_versions_of_the_same_library_are_selected() throws Exception {
        appProject.getDependencies().add(createDependency("org.apache.commons", "commons-text", "1.9"));

        var configuration = aConfigurationWith(
                FragmentBuilder.aFragment().withValue("commons-text-1.9.jar").withType("JAR").exported(),
                FragmentBuilder.aFragment().withValue("commons-text-1.12.0.jar").withType("JAR").exported());

        withProcessPom(configuration, processPom -> {
            // ambiguous selection: Maven arbitration is left untouched
            assertThat(managedVersionOf(processPom, "org.apache.commons", "commons-text")).isNull();
        });
    }

    @Test
    void should_not_pin_when_group_id_is_ambiguous() throws Exception {
        appProject.setArtifacts(Set.of(anArtifact("asm", "asm", "3.3.1"),
                anArtifact("org.ow2.asm", "asm", "9.8")));

        var configuration = aConfigurationWith(
                FragmentBuilder.aFragment().withValue("asm-9.8.jar").withType("JAR").exported());

        withProcessPom(configuration, processPom -> {
            assertThat(processPom.getDependencyManagement()).isNull();
        });
    }

    @Test
    void should_not_pin_when_version_cannot_be_read_from_file_name() throws Exception {
        appProject.getDependencies().add(createDependency("org.example", "catalina", "1.0.0"));

        var configuration = aConfigurationWith(
                FragmentBuilder.aFragment().withValue("catalina.jar").withType("JAR").exported());

        withProcessPom(configuration, processPom -> {
            assertThat(processPom.getDependencyManagement()).isNull();
        });
    }

    @Test
    void should_not_pin_unselected_library() throws Exception {
        appProject.getDependencies().add(createDependency("org.example", "excluded-lib", "2.0.0"));

        var configuration = aConfigurationWith(
                FragmentBuilder.aFragment().withValue("excluded-lib-2.0.0.jar").withType("JAR").notExported());

        withProcessPom(configuration, processPom -> {
            assertThat(processPom.getDependencyManagement()).isNull();
        });
    }

    @Test
    void should_keep_library_when_one_of_its_versions_is_selected_and_pin_it() throws Exception {
        // two connectors bring the same library in different versions, the user unselects one of them
        appProject.getDependencies().add(createDependency("org.apache.commons", "commons-text", "1.9"));

        var configuration = ConfigurationBuilder.aConfiguration()
                .havingProcessDependencies(
                        FragmentContainerBuilder.aFragmentContainer("CONNECTOR")
                                .havingFragments(FragmentBuilder.aFragment()
                                        .withValue("commons-text-1.9.jar").withType("JAR").notExported()),
                        FragmentContainerBuilder.aFragmentContainer("OTHER")
                                .havingFragments(FragmentBuilder.aFragment()
                                        .withValue("commons-text-1.12.0.jar").withType("JAR").exported()))
                .build();

        withProcessPom(configuration, processPom -> {
            // the library must not be dropped from the pom...
            assertThat(processPom.getDependencies())
                    .anyMatch(dep -> "org.apache.commons:commons-text:jar".equals(dep.getManagementKey()));
            // ...and the selected version wins
            assertThat(managedVersionOf(processPom, "org.apache.commons", "commons-text")).isEqualTo("1.12.0");
        });
    }

    @Test
    void should_supersede_inherited_managed_version() throws Exception {
        appProject.getDependencies().add(createDependency("org.example", "managed-lib", null));
        var inherited = new DependencyManagement();
        inherited.addDependency(createDependency("org.example", "managed-lib", "1.0.0"));
        appProject.getModel().setDependencyManagement(inherited);

        var configuration = aConfigurationWith(
                FragmentBuilder.aFragment().withValue("managed-lib-3.0.0.jar").withType("JAR").exported());

        withProcessPom(configuration, processPom -> {
            assertThat(processPom.getDependencyManagement().getDependencies())
                    .filteredOn(dep -> "managed-lib".equals(dep.getArtifactId()))
                    .hasSize(1);
            assertThat(managedVersionOf(processPom, "org.example", "managed-lib")).isEqualTo("3.0.0");
        });
    }

    private Configuration aConfigurationWith(FragmentBuilder... fragments) {
        return ConfigurationBuilder.aConfiguration()
                .havingProcessDependencies(
                        FragmentContainerBuilder.aFragmentContainer("OTHER").havingFragments(fragments))
                .build();
    }

    private void withProcessPom(Configuration configuration, Consumer<Model> assertions) throws Exception {
        Optional<Pool> process = processRegistry.getProcess("SimpleProcessWithParameters", "1.0");
        var gen = ProcessPomGenerator.create(appProject, connectorImplementationRegistry);
        gen.withGeneratedPom(process.get(), configuration, pomAccess -> {
            assertions.accept(pomAccess.readPom());
            return null;
        });
    }

    private static String managedVersionOf(Model pom, String groupId, String artifactId) {
        return Optional.ofNullable(pom.getDependencyManagement())
                .map(DependencyManagement::getDependencies)
                .stream()
                .flatMap(List::stream)
                .filter(dep -> groupId.equals(dep.getGroupId()) && artifactId.equals(dep.getArtifactId()))
                .map(Dependency::getVersion)
                .findFirst()
                .orElse(null);
    }

    private static Artifact anArtifact(String groupId, String artifactId, String version) {
        return new DefaultArtifact(groupId, artifactId, version, "compile", "jar", null,
                new DefaultArtifactHandler("jar"));
    }

    private Dependency createDependency(String groupId, String artifactId, String version) {
        var dep = new Dependency();
        dep.setGroupId(groupId);
        dep.setArtifactId(artifactId);
        dep.setVersion(version);
        return dep;
    }

}
