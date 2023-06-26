/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.bpm.model.util;

/**
 * Constants used in many places related to expressions
 * 
 * @author Romain Bioteau
 */
public interface ExpressionConstants {

    /*
     * constants in type attribute of expressions
     */
    String CONSTANT_TYPE = "TYPE_CONSTANT";

    String CONDITION_TYPE = "TYPE_CONDITION";

    String SCRIPT_TYPE = "TYPE_READ_ONLY_SCRIPT";

    String VARIABLE_TYPE = "TYPE_VARIABLE";

    String TRANSIENT_VARIABLE_TYPE = "TYPE_TRANSIENT_VARIABLE";

    String PARAMETER_TYPE = "TYPE_PARAMETER";

    String ENGINE_CONSTANT_TYPE = "TYPE_ENGINE_CONSTANT";

    String CONNECTOR_OUTPUT_TYPE = "CONNECTOR_OUTPUT_TYPE";

    String MESSAGE_ID_TYPE = "MESSAGE_ID_TYPE";

    String SEARCH_INDEX_TYPE = "SEARCH_INDEX_TYPE";

    String BUSINESS_DATA_TYPE = "TYPE_BUSINESS_DATA";

    String CONTRACT_INPUT_TYPE = "TYPE_CONTRACT_INPUT";

    String CONNECTOR_TYPE = "CONNECTOR_TYPE";

    String XPATH_TYPE = "TYPE_XPATH_READ";

    String JAVA_TYPE = "TYPE_JAVA_METHOD_CALL";
    String DOCUMENT_LIST_TYPE = "TYPE_DOCUMENT_LIST";

    String ASSIGNMENT_OPERATOR = "ASSIGNMENT";

    String JAVA_METHOD_OPERATOR = "JAVA_METHOD";

    String DELETION_OPERATOR = "DELETION";

    String XPATH_UPDATE_OPERATOR = "XPATH_UPDATE_QUERY";

    String GROOVY = "GROOVY";

    String DOCUMENT_TYPE = "TYPE_DOCUMENT";

    String PATTERN_TYPE = "TYPE_PATTERN";

    String SET_DOCUMENT_OPERATOR = "DOCUMENT_CREATE_UPDATE";
    String SET_LIST_DOCUMENT_OPERATOR = "DOCUMENT_LIST_SET";

    String VARIABLE_TYPE_FOR_FORM_OUPUT = "VARIABLE_TYPE_FOR_FORM_OUPUT";

    String DOCUMENT_VALUE_RETURN_TYPE = "org.bonitasoft.engine.bpm.document.DocumentValue";

    String DOCUMENT_REF_TYPE = "DOCUMENT_REF_TYPE";

    String ALL_TYPES = "ALL_TYPES";

    String MULTIINSTANCE_ITERATOR_TYPE = "MULTIINSTANCE_ITERATOR_TYPE";

    String QUERY_TYPE = "TYPE_QUERY_BUSINESS_DATA";

    String DAO_TYPE = "TYPE_BUSINESS_OBJECT_DAO";

    // left operand types:
    String LEFT_OPERAND_DATA = "DATA";
    String LEFT_OPERAND_SEARCH_INDEX = "SEARCH_INDEX";
    String LEFT_OPERAND_DOCUMENT = "DOCUMENT";
    String LEFT_OPERAND_DOCUMENT_LIST = "DOCUMENT_LIST";
    String LEFT_OPERAND_EXTERNAL_DATA = "EXTERNAL_DATA";
    String LEFT_OPERAND_TRANSIENT_DATA = "TRANSIENT_DATA";
    String LEFT_OPERAND_BUSINESS_DATA = "BUSINESS_DATA";

    /*
     * It is an assignment to a left operand that is a a business data (business data do not exists exists)
     */
    String CREATE_BUSINESS_DATA_OPERATOR = "CREATE_BUSINESS_DATA";

    /*
     * It is an java operation to a left operand that is a a business data
     */
    String BUSINESS_DATA_JAVA_SETTER_OPERATOR = "BUSINESS_DATA_JAVA_SETTER";

    /*
     * It is an assignment to a left operand that is a a business data (business data already exists)
     */
    String ATTACH_EXISTING_BUSINESS_DATA = "ATTACH_EXISTING_BUSINESS_DATA";

    String FORM_REFERENCE_TYPE = "FORM_REFERENCE_TYPE";

}
