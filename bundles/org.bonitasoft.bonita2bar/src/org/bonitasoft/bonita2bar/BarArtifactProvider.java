/**
 * Copyright (C) 2018 Bonitasoft S.A.
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

import org.bonitasoft.bonita2bar.configuration.EnvironmentConfigurationBuilder;
import org.bonitasoft.bonita2bar.process.pomgen.ProcessPom;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;

public interface BarArtifactProvider {

    /**
     * @deprecated use {@link #build(BusinessArchiveBuilder, Pool, ProcessPom, Configuration)} instead
     */
    @Deprecated
    default void build(BusinessArchiveBuilder builder, Pool process, Configuration configuration)
            throws BuildBarException {
        // do nothing, we'll use the other build method
    }

    default void build(BusinessArchiveBuilder builder, Pool process, ProcessPom pomAccess, Configuration configuration)
            throws BuildBarException {
        // call the deprecated method for old implementations compatibility
        build(builder, process, configuration);
    }

    default void configure(EnvironmentConfigurationBuilder builder, Configuration configuration, Pool process) {
        //No configuration
    }

}
