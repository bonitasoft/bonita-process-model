/**
 * Copyright (C) 2018 Bonitasoft S.A.
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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

class JarBuilder {

    private JarBuilder() {
        //implicit constructor
    }

    static Path createJar(File targetClasses, Path outputJarFile) throws IOException {
        Manifest manifest = new Manifest();
        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
        try (JarOutputStream target = new JarOutputStream(Files.newOutputStream(outputJarFile), manifest)) {
            File[] classes = targetClasses.listFiles();
            if (classes != null) {
                for (File classFile : classes) {
                    add(classFile, target, targetClasses);
                }
            }
        }
        return outputJarFile;
    }

    private static void add(File source, JarOutputStream target, File targetClasses) throws IOException {
        String baseDir = targetClasses.getPath() + File.separatorChar;
        if (source.isDirectory()) {
            String name = source.getPath().replace("\\", "/").replace(baseDir.replace("\\", "/"), "");
            if (!name.isEmpty()) {
                if (!name.endsWith("/"))
                    name += "/";
                JarEntry entry = new JarEntry(name);
                entry.setTime(source.lastModified());
                target.putNextEntry(entry);
                target.closeEntry();
            }
            for (File nestedFile : source.listFiles())
                add(nestedFile, target, targetClasses);
            return;
        }

        JarEntry entry = new JarEntry(
                source.getPath().replace("\\", "/").replace(baseDir.replace("\\", "/"), ""));
        entry.setTime(source.lastModified());
        target.putNextEntry(entry);
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source));) {
            byte[] buffer = new byte[1024];
            while (true) {
                int count = in.read(buffer);
                if (count == -1)
                    break;
                target.write(buffer, 0, count);
            }
            target.closeEntry();
        }
    }

}
