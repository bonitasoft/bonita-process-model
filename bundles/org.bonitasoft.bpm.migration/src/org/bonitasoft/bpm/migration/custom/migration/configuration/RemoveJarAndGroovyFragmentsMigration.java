/**
 * Copyright (C) 2025 Bonitasoft S.A.
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
package org.bonitasoft.bpm.migration.custom.migration.configuration;

import org.bonitasoft.bpm.model.configuration.ConfigurationPackage;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * Removes the Jar and Groovy configuration fragments as they are no longer used for environment configuration.
 * 
 * @author Vincent Hemery
 */
public class RemoveJarAndGroovyFragmentsMigration extends CustomMigration {

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.edapt.migration.CustomMigration#migrateBefore(org.eclipse.emf.edapt.spi.migration.Model,
     * org.eclipse.emf.edapt.spi.migration.Metamodel)
     */
    @Override
    public void migrateBefore(Model model, Metamodel metamodel) throws MigrationException {
        for (final Instance fragment : model.getAllInstances(
                ConfigurationPackage.eNS_PREFIX + "." + ConfigurationPackage.Literals.FRAGMENT.getName())) {
            String fragmentType = fragment.get(ConfigurationPackage.Literals.FRAGMENT__TYPE.getName());
            if ("JAR".equals(fragmentType) || "GROOVY_SCRIPT".equals(fragmentType)) {
                // this fragment is no longer used in environment configuration
                model.delete(fragment);
            }
        }
        for (final Instance fragmentContainer : model.getAllInstances(
                ConfigurationPackage.eNS_PREFIX + "." + ConfigurationPackage.Literals.FRAGMENT_CONTAINER.getName())) {
            if (fragmentContainer.get(ConfigurationPackage.Literals.FRAGMENT_CONTAINER__ID.getName())
                    .equals("GROOVY_SCRIPT")) {
                // this fragment container is no longer necessary
                model.delete(fragmentContainer);
            }
        }
    }

}
