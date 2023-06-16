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
import org.omg.spec.bpmn.model.TChoreographyActivity;
import org.omg.spec.bpmn.model.TChoreographyLoopType;
import org.omg.spec.bpmn.model.TCorrelationKey;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TChoreography Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TChoreographyActivityImpl#getParticipantRef <em>Participant Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TChoreographyActivityImpl#getCorrelationKey <em>Correlation Key</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TChoreographyActivityImpl#getInitiatingParticipantRef <em>Initiating Participant Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TChoreographyActivityImpl#getLoopType <em>Loop Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class TChoreographyActivityImpl extends TFlowNodeImpl implements TChoreographyActivity {

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
     * The cached value of the '{@link #getCorrelationKey() <em>Correlation Key</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCorrelationKey()
     * @generated
     * @ordered
     */
    protected EList<TCorrelationKey> correlationKey;

    /**
     * The default value of the '{@link #getInitiatingParticipantRef() <em>Initiating Participant Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInitiatingParticipantRef()
     * @generated
     * @ordered
     */
    protected static final QName INITIATING_PARTICIPANT_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getInitiatingParticipantRef() <em>Initiating Participant Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInitiatingParticipantRef()
     * @generated
     * @ordered
     */
    protected QName initiatingParticipantRef = INITIATING_PARTICIPANT_REF_EDEFAULT;

    /**
     * The default value of the '{@link #getLoopType() <em>Loop Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLoopType()
     * @generated
     * @ordered
     */
    protected static final TChoreographyLoopType LOOP_TYPE_EDEFAULT = TChoreographyLoopType.NONE;

    /**
     * The cached value of the '{@link #getLoopType() <em>Loop Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLoopType()
     * @generated
     * @ordered
     */
    protected TChoreographyLoopType loopType = LOOP_TYPE_EDEFAULT;

    /**
     * This is true if the Loop Type attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean loopTypeESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TChoreographyActivityImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TCHOREOGRAPHY_ACTIVITY;
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
                    ModelPackage.TCHOREOGRAPHY_ACTIVITY__PARTICIPANT_REF);
        }
        return participantRef;
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
                    ModelPackage.TCHOREOGRAPHY_ACTIVITY__CORRELATION_KEY);
        }
        return correlationKey;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getInitiatingParticipantRef() {
        return initiatingParticipantRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setInitiatingParticipantRef(QName newInitiatingParticipantRef) {
        QName oldInitiatingParticipantRef = initiatingParticipantRef;
        initiatingParticipantRef = newInitiatingParticipantRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TCHOREOGRAPHY_ACTIVITY__INITIATING_PARTICIPANT_REF, oldInitiatingParticipantRef,
                    initiatingParticipantRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TChoreographyLoopType getLoopType() {
        return loopType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setLoopType(TChoreographyLoopType newLoopType) {
        TChoreographyLoopType oldLoopType = loopType;
        loopType = newLoopType == null ? LOOP_TYPE_EDEFAULT : newLoopType;
        boolean oldLoopTypeESet = loopTypeESet;
        loopTypeESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TCHOREOGRAPHY_ACTIVITY__LOOP_TYPE,
                    oldLoopType, loopType, !oldLoopTypeESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetLoopType() {
        TChoreographyLoopType oldLoopType = loopType;
        boolean oldLoopTypeESet = loopTypeESet;
        loopType = LOOP_TYPE_EDEFAULT;
        loopTypeESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TCHOREOGRAPHY_ACTIVITY__LOOP_TYPE,
                    oldLoopType, LOOP_TYPE_EDEFAULT, oldLoopTypeESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetLoopType() {
        return loopTypeESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ModelPackage.TCHOREOGRAPHY_ACTIVITY__CORRELATION_KEY:
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
            case ModelPackage.TCHOREOGRAPHY_ACTIVITY__PARTICIPANT_REF:
                return getParticipantRef();
            case ModelPackage.TCHOREOGRAPHY_ACTIVITY__CORRELATION_KEY:
                return getCorrelationKey();
            case ModelPackage.TCHOREOGRAPHY_ACTIVITY__INITIATING_PARTICIPANT_REF:
                return getInitiatingParticipantRef();
            case ModelPackage.TCHOREOGRAPHY_ACTIVITY__LOOP_TYPE:
                return getLoopType();
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
            case ModelPackage.TCHOREOGRAPHY_ACTIVITY__PARTICIPANT_REF:
                getParticipantRef().clear();
                getParticipantRef().addAll((Collection<? extends QName>) newValue);
                return;
            case ModelPackage.TCHOREOGRAPHY_ACTIVITY__CORRELATION_KEY:
                getCorrelationKey().clear();
                getCorrelationKey().addAll((Collection<? extends TCorrelationKey>) newValue);
                return;
            case ModelPackage.TCHOREOGRAPHY_ACTIVITY__INITIATING_PARTICIPANT_REF:
                setInitiatingParticipantRef((QName) newValue);
                return;
            case ModelPackage.TCHOREOGRAPHY_ACTIVITY__LOOP_TYPE:
                setLoopType((TChoreographyLoopType) newValue);
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
            case ModelPackage.TCHOREOGRAPHY_ACTIVITY__PARTICIPANT_REF:
                getParticipantRef().clear();
                return;
            case ModelPackage.TCHOREOGRAPHY_ACTIVITY__CORRELATION_KEY:
                getCorrelationKey().clear();
                return;
            case ModelPackage.TCHOREOGRAPHY_ACTIVITY__INITIATING_PARTICIPANT_REF:
                setInitiatingParticipantRef(INITIATING_PARTICIPANT_REF_EDEFAULT);
                return;
            case ModelPackage.TCHOREOGRAPHY_ACTIVITY__LOOP_TYPE:
                unsetLoopType();
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
            case ModelPackage.TCHOREOGRAPHY_ACTIVITY__PARTICIPANT_REF:
                return participantRef != null && !participantRef.isEmpty();
            case ModelPackage.TCHOREOGRAPHY_ACTIVITY__CORRELATION_KEY:
                return correlationKey != null && !correlationKey.isEmpty();
            case ModelPackage.TCHOREOGRAPHY_ACTIVITY__INITIATING_PARTICIPANT_REF:
                return INITIATING_PARTICIPANT_REF_EDEFAULT == null ? initiatingParticipantRef != null
                        : !INITIATING_PARTICIPANT_REF_EDEFAULT.equals(initiatingParticipantRef);
            case ModelPackage.TCHOREOGRAPHY_ACTIVITY__LOOP_TYPE:
                return isSetLoopType();
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
        result.append(", initiatingParticipantRef: "); //$NON-NLS-1$
        result.append(initiatingParticipantRef);
        result.append(", loopType: "); //$NON-NLS-1$
        if (loopTypeESet)
            result.append(loopType);
        else
            result.append("<unset>"); //$NON-NLS-1$
        result.append(')');
        return result.toString();
    }

} //TChoreographyActivityImpl
