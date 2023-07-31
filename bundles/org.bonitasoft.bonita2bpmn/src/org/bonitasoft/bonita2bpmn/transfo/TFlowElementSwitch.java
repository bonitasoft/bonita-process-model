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
package org.bonitasoft.bonita2bpmn.transfo;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Objects;

import javax.xml.namespace.QName;

import org.bonitasoft.bonita2bpmn.transfo.data.DataIOTransformer;
import org.bonitasoft.bonita2bpmn.transfo.expression.FormalExpressionFunctionFactory;
import org.bonitasoft.bonita2bpmn.util.Strings;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.expression.Operation;
import org.bonitasoft.bpm.model.process.ANDGateway;
import org.bonitasoft.bpm.model.process.AbstractTimerEvent;
import org.bonitasoft.bpm.model.process.Activity;
import org.bonitasoft.bpm.model.process.Actor;
import org.bonitasoft.bpm.model.process.CallActivity;
import org.bonitasoft.bpm.model.process.CatchLinkEvent;
import org.bonitasoft.bpm.model.process.Connector;
import org.bonitasoft.bpm.model.process.Data;
import org.bonitasoft.bpm.model.process.EndErrorEvent;
import org.bonitasoft.bpm.model.process.EndEvent;
import org.bonitasoft.bpm.model.process.EndMessageEvent;
import org.bonitasoft.bpm.model.process.EndSignalEvent;
import org.bonitasoft.bpm.model.process.EndTerminatedEvent;
import org.bonitasoft.bpm.model.process.InclusiveGateway;
import org.bonitasoft.bpm.model.process.InputMapping;
import org.bonitasoft.bpm.model.process.IntermediateCatchMessageEvent;
import org.bonitasoft.bpm.model.process.IntermediateCatchSignalEvent;
import org.bonitasoft.bpm.model.process.IntermediateCatchTimerEvent;
import org.bonitasoft.bpm.model.process.IntermediateThrowMessageEvent;
import org.bonitasoft.bpm.model.process.IntermediateThrowSignalEvent;
import org.bonitasoft.bpm.model.process.LinkEvent;
import org.bonitasoft.bpm.model.process.Message;
import org.bonitasoft.bpm.model.process.MultiInstanceType;
import org.bonitasoft.bpm.model.process.MultiInstantiable;
import org.bonitasoft.bpm.model.process.OutputMapping;
import org.bonitasoft.bpm.model.process.ReceiveTask;
import org.bonitasoft.bpm.model.process.ScriptTask;
import org.bonitasoft.bpm.model.process.SendTask;
import org.bonitasoft.bpm.model.process.ServiceTask;
import org.bonitasoft.bpm.model.process.SignalEvent;
import org.bonitasoft.bpm.model.process.StartErrorEvent;
import org.bonitasoft.bpm.model.process.StartEvent;
import org.bonitasoft.bpm.model.process.StartMessageEvent;
import org.bonitasoft.bpm.model.process.StartSignalEvent;
import org.bonitasoft.bpm.model.process.StartTimerEvent;
import org.bonitasoft.bpm.model.process.SubProcessEvent;
import org.bonitasoft.bpm.model.process.Task;
import org.bonitasoft.bpm.model.process.ThrowLinkEvent;
import org.bonitasoft.bpm.model.process.XORGateway;
import org.bonitasoft.bpm.model.process.util.ProcessSwitch;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.bonitasoft.bpm.model.util.IModelSearch;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.omg.spec.bpmn.model.ModelFactory;
import org.omg.spec.bpmn.model.TActivity;
import org.omg.spec.bpmn.model.TAssignment;
import org.omg.spec.bpmn.model.TCallActivity;
import org.omg.spec.bpmn.model.TCatchEvent;
import org.omg.spec.bpmn.model.TDataInput;
import org.omg.spec.bpmn.model.TDataInputAssociation;
import org.omg.spec.bpmn.model.TDataOutput;
import org.omg.spec.bpmn.model.TDataOutputAssociation;
import org.omg.spec.bpmn.model.TEndEvent;
import org.omg.spec.bpmn.model.TErrorEventDefinition;
import org.omg.spec.bpmn.model.TExclusiveGateway;
import org.omg.spec.bpmn.model.TExpression;
import org.omg.spec.bpmn.model.TFlowElement;
import org.omg.spec.bpmn.model.TFormalExpression;
import org.omg.spec.bpmn.model.TInclusiveGateway;
import org.omg.spec.bpmn.model.TInputOutputSpecification;
import org.omg.spec.bpmn.model.TIntermediateCatchEvent;
import org.omg.spec.bpmn.model.TIntermediateThrowEvent;
import org.omg.spec.bpmn.model.TItemDefinition;
import org.omg.spec.bpmn.model.TLinkEventDefinition;
import org.omg.spec.bpmn.model.TLoopCharacteristics;
import org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics;
import org.omg.spec.bpmn.model.TOutputSet;
import org.omg.spec.bpmn.model.TParallelGateway;
import org.omg.spec.bpmn.model.TPerformer;
import org.omg.spec.bpmn.model.TReceiveTask;
import org.omg.spec.bpmn.model.TResourceRole;
import org.omg.spec.bpmn.model.TScriptTask;
import org.omg.spec.bpmn.model.TSendTask;
import org.omg.spec.bpmn.model.TServiceTask;
import org.omg.spec.bpmn.model.TSignal;
import org.omg.spec.bpmn.model.TSignalEventDefinition;
import org.omg.spec.bpmn.model.TStandardLoopCharacteristics;
import org.omg.spec.bpmn.model.TStartEvent;
import org.omg.spec.bpmn.model.TSubProcess;
import org.omg.spec.bpmn.model.TTerminateEventDefinition;
import org.omg.spec.bpmn.model.TThrowEvent;
import org.omg.spec.bpmn.model.TTimerEventDefinition;
import org.omg.spec.bpmn.model.TUserTask;

