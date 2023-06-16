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
 * A representation of the model object '<em><b>TAssignment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TAssignment#getFrom <em>From</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TAssignment#getTo <em>To</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTAssignment()
 * @model extendedMetaData="name='tAssignment' kind='elementOnly'"
 * @generated
 */
public interface TAssignment extends TBaseElement {

    /**
     * Returns the value of the '<em><b>From</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>From</em>' containment reference.
     * @see #setFrom(TExpression)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTAssignment_From()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='from' namespace='##targetNamespace'"
     * @generated
     */
    TExpression getFrom();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TAssignment#getFrom <em>From</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>From</em>' containment reference.
     * @see #getFrom()
     * @generated
     */
    void setFrom(TExpression value);

    /**
     * Returns the value of the '<em><b>To</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>To</em>' containment reference.
     * @see #setTo(TExpression)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTAssignment_To()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='to' namespace='##targetNamespace'"
     * @generated
     */
    TExpression getTo();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TAssignment#getTo <em>To</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>To</em>' containment reference.
     * @see #getTo()
     * @generated
     */
    void setTo(TExpression value);

} // TAssignment
