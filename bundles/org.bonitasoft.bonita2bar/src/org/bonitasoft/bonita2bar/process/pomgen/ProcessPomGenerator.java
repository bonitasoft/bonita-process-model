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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.maven.model.Build;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.Model;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry.ArtifactInfo;
import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.Fragment;
import org.bonitasoft.bpm.model.process.Connector;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.util.FragmentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generates the temporary pom.xml dedicated to a specific {@link Pool} process.
 */
public class ProcessPomGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessPomGenerator.class);

    private MavenProject applicationProject;
    private ConnectorImplementationRegistry connectorImplementationRegistry;

    /**
     * Default Constructor.
     */
    private ProcessPomGenerator() {
    }

    /**
     * Creates a new generator
     * 
     * @param applicationProject the application maven project
     * @param connectorImplementationRegistry the connector implementation registry
     * @return a new instance of {@link ProcessPomGenerator}
     */
    public static ProcessPomGenerator create(MavenProject applicationProject,
            ConnectorImplementationRegistry connectorImplementationRegistry) {
        var gen = new ProcessPomGenerator();
        gen.applicationProject = applicationProject;
        gen.connectorImplementationRegistry = connectorImplementationRegistry;
        return gen;
    }

    /**
     * Consumer of the generated pom.xml file.
     *
     * @param <R> the expected result type (may be {@link Void})
     * @param <E> the exception type
     */
    @FunctionalInterface
    public static interface ProcessPomConsumer<R, E extends Exception> {

        R consume(ProcessPom pomAccess) throws E;
    }

    /**
     * Generates the temporary pom.xml dedicated to a specific {@link Pool} process.
     * Then executes the consumer with the generated pom.xml file.
     *
     * @param process the process to generate the pom for
     * @param consumer the consumer to execute with access to the generated pom.xml file
     * @return the result of the consumer
     * @throws IOException if an error occurs while generating
     */
    public <R, E extends Exception> R withGeneratedPom(Pool process, ProcessPomConsumer<R, E> consumer)
            throws IOException, E {
        return withGeneratedPom(process, null, consumer);
    }

    /**
     * Generates the temporary pom.xml dedicated to a specific {@link Pool} process.
     * Then executes the consumer with the generated pom.xml file.
     *
     * @param process the process to generate the pom for
     * @param configuration the configuration to use for filtering dependencies (can be null)
     * @param consumer the consumer to execute with access to the generated pom.xml file
     * @return the result of the consumer
     * @throws IOException if an error occurs while generating
     */
    public <R, E extends Exception> R withGeneratedPom(Pool process, Configuration configuration,
            ProcessPomConsumer<R, E> consumer)
            throws IOException, E {
        try (var pomAccess = generatePom(process, configuration)) {
            return consumer.consume(pomAccess);
        }
    }

    /**
     * Generates the temporary pom.xml dedicated to a specific {@link Pool} process.
     * <b>Always invoke this method in a try-with-resources block</b> to ensure the generated pom.xml file is closed properly.
     * <br/>
     * This method is private to ensure the correct usage and ignore java:S2095 warning.
     *
     * @param process the process to generate the pom for
     * @param configuration the configuration to use for filtering dependencies (can be null)
     * @return access to the generated pom.xml file (to be closed after use)
     * @throws IOException if an error occurs while generating
     */
    @SuppressWarnings("java:S2095")
    private ProcessPom generatePom(Pool process, Configuration configuration) throws IOException {
        // get target dir
        var target = Optional.ofNullable(applicationProject.getBuild()).map(Build::getDirectory)
                .filter(Objects::nonNull)
                .orElseGet(() -> applicationProject.getBasedir().toPath().resolve("target").toString());
        Files.createDirectories(Path.of(target));
        // create a temporary folder with the process name and version
        // pattern similar to the one in org.bonitasoft.bonita2bar.BuildResult.writeBar(Path, BusinessArchive)
        String notNormalizedName = String.format("%s--%s", process.getName(), process.getVersion());
        final String folderName = notNormalizedName.toLowerCase().replaceAll("[^a-z0-9\\-]", "-");
        var tempFolderPath = Files.createTempDirectory(Path.of(target), folderName + "_");
        // access to pom.xml file
        var pomAccess = new ProcessPom(tempFolderPath);
        // generate the pom.xml file content from applicationProject
        var model = applicationProject.getModel().clone();
        // keep the process version in the artifact name and use the app version, because we don't know the format of process version
        model.setArtifactId(folderName);
        // update parent relative path
        Optional.ofNullable(model.getParent().getRelativePath()).ifPresent(relPath -> {
            var fixedPath = tempFolderPath
                    .relativize(applicationProject.getBasedir().toPath().resolve(Path.of(relPath)));
            model.getParent().setRelativePath(fixedPath.toString());
        });
        // remove connector dependencies from other processes
        filterUnusedConnectorDependencies(model, process);
        // remove zip dependencies (custom extensions deployed on their own and application pages handled otherwise)
        filterZipDependencies(model);
        // remove dependencies excluded by the process configuration (exported=false)
        filterExcludedDependencies(model, configuration);
        // pin the versions selected by the user in the process configuration
        injectDependencyManagement(model, configuration);
        pomAccess.writePom(model);
        return pomAccess;
    }

    /**
     * Remove zip dependencies.
     * 
     * @param model the maven model to update
     */
    private void filterZipDependencies(Model model) {
        model.getDependencies().removeIf(dep -> "zip".equalsIgnoreCase(dep.getType()));
    }

    /**
     * Remove dependencies that are explicitly excluded (exported=false) in the process configuration.
     * <p>
     * Matches Maven dependencies to configuration fragments by comparing the dependency's
     * artifactId with the base name extracted from the fragment's JAR filename.
     * Exclusion (exported=false) takes priority over inclusion (exported=true) when the
     * same base name appears in multiple fragments.
     * </p>
     *
     * @param model the maven model to update
     * @param configuration the configuration containing exported fragment information (can be null)
     */
    private void filterExcludedDependencies(Model model, Configuration configuration) {
        if (configuration == null) {
            return;
        }
        var containers = configuration.getProcessDependencies();
        if (containers.isEmpty()) {
            return;
        }

        var allFragments = containers.stream()
                .flatMap(FragmentUtils::walkAllFragments)
                .toList();

        if (allFragments.isEmpty()) {
            return;
        }

        // Collect base names of explicitly non-exported fragments
        Set<String> excludedBases = allFragments.stream()
                .filter(f -> !f.isExported())
                .map(f -> FragmentUtils.extractArtifactBase(f.getValue()))
                .collect(Collectors.toSet());

        // A library may appear several times with different versions, typically when two connectors
        // declare it. Selecting one of those versions must keep the library: the arbitration is then
        // done by injectDependencyManagement, not by dropping the dependency altogether.
        Set<String> exportedBases = allFragments.stream()
                .filter(Fragment::isExported)
                .map(f -> FragmentUtils.extractArtifactBase(f.getValue()))
                .collect(Collectors.toSet());

        model.getDependencies().removeIf(dep -> {
            String artifactId = dep.getArtifactId();
            // If this dependency's artifactId matches an excluded base name, and no other fragment of
            // the same library is selected, remove it
            return excludedBases.contains(artifactId) && !exportedBases.contains(artifactId);
        });
    }

    /**
     * Pin, in the generated pom, the versions selected by the user in the process configuration.
     * <p>
     * Every selected fragment carries a jar file name, hence a version. When a library has exactly one
     * selected version, a {@code dependencyManagement} entry is added so that Maven resolves that very
     * version - including for a transitive dependency of a connector. This also guarantees that the copied
     * jar file name matches the fragment value, which is what
     * {@code DependenciesArtifactProvider} expects to keep the jar in the BAR.
     * </p>
     * <p>
     * A {@code dependencyManagement} entry only drives transitive dependencies and declarations without a
     * version, so the version of a matching direct dependency is set as well.
     * </p>
     * <p>
     * Nothing is pinned when the version cannot be read from the file name, when the group id cannot be
     * resolved unambiguously, or when several versions of the same library are selected - in that last
     * case the user arbitrates by unselecting the unwanted one.
     * </p>
     *
     * @param model the maven model to update
     * @param configuration the configuration holding the user selection (can be null)
     */
    private void injectDependencyManagement(Model model, Configuration configuration) {
        // library base name -> versions explicitly selected by the user. The configuration wizard warns
        // on the very same grouping, so that what the user is told matches what is pinned here.
        Map<String, Set<String>> selectedVersions = FragmentUtils.selectedVersionsByArtifactBase(configuration);
        if (selectedVersions.isEmpty()) {
            return;
        }
        Map<String, Set<String>> groupIds = groupIdsByArtifactId(model);
        List<Dependency> managedDependencies = new ArrayList<>();
        selectedVersions.forEach((artifactId, versions) -> {
            if (versions.size() > 1) {
                LOGGER.warn(
                        "Several versions of '{}' are selected in the configuration ({}). Maven arbitration"
                                + " applies. Unselect the unwanted one to choose the embedded version.",
                        artifactId, String.join(", ", versions));
                return;
            }
            Set<String> candidates = groupIds.getOrDefault(artifactId, Set.of());
            if (candidates.size() != 1) {
                if (candidates.size() > 1) {
                    LOGGER.warn("Cannot pin the version of '{}': several group ids match ({}).", artifactId,
                            String.join(", ", candidates));
                }
                return;
            }
            var dependency = new Dependency();
            dependency.setGroupId(candidates.iterator().next());
            dependency.setArtifactId(artifactId);
            dependency.setVersion(versions.iterator().next());
            managedDependencies.add(dependency);
        });
        if (managedDependencies.isEmpty()) {
            return;
        }
        var dependencyManagement = model.getDependencyManagement();
        if (dependencyManagement == null) {
            dependencyManagement = new DependencyManagement();
            model.setDependencyManagement(dependencyManagement);
        }
        for (var dependency : managedDependencies) {
            // an explicit entry supersedes any entry inherited from the application pom
            dependencyManagement.getDependencies().removeIf(existing -> isSameArtifact(existing, dependency));
            dependencyManagement.addDependency(dependency);
            // dependencyManagement is ignored for a direct dependency holding an explicit version
            model.getDependencies().stream()
                    .filter(direct -> isSameArtifact(direct, dependency))
                    .forEach(direct -> direct.setVersion(dependency.getVersion()));
            LOGGER.debug("Pinning {}:{} to version {} for this process.", dependency.getGroupId(),
                    dependency.getArtifactId(), dependency.getVersion());
        }
    }

    private static boolean isSameArtifact(Dependency candidate, Dependency reference) {
        return Objects.equals(candidate.getGroupId(), reference.getGroupId())
                && Objects.equals(candidate.getArtifactId(), reference.getArtifactId())
                && candidate.getClassifier() == null
                && (candidate.getType() == null || "jar".equals(candidate.getType()));
    }

    /**
     * Index the group ids known for each artifact id, from the resolved artifacts of the application
     * project and from the dependencies declared in the generated model.
     *
     * @param model the maven model being generated
     * @return the group ids indexed by artifact id
     */
    private Map<String, Set<String>> groupIdsByArtifactId(Model model) {
        Map<String, Set<String>> groupIds = new HashMap<>();
        var artifacts = applicationProject.getArtifacts();
        if (artifacts != null) {
            artifacts.forEach(artifact -> index(groupIds, artifact.getArtifactId(), artifact.getGroupId()));
        }
        model.getDependencies().forEach(dep -> index(groupIds, dep.getArtifactId(), dep.getGroupId()));
        return groupIds;
    }

    private static void index(Map<String, Set<String>> groupIds, String artifactId, String groupId) {
        // an unresolved property cannot be used as a group id
        if (artifactId == null || groupId == null || groupId.contains("${")) {
            return;
        }
        groupIds.computeIfAbsent(artifactId, k -> new LinkedHashSet<>()).add(groupId);
    }

    /**
     * Remove connector dependencies from other processes, with help of the dependency report.
     *
     * @param model the maven model to update
     * @param process the process to keep the dependencies for
     */
    private void filterUnusedConnectorDependencies(Model model, Pool process) {
        List<Connector> processUsedConnectors = new ArrayList<>();
        process.eAllContents().forEachRemaining(obj -> {
            if (obj instanceof Connector c) {
                processUsedConnectors.add(c);
            }
        });

        model.getDependencies().removeIf(dep -> {
            // get related connector implementation(s) (a same dependency may be multiple connector implementations, like the REST one)
            List<ConnectorImplementation> connectorImplementations = connectorImplementationRegistry
                    .findAll(ArtifactInfo.matchesDep(dep));
            boolean isConnectorDep = !connectorImplementations.isEmpty();
            return isConnectorDep && connectorImplementations.stream().allMatch(connImpl -> {
                // check whether connector implementation is used in the process
                Predicate<Connector> matchesImpl = connDef -> connDef.getDefinitionId()
                        .equals(connImpl.getDefinitionId())
                        && connDef.getDefinitionVersion().equals(connImpl.getDefinitionVersion());
                return processUsedConnectors.stream().noneMatch(matchesImpl);
            });
        });
    }

}
