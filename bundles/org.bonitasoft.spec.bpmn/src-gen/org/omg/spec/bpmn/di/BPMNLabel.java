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

import javax.xml.namespace.QName;

import org.omg.spec.dd.di.Label;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BPMN Label</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.di.BPMNLabel#getLabelStyle <em>Label Style</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.di.DiPackage#getBPMNLabel()
 * @model extendedMetaData="name='BPMNLabel' kind='elementOnly'"
 * @generated
 */
public interface BPMNLabel extends Label {

    /**
     * Returns the value of the '<em><b>Label Style</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label Style</em>' attribute.
     * @see #setLabelStyle(QName)
     * @see org.omg.spec.bpmn.di.DiPackage#getBPMNLabel_LabelStyle()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='labelStyle'"
     * @generated
     */
    QName getLabelStyle();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.di.BPMNLabel#getLabelStyle <em>Label Style</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label Style</em>' attribute.
     * @see #getLabelStyle()
     * @generated
     */
    void setLabelStyle(QName value);

} // BPMNLabel
