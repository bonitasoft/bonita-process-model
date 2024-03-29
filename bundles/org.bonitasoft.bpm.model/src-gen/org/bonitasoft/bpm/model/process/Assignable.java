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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assignable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.model.process.Assignable#getActor <em>Actor</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.model.process.Assignable#getFilters <em>Filters</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.bpm.model.process.ProcessPackage#getAssignable()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Assignable extends EObject {
    /**
     * Returns the value of the '<em><b>Actor</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Actor</em>' reference.
     * @see #setActor(Actor)
     * @see org.bonitasoft.bpm.model.process.ProcessPackage#getAssignable_Actor()
     * @model
     * @generated
     */
    Actor getActor();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.model.process.Assignable#getActor <em>Actor</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Actor</em>' reference.
     * @see #getActor()
     * @generated
     */
    void setActor(Actor value);

    /**
     * Returns the value of the '<em><b>Filters</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.bpm.model.process.ActorFilter}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Filters</em>' containment reference list.
     * @see org.bonitasoft.bpm.model.process.ProcessPackage#getAssignable_Filters()
     * @model containment="true"
     * @generated
     */
    EList<ActorFilter> getFilters();

} // Assignable
