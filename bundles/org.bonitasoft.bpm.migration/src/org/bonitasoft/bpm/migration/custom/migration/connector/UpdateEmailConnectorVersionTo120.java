/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.bpm.migration.custom.migration.connector;

import java.util.List;
import java.util.Objects;

import org.bonitasoft.bpm.migration.utils.StringToExpressionConverter;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Model;

public class UpdateEmailConnectorVersionTo120 extends AbstractUpdateConnectorDefinitionMigration {

    private static final String EMAIL_CONNECTOR_DEFINITION = "email";

    @Override
    public String getNewDefinitionVersion() {
        return "1.2.0";
    }

    @Override
    public String getOldDefinitionVersion() {
        return "1.1.0";
    }

    @Override
    public boolean shouldUpdateVersion(final String defId) {
        return Objects.equals(EMAIL_CONNECTOR_DEFINITION, defId);
    }

    @Override
    protected void updateConfiguration(Instance connectorConfigInstance, Model model) {
        super.updateConfiguration(connectorConfigInstance, model);

        List<Instance> parameters = connectorConfigInstance.get("parameters");
        Instance trustCertificate = model.newInstance("connectorconfiguration.ConnectorParameter");
        trustCertificate.set("key", "trustCertificate");
        trustCertificate.set("expression", StringToExpressionConverter.createExpressionInstance(model,
                "false", "false", Boolean.class.getName(), ExpressionConstants.CONSTANT_TYPE, true));
        parameters.add(trustCertificate);
    }

}
