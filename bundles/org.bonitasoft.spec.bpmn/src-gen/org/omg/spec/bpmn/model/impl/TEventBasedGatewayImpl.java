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
import org.omg.spec.bpmn.model.TEventBasedGateway;
import org.omg.spec.bpmn.model.TEventBasedGatewayType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TEvent Based Gateway</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TEventBasedGatewayImpl#getEventGatewayType <em>Event Gateway Type</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TEventBasedGatewayImpl#isInstantiate <em>Instantiate</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TEventBasedGatewayImpl extends TGatewayImpl implements TEventBasedGateway {

    /**
     * The default value of the '{@link #getEventGatewayType() <em>Event Gateway Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEventGatewayType()
     * @generated
     * @ordered
     */
    protected static final TEventBasedGatewayType EVENT_GATEWAY_TYPE_EDEFAULT = TEventBasedGatewayType.EXCLUSIVE;

    /**
     * The cached value of the '{@link #getEventGatewayType() <em>Event Gateway Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEventGatewayType()
     * @generated
     * @ordered
     */
    protected TEventBasedGatewayType eventGatewayType = EVENT_GATEWAY_TYPE_EDEFAULT;

    /**
     * This is true if the Event Gateway Type attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean eventGatewayTypeESet;

    /**
     * The default value of the '{@link #isInstantiate() <em>Instantiate</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isInstantiate()
     * @generated
     * @ordered
     */
    protected static final boolean INSTANTIATE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isInstantiate() <em>Instantiate</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isInstantiate()
     * @generated
     * @ordered
     */
    protected boolean instantiate = INSTANTIATE_EDEFAULT;

    /**
     * This is true if the Instantiate attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean instantiateESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TEventBasedGatewayImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TEVENT_BASED_GATEWAY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TEventBasedGatewayType getEventGatewayType() {
        return eventGatewayType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setEventGatewayType(TEventBasedGatewayType newEventGatewayType) {
        TEventBasedGatewayType oldEventGatewayType = eventGatewayType;
        eventGatewayType = newEventGatewayType == null ? EVENT_GATEWAY_TYPE_EDEFAULT : newEventGatewayType;
        boolean oldEventGatewayTypeESet = eventGatewayTypeESet;
        eventGatewayTypeESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TEVENT_BASED_GATEWAY__EVENT_GATEWAY_TYPE,
                    oldEventGatewayType, eventGatewayType, !oldEventGatewayTypeESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetEventGatewayType() {
        TEventBasedGatewayType oldEventGatewayType = eventGatewayType;
        boolean oldEventGatewayTypeESet = eventGatewayTypeESet;
        eventGatewayType = EVENT_GATEWAY_TYPE_EDEFAULT;
        eventGatewayTypeESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET,
                    ModelPackage.TEVENT_BASED_GATEWAY__EVENT_GATEWAY_TYPE, oldEventGatewayType,
                    EVENT_GATEWAY_TYPE_EDEFAULT, oldEventGatewayTypeESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetEventGatewayType() {
        return eventGatewayTypeESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isInstantiate() {
        return instantiate;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setInstantiate(boolean newInstantiate) {
        boolean oldInstantiate = instantiate;
        instantiate = newInstantiate;
        boolean oldInstantiateESet = instantiateESet;
        instantiateESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TEVENT_BASED_GATEWAY__INSTANTIATE,
                    oldInstantiate, instantiate, !oldInstantiateESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetInstantiate() {
        boolean oldInstantiate = instantiate;
        boolean oldInstantiateESet = instantiateESet;
        instantiate = INSTANTIATE_EDEFAULT;
        instantiateESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TEVENT_BASED_GATEWAY__INSTANTIATE,
                    oldInstantiate, INSTANTIATE_EDEFAULT, oldInstantiateESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetInstantiate() {
        return instantiateESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ModelPackage.TEVENT_BASED_GATEWAY__EVENT_GATEWAY_TYPE:
                return getEventGatewayType();
            case ModelPackage.TEVENT_BASED_GATEWAY__INSTANTIATE:
                return isInstantiate();
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
            case ModelPackage.TEVENT_BASED_GATEWAY__EVENT_GATEWAY_TYPE:
                setEventGatewayType((TEventBasedGatewayType) newValue);
                return;
            case ModelPackage.TEVENT_BASED_GATEWAY__INSTANTIATE:
                setInstantiate((Boolean) newValue);
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
            case ModelPackage.TEVENT_BASED_GATEWAY__EVENT_GATEWAY_TYPE:
                unsetEventGatewayType();
                return;
            case ModelPackage.TEVENT_BASED_GATEWAY__INSTANTIATE:
                unsetInstantiate();
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
            case ModelPackage.TEVENT_BASED_GATEWAY__EVENT_GATEWAY_TYPE:
                return isSetEventGatewayType();
            case ModelPackage.TEVENT_BASED_GATEWAY__INSTANTIATE:
                return isSetInstantiate();
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
        result.append(" (eventGatewayType: "); //$NON-NLS-1$
        if (eventGatewayTypeESet)
            result.append(eventGatewayType);
        else
            result.append("<unset>"); //$NON-NLS-1$
        result.append(", instantiate: "); //$NON-NLS-1$
        if (instantiateESet)
            result.append(instantiate);
        else
            result.append("<unset>"); //$NON-NLS-1$
        result.append(')');
        return result.toString();
    }

} //TEventBasedGatewayImpl
