/**
 * Copyright (C) 2011 Bonitasoft S.A.
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
package org.bonitasoft.bpm.model.edit.decision;

import org.bonitasoft.bpm.model.process.decision.provider.DecisionItemProviderAdapterFactory;
import org.bonitasoft.bpm.model.process.decision.provider.DecisionTableActionItemProvider;
import org.eclipse.emf.common.notify.Adapter;

/**
 * @author Mickael Istria
 */
public class CustomDecisionItemProviderAdapterFactory extends DecisionItemProviderAdapterFactory {

    private DecisionTableActionItemProvider decisionTableActionItemProvider;

    @Override
    public Adapter createDecisionTableLineAdapter() {
        if (decisionTableLineItemProvider == null) {
            decisionTableLineItemProvider = new CustomDecisionTableLineItemProvider(this);
        }

        return decisionTableLineItemProvider;
    }

    @Override
    public Adapter createDecisionTableActionAdapter() {
        if (decisionTableActionItemProvider == null) {
            decisionTableActionItemProvider = new CustomDecisionTableActionItemProvider(this);
        }

        return decisionTableActionItemProvider;
    }

}
