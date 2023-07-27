/**
 * Copyright (C) 2012-2015 Bonitasoft S.A.
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
package org.bonitasoft.bonita2bar.process.expression;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.bpm.connector.model.definition.Output;
import org.bonitasoft.bpm.model.expression.ListExpression;
import org.bonitasoft.bpm.model.expression.Operation;
import org.bonitasoft.bpm.model.expression.TableExpression;
import org.bonitasoft.bpm.model.parameter.Parameter;
import org.bonitasoft.bpm.model.process.BusinessObjectData;
import org.bonitasoft.bpm.model.process.ContractInput;
import org.bonitasoft.bpm.model.process.Data;
import org.bonitasoft.bpm.model.process.Document;
import org.bonitasoft.bpm.model.process.util.DataUtil;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.engine.expression.ExpressionBuilder;
import org.bonitasoft.engine.expression.ExpressionType;
import org.bonitasoft.engine.expression.InvalidExpressionException;
import org.bonitasoft.engine.operation.LeftOperand;
import org.bonitasoft.engine.operation.LeftOperandBuilder;
import org.bonitasoft.engine.operation.OperationBuilder;
import org.bonitasoft.engine.operation.OperatorType;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class EngineExpressionUtil {

    public static final String PAGEFLOW_DATASOURCE = "PAGEFLOW";

    private static List<IExpressionConverter> converters;

    private EngineExpressionUtil() {
        // private constructor
    }

    public static org.bonitasoft.engine.operation.Operation createOperation(final Operation operation)
            throws InvalidExpressionException {
        return createOperation(operation, createLeftOperand(operation.getLeftOperand()));
    }

    public static void addConverter(IExpressionConverter converter) {
        if (converters == null) {
            converters = new ArrayList<>();
        }
        converters.add(converter);
    }

    public static boolean hasConverter(Class<?> converterType) {
        if (converters == null) {
            return false;
        }
        return converters.stream().anyMatch(converterType::isInstance);
    }

    public static org.bonitasoft.engine.operation.Operation createOperation(final Operation operation,
            final LeftOperand leftOperand) throws InvalidExpressionException {
        final OperationBuilder builder = new OperationBuilder();
        builder.createNewInstance();
        builder.setType(getEngineOperator(operation));
        builder.setOperator(operation.getOperator().getExpression());
        final EList<String> operatorInputTypes = operation.getOperator().getInputTypes();
        if (!operatorInputTypes.isEmpty()) {
            builder.setOperatorInputType(operatorInputTypes.get(0));
        }
        builder.setRightOperand(createExpression(operation.getRightOperand()));
        builder.setLeftOperand(leftOperand);
        return builder.done();
    }

    public static OperatorType getEngineOperator(final Operation operation) {
        final String type = operation.getOperator().getType();
        return getEngineOperator(type);
    }

    /**
     * @param type in the Studio
     * @return Engine OperatorType
     */
    @SuppressWarnings("deprecation")
    public static OperatorType getEngineOperator(final String type) {
        // it's the left operand that tell if it's a document to set
        if (OperatorType.DOCUMENT_CREATE_UPDATE.name().equals(type)) {
            return OperatorType.ASSIGNMENT;
        }
        if (ExpressionConstants.SET_LIST_DOCUMENT_OPERATOR.equals(type)) {
            return OperatorType.ASSIGNMENT;
        }
        // it's the left operand that tell if it's a string index to set
        if (OperatorType.STRING_INDEX.name().equals(type)) {
            return OperatorType.ASSIGNMENT;
        }
        // it's the left operand that tell if it's a string index to set
        if (ExpressionConstants.CREATE_BUSINESS_DATA_OPERATOR.equals(type)) {
            return OperatorType.ASSIGNMENT;
        }
        if (ExpressionConstants.BUSINESS_DATA_JAVA_SETTER_OPERATOR.equals(type)) {
            return OperatorType.JAVA_METHOD;
        }
        if (ExpressionConstants.ATTACH_EXISTING_BUSINESS_DATA.equals(type)) {
            return OperatorType.ASSIGNMENT;
        }
        return OperatorType.valueOf(type);
    }

    public static org.bonitasoft.engine.operation.Operation createOperation(final Operation operation,
            final boolean isExternal) throws InvalidExpressionException {
        return createOperation(operation, createLeftOperand(operation.getLeftOperand(), isExternal));
    }

    public static String getOperatorType(final Operation operation) {
        if (ExpressionConstants.JAVA_METHOD_OPERATOR.equals(operation.getOperator().getType())
                && operation.getLeftOperand() != null && !operation.getLeftOperand().getReferencedElements().isEmpty()
                && operation.getLeftOperand().getReferencedElements().get(0) instanceof BusinessObjectData) {
            return ExpressionConstants.BUSINESS_DATA_JAVA_SETTER_OPERATOR;
        }
        if (ExpressionConstants.ASSIGNMENT_OPERATOR.equals(operation.getOperator().getType())
                && operation.getLeftOperand() != null && !operation.getLeftOperand().getReferencedElements().isEmpty()
                && operation.getLeftOperand().getReferencedElements().get(0) instanceof BusinessObjectData) {
            return ExpressionConstants.ATTACH_EXISTING_BUSINESS_DATA;
        }
        return operation.getOperator().getType();
    }

    public static org.bonitasoft.engine.operation.Operation createOperationForMessageContent(final Operation operation)
            throws InvalidExpressionException {
        final OperationBuilder builder = new OperationBuilder();
        builder.createNewInstance();
        builder.setType(getEngineOperator(operation));
        builder.setOperator(operation.getOperator().getExpression());
        final EList<String> operatorInputTypes = operation.getOperator().getInputTypes();
        if (!operatorInputTypes.isEmpty()) {
            builder.setOperatorInputType(operatorInputTypes.get(0));
        }
        final org.bonitasoft.bpm.model.expression.Expression rightOperand = EcoreUtil.copy(operation.getRightOperand());
        rightOperand.setType(ExpressionConstants.VARIABLE_TYPE);
        if (!operatorInputTypes.isEmpty()) {
            rightOperand.setReturnType(operatorInputTypes.get(0));
        } else {
            rightOperand.setReturnType(operation.getLeftOperand().getReturnType());// use return type of the left
                                                                                   // operand
        }

        builder.setRightOperand(createExpression(rightOperand));
        builder.setLeftOperand(createLeftOperand(operation.getLeftOperand()));
        return builder.done();
    }

    private static List<Expression> createDependenciesList(
            final org.bonitasoft.bpm.model.expression.Expression expression) throws InvalidExpressionException {
        final List<Expression> result = new ArrayList<>();
        if (expression.getType().equals(ExpressionConstants.SCRIPT_TYPE)
                || expression.getType().equals(ExpressionConstants.PATTERN_TYPE)
                || expression.getType().equals(ExpressionConstants.JAVA_TYPE)
                || expression.getType().equals(ExpressionConstants.XPATH_TYPE)) {
            final ExpressionBuilder expBuilder = new ExpressionBuilder();
            for (final EObject element : expression.getReferencedElements()) {
                addDependencyExpression(result, expBuilder, element);
            }
        }

        return result;
    }

    private static void addDependencyExpression(final List<Expression> result, final ExpressionBuilder expBuilder,
            final EObject element) throws InvalidExpressionException {
        if (element instanceof Data) {
            result.add(createVariableExpression((Data) element));
        } else if (element instanceof Output) {
            result.add(createConnectorOutputExpression((Output) element));
        } else if (element instanceof Parameter) {
            result.add(createParameterExpression((Parameter) element));
        } else if (element instanceof org.bonitasoft.bpm.model.expression.Expression) {
            result.add(createExpression((org.bonitasoft.bpm.model.expression.Expression) element));
        } else if (element instanceof Document) {
            if (((Document) element).isMultiple()) {
                result.add(expBuilder.createDocumentListExpression(((Document) element).getName()));
            } else {
                result.add(expBuilder.createDocumentReferenceExpression(((Document) element).getName()));
            }
        } else if (element instanceof ContractInput) {
            result.add(expBuilder.createContractInputExpression(((ContractInput) element).getName(),
                    getContractInputReturnType((ContractInput) element)));
        }
    }

    private static String getContractInputReturnType(final ContractInput input) {
        if (input == null) {
            return null;
        }
        String returnType = input.getJavaType();
        if (input.isMultiple()) {
            returnType = List.class.getName();
        }
        return returnType;
    }

    private static Expression createParameterExpression(final Parameter parameter) throws InvalidExpressionException {
        final ExpressionBuilder exp = new ExpressionBuilder();
        exp.createNewInstance(parameter.getName());
        exp.setContent(parameter.getName());
        exp.setExpressionType(ExpressionType.TYPE_PARAMETER);
        exp.setReturnType(parameter.getTypeClassname());
        return exp.done();
    }

    public static LeftOperand createLeftOperand(final org.bonitasoft.bpm.model.expression.Expression leftOperand) {
        return createLeftOperand(leftOperand, false);
    }

    public static LeftOperand createLeftOperand(final org.bonitasoft.bpm.model.expression.Expression leftOperand,
            final boolean isExternal) {
        final LeftOperandBuilder builder = new LeftOperandBuilder();
        builder.createNewInstance();
        builder.setName(leftOperand.getName());
        builder.setType(getLeftOperandType(leftOperand, isExternal));
        return builder.done();
    }

    public static String getLeftOperandType(final org.bonitasoft.bpm.model.expression.Expression leftOperand,
            final boolean external) {
        if (external) {
            return ExpressionConstants.LEFT_OPERAND_EXTERNAL_DATA;
        }
        final String leftOperandType = leftOperand.getType();
        if (ExpressionConstants.DOCUMENT_TYPE.equals(leftOperandType)
                || ExpressionConstants.DOCUMENT_REF_TYPE.equals(leftOperandType)) {
            return getLeftOperandDocumentType(leftOperand);
        }
        if (ExpressionConstants.VARIABLE_TYPE.equals(leftOperandType)) {
            return getLeftOperandVariableType(leftOperand, external);
        }
        if (ExpressionConstants.SEARCH_INDEX_TYPE.equals(leftOperandType)) {
            return ExpressionConstants.LEFT_OPERAND_SEARCH_INDEX;
        }
        return leftOperand.getType();
    }

    private static String getLeftOperandDocumentType(final org.bonitasoft.bpm.model.expression.Expression leftOperand) {
        if (!leftOperand.getReferencedElements().isEmpty()) {
            final EObject referencedElement = leftOperand.getReferencedElements().get(0);
            if (referencedElement instanceof Document) {
                if (((Document) referencedElement).isMultiple()) {
                    return ExpressionConstants.LEFT_OPERAND_DOCUMENT_LIST;
                } else {
                    return ExpressionConstants.LEFT_OPERAND_DOCUMENT;
                }
            }
        }
        return ExpressionConstants.LEFT_OPERAND_DOCUMENT;
    }

    private static String getLeftOperandVariableType(final org.bonitasoft.bpm.model.expression.Expression leftOperand,
            final boolean external) {
        final EList<EObject> referencedElements = leftOperand.getReferencedElements();
        if (referencedElements != null && !referencedElements.isEmpty()) {
            final EObject referencedElement = referencedElements.get(0);
            if (referencedElement instanceof Data) {
                final Data data = (Data) referencedElement;
                if (data.isTransient()) {
                    return ExpressionConstants.LEFT_OPERAND_TRANSIENT_DATA;
                } else if (data instanceof BusinessObjectData) {
                    return ExpressionConstants.LEFT_OPERAND_BUSINESS_DATA;
                } else if (external || PAGEFLOW_DATASOURCE.equals(data.getDatasourceId())) {
                    return ExpressionConstants.LEFT_OPERAND_EXTERNAL_DATA;
                }
            }
        }
        return ExpressionConstants.LEFT_OPERAND_DATA;
    }

    public static LeftOperand createLeftOperandIndex(final int i) {
        final LeftOperandBuilder builder = new LeftOperandBuilder();
        builder.createNewInstance();
        builder.setName(String.valueOf(i));
        builder.setType(ExpressionConstants.LEFT_OPERAND_SEARCH_INDEX);
        return builder.done();
    }

    public static Expression createExpression(final org.bonitasoft.bpm.model.expression.AbstractExpression expression)
            throws InvalidExpressionException {
        if (expression == null) {
            return null;
        }
        if (expression instanceof org.bonitasoft.bpm.model.expression.Expression) {
            return buildSimpleEngineExpressionWithName(
                    ((org.bonitasoft.bpm.model.expression.Expression) expression).getName(),
                    (org.bonitasoft.bpm.model.expression.Expression) expression);
        } else if (expression instanceof ListExpression) {
            return buildListEngineExpression(expression);
        } else if (expression instanceof TableExpression) {
            return buildTableEngineExpression(expression);
        }
        throw new IllegalArgumentException("Unsupported expression convertion");
    }

    protected static Expression buildTableEngineExpression(
            final org.bonitasoft.bpm.model.expression.AbstractExpression expression) throws InvalidExpressionException {
        final ExpressionBuilder exp = new ExpressionBuilder();
        final List<List<Expression>> expressions = new ArrayList<>();
        final StringBuilder expressionNames = new StringBuilder(
                "Table of expression containing the following expressions: [");
        for (final ListExpression listExpression : ((TableExpression) expression).getExpressions()) {
            final List<Expression> engineExpressionList = new ArrayList<>();
            expressionNames.append("(");
            for (final org.bonitasoft.bpm.model.expression.Expression simpleExpression : listExpression
                    .getExpressions()) {
                final Expression createExpression = createExpression(simpleExpression);
                if (createExpression != null) {
                    engineExpressionList.add(createExpression);
                    expressionNames.append(createExpression.getName());
                    expressionNames.append(",");
                }
            }
            expressionNames.append(")");
            expressions.add(engineExpressionList);
        }
        expressionNames.append("].");
        return exp.createListOfListExpression(expressionNames.toString(), expressions);
    }

    protected static Expression buildListEngineExpression(
            final org.bonitasoft.bpm.model.expression.AbstractExpression expression) throws InvalidExpressionException {
        final ExpressionBuilder exp = new ExpressionBuilder();
        final List<Expression> expressions = new ArrayList<>();
        final StringBuilder expressionNames = new StringBuilder(
                "List of expression containing the following expressions: (");
        for (final org.bonitasoft.bpm.model.expression.Expression simpleExpression : ((ListExpression) expression)
                .getExpressions()) {
            final Expression createExpression = createExpression(simpleExpression);
            if (createExpression != null) {
                expressions.add(createExpression);
                expressionNames.append(createExpression.getName());
                expressionNames.append(",");
            } else {
                final Expression nullExpression = createNullExpression();
                expressions.add(nullExpression);
                expressionNames.append(nullExpression.getName());
                expressionNames.append(",");
            }
        }
        expressionNames.append(").");
        return exp.createListExpression(expressionNames.toString(), expressions);
    }

    private static Expression buildSimpleEngineExpressionWithName(String name,
            final org.bonitasoft.bpm.model.expression.Expression expression) throws InvalidExpressionException {
        final ExpressionBuilder expressionBuilder = new ExpressionBuilder();
        if (name == null || name.isEmpty()) {
            name = expression.getName();
            if (name == null || name.isEmpty()) {
                name = "<empty-name>";
            }
        }
        return buildSimpleEngineExpression(expressionBuilder.createNewInstance(name), expression);
    }

    private static Expression buildSimpleEngineExpression(final ExpressionBuilder expressionBuilder,
            final org.bonitasoft.bpm.model.expression.Expression expression) throws InvalidExpressionException {
        if (!expression.hasContent()) {
            return null;
        }
        final IExpressionConverter converter = getConverter(expression);
        if (converter != null) {
            return converter.convert(expression);
        }
        final String type = expression.getType();
        if (ExpressionConstants.PATTERN_TYPE.equals(type)) {
            return createPatternExpression(expressionBuilder, expression);
        }
        if (ExpressionConstants.DOCUMENT_TYPE.equals(type)) {
            return createDocumentReferenceExpression(expressionBuilder, expression);
        }
        if (ExpressionConstants.DOCUMENT_LIST_TYPE.equals(type)) {
            return expressionBuilder.createDocumentListExpression(expression.getName());
        }
        if (ExpressionConstants.DOCUMENT_REF_TYPE.equals(type)) {
            return createEngineExpressionForDocumentRef(expressionBuilder, expression);
        }
        if (ExpressionConstants.XPATH_TYPE.equals(type)) {
            return createXPATHExpression(expressionBuilder, expression);
        }
        if (ExpressionConstants.QUERY_TYPE.equals(type)) {
            return createQueryExpression(expressionBuilder, expression);
        } else {
            expressionBuilder.setContent(expression.getContent().replace("\r", "\n"));
            final ExpressionType engineExpressionType = toEngineExpressionType(expression);
            expressionBuilder.setExpressionType(engineExpressionType);
            expressionBuilder.setInterpreter(
                    ExpressionType.TYPE_READ_ONLY_SCRIPT.equals(engineExpressionType) ? expression.getInterpreter()
                            : "");
            expressionBuilder.setReturnType(expression.getReturnType());
            expressionBuilder.setDependencies(createDependenciesList(expression));
            return expressionBuilder.done();
        }
    }

    private static Expression createDocumentReferenceExpression(final ExpressionBuilder expressionBuilder,
            final org.bonitasoft.bpm.model.expression.Expression expression) throws InvalidExpressionException {
        final EList<EObject> referencedElements = expression.getReferencedElements();
        if (!referencedElements.isEmpty()) {
            final Document document = (Document) referencedElements.get(0);
            if (document.isMultiple()) {
                return expressionBuilder.createDocumentListExpression(expression.getName());
            }
        }
        return expressionBuilder.createDocumentReferenceExpression(expression.getName());
    }

    private static IExpressionConverter getConverter(final org.bonitasoft.bpm.model.expression.Expression expression) {
        if (converters == null) {
            converters = new ArrayList<>();
            converters.add(new ComparisonExpressionConverter());
        }
        for (final IExpressionConverter converter : converters) {
            if (converter.appliesTo(expression)) {
                return converter;
            }
        }
        return null;
    }

    private static Expression createEngineExpressionForDocumentRef(final ExpressionBuilder expressionBuilder,
            final org.bonitasoft.bpm.model.expression.Expression expression) throws InvalidExpressionException {
        final String content = expression.getContent();
        final EList<EObject> referencedElements = expression.getReferencedElements();
        if (!referencedElements.isEmpty()) {
            final EObject referencedElement = referencedElements.get(0);
            if (referencedElement instanceof Document && ((Document) referencedElement).isMultiple()) {
                return expressionBuilder.createDocumentListExpression(expression.getName());
            }
        }
        return createConstantExpression(content, content, String.class.getName());
    }

    private static Expression createQueryExpression(final ExpressionBuilder expressionBuilder,
            final org.bonitasoft.bpm.model.expression.Expression simpleExpression) throws InvalidExpressionException {
        final List<Expression> dependencies = new ArrayList<>();
        for (final EObject param : simpleExpression.getReferencedElements()) {
            if (param instanceof org.bonitasoft.bpm.model.expression.Expression) {
                final EList<EObject> referencedElements = ((org.bonitasoft.bpm.model.expression.Expression) param)
                        .getReferencedElements();
                if (!referencedElements.isEmpty()) {
                    final Expression paramExpression = buildSimpleEngineExpressionWithName(
                            ((org.bonitasoft.bpm.model.expression.Expression) param).getName(),
                            (org.bonitasoft.bpm.model.expression.Expression) referencedElements.get(0));
                    if (paramExpression != null) {
                        dependencies.add(paramExpression);
                    }
                }
            }
        }
        return expressionBuilder.createQueryBusinessDataExpression(simpleExpression.getName(),
                simpleExpression.getName(), simpleExpression.getReturnType(),
                dependencies.toArray(new Expression[dependencies.size()]));
    }

    private static Expression createNullExpression() throws InvalidExpressionException {
        return new ExpressionBuilder().createGroovyScriptExpression("ExpressionNotDefinedSetAsNull", "null",
                Object.class.getName());
    }

    public static Expression createPatternExpression(final ExpressionBuilder exp,
            final org.bonitasoft.bpm.model.expression.Expression simpleExpression) throws InvalidExpressionException {
        exp.createNewInstance("<pattern-expression>");
        exp.setContent(simpleExpression.getContent());
        final ExpressionType engineExpressionType = toEngineExpressionType(simpleExpression);
        exp.setExpressionType(engineExpressionType);
        if (ExpressionType.TYPE_READ_ONLY_SCRIPT == engineExpressionType) {
            exp.setInterpreter(simpleExpression.getInterpreter());
        } else {
            exp.setInterpreter("");
        }
        exp.setReturnType(simpleExpression.getReturnType());
        final List<Expression> dependenciesList = createDependenciesList(simpleExpression);
        final List<Expression> toRemove = new ArrayList<>();
        for (final Expression expression : dependenciesList) {
            if (!simpleExpression.getContent().contains("${" + expression.getName() + "}")) {
                toRemove.add(expression);
            }
        }
        dependenciesList.removeAll(toRemove);
        exp.setDependencies(dependenciesList);
        return exp.done();
    }

    static ExpressionType toEngineExpressionType(final org.bonitasoft.bpm.model.expression.Expression expression) {
        final String type = expression.getType();
        if (ExpressionConstants.CONNECTOR_OUTPUT_TYPE.equals(type)) {
            return ExpressionType.TYPE_INPUT;
        }
        if (ExpressionConstants.DOCUMENT_REF_TYPE.equals(type)) {
            return toEngineExpressionTypeFoDocumentRef(expression);
        }
        if (ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE.equals(type)) {
            return ExpressionType.TYPE_VARIABLE;
        }
        if (ExpressionConstants.VARIABLE_TYPE.equals(type) && !expression.getReferencedElements().isEmpty()) {
            EObject reference = expression.getReferencedElements().get(0);
            if (!(reference instanceof Data)) {
                throw new IllegalArgumentException(String.format(
                        "Incompatible expression type found. Expecting a %s but found a %s for expression %s",
                        Data.class.getSimpleName(), reference.getClass().getSimpleName(), expression.getName()));
            }
            final Data data = (Data) reference;
            final String ds = data.getDatasourceId();
            if (PAGEFLOW_DATASOURCE.equals(ds)) {
                return ExpressionType.TYPE_INPUT;
            }
            if (data.isTransient()) {
                return ExpressionType.TYPE_TRANSIENT_VARIABLE;
            }
            if (data instanceof BusinessObjectData) {
                return ExpressionType.TYPE_BUSINESS_DATA;
            }

        }
        return ExpressionType.valueOf(expression.getType());
    }

    private static ExpressionType toEngineExpressionTypeFoDocumentRef(
            final org.bonitasoft.bpm.model.expression.Expression expression) {
        if (!expression.getReferencedElements().isEmpty()) {
            final EObject referencedElement = expression.getReferencedElements().get(0);
            if (referencedElement instanceof Document && ((Document) referencedElement).isMultiple()) {
                return ExpressionType.TYPE_DOCUMENT_LIST;
            }
        }
        // Return CONSTANT_TYPE for ensuring backward-compatibility even if Engine has
        // introduced the DOCUMENT_TYPE,
        return ExpressionType.TYPE_CONSTANT;
    }

    private static Expression createConnectorOutputExpression(final Output element) throws InvalidExpressionException {
        final ExpressionBuilder exp = new ExpressionBuilder();
        exp.createNewInstance(element.getName());
        exp.setContent(element.getName());
        exp.setExpressionType(ExpressionType.TYPE_INPUT);
        exp.setReturnType(element.getType());
        return exp.done();
    }

    private static Expression createXPATHExpression(final ExpressionBuilder exp,
            final org.bonitasoft.bpm.model.expression.Expression expression) throws InvalidExpressionException {
        exp.createNewInstance(expression.getName()).setExpressionType(ExpressionType.TYPE_XPATH_READ)
                .setContent(expression.getContent());
        exp.setReturnType(expression.getReturnType());
        exp.setDependencies(createDependenciesList(expression));
        return exp.done();
    }

    public static Expression createVariableExpression(final Data element) throws InvalidExpressionException {
        final String datasourceId = element.getDatasourceId();
        ExpressionType type = ExpressionType.TYPE_VARIABLE;
        if (element instanceof BusinessObjectData) {
            type = ExpressionType.TYPE_BUSINESS_DATA;
        }
        if (PAGEFLOW_DATASOURCE.equals(datasourceId)) {
            type = ExpressionType.TYPE_INPUT;
        }
        if (element.isTransient()) {
            type = ExpressionType.TYPE_TRANSIENT_VARIABLE;
        }
        final ExpressionBuilder exp = new ExpressionBuilder();
        exp.createNewInstance(element.getName());
        exp.setContent(element.getName());
        exp.setExpressionType(type);
        exp.setReturnType(DataUtil.getTechnicalTypeFor(element));
        return exp.done();
    }

    public static Expression createEmptyListExpression() throws InvalidExpressionException {
        final ExpressionBuilder exp = new ExpressionBuilder();
        exp.createNewInstance("<empty-name>");
        exp.setContent("[]");
        exp.setExpressionType(ExpressionType.TYPE_READ_ONLY_SCRIPT);
        exp.setInterpreter(ExpressionConstants.GROOVY);
        exp.setReturnType(List.class.getName());
        exp.setDependencies(new ArrayList<>());
        return exp.done();
    }

    private static Expression createConstantExpression(final String name, final String content, final String returnType)
            throws InvalidExpressionException {
        final ExpressionBuilder exp = new ExpressionBuilder();
        exp.createNewInstance(name);
        exp.setContent(content);
        exp.setExpressionType(ExpressionType.TYPE_CONSTANT);
        exp.setReturnType(returnType);
        return exp.done();
    }

}
