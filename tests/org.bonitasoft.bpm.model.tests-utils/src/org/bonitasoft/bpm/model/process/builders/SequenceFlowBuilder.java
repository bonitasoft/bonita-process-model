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

import org.bonitasoft.bpm.model.Buildable;
import org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.bpm.model.process.SequenceFlow;

/**
 * @author Romain Bioteau
 */
public class SequenceFlowBuilder extends ConnectionBuilder<SequenceFlow, SequenceFlowBuilder> {

    public static SequenceFlowBuilder aSequenceFlow() {
        return new SequenceFlowBuilder();
    }

    @Override
    protected SequenceFlow newInstance() {
        return ProcessFactory.eINSTANCE.createSequenceFlow();
    }

    public SequenceFlowBuilder havingCondition(final ExpressionBuilder conditionExpression) {
        getBuiltInstance().setCondition(conditionExpression.build());
        return getThis();
    }

    public SequenceFlowBuilder defaultFlow() {
        getBuiltInstance().setIsDefault(true);
        return getThis();
    }

    public SequenceFlowBuilder notDefaultFlow() {
        getBuiltInstance().setIsDefault(false);
        return getThis();
    }

    public SequenceFlowBuilder in(final Buildable<? extends AbstractProcess> connectionContainerBuildable) {
        connectionContainerBuildable.build().getConnections().add(getBuiltInstance());
        return this;
    }

    public SequenceFlowBuilder in(final AbstractProcess connectionContainer) {
        connectionContainer.getConnections().add(getBuiltInstance());
        return this;
    }
}
