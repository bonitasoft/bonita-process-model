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
package org.bonitasoft.bpm.migration.custom.migration.contract;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreatePoolContractCustomMigrationTest {

    private AbstractCreateContractCustomMigration customMigration;

    @Mock
    private Model model;

    @Mock
    private Metamodel metamodel;

    @Mock
    private Instance originalPoolInstance;

    @Mock
    private Instance newContractInstance, existingContractInstance;

    @BeforeEach
    void setUp() throws Exception {
        customMigration = new CreatePoolContractCustomMigration();
        final BasicEList<Instance> uniquePoolEList = new BasicEList<Instance>();
        uniquePoolEList.add(originalPoolInstance);
        when(model.getAllInstances("process.Pool")).thenReturn(uniquePoolEList);

    }

    @Test
    void should_migrateAfter_add_an_empty_contract_to_a_pool() throws Exception {
    	when(model.newInstance("process.Contract")).thenReturn(newContractInstance);
        customMigration.migrateAfter(model, metamodel);
        verify(originalPoolInstance).set("contract", newContractInstance);
    }

    @Test
    void should_migrateAfter_NOTadd_an_empty_contract_to_a_pool() throws Exception {
        when(originalPoolInstance.get("contract")).thenReturn(existingContractInstance);
        customMigration.migrateAfter(model, metamodel);
        verify(model, never()).newInstance("process.Contract");
    }

}
