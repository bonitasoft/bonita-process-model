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

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import org.bonitasoft.bpm.connector.model.definition.Output;
import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.expression.ExpressionFactory;
import org.bonitasoft.bpm.model.parameter.Parameter;
import org.bonitasoft.bpm.model.process.BooleanType;
import org.bonitasoft.bpm.model.process.BusinessObjectData;
import org.bonitasoft.bpm.model.process.BusinessObjectType;
import org.bonitasoft.bpm.model.process.ContractInput;
import org.bonitasoft.bpm.model.process.ContractInputType;
import org.bonitasoft.bpm.model.process.Data;
import org.bonitasoft.bpm.model.process.DataType;
import org.bonitasoft.bpm.model.process.DateType;
import org.bonitasoft.bpm.model.process.Document;
import org.bonitasoft.bpm.model.process.DoubleType;
import org.bonitasoft.bpm.model.process.IntegerType;
import org.bonitasoft.bpm.model.process.JavaObjectData;
import org.bonitasoft.bpm.model.process.JavaType;
import org.bonitasoft.bpm.model.process.LongType;
import org.bonitasoft.bpm.model.process.MainProcess;
import org.bonitasoft.bpm.model.process.MultiInstantiable;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.bpm.model.process.SearchIndex;
import org.bonitasoft.bpm.model.process.StringType;
import org.bonitasoft.bpm.model.process.util.DataUtil;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class ExpressionHelper {

    protected static final String EMPTY_LIST_NAME = "Empty list";
    protected static final String EMPTY_LIST_CONTENT = "[]";

    private ExpressionHelper() {

    }

    public static Expression createConstantExpression(final String content, final String returnClassName) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setType(ExpressionConstants.CONSTANT_TYPE);
        exp.setName(content);
        exp.setContent(content);
        exp.setReturnType(returnClassName);
        return exp;
    }

    public static EObject createDependencyFromEObject(final EObject dependency) {
        if (dependency instanceof Data) {
            return createDataDependency(dependency);
        }
        if (dependency instanceof Document) {
            return createDocumentDependency(dependency);
        }
        if (dependency instanceof SearchIndex) {
            return createSearchIndexDependency(dependency);
        }
        if (dependency instanceof ContractInput) {
            return createContractInputDependency(dependency);
        }
        return EcoreUtil.copy(dependency);
    }

    private static EObject createContractInputDependency(EObject dependency) {
        final ContractInput contractInputDependency = (ContractInput) EcoreUtil.copy(dependency);
        if (contractInputDependency.getType() == ContractInputType.COMPLEX) {
            contractInputDependency.getInputs().clear();
        }
        contractInputDependency.setDataReference(null);
        return contractInputDependency;
    }

    private static EObject createSearchIndexDependency(final EObject dependency) {
        final SearchIndex searchIndexDependency = (SearchIndex) ProcessFactory.eINSTANCE.create(dependency.eClass());
        final Expression name = ((SearchIndex) dependency).getName();
        if (name != null) {
            final Expression nameExpression = EcoreUtil.copy(name);
            nameExpression.getReferencedElements().clear();
            searchIndexDependency.setName(nameExpression);
        }
        return searchIndexDependency;
    }

    private static EObject createDocumentDependency(final EObject dependency) {
        final Document documentDependency = (Document) ProcessFactory.eINSTANCE.create(dependency.eClass());
        documentDependency.setName(((Document) dependency).getName());
        documentDependency.setMultiple(((Document) dependency).isMultiple());
        return documentDependency;
    }

    private static EObject createDataDependency(final EObject dependency) {
        final Data dataDependency = (Data) EcoreUtil.copy(dependency);
        dataDependency.setDefaultValue(null);
        return dataDependency;
    }

    private static Expression createVariableExpression(final Data data) {
        final Expression expression = ExpressionFactory.eINSTANCE.createExpression();
        expression.setType(ExpressionConstants.VARIABLE_TYPE);
        expression.setReturnType(DataUtil.getTechnicalTypeFor(data));
        expression.setName(data.getName());
        expression.setContent(data.getName());
        expression.getReferencedElements().add(createDependencyFromEObject(data));
        return expression;
    }

    public static Expression createExpressionFromEObject(final EObject element) {
        if (element instanceof Data) {
            return createVariableExpression((Data) element);
        } else if (element instanceof Output) {
            return createConnectorOutputExpression((Output) element);
        } else if (element instanceof Parameter) {
            return createParameterExpression((Parameter) element);
        } else if (element instanceof org.bonitasoft.bpm.model.expression.Expression) {
            return (Expression) EcoreUtil.copy(element);
        } else if (element instanceof Document) {
            return createDocumentExpressionWithDependency((Document) element);
        } else if (element instanceof ContractInput) {
            return createContractInputExpression((ContractInput) element);
        }
        throw new IllegalArgumentException("element argument is not supported: " + element);
    }

    private static Expression createContractInputExpression(final ContractInput input) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setType(ExpressionConstants.CONTRACT_INPUT_TYPE);
        exp.setContent(input.getName());
        exp.setName(input.getName());
        exp.setReturnType(getContractInputReturnType(input));
        exp.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(input));
        return exp;
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

    public static Expression createConnectorOutputExpression(final Output output) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setType(ExpressionConstants.CONNECTOR_OUTPUT_TYPE);
        exp.setContent(output.getName());
        exp.setName(output.getName());
        exp.setReturnType(output.getType());
        exp.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(output));
        return exp;
    }

    private static Expression createParameterExpression(final Parameter p) {
        final Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setType(ExpressionConstants.PARAMETER_TYPE);
        exp.setContent(p.getName());
        exp.setName(p.getName());
        exp.setReturnType(p.getTypeClassname());
        exp.getReferencedElements().add(createDependencyFromEObject(p));
        return exp;
    }

    public static Expression createDocumentExpressionWithDependency(final Document document) {
        final Expression expression = ExpressionFactory.eINSTANCE.createExpression();
        expression.setContent(document.getName());
        expression.setName(document.getName());
        expression.setType(ExpressionConstants.DOCUMENT_TYPE);
        expression.setReturnType(String.class.getName());
        if (document.isMultiple()) {
            expression.setReturnType(List.class.getName());
        } else {
            expression.setReturnType(org.bonitasoft.engine.bpm.document.Document.class.getName());
        }
        expression.getReferencedElements().add(createDependencyFromEObject(document));
        return expression;
    }

    public static Data dataFromIteratorExpression(final MultiInstantiable parentFlowElement,
            final Expression iteratorExpression, final MainProcess mainProcess) {
        final String returnType = iteratorExpression.getReturnType();
        Data d = null;
        if (returnType != null) {
            final DataType dt = getDataTypeFrom(returnType, mainProcess, parentFlowElement);
            if (dt instanceof BusinessObjectType) {
                d = ProcessFactory.eINSTANCE.createBusinessObjectData();
                ((JavaObjectData) d).setClassName(returnType);
            } else if (dt instanceof JavaType) {
                d = ProcessFactory.eINSTANCE.createJavaObjectData();
                ((JavaObjectData) d).setClassName(returnType);
            } else {
                d = ProcessFactory.eINSTANCE.createData();
            }
            d.setName(iteratorExpression.getName());
            d.setDataType(dt);
        }
        return d;
    }

    private static DataType getDataTypeFrom(final String returnType, final MainProcess mainProcess,
            final MultiInstantiable parentFlowElement) {
        if (parentFlowElement.getCollectionDataToMultiInstantiate() instanceof BusinessObjectData) {
            return mainProcess.getDatatypes().stream()
                    .filter(BusinessObjectType.class::isInstance)
                    .findFirst().orElse(null);
        } else {
            return getDataTypeByClassName(mainProcess, returnType);
        }
    }

    static DataType getDataTypeByClassName(final MainProcess dataTypeContainer, final String returnTypeClassname) {
        Objects.requireNonNull(dataTypeContainer);
        Objects.requireNonNull(returnTypeClassname);
        if (returnTypeClassname.equals(Boolean.class.getName())) {
            return dataTypeContainer.getDatatypes().stream()
                    .filter(BooleanType.class::isInstance)
                    .findFirst()
                    .orElse(null);
        } else if (returnTypeClassname.equals(String.class.getName())) {
            return dataTypeContainer.getDatatypes().stream()
                    .filter(StringType.class::isInstance)
                    .filter(Predicate.not(DateType.class::isInstance))
                    .findFirst()
                    .orElse(null);
        } else if (returnTypeClassname.equals(Double.class.getName())) {
            return dataTypeContainer.getDatatypes().stream()
                    .filter(DoubleType.class::isInstance)
                    .findFirst()
                    .orElse(null);
        } else if (returnTypeClassname.equals(Long.class.getName())) {
            return dataTypeContainer.getDatatypes().stream()
                    .filter(LongType.class::isInstance)
                    .findFirst()
                    .orElse(null);
        } else if (returnTypeClassname.equals(Integer.class.getName())) {
            return dataTypeContainer.getDatatypes().stream()
                    .filter(IntegerType.class::isInstance)
                    .findFirst()
                    .orElse(null);
        } else if (returnTypeClassname.equals(Date.class.getName())) {
            return dataTypeContainer.getDatatypes().stream()
                    .filter(DateType.class::isInstance)
                    .findFirst()
                    .orElse(null);
        } else {
            return dataTypeContainer.getDatatypes().stream()
                    .filter(JavaType.class::isInstance)
                    .findFirst()
                    .orElse(null);
        }
    }

}
