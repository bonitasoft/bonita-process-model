/**
 * Copyright (C) 2023 Bonitasoft S.A.
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
package org.bonitasoft.bpm.model.process.util;

import static org.junit.Assert.assertEquals;

import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.bonitasoft.engine.expression.ExpressionInterpreter;
import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.engine.operation.LeftOperand;
import org.bonitasoft.engine.operation.OperatorType;
import org.junit.Test;

/**
 * Test that values in {@link ExpressionConstants} are still in sync with runtime definition
 * 
 * @author Vincent Hemery
 */
public class ExpressionConstantsTest {

    /**
     * Test that values in {@link ExpressionConstants} are still in sync with runtime definition
     * 
     * @throws Exception
     */
    @Test
    public void testExpressionConstantsAreSyncWithRuntime() throws Exception {
        assertEquals(ExpressionType.TYPE_CONSTANT.name(), ExpressionConstants.CONSTANT_TYPE);
        assertEquals(ExpressionType.TYPE_CONDITION.name(), ExpressionConstants.CONDITION_TYPE);
        assertEquals(ExpressionType.TYPE_READ_ONLY_SCRIPT.name(), ExpressionConstants.SCRIPT_TYPE);
        assertEquals(ExpressionType.TYPE_VARIABLE.name(), ExpressionConstants.VARIABLE_TYPE);
        assertEquals(ExpressionType.TYPE_TRANSIENT_VARIABLE.name(), ExpressionConstants.TRANSIENT_VARIABLE_TYPE);
        assertEquals(ExpressionType.TYPE_PARAMETER.name(), ExpressionConstants.PARAMETER_TYPE);
        assertEquals(ExpressionType.TYPE_ENGINE_CONSTANT.name(), ExpressionConstants.ENGINE_CONSTANT_TYPE);
        assertEquals(ExpressionType.TYPE_BUSINESS_DATA.name(), ExpressionConstants.BUSINESS_DATA_TYPE);
        assertEquals(ExpressionType.TYPE_CONTRACT_INPUT.name(), ExpressionConstants.CONTRACT_INPUT_TYPE);
        assertEquals(ExpressionType.TYPE_XPATH_READ.name(), ExpressionConstants.XPATH_TYPE);
        assertEquals(ExpressionType.TYPE_JAVA_METHOD_CALL.name(), ExpressionConstants.JAVA_TYPE);
        assertEquals(ExpressionType.TYPE_DOCUMENT.name(), ExpressionConstants.DOCUMENT_TYPE);
        assertEquals(ExpressionType.TYPE_DOCUMENT_LIST.name(), ExpressionConstants.DOCUMENT_LIST_TYPE);
        assertEquals(ExpressionType.TYPE_PATTERN.name(), ExpressionConstants.PATTERN_TYPE);
        assertEquals(ExpressionType.TYPE_QUERY_BUSINESS_DATA.name(), ExpressionConstants.QUERY_TYPE);
        assertEquals(ExpressionType.TYPE_BUSINESS_OBJECT_DAO.name(), ExpressionConstants.DAO_TYPE);
        assertEquals(OperatorType.ASSIGNMENT.name(), ExpressionConstants.ASSIGNMENT_OPERATOR);
        assertEquals(OperatorType.JAVA_METHOD.name(), ExpressionConstants.JAVA_METHOD_OPERATOR);
        assertEquals(OperatorType.DELETION.name(), ExpressionConstants.DELETION_OPERATOR);
        assertEquals(OperatorType.XPATH_UPDATE_QUERY.name(), ExpressionConstants.XPATH_UPDATE_OPERATOR);
        assertEquals(ExpressionInterpreter.GROOVY.name(), ExpressionConstants.GROOVY);
        assertEquals(LeftOperand.TYPE_DATA, ExpressionConstants.LEFT_OPERAND_DATA);
        assertEquals(LeftOperand.TYPE_SEARCH_INDEX, ExpressionConstants.LEFT_OPERAND_SEARCH_INDEX);
        assertEquals(LeftOperand.TYPE_DOCUMENT, ExpressionConstants.LEFT_OPERAND_DOCUMENT);
        assertEquals(LeftOperand.TYPE_DOCUMENT_LIST, ExpressionConstants.LEFT_OPERAND_DOCUMENT_LIST);
        assertEquals(LeftOperand.TYPE_EXTERNAL_DATA, ExpressionConstants.LEFT_OPERAND_EXTERNAL_DATA);
        assertEquals(LeftOperand.TYPE_TRANSIENT_DATA, ExpressionConstants.LEFT_OPERAND_TRANSIENT_DATA);
        assertEquals(LeftOperand.TYPE_BUSINESS_DATA, ExpressionConstants.LEFT_OPERAND_BUSINESS_DATA);
    }
}
