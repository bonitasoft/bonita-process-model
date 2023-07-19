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
package org.bonitasoft.bpm.migration.custom.migration;

import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * Loads the notation package and its classes from EMF registry
 * 
 * @author Vincent Hemery
 */
public class NotationPackageLoader {

    /** GMF Notation namespace URI */
    private static final String NOTATION_NS = "http://www.eclipse.org/gmf/runtime/1.0.3/notation";//$NON-NLS-1$
    /** Node EClass name */
    private static final String NODE_EC = "Node";//$NON-NLS-1$
    /** View EClass name */
    private static final String VIEW_EC = "View";//$NON-NLS-1$

    private static final class SingletonHolder {

        private static final NotationPackageLoader INSTANCE = new NotationPackageLoader();
    }

    /** GMF Notation package (if registered) */
    private Optional<EPackage> notation;

    /**
     * Private singleton constructor.
     */
    private NotationPackageLoader() {
        this.notation = Optional.ofNullable(EPackage.Registry.INSTANCE.getEPackage(NOTATION_NS));
    }

    /**
     * Get the singleton instance
     * 
     * @return the only instance
     */
    public static NotationPackageLoader getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Get notation's Node EClass
     * 
     * @return the EClass or empty
     */
    public Optional<EClass> getNode() {
        return notation.map(p -> p.getEClassifier(NODE_EC)).map(EClass.class::cast);
    }

    /**
     * Get notation's View EClass
     * 
     * @return the EClass or empty
     */
    public Optional<EClass> getView() {
        return notation.map(p -> p.getEClassifier(VIEW_EC)).map(EClass.class::cast);
    }
}
