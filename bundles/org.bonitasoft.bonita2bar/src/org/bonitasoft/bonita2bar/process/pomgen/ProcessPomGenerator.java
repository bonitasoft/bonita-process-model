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
import java.util.Objects;
import java.util.Optional;

import org.apache.maven.model.Build;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.bpm.model.process.Pool;

/**
 * Generates the temporary pom.xml dedicated to a specific {@link Pool} process.
 */
public class ProcessPomGenerator {

    private MavenProject applicationProject;
    private Pool process;

    /**
     * Default Constructor.
     */
    private ProcessPomGenerator() {
    }

    /**
     * Creates a new generator
     * 
     * @param applicationProject the application maven project
     * @param process the process to generate the pom for
     * @return a new instance of {@link ProcessPomGenerator}
     */
    public static ProcessPomGenerator create(MavenProject applicationProject, Pool process) {
        var gen = new ProcessPomGenerator();
        gen.applicationProject = applicationProject;
        gen.process = process;
        return gen;
    }

    /**
     * Generates the temporary pom.xml dedicated to a specific {@link Pool} process.
     * 
     * @return access to the generated pom.xml file
     * @throws IOException if an error occurs while generating
     */
    public ProcessPom generatePom() throws IOException {
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
        pomAccess.writePom(model);
        return pomAccess;
    }

}
