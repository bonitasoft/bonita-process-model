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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiPredicate;

import javax.xml.parsers.ParserConfigurationException;

import org.bonitasoft.bpm.model.process.util.migration.InputStreamSupplier;
import org.bonitasoft.bpm.model.process.util.migration.MigrationHelper;
import org.bonitasoft.bpm.model.process.util.migration.MigrationPolicy;
import org.bonitasoft.bpm.model.process.util.migration.MigrationResult;
import org.bonitasoft.bpm.model.process.util.migration.MigrationResultAndStatus;
import org.bonitasoft.bpm.model.util.internal.DuplicatingInputStream;
import org.bonitasoft.bpm.model.util.internal.MigrationExtendedMetaData;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.xml.sax.SAXException;

/**
 * The <b>Resource</b> associated with the package.
 * 
 * @see org.bonitasoft.bpm.model.process.util.ProcessResourceFactoryImpl
 */
public class ProcessResourceImpl extends XMIResourceImpl {

    /**
     * This option allows to specify a policy for model file migration.
     * Corresponding value must be a {@link MigrationPolicy}.
     * 
     * @see MigrationPolicy#ALWAYS_MIGRATE_POLICY
     * @see MigrationPolicy#NEVER_MIGRATE_POLICY
     * @see #setMigrationPolicy(BiPredicate)
     */
    public static final String OPTION_MIGRATION_POLICY = MigrationHelper.OPTION_MIGRATION_POLICY;

    /**
     * The policy for file migration.
     * By default, never migrate, or it will try to migrate the pre-migration file, causing an infinite loop.
     */
    private MigrationPolicy migrationPolicy = MigrationPolicy.NEVER_MIGRATE_POLICY;

    /**
     * Creates an instance of the resource.
     * 
     * @param uri the URI of the new resource.
     */
    public ProcessResourceImpl(URI uri) {
        super(uri);
    }

    /**
     * Change the policy for file migration.
     * 
     * @param migrationPolicy a policy to decides whether to migrate the file, based on the model information.
     * @see #OPTION_MIGRATION_POLICY
     */
    public void setMigrationPolicy(MigrationPolicy migrationPolicy) {
        this.migrationPolicy = migrationPolicy;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl#useUUIDs()
     */
    @Override
    protected boolean useUUIDs() {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl#doSave(java.io.OutputStream, java.util.Map)
     */
    @Override
    public void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
        Map<Object, Object> overriddenOptions = getOverriddenOptions(options);
        super.doSave(outputStream, overriddenOptions);
    }

    /**
     * Overrides the options to not rely on extended metadata names
     * 
     * @param options original options
     * @return overridden options
     */
    private Map<Object, Object> getOverriddenOptions(Map<?, ?> options) {
        Map<Object, Object> overriddenOptions = new HashMap<>(options);
        if (Boolean.TRUE.equals(options.get(XMLResource.OPTION_EXTENDED_META_DATA))) {
            // we are with XMI and do not want to use the XSD-fashioned name...
            Object extendedMetadataOption = new MigrationExtendedMetaData(getResourceSet().getPackageRegistry());
            overriddenOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetadataOption);
        }
        return overriddenOptions;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl#doLoad(java.io.InputStream, java.util.Map)
     */
    @Override
    public void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
        // look for migration option
        if (options.containsKey(OPTION_MIGRATION_POLICY)) {
            Object optionValue = options.get(OPTION_MIGRATION_POLICY);
            if (optionValue instanceof MigrationPolicy policy) {
                setMigrationPolicy(policy);
            }
        }
        // when loaded with extended metadata, use the same option value for saving... (save may occur during load migration)
        Map<Object, Object> overriddenOptions = getOverriddenOptions(options);
        Object extendedMetadataOption = overriddenOptions.get(XMLResource.OPTION_EXTENDED_META_DATA);
        if (extendedMetadataOption != null) {
            getDefaultSaveOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetadataOption);
        }
        Optional<MigrationExtendedMetaData> extended = Optional.ofNullable(extendedMetadataOption)
                .filter(MigrationExtendedMetaData.class::isInstance)
                .map(MigrationExtendedMetaData.class::cast);
        // migrate if needed
        if (getURIConverter().exists(uri, overriddenOptions)) {
            // easier to just reopen the resource when we need it
            InputStreamSupplier streamSupplier = () -> getURIConverter().createInputStream(uri, overriddenOptions);
            MigrationResultAndStatus result = checkMigration(streamSupplier, overriddenOptions);
            if (!MigrationResult.SOFT_MIGRATION.equals(result.migrationResult())) {
                // then load (or reload) the model
                if (!result.modelVersionStatus().isOK()) {
                    // loading an old model version
                    extended.ifPresent(e -> e.setIsLoadingAnOldMetamodel(true));
                }
                try {
                    super.doLoad(inputStream, overriddenOptions);
                } finally {
                    extended.ifPresent(e -> e.setIsLoadingAnOldMetamodel(false));
                }
            }
            // else, resource has already been updated
        } else {
            // probably a virtual resource, we must duplicate the stream...
            try (DuplicatingInputStream buffered = new DuplicatingInputStream(inputStream);) {
                InputStreamSupplier streamSupplier = buffered::getNonClosingStreamCopy;
                MigrationResultAndStatus result = checkMigration(streamSupplier, overriddenOptions);
                if (!MigrationResult.SOFT_MIGRATION.equals(result.migrationResult())) {
                    // then load (or reload) the model
                    if (!result.modelVersionStatus().isOK()) {
                        // loading an old model version
                        extended.ifPresent(e -> e.setIsLoadingAnOldMetamodel(true));
                    }
                    try (InputStream streamForSuper = buffered.getClosingMasterStreamCopy();) {
                        super.doLoad(streamForSuper, overriddenOptions);
                    } finally {
                        extended.ifPresent(e -> e.setIsLoadingAnOldMetamodel(false));
                    }
                }
                // else, resource has already been updated
            }
            /*
             * Closing early may close the pipe...
             * That's a risk we assume and that has a workaround in
             * ResourceSetLoadingFromTarget.demandLoad(Resource)
             */
        }
    }

    /**
     * Check whether a migration is needed and perform it when necessary.
     * 
     * @param streamSupplier supplies a stream with the resource content for eventual information parsing
     * @param options the loading options
     * @return how the model has actually been migrated
     * @throws IOException exception accessing the resource content
     */
    protected MigrationResultAndStatus checkMigration(InputStreamSupplier streamSupplier, Map<?, ?> options)
            throws IOException {
        try {
            MigrationHelper migration = MigrationHelper.getHelper(this, streamSupplier);
            if (migration.getModelVersionStatus().getSeverity() == IStatus.WARNING) {
                return migration.tryAndMigrate(migrationPolicy, options, getDefaultSaveOptions());
            }
            return new MigrationResultAndStatus(MigrationResult.NO_MIGRATION, migration.getModelVersionStatus());
        } catch (SAXException | ParserConfigurationException | MigrationException exception) {
            Notification notification = setLoaded(true);
            isLoading = true;
            if (errors != null) {
                errors.clear();
            }
            if (warnings != null) {
                warnings.clear();
            }
            isLoading = false;
            if (notification != null) {
                eNotify(notification);
            }
            setModified(false);
            throw new IOException(exception);
        } catch (IOException exception) {
            Notification notification = setLoaded(true);
            isLoading = true;
            if (errors != null) {
                errors.clear();
            }
            if (warnings != null) {
                warnings.clear();
            }
            isLoading = false;
            if (notification != null) {
                eNotify(notification);
            }
            setModified(false);

            throw exception;
        }
    }

} //ProcessResourceImpl
