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
 * A representation of the model object '<em><b>TComplex Behavior Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TComplexBehaviorDefinition#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TComplexBehaviorDefinition#getEvent <em>Event</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTComplexBehaviorDefinition()
 * @model extendedMetaData="name='tComplexBehaviorDefinition' kind='elementOnly'"
 * @generated
 */
public interface TComplexBehaviorDefinition extends TBaseElement {

    /**
     * Returns the value of the '<em><b>Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Condition</em>' containment reference.
     * @see #setCondition(TFormalExpression)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTComplexBehaviorDefinition_Condition()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='condition' namespace='##targetNamespace'"
     * @generated
     */
    TFormalExpression getCondition();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TComplexBehaviorDefinition#getCondition <em>Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Condition</em>' containment reference.
     * @see #getCondition()
     * @generated
     */
    void setCondition(TFormalExpression value);

    /**
     * Returns the value of the '<em><b>Event</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Event</em>' containment reference.
     * @see #setEvent(TImplicitThrowEvent)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTComplexBehaviorDefinition_Event()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='event' namespace='##targetNamespace'"
     * @generated
     */
    TImplicitThrowEvent getEvent();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TComplexBehaviorDefinition#getEvent <em>Event</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Event</em>' containment reference.
     * @see #getEvent()
     * @generated
     */
    void setEvent(TImplicitThrowEvent value);

} // TComplexBehaviorDefinition
