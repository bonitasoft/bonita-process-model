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
 * A representation of the model object '<em><b>TCallable Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TCallableElement#getSupportedInterfaceRef <em>Supported Interface Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCallableElement#getIoSpecification <em>Io Specification</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCallableElement#getIoBinding <em>Io Binding</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCallableElement#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTCallableElement()
 * @model extendedMetaData="name='tCallableElement' kind='elementOnly'"
 * @generated
 */
public interface TCallableElement extends TRootElement {

    /**
     * Returns the value of the '<em><b>Supported Interface Ref</b></em>' attribute list.
     * The list contents are of type {@link javax.xml.namespace.QName}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Supported Interface Ref</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTCallableElement_SupportedInterfaceRef()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='element' name='supportedInterfaceRef' namespace='##targetNamespace'"
     * @generated
     */
    EList<QName> getSupportedInterfaceRef();

    /**
     * Returns the value of the '<em><b>Io Specification</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Io Specification</em>' containment reference.
     * @see #setIoSpecification(TInputOutputSpecification)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTCallableElement_IoSpecification()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='ioSpecification' namespace='##targetNamespace'"
     * @generated
     */
    TInputOutputSpecification getIoSpecification();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TCallableElement#getIoSpecification <em>Io Specification</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Io Specification</em>' containment reference.
     * @see #getIoSpecification()
     * @generated
     */
    void setIoSpecification(TInputOutputSpecification value);

    /**
     * Returns the value of the '<em><b>Io Binding</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.bpmn.model.TInputOutputBinding}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Io Binding</em>' containment reference list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTCallableElement_IoBinding()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='ioBinding' namespace='##targetNamespace'"
     * @generated
     */
    EList<TInputOutputBinding> getIoBinding();

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTCallableElement_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TCallableElement#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // TCallableElement
