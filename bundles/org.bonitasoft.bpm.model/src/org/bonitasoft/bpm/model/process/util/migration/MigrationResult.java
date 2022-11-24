/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.bpm.model.process.util.migration;

/** Describes how the model has been or should be migrated */
public enum MigrationResult {

    /** No migration performed */
    NO_MIGRATION,
    /** The physical resource has been migrated and erased */
    HARD_MIGRATION,
    /** The model has been loaded and migrated, but the physical resource is still untouched */
    SOFT_MIGRATION;

    /**
     * Test whether model should be migrated
     * 
     * @return true when model should be migrated
     */
    public boolean doMigrate() {
        return !NO_MIGRATION.equals(this);
    }

    /**
     * Test whether physical resource should be modified and erased.
     * 
     * @return true when resource should be erased
     */
    public boolean doEraseResource() {
        return HARD_MIGRATION.equals(this);
    }
}
