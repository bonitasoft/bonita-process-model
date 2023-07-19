/**
 * Copyright (C) 2021 Bonitasoft S.A.
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

import java.io.IOException;

import org.bonitasoft.engine.bpm.bar.BarResource;

public class CustomPageBarResourceBuilder {

    private static final String BAR_CUSTOMPAGES_LOCATION = "customPages";

    private FormBuilder formBuilder;

    public CustomPageBarResourceBuilder(FormBuilder formBuilder) {
        this.formBuilder = formBuilder;
    }

    public BarResource newBarResource(final String targetFormCustomPageId, final String formId) throws IOException {
        return new BarResource(BAR_CUSTOMPAGES_LOCATION + "/" + targetFormCustomPageId + ".zip",
                formBuilder.export(formId));
    }

}
