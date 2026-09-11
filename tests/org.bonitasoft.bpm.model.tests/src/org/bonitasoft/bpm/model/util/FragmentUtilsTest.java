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
package org.bonitasoft.bpm.model.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.ConfigurationFactory;
import org.bonitasoft.bpm.model.configuration.Fragment;
import org.bonitasoft.bpm.model.configuration.FragmentContainer;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link FragmentUtils#extractArtifactBase(String)}.
 */
class FragmentUtilsTest {

    @Test
    void should_extract_base_from_standard_jar() {
        assertThat(FragmentUtils.extractArtifactBase("bcpkix-jdk18on-1.78.1.jar"))
                .isEqualTo("bcpkix-jdk18on");
    }

    @Test
    void should_extract_base_when_artifactId_contains_digit() {
        assertThat(FragmentUtils.extractArtifactBase("commons-collections4-4.4.jar"))
                .isEqualTo("commons-collections4");
    }

    @Test
    void should_extract_base_from_simple_jar() {
        assertThat(FragmentUtils.extractArtifactBase("FastInfoset-1.2.15.jar"))
                .isEqualTo("FastInfoset");
    }

    @Test
    void should_extract_base_from_long_artifactId() {
        assertThat(FragmentUtils.extractArtifactBase("bonita-connector-ai-mistral-1.1.0.jar"))
                .isEqualTo("bonita-connector-ai-mistral");
    }

    @Test
    void should_extract_base_with_log4j_style() {
        assertThat(FragmentUtils.extractArtifactBase("log4j-api-2.25.3.jar"))
                .isEqualTo("log4j-api");
    }

    @Test
    void should_handle_snapshot_qualifier() {
        assertThat(FragmentUtils.extractArtifactBase("my-lib-1.0.0-SNAPSHOT.jar"))
                .isEqualTo("my-lib");
    }

    @Test
    void should_fallback_for_non_standard_filename() {
        assertThat(FragmentUtils.extractArtifactBase("weirdname.jar"))
                .isEqualTo("weirdname");
    }

    // ---------------------------------------------------------------------------------------------
    // extractArtifactVersion
    // ---------------------------------------------------------------------------------------------

    @Test
    void should_extract_version_from_standard_jar() {
        assertThat(FragmentUtils.extractArtifactVersion("commons-text-1.9.jar")).contains("1.9");
        assertThat(FragmentUtils.extractArtifactVersion("bcpkix-jdk18on-1.78.1.jar")).contains("1.78.1");
        assertThat(FragmentUtils.extractArtifactVersion("commons-collections4-4.4.jar")).contains("4.4");
    }

    @Test
    void should_keep_qualifier_in_version() {
        assertThat(FragmentUtils.extractArtifactVersion("guava-31.1-jre.jar")).contains("31.1-jre");
        assertThat(FragmentUtils.extractArtifactVersion("netty-buffer-4.1.100.Final.jar"))
                .contains("4.1.100.Final");
    }

    @Test
    void should_keep_snapshot_suffix_in_version() {
        assertThat(FragmentUtils.extractArtifactVersion("bonita-common-6.2.0-SNAPSHOT.jar"))
                .contains("6.2.0-SNAPSHOT");
    }

    @Test
    void should_not_extract_version_when_file_name_holds_none() {
        assertThat(FragmentUtils.extractArtifactVersion("catalina.jar")).isEmpty();
    }

    @Test
    void should_not_extract_version_from_null_or_non_jar() {
        assertThat(FragmentUtils.extractArtifactVersion(null)).isEmpty();
        assertThat(FragmentUtils.extractArtifactVersion("commons-text-1.9")).isEmpty();
    }

    @Test
    void should_be_consistent_with_extractArtifactBase() {
        var fileName = "bcpkix-jdk18on-1.78.1.jar";
        assertThat(FragmentUtils.extractArtifactBase(fileName) + "-"
                + FragmentUtils.extractArtifactVersion(fileName).orElseThrow() + ".jar").isEqualTo(fileName);
    }

    // ---------------------------------------------------------------------------------------------
    // selectedVersionsByArtifactBase / conflictingArtifactBases
    // ---------------------------------------------------------------------------------------------

    @Test
    void should_index_the_selected_version_by_artifact_base() {
        var configuration = aConfigurationWith(exported("commons-text-1.9.jar"));

        assertThat(FragmentUtils.selectedVersionsByArtifactBase(configuration))
                .containsOnlyKeys("commons-text")
                .containsEntry("commons-text", Set.of("1.9"));
    }

    @Test
    void should_index_both_versions_when_two_are_selected() {
        var configuration = aConfigurationWith(exported("commons-text-1.9.jar"),
                exported("commons-text-1.12.0.jar"));

        assertThat(FragmentUtils.selectedVersionsByArtifactBase(configuration).get("commons-text"))
                .containsExactly("1.9", "1.12.0");
    }

    @Test
    void should_ignore_unselected_fragments() {
        var configuration = aConfigurationWith(notExported("commons-text-1.9.jar"));

        assertThat(FragmentUtils.selectedVersionsByArtifactBase(configuration)).isEmpty();
    }

