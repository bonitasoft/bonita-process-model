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
package org.bonitasoft.bpm.model.expression.assertions;

import static java.lang.String.format;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.process.Connector;
import org.eclipse.emf.ecore.EObject;

/**
 * {@link Expression} specific assertions - Generated by CustomAssertionGenerator.
 */
public class ExpressionAssert extends AbstractAssert<ExpressionAssert, Expression> {

    /**
     * Creates a new </code>{@link ExpressionAssert}</code> to make assertions on actual Expression.
     * 
     * @param actual the Expression we want to make assertions on.
     */
    public ExpressionAssert(final Expression actual) {
        super(actual, ExpressionAssert.class);
    }

    /**
     * An entry point for ExpressionAssert to follow AssertJ standard <code>assertThat()</code> statements.<br>
     * With a static import, one's can write directly : <code>assertThat(myExpression)</code> and get specific assertion with code completion.
     * 
     * @param actual the Expression we want to make assertions on.
     * @return a new </code>{@link ExpressionAssert}</code>
     */
    public static ExpressionAssert assertThat(final Expression actual) {
        return new ExpressionAssert(actual);
    }

    /**
     * Verifies that the actual Expression is automaticDependencies.
     * 
     * @return this assertion object.
     * @throws AssertionError - if the actual Expression is not automaticDependencies.
     */
    public ExpressionAssert isAutomaticDependencies() {
        // check that actual Expression we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("Expected actual Expression to be automaticDependencies but was not.",
                actual);

        // check
        if (!actual.isAutomaticDependencies()) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual Expression is not automaticDependencies.
     * 
     * @return this assertion object.
     * @throws AssertionError - if the actual Expression is automaticDependencies.
     */
    public ExpressionAssert isNotAutomaticDependencies() {
        // check that actual Expression we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("Expected actual Expression not to be automaticDependencies but was.",
                actual);

