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
import static org.assertj.core.api.Assertions.tuple;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.eclipse.core.runtime.FileLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ParameterConfigurationExtractorTest {

    private ParameterConfigurationExtractor extractor;
    private File configurationFile;
    private ParametersConfigurationMapper parametersConfigurationMapper;

    @BeforeEach
    void setup() throws IOException {
        extractor = new ParameterConfigurationExtractor();
        configurationFile = new File(FileLocator
                .toFileURL(ParameterConfigurationExtractorTest.class.getResource("/test-repository.bconf")).getFile());
        parametersConfigurationMapper = new ParametersConfigurationMapper();
    }

    @Test
    void extractAllParameters(@TempDir Path outputFolder) throws Exception {
        var parametersFile = outputFolder.resolve("parameters.yml");

        extractor.extract(configurationFile, parametersFile.toString(), false);

        assertThat(parametersFile).exists();

        var parametersConfiguration = parametersConfigurationMapper.read(parametersFile.toFile());
        assertThat(parametersConfiguration.getProcessConfigurations()).extracting("name", "version")
                .contains(tuple("SimpleProcessWithParameters", "1.0"));
        assertThat(parametersConfiguration.getProcessConfigurations().get(0).getParameters())
                .extracting("name", "value", "type")
                .contains(tuple("host", "localhost", "String"), tuple("port", 8080, "Integer"),
                        tuple("password", null, "String"));
    }

    @Test
    void extractParametersWithoutValues(@TempDir Path outputFolder) throws Exception {
        var parametersFile = outputFolder.resolve("parameters.yml");

        extractor.extract(configurationFile, parametersFile.toString(), true);

        assertThat(parametersFile).exists();

        var parametersConfiguration = parametersConfigurationMapper.read(parametersFile.toFile());
        assertThat(parametersConfiguration.getProcessConfigurations()).extracting("name", "version")
                .contains(tuple("SimpleProcessWithParameters", "1.0"));
        assertThat(parametersConfiguration.getProcessConfigurations().get(0).getParameters())
                .extracting("name", "value", "type")
                .containsOnly(tuple("password", null, "String"));
    }

}
