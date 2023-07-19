/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.bonita2bar.process.builder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.bpm.model.process.builders.DocumentBuilder.aDocument;
import static org.bonitasoft.bpm.model.process.builders.PoolBuilder.aPool;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DocumentEngineDefinitionBuilderTest {

    @Spy
    public DocumentEngineDefinitionBuilder documentEngineDefinitionBuilder;

    @Mock
    private ProcessDefinitionBuilder processDefinitionBuilder;

    @Mock
    private SingleDocumentEngineDefinitionBuilder singleDelegate;

    @Mock
    private MultipleDocumentEngineDefinitionBuilder multipleDelegate;

    @BeforeEach
    void setUp() throws Exception {
        documentEngineDefinitionBuilder.setEngineBuilder(processDefinitionBuilder);
    }

    @Test
    void should_appliesTo_Document_in_a_Pool() throws Exception {
        assertThat(documentEngineDefinitionBuilder.appliesTo(aPool().build(), aDocument().build())).isTrue();
    }

    @Test
    void should_not_appliesTo_anything_else() throws Exception {
        assertThat(documentEngineDefinitionBuilder.appliesTo(aPool().build(), null)).isFalse();
        assertThat(documentEngineDefinitionBuilder.appliesTo(null, aDocument().build())).isFalse();
        assertThat(documentEngineDefinitionBuilder.appliesTo(null, null)).isFalse();
    }

    @Test
    void should_delegate_build_to_single_document_builder() throws Exception {
        doReturn(singleDelegate).when(documentEngineDefinitionBuilder).singleDocumentDelegate(notNull());

        documentEngineDefinitionBuilder.build(aDocument().build());

        verify(singleDelegate).build();
    }

    @Test
    void should_delegate_build_to_multiple_document_builder() throws Exception {
        doReturn(multipleDelegate).when(documentEngineDefinitionBuilder).multipeDocumentDelegate(notNull());
        documentEngineDefinitionBuilder.build(aDocument().multiple().build());

        verify(multipleDelegate).build();
    }
}
