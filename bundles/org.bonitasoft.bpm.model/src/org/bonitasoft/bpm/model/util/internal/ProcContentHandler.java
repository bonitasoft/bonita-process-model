/** 
 * Copyright (C) 2022 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.bpm.model.util.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.bonitasoft.bpm.model.process.util.ProcessResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.impl.ContentHandlerImpl;

/**
 * A content handler implementation for .proc files.
 * 
 * @author Vincent Hemery
 */
public class ProcContentHandler extends ContentHandlerImpl {

    /** The .proc file extension */
    public static final String PROC_EXT = "proc";

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.ecore.resource.impl.ContentHandlerImpl#canHandle(org.eclipse.emf.common.util.URI)
     */
    @Override
    public boolean canHandle(URI uri) {
        return PROC_EXT.equals(uri.fileExtension());
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.ecore.resource.impl.ContentHandlerImpl#contentDescription(org.eclipse.emf.common.util.URI, java.io.InputStream,
     * java.util.Map, java.util.Map)
     */
    @Override
    public Map<String, Object> contentDescription(URI uri, InputStream inputStream, Map<?, ?> options,
            Map<Object, Object> context) throws IOException {
        Map<String, Object> res = super.contentDescription(uri, inputStream, options, context);
        res.put(ContentHandler.CONTENT_TYPE_PROPERTY, ProcessResourceFactoryImpl.CONTENT_TYPE);
        return res;
    }
}
