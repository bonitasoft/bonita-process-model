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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TOperation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TOperation#getInMessageRef <em>In Message Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TOperation#getOutMessageRef <em>Out Message Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TOperation#getErrorRef <em>Error Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TOperation#getImplementationRef <em>Implementation Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TOperation#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTOperation()
 * @model extendedMetaData="name='tOperation' kind='elementOnly'"
 * @generated
 */
public interface TOperation extends TBaseElement {

    /**
     * Returns the value of the '<em><b>In Message Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>In Message Ref</em>' attribute.
     * @see #setInMessageRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTOperation_InMessageRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
     *        extendedMetaData="kind='element' name='inMessageRef' namespace='##targetNamespace'"
     * @generated
     */
    QName getInMessageRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TOperation#getInMessageRef <em>In Message Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>In Message Ref</em>' attribute.
     * @see #getInMessageRef()
     * @generated
     */
    void setInMessageRef(QName value);

    /**
     * Returns the value of the '<em><b>Out Message Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Out Message Ref</em>' attribute.
     * @see #setOutMessageRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTOperation_OutMessageRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='element' name='outMessageRef' namespace='##targetNamespace'"
     * @generated
     */
    QName getOutMessageRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TOperation#getOutMessageRef <em>Out Message Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Out Message Ref</em>' attribute.
     * @see #getOutMessageRef()
     * @generated
     */
    void setOutMessageRef(QName value);

    /**
     * Returns the value of the '<em><b>Error Ref</b></em>' attribute list.
     * The list contents are of type {@link javax.xml.namespace.QName}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Error Ref</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTOperation_ErrorRef()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='element' name='errorRef' namespace='##targetNamespace'"
     * @generated
     */
    EList<QName> getErrorRef();

    /**
     * Returns the value of the '<em><b>Implementation Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Implementation Ref</em>' attribute.
     * @see #setImplementationRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTOperation_ImplementationRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='implementationRef'"
     * @generated
     */
    QName getImplementationRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TOperation#getImplementationRef <em>Implementation Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Implementation Ref</em>' attribute.
     * @see #getImplementationRef()
     * @generated
     */
    void setImplementationRef(QName value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTOperation_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TOperation#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // TOperation
