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

import org.omg.spec.dd.dc.Font;

import org.omg.spec.dd.di.Style;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BPMN Label Style</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.di.BPMNLabelStyle#getFont <em>Font</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.di.DiPackage#getBPMNLabelStyle()
 * @model extendedMetaData="name='BPMNLabelStyle' kind='elementOnly'"
 * @generated
 */
public interface BPMNLabelStyle extends Style {

    /**
     * Returns the value of the '<em><b>Font</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Font</em>' containment reference.
     * @see #setFont(Font)
     * @see org.omg.spec.bpmn.di.DiPackage#getBPMNLabelStyle_Font()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='Font' namespace='http://www.omg.org/spec/DD/20100524/DC'"
     * @generated
     */
    Font getFont();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.di.BPMNLabelStyle#getFont <em>Font</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Font</em>' containment reference.
     * @see #getFont()
     * @generated
     */
    void setFont(Font value);

} // BPMNLabelStyle
