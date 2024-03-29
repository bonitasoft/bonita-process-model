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
package org.bonitasoft.bpm.migration.custom.migration.connector;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
class UpdateConnectorDefinitionMigrationTest {

    @Mock
    private Model model;

    @Mock
    private Metamodel metamodel;

    @Mock
    private AbstractUpdateConnectorDefinitionMigration updateConnectorVersionMigration;

    @Test
    void should_migrateAfter_update_definitionVersion_in_connector() throws Exception {
        //Given
        doCallRealMethod().when(updateConnectorVersionMigration).migrateAfter(model, metamodel);
        final EList<Instance> connectorInstanceList = connectorInstanceList("id1", "id2");
        final Instance oldConnectorInstance = aConnectorInstance("id1", "0.9");
        connectorInstanceList.add(oldConnectorInstance);
        when(model.getAllInstances("process.Connector")).thenReturn(connectorInstanceList);
        when(model.getAllInstances("connectorconfiguration.ConnectorConfiguration"))
                .thenReturn(new BasicEList<Instance>());
        when(updateConnectorVersionMigration.shouldUpdateVersion("id1")).thenReturn(true);
        when(updateConnectorVersionMigration.getOldDefinitionVersion()).thenReturn("1.0");
        when(updateConnectorVersionMigration.getNewDefinitionVersion()).thenReturn("2.0");

        //When
        updateConnectorVersionMigration.migrateAfter(model, metamodel);

        //Then
        final Instance id1Connector = connectorInstanceList.get(0);
        final Instance id2Connector = connectorInstanceList.get(1);
        verify(id1Connector).set(AbstractUpdateConnectorDefinitionMigration.DEFINITION_VERSION_FEATURE_NAME, "2.0");
        verify(id2Connector, never()).set(AbstractUpdateConnectorDefinitionMigration.DEFINITION_VERSION_FEATURE_NAME,
                "2.0");
        verify(oldConnectorInstance, never()).set(
                AbstractUpdateConnectorDefinitionMigration.DEFINITION_VERSION_FEATURE_NAME,
                "2.0");
    }

    @Test
    void should_migrateAfter_update_version_in_connector_configuration() throws Exception {
        //Given
        doCallRealMethod().when(updateConnectorVersionMigration).migrateAfter(model, metamodel);
        final EList<Instance> connectorConfigInstanceList = connectorConfiguratiobInstanceList("id1", "id2");
        final Instance oldConnectorConfInstance = aConnectorConfigurationInstance("id1", "0.9");
        connectorConfigInstanceList.add(oldConnectorConfInstance);
        when(model.getAllInstances("connectorconfiguration.ConnectorConfiguration"))
                .thenReturn(connectorConfigInstanceList);
        when(model.getAllInstances("process.Connector")).thenReturn(new BasicEList<Instance>());
        when(updateConnectorVersionMigration.shouldUpdateVersion("id1")).thenReturn(true);
        when(updateConnectorVersionMigration.getOldDefinitionVersion()).thenReturn("1.0");
        when(updateConnectorVersionMigration.getNewDefinitionVersion()).thenReturn("2.0");

        //When
        updateConnectorVersionMigration.migrateAfter(model, metamodel);

        //Then
        final Instance id1ConnectorConf = connectorConfigInstanceList.get(0);
        final Instance id2ConnectorConf = connectorConfigInstanceList.get(1);
        verify(id1ConnectorConf).set(AbstractUpdateConnectorDefinitionMigration.VERSION_FEATURE_NAME, "2.0");
        verify(id2ConnectorConf, never()).set(AbstractUpdateConnectorDefinitionMigration.VERSION_FEATURE_NAME, "2.0");
        verify(oldConnectorConfInstance, never()).set(AbstractUpdateConnectorDefinitionMigration.VERSION_FEATURE_NAME,
                "2.0");
    }

    private EList<Instance> connectorInstanceList(final String... defIds) {
        final BasicEList<Instance> instances = new BasicEList<Instance>();
        for (final String defId : defIds) {
            instances.add(aConnectorInstance(defId, "1.0"));
        }
        return instances;
    }

    private EList<Instance> connectorConfiguratiobInstanceList(final String... defIds) {
        final BasicEList<Instance> instances = new BasicEList<Instance>();
        for (final String defId : defIds) {
            instances.add(aConnectorConfigurationInstance(defId, "1.0"));
        }
        return instances;
    }

    private Instance aConnectorConfigurationInstance(final String definitionId, final String definitionVersion) {
        final Instance instance = mock(Instance.class, withSettings().strictness(Strictness.LENIENT));
        when(instance.get(AbstractUpdateConnectorDefinitionMigration.DEFINITION_ID_FEATURE_NAME))
                .thenReturn(definitionId);
        when(instance.get(AbstractUpdateConnectorDefinitionMigration.VERSION_FEATURE_NAME))
                .thenReturn(definitionVersion);
        return instance;
    }

    private Instance aConnectorInstance(final String definitionId, final String definitionVersion) {
        final Instance instance = mock(Instance.class, withSettings().strictness(Strictness.LENIENT));
        when(instance.get(AbstractUpdateConnectorDefinitionMigration.DEFINITION_ID_FEATURE_NAME))
                .thenReturn(definitionId);
        when(instance.get(AbstractUpdateConnectorDefinitionMigration.DEFINITION_VERSION_FEATURE_NAME))
                .thenReturn(definitionVersion);
        return instance;
    }
}
