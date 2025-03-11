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

import org.eclipse.core.runtime.IProgressMonitor;
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
import org.eclipse.emf.edapt.common.IResourceSetFactory;
import org.eclipse.emf.edapt.common.IResourceSetProcessor;
import org.eclipse.emf.edapt.internal.common.ResourceUtils;
import org.eclipse.emf.edapt.internal.migration.execution.IClassLoader;
import org.eclipse.emf.edapt.internal.migration.execution.ValidationLevel;
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

/**
 * Migrator for a single resource, which supports unresolved metamodels.
 */
public class LenientResourceMigrator extends Migrator {

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

    /** Another migrator with the correct class loader to delegate the migrate method */
    private Optional<Migrator> wrappedMigrator = Optional.empty();

    /**
     * Default Constructor.
     */
    public LenientResourceMigrator(URI historyURI, IClassLoader classLoader) throws MigrationException {
        super(historyURI, classLoader);
    }

    /**
     * Replace an already registered migrator to make it more robust.
     */
    public LenientResourceMigrator(Migrator original) {
        /*
         * We do not have easy access to migrator's class loader.
         * Use an incorrect class loader, we'll delegate the migrate method which is the only one using it.
         */
        super(original.getLatestRelease().getHistory(),
                new ClassLoaderFacade(Thread.currentThread().getContextClassLoader()));
        wrappedMigrator = Optional.of(original);
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
        if (resourceSet == null) {
            throw new MigrationException(modelURIs.get(0), "Migration failed. A prior error should be logged.",
                    new NullPointerException());
        }
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
    @Override
    public ResourceSet migrateAndLoad(List<URI> modelURIs,
            Release sourceRelease, Release targetRelease,
            IProgressMonitor monitor) throws MigrationException {
        final Model model = doMigrate(modelURIs, sourceRelease, targetRelease, monitor);
        if (model == null) {
            throw new MigrationException(modelURIs.get(0), "Migration failed. A prior error should be logged.",
                    new NullPointerException());
        }
        final MaterializingBackwardConverter converter = new CustomMaterializingBackwardConverter();
        return converter.convert(model);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.edapt.migration.execution.Migrator#getResourceSetFactory()
     */
    @Override
    public IResourceSetFactory getResourceSetFactory() {
        return wrappedMigrator.map(Migrator::getResourceSetFactory).orElseGet(super::getResourceSetFactory);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.edapt.migration.execution.Migrator#setResourceSetFactory(org.eclipse.emf.edapt.common.IResourceSetFactory)
     */
    @Override
    public void setResourceSetFactory(IResourceSetFactory resourceSetFactory) {
        wrappedMigrator.ifPresentOrElse(m -> m.setResourceSetFactory(resourceSetFactory),
                () -> super.setResourceSetFactory(resourceSetFactory));
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.edapt.migration.execution.Migrator#setLevel(org.eclipse.emf.edapt.internal.migration.execution.ValidationLevel)
     */
    @Override
    public void setLevel(ValidationLevel level) {
        wrappedMigrator.ifPresentOrElse(m -> m.setLevel(level),
                () -> super.setLevel(level));
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.edapt.migration.execution.Migrator#getPostLoadModelProcessor()
     */
    @Override
    public IResourceSetProcessor getPostLoadModelProcessor() {
        return wrappedMigrator.map(Migrator::getPostLoadModelProcessor).orElseGet(super::getPostLoadModelProcessor);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.edapt.migration.execution.Migrator#setPostLoadModelProcessor(org.eclipse.emf.edapt.common.IResourceSetProcessor)
     */
    @Override
    public void setPostLoadModelProcessor(IResourceSetProcessor postLoadProcessor) {
        wrappedMigrator.ifPresentOrElse(m -> m.setPostLoadModelProcessor(postLoadProcessor),
                () -> super.setPostLoadModelProcessor(postLoadProcessor));
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
     * @throws MigrationException
     */
    private Model doMigrate(List<URI> modelURIs, Release sourceRelease, Release targetRelease,
            IProgressMonitor monitor) throws MigrationException {
        try {
            Migrator migratorToInvokeOn = wrappedMigrator.orElse(this);
            return (Model) migrateMethod.invoke(migratorToInvokeOn, modelURIs, sourceRelease, targetRelease, monitor);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            EcorePlugin.INSTANCE.log(e);
            throw new MigrationException(e);
        }
    }

    static class CustomMaterializingBackwardConverter extends MaterializingBackwardConverter {

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
            if (!XMLTypePackage.Literals.ANY_TYPE.isSuperTypeOf(eClass)) {
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
            if (instanceEClass.filter(ec -> !XMLTypePackage.Literals.ANY_TYPE.isSuperTypeOf(ec))
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
                    if (slot instanceof AttributeSlot
                            && Entry.class.isAssignableFrom(sourceFeature.getEType().getInstanceClass())) {
                        FeatureMap objectFeatureMap = (FeatureMap) eObject.eGet(targetFeature);
                        UpdatingList<?> valuesMap = element.get(sourceFeature);
                        for (Object v : valuesMap) {
                            Entry e = (Entry) v;
                            objectFeatureMap.add(e.getEStructuralFeature(), e.getValue());
                        }
                    }
                }
            }
        }
    }
}
