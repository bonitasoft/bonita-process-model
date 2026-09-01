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

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.bpm.model.configuration.Configuration;
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
     * Index the versions selected by the user, by artifact base name.
     * <p>
     * Every selected (exported) fragment carries a jar file name, hence a version. A library may appear
     * several times with different versions, typically when two connectors bring it transitively, which
     * is why the value is a set.
     * </p>
     * <p>
     * Fragments whose version cannot be read from the file name are left out, since nothing can be
     * decided about them. Iteration order follows the configuration, so callers get stable messages.
     * </p>
     *
     * @param configuration the configuration holding the user selection, may be {@code null}
     * @return the selected versions indexed by artifact base name, never {@code null}
     */
    public static Map<String, Set<String>> selectedVersionsByArtifactBase(Configuration configuration) {
        Map<String, Set<String>> selectedVersions = new LinkedHashMap<>();
        if (configuration == null) {
            return selectedVersions;
        }
        configuration.getProcessDependencies().stream()
                .flatMap(FragmentUtils::walkAllFragments)
                .filter(Fragment::isExported)
                .map(Fragment::getValue)
                .filter(Objects::nonNull)
                .forEach(value -> extractArtifactVersion(value)
                        .ifPresent(version -> selectedVersions
                                .computeIfAbsent(extractArtifactBase(value), k -> new LinkedHashSet<>())
                                .add(version)));
        return selectedVersions;
    }

    /**
     * List the libraries for which the user selected more than one version.
     * <p>
     * Such a selection cannot be honoured: a {@code dependencyManagement} entry is indexed by
     * {@code groupId:artifactId}, so only one version can be pinned. Maven arbitration applies instead,
     * and the user has to unselect the unwanted version to decide. This is the predicate both the pom
     * generation and the configuration wizard rely on, so that the warning shown to the user matches
     * what actually happens.
     * </p>
     *
     * @param configuration the configuration holding the user selection, may be {@code null}
     * @return the artifact base names carrying several selected versions, never {@code null}
     */
    public static Set<String> conflictingArtifactBases(Configuration configuration) {
        return selectedVersionsByArtifactBase(configuration).entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(LinkedHashSet::new));
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
