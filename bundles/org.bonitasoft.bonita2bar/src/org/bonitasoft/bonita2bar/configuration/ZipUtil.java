/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.bonita2bar.configuration;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil extends SimpleFileVisitor<Path> implements java.lang.AutoCloseable {

    public static final int BUFFER_SIZE = 4096;
    private Path source;
    private FileOutputStream fos;
    private ZipOutputStream zos;
    private Predicate<Path> targetFilter;

    public static void zip(Path source, Path target) throws IOException {
        try (ZipUtil zippingVisitor = new ZipUtil(source, target)) {
            Files.walkFileTree(source, zippingVisitor);
        }
    }

    public ZipUtil(Path source, Path target) throws FileNotFoundException {
        this.source = source;
        fos = new FileOutputStream(target.toFile());
        zos = new ZipOutputStream(fos);
        targetFilter = target::equals;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (targetFilter.test(file)) {
            // do not include the target in zip
            return CONTINUE;
        }
        if (!file.toFile().exists()) {
            throw new IOException("File " + file.toString() + " not found.");
        }
        Path zipEntryPath = source.relativize(file);
        byte[] buffer = new byte[BUFFER_SIZE];
        try (FileInputStream fis = new FileInputStream(file.toFile())) {
            zos.putNextEntry(new ZipEntry(normalizePath(zipEntryPath)));
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
            zos.closeEntry();
        }
        return CONTINUE;
    }

    /**
     * Ensure that the zip entry separator is '/', which is not the case by default on windows ('\\'),
     * So the client doesn't have to manage this.
     */
    public static String normalizePath(Path path) {
        return path.toString().replace("\\", "/");
    }

    @Override
    public void close() throws IOException {
        zos.close();
        fos.close();
    }

    public static Path unzip(File file, Path targetFolder) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
                ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                unzipEntry(targetFolder, zis, entry);
            }
        }
        return targetFolder;
    }

    private static void unzipEntry(Path targetDir, ZipInputStream zis, ZipEntry entry)
            throws IOException {
        File target = targetDir.toFile().toPath().resolve(entry.getName()).toFile();
        if (entry.isDirectory()) {
            if (!target.exists()) {
                target.mkdirs();
            }
        } else {
            if (!target.exists()) {
                target.getParentFile().mkdirs();
                if (!target.createNewFile()) {
                    throw new IOException("Failed to create file " + target.getAbsolutePath());
                }
            }

            try (var fos = new FileOutputStream(target);
                    var dest = new BufferedOutputStream(fos, BUFFER_SIZE);) {
                byte[] data = new byte[BUFFER_SIZE];
                int count;
                while ((count = zis.read(data, 0, BUFFER_SIZE)) != -1) {
                    dest.write(data, 0, count);
                }
                dest.flush();
            }
        }
    }

}