public class TFlowElementSwitch extends ProcessSwitch<TFlowElement> {

    private static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd/HH/mm/ss";
    private static final String DISPLAY_DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
    static final String EVENTDEF_PREFIX = "eventdef-";

    private IModelSearch modelSearch;
    private ConnectorDefinitionTransformer connectorDefinitionTransformer;
    private FormalExpressionFunctionFactory formalExpressionTransformerFactory;
    private ModelRegistry modelRegistry;

    TFlowElementSwitch(IModelSearch modelSearch, ConnectorDefinitionTransformer connectorDefinitionTransformer,
            FormalExpressionFunctionFactory formalExpressionTransformerFactory, ModelRegistry modelRegistry) {
        this.modelSearch = modelSearch;
        this.connectorDefinitionTransformer = connectorDefinitionTransformer;
        this.formalExpressionTransformerFactory = formalExpressionTransformerFactory;
        this.modelRegistry = modelRegistry;
    }

    @Override
    public TActivity caseActivity(Activity object) {
        var task = ModelFactory.eINSTANCE.createTTask();
        handleMultiInstance(object, task);
        return task;
    }

    @Override
    public TServiceTask caseServiceTask(ServiceTask object) {
        var serviceTask = ModelFactory.eINSTANCE.createTServiceTask();
        handleMultiInstance(object, serviceTask);
        if (!object.getConnectors().isEmpty()) {
            handleConnectorOnServiceTask(object, serviceTask);
        }
        return serviceTask;
    }

    @Override
    public TScriptTask caseScriptTask(ScriptTask object) {
        var scriptTask = ModelFactory.eINSTANCE.createTScriptTask();
        handleMultiInstance(object, scriptTask);
        return scriptTask;
    }

    @Override
    public TSendTask caseSendTask(SendTask object) {
        var sendTask = ModelFactory.eINSTANCE.createTSendTask();
        handleMultiInstance(object, sendTask);
        return sendTask;
    }

    @Override
    public TReceiveTask caseReceiveTask(ReceiveTask object) {
        var receiveTask = ModelFactory.eINSTANCE.createTReceiveTask();
        handleMultiInstance(object, receiveTask);
        return receiveTask;
    }

