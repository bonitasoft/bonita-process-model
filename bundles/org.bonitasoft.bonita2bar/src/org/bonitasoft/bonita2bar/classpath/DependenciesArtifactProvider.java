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
package org.bonitasoft.bonita2bar.classpath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.bonita2bar.BarArtifactProvider;
import org.bonitasoft.bonita2bar.BarBuilder;
import org.bonitasoft.bonita2bar.BuildBarException;
import org.bonitasoft.bonita2bar.MavenExecutor;
import org.bonitasoft.bonita2bar.process.pomgen.ProcessPom;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.Fragment;
import org.bonitasoft.bpm.model.configuration.FragmentContainer;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides artifacts for building the BAR file, based on the dependencies from maven and connectors used by the process.
 */
public class DependenciesArtifactProvider implements BarArtifactProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(DependenciesArtifactProvider.class);

    /**
     * Format for building the profile name used for a specific environment.
     */
    private static final String ENV_PROFILE_FORMAT = "env-%s";

    /**
     * Pattern to extract the artifact base name (artifactId) from a jar filename.
     * Matches everything before the last hyphen-digit sequence that looks like a version.
     * Examples: {@code bcpkix-jdk18on-1.78.1.jar} → {@code bcpkix-jdk18on},
     * {@code commons-collections4-4.4.jar} → {@code commons-collections4}
     */
    static final Pattern ARTIFACT_BASE_PATTERN = Pattern.compile("^(.+)-(\\d[\\d.]*)(?:-[\\w]+)?\\.jar$");

    /** Executes maven command */
    private MavenExecutor mavenExecutor;

    /**
     * Default Constructor.
     *
     * @param mavenExecutor the maven executor
     */
    public DependenciesArtifactProvider(MavenExecutor mavenExecutor) {
        this.mavenExecutor = mavenExecutor;
    }

    @Override
    public void build(BusinessArchiveBuilder builder, Pool process, ProcessPom pomAccess, Configuration configuration)
            throws BuildBarException {
        try {
            var pom = pomAccess.readPom();
            File processPomFolder = pom.getPomFile().getParentFile();
            var dependenciesFolder = new File(processPomFolder, "dependencies");
            String envName = configuration.getName() == null ? BarBuilder.LOCAL_ENVIRONMENT : configuration.getName();
            var profileToUse = String.format(ENV_PROFILE_FORMAT, envName);

            Supplier<String> errMsg = () -> String.format("Failed to build dependencies for process %s-%s",
                    process.getName(), process.getVersion());
            mavenExecutor.execute(pom.getPomFile(), List.of("dependency:copy-dependencies"),
                    Map.of("outputDirectory", "./" + dependenciesFolder.getName(),
                            "includeScope", "runtime",
                            "includeTypes", "jar"),
                    List.of(profileToUse), errMsg);

            // filter copied dependencies based on configuration exported flags,
            // then add remaining files to the business archive
            if (dependenciesFolder.exists()) {
                filterCopiedDependencies(dependenciesFolder, configuration);
                exploreDependencies(builder, dependenciesFolder);
            }
        } catch (IOException | XmlPullParserException e) {
            throw new BuildBarException(String.format("Failed to add dependencies in bar %s-%s.bar.", process.getName(),
                    process.getVersion()), e);
        }

    }

    /**
     * Filter copied dependencies using a dual matching strategy:
     * <ul>
     * <li><b>Runtime container JARs</b> (base name in {@code runtimeContainerBaseNames}):
     * kept using base-name matching (tolerant of version differences), because Maven may
     * resolve a different version than the one in the Bonita runtime container (Tomcat).</li>
     * <li><b>All other exported JARs</b>: require exact filename matching. If Maven resolves
     * a different version than what the fragment references, the JAR is excluded and a warning
     * is logged.</li>
     * </ul>
     *
     * @param dependenciesFolder the folder containing copied dependencies
     * @param configuration the configuration containing exported fragment information
     */
    void filterCopiedDependencies(File dependenciesFolder, Configuration configuration) throws IOException {
        if (configuration == null) {
            return;
        }
        var containers = configuration.getProcessDependencies();
        if (containers.isEmpty()) {
            return;
        }

        // Collect all fragments once to avoid multiple tree traversals
        var allFragments = containers.stream()
                .flatMap(DependenciesArtifactProvider::walkAllFragments)
                .toList();

        // No fragments = old configuration file, don't filter (backward compatibility)
        if (allFragments.isEmpty()) {
            return;
        }

        // Whitelist: collect exact filenames of exported fragments
        Set<String> exportedExactNames = allFragments.stream()
                .filter(Fragment::isExported)
                .map(Fragment::getValue)
                .collect(Collectors.toSet());

        // Also collect base name -> exact filename for version mismatch detection
        Map<String, String> exportedBaseToExact = allFragments.stream()
                .filter(Fragment::isExported)
                .collect(Collectors.toMap(
                        f -> extractArtifactBase(f.getValue()),
                        Fragment::getValue,
                        (a, b) -> a));

        // Exclusion: explicitly non-exported fragments take priority.
        // After flattenFragmentsToOther, a jar may appear as exported=true in a connector
        // child container while the user has set exported=false in the OTHER container.
        // The user's explicit exclusion must win.
        Set<String> excludedExactNames = allFragments.stream()
                .filter(f -> !f.isExported())
                .map(Fragment::getValue)
                .collect(Collectors.toSet());

        Set<String> excludedBases = allFragments.stream()
                .filter(f -> !f.isExported())
                .map(f -> extractArtifactBase(f.getValue()))
                .collect(Collectors.toSet());

        exportedExactNames.removeAll(excludedExactNames);
        excludedBases.forEach(exportedBaseToExact::remove);

        // Filter files
        File[] files = dependenciesFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                String fileBase = extractArtifactBase(fileName);

                if (exportedExactNames.contains(fileName)) {
                    // Exact filename match with exported=true → keep
                    // This takes priority over base-name exclusion to handle the case
                    // where two versions of the same artifact have different exported flags
                    // (e.g. asm-3.3.1.jar exported=true, asm-9.8.jar exported=false)
                } else if (excludedBases.contains(fileBase)) {
                    // Base-name excluded by user (exported=false) and no exact match exported
                    Files.delete(file.toPath());
                } else if (exportedBaseToExact.containsKey(fileBase)) {
                    // Non-runtime JAR: base name matches but version differs → exclude + warning
                    String expected = exportedBaseToExact.get(fileBase);
                    LOGGER.warn("Version mismatch: fragment expects '{}' but Maven resolved '{}'. "
                            + "Excluding from BAR.", expected, fileName);
                    Files.delete(file.toPath());
                } else {
                    // Unknown jar, not tracked by any fragment → exclude
                    Files.delete(file.toPath());
                }
            }
        }
    }

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
    static String extractArtifactBase(String filename) {
        Matcher m = ARTIFACT_BASE_PATTERN.matcher(filename);
        if (m.matches()) {
            return m.group(1);
        }
        // Fallback: strip .jar extension
        return filename.endsWith(".jar") ? filename.substring(0, filename.length() - 4) : filename;
    }

    /**
     * Explore dependencies folder and add all files to the business archive.
     * 
     * @param builder archive builder
     * @param dependenciesFolder folder containing dependencies
     * @throws BuildBarException if an error occurs while building the archive
     * @throws IOException if an error occurs while reading the dependencies
     */
    private void exploreDependencies(BusinessArchiveBuilder builder, File dependenciesFolder)
            throws BuildBarException, IOException {
        try (Stream<Path> walker = Files.walk(dependenciesFolder.toPath())) {
            List<Path> files = walker
                    .filter(Files::isRegularFile).toList();
            for (var file : files) {
                addDependencyToBuild(builder, file);
            }
        }
    }

    /**
     * Add a file as dependency to the business archive.
     * 
     * @param builder archive builder
     * @param file file to add as dependency
     * @throws BuildBarException if an error occurs while building the archive
     */
    private void addDependencyToBuild(BusinessArchiveBuilder builder, Path file) throws BuildBarException {
        try {
            BarResource barResource = new BarResource(file.getFileName().toString(),
                    Files.readAllBytes(file));
            if (file.toString().endsWith(".jar")) {
                builder.addClasspathResource(barResource);
            } else {
                builder.addExternalResource(barResource);
            }
        } catch (IOException e) {
            throw new BuildBarException(String.format("Unable to get content of the %s ", file), e);
        }
    }

    static Stream<Fragment> walkAllFragments(FragmentContainer container) {
        return Stream.concat(
                container.getFragments().stream(),
                container.getChildren().stream().flatMap(DependenciesArtifactProvider::walkAllFragments));
    }

}
