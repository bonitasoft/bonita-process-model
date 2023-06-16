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
import org.omg.spec.bpmn.model.TCallChoreography;
import org.omg.spec.bpmn.model.TParticipantAssociation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TCall Choreography</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCallChoreographyImpl#getParticipantAssociation <em>Participant Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCallChoreographyImpl#getCalledChoreographyRef <em>Called Choreography Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TCallChoreographyImpl extends TChoreographyActivityImpl implements TCallChoreography {

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
     * The default value of the '{@link #getCalledChoreographyRef() <em>Called Choreography Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCalledChoreographyRef()
     * @generated
     * @ordered
     */
    protected static final QName CALLED_CHOREOGRAPHY_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCalledChoreographyRef() <em>Called Choreography Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCalledChoreographyRef()
     * @generated
     * @ordered
     */
    protected QName calledChoreographyRef = CALLED_CHOREOGRAPHY_REF_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TCallChoreographyImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TCALL_CHOREOGRAPHY;
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
                    this, ModelPackage.TCALL_CHOREOGRAPHY__PARTICIPANT_ASSOCIATION);
        }
        return participantAssociation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getCalledChoreographyRef() {
        return calledChoreographyRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCalledChoreographyRef(QName newCalledChoreographyRef) {
        QName oldCalledChoreographyRef = calledChoreographyRef;
        calledChoreographyRef = newCalledChoreographyRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TCALL_CHOREOGRAPHY__CALLED_CHOREOGRAPHY_REF, oldCalledChoreographyRef,
                    calledChoreographyRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ModelPackage.TCALL_CHOREOGRAPHY__PARTICIPANT_ASSOCIATION:
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
            case ModelPackage.TCALL_CHOREOGRAPHY__PARTICIPANT_ASSOCIATION:
                return getParticipantAssociation();
            case ModelPackage.TCALL_CHOREOGRAPHY__CALLED_CHOREOGRAPHY_REF:
                return getCalledChoreographyRef();
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
            case ModelPackage.TCALL_CHOREOGRAPHY__PARTICIPANT_ASSOCIATION:
                getParticipantAssociation().clear();
                getParticipantAssociation().addAll((Collection<? extends TParticipantAssociation>) newValue);
                return;
            case ModelPackage.TCALL_CHOREOGRAPHY__CALLED_CHOREOGRAPHY_REF:
                setCalledChoreographyRef((QName) newValue);
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
            case ModelPackage.TCALL_CHOREOGRAPHY__PARTICIPANT_ASSOCIATION:
                getParticipantAssociation().clear();
                return;
            case ModelPackage.TCALL_CHOREOGRAPHY__CALLED_CHOREOGRAPHY_REF:
                setCalledChoreographyRef(CALLED_CHOREOGRAPHY_REF_EDEFAULT);
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
            case ModelPackage.TCALL_CHOREOGRAPHY__PARTICIPANT_ASSOCIATION:
                return participantAssociation != null && !participantAssociation.isEmpty();
            case ModelPackage.TCALL_CHOREOGRAPHY__CALLED_CHOREOGRAPHY_REF:
                return CALLED_CHOREOGRAPHY_REF_EDEFAULT == null ? calledChoreographyRef != null
                        : !CALLED_CHOREOGRAPHY_REF_EDEFAULT.equals(calledChoreographyRef);
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
        result.append(" (calledChoreographyRef: "); //$NON-NLS-1$
        result.append(calledChoreographyRef);
        result.append(')');
        return result.toString();
    }

} //TCallChoreographyImpl
