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
import org.omg.spec.bpmn.model.TMessageEventDefinition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TMessage Event Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TMessageEventDefinitionImpl#getOperationRef <em>Operation Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TMessageEventDefinitionImpl#getMessageRef <em>Message Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TMessageEventDefinitionImpl extends TEventDefinitionImpl implements TMessageEventDefinition {

    /**
     * The default value of the '{@link #getOperationRef() <em>Operation Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOperationRef()
     * @generated
     * @ordered
     */
    protected static final QName OPERATION_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getOperationRef() <em>Operation Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOperationRef()
     * @generated
     * @ordered
     */
    protected QName operationRef = OPERATION_REF_EDEFAULT;

    /**
     * The default value of the '{@link #getMessageRef() <em>Message Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMessageRef()
     * @generated
     * @ordered
     */
    protected static final QName MESSAGE_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMessageRef() <em>Message Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMessageRef()
     * @generated
     * @ordered
     */
    protected QName messageRef = MESSAGE_REF_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TMessageEventDefinitionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TMESSAGE_EVENT_DEFINITION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getOperationRef() {
        return operationRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setOperationRef(QName newOperationRef) {
        QName oldOperationRef = operationRef;
        operationRef = newOperationRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TMESSAGE_EVENT_DEFINITION__OPERATION_REF,
                    oldOperationRef, operationRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getMessageRef() {
        return messageRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setMessageRef(QName newMessageRef) {
        QName oldMessageRef = messageRef;
        messageRef = newMessageRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TMESSAGE_EVENT_DEFINITION__MESSAGE_REF,
                    oldMessageRef, messageRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ModelPackage.TMESSAGE_EVENT_DEFINITION__OPERATION_REF:
                return getOperationRef();
            case ModelPackage.TMESSAGE_EVENT_DEFINITION__MESSAGE_REF:
                return getMessageRef();
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
            case ModelPackage.TMESSAGE_EVENT_DEFINITION__OPERATION_REF:
                setOperationRef((QName) newValue);
                return;
            case ModelPackage.TMESSAGE_EVENT_DEFINITION__MESSAGE_REF:
                setMessageRef((QName) newValue);
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
            case ModelPackage.TMESSAGE_EVENT_DEFINITION__OPERATION_REF:
                setOperationRef(OPERATION_REF_EDEFAULT);
                return;
            case ModelPackage.TMESSAGE_EVENT_DEFINITION__MESSAGE_REF:
                setMessageRef(MESSAGE_REF_EDEFAULT);
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
            case ModelPackage.TMESSAGE_EVENT_DEFINITION__OPERATION_REF:
                return OPERATION_REF_EDEFAULT == null ? operationRef != null
                        : !OPERATION_REF_EDEFAULT.equals(operationRef);
            case ModelPackage.TMESSAGE_EVENT_DEFINITION__MESSAGE_REF:
                return MESSAGE_REF_EDEFAULT == null ? messageRef != null : !MESSAGE_REF_EDEFAULT.equals(messageRef);
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
        result.append(" (operationRef: "); //$NON-NLS-1$
        result.append(operationRef);
        result.append(", messageRef: "); //$NON-NLS-1$
        result.append(messageRef);
        result.append(')');
        return result.toString();
    }

} //TMessageEventDefinitionImpl
