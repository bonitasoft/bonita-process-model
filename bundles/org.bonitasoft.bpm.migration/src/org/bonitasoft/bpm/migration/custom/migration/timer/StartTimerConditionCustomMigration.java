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
package org.bonitasoft.bpm.migration.custom.migration.timer;

import java.util.Date;

import org.bonitasoft.bpm.migration.utils.LegacyTimerExpressionGenerator;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.bpm.model.process.StartTimerEvent;
import org.bonitasoft.bpm.model.process.StartTimerScriptType;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 */
public class StartTimerConditionCustomMigration extends CustomMigration {

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel)
            throws MigrationException {
        for (final Instance startTimer : model.getAllInstances("process.StartTimerEvent")) {
            final EEnumLiteral scriptType = startTimer.get("scriptType");
            final StartTimerEvent event = ProcessFactory.eINSTANCE.createStartTimerEvent();
            event.setAt(startTimer.get("at"));
            event.setFrom(startTimer.get("from"));
            event.setMonth(startTimer.get("month"));
            event.setDay(startTimer.get("day"));
            event.setHours(startTimer.get("hours"));
            event.setDayNumber(startTimer.get("dayNumber"));
            event.setMinutes(startTimer.get("minutes"));
            event.setSeconds(startTimer.get("seconds"));
            event.setScriptType(StartTimerScriptType.valueOf(scriptType.getLiteral()));
            if (LegacyTimerExpressionGenerator.isCycle(event)) {
                migrateCycleCondition(model, startTimer, event);
            } else if (event.getScriptType() == StartTimerScriptType.CONSTANT && event.getAt() != null) {
                migrateConstantCondition(model, startTimer);
            }
        }
    }

    private void migrateConstantCondition(final Model model, final Instance startTimer) {
        Instance condition = startTimer.get("condition");
        if (condition != null) {
            model.delete(condition);
        }
        condition = model.newInstance("expression.Expression");
        condition.set("name", "fixedDate");
        condition.set("content", LegacyTimerExpressionGenerator.generateConstant(startTimer.get("at")));
        condition.set("returnType", Date.class.getName());
        condition.set("type", ExpressionConstants.SCRIPT_TYPE);
        condition.set("interpreter", ExpressionConstants.GROOVY);
        startTimer.set("condition", condition);
    }

    private void migrateCycleCondition(final Model model, final Instance startTimer, final StartTimerEvent event) {
        var timerExpressionGenerator = new LegacyTimerExpressionGenerator();
        final String cron = timerExpressionGenerator.getTimerExpressionContent(event);
        if (cron != null) {
            Instance condition = startTimer.get(ProcessPackage.Literals.ABSTRACT_TIMER_EVENT__CONDITION.getName());
            if (condition != null) {
                model.delete(condition);
            }
            condition = model.newInstance("expression.Expression");
            condition.set("name", cron);
            condition.set("content", cron);
            condition.set("returnType", String.class.getName());
            condition.set("type", ExpressionConstants.CONSTANT_TYPE);

            startTimer.set(ProcessPackage.Literals.ABSTRACT_TIMER_EVENT__CONDITION.getName(), condition);
        }
    }

}
