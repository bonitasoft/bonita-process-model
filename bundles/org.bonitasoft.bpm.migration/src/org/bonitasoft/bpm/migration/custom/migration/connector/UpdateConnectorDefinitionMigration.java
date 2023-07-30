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

public interface UpdateConnectorDefinitionMigration {

    default boolean shouldUpdateId(String defId) {
        return false;
    }

    default String getNewDefinitionId() {
        return null;
    }

    default boolean shouldUpdateVersion(final String defId) {
        return false;
    }

    default String getNewDefinitionVersion() {
        throw new UnsupportedOperationException("Need to be overriden in subclass");
    }

    default String getOldDefinitionVersion() {
        throw new UnsupportedOperationException("Need to be overriden in subclass");
    }
}
