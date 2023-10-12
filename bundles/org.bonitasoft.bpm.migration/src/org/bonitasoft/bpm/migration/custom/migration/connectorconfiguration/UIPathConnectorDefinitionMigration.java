/**
 * Copyright (C) 2023 Bonitasoft S.A.
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
package org.bonitasoft.bpm.migration.custom.migration.connectorconfiguration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

public class UIPathConnectorDefinitionMigration extends CustomMigration {

    private static final String UIPATH_STARTJOB_ID = "uipath-startjob";
    private static final Set<String> UI_PATH_DEFINITIONS_IDS = new HashSet<>();
    static {
        UI_PATH_DEFINITIONS_IDS.add("uipath-getjob");
        UI_PATH_DEFINITIONS_IDS.add(UIPATH_STARTJOB_ID);
        UI_PATH_DEFINITIONS_IDS.add("uipath-add-queueItem");
    }
    private static final String SOURCE_DEFINITION_VERSION = "2.0.0";
    private static final String TARGET_DEFINITION_VERSION = "2.1.0";
    private static final String ORGANIZATION_UNIT_ID_INPUT = "organizationUnitId";
    private static final String RUNTIME_TYPE_INPUT = "runtimeType";
    private static final String SOURCE_INPUT = "source";

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.edapt.migration.CustomMigration#migrateAfter(org.eclipse.emf.edapt.spi.migration.Model,
     * org.eclipse.emf.edapt.spi.migration.Metamodel)
     */
    @Override
    public void migrateAfter(Model model, Metamodel metamodel) throws MigrationException {
        for (final Instance configuration : model.getAllInstances("connectorconfiguration.ConnectorConfiguration")) {
            if (UI_PATH_DEFINITIONS_IDS.contains(configuration.get("definitionId"))
                    && SOURCE_DEFINITION_VERSION.equals(configuration.get("version"))) {
                List<Instance> parameters = configuration.getLinks("parameters");
                parameters.add(createParamenter(model, ORGANIZATION_UNIT_ID_INPUT, "", String.class.getName()));
                if (UIPATH_STARTJOB_ID.equals(configuration.get("definitionId"))) {
                    parameters.add(createParamenter(model, RUNTIME_TYPE_INPUT, "Development", String.class.getName()));
                    parameters.add(createParamenter(model, SOURCE_INPUT, "Manual", String.class.getName()));
                }
                Instance container = configuration.getContainer();
                if (container.instanceOf("process.Connector")) {
                    container.set("definitionVersion", TARGET_DEFINITION_VERSION);
                }
                configuration.set("version", TARGET_DEFINITION_VERSION);
            }
        }
    }

    private Instance createParamenter(Model model, String key, String value, String returnType) {
        Instance connectorParameter = model.newInstance("connectorconfiguration.ConnectorParameter");
        connectorParameter.set("key", key);
        connectorParameter.set("expression", createConstantExpression(model, value, returnType));
        return connectorParameter;
    }

    private Instance createConstantExpression(Model model, String content, String returnClassName) {
        Instance exp = model.newInstance("expression.Expression");
        exp.set("type", ExpressionConstants.CONSTANT_TYPE);
        exp.set("name", content);
        exp.set("content", content);
        exp.set("returnType", returnClassName);
        return exp;
    }
}