    @Override
    public TUserTask caseTask(Task object) {
        var userTask = ModelFactory.eINSTANCE.createTUserTask();
        final Actor actor = object.getActor();
        if (actor != null) {
            final EList<TResourceRole> resourceRoles = userTask.getResourceRole();
            final TPerformer role = ModelFactory.eINSTANCE.createTPerformer();
            role.setResourceRef(QName.valueOf(modelSearch.getEObjectID(actor)));
            role.setId(EcoreUtil.generateUUID());
            resourceRoles.add(role);
        }
        handleMultiInstance(object, userTask);
        return userTask;
    }

    @Override
    public TCallActivity caseCallActivity(CallActivity object) {
        var callActivity = ModelFactory.eINSTANCE.createTCallActivity();
        final Expression calledActivityName = object.getCalledActivityName();
        if (calledActivityName != null
                && ExpressionConstants.CONSTANT_TYPE.equals(calledActivityName.getType())
                && calledActivityName.getContent() != null) {
            final Expression calledVersion = object.getCalledActivityVersion();
            String version = null;
            if (calledVersion != null && calledVersion.getContent() != null
                    && !calledVersion.getContent().isEmpty()) {
                version = calledVersion.getContent();
            }
            modelSearch.findProcess(calledActivityName.getContent(), version).ifPresent(calledProcess -> callActivity
                    .setCalledElement(QName.valueOf(modelSearch.getEObjectID(calledProcess))));
        }
        handleMultiInstance(object, callActivity);
        dataMappingWithCallActivity(object, callActivity);
        return callActivity;
    }

    @Override
    public TParallelGateway caseANDGateway(ANDGateway object) {
        return ModelFactory.eINSTANCE.createTParallelGateway();
    }

    @Override
    public TInclusiveGateway caseInclusiveGateway(InclusiveGateway object) {
        return ModelFactory.eINSTANCE.createTInclusiveGateway();
    }

    @Override
    public TExclusiveGateway caseXORGateway(XORGateway object) {
        return ModelFactory.eINSTANCE.createTExclusiveGateway();
    }

    @Override
    public TSubProcess caseSubProcessEvent(SubProcessEvent object) {
        var eventSubProc = ModelFactory.eINSTANCE.createTSubProcess();
        eventSubProc.setTriggeredByEvent(true);
        return eventSubProc;
    }

    @Override
    public TStartEvent caseStartTimerEvent(StartTimerEvent object) {
        var event = ModelFactory.eINSTANCE.createTStartEvent();
        var eventDef = ModelFactory.eINSTANCE.createTTimerEventDefinition();
        final Expression conditionExpression = object.getCondition();
        if (conditionExpression != null && conditionExpression.hasContent()) {
            createTimerExpression(eventDef, conditionExpression);
        }
        eventDef.setId(EVENTDEF_PREFIX + object.getName());
        event.getEventDefinition().add(eventDef);
        modelRegistry.addError(String.format("Timer definition for event %s not exported", object.getName()));
        return event;
    }

    @Override
    public TStartEvent caseStartErrorEvent(StartErrorEvent object) {
        var event = ModelFactory.eINSTANCE.createTStartEvent();
        final TErrorEventDefinition eventDef = ModelFactory.eINSTANCE.createTErrorEventDefinition();
        event.getEventDefinition().add(eventDef);
        final String errorCode = object.getErrorCode();
        final String eventDefId = errorCode != null ? errorCode + EcoreUtil.generateUUID() : EcoreUtil.generateUUID();
        eventDef.setId(eventDefId);
        if (errorCode != null) {
            eventDef.setErrorRef(QName.valueOf(errorCode));
        }
        return event;
    }

    @Override
    public TStartEvent caseStartMessageEvent(StartMessageEvent object) {
        var event = ModelFactory.eINSTANCE.createTStartEvent();
        final String eventId = object.getEvent() != null && !object.getEvent().isBlank() ? object.getEvent()
                : EcoreUtil.generateUUID();
        event.getEventDefinitionRef().add(QName.valueOf(eventId));
        modelRegistry.addMessage(eventId);
        return event;
    }

