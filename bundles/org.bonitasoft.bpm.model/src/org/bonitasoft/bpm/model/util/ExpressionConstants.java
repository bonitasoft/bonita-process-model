/**
 * Copyright (C) 2013 Bonitasoft S.A.
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
public final class ExpressionConstants {

	private ExpressionConstants() {
		// private constructor
	}

	/*
	 * constants in type attribute of expressions
	 */
	public static final String CONSTANT_TYPE = "TYPE_CONSTANT";
	public static final String CONDITION_TYPE = "TYPE_CONDITION";
	public static final String SCRIPT_TYPE = "TYPE_READ_ONLY_SCRIPT";
	public static final String VARIABLE_TYPE = "TYPE_VARIABLE";
	public static final String TRANSIENT_VARIABLE_TYPE = "TYPE_TRANSIENT_VARIABLE";
	public static final String PARAMETER_TYPE = "TYPE_PARAMETER";
	public static final String ENGINE_CONSTANT_TYPE = "TYPE_ENGINE_CONSTANT";
	public static final String CONNECTOR_OUTPUT_TYPE = "CONNECTOR_OUTPUT_TYPE";
	public static final String MESSAGE_ID_TYPE = "MESSAGE_ID_TYPE";
	public static final String SEARCH_INDEX_TYPE = "SEARCH_INDEX_TYPE";
	public static final String BUSINESS_DATA_TYPE = "TYPE_BUSINESS_DATA";
	public static final String CONTRACT_INPUT_TYPE = "TYPE_CONTRACT_INPUT";
	public static final String CONNECTOR_TYPE = "CONNECTOR_TYPE";
	public static final String XPATH_TYPE = "TYPE_XPATH_READ";
	public static final String JAVA_TYPE = "TYPE_JAVA_METHOD_CALL";
	public static final String DOCUMENT_LIST_TYPE = "TYPE_DOCUMENT_LIST";
	public static final String ASSIGNMENT_OPERATOR = "ASSIGNMENT";
	public static final String JAVA_METHOD_OPERATOR = "JAVA_METHOD";
	public static final String DELETION_OPERATOR = "DELETION";
	public static final String XPATH_UPDATE_OPERATOR = "XPATH_UPDATE_QUERY";
	public static final String GROOVY = "GROOVY";
	public static final String DOCUMENT_TYPE = "TYPE_DOCUMENT";
	public static final String PATTERN_TYPE = "TYPE_PATTERN";
	public static final String SET_DOCUMENT_OPERATOR = "DOCUMENT_CREATE_UPDATE";
	public static final String SET_LIST_DOCUMENT_OPERATOR = "DOCUMENT_LIST_SET";
	public static final String VARIABLE_TYPE_FOR_FORM_OUPUT = "VARIABLE_TYPE_FOR_FORM_OUPUT";
	public static final String DOCUMENT_VALUE_RETURN_TYPE = "org.bonitasoft.engine.bpm.document.DocumentValue";
	public static final String DOCUMENT_REF_TYPE = "DOCUMENT_REF_TYPE";
	public static final String ALL_TYPES = "ALL_TYPES";
	public static final String MULTIINSTANCE_ITERATOR_TYPE = "MULTIINSTANCE_ITERATOR_TYPE";
	public static final String QUERY_TYPE = "TYPE_QUERY_BUSINESS_DATA";
	public static final String DAO_TYPE = "TYPE_BUSINESS_OBJECT_DAO";

	// left operand types:
	public static final String LEFT_OPERAND_DATA = "DATA";
	public static final String LEFT_OPERAND_SEARCH_INDEX = "SEARCH_INDEX";
	public static final String LEFT_OPERAND_DOCUMENT = "DOCUMENT";
	public static final String LEFT_OPERAND_DOCUMENT_LIST = "DOCUMENT_LIST";
	public static final String LEFT_OPERAND_EXTERNAL_DATA = "EXTERNAL_DATA";
	public static final String LEFT_OPERAND_TRANSIENT_DATA = "TRANSIENT_DATA";
	public static final String LEFT_OPERAND_BUSINESS_DATA = "BUSINESS_DATA";

	/*
	 * It is an assignment to a left operand that is a a business data (business
	 * data do not exists exists)
	 */
	public static final String CREATE_BUSINESS_DATA_OPERATOR = "CREATE_BUSINESS_DATA";

	/*
	 * It is an java operation to a left operand that is a a business data
	 */
	public static final String BUSINESS_DATA_JAVA_SETTER_OPERATOR = "BUSINESS_DATA_JAVA_SETTER";

	/*
	 * It is an assignment to a left operand that is a a business data (business
	 * data already exists)
	 */
	public static final String ATTACH_EXISTING_BUSINESS_DATA = "ATTACH_EXISTING_BUSINESS_DATA";

	public static final String FORM_REFERENCE_TYPE = "FORM_REFERENCE_TYPE";

}
