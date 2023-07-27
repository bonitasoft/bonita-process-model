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

import static org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder.aGroovyScriptExpression;
import static org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.bpm.model.process.builders.ContractInputBuilder.aContractInput;
import static org.bonitasoft.bpm.model.process.builders.DocumentBuilder.aDocument;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.bonitasoft.bpm.model.process.ContractInput;
import org.bonitasoft.bpm.model.process.ContractInputType;
import org.bonitasoft.bpm.model.process.DocumentType;
import org.bonitasoft.bpm.model.process.builders.ContractInputBuilder;
import org.bonitasoft.engine.bpm.process.impl.DocumentDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SingleDocumentEngineDefinitionBuilderTest {

    @Mock
    private ProcessDefinitionBuilder processBuilder;

    @Mock
    private DocumentDefinitionBuilder docDefinitionBuilder;

    @Mock
    private DocumentGroovyScriptExpressionFactory scriptFactory;

    @Test
    void testMimeTypeAddedIfThereIsADefaultValue() throws Exception {
        when(processBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);

        final SingleDocumentEngineDefinitionBuilder builder = new SingleDocumentEngineDefinitionBuilder(aDocument().withDocumentType(DocumentType.EXTERNAL)
                .havingURL(anExpression().withContent("http://test.com")).havingMimeType(anExpression().withContent("octet/stream")).build(), processBuilder,
                scriptFactory);
        builder.build();

        verify(docDefinitionBuilder).addMimeType(anyString());
    }

    @Test
    void testMimeTypeAddedEvenIfThereIsNoDefaultValue() throws Exception {
        when(processBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);

        final SingleDocumentEngineDefinitionBuilder builder = new SingleDocumentEngineDefinitionBuilder(aDocument().withDocumentType(DocumentType.EXTERNAL)
                .havingURL(anExpression()).havingMimeType(anExpression().withContent("octet/stream")).build(), processBuilder, scriptFactory);
        builder.build();

        verify(docDefinitionBuilder).addMimeType(anyString());
    }

    @Test
    void testIntialContentForExternalSimpleDocumentAdded() throws Exception {
        when(processBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);

        final SingleDocumentEngineDefinitionBuilder builder = new SingleDocumentEngineDefinitionBuilder(aDocument().withDocumentType(DocumentType.EXTERNAL)
                .havingURL(aGroovyScriptExpression().withReturnType(List.class.getName()).withContent("http://test.com")).build(), processBuilder,
                scriptFactory);
        builder.build();

        verify(docDefinitionBuilder).addUrl(Mockito.anyString());
    }

    @Test
    void testIntialContentForExternalSimpleDocumentNotAddedIfUrlNotSpecified() throws Exception {
        when(processBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);

        final SingleDocumentEngineDefinitionBuilder builder = new SingleDocumentEngineDefinitionBuilder(aDocument().withDocumentType(DocumentType.EXTERNAL)
                .build(), processBuilder, scriptFactory);
        builder.build();

        verify(docDefinitionBuilder, Mockito.never()).addUrl(Mockito.anyString());
    }

    @Test
    void testIntialContentForInteralSimpleDocumentAdded() throws Exception {
        when(processBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);

        final SingleDocumentEngineDefinitionBuilder builder = new SingleDocumentEngineDefinitionBuilder(aDocument().withDocumentType(DocumentType.INTERNAL)
                .withDefaultValueIdOfDocumentStore("idTest").build(), processBuilder, scriptFactory);
        builder.build();

        verify(docDefinitionBuilder).addFile(Mockito.anyString());
        verify(docDefinitionBuilder).addContentFileName(Mockito.anyString());
    }

    @Test
    void testIntialContentNotAddedForInternalSimpleDocumentAddedIfStoreIdNotSpecified() throws Exception {
        when(processBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);

        final SingleDocumentEngineDefinitionBuilder builder = new SingleDocumentEngineDefinitionBuilder(aDocument().withDocumentType(DocumentType.INTERNAL)
                .build(), processBuilder, scriptFactory);
        builder.build();

        verify(docDefinitionBuilder, Mockito.never()).addFile(Mockito.anyString());
        verify(docDefinitionBuilder, Mockito.never()).addContentFileName(Mockito.anyString());
    }

    @Test
    void should_not_add_initial_content_for_CONTRACT_type_if_contract_input_is_null() throws Exception {
        when(processBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);

        final SingleDocumentEngineDefinitionBuilder builder = new SingleDocumentEngineDefinitionBuilder(aDocument().withDocumentType(DocumentType.CONTRACT)
                .build(), processBuilder, scriptFactory);
        builder.build();

        verify(docDefinitionBuilder, never()).addInitialValue(notNull());
    }

    @Test
    void should_add_initial_content_as_expression_for_CONTRACT_type() throws Exception {
        when(processBuilder.addDocumentDefinition(anyString())).thenReturn(docDefinitionBuilder);
        when(scriptFactory.createSingleDocumentInitialContentScriptExpression(any(ContractInput.class))).thenReturn(
                aGroovyScriptExpression().withContent("script content").build());

        final ContractInputBuilder fileInput = aContractInput().withName("myFile").withType(ContractInputType.FILE);
        aContractInput().withName("parent").withType(ContractInputType.COMPLEX).havingInput(fileInput).build();
        final SingleDocumentEngineDefinitionBuilder builder = new SingleDocumentEngineDefinitionBuilder(aDocument().withDocumentType(DocumentType.CONTRACT)
                .havingContractInput(fileInput).build(), processBuilder, scriptFactory);
        builder.build();

        verify(docDefinitionBuilder).addInitialValue(notNull());
    }
}
