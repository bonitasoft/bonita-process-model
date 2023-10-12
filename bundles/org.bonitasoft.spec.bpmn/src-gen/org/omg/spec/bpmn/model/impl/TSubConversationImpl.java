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
package org.omg.spec.bpmn.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TConversationNode;
import org.omg.spec.bpmn.model.TSubConversation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TSub Conversation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TSubConversationImpl#getConversationNodeGroup <em>Conversation Node Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TSubConversationImpl#getConversationNode <em>Conversation Node</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TSubConversationImpl extends TConversationNodeImpl implements TSubConversation {

    /**
     * The cached value of the '{@link #getConversationNodeGroup() <em>Conversation Node Group</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConversationNodeGroup()
     * @generated
     * @ordered
     */
    protected FeatureMap conversationNodeGroup;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TSubConversationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TSUB_CONVERSATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FeatureMap getConversationNodeGroup() {
        if (conversationNodeGroup == null) {
            conversationNodeGroup = new BasicFeatureMap(this, ModelPackage.TSUB_CONVERSATION__CONVERSATION_NODE_GROUP);
        }
        return conversationNodeGroup;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TConversationNode> getConversationNode() {
        return getConversationNodeGroup().list(ModelPackage.Literals.TSUB_CONVERSATION__CONVERSATION_NODE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ModelPackage.TSUB_CONVERSATION__CONVERSATION_NODE_GROUP:
                return ((InternalEList<?>) getConversationNodeGroup()).basicRemove(otherEnd, msgs);
            case ModelPackage.TSUB_CONVERSATION__CONVERSATION_NODE:
                return ((InternalEList<?>) getConversationNode()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ModelPackage.TSUB_CONVERSATION__CONVERSATION_NODE_GROUP:
                if (coreType)
                    return getConversationNodeGroup();
                return ((FeatureMap.Internal) getConversationNodeGroup()).getWrapper();
            case ModelPackage.TSUB_CONVERSATION__CONVERSATION_NODE:
                return getConversationNode();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ModelPackage.TSUB_CONVERSATION__CONVERSATION_NODE_GROUP:
                ((FeatureMap.Internal) getConversationNodeGroup()).set(newValue);
                return;
            case ModelPackage.TSUB_CONVERSATION__CONVERSATION_NODE:
                getConversationNode().clear();
                getConversationNode().addAll((Collection<? extends TConversationNode>) newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case ModelPackage.TSUB_CONVERSATION__CONVERSATION_NODE_GROUP:
                getConversationNodeGroup().clear();
                return;
            case ModelPackage.TSUB_CONVERSATION__CONVERSATION_NODE:
                getConversationNode().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case ModelPackage.TSUB_CONVERSATION__CONVERSATION_NODE_GROUP:
                return conversationNodeGroup != null && !conversationNodeGroup.isEmpty();
            case ModelPackage.TSUB_CONVERSATION__CONVERSATION_NODE:
                return !getConversationNode().isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (conversationNodeGroup: "); //$NON-NLS-1$
        result.append(conversationNodeGroup);
        result.append(')');
        return result.toString();
    }

} //TSubConversationImpl
