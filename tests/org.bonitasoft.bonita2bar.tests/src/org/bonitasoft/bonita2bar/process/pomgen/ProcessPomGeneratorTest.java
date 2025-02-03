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
package org.bonitasoft.bonita2bar.process.pomgen;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Optional;
import java.util.function.Predicate;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.bonita2bar.ProcessRegistry;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.util.migration.MigrationPolicy;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.eclipse.core.runtime.FileLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test {@link ProcessPomGenerator}
 */
public class ProcessPomGeneratorTest {

    /** Test the email connector dependency */
    private static final Predicate<Dependency> IS_EMAIL_CONNECTOR = dep -> {
        return "org.bonitasoft.connectors:bonita-connector-email:jar".equals(dep.getManagementKey())
                && "1.3.0".equals(dep.getVersion());
    };
    private MavenProject appProject;
    private ProcessRegistry processRegistry;

    @BeforeEach
    void setUp() throws Exception {
        var repoRoot = new File(URLDecoder.decode(
                FileLocator.toFileURL(ProcessPomGeneratorTest.class.getResource("/test-repository")).getFile(),
                "UTF-8"));

        processRegistry = ProcessRegistry.of(repoRoot.toPath().resolve("app").resolve("diagrams"),
                MigrationPolicy.NEVER_MIGRATE_POLICY);
        var appPomFile = repoRoot.toPath().resolve("app").resolve("pom.xml").toFile();
        // load maven project

        MavenXpp3Reader reader = new MavenXpp3Reader();
        try (var fileReader = new FileReader(appPomFile)) {
            var model = reader.read(fileReader);
            appProject = new MavenProject(model);
            appProject.setFile(appPomFile);
        }

    }

    @Test
    void should_generate_pom_without_connector_dep() throws IOException, XmlPullParserException {
        Optional<Pool> process = processRegistry.getProcess("SimpleProcessWithParameters", "1.0");
        var gen = ProcessPomGenerator.create(appProject, process.get());
        Model processPom = gen.generatePom().readPom();
        assertThat(processPom.getDependencies()).noneMatch(IS_EMAIL_CONNECTOR);
    }

    @Test
    void should_generate_pom_with_connector_dep() throws IOException, XmlPullParserException {
        Optional<Pool> process = processRegistry.getProcess("ProcessWithConnectors", "1.0");
        var gen = ProcessPomGenerator.create(appProject, process.get());
        Model processPom = gen.generatePom().readPom();
        assertThat(processPom.getDependencies()).anyMatch(IS_EMAIL_CONNECTOR);
    }

}
