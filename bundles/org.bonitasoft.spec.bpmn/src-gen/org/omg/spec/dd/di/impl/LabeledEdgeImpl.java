/**
 * Copyright (C) 2023 BonitaSoft S.A.
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
package org.omg.spec.dd.di.impl;

import org.eclipse.emf.ecore.EClass;

import org.omg.spec.dd.di.DiPackage;
import org.omg.spec.dd.di.LabeledEdge;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Labeled Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class LabeledEdgeImpl extends EdgeImpl implements LabeledEdge {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected LabeledEdgeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiPackage.Literals.LABELED_EDGE;
    }

} //LabeledEdgeImpl
