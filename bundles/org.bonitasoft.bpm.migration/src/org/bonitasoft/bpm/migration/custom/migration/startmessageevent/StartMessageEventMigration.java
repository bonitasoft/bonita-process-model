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
package org.bonitasoft.bpm.migration.custom.migration.startmessageevent;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

public class StartMessageEventMigration extends CustomMigration {

    @Override
    public void migrateBefore(Model model, Metamodel metamodel)
            throws MigrationException {
        for (Instance ste : model.getAllInstances("process.StartMessageEvent")) {
            Instance tableCorrelation = ste.get("correlation");
            if (tableCorrelation != null) {
                model.delete(tableCorrelation);
            }
            Instance messageFlow = ste.get("incomingMessag");
            if (messageFlow != null) {
                Instance messageSource = messageFlow.get("source");
                if (messageSource != null) {
                    List<Instance> events = new ArrayList<Instance>();
                    events = messageSource.get("events");
                    for (Instance event : events) {
                        Instance catchMessageEventName = event.get("targetElementExpression");
                        if (catchMessageEventName.get("name").equals(ste.get("name"))) {
                            Instance correlation = event.get("correlation");
                            correlation.set("correlationType",
                                    metamodel.getEEnumLiteral("process.CorrelationTypeActive.INACTIVE"));
                        }
                    }
                }
            }
        }
    }
}
