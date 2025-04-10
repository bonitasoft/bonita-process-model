/**
 * Copyright (C) 2025 Bonitasoft S.A.
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.bonita2bar.BarBuilderFactory.BuildConfig;
import org.bonitasoft.bpm.model.MavenUtil;
import org.bonitasoft.bpm.model.process.util.migration.MigrationPolicy;
import org.eclipse.core.runtime.FileLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class BarBuilderWithoutDependenciesTest {

    private BarBuilder barBuilder;
    private ProcessRegistry processRegistry;
    private MavenProject appProject;

    @BeforeEach
    void setUp() throws Exception {
        var repoRoot = new File(URLDecoder.decode(
                FileLocator.toFileURL(BarBuilderWithoutDependenciesTest.class.getResource("/my-project")).getFile(),
                "UTF-8"));
        Path appPath = repoRoot.toPath().resolve("app");
        var appPomFile = appPath.resolve("pom.xml").toFile();
        // load maven project
        MavenXpp3Reader reader = new MavenXpp3Reader();
        try (var fileReader = new FileReader(appPomFile)) {
            var model = reader.read(fileReader);
            appProject = new MavenProject(model);
            appProject.setFile(appPomFile);
        }
        var mvnExecutable = MavenUtil.getMvnExecutable();
        var classpath = MavenUtil.buildClasspath(repoRoot.toPath(), mvnExecutable);

        processRegistry = ProcessRegistry.of(appPath.resolve("diagrams"),
                MigrationPolicy.NEVER_MIGRATE_POLICY);
        barBuilder = BarBuilderFactory
                .create(BuildConfig.builder()
                        .connectorImplementationRegistry(ConnectorImplementationRegistry.of(List.of()))
                        .formBuilder(id -> new byte[0]).workingDirectory(repoRoot.toPath().resolve("target"))
                        .mavenProject(appProject)
                        .processRegistry(processRegistry)
                        .classpathResolver(ClasspathResolver.of(classpath))
                        // no dependencies
                        .withDependencyJars(false)
                        .build());
    }

    @Test
    void should_build_jarless_bar(@TempDir Path tmpFolder) throws Exception {
        var result = barBuilder.build("SimpleProcessWithParameters", "1.0", "Local");

        assertThat(result.getBusinessArchives()).hasSize(1);
        assertThat(result.getConfigurations()).hasSize(1);

        var barOutput = Files.createDirectory(tmpFolder.resolve("bars"));

        result.writeBusinessArchivesTo(barOutput);
        var bonitaConfigurationFile = tmpFolder.resolve("my-project.bconf");
        result.writeBonitaConfigurationTo(bonitaConfigurationFile);

        assertThat(barOutput.resolve("SimpleProcessWithParameters--1.0.bar")).exists();
        assertThat(bonitaConfigurationFile).exists();

        // inspect the bar zip content
        File barFile = barOutput.resolve("SimpleProcessWithParameters--1.0.bar").toFile();
        boolean jarless = false;
        try (ZipInputStream zipInputstream = new ZipInputStream(new FileInputStream(barFile));) {
            ZipEntry zipEntry = null;
            while ((zipEntry = zipInputstream.getNextEntry()) != null) {
                if (".jarless".equals(zipEntry.getName())) {
                    jarless = true;
                }
            }
        }
        assertThat(jarless).isTrue();
    }
}
