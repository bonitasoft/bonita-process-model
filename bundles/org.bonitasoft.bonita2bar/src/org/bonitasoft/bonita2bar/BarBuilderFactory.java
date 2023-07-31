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
package org.bonitasoft.bonita2bar;

import java.nio.file.Path;
import java.util.Objects;

import org.bonitasoft.bonita2bar.actor.ActorMappingArtifactProvider;
import org.bonitasoft.bonita2bar.classpath.ConnectorImplementationArtifactProvider;
import org.bonitasoft.bonita2bar.classpath.ConnectorImplementationProvider;
import org.bonitasoft.bonita2bar.classpath.CustomGroovyArtifactProvider;
import org.bonitasoft.bonita2bar.classpath.JarArtifactProvider;
import org.bonitasoft.bonita2bar.configuration.ParameterArtifactProvider;
import org.bonitasoft.bonita2bar.configuration.model.ParametersConfiguration;
import org.bonitasoft.bonita2bar.form.FormBuilder;
import org.bonitasoft.bonita2bar.form.FormMappingArtifactProvider;
import org.bonitasoft.bonita2bar.process.ProcessDefinitionArtifactProvider;
import org.bonitasoft.bonita2bar.resources.AdditionalResourcesArtifactProvider;
import org.bonitasoft.bonita2bar.resources.BPMN2ArtifactProvider;
import org.bonitasoft.bonita2bar.resources.DocumentArtifactProvider;
import org.bonitasoft.bpm.model.util.FragmentTypes;
import org.bonitasoft.plugin.analyze.report.model.DependencyReport;

public class BarBuilderFactory {

    private BarBuilderFactory() {
        // private constructor
    }

    public static BarBuilder create(BuildConfig config) {
        ParametersConfiguration parametersConfiguration = new ParametersConfiguration();

        var sourceProvider = config.getSourcePathProvider();
        var processRegistry = config.getProcessRegistry();
        var dependencyReport = config.getDependencyReport();
        var classpathResolver = config.getClasspathResolver();
        var workingDirectory = config.getWorkingDirectory();

        var barBuilder = new BarBuilder(processRegistry,
                sourceProvider.getLocalConfiguration(),
                parametersConfiguration,
                workingDirectory);
        barBuilder.register(new ParameterArtifactProvider(parametersConfiguration));
        barBuilder.register(new ActorMappingArtifactProvider());
        barBuilder.register(new ProcessDefinitionArtifactProvider(config.getProcessRegistry()));
        barBuilder.register(new JarArtifactProvider(classpathResolver));
        barBuilder.register(new FormMappingArtifactProvider(sourceProvider.getForms(), config.getFormBuilder(),
                config.allowEmptyFormMapping()));

        barBuilder.register(new BPMN2ArtifactProvider(processRegistry, workingDirectory));
        barBuilder.register(new DocumentArtifactProvider(sourceProvider.getAttachments()));
        barBuilder.register(
                new CustomGroovyArtifactProvider(sourceProvider.getGroovySource(), classpathResolver,
                        workingDirectory));
        barBuilder.register(new ConnectorImplementationArtifactProvider(classpathResolver,
                new ConnectorImplementationProvider(dependencyReport), FragmentTypes.CONNECTOR));
        barBuilder.register(new ConnectorImplementationArtifactProvider(classpathResolver,
                new ConnectorImplementationProvider(dependencyReport), FragmentTypes.ACTOR_FILTER));
        barBuilder.register(new AdditionalResourcesArtifactProvider(sourceProvider.getResources()));
        return barBuilder;
    }

    public static class BuildConfig {

        private boolean allowEmptyFormMapping;
        private Path workingDirectory;
        private DependencyReport dependencyReport;
        private FormBuilder formBuilder;
        private ProcessRegistry processRegistry;
        private SourcePathProvider sourcePathProvider;
        private ClasspathResolver classpathResolver;

        private BuildConfig(BuildConfigBuilder builder) {
            this.allowEmptyFormMapping = builder.allowEmptyFormMapping;
            this.workingDirectory = builder.workingDirectory;
            this.dependencyReport = builder.dependencyReport;
            this.formBuilder = builder.formBuilder;
            this.processRegistry = builder.processRegistry;
            this.sourcePathProvider = builder.sourcePathProvider;
            this.classpathResolver = builder.classpathResolver;
        }

        public boolean allowEmptyFormMapping() {
            return allowEmptyFormMapping;
        }

        public Path getWorkingDirectory() {
            Objects.requireNonNull(workingDirectory, "No workingDirectory configured.");
            return workingDirectory;
        }

        public DependencyReport getDependencyReport() {
            Objects.requireNonNull(dependencyReport, "No DependencyReport configured.");
            return dependencyReport;
        }

        public FormBuilder getFormBuilder() {
            Objects.requireNonNull(formBuilder, "No FormBuilder configured.");
            return formBuilder;
        }

        public ProcessRegistry getProcessRegistry() {
            Objects.requireNonNull(processRegistry, "No ProcessRegistry configured.");
            return processRegistry;
        }

        public SourcePathProvider getSourcePathProvider() {
            Objects.requireNonNull(sourcePathProvider, "No SourcePathProvider configured.");
            return sourcePathProvider;
        }

        public ClasspathResolver getClasspathResolver() {
            Objects.requireNonNull(sourcePathProvider, "No ClasspathResolver configured.");
            return classpathResolver;
        }

        public static BuildConfigBuilder builder() {
            return new BuildConfigBuilder();
        }

        public static class BuildConfigBuilder {

            private boolean allowEmptyFormMapping = true;
            private Path workingDirectory;
            private DependencyReport dependencyReport;
            private FormBuilder formBuilder;
            private ProcessRegistry processRegistry;
            private SourcePathProvider sourcePathProvider;
            private ClasspathResolver classpathResolver;

            private BuildConfigBuilder() {

            }

            public BuildConfig build() {
                return new BuildConfig(this);
            }

            public BuildConfigBuilder allowEmptyFormMapping(boolean allowEmptyFormMapping) {
                this.allowEmptyFormMapping = allowEmptyFormMapping;
                return this;
            }

            public BuildConfigBuilder workingDirectory(Path workingDirectory) {
                this.workingDirectory = workingDirectory;
                return this;
            }

            public BuildConfigBuilder formBuilder(FormBuilder formBuilder) {
                this.formBuilder = formBuilder;
                return this;
            }

            public BuildConfigBuilder dependencyReport(DependencyReport dependencyReport) {
                this.dependencyReport = dependencyReport;
                return this;
            }

            public BuildConfigBuilder processRegistry(ProcessRegistry processRegistry) {
                this.processRegistry = processRegistry;
                return this;
            }

            public BuildConfigBuilder sourcePathProvider(SourcePathProvider sourcePathProvider) {
                this.sourcePathProvider = sourcePathProvider;
                return this;
            }

            public BuildConfigBuilder classpathResolver(ClasspathResolver classpathResolver) {
                this.classpathResolver = classpathResolver;
                return this;
            }

        }
    }

}
