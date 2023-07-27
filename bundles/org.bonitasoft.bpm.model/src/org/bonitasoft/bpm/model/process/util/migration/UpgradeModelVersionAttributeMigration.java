/**
 * Copyright (C) 2022 Bonitasoft S.A.
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
package org.bonitasoft.bpm.model.process.util.migration;

import org.bonitasoft.bpm.model.configuration.ConfigurationPackage;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorConfigurationPackage;
import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * Migration which just updates the model version attribute value to the current value
 */
public class UpgradeModelVersionAttributeMigration extends CustomMigration {

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.edapt.migration.CustomMigration#migrateAfter(org.eclipse.emf.edapt.spi.migration.Model,
     * org.eclipse.emf.edapt.spi.migration.Metamodel)
     */
    @Override
    public void migrateAfter(Model model, Metamodel metamodel) throws MigrationException {
        EList<Instance> mainProcesses = model
                .getAllInstances(ProcessPackage.eNS_PREFIX + "." + ProcessPackage.Literals.MAIN_PROCESS.getName());
        mainProcesses.forEach(mp -> mp.set(ProcessPackage.Literals.MAIN_PROCESS__BONITA_MODEL_VERSION.getName(),
                HistoryUtils.CURRENT_MODEL_VERSION));

        EList<Instance> connConfigs = model.getAllInstances(ConnectorConfigurationPackage.eNS_PREFIX + "."
                + ConnectorConfigurationPackage.Literals.CONNECTOR_CONFIGURATION.getName());
        connConfigs.forEach(
                c -> c.set(ConnectorConfigurationPackage.Literals.CONNECTOR_CONFIGURATION__MODEL_VERSION.getName(),
                        HistoryUtils.CURRENT_MODEL_VERSION));

        EList<Instance> configs = model.getAllInstances(
                ConfigurationPackage.eNS_PREFIX + "." + ConfigurationPackage.Literals.CONFIGURATION.getName());
        configs.forEach(c -> c.set(ConfigurationPackage.Literals.CONFIGURATION__VERSION.getName(),
                HistoryUtils.CURRENT_MODEL_VERSION));
    }

}
