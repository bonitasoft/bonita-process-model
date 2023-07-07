/** 
 * Copyright (C) 2022 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.bpm.model.process.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.function.BiPredicate;

import javax.xml.parsers.ParserConfigurationException;

import org.bonitasoft.bpm.model.process.util.migration.InputStreamSupplier;
import org.bonitasoft.bpm.model.process.util.migration.MigrationHelper;
import org.bonitasoft.bpm.model.process.util.migration.MigrationPolicy;
import org.bonitasoft.bpm.model.process.util.migration.MigrationResult;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
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
     * Corresponding value must be a <code>BiPredicate<IStatus, String></code>.
     * 
     * @see MigrationHelper#ALWAYS_MIGRATE_POLICY
     * @see MigrationHelper#NEVER_MIGRATE_POLICY
     * @see #setMigrationPolicy(BiPredicate)
     */
    public static String OPTION_MIGRATION_POLICY = "MIGRATION_POLICY";//$NON-NLS-1$

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
     * @see org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl#doLoad(java.io.InputStream, java.util.Map)
     */
    @Override
    public void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
        // look for migration option
        if (options.containsKey(OPTION_MIGRATION_POLICY)) {
            Object optionValue = options.get(OPTION_MIGRATION_POLICY);
            if (optionValue instanceof MigrationPolicy) {
                setMigrationPolicy((MigrationPolicy) optionValue);
            }
        }
        // migrate if needed
        MigrationResult result = checkMigration(options);
        if (!MigrationResult.SOFT_MIGRATION.equals(result)) {
            // then load (or reload) the model
            super.doLoad(inputStream, options);
        }
        // else, resource has already been updated
    }

    /**
     * Check whether a migration is needed and perform it when necessary.
     * 
     * @return how the model has actually been migrated
     * @throws IOException exception accessing the resource content
     */
    protected MigrationResult checkMigration(Map<?, ?> options) throws IOException {
        InputStreamSupplier streamSupplier = () -> getURIConverter().createInputStream(uri, options);
        try {
            MigrationHelper migration = MigrationHelper.getHelper(this, streamSupplier);
            if (migration.getModelVersionStatus().getSeverity() == IStatus.WARNING) {
                return migration.tryAndMigrate(migrationPolicy);
            }
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
        return MigrationResult.NO_MIGRATION;
    }

} //ProcessResourceImpl
