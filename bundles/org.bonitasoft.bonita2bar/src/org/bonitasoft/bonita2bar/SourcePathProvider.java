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

import java.nio.file.Path;

public interface SourcePathProvider {

    Path getResources();

    Path getLocalConfiguration();

    Path getAttachments();

    Path getGroovySource();

    Path getForms();

    static SourcePathProvider of(Path projectRoot) {
        return new SourcePathProvider() {

            @Override
            public Path getResources() {
                return projectRoot.resolve("src/main/resources");
            }

            @Override
            public Path getLocalConfiguration() {
                return projectRoot.resolve("process_configurations");
            }

            @Override
            public Path getGroovySource() {
                return projectRoot.resolve("src-groovy");
            }

            @Override
            public Path getForms() {
                return projectRoot.resolve("web_page");
            }

            @Override
            public Path getAttachments() {
                return projectRoot.resolve("attachments");
            }
        };
    }

}
