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
import org.omg.spec.bpmn.model.TFlowNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TFlow Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TFlowNodeImpl#getIncoming <em>Incoming</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TFlowNodeImpl#getOutgoing <em>Outgoing</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class TFlowNodeImpl extends TFlowElementImpl implements TFlowNode {

    /**
     * The cached value of the '{@link #getIncoming() <em>Incoming</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIncoming()
     * @generated
     * @ordered
     */
    protected EList<QName> incoming;

    /**
     * The cached value of the '{@link #getOutgoing() <em>Outgoing</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOutgoing()
     * @generated
     * @ordered
     */
    protected EList<QName> outgoing;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TFlowNodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TFLOW_NODE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<QName> getIncoming() {
        if (incoming == null) {
            incoming = new EDataTypeEList<QName>(QName.class, this, ModelPackage.TFLOW_NODE__INCOMING);
        }
        return incoming;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<QName> getOutgoing() {
        if (outgoing == null) {
            outgoing = new EDataTypeEList<QName>(QName.class, this, ModelPackage.TFLOW_NODE__OUTGOING);
        }
        return outgoing;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ModelPackage.TFLOW_NODE__INCOMING:
                return getIncoming();
            case ModelPackage.TFLOW_NODE__OUTGOING:
                return getOutgoing();
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
            case ModelPackage.TFLOW_NODE__INCOMING:
                getIncoming().clear();
                getIncoming().addAll((Collection<? extends QName>) newValue);
                return;
            case ModelPackage.TFLOW_NODE__OUTGOING:
                getOutgoing().clear();
                getOutgoing().addAll((Collection<? extends QName>) newValue);
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
            case ModelPackage.TFLOW_NODE__INCOMING:
                getIncoming().clear();
                return;
            case ModelPackage.TFLOW_NODE__OUTGOING:
                getOutgoing().clear();
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
            case ModelPackage.TFLOW_NODE__INCOMING:
                return incoming != null && !incoming.isEmpty();
            case ModelPackage.TFLOW_NODE__OUTGOING:
                return outgoing != null && !outgoing.isEmpty();
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
        result.append(" (incoming: "); //$NON-NLS-1$
        result.append(incoming);
        result.append(", outgoing: "); //$NON-NLS-1$
        result.append(outgoing);
        result.append(')');
        return result.toString();
    }

} //TFlowNodeImpl
