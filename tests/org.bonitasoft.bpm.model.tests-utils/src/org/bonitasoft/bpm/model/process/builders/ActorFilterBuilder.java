/** 
 * Copyright (C) 2015 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.bpm.model.process.builders;

import org.bonitasoft.bpm.model.Buildable;
import org.bonitasoft.bpm.model.connectorconfiguration.builders.ConnectorConfigurationBuilder;
import org.bonitasoft.bpm.model.expression.Operation;
import org.bonitasoft.bpm.model.process.ActorFilter;
import org.bonitasoft.bpm.model.process.ProcessFactory;

/**
 * @author Romain Bioteau
 */
public class ActorFilterBuilder extends ElementBuilder<ActorFilter, ActorFilterBuilder> {

    public static ActorFilterBuilder anActorFilter() {
        return new ActorFilterBuilder();
    }

    public ActorFilterBuilder withDefinitionId(final String definitionId) {
        getBuiltInstance().setDefinitionId(definitionId);
        return this;
    }

    public ActorFilterBuilder withDefinitionVersion(final String definitionVersion) {
        getBuiltInstance().setDefinitionVersion(definitionVersion);
        return this;
    }

    public ActorFilterBuilder onEvent(final String event) {
        getBuiltInstance().setEvent(event);
        return this;
    }

    public ActorFilterBuilder havingConfiguration(final ConnectorConfigurationBuilder connectorConfiguration) {
        getBuiltInstance().setConfiguration(connectorConfiguration.build());
        return this;
    }

    @SafeVarargs
    public final ActorFilterBuilder havingOutput(final Buildable<Operation>... outputOperation) {
        for (final Buildable<Operation> output : outputOperation) {
            getBuiltInstance().getOutputs().add(output.build());
        }
        return this;
    }

    @Override
    protected ActorFilter newInstance() {
        return ProcessFactory.eINSTANCE.createActorFilter();
    }

}
