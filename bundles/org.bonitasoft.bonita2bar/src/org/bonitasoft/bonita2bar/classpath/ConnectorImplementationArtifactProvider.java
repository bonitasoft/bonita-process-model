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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.bonita2bar.BarArtifactProvider;
import org.bonitasoft.bonita2bar.BuildBarException;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry;
import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.bpm.connector.model.implementation.util.ConnectorImplementationResourceFactoryImpl;
import org.bonitasoft.bpm.connector.model.implementation.util.ConnectorImplementationXMLProcessor;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.DefinitionMapping;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.util.FragmentTypes;
import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectorImplementationArtifactProvider implements BarArtifactProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectorImplementationArtifactProvider.class);

    private ConnectorImplementationResourceFactoryImpl resourceFactory;
    private ConnectorImplementationXMLProcessor xmlProcessor;
    private ConnectorImplementationRegistry implRegistry;
    private String type;

    public ConnectorImplementationArtifactProvider(ConnectorImplementationRegistry implRegistry, String type) {
        this.type = type;
        this.implRegistry = implRegistry;
        this.resourceFactory = new ConnectorImplementationResourceFactoryImpl();
        this.xmlProcessor = new ConnectorImplementationXMLProcessor();
    }

    @Override
    public void build(BusinessArchiveBuilder builder, Pool process, Configuration configuration)
            throws BuildBarException {
        if (configuration == null) {
            return;
        }
        for (BarResource connectorImplemetation : createImplementationResources(configuration)) {
            if (FragmentTypes.CONNECTOR.equals(type)) {
                builder.addConnectorImplementation(connectorImplemetation);
            } else if (FragmentTypes.ACTOR_FILTER.equals(type)) {
                builder.addUserFilters(connectorImplemetation);
            } else {
                throw new IllegalStateException(String.format("Unknown resource type: %s", type));
            }
        }
    }

    private List<BarResource> createImplementationResources(Configuration configuration)
            throws BuildBarException {
        List<BarResource> implResources = new ArrayList<>();
        List<DefinitionMapping> mappings = configuration.getDefinitionMappings().stream()
                .filter(mapping -> Objects.equals(type, mapping.getType()))
                .filter(mapping -> java.util.Optional.ofNullable(mapping.getImplementationId()).isPresent()
                        && java.util.Optional.ofNullable(mapping.getDefinitionVersion()).isPresent())
                .collect(Collectors.toList());

        for (final DefinitionMapping association : mappings) {
            final String connectorImplementationFilename = toConnectorImplementationFilename(
                    association.getImplementationId(), association.getImplementationVersion(), true);
            final String implId = association.getImplementationId();
            final String implVersion = association.getImplementationVersion();
            var implementation = implRegistry.find(implId, implVersion)
                    .orElseThrow(
                            () -> new BuildBarException(String.format("Implementation %s (%s) not found in repository",
                                    association.getImplementationId(), association.getImplementationVersion())));
            try {
                BarResource connectorImpl = createImplementationResource(connectorImplementationFilename,
                        implementation);
                implResources.add(connectorImpl);
            } catch (IOException e) {
                throw new BuildBarException(String.format("Failed to add implementation %s-%s in bar file",
                        association.getImplementationId(), association.getImplementationVersion()), e);
            }
        }
        return implResources;
    }

    private BarResource createImplementationResource(final String connectorImplementationFilename,
            final ConnectorImplementation implementation) throws IOException {
        if (FragmentTypes.CONNECTOR.equals(type)) {
            LOGGER.info("Adding connector implementation {}...", connectorImplementationFilename);
            return newBarResource(connectorImplementationFilename, implementation);
        } else if (FragmentTypes.ACTOR_FILTER.equals(type)) {
            LOGGER.info("Adding actor filter implementation {}...", connectorImplementationFilename);
            return newBarResource(connectorImplementationFilename, implementation);
        }
        return null;
    }

    private BarResource newBarResource(final String connectorImplementationFilename,
            final ConnectorImplementation implementation) throws IOException {
        return new BarResource(connectorImplementationFilename,
                toXMLString(implementation).getBytes(StandardCharsets.UTF_8));
    }

    private String toXMLString(final ConnectorImplementation connectorImplementation) throws IOException {
        final XMLResource resource = (XMLResource) resourceFactory.createResource(URI.createURI("tmp.impl"));
        resource.getContents().add(connectorImplementation);
        final Map<Object, Object> options = resource.getDefaultSaveOptions();
        options.put(XMLResource.OPTION_ENCODING, "UTF-8");
        try {
            return xmlProcessor.saveToString(resource, options).replace("implementation:ConnectorImplementation",
                    "implementation:connectorImplementation");
        } finally {
            resource.delete(options);
        }
    }

    private static String toConnectorImplementationFilename(final String implementationId,
            final String implementationVersion, final boolean inculdeExtension) {
        return implementationId + "-" + implementationVersion + (inculdeExtension ? ".impl" : "");
    }

}
