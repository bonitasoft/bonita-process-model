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
package org.bonitasoft.bonita2bar.classpath;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.bonita2bar.BarArtifactProvider;
import org.bonitasoft.bonita2bar.BuildBarException;
import org.bonitasoft.bonita2bar.ClasspathResolver;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.util.EnvironmentUtil;
import org.bonitasoft.bpm.model.util.FileUtil;
import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.control.CompilationUnit;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.messages.SimpleMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import groovy.lang.GroovyClassLoader;

public class CustomGroovyArtifactProvider implements BarArtifactProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomGroovyArtifactProvider.class);

    static final String GROOVYSCRIPT_JAR = "groovyscripts.jar";
    static final String GROOVY_SOURCE_FOLDER = "src-groovy";

    private ClasspathResolver classpathResolver;
    private Path workingDirectory;
    private Path groovySource;

    public CustomGroovyArtifactProvider(Path groovySource, ClasspathResolver classpathResolver,
            Path workingDirectory) {
        this.groovySource = groovySource;
        this.classpathResolver = classpathResolver;
        this.workingDirectory = workingDirectory;
    }

    @Override
    public void build(BusinessArchiveBuilder builder, Pool process, Configuration configuration)
            throws BuildBarException {
        LOGGER.info("Adding custom groovy scripts in classpath...");

        // make sure to delete the target folder before even when there is no file to compile
        var targetClasses = workingDirectory.resolve("groovy-classes");
        if (Files.exists(targetClasses)) {
            try {
                FileUtil.deleteDir(targetClasses);
            } catch (IOException e) {
                throw new BuildBarException(String.format("Failed to delete folder %s", targetClasses), e);
            }
        }
        var outputJarFile = workingDirectory.resolve(GROOVYSCRIPT_JAR);
        try {
            Files.deleteIfExists(outputJarFile);
        } catch (IOException e) {
            throw new BuildBarException(String.format("Failed to delete custom groovy jar %s.", outputJarFile), e);
        }

        Set<File> filesToCompile = collectGroovySourceFile(groovySource, process);
        if (filesToCompile.isEmpty()) {
            LOGGER.info("No custom groovy script to compile found.");
            return;
        }

        try {
            Files.createDirectories(targetClasses);
        } catch (IOException e) {
            throw new BuildBarException(String.format("Failed to create folder %s", targetClasses), e);
        }
        try {
            compile(filesToCompile, targetClasses.toFile());
        } catch (CompilationFailedException | IOException e) {
            throw new BuildBarException(String.format("Failed to compile custom groovy files for %s (%s)",
                    process.getName(), process.getVersion()), e);
        }
        try {
            var jarFile = JarBuilder.createJar(targetClasses.toFile(), outputJarFile);
            builder.addClasspathResource(new BarResource(GROOVYSCRIPT_JAR, Files.readAllBytes(jarFile)));
        } catch (IOException e) {
            throw new BuildBarException(String.format("Failed to add custom groovy jar in %s (%s) bar.",
                    process.getName(), process.getVersion()), e);
        }
    }

    private void compile(Set<File> filesToCompile, File targetClasses) throws IOException, CompilationFailedException {
        try (GroovyClassLoader classLoader = new GroovyClassLoader()) {
            selectDependencies().forEach(classLoader::addURL);
            CompilerConfiguration configuration = new CompilerConfiguration();
            configuration.setTargetDirectory(targetClasses.getAbsolutePath());
            configuration.setSourceEncoding("utf-8");
            configuration.setTargetBytecode("17");
            CompilationUnit compileUnit = new CompilationUnit(configuration, null, classLoader);
            compileUnit.addSources(filesToCompile.toArray(new File[filesToCompile.size()]));
            if (EnvironmentUtil.isOSGi()) {
                // When using Groovy eclipse compiler, the CompilationUnit is modified and do
                // not execute the OUTPUT phase
                // See
                // https://github.com/groovy/groovy-eclipse/blob/v4.9.0/base/org.codehaus.groovy30/src/org/codehaus/groovy/control/CompilationUnit.java#L295
                compileUnit.addPhaseOperation(groovyClass -> {
                    String name = groovyClass.getName().replace('.', File.separatorChar) + ".class";
                    File path = new File(configuration.getTargetDirectory(), name);

                    // ensure the path is ready for the file
                    File directory = path.getParentFile();
                    if (directory != null && !directory.exists()) {
                        directory.mkdirs();
                    }

                    // create the file and write out the data
                    try (FileOutputStream stream = new FileOutputStream(path)) {
                        byte[] bytes = groovyClass.getBytes();
                        stream.write(bytes, 0, bytes.length);
                    } catch (IOException e) {
                        compileUnit.getErrorCollector().addError(new SimpleMessage(e.getMessage(), compileUnit));
                    }
                });
            }

            compileUnit.compile();
        }
    }

    private List<URL> selectDependencies() throws IOException {
        return classpathResolver.listFiles().stream()
                .map(File::toURI)
                .map(uri -> {
                    try {
                        LOGGER.debug("{} added to compilation classpath", uri);
                        return uri.toURL();
                    } catch (MalformedURLException e) {
                        throw new IllegalStateException(e);
                    }
                }).collect(Collectors.toList());
    }

    private Set<File> collectGroovySourceFile(Path groovySource, final Pool process) throws BuildBarException {
        try (Stream<Path> walker = Files.walk(groovySource)) {
            List<Path> groovyPaths = walker.filter(Files::isRegularFile)
                    .filter(file -> file.toString().endsWith(".groovy")).toList();
            return groovyPaths.stream().map(groovyPath -> {
                LOGGER.info("{} added to compilation classpath.",
                        groovySource.relativize(groovyPath));
                return groovyPath.toFile();
            }).collect(Collectors.toSet());
        } catch (IOException e) {
            throw new BuildBarException(String.format("Failed to read custom groovy libraries for %s (%s) bar.",
                    process.getName(), process.getVersion()), e);
        }
    }
}
