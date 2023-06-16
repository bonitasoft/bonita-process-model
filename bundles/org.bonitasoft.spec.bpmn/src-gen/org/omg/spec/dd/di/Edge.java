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
package org.omg.spec.dd.di;

import org.eclipse.emf.common.util.EList;

import org.omg.spec.dd.dc.Point;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.dd.di.Edge#getWaypoint <em>Waypoint</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.dd.di.DiPackage#getEdge()
 * @model abstract="true"
 *        extendedMetaData="name='Edge' kind='elementOnly'"
 * @generated
 */
public interface Edge extends DiagramElement {

    /**
     * Returns the value of the '<em><b>Waypoint</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.dd.dc.Point}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Waypoint</em>' containment reference list.
     * @see org.omg.spec.dd.di.DiPackage#getEdge_Waypoint()
     * @model containment="true" lower="2"
     *        extendedMetaData="kind='element' name='waypoint' namespace='##targetNamespace'"
     * @generated
     */
    EList<Point> getWaypoint();

} // Edge
