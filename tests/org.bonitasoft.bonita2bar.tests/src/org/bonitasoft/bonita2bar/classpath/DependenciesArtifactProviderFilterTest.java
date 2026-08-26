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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.builders.ConfigurationBuilder;
import org.bonitasoft.bpm.model.configuration.builders.FragmentBuilder;
import org.bonitasoft.bpm.model.configuration.builders.FragmentContainerBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Unit tests for {@link DependenciesArtifactProvider#filterCopiedDependencies(File, Configuration)}.
 */
class DependenciesArtifactProviderFilterTest {

    @TempDir
    Path tempDir;

    private DependenciesArtifactProvider provider;
    private File dependenciesFolder;

    @BeforeEach
    void setUp() {
        provider = new DependenciesArtifactProvider(null);
        dependenciesFolder = tempDir.resolve("dependencies").toFile();
        dependenciesFolder.mkdirs();
    }

    // ── Whitelist filtering tests ──────────────────────────────────────────

    @Test
    void should_remove_unchecked_transitive_when_parent_is_checked() throws IOException {
        createJarFile("parent-1.0.jar");
        createJarFile("transitive-1.0.jar");

        Configuration configuration = ConfigurationBuilder.aConfiguration()
                .havingProcessDependencies(
                        FragmentContainerBuilder.aFragmentContainer("OTHER")
                                .havingFragments(
                                        FragmentBuilder.aFragment()
                                                .withValue("parent-1.0.jar")
                                                .withType("JAR")
                                                .exported(),
                                        FragmentBuilder.aFragment()
                                                .withValue("transitive-1.0.jar")
                                                .withType("JAR")
                                                .notExported()))
                .build();

        provider.filterCopiedDependencies(dependenciesFolder, configuration);

        assertThat(dependenciesFolder.listFiles())
                .extracting(File::getName)
                .containsExactlyInAnyOrder("parent-1.0.jar");
    }

    @Test
    void should_remove_unchecked_parent_but_keep_checked_transitives() throws IOException {
        createJarFile("parent-1.0.jar");
        createJarFile("transitive-a-1.0.jar");
        createJarFile("transitive-b-1.0.jar");

        Configuration configuration = ConfigurationBuilder.aConfiguration()
                .havingProcessDependencies(
                        FragmentContainerBuilder.aFragmentContainer("OTHER")
                                .havingFragments(
                                        FragmentBuilder.aFragment()
                                                .withValue("parent-1.0.jar")
                                                .withType("JAR")
                                                .notExported(),
                                        FragmentBuilder.aFragment()
                                                .withValue("transitive-a-1.0.jar")
                                                .withType("JAR")
                                                .exported(),
                                        FragmentBuilder.aFragment()
                                                .withValue("transitive-b-1.0.jar")
                                                .withType("JAR")
                                                .exported()))
                .build();

        provider.filterCopiedDependencies(dependenciesFolder, configuration);

        assertThat(dependenciesFolder.listFiles())
                .extracting(File::getName)
                .containsExactlyInAnyOrder("transitive-a-1.0.jar", "transitive-b-1.0.jar");
    }

    @Test
    void should_remove_unknown_jars_not_tracked_by_any_fragment() throws IOException {
        // Whitelist approach: unknown jars (not in any fragment) are removed
        createJarFile("parent-1.0.jar");
        createJarFile("unknown-lib-2.0.jar");

        Configuration configuration = ConfigurationBuilder.aConfiguration()
                .havingProcessDependencies(
                        FragmentContainerBuilder.aFragmentContainer("OTHER")
                                .havingFragments(
                                        FragmentBuilder.aFragment()
                                                .withValue("parent-1.0.jar")
                                                .withType("JAR")
                                                .exported()))
                .build();

        provider.filterCopiedDependencies(dependenciesFolder, configuration);

        // Only exported jars remain, unknown jar is removed
        assertThat(dependenciesFolder.listFiles())
                .extracting(File::getName)
                .containsExactlyInAnyOrder("parent-1.0.jar");
    }

    @Test
    void should_remove_all_jars_when_containers_exist_but_no_fragments_are_declared() throws IOException {
        // BPA-449: a modern configuration of a pool with no declared dependency
        // has its containers created (e.g. OTHER) but no Fragment inside.
        // The whitelist is empty, so every copied jar must be excluded from the BAR.
        createJarFile("lib1-1.0.jar");
        createJarFile("lib2-2.0.jar");

        Configuration configuration = ConfigurationBuilder.aConfiguration()
                .havingProcessDependencies(
                        FragmentContainerBuilder.aFragmentContainer("OTHER"))
                .build();

        provider.filterCopiedDependencies(dependenciesFolder, configuration);

        assertThat(dependenciesFolder).isEmptyDirectory();
    }

    @Test
    void should_remove_all_known_jars_when_none_are_exported() throws IOException {
        createJarFile("lib1-1.0.jar");
        createJarFile("lib2-2.0.jar");

        Configuration configuration = ConfigurationBuilder.aConfiguration()
                .havingProcessDependencies(
                        FragmentContainerBuilder.aFragmentContainer("OTHER")
                                .havingFragments(
                                        FragmentBuilder.aFragment()
                                                .withValue("lib1-1.0.jar")
                                                .withType("JAR")
                                                .notExported(),
                                        FragmentBuilder.aFragment()
                                                .withValue("lib2-2.0.jar")
                                                .withType("JAR")
                                                .notExported()))
                .build();

        provider.filterCopiedDependencies(dependenciesFolder, configuration);

        assertThat(dependenciesFolder).isEmptyDirectory();
    }

    @Test
    void should_not_filter_when_configuration_is_null() throws IOException {
        createJarFile("lib1-1.0.jar");

        provider.filterCopiedDependencies(dependenciesFolder, null);

        assertThat(dependenciesFolder.listFiles())
                .extracting(File::getName)
                .containsExactlyInAnyOrder("lib1-1.0.jar");
    }

    // ── Version mismatch tests ─────────────────────────────────────────────

    @Test
    void should_remove_unchecked_jar_even_when_maven_resolves_different_version() throws IOException {
        // Fragment says bcpkix-jdk18on-1.78.1 (exported=false)
        // but Maven resolved bcpkix-jdk18on-1.83
        createJarFile("bcpkix-jdk18on-1.83.jar");
        createJarFile("exported-lib-1.0.jar");

        Configuration configuration = ConfigurationBuilder.aConfiguration()
                .havingProcessDependencies(
                        FragmentContainerBuilder.aFragmentContainer("OTHER")
                                .havingFragments(
                                        FragmentBuilder.aFragment()
                                                .withValue("bcpkix-jdk18on-1.78.1.jar")
                                                .withType("JAR")
                                                .notExported(),
                                        FragmentBuilder.aFragment()
                                                .withValue("exported-lib-1.0.jar")
                                                .withType("JAR")
                                                .exported()))
                .build();

        provider.filterCopiedDependencies(dependenciesFolder, configuration);

        // bcpkix removed despite version mismatch, exported-lib kept
        assertThat(dependenciesFolder.listFiles())
                .extracting(File::getName)
                .containsExactlyInAnyOrder("exported-lib-1.0.jar");
    }

    @Test
    void should_exclude_jar_when_exported_fragment_has_different_version() throws IOException {
        // Fragment says commons-collections4-4.4 (exported=true)
        // but Maven resolved commons-collections4-4.5.0 → version mismatch → excluded
        createJarFile("commons-collections4-4.5.0.jar");

        Configuration configuration = ConfigurationBuilder.aConfiguration()
                .havingProcessDependencies(
                        FragmentContainerBuilder.aFragmentContainer("OTHER")
                                .havingFragments(
                                        FragmentBuilder.aFragment()
                                                .withValue("commons-collections4-4.4.jar")
                                                .withType("JAR")
                                                .exported()))
                .build();

        var mismatches = provider.filterCopiedDependencies(dependenciesFolder, configuration);

        // Excluded: base name matches but exact version differs
        assertThat(dependenciesFolder).isEmptyDirectory();
        // ...and the exclusion is reported, not only logged
        assertThat(mismatches).containsExactly(
                new DependenciesArtifactProvider.VersionMismatch("commons-collections4-4.4.jar",
                        "commons-collections4-4.5.0.jar"));
    }

    @Test
    void should_keep_jar_when_exact_version_matches() throws IOException {
        // Fragment says guava-33.0.jar (exported=true), Maven copies guava-33.0.jar → exact match → kept
        createJarFile("guava-33.0.jar");

        Configuration configuration = ConfigurationBuilder.aConfiguration()
                .havingProcessDependencies(
                        FragmentContainerBuilder.aFragmentContainer("OTHER")
                                .havingFragments(
                                        FragmentBuilder.aFragment()
                                                .withValue("guava-33.0.jar")
                                                .withType("JAR")
                                                .exported()))
                .build();

        provider.filterCopiedDependencies(dependenciesFolder, configuration);

        assertThat(dependenciesFolder.listFiles())
                .extracting(File::getName)
                .containsExactlyInAnyOrder("guava-33.0.jar");
    }

    @Test
    void should_exclude_version_mismatch_in_real_world_pdfbox_scenario() throws IOException {
        // Mistral connector scenario: fragment expects pdfbox 3.x but Maven resolves pdfbox 2.x
        createJarFile("pdfbox-2.0.1.jar");
        createJarFile("bonita-connector-ai-mistral-1.1.0.jar");

        Configuration configuration = ConfigurationBuilder.aConfiguration()
                .havingProcessDependencies(
                        FragmentContainerBuilder.aFragmentContainer("OTHER")
                                .havingFragments(
                                        FragmentBuilder.aFragment()
                                                .withValue("pdfbox-3.0.3.jar")
                                                .withType("JAR")
                                                .exported(),
                                        FragmentBuilder.aFragment()
                                                .withValue("bonita-connector-ai-mistral-1.1.0.jar")
                                                .withType("JAR")
                                                .exported()))
                .build();

        var mismatches = provider.filterCopiedDependencies(dependenciesFolder, configuration);

        // pdfbox excluded (version mismatch: 3.0.3 expected, 2.0.1 resolved)
        // mistral connector kept (exact match)
        assertThat(dependenciesFolder.listFiles())
                .extracting(File::getName)
                .containsExactlyInAnyOrder("bonita-connector-ai-mistral-1.1.0.jar");
        // only the mismatching jar is reported
        assertThat(mismatches).containsExactly(
                new DependenciesArtifactProvider.VersionMismatch("pdfbox-3.0.3.jar", "pdfbox-2.0.1.jar"));
    }

    @Test
    void should_not_report_any_mismatch_when_versions_match() throws IOException {
        createJarFile("guava-33.0.jar");

        Configuration configuration = ConfigurationBuilder.aConfiguration()
                .havingProcessDependencies(
                        FragmentContainerBuilder.aFragmentContainer("OTHER")
                                .havingFragments(
                                        FragmentBuilder.aFragment()
                                                .withValue("guava-33.0.jar")
                                                .withType("JAR")
                                                .exported()))
                .build();

        var mismatches = provider.filterCopiedDependencies(dependenciesFolder, configuration);

        assertThat(mismatches).isEmpty();
    }

    @Test
    void should_not_report_a_mismatch_when_the_user_excluded_the_library() throws IOException {
        // an unselected library is not a mismatch, the user asked for its removal
        createJarFile("commons-collections4-4.5.0.jar");

        Configuration configuration = ConfigurationBuilder.aConfiguration()
                .havingProcessDependencies(
                        FragmentContainerBuilder.aFragmentContainer("OTHER")
                                .havingFragments(
                                        FragmentBuilder.aFragment()
                                                .withValue("commons-collections4-4.4.jar")
                                                .withType("JAR")
                                                .notExported()))
                .build();

        var mismatches = provider.filterCopiedDependencies(dependenciesFolder, configuration);

        assertThat(dependenciesFolder).isEmptyDirectory();
        assertThat(mismatches).isEmpty();
    }

    @Test
    void should_handle_real_world_connector_scenario() throws IOException {
        // Simulates: mistral connector with bcpkix exported=false,
        // but Maven resolves newer version from engine
        createJarFile("bonita-connector-ai-mistral-1.1.0.jar");
        createJarFile("bcpkix-jdk18on-1.83.jar");
        createJarFile("bcprov-jdk18on-1.83.jar");
        createJarFile("bcutil-jdk18on-1.83.jar");
        createJarFile("FastInfoset-1.2.15.jar");
        createJarFile("jackson-databind-2.20.0.jar");
        createJarFile("commons-io-2.20.0.jar");

        Configuration configuration = ConfigurationBuilder.aConfiguration()
                .havingProcessDependencies(
                        FragmentContainerBuilder.aFragmentContainer("OTHER")
                                .havingFragments(
                                        // Connector jar: exported
                                        FragmentBuilder.aFragment()
                                                .withValue("bonita-connector-ai-mistral-1.1.0.jar")
                                                .withType("CONNECTOR")
                                                .exported(),
                                        // FastInfoset: exported
                                        FragmentBuilder.aFragment()
                                                .withValue("FastInfoset-1.2.15.jar")
                                                .withType("CONNECTOR")
                                                .exported(),
                                        // bcpkix: NOT exported (user unchecked), version mismatch
                                        FragmentBuilder.aFragment()
                                                .withValue("bcpkix-jdk18on-1.78.1.jar")
                                                .withType("CONNECTOR")
                                                .notExported(),
                                        // bcprov: NOT exported, version mismatch
                                        FragmentBuilder.aFragment()
                                                .withValue("bcprov-jdk18on-1.78.1.jar")
                                                .withType("CONNECTOR")
                                                .notExported(),
                                        // bcutil: NOT exported, version mismatch
                                        FragmentBuilder.aFragment()
                                                .withValue("bcutil-jdk18on-1.78.1.jar")
                                                .withType("CONNECTOR")
                                                .notExported()))
                .build();

        provider.filterCopiedDependencies(dependenciesFolder, configuration);

        // Exported fragments kept, version-mismatched unchecked jars and unknown jars removed
        assertThat(dependenciesFolder.listFiles())
                .extracting(File::getName)
                .containsExactlyInAnyOrder(
                        "bonita-connector-ai-mistral-1.1.0.jar",
                        "FastInfoset-1.2.15.jar");
    }

    // ── Conflicting exported flags across containers (post-flatten scenario) ──

    @Test
    void should_exclude_jar_unchecked_in_OTHER_even_if_exported_in_connector_child() throws IOException {
        // Reproduces the bug: after flattenFragmentsToOther, pdfbox exists in both:
        // - OTHER container with exported=false (user explicitly unchecked)
        // - CONNECTOR child with exported=true (auto-generated default)
        createJarFile("pdfbox-2.0.24.jar");
        createJarFile("fontbox-2.0.24.jar");
        createJarFile("openhtmltopdf-pdfbox-1.0.10.jar");

        Configuration configuration = ConfigurationBuilder.aConfiguration()
                .havingProcessDependencies(
                        FragmentContainerBuilder.aFragmentContainer("CONNECTOR")
                                .havingChildren(
                                        FragmentContainerBuilder.aFragmentContainer("openhtmltopdf-pdfbox-1.0.10.jar")
                                                .havingFragments(
                                                        FragmentBuilder.aFragment()
                                                                .withValue("pdfbox-2.0.24.jar")
                                                                .withType("JAR")
                                                                .exported(),
                                                        FragmentBuilder.aFragment()
                                                                .withValue("fontbox-2.0.24.jar")
                                                                .withType("JAR")
                                                                .exported())),
                        FragmentContainerBuilder.aFragmentContainer("OTHER")
                                .havingFragments(
                                        FragmentBuilder.aFragment()
                                                .withValue("openhtmltopdf-pdfbox-1.0.10.jar")
                                                .withType("JAR")
                                                .exported(),
                                        FragmentBuilder.aFragment()
                                                .withValue("pdfbox-2.0.24.jar")
                                                .withType("JAR")
                                                .notExported(),
                                        FragmentBuilder.aFragment()
                                                .withValue("fontbox-2.0.24.jar")
                                                .withType("JAR")
                                                .exported()))
                .build();

        provider.filterCopiedDependencies(dependenciesFolder, configuration);

        // pdfbox excluded (user's exported=false in OTHER wins over exported=true in CONNECTOR child)
        // fontbox and openhtmltopdf-pdfbox kept (exported=true in OTHER)
        assertThat(dependenciesFolder.listFiles())
                .extracting(File::getName)
                .containsExactlyInAnyOrder("fontbox-2.0.24.jar", "openhtmltopdf-pdfbox-1.0.10.jar");
    }

    @Test
    void should_keep_exported_version_when_another_version_of_same_artifact_is_excluded() throws IOException {
        // Two versions of asm: 3.3.1 (exported, from CMIS) and 9.8 (not exported, from Mistral)
        // The exported version must be kept even though they share the same base-name "asm"
        createJarFile("asm-3.3.1.jar");
        createJarFile("asm-9.8.jar");

        Configuration configuration = ConfigurationBuilder.aConfiguration()
                .havingProcessDependencies(
                        FragmentContainerBuilder.aFragmentContainer("OTHER")
                                .havingFragments(
                                        FragmentBuilder.aFragment()
                                                .withValue("asm-3.3.1.jar")
                                                .withType("JAR")
                                                .exported(),
                                        FragmentBuilder.aFragment()
                                                .withValue("asm-9.8.jar")
                                                .withType("JAR")
                                                .notExported()))
                .build();

        provider.filterCopiedDependencies(dependenciesFolder, configuration);

        // asm-3.3.1.jar kept (exact match exported), asm-9.8.jar removed (not exported)
        assertThat(dependenciesFolder.listFiles())
                .extracting(File::getName)
                .containsExactlyInAnyOrder("asm-3.3.1.jar");
    }

    private void createJarFile(String name) throws IOException {
        Files.write(dependenciesFolder.toPath().resolve(name), new byte[] { 0 });
    }

}
