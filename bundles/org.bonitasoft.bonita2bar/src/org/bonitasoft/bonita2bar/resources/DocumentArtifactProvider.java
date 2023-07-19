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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.bonitasoft.bonita2bar.BarArtifactProvider;
import org.bonitasoft.bonita2bar.BuildBarException;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.process.Document;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocumentArtifactProvider implements BarArtifactProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentArtifactProvider.class);
    private Path attachmentFolder;

    public DocumentArtifactProvider(Path attachmentFolder) {
        this.attachmentFolder = attachmentFolder;
    }

    @Override
    public void build(BusinessArchiveBuilder builder,
            Pool process,
            Configuration configuration)
            throws BuildBarException {
        for (final Document document : process.getDocuments()) {
            if (document.getDocumentType().equals(org.bonitasoft.bpm.model.process.DocumentType.INTERNAL)) {
                final String attachmentName = document.getDefaultValueIdOfDocumentStore();
                if (attachmentName != null) {
                    final File attachment = attachmentFolder.resolve(attachmentName).toFile();
                    if (attachment.exists()) {
                        try {
                            LOGGER.info("Adding {} document...", attachment.getName());
                            builder.addDocumentResource(
                                    new BarResource(attachment.getName(), Files.readAllBytes(attachment.toPath())));
                        } catch (IOException e) {
                            String message = String.format("Failed to add document %s in bar %s-%s.bar",
                                    attachmentName, process.getName(), process.getVersion());
                            throw new BuildBarException(message, e);
                        }
                    }
                }
            }
        }
    }

}
