/**
 * Copyright (C) 2020 Bonitasoft S.A.
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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import org.bonitasoft.bonita2bar.BarArtifactProvider;
import org.bonitasoft.bonita2bar.BuildBarException;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdditionalResourcesArtifactProvider implements BarArtifactProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdditionalResourcesArtifactProvider.class);
    private static final String COMMON_FOLDER = "_common";
    private Path resourcesFolder;

    public AdditionalResourcesArtifactProvider(Path resourcesFolder) {
        this.resourcesFolder = resourcesFolder;
    }

    @Override
    public void build(BusinessArchiveBuilder builder, Pool process, Configuration configuration)
            throws BuildBarException {
        try {
            var commonFolder = resourcesFolder.resolve(COMMON_FOLDER).toFile();
            if (commonFolder.exists()) {
                addFolderInBarResources(builder, commonFolder, process);
            }

            var processFolder = resourcesFolder.resolve(process.getName()).toFile();

            if (processFolder.exists()) {
                addFolderInBarResources(builder, processFolder, process);
            }
            processFolder = resourcesFolder.resolve(String.format("%s-%s", process.getName(), process.getVersion()))
                    .toFile();
            if (processFolder.exists()) {
                addFolderInBarResources(builder, processFolder, process);
            }

        } catch (IOException e) {
            String message = String.format("Failed to add resource folder %s in bar %s-%s.bar.",
                    resourcesFolder.toString(), process.getName(), process.getVersion());
            throw new BuildBarException(message, e);
        }
    }

    private void addFolderInBarResources(BusinessArchiveBuilder builder, File folder, AbstractProcess process)
            throws IOException {
        Path folderPath = folder.toPath();
        LOGGER.info("Adding resource folder '{}' in bar.", folder.getPath());
        try (var files = Files.walk(folderPath)) {
            for (Path resourcePath : files.collect(Collectors.toList())) {
                if (resourcePath.toFile().isFile()) {
                    Path barPath = folderPath.relativize(resourcePath);
                    var content = Files.readAllBytes(resourcePath);
                    if (content.length > 0) {
                        builder.addExternalResource(new BarResource(barPath.toString(), content));
                        LOGGER.debug("Resource '{}' has been added to {}--{}.bar.", resourcePath, process.getName(),
                                process.getVersion());
                    } else {
                        LOGGER.warn("Resource '{}' is empty and has not been added to {}--{}.bar.", resourcePath,
                                process.getName(), process.getVersion());
                    }
                }
            }
        }
    }

}
