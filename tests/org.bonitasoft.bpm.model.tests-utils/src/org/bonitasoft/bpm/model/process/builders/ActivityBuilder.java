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
package org.bonitasoft.bpm.model.process.builders;

import org.bonitasoft.bpm.model.expression.Operation;
import org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.bpm.model.expression.builders.OperationBuilder;
import org.bonitasoft.bpm.model.kpi.AbstractKPIBinding;
import org.bonitasoft.bpm.model.process.Activity;
import org.bonitasoft.bpm.model.process.Data;
import org.bonitasoft.bpm.model.process.MultiInstanceType;
import org.bonitasoft.bpm.model.process.ProcessFactory;

/**
 * @author Romain Bioteau
 */
public class ActivityBuilder<T extends Activity, B extends ActivityBuilder<T, B>> extends FlowElementBuilder<T, B> {

    public static <B extends ActivityBuilder<Activity, B>> ActivityBuilder<Activity, B> anActivity() {
        return new ActivityBuilder<Activity, B>();
    }

    public B withMultiInstanceType(final MultiInstanceType type) {
        getBuiltInstance().setType(type);
        return getThis();
    }

    public B havingCardinalityExpression(final ExpressionBuilder cardinalityExpression) {
        getBuiltInstance().setCardinalityExpression(cardinalityExpression.build());
        return getThis();
    }

    public B havingCompletionCondition(final ExpressionBuilder completionConditionExpression) {
        getBuiltInstance().setCompletionCondition(completionConditionExpression.build());
        return getThis();
    }

    public B havingLoopCondition(final ExpressionBuilder loopConditionExpression) {
        getBuiltInstance().setLoopCondition(loopConditionExpression.build());
        return getThis();
    }

    public B havingMaximumLoop(final ExpressionBuilder maximumLoopExpression) {
        getBuiltInstance().setLoopMaximum(maximumLoopExpression.build());
        return getThis();
    }

    public B havingIteratorExpression(final ExpressionBuilder iteratorExpression) {
        getBuiltInstance().setIteratorExpression(iteratorExpression.build());
        return getThis();
    }

    public B havingCollectionDataToMultiInstantiate(final Data data) {
        getBuiltInstance().setCollectionDataToMultiInstantiate(data);
        return getThis();
    }

    public B havingListDataContainingOutputResults(final Data data) {
        getBuiltInstance().setListDataContainingOutputResults(data);
        return getThis();
    }

    public B havingOutputData(final Data outputData) {
        getBuiltInstance().setOutputData(outputData);
        return getThis();
    }

    public B storeOutputResult() {
        getBuiltInstance().setStoreOutput(true);
        return getThis();
    }

    public B ignoreOutputResult() {
        getBuiltInstance().setStoreOutput(false);
        return getThis();
    }

    public B basedOnCardinality() {
        getBuiltInstance().setUseCardinality(true);
        return getThis();
    }

    public B basedOnCollection() {
        getBuiltInstance().setUseCardinality(false);
        return getThis();
    }

    public B testBefore() {
        getBuiltInstance().setTestBefore(true);
        return getThis();
    }

    public B testAfter() {
        getBuiltInstance().setTestBefore(false);
        return getThis();
    }

    public B havingData(final DataBuilder<?, ?>... data) {
        if (data != null) {
            for (final DataBuilder<?, ?> dataBuilder : data) {
                getBuiltInstance().getData().add(dataBuilder.build());
            }
        }
        return getThis();
    }

    public B havingData(final Data... data) {
        if (data != null) {
            for (final Data d : data) {
                getBuiltInstance().getData().add(d);
            }
        }
        return getThis();
    }

    public B havingOperations(final OperationBuilder... operations) {
        if (operations != null) {
            for (final OperationBuilder operationBuilder : operations) {
                getBuiltInstance().getOperations().add(operationBuilder.build());
            }
        }
        return getThis();
    }

    public B havingOperations(final Operation... operations) {
        if (operations != null) {
            for (final Operation operation : operations) {
                getBuiltInstance().getOperations().add(operation);
            }
        }
        return getThis();
    }

    public B havingKPI(final AbstractKPIBinding... kpiBindings) {
        if (kpiBindings != null) {
            for (final AbstractKPIBinding abstractKPIBinding : kpiBindings) {
                getBuiltInstance().getKpis().add(abstractKPIBinding);
            }
        }
        return getThis();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected T newInstance() {
        return (T) ProcessFactory.eINSTANCE.createActivity();
    }

}
