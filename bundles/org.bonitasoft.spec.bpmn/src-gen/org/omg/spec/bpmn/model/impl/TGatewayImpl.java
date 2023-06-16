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

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TGateway;
import org.omg.spec.bpmn.model.TGatewayDirection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TGateway</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TGatewayImpl#getGatewayDirection <em>Gateway Direction</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TGatewayImpl extends TFlowNodeImpl implements TGateway {

    /**
     * The default value of the '{@link #getGatewayDirection() <em>Gateway Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGatewayDirection()
     * @generated
     * @ordered
     */
    protected static final TGatewayDirection GATEWAY_DIRECTION_EDEFAULT = TGatewayDirection.UNSPECIFIED;

    /**
     * The cached value of the '{@link #getGatewayDirection() <em>Gateway Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGatewayDirection()
     * @generated
     * @ordered
     */
    protected TGatewayDirection gatewayDirection = GATEWAY_DIRECTION_EDEFAULT;

    /**
     * This is true if the Gateway Direction attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean gatewayDirectionESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TGatewayImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TGATEWAY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TGatewayDirection getGatewayDirection() {
        return gatewayDirection;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setGatewayDirection(TGatewayDirection newGatewayDirection) {
        TGatewayDirection oldGatewayDirection = gatewayDirection;
        gatewayDirection = newGatewayDirection == null ? GATEWAY_DIRECTION_EDEFAULT : newGatewayDirection;
        boolean oldGatewayDirectionESet = gatewayDirectionESet;
        gatewayDirectionESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TGATEWAY__GATEWAY_DIRECTION,
                    oldGatewayDirection, gatewayDirection, !oldGatewayDirectionESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetGatewayDirection() {
        TGatewayDirection oldGatewayDirection = gatewayDirection;
        boolean oldGatewayDirectionESet = gatewayDirectionESet;
        gatewayDirection = GATEWAY_DIRECTION_EDEFAULT;
        gatewayDirectionESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TGATEWAY__GATEWAY_DIRECTION,
                    oldGatewayDirection, GATEWAY_DIRECTION_EDEFAULT, oldGatewayDirectionESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetGatewayDirection() {
        return gatewayDirectionESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ModelPackage.TGATEWAY__GATEWAY_DIRECTION:
                return getGatewayDirection();
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
            case ModelPackage.TGATEWAY__GATEWAY_DIRECTION:
                setGatewayDirection((TGatewayDirection) newValue);
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
            case ModelPackage.TGATEWAY__GATEWAY_DIRECTION:
                unsetGatewayDirection();
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
            case ModelPackage.TGATEWAY__GATEWAY_DIRECTION:
                return isSetGatewayDirection();
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
        result.append(" (gatewayDirection: "); //$NON-NLS-1$
        if (gatewayDirectionESet)
            result.append(gatewayDirection);
        else
            result.append("<unset>"); //$NON-NLS-1$
        result.append(')');
        return result.toString();
    }

} //TGatewayImpl
