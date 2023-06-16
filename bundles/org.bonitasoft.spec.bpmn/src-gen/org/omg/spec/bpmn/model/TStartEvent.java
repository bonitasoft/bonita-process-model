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
package org.omg.spec.bpmn.model;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TStart Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TStartEvent#isIsInterrupting <em>Is Interrupting</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTStartEvent()
 * @model extendedMetaData="name='tStartEvent' kind='elementOnly'"
 * @generated
 */
public interface TStartEvent extends TCatchEvent {

    /**
     * Returns the value of the '<em><b>Is Interrupting</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Interrupting</em>' attribute.
     * @see #isSetIsInterrupting()
     * @see #unsetIsInterrupting()
     * @see #setIsInterrupting(boolean)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTStartEvent_IsInterrupting()
     * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='isInterrupting'"
     * @generated
     */
    boolean isIsInterrupting();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TStartEvent#isIsInterrupting <em>Is Interrupting</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Interrupting</em>' attribute.
     * @see #isSetIsInterrupting()
     * @see #unsetIsInterrupting()
     * @see #isIsInterrupting()
     * @generated
     */
    void setIsInterrupting(boolean value);

    /**
     * Unsets the value of the '{@link org.omg.spec.bpmn.model.TStartEvent#isIsInterrupting <em>Is Interrupting</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetIsInterrupting()
     * @see #isIsInterrupting()
     * @see #setIsInterrupting(boolean)
     * @generated
     */
    void unsetIsInterrupting();

    /**
     * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TStartEvent#isIsInterrupting <em>Is Interrupting</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Is Interrupting</em>' attribute is set.
     * @see #unsetIsInterrupting()
     * @see #isIsInterrupting()
     * @see #setIsInterrupting(boolean)
     * @generated
     */
    boolean isSetIsInterrupting();

} // TStartEvent
