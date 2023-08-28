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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

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
import org.bonitasoft.bpm.model.process.util.migration.HistoryUtils;
import org.bonitasoft.bpm.model.process.util.migration.MigrationPolicy;
import org.bonitasoft.bpm.model.simulation.SimulationPackage;
import org.bonitasoft.bpm.model.util.internal.ConfigurationContentHandler;
import org.bonitasoft.bpm.model.util.internal.ProcContentHandler;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

/**
 * This is usefull for loading a model in a non-OSGi environment. It ensures everything is correctly configured, without the need of extension points.
 * 
 * @author Vincent Hemery
 */
public final class ModelLoader {

    public abstract static class Prerequisite implements Runnable {

        private static Map<Runnable, Prerequisite> fromRunnables = new HashMap<>();

        /** whether this has already run */
        private AtomicBoolean hasRun = new AtomicBoolean(false);

        /*
         * (non-Javadoc)
         * @see org.bonitasoft.bpm.model.util.ModelLoader.Prerequisite#run()
         */
        @Override
        public final void run() {
            // ensure it is run only once.
            if (!hasRun.getAndSet(true)) {
                doRun();
            }
        }

        /**
         * Perform the required registrations as prerequisite.
         */
        public abstract void doRun();

        /**
         * Make a prerequisite to execute the runnable object when not in OSGi. Useful to register some EPackages.<br/>
         * In case a prerequisite was already made from this runnable, the same prerequisite is returned so that we execute it only once.
         * But you have to make sure you use the same runnable instance, and not a lambda or function pointer.
         * 
         * @param runnable runnable object
         * @return prerequisite
         */
        public static Prerequisite fromRunnableWhenNotInOSGi(Runnable runnable) {
            return fromRunnables.computeIfAbsent(runnable, r -> new Prerequisite() {

                @Override
                public void doRun() {
                    if (!EnvironmentUtil.isOSGi()) {
                        r.run();
                    }
                }
            });
        }

    }

    /**
     * Creates model loaders, usefull for loading a model in a non-OSGi environment.
     * 
     * @author Vincent Hemery
     */
    public static class Factory {

        /** Lazy singleton holder */
        private static class LazyHolder {

            /** singleton instance */
            static final Factory INSTANCE = new Factory();
        }

        /**
         * Default Constructor. Initializes everything to work without extension points.
         */
        private Factory() {
        }

        /**
         * Get singleton instance
         * 
         * @return model loader factory
         */
        public static Factory getInstance() {
            return LazyHolder.INSTANCE;
        }

        /**
         * Create a new model loader with default configuration
         * 
         * @return model loader
         */
        public ModelLoader create() {
            return new ModelLoader();
        }
    }

    private static final Prerequisite[] DEFAULT_PREREQUISITE = new Prerequisite[] {
            // registration of history is necessary when we migrate a model not using HistoryUtils
            HistoryUtils.REGISTRATION,
            Prerequisite.fromRunnableWhenNotInOSGi(() -> {
                // Factories are registered with plugin extensions, but not without OSGi
                // register EPackages: registration is done during init, so consulting eINSTANCE is enough
                ProcessPackage.eINSTANCE.getNsURI();
                DecisionPackage.eINSTANCE.getNsURI();
                TransitionsPackage.eINSTANCE.getNsURI();
                ActorMappingPackage.eINSTANCE.getNsURI();
                ConfigurationPackage.eINSTANCE.getNsURI();
                ConnectorConfigurationPackage.eINSTANCE.getNsURI();
                ExpressionPackage.eINSTANCE.getNsURI();
                FormPackage.eINSTANCE.getNsURI();
                KpiPackage.eINSTANCE.getNsURI();
                ParameterPackage.eINSTANCE.getNsURI();
                SimulationPackage.eINSTANCE.getNsURI();
                // register the content handler (which may be overridden e.g. by extension mapping)
                ContentHandler.Registry.INSTANCE.put(ContentHandler.Registry.VERY_LOW_PRIORITY,
                        new ProcContentHandler());
                ContentHandler.Registry.INSTANCE.put(ContentHandler.Registry.VERY_LOW_PRIORITY,
                        new ConfigurationContentHandler());
                // register the resource factory
                Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap().put(
                        ProcessResourceFactoryImpl.CONTENT_TYPE,
                        new ProcessResourceFactoryImpl());
                Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap()
                        .put(ConfigurationResourceFactoryImpl.CONTENT_TYPE, new ConfigurationResourceFactoryImpl());
            })
    };

