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
package org.bonitasoft.bpm.migration.custom.migration.connector;

import java.util.Set;

/**
 * @author Romain Bioteau
 */
public class UpdateRestConnectorVersionTo100 extends AbstractUpdateConnectorDefinitionMigration {

    private static final Set<String> REST_CONNECTOR_DEFINITIONS = Set.of("rest-get", "rest-delete", "rest-post",
            "rest-put");

    @Override
    public String getNewDefinitionVersion() {
        return "1.0.0";
    }

    @Override
    public String getOldDefinitionVersion() {
        return "alpha5";
    }

    @Override
    public boolean shouldUpdateVersion(final String defId) {
        return REST_CONNECTOR_DEFINITIONS.contains(defId);
    }
}
