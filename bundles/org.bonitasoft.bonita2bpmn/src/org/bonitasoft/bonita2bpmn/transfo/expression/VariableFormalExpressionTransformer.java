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

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.function.Predicate;

import org.bonitasoft.bonita2bpmn.transfo.data.DataScope;
import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.bpm.model.process.Data;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.bonitasoft.bpm.model.util.IModelSearch;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.omg.spec.bpmn.model.TFormalExpression;
import org.omg.spec.bpmn.model.TItemDefinition;

/**
 * @author Romain Bioteau
 */
public class VariableFormalExpressionTransformer extends FormalExpressionFunction {

    private static final String DATA_OBJECT_PATTERN = "getDataObject('%s')";
    private static final String ACTIVITY_PROPERTY_PATTERN = "getActivityProperty('%s','%s')";

    /**
     * The expected and only supported expression type.
     */
    private static final String EXPRESSION_TYPE = ExpressionConstants.VARIABLE_TYPE;

    /** Test a String expression type value against the supported type */
    public static final Predicate<String> IS_SUPPORTED_TYPE = EXPRESSION_TYPE::equals;
    private final DataScope dataScope;
    private IModelSearch modelSearch;

    public VariableFormalExpressionTransformer(final DataScope dataScope, IModelSearch modelSearch) {
        this.dataScope = dataScope;
        this.modelSearch = modelSearch;
    }

    @Override
    protected TFormalExpression addContent(final Expression bonitaExpression,
            final TFormalExpression formalExpression) {
        requireNonNull(bonitaExpression);
        requireNonNull(formalExpression);
        if (!IS_SUPPORTED_TYPE.test(bonitaExpression.getType())) {
            throw new IllegalArgumentException(
                    String.format("Expression type is invalid. Expected %s but was %s",
                            EXPRESSION_TYPE,
                            bonitaExpression.getType()));
        }
        final EList<EObject> referencedElements = bonitaExpression.getReferencedElements();
        if (referencedElements.isEmpty()) {
            throw new IllegalArgumentException(
                    String.format("Missing referenced elements for variable expression %s",
                            bonitaExpression.getName()));
        }

        final Data bonitaData = requireNonNull((Data) referencedElements.get(0));
        final TItemDefinition bpmnData = dataScope.get(resolveData(bonitaData.getName(), bonitaExpression));
        FeatureMapUtil.addText(formalExpression.getMixed(),
                createContentFor(bpmnData, bonitaData, bonitaExpression.getContent()));
        return formalExpression;
    }

    private String createContentFor(final TItemDefinition bpmnData, final Data bonitaData,
            final String expressionContent) {
        if (bonitaData.isTransient()) {
            return createContentForTransientData(bpmnData, bonitaData, expressionContent);
        }
        return createContentForData(bpmnData, expressionContent);
    }

    private String createContentForData(final TItemDefinition bpmnData,
            final String expressionContent) {
        return String.format(DATA_OBJECT_PATTERN, bpmnData != null ? bpmnData.getId() : expressionContent);
    }

    private String createContentForTransientData(final TItemDefinition bpmnData, final Data bonitaData,
            final String expressionContent) {
        final AbstractProcess parentProcess = modelSearch.getDirectParentOfType(bonitaData, AbstractProcess.class);
        return String.format(ACTIVITY_PROPERTY_PATTERN, parentProcess.getName(),
                bpmnData != null ? bpmnData.getId() : expressionContent);
    }

    private Data resolveData(final String referencedDataName, EObject context) {
        return modelSearch.getAccessibleData(context).stream()
                .filter(data -> Objects.equals(data.getName(), referencedDataName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        String.format("No data found in scope with name '%s'", referencedDataName)));
    }

}
