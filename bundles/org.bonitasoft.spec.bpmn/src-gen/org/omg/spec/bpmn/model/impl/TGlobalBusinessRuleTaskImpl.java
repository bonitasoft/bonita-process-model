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

import org.omg.spec.bpmn.model.ModelFactory;
import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TGlobalBusinessRuleTask;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TGlobal Business Rule Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TGlobalBusinessRuleTaskImpl#getImplementation <em>Implementation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TGlobalBusinessRuleTaskImpl extends TGlobalTaskImpl implements TGlobalBusinessRuleTask {

    /**
     * The default value of the '{@link #getImplementation() <em>Implementation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getImplementation()
     * @generated
     * @ordered
     */
    protected static final Object IMPLEMENTATION_EDEFAULT = ModelFactory.eINSTANCE
            .createFromString(ModelPackage.eINSTANCE.getTImplementation(), "##unspecified"); //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getImplementation() <em>Implementation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getImplementation()
     * @generated
     * @ordered
     */
    protected Object implementation = IMPLEMENTATION_EDEFAULT;

    /**
     * This is true if the Implementation attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean implementationESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TGlobalBusinessRuleTaskImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TGLOBAL_BUSINESS_RULE_TASK;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImplementation() {
        return implementation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setImplementation(Object newImplementation) {
        Object oldImplementation = implementation;
        implementation = newImplementation;
        boolean oldImplementationESet = implementationESet;
        implementationESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TGLOBAL_BUSINESS_RULE_TASK__IMPLEMENTATION, oldImplementation, implementation,
                    !oldImplementationESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetImplementation() {
        Object oldImplementation = implementation;
        boolean oldImplementationESet = implementationESet;
        implementation = IMPLEMENTATION_EDEFAULT;
        implementationESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET,
                    ModelPackage.TGLOBAL_BUSINESS_RULE_TASK__IMPLEMENTATION, oldImplementation, IMPLEMENTATION_EDEFAULT,
                    oldImplementationESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetImplementation() {
        return implementationESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ModelPackage.TGLOBAL_BUSINESS_RULE_TASK__IMPLEMENTATION:
                return getImplementation();
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
            case ModelPackage.TGLOBAL_BUSINESS_RULE_TASK__IMPLEMENTATION:
                setImplementation(newValue);
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
            case ModelPackage.TGLOBAL_BUSINESS_RULE_TASK__IMPLEMENTATION:
                unsetImplementation();
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
            case ModelPackage.TGLOBAL_BUSINESS_RULE_TASK__IMPLEMENTATION:
                return isSetImplementation();
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
        result.append(" (implementation: "); //$NON-NLS-1$
        if (implementationESet)
            result.append(implementation);
        else
            result.append("<unset>"); //$NON-NLS-1$
        result.append(')');
        return result.toString();
    }

} //TGlobalBusinessRuleTaskImpl
