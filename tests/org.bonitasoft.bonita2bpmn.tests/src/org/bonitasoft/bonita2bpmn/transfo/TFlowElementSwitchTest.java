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

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder.aConstantExpression;
import static org.bonitasoft.bpm.model.process.builders.PoolBuilder.aPool;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.xml.namespace.QName;

import org.bonitasoft.bonita2bpmn.transfo.data.DataScope;
import org.bonitasoft.bonita2bpmn.transfo.expression.FormalExpressionFunctionFactory;
import org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.bpm.model.util.ModelSearch;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.spec.bpmn.model.ModelFactory;
import org.omg.spec.bpmn.model.TActivity;
import org.omg.spec.bpmn.model.TCallActivity;
import org.omg.spec.bpmn.model.TEndEvent;
import org.omg.spec.bpmn.model.TErrorEventDefinition;
import org.omg.spec.bpmn.model.TExclusiveGateway;
import org.omg.spec.bpmn.model.TInclusiveGateway;
import org.omg.spec.bpmn.model.TIntermediateCatchEvent;
import org.omg.spec.bpmn.model.TIntermediateThrowEvent;
import org.omg.spec.bpmn.model.TLinkEventDefinition;
import org.omg.spec.bpmn.model.TParallelGateway;
import org.omg.spec.bpmn.model.TPerformer;
import org.omg.spec.bpmn.model.TReceiveTask;
import org.omg.spec.bpmn.model.TScriptTask;
import org.omg.spec.bpmn.model.TSendTask;
import org.omg.spec.bpmn.model.TServiceTask;
import org.omg.spec.bpmn.model.TSignalEventDefinition;
import org.omg.spec.bpmn.model.TStartEvent;
import org.omg.spec.bpmn.model.TSubProcess;
import org.omg.spec.bpmn.model.TTerminateEventDefinition;
import org.omg.spec.bpmn.model.TTimerEventDefinition;
import org.omg.spec.bpmn.model.TUserTask;

class TFlowElementSwitchTest {

    private TFlowElementSwitch flowElementSwitch;
    private ModelSearch modelSearch;

    @BeforeEach
    void setup() throws Exception {
        modelSearch = spy(new ModelSearch(Collections::emptyList));
        doReturn(EcoreUtil.generateUUID()).when(modelSearch).getEObjectID(any());
        flowElementSwitch = new TFlowElementSwitch(modelSearch,
                mock(ConnectorDefinitionTransformer.class),
                mock(FormalExpressionFunctionFactory.class),
                new ModelRegistry(ModelFactory.eINSTANCE.createTDefinitions(), mock(DataScope.class)));
    }

    @Test
    void caseActivity() throws Exception {
        var activity = ProcessFactory.eINSTANCE.createActivity();

        var tActivity = flowElementSwitch.caseActivity(activity);

        assertThat(tActivity).isInstanceOf(TActivity.class);
    }

    @Test
    void caseScriptTask() throws Exception {
        var activity = ProcessFactory.eINSTANCE.createScriptTask();

        var tActivity = flowElementSwitch.caseScriptTask(activity);

        assertThat(tActivity).isInstanceOf(TScriptTask.class);
    }

    @Test
    void caseServiceTask() throws Exception {
        var activity = ProcessFactory.eINSTANCE.createServiceTask();

        var tActivity = flowElementSwitch.caseServiceTask(activity);

        assertThat(tActivity).isInstanceOf(TServiceTask.class);
    }

    @Test
    void caseSendTask() throws Exception {
        var activity = ProcessFactory.eINSTANCE.createSendTask();

        var tActivity = flowElementSwitch.caseSendTask(activity);

        assertThat(tActivity).isInstanceOf(TSendTask.class);
    }

    @Test
    void caseReceiveTask() throws Exception {
        var activity = ProcessFactory.eINSTANCE.createReceiveTask();

        var tActivity = flowElementSwitch.caseReceiveTask(activity);

        assertThat(tActivity).isInstanceOf(TReceiveTask.class);
    }

    @Test
    void caseTask() throws Exception {
        var activity = ProcessFactory.eINSTANCE.createTask();

        var tActivity = flowElementSwitch.caseTask(activity);

        assertThat(tActivity).isInstanceOf(TUserTask.class);
    }

