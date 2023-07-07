/** 
 * Copyright (C) 2015 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.bpm.model.process.builders;

import org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.bpm.model.process.CallActivity;
import org.bonitasoft.bpm.model.process.InputMapping;
import org.bonitasoft.bpm.model.process.OutputMapping;
import org.bonitasoft.bpm.model.process.ProcessFactory;

/**
 * @author Romain Bioteau
 */
public class CallActivityBuilder extends ActivityBuilder<CallActivity, CallActivityBuilder> {

    public static CallActivityBuilder aCallActivity() {
        return new CallActivityBuilder();
    }

    public CallActivityBuilder havingCalledActivityName(final ExpressionBuilder callActivityNameExpression) {
        getBuiltInstance().setCalledActivityName(callActivityNameExpression.build());
        return this;
    }

    public CallActivityBuilder havingCalledActivityVersion(final ExpressionBuilder callActivityVersionExpression) {
        getBuiltInstance().setCalledActivityVersion(callActivityVersionExpression.build());
        return this;
    }

    @Override
    protected CallActivity newInstance() {
        return ProcessFactory.eINSTANCE.createCallActivity();
    }

    public CallActivityBuilder havingInputMappings(final InputMapping... inputMappings) {
        if (inputMappings != null) {
            for (final InputMapping inputMapping : inputMappings) {
                getBuiltInstance().getInputMappings().add(inputMapping);
            }
        }
        return this;
    }

    public CallActivityBuilder havingOutputMapping(final OutputMapping... outputMappings) {
        if (outputMappings != null) {
            for (final OutputMapping outputMapping : outputMappings) {
                getBuiltInstance().getOutputMappings().add(outputMapping);
            }
        }
        return this;
    }

}
