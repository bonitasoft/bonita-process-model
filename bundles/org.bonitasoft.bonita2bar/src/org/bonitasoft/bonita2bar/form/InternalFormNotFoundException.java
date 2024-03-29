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
package org.bonitasoft.bonita2bar.form;

import org.bonitasoft.bpm.model.process.FormMapping;

public class InternalFormNotFoundException extends FormMappingException {

    private static final long serialVersionUID = 1L;

    public InternalFormNotFoundException(final FormMapping formMapping) {
        super(formMapping);
    }

    @Override
    public String getMessage() {
        if (formId() != null) {
            return String.format("UIDesigner form with id '%s' has not been found for '%s' %s.", formId(),
                    containerName(), containerType());
        }
        return String.format("No UIDesigner form is defined on '%s' %s.", containerName(), containerType());
    }

}
