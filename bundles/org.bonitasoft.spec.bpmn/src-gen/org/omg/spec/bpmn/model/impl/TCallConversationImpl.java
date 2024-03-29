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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TCallConversation;
import org.omg.spec.bpmn.model.TParticipantAssociation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TCall Conversation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCallConversationImpl#getParticipantAssociation <em>Participant Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCallConversationImpl#getCalledCollaborationRef <em>Called Collaboration Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TCallConversationImpl extends TConversationNodeImpl implements TCallConversation {

    /**
     * The cached value of the '{@link #getParticipantAssociation() <em>Participant Association</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParticipantAssociation()
     * @generated
     * @ordered
     */
    protected EList<TParticipantAssociation> participantAssociation;

    /**
     * The default value of the '{@link #getCalledCollaborationRef() <em>Called Collaboration Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCalledCollaborationRef()
     * @generated
     * @ordered
     */
    protected static final QName CALLED_COLLABORATION_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCalledCollaborationRef() <em>Called Collaboration Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCalledCollaborationRef()
     * @generated
     * @ordered
     */
    protected QName calledCollaborationRef = CALLED_COLLABORATION_REF_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TCallConversationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TCALL_CONVERSATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TParticipantAssociation> getParticipantAssociation() {
        if (participantAssociation == null) {
            participantAssociation = new EObjectContainmentEList<TParticipantAssociation>(TParticipantAssociation.class,
                    this, ModelPackage.TCALL_CONVERSATION__PARTICIPANT_ASSOCIATION);
        }
        return participantAssociation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getCalledCollaborationRef() {
        return calledCollaborationRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCalledCollaborationRef(QName newCalledCollaborationRef) {
        QName oldCalledCollaborationRef = calledCollaborationRef;
        calledCollaborationRef = newCalledCollaborationRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TCALL_CONVERSATION__CALLED_COLLABORATION_REF, oldCalledCollaborationRef,
                    calledCollaborationRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ModelPackage.TCALL_CONVERSATION__PARTICIPANT_ASSOCIATION:
                return ((InternalEList<?>) getParticipantAssociation()).basicRemove(otherEnd, msgs);
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
            case ModelPackage.TCALL_CONVERSATION__PARTICIPANT_ASSOCIATION:
                return getParticipantAssociation();
            case ModelPackage.TCALL_CONVERSATION__CALLED_COLLABORATION_REF:
                return getCalledCollaborationRef();
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
            case ModelPackage.TCALL_CONVERSATION__PARTICIPANT_ASSOCIATION:
                getParticipantAssociation().clear();
                getParticipantAssociation().addAll((Collection<? extends TParticipantAssociation>) newValue);
                return;
            case ModelPackage.TCALL_CONVERSATION__CALLED_COLLABORATION_REF:
                setCalledCollaborationRef((QName) newValue);
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
            case ModelPackage.TCALL_CONVERSATION__PARTICIPANT_ASSOCIATION:
                getParticipantAssociation().clear();
                return;
            case ModelPackage.TCALL_CONVERSATION__CALLED_COLLABORATION_REF:
                setCalledCollaborationRef(CALLED_COLLABORATION_REF_EDEFAULT);
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
            case ModelPackage.TCALL_CONVERSATION__PARTICIPANT_ASSOCIATION:
                return participantAssociation != null && !participantAssociation.isEmpty();
            case ModelPackage.TCALL_CONVERSATION__CALLED_COLLABORATION_REF:
                return CALLED_COLLABORATION_REF_EDEFAULT == null ? calledCollaborationRef != null
                        : !CALLED_COLLABORATION_REF_EDEFAULT.equals(calledCollaborationRef);
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
        result.append(" (calledCollaborationRef: "); //$NON-NLS-1$
        result.append(calledCollaborationRef);
        result.append(')');
        return result.toString();
    }

} //TCallConversationImpl
