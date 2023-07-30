/**
 * Copyright (C) 2012 Bonitasoft S.A.
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

import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 */
public class UpdatePoolSize extends CustomMigration {

    private static final String LAYOUT_CONSTRAINT = "layoutConstraint";
    private static final String POOL_VISUAL_ID = "2007";
    private static final int DEFAULT_WIDTH = 975;

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel)
            throws MigrationException {
        Optional<EClass> nodeEC = NotationPackageLoader.getInstance().getNode();
        if (nodeEC.isPresent()) {
            for (final Instance node : model.getAllInstances(nodeEC.get())) {
                if (node.get("type").equals(POOL_VISUAL_ID)
                        && node.get(LAYOUT_CONSTRAINT) != null
                        && (Integer) ((Instance) node.get(LAYOUT_CONSTRAINT)).get("width") == 0) {
                    ((Instance) node.get(LAYOUT_CONSTRAINT)).set("width", DEFAULT_WIDTH);
                }
            }
        }
    }

}
