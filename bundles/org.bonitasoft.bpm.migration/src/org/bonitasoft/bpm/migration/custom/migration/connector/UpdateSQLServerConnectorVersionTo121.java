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
public class UpdateSQLServerConnectorVersionTo121 extends AbstractUpdateConnectorDefinitionMigration {

    private static final String SQLSERVER_2008_DEFINITION_ID = "database-mssqlserver2008";
    private static final String SQLSERVER_2012_DEFINITION_ID = "database-mssqlserver2012";
    private static final String SQLSERVER_DEFINITION_ID = "database-mssqlserver";

    @Override
    public String getNewDefinitionVersion() {
        return "1.2.1";
    }

    @Override
    public String getOldDefinitionVersion() {
        return "1.0.0";
    }

    @Override
    public boolean shouldUpdateVersion(final String defId) {
        return defId.equals(SQLSERVER_2008_DEFINITION_ID) || defId.equals(SQLSERVER_2012_DEFINITION_ID);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.bpm.migration.custom.migration.connector.UpdateConnectorDefinitionMigration#shouldUpdateId(java.lang.String)
     */
    @Override
    public boolean shouldUpdateId(String defId) {
        return defId.equals(SQLSERVER_2008_DEFINITION_ID) || defId.equals(SQLSERVER_2012_DEFINITION_ID);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.bpm.migration.custom.migration.connector.UpdateConnectorDefinitionMigration#getNewDefinitionId()
     */
    @Override
    public String getNewDefinitionId() {
        return SQLSERVER_DEFINITION_ID;
    }
}
