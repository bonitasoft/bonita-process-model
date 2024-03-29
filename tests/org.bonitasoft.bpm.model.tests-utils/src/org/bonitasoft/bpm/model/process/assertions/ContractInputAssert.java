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
import org.assertj.core.api.Assertions;
import org.bonitasoft.bpm.model.process.ContractInput;
import org.bonitasoft.bpm.model.process.ContractInputMapping;
import org.bonitasoft.bpm.model.process.ContractInputType;

/**
 * {@link ContractInput} specific assertions - Generated by CustomAssertionGenerator.
 */
public class ContractInputAssert extends AbstractAssert<ContractInputAssert, ContractInput> {

    /**
     * Creates a new </code>{@link ContractInputAssert}</code> to make assertions on actual ContractInput.
     *
     * @param actual the ContractInput we want to make assertions on.
     */
    public ContractInputAssert(final ContractInput actual) {
        super(actual, ContractInputAssert.class);
    }

    /**
     * An entry point for ContractInputAssert to follow AssertJ standard <code>assertThat()</code> statements.<br>
     * With a static import, one's can write directly : <code>assertThat(myContractInput)</code> and get specific assertion with code completion.
     *
     * @param actual the ContractInput we want to make assertions on.
     * @return a new </code>{@link ContractInputAssert}</code>
     */
    public static ContractInputAssert assertThat(final ContractInput actual) {
        return new ContractInputAssert(actual);
    }

    /**
     * Verifies that the actual ContractInput's description is equal to the given one.
     *
     * @param description the given description to compare the actual ContractInput's description to.
     * @return this assertion object.
     * @throws AssertionError - if the actual ContractInput's description is not equal to the given one.
     */
    public ContractInputAssert hasDescription(final String description) {
        // check that actual ContractInput we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("\nExpected <%s> description to be:\n  <%s>\n but was:\n  <%s>", actual,
                description, actual.getDescription());

        // check
        if (!actual.getDescription().equals(description)) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual ContractInput's inputs contains the given ContractInput elements.
     *
     * @param inputs the given elements that should be contained in actual ContractInput's inputs.
     * @return this assertion object.
     * @throws AssertionError if the actual ContractInput's inputs does not contain all given ContractInput elements.
     */
    public ContractInputAssert hasInputs(final ContractInput... inputs) {
        // check that actual ContractInput we want to make assertions on is not null.
        isNotNull();

        // check that given ContractInput varargs is not null.
        if (inputs == null) {
            throw new AssertionError("Expecting inputs parameter not to be null.");
        }

        // check with standard error message (see commented below to set your own message).
        Assertions.assertThat(actual.getInputs()).contains(inputs);

        // uncomment the 4 lines below if you want to build your own error message :
        // WritableAssertionInfo assertionInfo = new WritableAssertionInfo();
        // String errorMessage = "my error message";
        // assertionInfo.overridingErrorMessage(errorMessage);
        // Iterables.instance().assertContains(assertionInfo, actual.getTeamMates(), teamMates);

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual ContractInput has no inputs.
     *
     * @return this assertion object.
     * @throws AssertionError if the actual ContractInput's inputs is not empty.
     */
    public ContractInputAssert hasNoInputs() {
        // check that actual ContractInput we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("\nExpected :\n  <%s>\nnot to have inputs but had :\n  <%s>", actual,
                actual.getInputs());

        // check
        if (!actual.getInputs().isEmpty()) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual ContractInput's mapping is equal to the given one.
     *
     * @param mapping the given mapping to compare the actual ContractInput's mapping to.
     * @return this assertion object.
     * @throws AssertionError - if the actual ContractInput's mapping is not equal to the given one.
     */
    public ContractInputAssert hasMapping(final ContractInputMapping mapping) {
        // check that actual ContractInput we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("\nExpected <%s> mapping to be:\n  <%s>\n but was:\n  <%s>", actual, mapping,
                actual.getMapping());

        // check
        if (!actual.getMapping().equals(mapping)) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual ContractInput is multiple.
     *
     * @return this assertion object.
     * @throws AssertionError - if the actual ContractInput is not multiple.
     */
    public ContractInputAssert isMultiple() {
        // check that actual ContractInput we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("Expected actual ContractInput to be multiple but was not.", actual);

        // check
        if (!actual.isMultiple()) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual ContractInput is not multiple.
     *
     * @return this assertion object.
     * @throws AssertionError - if the actual ContractInput is multiple.
     */
    public ContractInputAssert isNotMultiple() {
        // check that actual ContractInput we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("Expected actual ContractInput not to be multiple but was.", actual);

        // check
        if (actual.isMultiple()) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual ContractInput's name is equal to the given one.
     *
     * @param name the given name to compare the actual ContractInput's name to.
     * @return this assertion object.
     * @throws AssertionError - if the actual ContractInput's name is not equal to the given one.
     */
    public ContractInputAssert hasName(final String name) {
        // check that actual ContractInput we want to make assertions on is not null.
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
     * Verifies that the actual ContractInput's type is equal to the given one.
     *
     * @param type the given type to compare the actual ContractInput's type to.
     * @return this assertion object.
     * @throws AssertionError - if the actual ContractInput's type is not equal to the given one.
     */
    public ContractInputAssert hasType(final ContractInputType type) {
        // check that actual ContractInput we want to make assertions on is not null.
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

}
