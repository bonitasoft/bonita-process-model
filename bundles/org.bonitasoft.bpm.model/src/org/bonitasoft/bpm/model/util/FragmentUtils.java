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

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.bonitasoft.bpm.model.configuration.Fragment;
import org.bonitasoft.bpm.model.configuration.FragmentContainer;

/**
 * Utility methods for working with configuration fragments.
 */
public class FragmentUtils {

    private FragmentUtils() {
        // private constructor
    }

    /**
     * Pattern to extract the artifact base name (artifactId) from a jar filename.
     * Matches everything before the last hyphen-digit sequence that looks like a version.
     * Examples: {@code bcpkix-jdk18on-1.78.1.jar} → {@code bcpkix-jdk18on},
     * {@code commons-collections4-4.4.jar} → {@code commons-collections4}
     */
    public static final Pattern ARTIFACT_BASE_PATTERN = Pattern
            .compile("^(.+)-(\\d[\\d.]*)(?:[.-](?!\\d).*?)?\\.jar$");

    /**
     * Extract the artifact base name (artifactId) from a jar filename, stripping the version suffix.
     * <p>
     * Examples:
     * <ul>
     * <li>{@code bcpkix-jdk18on-1.78.1.jar} → {@code bcpkix-jdk18on}</li>
     * <li>{@code commons-collections4-4.4.jar} → {@code commons-collections4}</li>
     * <li>{@code FastInfoset-1.2.15.jar} → {@code FastInfoset}</li>
     * </ul>
     * If the filename does not match the expected pattern, it is returned as-is (without .jar extension).
     *
     * @param filename the jar filename
     * @return the artifact base name
     */
    public static String extractArtifactBase(String filename) {
        Matcher m = ARTIFACT_BASE_PATTERN.matcher(filename);
        if (m.matches()) {
            return m.group(1);
        }
        // Fallback: strip .jar extension
        return filename.endsWith(".jar") ? filename.substring(0, filename.length() - 4) : filename;
    }

    /**
     * Extract the artifact version from a jar filename, keeping any qualifier.
     * <p>
     * Examples:
     * <ul>
     * <li>{@code commons-text-1.9.jar} &rarr; {@code 1.9}</li>
     * <li>{@code guava-31.1-jre.jar} &rarr; {@code 31.1-jre}</li>
     * <li>{@code bonita-common-6.2.0-SNAPSHOT.jar} &rarr; {@code 6.2.0-SNAPSHOT}</li>
     * </ul>
     * Everything between the artifact base name and the {@code .jar} extension is returned, so that
     * classifiers and qualifiers are preserved. An empty optional is returned when the filename does not
     * hold a readable version, for instance {@code catalina.jar}.
     *
     * @param filename the jar filename
     * @return the artifact version, or an empty optional when it cannot be read
     */
    public static Optional<String> extractArtifactVersion(String filename) {
        if (filename == null) {
            return Optional.empty();
        }
        Matcher m = ARTIFACT_BASE_PATTERN.matcher(filename);
        if (!m.matches()) {
            return Optional.empty();
        }
        int start = m.group(1).length() + 1;
        int end = filename.length() - 4;
        return start < end ? Optional.of(filename.substring(start, end)) : Optional.empty();
    }

    /**
     * Recursively walk all fragments in a container and its children.
     *
     * @param container the fragment container to walk
     * @return a stream of all fragments
     */
    public static Stream<Fragment> walkAllFragments(FragmentContainer container) {
        return Stream.concat(
                container.getFragments().stream(),
                container.getChildren().stream().flatMap(FragmentUtils::walkAllFragments));
    }

}
