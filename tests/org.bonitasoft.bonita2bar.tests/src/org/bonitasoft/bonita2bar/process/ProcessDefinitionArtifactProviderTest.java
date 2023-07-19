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
package org.bonitasoft.bonita2bar.process;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.bonitasoft.bonita2bar.ProcessRegistry;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.engine.bpm.process.DesignProcessDefinition;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class ProcessDefinitionArtifactProviderTest {

    @Test
    void should_set_process_definition_on_business_archive_builder() throws Exception {
        final ProcessDefinitionArtifactProvider processDefinitionArtifactProvider = new ProcessDefinitionArtifactProvider(
                mock(ProcessRegistry.class));

        final BusinessArchiveBuilder builder = mock(BusinessArchiveBuilder.class);

        final Pool pool = ProcessFactory.eINSTANCE.createPool();
        pool.setName("Test");
        pool.setVersion("1.0");
        processDefinitionArtifactProvider.build(builder, pool,
                mock(Configuration.class));

        final ArgumentCaptor<DesignProcessDefinition> captor = ArgumentCaptor.forClass(DesignProcessDefinition.class);
        verify(builder).setProcessDefinition(captor.capture());

        final DesignProcessDefinition def = captor.getValue();
        assertThat(def.getName()).isEqualTo("Test");
        assertThat(def.getVersion()).isEqualTo("1.0");
    }

}
