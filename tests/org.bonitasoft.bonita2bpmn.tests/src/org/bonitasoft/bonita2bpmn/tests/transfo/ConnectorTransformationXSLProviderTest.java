/** 
 * Copyright (C) 2023 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.bonita2bpmn.tests.transfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.assertj.core.util.Files;
import org.bonitasoft.bonita2bpmn.transfo.ConnectorTransformationXSLProvider;
import org.junit.Test;

/**
 * @author Vincent Hemery
 */
public class ConnectorTransformationXSLProviderTest {

    /**
     * Test the default implementation of ConnectorTransformationXSLProvider in an OSGi environment
     * 
     * @throws IOException
     */
    @Test
    public void testDefaultInOsgi() throws IOException {
        var instance = ConnectorTransformationXSLProvider.DEFAULT;
        File file = instance.getConnectorXSLFile();
        assertTrue(file.exists());
        String content = Files.contentOf(file, Charset.defaultCharset());
        assertThat(content).contains("xsl:stylesheet");
    }
}
