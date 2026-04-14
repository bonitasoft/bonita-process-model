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
package org.bonitasoft.bpm.model.util;

import static org.assertj.core.api.Assertions.assertThat;

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

}
