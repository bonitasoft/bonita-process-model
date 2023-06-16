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
 * A representation of the model object '<em><b>TLane</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TLane#getPartitionElement <em>Partition Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TLane#getFlowNodeRef <em>Flow Node Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TLane#getChildLaneSet <em>Child Lane Set</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TLane#getName <em>Name</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TLane#getPartitionElementRef <em>Partition Element Ref</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTLane()
 * @model extendedMetaData="name='tLane' kind='elementOnly'"
 * @generated
 */
public interface TLane extends TBaseElement {

    /**
     * Returns the value of the '<em><b>Partition Element</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Partition Element</em>' containment reference.
     * @see #setPartitionElement(TBaseElement)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTLane_PartitionElement()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='partitionElement' namespace='##targetNamespace'"
     * @generated
     */
    TBaseElement getPartitionElement();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TLane#getPartitionElement <em>Partition Element</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Partition Element</em>' containment reference.
     * @see #getPartitionElement()
     * @generated
     */
    void setPartitionElement(TBaseElement value);

    /**
     * Returns the value of the '<em><b>Flow Node Ref</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Flow Node Ref</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTLane_FlowNodeRef()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.IDREF"
     *        extendedMetaData="kind='element' name='flowNodeRef' namespace='##targetNamespace'"
     * @generated
     */
    EList<String> getFlowNodeRef();

    /**
     * Returns the value of the '<em><b>Child Lane Set</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Child Lane Set</em>' containment reference.
     * @see #setChildLaneSet(TLaneSet)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTLane_ChildLaneSet()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='childLaneSet' namespace='##targetNamespace'"
     * @generated
     */
    TLaneSet getChildLaneSet();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TLane#getChildLaneSet <em>Child Lane Set</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Child Lane Set</em>' containment reference.
     * @see #getChildLaneSet()
     * @generated
     */
    void setChildLaneSet(TLaneSet value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTLane_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TLane#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Partition Element Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Partition Element Ref</em>' attribute.
     * @see #setPartitionElementRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTLane_PartitionElementRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='partitionElementRef'"
     * @generated
     */
    QName getPartitionElementRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TLane#getPartitionElementRef <em>Partition Element Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Partition Element Ref</em>' attribute.
     * @see #getPartitionElementRef()
     * @generated
     */
    void setPartitionElementRef(QName value);

} // TLane
