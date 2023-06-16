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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TComplex Gateway</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TComplexGateway#getActivationCondition <em>Activation Condition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TComplexGateway#getDefault <em>Default</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTComplexGateway()
 * @model extendedMetaData="name='tComplexGateway' kind='elementOnly'"
 * @generated
 */
public interface TComplexGateway extends TGateway {

    /**
     * Returns the value of the '<em><b>Activation Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Activation Condition</em>' containment reference.
     * @see #setActivationCondition(TExpression)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTComplexGateway_ActivationCondition()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='activationCondition' namespace='##targetNamespace'"
     * @generated
     */
    TExpression getActivationCondition();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TComplexGateway#getActivationCondition <em>Activation Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Activation Condition</em>' containment reference.
     * @see #getActivationCondition()
     * @generated
     */
    void setActivationCondition(TExpression value);

    /**
     * Returns the value of the '<em><b>Default</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Default</em>' attribute.
     * @see #setDefault(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTComplexGateway_Default()
     * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF"
     *        extendedMetaData="kind='attribute' name='default'"
     * @generated
     */
    String getDefault();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TComplexGateway#getDefault <em>Default</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default</em>' attribute.
     * @see #getDefault()
     * @generated
     */
    void setDefault(String value);

} // TComplexGateway
