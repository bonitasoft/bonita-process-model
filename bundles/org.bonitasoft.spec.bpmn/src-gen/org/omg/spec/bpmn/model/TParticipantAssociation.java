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
 * A representation of the model object '<em><b>TParticipant Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TParticipantAssociation#getInnerParticipantRef <em>Inner Participant Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TParticipantAssociation#getOuterParticipantRef <em>Outer Participant Ref</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTParticipantAssociation()
 * @model extendedMetaData="name='tParticipantAssociation' kind='elementOnly'"
 * @generated
 */
public interface TParticipantAssociation extends TBaseElement {

    /**
     * Returns the value of the '<em><b>Inner Participant Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Inner Participant Ref</em>' attribute.
     * @see #setInnerParticipantRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTParticipantAssociation_InnerParticipantRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
     *        extendedMetaData="kind='element' name='innerParticipantRef' namespace='##targetNamespace'"
     * @generated
     */
    QName getInnerParticipantRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TParticipantAssociation#getInnerParticipantRef <em>Inner Participant Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Inner Participant Ref</em>' attribute.
     * @see #getInnerParticipantRef()
     * @generated
     */
    void setInnerParticipantRef(QName value);

    /**
     * Returns the value of the '<em><b>Outer Participant Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Outer Participant Ref</em>' attribute.
     * @see #setOuterParticipantRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTParticipantAssociation_OuterParticipantRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
     *        extendedMetaData="kind='element' name='outerParticipantRef' namespace='##targetNamespace'"
     * @generated
     */
    QName getOuterParticipantRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TParticipantAssociation#getOuterParticipantRef <em>Outer Participant Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Outer Participant Ref</em>' attribute.
     * @see #getOuterParticipantRef()
     * @generated
     */
    void setOuterParticipantRef(QName value);

} // TParticipantAssociation
