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
package org.bonitasoft.bonita2bar.resources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import org.bonitasoft.bonita2bar.BarArtifactProvider;
import org.bonitasoft.bonita2bar.BarBuilder;
import org.bonitasoft.bonita2bar.BuildBarException;
import org.bonitasoft.bonita2bar.ProcessRegistry;
import org.bonitasoft.bonita2bpmn.extension.BonitaModelExporterImpl;
import org.bonitasoft.bonita2bpmn.transfo.BonitaToBPMNExporter;
import org.bonitasoft.bonita2bpmn.transfo.ConnectorTransformationXSLProvider;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.util.ModelSearch;
import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BPMN2ArtifactProvider implements BarArtifactProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(BPMN2ArtifactProvider.class);
    private ProcessRegistry processRegistry;
    private Path workingDirectory;

    public BPMN2ArtifactProvider(ProcessRegistry processRegistry, Path workingDirectory) {
        this.processRegistry = processRegistry;
        this.workingDirectory = workingDirectory;
    }

    @Override
    public void build(BusinessArchiveBuilder builder, Pool process, Configuration configuration)
            throws BuildBarException {
        LOGGER.info("Adding BPMN2 model...");
        ModelSearch modelSearch = new ModelSearch(processRegistry::getProcesses);
        BonitaToBPMNExporter bonitaToBPMNExporter = new BonitaToBPMNExporter();
        Path destFile = null;
        try {
            if (!Files.exists(workingDirectory)) {
                Files.createDirectories(workingDirectory);
            }
            destFile = Files.createTempFile(workingDirectory, "process", ".bpmn");
            var resource = process.eResource();
            if (resource != null) {
                bonitaToBPMNExporter.export(new BonitaModelExporterImpl(resource, modelSearch), modelSearch,
                        Collections::emptyList, destFile.toFile(), ConnectorTransformationXSLProvider.DEFAULT,
                        BarBuilder.builderVersion());
                builder.addExternalResource(new BarResource("process.bpmn", Files.readAllBytes(destFile)));
            } else {
                LOGGER.warn("Process {} ({}) is not contained in a Resource. BPMN file will not be added to the bar.",
                        process.getName(), process.getVersion());
            }
        } catch (IOException e) {
            throw new BuildBarException("Failed to build bpmn2 file", e);
        } finally {
            if (destFile != null) {
                try {
                    Files.deleteIfExists(destFile);
                } catch (IOException e) {
                    LOGGER.warn("Failed to delete temporary file {}", destFile);
                }
            }
        }
    }

}
