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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.bpm.model.util.EnvironmentUtil;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.emf.edapt.internal.common.ResourceUtils;
import org.eclipse.emf.edapt.internal.migration.execution.IClassLoader;
import org.eclipse.emf.edapt.internal.migration.execution.internal.BundleClassLoader;
import org.eclipse.emf.edapt.internal.migration.execution.internal.ClassLoaderFacade;
import org.eclipse.emf.edapt.internal.migration.impl.UpdatingList;
import org.eclipse.emf.edapt.internal.migration.internal.MaterializingBackwardConverter;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edapt.spi.history.Release;
import org.eclipse.emf.edapt.spi.migration.AttributeSlot;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.eclipse.emf.edapt.spi.migration.ModelResource;
import org.eclipse.emf.edapt.spi.migration.Slot;
import org.osgi.framework.Bundle;

/**
 * Migrator for a single process resource
 */
public class SingleResourceMigrator extends Migrator {

    /** The migrate super method */
    private static Method migrateMethod;

    static {
        // give access to private migrate method
        try {
            migrateMethod = Migrator.class.getDeclaredMethod("migrate", List.class, Release.class, Release.class,
                    IProgressMonitor.class);
            migrateMethod.trySetAccessible();
        } catch (NoSuchMethodException | SecurityException e) {
            EcorePlugin.INSTANCE.log(e);
        }
    }

    public SingleResourceMigrator() throws MigrationException {
        super(HistoryUtils.getMigrationHistoryURI(),
                getMigrationClassLoader());
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
        if (classLoader.getDefinedPackage("org.bonitasoft.bpm.migration.custom.migration") == null) {
            // migration lib not found, restrict and just use this plugin
            EcorePlugin.INSTANCE.log("Library org.bonitasoft.bpm.migration not found for migration");
        }
        return new ClassLoaderFacade(classLoader);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.edapt.migration.execution.Migrator#migrateAndSave(java.util.List, org.eclipse.emf.edapt.spi.history.Release,
     * org.eclipse.emf.edapt.spi.history.Release, org.eclipse.core.runtime.IProgressMonitor, java.util.Map)
     */
    @Override
    public void migrateAndSave(List<URI> modelURIs, Release sourceRelease, Release targetRelease,
            IProgressMonitor monitor, Map<String, Object> options) throws MigrationException {
        ResourceSet resourceSet = migrateAndLoad(modelURIs, sourceRelease, targetRelease, monitor);
        try {
            if (resourceSet.getResources().size() > 1) {
                resourceSet.getResources().removeIf(r -> !modelURIs.contains(r.getURI()));
            }
            ResourceUtils.saveResourceSet(resourceSet, options);
        } catch (IOException e) {
            throw new MigrationException(e);
        }
    }

