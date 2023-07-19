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
import static org.bonitasoft.bpm.model.parameter.builders.ParameterBuilder.aParameter;
import static org.bonitasoft.bpm.model.process.builders.ActorBuilder.anActor;
import static org.bonitasoft.bpm.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.bpm.model.process.builders.DocumentBuilder.aDocument;
import static org.bonitasoft.bpm.model.process.builders.PoolBuilder.aPool;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Collections;

import org.bonitasoft.bpm.model.process.Contract;
import org.bonitasoft.bpm.model.process.Document;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.builders.BusinessObjectDataBuilder;
import org.bonitasoft.bpm.model.process.builders.PoolBuilder;
import org.bonitasoft.bpm.model.util.ModelSearch;
import org.bonitasoft.engine.bpm.process.impl.ActorDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.BusinessDataDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ParameterDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.engine.expression.ExpressionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EngineProcessBuilderTest {

    private EngineProcessBuilder engineProcessBuilder;
    @Mock
    private ProcessDefinitionBuilder processDefBuilder;
    @Mock
    private BusinessDataDefinitionBuilder bDataBuilder;
    @Mock
    private ProcessContractEngineBuilder processContractBuilder;
    @Mock
    private DocumentEngineDefinitionBuilder documentBuilder;

    @BeforeEach
    void setup() {
        engineProcessBuilder = spy(
                new EngineProcessBuilder(processDefBuilder, processContractBuilder, documentBuilder,
                        new ModelSearch(Collections::emptyList)));
    }

    @Test
    void testAddBusinessDataInContext() {
        doReturn(bDataBuilder).when(processDefBuilder).addBusinessData(any(), any(), any());
        final Pool pool = PoolBuilder.aPool()
                .havingData(BusinessObjectDataBuilder.aBusinessData().withName("myBData").withClassname("my.classname"))
                .build();
        engineProcessBuilder.casePool(pool);

        final ArgumentCaptor<Expression> argument = ArgumentCaptor.forClass(Expression.class);
        verify(processDefBuilder).addContextEntry(eq("myBData_ref"), argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("myBData");
        assertThat(argument.getValue().getExpressionType())
                .isEqualTo(ExpressionType.TYPE_BUSINESS_DATA_REFERENCE.name());
    }

    @Test
    void testAddSimpleDocumentInContext() throws Exception {
        final Pool pool = PoolBuilder.aPool()
                .havingDocuments(aDocument().withName("myDoc"))
                .build();
        engineProcessBuilder.casePool(pool);

        final ArgumentCaptor<Expression> argument = ArgumentCaptor.forClass(Expression.class);
        verify(processDefBuilder).addContextEntry(eq("myDoc_ref"), argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("myDoc");
        assertThat(argument.getValue().getExpressionType()).isEqualTo(ExpressionType.TYPE_DOCUMENT.name());
    }

    @Test
    void testAddSMultipleDocumentInContext() throws Exception {
        final Pool pool = PoolBuilder.aPool()
                .havingDocuments(aDocument().withName("myDoc").multiple())
                .build();
        engineProcessBuilder.casePool(pool);

        final ArgumentCaptor<Expression> argument = ArgumentCaptor.forClass(Expression.class);
        verify(processDefBuilder).addContextEntry(eq("myDoc_ref"), argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("myDoc");
        assertThat(argument.getValue().getExpressionType()).isEqualTo(ExpressionType.TYPE_DOCUMENT_LIST.name());
    }

    @Test
    void testAddBusinessDataAndDocumentInContext() throws Exception {
        final Pool pool = PoolBuilder.aPool()
                .havingData(BusinessObjectDataBuilder.aBusinessData().withName("myBData").withClassname("my.classname"))
                .havingDocuments(aDocument().withName("myDoc"))
                .build();
        doReturn(bDataBuilder).when(processDefBuilder).addBusinessData(any(), any(), any());
        engineProcessBuilder.casePool(pool);

        final ArgumentCaptor<Expression> argument = ArgumentCaptor.forClass(Expression.class);
        verify(processDefBuilder).addContextEntry(eq("myBData_ref"), argument.capture());
        assertThat(argument.getValue().getName()).isEqualTo("myBData");
        assertThat(argument.getValue().getExpressionType())
                .isEqualTo(ExpressionType.TYPE_BUSINESS_DATA_REFERENCE.name());

        final ArgumentCaptor<Expression> argumentDoc = ArgumentCaptor.forClass(Expression.class);
        verify(processDefBuilder).addContextEntry(eq("myDoc_ref"), argumentDoc.capture());
        assertThat(argumentDoc.getValue().getName()).isEqualTo("myDoc");
        assertThat(argumentDoc.getValue().getExpressionType()).isEqualTo(ExpressionType.TYPE_DOCUMENT.name());
    }

    @Test
    void should_add_process_actors() throws Exception {
        final Pool pool = aPool()
                .havingActors(anActor().withName("employee").withDocumentation("an employee actor").initiator())
                .build();
        final ActorDefinitionBuilder actorDefinitionBuilder = mock(ActorDefinitionBuilder.class);
        doReturn(actorDefinitionBuilder).when(processDefBuilder).addActor(any(), anyBoolean());

        engineProcessBuilder.casePool(pool);

        verify(processDefBuilder).addActor("employee", true);
        verify(actorDefinitionBuilder).addDescription("an employee actor");
    }

    @Test
    void should_add_process_parameters() throws Exception {
        final Pool pool = aPool()
                .havingParameters(aParameter().withName("myParam").withDescription("a parameter example")
                        .withType(String.class.getName()))
                .build();
        final ParameterDefinitionBuilder parameterDefinitionBuilder = mock(ParameterDefinitionBuilder.class);
        doReturn(parameterDefinitionBuilder).when(processDefBuilder).addParameter(any(), any());

        engineProcessBuilder.casePool(pool);

        verify(processDefBuilder).addParameter("myParam", String.class.getName());
        verify(parameterDefinitionBuilder).addDescription("a parameter example");
    }

    @Test
    void should_add_process_documents() throws Exception {
        final Document myDocument = aDocument().withName("myDoc").build();
        final Pool pool = aPool()
                .havingDocuments(myDocument)
                .build();

        engineProcessBuilder.casePool(pool);

        verify(documentBuilder).build(myDocument);
    }

    @Test
    void should_add_process_contract() throws Exception {
        final Contract myContract = aContract().build();
        final Pool pool = aPool()
                .havingContract(myContract)
                .build();

        engineProcessBuilder.casePool(pool);

        verify(processContractBuilder).build(myContract);
    }

    @Test
    void should_set_display_name() throws Exception {
        final Pool pool = aPool()
                .withDisplayName("my process")
                .build();

        engineProcessBuilder.casePool(pool);

        verify(processDefBuilder).addDisplayName("my process");
    }

}
