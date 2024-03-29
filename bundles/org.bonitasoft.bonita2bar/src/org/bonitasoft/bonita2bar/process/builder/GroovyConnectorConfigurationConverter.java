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
package org.bonitasoft.bonita2bar.process.builder;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.bonitasoft.bonita2bar.process.expression.ExpressionHelper;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.bpm.model.expression.AbstractExpression;
import org.bonitasoft.bpm.model.expression.ExpressionFactory;
import org.bonitasoft.bpm.model.expression.ListExpression;
import org.bonitasoft.bpm.model.expression.TableExpression;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * @author Romain Bioteau
 */
public class GroovyConnectorConfigurationConverter {

    private static final String SCRIPTING_GROOVY_SCRIPT_DEF_ID = "scripting-groovy-script";
    private static final String VARIABLES_INPUT = "variables";
    private static final String SCRIPT_INPUT = "script";
    private static final String FAKE_SCRIPT_EXPRESSION = "fakeScriptExpression";

    public ConnectorConfiguration convert(final ConnectorConfiguration connectorConfiguration) {
        checkConnectorConfiguration(connectorConfiguration);
        final ConnectorConfiguration configuration = EcoreUtil.copy(connectorConfiguration);
        final org.bonitasoft.bpm.model.expression.Expression fakeExpression = getFakeExpression(configuration);
        configuration.getParameters().stream()
                .filter(withInputName(SCRIPT_INPUT))
                .findFirst()
                .ifPresent(param -> param.setExpression(createConstantScriptExpression(fakeExpression)));
        configuration.getParameters().stream()
                .filter(withInputName(VARIABLES_INPUT))
                .findFirst()
                .ifPresent(param -> param.setExpression(createVariableExpression(fakeExpression)));
        configuration.getParameters()
                .removeIf(withInputName(FAKE_SCRIPT_EXPRESSION));
        return configuration;
    }

    static Predicate<ConnectorParameter> withInputName(final String name) {
        return input -> Objects.equals(name, input.getKey());
    }

    private org.bonitasoft.bpm.model.expression.Expression createConstantScriptExpression(
            final org.bonitasoft.bpm.model.expression.Expression fakeExpression) {
        final org.bonitasoft.bpm.model.expression.Expression scriptExpression = ExpressionHelper
                .createConstantExpression(
                        fakeExpression.getContent(), String.class.getName());
        scriptExpression.setName(fakeExpression.getName());
        return scriptExpression;
    }

    private void checkConnectorConfiguration(final ConnectorConfiguration connectorConfig) {
        Objects.requireNonNull(connectorConfig);
        if (!connectorConfig.getDefinitionId().equals(SCRIPTING_GROOVY_SCRIPT_DEF_ID)) {
            throw new IllegalArgumentException();
        }
        final Set<String> keys = connectorConfig.getParameters().stream().map(ConnectorParameter::getKey)
                .collect(Collectors.toSet());
        if (!keys.containsAll(Arrays.asList(SCRIPT_INPUT, FAKE_SCRIPT_EXPRESSION, VARIABLES_INPUT))) {
            throw new IllegalStateException();
        }
    }

    private org.bonitasoft.bpm.model.expression.TableExpression createVariableExpression(
            final org.bonitasoft.bpm.model.expression.Expression fakeExpression) {
        final TableExpression variableExpression = ExpressionFactory.eINSTANCE.createTableExpression();
        for (final EObject dep : fakeExpression.getReferencedElements()) {
            final org.bonitasoft.bpm.model.expression.Expression depValueExpression = ExpressionHelper
                    .createExpressionFromEObject(dep);
            if (depValueExpression != null) {
                final ListExpression depLine = ExpressionFactory.eINSTANCE.createListExpression();
                final org.bonitasoft.bpm.model.expression.Expression depNameExpression = ExpressionHelper
                        .createConstantExpression(
                                depValueExpression.getName(), String.class.getName());
                final EList<org.bonitasoft.bpm.model.expression.Expression> expressions = depLine.getExpressions();
                expressions.addAll(Arrays.asList(depNameExpression, depValueExpression));
                variableExpression.getExpressions().add(depLine);
            }
        }
        return variableExpression;
    }

    private org.bonitasoft.bpm.model.expression.Expression getFakeExpression(
            final ConnectorConfiguration configuration) {
        for (final ConnectorParameter param : configuration.getParameters()) {
            if (FAKE_SCRIPT_EXPRESSION.equals(param.getKey())) {
                final AbstractExpression expression = param.getExpression();
                if (expression instanceof org.bonitasoft.bpm.model.expression.Expression) {
                    return (org.bonitasoft.bpm.model.expression.Expression) expression;
                }
            }
        }
        return null;
    }

    public boolean appliesTo(final ConnectorConfiguration configuration) {
        return configuration != null && SCRIPTING_GROOVY_SCRIPT_DEF_ID.equals(configuration.getDefinitionId());
    }

}
