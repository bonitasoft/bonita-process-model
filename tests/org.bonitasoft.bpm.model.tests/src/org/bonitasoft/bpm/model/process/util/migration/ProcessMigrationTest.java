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
package org.bonitasoft.bpm.model.process.util.migration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.bonitasoft.bpm.model.util.ModelLoader;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.Test;

/**
 * Tests migration of a process model from an older model version
 * 
 * @author Vincent Hemery
 */
class ProcessMigrationTest {

    /** Prefix for model URI strings */
    private static final String PREFIX = "/org.bonitasoft.bpm.model.tests/resources/";
    /** URI to 7.12.0 model instance */
    private static final URI PROC_7_12_0_MODEL_URI = URI.createPlatformPluginURI(PREFIX + "MyDiagram-7.12.0-004.proc",
            false);
    /** URI to 8 model instance */
    private static final URI PROC_8_MODEL_URI = URI.createPlatformPluginURI(PREFIX + "MyDiagram-8.proc", false);

    @Test
    void testModelMigration_7_12_0() throws Exception {
        // load both models
        Resource oldModel = ModelLoader.getInstance().loadModel(PROC_7_12_0_MODEL_URI);
        assertTrue(oldModel.isLoaded());
        Resource latestModel = ModelLoader.getInstance().loadModel(PROC_8_MODEL_URI);
        assertTrue(oldModel.isLoaded());

        // and test that content is similar
        assertTrue(EcoreUtil.equals(oldModel.getContents().get(0), latestModel.getContents().get(0)));
    }
}