    @Test
    void caseTaskWithActor() throws Exception {
        var activity = ProcessFactory.eINSTANCE.createTask();
        activity.setActor(ProcessFactory.eINSTANCE.createActor());

        var tActivity = flowElementSwitch.caseTask(activity);

        assertThat(tActivity).isInstanceOf(TUserTask.class);
        assertThat(tActivity.getResourceRole().get(0)).isInstanceOf(TPerformer.class);
    }

    @Test
    void caseCallActivity() throws Exception {
        var activity = ProcessFactory.eINSTANCE.createCallActivity();

        var tActivity = flowElementSwitch.caseCallActivity(activity);

        assertThat(tActivity).isInstanceOf(TCallActivity.class);
    }

    @Test
    void caseCallActivityWithCalledProcess() throws Exception {
        var activity = ProcessFactory.eINSTANCE.createCallActivity();
        activity.setCalledActivityName(aConstantExpression().withContent("MyProcess").build());
        activity.setCalledActivityVersion(aConstantExpression().withContent("1.0").build());
        doReturn(Optional.of(aPool().build())).when(modelSearch).findProcess("MyProcess", "1.0");

        var tActivity = flowElementSwitch.caseCallActivity(activity);

        assertThat(tActivity).isInstanceOf(TCallActivity.class);
        assertThat(tActivity.getCalledElement()).isNotNull();
    }

    @Test
    void caseANDGateway() throws Exception {
        var gateway = ProcessFactory.eINSTANCE.createANDGateway();

        var tGateway = flowElementSwitch.caseANDGateway(gateway);

        assertThat(tGateway).isInstanceOf(TParallelGateway.class);
    }

    @Test
    void caseInclusiveGateway() throws Exception {
        var gateway = ProcessFactory.eINSTANCE.createInclusiveGateway();

        var tGateway = flowElementSwitch.caseInclusiveGateway(gateway);

        assertThat(tGateway).isInstanceOf(TInclusiveGateway.class);
    }

    @Test
    void caseXORGateway() throws Exception {
        var gateway = ProcessFactory.eINSTANCE.createXORGateway();

        var tGateway = flowElementSwitch.caseXORGateway(gateway);

        assertThat(tGateway).isInstanceOf(TExclusiveGateway.class);
    }

    @Test
    void caseStartTimerEvent() throws Exception {
        var event = ProcessFactory.eINSTANCE.createStartTimerEvent();

        var tEvent = flowElementSwitch.caseStartTimerEvent(event);

        assertThat(tEvent).isInstanceOf(TStartEvent.class);
        assertThat(tEvent.getEventDefinition().get(0)).isInstanceOf(TTimerEventDefinition.class);
    }

    @Test
    void caseStartTimerEventWithDuration() throws Exception {
        var event = ProcessFactory.eINSTANCE.createStartTimerEvent();
        event.setCondition(ExpressionBuilder.aConstantExpression().withContent("123").build());

        var tEvent = flowElementSwitch.caseStartTimerEvent(event);

        assertThat(tEvent).isInstanceOf(TStartEvent.class);
        var eventDefinition = tEvent.getEventDefinition().get(0);
        assertThat(eventDefinition).isInstanceOf(TTimerEventDefinition.class);
        assertThat(((TTimerEventDefinition) eventDefinition).getTimeDuration()).isNotNull();
        assertThat(((TTimerEventDefinition) eventDefinition).getTimeDuration().getMixed()
                .get(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text(), false)).isEqualTo(List.of("123"));
    }

    @Test
    void caseStartTimerEventWithDate() throws Exception {
        var event = ProcessFactory.eINSTANCE.createStartTimerEvent();
        event.setCondition(ExpressionBuilder.aConstantExpression().withContent("2023/08/01/12/15/30").build());

        var tEvent = flowElementSwitch.caseStartTimerEvent(event);

        assertThat(tEvent).isInstanceOf(TStartEvent.class);
        var eventDefinition = tEvent.getEventDefinition().get(0);
        assertThat(eventDefinition).isInstanceOf(TTimerEventDefinition.class);
        assertThat(((TTimerEventDefinition) eventDefinition).getTimeDate()).isNotNull();
        assertThat(((TTimerEventDefinition) eventDefinition).getTimeDate().getMixed()
                .get(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text(), false))
                .isEqualTo(List.of("2023/08/01/12/15/30"));
    }

