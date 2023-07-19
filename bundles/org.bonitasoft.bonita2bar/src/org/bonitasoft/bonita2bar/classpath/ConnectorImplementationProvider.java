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

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import java.util.jar.JarFile;
import java.util.stream.Stream;

import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.bpm.connector.model.implementation.DocumentRoot;
import org.bonitasoft.bpm.connector.model.implementation.util.ConnectorImplementationResourceFactoryImpl;
import org.bonitasoft.plugin.analyze.report.model.DependencyReport;
import org.eclipse.emf.ecore.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectorImplementationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectorImplementationProvider.class);

    private DependencyReport dependencyReport;

    private static final ConnectorImplementationResourceFactoryImpl RESOURCE_FACTORY = new ConnectorImplementationResourceFactoryImpl();

    public ConnectorImplementationProvider(DependencyReport dependencyReport) {
        this.dependencyReport = dependencyReport;
    }

    private static ConnectorImplementation loadImplementation(
            org.bonitasoft.plugin.analyze.report.model.Implementation impl) {
        try (var jarFile = new JarFile(impl.getArtifact().getFile());
                var is = jarFile.getInputStream(jarFile.getEntry(impl.getJarEntry()))) {
            Resource resource = RESOURCE_FACTORY.createResource(null);
            resource.load(is, Collections.emptyMap());
            return ((DocumentRoot) resource.getContents().get(0)).getConnectorImplementation();
        } catch (IOException e) {
            LOGGER.error("Failed to parse connector implementation", e);
            return null;
        }
    }

    public ConnectorImplementation getConnectorImplementation(String id, String version) {
        return Stream.concat(
                dependencyReport.getConnectorImplementations().stream(),
                dependencyReport.getFilterImplementations().stream())
                .filter(impl -> impl.getImplementationId().equals(id))
                .filter(impl -> impl.getImplementationVersion().equals(version))
                .map(ConnectorImplementationProvider::loadImplementation)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

}
