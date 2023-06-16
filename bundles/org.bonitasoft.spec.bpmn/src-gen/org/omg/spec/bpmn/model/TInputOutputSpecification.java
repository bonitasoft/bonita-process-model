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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TInput Output Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TInputOutputSpecification#getDataInput <em>Data Input</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TInputOutputSpecification#getDataOutput <em>Data Output</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TInputOutputSpecification#getInputSet <em>Input Set</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TInputOutputSpecification#getOutputSet <em>Output Set</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTInputOutputSpecification()
 * @model extendedMetaData="name='tInputOutputSpecification' kind='elementOnly'"
 * @generated
 */
public interface TInputOutputSpecification extends TBaseElement {

    /**
     * Returns the value of the '<em><b>Data Input</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.bpmn.model.TDataInput}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Input</em>' containment reference list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTInputOutputSpecification_DataInput()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='dataInput' namespace='##targetNamespace'"
     * @generated
     */
    EList<TDataInput> getDataInput();

    /**
     * Returns the value of the '<em><b>Data Output</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.bpmn.model.TDataOutput}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Output</em>' containment reference list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTInputOutputSpecification_DataOutput()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='dataOutput' namespace='##targetNamespace'"
     * @generated
     */
    EList<TDataOutput> getDataOutput();

    /**
     * Returns the value of the '<em><b>Input Set</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.bpmn.model.TInputSet}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Input Set</em>' containment reference list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTInputOutputSpecification_InputSet()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='inputSet' namespace='##targetNamespace'"
     * @generated
     */
    EList<TInputSet> getInputSet();

    /**
     * Returns the value of the '<em><b>Output Set</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.bpmn.model.TOutputSet}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Output Set</em>' containment reference list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTInputOutputSpecification_OutputSet()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='outputSet' namespace='##targetNamespace'"
     * @generated
     */
    EList<TOutputSet> getOutputSet();

} // TInputOutputSpecification
