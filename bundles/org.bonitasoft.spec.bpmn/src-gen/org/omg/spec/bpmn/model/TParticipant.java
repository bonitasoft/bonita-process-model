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
 * A representation of the model object '<em><b>TParticipant</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TParticipant#getInterfaceRef <em>Interface Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TParticipant#getEndPointRef <em>End Point Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TParticipant#getParticipantMultiplicity <em>Participant Multiplicity</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TParticipant#getName <em>Name</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TParticipant#getProcessRef <em>Process Ref</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTParticipant()
 * @model extendedMetaData="name='tParticipant' kind='elementOnly'"
 * @generated
 */
public interface TParticipant extends TBaseElement {

    /**
     * Returns the value of the '<em><b>Interface Ref</b></em>' attribute list.
     * The list contents are of type {@link javax.xml.namespace.QName}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Interface Ref</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTParticipant_InterfaceRef()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='element' name='interfaceRef' namespace='##targetNamespace'"
     * @generated
     */
    EList<QName> getInterfaceRef();

    /**
     * Returns the value of the '<em><b>End Point Ref</b></em>' attribute list.
     * The list contents are of type {@link javax.xml.namespace.QName}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>End Point Ref</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTParticipant_EndPointRef()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='element' name='endPointRef' namespace='##targetNamespace'"
     * @generated
     */
    EList<QName> getEndPointRef();

    /**
     * Returns the value of the '<em><b>Participant Multiplicity</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Participant Multiplicity</em>' containment reference.
     * @see #setParticipantMultiplicity(TParticipantMultiplicity)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTParticipant_ParticipantMultiplicity()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='participantMultiplicity' namespace='##targetNamespace'"
     * @generated
     */
    TParticipantMultiplicity getParticipantMultiplicity();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TParticipant#getParticipantMultiplicity <em>Participant Multiplicity</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Participant Multiplicity</em>' containment reference.
     * @see #getParticipantMultiplicity()
     * @generated
     */
    void setParticipantMultiplicity(TParticipantMultiplicity value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTParticipant_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TParticipant#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Process Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Process Ref</em>' attribute.
     * @see #setProcessRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTParticipant_ProcessRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='processRef'"
     * @generated
     */
    QName getProcessRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TParticipant#getProcessRef <em>Process Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Process Ref</em>' attribute.
     * @see #getProcessRef()
     * @generated
     */
    void setProcessRef(QName value);

} // TParticipant
