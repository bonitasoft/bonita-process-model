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
package org.bonitasoft.bonita2bar.process;

import org.bonitasoft.bonita2bar.BarArtifactProvider;
import org.bonitasoft.bonita2bar.BuildBarException;
import org.bonitasoft.bonita2bar.ProcessRegistry;
import org.bonitasoft.bonita2bar.process.builder.DesignProcessDefinitionBuilder;
import org.bonitasoft.bonita2bar.process.builder.DocumentEngineDefinitionBuilder;
import org.bonitasoft.bonita2bar.process.builder.ProcessContractEngineBuilder;
import org.bonitasoft.bonita2bar.process.builder.TaskContractEngineDefinitionBuilder;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.util.ModelSearch;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.engine.bpm.process.InvalidProcessDefinitionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessDefinitionArtifactProvider implements BarArtifactProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessDefinitionArtifactProvider.class);
    private ProcessRegistry processRegistry;

    public ProcessDefinitionArtifactProvider(ProcessRegistry processRegistry) {
        this.processRegistry = processRegistry;
    }

    @Override
    public void build(BusinessArchiveBuilder builder,
            Pool process,
            Configuration configuration)
            throws BuildBarException {
        LOGGER.info("Adding process definition...");
        ModelSearch modelSearch = new ModelSearch(processRegistry::getProcesses);
        final DesignProcessDefinitionBuilder designProcessDefinitionBuilder = new DesignProcessDefinitionBuilder(
                modelSearch,
                new ProcessContractEngineBuilder(),
                new TaskContractEngineDefinitionBuilder(),
                new DocumentEngineDefinitionBuilder());
        try {
            builder.setProcessDefinition(designProcessDefinitionBuilder.createDefinition(process));
        } catch (final InvalidProcessDefinitionException e) {
            throw new BuildBarException(String.format("Invalid process definition for %s", process.getName()), e);
        }
    }

}
