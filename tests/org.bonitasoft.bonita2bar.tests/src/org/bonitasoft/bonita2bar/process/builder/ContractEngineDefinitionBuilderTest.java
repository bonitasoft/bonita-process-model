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
package org.bonitasoft.bonita2bar.process.builder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.bpm.model.process.builders.ContractBuilder.aContract;
import static org.bonitasoft.bpm.model.process.builders.ContractConstraintBuilder.aContractConstraint;
import static org.bonitasoft.bpm.model.process.builders.ContractInputBuilder.aContractInput;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bonitasoft.bpm.model.process.Contract;
import org.bonitasoft.bpm.model.process.ContractInput;
import org.bonitasoft.bpm.model.process.ContractInputMapping;
import org.bonitasoft.bpm.model.process.ContractInputType;
import org.bonitasoft.bpm.model.process.Data;
import org.bonitasoft.bpm.model.process.DataType;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.engine.bpm.contract.Type;
import org.bonitasoft.engine.bpm.process.impl.ContractDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ContractInputDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.UserTaskDefinitionBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ContractEngineDefinitionBuilderTest {

    @Mock
    private UserTaskDefinitionBuilder taskBuilder;

    @Mock
    private ProcessDefinitionBuilder processBuilder;

    private ContractEngineDefinitionBuilder<UserTaskDefinitionBuilder> userTaskengineContractBuilder;
    private ContractEngineDefinitionBuilder<ProcessDefinitionBuilder> processengineContractBuilder;

    private Contract aContract;

    @Mock
    private ContractDefinitionBuilder contractDefBuilder;
    @Mock
    private ContractInputDefinitionBuilder contractInputDefinitionBuilder;

    @BeforeEach
    void setUp() throws Exception {
        aContract = ProcessFactory.eINSTANCE.createContract();
        processengineContractBuilder = new ProcessContractEngineBuilder();
        processengineContractBuilder.setEngineBuilder(processBuilder);

        aContract = ProcessFactory.eINSTANCE.createContract();
        userTaskengineContractBuilder = new TaskContractEngineDefinitionBuilder();
        userTaskengineContractBuilder.setEngineBuilder(taskBuilder);
    }

    @Test
    void should_appliesTo_a_contract() throws Exception {
        assertThat(
                new TaskContractEngineDefinitionBuilder().appliesTo(ProcessFactory.eINSTANCE.createTask(), aContract))
                .isTrue();
        assertThat(
                new TaskContractEngineDefinitionBuilder().appliesTo(ProcessFactory.eINSTANCE.createPool(), aContract))
                .isFalse();
        assertThat(new TaskContractEngineDefinitionBuilder().appliesTo(ProcessFactory.eINSTANCE.createTask(), null))
                .isFalse();
        assertThat(userTaskengineContractBuilder.appliesTo(null, null)).isFalse();
        assertThat(new ProcessContractEngineBuilder().appliesTo(ProcessFactory.eINSTANCE.createTask(), aContract))
                .isFalse();
        assertThat(new ProcessContractEngineBuilder().appliesTo(ProcessFactory.eINSTANCE.createPool(), aContract))
                .isTrue();
        assertThat(new ProcessContractEngineBuilder().appliesTo(ProcessFactory.eINSTANCE.createPool(), null)).isFalse();
        assertThat(userTaskengineContractBuilder.appliesTo(null, null)).isFalse();
    }

    @Test
    void should_build_build_an_empty_contract() throws Exception {
        userTaskengineContractBuilder.build(aContract);
        verify(taskBuilder).addContract();
    }

    @Test
    void should_build_build_an_empty_contract_for_process() throws Exception {
        processengineContractBuilder.build(aContract);
        verify(processBuilder).addContract();
    }

    @Test
	void should_build_create_a_contract_with_simple_input() throws Exception {
		 when(taskBuilder.addContract()).thenReturn(contractDefBuilder);
		addInput(aContract, "name", ContractInputType.TEXT, "name of an employee");
		addInput(aContract, "birthDate", ContractInputType.DATE, "Birth date of an employee");
		addInput(aContract, "age", ContractInputType.INTEGER, null);
		addInput(aContract, "salary", ContractInputType.DECIMAL, null);
		addInput(aContract, "isMarried", ContractInputType.BOOLEAN, null);
		userTaskengineContractBuilder.build(aContract);
		verify(taskBuilder).addContract();
		verify(contractDefBuilder).addInput("name", Type.TEXT, "name of an employee", false);
		verify(contractDefBuilder).addInput("birthDate", Type.DATE, "Birth date of an employee", false);
		verify(contractDefBuilder).addInput("age", Type.INTEGER, null, false);
		verify(contractDefBuilder).addInput("salary", Type.DECIMAL, null, false);
		verify(contractDefBuilder).addInput("isMarried", Type.BOOLEAN, null, false);
	}

    @Test
    void should_build_create_a_contract_with_simple_input_for_process() throws Exception {
		when(processBuilder.addContract()).thenReturn(contractDefBuilder);
        addInput(aContract, "name", ContractInputType.TEXT, "name of an employee");
        addInput(aContract, "birthDate", ContractInputType.DATE, "Birth date of an employee");
        addInput(aContract, "age", ContractInputType.INTEGER, null);
        addInput(aContract, "salary", ContractInputType.DECIMAL, null);
        addInput(aContract, "isMarried", ContractInputType.BOOLEAN, null);
        processengineContractBuilder.build(aContract);
        verify(processBuilder).addContract();
        verify(contractDefBuilder).addInput("name", Type.TEXT, "name of an employee", false);
        verify(contractDefBuilder).addInput("birthDate", Type.DATE, "Birth date of an employee", false);
        verify(contractDefBuilder).addInput("age", Type.INTEGER, null, false);
        verify(contractDefBuilder).addInput("salary", Type.DECIMAL, null, false);
        verify(contractDefBuilder).addInput("isMarried", Type.BOOLEAN, null, false);
    }

    private ContractInput addInput(final Contract contract, final String inputName, final ContractInputType type,
            final String description) {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setName(inputName);
        contractInput.setType(type);
        contractInput.setDescription(description);
        contract.getInputs().add(contractInput);
        return contractInput;
    }

    private ContractInput addInput(final ContractInput parentInput, final String inputName,
            final ContractInputType type, final String description) {
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setName(inputName);
        contractInput.setType(type);
        contractInput.setDescription(description);
        parentInput.getInputs().add(contractInput);
        return contractInput;
    }

    @Test
    void should_build_throw_a_NullPointerException_if_no_builder_is_set() throws Exception {
        userTaskengineContractBuilder = new TaskContractEngineDefinitionBuilder();
        assertThrows(NullPointerException.class, () -> userTaskengineContractBuilder.build(aContract));
    }

    @Test
    void should_build_throw_an_IllegalArgumentException_if_no_contract_is_set() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> userTaskengineContractBuilder.build(null));
    }

    @Test
	void should_build_create_a_contract_with_constraint() throws Exception {
		 when(taskBuilder.addContract()).thenReturn(contractDefBuilder);
		final ContractInput nameInput = addInput(aContract, "name", ContractInputType.TEXT, "name of an employee");
		aContract.getConstraints().add(aContractConstraint().withName("myConstraint").withExpression("name.length < 50")
				.withErrorMessage("name is too long").havingInput(nameInput.getName()).build());
		userTaskengineContractBuilder.build(aContract);
		verify(taskBuilder).addContract();
		verify(contractDefBuilder).addInput("name", Type.TEXT, "name of an employee", nameInput.isMultiple());
		verify(contractDefBuilder).addConstraint("myConstraint", "name.length < 50", "name is too long",
				nameInput.getName());
	}

    @Test
	void should_build_create_a_contract_with_mandatory_constraint() throws Exception {
		when(taskBuilder.addContract()).thenReturn(contractDefBuilder);
		final ContractInput nameInput = addInput(aContract, "name", ContractInputType.TEXT, "name of an employee");
		userTaskengineContractBuilder.build(aContract);
		verify(taskBuilder).addContract();
		verify(contractDefBuilder).addInput("name", Type.TEXT, "name of an employee", nameInput.isMultiple());
	}

    @Test
	void should_build_create_operation_for_input_mapping() throws Exception {
		when(taskBuilder.addContract()).thenReturn(contractDefBuilder);
		final ContractInput nameInput = addInput(aContract, "name", ContractInputType.TEXT, "name of an employee");
		final ContractInputMapping mapping = ProcessFactory.eINSTANCE.createContractInputMapping();
		final Data textData = ProcessFactory.eINSTANCE.createData();
		final DataType textDt = ProcessFactory.eINSTANCE.createStringType();
		textDt.setName("String");
		textData.setDataType(textDt);
		textData.setName("employeeName");
		mapping.setData(textData);
		nameInput.setMapping(mapping);
		userTaskengineContractBuilder.build(aContract);
		verify(taskBuilder).addContract();
		verify(contractDefBuilder).addInput("name", Type.TEXT, "name of an employee", nameInput.isMultiple());
	}

    @Test
	void should_create_complex_input_recursively() throws Exception {
	  when(contractInputDefinitionBuilder.addComplexInput(any(), any(), anyBoolean()))
      .thenReturn(contractInputDefinitionBuilder);
	  when(contractDefBuilder.addComplexInput(any(), any(), anyBoolean())).thenReturn(contractInputDefinitionBuilder);
		when(taskBuilder.addContract()).thenReturn(contractDefBuilder);
		final ContractInput employeeInput = addInput(aContract, "employee", ContractInputType.COMPLEX,
				"employee complex type");
		addInput(employeeInput, "firstName", ContractInputType.TEXT, null);
		addInput(employeeInput, "lastName", ContractInputType.TEXT, null);
		addInput(employeeInput, "birthDate", ContractInputType.DATE, null);
		final ContractInput skillsInput = addInput(employeeInput, "skills", ContractInputType.COMPLEX, null);
		skillsInput.setMultiple(true);
		addInput(skillsInput, "name", ContractInputType.TEXT, "name of the skills");
		addInput(skillsInput, "rate", ContractInputType.INTEGER, "rate of the skill");

		userTaskengineContractBuilder.build(aContract);

		verify(contractDefBuilder).addComplexInput("employee", "employee complex type", false);
		verify(contractInputDefinitionBuilder).addInput("firstName", Type.TEXT, null, false);
		verify(contractInputDefinitionBuilder).addInput("lastName", Type.TEXT, null, false);
		verify(contractInputDefinitionBuilder).addInput("birthDate", Type.DATE, null, false);
		verify(contractInputDefinitionBuilder).addComplexInput("skills", null, true);
		verify(contractInputDefinitionBuilder).addInput("name", Type.TEXT, "name of the skills", false);
		verify(contractInputDefinitionBuilder).addInput("rate", Type.INTEGER, "rate of the skill", false);
	}

    @Test
	void should_add_a_file_contrac_input() throws Exception {
		when(taskBuilder.addContract()).thenReturn(contractDefBuilder);
		userTaskengineContractBuilder.build(
				aContract().havingInput(aContractInput().withName("myFile").withType(ContractInputType.FILE)).build());

		verify(contractDefBuilder).addFileInput("myFile", null, false);
	}

}
