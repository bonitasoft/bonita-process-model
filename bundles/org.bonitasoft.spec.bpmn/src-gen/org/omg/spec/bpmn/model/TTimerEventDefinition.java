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
 * A representation of the model object '<em><b>TTimer Event Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TTimerEventDefinition#getTimeDate <em>Time Date</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TTimerEventDefinition#getTimeDuration <em>Time Duration</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TTimerEventDefinition#getTimeCycle <em>Time Cycle</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTTimerEventDefinition()
 * @model extendedMetaData="name='tTimerEventDefinition' kind='elementOnly'"
 * @generated
 */
public interface TTimerEventDefinition extends TEventDefinition {

    /**
     * Returns the value of the '<em><b>Time Date</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Time Date</em>' containment reference.
     * @see #setTimeDate(TExpression)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTTimerEventDefinition_TimeDate()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='timeDate' namespace='##targetNamespace'"
     * @generated
     */
    TExpression getTimeDate();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TTimerEventDefinition#getTimeDate <em>Time Date</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Time Date</em>' containment reference.
     * @see #getTimeDate()
     * @generated
     */
    void setTimeDate(TExpression value);

    /**
     * Returns the value of the '<em><b>Time Duration</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Time Duration</em>' containment reference.
     * @see #setTimeDuration(TExpression)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTTimerEventDefinition_TimeDuration()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='timeDuration' namespace='##targetNamespace'"
     * @generated
     */
    TExpression getTimeDuration();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TTimerEventDefinition#getTimeDuration <em>Time Duration</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Time Duration</em>' containment reference.
     * @see #getTimeDuration()
     * @generated
     */
    void setTimeDuration(TExpression value);

    /**
     * Returns the value of the '<em><b>Time Cycle</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Time Cycle</em>' containment reference.
     * @see #setTimeCycle(TExpression)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTTimerEventDefinition_TimeCycle()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='timeCycle' namespace='##targetNamespace'"
     * @generated
     */
    TExpression getTimeCycle();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TTimerEventDefinition#getTimeCycle <em>Time Cycle</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Time Cycle</em>' containment reference.
     * @see #getTimeCycle()
     * @generated
     */
    void setTimeCycle(TExpression value);

} // TTimerEventDefinition
