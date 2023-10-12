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
import org.omg.spec.bpmn.model.TFormalExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TFormal Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TFormalExpressionImpl#getEvaluatesToTypeRef <em>Evaluates To Type Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TFormalExpressionImpl#getLanguage <em>Language</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TFormalExpressionImpl extends TExpressionImpl implements TFormalExpression {

    /**
     * The default value of the '{@link #getEvaluatesToTypeRef() <em>Evaluates To Type Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEvaluatesToTypeRef()
     * @generated
     * @ordered
     */
    protected static final QName EVALUATES_TO_TYPE_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getEvaluatesToTypeRef() <em>Evaluates To Type Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEvaluatesToTypeRef()
     * @generated
     * @ordered
     */
    protected QName evaluatesToTypeRef = EVALUATES_TO_TYPE_REF_EDEFAULT;

    /**
     * The default value of the '{@link #getLanguage() <em>Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLanguage()
     * @generated
     * @ordered
     */
    protected static final String LANGUAGE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLanguage() <em>Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLanguage()
     * @generated
     * @ordered
     */
    protected String language = LANGUAGE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TFormalExpressionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TFORMAL_EXPRESSION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getEvaluatesToTypeRef() {
        return evaluatesToTypeRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setEvaluatesToTypeRef(QName newEvaluatesToTypeRef) {
        QName oldEvaluatesToTypeRef = evaluatesToTypeRef;
        evaluatesToTypeRef = newEvaluatesToTypeRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TFORMAL_EXPRESSION__EVALUATES_TO_TYPE_REF, oldEvaluatesToTypeRef, evaluatesToTypeRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getLanguage() {
        return language;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setLanguage(String newLanguage) {
        String oldLanguage = language;
        language = newLanguage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TFORMAL_EXPRESSION__LANGUAGE,
                    oldLanguage, language));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ModelPackage.TFORMAL_EXPRESSION__EVALUATES_TO_TYPE_REF:
                return getEvaluatesToTypeRef();
            case ModelPackage.TFORMAL_EXPRESSION__LANGUAGE:
                return getLanguage();
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
            case ModelPackage.TFORMAL_EXPRESSION__EVALUATES_TO_TYPE_REF:
                setEvaluatesToTypeRef((QName) newValue);
                return;
            case ModelPackage.TFORMAL_EXPRESSION__LANGUAGE:
                setLanguage((String) newValue);
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
            case ModelPackage.TFORMAL_EXPRESSION__EVALUATES_TO_TYPE_REF:
                setEvaluatesToTypeRef(EVALUATES_TO_TYPE_REF_EDEFAULT);
                return;
            case ModelPackage.TFORMAL_EXPRESSION__LANGUAGE:
                setLanguage(LANGUAGE_EDEFAULT);
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
            case ModelPackage.TFORMAL_EXPRESSION__EVALUATES_TO_TYPE_REF:
                return EVALUATES_TO_TYPE_REF_EDEFAULT == null ? evaluatesToTypeRef != null
                        : !EVALUATES_TO_TYPE_REF_EDEFAULT.equals(evaluatesToTypeRef);
            case ModelPackage.TFORMAL_EXPRESSION__LANGUAGE:
                return LANGUAGE_EDEFAULT == null ? language != null : !LANGUAGE_EDEFAULT.equals(language);
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
        result.append(" (evaluatesToTypeRef: "); //$NON-NLS-1$
        result.append(evaluatesToTypeRef);
        result.append(", language: "); //$NON-NLS-1$
        result.append(language);
        result.append(')');
        return result.toString();
    }

} //TFormalExpressionImpl
