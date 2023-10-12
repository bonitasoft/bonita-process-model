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
import org.omg.spec.bpmn.model.TChoreography;
import org.omg.spec.bpmn.model.TFlowElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TChoreography</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TChoreographyImpl#getFlowElementGroup <em>Flow Element Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TChoreographyImpl#getFlowElement <em>Flow Element</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TChoreographyImpl extends TCollaborationImpl implements TChoreography {

    /**
     * The cached value of the '{@link #getFlowElementGroup() <em>Flow Element Group</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFlowElementGroup()
     * @generated
     * @ordered
     */
    protected FeatureMap flowElementGroup;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TChoreographyImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TCHOREOGRAPHY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FeatureMap getFlowElementGroup() {
        if (flowElementGroup == null) {
            flowElementGroup = new BasicFeatureMap(this, ModelPackage.TCHOREOGRAPHY__FLOW_ELEMENT_GROUP);
        }
        return flowElementGroup;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TFlowElement> getFlowElement() {
        return getFlowElementGroup().list(ModelPackage.Literals.TCHOREOGRAPHY__FLOW_ELEMENT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ModelPackage.TCHOREOGRAPHY__FLOW_ELEMENT_GROUP:
                return ((InternalEList<?>) getFlowElementGroup()).basicRemove(otherEnd, msgs);
            case ModelPackage.TCHOREOGRAPHY__FLOW_ELEMENT:
                return ((InternalEList<?>) getFlowElement()).basicRemove(otherEnd, msgs);
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
            case ModelPackage.TCHOREOGRAPHY__FLOW_ELEMENT_GROUP:
                if (coreType)
                    return getFlowElementGroup();
                return ((FeatureMap.Internal) getFlowElementGroup()).getWrapper();
            case ModelPackage.TCHOREOGRAPHY__FLOW_ELEMENT:
                return getFlowElement();
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
            case ModelPackage.TCHOREOGRAPHY__FLOW_ELEMENT_GROUP:
                ((FeatureMap.Internal) getFlowElementGroup()).set(newValue);
                return;
            case ModelPackage.TCHOREOGRAPHY__FLOW_ELEMENT:
                getFlowElement().clear();
                getFlowElement().addAll((Collection<? extends TFlowElement>) newValue);
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
            case ModelPackage.TCHOREOGRAPHY__FLOW_ELEMENT_GROUP:
                getFlowElementGroup().clear();
                return;
            case ModelPackage.TCHOREOGRAPHY__FLOW_ELEMENT:
                getFlowElement().clear();
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
            case ModelPackage.TCHOREOGRAPHY__FLOW_ELEMENT_GROUP:
                return flowElementGroup != null && !flowElementGroup.isEmpty();
            case ModelPackage.TCHOREOGRAPHY__FLOW_ELEMENT:
                return !getFlowElement().isEmpty();
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
        result.append(" (flowElementGroup: "); //$NON-NLS-1$
        result.append(flowElementGroup);
        result.append(')');
        return result.toString();
    }

} //TChoreographyImpl