    /**
     * Create a new model loader with default configuration
     * 
     * @return model loader
     */
    public static ModelLoader create() {
        return Factory.getInstance().create();
    }

    private Map<String, Object> loadOptions = new HashMap<>();

    private List<Prerequisite> prerequisites = new ArrayList<>();

    /**
     * Default Constructor.
     */
    private ModelLoader() {
        // add default prerequisite
        withPrerequisite(DEFAULT_PREREQUISITE);
        // initiate migration policy with default
        withPolicy(MigrationPolicy.ALWAYS_MIGRATE_POLICY);
    }

    /**
     * Load a model from a file URL (always migrating)
     * 
     * @param fileUrl the URL pointing to a file
     * @return resource with loaded model
     */
    public Resource loadModel(URL fileUrl) {
        return loadModel(URI.createURI(fileUrl.toExternalForm()));

    }

    /**
     * Load a model from an EMF URI
     * 
     * @param modelUri the EMF model URI
     * @return resource with loaded model
     */
    public Resource loadModel(URI modelUri) {
        // first, make sure prerequisites have been executed
        prerequisites.forEach(Runnable::run);
        ResourceSetImpl rset = new ResourceSetImpl();
        rset.getLoadOptions().putAll(loadOptions);
        Resource resource = rset.getResource(modelUri, true);
        if (resource instanceof XMLResource) {
            // when loaded with extended metadata, use the same option value for saving... or failure of AnyType for notation will fail
            Object extendedMetadataOption = loadOptions.get(XMLResource.OPTION_EXTENDED_META_DATA);
            if (extendedMetadataOption != null) {
                ((XMLResourceImpl) resource).getDefaultSaveOptions().putIfAbsent(XMLResource.OPTION_EXTENDED_META_DATA,
                        extendedMetadataOption);
            }
        }
        return resource;
    }

    /**
     * Use the given migration policy when loading the model.<br/>
     * When not called, default is always migrating.
     * 
     * @param migrationPolicy a policy for model file migration
     * @return this ModelLoader
     */
    public ModelLoader withPolicy(MigrationPolicy migrationPolicy) {
        // try and migrate file during loading
        loadOptions.put(ProcessResourceImpl.OPTION_MIGRATION_POLICY, migrationPolicy);
        return this;
    }

    /**
     * Ensure the given prerequisites are executed before loading the model.
     * 
     * @param prerequisite some prerequisites to execute
     * @return this ModelLoader
     */
    public ModelLoader withPrerequisite(Prerequisite... prerequisite) {
        prerequisites.addAll(Arrays.asList(prerequisite));
        return this;
    }

    /**
     * Adds required save/load options in order to be able to save/load a .proc resource
     * even if some packages are missing like the GMF Notation package.<br/>
     * When not called, disabled by default.
     * 
     * @return this ModelLoader
     */
    public ModelLoader enablePartial() {
        // record unknown features, especially for notation package which we may not know
        loadOptions.putAll(Map.of(
                XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE,
                XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE));
        return this;
    }

    /**
     * Remove specific save/load options handling missing packages.<br/>
     * This restores the default behavior where all packages must be resolved.
     * 
     * @return this ModelLoader
     */
    public ModelLoader disablePartial() {
        loadOptions.remove(XMLResource.OPTION_EXTENDED_META_DATA);
        loadOptions.remove(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE);
        return this;
    }

}
