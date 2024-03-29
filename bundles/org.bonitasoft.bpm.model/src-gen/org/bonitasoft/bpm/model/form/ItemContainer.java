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
 * A representation of the model object '<em><b>Item Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.model.form.ItemContainer#getItemClass <em>Item Class</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.bpm.model.form.FormPackage#getItemContainer()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface ItemContainer extends EObject {
    /**
     * Returns the value of the '<em><b>Item Class</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Item Class</em>' attribute.
     * @see #setItemClass(String)
     * @see org.bonitasoft.bpm.model.form.FormPackage#getItemContainer_ItemClass()
     * @model
     * @generated
     */
    String getItemClass();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.model.form.ItemContainer#getItemClass <em>Item Class</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Item Class</em>' attribute.
     * @see #getItemClass()
     * @generated
     */
    void setItemClass(String value);

} // ItemContainer
