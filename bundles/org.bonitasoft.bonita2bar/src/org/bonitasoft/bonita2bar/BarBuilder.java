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
package org.bonitasoft.bonita2bar;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.bonitasoft.bonita2bar.configuration.EnvironmentConfigurationBuilder;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.ConfigurationFactory;
import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.util.ModelLoader;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.engine.bpm.bar.InvalidBusinessArchiveFormatException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BarBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(BarBuilder.class);

    private List<BarArtifactProvider> providers = new ArrayList<>();

    private String environment;

    private Path localConfiguration;

    private ProcessRegistry processRegistry;

    BarBuilder(ProcessRegistry processRegistry, Path localConfiguration, String environment) {
        this.processRegistry = processRegistry;
        this.localConfiguration = localConfiguration;
        this.environment = environment;
    }

    /**
     * Build all processes found in the {@link ProcessRegistry}
     * 
     * @return a BuildResult
     * @throws BuildBarException
     */
    public BuildResult buildAll() throws BuildBarException {
        var result = new BuildResult();
        for (AbstractProcess process : processRegistry.getProcesses()) {
            if (process instanceof Pool) {
                buildBar((Pool) process, result);
            }
        }
        return result;
    }

    /**
     * Build a process given its name and version.
     * 
     * @param name A process name
     * @param version A process version
     * @return a BuildResult
     * @throws BuildBarException
     * @throws {@link IllegalArgumentException} when the given process name and version are not found in the {@link ProcessRegistry}
     */
    public BuildResult build(String name, String version) throws BuildBarException {
        var result = new BuildResult();
        var process = processRegistry.getProcess(name, version).orElseThrow(() -> new IllegalArgumentException(
                String.format("No process found in registry for %s (%s)", name, version)));
        buildBar(process, result);
        return result;
    }

    /**
     * Build a process.
     * 
     * @param process A process name
     * @return a BuildResult
     * @throws BuildBarException
     */
    public BuildResult build(Pool process) throws BuildBarException {
        var result = new BuildResult();
        buildBar(process, result);
        return result;
    }

    private void buildBar(Pool process, BuildResult result) throws BuildBarException {
        LOGGER.info("Building {} ({})...", process.getName(), process.getVersion());
        Optional<Configuration> configuration;
        try {
            configuration = getConfiguration(process, localConfiguration, environment);
        } catch (IOException e) {
            throw new BuildBarException("Failed to load local configuration", e);
        }
        if (!configuration.isPresent()) {
            LOGGER.warn("No configuration found for environment {}", environment);
        }
        final BusinessArchiveBuilder barBuilder = createBusinessArchiveBuilder();
        final EnvironmentConfigurationBuilder confBuilder = createEnvironmentConfigurationBuilder(process.getName(),
                process.getVersion(), environment);
        try {
            for (final BarArtifactProvider provider : providers) {
                Configuration defaultConfiguration = ConfigurationFactory.eINSTANCE.createConfiguration();
                defaultConfiguration.setName(environment);
                provider.build(barBuilder, process, configuration.orElse(defaultConfiguration));
                provider.configure(confBuilder, configuration.orElse(defaultConfiguration), process);
            }
            result.addBusinessArchive(barBuilder.done());
            result.addConfiguration(confBuilder.done());
        } catch (InvalidBusinessArchiveFormatException e) {
            String errorMessage = String.format("%s-%s build failed", process.getName(), process.getVersion());
            throw new BuildBarException(errorMessage, e);
        }
    }

    private EnvironmentConfigurationBuilder createEnvironmentConfigurationBuilder(String processName,
            String processVersion, String environment) {
        return new EnvironmentConfigurationBuilder(processName, processVersion, environment);
    }

    protected BusinessArchiveBuilder createBusinessArchiveBuilder() {
        return new BusinessArchiveBuilder().createNewBusinessArchive();
    }

    static Optional<Configuration> getConfiguration(Pool process, Path localConfiguration, String environment)
            throws IOException {
        final String uuid = getEObjectID(process);
        if ("Local".equals(environment) || environment == null) {// Use local environment
            final File configurationFolder = localConfiguration.toFile();
            final File confFile = new File(configurationFolder, String.format("%s.conf", uuid));
            if (!confFile.exists()) {
                return Optional.empty();
            }
            var resource = ModelLoader.getInstance().loadModel(URI.createFileURI(confFile.getAbsolutePath()));
            if (resource.getContents().isEmpty() || !(resource.getContents().get(0) instanceof Configuration)) {
                throw new IOException(String.format("No Configuration found in file %s", confFile.getAbsolutePath()));
            }
            final EList<EObject> contents = resource.getContents();
            return Optional.ofNullable((Configuration) contents.get(0));
        }
        return process.getConfigurations().stream().filter(conf -> Objects.equals(conf.getName(), environment))
                .findFirst();
    }

    private static String getEObjectID(final EObject eObject) {
        if (eObject == null) {
            return null;
        }
        final Resource eResource = eObject.eResource();
        if (eResource != null) {
            return eResource.getURIFragment(eObject);
        }
        return null;
    }

    public void register(BarArtifactProvider barArtifactProvider) {
        providers.add(barArtifactProvider);
    }

    public static String builderVersion() {
        URL url = Thread.currentThread().getContextClassLoader().getResource(JarFile.MANIFEST_NAME);
        try (var is = url.openStream()) {
            Manifest manifest = new Manifest(is);
            Attributes mainAttribs = manifest.getMainAttributes();
            return mainAttribs.getValue("Bundle-Version");
        } catch (Exception e) {
            // Silently ignore wrong manifests on classpath?
        }
        return null;
    }

}
