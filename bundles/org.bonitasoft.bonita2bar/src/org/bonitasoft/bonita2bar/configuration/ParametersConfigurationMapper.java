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

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.bonitasoft.bonita2bar.configuration.model.ParametersConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class ParametersConfigurationMapper {

    public static final String DEFAULT_PARAMETERS_FILE = "parameters.yml";

    private ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    public ParametersConfiguration read(File configurationFile) throws IOException {
        return mapper.readValue(configurationFile, ParametersConfiguration.class);
    }

    public ParametersConfiguration read(InputStream configurationFile) throws IOException {
        return mapper.readValue(configurationFile, ParametersConfiguration.class);
    }

    public void writeToFile(ParametersConfiguration configuration, Path to) throws IOException {
        byte[] content = mapper.writeValueAsBytes(configuration);
        try (InputStream is = new ByteArrayInputStream(content)) {
            Files.copy(is, to, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
