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
import org.omg.spec.bpmn.model.TParticipantAssociation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TParticipant Association</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TParticipantAssociationImpl#getInnerParticipantRef <em>Inner Participant Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TParticipantAssociationImpl#getOuterParticipantRef <em>Outer Participant Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TParticipantAssociationImpl extends TBaseElementImpl implements TParticipantAssociation {

    /**
     * The default value of the '{@link #getInnerParticipantRef() <em>Inner Participant Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInnerParticipantRef()
     * @generated
     * @ordered
     */
    protected static final QName INNER_PARTICIPANT_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getInnerParticipantRef() <em>Inner Participant Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInnerParticipantRef()
     * @generated
     * @ordered
     */
    protected QName innerParticipantRef = INNER_PARTICIPANT_REF_EDEFAULT;

    /**
     * The default value of the '{@link #getOuterParticipantRef() <em>Outer Participant Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOuterParticipantRef()
     * @generated
     * @ordered
     */
    protected static final QName OUTER_PARTICIPANT_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getOuterParticipantRef() <em>Outer Participant Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOuterParticipantRef()
     * @generated
     * @ordered
     */
    protected QName outerParticipantRef = OUTER_PARTICIPANT_REF_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TParticipantAssociationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TPARTICIPANT_ASSOCIATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getInnerParticipantRef() {
        return innerParticipantRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setInnerParticipantRef(QName newInnerParticipantRef) {
        QName oldInnerParticipantRef = innerParticipantRef;
        innerParticipantRef = newInnerParticipantRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TPARTICIPANT_ASSOCIATION__INNER_PARTICIPANT_REF, oldInnerParticipantRef,
                    innerParticipantRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getOuterParticipantRef() {
        return outerParticipantRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setOuterParticipantRef(QName newOuterParticipantRef) {
        QName oldOuterParticipantRef = outerParticipantRef;
        outerParticipantRef = newOuterParticipantRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TPARTICIPANT_ASSOCIATION__OUTER_PARTICIPANT_REF, oldOuterParticipantRef,
                    outerParticipantRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ModelPackage.TPARTICIPANT_ASSOCIATION__INNER_PARTICIPANT_REF:
                return getInnerParticipantRef();
            case ModelPackage.TPARTICIPANT_ASSOCIATION__OUTER_PARTICIPANT_REF:
                return getOuterParticipantRef();
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
            case ModelPackage.TPARTICIPANT_ASSOCIATION__INNER_PARTICIPANT_REF:
                setInnerParticipantRef((QName) newValue);
                return;
            case ModelPackage.TPARTICIPANT_ASSOCIATION__OUTER_PARTICIPANT_REF:
                setOuterParticipantRef((QName) newValue);
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
            case ModelPackage.TPARTICIPANT_ASSOCIATION__INNER_PARTICIPANT_REF:
                setInnerParticipantRef(INNER_PARTICIPANT_REF_EDEFAULT);
                return;
            case ModelPackage.TPARTICIPANT_ASSOCIATION__OUTER_PARTICIPANT_REF:
                setOuterParticipantRef(OUTER_PARTICIPANT_REF_EDEFAULT);
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
            case ModelPackage.TPARTICIPANT_ASSOCIATION__INNER_PARTICIPANT_REF:
                return INNER_PARTICIPANT_REF_EDEFAULT == null ? innerParticipantRef != null
                        : !INNER_PARTICIPANT_REF_EDEFAULT.equals(innerParticipantRef);
            case ModelPackage.TPARTICIPANT_ASSOCIATION__OUTER_PARTICIPANT_REF:
                return OUTER_PARTICIPANT_REF_EDEFAULT == null ? outerParticipantRef != null
                        : !OUTER_PARTICIPANT_REF_EDEFAULT.equals(outerParticipantRef);
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
        result.append(" (innerParticipantRef: "); //$NON-NLS-1$
        result.append(innerParticipantRef);
        result.append(", outerParticipantRef: "); //$NON-NLS-1$
        result.append(outerParticipantRef);
        result.append(')');
        return result.toString();
    }

} //TParticipantAssociationImpl
