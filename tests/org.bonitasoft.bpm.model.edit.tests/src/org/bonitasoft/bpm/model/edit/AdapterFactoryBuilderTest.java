/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.bpm.model.edit;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.bpm.model.actormapping.ActorMappingFactory;
import org.bonitasoft.bpm.model.configuration.ConfigurationFactory;
import org.bonitasoft.bpm.model.configuration.provider.ConfigurationItemProviderAdapterFactory;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.bonitasoft.bpm.model.connectorconfiguration.provider.ConnectorConfigurationItemProviderAdapterFactory;
import org.bonitasoft.bpm.model.edit.actormapping.CustomActorMappingItemProviderAdapterFactory;
import org.bonitasoft.bpm.model.edit.decision.CustomDecisionItemProviderAdapterFactory;
import org.bonitasoft.bpm.model.edit.process.CustomProcessItemProviderAdapterFactory;
import org.bonitasoft.bpm.model.expression.ExpressionFactory;
import org.bonitasoft.bpm.model.expression.provider.ExpressionItemProviderAdapterFactory;
import org.bonitasoft.bpm.model.form.FormFactory;
import org.bonitasoft.bpm.model.form.provider.FormItemProviderAdapterFactory;
import org.bonitasoft.bpm.model.kpi.KpiFactory;
import org.bonitasoft.bpm.model.kpi.provider.KpiItemProviderAdapterFactory;
import org.bonitasoft.bpm.model.parameter.ParameterFactory;
import org.bonitasoft.bpm.model.parameter.provider.ParameterItemProviderAdapterFactory;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.bpm.model.process.decision.DecisionFactory;
import org.bonitasoft.bpm.model.process.decision.transitions.TransitionsFactory;
import org.bonitasoft.bpm.model.process.decision.transitions.provider.TransitionsItemProviderAdapterFactory;
import org.bonitasoft.bpm.model.simulation.SimulationFactory;
import org.bonitasoft.bpm.model.simulation.provider.SimulationItemProviderAdapterFactory;
import org.junit.jupiter.api.Test;

class AdapterFactoryBuilderTest {

    @Test
    void processAdapterFactory() throws Exception {
        var adapterFactoryBuilder = new AdapterFactoryBuilder();

        var factory = adapterFactoryBuilder.createAdapterFactory();

        assertThat(factory.getFactoryForType(ProcessFactory.eINSTANCE.createPool()))
                .isInstanceOf(CustomProcessItemProviderAdapterFactory.class);
    }

    @Test
    void decisionAdapterFactory() throws Exception {
        var adapterFactoryBuilder = new AdapterFactoryBuilder();

        var factory = adapterFactoryBuilder.createAdapterFactory();

        assertThat(factory.getFactoryForType(DecisionFactory.eINSTANCE.createDecisionTable()))
                .isInstanceOf(CustomDecisionItemProviderAdapterFactory.class);
    }

    @Test
    void kpiAdapterFactory() throws Exception {
        var adapterFactoryBuilder = new AdapterFactoryBuilder();

        var factory = adapterFactoryBuilder.createAdapterFactory();

        assertThat(factory.getFactoryForType(KpiFactory.eINSTANCE.createKPIField()))
                .isInstanceOf(KpiItemProviderAdapterFactory.class);
    }

    @Test
    void actorMappingAdapterFactory() throws Exception {
        var adapterFactoryBuilder = new AdapterFactoryBuilder();

        var factory = adapterFactoryBuilder.createAdapterFactory();

        assertThat(factory.getFactoryForType(ActorMappingFactory.eINSTANCE.createActorMapping()))
                .isInstanceOf(CustomActorMappingItemProviderAdapterFactory.class);
    }

    @Test
    void configurationAdapterFactory() throws Exception {
        var adapterFactoryBuilder = new AdapterFactoryBuilder();

        var factory = adapterFactoryBuilder.createAdapterFactory();

        assertThat(factory.getFactoryForType(ConfigurationFactory.eINSTANCE.createConfiguration()))
                .isInstanceOf(ConfigurationItemProviderAdapterFactory.class);
    }

    @Test
    void connectorConfigurationAdapterFactory() throws Exception {
        var adapterFactoryBuilder = new AdapterFactoryBuilder();

        var factory = adapterFactoryBuilder.createAdapterFactory();

        assertThat(factory.getFactoryForType(ConnectorConfigurationFactory.eINSTANCE.createConnectorConfiguration()))
                .isInstanceOf(ConnectorConfigurationItemProviderAdapterFactory.class);
    }

    @Test
    void expressionAdapterFactory() throws Exception {
        var adapterFactoryBuilder = new AdapterFactoryBuilder();

        var factory = adapterFactoryBuilder.createAdapterFactory();

        assertThat(factory.getFactoryForType(ExpressionFactory.eINSTANCE.createExpression()))
                .isInstanceOf(ExpressionItemProviderAdapterFactory.class);
    }

    @Test
    void parameterAdapterFactory() throws Exception {
        var adapterFactoryBuilder = new AdapterFactoryBuilder();

        var factory = adapterFactoryBuilder.createAdapterFactory();

        assertThat(factory.getFactoryForType(ParameterFactory.eINSTANCE.createParameter()))
                .isInstanceOf(ParameterItemProviderAdapterFactory.class);
    }

    @Test
    void tansitionItemAdapterFactory() throws Exception {
        var adapterFactoryBuilder = new AdapterFactoryBuilder();

        var factory = adapterFactoryBuilder.createAdapterFactory();

        assertThat(factory.getFactoryForType(TransitionsFactory.eINSTANCE.createTakeTransitionAction()))
                .isInstanceOf(TransitionsItemProviderAdapterFactory.class);
    }

    @Test
    void formItemAdapterFactory() throws Exception {
        var adapterFactoryBuilder = new AdapterFactoryBuilder();

        var factory = adapterFactoryBuilder.createAdapterFactory();

        assertThat(factory.getFactoryForType(FormFactory.eINSTANCE.createForm()))
                .isInstanceOf(FormItemProviderAdapterFactory.class);
    }

    @Test
    void simulationItemAdapterFactory() throws Exception {
        var adapterFactoryBuilder = new AdapterFactoryBuilder();

        var factory = adapterFactoryBuilder.createAdapterFactory();

        assertThat(factory.getFactoryForType(SimulationFactory.eINSTANCE.createModelVersion()))
                .isInstanceOf(SimulationItemProviderAdapterFactory.class);
    }

}
