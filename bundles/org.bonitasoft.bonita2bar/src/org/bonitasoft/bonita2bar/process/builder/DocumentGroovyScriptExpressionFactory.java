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
package org.bonitasoft.bonita2bar.process.builder;

import static java.util.function.Predicate.not;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.bonitasoft.bonita2bar.process.expression.ExpressionHelper;
import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.expression.ExpressionFactory;
import org.bonitasoft.bpm.model.process.ContractInput;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.bonitasoft.engine.bpm.contract.FileInputValue;
import org.eclipse.emf.ecore.EObject;

public class DocumentGroovyScriptExpressionFactory {

    public Expression createSingleDocumentInitialContentScriptExpression(final ContractInput input) {
        final Expression groovyScriptExpression = createGroovyScriptExpression(fileContractInputAccessorScript(input),
                FileInputValue.class.getName());
        groovyScriptExpression.setName("Single document initial value script");
        addInputDependency(input, groovyScriptExpression);
        return groovyScriptExpression;
    }

    private void addInputDependency(final ContractInput input, final Expression groovyScriptExpression) {
        groovyScriptExpression.getReferencedElements()
                .add(ExpressionHelper.createDependencyFromEObject(rootInput(input)));
    }

    public Expression createMultipleDocumentInitialContentScriptExpression(final ContractInput input) {
        final Expression groovyScriptExpression = createGroovyScriptExpression(fileContractInputAccessorScript(input),
                List.class.getName());
        groovyScriptExpression.setName("Multiple document initial value script");
        addInputDependency(input, groovyScriptExpression);
        return groovyScriptExpression;
    }

    private String fileContractInputAccessorScript(final ContractInput contractInput) {
        if (!(contractInput.eContainer() instanceof ContractInput)
                || not(withMultipleInHierarchy()).test((ContractInput) contractInput.eContainer())) {
            return toAncestorNameList().apply(contractInput).stream().collect(Collectors.joining("."));
        }
        final ContractInput parentInput = (ContractInput) contractInput.eContainer();
        final StringBuilder scriptBuilder = new StringBuilder(
                toAncestorNameList().apply(parentInput).stream().collect(Collectors.joining(".")));
        scriptBuilder.append(".collect{it.");
        scriptBuilder.append(contractInput.getName());
        scriptBuilder.append("}.flatten()");
        return scriptBuilder.toString();
    }

    private static Predicate<ContractInput> withMultipleInHierarchy() {
        return input -> {
            ContractInput current = input;
            while (current.eContainer() instanceof ContractInput) {
                if (current.isMultiple()) {
                    return true;
                }
                current = (ContractInput) current.eContainer();
            }
            return current.isMultiple();
        };
    }

    private ContractInput rootInput(final ContractInput contractInput) {
        ContractInput root = contractInput;
        while (root.eContainer() instanceof ContractInput) {
            root = (ContractInput) root.eContainer();
        }
        return root;
    }

    private static Function<ContractInput, List<String>> toAncestorNameList() {
        return input -> {
            final List<String> ancestors = new ArrayList<>();
            EObject current = input;
            while (current instanceof ContractInput) {
                ancestors.add(((ContractInput) current).getName());
                current = current.eContainer();
            }
            Collections.reverse(ancestors);
            return ancestors;
        };
    }

    private static Expression createGroovyScriptExpression(String expressionContent, String returnType) {
        Expression exp = ExpressionFactory.eINSTANCE.createExpression();
        exp.setName("ExpressionForEvaluation");
        exp.setInterpreter(ExpressionConstants.GROOVY);
        exp.setType(ExpressionConstants.SCRIPT_TYPE);
        exp.setContent(expressionContent);
        exp.setReturnType(returnType);
        return exp;
    }

}
