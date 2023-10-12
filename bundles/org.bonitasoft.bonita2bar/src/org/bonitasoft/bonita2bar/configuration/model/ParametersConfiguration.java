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
package org.bonitasoft.bonita2bar.configuration.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ParametersConfiguration {

    @JsonInclude(Include.NON_EMPTY)
    @JsonProperty("processes")
    private List<ProcessConfiguration> processConfigurations = new ArrayList<>();
    @JsonProperty("global_parameters")
    @JsonInclude(Include.NON_EMPTY)
    private List<Parameter> globalParameters = new ArrayList<>();

    public ParametersConfiguration() {

    }

    public ParametersConfiguration(List<ProcessConfiguration> processConfigurations, List<Parameter> globalParameters) {
        this.processConfigurations = processConfigurations;
        this.globalParameters = globalParameters;
    }

    public List<ProcessConfiguration> getProcessConfigurations() {
        return processConfigurations;
    }

    public void setProcessConfigurations(List<ProcessConfiguration> processConfigurations) {
        this.processConfigurations = processConfigurations;
    }

    public List<Parameter> getGlobalParameters() {
        return globalParameters;
    }

    public void setGlobalParameters(List<Parameter> globalParameters) {
        this.globalParameters = globalParameters;
    }

    @Override
    public int hashCode() {
        return Objects.hash(globalParameters, processConfigurations);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ParametersConfiguration other = (ParametersConfiguration) obj;
        return Objects.equals(globalParameters, other.globalParameters)
                && Objects.equals(processConfigurations, other.processConfigurations);
    }

    public static ParametersConfigurationBuilder builder() {
        return new ParametersConfigurationBuilder();
    }

    public static class ParametersConfigurationBuilder {

        private List<ProcessConfiguration> processConfigurations = new ArrayList<>();
        private List<Parameter> globalParameters = new ArrayList<>();

        ParametersConfigurationBuilder() {

        }

        public ParametersConfigurationBuilder processConfigurations(List<ProcessConfiguration> processConfigurations) {
            this.processConfigurations = processConfigurations;
            return this;
        }

        public ParametersConfigurationBuilder globalParameters(List<Parameter> globalParameters) {
            this.globalParameters = globalParameters;
            return this;
        }

        public ParametersConfiguration build() {
            return new ParametersConfiguration(processConfigurations, globalParameters);
        }
    }

}
