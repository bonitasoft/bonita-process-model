/**
 * Copyright (C) 2021 Bonitasoft S.A.
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

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface ClasspathResolver {

    File findJarFile(String fileName) throws IOException;

    public List<File> listFiles() throws IOException;

    static ClasspathResolver of(List<String> classpath) {
        return new ClasspathResolver() {

            @Override
            public File findJarFile(String fileName) throws IOException {
                return listFiles().stream().filter(file -> Objects.equals(file.getName(), fileName)).findFirst()
                        .orElse(null);
            }

            @Override
            public List<File> listFiles() throws IOException {
                return classpath.stream()
                        .map(File::new)
                        .collect(Collectors.toList());
            }

        };
    }

}
