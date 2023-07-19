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

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Parameter {

    private String name;
    private Object value;
    private String type;
    @JsonInclude(Include.NON_NULL)
    private String description;

    public Parameter() {

    }

    public Parameter(String name, Object value, String type, String description) {
        this.name = name;
        this.value = value;
        this.type = type;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, name, type, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Parameter other = (Parameter) obj;
        return Objects.equals(description, other.description) && Objects.equals(name, other.name)
                && Objects.equals(type, other.type) && Objects.equals(value, other.value);
    }

    public static ParameterBuilder builder() {
        return new ParameterBuilder();
    }

    public static class ParameterBuilder {

        private String name;
        private Object value;
        private String type;
        private String description;

        ParameterBuilder() {

        }

        public ParameterBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ParameterBuilder value(Object value) {
            this.value = value;
            return this;
        }

        public ParameterBuilder type(String type) {
            this.type = type;
            return this;
        }

        public ParameterBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Parameter build() {
            return new Parameter(name, value, type, description);
        }
    }

}