    @Test
    void should_ignore_fragments_whose_version_cannot_be_read() {
        var configuration = aConfigurationWith(exported("catalina.jar"));

        assertThat(FragmentUtils.selectedVersionsByArtifactBase(configuration)).isEmpty();
    }

    @Test
    void should_ignore_fragments_without_value() {
        var configuration = aConfigurationWith(exported(null));

        assertThat(FragmentUtils.selectedVersionsByArtifactBase(configuration)).isEmpty();
    }

    @Test
    void should_walk_fragments_of_children_containers() {
        var configuration = aConfigurationWith(exported("commons-text-1.9.jar"));
        var child = ConfigurationFactory.eINSTANCE.createFragmentContainer();
        child.setId("CONNECTOR");
        child.getFragments().add(exported("commons-text-1.12.0.jar"));
        configuration.getProcessDependencies().get(0).getChildren().add(child);

        assertThat(FragmentUtils.selectedVersionsByArtifactBase(configuration).get("commons-text"))
                .containsExactly("1.9", "1.12.0");
    }

    @Test
    void should_ignore_a_jar_unselected_by_the_user_though_exported_in_a_child() {
        // the flag the user set in OTHER wins over the one the connector child carries, which is the
        // precedence the copied jars are filtered with
        var configuration = aConfigurationWithChild(notExported("pdfbox-2.0.24.jar"),
                exported("pdfbox-2.0.24.jar"));

        assertThat(FragmentUtils.selectedVersionsByArtifactBase(configuration)).isEmpty();
    }

    @Test
    void should_keep_the_selected_version_when_another_version_is_unselected_in_a_child() {
        // different file names: unselecting one version says nothing about the other one
        var configuration = aConfigurationWithChild(notExported("asm-9.8.jar"), exported("asm-3.3.1.jar"));

        assertThat(FragmentUtils.selectedVersionsByArtifactBase(configuration))
                .containsEntry("asm", Set.of("3.3.1"));
    }

    @Test
    void should_list_the_selected_jar_names_without_the_unselected_ones() {
        assertThat(FragmentUtils.selectedJarNames(List.of(exported("commons-text-1.9.jar"),
                exported("asm-3.3.1.jar"), notExported("asm-3.3.1.jar"), notExported("guava-31.1-jre.jar"))))
                .containsExactly("commons-text-1.9.jar");
    }

    @Test
    void should_return_an_empty_result_for_a_null_configuration() {
        assertThat(FragmentUtils.selectedVersionsByArtifactBase(null)).isEmpty();
        assertThat(FragmentUtils.conflictingArtifactBases(null)).isEmpty();
    }

    @Test
    void should_report_the_library_selected_in_several_versions_as_conflicting() {
        var configuration = aConfigurationWith(exported("commons-text-1.9.jar"),
                exported("commons-text-1.12.0.jar"),
                exported("commons-lang3-3.14.0.jar"));

        assertThat(FragmentUtils.conflictingArtifactBases(configuration)).containsExactly("commons-text");
    }

    @Test
    void should_not_report_a_conflict_when_a_single_version_is_selected() {
        var configuration = aConfigurationWith(exported("commons-text-1.9.jar"),
                notExported("commons-text-1.12.0.jar"));

        assertThat(FragmentUtils.conflictingArtifactBases(configuration)).isEmpty();
    }

    @Test
    void should_not_report_a_conflict_for_the_same_version_selected_twice() {
        // the same jar may appear in two containers, typically after fragments have been flattened
        var configuration = aConfigurationWith(exported("commons-text-1.9.jar"),
                exported("commons-text-1.9.jar"));

        assertThat(FragmentUtils.conflictingArtifactBases(configuration)).isEmpty();
    }

    /**
     * A configuration shaped like the one the export works on: the jar sits in the {@code OTHER}
     * container with the flag the user set, and in a connector child container with the automatic one.
     */
    private static Configuration aConfigurationWithChild(Fragment otherFragment, Fragment childFragment) {
        var configuration = aConfigurationWith(otherFragment);
        var child = ConfigurationFactory.eINSTANCE.createFragmentContainer();
        child.setId("CONNECTOR");
        child.getFragments().add(childFragment);
        configuration.getProcessDependencies().get(0).getChildren().add(child);
        return configuration;
    }

    private static Fragment exported(String value) {
        return aFragment(value, true);
    }

    private static Fragment notExported(String value) {
        return aFragment(value, false);
    }

    private static Fragment aFragment(String value, boolean exported) {
        var fragment = ConfigurationFactory.eINSTANCE.createFragment();
        fragment.setValue(value);
        fragment.setType("JAR");
        fragment.setExported(exported);
        return fragment;
    }

    private static Configuration aConfigurationWith(Fragment... fragments) {
        FragmentContainer container = ConfigurationFactory.eINSTANCE.createFragmentContainer();
        container.setId("OTHER");
        for (var fragment : fragments) {
            container.getFragments().add(fragment);
        }
        var configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        configuration.getProcessDependencies().add(container);
        return configuration;
    }

}
