/**
 * Copyright (C) 2026 Bonitasoft S.A.
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

/**
 * A message reported while building a BAR, meant to be surfaced to the user by the caller (Studio dialog,
 * Maven log, ...) instead of being buried in the logs.
 *
 * @param severity how much attention the message deserves
 * @param message the human readable message
 */
public record BuildDiagnostic(Severity severity, String message) {

    /**
     * Creates a warning diagnostic.
     *
     * @param format a {@link String#format(String, Object...)} format
     * @param args the format arguments
     * @return the diagnostic
     */
    public static BuildDiagnostic warning(String format, Object... args) {
        return new BuildDiagnostic(Severity.WARNING, String.format(format, args));
    }

    /** Severity of a {@link BuildDiagnostic}. */
    public enum Severity {

        /** Something the user should look at: the built BAR may not be what was expected. */
        WARNING
    }

}
