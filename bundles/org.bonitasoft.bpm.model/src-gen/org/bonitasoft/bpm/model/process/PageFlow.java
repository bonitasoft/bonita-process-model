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
 * A representation of the model object '<em><b>Page Flow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.model.process.PageFlow#getFormMapping <em>Form Mapping</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.bpm.model.process.ProcessPackage#getPageFlow()
 * @model
 * @generated
 */
public interface PageFlow extends ConnectableElement, AbstractPageFlow {
    /**
     * Returns the value of the '<em><b>Form Mapping</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Form Mapping</em>' containment reference.
     * @see #setFormMapping(FormMapping)
     * @see org.bonitasoft.bpm.model.process.ProcessPackage#getPageFlow_FormMapping()
     * @model containment="true"
     * @generated
     */
    FormMapping getFormMapping();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.model.process.PageFlow#getFormMapping <em>Form Mapping</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Form Mapping</em>' containment reference.
     * @see #getFormMapping()
     * @generated
     */
    void setFormMapping(FormMapping value);

} // PageFlow
