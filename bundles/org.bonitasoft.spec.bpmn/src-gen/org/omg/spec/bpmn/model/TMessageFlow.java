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
 * A representation of the model object '<em><b>TMessage Flow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TMessageFlow#getMessageRef <em>Message Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TMessageFlow#getName <em>Name</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TMessageFlow#getSourceRef <em>Source Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TMessageFlow#getTargetRef <em>Target Ref</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTMessageFlow()
 * @model extendedMetaData="name='tMessageFlow' kind='elementOnly'"
 * @generated
 */
public interface TMessageFlow extends TBaseElement {

    /**
     * Returns the value of the '<em><b>Message Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Message Ref</em>' attribute.
     * @see #setMessageRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTMessageFlow_MessageRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='messageRef'"
     * @generated
     */
    QName getMessageRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TMessageFlow#getMessageRef <em>Message Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Message Ref</em>' attribute.
     * @see #getMessageRef()
     * @generated
     */
    void setMessageRef(QName value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTMessageFlow_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TMessageFlow#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Source Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Ref</em>' attribute.
     * @see #setSourceRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTMessageFlow_SourceRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
     *        extendedMetaData="kind='attribute' name='sourceRef'"
     * @generated
     */
    QName getSourceRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TMessageFlow#getSourceRef <em>Source Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Ref</em>' attribute.
     * @see #getSourceRef()
     * @generated
     */
    void setSourceRef(QName value);

    /**
     * Returns the value of the '<em><b>Target Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Ref</em>' attribute.
     * @see #setTargetRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTMessageFlow_TargetRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
     *        extendedMetaData="kind='attribute' name='targetRef'"
     * @generated
     */
    QName getTargetRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TMessageFlow#getTargetRef <em>Target Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Ref</em>' attribute.
     * @see #getTargetRef()
     * @generated
     */
    void setTargetRef(QName value);

} // TMessageFlow
