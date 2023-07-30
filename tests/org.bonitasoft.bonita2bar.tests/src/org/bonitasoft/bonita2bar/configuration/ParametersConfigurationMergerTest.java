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
import java.io.IOException;
import java.nio.file.Path;

import org.eclipse.core.runtime.FileLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ParametersConfigurationMergerTest {

    private File configurationFile;
    private ParametersConfigurationMerger merger;
    private File parametersFile;

    @BeforeEach
    void setup() throws IOException {
        merger = new ParametersConfigurationMerger();
        configurationFile = new File(FileLocator
                .toFileURL(ParametersConfigurationMergerTest.class.getResource("/test-repository.bconf")).getFile());
        parametersFile = new File(FileLocator
                .toFileURL(ParametersConfigurationMergerTest.class.getResource("/toMerge.yaml")).getFile());
    }

    @Test
    void mergeParameters(@TempDir Path outputFolder) throws Exception {
        var mergeConfigurationFile = outputFolder.resolve("merged.bconf");

        merger.merge(configurationFile, parametersFile, mergeConfigurationFile.toString());

        assertThat(mergeConfigurationFile).exists();
    }

}
