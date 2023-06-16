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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TSub Conversation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TSubConversation#getConversationNodeGroup <em>Conversation Node Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TSubConversation#getConversationNode <em>Conversation Node</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTSubConversation()
 * @model extendedMetaData="name='tSubConversation' kind='elementOnly'"
 * @generated
 */
public interface TSubConversation extends TConversationNode {

    /**
     * Returns the value of the '<em><b>Conversation Node Group</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Conversation Node Group</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTSubConversation_ConversationNodeGroup()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='group' name='conversationNode:group' namespace='##targetNamespace'"
     * @generated
     */
    FeatureMap getConversationNodeGroup();

    /**
     * Returns the value of the '<em><b>Conversation Node</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.bpmn.model.TConversationNode}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Conversation Node</em>' containment reference list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTSubConversation_ConversationNode()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='conversationNode' namespace='##targetNamespace' group='conversationNode:group'"
     * @generated
     */
    EList<TConversationNode> getConversationNode();

} // TSubConversation
