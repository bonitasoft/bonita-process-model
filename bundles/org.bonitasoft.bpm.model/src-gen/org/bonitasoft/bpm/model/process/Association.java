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
package org.bonitasoft.bpm.model.process;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.model.process.Association#isIsDirected <em>Is Directed</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.bpm.model.process.ProcessPackage#getAssociation()
 * @model
 * @generated
 */
public interface Association extends Connection {
    /**
     * Returns the value of the '<em><b>Is Directed</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Directed</em>' attribute.
     * @see #setIsDirected(boolean)
     * @see org.bonitasoft.bpm.model.process.ProcessPackage#getAssociation_IsDirected()
     * @model default="false"
     * @generated
     */
    boolean isIsDirected();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.model.process.Association#isIsDirected <em>Is Directed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Directed</em>' attribute.
     * @see #isIsDirected()
     * @generated
     */
    void setIsDirected(boolean value);

} // Association
