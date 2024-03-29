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
 * A representation of the model object '<em><b>TRelationship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TRelationship#getSource <em>Source</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TRelationship#getTarget <em>Target</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TRelationship#getDirection <em>Direction</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TRelationship#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTRelationship()
 * @model extendedMetaData="name='tRelationship' kind='elementOnly'"
 * @generated
 */
public interface TRelationship extends TBaseElement {

    /**
     * Returns the value of the '<em><b>Source</b></em>' attribute list.
     * The list contents are of type {@link javax.xml.namespace.QName}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTRelationship_Source()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
     *        extendedMetaData="kind='element' name='source' namespace='##targetNamespace'"
     * @generated
     */
    EList<QName> getSource();

    /**
     * Returns the value of the '<em><b>Target</b></em>' attribute list.
     * The list contents are of type {@link javax.xml.namespace.QName}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTRelationship_Target()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
     *        extendedMetaData="kind='element' name='target' namespace='##targetNamespace'"
     * @generated
     */
    EList<QName> getTarget();

    /**
     * Returns the value of the '<em><b>Direction</b></em>' attribute.
     * The literals are from the enumeration {@link org.omg.spec.bpmn.model.TRelationshipDirection}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Direction</em>' attribute.
     * @see org.omg.spec.bpmn.model.TRelationshipDirection
     * @see #isSetDirection()
     * @see #unsetDirection()
     * @see #setDirection(TRelationshipDirection)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTRelationship_Direction()
     * @model unsettable="true"
     *        extendedMetaData="kind='attribute' name='direction'"
     * @generated
     */
    TRelationshipDirection getDirection();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TRelationship#getDirection <em>Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Direction</em>' attribute.
     * @see org.omg.spec.bpmn.model.TRelationshipDirection
     * @see #isSetDirection()
     * @see #unsetDirection()
     * @see #getDirection()
     * @generated
     */
    void setDirection(TRelationshipDirection value);

    /**
     * Unsets the value of the '{@link org.omg.spec.bpmn.model.TRelationship#getDirection <em>Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetDirection()
     * @see #getDirection()
     * @see #setDirection(TRelationshipDirection)
     * @generated
     */
    void unsetDirection();

    /**
     * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TRelationship#getDirection <em>Direction</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Direction</em>' attribute is set.
     * @see #unsetDirection()
     * @see #getDirection()
     * @see #setDirection(TRelationshipDirection)
     * @generated
     */
    boolean isSetDirection();

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see #setType(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTRelationship_Type()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='type'"
     * @generated
     */
    String getType();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TRelationship#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see #getType()
     * @generated
     */
    void setType(String value);

} // TRelationship
