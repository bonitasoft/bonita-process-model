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

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.bonita2bar.process.builder.GroovyConnectorConfigurationConverter.withInputName;
import static org.bonitasoft.bpm.model.connectorconfiguration.builders.ConnectorConfigurationBuilder.aConnectorConfiguration;
import static org.bonitasoft.bpm.model.connectorconfiguration.builders.ConnectorParameterBuilder.aConnectorParameter;
import static org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder.aConstantExpression;
import static org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder.aGroovyScriptExpression;
import static org.bonitasoft.bpm.model.process.builders.DataBuilder.aData;
import static org.bonitasoft.bpm.model.process.builders.StringDataTypeBuilder.aStringDataType;

import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.bpm.model.expression.AbstractExpression;
import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.expression.TableExpression;
import org.bonitasoft.bpm.model.expression.assertions.ExpressionAssert;
import org.junit.jupiter.api.Test;

class GroovyConnectorConfigurationConverterTest {

    @Test
    void should_remove_fake_parameter_from_configuration() throws Exception {
        final ConnectorConfiguration convertedConfig = new GroovyConnectorConfigurationConverter()
                .convert(aGroovyConnectorConfiguration());

        assertThat(convertedConfig.getParameters()).extracting("key").containsExactly("script", "variables");
    }

    @Test
    void should_use_script_expression_name_as_constant_expression_name() throws Exception {
        final ConnectorConfiguration convertedConfig = new GroovyConnectorConfigurationConverter()
                .convert(aGroovyConnectorConfiguration());

        ExpressionAssert
                .assertThat((Expression) convertedConfig.getParameters().stream().filter(withInputName("script"))
                        .findFirst().orElseThrow().getExpression())
                .hasName("scriptName");
    }

    @Test
    void should_use_script_expression_content_as_constant_expression_content() throws Exception {
        final ConnectorConfiguration convertedConfig = new GroovyConnectorConfigurationConverter()
                .convert(aGroovyConnectorConfiguration());

        ExpressionAssert
                .assertThat((Expression) convertedConfig.getParameters().stream().filter(withInputName("script"))
                        .findFirst().orElseThrow().getExpression())
                .hasContent("return true;");
    }

    @Test
    void should_use_script_expression_referencedElement_in_variables_input() throws Exception {
        final ConnectorConfiguration convertedConfig = new GroovyConnectorConfigurationConverter()
                .convert(aGroovyConnectorConfiguration());

        final AbstractExpression expression = convertedConfig.getParameters().stream()
                .filter(withInputName("variables"))
                .findFirst()
                .orElseThrow()
                .getExpression();
        assertThat(expression).isInstanceOf(TableExpression.class);
        assertThat(((TableExpression) expression).getExpressions().get(0).getExpressions()).extracting("name")
                .contains("myData");
    }

    private ConnectorConfiguration aGroovyConnectorConfiguration() {
        return aConnectorConfiguration()
                .withDefinitionId("scripting-groovy-script")
                .havingParameters(
                        aConnectorParameter().withKey("fakeScriptExpression").havingExpression(
                                aGroovyScriptExpression().withName("scriptName").withContent("return true;")
                                        .havingReferencedElements(
                                                aData().withName("myData").havingDataType(aStringDataType()))),
                        aConnectorParameter().withKey("script").havingExpression(
                                aConstantExpression().withName("").withContent("")),
                        aConnectorParameter().withKey("variables").havingExpression(
                                aConstantExpression().withName("").withContent("")))
                .build();
    }
}
