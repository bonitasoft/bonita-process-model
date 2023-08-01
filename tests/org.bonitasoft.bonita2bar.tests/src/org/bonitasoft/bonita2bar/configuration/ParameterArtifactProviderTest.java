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
package org.bonitasoft.bonita2bar.configuration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.bpm.model.parameter.builders.ParameterBuilder.aParameter;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.bonita2bar.configuration.model.ParametersConfiguration;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.builders.ConfigurationBuilder;
import org.bonitasoft.bpm.model.parameter.Parameter;
import org.bonitasoft.bpm.model.parameter.ParameterFactory;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.engine.bpm.flownode.impl.internal.FlowElementContainerDefinitionImpl;
import org.bonitasoft.engine.bpm.process.impl.internal.DesignProcessDefinitionImpl;
import org.eclipse.emf.common.util.BasicEList;
import org.junit.jupiter.api.Test;

class ParameterArtifactProviderTest {

    @Test
    void should_add_parameters_in_configuration() throws Exception {
        ParametersConfiguration conf = new ParametersConfiguration();
        final ParameterArtifactProvider parameterProvider = new ParameterArtifactProvider(conf, false);

        final EnvironmentConfigurationBuilder builder = mock(EnvironmentConfigurationBuilder.class);
        final Configuration configuration = mock(Configuration.class);
        when(configuration.getParameters())
                .thenReturn(new BasicEList<>(List.of(parameter("host", "localhost", String.class.getName()),
                        parameter("port", "", Integer.class.getName()))));
        Pool createPool = ProcessFactory.eINSTANCE.createPool();
        createPool.getParameters().add(parameter("host", "localhost", String.class.getName()));
        createPool.getParameters().add(parameter("port", "8080", Integer.class.getName()));
        parameterProvider.configure(builder, configuration, createPool);

        final Map<String, String> parameters = new HashMap<>();
        parameters.put("host", "localhost");
        parameters.put("port", null); // "" should have been converted to null
        verify(builder).addParameters(parameters);

        assertThat(conf.getProcessConfigurations()).isNotEmpty();
        assertThat(conf.getProcessConfigurations().get(0).getParameters()).hasSize(2);

        assertThat(conf.getProcessConfigurations().get(0).getParameters()).extracting("value")
                .containsExactlyInAnyOrder("localhost", null);
    }

    @Test
    void should_add_parameters_in_business_archive() throws Exception {
        var builder = new BusinessArchiveBuilder().createNewBusinessArchive();
        var definition = new DesignProcessDefinitionImpl("test", "1.0");
        definition.setProcessContainer(new FlowElementContainerDefinitionImpl());
        builder.setProcessDefinition(definition);
        var parameterProvider = new ParameterArtifactProvider(new ParametersConfiguration(), true);

        var configuration = ConfigurationBuilder.aConfiguration()
                .havingParameters(aParameter().withName("host").withValue("localhost").withType(String.class.getName()),
                        aParameter().withName("port").withValue("").withType(Integer.class.getName()))
                .build();
        var pool = ProcessFactory.eINSTANCE.createPool();
        pool.getParameters().add(aParameter().withName("host").withType(String.class.getName()).build());
        pool.getParameters().add(aParameter().withName("port").withType(Integer.class.getName()).build());

        parameterProvider.build(builder, pool, configuration);
        var businessArchive = builder.done();

        assertThat(businessArchive.getParameters()).containsEntry("host", "localhost").containsEntry("port", null);
    }

    private Parameter parameter(String name, String value, String className) {
        final Parameter parameter = ParameterFactory.eINSTANCE.createParameter();
        parameter.setName(name);
        parameter.setValue(value);
        parameter.setTypeClassname(className);
        return parameter;
    }
}
