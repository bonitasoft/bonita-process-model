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
package org.bonitasoft.bpm.model.process.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import java.util.Collections;

import org.bonitasoft.bpm.model.actormapping.ActorMappingFactory;
import org.bonitasoft.bpm.model.configuration.ConfigurationFactory;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ProcessResourceFactoryImplTest {

    @Test
    void createResourceAndSave(@TempDir Path tmpFolder) throws Exception {
        var factory = new ProcessResourceFactoryImpl();

        Path procFile = tmpFolder.resolve("my-process.proc");
        var uri = URI.createFileURI(procFile.toString());
        var resources = factory.createResource(uri);

        var mainProcess = ProcessFactory.eINSTANCE.createMainProcess();
        var pool = ProcessFactory.eINSTANCE.createPool();
        var configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        var actorMappingType = ActorMappingFactory.eINSTANCE.createActorMappingsType();
        var mappping = ActorMappingFactory.eINSTANCE.createActorMapping();
        var roles = ActorMappingFactory.eINSTANCE.createRoles();
        roles.getRole().add("member");
        mappping.setRoles(roles);
        actorMappingType.getActorMapping().add(mappping);
        configuration.setActorMappings(actorMappingType);
        pool.getConfigurations().add(configuration);
        mainProcess.getElements().add(pool);
        resources.getContents().add(mainProcess);

        resources.save(Collections.emptyMap());
        assertThat(procFile).exists();
    }

}
