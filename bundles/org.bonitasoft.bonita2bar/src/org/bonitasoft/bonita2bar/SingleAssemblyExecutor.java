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

import java.nio.file.Path;
import java.util.Properties;

import org.apache.maven.artifact.Artifact;

/**
 * Facade use to execute the Maven assembly plugin when add extension to BAR
 * files.
 */
public interface SingleAssemblyExecutor {

    /**
     * Runs the Maven Assembly Plugin single goal to resolve the given artifact classpath
     * 
     * @param artifact The extension artifact to resolve
     * @return the {@link Path} where the artifact and its dependencies is located
     * @throws AssemblyExecutionException When the assembly execution fails
     */
    Path execute(Artifact artifact) throws AssemblyExecutionException;

    default String getGoal() {
        return "assembly:single@bar-extension-classpath";
    }

    default Properties getUserProperties(String groupId, String artifactId) {
        var properties = new Properties();
        properties.setProperty("extension.groupId", groupId);
        properties.setProperty("extension.artifactId", artifactId);
        return properties;
    }

}
