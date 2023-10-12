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

import javax.xml.namespace.QName;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EDataTypeEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TChoreographyTask;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TChoreography Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TChoreographyTaskImpl#getMessageFlowRef <em>Message Flow Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TChoreographyTaskImpl extends TChoreographyActivityImpl implements TChoreographyTask {

    /**
     * The cached value of the '{@link #getMessageFlowRef() <em>Message Flow Ref</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMessageFlowRef()
     * @generated
     * @ordered
     */
    protected EList<QName> messageFlowRef;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TChoreographyTaskImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TCHOREOGRAPHY_TASK;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<QName> getMessageFlowRef() {
        if (messageFlowRef == null) {
            messageFlowRef = new EDataTypeEList<QName>(QName.class, this,
                    ModelPackage.TCHOREOGRAPHY_TASK__MESSAGE_FLOW_REF);
        }
        return messageFlowRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ModelPackage.TCHOREOGRAPHY_TASK__MESSAGE_FLOW_REF:
                return getMessageFlowRef();
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
            case ModelPackage.TCHOREOGRAPHY_TASK__MESSAGE_FLOW_REF:
                getMessageFlowRef().clear();
                getMessageFlowRef().addAll((Collection<? extends QName>) newValue);
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
            case ModelPackage.TCHOREOGRAPHY_TASK__MESSAGE_FLOW_REF:
                getMessageFlowRef().clear();
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
            case ModelPackage.TCHOREOGRAPHY_TASK__MESSAGE_FLOW_REF:
                return messageFlowRef != null && !messageFlowRef.isEmpty();
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
        result.append(" (messageFlowRef: "); //$NON-NLS-1$
        result.append(messageFlowRef);
        result.append(')');
        return result.toString();
    }

} //TChoreographyTaskImpl
