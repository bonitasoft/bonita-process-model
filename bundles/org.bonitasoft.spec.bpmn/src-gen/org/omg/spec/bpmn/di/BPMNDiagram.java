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
package org.omg.spec.bpmn.di;

import org.eclipse.emf.common.util.EList;

import org.omg.spec.dd.di.Diagram;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BPMN Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.di.BPMNDiagram#getBPMNPlane <em>BPMN Plane</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.BPMNDiagram#getBPMNLabelStyle <em>BPMN Label Style</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.di.DiPackage#getBPMNDiagram()
 * @model extendedMetaData="name='BPMNDiagram' kind='elementOnly'"
 * @generated
 */
public interface BPMNDiagram extends Diagram {

    /**
     * Returns the value of the '<em><b>BPMN Plane</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>BPMN Plane</em>' containment reference.
     * @see #setBPMNPlane(BPMNPlane)
     * @see org.omg.spec.bpmn.di.DiPackage#getBPMNDiagram_BPMNPlane()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='BPMNPlane' namespace='##targetNamespace'"
     * @generated
     */
    BPMNPlane getBPMNPlane();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.di.BPMNDiagram#getBPMNPlane <em>BPMN Plane</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BPMN Plane</em>' containment reference.
     * @see #getBPMNPlane()
     * @generated
     */
    void setBPMNPlane(BPMNPlane value);

    /**
     * Returns the value of the '<em><b>BPMN Label Style</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.bpmn.di.BPMNLabelStyle}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>BPMN Label Style</em>' containment reference list.
     * @see org.omg.spec.bpmn.di.DiPackage#getBPMNDiagram_BPMNLabelStyle()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='BPMNLabelStyle' namespace='##targetNamespace'"
     * @generated
     */
    EList<BPMNLabelStyle> getBPMNLabelStyle();

} // BPMNDiagram