    @Override
    public TStartEvent caseStartSignalEvent(StartSignalEvent object) {
        var event = ModelFactory.eINSTANCE.createTStartEvent();
        final TSignal tSignal = getOrCreateTSignal(object);
        final TSignalEventDefinition eventDef = ModelFactory.eINSTANCE.createTSignalEventDefinition();
        eventDef.setSignalRef(QName.valueOf(tSignal.getId()));
        event.getEventDefinition().add(eventDef);
        eventDef.setId(object.getName());
        return event;
    }

    @Override
    public TStartEvent caseStartEvent(StartEvent object) {
        return ModelFactory.eINSTANCE.createTStartEvent();
    }

    @Override
    public TEndEvent caseEndErrorEvent(EndErrorEvent object) {
        var bpmnEnd = ModelFactory.eINSTANCE.createTEndEvent();
        final TErrorEventDefinition eventDef = ModelFactory.eINSTANCE.createTErrorEventDefinition();
        bpmnEnd.getEventDefinition().add(eventDef);
        final String errorCode = object.getErrorCode();
        final String eventDefId = errorCode != null ? errorCode + EcoreUtil.generateUUID() : EcoreUtil.generateUUID();
        eventDef.setId(eventDefId);
        if (errorCode != null) {
            eventDef.setErrorRef(QName.valueOf(errorCode));
        }
        return bpmnEnd;
    }

    @Override
    public TEndEvent caseEndMessageEvent(EndMessageEvent object) {
        var bpmnEnd = ModelFactory.eINSTANCE.createTEndEvent();
        for (final Message eventObject : object.getEvents()) {
            final String eventId = eventObject.getName() != null && !eventObject.getName().isBlank()
                    ? eventObject.getName()
                    : EcoreUtil.generateUUID();
            bpmnEnd.getEventDefinitionRef().add(QName.valueOf(eventId));
            modelRegistry.addMessage(eventId);
        }
        return bpmnEnd;
    }

    @Override
    public TEndEvent caseEndSignalEvent(EndSignalEvent object) {
        var event = ModelFactory.eINSTANCE.createTEndEvent();
        addSignalEventDefinition(object, event);
        return event;
    }

    @Override
    public TEndEvent caseEndTerminatedEvent(EndTerminatedEvent object) {
        var event = ModelFactory.eINSTANCE.createTEndEvent();
        final TTerminateEventDefinition eventDef = ModelFactory.eINSTANCE.createTTerminateEventDefinition();
        event.getEventDefinition().add(eventDef);
        eventDef.setId(EcoreUtil.generateUUID());
        return event;
    }

    @Override
    public TEndEvent caseEndEvent(EndEvent object) {
        return ModelFactory.eINSTANCE.createTEndEvent();
    }

    @Override
    public TIntermediateCatchEvent caseIntermediateCatchMessageEvent(IntermediateCatchMessageEvent object) {
        var bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateCatchEvent();
        final String event = object.getEvent() != null && !object.getEvent().isBlank()
                ? object.getEvent()
                : EcoreUtil.generateUUID();
        bpmnEvent.getEventDefinitionRef().add(QName.valueOf(event));
        modelRegistry.addMessage(event);
        return bpmnEvent;
    }

    @Override
    public TIntermediateThrowEvent caseIntermediateThrowMessageEvent(IntermediateThrowMessageEvent object) {
        var event = ModelFactory.eINSTANCE.createTIntermediateThrowEvent();
        for (final Message bonitaEventDef : object.getEvents()) {
            final String eventId = bonitaEventDef.getName() != null && !bonitaEventDef.getName().isBlank()
                    ? bonitaEventDef.getName()
                    : EcoreUtil.generateUUID();
            event.getEventDefinitionRef().add(QName.valueOf(eventId));
            modelRegistry.addMessage(eventId);
        }
        return event;
    }

    @Override
    public TIntermediateCatchEvent caseIntermediateCatchSignalEvent(IntermediateCatchSignalEvent object) {
        final TIntermediateCatchEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateCatchEvent();
        addSignalEventDefinition(object, bpmnEvent);
        return bpmnEvent;
    }

    @Override
    public TIntermediateThrowEvent caseIntermediateThrowSignalEvent(IntermediateThrowSignalEvent object) {
        final TIntermediateThrowEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateThrowEvent();
        addSignalEventDefinition(object, bpmnEvent);
        return bpmnEvent;
    }

