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

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TGroup;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TGroup</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TGroupImpl#getCategoryValueRef <em>Category Value Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TGroupImpl extends TArtifactImpl implements TGroup {

    /**
     * The default value of the '{@link #getCategoryValueRef() <em>Category Value Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCategoryValueRef()
     * @generated
     * @ordered
     */
    protected static final QName CATEGORY_VALUE_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCategoryValueRef() <em>Category Value Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCategoryValueRef()
     * @generated
     * @ordered
     */
    protected QName categoryValueRef = CATEGORY_VALUE_REF_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TGroupImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TGROUP;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getCategoryValueRef() {
        return categoryValueRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCategoryValueRef(QName newCategoryValueRef) {
        QName oldCategoryValueRef = categoryValueRef;
        categoryValueRef = newCategoryValueRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TGROUP__CATEGORY_VALUE_REF,
                    oldCategoryValueRef, categoryValueRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ModelPackage.TGROUP__CATEGORY_VALUE_REF:
                return getCategoryValueRef();
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
            case ModelPackage.TGROUP__CATEGORY_VALUE_REF:
                setCategoryValueRef((QName) newValue);
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
            case ModelPackage.TGROUP__CATEGORY_VALUE_REF:
                setCategoryValueRef(CATEGORY_VALUE_REF_EDEFAULT);
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
            case ModelPackage.TGROUP__CATEGORY_VALUE_REF:
                return CATEGORY_VALUE_REF_EDEFAULT == null ? categoryValueRef != null
                        : !CATEGORY_VALUE_REF_EDEFAULT.equals(categoryValueRef);
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
        result.append(')');
        return result.toString();
    }

} //TGroupImpl
