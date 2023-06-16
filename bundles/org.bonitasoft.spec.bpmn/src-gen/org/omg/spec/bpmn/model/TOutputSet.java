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
 * A representation of the model object '<em><b>TOutput Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TOutputSet#getDataOutputRefs <em>Data Output Refs</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TOutputSet#getOptionalOutputRefs <em>Optional Output Refs</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TOutputSet#getWhileExecutingOutputRefs <em>While Executing Output Refs</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TOutputSet#getInputSetRefs <em>Input Set Refs</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TOutputSet#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTOutputSet()
 * @model extendedMetaData="name='tOutputSet' kind='elementOnly'"
 * @generated
 */
public interface TOutputSet extends TBaseElement {

    /**
     * Returns the value of the '<em><b>Data Output Refs</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Output Refs</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTOutputSet_DataOutputRefs()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.IDREF"
     *        extendedMetaData="kind='element' name='dataOutputRefs' namespace='##targetNamespace'"
     * @generated
     */
    EList<String> getDataOutputRefs();

    /**
     * Returns the value of the '<em><b>Optional Output Refs</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Optional Output Refs</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTOutputSet_OptionalOutputRefs()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.IDREF"
     *        extendedMetaData="kind='element' name='optionalOutputRefs' namespace='##targetNamespace'"
     * @generated
     */
    EList<String> getOptionalOutputRefs();

    /**
     * Returns the value of the '<em><b>While Executing Output Refs</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>While Executing Output Refs</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTOutputSet_WhileExecutingOutputRefs()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.IDREF"
     *        extendedMetaData="kind='element' name='whileExecutingOutputRefs' namespace='##targetNamespace'"
     * @generated
     */
    EList<String> getWhileExecutingOutputRefs();

    /**
     * Returns the value of the '<em><b>Input Set Refs</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Input Set Refs</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTOutputSet_InputSetRefs()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.IDREF"
     *        extendedMetaData="kind='element' name='inputSetRefs' namespace='##targetNamespace'"
     * @generated
     */
    EList<String> getInputSetRefs();

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTOutputSet_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TOutputSet#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // TOutputSet
