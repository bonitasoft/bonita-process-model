/**
 * Copyright (C) 2023 Bonitasoft S.A.
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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.bpm.connector.model.implementation.DocumentRoot;
import org.bonitasoft.bpm.connector.model.implementation.util.ConnectorImplementationResourceFactoryImpl;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.eclipse.emf.ecore.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ConnectorImplementationRegistry {

    static final Logger LOGGER = LoggerFactory.getLogger(ConnectorImplementationRegistry.class);
    static final ConnectorImplementationResourceFactoryImpl RESOURCE_FACTORY = new ConnectorImplementationResourceFactoryImpl();

    Optional<ConnectorImplementation> find(String id, String version);

    Optional<ConnectorImplementation> find(Predicate<ArtifactInfo> predicate);

    /**
     * {@link ConnectorImplementationRegistry} factory method.
     * 
     * @param implementations A list of {@link ConnectorImplementationJar}
     * @return A default implementation of {@link ConnectorImplementationRegistry} that search for
     *         connector implementations from the given list of {@link ConnectorImplementationJar}.
     */
    public static ConnectorImplementationRegistry of(List<ConnectorImplementationJar> implementations) {
        return new ConnectorImplementationRegistry() {

            @Override
            public Optional<ConnectorImplementation> find(String id, String version) {
                return implementations.stream()
                        .filter(impl -> Objects.equals(impl.getId(), id) && Objects.equals(impl.getVersion(), version))
                        .map(this::loadImplementation).filter(Objects::nonNull).findFirst();
            }

            /*
             * (non-Javadoc)
             * @see org.bonitasoft.bonita2bar.ConnectorImplementationRegistry#find(java.util.function.Predicate)
             */
            @Override
            public Optional<ConnectorImplementation> find(Predicate<ArtifactInfo> predicate) {
                return implementations.stream()
                        .filter(impl -> predicate.test(impl.getArtifactInformation()))
                        .map(this::loadImplementation).filter(Objects::nonNull).findFirst();
            }

            private ConnectorImplementation loadImplementation(ConnectorImplementationJar implementation) {
                try (var jar = new JarFile(implementation.getJarFile());
                        var is = jar.getInputStream(jar.getEntry(implementation.getEntry()))) {
                    Resource resource = RESOURCE_FACTORY.createResource(null);
                    resource.load(is, Collections.emptyMap());
                    return ((DocumentRoot) resource.getContents().get(0)).getConnectorImplementation();
                } catch (IOException e) {
                    LOGGER.error("Failed to parse connector implementation", e);
                    return null;
                }
            }
        };
    }

    /**
     * A record class with maven artifact information
     */
    public static record ArtifactInfo(String groupId, String artifactId, String version, String classifier,
            String file) {

        public static Predicate<ArtifactInfo> matchesDep(Dependency dep) {
            return info -> {
                return Objects.equals(dep.getGroupId(), info.groupId())
                        && Objects.equals(dep.getArtifactId(), info.artifactId())
                        && Objects.equals(dep.getVersion(), info.version())
                        && Objects.equals(dep.getClassifier(), info.classifier());
            };
        }
    }

    public static class ConnectorImplementationJar {

        private final String id;
        private final String version;
        private final ArtifactInfo artifactInformation;
        private final String entry;

        public static ConnectorImplementationJar of(String id, String version, ArtifactInfo artifactInformation,
                String entry) {
            return new ConnectorImplementationJar(id, version, artifactInformation, entry);
        }

        /**
         * @deprecated use {@link #of(String, String, ArtifactInfo, String)} instead
         */
        @Deprecated
        public static ConnectorImplementationJar of(String id, String version, File jarFile, String entry) {
            try (var jar = new JarFile(jarFile)) {
                // find pom.xml within the jar
                var jarEntries = StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(jar.entries().asIterator(), Spliterator.ORDERED), false);
                var inMavenEntries = jarEntries.filter(e -> e.getName().contains("META-INF/maven/"))
                        .collect(Collectors.toList());
                var propertiesEntry = inMavenEntries.stream().filter(e -> e.getName().endsWith("pom.properties"))
                        .findFirst();
                // try to get artifact information from pom.properties
                var artifactFromProp = propertiesEntry.map(e -> {
                    // first prevent a Zip Bomb Attack
                    preventZipBombAttack(jar, e);
                    // parse the pom.properties to get the maven artifact information
                    try (var entryReader = new InputStreamReader(jar.getInputStream(e))) {
                        var properties = new java.util.Properties();
                        properties.load(entryReader);
                        return new ArtifactInfo(properties.getProperty("groupId"), properties.getProperty("artifactId"),
                                properties.getProperty("version"), properties.getProperty("classifier"),
                                jarFile.getAbsolutePath());
                    } catch (IOException e1) {
                        LOGGER.error("Failed to read pom.properties", e1);
                        return null;
                    }
                }).filter(Objects::nonNull);
                // try to get artifact information from pom.xml
                var pomEntry = inMavenEntries.stream().filter(e -> e.getName().endsWith("pom.xml")).findFirst();
                Supplier<Optional<ArtifactInfo>> getArtifactFromPom = () -> pomEntry.map(e -> {
                    // first prevent a Zip Bomb Attack
                    preventZipBombAttack(jar, e);
                    // parse the pom.xml to get the maven artifact information
                    MavenXpp3Reader reader = new MavenXpp3Reader();
                    try (var entryReader = new InputStreamReader(jar.getInputStream(e))) {
                        var model = reader.read(entryReader);
                        return new ArtifactInfo(model.getGroupId(), model.getArtifactId(), model.getVersion(), null,
                                jarFile.getAbsolutePath());
                    } catch (IOException | XmlPullParserException e1) {
                        LOGGER.error("Failed to read pom.xml", e1);
                        return null;
                    }
                }).filter(Objects::nonNull);
                // try to get artifact information from file location in repository
                Supplier<Optional<ArtifactInfo>> getArtifactFromFileLocation = () -> {
                    File versionFolder = jarFile.getParentFile();
                    if (versionFolder != null) {
                        var jarVersion = versionFolder.getName();
                        File artifactFolder = versionFolder.getParentFile();
                        if (artifactFolder != null) {
                            var jarArtifactId = artifactFolder.getName();
                            File groupFolder = artifactFolder.getParentFile();
                            if (groupFolder != null) {
                                var jarGroupId = getGroupId(groupFolder);
                                var supposedFileName = jarArtifactId + "-" + jarVersion + ".jar";
                                if (supposedFileName.equals(jarFile.getName())) {
                                    return Optional.of(new ArtifactInfo(jarGroupId, jarArtifactId, jarVersion, null,
                                            jarFile.getAbsolutePath()));
                                } else {
                                    var supposedFileNameWithClassifier = jarArtifactId + "-" + jarVersion
                                            + "-([\\w]*).jar";
                                    var matcher = Pattern.compile(supposedFileNameWithClassifier)
                                            .matcher(jarFile.getName());
                                    if (matcher.matches()) {
                                        return Optional.of(
                                                new ArtifactInfo(jarGroupId, jarArtifactId, jarVersion,
                                                        matcher.group(1),
                                                        jarFile.getAbsolutePath()));
                                    }
                                }
                            }
                        }
                    }
                    return Optional.empty();
                };
                var artifactInfo = artifactFromProp.or(getArtifactFromPom).or(getArtifactFromFileLocation)
                        .orElseThrow(() -> {
                            var msg = MessageFormat.format("Failed to get artifact information for {0}",
                                    jarFile.getAbsolutePath());
                            return new IllegalArgumentException(msg);
                        });
                return new ConnectorImplementationJar(id, version, artifactInfo, entry);
            } catch (IOException e) {
                var msg = MessageFormat.format("Failed to get artifact information for {0}",
                        jarFile.getAbsolutePath());
                LOGGER.error(msg, e);
                throw new IllegalArgumentException(msg);
            }
        }

        /**
         * Prevent a zip bomb attack by analyzing the entry size
         * 
         * @param jar jar file
         * @param entry entry to analyze
         */
        private static void preventZipBombAttack(JarFile jar, JarEntry entry) {
            int thresholdSize = 1_000_000; // 1 MB
            double thresholdRatio = 10.0;
            byte[] buffer = new byte[2048];
            int totalSizeEntry = 0;
            int nBytes = -1;
            try (InputStream in = new BufferedInputStream(jar.getInputStream(entry))) {
                var output = Files.createTempFile("outputForTesting", ".txt");
                OutputStream out = new BufferedOutputStream(new FileOutputStream(output.toFile()));
                while ((nBytes = in.read(buffer)) > 0) {
                    out.write(buffer, 0, nBytes);
                    totalSizeEntry += nBytes;
                    double compressionRatio = totalSizeEntry / entry.getCompressedSize();
                    if (compressionRatio > thresholdRatio || totalSizeEntry > thresholdSize) {
                        // ratio between compressed and uncompressed data is highly suspicious or entry size is too big
                        // looks like a Zip Bomb Attack
                        throw new IllegalArgumentException("Zip Bomb Attack detected");
                    }
                }
            } catch (IOException e) {
                String msg = "Zip Bomb Attack detection failed with exception";
                LOGGER.error(msg, e);
                throw new IllegalArgumentException(msg);
            }
        }

        /**
         * Get the group id out of the group folder in a maven repository
         * 
         * @param groupFolder the group folder
         * @return the group id
         */
        private static String getGroupId(File groupFolder) {
            var nameBuilder = new StringBuilder(groupFolder.getName());
            var parentFolder = groupFolder.getParentFile();
            Predicate<File> isRepositoryRoot = d -> {
                // root of repository is expected to contain directories like "org", "com", "fr"
                var commonRootFolders = Stream.of("org", "com", "fr");
                return commonRootFolders.map(n -> new File(d, n)).anyMatch(f -> f.exists() && f.isDirectory());
            };
            while (!isRepositoryRoot.test(parentFolder)) {
                nameBuilder.insert(0, '.');
                nameBuilder.insert(0, parentFolder.getName());
                parentFolder = parentFolder.getParentFile();
            }
            return nameBuilder.toString();
        }

        private ConnectorImplementationJar(String id, String version, ArtifactInfo artifactInformation, String entry) {
            this.id = id;
            this.version = version;
            this.artifactInformation = artifactInformation;
            this.entry = entry;
        }

        public String getId() {
            return id;
        }

        public String getVersion() {
            return version;
        }

        public File getJarFile() {
            return new File(artifactInformation.file());
        }

        public ArtifactInfo getArtifactInformation() {
            return artifactInformation;
        }

        public String getEntry() {
            return entry;
        }

        @Override
        public int hashCode() {
            return Objects.hash(entry, id, artifactInformation, version);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ConnectorImplementationJar other = (ConnectorImplementationJar) obj;
            return Objects.equals(entry, other.entry) && Objects.equals(id, other.id)
                    && Objects.equals(artifactInformation, other.artifactInformation)
                    && Objects.equals(version, other.version);
        }

    }

}
