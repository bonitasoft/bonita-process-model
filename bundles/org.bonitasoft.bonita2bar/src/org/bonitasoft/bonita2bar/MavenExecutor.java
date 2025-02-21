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
package org.bonitasoft.bonita2bar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.apache.maven.cli.MavenCli;

/**
 * Allows to execute maven commands.
 * Since maven can be launched in many ways, this interface offers the opportunity to choose how maven commands are launched
 * (e.g. with Eclipse M2e, with Maven CLI configured with Plexus...).
 */
@FunctionalInterface
public interface MavenExecutor {

    /**
     * Execute maven commands on a pom file.
     * 
     * @param pomFile the pom file
     * @param goals the goals to execute
     * @param properties user properties to pass as -D arguments
     * @param activeProfiles the active profiles to use
     * @param errorMessageBase a supplier of the base error message to use in case of failure
     * @throws BuildBarException if an error occurs or the execution fails
     */
    void execute(File pomFile, List<String> goals, Map<String, String> properties, List<String> activeProfiles,
            Supplier<String> errorMessageBase)
            throws BuildBarException;

    /**
     * Get the implementation based on CLI and configured with Plexus
     * 
     * @return default implementation for a maven environment
     */
    public static MavenExecutor getCliImplementation() {
        return (File pomFile, List<String> goals, Map<String, String> properties, List<String> activeProfiles,
                Supplier<String> errorMessageBase) -> {
            File pomFolder = pomFile.getParentFile();
            // use the maven cli to execute
            Stream<String> argumentsStream = Stream.concat(
                    goals.stream(),
                    Stream.concat(
                            properties.entrySet().stream().map(e -> "-D" + e.getKey() + "=" + e.getValue()),
                            activeProfiles.stream().map(p -> "-P" + p)));
            var args = argumentsStream.toArray(String[]::new);
            var cli = new MavenCli();
            System.setProperty(MavenCli.MULTIMODULE_PROJECT_DIRECTORY, pomFolder.getAbsolutePath());
            // keep error to display it in the exception
            try (ByteArrayOutputStream errStream = new ByteArrayOutputStream()) {
                // something may go wrong when loading the mojo here, when plexus is not configured correctly.
                int buildResult = cli.doMain(args, pomFolder.getAbsolutePath(), System.out, new PrintStream(errStream));
                if (buildResult != 0) {
                    throw new BuildBarException(errorMessageBase.get()
                            + "\n" + errStream.toString());
                }
            } catch (IOException e) {
                throw new BuildBarException(errorMessageBase.get(), e);
            }
        };
    }

}
