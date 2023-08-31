/**
 * Copyright (C) 2023 Bonitasoft S.A.
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
package org.bonitasoft.bpm.connector.model;

import java.net.URL;

import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.bpm.connector.model.definition.util.ConnectorDefinitionResourceFactoryImpl;
import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementationPackage;
import org.bonitasoft.bpm.connector.model.implementation.util.ConnectorImplementationResourceFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edapt.internal.migration.execution.internal.ClassLoaderFacade;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.execution.MigratorRegistry;

/**
 * To register everything necessary in a non-OSGi context.
 * We need a searate class from ConnectorModelPlugin which has OSGi services dependencies.
 * 
 * @author Vincent Hemery
 */
public class ConnectorModelRegistration {

    /** path to Edapt history file within the plugin */
    private static final String MIGRATION_HISTORY_PATH = "model/connector.history";

    /** Run the required registrations for when used outside OSGi */
    public static final Runnable REGISTER = () -> {
        // registration is done during init, so consulting eINSTANCE is enough
        ConnectorDefinitionPackage.eINSTANCE.getNsURI();
        ConnectorImplementationPackage.eINSTANCE.getNsURI();
        // register the resource factory
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("def",
                new ConnectorDefinitionResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("impl",
                new ConnectorImplementationResourceFactoryImpl());
        // register history
        var classLoader = ConnectorDefinitionPackage.class.getClassLoader();
        URL url = classLoader.getResource(MIGRATION_HISTORY_PATH);
        try {
            MigratorRegistry.getInstance().registerMigrator(url, new ClassLoaderFacade(classLoader));
        } catch (MigrationException e) {
            EcorePlugin.INSTANCE.log(e);
        }
    };
}
