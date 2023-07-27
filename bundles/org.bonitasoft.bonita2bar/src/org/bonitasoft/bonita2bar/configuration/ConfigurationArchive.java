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
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.bonitasoft.bonita2bar.BarBuilder;
import org.bonitasoft.bonita2bar.configuration.model.ParametersConfiguration;

public class ConfigurationArchive {

    private File configurationFile;
    private ParametersConfigurationMapper mapper = new ParametersConfigurationMapper();

    public ConfigurationArchive(File configurationFile) {
        this.configurationFile = configurationFile;
    }

    public ParametersConfiguration loadParametersConfiguration() throws IOException {
        try (ZipFile zipFile = new ZipFile(configurationFile)) {
            ZipEntry entry = zipFile.getEntry(ParametersConfigurationMapper.DEFAULT_PARAMETERS_FILE);
            if (entry == null) {
                throw new IOException(
                        String.format("Invalid bonita configuration archive. No '%s' found.",
                                ParametersConfigurationMapper.DEFAULT_PARAMETERS_FILE));
            }
            return mapper.read(zipFile.getInputStream(entry));
        }
    }

    public void create(File envFolder, ParametersConfiguration parametersConfiguration) throws IOException {
        addArchiveManifest(envFolder);
        addParametersConfigurationFile(envFolder, parametersConfiguration);
        ZipUtil.zip(envFolder.toPath(), configurationFile.toPath());
    }

    private void addParametersConfigurationFile(File envFolder, ParametersConfiguration parametersConfiguration)
            throws IOException {
        mapper.writeToFile(parametersConfiguration,
                envFolder.toPath().resolve(ParametersConfigurationMapper.DEFAULT_PARAMETERS_FILE));
    }

    private void addArchiveManifest(File envFolder) throws IOException {
        File manifestFile = new File(envFolder, "MANIFEST");
        Properties props = new Properties();
        props.put("builder.version", BarBuilder.builderVersion());
        props.put("target.environment", envFolder.getName());
        try (OutputStream out = new FileOutputStream(manifestFile)) {
            props.store(out, null);
        }
    }

}
