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
package org.bonitasoft.bpm.model.process.assertions;

import static java.lang.String.format;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.bonitasoft.bpm.model.process.Element;
import org.bonitasoft.bpm.model.process.TextAnnotationAttachment;

/**
 * {@link Element} specific assertions - Generated by CustomAssertionGenerator.
 */
public class ElementAssert extends AbstractAssert<ElementAssert, Element> {

    /**
     * Creates a new </code>{@link ElementAssert}</code> to make assertions on actual Element.
     * 
     * @param actual the Element we want to make assertions on.
     */
    public ElementAssert(final Element actual) {
        super(actual, ElementAssert.class);
    }

    /**
     * An entry point for ElementAssert to follow AssertJ standard <code>assertThat()</code> statements.<br>
     * With a static import, one's can write directly : <code>assertThat(myElement)</code> and get specific assertion with code completion.
     * 
     * @param actual the Element we want to make assertions on.
     * @return a new </code>{@link ElementAssert}</code>
     */
    public static ElementAssert assertThat(final Element actual) {
        return new ElementAssert(actual);
    }

    /**
     * Verifies that the actual Element's documentation is equal to the given one.
     * 
     * @param documentation the given documentation to compare the actual Element's documentation to.
     * @return this assertion object.
     * @throws AssertionError - if the actual Element's documentation is not equal to the given one.
     */
    public ElementAssert hasDocumentation(final String documentation) {
        // check that actual Element we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("\nExpected <%s> documentation to be:\n  <%s>\n but was:\n  <%s>", actual,
                documentation, actual.getDocumentation());

        // check
        if (!actual.getDocumentation().equals(documentation)) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual Element's name is equal to the given one.
     * 
     * @param name the given name to compare the actual Element's name to.
     * @return this assertion object.
     * @throws AssertionError - if the actual Element's name is not equal to the given one.
     */
    public ElementAssert hasName(final String name) {
        // check that actual Element we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("\nExpected <%s> name to be:\n  <%s>\n but was:\n  <%s>", actual, name,
                actual.getName());

        // check
        if (!actual.getName().equals(name)) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual Element's textAnnotationAttachment contains the given TextAnnotationAttachment elements.
     * 
     * @param textAnnotationAttachment the given elements that should be contained in actual Element's textAnnotationAttachment.
     * @return this assertion object.
     * @throws AssertionError if the actual Element's textAnnotationAttachment does not contain all given TextAnnotationAttachment elements.
     */
    public ElementAssert hasTextAnnotationAttachment(final TextAnnotationAttachment... textAnnotationAttachment) {
        // check that actual Element we want to make assertions on is not null.
        isNotNull();

        // check that given TextAnnotationAttachment varargs is not null.
        if (textAnnotationAttachment == null) {
            throw new AssertionError("Expecting textAnnotationAttachment parameter not to be null.");
        }

        // check with standard error message (see commented below to set your own message).
        Assertions.assertThat(actual.getTextAnnotationAttachment()).contains(textAnnotationAttachment);

        // uncomment the 4 lines below if you want to build your own error message :
        // WritableAssertionInfo assertionInfo = new WritableAssertionInfo();
        // String errorMessage = "my error message";
        // assertionInfo.overridingErrorMessage(errorMessage);
        // Iterables.instance().assertContains(assertionInfo, actual.getTeamMates(), teamMates);

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual Element has no textAnnotationAttachment.
     * 
     * @return this assertion object.
     * @throws AssertionError if the actual Element's textAnnotationAttachment is not empty.
     */
    public ElementAssert hasNoTextAnnotationAttachment() {
        // check that actual Element we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format(
                "\nExpected :\n  <%s>\nnot to have textAnnotationAttachment but had :\n  <%s>", actual,
                actual.getTextAnnotationAttachment());

        // check
        if (!actual.getTextAnnotationAttachment().isEmpty()) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

}
