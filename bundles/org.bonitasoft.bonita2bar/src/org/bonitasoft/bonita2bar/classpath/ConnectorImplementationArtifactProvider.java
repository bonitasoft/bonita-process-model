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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.bonita2bar.AssemblyExecutionException;
import org.bonitasoft.bonita2bar.BarArtifactProvider;
import org.bonitasoft.bonita2bar.BuildBarException;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry;
import org.bonitasoft.bonita2bar.SingleAssemblyExecutor;
import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.bpm.connector.model.implementation.util.ConnectorImplementationResourceFactoryImpl;
import org.bonitasoft.bpm.connector.model.implementation.util.ConnectorImplementationXMLProcessor;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.DefinitionMapping;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.util.FileUtil;
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

    private SingleAssemblyExecutor singleAssemblyExecutor;

    public ConnectorImplementationArtifactProvider(SingleAssemblyExecutor singleAssemblyExecutor,
            ConnectorImplementationRegistry implRegistry,
            String type) {
        this.singleAssemblyExecutor = singleAssemblyExecutor;
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
        for (ConnectorImplementationResources connector : createImplementationResources(configuration)) {
            if (FragmentTypes.CONNECTOR.equals(type)) {
                builder.addConnectorImplementation(connector.getImplemetation());
            } else if (FragmentTypes.ACTOR_FILTER.equals(type)) {
                builder.addUserFilters(connector.getImplemetation());
            } else {
                throw new IllegalStateException(String.format("Unknown resource type: %s", type));
            }
            for (final BarResource barResource : connector.getDependencies()) {
                builder.addClasspathResource(barResource);
            }
        }
    }

    private List<ConnectorImplementationResources> createImplementationResources(Configuration configuration)
            throws BuildBarException {
        List<ConnectorImplementationResources> implResources = new ArrayList<>();
        List<DefinitionMapping> mappings = configuration.getDefinitionMappings().stream()
                .filter(mapping -> Objects.equals(type, mapping.getType()))
                .filter(mapping -> java.util.Optional.ofNullable(mapping.getImplementationId()).isPresent()
                        && java.util.Optional.ofNullable(mapping.getDefinitionVersion()).isPresent())
                .collect(Collectors.toList());

        for (final DefinitionMapping association : mappings) {
            ConnectorImplementationResources connectorImpl = new ConnectorImplementationResources();
            final String connectorImplementationFilename = toConnectorImplementationFilename(
                    association.getImplementationId(), association.getImplementationVersion(), true);
            final String implId = association.getImplementationId();
            final String implVersion = association.getImplementationVersion();
            var implementation = implRegistry.find(implId, implVersion)
                    .orElseThrow(
                            () -> new BuildBarException(String.format("Implementation %s (%s) not found in repository",
                                    association.getImplementationId(), association.getImplementationVersion())));
            var artifact = implRegistry.findArtifact(implId, implVersion)
                    .orElseThrow(
                            () -> new BuildBarException(String.format("No Artifact found for implementation %s (%s)",
                                    association.getImplementationId(), association.getImplementationVersion())));
            Path targetPath = null;
            try {
                connectorImpl.setImplemetation(
                        createImplementationResource(connectorImplementationFilename, implementation));
                targetPath = singleAssemblyExecutor.execute(artifact);
                if (targetPath != null && Files.exists(targetPath)) {
                    for (var jarFile : targetPath.toFile().listFiles(f -> f.getName().endsWith(".jar"))) {
                        var barResource = new BarResource(jarFile.getName(), Files.readAllBytes(jarFile.toPath()));
                        LOGGER.info("Adding {} to bar classpath...", jarFile.getName());
                        connectorImpl.addDependency(barResource);
                    }
                }
                implResources.add(connectorImpl);
            } catch (IOException e) {
                String message = String.format("Failed to add implementation %s-%s in bar file",
                        association.getImplementationId(), association.getImplementationVersion());
                throw new BuildBarException(message, e);
            } catch (AssemblyExecutionException e) {
                String message = String.format("Failed to resolve implementation %s-%s dependencies",
                        association.getImplementationId(), association.getImplementationVersion());
                throw new BuildBarException(message, e);
            } finally {
                if (targetPath != null && Files.exists(targetPath)) {
                    try {
                        FileUtil.deleteDir(targetPath);
                    } catch (IOException e) {
                        LOGGER.warn(e.getMessage());
                    }
                }
            }
        }
        return implResources;
    }

    //    private void addProcessDependencies(final ConnectorImplementationResources implResources,
    //            final Configuration configuration, final ConnectorImplementation implementation) throws IOException {
    //        final Optional<FragmentContainer> fragmentContainer = configuration.getProcessDependencies().stream()
    //                .filter(fc -> fc.getId().equals(type)).findFirst();
    //        if (fragmentContainer.isPresent()) {
    //            final Optional<FragmentContainer> implementationContainer = fragmentContainer.get().getChildren().stream()
    //                    .filter(fragment -> Objects.equals(fragment.getId(), toConnectorImplementationFilename(
    //                            implementation.getImplementationId(), implementation.getImplementationVersion(), false)))
    //                    .findFirst();
    //            if (implementationContainer.isPresent()) {
    //                for (final Fragment fragment : implementationContainer.get().getFragments().stream()
    //                        .filter(Fragment::isExported).collect(Collectors.toList())) {
    //                    File jarFile = classpathResolver.findJarFile(fragment.getValue());
    //                    if (jarFile != null && jarFile.exists()) {
    //                        LOGGER.info("Adding {} to bar classpath...", jarFile.getName());
    //                        implResources.addDependency(
    //                                new BarResource(jarFile.getName(), Files.readAllBytes(jarFile.toPath())));
    //                    } else {
    //                        LOGGER.warn("Expected jar file {} not found in classpath.", fragment.getValue());
    //                    }
    //                }
    //            }
    //        }
    //    }

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
