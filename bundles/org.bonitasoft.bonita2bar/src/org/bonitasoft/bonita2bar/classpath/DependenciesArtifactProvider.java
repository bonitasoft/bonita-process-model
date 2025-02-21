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
package org.bonitasoft.bonita2bar.classpath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.bonitasoft.bonita2bar.BarArtifactProvider;
import org.bonitasoft.bonita2bar.BuildBarException;
import org.bonitasoft.bonita2bar.MavenExecutor;
import org.bonitasoft.bonita2bar.process.pomgen.ProcessPom;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

/**
 * Provides artifacts for building the BAR file, based on the dependencies from maven and connectors used by the process.
 */
public class DependenciesArtifactProvider implements BarArtifactProvider {

    /**
     * Format for building the profile name used for a specific environment.
     */
    private static final String ENV_PROFILE_FORMAT = "env-%s";

    /** Executes maven command */
    private MavenExecutor mavenExecutor;

    /**
     * Default Constructor.
     * 
     * @param maventExecutor the maven executor
     */
    public DependenciesArtifactProvider(MavenExecutor maventExecutor) {
        this.mavenExecutor = maventExecutor;
    }

    @Override
    public void build(BusinessArchiveBuilder builder, Pool process, ProcessPom pomAccess, Configuration configuration)
            throws BuildBarException {
        try {
            var pom = pomAccess.readPom();
            File processPomFolder = pom.getPomFile().getParentFile();
            var dependenciesFolder = new File(processPomFolder, "dependencies");
            var profileToUse = String.format(ENV_PROFILE_FORMAT, configuration.getName());

            mavenExecutor.execute(pom.getPomFile(), List.of("dependency:copy-dependencies"),
                    Map.of("outputDirectory", "./" + dependenciesFolder.getName()),
                    List.of(profileToUse), () -> "Failed to build dependencies for process " + process.getName() + "-"
                            + process.getVersion());

            // explore all files in the dependencies folder
            if (dependenciesFolder.exists()) {
                try (Stream<Path> walker = Files.walk(dependenciesFolder.toPath())) {
                    List<Path> files = walker
                            .filter(Files::isRegularFile).toList();
                    for (var file : files) {
                        try {
                            BarResource barResource = new BarResource(file.getFileName().toString(),
                                    Files.readAllBytes(file));
                            if (file.toString().endsWith(".jar")) {
                                builder.addClasspathResource(barResource);
                            } else {
                                builder.addExternalResource(barResource);
                            }
                        } catch (IOException e) {
                            throw new BuildBarException(String.format("Unable to get content of the %s ", file),
                                    e);
                        }
                    }
                }
            }
        } catch (IOException | XmlPullParserException e) {
            String message = String.format("Failed to add dependencies in bar %s-%s.bar.",
                    process.getName(), process.getVersion());
            throw new BuildBarException(message, e);
        }

    }

}
