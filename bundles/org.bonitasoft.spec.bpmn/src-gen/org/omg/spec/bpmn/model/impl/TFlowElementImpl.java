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
import org.omg.spec.bpmn.model.TAuditing;
import org.omg.spec.bpmn.model.TFlowElement;
import org.omg.spec.bpmn.model.TMonitoring;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TFlow Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TFlowElementImpl#getAuditing <em>Auditing</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TFlowElementImpl#getMonitoring <em>Monitoring</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TFlowElementImpl#getCategoryValueRef <em>Category Value Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TFlowElementImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class TFlowElementImpl extends TBaseElementImpl implements TFlowElement {

    /**
     * The cached value of the '{@link #getAuditing() <em>Auditing</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAuditing()
     * @generated
     * @ordered
     */
    protected TAuditing auditing;

    /**
     * The cached value of the '{@link #getMonitoring() <em>Monitoring</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMonitoring()
     * @generated
     * @ordered
     */
    protected TMonitoring monitoring;

    /**
     * The cached value of the '{@link #getCategoryValueRef() <em>Category Value Ref</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCategoryValueRef()
     * @generated
     * @ordered
     */
    protected EList<QName> categoryValueRef;

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
    protected TFlowElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TFLOW_ELEMENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TAuditing getAuditing() {
        return auditing;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAuditing(TAuditing newAuditing, NotificationChain msgs) {
        TAuditing oldAuditing = auditing;
        auditing = newAuditing;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TFLOW_ELEMENT__AUDITING, oldAuditing, newAuditing);
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
    public void setAuditing(TAuditing newAuditing) {
        if (newAuditing != auditing) {
            NotificationChain msgs = null;
            if (auditing != null)
                msgs = ((InternalEObject) auditing).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ModelPackage.TFLOW_ELEMENT__AUDITING, null, msgs);
            if (newAuditing != null)
                msgs = ((InternalEObject) newAuditing).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ModelPackage.TFLOW_ELEMENT__AUDITING, null, msgs);
            msgs = basicSetAuditing(newAuditing, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TFLOW_ELEMENT__AUDITING, newAuditing,
                    newAuditing));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TMonitoring getMonitoring() {
        return monitoring;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetMonitoring(TMonitoring newMonitoring, NotificationChain msgs) {
        TMonitoring oldMonitoring = monitoring;
        monitoring = newMonitoring;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TFLOW_ELEMENT__MONITORING, oldMonitoring, newMonitoring);
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
    public void setMonitoring(TMonitoring newMonitoring) {
        if (newMonitoring != monitoring) {
            NotificationChain msgs = null;
            if (monitoring != null)
                msgs = ((InternalEObject) monitoring).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ModelPackage.TFLOW_ELEMENT__MONITORING, null, msgs);
            if (newMonitoring != null)
                msgs = ((InternalEObject) newMonitoring).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ModelPackage.TFLOW_ELEMENT__MONITORING, null, msgs);
            msgs = basicSetMonitoring(newMonitoring, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TFLOW_ELEMENT__MONITORING, newMonitoring,
                    newMonitoring));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<QName> getCategoryValueRef() {
        if (categoryValueRef == null) {
            categoryValueRef = new EDataTypeEList<QName>(QName.class, this,
                    ModelPackage.TFLOW_ELEMENT__CATEGORY_VALUE_REF);
        }
        return categoryValueRef;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TFLOW_ELEMENT__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ModelPackage.TFLOW_ELEMENT__AUDITING:
                return basicSetAuditing(null, msgs);
            case ModelPackage.TFLOW_ELEMENT__MONITORING:
                return basicSetMonitoring(null, msgs);
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
            case ModelPackage.TFLOW_ELEMENT__AUDITING:
                return getAuditing();
            case ModelPackage.TFLOW_ELEMENT__MONITORING:
                return getMonitoring();
            case ModelPackage.TFLOW_ELEMENT__CATEGORY_VALUE_REF:
                return getCategoryValueRef();
            case ModelPackage.TFLOW_ELEMENT__NAME:
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
            case ModelPackage.TFLOW_ELEMENT__AUDITING:
                setAuditing((TAuditing) newValue);
                return;
            case ModelPackage.TFLOW_ELEMENT__MONITORING:
                setMonitoring((TMonitoring) newValue);
                return;
            case ModelPackage.TFLOW_ELEMENT__CATEGORY_VALUE_REF:
                getCategoryValueRef().clear();
                getCategoryValueRef().addAll((Collection<? extends QName>) newValue);
                return;
            case ModelPackage.TFLOW_ELEMENT__NAME:
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
            case ModelPackage.TFLOW_ELEMENT__AUDITING:
                setAuditing((TAuditing) null);
                return;
            case ModelPackage.TFLOW_ELEMENT__MONITORING:
                setMonitoring((TMonitoring) null);
                return;
            case ModelPackage.TFLOW_ELEMENT__CATEGORY_VALUE_REF:
                getCategoryValueRef().clear();
                return;
            case ModelPackage.TFLOW_ELEMENT__NAME:
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
            case ModelPackage.TFLOW_ELEMENT__AUDITING:
                return auditing != null;
            case ModelPackage.TFLOW_ELEMENT__MONITORING:
                return monitoring != null;
            case ModelPackage.TFLOW_ELEMENT__CATEGORY_VALUE_REF:
                return categoryValueRef != null && !categoryValueRef.isEmpty();
            case ModelPackage.TFLOW_ELEMENT__NAME:
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
        result.append(" (categoryValueRef: "); //$NON-NLS-1$
        result.append(categoryValueRef);
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(')');
        return result.toString();
    }

} //TFlowElementImpl
