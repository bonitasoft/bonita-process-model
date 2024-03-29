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

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;

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

    /** default load options */
    private static final Map<Object, Object> loadOptions = Map.of(
            XMLResource.OPTION_LAX_FEATURE_PROCESSING, Boolean.TRUE,
            XMLResource.OPTION_USE_PACKAGE_NS_URI_AS_LOCATION, Boolean.FALSE);

    /** default save options */
    private static final Map<Object, Object> saveOptions = Map.of(
            XMLResource.OPTION_DECLARE_XML, Boolean.TRUE,
            XMLResource.OPTION_PROCESS_DANGLING_HREF, XMLResource.OPTION_PROCESS_DANGLING_HREF_DISCARD,
            XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE,
            XMIResource.OPTION_USE_XMI_TYPE, Boolean.TRUE,
            XMLResource.OPTION_SAVE_TYPE_INFORMATION, Boolean.TRUE,
            XMLResource.OPTION_SKIP_ESCAPE_URI, Boolean.FALSE,
            XMLResource.OPTION_ENCODING, DEFAULT_ENCODING);

    /**
     * Creates an instance of the resource.
     */
    @Override
    public Resource createResource(URI uri) {
        XMIResource result = new ProcessResourceImpl(uri);
        result.getDefaultLoadOptions().putAll(loadOptions);
        result.getDefaultSaveOptions().putAll(saveOptions);
        result.setEncoding(DEFAULT_ENCODING);
        return result;
    }

} //ProcessResourceFactoryImpl
