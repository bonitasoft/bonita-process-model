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

import java.io.IOException;
import java.io.InputStream;

/**
 * Supplies an {@link InputStream}, but with the possibility to fail with an {@link IOException}
 */
@FunctionalInterface
public interface InputStreamSupplier {

    /**
     * Gets an InputStream.
     *
     * @return an InputStream
     * @throws IOException the exception which occurred during access
     */
    InputStream get() throws IOException;
}
