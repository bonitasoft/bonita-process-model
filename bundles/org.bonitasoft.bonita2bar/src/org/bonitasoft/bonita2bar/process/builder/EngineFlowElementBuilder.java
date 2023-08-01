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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.bonitasoft.bonita2bar.process.expression.EngineExpressionUtil;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.expression.ListExpression;
import org.bonitasoft.bpm.model.expression.Operation;
import org.bonitasoft.bpm.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.bpm.model.process.AbstractTimerEvent;
import org.bonitasoft.bpm.model.process.Activity;
import org.bonitasoft.bpm.model.process.ActorFilter;
import org.bonitasoft.bpm.model.process.BoundaryEvent;
import org.bonitasoft.bpm.model.process.BoundaryMessageEvent;
import org.bonitasoft.bpm.model.process.BoundarySignalEvent;
import org.bonitasoft.bpm.model.process.BoundaryTimerEvent;
import org.bonitasoft.bpm.model.process.BusinessObjectData;
import org.bonitasoft.bpm.model.process.CallActivity;
import org.bonitasoft.bpm.model.process.Connection;
import org.bonitasoft.bpm.model.process.Correlation;
import org.bonitasoft.bpm.model.process.CorrelationTypeActive;
import org.bonitasoft.bpm.model.process.Data;
import org.bonitasoft.bpm.model.process.DataAware;
import org.bonitasoft.bpm.model.process.Element;
import org.bonitasoft.bpm.model.process.EndErrorEvent;
import org.bonitasoft.bpm.model.process.EndEvent;
import org.bonitasoft.bpm.model.process.EndMessageEvent;
import org.bonitasoft.bpm.model.process.EndSignalEvent;
import org.bonitasoft.bpm.model.process.EndTerminatedEvent;
import org.bonitasoft.bpm.model.process.FlowElement;
import org.bonitasoft.bpm.model.process.InputMapping;
import org.bonitasoft.bpm.model.process.InputMappingAssignationType;
import org.bonitasoft.bpm.model.process.IntermediateCatchMessageEvent;
import org.bonitasoft.bpm.model.process.IntermediateCatchSignalEvent;
import org.bonitasoft.bpm.model.process.IntermediateCatchTimerEvent;
import org.bonitasoft.bpm.model.process.IntermediateErrorCatchEvent;
import org.bonitasoft.bpm.model.process.IntermediateThrowMessageEvent;
import org.bonitasoft.bpm.model.process.IntermediateThrowSignalEvent;
import org.bonitasoft.bpm.model.process.Lane;
import org.bonitasoft.bpm.model.process.Message;
import org.bonitasoft.bpm.model.process.MultiInstanceType;
import org.bonitasoft.bpm.model.process.MultiInstantiable;
import org.bonitasoft.bpm.model.process.NonInterruptingBoundaryTimerEvent;
import org.bonitasoft.bpm.model.process.OperationContainer;
import org.bonitasoft.bpm.model.process.OutputMapping;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.ReceiveTask;
import org.bonitasoft.bpm.model.process.SearchIndex;
import org.bonitasoft.bpm.model.process.SendTask;
import org.bonitasoft.bpm.model.process.SourceElement;
import org.bonitasoft.bpm.model.process.StartErrorEvent;
import org.bonitasoft.bpm.model.process.StartEvent;
import org.bonitasoft.bpm.model.process.StartMessageEvent;
import org.bonitasoft.bpm.model.process.StartSignalEvent;
import org.bonitasoft.bpm.model.process.StartTimerEvent;
import org.bonitasoft.bpm.model.process.SubProcessEvent;
import org.bonitasoft.bpm.model.process.Task;
import org.bonitasoft.bpm.model.process.util.DataUtil;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.bonitasoft.bpm.model.util.IModelSearch;
import org.bonitasoft.engine.bpm.flownode.GatewayType;
import org.bonitasoft.engine.bpm.flownode.TaskPriority;
import org.bonitasoft.engine.bpm.flownode.TimerType;
import org.bonitasoft.engine.bpm.process.impl.ActivityDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.AutomaticTaskDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.BoundaryEventDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.CallActivityBuilder;
import org.bonitasoft.engine.bpm.process.impl.CatchMessageEventTriggerDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.EndEventDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.FlowElementBuilder;
import org.bonitasoft.engine.bpm.process.impl.GatewayDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.IntermediateCatchEventDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.IntermediateThrowEventDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.MultiInstanceLoopCharacteristicsBuilder;
import org.bonitasoft.engine.bpm.process.impl.ReceiveTaskDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.SendTaskDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.StartEventDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.SubProcessDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ThrowMessageEventTriggerBuilder;
import org.bonitasoft.engine.bpm.process.impl.UserFilterDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.UserTaskDefinitionBuilder;
import org.bonitasoft.engine.expression.InvalidExpressionException;
import org.bonitasoft.engine.operation.LeftOperand;
import org.bonitasoft.engine.operation.LeftOperandBuilder;
import org.bonitasoft.engine.operation.OperationBuilder;
import org.bonitasoft.engine.operation.OperatorType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class EngineFlowElementBuilder extends AbstractProcessBuilder {

    private final FlowElementBuilder builder;
    private TaskContractEngineDefinitionBuilder contractBuilder;

    public EngineFlowElementBuilder(final FlowElementBuilder processBuilder,
            TaskContractEngineDefinitionBuilder contractBuilder, IModelSearch modelSearch) {
        super(modelSearch);
        builder = processBuilder;
        this.contractBuilder = contractBuilder;
    }

    @Override
    public Element caseSubProcessEvent(final SubProcessEvent subProcessEvent) {
        final SubProcessDefinitionBuilder subProcessBuilder = builder.addSubProcess(subProcessEvent.getName(), true)
                .getSubProcessBuilder();
        final AbstractProcessBuilder subProcessSwitch = new EngineFlowElementBuilder(subProcessBuilder, contractBuilder,
                modelSearch);
        final List<FlowElement> flowElements = modelSearch.getAllItemsOfType(subProcessEvent, FlowElement.class);
        for (final FlowElement flowElement : flowElements) {
            subProcessSwitch.doSwitch(flowElement);
        }
        final List<SourceElement> sourceElements = modelSearch.getAllItemsOfType(subProcessEvent, SourceElement.class);
        final EngineSequenceFlowBuilder sequenceFlowSwitch = new EngineSequenceFlowBuilder(subProcessBuilder);
        for (final SourceElement sourceElement : sourceElements) {
            for (final Connection connection : sourceElement.getOutgoing()) {
                sequenceFlowSwitch.doSwitch(connection);
            }
        }
        return subProcessEvent;
    }

    @Override
    public Activity caseActivity(final Activity activity) {
        final AutomaticTaskDefinitionBuilder taskBuilder = builder.addAutomaticTask(activity.getName());
        handleCommonActivity(activity, taskBuilder);
        return activity;
    }

    @Override
    public Element caseSendTask(final SendTask senTask) {
        try {
            org.bonitasoft.engine.expression.Expression targetProcess = null;
            Message message = null;
            if (!senTask.getEvents().isEmpty()) {
                message = senTask.getEvents().get(0);
                targetProcess = EngineExpressionUtil.createExpression(message.getTargetProcessExpression());

                final SendTaskDefinitionBuilder taskBuilder = builder.addSendTask(senTask.getName(), message.getName(),
                        targetProcess);
                taskBuilder
                        .setTargetFlowNode(EngineExpressionUtil.createExpression(message.getTargetElementExpression()));
                if (message.getMessageContent() != null) {
                    addMessageContent(message, taskBuilder);
                }
                if (message.getCorrelation() != null) {
                    addCorrelation(message, taskBuilder);
                }
                handleCommonActivity(senTask, taskBuilder);
            }
        } catch (InvalidExpressionException e) {
            throw new ProcessBuilderException(e);
        }
        return senTask;
    }

    private void addMessageContent(Message message, final SendTaskDefinitionBuilder taskBuilder)
            throws InvalidExpressionException {
        for (final ListExpression row : message.getMessageContent().getExpressions()) {
            final List<org.bonitasoft.bpm.model.expression.Expression> col = row.getExpressions();
            final org.bonitasoft.bpm.model.expression.Expression idExp = col.get(0);
            final org.bonitasoft.bpm.model.expression.Expression messageContentExp = col.get(1);
            if (col.size() == 2 && idExp.getContent() != null && !idExp.getContent().isEmpty()
                    && messageContentExp.getContent() != null && !messageContentExp.getContent().isEmpty()) {
                taskBuilder.addMessageContentExpression(EngineExpressionUtil.createExpression(idExp),
                        EngineExpressionUtil.createExpression(messageContentExp));
            }
        }
    }

    private void addCorrelation(Message message, final SendTaskDefinitionBuilder taskBuilder)
            throws InvalidExpressionException {
        for (final ListExpression row : message.getCorrelation().getCorrelationAssociation().getExpressions()) {
            final List<org.bonitasoft.bpm.model.expression.Expression> col = row.getExpressions();
            if (col.size() == 2) {
                final org.bonitasoft.bpm.model.expression.Expression correlationKeyExp = col.get(0);
                final org.bonitasoft.bpm.model.expression.Expression valueExpression = col.get(1);
                if (correlationKeyExp.getContent() != null && !correlationKeyExp.getContent().isEmpty()
                        && valueExpression.getContent() != null && !valueExpression.getContent().isEmpty()) {
                    taskBuilder.addCorrelation(EngineExpressionUtil.createExpression(correlationKeyExp),
                            EngineExpressionUtil.createExpression(valueExpression));
                }
            }
        }
    }

    @Override
    public Element caseReceiveTask(final ReceiveTask receiveTask) {
        try {
            final String messageName = receiveTask.getEvent();
            final ReceiveTaskDefinitionBuilder taskBuilder = builder.addReceiveTask(receiveTask.getName(), messageName);
            if (messageName != null) {
                for (final Operation operation : receiveTask.getMessageContent()) {
                    if (operation.getLeftOperand() != null && operation.getLeftOperand().hasContent()
                            && operation.getRightOperand() != null
                            && operation.getRightOperand().getContent() != null) {
                        taskBuilder
                                .addMessageOperation(EngineExpressionUtil.createOperationForMessageContent(operation));
                    }
                }
                if (receiveTask.getCorrelation() != null) {
                    addCorrelation(receiveTask, taskBuilder);
                }
            }
            handleCommonActivity(receiveTask, taskBuilder);
        } catch (InvalidExpressionException e) {
            throw new ProcessBuilderException(e);
        }
        return receiveTask;
    }

    private void addCorrelation(final ReceiveTask receiveTask, final ReceiveTaskDefinitionBuilder taskBuilder)
            throws InvalidExpressionException {
        for (final ListExpression row : receiveTask.getCorrelation().getExpressions()) {
            final List<org.bonitasoft.bpm.model.expression.Expression> col = row.getExpressions();
            if (col.size() == 2) {
                final org.bonitasoft.bpm.model.expression.Expression correlationKeyExp = col.get(0);
                final org.bonitasoft.bpm.model.expression.Expression valueExpression = col.get(1);
                if (correlationKeyExp.getContent() != null && !correlationKeyExp.getContent().isEmpty()
                        && valueExpression.getContent() != null && !valueExpression.getContent().isEmpty()) {
                    taskBuilder.addCorrelation(EngineExpressionUtil.createExpression(correlationKeyExp),
                            EngineExpressionUtil.createExpression(valueExpression));
                }
            }
        }
    }

    @Override
    public FlowElement caseStartMessageEvent(final StartMessageEvent object) {
        final StartEventDefinitionBuilder eventBuilder = builder.addStartEvent(object.getName());
        final String message = object.getEvent();
        if (message != null) {
            try {
                final CatchMessageEventTriggerDefinitionBuilder triggerBuilder = eventBuilder
                        .addMessageEventTrigger(message);
                addMessageContent(object, triggerBuilder);
                if (modelSearch.isInEvenementialSubProcessPool(object)) {
                    addMessageCorrelation(object, triggerBuilder);
                }
            } catch (InvalidExpressionException e) {
                throw new ProcessBuilderException(e);
            }
        }
        addDescription(eventBuilder, object.getDocumentation());
        return object;
    }

    @Override
    public FlowElement caseIntermediateCatchMessageEvent(final IntermediateCatchMessageEvent object) {
        final IntermediateCatchEventDefinitionBuilder eventBuilder = builder
                .addIntermediateCatchEvent(object.getName());
        final String message = object.getEvent();
        if (message != null) {
            try {
                final CatchMessageEventTriggerDefinitionBuilder triggerBuilder = eventBuilder
                        .addMessageEventTrigger(message);
                addMessageContent(object, triggerBuilder);
                addMessageCorrelation(object, triggerBuilder);
            } catch (InvalidExpressionException e) {
                throw new ProcessBuilderException(e);
            }
        }
        addDescription(eventBuilder, object.getDocumentation());
        return object;
    }

    private void addMessageContent(final AbstractCatchMessageEvent messageEvent,
            final CatchMessageEventTriggerDefinitionBuilder triggerBuilder) throws InvalidExpressionException {
        for (final Operation operation : messageEvent.getMessageContent()) {
            if (operation.getLeftOperand() != null && operation.getLeftOperand().hasContent()
                    && operation.getRightOperand() != null && operation.getRightOperand().getContent() != null) {
                triggerBuilder.addOperation(EngineExpressionUtil.createOperationForMessageContent(operation));
            }
        }
    }

    private void addMessageCorrelation(final AbstractCatchMessageEvent messageEvent,
            final CatchMessageEventTriggerDefinitionBuilder triggerBuilder) throws InvalidExpressionException {
        if (messageEvent.getCorrelation() != null) {
            for (final ListExpression row : messageEvent.getCorrelation().getExpressions()) {
                final List<org.bonitasoft.bpm.model.expression.Expression> col = row.getExpressions();
                if (col.size() == 2) {
                    final org.bonitasoft.bpm.model.expression.Expression correlationKeyExp = col.get(0);
                    final org.bonitasoft.bpm.model.expression.Expression valueExpression = col.get(1);
                    if (correlationKeyExp.getContent() != null && !correlationKeyExp.getContent().isEmpty()
                            && valueExpression.getContent() != null && !valueExpression.getContent().isEmpty()) {
                        triggerBuilder.addCorrelation(EngineExpressionUtil.createExpression(correlationKeyExp),
                                EngineExpressionUtil.createExpression(valueExpression));
                    }
                }
            }
        }
    }

    @Override
    public FlowElement caseIntermediateThrowMessageEvent(final IntermediateThrowMessageEvent object) {
        final IntermediateThrowEventDefinitionBuilder eventBuilder = builder
                .addIntermediateThrowEvent(object.getName());
        for (final Message message : object.getEvents()) {
            try {
                final ThrowMessageEventTriggerBuilder triggerBuilder = eventBuilder.addMessageEventTrigger(
                        message.getName(), EngineExpressionUtil.createExpression(message.getTargetProcessExpression()),
                        EngineExpressionUtil.createExpression(message.getTargetElementExpression()));
                if (message.getMessageContent() != null) {
                    addThrowMessageContent(message, triggerBuilder);
                }
                final Correlation correlation = message.getCorrelation();
                if (correlation != null && correlation.getCorrelationType() != CorrelationTypeActive.INACTIVE) {
                    addThrowMessageCorrelation(message, triggerBuilder);
                }
            } catch (InvalidExpressionException e) {
                throw new ProcessBuilderException(e);
            }
        }
        addDescription(eventBuilder, object.getDocumentation());
        return object;
    }

    private void addThrowMessageCorrelation(final Message message, final ThrowMessageEventTriggerBuilder triggerBuilder)
            throws InvalidExpressionException {
        for (final ListExpression row : message.getCorrelation().getCorrelationAssociation().getExpressions()) {
            final List<org.bonitasoft.bpm.model.expression.Expression> col = row.getExpressions();
            if (col.size() == 2) {
                final org.bonitasoft.bpm.model.expression.Expression correlationKeyExp = col.get(0);
                final org.bonitasoft.bpm.model.expression.Expression valueExpression = col.get(1);
                if (correlationKeyExp.getContent() != null && !correlationKeyExp.getContent().isEmpty()
                        && valueExpression.getContent() != null && !valueExpression.getContent().isEmpty()) {
                    triggerBuilder.addCorrelation(EngineExpressionUtil.createExpression(correlationKeyExp),
                            EngineExpressionUtil.createExpression(valueExpression));
                }
            }
        }
    }

    private void addThrowMessageContent(final Message message, final ThrowMessageEventTriggerBuilder triggerBuilder)
            throws InvalidExpressionException {
        for (final ListExpression row : message.getMessageContent().getExpressions()) {
            final List<org.bonitasoft.bpm.model.expression.Expression> col = row.getExpressions();
            final org.bonitasoft.bpm.model.expression.Expression idExp = col.get(0);
            final org.bonitasoft.bpm.model.expression.Expression messageContentExp = col.get(1);
            if (col.size() == 2 && idExp.getContent() != null && !idExp.getContent().isEmpty()
                    && messageContentExp.getContent() != null && !messageContentExp.getContent().isEmpty()) {
                triggerBuilder.addMessageContentExpression(EngineExpressionUtil.createExpression(idExp),
                        EngineExpressionUtil.createExpression(messageContentExp));
            }
        }
    }

    @Override
    public FlowElement caseEndMessageEvent(final EndMessageEvent object) {
        final EndEventDefinitionBuilder eventBuilder = builder.addEndEvent(object.getName());
        for (final Message message : object.getEvents()) {
            try {
                final ThrowMessageEventTriggerBuilder triggerBuilder = eventBuilder.addMessageEventTrigger(
                        message.getName(), EngineExpressionUtil.createExpression(message.getTargetProcessExpression()),
                        EngineExpressionUtil.createExpression(message.getTargetElementExpression()));
                if (message.getMessageContent() != null) {
                    addMessageContent(message, triggerBuilder);
                }
                final Correlation correlation = message.getCorrelation();
                if (correlation != null && correlation.getCorrelationType() != CorrelationTypeActive.INACTIVE) {
                    addThrowMessageCorrelation(message, triggerBuilder);
                }
            } catch (InvalidExpressionException e) {
                throw new ProcessBuilderException(e);
            }
        }
        addDescription(eventBuilder, object.getDocumentation());
        return object;
    }

    private void addMessageContent(final Message message, final ThrowMessageEventTriggerBuilder triggerBuilder)
            throws InvalidExpressionException {
        for (final ListExpression row : message.getMessageContent().getExpressions()) {
            final List<org.bonitasoft.bpm.model.expression.Expression> col = row.getExpressions();
            if (col.size() == 2) {
                final org.bonitasoft.bpm.model.expression.Expression idExp = col.get(0);
                final org.bonitasoft.bpm.model.expression.Expression messageContentExp = col.get(1);
                if (idExp.getContent() != null && !idExp.getContent().isEmpty()
                        && messageContentExp.getContent() != null && !messageContentExp.getContent().isEmpty()) {
                    triggerBuilder.addMessageContentExpression(EngineExpressionUtil.createExpression(idExp),
                            EngineExpressionUtil.createExpression(messageContentExp));
                }
            }
        }
    }

    @Override
    public Element caseStartSignalEvent(final StartSignalEvent object) {
        final StartEventDefinitionBuilder eventBuilder = builder.addStartEvent(object.getName());
        final String signal = object.getSignalCode();
        if (signal != null) {
            eventBuilder.addSignalEventTrigger(signal);
        }
        addDescription(eventBuilder, object.getDocumentation());
        return object;
    }

    @Override
    public EndSignalEvent caseEndSignalEvent(final EndSignalEvent object) {
        final EndEventDefinitionBuilder eventBuilder = builder.addEndEvent(object.getName());
        final String signalCode = object.getSignalCode();
        if (signalCode != null) {
            eventBuilder.addSignalEventTrigger(signalCode);
        }
        addDescription(eventBuilder, object.getDocumentation());
        return object;
    }

    @Override
    public IntermediateCatchSignalEvent caseIntermediateCatchSignalEvent(final IntermediateCatchSignalEvent object) {
        final IntermediateCatchEventDefinitionBuilder eventBuilder = builder
                .addIntermediateCatchEvent(object.getName());
        final String signal = object.getSignalCode();
        if (signal != null) {
            eventBuilder.addSignalEventTrigger(signal);
        }
        addDescription(eventBuilder, object.getDocumentation());
        return object;
    }

    @Override
    public IntermediateThrowSignalEvent caseIntermediateThrowSignalEvent(final IntermediateThrowSignalEvent object) {
        final IntermediateThrowEventDefinitionBuilder eventBuilder = builder
                .addIntermediateThrowEvent(object.getName());
        final String signal = object.getSignalCode();
        if (signal != null) {
            eventBuilder.addSignalEventTrigger(signal);
        }
        addDescription(eventBuilder, object.getDocumentation());
        return object;
    }

    @Override
    public CallActivity caseCallActivity(final CallActivity object) {
        Expression version = object.getCalledActivityVersion();
        if (version == null || version.getContent() == null || version.getContent().trim().isEmpty()) {
            version = null; // latest version will be used by the engine
        }
        try {
            final CallActivityBuilder activityBuilder = builder.addCallActivity(object.getName(),
                    EngineExpressionUtil.createExpression(object.getCalledActivityName()),
                    EngineExpressionUtil.createExpression(version));
            exportInputMappingsForCallActivity(object, activityBuilder);
            exportOutputMappingForCallActivities(object, activityBuilder);

            handleCommonActivity(object, activityBuilder);
        } catch (InvalidExpressionException e) {
            throw new ProcessBuilderException(e);
        }
        return object;
    }

    private void exportInputMappingsForCallActivity(final CallActivity callActivity,
            final CallActivityBuilder activityBuilder) throws InvalidExpressionException {
        for (final InputMapping mapping : callActivity.getInputMappings()) {
            if (InputMappingAssignationType.DATA == mapping.getAssignationType()) {
                exportInputMappingAssignedToDataForCallActivity(activityBuilder, callActivity, mapping);
            } else {
                exportInputMappingAssignedToContractInputForCallActivity(activityBuilder, mapping);
            }
        }
    }

    private void exportInputMappingAssignedToContractInputForCallActivity(final CallActivityBuilder activityBuilder,
            final InputMapping mapping) throws InvalidExpressionException {
        activityBuilder.addProcessStartContractInput(mapping.getSubprocessTarget(),
                EngineExpressionUtil.createExpression(mapping.getProcessSource()));
    }

    private void exportInputMappingAssignedToDataForCallActivity(final CallActivityBuilder activityBuilder,
            CallActivity callActivity, final InputMapping mapping) throws InvalidExpressionException {
        final OperationBuilder opBuilder = new OperationBuilder();
        opBuilder.createNewInstance();
        opBuilder.setRightOperand(EngineExpressionUtil.createExpression(mapping.getProcessSource()));
        var leftOperandbuilder = new LeftOperandBuilder();
        leftOperandbuilder.createNewInstance();
        String subprocessTarget = mapping.getSubprocessTarget();
        leftOperandbuilder.setName(subprocessTarget);

        Optional<Data> targetData = modelSearch.findProcess(
                callActivity.getCalledActivityName() != null ? callActivity.getCalledActivityName().getContent() : null,
                callActivity.getCalledActivityVersion() != null ? callActivity.getCalledActivityVersion().getContent()
                        : null)
                .map(AbstractProcess::getData).map(Collection::stream).orElse(Stream.empty())
                .filter(data -> subprocessTarget.equals(data.getName())).findFirst();
        if (targetData.isPresent()) {
            leftOperandbuilder.setType(targetData.get() instanceof BusinessObjectData ? LeftOperand.TYPE_BUSINESS_DATA
                    : LeftOperand.TYPE_DATA);
        } else {
            final List<EObject> referencedElements = mapping.getProcessSource().getReferencedElements();
            String type = LeftOperand.TYPE_DATA;
            if (!referencedElements.isEmpty()) {
                type = getLeftOperandTypeForData(referencedElements.get(0));
            }
            leftOperandbuilder.setType(type);
        }
        opBuilder.setLeftOperand(leftOperandbuilder.done());
        opBuilder.setType(OperatorType.ASSIGNMENT);
        activityBuilder.addDataInputOperation(opBuilder.done());
    }

    private void exportOutputMappingForCallActivities(final CallActivity object,
            final CallActivityBuilder activityBuilder) throws InvalidExpressionException {
        for (final OutputMapping mapping : object.getOutputMappings()) {
            final OperationBuilder opBuilder = new OperationBuilder();
            opBuilder.createNewInstance();
            final Data d = EcoreUtil.copy(mapping.getProcessTarget());
            d.setName(mapping.getSubprocessSource());
            opBuilder.setRightOperand(EngineExpressionUtil.createVariableExpression(d));
            var leftOperandbuilder = new LeftOperandBuilder();
            leftOperandbuilder.createNewInstance();
            leftOperandbuilder.setName(mapping.getProcessTarget().getName());
            leftOperandbuilder.setType(getLeftOperandTypeForData(mapping.getProcessTarget()));
            opBuilder.setLeftOperand(leftOperandbuilder.done());
            opBuilder.setType(OperatorType.ASSIGNMENT);
            activityBuilder.addDataOutputOperation(opBuilder.done());
        }
    }

    private String getLeftOperandTypeForData(final EObject data) {
        if (data instanceof BusinessObjectData) {
            return LeftOperand.TYPE_BUSINESS_DATA;
        }
        return LeftOperand.TYPE_DATA;
    }

    @Override
    public Task caseTask(final Task task) {
        String actor = null;
        ActorFilter filter = null;
        if (!task.getFilters().isEmpty()) {
            filter = task.getFilters().get(0);
        }
        if (task.isOverrideActorsOfTheLane()) {
            if (task.getActor() != null) {
                actor = task.getActor().getName();
            }
        } else {
            final Lane lane = modelSearch.getDirectParentOfType(task, Lane.class);
            if (lane != null && lane.getActor() != null) {
                actor = lane.getActor().getName();
            }
            if (lane != null && task.getFilters().isEmpty() && !lane.getFilters().isEmpty()) {
                filter = lane.getFilters().get(0);
            }
        }

        try {
            final UserTaskDefinitionBuilder taskBuilder = builder.addUserTask(task.getName(), actor);
            taskBuilder.addPriority(TaskPriority.values()[task.getPriority()].name());
            addExpectedDuration(taskBuilder, task);
            addUserFilterToTask(taskBuilder, filter);
            addTaskContract(taskBuilder, task);
            addContext(taskBuilder, task);

            handleCommonActivity(task, taskBuilder);
        } catch (InvalidExpressionException e) {
            throw new ProcessBuilderException(e);
        }
        return task;
    }

    private void addTaskContract(UserTaskDefinitionBuilder taskBuilder, Task task) {
        if (task != null && task.getContract() != null) {
            contractBuilder.setEngineBuilder(taskBuilder);
            try {
                contractBuilder.build(task.getContract());
            } catch (BuildProcessDefinitionException e) {
                throw new ProcessBuilderException(e);
            }
        }
    }

    private void addUserFilterToTask(final UserTaskDefinitionBuilder taskBuilder, final ActorFilter filter)
            throws InvalidExpressionException {
        if (filter != null) {
            final UserFilterDefinitionBuilder filterBuilder = taskBuilder.addUserFilter(filter.getName(),
                    filter.getDefinitionId(), filter.getDefinitionVersion());
            for (final ConnectorParameter parameter : filter.getConfiguration().getParameters()) {
                final org.bonitasoft.engine.expression.Expression inputExpression = EngineExpressionUtil
                        .createExpression(parameter.getExpression());
                if (inputExpression != null) {
                    filterBuilder.addInput(parameter.getKey(), inputExpression);
                }
            }
        }
    }

    @Override
    public StartEvent caseStartEvent(final StartEvent startEvent) {
        final StartEventDefinitionBuilder eventBuilder = builder.addStartEvent(startEvent.getName());
        addDescription(eventBuilder, startEvent.getDocumentation());
        return startEvent;
    }

    @Override
    public FlowElement caseStartTimerEvent(final StartTimerEvent startTimer) {
        final StartEventDefinitionBuilder startTimerBuilder = builder.addStartEvent(startTimer.getName());
        try {
            final org.bonitasoft.engine.expression.Expression startConditionExpression = EngineExpressionUtil
                    .createExpression(startTimer.getCondition());
            if (modelSearch.isInEvenementialSubProcessPool(startTimer)) {
                final TimerType timerType = getTimerType(startTimer);
                if (timerType != null) {
                    startTimerBuilder.addTimerEventTriggerDefinition(timerType, startConditionExpression);
                }
            } else {
                TimerType type;
                if (startConditionExpression.getReturnType().equals(String.class.getName())) {
                    type = TimerType.CYCLE;
                } else if (startConditionExpression.getReturnType().equals(Date.class.getName())) {
                    type = TimerType.DATE;
                } else if (startConditionExpression.getReturnType().equals(Long.class.getName())) {
                    type = TimerType.DURATION;
                } else {
                    throw new IllegalArgumentException(
                            "Unsupported return type " + startConditionExpression.getReturnType()
                                    + " for Start timer condition " + startTimer.getName());
                }
                startTimerBuilder.addTimerEventTriggerDefinition(type, startConditionExpression);
            }
        } catch (InvalidExpressionException e) {
            throw new ProcessBuilderException(e);
        }
        addDescription(startTimerBuilder, startTimer.getDocumentation());
        return startTimer;
    }

    @Override
    public FlowElement caseIntermediateCatchTimerEvent(final IntermediateCatchTimerEvent timer) {
        final IntermediateCatchEventDefinitionBuilder timerBuilder = builder.addIntermediateCatchEvent(timer.getName());
        try {
            final TimerType timerType = getTimerType(timer);
            if (timerType != null) {
                timerBuilder.addTimerEventTriggerDefinition(timerType,
                        EngineExpressionUtil.createExpression(timer.getCondition()));
            }
        } catch (InvalidExpressionException e) {
            throw new ProcessBuilderException(e);
        }
        addDescription(timerBuilder, timer.getDocumentation());
        return timer;
    }

    private TimerType getTimerType(final AbstractTimerEvent timer) {
        if (isDuration(timer)) {
            return TimerType.DURATION;
        } else {
            final String timerConditionReturnType = timer.getCondition().getReturnType();
            try {
                if (Number.class.isAssignableFrom(Class.forName(timerConditionReturnType))) {
                    return TimerType.DURATION;
                } else if (Date.class.getName().equals(timerConditionReturnType)) {
                    return TimerType.DATE;
                }
            } catch (final ClassNotFoundException e) {
                throw new IllegalArgumentException(
                        String.format("Timer condition return type '%s' is not supported.", timerConditionReturnType),
                        e);
            }
        }
        return null;
    }

    private boolean isDuration(final AbstractTimerEvent timer) {
        final Expression exp = timer.getCondition();
        if (exp != null) {
            return isDuration(exp.getContent());
        }
        return false;
    }

    private static boolean isDuration(String event) {
        long r = -1;
        try {
            r = Long.parseLong(event);
        } catch (NumberFormatException e) {
            return false;
        }
        return String.valueOf(r).equals(event);
    }

    @Override
    public FlowElement caseEndEvent(final EndEvent endEvent) {
        final EndEventDefinitionBuilder eventBuilder = builder.addEndEvent(endEvent.getName());
        addDescription(eventBuilder, endEvent.getDocumentation());
        return endEvent;
    }

    @Override
    public Element caseStartErrorEvent(final StartErrorEvent startErrorEvent) {
        final StartEventDefinitionBuilder eventBuilder = builder.addStartEvent(startErrorEvent.getName());
        eventBuilder.addErrorEventTrigger(startErrorEvent.getErrorCode());
        addDescription(eventBuilder, startErrorEvent.getDocumentation());
        return startErrorEvent;
    }

    @Override
    public Element caseEndErrorEvent(final EndErrorEvent endErrorEvent) {
        final EndEventDefinitionBuilder eventBuilder = builder.addEndEvent(endErrorEvent.getName());
        eventBuilder.addErrorEventTrigger(endErrorEvent.getErrorCode());
        addDescription(eventBuilder, endErrorEvent.getDocumentation());
        return endErrorEvent;
    }

    @Override
    public FlowElement caseEndTerminatedEvent(final EndTerminatedEvent endTerminatedEvent) {
        final EndEventDefinitionBuilder eventBuilder = builder.addEndEvent(endTerminatedEvent.getName());
        eventBuilder.addTerminateEventTrigger();
        addDescription(eventBuilder, endTerminatedEvent.getDocumentation());
        return endTerminatedEvent;
    }

    @Override
    public FlowElement caseANDGateway(final org.bonitasoft.bpm.model.process.ANDGateway gateway) {
        final GatewayDefinitionBuilder gatewayBuilder = builder.addGateway(gateway.getName(), GatewayType.PARALLEL);
        try {
            addDisplayTitle(gatewayBuilder, gateway);
            addDisplayDescription(gatewayBuilder, gateway);
            addDisplayDescriptionAfterCompletion(gatewayBuilder, gateway);
        } catch (InvalidExpressionException e) {
            throw new ProcessBuilderException(e);
        }
        return gateway;
    }

    @Override
    public FlowElement caseInclusiveGateway(final org.bonitasoft.bpm.model.process.InclusiveGateway gateway) {
        final GatewayDefinitionBuilder gatewayBuilder = builder.addGateway(gateway.getName(), GatewayType.INCLUSIVE);
        try {
            addDisplayTitle(gatewayBuilder, gateway);
            addDisplayDescription(gatewayBuilder, gateway);
            addDisplayDescriptionAfterCompletion(gatewayBuilder, gateway);
        } catch (InvalidExpressionException e) {
            throw new ProcessBuilderException(e);
        }
        return gateway;
    }

    @Override
    public FlowElement caseXORGateway(final org.bonitasoft.bpm.model.process.XORGateway gateway) {
        final GatewayDefinitionBuilder gatewayBuilder = builder.addGateway(gateway.getName(), GatewayType.EXCLUSIVE);
        try {
            addDisplayTitle(gatewayBuilder, gateway);
            addDisplayTitle(gatewayBuilder, gateway);
            addDisplayDescription(gatewayBuilder, gateway);
            addDisplayDescriptionAfterCompletion(gatewayBuilder, gateway);
        } catch (InvalidExpressionException e) {
            throw new ProcessBuilderException(e);
        }
        return gateway;
    }

    private void handleCommonActivity(final Activity activity, final ActivityDefinitionBuilder taskBuilder) {
        try {
            addData(taskBuilder, activity);
            addOperation(taskBuilder, activity);
            addConnector(taskBuilder, activity);
            addKPIBinding(taskBuilder, activity);
            addDisplayTitle(taskBuilder, activity);
            addDescription(taskBuilder, activity.getDocumentation());
            addDisplayDescription(taskBuilder, activity);
            addDisplayDescriptionAfterCompletion(taskBuilder, activity);
            addMultiInstantiation(taskBuilder, activity);
            addBoundaryEvents(taskBuilder, activity);
            addDescription(taskBuilder, activity.getDocumentation());
        } catch (InvalidExpressionException e) {
            throw new ProcessBuilderException(e);
        }
    }

    private void addBoundaryEvents(final ActivityDefinitionBuilder taskBuilder, final Activity activity)
            throws InvalidExpressionException {
        for (final BoundaryEvent boundaryEvent : activity.getBoundaryIntermediateEvents()) {
            final BoundaryEventDefinitionBuilder boundaryEventBuilder = taskBuilder.addBoundaryEvent(
                    boundaryEvent.getName(), !(boundaryEvent instanceof NonInterruptingBoundaryTimerEvent));
            if (boundaryEvent instanceof IntermediateErrorCatchEvent) {
                String errorCode = ((IntermediateErrorCatchEvent) boundaryEvent).getErrorCode();
                if (errorCode != null && errorCode.trim().isEmpty()) {
                    errorCode = null;
                }
                boundaryEventBuilder.addErrorEventTrigger(errorCode);
            } else if (boundaryEvent instanceof BoundaryMessageEvent) {
                final CatchMessageEventTriggerDefinitionBuilder catchMessageEventTriggerDefinitionBuilder = boundaryEventBuilder
                        .addMessageEventTrigger(((BoundaryMessageEvent) boundaryEvent).getEvent());
                addMessageContent((BoundaryMessageEvent) boundaryEvent, catchMessageEventTriggerDefinitionBuilder);
                addMessageCorrelation((BoundaryMessageEvent) boundaryEvent, catchMessageEventTriggerDefinitionBuilder);
            } else if (boundaryEvent instanceof BoundaryTimerEvent) {
                final TimerType timerType = getTimerType((BoundaryTimerEvent) boundaryEvent);
                if (timerType != null) {
                    boundaryEventBuilder.addTimerEventTriggerDefinition(timerType,
                            EngineExpressionUtil.createExpression(((AbstractTimerEvent) boundaryEvent).getCondition()));
                }
            } else if (boundaryEvent instanceof BoundarySignalEvent) {
                boundaryEventBuilder.addSignalEventTrigger(((BoundarySignalEvent) boundaryEvent).getSignalCode());
            }
        }
    }

    private void addMultiInstantiation(final ActivityDefinitionBuilder taskBuilder, final Activity activity)
            throws InvalidExpressionException {
        final MultiInstanceType multiInstanceType = activity.getType();
        switch (multiInstanceType) {
            case NONE:
                break;
            case STANDARD:
                addStandardLoop(taskBuilder, activity);
                break;
            case PARALLEL:
            case SEQUENTIAL:
                configureMultiInstantiation(taskBuilder, activity);
                break;
            default:
                break;
        }
    }

    private void addExpectedDuration(final UserTaskDefinitionBuilder taskBuilder, final Task task)
            throws InvalidExpressionException {
        final Expression duration = task.getExpectedDuration();
        if (duration != null && duration.hasContent()) {
            taskBuilder.addExpectedDuration(EngineExpressionUtil.createExpression(duration));
        }
    }

    private void addDisplayDescription(final ActivityDefinitionBuilder builder, final FlowElement flowElement)
            throws InvalidExpressionException {
        final org.bonitasoft.engine.expression.Expression exp = EngineExpressionUtil
                .createExpression(flowElement.getDynamicDescription());
        if (exp != null) {
            builder.addDisplayDescription(exp);
        }
    }

    private void addDisplayDescriptionAfterCompletion(final ActivityDefinitionBuilder builder,
            final FlowElement flowElement) throws InvalidExpressionException {
        final org.bonitasoft.engine.expression.Expression exp = EngineExpressionUtil
                .createExpression(flowElement.getStepSummary());
        if (exp != null) {
            builder.addDisplayDescriptionAfterCompletion(exp);
        }
    }

    private void addDisplayTitle(final ActivityDefinitionBuilder builder, final FlowElement flowElement)
            throws InvalidExpressionException {
        final org.bonitasoft.engine.expression.Expression exp = EngineExpressionUtil
                .createExpression(flowElement.getDynamicLabel());
        if (exp != null) {
            builder.addDisplayName(exp);
        }
    }

    private void addDisplayDescription(final GatewayDefinitionBuilder builder, final FlowElement flowElement)
            throws InvalidExpressionException {
        final org.bonitasoft.engine.expression.Expression exp = EngineExpressionUtil
                .createExpression(flowElement.getDynamicDescription());
        if (exp != null) {
            builder.addDisplayDescription(exp);
        }
    }

    private void addDisplayTitle(final GatewayDefinitionBuilder builder, final FlowElement flowElement)
            throws InvalidExpressionException {
        final org.bonitasoft.engine.expression.Expression exp = EngineExpressionUtil
                .createExpression(flowElement.getDynamicLabel());
        if (exp != null) {
            builder.addDisplayName(exp);
        }
    }

    private void addDisplayDescriptionAfterCompletion(final GatewayDefinitionBuilder builder,
            final FlowElement flowElement) throws InvalidExpressionException {
        final org.bonitasoft.engine.expression.Expression exp = EngineExpressionUtil
                .createExpression(flowElement.getStepSummary());
        if (exp != null) {
            builder.addDisplayDescriptionAfterCompletion(exp);
        }
    }

    private void addOperation(final ActivityDefinitionBuilder builder, final OperationContainer activity)
            throws InvalidExpressionException {
        for (final Operation operation : activity.getOperations()) {
            if (operation.getLeftOperand() != null && operation.getLeftOperand().hasContent()
                    && operation.getRightOperand() != null && operation.getRightOperand().getContent() != null) {
                if (ExpressionConstants.SEARCH_INDEX_TYPE.equals(operation.getLeftOperand().getType())) {
                    addSearchIndexOperation(builder, activity, operation);
                } else {
                    builder.addOperation(EngineExpressionUtil.createOperation(operation));
                }
            }
        }
    }

    private void addSearchIndexOperation(final ActivityDefinitionBuilder builder, final OperationContainer activity,
            final Operation operation) throws InvalidExpressionException {
        // get the pool to get the list of searchIndex list
        Pool pool = null;
        if (activity.eContainer() instanceof Pool) {
            pool = (Pool) activity.eContainer();
        } else if (activity.eContainer().eContainer() instanceof Pool) {
            pool = (Pool) activity.eContainer().eContainer();
        }
        // get the searchIndex list
        List<SearchIndex> searchIndexList = new ArrayList<>();
        if (pool != null) {
            searchIndexList = pool.getSearchIndexes();
        }
        int idx = 1;
        for (final SearchIndex searchIdx : searchIndexList) {
            // get the related searchIndex to set the operation
            if (searchIdx.getName().getContent().equals(operation.getLeftOperand().getName())) {
                builder.addOperation(EngineExpressionUtil.createOperation(operation,
                        EngineExpressionUtil.createLeftOperandIndex(idx)));
                break;
            }
            idx++;
        }
    }

    private void addStandardLoop(final ActivityDefinitionBuilder builder, final MultiInstantiable multiInstantiable)
            throws InvalidExpressionException {
        builder.addLoop(multiInstantiable.getTestBefore(),
                EngineExpressionUtil.createExpression(multiInstantiable.getLoopCondition()),
                EngineExpressionUtil.createExpression(multiInstantiable.getLoopMaximum()));
    }

    private void configureMultiInstantiation(final ActivityDefinitionBuilder taskBuilder,
            final MultiInstantiable activity) throws InvalidExpressionException {
        final Expression completionCondition = activity.getCompletionCondition();
        if (activity.isUseCardinality()) {
            configureCardinalityMultiInstance(taskBuilder, activity, completionCondition);
        } else {
            configureCollectionMultiInstance(taskBuilder, activity, completionCondition);
        }
    }

    private void configureCollectionMultiInstance(final ActivityDefinitionBuilder taskBuilder,
            final MultiInstantiable activity, final Expression completionCondition) throws InvalidExpressionException {
        final Data collectionDataToMultiInstantiate = activity.getCollectionDataToMultiInstantiate();
        final Expression iteratorExpression = activity.getIteratorExpression();
        if (ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE.equals(iteratorExpression.getType())
                && iteratorExpression.getName() != null && !iteratorExpression.getName().isEmpty()
                && activity instanceof DataAware) {
            addDataForMultiInstanceIterator(taskBuilder, iteratorExpression, collectionDataToMultiInstantiate);
        }
        if (collectionDataToMultiInstantiate != null) {
            addMultiInstanceCollection(taskBuilder, activity, completionCondition, collectionDataToMultiInstantiate,
                    iteratorExpression);
        }
    }

    private void addMultiInstanceCollection(final ActivityDefinitionBuilder taskBuilder,
            final MultiInstantiable activity, final Expression completionCondition,
            final Data collectionDataToMultiInstantiate, final Expression iteratorExpression)
            throws InvalidExpressionException {
        final MultiInstanceLoopCharacteristicsBuilder multiInstanceBuilder = taskBuilder.addMultiInstance(
                activity.getType() == MultiInstanceType.SEQUENTIAL, collectionDataToMultiInstantiate.getName());
        if (completionCondition != null && completionCondition.getContent() != null
                && !completionCondition.getContent().isEmpty()) {
            multiInstanceBuilder.addCompletionCondition(EngineExpressionUtil.createExpression(completionCondition));
        }

        if (iteratorExpression.getName() != null && !iteratorExpression.getName().isEmpty()) {
            multiInstanceBuilder.addDataInputItemRef(iteratorExpression.getName());
        }
        if (activity.isStoreOutput()) {
            final Data outputData = activity.getOutputData();
            if (outputData != null) {
                multiInstanceBuilder.addDataOutputItemRef(outputData.getName());
            }
            final Data listDataContainingOutputResults = activity.getListDataContainingOutputResults();
            if (listDataContainingOutputResults != null) {
                multiInstanceBuilder.addLoopDataOutputRef(listDataContainingOutputResults.getName());
            }
        }
    }

    private void configureCardinalityMultiInstance(final ActivityDefinitionBuilder taskBuilder,
            final MultiInstantiable activity, final Expression completionCondition) throws InvalidExpressionException {
        final Expression cardinality = activity.getCardinalityExpression();
        if (cardinality != null && cardinality.getContent() != null && !cardinality.getContent().isEmpty()) {
            final MultiInstanceLoopCharacteristicsBuilder multiInstanceBuilder = taskBuilder.addMultiInstance(
                    activity.getType() == MultiInstanceType.SEQUENTIAL,
                    EngineExpressionUtil.createExpression(cardinality));
            if (completionCondition != null && completionCondition.getContent() != null
                    && !completionCondition.getContent().isEmpty()) {
                multiInstanceBuilder.addCompletionCondition(EngineExpressionUtil.createExpression(completionCondition));
            }
            if (activity.isStoreOutput()) {
                final Data outputData = activity.getOutputData();
                if (outputData != null) {
                    multiInstanceBuilder.addDataOutputItemRef(outputData.getName());
                }
                final Data listDataContainingOutputResults = activity.getListDataContainingOutputResults();
                if (listDataContainingOutputResults != null) {
                    multiInstanceBuilder.addLoopDataOutputRef(listDataContainingOutputResults.getName());
                }
            }
        }
    }

    private void addDataForMultiInstanceIterator(final ActivityDefinitionBuilder taskBuilder,
            final Expression iteratorExpression, final Data collectionDataToMultiInstantiate) {
        if (collectionDataToMultiInstantiate instanceof BusinessObjectData) {
            taskBuilder.addBusinessData(iteratorExpression.getName(), iteratorExpression.getReturnType());
        } else {
            final FlowElement parentFlowElement = modelSearch.getDirectParentOfType(iteratorExpression,
                    FlowElement.class);
            if (parentFlowElement instanceof DataAware
                    && !isDataAlreadyExists(iteratorExpression, (DataAware) parentFlowElement)) {
                taskBuilder.addData(iteratorExpression.getName(), iteratorExpression.getReturnType(), null);
            }
        }
    }

    private boolean isDataAlreadyExists(final Expression iteratorExpression, final DataAware parentFlowElement) {
        return parentFlowElement.getData().stream()
                .anyMatch(data -> Objects.equals(data.getName(), iteratorExpression.getName())
                        && Objects.equals(DataUtil.getTechnicalTypeFor(data), iteratorExpression.getReturnType()));
    }

}
