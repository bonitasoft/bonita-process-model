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
package org.bonitasoft.bpm.migration.custom.migration.expression;

import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Vincent Hemery
 */
public class JavaGetterExpressionMigration extends CustomMigration {

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.edapt.migration.CustomMigration#migrateAfter(org.eclipse.emf.edapt.spi.migration.Model,
     * org.eclipse.emf.edapt.spi.migration.Metamodel)
     */
    @Override
    public void migrateAfter(Model model, Metamodel metamodel) throws MigrationException {
        for (final Instance expression : model.getAllInstances("expression.Expression")) {
            if (ExpressionConstants.JAVA_TYPE.equals(expression.get("type"))) {
                expression.set("type", ExpressionConstants.SCRIPT_TYPE);
                expression.set("interpreter", ExpressionConstants.GROOVY);
                EList<Instance> refs = expression.getLinks("referencedElements");
                if (!refs.isEmpty() && refs.get(0).instanceOf("Element")) {
                    var name = refs.get(0).get("name");
                    String newContent = String.format("%s.%s()", name, expression.get("content"));
                    expression.set("content", newContent);
                }
            }
        }
    }

}
