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

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TExpression;
import org.omg.spec.bpmn.model.TResourceAssignmentExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TResource Assignment Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TResourceAssignmentExpressionImpl#getExpressionGroup <em>Expression Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TResourceAssignmentExpressionImpl#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TResourceAssignmentExpressionImpl extends TBaseElementImpl implements TResourceAssignmentExpression {

    /**
     * The cached value of the '{@link #getExpressionGroup() <em>Expression Group</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExpressionGroup()
     * @generated
     * @ordered
     */
    protected FeatureMap expressionGroup;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TResourceAssignmentExpressionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TRESOURCE_ASSIGNMENT_EXPRESSION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FeatureMap getExpressionGroup() {
        if (expressionGroup == null) {
            expressionGroup = new BasicFeatureMap(this, ModelPackage.TRESOURCE_ASSIGNMENT_EXPRESSION__EXPRESSION_GROUP);
        }
        return expressionGroup;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TExpression getExpression() {
        return (TExpression) getExpressionGroup().get(ModelPackage.Literals.TRESOURCE_ASSIGNMENT_EXPRESSION__EXPRESSION,
                true);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetExpression(TExpression newExpression, NotificationChain msgs) {
        return ((FeatureMap.Internal) getExpressionGroup())
                .basicAdd(ModelPackage.Literals.TRESOURCE_ASSIGNMENT_EXPRESSION__EXPRESSION, newExpression, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setExpression(TExpression newExpression) {
        ((FeatureMap.Internal) getExpressionGroup())
                .set(ModelPackage.Literals.TRESOURCE_ASSIGNMENT_EXPRESSION__EXPRESSION, newExpression);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ModelPackage.TRESOURCE_ASSIGNMENT_EXPRESSION__EXPRESSION_GROUP:
                return ((InternalEList<?>) getExpressionGroup()).basicRemove(otherEnd, msgs);
            case ModelPackage.TRESOURCE_ASSIGNMENT_EXPRESSION__EXPRESSION:
                return basicSetExpression(null, msgs);
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
            case ModelPackage.TRESOURCE_ASSIGNMENT_EXPRESSION__EXPRESSION_GROUP:
                if (coreType)
                    return getExpressionGroup();
                return ((FeatureMap.Internal) getExpressionGroup()).getWrapper();
            case ModelPackage.TRESOURCE_ASSIGNMENT_EXPRESSION__EXPRESSION:
                return getExpression();
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
            case ModelPackage.TRESOURCE_ASSIGNMENT_EXPRESSION__EXPRESSION_GROUP:
                ((FeatureMap.Internal) getExpressionGroup()).set(newValue);
                return;
            case ModelPackage.TRESOURCE_ASSIGNMENT_EXPRESSION__EXPRESSION:
                setExpression((TExpression) newValue);
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
            case ModelPackage.TRESOURCE_ASSIGNMENT_EXPRESSION__EXPRESSION_GROUP:
                getExpressionGroup().clear();
                return;
            case ModelPackage.TRESOURCE_ASSIGNMENT_EXPRESSION__EXPRESSION:
                setExpression((TExpression) null);
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
            case ModelPackage.TRESOURCE_ASSIGNMENT_EXPRESSION__EXPRESSION_GROUP:
                return expressionGroup != null && !expressionGroup.isEmpty();
            case ModelPackage.TRESOURCE_ASSIGNMENT_EXPRESSION__EXPRESSION:
                return getExpression() != null;
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
        result.append(" (expressionGroup: "); //$NON-NLS-1$
        result.append(expressionGroup);
        result.append(')');
        return result.toString();
    }

} //TResourceAssignmentExpressionImpl
