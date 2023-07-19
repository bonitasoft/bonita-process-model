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
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Properties;

import org.bonitasoft.bonita2bar.BarBuilder;
import org.bonitasoft.bonita2bar.configuration.model.ParametersConfiguration;

public class ConfigurationArchiveBuilder {

    private ParametersConfigurationMapper mapper = new ParametersConfigurationMapper();
    private Path envFolder;
    private ParametersConfiguration parametersConfiguration;

    public ConfigurationArchiveBuilder withEnv(Path envFolder) {
        this.envFolder = envFolder;
        return this;
    }

    public ConfigurationArchiveBuilder withParametersConfiguration(ParametersConfiguration parametersConfiguration) {
        this.parametersConfiguration = parametersConfiguration;
        return this;
    }

    public void create(Path configurationFile) throws IOException {
        addArchiveManifest(envFolder);
        mapper.writeToFile(parametersConfiguration,
                envFolder.resolve(ParametersConfigurationMapper.DEFAULT_PARAMETERS_FILE));
        ZipUtil.zip(envFolder, configurationFile);
    }

    private void addArchiveManifest(Path envFolder) throws IOException {
        File manifestFile = envFolder.resolve("MANIFEST").toFile();
        Properties props = new Properties();
        props.put("builder.version", BarBuilder.builderVersion());
        props.put("target.environment", envFolder.toFile().getName());
        try (OutputStream out = new FileOutputStream(manifestFile)) {
            props.store(out, null);
        }
    }

}
