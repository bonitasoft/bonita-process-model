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
package org.bonitasoft.bpm.migration.custom.migration.connector;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.bpm.migration.utils.StringToExpressionConverter;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Model;

public class UpdateGoogleCalendarDefinitionTo110 extends AbstractUpdateConnectorDefinitionMigration {

    private static final Set<String> GOOGLE_CALENDAR_DEFINITIONS = new HashSet<>();
    static {
        GOOGLE_CALENDAR_DEFINITIONS.add("google-calendar-v3-create-event");
        GOOGLE_CALENDAR_DEFINITIONS.add("google-calendar-v3-get-event");
        GOOGLE_CALENDAR_DEFINITIONS.add("google-calendar-v3-delete-event");
        GOOGLE_CALENDAR_DEFINITIONS.add("google-calendar-v3-move-event");
        GOOGLE_CALENDAR_DEFINITIONS.add("google-calendar-v3-update-event");
    }

    @Override
    public String getNewDefinitionVersion() {
        return "1.1.0";
    }

    @Override
    public String getOldDefinitionVersion() {
        return "1.0.0";
    }

    @Override
    public boolean shouldUpdateVersion(final String defId) {
        return GOOGLE_CALENDAR_DEFINITIONS.contains(defId);
    }

    @Override
    protected void updateConfiguration(Instance connectorConfigInstance, Model model) {
        super.updateConfiguration(connectorConfigInstance, model);

        List<Instance> parameters = connectorConfigInstance.get("parameters");
        Instance authModeParameter = model.newInstance("connectorconfiguration.ConnectorParameter");
        authModeParameter.set("key", "authMode");
        authModeParameter.set("expression", StringToExpressionConverter.createExpressionInstance(model,
                "P12", "P12", String.class.getName(), ExpressionConstants.CONSTANT_TYPE, true));
        parameters.add(authModeParameter);
    }

}
