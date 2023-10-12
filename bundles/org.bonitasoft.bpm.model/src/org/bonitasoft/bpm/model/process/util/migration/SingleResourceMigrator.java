/**
 * Copyright (C) 2018 Bonitasoft S.A.
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

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.bpm.model.util.EnvironmentUtil;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edapt.common.IResourceSetProcessor;
import org.eclipse.emf.edapt.internal.migration.execution.IClassLoader;
import org.eclipse.emf.edapt.internal.migration.execution.internal.BundleClassLoader;
import org.eclipse.emf.edapt.internal.migration.execution.internal.ClassLoaderFacade;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.osgi.framework.Bundle;

/**
 * Migrator for a single process resource.
 */
public class SingleResourceMigrator extends LenientResourceMigrator {

    /** Reinject extensions with unknown metamodel into the model */
    @SuppressWarnings("unchecked")
    private static final IResourceSetProcessor POST_LOAD_PROCESSOR = rset -> {
        var r = rset.getResources().get(0);
        if (r instanceof XMLResource) {
            var extMap = ((XMLResource) r).getEObjectToExtensionMap();
            // collect operations to ConcurrentModificationException
            List<Runnable> operations = extMap.entrySet().stream().flatMap(entry -> {
                var obj = entry.getKey();
                var featMap = entry.getValue().getMixed();
                return featMap.stream().map(featEntry -> {
                    // get original feature, not the one created for unkown type
                    var feat = obj.eClass().getEStructuralFeature(featEntry.getEStructuralFeature().getName());
                    var val = featEntry.getValue();
                    // extensions are usually EObject typed references
                    if (feat.getEType().isInstance(val)) {
                        final Runnable run;
                        if (feat instanceof EReference && ((EReference) feat).isContainer()) {
                            // skip
                            run = null;
                        } else if (feat.isMany()) {
                            run = () -> ((List) obj.eGet(feat)).add(val);
                        } else {
                            run = () -> obj.eSet(feat, val);
                        }
                        return run;
                    } else {
                        String msg = MessageFormat.format(
                                "Model was built with unknown elements in a specific feature. Value {0} can not be inserted in feature {1} of {2}",
                                val, feat.getName(), obj);
                        throw new IllegalStateException(msg);
                    }
                }).filter(Objects::nonNull);
            }).collect(Collectors.toList());
            operations.forEach(Runnable::run);
        }
    };

    private static SingleResourceMigrator instance;

    public static SingleResourceMigrator getInstance() {
        if (instance == null) {
            try {
                instance = new SingleResourceMigrator();
            } catch (MigrationException e) {
                throw new IllegalArgumentException();
            }
        }
        return instance;
    }

    private SingleResourceMigrator() throws MigrationException {
        super(HistoryUtils.getMigrationHistoryURI(),
                getMigrationClassLoader());
        this.setPostLoadModelProcessor(POST_LOAD_PROCESSOR);
    }

    /**
     * Get the migration plugin class loader
     * 
     * @return class loader with access to migration classes
     */
    private static IClassLoader getMigrationClassLoader() {
        if (EnvironmentUtil.isOSGi()) {
            // load from the migration OSGi bundle
            Bundle bundle = Platform.getBundle("org.bonitasoft.bpm.migration");//$NON-NLS-1$
            if (bundle != null) {
                return new BundleClassLoader(bundle);
            }
        }
        // else, class loader is the same for this jar and the migration jar
        ClassLoader classLoader = SingleResourceMigrator.class.getClassLoader();
        try {
            classLoader.loadClass(
                    "org.bonitasoft.bpm.migration.custom.migration.BusinessObjectTypeCustomMigration");
        } catch (ClassNotFoundException e) {
            // migration lib not found, restrict and just use this plugin
            EcorePlugin.INSTANCE.log("Library org.bonitasoft.bpm.migration not found for migration");
        }
        return new ClassLoaderFacade(classLoader);
    }

}
