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
package org.bonitasoft.bonita2bar.process.expression;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.expression.ExpressionFactory;
import org.bonitasoft.bpm.model.process.decision.DecisionFactory;
import org.bonitasoft.bpm.model.process.decision.DecisionTable;
import org.bonitasoft.bpm.model.process.decision.DecisionTableLine;
import org.bonitasoft.bpm.model.process.decision.transitions.TakeTransitionAction;
import org.bonitasoft.bpm.model.process.decision.transitions.TransitionsFactory;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.Test;

import groovy.lang.GroovyShell;

class DecisionTableUtilTest {

    @Test
    void testSimpleDecisionOK() {
        final TakeTransitionAction takeTransition = TransitionsFactory.eINSTANCE.createTakeTransitionAction();
        takeTransition.setTakeTransition(true);
        final TakeTransitionAction dontTakeTransition = TransitionsFactory.eINSTANCE.createTakeTransitionAction();
        dontTakeTransition.setTakeTransition(false);

        DecisionTable decisionTable = DecisionFactory.eINSTANCE.createDecisionTable();
        {
            DecisionTableLine line = DecisionFactory.eINSTANCE.createDecisionTableLine();
            decisionTable.getLines().add(line);
            Expression condition = createNewComparisonExpression("1==1");
            line.getConditions().add(condition);
            line.setAction(EcoreUtil.copy(takeTransition));
        }
        {
            DecisionTableLine line = DecisionFactory.eINSTANCE.createDecisionTableLine();
            decisionTable.getLines().add(line);
            Expression condition2 = createNewComparisonExpression("1==1");
            line.getConditions().add(condition2);
            line.setAction(EcoreUtil.copy(takeTransition));
        }
        decisionTable.setDefaultAction(dontTakeTransition);
        Expression groovy = DecisionTableUtil.toGroovyScriptExpression(decisionTable);
        GroovyShell shell = new GroovyShell();
        assertTrue((Boolean) shell.evaluate(groovy.getContent()));
    }

    private Expression createNewComparisonExpression(String script) {
        Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setReturnType(Boolean.class.getName());
        exp.setReturnTypeFixed(true);
        exp.setType(ExpressionConstants.CONDITION_TYPE);
        exp.setContent(script);
        return exp;
    }

    @Test
    void testSimpleDecisionKO() {
        final TakeTransitionAction takeTransition = TransitionsFactory.eINSTANCE.createTakeTransitionAction();
        takeTransition.setTakeTransition(true);
        final TakeTransitionAction dontTakeTransition = TransitionsFactory.eINSTANCE.createTakeTransitionAction();
        dontTakeTransition.setTakeTransition(true);

        DecisionTable decisionTable = DecisionFactory.eINSTANCE.createDecisionTable();
        DecisionTableLine line = DecisionFactory.eINSTANCE.createDecisionTableLine();
        decisionTable.getLines().add(line);
        Expression condition = createNewComparisonExpression("1==2");
        line.setAction(takeTransition);
        line.getConditions().add(condition);
        decisionTable.setDefaultAction(dontTakeTransition);
        Expression groovy = DecisionTableUtil.toGroovyScriptExpression(decisionTable);
        GroovyShell shell = new GroovyShell();
        assertTrue((Boolean) shell.evaluate(groovy.getContent()));
    }

}
