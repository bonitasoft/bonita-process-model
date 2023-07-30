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
package org.bonitasoft.bpm.standalone.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

import org.apache.commons.lang3.SystemUtils;
import org.bonitasoft.bonita2bar.BarBuilderFactory;
import org.bonitasoft.bonita2bar.BarBuilderFactory.BuildConfig;
import org.bonitasoft.bonita2bar.ClasspathResolver;
import org.bonitasoft.bonita2bar.ProcessRegistry;
import org.bonitasoft.bonita2bar.SourcePathProvider;
import org.bonitasoft.bpm.model.FileUtil;
import org.bonitasoft.bpm.model.MavenUtil;
import org.bonitasoft.bpm.model.process.util.migration.MigrationPolicy;
import org.bonitasoft.web.designer.ArtifactBuilderFactory;
import org.bonitasoft.web.designer.config.UiDesignerProperties;
import org.bonitasoft.web.designer.config.UiDesignerPropertiesBuilder;
import org.bonitasoft.web.designer.controller.export.ExportException;
import org.bonitasoft.web.designer.model.ModelException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class BarBuilderIT {

    @Test
    void buildProjectBusinessArchives(@TempDir Path tmpDir) throws Exception {
        var projectRoot = tmpDir.resolve("test-respository");
        FileUtil.copyDirectory(new File(BarBuilderIT.class.getResource("/test-repository").getFile()).getAbsolutePath(),
                projectRoot.toFile().getAbsolutePath());
        var mvnExecutable = SystemUtils.IS_OS_WINDOWS ? "mvn.cmd" : "mvn";
        var artifactBuilder = new ArtifactBuilderFactory(uidWorkspaceProperties(projectRoot, tmpDir.resolve("uid")))
                .create();

        var barBuilder = BarBuilderFactory.create(BuildConfig.builder()
                .environment("Local")
                .formBuilder(formId -> {
                    try {
                        return artifactBuilder.buildPage(formId);
                    } catch (ExportException | ModelException e) {
                        throw new RuntimeException(e);
                    }
                })
                .workingDirectory(tmpDir.resolve("workdir"))
                .classpathResolver(ClasspathResolver.of(MavenUtil.buildClasspath(projectRoot, mvnExecutable)))
                .dependencyReport(MavenUtil.loadReport(MavenUtil.analyze(projectRoot, mvnExecutable)))
                .processRegistry(ProcessRegistry.of(projectRoot.resolve("app").resolve("diagrams"),
                        MigrationPolicy.ALWAYS_MIGRATE_POLICY))
                .sourcePathProvider(SourcePathProvider.of(projectRoot))
                .build());

        var result = barBuilder.buildAll();
        var barOutputFolder = projectRoot.resolve("target").resolve("processes");
        result.writeBusinessArchivesTo(barOutputFolder);
        var bonitaConfigurationFile = projectRoot.resolve("target").resolve("test-repository.bconf");
        result.writeBonitaConfigurationTo(bonitaConfigurationFile);

        assertThat(result.getBusinessArchives()).hasSize(12);
        assertThat(bonitaConfigurationFile).exists();
        assertThat(barOutputFolder)
                .exists()
                .isDirectoryContaining(bar -> Objects.equals(bar.getFileName().toString(), "ChildProcess--1.0.bar"))
                .isDirectoryContaining(bar -> Objects.equals(bar.getFileName().toString(), "ParentProcess--1.0.bar"))
                .isDirectoryContaining(
                        bar -> Objects.equals(bar.getFileName().toString(), "ProcessWithAdditionalResource--1.0.bar"))
                .isDirectoryContaining(
                        bar -> Objects.equals(bar.getFileName().toString(), "ProcessWithConnectors--1.0.bar"))
                .isDirectoryContaining(
                        bar -> Objects.equals(bar.getFileName().toString(), "PoolWithDecisionTable--1.0.bar"))
                .isDirectoryContaining(
                        bar -> Objects.equals(bar.getFileName().toString(), "ProcessWithComparisonExpression--1.0.bar"))
                .isDirectoryContaining(
                        bar -> Objects.equals(bar.getFileName().toString(), "ProcessWithCustonGroovyDep--1.0.bar"))
                .isDirectoryContaining(
                        bar -> Objects.equals(bar.getFileName().toString(), "ProcessWithDocument--1.0.bar"))
                .isDirectoryContaining(
                        bar -> Objects.equals(bar.getFileName().toString(), "ProcessWithGroovyConnector--1.0.bar"))
                .isDirectoryContaining(
                        bar -> Objects.equals(bar.getFileName().toString(), "ProcessWithv7Forms--1.0.bar"))
                .isDirectoryContaining(
                        bar -> Objects.equals(bar.getFileName().toString(), "SimpleProcessWithContract--1.0.bar"))
                .isDirectoryContaining(
                        bar -> Objects.equals(bar.getFileName().toString(), "SimpleProcessWithParameters--1.0.bar"));
    }

    @Test
    void buildBusinessArchivesWithMigration(@TempDir Path tmpDir) throws Exception {
        var projectRoot = tmpDir.resolve("test-respository");
        FileUtil.copyDirectory(new File(BarBuilderIT.class.getResource("/test-repository").getFile()).getAbsolutePath(),
                projectRoot.toFile().getAbsolutePath());
        var mvnExecutable = SystemUtils.IS_OS_WINDOWS ? "mvn.cmd" : "mvn";
        var artifactBuilder = new ArtifactBuilderFactory(uidWorkspaceProperties(projectRoot, tmpDir.resolve("uid")))
                .create();

        var barBuilder = BarBuilderFactory.create(BuildConfig.builder()
                .environment("Local")
                .formBuilder(formId -> {
                    try {
                        return artifactBuilder.buildPage(formId);
                    } catch (ExportException | ModelException e) {
                        throw new RuntimeException(e);
                    }
                })
                .workingDirectory(tmpDir.resolve("workdir"))
                .classpathResolver(ClasspathResolver.of(MavenUtil.buildClasspath(projectRoot, mvnExecutable)))
                .dependencyReport(MavenUtil.loadReport(MavenUtil.analyze(projectRoot, mvnExecutable)))
                .processRegistry(ProcessRegistry.of(projectRoot.resolve("app").resolve("diagrams"),
                        MigrationPolicy.ALWAYS_MIGRATE_POLICY))
                .sourcePathProvider(SourcePathProvider.of(projectRoot))
                .build());

        var result = barBuilder.build("ProcessWithAdditionalResource", "1.0");

        assertThat(result.getBusinessArchives()).hasSize(1);
    }

    private UiDesignerProperties uidWorkspaceProperties(Path projectRoot, Path uidWorkdir) {
        return new UiDesignerPropertiesBuilder()
                .workspaceUidPath(uidWorkdir)
                .experimental(false)
                .workspacePath(projectRoot.resolve("app"))
                .pagesFolderName("web_page")
                .widgetsFolderName("web_widgets")
                .fragmentsFolderName("web_fragments")
                .build();
    }

}
