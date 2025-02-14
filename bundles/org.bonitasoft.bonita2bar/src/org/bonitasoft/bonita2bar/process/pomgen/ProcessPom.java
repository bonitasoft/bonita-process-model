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
package org.bonitasoft.bonita2bar.process.pomgen;

import java.io.Closeable;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

/**
 * Gives access to the pom.xml generated for a specific process.
 */
public class ProcessPom implements Closeable {

    Path folderPath;

    /**
     * Default Constructor.
     * 
     * @param folderPath the folder where the pom.xml is generated
     */
    ProcessPom(Path folderPath) {
        this.folderPath = folderPath;
    }

    public Model readPom() throws IOException, XmlPullParserException {
        // read the generated pom.xml file
        var pom = folderPath.resolve("pom.xml").toFile();
        if (!pom.exists()) {
            throw new IllegalStateException("pom.xml not found in " + folderPath);
        }
        var reader = new MavenXpp3Reader();
        try (var fileReader = new FileReader(pom)) {
            var pomModel = reader.read(fileReader);
            pomModel.setPomFile(pom);
            return pomModel;
        }
    }

    /**
     * Write the pom.xml file.
     * 
     * @param model the model to write
     * @throws IOException if an error occurs while writing
     */
    public void writePom(Model model) throws IOException {
        var pom = folderPath.resolve("pom.xml").toFile();
        var writer = new MavenXpp3Writer();
        try (var fileWriter = new FileWriter(pom)) {
            writer.write(fileWriter, model);
        }
    }

    /*
     * (non-Javadoc)
     * @see java.io.Closeable#close()
     */
    @Override
    public void close() throws IOException {
        // get rid of temporary folder
        if (folderPath != null && Files.exists(folderPath)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(folderPath)) {
                for (Path entry : stream) {
                    Files.delete(entry);
                }
            }
            Files.delete(folderPath);
        }
    }

}