    @Override
    public TIntermediateCatchEvent caseCatchLinkEvent(CatchLinkEvent object) {
        final TIntermediateCatchEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateCatchEvent();
        var bpmnLinkEvent = createTLinkEventDefinition(object);
        bpmnEvent.getEventDefinition().add(bpmnLinkEvent);
        return bpmnEvent;
    }

    @Override
    public TIntermediateThrowEvent caseThrowLinkEvent(ThrowLinkEvent object) {
        final TIntermediateThrowEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateThrowEvent();
        var bpmnLinkEvent = createTLinkEventDefinition(object);
        bpmnEvent.getEventDefinition().add(bpmnLinkEvent);
        return bpmnEvent;
    }

    @Override
    public TIntermediateCatchEvent caseIntermediateCatchTimerEvent(IntermediateCatchTimerEvent object) {
        final TIntermediateCatchEvent bpmnEvent = ModelFactory.eINSTANCE.createTIntermediateCatchEvent();
        final TTimerEventDefinition eventDef = createTimerEventDef(object);
        bpmnEvent.getEventDefinition().add(eventDef);
        return bpmnEvent;
    }

    public TTimerEventDefinition createTimerEventDef(AbstractTimerEvent bonitaEvent) {
        final TTimerEventDefinition eventDef = ModelFactory.eINSTANCE.createTTimerEventDefinition();
        final Expression conditionExpression = bonitaEvent.getCondition();
        if (conditionExpression != null && conditionExpression.hasContent()) {
            createTimerExpression(eventDef, conditionExpression);
        }
        eventDef.setId(EVENTDEF_PREFIX + Strings.slugify(bonitaEvent.getName()));
        return eventDef;
    }

    private void createTimerExpression(final TTimerEventDefinition eventDef, final Expression conditionExpression) {
        var condition = conditionExpression.getContent();
        final TExpression expression = ModelFactory.eINSTANCE.createTExpression();
        FeatureMapUtil.addText(expression.getMixed(), condition);
        if (isDuration(condition)) {
            eventDef.setTimeDuration(expression);
        } else if (isDate(condition)) {
            eventDef.setTimeDate(expression);
        } else {
            eventDef.setTimeCycle(expression);
        }
    }

    private void dataMappingWithCallActivity(final CallActivity callActivity, final TCallActivity tCallActivity) {
        final TDataInputAssociation dia = ModelFactory.eINSTANCE.createTDataInputAssociation();
        dia.setId(EcoreUtil.generateUUID());
        tCallActivity.getDataInputAssociation().add(dia);
        for (final InputMapping im : callActivity.getInputMappings()) {
            final TAssignment inputAssignment = ModelFactory.eINSTANCE.createTAssignment();
            final Expression processSource = im.getProcessSource();
            if (processSource != null) {
                inputAssignment.setFrom(createBPMNExpressionFromString(processSource.getName()));
                final String dataTo = getDataReferenceValue(callActivity, im.getSubprocessTarget());
                if (dataTo != null) {
                    inputAssignment.setTo(createBPMNExpressionFromString(dataTo));
                    dia.getAssignment().add(inputAssignment);
                }
            }
        }
        final TDataOutputAssociation doa = ModelFactory.eINSTANCE.createTDataOutputAssociation();
        doa.setId(EcoreUtil.generateUUID());
        tCallActivity.getDataOutputAssociation().add(doa);
        for (final OutputMapping om : callActivity.getOutputMappings()) {
            final TAssignment outputAssignment = ModelFactory.eINSTANCE.createTAssignment();
            final String dataFrom = getDataReferenceValue(callActivity, om.getSubprocessSource());
            outputAssignment.setFrom(createBPMNExpressionFromString(dataFrom));
            final Data processTarget = om.getProcessTarget();
            if (processTarget != null) {
                final TItemDefinition dataTo = modelRegistry.getDataScope().get(processTarget);
                outputAssignment.setTo(
                        createBPMNExpressionFromString(dataTo != null ? dataTo.getId() : processTarget.getName()));
                doa.getAssignment().add(outputAssignment);
            }
        }
    }