        // check
        if (actual.isAutomaticDependencies()) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual Expression's connectors contains the given Connector elements.
     * 
     * @param connectors the given elements that should be contained in actual Expression's connectors.
     * @return this assertion object.
     * @throws AssertionError if the actual Expression's connectors does not contain all given Connector elements.
     */
    public ExpressionAssert hasConnectors(final Connector... connectors) {
        // check that actual Expression we want to make assertions on is not null.
        isNotNull();

        // check that given Connector varargs is not null.
        if (connectors == null) {
            throw new AssertionError("Expecting connectors parameter not to be null.");
        }

        // check with standard error message (see commented below to set your own message).
        Assertions.assertThat(actual.getConnectors()).contains(connectors);

        // uncomment the 4 lines below if you want to build your own error message :
        // WritableAssertionInfo assertionInfo = new WritableAssertionInfo();
        // String errorMessage = "my error message";
        // assertionInfo.overridingErrorMessage(errorMessage);
        // Iterables.instance().assertContains(assertionInfo, actual.getTeamMates(), teamMates);

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual Expression has no connectors.
     * 
     * @return this assertion object.
     * @throws AssertionError if the actual Expression's connectors is not empty.
     */
    public ExpressionAssert hasNoConnectors() {
        // check that actual Expression we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("\nExpected :\n  <%s>\nnot to have connectors but had :\n  <%s>", actual,
                actual.getConnectors());

        // check
        if (!actual.getConnectors().isEmpty()) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual Expression's content is equal to the given one.
     * 
     * @param content the given content to compare the actual Expression's content to.
     * @return this assertion object.
     * @throws AssertionError - if the actual Expression's content is not equal to the given one.
     */
    public ExpressionAssert hasContent(final String content) {
        // check that actual Expression we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("\nExpected <%s> content to be:\n  <%s>\n but was:\n  <%s>", actual, content,
                actual.getContent());

        // check
        if (!actual.getContent().equals(content)) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual Expression is htmlAllowed.
     * 
     * @return this assertion object.
     * @throws AssertionError - if the actual Expression is not htmlAllowed.
     */
    public ExpressionAssert isHtmlAllowed() {
        // check that actual Expression we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("Expected actual Expression to be htmlAllowed but was not.", actual);

        // check
        if (!actual.isHtmlAllowed()) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual Expression is not htmlAllowed.
     * 
     * @return this assertion object.
     * @throws AssertionError - if the actual Expression is htmlAllowed.
     */
    public ExpressionAssert isNotHtmlAllowed() {
        // check that actual Expression we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("Expected actual Expression not to be htmlAllowed but was.", actual);

        // check
        if (actual.isHtmlAllowed()) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual Expression's interpreter is equal to the given one.
     * 
     * @param interpreter the given interpreter to compare the actual Expression's interpreter to.
     * @return this assertion object.
     * @throws AssertionError - if the actual Expression's interpreter is not equal to the given one.
     */
    public ExpressionAssert hasInterpreter(final String interpreter) {
        // check that actual Expression we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("\nExpected <%s> interpreter to be:\n  <%s>\n but was:\n  <%s>", actual,
                interpreter, actual.getInterpreter());

        // check
        if (!actual.getInterpreter().equals(interpreter)) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual Expression's name is equal to the given one.
     * 
     * @param name the given name to compare the actual Expression's name to.
     * @return this assertion object.
     * @throws AssertionError - if the actual Expression's name is not equal to the given one.
     */
    public ExpressionAssert hasName(final String name) {
        // check that actual Expression we want to make assertions on is not null.
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
     * Verifies that the actual Expression's propagateVariableChange is equal to the given one.
     * 
     * @param propagateVariableChange the given propagateVariableChange to compare the actual Expression's propagateVariableChange to.
     * @return this assertion object.
     * @throws AssertionError - if the actual Expression's propagateVariableChange is not equal to the given one.
     */
    public ExpressionAssert hasPropagateVariableChange(final Boolean propagateVariableChange) {
        // check that actual Expression we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("\nExpected <%s> propagateVariableChange to be:\n  <%s>\n but was:\n  <%s>",
                actual, propagateVariableChange,
                actual.getPropagateVariableChange());

        // check
        if (!actual.getPropagateVariableChange().equals(propagateVariableChange)) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual Expression's referencedElements contains the given EObject elements.
     * 
     * @param referencedElements the given elements that should be contained in actual Expression's referencedElements.
     * @return this assertion object.
     * @throws AssertionError if the actual Expression's referencedElements does not contain all given EObject elements.
     */
    public ExpressionAssert hasReferencedElements(final EObject... referencedElements) {
        // check that actual Expression we want to make assertions on is not null.
        isNotNull();

        // check that given EObject varargs is not null.
        if (referencedElements == null) {
            throw new AssertionError("Expecting referencedElements parameter not to be null.");
        }

        // check with standard error message (see commented below to set your own message).
        Assertions.assertThat(actual.getReferencedElements()).contains(referencedElements);

        // uncomment the 4 lines below if you want to build your own error message :
        // WritableAssertionInfo assertionInfo = new WritableAssertionInfo();
        // String errorMessage = "my error message";
        // assertionInfo.overridingErrorMessage(errorMessage);
        // Iterables.instance().assertContains(assertionInfo, actual.getTeamMates(), teamMates);

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual Expression has no referencedElements.
     * 
     * @return this assertion object.
     * @throws AssertionError if the actual Expression's referencedElements is not empty.
     */
    public ExpressionAssert hasNoReferencedElements() {
        // check that actual Expression we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("\nExpected :\n  <%s>\nnot to have referencedElements but had :\n  <%s>",
                actual, actual.getReferencedElements());

        // check
        if (!actual.getReferencedElements().isEmpty()) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual Expression's returnType is equal to the given one.
     * 
     * @param returnType the given returnType to compare the actual Expression's returnType to.
     * @return this assertion object.
     * @throws AssertionError - if the actual Expression's returnType is not equal to the given one.
     */
    public ExpressionAssert hasReturnType(final String returnType) {
        // check that actual Expression we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("\nExpected <%s> returnType to be:\n  <%s>\n but was:\n  <%s>", actual,
                returnType, actual.getReturnType());

        // check
        if (!actual.getReturnType().equals(returnType)) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual Expression is returnTypeFixed.
     * 
     * @return this assertion object.
     * @throws AssertionError - if the actual Expression is not returnTypeFixed.
     */
    public ExpressionAssert isReturnTypeFixed() {
        // check that actual Expression we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("Expected actual Expression to be returnTypeFixed but was not.", actual);

        // check
        if (!actual.isReturnTypeFixed()) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual Expression is not returnTypeFixed.
     * 
     * @return this assertion object.
     * @throws AssertionError - if the actual Expression is returnTypeFixed.
     */
    public ExpressionAssert isNotReturnTypeFixed() {
        // check that actual Expression we want to make assertions on is not null.
        isNotNull();

        // we overrides the default error message with a more explicit one
        final String errorMessage = format("Expected actual Expression not to be returnTypeFixed but was.", actual);

        // check
        if (actual.isReturnTypeFixed()) {
            throw new AssertionError(errorMessage);
        }

        // return the current assertion for method chaining
        return this;
    }

    /**
     * Verifies that the actual Expression's type is equal to the given one.
     * 
     * @param type the given type to compare the actual Expression's type to.
     * @return this assertion object.
     * @throws AssertionError - if the actual Expression's type is not equal to the given one.
     */
    public ExpressionAssert hasType(final String type) {
        // check that actual Expression we want to make assertions on is not null.
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
