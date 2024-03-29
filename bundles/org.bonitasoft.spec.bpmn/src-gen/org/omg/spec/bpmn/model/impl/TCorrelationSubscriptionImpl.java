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
import org.omg.spec.bpmn.model.TCorrelationPropertyBinding;
import org.omg.spec.bpmn.model.TCorrelationSubscription;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TCorrelation Subscription</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCorrelationSubscriptionImpl#getCorrelationPropertyBinding <em>Correlation Property Binding</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCorrelationSubscriptionImpl#getCorrelationKeyRef <em>Correlation Key Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TCorrelationSubscriptionImpl extends TBaseElementImpl implements TCorrelationSubscription {

    /**
     * The cached value of the '{@link #getCorrelationPropertyBinding() <em>Correlation Property Binding</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCorrelationPropertyBinding()
     * @generated
     * @ordered
     */
    protected EList<TCorrelationPropertyBinding> correlationPropertyBinding;

    /**
     * The default value of the '{@link #getCorrelationKeyRef() <em>Correlation Key Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCorrelationKeyRef()
     * @generated
     * @ordered
     */
    protected static final QName CORRELATION_KEY_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCorrelationKeyRef() <em>Correlation Key Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCorrelationKeyRef()
     * @generated
     * @ordered
     */
    protected QName correlationKeyRef = CORRELATION_KEY_REF_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TCorrelationSubscriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TCORRELATION_SUBSCRIPTION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TCorrelationPropertyBinding> getCorrelationPropertyBinding() {
        if (correlationPropertyBinding == null) {
            correlationPropertyBinding = new EObjectContainmentEList<TCorrelationPropertyBinding>(
                    TCorrelationPropertyBinding.class, this,
                    ModelPackage.TCORRELATION_SUBSCRIPTION__CORRELATION_PROPERTY_BINDING);
        }
        return correlationPropertyBinding;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getCorrelationKeyRef() {
        return correlationKeyRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCorrelationKeyRef(QName newCorrelationKeyRef) {
        QName oldCorrelationKeyRef = correlationKeyRef;
        correlationKeyRef = newCorrelationKeyRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TCORRELATION_SUBSCRIPTION__CORRELATION_KEY_REF, oldCorrelationKeyRef,
                    correlationKeyRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ModelPackage.TCORRELATION_SUBSCRIPTION__CORRELATION_PROPERTY_BINDING:
                return ((InternalEList<?>) getCorrelationPropertyBinding()).basicRemove(otherEnd, msgs);
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
            case ModelPackage.TCORRELATION_SUBSCRIPTION__CORRELATION_PROPERTY_BINDING:
                return getCorrelationPropertyBinding();
            case ModelPackage.TCORRELATION_SUBSCRIPTION__CORRELATION_KEY_REF:
                return getCorrelationKeyRef();
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
            case ModelPackage.TCORRELATION_SUBSCRIPTION__CORRELATION_PROPERTY_BINDING:
                getCorrelationPropertyBinding().clear();
                getCorrelationPropertyBinding().addAll((Collection<? extends TCorrelationPropertyBinding>) newValue);
                return;
            case ModelPackage.TCORRELATION_SUBSCRIPTION__CORRELATION_KEY_REF:
                setCorrelationKeyRef((QName) newValue);
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
            case ModelPackage.TCORRELATION_SUBSCRIPTION__CORRELATION_PROPERTY_BINDING:
                getCorrelationPropertyBinding().clear();
                return;
            case ModelPackage.TCORRELATION_SUBSCRIPTION__CORRELATION_KEY_REF:
                setCorrelationKeyRef(CORRELATION_KEY_REF_EDEFAULT);
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
            case ModelPackage.TCORRELATION_SUBSCRIPTION__CORRELATION_PROPERTY_BINDING:
                return correlationPropertyBinding != null && !correlationPropertyBinding.isEmpty();
            case ModelPackage.TCORRELATION_SUBSCRIPTION__CORRELATION_KEY_REF:
                return CORRELATION_KEY_REF_EDEFAULT == null ? correlationKeyRef != null
                        : !CORRELATION_KEY_REF_EDEFAULT.equals(correlationKeyRef);
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
        result.append(" (correlationKeyRef: "); //$NON-NLS-1$
        result.append(correlationKeyRef);
        result.append(')');
        return result.toString();
    }

} //TCorrelationSubscriptionImpl