    private void handleConnectorOnServiceTask(ServiceTask activity, final TServiceTask serviceTask) {
        final EList<Connector> connectors = activity.getConnectors();
        if (!connectors.isEmpty()) {
            final Connector connector = connectors.get(0);
            connectorDefinitionTransformer.handleBonitaConnectorDefinition(connector.getDefinitionId());
            /*
             * Service task should be used with a connector, this connector will be the
             * bpmn2 operation used /!\BEGIN TO HANDLE A SINGLE OPERATION
             */
            serviceTask.setImplementation("BonitaConnector");
            serviceTask.setOperationRef(QName.valueOf("Exec" + connector.getDefinitionId()));

            final TDataInput dataInput = DataIOTransformer.fillIOSpecificationWithNewDataInput(serviceTask,
                    QName.valueOf(
                            connectorDefinitionTransformer.generateConnectorInputItemDef(connector.getDefinitionId())));
            final TDataOutput dataOutput = fillIOSpecificationWithNewDataOutput(serviceTask, QName.valueOf(
                    connectorDefinitionTransformer.generateConnectorOutputItemDef(connector.getDefinitionId())));

            handleConnectorInput(serviceTask, connector, dataInput);

            handleConnectorOutput(serviceTask, connector, dataInput, dataOutput);
        }
    }

    private void handleConnectorInput(final TServiceTask serviceTask, final Connector connector,
            final TDataInput dataInput) {
        final TDataInputAssociation tDataInputAssociation = ModelFactory.eINSTANCE.createTDataInputAssociation();
        final EList<TAssignment> inputAssignments = tDataInputAssociation.getAssignment();
        for (final ConnectorParameter cp : connector.getConfiguration().getParameters()) {
            final TAssignment inputAssignment = ModelFactory.eINSTANCE.createTAssignment();
            if (cp.getExpression() instanceof Expression && ((Expression) cp.getExpression()).getContent() != null) {
                inputAssignment.setFrom(convertExpression((Expression) cp.getExpression()));
                inputAssignment.setTo(createBPMNExpressionFromString("getDataInput('" + dataInput.getId() + "')/"
                        + ConnectorDefinitionTransformer.XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION + ":"
                        + cp.getKey()));
                inputAssignments.add(inputAssignment);
            }
        }
        if (!tDataInputAssociation.getAssignment().isEmpty()) {
            serviceTask.getDataInputAssociation().add(tDataInputAssociation);
            tDataInputAssociation.setTargetRef(dataInput.getId());
        }
    }

    private void handleConnectorOutput(final TServiceTask serviceTask, final Connector connector,
            final TDataInput dataInput, final TDataOutput dataOutput) {
        final TDataOutputAssociation tDataOutputAssociation = ModelFactory.eINSTANCE.createTDataOutputAssociation();
        final EList<TAssignment> outputAssignments = tDataOutputAssociation.getAssignment();
        for (final Operation opm : connector.getOutputs()) {
            if (opm.getRightOperand().hasName() && opm.getRightOperand().hasContent()) {
                final TAssignment outputAssignment = ModelFactory.eINSTANCE.createTAssignment();
                if (ExpressionConstants.CONNECTOR_OUTPUT_TYPE.equals(opm.getRightOperand().getType())) {
                    outputAssignment.setFrom(createBPMNExpressionFromString("getDataOutput('" + dataInput.getId()
                            + "')/"
                            + ConnectorDefinitionTransformer.XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION + ":"
                            + opm.getRightOperand().getName()));
                } else {
                    outputAssignment.setFrom(convertExpression(opm.getRightOperand()));
                }
                if (opm.getLeftOperand().hasContent()) {
                    outputAssignment.setTo(convertExpression(opm.getLeftOperand()));
                }
                outputAssignments.add(outputAssignment);
            }
        }
        if (!tDataOutputAssociation.getAssignment().isEmpty()) {
            serviceTask.getDataOutputAssociation().add(tDataOutputAssociation);
            tDataOutputAssociation.setTargetRef(dataOutput.getId());
        }
    }

