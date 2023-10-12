/**
 * Copyright (C) 2014 Bonitasoft S.A.
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

import org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.bpm.model.process.FormMapping;
import org.bonitasoft.bpm.model.process.FormMappingType;
import org.bonitasoft.bpm.model.process.ProcessFactory;

/**
 * @author Romain Bioteau
 */
public class FormMappingBuilder {

    public static FormMappingBuilder aFormMapping() {
        return new FormMappingBuilder();
    }

    private final FormMapping instance;

    public FormMappingBuilder() {
        instance = ProcessFactory.eINSTANCE.createFormMapping();
    }

    public FormMappingBuilder havingTargetForm(final ExpressionBuilder targetForm) {
        instance.setTargetForm(targetForm.build());
        return this;
    }

    public FormMappingBuilder withURL(final String url) {
        instance.setUrl(url);
        return this;
    }

    public FormMappingBuilder withType(final FormMappingType type) {
        instance.setType(type);
        return this;
    }

    public FormMapping build() {
        return instance;
    }

}
