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
import org.omg.spec.bpmn.model.TEscalation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TEscalation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TEscalationImpl#getEscalationCode <em>Escalation Code</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TEscalationImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TEscalationImpl#getStructureRef <em>Structure Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TEscalationImpl extends TRootElementImpl implements TEscalation {

    /**
     * The default value of the '{@link #getEscalationCode() <em>Escalation Code</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEscalationCode()
     * @generated
     * @ordered
     */
    protected static final String ESCALATION_CODE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getEscalationCode() <em>Escalation Code</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEscalationCode()
     * @generated
     * @ordered
     */
    protected String escalationCode = ESCALATION_CODE_EDEFAULT;

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
     * The default value of the '{@link #getStructureRef() <em>Structure Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStructureRef()
     * @generated
     * @ordered
     */
    protected static final QName STRUCTURE_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getStructureRef() <em>Structure Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStructureRef()
     * @generated
     * @ordered
     */
    protected QName structureRef = STRUCTURE_REF_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TEscalationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TESCALATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getEscalationCode() {
        return escalationCode;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setEscalationCode(String newEscalationCode) {
        String oldEscalationCode = escalationCode;
        escalationCode = newEscalationCode;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TESCALATION__ESCALATION_CODE,
                    oldEscalationCode, escalationCode));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TESCALATION__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getStructureRef() {
        return structureRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setStructureRef(QName newStructureRef) {
        QName oldStructureRef = structureRef;
        structureRef = newStructureRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TESCALATION__STRUCTURE_REF,
                    oldStructureRef, structureRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ModelPackage.TESCALATION__ESCALATION_CODE:
                return getEscalationCode();
            case ModelPackage.TESCALATION__NAME:
                return getName();
            case ModelPackage.TESCALATION__STRUCTURE_REF:
                return getStructureRef();
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
            case ModelPackage.TESCALATION__ESCALATION_CODE:
                setEscalationCode((String) newValue);
                return;
            case ModelPackage.TESCALATION__NAME:
                setName((String) newValue);
                return;
            case ModelPackage.TESCALATION__STRUCTURE_REF:
                setStructureRef((QName) newValue);
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
            case ModelPackage.TESCALATION__ESCALATION_CODE:
                setEscalationCode(ESCALATION_CODE_EDEFAULT);
                return;
            case ModelPackage.TESCALATION__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ModelPackage.TESCALATION__STRUCTURE_REF:
                setStructureRef(STRUCTURE_REF_EDEFAULT);
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
            case ModelPackage.TESCALATION__ESCALATION_CODE:
                return ESCALATION_CODE_EDEFAULT == null ? escalationCode != null
                        : !ESCALATION_CODE_EDEFAULT.equals(escalationCode);
            case ModelPackage.TESCALATION__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ModelPackage.TESCALATION__STRUCTURE_REF:
                return STRUCTURE_REF_EDEFAULT == null ? structureRef != null
                        : !STRUCTURE_REF_EDEFAULT.equals(structureRef);
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
        result.append(" (escalationCode: "); //$NON-NLS-1$
        result.append(escalationCode);
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", structureRef: "); //$NON-NLS-1$
        result.append(structureRef);
        result.append(')');
        return result.toString();
    }

} //TEscalationImpl
