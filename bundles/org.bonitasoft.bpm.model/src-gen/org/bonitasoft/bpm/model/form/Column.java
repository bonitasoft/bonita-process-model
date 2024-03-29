/**
 * Copyright (C) 2009-2022 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.bpm.model.form;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Column</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.model.form.Column#getWidth <em>Width</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.model.form.Column#getNumber <em>Number</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.bpm.model.form.FormPackage#getColumn()
 * @model
 * @generated
 */
public interface Column extends EObject {
    /**
     * Returns the value of the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Width</em>' attribute.
     * @see #setWidth(String)
     * @see org.bonitasoft.bpm.model.form.FormPackage#getColumn_Width()
     * @model
     * @generated
     */
    String getWidth();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.model.form.Column#getWidth <em>Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Width</em>' attribute.
     * @see #getWidth()
     * @generated
     */
    void setWidth(String value);

    /**
     * Returns the value of the '<em><b>Number</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Number</em>' attribute.
     * @see #setNumber(int)
     * @see org.bonitasoft.bpm.model.form.FormPackage#getColumn_Number()
     * @model
     * @generated
     */
    int getNumber();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.model.form.Column#getNumber <em>Number</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Number</em>' attribute.
     * @see #getNumber()
     * @generated
     */
    void setNumber(int value);

} // Column
