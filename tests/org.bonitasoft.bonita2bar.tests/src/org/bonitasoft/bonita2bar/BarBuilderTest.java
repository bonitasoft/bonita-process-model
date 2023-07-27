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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import org.bonitasoft.bonita2bar.BarBuilderFactory.BuildConfig;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.engine.bpm.bar.InvalidBusinessArchiveFormatException;
import org.bonitasoft.plugin.analyze.report.model.DependencyReport;
import org.eclipse.core.runtime.FileLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;

class BarBuilderTest {

    private BarBuilder barBuilder;
    private ProcessRegistry processRegistry;
    private SourcePathProvider sourcePathProvider;

    @BeforeEach
    void setUp() throws Exception {
        var repoRoot = new File(URLDecoder.decode(
                FileLocator.toFileURL(BarBuilderTest.class.getResource("/test-repository")).getFile(), "UTF-8"));

        processRegistry = ProcessRegistry.of(repoRoot.toPath().resolve("app").resolve("diagrams"));
        sourcePathProvider = SourcePathProvider.of(repoRoot.toPath());
        barBuilder = BarBuilderFactory.create(BuildConfig.builder()
                .environment("Local")
                .dependencyReport(new DependencyReport())
                .formBuilder(id -> new byte[0])
                .workingDirectory(repoRoot.toPath().resolve("target"))
                .sourcePathProvider(sourcePathProvider)
                .processRegistry(processRegistry)
                .build());
    }

    @Test
    void should_build_bar_for_process(@TempDir Path tmpFolder) throws Exception {
        var result = barBuilder.build("SimpleProcessWithParameters", "1.0");

        assertThat(result.getBusinessArchives()).hasSize(1);
        assertThat(result.getConfigurations()).hasSize(1);

        var barOutput = Files.createDirectory(tmpFolder.resolve("bars"));

        result.writeBusinessArchivesTo(barOutput);
        var bonitaConfigurationFile = tmpFolder.resolve("test-repository.bconf");
        result.writeBonitaConfigurationTo(bonitaConfigurationFile);

        assertThat(barOutput.resolve("SimpleProcessWithParameters--1.0.bar")).exists();
        assertThat(bonitaConfigurationFile).exists();
    }

    @Test
    void should_throw_an_IllegalArgumentExcpetion_when_process_not_found() throws Exception {
        assertThatThrownBy(() -> barBuilder.build("Unknown", "1.0")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void should_throw_a_BuildBarException_when_a_builder_failed() throws Exception {
        BusinessArchiveBuilder builder = mock(BusinessArchiveBuilder.class);
        var spyedBuilder = Mockito.spy(barBuilder);
        doReturn(builder).when(spyedBuilder).createBusinessArchiveBuilder();
        doThrow(new InvalidBusinessArchiveFormatException("error")).when(builder).done();

        assertThatThrownBy(() -> spyedBuilder.build("SimpleProcessWithParameters", "1.0"))
                .isInstanceOf(BuildBarException.class);
    }

    @Test
    void should_retieve_local_configuration_for_a_pool() throws Exception {
        var process = processRegistry.getProcess("SimpleProcessWithParameters", "1.0").orElseThrow();

        final Optional<Configuration> configuration = BarBuilder
                .getConfiguration(process, sourcePathProvider.getLocalConfiguration(), null);

        assertThat(configuration).isPresent();
    }

    @Test
    void should_have_implementation_version() throws Exception {
        var version = BarBuilder.builderVersion();

        assertThat(version).isNotNull().isNotEmpty();
    }

}
