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

public class ProcessConfiguration {

    private String name;
    private String version;
    @JsonInclude(Include.NON_EMPTY)
    private List<Parameter> parameters = new ArrayList<>();

    public ProcessConfiguration() {

    }

    public ProcessConfiguration(String name, String version, List<Parameter> parameters) {
        super();
        this.name = name;
        this.version = version;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parameters, version);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProcessConfiguration other = (ProcessConfiguration) obj;
        return Objects.equals(name, other.name) && Objects.equals(parameters, other.parameters)
                && Objects.equals(version, other.version);
    }

    public static ProcessConfigurationBuilder builder() {
        return new ProcessConfigurationBuilder();
    }

    public static class ProcessConfigurationBuilder {

        private String name;
        private String version;
        private List<Parameter> parameters = new ArrayList<>();

        ProcessConfigurationBuilder() {

        }

        public ProcessConfigurationBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProcessConfigurationBuilder version(String version) {
            this.version = version;
            return this;
        }

        public ProcessConfigurationBuilder parameters(List<Parameter> parameters) {
            this.parameters = parameters;
            return this;
        }

        public ProcessConfiguration build() {
            return new ProcessConfiguration(name, version, parameters);
        }
    }

}
