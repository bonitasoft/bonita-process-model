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
 * A representation of the model object '<em><b>TConversation Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TConversationAssociation#getInnerConversationNodeRef <em>Inner Conversation Node Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TConversationAssociation#getOuterConversationNodeRef <em>Outer Conversation Node Ref</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTConversationAssociation()
 * @model extendedMetaData="name='tConversationAssociation' kind='elementOnly'"
 * @generated
 */
public interface TConversationAssociation extends TBaseElement {

    /**
     * Returns the value of the '<em><b>Inner Conversation Node Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Inner Conversation Node Ref</em>' attribute.
     * @see #setInnerConversationNodeRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTConversationAssociation_InnerConversationNodeRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
     *        extendedMetaData="kind='attribute' name='innerConversationNodeRef'"
     * @generated
     */
    QName getInnerConversationNodeRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TConversationAssociation#getInnerConversationNodeRef <em>Inner Conversation Node Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Inner Conversation Node Ref</em>' attribute.
     * @see #getInnerConversationNodeRef()
     * @generated
     */
    void setInnerConversationNodeRef(QName value);

    /**
     * Returns the value of the '<em><b>Outer Conversation Node Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Outer Conversation Node Ref</em>' attribute.
     * @see #setOuterConversationNodeRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTConversationAssociation_OuterConversationNodeRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
     *        extendedMetaData="kind='attribute' name='outerConversationNodeRef'"
     * @generated
     */
    QName getOuterConversationNodeRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TConversationAssociation#getOuterConversationNodeRef <em>Outer Conversation Node Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Outer Conversation Node Ref</em>' attribute.
     * @see #getOuterConversationNodeRef()
     * @generated
     */
    void setOuterConversationNodeRef(QName value);

} // TConversationAssociation
