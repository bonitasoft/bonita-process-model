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
 * A representation of the model object '<em><b>TEscalation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TEscalation#getEscalationCode <em>Escalation Code</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TEscalation#getName <em>Name</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TEscalation#getStructureRef <em>Structure Ref</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTEscalation()
 * @model extendedMetaData="name='tEscalation' kind='elementOnly'"
 * @generated
 */
public interface TEscalation extends TRootElement {

    /**
     * Returns the value of the '<em><b>Escalation Code</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Escalation Code</em>' attribute.
     * @see #setEscalationCode(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTEscalation_EscalationCode()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='escalationCode'"
     * @generated
     */
    String getEscalationCode();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TEscalation#getEscalationCode <em>Escalation Code</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Escalation Code</em>' attribute.
     * @see #getEscalationCode()
     * @generated
     */
    void setEscalationCode(String value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTEscalation_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TEscalation#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Structure Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Structure Ref</em>' attribute.
     * @see #setStructureRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTEscalation_StructureRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='structureRef'"
     * @generated
     */
    QName getStructureRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TEscalation#getStructureRef <em>Structure Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Structure Ref</em>' attribute.
     * @see #getStructureRef()
     * @generated
     */
    void setStructureRef(QName value);

} // TEscalation
