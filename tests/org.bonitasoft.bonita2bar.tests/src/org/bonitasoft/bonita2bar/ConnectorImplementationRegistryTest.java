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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry.ArtifactInfo;
import org.bonitasoft.bonita2bar.ConnectorImplementationRegistry.ConnectorImplementationJar;
import org.bonitasoft.bonita2bar.process.pomgen.ProcessPomGeneratorTest;
import org.bonitasoft.bpm.model.FileUtil;
import org.bonitasoft.bpm.model.MavenUtil;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for ConnectorImplementationRegistry
 */
public class ConnectorImplementationRegistryTest {

    private Path projectRoot;

    @BeforeEach
    void setUp() throws Exception {
        projectRoot = Files.createTempDirectory("test-repository");
        var testRepoRoot = new File(URLDecoder.decode(
                FileLocator.toFileURL(ProcessPomGeneratorTest.class.getResource("/test-repository")).getFile(),
                "UTF-8"));
        FileUtil.copyDirectory(testRepoRoot.getAbsolutePath(), projectRoot.toFile().getAbsolutePath());
    }

    @Test
    void should_ConnectorImplementationJar_legacyEqualModernContructor() throws Exception {
        var mvnExecutable = Platform.getOS().contains("win") ? "mvn.cmd" : "mvn";
        var jsonReportFile = MavenUtil.analyze(projectRoot, mvnExecutable);

        // jsonReportFile should contain e.g. the email connector
        var report = MavenUtil.loadReport(jsonReportFile);
        var implementations = (List<Map<String, Object>>) report.get("connectorImplementations");
        Map<String, Object> emailImpl = implementations.stream()
                .filter(impl -> impl.get("definitionId").equals("email"))
                .findFirst().get();
        var artifact = ((Map<String, String>) emailImpl.get("artifact"));
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

}
