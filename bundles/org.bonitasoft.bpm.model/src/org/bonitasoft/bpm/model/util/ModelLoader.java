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
package org.bonitasoft.bpm.model.util;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.bpm.model.actormapping.ActorMappingPackage;
import org.bonitasoft.bpm.model.configuration.ConfigurationPackage;
import org.bonitasoft.bpm.model.configuration.util.ConfigurationResourceFactoryImpl;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorConfigurationPackage;
import org.bonitasoft.bpm.model.expression.ExpressionPackage;
import org.bonitasoft.bpm.model.form.FormPackage;
import org.bonitasoft.bpm.model.kpi.KpiPackage;
import org.bonitasoft.bpm.model.parameter.ParameterPackage;
import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.bpm.model.process.decision.DecisionPackage;
import org.bonitasoft.bpm.model.process.decision.transitions.TransitionsPackage;
import org.bonitasoft.bpm.model.process.util.ProcessResourceFactoryImpl;
import org.bonitasoft.bpm.model.process.util.ProcessResourceImpl;
import org.bonitasoft.bpm.model.process.util.migration.MigrationPolicy;
import org.bonitasoft.bpm.model.simulation.SimulationPackage;
import org.bonitasoft.bpm.model.util.internal.ConfigurationContentHandler;
import org.bonitasoft.bpm.model.util.internal.ProcContentHandler;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 * This singleton is usefull for loading a model in a non-OSGi environment. It
 * ensures everything is correctly configured, without the need of extension
 * points.
 * 
 * @author Vincent Hemery
 */
public final class ModelLoader {

    /** Lazy singleton holder */
    private static class LazyHolder {

        /** singleton instance */
        static final ModelLoader INSTANCE = new ModelLoader();
    }

    private static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * Get singleton instance
     * 
     * @return model loader
     */
    public static ModelLoader getInstance() {
        return LazyHolder.INSTANCE;
    }

    private Map<String, Object> defaultLoadOptions = new HashMap<String, Object>();

    private Map<String, Object> defaultSaveOptions = new HashMap<String, Object>();

    /**
     * Default Constructor. Initializes everything to work without extension points.
     */
    private ModelLoader() {
        defaultLoadOptions.put(XMLResource.OPTION_LAX_FEATURE_PROCESSING, Boolean.TRUE);
        defaultSaveOptions.putAll(Map.of(XMLResource.OPTION_DECLARE_XML, Boolean.TRUE,
                XMLResource.OPTION_PROCESS_DANGLING_HREF, XMLResource.OPTION_PROCESS_DANGLING_HREF_DISCARD,
                XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE, XMIResource.OPTION_USE_XMI_TYPE, Boolean.TRUE,
                XMLResource.OPTION_SAVE_TYPE_INFORMATION, Boolean.TRUE, XMLResource.OPTION_SKIP_ESCAPE_URI,
                Boolean.FALSE, XMLResource.OPTION_ENCODING, DEFAULT_ENCODING));

        if (EnvironmentUtil.isOSGi()) {
            // Factories are registered with plugin extensions
            return;
        }
        // register EPackages
        EPackage.Registry.INSTANCE.put(ProcessPackage.eNS_URI, ProcessPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(DecisionPackage.eNS_URI, DecisionPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(TransitionsPackage.eNS_URI, TransitionsPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(ActorMappingPackage.eNS_URI, ActorMappingPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(ConfigurationPackage.eNS_URI, ConfigurationPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(ConnectorConfigurationPackage.eNS_URI, ConnectorConfigurationPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(ExpressionPackage.eNS_URI, ExpressionPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(FormPackage.eNS_URI, FormPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(KpiPackage.eNS_URI, KpiPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(ParameterPackage.eNS_URI, ParameterPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(SimulationPackage.eNS_URI, SimulationPackage.eINSTANCE);
        // register the content handler (which may be overridden e.g. by extension
        // mapping)
        ContentHandler.Registry.INSTANCE.put(ContentHandler.Registry.VERY_LOW_PRIORITY, new ProcContentHandler());
        ContentHandler.Registry.INSTANCE.put(ContentHandler.Registry.VERY_LOW_PRIORITY,
                new ConfigurationContentHandler());
        // register the resource factory
        Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap().put(ProcessResourceFactoryImpl.CONTENT_TYPE,
                new ProcessResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap()
                .put(ConfigurationResourceFactoryImpl.CONTENT_TYPE, new ConfigurationResourceFactoryImpl());
    }

    /**
     * Load a model from a file URL (always migrating)
     * 
     * @param fileUrl the URL pointing to a file
     * @return resource with loaded model
     */
    public Resource loadModel(URL fileUrl) {
        return loadModel(URI.createURI(fileUrl.toExternalForm()), MigrationPolicy.ALWAYS_MIGRATE_POLICY);

    }

    /**
     * Load a model from a file URL
     * 
     * @param fileUrl the URL pointing to a file
     * @param migrationPolicy a policy for model file migration
     * @return resource with loaded model
     */
    public Resource loadModel(URL fileUrl, MigrationPolicy migrationPolicy) {
        return loadModel(URI.createURI(fileUrl.toExternalForm()), migrationPolicy);

    }

    /**
     * Load a model from an EMF URI (always migrating)
     * 
     * @param modelUri the EMF model URI
     * @return resource with loaded model
     */
    public Resource loadModel(URI modelUri) {
        return loadModel(modelUri, MigrationPolicy.ALWAYS_MIGRATE_POLICY);
    }

    /**
     * Load a model from an EMF URI
     * 
     * @param modelUri the EMF model URI
     * @param migrationPolicy a policy for model file migration
     * @return resource with loaded model
     */
    public Resource loadModel(URI modelUri, MigrationPolicy migrationPolicy) {
        ResourceSetImpl rset = new ResourceSetImpl();
        // try and migrate file during loading
        rset.getLoadOptions().put(ProcessResourceImpl.OPTION_MIGRATION_POLICY, migrationPolicy);
        return rset.getResource(modelUri, true);
    }

    /**
     * Adds required load options in order to be able to load a .proc resource
     * even if some packages are missing like the GMF Notation package.
     * 
     * @return this ModelLoader
     */
    public ModelLoader enablePartialLoad() {
        defaultLoadOptions.putAll(Map.of(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE,
                XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE));
        return this;
    }

    /**
     * Adds required save options in order to be able to save a .proc resource
     * even if some packages are missing like the GMF Notation package.
     * 
     * @return this ModelLoader
     */
    public ModelLoader enablePartialSave() {
        defaultSaveOptions.putAll(Map.of(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE));
        return this;
    }

    public Map<String, Object> getDefaultLoadOptions() {
        return defaultLoadOptions;
    }

    public Map<String, Object> getDefaultSaveOptions() {
        return defaultSaveOptions;
    }

}
