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
package org.bonitasoft.bonita2bar.resources;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.net.URLDecoder;

import org.bonitasoft.bonita2bar.ProcessRegistry;
import org.bonitasoft.bpm.model.process.util.migration.MigrationPolicy;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.engine.bpm.flownode.impl.internal.FlowElementContainerDefinitionImpl;
import org.bonitasoft.engine.bpm.process.impl.internal.DesignProcessDefinitionImpl;
import org.eclipse.core.runtime.FileLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BPMN2ArtifactProviderTest {

    private BPMN2ArtifactProvider bpmn2ArtifactProvider;
    private BusinessArchiveBuilder businessArchiveBuilder;
    private ProcessRegistry processRegistry;

    @BeforeEach
    void before() throws Exception {
        // given
        File repoRoot = new File(URLDecoder.decode(
                FileLocator.toFileURL(BPMN2ArtifactProvider.class.getResource("/my-project/")).getFile(),
                "UTF-8"));
        processRegistry = ProcessRegistry.of(repoRoot.toPath().resolve("app").resolve("diagrams"),
                MigrationPolicy.NEVER_MIGRATE_POLICY);
        bpmn2ArtifactProvider = new BPMN2ArtifactProvider(processRegistry,
                repoRoot.toPath().resolve("target"));
        businessArchiveBuilder = new BusinessArchiveBuilder().createNewBusinessArchive();
    }

    @Test
    void should_export_bpmn2_file_in_bar() throws Exception {

        var pool = processRegistry.getProcess("SimpleProcessWithContract", "1.0").orElseThrow();

        // when
        bpmn2ArtifactProvider.build(businessArchiveBuilder, pool, null);
        // then
        businessArchiveBuilder.setProcessDefinition(fakeProcessDef());
        assertThat(businessArchiveBuilder.done().getResource("resources/process.bpmn")).isNotNull().isNotEmpty();
    }

    private static DesignProcessDefinitionImpl fakeProcessDef() {
        DesignProcessDefinitionImpl designProcessDefinitionImpl = new DesignProcessDefinitionImpl();
        FlowElementContainerDefinitionImpl processContainer = new FlowElementContainerDefinitionImpl();
        designProcessDefinitionImpl.setProcessContainer(processContainer);
        return designProcessDefinitionImpl;
    }

}
