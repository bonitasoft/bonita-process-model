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

import org.bonitasoft.bpm.model.Buildable;
import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.process.InputMapping;
import org.bonitasoft.bpm.model.process.InputMappingAssignationType;
import org.bonitasoft.bpm.model.process.ProcessFactory;

public class InputMappingBuilder implements Buildable<InputMapping> {

    private final InputMapping inputMapping;

    public static InputMappingBuilder anInputMapping() {
        return new InputMappingBuilder();
    }

    protected InputMappingBuilder() {
        inputMapping = ProcessFactory.eINSTANCE.createInputMapping();
    }

    /**
     * @param elementInprocessSource : the data of the source process
     * @return
     */
    public InputMappingBuilder setProcessSource(final Expression elementInprocessSource) {
        inputMapping.setProcessSource(elementInprocessSource);
        return this;
    }

    /**
     * @param assignationType
     * @param elementInCalledProcess : the Data or Contract Input name of the called process
     * @return
     */
    public InputMappingBuilder setSubProcessTarget(final InputMappingAssignationType assignationType,
            final String elementInCalledProcess) {
        inputMapping.setSubprocessTarget(elementInCalledProcess);
        inputMapping.setAssignationType(assignationType);
        return this;
    }

    @Override
    public InputMapping build() {
        return inputMapping;
    }

}
