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

/**
 * @author Romain Bioteau
 */
public class UpdateGroovyConnectorVersionTo101 extends AbstractUpdateConnectorDefinitionMigration {

    private static final String GROOVY_DEF_ID = "scripting-groovy";

    @Override
    public String getNewDefinitionVersion() {
        return "1.0.1";
    }

    @Override
    public String getOldDefinitionVersion() {
        return "1.0.0";
    }

    @Override
    public boolean shouldUpdateVersion(final String defId) {
        return defId.equals(GROOVY_DEF_ID);
    }
}
