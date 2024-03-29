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
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.xml.namespace.QName;

import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.spec.bpmn.model.TFormalExpression;

class ScriptFormalExpressionFunctionTest {

    private ScriptFormalExpressionFunction scriptFormalExpressionTransformer;

    @BeforeEach
    void setUp() throws Exception {
        scriptFormalExpressionTransformer = new ScriptFormalExpressionFunction();
    }

    @Test
    void should_transform_throw_an_IllegalArgumentException_if_invalid_expression_type() throws Exception {
        var expression = anExpression().build();

        assertThrows(IllegalArgumentException.class, () -> scriptFormalExpressionTransformer.apply(expression));
    }

    @Test
    void should_transform_a_script_expression_into_a_TFormalExpression() throws Exception {
        final TFormalExpression tFormalExpression = scriptFormalExpressionTransformer.apply(anExpression()
                .withExpressionType(ExpressionConstants.SCRIPT_TYPE).withInterpreter("Scala")
                .withContent("1 > 2").withReturnType(Boolean.class.getName()).build());

        assertThat(tFormalExpression).isNotNull();
        assertThat(tFormalExpression.getId()).isNotNull();
        assertThat(tFormalExpression.getEvaluatesToTypeRef()).isEqualTo(QName.valueOf("java:java.lang.Boolean"));
        assertThat(tFormalExpression.getLanguage()).isEqualTo("Scala");
        assertThat(tFormalExpression.getMixed().getValue(0)).isEqualTo("1 > 2");
    }

    @Test
    void should_transform_a_script_expression_into_a_TFormalExpression_with_null_language_for_Groovy_interpreter()
            throws Exception {
        final TFormalExpression tFormalExpression = scriptFormalExpressionTransformer.apply(anExpression()
                .withExpressionType(ExpressionConstants.SCRIPT_TYPE).withInterpreter(ExpressionConstants.GROOVY)
                .withContent("1 > 2").withReturnType(Boolean.class.getName()).build());

        assertThat(tFormalExpression.getLanguage()).isNull();
    }
}
