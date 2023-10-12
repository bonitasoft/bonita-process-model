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
import org.omg.spec.bpmn.model.TResourceParameter;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TResource Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TResourceParameterImpl#isIsRequired <em>Is Required</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TResourceParameterImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TResourceParameterImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TResourceParameterImpl extends TBaseElementImpl implements TResourceParameter {

    /**
     * The default value of the '{@link #isIsRequired() <em>Is Required</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsRequired()
     * @generated
     * @ordered
     */
    protected static final boolean IS_REQUIRED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsRequired() <em>Is Required</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsRequired()
     * @generated
     * @ordered
     */
    protected boolean isRequired = IS_REQUIRED_EDEFAULT;

    /**
     * This is true if the Is Required attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean isRequiredESet;

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
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final QName TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected QName type = TYPE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TResourceParameterImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TRESOURCE_PARAMETER;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isIsRequired() {
        return isRequired;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setIsRequired(boolean newIsRequired) {
        boolean oldIsRequired = isRequired;
        isRequired = newIsRequired;
        boolean oldIsRequiredESet = isRequiredESet;
        isRequiredESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TRESOURCE_PARAMETER__IS_REQUIRED,
                    oldIsRequired, isRequired, !oldIsRequiredESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetIsRequired() {
        boolean oldIsRequired = isRequired;
        boolean oldIsRequiredESet = isRequiredESet;
        isRequired = IS_REQUIRED_EDEFAULT;
        isRequiredESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TRESOURCE_PARAMETER__IS_REQUIRED,
                    oldIsRequired, IS_REQUIRED_EDEFAULT, oldIsRequiredESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetIsRequired() {
        return isRequiredESet;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TRESOURCE_PARAMETER__NAME, oldName,
                    name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setType(QName newType) {
        QName oldType = type;
        type = newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TRESOURCE_PARAMETER__TYPE, oldType,
                    type));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ModelPackage.TRESOURCE_PARAMETER__IS_REQUIRED:
                return isIsRequired();
            case ModelPackage.TRESOURCE_PARAMETER__NAME:
                return getName();
            case ModelPackage.TRESOURCE_PARAMETER__TYPE:
                return getType();
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
            case ModelPackage.TRESOURCE_PARAMETER__IS_REQUIRED:
                setIsRequired((Boolean) newValue);
                return;
            case ModelPackage.TRESOURCE_PARAMETER__NAME:
                setName((String) newValue);
                return;
            case ModelPackage.TRESOURCE_PARAMETER__TYPE:
                setType((QName) newValue);
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
            case ModelPackage.TRESOURCE_PARAMETER__IS_REQUIRED:
                unsetIsRequired();
                return;
            case ModelPackage.TRESOURCE_PARAMETER__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ModelPackage.TRESOURCE_PARAMETER__TYPE:
                setType(TYPE_EDEFAULT);
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
            case ModelPackage.TRESOURCE_PARAMETER__IS_REQUIRED:
                return isSetIsRequired();
            case ModelPackage.TRESOURCE_PARAMETER__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ModelPackage.TRESOURCE_PARAMETER__TYPE:
                return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
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
        result.append(" (isRequired: "); //$NON-NLS-1$
        if (isRequiredESet)
            result.append(isRequired);
        else
            result.append("<unset>"); //$NON-NLS-1$
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", type: "); //$NON-NLS-1$
        result.append(type);
        result.append(')');
        return result.toString();
    }

} //TResourceParameterImpl