    private TDataOutput fillIOSpecificationWithNewDataOutput(final TServiceTask serviceTask,
            final QName dataItemDefinitionIdAsQname) {
        TInputOutputSpecification tInputOutputAssociation = serviceTask.getIoSpecification();
        if (tInputOutputAssociation == null) {
            tInputOutputAssociation = ModelFactory.eINSTANCE.createTInputOutputSpecification();
            tInputOutputAssociation.setId(EcoreUtil.generateUUID());
            serviceTask.setIoSpecification(tInputOutputAssociation);
        }

        return fillIOSpecificationWithNewDataOutput(dataItemDefinitionIdAsQname, tInputOutputAssociation);
    }

    private TDataOutput fillIOSpecificationWithNewDataOutput(final QName dataItemDefinitionIdAsQname,
            final TInputOutputSpecification tInputOutputAssociation) {
        final TDataOutput tDataOutput = ModelFactory.eINSTANCE.createTDataOutput();
        tDataOutput.setItemSubjectRef(dataItemDefinitionIdAsQname);
        tDataOutput.setId(EcoreUtil.generateUUID());

        final TOutputSet tOutputSet = ModelFactory.eINSTANCE.createTOutputSet();
        tOutputSet.setId(EcoreUtil.generateUUID());
        tOutputSet.getDataOutputRefs().add(tDataOutput.getId());
        final EList<TDataOutput> dataOutput = tInputOutputAssociation.getDataOutput();
        final EList<TOutputSet> outputSet = tInputOutputAssociation.getOutputSet();
        outputSet.add(tOutputSet);
        dataOutput.add(tDataOutput);
        return tDataOutput;
    }

    private TExpression createBPMNExpressionFromString(final String value) {
        final TExpression fromExpression = ModelFactory.eINSTANCE.createTExpression();
        fromExpression.setId(EcoreUtil.generateUUID());
        FeatureMapUtil.addText(fromExpression.getMixed(), value == null ? "" : value);
        return fromExpression;
    }

    private String getDataReferenceValue(final CallActivity callActivity, final String bonitaReferenceString) {
        String result = bonitaReferenceString;
        final Expression calledActivityName = callActivity.getCalledActivityName();
        if (calledActivityName != null && calledActivityName.getType().equals(ExpressionConstants.CONSTANT_TYPE)
                && calledActivityName.getContent() != null) {
            String version = null;
            final Expression calledActivityVersion = callActivity.getCalledActivityVersion();
            if (calledActivityVersion != null
                    && calledActivityVersion.getType().equals(ExpressionConstants.CONSTANT_TYPE)
                    && calledActivityVersion.getContent() != null) {
                version = calledActivityVersion.getContent();
            }
            result = modelSearch.findProcess(calledActivityName.getContent(), version)
                    .flatMap(calledProcess -> calledProcess.getData().stream()
                            .filter(data -> Objects.equals(data.getName(), bonitaReferenceString))
                            .map(data -> modelSearch.getEObjectID(data)).findFirst())
                    .orElse(null);
        }
        return result;
    }

    private void handleMultiInstance(final MultiInstantiable multiInstantiable, final TActivity res) {
        final MultiInstanceType multiInstanceType = multiInstantiable.getType();
        TLoopCharacteristics loopCharacteristics = null;
        if (MultiInstanceType.STANDARD.equals(multiInstanceType)) {
            loopCharacteristics = ModelFactory.eINSTANCE.createTStandardLoopCharacteristics();
            ((TStandardLoopCharacteristics) loopCharacteristics).setTestBefore(multiInstantiable.getTestBefore());
            final Expression loopCondition = multiInstantiable.getLoopCondition();
            if (loopCondition != null) {
                ((TStandardLoopCharacteristics) loopCharacteristics).setLoopCondition(convertExpression(loopCondition));
            }
            final Expression loopMaximum = multiInstantiable.getLoopMaximum();
            if (loopMaximum != null && ExpressionConstants.CONSTANT_TYPE.equals(loopMaximum.getType())
                    && loopMaximum.getContent() != null && !loopMaximum.getContent().isEmpty()) {
                ((TStandardLoopCharacteristics) loopCharacteristics)
                        .setLoopMaximum(new BigInteger(loopMaximum.getContent()));
            }
        } else if (MultiInstanceType.PARALLEL.equals(multiInstanceType)
                || MultiInstanceType.SEQUENTIAL.equals(multiInstanceType)) {
            loopCharacteristics = ModelFactory.eINSTANCE.createTMultiInstanceLoopCharacteristics();
            ((TMultiInstanceLoopCharacteristics) loopCharacteristics)
                    .setIsSequential(MultiInstanceType.SEQUENTIAL.equals(multiInstanceType));
            final Expression completionCondition = multiInstantiable.getCompletionCondition();
            if (completionCondition != null) {
                ((TMultiInstanceLoopCharacteristics) loopCharacteristics)
                        .setCompletionCondition(convertExpression(completionCondition));
            }
            final Expression cardinalityExpression = multiInstantiable.getCardinalityExpression();
            if (cardinalityExpression != null) {
                ((TMultiInstanceLoopCharacteristics) loopCharacteristics)
                        .setLoopCardinality(convertExpression(cardinalityExpression));
            }
        }
        if (loopCharacteristics != null) {
            loopCharacteristics.setId(EcoreUtil.generateUUID());
            res.setLoopCharacteristics(loopCharacteristics);
        }
    }

