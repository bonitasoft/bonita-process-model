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
 * A representation of the model object '<em><b>TCall Conversation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TCallConversation#getParticipantAssociation <em>Participant Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCallConversation#getCalledCollaborationRef <em>Called Collaboration Ref</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTCallConversation()
 * @model extendedMetaData="name='tCallConversation' kind='elementOnly'"
 * @generated
 */
public interface TCallConversation extends TConversationNode {

    /**
     * Returns the value of the '<em><b>Participant Association</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.bpmn.model.TParticipantAssociation}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Participant Association</em>' containment reference list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTCallConversation_ParticipantAssociation()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='participantAssociation' namespace='##targetNamespace'"
     * @generated
     */
    EList<TParticipantAssociation> getParticipantAssociation();

    /**
     * Returns the value of the '<em><b>Called Collaboration Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Called Collaboration Ref</em>' attribute.
     * @see #setCalledCollaborationRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTCallConversation_CalledCollaborationRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='calledCollaborationRef'"
     * @generated
     */
    QName getCalledCollaborationRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TCallConversation#getCalledCollaborationRef <em>Called Collaboration Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Called Collaboration Ref</em>' attribute.
     * @see #getCalledCollaborationRef()
     * @generated
     */
    void setCalledCollaborationRef(QName value);

} // TCallConversation
