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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.bonita2bar.configuration.model.Parameter;
import org.bonitasoft.bonita2bar.configuration.model.ParametersConfiguration;
import org.bonitasoft.bonita2bar.configuration.model.ProcessConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterConfigurationExtractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterConfigurationExtractor.class);

    private ParametersConfigurationMapper mapper = new ParametersConfigurationMapper();

    public void extract(File configurationArchiveFile, String outputFile, boolean withoutValue)
            throws IOException {
        ConfigurationArchive configurationArchive = new ConfigurationArchive(configurationArchiveFile);
        ParametersConfiguration conf = configurationArchive.loadParametersConfiguration();

        boolean hasParameter = withoutValue
                ? extractWithoutValueOnly(outputFile, conf)
                : extractAll(outputFile, conf);

        LOGGER.info("");
        if (hasParameter) {
            LOGGER.info("Parameter descriptor file has been extracted to {}.",
                    new File(outputFile).getCanonicalFile().getAbsolutePath());
        } else {
            LOGGER.info("No parameters found. No file extracted.");
        }
    }

    private boolean extractWithoutValueOnly(String outputFile, ParametersConfiguration conf)
            throws IOException {
        boolean hasParameter = false;
        ParametersConfiguration removeValuatedParameters = removeValuatedParameters(conf);
        for (ProcessConfiguration pConf : removeValuatedParameters.getProcessConfigurations()) {
            LOGGER.info("");
            LOGGER.info("[{} - {}]", pConf.getName(), pConf.getVersion());
            for (Parameter p : pConf.getParameters()) {
                hasParameter = true;
                printParam(p);
            }
        }
        if (hasParameter) {
            mapper.writeToFile(removeValuatedParameters, new File(outputFile).getCanonicalFile().toPath());
        }
        return hasParameter;
    }

    private boolean extractAll(String outputFile, ParametersConfiguration conf) throws IOException {
        List<ProcessConfiguration> processWithParams = conf.getProcessConfigurations().stream()
                .filter(process -> !process.getParameters().isEmpty())
                .collect(Collectors.toList());
        boolean hasParameter = !processWithParams.isEmpty();
        for (ProcessConfiguration pConf : processWithParams) {
            LOGGER.info("");
            LOGGER.info("[{} - {}]", pConf.getName(), pConf.getVersion());
            List<Parameter> paramsWithoutValue = new ArrayList<>();
            for (Parameter p : pConf.getParameters()) {
                if (p.getValue() == null) {
                    paramsWithoutValue.add(p);
                    continue;
                }
                printParam(p);
            }
            if (!paramsWithoutValue.isEmpty()) {
                LOGGER.info("-- Parameters without value --");
                for (Parameter param : paramsWithoutValue) {
                    printParam(param);
                }
            }
        }
        if (hasParameter) {
            mapper.writeToFile(conf, new File(outputFile).getCanonicalFile().toPath());
        }
        return hasParameter;
    }

    private void printParam(Parameter p) {
        if (p.getDescription() != null && !p.getDescription().isEmpty()) {
            LOGGER.info("{} ({}) -- {}", p.getName(), p.getType(), p.getDescription());
        } else {
            LOGGER.info("{} ({})", p.getName(), p.getType());
        }
    }

    private ParametersConfiguration removeValuatedParameters(ParametersConfiguration conf) {
        for (ProcessConfiguration pConf : conf.getProcessConfigurations()) {
            if (pConf.getParameters() != null) {
                pConf.getParameters().removeIf(p -> p.getValue() != null);
            }
        }
        conf.getProcessConfigurations().removeIf(p -> p.getParameters() == null || p.getParameters().isEmpty());
        return conf;
    }

}
