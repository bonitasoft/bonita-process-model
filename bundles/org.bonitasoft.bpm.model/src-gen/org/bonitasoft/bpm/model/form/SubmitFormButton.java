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

import org.bonitasoft.bpm.model.expression.Operation;

import org.bonitasoft.bpm.model.process.ConnectableElement;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Submit Form Button</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.model.form.SubmitFormButton#getActions <em>Actions</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.bpm.model.form.FormPackage#getSubmitFormButton()
 * @model
 * @generated
 */
public interface SubmitFormButton extends FormButton, ConnectableElement {
    /**
     * Returns the value of the '<em><b>Actions</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.bpm.model.expression.Operation}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Actions</em>' containment reference list.
     * @see org.bonitasoft.bpm.model.form.FormPackage#getSubmitFormButton_Actions()
     * @model containment="true"
     * @generated
     */
    EList<Operation> getActions();

} // SubmitFormButton
