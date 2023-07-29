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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.spec.bpmn.model.TFormalExpression;

class FormalExpressionFunctionTest {

    private FormalExpressionFunction formalExpressionTransformer;

    @BeforeEach
    void setup() throws Exception {
        formalExpressionTransformer = new FormalExpressionFunction();
    }

    @Test
    void should_transform_throw_a_NullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> formalExpressionTransformer.apply(null));
    }

    @Test
    void should_transform_a_expression_into_a_TFormalExpression() throws Exception {
        final TFormalExpression formalExpression = formalExpressionTransformer.apply(
                anExpression()
                        .withContent("some content")
                        .withReturnType(String.class.getName())
                        .build());

        assertThat(formalExpression).isNotNull();
        assertThat(formalExpression.getId()).isNotEmpty();
        assertThat(formalExpression.getEvaluatesToTypeRef()).isEqualTo(QName.valueOf("java:java.lang.String"));
        assertThat(formalExpression.getLanguage()).isEqualTo(FormalExpressionFunction.XPATH_LANGUAGE_NS);
        assertThat(formalExpression.getMixed().getValue(0)).isEqualTo("some content");
    }

}
