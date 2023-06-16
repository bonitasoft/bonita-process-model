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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TConversation Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TConversationNode#getParticipantRef <em>Participant Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TConversationNode#getMessageFlowRef <em>Message Flow Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TConversationNode#getCorrelationKey <em>Correlation Key</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TConversationNode#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTConversationNode()
 * @model abstract="true"
 *        extendedMetaData="name='tConversationNode' kind='elementOnly'"
 * @generated
 */
public interface TConversationNode extends TBaseElement {

    /**
     * Returns the value of the '<em><b>Participant Ref</b></em>' attribute list.
     * The list contents are of type {@link javax.xml.namespace.QName}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Participant Ref</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTConversationNode_ParticipantRef()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='element' name='participantRef' namespace='##targetNamespace'"
     * @generated
     */
    EList<QName> getParticipantRef();

    /**
     * Returns the value of the '<em><b>Message Flow Ref</b></em>' attribute list.
     * The list contents are of type {@link javax.xml.namespace.QName}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Message Flow Ref</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTConversationNode_MessageFlowRef()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='element' name='messageFlowRef' namespace='##targetNamespace'"
     * @generated
     */
    EList<QName> getMessageFlowRef();

    /**
     * Returns the value of the '<em><b>Correlation Key</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.bpmn.model.TCorrelationKey}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Correlation Key</em>' containment reference list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTConversationNode_CorrelationKey()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='correlationKey' namespace='##targetNamespace'"
     * @generated
     */
    EList<TCorrelationKey> getCorrelationKey();

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTConversationNode_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TConversationNode#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // TConversationNode