    @Test
    void caseStartTimerEventWithCycle() throws Exception {
        var event = ProcessFactory.eINSTANCE.createStartTimerEvent();
        event.setCondition(ExpressionBuilder.aConstantExpression().withContent("0 0 0 ? * 2/1 *").build());

        var tEvent = flowElementSwitch.caseStartTimerEvent(event);

        assertThat(tEvent).isInstanceOf(TStartEvent.class);
        var eventDefinition = tEvent.getEventDefinition().get(0);
        assertThat(eventDefinition).isInstanceOf(TTimerEventDefinition.class);
        assertThat(((TTimerEventDefinition) eventDefinition).getTimeCycle()).isNotNull();
        assertThat(((TTimerEventDefinition) eventDefinition).getTimeCycle().getMixed()
                .get(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text(), false))
                .isEqualTo(List.of("0 0 0 ? * 2/1 *"));
    }

    @Test
    void caseSubProcessEvent() throws Exception {
        var subProcessEvent = ProcessFactory.eINSTANCE.createSubProcessEvent();

        var tSubprocessEvent = flowElementSwitch.caseSubProcessEvent(subProcessEvent);

        assertThat(tSubprocessEvent).isInstanceOf(TSubProcess.class);
        assertThat(tSubprocessEvent.isTriggeredByEvent()).isTrue();
    }

    @Test
    void caseStartErrorEvent() throws Exception {
        var event = ProcessFactory.eINSTANCE.createStartErrorEvent();

        var tEvent = flowElementSwitch.caseStartErrorEvent(event);

        assertThat(tEvent).isInstanceOf(TStartEvent.class);
        assertThat(tEvent.getEventDefinition().get(0)).isInstanceOf(TErrorEventDefinition.class);
    }

    @Test
    void caseStartMessageEvent() throws Exception {
        var event = ProcessFactory.eINSTANCE.createStartMessageEvent();
        event.setEvent("message-id");

        var tEvent = flowElementSwitch.caseStartMessageEvent(event);

        assertThat(tEvent).isInstanceOf(TStartEvent.class);
        assertThat(tEvent.getEventDefinitionRef().get(0)).isEqualTo(QName.valueOf("message-id"));
    }

    @Test
    void caseStartSignalEvent() throws Exception {
        var event = ProcessFactory.eINSTANCE.createStartSignalEvent();
        event.setSignalCode("signal-id");

        var tEvent = flowElementSwitch.caseStartSignalEvent(event);

        assertThat(tEvent).isInstanceOf(TStartEvent.class);
        assertThat(tEvent.getEventDefinition().get(0)).isInstanceOf(TSignalEventDefinition.class);
    }

    @Test
    void caseStartEvent() throws Exception {
        var event = ProcessFactory.eINSTANCE.createStartEvent();

        var tEvent = flowElementSwitch.caseStartEvent(event);

        assertThat(tEvent).isInstanceOf(TStartEvent.class);
    }

    @Test
    void caseEndEvent() throws Exception {
        var event = ProcessFactory.eINSTANCE.createEndEvent();

        var tEvent = flowElementSwitch.caseEndEvent(event);

        assertThat(tEvent).isInstanceOf(TEndEvent.class);
    }

    @Test
    void caseEndErrorEvent() throws Exception {
        var event = ProcessFactory.eINSTANCE.createEndErrorEvent();

        var tEvent = flowElementSwitch.caseEndErrorEvent(event);

        assertThat(tEvent).isInstanceOf(TEndEvent.class);
        assertThat(tEvent.getEventDefinition().get(0)).isInstanceOf(TErrorEventDefinition.class);
    }

    @Test
    void caseEndMessageEvent() throws Exception {
        var event = ProcessFactory.eINSTANCE.createEndMessageEvent();
        var message = ProcessFactory.eINSTANCE.createMessage();
        message.setName("message-id");
        event.getEvents().add(message);

        var tEvent = flowElementSwitch.caseEndMessageEvent(event);

        assertThat(tEvent).isInstanceOf(TEndEvent.class);
        assertThat(tEvent.getEventDefinitionRef().get(0)).isEqualTo(QName.valueOf("message-id"));
    }

