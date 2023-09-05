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

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentConfigurationBuilder {

    private Map<String, String> parameters = new HashMap<>();

    private String envName;

    private String processName;

    private String processVersion;

    public EnvironmentConfigurationBuilder(String processName, String processVersion, String envName) {
        this.processName = requireNonNull(processName);
        this.processVersion = requireNonNull(processVersion);
        this.envName = requireNonNull(envName).toLowerCase();
    }

    public void addParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public EnvironmentConfiguration done() {
        return new EnvironmentConfiguration(envName, processName, processVersion, parameters);
    }

}
