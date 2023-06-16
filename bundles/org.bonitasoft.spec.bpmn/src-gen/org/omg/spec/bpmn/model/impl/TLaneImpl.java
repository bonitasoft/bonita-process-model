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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TBaseElement;
import org.omg.spec.bpmn.model.TLane;
import org.omg.spec.bpmn.model.TLaneSet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TLane</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TLaneImpl#getPartitionElement <em>Partition Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TLaneImpl#getFlowNodeRef <em>Flow Node Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TLaneImpl#getChildLaneSet <em>Child Lane Set</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TLaneImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TLaneImpl#getPartitionElementRef <em>Partition Element Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TLaneImpl extends TBaseElementImpl implements TLane {

    /**
     * The cached value of the '{@link #getPartitionElement() <em>Partition Element</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPartitionElement()
     * @generated
     * @ordered
     */
    protected TBaseElement partitionElement;

    /**
     * The cached value of the '{@link #getFlowNodeRef() <em>Flow Node Ref</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFlowNodeRef()
     * @generated
     * @ordered
     */
    protected EList<String> flowNodeRef;

    /**
     * The cached value of the '{@link #getChildLaneSet() <em>Child Lane Set</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChildLaneSet()
     * @generated
     * @ordered
     */
    protected TLaneSet childLaneSet;

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
     * The default value of the '{@link #getPartitionElementRef() <em>Partition Element Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPartitionElementRef()
     * @generated
     * @ordered
     */
    protected static final QName PARTITION_ELEMENT_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPartitionElementRef() <em>Partition Element Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPartitionElementRef()
     * @generated
     * @ordered
     */
    protected QName partitionElementRef = PARTITION_ELEMENT_REF_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TLaneImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TLANE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TBaseElement getPartitionElement() {
        return partitionElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPartitionElement(TBaseElement newPartitionElement, NotificationChain msgs) {
        TBaseElement oldPartitionElement = partitionElement;
        partitionElement = newPartitionElement;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TLANE__PARTITION_ELEMENT, oldPartitionElement, newPartitionElement);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setPartitionElement(TBaseElement newPartitionElement) {
        if (newPartitionElement != partitionElement) {
            NotificationChain msgs = null;
            if (partitionElement != null)
                msgs = ((InternalEObject) partitionElement).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ModelPackage.TLANE__PARTITION_ELEMENT, null, msgs);
            if (newPartitionElement != null)
                msgs = ((InternalEObject) newPartitionElement).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ModelPackage.TLANE__PARTITION_ELEMENT, null, msgs);
            msgs = basicSetPartitionElement(newPartitionElement, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TLANE__PARTITION_ELEMENT,
                    newPartitionElement, newPartitionElement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<String> getFlowNodeRef() {
        if (flowNodeRef == null) {
            flowNodeRef = new EDataTypeEList<String>(String.class, this, ModelPackage.TLANE__FLOW_NODE_REF);
        }
        return flowNodeRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TLaneSet getChildLaneSet() {
        return childLaneSet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetChildLaneSet(TLaneSet newChildLaneSet, NotificationChain msgs) {
        TLaneSet oldChildLaneSet = childLaneSet;
        childLaneSet = newChildLaneSet;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TLANE__CHILD_LANE_SET, oldChildLaneSet, newChildLaneSet);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setChildLaneSet(TLaneSet newChildLaneSet) {
        if (newChildLaneSet != childLaneSet) {
            NotificationChain msgs = null;
            if (childLaneSet != null)
                msgs = ((InternalEObject) childLaneSet).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ModelPackage.TLANE__CHILD_LANE_SET, null, msgs);
            if (newChildLaneSet != null)
                msgs = ((InternalEObject) newChildLaneSet).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ModelPackage.TLANE__CHILD_LANE_SET, null, msgs);
            msgs = basicSetChildLaneSet(newChildLaneSet, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TLANE__CHILD_LANE_SET, newChildLaneSet,
                    newChildLaneSet));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TLANE__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getPartitionElementRef() {
        return partitionElementRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setPartitionElementRef(QName newPartitionElementRef) {
        QName oldPartitionElementRef = partitionElementRef;
        partitionElementRef = newPartitionElementRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TLANE__PARTITION_ELEMENT_REF,
                    oldPartitionElementRef, partitionElementRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ModelPackage.TLANE__PARTITION_ELEMENT:
                return basicSetPartitionElement(null, msgs);
            case ModelPackage.TLANE__CHILD_LANE_SET:
                return basicSetChildLaneSet(null, msgs);
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
            case ModelPackage.TLANE__PARTITION_ELEMENT:
                return getPartitionElement();
            case ModelPackage.TLANE__FLOW_NODE_REF:
                return getFlowNodeRef();
            case ModelPackage.TLANE__CHILD_LANE_SET:
                return getChildLaneSet();
            case ModelPackage.TLANE__NAME:
                return getName();
            case ModelPackage.TLANE__PARTITION_ELEMENT_REF:
                return getPartitionElementRef();
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
            case ModelPackage.TLANE__PARTITION_ELEMENT:
                setPartitionElement((TBaseElement) newValue);
                return;
            case ModelPackage.TLANE__FLOW_NODE_REF:
                getFlowNodeRef().clear();
                getFlowNodeRef().addAll((Collection<? extends String>) newValue);
                return;
            case ModelPackage.TLANE__CHILD_LANE_SET:
                setChildLaneSet((TLaneSet) newValue);
                return;
            case ModelPackage.TLANE__NAME:
                setName((String) newValue);
                return;
            case ModelPackage.TLANE__PARTITION_ELEMENT_REF:
                setPartitionElementRef((QName) newValue);
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
            case ModelPackage.TLANE__PARTITION_ELEMENT:
                setPartitionElement((TBaseElement) null);
                return;
            case ModelPackage.TLANE__FLOW_NODE_REF:
                getFlowNodeRef().clear();
                return;
            case ModelPackage.TLANE__CHILD_LANE_SET:
                setChildLaneSet((TLaneSet) null);
                return;
            case ModelPackage.TLANE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ModelPackage.TLANE__PARTITION_ELEMENT_REF:
                setPartitionElementRef(PARTITION_ELEMENT_REF_EDEFAULT);
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
            case ModelPackage.TLANE__PARTITION_ELEMENT:
                return partitionElement != null;
            case ModelPackage.TLANE__FLOW_NODE_REF:
                return flowNodeRef != null && !flowNodeRef.isEmpty();
            case ModelPackage.TLANE__CHILD_LANE_SET:
                return childLaneSet != null;
            case ModelPackage.TLANE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ModelPackage.TLANE__PARTITION_ELEMENT_REF:
                return PARTITION_ELEMENT_REF_EDEFAULT == null ? partitionElementRef != null
                        : !PARTITION_ELEMENT_REF_EDEFAULT.equals(partitionElementRef);
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
        result.append(" (flowNodeRef: "); //$NON-NLS-1$
        result.append(flowNodeRef);
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", partitionElementRef: "); //$NON-NLS-1$
        result.append(partitionElementRef);
        result.append(')');
        return result.toString();
    }

} //TLaneImpl
