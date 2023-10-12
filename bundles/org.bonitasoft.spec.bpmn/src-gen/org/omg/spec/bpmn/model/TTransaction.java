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
 * A representation of the model object '<em><b>TTransaction</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TTransaction#getMethod <em>Method</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTTransaction()
 * @model extendedMetaData="name='tTransaction' kind='elementOnly'"
 * @generated
 */
public interface TTransaction extends TSubProcess {

    /**
     * Returns the value of the '<em><b>Method</b></em>' attribute.
     * The default value is <code>"##Compensate"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Method</em>' attribute.
     * @see #isSetMethod()
     * @see #unsetMethod()
     * @see #setMethod(Object)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTTransaction_Method()
     * @model default="##Compensate" unsettable="true" dataType="org.omg.spec.bpmn.model.TTransactionMethod"
     *        extendedMetaData="kind='attribute' name='method'"
     * @generated
     */
    Object getMethod();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TTransaction#getMethod <em>Method</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Method</em>' attribute.
     * @see #isSetMethod()
     * @see #unsetMethod()
     * @see #getMethod()
     * @generated
     */
    void setMethod(Object value);

    /**
     * Unsets the value of the '{@link org.omg.spec.bpmn.model.TTransaction#getMethod <em>Method</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetMethod()
     * @see #getMethod()
     * @see #setMethod(Object)
     * @generated
     */
    void unsetMethod();

    /**
     * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TTransaction#getMethod <em>Method</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Method</em>' attribute is set.
     * @see #unsetMethod()
     * @see #getMethod()
     * @see #setMethod(Object)
     * @generated
     */
    boolean isSetMethod();

} // TTransaction
