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
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import org.apache.maven.model.Build;
import org.apache.maven.model.Model;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry.ArtifactInfo;
import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.bpm.model.process.Connector;
import org.bonitasoft.bpm.model.process.Pool;

/**
 * Generates the temporary pom.xml dedicated to a specific {@link Pool} process.
 */
public class ProcessPomGenerator {

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
        try (var pomAccess = generatePom(process)) {
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
     * @return access to the generated pom.xml file (to be closed after use)
     * @throws IOException if an error occurs while generating
     */
    @SuppressWarnings("java:S2095")
    private ProcessPom generatePom(Pool process) throws IOException {
        // get target dir
        var target = Optional.ofNullable(applicationProject.getBuild()).map(Build::getDirectory)
                .filter(Objects::nonNull)
                .orElseGet(() -> applicationProject.getBasedir().toPath().resolve("target").toString());
        Files.createDirectories(Path.of(target));
        // create a temporary folder with the process name and version
        // pattern similar to the one in org.bonitasoft.bonita2bar.BuildResult.writeBar(Path, BusinessArchive)
        final String folderName = String.format("%s--%s", process.getName(), process.getVersion());
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
        pomAccess.writePom(model);
        return pomAccess;
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
            return connectorImplementations.stream().allMatch(connImpl -> {
                // check whether connector implementation is used in the process
                Predicate<Connector> matchesImpl = connDef -> connDef.getDefinitionId()
                        .equals(connImpl.getDefinitionId())
                        && connDef.getDefinitionVersion().equals(connImpl.getDefinitionVersion());
                return processUsedConnectors.stream().noneMatch(matchesImpl);
            });
        });
    }

}
