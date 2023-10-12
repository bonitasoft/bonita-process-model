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
import org.omg.spec.bpmn.model.TEscalationEventDefinition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TEscalation Event Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TEscalationEventDefinitionImpl#getEscalationRef <em>Escalation Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TEscalationEventDefinitionImpl extends TEventDefinitionImpl implements TEscalationEventDefinition {

    /**
     * The default value of the '{@link #getEscalationRef() <em>Escalation Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEscalationRef()
     * @generated
     * @ordered
     */
    protected static final QName ESCALATION_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getEscalationRef() <em>Escalation Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEscalationRef()
     * @generated
     * @ordered
     */
    protected QName escalationRef = ESCALATION_REF_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TEscalationEventDefinitionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TESCALATION_EVENT_DEFINITION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getEscalationRef() {
        return escalationRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setEscalationRef(QName newEscalationRef) {
        QName oldEscalationRef = escalationRef;
        escalationRef = newEscalationRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TESCALATION_EVENT_DEFINITION__ESCALATION_REF, oldEscalationRef, escalationRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ModelPackage.TESCALATION_EVENT_DEFINITION__ESCALATION_REF:
                return getEscalationRef();
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
            case ModelPackage.TESCALATION_EVENT_DEFINITION__ESCALATION_REF:
                setEscalationRef((QName) newValue);
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
            case ModelPackage.TESCALATION_EVENT_DEFINITION__ESCALATION_REF:
                setEscalationRef(ESCALATION_REF_EDEFAULT);
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
            case ModelPackage.TESCALATION_EVENT_DEFINITION__ESCALATION_REF:
                return ESCALATION_REF_EDEFAULT == null ? escalationRef != null
                        : !ESCALATION_REF_EDEFAULT.equals(escalationRef);
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
        result.append(" (escalationRef: "); //$NON-NLS-1$
        result.append(escalationRef);
        result.append(')');
        return result.toString();
    }

} //TEscalationEventDefinitionImpl
