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

import java.util.function.Predicate;

import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.omg.spec.bpmn.model.TFormalExpression;

/**
 * @author Romain Bioteau
 */
public class ScriptFormalExpressionFunction extends FormalExpressionFunction {

    /**
     * The expected and only supported expression type.
     */
    private static final String EXPRESSION_TYPE = ExpressionConstants.SCRIPT_TYPE;

    /** Test a String expression type value against the supported type */
    public static final Predicate<String> IS_SUPPORTED_TYPE = EXPRESSION_TYPE::equals;

    @Override
    public TFormalExpression apply(final Expression bonitaExpression) {
        if (!IS_SUPPORTED_TYPE.test(bonitaExpression.getType())) {
            throw new IllegalArgumentException(String.format("Expression type is invalid. Expected %s but was %s",
                    EXPRESSION_TYPE, bonitaExpression.getType()));
        }

        final TFormalExpression formalExpression = super.apply(bonitaExpression);
        if (!ExpressionConstants.GROOVY.equals(bonitaExpression.getInterpreter())) {
            formalExpression.setLanguage(bonitaExpression.getInterpreter());//it is another Interpreter, doesn't exist yet
        }

        return formalExpression;
    }

    @Override
    protected String getFormalExpressionLanguage() {
        //null for default : GROOVY
        return null;
    }

}
