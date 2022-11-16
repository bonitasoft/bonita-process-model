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
package org.bonitasoft.bpm.model.util;

import java.net.URL;

import org.bonitasoft.bpm.model.actormapping.ActorMappingPackage;
import org.bonitasoft.bpm.model.configuration.ConfigurationPackage;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorConfigurationPackage;
import org.bonitasoft.bpm.model.expression.ExpressionPackage;
import org.bonitasoft.bpm.model.form.FormPackage;
import org.bonitasoft.bpm.model.kpi.KpiPackage;
import org.bonitasoft.bpm.model.parameter.ParameterPackage;
import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.bpm.model.process.util.ProcessResourceFactoryImpl;
import org.bonitasoft.bpm.model.simulation.SimulationPackage;
import org.bonitasoft.bpm.model.util.internal.ProcContentHandler;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

/**
 * This singleton is usefull for loading a model in a non-OSGi environment.
 * It ensures everything is correctly configured, without the need of extension points.
 * 
 * @author Vincent Hemery
 */
public final class ModelLoader {

    /** Lazy singleton holder */
    private static class LazyHolder {

        /** singleton instance */
        static final ModelLoader INSTANCE = new ModelLoader();
    }

    /**
     * Get singleton instance
     * 
     * @return model loader
     */
    public static ModelLoader getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * Default Constructor.
     * Initializes everything to work without extension points.
     */
    private ModelLoader() {
        // register EPackages
        EPackage.Registry.INSTANCE.put(ProcessPackage.eNS_URI, ProcessPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(ActorMappingPackage.eNS_URI, ActorMappingPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(ConfigurationPackage.eNS_URI, ConfigurationPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(ConnectorConfigurationPackage.eNS_URI, ConnectorConfigurationPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(ExpressionPackage.eNS_URI, ExpressionPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(FormPackage.eNS_URI, FormPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(KpiPackage.eNS_URI, KpiPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(ParameterPackage.eNS_URI, ParameterPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(SimulationPackage.eNS_URI, SimulationPackage.eINSTANCE);
        // register the content handler (which may be overridden e.g. by extension mapping)
        ContentHandler.Registry.INSTANCE.put(ContentHandler.Registry.VERY_LOW_PRIORITY, new ProcContentHandler());
        // register the resource factory
        Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap().put(
                ProcessResourceFactoryImpl.CONTENT_TYPE, new ProcessResourceFactoryImpl());
    }

    /**
     * Load a model from a file URL
     * 
     * @param fileUrl the URL pointing to a file
     * @return resource with loaded model
     */
    public Resource loadModel(URL fileUrl) {
        return loadModel(URI.createFileURI(fileUrl.getPath()));

    }

    /**
     * Load a model from an EMF URI
     * 
     * @param modelUri the EMF model URI
     * @return resource with loaded model
     */
    public Resource loadModel(URI modelUri) {
        ResourceSetImpl rset = new ResourceSetImpl();
        Resource model = rset.getResource(modelUri, true);
        return model;
    }

}
