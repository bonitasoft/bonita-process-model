/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.bpm.model.edit.custom.process;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Map;

import org.bonitasoft.bpm.model.edit.process.CustomContractInputItemProvider;
import org.bonitasoft.bpm.model.process.Contract;
import org.bonitasoft.bpm.model.process.ContractInput;
import org.bonitasoft.bpm.model.process.ContractInputType;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.bpm.model.process.Task;
import org.bonitasoft.bpm.model.process.provider.ProcessItemProviderAdapterFactory;
import org.junit.Test;

public class CustomContractInputItemProviderTest {

    @Test
    public void should_display_string_type_next_to_TEXT_contract_type() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());

        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        final String label = itemProvider.getPropertyDescriptor(contractInput, ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getLabelProvider(contractInput)
                .getText(contractInput.getType());

        assertTrue(label.contains(String.class.getName()));
    }

    @Test
    public void should_display_integer_type_next_to_INTEGER_contract_type() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());

        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setType(ContractInputType.INTEGER);
        final String label = itemProvider.getPropertyDescriptor(contractInput, ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getLabelProvider(contractInput)
                .getText(contractInput.getType());

        assertTrue(label.contains(Integer.class.getName()));
    }

    @Test
    public void should_display_boolean_type_next_to_BOOLEAN_contract_type() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());

        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setType(ContractInputType.BOOLEAN);
        final String label = itemProvider.getPropertyDescriptor(contractInput, ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getLabelProvider(contractInput)
                .getText(contractInput.getType());

        assertTrue(label.contains(Boolean.class.getName()));
    }

    @Test
    public void should_display_double_type_next_to_DECIMAL_contract_type() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());

        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setType(ContractInputType.DECIMAL);
        final String label = itemProvider.getPropertyDescriptor(contractInput, ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getLabelProvider(contractInput)
                .getText(contractInput.getType());

        assertTrue(label.contains(Double.class.getName()));
    }

    @Test
    public void should_display_date_only_for_LOCALDATE_contract_type() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());

        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setType(ContractInputType.LOCALDATE);
        final String label = itemProvider.getPropertyDescriptor(contractInput, ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getLabelProvider(contractInput)
                .getText(contractInput.getType());

        assertTrue(label.equals(String.format("DATE ONLY - %s", LocalDate.class.getName())));
    }

    @Test
    public void should_display_date_and_time_for_LOCALDATETIME_contract_type() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());

        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setType(ContractInputType.LOCALDATETIME);
        final String label = itemProvider.getPropertyDescriptor(contractInput, ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getLabelProvider(contractInput)
                .getText(contractInput.getType());

        assertTrue(label.equals(String.format("DATE-TIME (NO TIME ZONE) - %s", LocalDateTime.class.getName())));
    }

    @Test
    public void should_display_date_and_time_for_OFFSETDATETIME_contract_type() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());

        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setType(ContractInputType.OFFSETDATETIME);
        final String label = itemProvider.getPropertyDescriptor(contractInput, ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getLabelProvider(contractInput)
                .getText(contractInput.getType());

        assertTrue(label.equals(String.format("DATE-TIME (TIME ZONE) - %s", OffsetDateTime.class.getName())));
    }

    @Test
    public void should_display_FileInputType_type_next_to_FILE_contract_type() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());

        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setType(ContractInputType.FILE);
        final String label = itemProvider.getPropertyDescriptor(contractInput, ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getLabelProvider(contractInput)
                .getText(contractInput.getType());

        assertTrue(label.contains(String.format("org.bonitasoft.engine.bpm.contract.FileInputValue")));
    }

    @Test
    public void should_display_Map_type_next_to_COMPLEX_contract_type() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());

        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        contractInput.setType(ContractInputType.COMPLEX);
        final String label = itemProvider.getPropertyDescriptor(contractInput, ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getLabelProvider(contractInput)
                .getText(contractInput.getType());

        assertTrue(label.contains(String.format(Map.class.getName())));
    }

    @Test
    public void should_remove_LONG_type_if_contract_is_on_a_Task() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());

        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        final Task task = ProcessFactory.eINSTANCE.createTask();
        task.setContract(contract);
        
        final Collection<ContractInputType> choiceOfValues = (Collection<ContractInputType>) itemProvider
                .getPropertyDescriptor(contractInput, ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getChoiceOfValues(contract);

        assertFalse(choiceOfValues.contains(ContractInputType.LONG));
    }

    @Test
    public void should_haveLONG_type_if_contract_is_on_a_Pool() throws Exception {
        final CustomContractInputItemProvider itemProvider = new CustomContractInputItemProvider(
                new ProcessItemProviderAdapterFactory());
        
        final ContractInput contractInput = ProcessFactory.eINSTANCE.createContractInput();
        final Contract contract = ProcessFactory.eINSTANCE.createContract();
        final Pool pool = ProcessFactory.eINSTANCE.createPool();
        pool.setContract(contract);

        final Collection<ContractInputType> choiceOfValues = (Collection<ContractInputType>) itemProvider
                .getPropertyDescriptor(contractInput, ProcessPackage.Literals.CONTRACT_INPUT__TYPE)
                .getChoiceOfValues(contract);

        assertTrue(choiceOfValues.contains(ContractInputType.LONG));
    }
}
