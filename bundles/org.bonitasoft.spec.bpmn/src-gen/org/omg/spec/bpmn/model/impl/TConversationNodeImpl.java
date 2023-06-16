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

import javax.xml.namespace.QName;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TConversationNode;
import org.omg.spec.bpmn.model.TCorrelationKey;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TConversation Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TConversationNodeImpl#getParticipantRef <em>Participant Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TConversationNodeImpl#getMessageFlowRef <em>Message Flow Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TConversationNodeImpl#getCorrelationKey <em>Correlation Key</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TConversationNodeImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class TConversationNodeImpl extends TBaseElementImpl implements TConversationNode {

    /**
     * The cached value of the '{@link #getParticipantRef() <em>Participant Ref</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParticipantRef()
     * @generated
     * @ordered
     */
    protected EList<QName> participantRef;

    /**
     * The cached value of the '{@link #getMessageFlowRef() <em>Message Flow Ref</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMessageFlowRef()
     * @generated
     * @ordered
     */
    protected EList<QName> messageFlowRef;

    /**
     * The cached value of the '{@link #getCorrelationKey() <em>Correlation Key</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCorrelationKey()
     * @generated
     * @ordered
     */
    protected EList<TCorrelationKey> correlationKey;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TConversationNodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TCONVERSATION_NODE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<QName> getParticipantRef() {
        if (participantRef == null) {
            participantRef = new EDataTypeEList<QName>(QName.class, this,
                    ModelPackage.TCONVERSATION_NODE__PARTICIPANT_REF);
        }
        return participantRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<QName> getMessageFlowRef() {
        if (messageFlowRef == null) {
            messageFlowRef = new EDataTypeEList<QName>(QName.class, this,
                    ModelPackage.TCONVERSATION_NODE__MESSAGE_FLOW_REF);
        }
        return messageFlowRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TCorrelationKey> getCorrelationKey() {
        if (correlationKey == null) {
            correlationKey = new EObjectContainmentEList<TCorrelationKey>(TCorrelationKey.class, this,
                    ModelPackage.TCONVERSATION_NODE__CORRELATION_KEY);
        }
        return correlationKey;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TCONVERSATION_NODE__NAME, oldName,
                    name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ModelPackage.TCONVERSATION_NODE__CORRELATION_KEY:
                return ((InternalEList<?>) getCorrelationKey()).basicRemove(otherEnd, msgs);
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
            case ModelPackage.TCONVERSATION_NODE__PARTICIPANT_REF:
                return getParticipantRef();
            case ModelPackage.TCONVERSATION_NODE__MESSAGE_FLOW_REF:
                return getMessageFlowRef();
            case ModelPackage.TCONVERSATION_NODE__CORRELATION_KEY:
                return getCorrelationKey();
            case ModelPackage.TCONVERSATION_NODE__NAME:
                return getName();
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
            case ModelPackage.TCONVERSATION_NODE__PARTICIPANT_REF:
                getParticipantRef().clear();
                getParticipantRef().addAll((Collection<? extends QName>) newValue);
                return;
            case ModelPackage.TCONVERSATION_NODE__MESSAGE_FLOW_REF:
                getMessageFlowRef().clear();
                getMessageFlowRef().addAll((Collection<? extends QName>) newValue);
                return;
            case ModelPackage.TCONVERSATION_NODE__CORRELATION_KEY:
                getCorrelationKey().clear();
                getCorrelationKey().addAll((Collection<? extends TCorrelationKey>) newValue);
                return;
            case ModelPackage.TCONVERSATION_NODE__NAME:
                setName((String) newValue);
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
            case ModelPackage.TCONVERSATION_NODE__PARTICIPANT_REF:
                getParticipantRef().clear();
                return;
            case ModelPackage.TCONVERSATION_NODE__MESSAGE_FLOW_REF:
                getMessageFlowRef().clear();
                return;
            case ModelPackage.TCONVERSATION_NODE__CORRELATION_KEY:
                getCorrelationKey().clear();
                return;
            case ModelPackage.TCONVERSATION_NODE__NAME:
                setName(NAME_EDEFAULT);
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
            case ModelPackage.TCONVERSATION_NODE__PARTICIPANT_REF:
                return participantRef != null && !participantRef.isEmpty();
            case ModelPackage.TCONVERSATION_NODE__MESSAGE_FLOW_REF:
                return messageFlowRef != null && !messageFlowRef.isEmpty();
            case ModelPackage.TCONVERSATION_NODE__CORRELATION_KEY:
                return correlationKey != null && !correlationKey.isEmpty();
            case ModelPackage.TCONVERSATION_NODE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
        result.append(" (participantRef: "); //$NON-NLS-1$
        result.append(participantRef);
        result.append(", messageFlowRef: "); //$NON-NLS-1$
        result.append(messageFlowRef);
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(')');
        return result.toString();
    }

} //TConversationNodeImpl
