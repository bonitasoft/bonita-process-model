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
 * A representation of the model object '<em><b>TResource Role</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TResourceRole#getResourceRef <em>Resource Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TResourceRole#getResourceParameterBinding <em>Resource Parameter Binding</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TResourceRole#getResourceAssignmentExpression <em>Resource Assignment Expression</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TResourceRole#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTResourceRole()
 * @model extendedMetaData="name='tResourceRole' kind='elementOnly'"
 * @generated
 */
public interface TResourceRole extends TBaseElement {

    /**
     * Returns the value of the '<em><b>Resource Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resource Ref</em>' attribute.
     * @see #setResourceRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTResourceRole_ResourceRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='element' name='resourceRef' namespace='##targetNamespace'"
     * @generated
     */
    QName getResourceRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TResourceRole#getResourceRef <em>Resource Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Resource Ref</em>' attribute.
     * @see #getResourceRef()
     * @generated
     */
    void setResourceRef(QName value);

    /**
     * Returns the value of the '<em><b>Resource Parameter Binding</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.bpmn.model.TResourceParameterBinding}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resource Parameter Binding</em>' containment reference list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTResourceRole_ResourceParameterBinding()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='resourceParameterBinding' namespace='##targetNamespace'"
     * @generated
     */
    EList<TResourceParameterBinding> getResourceParameterBinding();

    /**
     * Returns the value of the '<em><b>Resource Assignment Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resource Assignment Expression</em>' containment reference.
     * @see #setResourceAssignmentExpression(TResourceAssignmentExpression)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTResourceRole_ResourceAssignmentExpression()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='resourceAssignmentExpression' namespace='##targetNamespace'"
     * @generated
     */
    TResourceAssignmentExpression getResourceAssignmentExpression();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TResourceRole#getResourceAssignmentExpression <em>Resource Assignment Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Resource Assignment Expression</em>' containment reference.
     * @see #getResourceAssignmentExpression()
     * @generated
     */
    void setResourceAssignmentExpression(TResourceAssignmentExpression value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTResourceRole_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TResourceRole#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // TResourceRole
