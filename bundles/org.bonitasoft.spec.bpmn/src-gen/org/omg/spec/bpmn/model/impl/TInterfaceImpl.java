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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TInterface;
import org.omg.spec.bpmn.model.TOperation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TInterface</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TInterfaceImpl#getOperation <em>Operation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TInterfaceImpl#getImplementationRef <em>Implementation Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TInterfaceImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TInterfaceImpl extends TRootElementImpl implements TInterface {

    /**
     * The cached value of the '{@link #getOperation() <em>Operation</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOperation()
     * @generated
     * @ordered
     */
    protected EList<TOperation> operation;

    /**
     * The default value of the '{@link #getImplementationRef() <em>Implementation Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getImplementationRef()
     * @generated
     * @ordered
     */
    protected static final QName IMPLEMENTATION_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getImplementationRef() <em>Implementation Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getImplementationRef()
     * @generated
     * @ordered
     */
    protected QName implementationRef = IMPLEMENTATION_REF_EDEFAULT;

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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TInterfaceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TINTERFACE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TOperation> getOperation() {
        if (operation == null) {
            operation = new EObjectContainmentEList<TOperation>(TOperation.class, this,
                    ModelPackage.TINTERFACE__OPERATION);
        }
        return operation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getImplementationRef() {
        return implementationRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setImplementationRef(QName newImplementationRef) {
        QName oldImplementationRef = implementationRef;
        implementationRef = newImplementationRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TINTERFACE__IMPLEMENTATION_REF,
                    oldImplementationRef, implementationRef));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TINTERFACE__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ModelPackage.TINTERFACE__OPERATION:
                return ((InternalEList<?>) getOperation()).basicRemove(otherEnd, msgs);
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
            case ModelPackage.TINTERFACE__OPERATION:
                return getOperation();
            case ModelPackage.TINTERFACE__IMPLEMENTATION_REF:
                return getImplementationRef();
            case ModelPackage.TINTERFACE__NAME:
                return getName();
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
            case ModelPackage.TINTERFACE__OPERATION:
                getOperation().clear();
                getOperation().addAll((Collection<? extends TOperation>) newValue);
                return;
            case ModelPackage.TINTERFACE__IMPLEMENTATION_REF:
                setImplementationRef((QName) newValue);
                return;
            case ModelPackage.TINTERFACE__NAME:
                setName((String) newValue);
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
            case ModelPackage.TINTERFACE__OPERATION:
                getOperation().clear();
                return;
            case ModelPackage.TINTERFACE__IMPLEMENTATION_REF:
                setImplementationRef(IMPLEMENTATION_REF_EDEFAULT);
                return;
            case ModelPackage.TINTERFACE__NAME:
                setName(NAME_EDEFAULT);
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
            case ModelPackage.TINTERFACE__OPERATION:
                return operation != null && !operation.isEmpty();
            case ModelPackage.TINTERFACE__IMPLEMENTATION_REF:
                return IMPLEMENTATION_REF_EDEFAULT == null ? implementationRef != null
                        : !IMPLEMENTATION_REF_EDEFAULT.equals(implementationRef);
            case ModelPackage.TINTERFACE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
        result.append(" (implementationRef: "); //$NON-NLS-1$
        result.append(implementationRef);
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(')');
        return result.toString();
    }

} //TInterfaceImpl