    /**
     * Migrate a model based on a set of {@link URI}s and load it afterwards.
     *
     * @param modelURIs
     *        The set of {@link URI}
     * @param sourceRelease
     *        Release to which the model conforms
     * @param targetRelease
     *        Release to which the model should be migrated (use null for
     *        the newest release)
     * @param monitor
     *        Progress monitor
     * @return The model in a {@link ResourceSet}
     */
    public ResourceSet migrateAndLoad(List<URI> modelURIs,
            Release sourceRelease, Release targetRelease,
            IProgressMonitor monitor) throws MigrationException {
        final Model model = doMigrate(modelURIs, sourceRelease, targetRelease, monitor);
        if (model == null) {
            return null;
        }
        final MaterializingBackwardConverter converter = new MaterializingBackwardConverter() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.emf.edapt.internal.migration.internal.BackwardConverter#initResources(org.eclipse.emf.edapt.spi.migration.Model)
             */
            @Override
            protected ResourceSet initResources(Model model) {
                // like super, but also use the content type recognition to use ProcessResource and its options.
                final ResourceSet resourceSet = new ResourceSetImpl() {

                    /*
                     * (non-Javadoc)
                     * @see org.eclipse.emf.ecore.resource.impl.ResourceSetImpl#createResource(org.eclipse.emf.common.util.URI)
                     */
                    @Override
                    public Resource createResource(URI uri) {
                        return createResource(uri, ContentHandler.UNSPECIFIED_CONTENT_TYPE);
                    }
                };
                ResourceUtils.register(model.getMetamodel().getEPackages(),
                        resourceSet.getPackageRegistry());
                for (final ModelResource modelResource : model.getResources()) {
                    final Resource resource = resourceSet.createResource(modelResource
                            .getUri());
                    if (resource instanceof XMLResource) {
                        final XMLResource xmlResource = (XMLResource) resource;
                        if (modelResource.getEncoding() != null) {
                            xmlResource.setEncoding(modelResource.getEncoding());
                        }
                    }
                    for (final Instance element : modelResource.getRootInstances()) {
                        resource.getContents().add(resolve(element));
                    }
                }
                return resourceSet;
            }

            /*
             * (non-Javadoc)
             * @see org.eclipse.emf.edapt.internal.migration.internal.MaterializingBackwardConverter#resolveEClass(org.eclipse.emf.ecore.EClass)
             */
            @Override
            protected EClass resolveEClass(EClass eClass) {
                // support unknown EClasses without registered package
                if (!eClass.getESuperTypes().contains(XMLTypePackage.Literals.ANY_TYPE)) {
                    return super.resolveEClass(eClass);
                } else {
                    // this is a generated unknown type, skip resolution
                    return eClass;
                }
            }

            /*
             * (non-Javadoc)
             * @see org.eclipse.emf.edapt.internal.migration.internal.BackwardConverter#initProperties(org.eclipse.emf.edapt.spi.migration.Instance)
             */
            @Override
            protected void initProperties(Instance element) {
                Optional<EClass> instanceEClass = Optional.of(element).map(Instance::getEClass)
                        .filter(Objects::nonNull);
                if (instanceEClass.filter(ec -> !ec.getESuperTypes().contains(XMLTypePackage.Literals.ANY_TYPE))
                        .isPresent()) {
                    super.initProperties(element);
                } else {
                    // this is a generated unknown type, copy properties as FeatureMap
                    final AnyType eObject = (AnyType) resolve(element);
                    for (final Slot slot : element.getSlots()) {
                        final EStructuralFeature sourceFeature = slot.getEFeature();
                        final EStructuralFeature targetFeature = resolveFeature(sourceFeature);
                        if (ignore(sourceFeature)) {
                            continue;
                        }
                        if (slot instanceof AttributeSlot) {
                            // either "mixed" or "any" or "anyAttribute"
                            if (Entry.class.isAssignableFrom(sourceFeature.getEType().getInstanceClass())) {
                                FeatureMap objectFeatureMap = (FeatureMap) eObject.eGet(targetFeature);
                                UpdatingList valuesMap = element.get(sourceFeature);
                                for (Object v : valuesMap) {
                                    Entry e = (Entry) v;
                                    objectFeatureMap.add(e.getEStructuralFeature(), e.getValue());
                                }
                            }
                        }
                    }
                }
            }
        };
        return converter.convert(model);
    }

    /**
     * Migrate a model based on a set of {@link URI}s.
     *
     * @param modelURIs
     *        The set of {@link URI}
     * @param sourceRelease
     *        Release to which the model conforms
     * @param targetRelease
     *        Release to which the model should be migrated (use null for
     *        the newest release)
     * @param monitor
     *        Progress monitor
     * @return The model in the generic structure
     */
    private Model doMigrate(List<URI> modelURIs, Release sourceRelease, Release targetRelease,
            IProgressMonitor monitor) {
        try {
            return (Model) migrateMethod.invoke(this, modelURIs, sourceRelease, targetRelease, monitor);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            EcorePlugin.INSTANCE.log(e);
            return null;
        }
    }
}
