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
package org.bonitasoft.bonita2bar.form;

import java.nio.file.Path;

import org.bonitasoft.bonita2bar.BarArtifactProvider;
import org.bonitasoft.bonita2bar.BuildBarException;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;

public class FormMappingArtifactProvider implements BarArtifactProvider {

    private final FormBuilder formBuilder;
    private Path formsFolder;
    private boolean allowEmptyMapping;

    public FormMappingArtifactProvider(Path formsFolder, FormBuilder formBuilder, boolean allowEmptyMapping) {
        this.formsFolder = formsFolder;
        this.formBuilder = formBuilder;
        this.allowEmptyMapping = allowEmptyMapping;
    }

    @Override
    public void build(BusinessArchiveBuilder builder,
            Pool process,
            Configuration configuration)
            throws BuildBarException {
        final FormMappingBarResourceProvider formMappingBarResourceProvider = new FormMappingBarResourceProvider(
                new CustomPageBarResourceBuilder(formBuilder), formsFolder, allowEmptyMapping);
        try {
            formMappingBarResourceProvider.addResourcesForConfiguration(builder, process);
        } catch (final Exception e) {
            throw new BuildBarException("An error occurred while adding resources", e);
        }
    }

}
