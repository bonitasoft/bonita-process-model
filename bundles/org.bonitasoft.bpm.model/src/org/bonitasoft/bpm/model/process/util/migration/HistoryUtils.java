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
package org.bonitasoft.bpm.model.process.util.migration;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.emf.edapt.spi.history.History;
import org.eclipse.emf.edapt.spi.history.HistoryPackage;
import org.eclipse.emf.edapt.spi.history.Release;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.MigrationPackage;
import org.eclipse.emf.edapt.spi.migration.util.MigrationValidator;

/**
 * Access to constants and utility methods regarding the migration history
 */
public class HistoryUtils {

    /** path to Edapt history file */
    private static final String MIGRATION_HISTORY_PATH = "/org.bonitasoft.bpm.model/model/process.history";

    /**
     * Get path to migration history file
     * 
     * @return path to history file
     */
    static String getMigrationHistoryPath() {
        return MIGRATION_HISTORY_PATH;
    }

    /** The current model version (latest release) */
    public static final String CURRENT_MODEL_VERSION;
    /** A predicate to test whether a version exists in history */
    public static final Predicate<String> IS_KNOWN_VERSION;
    static {
        // make sure History metamodel is loaded first, even in maven env
        HistoryPackage.eINSTANCE.getHistory();
        // now get versions from history
        Resource historyResource = new ResourceSetImpl()
                .getResource(URI.createPlatformPluginURI(getMigrationHistoryPath(), false), true);
        History history = (History) historyResource.getContents().get(0);
        CURRENT_MODEL_VERSION = history.getLatestRelease().getLabel();
        List<String> knownVersions = history.getReleases().stream().map(Release::getLabel).collect(Collectors.toList());
        IS_KNOWN_VERSION = knownVersions::contains;
        historyResource.unload();
    }

    static {
        // patch the migration validator for packages which are not loaded... (like notation)
        EValidator.Registry.INSTANCE.put(MigrationPackage.eINSTANCE, new MigrationValidator() {

            /*
             * (non-Javadoc)
             * @see org.eclipse.emf.edapt.spi.migration.util.MigrationValidator#validateInstance_validContainment(org.eclipse.emf.edapt.spi.migration.Instance,
             * org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
             */
            @Override
            public boolean validateInstance_validContainment(Instance instance, DiagnosticChain diagnostics,
                    Map<Object, Object> context) {
                Optional<EClass> instanceEClass = Optional.of(instance).map(Instance::getEClass)
                        .filter(Objects::nonNull);
                if (instanceEClass.filter(ec -> !ec.getESuperTypes().contains(XMLTypePackage.Literals.ANY_TYPE))
                        .isPresent()) {
                    return super.validateInstance_validContainment(instance, diagnostics, context);
                } else {
                    // this is a generated unknown type, skip containment validation
                    return true;
                }
            }
        });
    }

}
