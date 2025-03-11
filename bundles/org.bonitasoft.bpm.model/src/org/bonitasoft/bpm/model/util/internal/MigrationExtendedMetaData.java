/**
 * Copyright (C) 2025 Bonitasoft S.A.
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
package org.bonitasoft.bpm.model.util.internal;

import java.util.Optional;

import org.bonitasoft.bpm.model.actormapping.ActorMappingPackage;
import org.bonitasoft.bpm.model.process.util.migration.SingleResourceMigrator;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;

/**
 * An extended metadata with proc migration-specific aspects.
 */
public class MigrationExtendedMetaData extends BasicExtendedMetaData {

    private Optional<EPackageExtendedMetaData> actorMappingExt = Optional.empty();
    private boolean useOldPackagesRatherThanGlobalRegistry = false;

    /**
     * Default Constructor.
     * 
     * @param registry
     */
    public MigrationExtendedMetaData(Registry registry) {
        super(registry);
    }

    /**
     * Instruct whether we should use packages registered in registry delegate with latest version.
     * 
     * @param useOldPackages false to use latest versions in package registry, true to ignore them
     */
    public void setIsLoadingAnOldMetamodel(boolean useOldPackages) {
        this.useOldPackagesRatherThanGlobalRegistry = useOldPackages;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.ecore.util.BasicExtendedMetaData#getExtendedMetaData(org.eclipse.emf.ecore.EPackage)
     */
    @Override
    protected EPackageExtendedMetaData getExtendedMetaData(EPackage ePackage) {
        String nsURI = ePackage.getNsURI();
        if (nsURI != null && nsURI.contains(ActorMappingPackage.eNS_PREFIX)) {
            // do not take statically initialized one
            if (actorMappingExt.isEmpty()) {
                actorMappingExt = Optional.of(createEPackageExtendedMetaData(ePackage));
            }
            return actorMappingExt.get();
        }
        return super.getExtendedMetaData(ePackage);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.ecore.util.BasicExtendedMetaData#getPackage(java.lang.String)
     */
    @Override
    public EPackage getPackage(String namespace) {
        /*
         * The migrated packages have the same namespace as old ones.
         * Do not take them from global registry while migrating.
         */
        if (useOldPackagesRatherThanGlobalRegistry
                && SingleResourceMigrator.getInstance().getNsURIs().contains(namespace)
                && registry.get(namespace) == null) {
            // we should use old dynamic package, not latest one from delegated registry
            return null;
        }
        return super.getPackage(namespace);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.ecore.util.BasicExtendedMetaData#putPackage(java.lang.String, org.eclipse.emf.ecore.EPackage)
     */
    @Override
    public void putPackage(String namespace, EPackage ePackage) {
        // when loading a package from migration history, we may have others nearby
        EList<EObject> contents = ePackage.eResource().getContents();
        if (contents.size() > 1 && contents.contains(ePackage)) {
            contents.stream().filter(EPackage.class::isInstance).map(EPackage.class::cast).forEach(p -> {
                if (p != ePackage) {
                    super.putPackage(getNamespace(p), p);
                } else {
                    super.putPackage(namespace, p);
                }
            });
        } else {
            super.putPackage(namespace, ePackage);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.ecore.util.BasicExtendedMetaData#getName(org.eclipse.emf.ecore.EClassifier)
     */
    @Override
    public String getName(EClassifier eClassifier) {
        String nsURI = eClassifier.getEPackage().getNsURI();
        if (nsURI != null && nsURI.contains(ActorMappingPackage.eNS_PREFIX)
                && !"DocumentRoot".equals(eClassifier.getName())) {
            return eClassifier.getName();
        }
        return super.getName(eClassifier);
    }
}
