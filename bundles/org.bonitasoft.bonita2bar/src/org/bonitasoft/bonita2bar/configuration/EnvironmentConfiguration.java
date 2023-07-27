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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.bonitasoft.engine.bpm.bar.ParameterContribution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnvironmentConfiguration {

    public static final String PARAMETERS_PROPERTIES_FILE = "parameters.properties";

    private static final Logger LOGGER = LoggerFactory.getLogger(EnvironmentConfiguration.class);

    private final Map<String, String> parameters;

    private String envName;

    private String processName;

    private String processVersion;

    EnvironmentConfiguration(String envName, String processName, String processVersion,
            Map<String, String> parameters) {
        this.envName = envName;
        this.processName = processName;
        this.processVersion = processVersion;
        this.parameters = parameters;
    }

    public void writeParameters(Path target) throws IOException {
        if (parameters != null && !parameters.isEmpty()) {
            Path processFolder = target.resolve(envName).resolve(processName).resolve(processVersion);
            processFolder.toFile().mkdirs();
            final File parametersFile = processFolder.resolve(PARAMETERS_PROPERTIES_FILE).toFile();
            if (parametersFile.exists()) {
                Files.delete(parametersFile.toPath());
            }
            LOGGER.info("Writing parameters file in {}", processFolder);
            Properties properties = new Properties();
            try (FileOutputStream out = new FileOutputStream(parametersFile)) {
                for (Entry<String, String> entry : parameters.entrySet()) {
                    properties.setProperty(entry.getKey(),
                            entry.getValue() == null ? ParameterContribution.NULL : entry.getValue());
                }
                properties.store(out, envName);
            }
        }
    }

}
