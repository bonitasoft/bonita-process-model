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

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TGlobalTask;
import org.omg.spec.bpmn.model.TResourceRole;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TGlobal Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TGlobalTaskImpl#getResourceRoleGroup <em>Resource Role Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TGlobalTaskImpl#getResourceRole <em>Resource Role</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TGlobalTaskImpl extends TCallableElementImpl implements TGlobalTask {

    /**
     * The cached value of the '{@link #getResourceRoleGroup() <em>Resource Role Group</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResourceRoleGroup()
     * @generated
     * @ordered
     */
    protected FeatureMap resourceRoleGroup;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TGlobalTaskImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TGLOBAL_TASK;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FeatureMap getResourceRoleGroup() {
        if (resourceRoleGroup == null) {
            resourceRoleGroup = new BasicFeatureMap(this, ModelPackage.TGLOBAL_TASK__RESOURCE_ROLE_GROUP);
        }
        return resourceRoleGroup;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TResourceRole> getResourceRole() {
        return getResourceRoleGroup().list(ModelPackage.Literals.TGLOBAL_TASK__RESOURCE_ROLE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ModelPackage.TGLOBAL_TASK__RESOURCE_ROLE_GROUP:
                return ((InternalEList<?>) getResourceRoleGroup()).basicRemove(otherEnd, msgs);
            case ModelPackage.TGLOBAL_TASK__RESOURCE_ROLE:
                return ((InternalEList<?>) getResourceRole()).basicRemove(otherEnd, msgs);
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
            case ModelPackage.TGLOBAL_TASK__RESOURCE_ROLE_GROUP:
                if (coreType)
                    return getResourceRoleGroup();
                return ((FeatureMap.Internal) getResourceRoleGroup()).getWrapper();
            case ModelPackage.TGLOBAL_TASK__RESOURCE_ROLE:
                return getResourceRole();
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
            case ModelPackage.TGLOBAL_TASK__RESOURCE_ROLE_GROUP:
                ((FeatureMap.Internal) getResourceRoleGroup()).set(newValue);
                return;
            case ModelPackage.TGLOBAL_TASK__RESOURCE_ROLE:
                getResourceRole().clear();
                getResourceRole().addAll((Collection<? extends TResourceRole>) newValue);
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
            case ModelPackage.TGLOBAL_TASK__RESOURCE_ROLE_GROUP:
                getResourceRoleGroup().clear();
                return;
            case ModelPackage.TGLOBAL_TASK__RESOURCE_ROLE:
                getResourceRole().clear();
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
            case ModelPackage.TGLOBAL_TASK__RESOURCE_ROLE_GROUP:
                return resourceRoleGroup != null && !resourceRoleGroup.isEmpty();
            case ModelPackage.TGLOBAL_TASK__RESOURCE_ROLE:
                return !getResourceRole().isEmpty();
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
        result.append(" (resourceRoleGroup: "); //$NON-NLS-1$
        result.append(resourceRoleGroup);
        result.append(')');
        return result.toString();
    }

} //TGlobalTaskImpl
