/**
 * Copyright (C) 2012 Bonitasoft S.A.
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
package org.bonitasoft.bonita2bar.process.builder;

import org.bonitasoft.bonita2bar.process.expression.DecisionTableUtil;
import org.bonitasoft.bonita2bar.process.expression.EngineExpressionUtil;
import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.process.ANDGateway;
import org.bonitasoft.bpm.model.process.BoundaryEvent;
import org.bonitasoft.bpm.model.process.CatchLinkEvent;
import org.bonitasoft.bpm.model.process.Connection;
import org.bonitasoft.bpm.model.process.SequenceFlow;
import org.bonitasoft.bpm.model.process.SequenceFlowConditionType;
import org.bonitasoft.bpm.model.process.SourceElement;
import org.bonitasoft.bpm.model.process.TargetElement;
import org.bonitasoft.bpm.model.process.ThrowLinkEvent;
import org.bonitasoft.bpm.model.process.util.ProcessSwitch;
import org.bonitasoft.engine.bpm.process.impl.FlowElementBuilder;
import org.bonitasoft.engine.expression.InvalidExpressionException;

/**
 * @author Romain Bioteau
 */
public class EngineSequenceFlowBuilder extends ProcessSwitch<SequenceFlow> {

    private final FlowElementBuilder builder;

    public EngineSequenceFlowBuilder(FlowElementBuilder processBuilder) {
        builder = processBuilder;
    }

    @Override
    public SequenceFlow caseSequenceFlow(SequenceFlow sequenceFlow) {
        SourceElement source = sequenceFlow.getSource();
        TargetElement target = sequenceFlow.getTarget();

        if (source == null) {
            throw new ProcessBuilderException("Source of sequenceflow is null");
        }

        if (target == null) {
            throw new ProcessBuilderException("Target of sequenceflow is null");
        }

        String sourceId = source.getName();
        String targetId = target.getName();

        if (target instanceof ThrowLinkEvent
                || source instanceof CatchLinkEvent) {/* link with catch or throw link event */
            try {
                addLinkEvents(builder, sequenceFlow);
            } catch (InvalidExpressionException e) {
                throw new ProcessBuilderException(e);
            }
        } else {
            if (!(source instanceof ANDGateway)) {
                addTransitionCondition(sequenceFlow, source, sourceId, targetId);
            } else {
                builder.addTransition(sourceId, targetId);
            }
        }

        return sequenceFlow;
    }

    private void addTransitionCondition(SequenceFlow sequenceFlow, SourceElement source, String sourceId,
            String targetId) {
        final SequenceFlowConditionType conditionType = sequenceFlow.getConditionType();
        final Expression condition = sequenceFlow.getCondition();
        try {
            if (sequenceFlow.isIsDefault() && !(source instanceof BoundaryEvent)) {
                builder.addDefaultTransition(sourceId, targetId);
            } else if (conditionType == SequenceFlowConditionType.EXPRESSION && condition != null
                    && condition.getContent() != null && !condition.getContent().isEmpty()) {
                org.bonitasoft.engine.expression.Expression conditionExpression = EngineExpressionUtil
                        .createExpression(condition);
                if (conditionExpression == null) {
                    throw new IllegalArgumentException("Condition expression " + condition.getName()
                            + " on SequenceFlow from " + sourceId + " to " + targetId + " is invalid");
                }
                builder.addTransition(sourceId, targetId, conditionExpression);
            } else if (conditionType == SequenceFlowConditionType.DECISION_TABLE) {
                builder.addTransition(sourceId, targetId, EngineExpressionUtil.createExpression(
                        DecisionTableUtil.toGroovyScriptExpression(sequenceFlow.getDecisionTable())));
            } else {
                builder.addTransition(sourceId, targetId);
            }
        } catch (InvalidExpressionException e) {
            throw new ProcessBuilderException(e);
        }
    }

    protected void addLinkEvents(FlowElementBuilder builder, SequenceFlow sequenceFlow)
            throws InvalidExpressionException {
        if (sequenceFlow.getTarget() instanceof ThrowLinkEvent) {
            final ThrowLinkEvent throwLink = (ThrowLinkEvent) sequenceFlow.getTarget();
            if (null != throwLink.getTo()) {
                final CatchLinkEvent target = throwLink.getTo();
                for (Connection c : target.getOutgoing()) {
                    String sourceId = sequenceFlow.getSource().getName();
                    String targetId = c.getTarget().getName();
                    org.bonitasoft.bpm.model.expression.Expression transitionCondition = sequenceFlow.getCondition();
                    if (sequenceFlow.isIsDefault()) {
                        builder.addDefaultTransition(sourceId, targetId);
                    } else if (null != transitionCondition && transitionCondition.getContent() != null
                            && !transitionCondition.getContent().isEmpty()) {
                        builder.addTransition(sourceId, targetId,
                                EngineExpressionUtil.createExpression(transitionCondition));
                    } else {
                        builder.addTransition(sourceId, targetId);
                    }

                }
            } else {
                throw new ProcessBuilderException(
                        String.format("The go to attribute of %s must be specified", throwLink.getName()));
            }
        }
    }

}
