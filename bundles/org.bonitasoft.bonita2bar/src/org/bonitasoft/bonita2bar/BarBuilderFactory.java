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
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.bonita2bar.actor.ActorMappingArtifactProvider;
import org.bonitasoft.bonita2bar.classpath.ConnectorImplementationArtifactProvider;
import org.bonitasoft.bonita2bar.classpath.CustomGroovyArtifactProvider;
import org.bonitasoft.bonita2bar.classpath.DependenciesArtifactProvider;
import org.bonitasoft.bonita2bar.configuration.ParameterArtifactProvider;
import org.bonitasoft.bonita2bar.configuration.model.ParametersConfiguration;
import org.bonitasoft.bonita2bar.form.FormBuilder;
import org.bonitasoft.bonita2bar.form.FormMappingArtifactProvider;
import org.bonitasoft.bonita2bar.internal.M2eMavenExecutor;
import org.bonitasoft.bonita2bar.process.ProcessDefinitionArtifactProvider;
import org.bonitasoft.bonita2bar.process.pomgen.ProcessPomGenerator;
import org.bonitasoft.bonita2bar.resources.AdditionalResourcesArtifactProvider;
import org.bonitasoft.bonita2bar.resources.BPMN2ArtifactProvider;
import org.bonitasoft.bonita2bar.resources.DocumentArtifactProvider;
import org.bonitasoft.bpm.model.util.EnvironmentUtil;
import org.bonitasoft.bpm.model.util.FragmentTypes;

public class BarBuilderFactory {

    private BarBuilderFactory() {
        // private constructor
    }

    public static BarBuilder create(BuildConfig config) {
        ParametersConfiguration parametersConfiguration = new ParametersConfiguration();

        var sourceProvider = config.getSourcePathProvider();
        var processRegistry = config.getProcessRegistry();
        var implementationRegistry = config.getConnectorImplementationRegistry();
        var classpathResolver = config.getClasspathResolver();
        var workingDirectory = config.getWorkingDirectory();

        var pomGen = ProcessPomGenerator.create(config.getMavenProject(), implementationRegistry);

        var barBuilder = new BarBuilder(processRegistry,
                sourceProvider.getLocalConfiguration(),
                parametersConfiguration,
                workingDirectory,
                pomGen);
        barBuilder.register(new ParameterArtifactProvider(parametersConfiguration, config.includeParameters()));
        barBuilder.register(new ActorMappingArtifactProvider());
        barBuilder.register(new ProcessDefinitionArtifactProvider(config.getProcessRegistry()));
        barBuilder.register(new DependenciesArtifactProvider(config.getMavenExecutor()));
        //        barBuilder.register(new JarArtifactProvider(classpathResolver));
        barBuilder.register(new FormMappingArtifactProvider(sourceProvider.getForms(), config.getFormBuilder(),
                config.allowEmptyFormMapping()));

        barBuilder.register(new BPMN2ArtifactProvider(processRegistry, workingDirectory));
        barBuilder.register(new DocumentArtifactProvider(sourceProvider.getAttachments()));
        barBuilder.register(
                new CustomGroovyArtifactProvider(sourceProvider.getGroovySource(), classpathResolver,
                        workingDirectory));
        barBuilder.register(new ConnectorImplementationArtifactProvider(classpathResolver,
                implementationRegistry, FragmentTypes.CONNECTOR));
        barBuilder.register(new ConnectorImplementationArtifactProvider(classpathResolver,
                implementationRegistry, FragmentTypes.ACTOR_FILTER));
        barBuilder.register(new AdditionalResourcesArtifactProvider(sourceProvider.getResources()));
        return barBuilder;
    }

    public static class BuildConfig {

        private boolean allowEmptyFormMapping;
        private boolean includeParameters;
        private Path workingDirectory;
        private ConnectorImplementationRegistry connectorImplementationRegistry;
        private FormBuilder formBuilder;
        private ProcessRegistry processRegistry;
        private MavenProject mavenProject;
        private SourcePathProvider sourcePathProvider;
        private ClasspathResolver classpathResolver;
        private MavenExecutor mavenExecutor;

