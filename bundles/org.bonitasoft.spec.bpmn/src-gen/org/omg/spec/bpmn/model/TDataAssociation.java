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
 * A representation of the model object '<em><b>TData Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TDataAssociation#getSourceRef <em>Source Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDataAssociation#getTargetRef <em>Target Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDataAssociation#getTransformation <em>Transformation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDataAssociation#getAssignment <em>Assignment</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTDataAssociation()
 * @model extendedMetaData="name='tDataAssociation' kind='elementOnly'"
 * @generated
 */
public interface TDataAssociation extends TBaseElement {

    /**
     * Returns the value of the '<em><b>Source Ref</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Ref</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTDataAssociation_SourceRef()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.IDREF"
     *        extendedMetaData="kind='element' name='sourceRef' namespace='##targetNamespace'"
     * @generated
     */
    EList<String> getSourceRef();

    /**
     * Returns the value of the '<em><b>Target Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Ref</em>' attribute.
     * @see #setTargetRef(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTDataAssociation_TargetRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF" required="true"
     *        extendedMetaData="kind='element' name='targetRef' namespace='##targetNamespace'"
     * @generated
     */
    String getTargetRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TDataAssociation#getTargetRef <em>Target Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Ref</em>' attribute.
     * @see #getTargetRef()
     * @generated
     */
    void setTargetRef(String value);

    /**
     * Returns the value of the '<em><b>Transformation</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Transformation</em>' containment reference.
     * @see #setTransformation(TFormalExpression)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTDataAssociation_Transformation()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='transformation' namespace='##targetNamespace'"
     * @generated
     */
    TFormalExpression getTransformation();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TDataAssociation#getTransformation <em>Transformation</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Transformation</em>' containment reference.
     * @see #getTransformation()
     * @generated
     */
    void setTransformation(TFormalExpression value);

    /**
     * Returns the value of the '<em><b>Assignment</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.bpmn.model.TAssignment}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Assignment</em>' containment reference list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTDataAssociation_Assignment()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='assignment' namespace='##targetNamespace'"
     * @generated
     */
    EList<TAssignment> getAssignment();

} // TDataAssociation
