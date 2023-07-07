/**
 * Copyright (C) 2022 Bonitasoft S.A.
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

import java.util.List;

import org.bonitasoft.bpm.model.configuration.provider.ConfigurationItemProviderAdapterFactory;
import org.bonitasoft.bpm.model.connectorconfiguration.provider.ConnectorConfigurationItemProviderAdapterFactory;
import org.bonitasoft.bpm.model.edit.actormapping.CustomActorMappingItemProviderAdapterFactory;
import org.bonitasoft.bpm.model.edit.decision.CustomDecisionItemProviderAdapterFactory;
import org.bonitasoft.bpm.model.edit.process.CustomProcessItemProviderAdapterFactory;
import org.bonitasoft.bpm.model.expression.provider.ExpressionItemProviderAdapterFactory;
import org.bonitasoft.bpm.model.form.provider.FormItemProviderAdapterFactory;
import org.bonitasoft.bpm.model.kpi.provider.KpiItemProviderAdapterFactory;
import org.bonitasoft.bpm.model.parameter.provider.ParameterItemProviderAdapterFactory;
import org.bonitasoft.bpm.model.process.decision.transitions.provider.TransitionsItemProviderAdapterFactory;
import org.bonitasoft.bpm.model.simulation.provider.SimulationItemProviderAdapterFactory;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

/**
 * Allows you to build an {@link AdapterFactory} which suits your needs.
 * 
 * @author Vincent Hemery
 */
public class AdapterFactoryBuilder {

    /**
     * Default Constructor.
     */
    public AdapterFactoryBuilder() {
    }

    /**
     * Create a complete adapter factory, ready to use.
     * 
     * @return composed adapter factory
     */
    public ComposedAdapterFactory createAdapterFactory() {
        return new ComposedAdapterFactory(getFactories());
    }

    /**
     * Get list of factories to integrate
     * 
     * @return list of adapter factories
     */
    protected List<AdapterFactory> getFactories() {
        return List.of(
                // custom model adapter factories
                new CustomProcessItemProviderAdapterFactory(),
                new CustomDecisionItemProviderAdapterFactory(),
                new CustomActorMappingItemProviderAdapterFactory(),
                // generated model adapter factories
                new ConfigurationItemProviderAdapterFactory(),
                new ConnectorConfigurationItemProviderAdapterFactory(),
                new ExpressionItemProviderAdapterFactory(),
                new KpiItemProviderAdapterFactory(),
                new ParameterItemProviderAdapterFactory(),
                new TransitionsItemProviderAdapterFactory(),
                new FormItemProviderAdapterFactory(),
                new SimulationItemProviderAdapterFactory(),
                // generic usefull adapter factories
                new ResourceItemProviderAdapterFactory(),
                new ReflectiveItemProviderAdapterFactory());
    }

}
