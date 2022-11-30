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
package org.bonitasoft.bpm.model.process.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

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

    /* default load options */
    private static final Map<Object, Object> loadOptions;
    /* default save options */
    private static final Map<Object, Object> saveOptions;
    static {
        // take default options from XMIResourceImpl
        XMIResource resource = new XMIResourceImpl();
        loadOptions = new HashMap<>(resource.getDefaultLoadOptions());
        // and complete with extra options
        loadOptions.putAll(Map.of(
                XMIResource.OPTION_LAX_FEATURE_PROCESSING, Boolean.TRUE,
                // record unknown features, especially for notation package which we may not know
                XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE,
                XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE));
        // take default options from XMIResourceImpl
        saveOptions = new HashMap<>(resource.getDefaultSaveOptions());
        // and complete with extra options
        saveOptions.putAll(Map.of(
                XMIResource.OPTION_DECLARE_XML, Boolean.TRUE,
                XMIResource.OPTION_PROCESS_DANGLING_HREF, XMIResource.OPTION_PROCESS_DANGLING_HREF_DISCARD,
                XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE,
                XMIResource.OPTION_USE_XMI_TYPE, Boolean.TRUE,
                XMIResource.OPTION_SAVE_TYPE_INFORMATION, Boolean.TRUE,
                XMIResource.OPTION_SKIP_ESCAPE_URI, Boolean.FALSE,
                XMIResource.OPTION_ENCODING, DEFAULT_ENCODING,
                // use extended metadata, or failure of AnyType for notation will fail
                XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE));
    }

    /**
     * Get default load options.
     */
    public static Map<Object, Object> getDefaultLoadOptions() {
        return loadOptions;
    }

    /**
     * Get default save options.
     */
    public static Map<Object, Object> getDefaultSaveOptions() {
        return saveOptions;
    }

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
