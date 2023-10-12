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
 * A representation of the model object '<em><b>TData Object Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TDataObjectReference#getDataState <em>Data State</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDataObjectReference#getDataObjectRef <em>Data Object Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDataObjectReference#getItemSubjectRef <em>Item Subject Ref</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTDataObjectReference()
 * @model extendedMetaData="name='tDataObjectReference' kind='elementOnly'"
 * @generated
 */
public interface TDataObjectReference extends TFlowElement {

    /**
     * Returns the value of the '<em><b>Data State</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data State</em>' containment reference.
     * @see #setDataState(TDataState)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTDataObjectReference_DataState()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='dataState' namespace='##targetNamespace'"
     * @generated
     */
    TDataState getDataState();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TDataObjectReference#getDataState <em>Data State</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data State</em>' containment reference.
     * @see #getDataState()
     * @generated
     */
    void setDataState(TDataState value);

    /**
     * Returns the value of the '<em><b>Data Object Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Object Ref</em>' attribute.
     * @see #setDataObjectRef(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTDataObjectReference_DataObjectRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF"
     *        extendedMetaData="kind='attribute' name='dataObjectRef'"
     * @generated
     */
    String getDataObjectRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TDataObjectReference#getDataObjectRef <em>Data Object Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Object Ref</em>' attribute.
     * @see #getDataObjectRef()
     * @generated
     */
    void setDataObjectRef(String value);

    /**
     * Returns the value of the '<em><b>Item Subject Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Item Subject Ref</em>' attribute.
     * @see #setItemSubjectRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTDataObjectReference_ItemSubjectRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='itemSubjectRef'"
     * @generated
     */
    QName getItemSubjectRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TDataObjectReference#getItemSubjectRef <em>Item Subject Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Item Subject Ref</em>' attribute.
     * @see #getItemSubjectRef()
     * @generated
     */
    void setItemSubjectRef(QName value);

} // TDataObjectReference
