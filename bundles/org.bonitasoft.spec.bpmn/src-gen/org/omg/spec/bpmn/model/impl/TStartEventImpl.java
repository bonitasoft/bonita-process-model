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
import org.omg.spec.bpmn.model.TStartEvent;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TStart Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TStartEventImpl#isIsInterrupting <em>Is Interrupting</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TStartEventImpl extends TCatchEventImpl implements TStartEvent {

    /**
     * The default value of the '{@link #isIsInterrupting() <em>Is Interrupting</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsInterrupting()
     * @generated
     * @ordered
     */
    protected static final boolean IS_INTERRUPTING_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isIsInterrupting() <em>Is Interrupting</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsInterrupting()
     * @generated
     * @ordered
     */
    protected boolean isInterrupting = IS_INTERRUPTING_EDEFAULT;

    /**
     * This is true if the Is Interrupting attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean isInterruptingESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TStartEventImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TSTART_EVENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isIsInterrupting() {
        return isInterrupting;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setIsInterrupting(boolean newIsInterrupting) {
        boolean oldIsInterrupting = isInterrupting;
        isInterrupting = newIsInterrupting;
        boolean oldIsInterruptingESet = isInterruptingESet;
        isInterruptingESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TSTART_EVENT__IS_INTERRUPTING,
                    oldIsInterrupting, isInterrupting, !oldIsInterruptingESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetIsInterrupting() {
        boolean oldIsInterrupting = isInterrupting;
        boolean oldIsInterruptingESet = isInterruptingESet;
        isInterrupting = IS_INTERRUPTING_EDEFAULT;
        isInterruptingESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TSTART_EVENT__IS_INTERRUPTING,
                    oldIsInterrupting, IS_INTERRUPTING_EDEFAULT, oldIsInterruptingESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetIsInterrupting() {
        return isInterruptingESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ModelPackage.TSTART_EVENT__IS_INTERRUPTING:
                return isIsInterrupting();
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
            case ModelPackage.TSTART_EVENT__IS_INTERRUPTING:
                setIsInterrupting((Boolean) newValue);
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
            case ModelPackage.TSTART_EVENT__IS_INTERRUPTING:
                unsetIsInterrupting();
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
            case ModelPackage.TSTART_EVENT__IS_INTERRUPTING:
                return isSetIsInterrupting();
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
        result.append(" (isInterrupting: "); //$NON-NLS-1$
        if (isInterruptingESet)
            result.append(isInterrupting);
        else
            result.append("<unset>"); //$NON-NLS-1$
        result.append(')');
        return result.toString();
    }

} //TStartEventImpl
