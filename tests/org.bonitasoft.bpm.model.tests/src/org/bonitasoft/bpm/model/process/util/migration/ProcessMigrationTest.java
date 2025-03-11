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
        /*
         * This tests that 7.12 proc model version was migrated to 8
         * But also that 6.0 connector definition was migrated to 6.1
         */
        // load both models
        ModelLoader loader = ModelLoader.create().enablePartial();
        Resource oldModel = loader.loadModel(PROC_7_12_0_MODEL_URI);
        assertTrue(oldModel.isLoaded());
        Resource latestModel = loader.loadModel(PROC_8_MODEL_URI);
        assertTrue(latestModel.isLoaded());

        // and test that content is similar
        assertTrue(EcoreUtil.equals(oldModel.getContents().get(0), latestModel.getContents().get(0)));
    }

    @Test
    void migrate() throws Exception {
        // temp test to migrate model from 8 to 9
        String FOLDER = "C:/Dev/bonita-studio-sp-dev/git/bonita-process-model/tests/org.bonitasoft.bpm.model.tests/resources/";
        ModelLoader loader = ModelLoader.create().enablePartial().withPolicy(MigrationPolicy.ALWAYS_MIGRATE_POLICY);
        Resource model = loader.loadModel(URI.createFileURI(FOLDER + "MyDiagram-8.proc"));
        assertTrue(model.isLoaded());
        model.save(null);
    }
}