        private BuildConfig(BuildConfigBuilder builder) throws BuildBarException {
            this.allowEmptyFormMapping = builder.allowEmptyFormMapping;
            this.includeParameters = builder.includeParameters;
            this.workingDirectory = builder.workingDirectory;
            this.connectorImplementationRegistry = builder.connectorImplementationRegistry;
            this.formBuilder = builder.formBuilder;
            this.processRegistry = builder.processRegistry;
            this.mavenProject = builder.mavenProject;
            this.sourcePathProvider = Optional.ofNullable(builder.sourcePathProvider).orElseGet(() -> {
                if (mavenProject != null) {
                    var baseDir = mavenProject.getBasedir();
                    if (baseDir != null) {
                        return SourcePathProvider.of(baseDir.toPath());
                    }
                }
                return null;
            });
            this.mavenExecutor = Optional.ofNullable(builder.mavenExecutor).orElseGet(
                    () -> EnvironmentUtil.isOSGi() ? new M2eMavenExecutor() : MavenExecutor.getCliImplementation());
            AtomicReference<DependencyResolutionRequiredException> exceptionDuringClasspathResolver = new AtomicReference<>();
            this.classpathResolver = Optional.ofNullable(builder.classpathResolver).orElseGet(() -> {
                if (mavenProject != null) {
                    // build classpath
                    try {
                        var classpath = Stream
                                .concat(mavenProject.getCompileClasspathElements().stream(),
                                        mavenProject.getRuntimeClasspathElements().stream())
                                .distinct().collect(Collectors.toList());
                        if (!classpath.isEmpty()) {
                            return ClasspathResolver.of(classpath);
                        }
                    } catch (DependencyResolutionRequiredException e) {
                        exceptionDuringClasspathResolver.set(e);
                    }
                }
                return null;
            });
            // rethrow exception if needed
            if (exceptionDuringClasspathResolver.get() != null) {
                throw new BuildBarException(exceptionDuringClasspathResolver.get());
            }
        }

        public MavenExecutor getMavenExecutor() {
            return mavenExecutor;
        }

        public boolean includeParameters() {
            return includeParameters;
        }

        public boolean allowEmptyFormMapping() {
            return allowEmptyFormMapping;
        }

        public Path getWorkingDirectory() {
            Objects.requireNonNull(workingDirectory, "No workingDirectory configured.");
            return workingDirectory;
        }

        public ConnectorImplementationRegistry getConnectorImplementationRegistry() {
            Objects.requireNonNull(connectorImplementationRegistry, "No ConnectorImplementationRegistry configured.");
            return connectorImplementationRegistry;
        }

        public FormBuilder getFormBuilder() {
            Objects.requireNonNull(formBuilder, "No FormBuilder configured.");
            return formBuilder;
        }

        public ProcessRegistry getProcessRegistry() {
            Objects.requireNonNull(processRegistry, "No ProcessRegistry configured.");
            return processRegistry;
        }

        public MavenProject getMavenProject() {
            Objects.requireNonNull(mavenProject, "No MavenProject configured.");
            return mavenProject;
        }

        public SourcePathProvider getSourcePathProvider() {
            Objects.requireNonNull(sourcePathProvider, "No SourcePathProvider configured.");
            return sourcePathProvider;
        }

        public ClasspathResolver getClasspathResolver() {
            Objects.requireNonNull(classpathResolver, "No ClasspathResolver configured.");
            return classpathResolver;
        }

        public static BuildConfigBuilder builder() {
            return new BuildConfigBuilder();
        }

        public static class BuildConfigBuilder {

            private boolean allowEmptyFormMapping = true;
            /* Studio includes them, la-builder not... */
            private boolean includeParameters = false;
            private Path workingDirectory;
            private ConnectorImplementationRegistry connectorImplementationRegistry;
            private FormBuilder formBuilder;
            private ProcessRegistry processRegistry;
            private MavenProject mavenProject;
            private SourcePathProvider sourcePathProvider;
            private ClasspathResolver classpathResolver;
            private MavenExecutor mavenExecutor;

            private BuildConfigBuilder() {

            }

            public BuildConfig build() throws BuildBarException {
                return new BuildConfig(this);
            }

            public BuildConfigBuilder allowEmptyFormMapping(boolean allowEmptyFormMapping) {
                this.allowEmptyFormMapping = allowEmptyFormMapping;
                return this;
            }

            public BuildConfigBuilder includeParameters(boolean includeParameters) {
                this.includeParameters = includeParameters;
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

            public BuildConfigBuilder connectorImplementationRegistry(
                    ConnectorImplementationRegistry connectorImplementationRegistry) {
                this.connectorImplementationRegistry = connectorImplementationRegistry;
                return this;
            }

            public BuildConfigBuilder processRegistry(ProcessRegistry processRegistry) {
                this.processRegistry = processRegistry;
                return this;
            }

            /**
             * Optional, if not provided, will use the mavenProject's paths
             * 
             * @param sourcePathProvider source path provider
             * @return builder
             */
            public BuildConfigBuilder sourcePathProvider(SourcePathProvider sourcePathProvider) {
                this.sourcePathProvider = sourcePathProvider;
                return this;
            }

            /**
             * Optional, if not provided, will use the mavenProject's classpath (if set)
             * 
             * @param classpathResolver classpath resolver
             * @return builder
             */
            public BuildConfigBuilder classpathResolver(ClasspathResolver classpathResolver) {
                this.classpathResolver = classpathResolver;
                return this;
            }

            public BuildConfigBuilder mavenProject(MavenProject mavenProject) {
                this.mavenProject = mavenProject;
                return this;
            }

            /**
             * Optional, if not provided, will use a default MavenExecutor
             * 
             * @param mavenExecutor maven executor
             * @return builder
             */
            public BuildConfigBuilder mavenExecutor(MavenExecutor mavenExecutor) {
                this.mavenExecutor = mavenExecutor;
                return this;
            }

        }
    }

}
