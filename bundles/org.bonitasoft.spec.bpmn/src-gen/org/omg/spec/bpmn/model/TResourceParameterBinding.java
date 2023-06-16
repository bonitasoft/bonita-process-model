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
package org.omg.spec.bpmn.model;

import javax.xml.namespace.QName;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TResource Parameter Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TResourceParameterBinding#getExpressionGroup <em>Expression Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TResourceParameterBinding#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TResourceParameterBinding#getParameterRef <em>Parameter Ref</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTResourceParameterBinding()
 * @model extendedMetaData="name='tResourceParameterBinding' kind='elementOnly'"
 * @generated
 */
public interface TResourceParameterBinding extends TBaseElement {

    /**
     * Returns the value of the '<em><b>Expression Group</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Expression Group</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTResourceParameterBinding_ExpressionGroup()
     * @model dataType="org.eclipse.emf.ecore.EFeatureMapEntry" required="true" many="false"
     *        extendedMetaData="kind='group' name='expression:group' namespace='##targetNamespace'"
     * @generated
     */
    FeatureMap getExpressionGroup();

    /**
     * Returns the value of the '<em><b>Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Expression</em>' containment reference.
     * @see #setExpression(TExpression)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTResourceParameterBinding_Expression()
     * @model containment="true" required="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='expression' namespace='##targetNamespace' group='expression:group'"
     * @generated
     */
    TExpression getExpression();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TResourceParameterBinding#getExpression <em>Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression</em>' containment reference.
     * @see #getExpression()
     * @generated
     */
    void setExpression(TExpression value);

    /**
     * Returns the value of the '<em><b>Parameter Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parameter Ref</em>' attribute.
     * @see #setParameterRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTResourceParameterBinding_ParameterRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
     *        extendedMetaData="kind='attribute' name='parameterRef'"
     * @generated
     */
    QName getParameterRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TResourceParameterBinding#getParameterRef <em>Parameter Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parameter Ref</em>' attribute.
     * @see #getParameterRef()
     * @generated
     */
    void setParameterRef(QName value);

} // TResourceParameterBinding
