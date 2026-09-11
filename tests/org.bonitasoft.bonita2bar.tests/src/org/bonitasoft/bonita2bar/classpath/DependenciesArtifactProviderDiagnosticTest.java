/**
 * Copyright (C) 2026 Bonitasoft S.A.
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
import static org.bonitasoft.bpm.model.process.builders.PoolBuilder.aPool;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.apache.maven.model.Model;
import org.bonitasoft.bonita2bar.BuildDiagnostic;
import org.bonitasoft.bonita2bar.MavenExecutor;
import org.bonitasoft.bonita2bar.process.pomgen.ProcessPom;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.builders.ConfigurationBuilder;
import org.bonitasoft.bpm.model.configuration.builders.FragmentBuilder;
import org.bonitasoft.bpm.model.configuration.builders.FragmentContainerBuilder;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Checks what a version mismatch found while filtering the copied dependencies looks like once it reaches the caller.
 * <p>
 * {@link DependenciesArtifactProviderFilterTest} asserts which jars survive the filtering; this one asserts that a
 * mismatch is surfaced as a {@link BuildDiagnostic} naming both versions, which is the only form callers consume.
 */
class DependenciesArtifactProviderDiagnosticTest {

    @TempDir
    Path processPomFolder;

    @Test
    void should_report_a_version_mismatch_as_a_diagnostic_naming_both_versions() throws Exception {
        // the configuration asks for pdfbox 3.0.3, Maven resolved 2.0.1
        givenCopiedJars("pdfbox-2.0.1.jar");

        var diagnostics = buildWith(configurationSelecting("pdfbox-3.0.3.jar"));

        assertThat(diagnostics).singleElement().satisfies(diagnostic -> {
            assertThat(diagnostic.severity()).isEqualTo(BuildDiagnostic.Severity.WARNING);
            assertThat(diagnostic.message())
                    .contains("MyProcess-1.0")
                    .contains("pdfbox-3.0.3.jar")
                    .contains("pdfbox-2.0.1.jar");
        });
    }

    @Test
    void should_not_report_any_diagnostic_when_maven_resolved_the_selected_version() throws Exception {
        givenCopiedJars("pdfbox-3.0.3.jar");

        assertThat(buildWith(configurationSelecting("pdfbox-3.0.3.jar"))).isEmpty();
    }

    private void givenCopiedJars(String... jarNames) throws Exception {
        var dependenciesFolder = Files.createDirectories(processPomFolder.resolve("dependencies"));
        for (var jarName : jarNames) {
            // a kept jar is added to the archive, and BusinessArchiveBuilder rejects empty content
            Files.writeString(dependenciesFolder.resolve(jarName), jarName);
        }
    }

    private Configuration configurationSelecting(String jarName) {
        return ConfigurationBuilder.aConfiguration()
                .havingProcessDependencies(
                        FragmentContainerBuilder.aFragmentContainer("OTHER")
                                .havingFragments(
                                        FragmentBuilder.aFragment()
                                                .withValue(jarName)
                                                .withType("JAR")
                                                .exported()))
                .build();
    }

    private List<BuildDiagnostic> buildWith(Configuration configuration) throws Exception {
        // the executor is a no-op: the jars dependency:copy-dependencies would have copied are already in place
        var provider = new DependenciesArtifactProvider(mock(MavenExecutor.class));
        return provider.build(new BusinessArchiveBuilder().createNewBusinessArchive(),
                aPool().withName("MyProcess").withVersion("1.0").build(),
                generatedProcessPom(),
                configuration);
    }

    private ProcessPom generatedProcessPom() throws Exception {
        var model = new Model();
        model.setPomFile(processPomFolder.resolve("pom.xml").toFile());
        var processPom = mock(ProcessPom.class);
        when(processPom.readPom()).thenReturn(model);
        return processPom;
    }

}
