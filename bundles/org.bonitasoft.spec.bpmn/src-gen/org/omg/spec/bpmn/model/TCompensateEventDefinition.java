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
 * A representation of the model object '<em><b>TCompensate Event Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TCompensateEventDefinition#getActivityRef <em>Activity Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCompensateEventDefinition#isWaitForCompletion <em>Wait For Completion</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTCompensateEventDefinition()
 * @model extendedMetaData="name='tCompensateEventDefinition' kind='elementOnly'"
 * @generated
 */
public interface TCompensateEventDefinition extends TEventDefinition {

    /**
     * Returns the value of the '<em><b>Activity Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Activity Ref</em>' attribute.
     * @see #setActivityRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTCompensateEventDefinition_ActivityRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='activityRef'"
     * @generated
     */
    QName getActivityRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TCompensateEventDefinition#getActivityRef <em>Activity Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Activity Ref</em>' attribute.
     * @see #getActivityRef()
     * @generated
     */
    void setActivityRef(QName value);

    /**
     * Returns the value of the '<em><b>Wait For Completion</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Wait For Completion</em>' attribute.
     * @see #isSetWaitForCompletion()
     * @see #unsetWaitForCompletion()
     * @see #setWaitForCompletion(boolean)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTCompensateEventDefinition_WaitForCompletion()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='waitForCompletion'"
     * @generated
     */
    boolean isWaitForCompletion();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TCompensateEventDefinition#isWaitForCompletion <em>Wait For Completion</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Wait For Completion</em>' attribute.
     * @see #isSetWaitForCompletion()
     * @see #unsetWaitForCompletion()
     * @see #isWaitForCompletion()
     * @generated
     */
    void setWaitForCompletion(boolean value);

    /**
     * Unsets the value of the '{@link org.omg.spec.bpmn.model.TCompensateEventDefinition#isWaitForCompletion <em>Wait For Completion</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetWaitForCompletion()
     * @see #isWaitForCompletion()
     * @see #setWaitForCompletion(boolean)
     * @generated
     */
    void unsetWaitForCompletion();

    /**
     * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TCompensateEventDefinition#isWaitForCompletion <em>Wait For Completion</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Wait For Completion</em>' attribute is set.
     * @see #unsetWaitForCompletion()
     * @see #isWaitForCompletion()
     * @see #setWaitForCompletion(boolean)
     * @generated
     */
    boolean isSetWaitForCompletion();

} // TCompensateEventDefinition
