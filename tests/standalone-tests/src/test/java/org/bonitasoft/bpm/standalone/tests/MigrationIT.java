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
package org.bonitasoft.bpm.standalone.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementationPackage;
import org.bonitasoft.bpm.model.process.MainProcess;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.util.ModelLoader;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MigrationIT {

    @BeforeAll
    static void registerPackages() {
        EPackage.Registry.INSTANCE.put(NotationPackage.eNS_URI, NotationPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put("http://www.bonitasoft.org/ns/connector/definition/6.0",
                ConnectorDefinitionPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(ConnectorDefinitionPackage.eNS_URI,
                ConnectorDefinitionPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(ConnectorImplementationPackage.eNS_URI,
                ConnectorImplementationPackage.eINSTANCE);
    }

    @Test
    void migrateConfigurationInProc() throws Exception {
        var resource = ModelLoader.getInstance()
                .loadModel(URI.createFileURI(
                        new File(MigrationIT.class.getResource("/ProcessWithConfigurations-1.0.proc").getFile())
                                .getAbsolutePath()));

        MainProcess mainProcess = (MainProcess) resource.getContents().get(0);
        Pool pool = (Pool) mainProcess.getElements().get(0);

        assertThat(pool.getConfigurations().get(0).getActorMappings()).isNotNull();
    }
}
