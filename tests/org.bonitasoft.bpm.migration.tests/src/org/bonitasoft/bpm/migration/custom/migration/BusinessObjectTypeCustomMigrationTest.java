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
package org.bonitasoft.bpm.migration.custom.migration;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.impl.MetamodelImpl;
import org.eclipse.emf.edapt.spi.migration.impl.ModelImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BusinessObjectTypeCustomMigrationTest {

    private BusinessObjectTypeCustomMigration migrationUnderTest;

    @Mock
    private ModelImpl model;

    @Mock
    private MetamodelImpl metamodel;

    @Mock
    private Instance diagramInstance;

    @Mock
    private Instance businessDataTypeInstance;

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        migrationUnderTest = new BusinessObjectTypeCustomMigration();
        final EList<Instance> diagramList = new BasicEList<Instance>();
        diagramList.add(diagramInstance);
        when(model.getAllInstances("process.MainProcess")).thenReturn(diagramList);
        when(model.newInstance("process.BusinessObjectType")).thenReturn(businessDataTypeInstance);
        final EList<Instance> emptyDatatypesList = new BasicEList<Instance>();
        when(diagramInstance.get("datatypes")).thenReturn(emptyDatatypesList);
    }

    @Test
    void should_migrateAfter_Add_the_businessObjectType_InDiagram_IfNotExists() throws Exception {
        migrationUnderTest.migrateAfter(model, metamodel);
        verify(model).newInstance("process.BusinessObjectType");
        verify(businessDataTypeInstance).set("name", BusinessObjectTypeCustomMigration.BUSINESS_OBJECT_DATA_TYPE_NAME);
        verify(diagramInstance).add("datatypes", businessDataTypeInstance);
    }

    @Test
    void should_migrateAfter_NOT_Add_the_businessObjectType_InDiagram_IfNotExists() throws Exception {
        final EList<Instance> datatypesList = new BasicEList<Instance>();
        when(businessDataTypeInstance.instanceOf("process.BusinessObjectType")).thenReturn(true);
        datatypesList.add(businessDataTypeInstance);
        when(diagramInstance.get("datatypes")).thenReturn(datatypesList);
        migrationUnderTest.migrateAfter(model, metamodel);
        verify(diagramInstance, never()).add("datatypes", businessDataTypeInstance);
    }

}
