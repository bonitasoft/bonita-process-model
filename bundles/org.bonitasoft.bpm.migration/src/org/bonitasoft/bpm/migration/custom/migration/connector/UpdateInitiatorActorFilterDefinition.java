/**
 * Copyright (C) 2020 Bonitasoft S.A.
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

public class UpdateInitiatorActorFilterDefinition extends AbstractUpdateConnectorDefinitionMigration {

    private static final String OLD_DEFINITION_ID = "initiator";
    private static final String NEW_DEFINITION_ID = "bonita-actorfilter-initiator";
    private static final String OLD_VERSION = "1.0.0";
    private static final String NEW_VERSION = "1.0";

    @Override
    public String getNewDefinitionId() {
        return NEW_DEFINITION_ID;
    }

    @Override
    public boolean shouldUpdateId(String defId) {
        return OLD_DEFINITION_ID.equals(defId);
    }

    @Override
    public boolean shouldUpdateVersion(String defId) {
        return OLD_DEFINITION_ID.equals(defId);
    }

    @Override
    public String getOldDefinitionVersion() {
        return OLD_VERSION;
    }

    @Override
    public String getNewDefinitionVersion() {
        return NEW_VERSION;
    }

}
