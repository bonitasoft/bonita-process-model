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

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;

import org.bonitasoft.bonita2bar.ClasspathResolver;
import org.bonitasoft.bonita2bar.ProcessRegistry;
import org.bonitasoft.bonita2bar.SourcePathProvider;
import org.bonitasoft.bpm.model.FileUtil;
import org.bonitasoft.bpm.model.MavenUtil;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.process.util.migration.MigrationPolicy;
import org.bonitasoft.bpm.model.util.ModelLoader;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomGroovyArtifactProviderTest {

    private Path projectRoot;

    @BeforeEach
    void setup() throws Exception {
        projectRoot = Files.createTempDirectory("test-repository");
        FileUtil.copyDirectory(new File(URLDecoder.decode(
                FileLocator.toFileURL(CustomGroovyArtifactProviderTest.class.getResource("/test-repository")).getFile(),
                "UTF-8")).getAbsolutePath(), projectRoot.toFile().getAbsolutePath());
    }

    @AfterEach
    void after() throws Exception {
        if (Files.exists(projectRoot)) {
            FileUtil.deleteDir(projectRoot);
        }
    }

    @Test
    void should_compile_groovy_classes() throws Exception {
        // given
        var outputFolder = projectRoot.resolve("target");
        var classpathFile = MavenUtil.buildClasspath(projectRoot, Platform.getOS().contains("win") ? "mvn.cmd" : "mvn");

        CustomGroovyArtifactProvider customGroovyArtifactProvider = new CustomGroovyArtifactProvider(
                SourcePathProvider.of(projectRoot).getGroovySource(), ClasspathResolver.of(classpathFile),
                outputFolder);
        BusinessArchiveBuilder builder = new BusinessArchiveBuilder().createNewBusinessArchive();
        var registry = ProcessRegistry.of(projectRoot.resolve("app").resolve("diagrams"),
                MigrationPolicy.NEVER_MIGRATE_POLICY);

        File confFolder = projectRoot.resolve("app").resolve("process_configurations").toFile();
        var configurationResource = ModelLoader.getInstance()
                .loadModel(URI.createFileURI(new File(confFolder, "_xQhDcRxzEeiplJoiu3AUHg.conf").getAbsolutePath()));

        // when
        customGroovyArtifactProvider.build(builder,
                registry.getProcess("ProcessWithCustonGroovyDep", "1.0").orElseThrow(),
                (Configuration) configurationResource.getContents().get(0));

        // then
        File targetClasses = outputFolder.resolve("groovy-classes").toFile();
        assertThat(targetClasses.listFiles()).doesNotContain(new File(targetClasses, "MyLib3.class"))
                .contains(new File(targetClasses, "MyLib.class"), new File(targetClasses, "MyLib2.class"));

        assertThat(outputFolder.resolve(CustomGroovyArtifactProvider.GROOVYSCRIPT_JAR)).exists();
    }

}
