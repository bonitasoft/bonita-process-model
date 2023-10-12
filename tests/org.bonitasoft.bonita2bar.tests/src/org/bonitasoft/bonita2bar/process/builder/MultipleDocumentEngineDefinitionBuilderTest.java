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

import java.util.Collection;

import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.expression.ExpressionFactory;
import org.bonitasoft.bpm.model.process.ContractInput;
import org.bonitasoft.bpm.model.process.Document;
import org.bonitasoft.bpm.model.process.DocumentType;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.bonitasoft.engine.bpm.process.impl.DocumentListDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MultipleDocumentEngineDefinitionBuilderTest {

    @Mock
    private ProcessDefinitionBuilder processBuilder;
    @Mock
    public DocumentListDefinitionBuilder docDefinitionListBuilder;
    @Mock
    private DocumentGroovyScriptExpressionFactory scriptFactory;

    @Test
    void testIntialContentForMultipleDocumentAdded() throws Exception {
        when(processBuilder.addDocumentListDefinition(anyString())).thenReturn(docDefinitionListBuilder);

        final Pool process = ProcessFactory.eINSTANCE.createPool();
        final Document document = createBasicDocument();
        document.setMultiple(true);
        final Expression initialExpression = createEmptyListGroovyScriptExpression();
        document.setInitialMultipleContent(initialExpression);
        document.setDocumentType(DocumentType.INTERNAL);
        process.getDocuments().add(document);

        final MultipleDocumentEngineDefinitionBuilder builder = new MultipleDocumentEngineDefinitionBuilder(document, processBuilder, scriptFactory);

        builder.build();

        verify(docDefinitionListBuilder).addInitialValue(Mockito.<org.bonitasoft.engine.expression.Expression> any());
    }

    @Test
    void should_not_add_initial_content_if_initial_expression_is_null_or_empty() throws Exception {
        when(processBuilder.addDocumentListDefinition(anyString())).thenReturn(docDefinitionListBuilder);

        final MultipleDocumentEngineDefinitionBuilder builder = new MultipleDocumentEngineDefinitionBuilder(aDocument().multiple()
                .withDocumentType(DocumentType.NONE).havingInitialMultipleContent(anExpression())
                .build(), processBuilder, scriptFactory);
        builder.build();

        verify(docDefinitionListBuilder, never()).addInitialValue(notNull());
    }

    @Test
    void should_not_add_initial_content_for_CONTRACT_type_if_contract_input_is_null() throws Exception {
        when(processBuilder.addDocumentListDefinition(anyString())).thenReturn(docDefinitionListBuilder);

        final MultipleDocumentEngineDefinitionBuilder builder = new MultipleDocumentEngineDefinitionBuilder(aDocument().multiple()
                .withDocumentType(DocumentType.CONTRACT)
                .build(), processBuilder, scriptFactory);
        builder.build();

        verify(docDefinitionListBuilder, never()).addInitialValue(notNull());
    }

    @Test
    void should_add_initial_content_for_CONTRACT_type_if_contract_input_is_not_null() throws Exception {
        when(processBuilder.addDocumentListDefinition(anyString())).thenReturn(docDefinitionListBuilder);
        when(scriptFactory.createMultipleDocumentInitialContentScriptExpression(any(ContractInput.class))).thenReturn(
                aGroovyScriptExpression().withContent("script content").build());
        final MultipleDocumentEngineDefinitionBuilder builder = new MultipleDocumentEngineDefinitionBuilder(aDocument().multiple()
                .withDocumentType(DocumentType.CONTRACT).havingContractInput(aContractInput().withName("input"))
                .build(), processBuilder, scriptFactory);
        builder.build();

        verify(docDefinitionListBuilder).addInitialValue(notNull());
    }

    @Test
    void should_add_initial_content_for_NONE_type_if_contract_input_is_not_null() throws Exception {
        when(processBuilder.addDocumentListDefinition(anyString())).thenReturn(docDefinitionListBuilder);
        final MultipleDocumentEngineDefinitionBuilder builder = new MultipleDocumentEngineDefinitionBuilder(aDocument().multiple()
                .withDocumentType(DocumentType.NONE).build(), processBuilder, scriptFactory);
        builder.build();

        verify(docDefinitionListBuilder, never()).addInitialValue(notNull());
    }

    private static Document createBasicDocument() {
        return aDocument().withName("testDocument").build();
    }

    private static Expression createEmptyListGroovyScriptExpression() {
        final Expression expression = ExpressionFactory.eINSTANCE.createExpression();
        expression.setType(ExpressionConstants.SCRIPT_TYPE);
        expression.setInterpreter(ExpressionConstants.GROOVY);
        expression.setReturnType(Collection.class.getName());
        expression.setName("Empty list");
        expression.setContent("[]");
        return expression;
    }

}
