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
package org.bonitasoft.bonita2bar;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.bonitasoft.bonita2bar.configuration.ConfigurationArchiveBuilder;
import org.bonitasoft.bonita2bar.configuration.EnvironmentConfiguration;
import org.bonitasoft.bonita2bar.configuration.model.ParametersConfiguration;
import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveFactory;
import org.bonitasoft.engine.bpm.process.DesignProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuildResult {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuildResult.class);

    private List<BusinessArchive> businessArchives = new ArrayList<>();
    private List<EnvironmentConfiguration> configurations = new ArrayList<>();

    private ParametersConfiguration parametersConfiguration;
    private String environment;

    private Path workdir;

    BuildResult(String environment, ParametersConfiguration parametersConfiguration, Path workdir) {
        this.environment = environment;
        this.parametersConfiguration = parametersConfiguration;
        this.workdir = workdir;
    }

    public void addBusinessArchive(BusinessArchive businessArchive) {
        businessArchives.add(businessArchive);
    }

    public void addConfiguration(EnvironmentConfiguration configuration) {
        configurations.add(configuration);
    }

    public List<BusinessArchive> getBusinessArchives() {
        return businessArchives;
    }

    public List<EnvironmentConfiguration> getConfigurations() {
        return configurations;
    }

    public void writeBusinessArchivesTo(Path barOutputFolder) throws IOException {
        for (final BusinessArchive archive : businessArchives) {
            writeBar(barOutputFolder, archive);
        }
    }

    public void writeBonitaConfigurationTo(Path bonitaConfigurationFile) throws IOException {
        var configurationTmpFolder = workdir.resolve("bconf");
        if (Files.exists(bonitaConfigurationFile)) {
            deleteDir(bonitaConfigurationFile);
        }
        Files.createDirectory(configurationTmpFolder);
        try {
            for (final EnvironmentConfiguration configuration : configurations) {
                writeConfiguration(configurationTmpFolder, configuration);
            }
            var envFolder = configurationTmpFolder.resolve(environment);
            if (Files.exists(envFolder) && envFolder.toFile().list().length > 0) {
                LOGGER.info("Writing Bonita configuration file for {} environment to {}", environment,
                        bonitaConfigurationFile);
                new ConfigurationArchiveBuilder().withEnv(envFolder)
                        .withParametersConfiguration(parametersConfiguration).create(bonitaConfigurationFile);
            } else {
                LOGGER.warn("No configuration found for {} environment.", environment);
            }
        } finally {
            deleteDir(configurationTmpFolder);
        }
    }

    private void writeBar(final Path targetFolder, final BusinessArchive archive) throws IOException {
        final DesignProcessDefinition processDefinition = archive.getProcessDefinition();
        final String name = processDefinition.getName();
        final String version = processDefinition.getVersion();
        final String fileName = String.format("%s--%s.bar", name, version);

        if (!Files.exists(targetFolder)) {
            Files.createDirectories(targetFolder);
        }
        final File newBar = targetFolder.resolve(fileName).toFile();
        if (newBar.exists()) {
            Files.delete(newBar.toPath());
        }
        LOGGER.info("Writing bar file {} in {}", fileName, targetFolder);
        BusinessArchiveFactory.writeBusinessArchiveToFile(archive, newBar);
    }

    private void writeConfiguration(final Path targetFolder, final EnvironmentConfiguration configuration)
            throws IOException {
        if (!Files.exists(targetFolder)) {
            Files.createDirectories(targetFolder);
        }
        configuration.writeParameters(targetFolder);
    }

    private static void deleteDir(Path directory) throws IOException {
        try (Stream<Path> pathStream = Files.walk(directory)) {
            pathStream.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
        }
    }

    public void add(BuildResult result) {
        businessArchives.addAll(result.getBusinessArchives());
        configurations.addAll(result.getConfigurations());
    }
}
