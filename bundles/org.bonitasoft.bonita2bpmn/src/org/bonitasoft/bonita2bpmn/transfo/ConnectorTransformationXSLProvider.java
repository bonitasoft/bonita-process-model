/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.bonita2bpmn.transfo;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;

public interface ConnectorTransformationXSLProvider {

    /**
     * A default instance which can be used as is
     */
    public static final ConnectorTransformationXSLProvider DEFAULT = new ConnectorTransformationXSLProvider() {
    };

    static final String CONNECTOR_XSL_FILE_PATH = "transfo/genConnectorsXSD.xsl";

    default File getConnectorXSLFile() throws IOException {
        final URL xsltUrl = ConnectorTransformationXSLProvider.class.getClassLoader()
                .getResource(CONNECTOR_XSL_FILE_PATH);
        return new File(FileLocator.toFileURL(xsltUrl).getFile());
    }

}
