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
 * A representation of the model object '<em><b>TService Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TServiceTask#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TServiceTask#getOperationRef <em>Operation Ref</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTServiceTask()
 * @model extendedMetaData="name='tServiceTask' kind='elementOnly'"
 * @generated
 */
public interface TServiceTask extends TTask {

    /**
     * Returns the value of the '<em><b>Implementation</b></em>' attribute.
     * The default value is <code>"##WebService"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Implementation</em>' attribute.
     * @see #isSetImplementation()
     * @see #unsetImplementation()
     * @see #setImplementation(Object)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTServiceTask_Implementation()
     * @model default="##WebService" unsettable="true" dataType="org.omg.spec.bpmn.model.TImplementation"
     *        extendedMetaData="kind='attribute' name='implementation'"
     * @generated
     */
    Object getImplementation();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TServiceTask#getImplementation <em>Implementation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Implementation</em>' attribute.
     * @see #isSetImplementation()
     * @see #unsetImplementation()
     * @see #getImplementation()
     * @generated
     */
    void setImplementation(Object value);

    /**
     * Unsets the value of the '{@link org.omg.spec.bpmn.model.TServiceTask#getImplementation <em>Implementation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetImplementation()
     * @see #getImplementation()
     * @see #setImplementation(Object)
     * @generated
     */
    void unsetImplementation();

    /**
     * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TServiceTask#getImplementation <em>Implementation</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Implementation</em>' attribute is set.
     * @see #unsetImplementation()
     * @see #getImplementation()
     * @see #setImplementation(Object)
     * @generated
     */
    boolean isSetImplementation();

    /**
     * Returns the value of the '<em><b>Operation Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Operation Ref</em>' attribute.
     * @see #setOperationRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTServiceTask_OperationRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='operationRef'"
     * @generated
     */
    QName getOperationRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TServiceTask#getOperationRef <em>Operation Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Operation Ref</em>' attribute.
     * @see #getOperationRef()
     * @generated
     */
    void setOperationRef(QName value);

} // TServiceTask
