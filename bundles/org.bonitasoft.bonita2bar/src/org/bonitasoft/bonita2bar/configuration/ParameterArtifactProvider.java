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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.bonitasoft.bonita2bar.BarArtifactProvider;
import org.bonitasoft.bonita2bar.configuration.model.ParametersConfiguration;
import org.bonitasoft.bonita2bar.configuration.model.ProcessConfiguration;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.ConfigurationFactory;
import org.bonitasoft.bpm.model.parameter.Parameter;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterArtifactProvider implements BarArtifactProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterArtifactProvider.class);
    private ParametersConfiguration parametersConfiguration;
    private boolean addParametersInBar;

    public ParameterArtifactProvider(ParametersConfiguration parametersConfiguration, boolean addParametersInBar) {
        this.parametersConfiguration = parametersConfiguration;
        this.addParametersInBar = addParametersInBar;
    }

    @Override
    public void build(BusinessArchiveBuilder builder, Pool process, Configuration configuration) {
        if (addParametersInBar) {
            builder.setParameters(getParameterMapFromConfiguration(configuration, process));
        }
    }

    private Map<String, String> getParameterMapFromConfiguration(Configuration configuration, Pool process) {
        final Map<String, String> result = new HashMap<>();
        if (configuration == null) {
            configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        }
        var builder = ProcessConfiguration.builder().name(process.getName()).version(process.getVersion());
        List<org.bonitasoft.bonita2bar.configuration.model.Parameter> parameters = new ArrayList<>();
        for (final Parameter defParam : process.getParameters()) {
            parameters.add(org.bonitasoft.bonita2bar.configuration.model.Parameter.builder().name(defParam.getName())
                    .description(defParam.getDescription()).type(toSimpleTypeName(defParam.getTypeClassname()))
                    .value(paramValue(configuration, defParam, defParam.getTypeClassname())).build());
            result.put(defParam.getName(), valueFromConfiguration(configuration, defParam));
            if (defParam.getDescription() != null) {
                LOGGER.debug("{} ({}) -- {}", defParam.getName(), defParam.getTypeClassname(),
                        defParam.getDescription());
            } else {
                LOGGER.debug("{} ({})", defParam.getName(), defParam.getTypeClassname());
            }

        }
        if (!parameters.isEmpty()) {
            parametersConfiguration.getProcessConfigurations().add(builder.parameters(parameters).build());
        }
        return result;
    }

    private Object paramValue(Configuration configuration, final Parameter p, String type) {
        String value = valueFromConfiguration(configuration, p);
        return value != null && !value.isEmpty() ? convertValue(value, type) : null;
    }

    private String valueFromConfiguration(Configuration configuration, final Parameter p) {
        return configuration.getParameters().stream().filter(param -> Objects.equals(param.getName(), p.getName()))
                .findFirst().map(param -> interpretNullValue(param.getValue())) // because Studio always sends "", never
                // null
                .orElse(null);
    }

    private String interpretNullValue(String value) {
        return ((value == null) || value.isEmpty()) ? null : value;
    }

    private String toSimpleTypeName(String typeClassname) {
        switch (typeClassname) {
            case "java.lang.String":
                return "String";
            case "java.lang.Integer":
                return "Integer";
            case "java.lang.Boolean":
                return "Boolean";
            case "java.lang.Double":
                return "Decimal";
            default:
                throw new IllegalStateException(String.format("Unknown parameter type: %s", typeClassname));
        }
    }

    private Object convertValue(String value, String typeClassname) {
        switch (typeClassname) {
            case "java.lang.String":
                return String.valueOf(value);
            case "java.lang.Integer":
                return Integer.valueOf(value);
            case "java.lang.Boolean":
                return Boolean.valueOf(value);
            case "java.lang.Double":
                return Double.valueOf(value);
            default:
                throw new IllegalStateException(String.format("Unknown parameter type: %s", typeClassname));
        }
    }

    @Override
    public void configure(EnvironmentConfigurationBuilder builder, Configuration configuration, Pool process) {
        if (configuration != null && !configuration.getParameters().isEmpty()) {
            LOGGER.info("Adding parameters for '{}' environment...", configuration.getName().toLowerCase());
        }
        builder.addParameters(getParameterMapFromConfiguration(configuration, process));
    }

}
