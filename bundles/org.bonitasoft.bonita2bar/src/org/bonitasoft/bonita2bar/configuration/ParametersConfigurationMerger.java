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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

import org.bonitasoft.bonita2bar.configuration.model.Parameter;
import org.bonitasoft.bonita2bar.configuration.model.ParametersConfiguration;
import org.bonitasoft.bonita2bar.configuration.model.ProcessConfiguration;
import org.bonitasoft.bpm.model.util.FileUtil;
import org.bonitasoft.engine.bpm.bar.ParameterContribution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParametersConfigurationMerger {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParametersConfigurationMerger.class);

    private ParametersConfigurationMapper mapper = new ParametersConfigurationMapper();

    public void merge(File configurationArchiveFile, File inputParameterFile, String output)
            throws IOException {
        ConfigurationArchive configurationArchive = new ConfigurationArchive(configurationArchiveFile);
        ParametersConfiguration conf = configurationArchive.loadParametersConfiguration();
        ParametersConfiguration confToMerge = mapper.read(inputParameterFile);

        List<Parameter> allParameters = conf.getProcessConfigurations().stream()
                .map(ProcessConfiguration::getParameters)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        List<Parameter> globalParameters = confToMerge.getGlobalParameters();
        ParameterUpdateStatus globalStatus = updateParameterValue(allParameters, globalParameters);
        LOGGER.info("");
        globalStatus.getUpdatedParameters().stream().map(
                p -> String.format("Parameter %s (%s) has been updated using global parameters.",
                        p.getName(), p.getType()))
                .forEach(LOGGER::info);
        globalStatus.getDuplicates().stream()
                .map(p -> String.format("Parameter %s (%s) is duplicated in global_parameters of %s",
                        p.getName(), p.getType(), inputParameterFile.getName()))
                .forEach(LOGGER::warn);

        for (ProcessConfiguration pConf : conf.getProcessConfigurations()) {
            List<ProcessConfiguration> matchingProcesses = confToMerge.getProcessConfigurations().stream()
                    .filter(process -> Objects.equals(process.getName(), pConf.getName()))
                    .filter(process -> Objects.equals(process.getVersion(), pConf.getVersion()))
                    .collect(Collectors.toList());
            if (matchingProcesses.size() > 1) {
                LOGGER.warn("Duplicated process found with name '{}' and version '{}' in {}.",
                        pConf.getName(),
                        pConf.getVersion(),
                        inputParameterFile.getName());
            }
            if (!matchingProcesses.isEmpty()) {
                ProcessConfiguration processConfiguration = matchingProcesses.get(0);
                ParameterUpdateStatus processStatus = updateParameterValue(pConf.getParameters(),
                        processConfiguration.getParameters());
                processStatus.getUpdatedParameters().stream()
                        .map(p -> String.format("Parameter %s (%s) has been updated using '%s-%s' process parameter.",
                                p.getName(), p.getType(), processConfiguration.getName(),
                                processConfiguration.getVersion()))
                        .forEach(LOGGER::info);
                processStatus.getDuplicates().stream()
                        .map(p -> String.format(
                                "Parameter %s (%s) is duplicated in process parameters of '%s-%s' in %s",
                                p.getName(), p.getType(), processConfiguration.getName(),
                                processConfiguration.getVersion(),
                                inputParameterFile.getName()))
                        .forEach(LOGGER::warn);
            }
        }

        File outputFile = new File(output).getCanonicalFile();
        if (!configurationArchiveFile.equals(outputFile)) {
            Files.copy(configurationArchiveFile.toPath(), outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        update(outputFile, conf);
        LOGGER.info("");
        if (!configurationArchiveFile.equals(outputFile)) {
            LOGGER.info("{} has been created.", output);
        } else {
            LOGGER.info("{} has been updated.", output);
        }

    }

    protected ParameterUpdateStatus updateParameterValue(List<Parameter> allParameters,
            List<Parameter> globalParameters) {
        ParameterUpdateStatus parameterUpdateStatus = new ParameterUpdateStatus();
        for (Parameter p : allParameters) {
            List<Parameter> matchingParams = globalParameters.stream()
                    .filter(param -> Objects.equals(param.getName(), p.getName()))
                    .filter(param -> Objects.equals(param.getType(), p.getType()))
                    .collect(Collectors.toList());
            if (matchingParams.size() > 1) {
                parameterUpdateStatus.getDuplicates().add(p);
            }
            if (!matchingParams.isEmpty()) {
                Parameter parameter = matchingParams.get(0);
                if (!Objects.equals(p.getValue(), parameter.getValue())) {
                    p.setValue(parameter.getValue());
                    parameterUpdateStatus.getUpdatedParameters().add(parameter);
                }
            }
        }
        return parameterUpdateStatus;
    }

    private void update(File configurationArchiveFile, ParametersConfiguration conf) throws IOException {
        Path tmpFolder = Files.createTempDirectory(configurationArchiveFile.getName());
        ZipUtil.unzip(configurationArchiveFile, tmpFolder);
        mapper.writeToFile(conf, tmpFolder.resolve(ParametersConfigurationMapper.DEFAULT_PARAMETERS_FILE));

        for (ProcessConfiguration pConf : conf.getProcessConfigurations()) {
            Path propertiesFile = tmpFolder.resolve(pConf.getName()).resolve(pConf.getVersion())
                    .resolve(EnvironmentConfiguration.PARAMETERS_PROPERTIES_FILE);
            Properties properties = new Properties();
            try (InputStream is = new FileInputStream(propertiesFile.toFile())) {
                properties.load(is);
            }
            pConf.getParameters().stream().forEach(
                    p -> properties.setProperty(p.getName(),
                            String.valueOf(p.getValue() == null ? ParameterContribution.NULL : p.getValue())));
            try (OutputStream out = new FileOutputStream(propertiesFile.toFile())) {
                properties.store(out, null);
            }
        }

        Files.deleteIfExists(configurationArchiveFile.toPath());
        ZipUtil.zip(tmpFolder, configurationArchiveFile.toPath());
        FileUtil.deleteDir(tmpFolder);
    }

}
