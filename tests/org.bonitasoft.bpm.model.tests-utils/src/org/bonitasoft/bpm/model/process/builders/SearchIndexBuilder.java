/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.bpm.model.process.builders;

import org.bonitasoft.bpm.model.Buildable;
import org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.bpm.model.process.SearchIndex;

/**
 * @author Romain Bioteau
 */
public class SearchIndexBuilder implements Buildable<SearchIndex> {

    public static SearchIndexBuilder aSearchIndex() {
        return new SearchIndexBuilder(ProcessFactory.eINSTANCE.createSearchIndex());
    }

    private final SearchIndex searchIndex;

    private SearchIndexBuilder(final SearchIndex searchIndex) {
        this.searchIndex = searchIndex;
    }

    public SearchIndexBuilder havingNameExpression(final ExpressionBuilder nameExpression) {
        searchIndex.setName(nameExpression.build());
        return this;
    }

    public SearchIndexBuilder havingValueExpression(final ExpressionBuilder valueExpression) {
        searchIndex.setValue(valueExpression.build());
        return this;
    }

    @Override
    public SearchIndex build() {
        return searchIndex;
    }

}
