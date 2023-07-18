/**
 * Copyright (C) 2022 Bonitasoft S.A.
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

import org.eclipse.core.runtime.IStatus;

/**
 * A policy to decide whether to migrate the file, based on the model information.
 */
@FunctionalInterface
public interface MigrationPolicy {

    /** A default migration policy which always migrates the model as much as possible. */
    public static MigrationPolicy ALWAYS_MIGRATE_POLICY = (versionComparisonStatus, fileName, isReadOnly) -> {
        if (versionComparisonStatus.getSeverity() == IStatus.WARNING) {
            if (isReadOnly) {
                return MigrationResult.SOFT_MIGRATION;
            } else {
                return MigrationResult.HARD_MIGRATION;
            }
        } else {
            return MigrationResult.NO_MIGRATION;
        }
    };

    /** A default migration policy which never migrates the model. */
    public static MigrationPolicy NEVER_MIGRATE_POLICY = (versionComparisonStatus, fileName,
            isReadOnly) -> MigrationResult.NO_MIGRATION;

    /**
     * Decide whether to migrate the file, based on the relevant information.
     * 
     * @param versionComparisonStatus the status resulting from the version comparison with the latest version. This status should be a {@link IStatus#WARNING}.
     * @param fileName the file name
     * @param isReadOnly whether opened model is read-only
     * @return how we wish to migrate the model
     */
    public abstract MigrationResult decideMigration(IStatus versionComparisonStatus, String fileName,
            boolean isReadOnly);

}
