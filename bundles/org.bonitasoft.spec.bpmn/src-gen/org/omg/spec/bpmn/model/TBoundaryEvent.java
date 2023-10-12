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

import javax.xml.namespace.QName;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TBoundary Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TBoundaryEvent#getAttachedToRef <em>Attached To Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TBoundaryEvent#isCancelActivity <em>Cancel Activity</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTBoundaryEvent()
 * @model extendedMetaData="name='tBoundaryEvent' kind='elementOnly'"
 * @generated
 */
public interface TBoundaryEvent extends TCatchEvent {

    /**
     * Returns the value of the '<em><b>Attached To Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Attached To Ref</em>' attribute.
     * @see #setAttachedToRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTBoundaryEvent_AttachedToRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
     *        extendedMetaData="kind='attribute' name='attachedToRef'"
     * @generated
     */
    QName getAttachedToRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TBoundaryEvent#getAttachedToRef <em>Attached To Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Attached To Ref</em>' attribute.
     * @see #getAttachedToRef()
     * @generated
     */
    void setAttachedToRef(QName value);

    /**
     * Returns the value of the '<em><b>Cancel Activity</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Cancel Activity</em>' attribute.
     * @see #isSetCancelActivity()
     * @see #unsetCancelActivity()
     * @see #setCancelActivity(boolean)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTBoundaryEvent_CancelActivity()
     * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='cancelActivity'"
     * @generated
     */
    boolean isCancelActivity();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TBoundaryEvent#isCancelActivity <em>Cancel Activity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Cancel Activity</em>' attribute.
     * @see #isSetCancelActivity()
     * @see #unsetCancelActivity()
     * @see #isCancelActivity()
     * @generated
     */
    void setCancelActivity(boolean value);

    /**
     * Unsets the value of the '{@link org.omg.spec.bpmn.model.TBoundaryEvent#isCancelActivity <em>Cancel Activity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetCancelActivity()
     * @see #isCancelActivity()
     * @see #setCancelActivity(boolean)
     * @generated
     */
    void unsetCancelActivity();

    /**
     * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TBoundaryEvent#isCancelActivity <em>Cancel Activity</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Cancel Activity</em>' attribute is set.
     * @see #unsetCancelActivity()
     * @see #isCancelActivity()
     * @see #setCancelActivity(boolean)
     * @generated
     */
    boolean isSetCancelActivity();

} // TBoundaryEvent
