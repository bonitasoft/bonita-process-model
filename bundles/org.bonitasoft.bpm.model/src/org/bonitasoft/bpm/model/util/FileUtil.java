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
package org.bonitasoft.bpm.model.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermissions;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * Utilities to work with files
 */
public class FileUtil {

    /**
     * Consumes a file path to perform an operation.<br/>
     * Like {@link Consumer<File>} but eventually throwing an {@link IOException}
     */
    @FunctionalInterface
    public static interface FileConsumer {

        /**
         * Performs this operation on the given argument.
         *
         * @param path the input file path
         * @throws IOException when operation on file fails
         */
        void accept(Path path) throws IOException;

        /**
         * Returns a composed {@code FileConsumer} that performs, in sequence, this
         * operation followed by the {@code after} operation. If performing either
         * operation throws an exception, it is relayed to the caller of the
         * composed operation. If performing this operation throws an exception,
         * the {@code after} operation will not be performed.
         *
         * @param after the operation to perform after this operation
         * @return a composed {@code FileConsumer} that performs in sequence this
         *         operation followed by the {@code after} operation
         * @throws NullPointerException if {@code after} is null
         */
        default FileConsumer andThen(FileConsumer after) {
            Objects.requireNonNull(after);
            return f -> {
                accept(f);
                after.accept(f);
            };
        }
    }

    /** User-only POSIX permissions */
    private static final String USER_ONLY_PERMISSIONS = "rwx------";

    /**
     * Private unused constructor.
     */
    private FileUtil() {
        // do nothing
    }

    /**
     * Delete a directory recursively
     * 
     * @param directory the directory path
     * @throws IOException exception during deletion
     */
    public static void deleteDir(Path directory) throws IOException {
        try (Stream<Path> pathStream = Files.walk(directory)) {
            pathStream.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
        }
    }

    /**
     * Creates a secured temporary directory
     * 
     * @param prefix the prefix string to be used in generating the directory's name
     * @throws IOException exception during creation
     */
    // java:S5443 here is a false positive : we do restrict permissions with #setWindowsPermissions
    @SuppressWarnings("java:S5443")
    public static Path createTempDir(String prefix) throws IOException {
        if (EnvironmentUtil.isWindows()) {
            Path dir = Files.createTempDirectory(prefix);
            setWindowsPermissions(dir);
            return dir;
        } else {
            var attr = PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString(USER_ONLY_PERMISSIONS));
            return Files.createTempDirectory(prefix, attr);
        }
    }

    /**
     * Perform an operation on a temporary directory
     * 
     * @param prefix the prefix string to be used in generating the directory's name
     * @param operation the operation consuming the temporary directory
     * @throws IOException when operation fails
     */
    public static void withTempDir(String prefix, FileConsumer operation) throws IOException {
        Objects.requireNonNull(operation);
        Path dir = createTempDir(prefix);
        try {
            operation.accept(dir);
        } finally {
            deleteDir(dir);
        }
    }

    /**
     * Creates a secured temporary file
     * 
     * @param baseFileName the original file name to use in generating the file's name
     * @throws IOException exception during creation
     */
    public static Path createTempFile(String baseFileName) throws IOException {
        int dotIndex = baseFileName.lastIndexOf('.');
        String prefix;
        String suffix;
        if (dotIndex > 0) {
            prefix = baseFileName.substring(0, dotIndex);
            suffix = baseFileName.substring(dotIndex);
        } else {
            prefix = baseFileName;
            suffix = null;
        }
        return createTempFile(prefix, suffix);
    }

    /**
     * Creates a secured temporary file
     * 
     * @param prefix the prefix string to be used in generating the file's name
     * @param suffix the suffix string to be used in generating the file's name
     * @throws IOException exception during creation
     */
    // java:S5443 here is a false positive : we do restrict permissions with #setWindowsPermissions
    @SuppressWarnings("java:S5443")
    public static Path createTempFile(String prefix, String suffix) throws IOException {
        if (EnvironmentUtil.isWindows()) {
            Path path = Files.createTempFile(prefix, suffix);
            setWindowsPermissions(path);
            return path;
        } else {
            var attr = PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString(USER_ONLY_PERMISSIONS));
            return Files.createTempFile(prefix, suffix, attr);
        }
    }

    /**
     * Perform an operation on a temporary file
     * 
     * @param prefix the prefix string to be used in generating the file's name
     * @param suffix the suffix string to be used in generating the file's name
     * @param operation the operation consuming the temporary file
     * @throws IOException when operation fails
     */
    public static void withTempFile(String prefix, String suffix, FileConsumer operation) throws IOException {
        Objects.requireNonNull(operation);
        Path file = createTempFile(prefix, suffix);
        try {
            operation.accept(file);
        } finally {
            Files.deleteIfExists(file);
        }
    }

    /**
     * Perform an operation on a temporary file
     * 
     * @param baseFileName the original file name to use in generating the file's name
     * @param operation the operation consuming the temporary file
     * @throws IOException when operation fails
     */
    public static void withTempFile(String baseFileName, FileConsumer operation) throws IOException {
        Objects.requireNonNull(operation);
        Path file = createTempFile(baseFileName);
        try {
            operation.accept(file);
        } finally {
            Files.deleteIfExists(file);
        }
    }

    /**
     * Set windows permissions to be tight
     * 
     * @param path the file path to secure
     */
    private static void setWindowsPermissions(Path path) {
        File file = path.toFile();
        boolean permOk = file.setReadable(true, true);
        permOk = file.setWritable(true, true) && permOk;
        permOk = file.setExecutable(true, true) && permOk;
        if (!permOk) {
            EcorePlugin.INSTANCE.log(MessageFormat.format("Permission could not be set for file {0}", path));
        }
    }

}
