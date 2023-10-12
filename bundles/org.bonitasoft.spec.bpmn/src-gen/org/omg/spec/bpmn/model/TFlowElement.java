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
 * A representation of the model object '<em><b>TFlow Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TFlowElement#getAuditing <em>Auditing</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TFlowElement#getMonitoring <em>Monitoring</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TFlowElement#getCategoryValueRef <em>Category Value Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TFlowElement#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTFlowElement()
 * @model abstract="true"
 *        extendedMetaData="name='tFlowElement' kind='elementOnly'"
 * @generated
 */
public interface TFlowElement extends TBaseElement {

    /**
     * Returns the value of the '<em><b>Auditing</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Auditing</em>' containment reference.
     * @see #setAuditing(TAuditing)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTFlowElement_Auditing()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='auditing' namespace='##targetNamespace'"
     * @generated
     */
    TAuditing getAuditing();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TFlowElement#getAuditing <em>Auditing</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Auditing</em>' containment reference.
     * @see #getAuditing()
     * @generated
     */
    void setAuditing(TAuditing value);

    /**
     * Returns the value of the '<em><b>Monitoring</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Monitoring</em>' containment reference.
     * @see #setMonitoring(TMonitoring)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTFlowElement_Monitoring()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='monitoring' namespace='##targetNamespace'"
     * @generated
     */
    TMonitoring getMonitoring();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TFlowElement#getMonitoring <em>Monitoring</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Monitoring</em>' containment reference.
     * @see #getMonitoring()
     * @generated
     */
    void setMonitoring(TMonitoring value);

    /**
     * Returns the value of the '<em><b>Category Value Ref</b></em>' attribute list.
     * The list contents are of type {@link javax.xml.namespace.QName}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Category Value Ref</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTFlowElement_CategoryValueRef()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='element' name='categoryValueRef' namespace='##targetNamespace'"
     * @generated
     */
    EList<QName> getCategoryValueRef();

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTFlowElement_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TFlowElement#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // TFlowElement
