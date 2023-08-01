/**
 * Copyright (C) 2014-2015 Bonitasoft S.A.
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.bonitasoft.bpm.model.connectorconfiguration.builders.ConnectorConfigurationBuilder.aConnectorConfiguration;
import static org.bonitasoft.bpm.model.connectorconfiguration.builders.ConnectorParameterBuilder.aConnectorParameter;
import static org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder.aConstantExpression;
import static org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder.aGroovyScriptExpression;
import static org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder.aVariableExpression;
import static org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.bpm.model.process.builders.ActivityBuilder.anActivity;
import static org.bonitasoft.bpm.model.process.builders.ActorFilterBuilder.anActorFilter;
import static org.bonitasoft.bpm.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.bonitasoft.bpm.model.process.builders.BusinessObjectDataTypeBuilder.aBusinessObjectDataType;
import static org.bonitasoft.bpm.model.process.builders.CallActivityBuilder.aCallActivity;
import static org.bonitasoft.bpm.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.bpm.model.process.builders.ContractInputBuilder.aContractInput;
import static org.bonitasoft.bpm.model.process.builders.DataBuilder.aData;
import static org.bonitasoft.bpm.model.process.builders.InputMappingBuilder.anInputMapping;
import static org.bonitasoft.bpm.model.process.builders.JavaObjectDataBuilder.aJavaObjectData;
import static org.bonitasoft.bpm.model.process.builders.MainProcessBuilder.aMainProcess;
import static org.bonitasoft.bpm.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.bpm.model.process.builders.SequenceFlowBuilder.aSequenceFlow;
import static org.bonitasoft.bpm.model.process.builders.StartMessageEventBuilder.aStartMessageEvent;
import static org.bonitasoft.bpm.model.process.builders.StringDataTypeBuilder.aStringDataType;
import static org.bonitasoft.bpm.model.process.builders.SubProcessEventBuilder.aSubProcessEvent;
import static org.bonitasoft.bpm.model.process.builders.TaskBuilder.aTask;

import java.util.Collections;
import java.util.List;

import org.bonitasoft.bpm.model.expression.ExpressionFactory;
import org.bonitasoft.bpm.model.expression.ListExpression;
import org.bonitasoft.bpm.model.expression.TableExpression;
import org.bonitasoft.bpm.model.process.BoundaryMessageEvent;
import org.bonitasoft.bpm.model.process.CallActivity;
import org.bonitasoft.bpm.model.process.ContractInputType;
import org.bonitasoft.bpm.model.process.InputMappingAssignationType;
import org.bonitasoft.bpm.model.process.MultiInstanceType;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.bpm.model.process.StartMessageEvent;
import org.bonitasoft.bpm.model.process.Task;
import org.bonitasoft.bpm.model.process.builders.StringDataTypeBuilder;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.bonitasoft.bpm.model.util.ModelSearch;
import org.bonitasoft.engine.bpm.contract.Type;
import org.bonitasoft.engine.bpm.flownode.AutomaticTaskDefinition;
import org.bonitasoft.engine.bpm.flownode.BoundaryEventDefinition;
import org.bonitasoft.engine.bpm.flownode.CallActivityDefinition;
import org.bonitasoft.engine.bpm.flownode.MultiInstanceLoopCharacteristics;
import org.bonitasoft.engine.bpm.flownode.UserTaskDefinition;
import org.bonitasoft.engine.bpm.process.SubProcessDefinition;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.engine.operation.LeftOperand;
import org.bonitasoft.engine.operation.OperatorType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EngineFlowElementBuilderTest {

    private EngineFlowElementBuilder flowElementSwitch;
    private ProcessDefinitionBuilder processDefinitionBuilder;
    private EngineSequenceFlowBuilder sequenceFlowSwitch;

    @BeforeEach
    void setUp() throws Exception {
        processDefinitionBuilder = new ProcessDefinitionBuilder().createNewInstance("test", "1.0");
        flowElementSwitch = new EngineFlowElementBuilder(processDefinitionBuilder,
                new TaskContractEngineDefinitionBuilder(), new ModelSearch(Collections::emptyList));
        sequenceFlowSwitch = new EngineSequenceFlowBuilder(processDefinitionBuilder);
    }

    @Test
    void caseActivity() throws Exception {
        var activity = anActivity().withName("My Activity").build();

        flowElementSwitch.caseActivity(activity);

        var processDefinition = processDefinitionBuilder.done();
        assertThat(processDefinition.getFlowElementContainer().getActivity("My Activity"))
                .isInstanceOf(AutomaticTaskDefinition.class);
    }

    @Test
    void caseSubProcessEvent() throws Exception {
        var activity = aSubProcessEvent().withName("My Subprocess Event").build();
        var startError = ProcessFactory.eINSTANCE.createStartErrorEvent();
        startError.setName("Start error");
        startError.setErrorCode("404");
        activity.getElements().add(startError);

        flowElementSwitch.caseSubProcessEvent(activity);

        var processDefinition = processDefinitionBuilder.done();
        assertThat(processDefinition.getFlowElementContainer().getActivity("My Subprocess Event"))
                .isInstanceOf(SubProcessDefinition.class);
    }

    @SuppressWarnings("deprecation")
    @Test
    void caseSubProcessEventWithCorrelationOnStartMessge() throws Exception {
        final StartMessageEvent startMessageEventinSubprocess = aStartMessageEvent().withName("Start")
                .catchingMessage("message").build();
        startMessageEventinSubprocess.setCorrelation(dummyCorrelation());
        var subProcessEvent = aSubProcessEvent().withName("A subprocess event")
                .havingElements(startMessageEventinSubprocess).build();

        flowElementSwitch.caseSubProcessEvent(subProcessEvent);

        var processDefinition = processDefinitionBuilder.done();
        assertThat(processDefinition.getFlowElementContainer().getActivity("A subprocess event"))
                .isInstanceOf(SubProcessDefinition.class);
        var subProcessEventDefinition = (SubProcessDefinition) processDefinition.getFlowElementContainer()
                .getActivity("A subprocess event");
        assertThat(subProcessEventDefinition.getSubProcessContainer().getStartEvents()).hasSize(1);
        var startEvent = subProcessEventDefinition.getSubProcessContainer().getStartEvents().get(0);
        assertThat(startEvent.getMessageEventTriggerDefinitions().get(0).getCorrelations()).isNotEmpty();
    }

    private TableExpression dummyCorrelation() {
        var tableExpression = ExpressionFactory.eINSTANCE.createTableExpression();
        var idCorrelation = ExpressionFactory.eINSTANCE.createListExpression();
        idCorrelation.getExpressions().add(aConstantExpression().withContent("processId").build());
        idCorrelation.getExpressions().add(aGroovyScriptExpression().withContent("processInstanceId").build());
        tableExpression.getExpressions().add(idCorrelation);
        return tableExpression;
    }

    @Test
    void caseTaskWithBoudaryMessageEventCorrelation() throws Exception {
        var exitActivity = anActivity().withName("Exit").build();
        final BoundaryMessageEvent bmEvent = ProcessFactory.eINSTANCE.createBoundaryMessageEvent();
        bmEvent.setEvent("myEvent");
        bmEvent.setName("bmEvent");
        bmEvent.setCorrelation(dummyCorrelation());
        var connection = aSequenceFlow().havingSource(bmEvent).havingTarget(exitActivity).build();
        bmEvent.getOutgoing().add(connection);
        exitActivity.getIncoming().add(connection);
        var activity = aTask().withName("My activity").in(aPool()).build();
        activity.getBoundaryIntermediateEvents().add(bmEvent);

        flowElementSwitch.caseActivity(exitActivity);
        flowElementSwitch.caseTask(activity);
        sequenceFlowSwitch.doSwitch(connection);

        var processDefinition = processDefinitionBuilder.done();
        var activityDefinition = processDefinition.getFlowElementContainer().getActivity("My activity");
        assertThat(activityDefinition).isInstanceOf(UserTaskDefinition.class);
        assertThat(activityDefinition.getBoundaryEventDefinitions().get(0)).isInstanceOf(BoundaryEventDefinition.class);
        var boundaryEventDef = activityDefinition.getBoundaryEventDefinitions().get(0);
        assertThat(boundaryEventDef.getMessageEventTriggerDefinitions().get(0).getCorrelations()).hasSize(1);
    }

    @Test
    void caseTaskWithContract() throws Exception {
        var activity = aTask().withName("My activity")
                .havingContract(
                        aContract().havingInput(aContractInput().withName("input").withType(ContractInputType.TEXT)))
                .in(aPool()).build();

        flowElementSwitch.caseTask(activity);

        var processDefinition = processDefinitionBuilder.done();
        var activityDefinition = processDefinition.getFlowElementContainer().getActivity("My activity");
        assertThat(activityDefinition).isInstanceOf(UserTaskDefinition.class);
        UserTaskDefinition userTaskDefinition = (UserTaskDefinition) activityDefinition;
        assertThat(userTaskDefinition.getContract()).isNotNull();
        assertThat(userTaskDefinition.getContract().getInputs()).extracting("name", "type")
                .contains(tuple("input", Type.TEXT));
    }

    @Test
    void caseTaskWithContext() throws Exception {
        var collectionDataToMultiInstantiate = aData().withName("pData").havingDataType(aStringDataType()).build();

        var taskB = aTask().withName("My task").havingCollectionDataToMultiInstantiate(collectionDataToMultiInstantiate)
                .havingIteratorExpression(
                        anExpression().withExpressionType(ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE)
                                .withReturnType(String.class.getName()).withName("pData"))
                .havingData(collectionDataToMultiInstantiate);

        var pool = aPool().havingElements(taskB)
                .havingData(aBusinessData().withName("myBData").withClassname("my.classname")).build();
        var mainProcess = aMainProcess().build();
        mainProcess.getElements().add(pool);
        mainProcess.getDatatypes().add(StringDataTypeBuilder.aStringDataType().build());

        flowElementSwitch.caseTask((Task) pool.getElements().get(0));

        var processDefinition = processDefinitionBuilder.done();
        assertThat(
                ((UserTaskDefinition) processDefinition.getFlowElementContainer().getActivity("My task")).getContext())
                .extracting("key", "expression.name").contains(tuple("myBData_ref", "myBData"));
    }

    @Test
    void caseTaskAddsIteratorInContext() throws Exception {
        var collectionDataToMultiInstantiate = aBusinessData().withName("bData").withClassname("classname").multiple()
                .build();

        var taskB = aTask().withName("My task").havingCollectionDataToMultiInstantiate(collectionDataToMultiInstantiate)
                .havingIteratorExpression(
                        anExpression().withExpressionType(ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE)
                                .withReturnType(String.class.getName()).withName("pData"))
                .havingData(collectionDataToMultiInstantiate);

        var pool = aPool().withName("My Process").havingElements(taskB).build();
        var mainProcess = aMainProcess().build();
        mainProcess.getElements().add(pool);
        mainProcess.getDatatypes().add(aBusinessObjectDataType().withName("classname").build());

        flowElementSwitch.caseTask((Task) pool.getElements().get(0));

        var processDefinition = processDefinitionBuilder.done();
        assertThat(
                ((UserTaskDefinition) processDefinition.getFlowElementContainer().getActivity("My task")).getContext())
                .extracting("key", "expression.name").contains(tuple("pData_ref", "pData"));
    }

    @Test
    void caseCallActivityWithInputMappingAssignedToData() throws Exception {
        final Pool pool = aPool()
                .havingElements(aCallActivity().withName("Call Activity")
                        .havingCalledActivityName(aConstantExpression().withContent("Pool1"))
                        .havingInputMappings(anInputMapping()
                                .setSubProcessTarget(InputMappingAssignationType.DATA, "subProcessData")
                                .setProcessSource(aVariableExpression().withContent("processData").build()).build()))
                .havingData(aData().havingDataType(aStringDataType()).withName("processData")).build();

        flowElementSwitch.caseCallActivity((CallActivity) pool.getElements().get(0));

        var processDefinition = processDefinitionBuilder.done();
        var callActivity = processDefinition.getFlowElementContainer().getActivity("Call Activity");
        assertThat(callActivity).isInstanceOf(CallActivityDefinition.class);
        assertThat(((CallActivityDefinition) callActivity).getDataInputOperations()).hasSize(1)
                .extracting("type", "leftOperand.name", "leftOperand.type", "rightOperand.content")
                .contains(tuple(OperatorType.ASSIGNMENT, "subProcessData", LeftOperand.TYPE_DATA, "processData"));
    }

    @Test
    void caseCallActivityWithInputMappingAssignedToContractInput() throws Exception {
        final Pool pool = aPool()
                .havingElements(aCallActivity().withName("Call Activity")
                        .havingCalledActivityName(aConstantExpression().withContent("Pool1"))
                        .havingInputMappings(anInputMapping()
                                .setSubProcessTarget(InputMappingAssignationType.CONTRACT_INPUT, "contractInput")
                                .setProcessSource(aVariableExpression().withContent("processData").build()).build()))
                .havingData(aData().havingDataType(aStringDataType()).withName("processData")).build();

        flowElementSwitch.caseCallActivity((CallActivity) pool.getElements().get(0));

        var processDefinition = processDefinitionBuilder.done();
        var callActivity = processDefinition.getFlowElementContainer().getActivity("Call Activity");
        assertThat(callActivity).isInstanceOf(CallActivityDefinition.class);
        assertThat(((CallActivityDefinition) callActivity).getProcessStartContractInputs())
                .extractingByKey("contractInput").extracting("content").isEqualTo("processData");
    }

    @Test
    void caseTaskWithUserFilterAndTableExpression() throws Exception {
        final TableExpression tableExpression = ExpressionFactory.eINSTANCE.createTableExpression();
        final ListExpression listExpression = ExpressionFactory.eINSTANCE.createListExpression();
        listExpression.getExpressions().add(aConstantExpression().withContent("test").build());
        tableExpression.getExpressions().add(listExpression);

        var activity = aTask().withName("My activity")
                .havingActorFilter(anActorFilter().withDefinitionId("my-filter").withDefinitionVersion("1.0")
                        .havingConfiguration(aConnectorConfiguration().havingParameters(
                                aConnectorParameter().withKey("plop").havingExpression(tableExpression))))
                .in(aPool()).build();

        flowElementSwitch.caseTask(activity);

        var processDefinition = processDefinitionBuilder.done();
        var userTask = processDefinition.getFlowElementContainer().getActivity("My activity");
        assertThat(userTask).isInstanceOf(UserTaskDefinition.class);
        assertThat(((UserTaskDefinition) userTask).getUserFilter()).extracting("filterId", "version")
                .isEqualTo(List.of("my-filter", "1.0"));
        assertThat(((UserTaskDefinition) userTask).getUserFilter().getInputs()).extractingByKey("plop").isNotNull();
    }

    @Test
    void caseTaskWithUserFilterAndEmptyInputs() throws Exception {
        final TableExpression tableExpression = ExpressionFactory.eINSTANCE.createTableExpression();
        final ListExpression listExpression = ExpressionFactory.eINSTANCE.createListExpression();
        listExpression.getExpressions().add(aConstantExpression().withContent("test").build());
        tableExpression.getExpressions().add(listExpression);

        var activity = aTask().withName("My activity")
                .havingActorFilter(anActorFilter().withDefinitionId("my-filter").withDefinitionVersion("1.0")
                        .havingConfiguration(aConnectorConfiguration().havingParameters(
                                aConnectorParameter().withKey("plop"))))
                .in(aPool()).build();

        flowElementSwitch.caseTask(activity);

        var processDefinition = processDefinitionBuilder.done();
        var userTask = processDefinition.getFlowElementContainer().getActivity("My activity");
        assertThat(userTask).isInstanceOf(UserTaskDefinition.class);
        assertThat(((UserTaskDefinition) userTask).getUserFilter()).extracting("filterId", "version")
                .isEqualTo(List.of("my-filter", "1.0"));
        assertThat(((UserTaskDefinition) userTask).getUserFilter().getInputs()).isEmpty();
    }

    @Test
    void caseActivityWithProcessDataCollectionMultiInstance() throws Exception {
        var collectionData = aJavaObjectData().withName("myList").build();
        var activity = anActivity()
                .withName("My activity")
                .withMultiInstanceType(MultiInstanceType.SEQUENTIAL)
                .havingCollectionDataToMultiInstantiate(collectionData)
                .havingIteratorExpression(anExpression()
                        .withExpressionType(ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE)
                        .withReturnType(String.class.getName())
                        .withName("myIterator"))
                .in(aPool().havingData(collectionData)).build();

        processDefinitionBuilder.addData("myList", "java.util.List", null);
        flowElementSwitch.caseActivity(activity);

        var processDefinition = processDefinitionBuilder.done();
        var automaticActivity = processDefinition.getFlowElementContainer().getActivity("My activity");
        assertThat(automaticActivity).isInstanceOf(AutomaticTaskDefinition.class);
        assertThat(((AutomaticTaskDefinition) automaticActivity).getDataDefinition("myIterator")).isNotNull();
        var loopCharacteristics = ((AutomaticTaskDefinition) automaticActivity).getLoopCharacteristics();
        assertThat(loopCharacteristics).isInstanceOf(MultiInstanceLoopCharacteristics.class);
        assertThat(((MultiInstanceLoopCharacteristics) loopCharacteristics).isSequential()).isTrue();
        assertThat(((MultiInstanceLoopCharacteristics) loopCharacteristics).getLoopDataInputRef()).isEqualTo("myList");
    }

    @Test
    void caseActivityWithBusinessDataCollectionMultiInstance() throws Exception {
        var collectionData = aBusinessData().withName("bData")
                .withClassname("classname")
                .multiple()
                .build();
        var activity = anActivity()
                .withName("My activity")
                .withMultiInstanceType(MultiInstanceType.SEQUENTIAL)
                .havingCollectionDataToMultiInstantiate(collectionData)
                .havingIteratorExpression(anExpression()
                        .withExpressionType(ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE)
                        .withReturnType(String.class.getName())
                        .withName("myIterator"))
                .in(aPool())
                .build();

        processDefinitionBuilder.addBusinessData("bData", "classname", null).setMultiple(true);
        flowElementSwitch.caseActivity(activity);

        var processDefinition = processDefinitionBuilder.done();
        var automaticActivity = processDefinition.getFlowElementContainer().getActivity("My activity");
        assertThat(automaticActivity).isInstanceOf(AutomaticTaskDefinition.class);
        assertThat(((AutomaticTaskDefinition) automaticActivity).getBusinessDataDefinition("myIterator")).isNotNull();
        var loopCharacteristics = ((AutomaticTaskDefinition) automaticActivity).getLoopCharacteristics();
        assertThat(loopCharacteristics).isInstanceOf(MultiInstanceLoopCharacteristics.class);
        assertThat(((MultiInstanceLoopCharacteristics) loopCharacteristics).isSequential()).isTrue();
        assertThat(((MultiInstanceLoopCharacteristics) loopCharacteristics).getLoopDataInputRef()).isEqualTo("bData");
    }

}