    @Test
    void caseEndSignalEvent() throws Exception {
        var event = ProcessFactory.eINSTANCE.createEndSignalEvent();
        event.setSignalCode("signal-code");

        var tEvent = flowElementSwitch.caseEndSignalEvent(event);

        assertThat(tEvent).isInstanceOf(TEndEvent.class);
        assertThat(tEvent.getEventDefinition().get(0)).isInstanceOf(TSignalEventDefinition.class);
    }

    @Test
    void caseEndTerminatedEvent() throws Exception {
        var event = ProcessFactory.eINSTANCE.createEndTerminatedEvent();

        var tEvent = flowElementSwitch.caseEndTerminatedEvent(event);

        assertThat(tEvent).isInstanceOf(TEndEvent.class);
        assertThat(tEvent.getEventDefinition().get(0)).isInstanceOf(TTerminateEventDefinition.class);
    }

    @Test
    void caseIntermediateCatchMessageEvent() throws Exception {
        var event = ProcessFactory.eINSTANCE.createIntermediateCatchMessageEvent();
        event.setEvent("message-id");

        var tEvent = flowElementSwitch.caseIntermediateCatchMessageEvent(event);

        assertThat(tEvent).isInstanceOf(TIntermediateCatchEvent.class);
        assertThat(tEvent.getEventDefinitionRef().get(0)).isEqualTo(QName.valueOf("message-id"));
    }

    @Test
    void caseIntermediateCatchSignalEvent() throws Exception {
        var event = ProcessFactory.eINSTANCE.createIntermediateCatchSignalEvent();
        event.setSignalCode("signal-code");

        var tEvent = flowElementSwitch.caseIntermediateCatchSignalEvent(event);

        assertThat(tEvent).isInstanceOf(TIntermediateCatchEvent.class);
        assertThat(tEvent.getEventDefinition().get(0)).isInstanceOf(TSignalEventDefinition.class);
    }

    @Test
    void caseCatchLinkEvent() throws Exception {
        var event = ProcessFactory.eINSTANCE.createCatchLinkEvent();

        var tEvent = flowElementSwitch.caseCatchLinkEvent(event);

        assertThat(tEvent).isInstanceOf(TIntermediateCatchEvent.class);
        assertThat(tEvent.getEventDefinition().get(0)).isInstanceOf(TLinkEventDefinition.class);
    }

    @Test
    void caseIntermediateCatchTimerEvent() throws Exception {
        var event = ProcessFactory.eINSTANCE.createIntermediateCatchTimerEvent();

        var tEvent = flowElementSwitch.caseIntermediateCatchTimerEvent(event);

        assertThat(tEvent).isInstanceOf(TIntermediateCatchEvent.class);
        assertThat(tEvent.getEventDefinition().get(0)).isInstanceOf(TTimerEventDefinition.class);
    }

    @Test
    void caseThrowLinkEvent() throws Exception {
        var event = ProcessFactory.eINSTANCE.createThrowLinkEvent();

        var tEvent = flowElementSwitch.caseThrowLinkEvent(event);

        assertThat(tEvent).isInstanceOf(TIntermediateThrowEvent.class);
        assertThat(tEvent.getEventDefinition().get(0)).isInstanceOf(TLinkEventDefinition.class);
    }

    @Test
    void caseIntermediateThrowSignalEvent() throws Exception {
        var event = ProcessFactory.eINSTANCE.createIntermediateThrowSignalEvent();
        event.setSignalCode("signal-code");

        var tEvent = flowElementSwitch.caseIntermediateThrowSignalEvent(event);

        assertThat(tEvent).isInstanceOf(TIntermediateThrowEvent.class);
        assertThat(tEvent.getEventDefinition().get(0)).isInstanceOf(TSignalEventDefinition.class);
    }

    @Test
    void caseIntermediateThrowMessageEvent() throws Exception {
        var event = ProcessFactory.eINSTANCE.createIntermediateThrowMessageEvent();
        var message = ProcessFactory.eINSTANCE.createMessage();
        message.setName("message-id");
        event.getEvents().add(message);

        var tEvent = flowElementSwitch.caseIntermediateThrowMessageEvent(event);

        assertThat(tEvent).isInstanceOf(TIntermediateThrowEvent.class);
        assertThat(tEvent.getEventDefinitionRef().get(0)).isEqualTo(QName.valueOf("message-id"));
    }

}
