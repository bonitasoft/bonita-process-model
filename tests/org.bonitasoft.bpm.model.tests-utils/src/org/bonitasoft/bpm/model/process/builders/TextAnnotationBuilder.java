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

import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.bpm.model.process.TextAnnotation;

/**
 * @author Romain Bioteau
 */
public class TextAnnotationBuilder extends ElementBuilder<TextAnnotation, TextAnnotationBuilder> {

    public static TextAnnotationBuilder create() {
        return new TextAnnotationBuilder();
    }

    public TextAnnotationBuilder withText(final String text) {
        getBuiltInstance().setText(text);
        return this;
    }

    @Override
    protected TextAnnotation newInstance() {
        return ProcessFactory.eINSTANCE.createTextAnnotation();
    }

    @Override
    protected TextAnnotationBuilder getThis() {
        return this;
    }

    @Override
    protected TextAnnotation getBuiltInstance() {
        return builtEObject;
    }
}
