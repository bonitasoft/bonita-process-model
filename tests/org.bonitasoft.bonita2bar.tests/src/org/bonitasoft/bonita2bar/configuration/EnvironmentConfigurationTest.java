/**
 * Copyright (C) 2023 Bonitasoft S.A.
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
import static org.bonitasoft.bonita2bar.configuration.EnvironmentConfiguration.PARAMETERS_PROPERTIES_FILE;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Properties;

import org.bonitasoft.engine.bpm.bar.ParameterContribution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class EnvironmentConfigurationTest {

    @Test
    void writeParameters_should_convert_null_values_to_engine_NULL_VALUE(@TempDir Path targetFolder) throws Exception {
        // given:
        final HashMap<String, String> parameters = new HashMap<>();
        parameters.put("myParam1", null);
        parameters.put("emptyParam", "");
        parameters.put("nonEmptyParam", "value");
        final EnvironmentConfiguration configuration = new EnvironmentConfiguration("env", "myProcess", "1.0",
                parameters);

        // when:
        configuration.writeParameters(targetFolder);

        // then:
        final Path propertiesFile = targetFolder.resolve("env").resolve("myProcess").resolve("1.0")
                .resolve(PARAMETERS_PROPERTIES_FILE);
        assertThat(propertiesFile).exists();
        final Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(propertiesFile.toFile())) {
            properties.load(inputStream);
        }
        assertThat(properties.getProperty("myParam1")).isEqualTo(ParameterContribution.NULL);
        assertThat(properties.getProperty("emptyParam")).isEmpty();
        assertThat(properties.getProperty("nonEmptyParam")).isEqualTo("value");
    }
}
