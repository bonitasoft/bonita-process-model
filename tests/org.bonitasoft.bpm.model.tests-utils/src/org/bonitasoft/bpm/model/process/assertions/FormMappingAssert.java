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
package org.bonitasoft.bpm.model.process.assertions;

import static java.lang.String.format;

import org.assertj.core.api.AbstractAssert;
import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.process.FormMapping;
import org.bonitasoft.bpm.model.process.FormMappingType;

/**
 * {@link FormMapping} specific assertions - Generated by CustomAssertionGenerator.
 */
public class FormMappingAssert extends AbstractAssert<FormMappingAssert, FormMapping> {

    /**
     * Creates a new </code>{@link FormMappingAssert}</code> to make assertions on actual FormMapping.
     *
     * @param actual the FormMapping we want to make assertions on.
     */
    public FormMappingAssert(final FormMapping actual) {
        super(actual, FormMappingAssert.class);
    }

    /**
     * An entry point for FormMappingAssert to follow AssertJ standard <code>assertThat()</code> statements.<br>
     * With a static import, one's can write directly : <code>assertThat(myFormMapping)</code> and get specific assertion with code completion.
     *
     * @param actual the FormMapping we want to make assertions on.
     * @return a new </code>{@link FormMappingAssert}</code>
     */
    public static FormMappingAssert assertThat(final FormMapping actual) {
        return new FormMappingAssert(actual);
    }

    /**
     * Verifies that the actual FormMapping's targetForm is equal to the given one.
     *
     * @param targetForm the given targetForm to compare the actual FormMapping's targetForm to.
     * @return this assertion object.
     * @throws AssertionError - if the actual FormMapping's targetForm is not equal to the given one.
     */
    public FormMappingAssert hasTargetForm(final Expression targetForm) {
        // check that actual FormMapping we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("\nExpected <%s> targetForm to be:\n  <%s>\n but was:\n  <%s>", actual,
                targetForm, actual.getTargetForm());

        // check
        if (!actual.getTargetForm().equals(targetForm)) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual FormMapping's type is equal to the given one.
     *
     * @param type the given type to compare the actual FormMapping's type to.
     * @return this assertion object.
     * @throws AssertionError - if the actual FormMapping's type is not equal to the given one.
     */
    public FormMappingAssert hasType(final FormMappingType type) {
        // check that actual FormMapping we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("\nExpected <%s> type to be:\n  <%s>\n but was:\n  <%s>", actual, type,
                actual.getType());

        // check
        if (!actual.getType().equals(type)) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual FormMapping's url is equal to the given one.
     *
     * @param url the given url to compare the actual FormMapping's url to.
     * @return this assertion object.
     * @throws AssertionError - if the actual FormMapping's url is not equal to the given one.
     */
    public FormMappingAssert hasUrl(final String url) {
        // check that actual FormMapping we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("\nExpected <%s> url to be:\n  <%s>\n but was:\n  <%s>", actual, url,
                actual.getUrl());

        // check
        if (!actual.getUrl().equals(url)) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

}
