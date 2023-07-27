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

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

import org.bonitasoft.bonita2bar.configuration.model.Parameter;
import org.bonitasoft.bonita2bar.configuration.model.ParametersConfiguration;
import org.bonitasoft.bonita2bar.configuration.model.ProcessConfiguration;
import org.eclipse.core.runtime.FileLocator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ParametersConfigurationMapperTest {

    @Test
    void should_read_yaml_parameters_configuration() throws Exception {
        ParametersConfigurationMapper parametersConfigurationReader = new ParametersConfigurationMapper();

        ParametersConfiguration conf = parametersConfigurationReader
                .read(new File(FileLocator
                        .toFileURL(ParametersConfigurationMapperTest.class.getResource("/parameters.yaml")).getFile()));

        assertThat(conf).isNotNull()
                .isEqualTo(testedParamtersConfiguration());
    }

    @Test
    void should_write_yaml_parameters_configuration(@TempDir Path tmpFolder) throws Exception {
        ParametersConfigurationMapper parametersConfigurationReader = new ParametersConfigurationMapper();
        var targetFile = tmpFolder.resolve("config.yml");
        ParametersConfiguration testedParamtersConfiguration = testedParamtersConfiguration();

        parametersConfigurationReader.writeToFile(testedParamtersConfiguration, targetFile);

        assertThat(targetFile).exists();
        assertThat(parametersConfigurationReader.read(targetFile.toFile())).isEqualTo(testedParamtersConfiguration);
    }

    private ParametersConfiguration testedParamtersConfiguration() {
        return ParametersConfiguration.builder()
                .processConfigurations(Arrays.asList(ProcessConfiguration.builder()
                        .name("New Vacation Request")
                        .version("1.4.1")
                        .parameters(Arrays.asList(Parameter.builder()
                                .name("calendarCalendarId")
                                .value(456)
                                .type("Integer")
                                .description("tutu")
                                .build(),
                                Parameter.builder()
                                        .name("calendarApplicationName")
                                        .value("my-super-app")
                                        .type("String")
                                        .description("titi")
                                        .build()))
                        .build(),
                        ProcessConfiguration.builder()
                                .name("Initiate Vacation Available")
                                .version("1.4.1")
                                .parameters(Arrays.asList(
                                        Parameter.builder()
                                                .name("uniqName1")
                                                .value("toto")
                                                .type("String")
                                                .description("...")
                                                .build(),
                                        Parameter.builder()
                                                .name("shouldConnect")
                                                .value(true)
                                                .type("Boolean")
                                                .build(),
                                        Parameter.builder()
                                                .name("amount")
                                                .value(12.4)
                                                .type("Decimal")
                                                .build()))
                                .build()))
                .globalParameters(Arrays.asList(
                        Parameter.builder().name("uniqName1")
                                .value("456")
                                .type("Integer")
                                .description("tutu")
                                .build(),
                        Parameter.builder().name("uniqName2")
                                .value("my-super-app")
                                .type("String")
                                .description("titi")
                                .build()))
                .build();
    }
}
