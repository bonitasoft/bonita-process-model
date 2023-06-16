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

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TInputSet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TInput Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TInputSetImpl#getDataInputRefs <em>Data Input Refs</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TInputSetImpl#getOptionalInputRefs <em>Optional Input Refs</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TInputSetImpl#getWhileExecutingInputRefs <em>While Executing Input Refs</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TInputSetImpl#getOutputSetRefs <em>Output Set Refs</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TInputSetImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TInputSetImpl extends TBaseElementImpl implements TInputSet {

    /**
     * The cached value of the '{@link #getDataInputRefs() <em>Data Input Refs</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataInputRefs()
     * @generated
     * @ordered
     */
    protected EList<String> dataInputRefs;

    /**
     * The cached value of the '{@link #getOptionalInputRefs() <em>Optional Input Refs</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOptionalInputRefs()
     * @generated
     * @ordered
     */
    protected EList<String> optionalInputRefs;

    /**
     * The cached value of the '{@link #getWhileExecutingInputRefs() <em>While Executing Input Refs</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWhileExecutingInputRefs()
     * @generated
     * @ordered
     */
    protected EList<String> whileExecutingInputRefs;

    /**
     * The cached value of the '{@link #getOutputSetRefs() <em>Output Set Refs</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOutputSetRefs()
     * @generated
     * @ordered
     */
    protected EList<String> outputSetRefs;

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
    protected TInputSetImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TINPUT_SET;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<String> getDataInputRefs() {
        if (dataInputRefs == null) {
            dataInputRefs = new EDataTypeEList<String>(String.class, this, ModelPackage.TINPUT_SET__DATA_INPUT_REFS);
        }
        return dataInputRefs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<String> getOptionalInputRefs() {
        if (optionalInputRefs == null) {
            optionalInputRefs = new EDataTypeEList<String>(String.class, this,
                    ModelPackage.TINPUT_SET__OPTIONAL_INPUT_REFS);
        }
        return optionalInputRefs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<String> getWhileExecutingInputRefs() {
        if (whileExecutingInputRefs == null) {
            whileExecutingInputRefs = new EDataTypeEList<String>(String.class, this,
                    ModelPackage.TINPUT_SET__WHILE_EXECUTING_INPUT_REFS);
        }
        return whileExecutingInputRefs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<String> getOutputSetRefs() {
        if (outputSetRefs == null) {
            outputSetRefs = new EDataTypeEList<String>(String.class, this, ModelPackage.TINPUT_SET__OUTPUT_SET_REFS);
        }
        return outputSetRefs;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TINPUT_SET__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ModelPackage.TINPUT_SET__DATA_INPUT_REFS:
                return getDataInputRefs();
            case ModelPackage.TINPUT_SET__OPTIONAL_INPUT_REFS:
                return getOptionalInputRefs();
            case ModelPackage.TINPUT_SET__WHILE_EXECUTING_INPUT_REFS:
                return getWhileExecutingInputRefs();
            case ModelPackage.TINPUT_SET__OUTPUT_SET_REFS:
                return getOutputSetRefs();
            case ModelPackage.TINPUT_SET__NAME:
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
            case ModelPackage.TINPUT_SET__DATA_INPUT_REFS:
                getDataInputRefs().clear();
                getDataInputRefs().addAll((Collection<? extends String>) newValue);
                return;
            case ModelPackage.TINPUT_SET__OPTIONAL_INPUT_REFS:
                getOptionalInputRefs().clear();
                getOptionalInputRefs().addAll((Collection<? extends String>) newValue);
                return;
            case ModelPackage.TINPUT_SET__WHILE_EXECUTING_INPUT_REFS:
                getWhileExecutingInputRefs().clear();
                getWhileExecutingInputRefs().addAll((Collection<? extends String>) newValue);
                return;
            case ModelPackage.TINPUT_SET__OUTPUT_SET_REFS:
                getOutputSetRefs().clear();
                getOutputSetRefs().addAll((Collection<? extends String>) newValue);
                return;
            case ModelPackage.TINPUT_SET__NAME:
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
            case ModelPackage.TINPUT_SET__DATA_INPUT_REFS:
                getDataInputRefs().clear();
                return;
            case ModelPackage.TINPUT_SET__OPTIONAL_INPUT_REFS:
                getOptionalInputRefs().clear();
                return;
            case ModelPackage.TINPUT_SET__WHILE_EXECUTING_INPUT_REFS:
                getWhileExecutingInputRefs().clear();
                return;
            case ModelPackage.TINPUT_SET__OUTPUT_SET_REFS:
                getOutputSetRefs().clear();
                return;
            case ModelPackage.TINPUT_SET__NAME:
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
            case ModelPackage.TINPUT_SET__DATA_INPUT_REFS:
                return dataInputRefs != null && !dataInputRefs.isEmpty();
            case ModelPackage.TINPUT_SET__OPTIONAL_INPUT_REFS:
                return optionalInputRefs != null && !optionalInputRefs.isEmpty();
            case ModelPackage.TINPUT_SET__WHILE_EXECUTING_INPUT_REFS:
                return whileExecutingInputRefs != null && !whileExecutingInputRefs.isEmpty();
            case ModelPackage.TINPUT_SET__OUTPUT_SET_REFS:
                return outputSetRefs != null && !outputSetRefs.isEmpty();
            case ModelPackage.TINPUT_SET__NAME:
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
        result.append(" (dataInputRefs: "); //$NON-NLS-1$
        result.append(dataInputRefs);
        result.append(", optionalInputRefs: "); //$NON-NLS-1$
        result.append(optionalInputRefs);
        result.append(", whileExecutingInputRefs: "); //$NON-NLS-1$
        result.append(whileExecutingInputRefs);
        result.append(", outputSetRefs: "); //$NON-NLS-1$
        result.append(outputSetRefs);
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(')');
        return result.toString();
    }

} //TInputSetImpl
