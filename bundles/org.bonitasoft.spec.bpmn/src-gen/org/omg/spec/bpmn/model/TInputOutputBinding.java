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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TInput Output Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TInputOutputBinding#getInputDataRef <em>Input Data Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TInputOutputBinding#getOperationRef <em>Operation Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TInputOutputBinding#getOutputDataRef <em>Output Data Ref</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTInputOutputBinding()
 * @model extendedMetaData="name='tInputOutputBinding' kind='elementOnly'"
 * @generated
 */
public interface TInputOutputBinding extends TBaseElement {

    /**
     * Returns the value of the '<em><b>Input Data Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Input Data Ref</em>' attribute.
     * @see #setInputDataRef(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTInputOutputBinding_InputDataRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF" required="true"
     *        extendedMetaData="kind='attribute' name='inputDataRef'"
     * @generated
     */
    String getInputDataRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TInputOutputBinding#getInputDataRef <em>Input Data Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Input Data Ref</em>' attribute.
     * @see #getInputDataRef()
     * @generated
     */
    void setInputDataRef(String value);

    /**
     * Returns the value of the '<em><b>Operation Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Operation Ref</em>' attribute.
     * @see #setOperationRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTInputOutputBinding_OperationRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
     *        extendedMetaData="kind='attribute' name='operationRef'"
     * @generated
     */
    QName getOperationRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TInputOutputBinding#getOperationRef <em>Operation Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Operation Ref</em>' attribute.
     * @see #getOperationRef()
     * @generated
     */
    void setOperationRef(QName value);

    /**
     * Returns the value of the '<em><b>Output Data Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Output Data Ref</em>' attribute.
     * @see #setOutputDataRef(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTInputOutputBinding_OutputDataRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF" required="true"
     *        extendedMetaData="kind='attribute' name='outputDataRef'"
     * @generated
     */
    String getOutputDataRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TInputOutputBinding#getOutputDataRef <em>Output Data Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Output Data Ref</em>' attribute.
     * @see #getOutputDataRef()
     * @generated
     */
    void setOutputDataRef(String value);

} // TInputOutputBinding
