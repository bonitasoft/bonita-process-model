/**
 * Copyright (C) 2023 Bonitasoft S.A.
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

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.jar.JarFile;

import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.bpm.connector.model.implementation.DocumentRoot;
import org.bonitasoft.bpm.connector.model.implementation.util.ConnectorImplementationResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ConnectorImplementationRegistry {

    static final Logger LOGGER = LoggerFactory.getLogger(ConnectorImplementationRegistry.class);
    static final ConnectorImplementationResourceFactoryImpl RESOURCE_FACTORY = new ConnectorImplementationResourceFactoryImpl();

    Optional<ConnectorImplementation> find(String id, String version);

    /**
     * {@link ConnectorImplementationRegistry} factory method.
     * 
     * @param implementations A lsit of {@link ConnectorImplementationJar}
     * @return A default implementation of {@link ConnectorImplementationRegistry} that search for
     *         connector implementations from the given list of {@link ConnectorImplementationJar}.
     */
    public static ConnectorImplementationRegistry of(List<ConnectorImplementationJar> implementations) {
        return new ConnectorImplementationRegistry() {

            @Override
            public Optional<ConnectorImplementation> find(String id, String version) {
                return implementations.stream()
                        .filter(impl -> Objects.equals(impl.getId(), id) && Objects.equals(impl.getVersion(), version))
                        .map(this::loadImplementation).filter(Objects::nonNull).findFirst();
            }

            private ConnectorImplementation loadImplementation(ConnectorImplementationJar implementation) {
                try (var jar = new JarFile(implementation.getJarFile());
                        var is = jar.getInputStream(jar.getEntry(implementation.getEntry()))) {
                    Resource resource = RESOURCE_FACTORY.createResource(null);
                    resource.load(is, Collections.emptyMap());
                    return ((DocumentRoot) resource.getContents().get(0)).getConnectorImplementation();
                } catch (IOException e) {
                    LOGGER.error("Failed to parse connector implementation", e);
                    return null;
                }
            }
        };
    }

    public static class ConnectorImplementationJar {

        private final String id;
        private final String version;
        private final File jarFile;
        private final String entry;

        public static ConnectorImplementationJar of(String id, String version, File jarFile, String entry) {
            return new ConnectorImplementationJar(id, version, jarFile, entry);
        }

        private ConnectorImplementationJar(String id, String version, File jarFile, String entry) {
            this.id = id;
            this.version = version;
            this.jarFile = jarFile;
            this.entry = entry;
        }

        public String getId() {
            return id;
        }

        public String getVersion() {
            return version;
        }

        public File getJarFile() {
            return jarFile;
        }

        public String getEntry() {
            return entry;
        }

        @Override
        public int hashCode() {
            return Objects.hash(entry, id, jarFile, version);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ConnectorImplementationJar other = (ConnectorImplementationJar) obj;
            return Objects.equals(entry, other.entry) && Objects.equals(id, other.id)
                    && Objects.equals(jarFile, other.jarFile) && Objects.equals(version, other.version);
        }

    }

}
