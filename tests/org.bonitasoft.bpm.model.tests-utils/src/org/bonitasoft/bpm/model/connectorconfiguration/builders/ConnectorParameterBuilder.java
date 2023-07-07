/** 
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.bpm.model.connectorconfiguration.builders;

import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.bpm.model.expression.AbstractExpression;
import org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder;

/**
 * @author Romain Bioteau
 */
public class ConnectorParameterBuilder {

    public static ConnectorParameterBuilder aConnectorParameter() {
        return new ConnectorParameterBuilder();
    }

    private final ConnectorParameter instance;

    public ConnectorParameterBuilder() {
        instance = ConnectorConfigurationFactory.eINSTANCE.createConnectorParameter();
    }

    public ConnectorParameterBuilder withKey(final String key) {
        instance.setKey(key);
        return this;
    }

    public ConnectorParameterBuilder havingExpression(final ExpressionBuilder expression) {
        instance.setExpression(expression.build());
        return this;
    }

    public ConnectorParameterBuilder havingExpression(final AbstractExpression expression) {
        instance.setExpression(expression);
        return this;
    }

    public ConnectorParameter build() {
        return instance;
    }
}
