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

import javax.xml.namespace.QName;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.omg.spec.bpmn.model.ModelFactory;
import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TReceiveTask;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TReceive Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TReceiveTaskImpl#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TReceiveTaskImpl#isInstantiate <em>Instantiate</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TReceiveTaskImpl#getMessageRef <em>Message Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TReceiveTaskImpl#getOperationRef <em>Operation Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TReceiveTaskImpl extends TTaskImpl implements TReceiveTask {

    /**
     * The default value of the '{@link #getImplementation() <em>Implementation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getImplementation()
     * @generated
     * @ordered
     */
    protected static final Object IMPLEMENTATION_EDEFAULT = ModelFactory.eINSTANCE
            .createFromString(ModelPackage.eINSTANCE.getTImplementation(), "##WebService"); //$NON-NLS-1$

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
     * The default value of the '{@link #getMessageRef() <em>Message Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMessageRef()
     * @generated
     * @ordered
     */
    protected static final QName MESSAGE_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMessageRef() <em>Message Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMessageRef()
     * @generated
     * @ordered
     */
    protected QName messageRef = MESSAGE_REF_EDEFAULT;

    /**
     * The default value of the '{@link #getOperationRef() <em>Operation Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOperationRef()
     * @generated
     * @ordered
     */
    protected static final QName OPERATION_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getOperationRef() <em>Operation Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOperationRef()
     * @generated
     * @ordered
     */
    protected QName operationRef = OPERATION_REF_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TReceiveTaskImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TRECEIVE_TASK;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TRECEIVE_TASK__IMPLEMENTATION,
                    oldImplementation, implementation, !oldImplementationESet));
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
            eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TRECEIVE_TASK__IMPLEMENTATION,
                    oldImplementation, IMPLEMENTATION_EDEFAULT, oldImplementationESet));
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
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TRECEIVE_TASK__INSTANTIATE,
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
            eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TRECEIVE_TASK__INSTANTIATE,
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
    public QName getMessageRef() {
        return messageRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setMessageRef(QName newMessageRef) {
        QName oldMessageRef = messageRef;
        messageRef = newMessageRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TRECEIVE_TASK__MESSAGE_REF,
                    oldMessageRef, messageRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getOperationRef() {
        return operationRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setOperationRef(QName newOperationRef) {
        QName oldOperationRef = operationRef;
        operationRef = newOperationRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TRECEIVE_TASK__OPERATION_REF,
                    oldOperationRef, operationRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ModelPackage.TRECEIVE_TASK__IMPLEMENTATION:
                return getImplementation();
            case ModelPackage.TRECEIVE_TASK__INSTANTIATE:
                return isInstantiate();
            case ModelPackage.TRECEIVE_TASK__MESSAGE_REF:
                return getMessageRef();
            case ModelPackage.TRECEIVE_TASK__OPERATION_REF:
                return getOperationRef();
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
            case ModelPackage.TRECEIVE_TASK__IMPLEMENTATION:
                setImplementation(newValue);
                return;
            case ModelPackage.TRECEIVE_TASK__INSTANTIATE:
                setInstantiate((Boolean) newValue);
                return;
            case ModelPackage.TRECEIVE_TASK__MESSAGE_REF:
                setMessageRef((QName) newValue);
                return;
            case ModelPackage.TRECEIVE_TASK__OPERATION_REF:
                setOperationRef((QName) newValue);
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
            case ModelPackage.TRECEIVE_TASK__IMPLEMENTATION:
                unsetImplementation();
                return;
            case ModelPackage.TRECEIVE_TASK__INSTANTIATE:
                unsetInstantiate();
                return;
            case ModelPackage.TRECEIVE_TASK__MESSAGE_REF:
                setMessageRef(MESSAGE_REF_EDEFAULT);
                return;
            case ModelPackage.TRECEIVE_TASK__OPERATION_REF:
                setOperationRef(OPERATION_REF_EDEFAULT);
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
            case ModelPackage.TRECEIVE_TASK__IMPLEMENTATION:
                return isSetImplementation();
            case ModelPackage.TRECEIVE_TASK__INSTANTIATE:
                return isSetInstantiate();
            case ModelPackage.TRECEIVE_TASK__MESSAGE_REF:
                return MESSAGE_REF_EDEFAULT == null ? messageRef != null : !MESSAGE_REF_EDEFAULT.equals(messageRef);
            case ModelPackage.TRECEIVE_TASK__OPERATION_REF:
                return OPERATION_REF_EDEFAULT == null ? operationRef != null
                        : !OPERATION_REF_EDEFAULT.equals(operationRef);
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
        result.append(", instantiate: "); //$NON-NLS-1$
        if (instantiateESet)
            result.append(instantiate);
        else
            result.append("<unset>"); //$NON-NLS-1$
        result.append(", messageRef: "); //$NON-NLS-1$
        result.append(messageRef);
        result.append(", operationRef: "); //$NON-NLS-1$
        result.append(operationRef);
        result.append(')');
        return result.toString();
    }

} //TReceiveTaskImpl