    private TLinkEventDefinition createTLinkEventDefinition(LinkEvent bonitaEvent) {
        final TLinkEventDefinition eventDef = ModelFactory.eINSTANCE.createTLinkEventDefinition();
        eventDef.setId(EcoreUtil.generateUUID());
        modelRegistry.putLink(bonitaEvent, eventDef);
        return eventDef;
    }

    private TSignalEventDefinition addSignalEventDefinition(final SignalEvent bonitaEvent,
            final TThrowEvent bpmnEvent) {
        final TSignalEventDefinition eventDef = createSignalEvent(bonitaEvent);
        bpmnEvent.getEventDefinition().add(eventDef);
        return eventDef;
    }

    private TSignalEventDefinition createSignalEvent(final SignalEvent bonitaEvent) {
        final TSignalEventDefinition eventDef = ModelFactory.eINSTANCE.createTSignalEventDefinition();
        eventDef.setId(EcoreUtil.generateUUID());
        final TSignal tSignal = getOrCreateTSignal(bonitaEvent);
        eventDef.setSignalRef(QName.valueOf(tSignal.getId()));
        return eventDef;
    }

    private TSignalEventDefinition addSignalEventDefinition(final SignalEvent bonitaEvent,
            final TCatchEvent bpmnEvent) {
        final TSignalEventDefinition eventDef = createSignalEvent(bonitaEvent);
        bpmnEvent.getEventDefinition().add(eventDef);
        return eventDef;
    }

    TSignal getOrCreateTSignal(final SignalEvent signalEvent) {
        final String signalCode = signalEvent.getSignalCode();
        TSignal tSignal = null;
        if (signalCode != null) {
            tSignal = modelRegistry.getSignalCode().get(signalCode);
            if (tSignal == null) {
                tSignal = ModelFactory.eINSTANCE.createTSignal();
                tSignal.setId(EcoreUtil.generateUUID());
                tSignal.setName(signalCode);
                modelRegistry.putSignalCode(signalCode, tSignal);
                modelRegistry.addSignal(tSignal);
            }
        } else {
            tSignal = ModelFactory.eINSTANCE.createTSignal();
            tSignal.setId(EcoreUtil.generateUUID());
            modelRegistry.addSignal(tSignal);
        }
        return tSignal;
    }

    private TFormalExpression convertExpression(final Expression bonitaExpression) {
        return formalExpressionTransformerFactory
                .newFormalExpressionFunction(modelRegistry.getDataScope(), bonitaExpression.getType(), modelSearch)
                .apply(bonitaExpression);
    }

    private static boolean isDate(String event) {
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT, java.util.Locale.US);
        sdf.setLenient(false);
        boolean displayDate = true;
        boolean systemDate = true;

        try {
            sdf.parse(event);
        } catch (Exception e) {
            systemDate = false;
        }

        sdf = new SimpleDateFormat(DISPLAY_DATE_FORMAT, java.util.Locale.US);
        try {
            sdf.parse(event);
        } catch (Exception e) {
            displayDate = false;
        }

        return systemDate || displayDate;
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

}
