/**
 * Copyright (C) 2015 Bonitasoft S.A.
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

import java.util.Objects;

/**
 * @author Romain Bioteau
 */
public class UpdateDocumentConverterTo102 extends AbstractUpdateConnectorDefinitionMigration {

    private static final String DOCUEMENT_CONVERTER_DEF_ID = "document-converter";

    @Override
    public String getNewDefinitionVersion() {
        return "1.0.2";
    }

    @Override
    public String getOldDefinitionVersion() {
        return "1.0.1";
    }

    @Override
    public boolean shouldUpdateVersion(final String defId) {
        return Objects.equals(defId, DOCUEMENT_CONVERTER_DEF_ID);
    }
}
