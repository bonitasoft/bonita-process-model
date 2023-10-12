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

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TThrow Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TThrowEvent#getDataInput <em>Data Input</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TThrowEvent#getDataInputAssociation <em>Data Input Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TThrowEvent#getInputSet <em>Input Set</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TThrowEvent#getEventDefinitionGroup <em>Event Definition Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TThrowEvent#getEventDefinition <em>Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TThrowEvent#getEventDefinitionRef <em>Event Definition Ref</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTThrowEvent()
 * @model abstract="true"
 *        extendedMetaData="name='tThrowEvent' kind='elementOnly'"
 * @generated
 */
public interface TThrowEvent extends TEvent {

    /**
     * Returns the value of the '<em><b>Data Input</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.bpmn.model.TDataInput}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Input</em>' containment reference list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTThrowEvent_DataInput()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='dataInput' namespace='##targetNamespace'"
     * @generated
     */
    EList<TDataInput> getDataInput();

    /**
     * Returns the value of the '<em><b>Data Input Association</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.bpmn.model.TDataInputAssociation}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Input Association</em>' containment reference list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTThrowEvent_DataInputAssociation()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='dataInputAssociation' namespace='##targetNamespace'"
     * @generated
     */
    EList<TDataInputAssociation> getDataInputAssociation();

    /**
     * Returns the value of the '<em><b>Input Set</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Input Set</em>' containment reference.
     * @see #setInputSet(TInputSet)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTThrowEvent_InputSet()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='inputSet' namespace='##targetNamespace'"
     * @generated
     */
    TInputSet getInputSet();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TThrowEvent#getInputSet <em>Input Set</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Input Set</em>' containment reference.
     * @see #getInputSet()
     * @generated
     */
    void setInputSet(TInputSet value);

    /**
     * Returns the value of the '<em><b>Event Definition Group</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Event Definition Group</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTThrowEvent_EventDefinitionGroup()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='group' name='eventDefinition:group' namespace='##targetNamespace'"
     * @generated
     */
    FeatureMap getEventDefinitionGroup();

    /**
     * Returns the value of the '<em><b>Event Definition</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.bpmn.model.TEventDefinition}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Event Definition</em>' containment reference list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTThrowEvent_EventDefinition()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='eventDefinition' namespace='##targetNamespace' group='eventDefinition:group'"
     * @generated
     */
    EList<TEventDefinition> getEventDefinition();

    /**
     * Returns the value of the '<em><b>Event Definition Ref</b></em>' attribute list.
     * The list contents are of type {@link javax.xml.namespace.QName}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Event Definition Ref</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTThrowEvent_EventDefinitionRef()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='element' name='eventDefinitionRef' namespace='##targetNamespace'"
     * @generated
     */
    EList<QName> getEventDefinitionRef();

} // TThrowEvent
