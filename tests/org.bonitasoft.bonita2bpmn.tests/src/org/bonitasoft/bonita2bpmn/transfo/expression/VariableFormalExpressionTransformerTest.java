/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.bonita2bpmn.transfo.expression;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.bpm.model.expression.builders.OperationBuilder.anOperation;
import static org.bonitasoft.bpm.model.process.builders.ActivityBuilder.anActivity;
import static org.bonitasoft.bpm.model.process.builders.DataBuilder.aData;
import static org.bonitasoft.bpm.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.bpm.model.process.builders.StringDataTypeBuilder.aStringDataType;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.bonitasoft.bonita2bpmn.transfo.data.DataScope;
import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.process.Data;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.bonitasoft.bpm.model.util.IModelSearch;
import org.bonitasoft.bpm.model.util.ModelSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.omg.spec.bpmn.model.ModelFactory;
import org.omg.spec.bpmn.model.TFormalExpression;
import org.omg.spec.bpmn.model.TItemDefinition;

@ExtendWith(MockitoExtension.class)
class VariableFormalExpressionTransformerTest {

    @Mock
    private DataScope dataScope;

    private VariableFormalExpressionTransformer variableFormalExpressionTransformer;

    private IModelSearch modelSearch = new ModelSearch(Collections::emptyList);

    @BeforeEach
    void setUp() throws Exception {
        variableFormalExpressionTransformer = new VariableFormalExpressionTransformer(dataScope, modelSearch);
    }

    @Test
    void should_transform_throw_an_IllegalArgumentException_if_expressionType_is_invalid() throws Exception {
        var expression = anExpression().withExpressionType(ExpressionConstants.PARAMETER_TYPE).build();

        assertThrows(IllegalArgumentException.class, () -> variableFormalExpressionTransformer
                .apply(expression));
    }

    @Test
    void should_transform_throw_an_IllegalArgumentException_for_missing_referenced_element() throws Exception {
        var expression = anExpression().withExpressionType(ExpressionConstants.VARIABLE_TYPE).build();

        assertThrows(IllegalArgumentException.class, () -> variableFormalExpressionTransformer.apply(expression));
    }

    @Test
    void should_transform_a_variable_expression_into_TFormalExpression() throws Exception {
        final Data data = aData().withName("name").build();
        final Expression expression = anExpression()
                .withExpressionType(ExpressionConstants.VARIABLE_TYPE)
                .withContent("name")
                .withReturnType(String.class.getName())
                .havingReferencedElements(data).build();

        final Pool pool = aPool()
                .havingData(aData().havingDataType(aStringDataType()).withName("name"))
                .havingElements(anActivity()
                        .havingOperations(anOperation().havingRightOperand(expression)))
                .build();

        final TItemDefinition itemDefValue = ModelFactory.eINSTANCE.createTItemDefinition();
        itemDefValue.setId("name");
        when(dataScope.get(pool.getData().get(0))).thenReturn(itemDefValue);

        final TFormalExpression tFormalExpression = variableFormalExpressionTransformer.apply(expression);

        assertThat(tFormalExpression).isNotNull();
        assertThat(tFormalExpression.getId()).isNotNull();
        assertThat(tFormalExpression.getLanguage()).isEqualTo(FormalExpressionFunction.XPATH_LANGUAGE_NS);
        assertThat(tFormalExpression.getMixed().get(0).getValue()).isEqualTo("getDataObject('name')");
    }

    @Test
    void should_transform_a_variable_expression_into_TFormalExpression_for_transient_data() throws Exception {
        final Data data = aData().withName("name").isTransient().build();
        final Expression expression = anExpression()
                .withExpressionType(ExpressionConstants.VARIABLE_TYPE)
                .withContent("name")
                .withReturnType(String.class.getName())
                .havingReferencedElements(data).build();

        final Pool pool = aPool()
                .withName("process")
                .havingData(aData().havingDataType(aStringDataType()).withName("name").isTransient())
                .havingElements(anActivity()
                        .havingOperations(anOperation().havingRightOperand(expression)))
                .build();

        final TItemDefinition itemDefValue = ModelFactory.eINSTANCE.createTItemDefinition();
        itemDefValue.setId("name");
        when(dataScope.get(pool.getData().get(0))).thenReturn(itemDefValue);

        final TFormalExpression tFormalExpression = variableFormalExpressionTransformer.apply(expression);

        assertThat(tFormalExpression).isNotNull();
        assertThat(tFormalExpression.getId()).isNotNull();
        assertThat(tFormalExpression.getLanguage()).isEqualTo(FormalExpressionFunction.XPATH_LANGUAGE_NS);
        assertThat(tFormalExpression.getMixed().get(0).getValue()).isEqualTo("getActivityProperty('process','name')");
    }
}
