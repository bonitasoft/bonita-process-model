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
package org.bonitasoft.bonita2bar;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.bonitasoft.bonita2bar.process.ProcessRegistryImpl;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.util.migration.MigrationPolicy;

public interface ProcessRegistry {

    List<Pool> getProcesses();

    Optional<Pool> getProcess(String name, String version);

    MigrationPolicy getMigrationPolicy();

    /**
     * {@link ProcessRegistry} factory method.
     * 
     * @param diagramsFolder The {@link Path} on the filesystem containing proc
     *        files
     * @param policy the migration policy to use when loading process
     *        resources
     * @return A default implementation of {@link ProcessRegistry} that loads all
     *         the processes from the proc files found in diagramsFolder. Once
     *         loaded, the processes are cached in memory.
     */
    static ProcessRegistry of(Path diagramsFolder, MigrationPolicy policy) {
        return new ProcessRegistryImpl(diagramsFolder, policy);
    }

    /**
     * {@link ProcessRegistry} factory method.
     * 
     * @param procFiles The list diagram file of this registry
     *        files
     * @param policy the migration policy to use when loading process
     *        resources
     * @return A default implementation of {@link ProcessRegistry} that loads all
     *         the processes from the proc files given in parameter. Once
     *         loaded, the processes are cached in memory.
     */
    static ProcessRegistry of(List<Path> procFiles, MigrationPolicy policy) {
        return new ProcessRegistryImpl(procFiles, policy);
    }

}
