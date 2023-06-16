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

import javax.xml.namespace.QName;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TConversationAssociation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TConversation Association</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TConversationAssociationImpl#getInnerConversationNodeRef <em>Inner Conversation Node Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TConversationAssociationImpl#getOuterConversationNodeRef <em>Outer Conversation Node Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TConversationAssociationImpl extends TBaseElementImpl implements TConversationAssociation {

    /**
     * The default value of the '{@link #getInnerConversationNodeRef() <em>Inner Conversation Node Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInnerConversationNodeRef()
     * @generated
     * @ordered
     */
    protected static final QName INNER_CONVERSATION_NODE_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getInnerConversationNodeRef() <em>Inner Conversation Node Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInnerConversationNodeRef()
     * @generated
     * @ordered
     */
    protected QName innerConversationNodeRef = INNER_CONVERSATION_NODE_REF_EDEFAULT;

    /**
     * The default value of the '{@link #getOuterConversationNodeRef() <em>Outer Conversation Node Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOuterConversationNodeRef()
     * @generated
     * @ordered
     */
    protected static final QName OUTER_CONVERSATION_NODE_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getOuterConversationNodeRef() <em>Outer Conversation Node Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOuterConversationNodeRef()
     * @generated
     * @ordered
     */
    protected QName outerConversationNodeRef = OUTER_CONVERSATION_NODE_REF_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TConversationAssociationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TCONVERSATION_ASSOCIATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getInnerConversationNodeRef() {
        return innerConversationNodeRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setInnerConversationNodeRef(QName newInnerConversationNodeRef) {
        QName oldInnerConversationNodeRef = innerConversationNodeRef;
        innerConversationNodeRef = newInnerConversationNodeRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TCONVERSATION_ASSOCIATION__INNER_CONVERSATION_NODE_REF, oldInnerConversationNodeRef,
                    innerConversationNodeRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getOuterConversationNodeRef() {
        return outerConversationNodeRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setOuterConversationNodeRef(QName newOuterConversationNodeRef) {
        QName oldOuterConversationNodeRef = outerConversationNodeRef;
        outerConversationNodeRef = newOuterConversationNodeRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TCONVERSATION_ASSOCIATION__OUTER_CONVERSATION_NODE_REF, oldOuterConversationNodeRef,
                    outerConversationNodeRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ModelPackage.TCONVERSATION_ASSOCIATION__INNER_CONVERSATION_NODE_REF:
                return getInnerConversationNodeRef();
            case ModelPackage.TCONVERSATION_ASSOCIATION__OUTER_CONVERSATION_NODE_REF:
                return getOuterConversationNodeRef();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ModelPackage.TCONVERSATION_ASSOCIATION__INNER_CONVERSATION_NODE_REF:
                setInnerConversationNodeRef((QName) newValue);
                return;
            case ModelPackage.TCONVERSATION_ASSOCIATION__OUTER_CONVERSATION_NODE_REF:
                setOuterConversationNodeRef((QName) newValue);
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
            case ModelPackage.TCONVERSATION_ASSOCIATION__INNER_CONVERSATION_NODE_REF:
                setInnerConversationNodeRef(INNER_CONVERSATION_NODE_REF_EDEFAULT);
                return;
            case ModelPackage.TCONVERSATION_ASSOCIATION__OUTER_CONVERSATION_NODE_REF:
                setOuterConversationNodeRef(OUTER_CONVERSATION_NODE_REF_EDEFAULT);
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
            case ModelPackage.TCONVERSATION_ASSOCIATION__INNER_CONVERSATION_NODE_REF:
                return INNER_CONVERSATION_NODE_REF_EDEFAULT == null ? innerConversationNodeRef != null
                        : !INNER_CONVERSATION_NODE_REF_EDEFAULT.equals(innerConversationNodeRef);
            case ModelPackage.TCONVERSATION_ASSOCIATION__OUTER_CONVERSATION_NODE_REF:
                return OUTER_CONVERSATION_NODE_REF_EDEFAULT == null ? outerConversationNodeRef != null
                        : !OUTER_CONVERSATION_NODE_REF_EDEFAULT.equals(outerConversationNodeRef);
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
        result.append(" (innerConversationNodeRef: "); //$NON-NLS-1$
        result.append(innerConversationNodeRef);
        result.append(", outerConversationNodeRef: "); //$NON-NLS-1$
        result.append(outerConversationNodeRef);
        result.append(')');
        return result.toString();
    }

} //TConversationAssociationImpl
