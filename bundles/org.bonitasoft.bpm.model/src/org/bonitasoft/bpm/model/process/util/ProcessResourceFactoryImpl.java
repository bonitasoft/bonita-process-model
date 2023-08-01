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
package org.bonitasoft.bpm.model.process.util;

import org.bonitasoft.bpm.model.util.ModelLoader;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource Factory</b> associated with the package.
 * <!-- end-user-doc -->
 * 
 * @see org.bonitasoft.bpm.model.process.util.ProcessResourceImpl
 */
public class ProcessResourceFactoryImpl extends ResourceFactoryImpl {

    /** The content type for files containing a bonita process model */
    public static final String CONTENT_TYPE = "bonita-process-model";

    /** The default encoding to use */
    private static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * Creates an instance of the resource factory.
     */
    public ProcessResourceFactoryImpl() {
        super();
    }

    /**
     * Creates an instance of the resource.
     */
    @Override
    public Resource createResource(URI uri) {
        XMIResource result = new ProcessResourceImpl(uri);
        result.getDefaultLoadOptions().putAll(ModelLoader.getInstance().getDefaultLoadOptions());
        result.getDefaultSaveOptions().putAll(ModelLoader.getInstance().getDefaultSaveOptions());
        result.setEncoding(DEFAULT_ENCODING);
        return result;
    }

} //ProcessResourceFactoryImpl
