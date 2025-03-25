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
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import org.apache.maven.shared.utils.StringUtils;
import org.assertj.core.api.Condition;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry.ArtifactInfo;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry.ConnectorImplementationJar;
import org.bonitasoft.bpm.model.FileUtil;
import org.bonitasoft.bpm.model.MavenUtil;
import org.codehaus.plexus.util.FileUtils;
import org.eclipse.core.runtime.FileLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for ConnectorImplementationRegistry
 */
class ConnectorImplementationRegistryTest {

    private Path projectRoot;

    @BeforeEach
    void setUp() throws Exception {
        projectRoot = Files.createTempDirectory("my-project");
        var testRepoRoot = new File(URLDecoder.decode(
                FileLocator.toFileURL(ConnectorImplementationRegistryTest.class.getResource("/my-project"))
                        .getFile(),
                "UTF-8"));
        FileUtil.copyDirectory(testRepoRoot.getAbsolutePath(), projectRoot.toFile().getAbsolutePath());
    }

    @Test
    void should_ConnectorImplementationJar_legacyEqualModernContructor() throws Exception {
        var mvnExecutable = MavenUtil.getMvnExecutable();
        var jsonReportFile = MavenUtil.analyze(projectRoot, mvnExecutable);

        // jsonReportFile should contain e.g. the email connector
        var report = MavenUtil.loadReport(jsonReportFile);
        var implementations = (List<Map<String, Object>>) report.get("connectorImplementations");
        Map<String, Object> emailImpl = implementations.stream()
                .filter(impl -> impl.get("definitionId").equals("email"))
                .findFirst().get();
        var artifact = ((Map<String, String>) emailImpl.get("artifact"));
        @SuppressWarnings("deprecation")
        var legacy = ConnectorImplementationJar.of(
                (String) emailImpl.get("implementationId"),
                (String) emailImpl.get("implementationVersion"),
                new File((String) artifact.get("file")),
                (String) emailImpl.get("jarEntry"));
        var modern = ConnectorImplementationJar.of(
                (String) emailImpl.get("implementationId"),
                (String) emailImpl.get("implementationVersion"),
                new ArtifactInfo(artifact.get("groupId"), artifact.get("artifactId"), artifact.get("version"),
                        artifact.get("classifier"), artifact.get("file")),
                (String) emailImpl.get("jarEntry"));
        // legacy constructor has found the artifact info on its own...
        assertEquals(legacy, modern);
    }

    @Test
    void should_find_ArtifactInfo_from_internalPom() throws Exception {
        // given a jar with a pom.xml file into it
        Path jarFilePath = Files.createTempFile("myLib", ".jar");
        try {
            var entryName = "META-INF/maven/org.bonitasoft/myArtifactId/pom.xml";
            var pomContent = """
                        <project>
                        <modelVersion>4.0.0</modelVersion>
                        <parent>
                            <groupId>com.bonitasoft</groupId>
                            <artifactId>myParent</artifactId>
                            <version>1.0.0</version>
                        </parent>
                        <groupId>org.bonitasoft</groupId>
                        <artifactId>myArtifactId</artifactId>
                        <version>0.50.0</version>
                    </project>
                    """;
            try (var out = new JarOutputStream(new FileOutputStream(jarFilePath.toFile()))) {
                var pomEntry = new JarEntry(entryName);
                out.putNextEntry(pomEntry);
                out.write(pomContent.getBytes());
                out.closeEntry();
            }

            // when we create a ConnectorImplementationJar for the jar (test of deprecated method for compatibility)
            @SuppressWarnings("deprecation")
            var impl = ConnectorImplementationJar.of("implId", "0.1.0", jarFilePath.toFile(), "myLib.jar");

            // then artifact info match the pom.xml content
            assertThat(impl.getArtifactInformation()).is(new Condition<>(info -> {
                return info.groupId().equals("org.bonitasoft") && info.artifactId().equals("myArtifactId")
                        && info.version().equals("0.50.0") && StringUtils.isBlank(info.classifier());
            },
                    "ArtifactInfo should match org.bonitasoft:myArtifactId:0.50.0"));
        } finally {
            Files.delete(jarFilePath);
        }
    }

    @Test
    void should_find_ArtifactInfo_from_Path_withoutClassifier() throws Exception {
        // given a jar at correct location
        Path tempRepo = Files.createTempDirectory("localRepository");
        try {
            var jarFilePath = tempRepo.resolve("org/bonitasoft/myArtifactId/0.50.0/myArtifactId-0.50.0.jar");
            jarFilePath.toFile().getParentFile().mkdirs();
            jarFilePath.toFile().createNewFile();
            try (var out = new JarOutputStream(new FileOutputStream(jarFilePath.toFile()))) {
                var pomEntry = new JarEntry("info.txt");
                out.putNextEntry(pomEntry);
                out.write("Hello".getBytes());
                out.closeEntry();
            }

            // when we create a ConnectorImplementationJar for the jar (test of deprecated method for compatibility)
            @SuppressWarnings("deprecation")
            var impl = ConnectorImplementationJar.of("implId", "0.1.0", jarFilePath.toFile(), "myLib.jar");

            // then artifact info match the path
            assertThat(impl.getArtifactInformation()).is(new Condition<>(info -> {
                return info.groupId().equals("org.bonitasoft") && info.artifactId().equals("myArtifactId")
                        && info.version().equals("0.50.0") && StringUtils.isBlank(info.classifier());
            },
                    "ArtifactInfo should match org.bonitasoft:myArtifactId:0.50.0"));
        } finally {
            FileUtils.deleteDirectory(tempRepo.toFile());
        }
    }

    @Test
    void should_find_ArtifactInfo_from_Path_withClassifier() throws Exception {
        // given a jar at correct location
        Path tempRepo = Files.createTempDirectory("localRepository");
        try {
            var jarFilePath = tempRepo.resolve("org/bonitasoft/myArtifactId/0.50.0/myArtifactId-0.50.0-linux.jar");
            jarFilePath.toFile().getParentFile().mkdirs();
            jarFilePath.toFile().createNewFile();
            try (var out = new JarOutputStream(new FileOutputStream(jarFilePath.toFile()))) {
                var pomEntry = new JarEntry("info.txt");
                out.putNextEntry(pomEntry);
                out.write("Hello".getBytes());
                out.closeEntry();
            }

            // when we create a ConnectorImplementationJar for the jar (test of deprecated method for compatibility)
            @SuppressWarnings("deprecation")
            var impl = ConnectorImplementationJar.of("implId", "0.1.0", jarFilePath.toFile(), "myLib.jar");

            // then artifact info match the path
            assertThat(impl.getArtifactInformation()).is(new Condition<>(info -> {
                return info.groupId().equals("org.bonitasoft") && info.artifactId().equals("myArtifactId")
                        && info.version().equals("0.50.0") && info.classifier().equals("linux");
            },
                    "ArtifactInfo should match org.bonitasoft:myArtifactId:0.50.0"));
        } finally {
            FileUtils.deleteDirectory(tempRepo.toFile());
        }
    }

}
