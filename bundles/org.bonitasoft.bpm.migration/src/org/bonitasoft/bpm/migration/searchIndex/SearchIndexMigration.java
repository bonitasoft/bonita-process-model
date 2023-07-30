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
package org.bonitasoft.bpm.migration.searchIndex;

import org.bonitasoft.bpm.migration.utils.StringToExpressionConverter;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author aurelie zara
 */
public class SearchIndexMigration extends CustomMigration {

    private static final int MAX = 5;

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel)
            throws MigrationException {
        for (final Instance pool : model.getAllInstances("process.Pool")) {
            for (int i = 0; i < MAX; i++) {
                pool.add("searchIndexes", createSearchIndex(model));
            }
        }
    }

    private Instance createSearchIndex(final Model model) {
        final Instance searchIndex = model.newInstance("process.SearchIndex");
        final Instance nameExpression = StringToExpressionConverter.createExpressionInstance(model, "", "",
                String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
        searchIndex.set("name", nameExpression);
        final Instance valueExpression = StringToExpressionConverter.createExpressionInstance(model, "", "",
                String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true);
        searchIndex.set("value", valueExpression);
        return searchIndex;
    }
}
