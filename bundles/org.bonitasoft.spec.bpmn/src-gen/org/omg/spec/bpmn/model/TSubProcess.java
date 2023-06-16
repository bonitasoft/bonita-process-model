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

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TSub Process</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TSubProcess#getLaneSet <em>Lane Set</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TSubProcess#getFlowElementGroup <em>Flow Element Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TSubProcess#getFlowElement <em>Flow Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TSubProcess#getArtifactGroup <em>Artifact Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TSubProcess#getArtifact <em>Artifact</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TSubProcess#isTriggeredByEvent <em>Triggered By Event</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTSubProcess()
 * @model extendedMetaData="name='tSubProcess' kind='elementOnly'"
 * @generated
 */
public interface TSubProcess extends TActivity {

    /**
     * Returns the value of the '<em><b>Lane Set</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.bpmn.model.TLaneSet}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Lane Set</em>' containment reference list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTSubProcess_LaneSet()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='laneSet' namespace='##targetNamespace'"
     * @generated
     */
    EList<TLaneSet> getLaneSet();

    /**
     * Returns the value of the '<em><b>Flow Element Group</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Flow Element Group</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTSubProcess_FlowElementGroup()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='group' name='flowElement:group' namespace='##targetNamespace'"
     * @generated
     */
    FeatureMap getFlowElementGroup();

    /**
     * Returns the value of the '<em><b>Flow Element</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.bpmn.model.TFlowElement}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Flow Element</em>' containment reference list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTSubProcess_FlowElement()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='flowElement' namespace='##targetNamespace' group='flowElement:group'"
     * @generated
     */
    EList<TFlowElement> getFlowElement();

    /**
     * Returns the value of the '<em><b>Artifact Group</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Artifact Group</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTSubProcess_ArtifactGroup()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='group' name='artifact:group' namespace='##targetNamespace'"
     * @generated
     */
    FeatureMap getArtifactGroup();

    /**
     * Returns the value of the '<em><b>Artifact</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.bpmn.model.TArtifact}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Artifact</em>' containment reference list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTSubProcess_Artifact()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='artifact' namespace='##targetNamespace' group='artifact:group'"
     * @generated
     */
    EList<TArtifact> getArtifact();

    /**
     * Returns the value of the '<em><b>Triggered By Event</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Triggered By Event</em>' attribute.
     * @see #isSetTriggeredByEvent()
     * @see #unsetTriggeredByEvent()
     * @see #setTriggeredByEvent(boolean)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTSubProcess_TriggeredByEvent()
     * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='triggeredByEvent'"
     * @generated
     */
    boolean isTriggeredByEvent();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TSubProcess#isTriggeredByEvent <em>Triggered By Event</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Triggered By Event</em>' attribute.
     * @see #isSetTriggeredByEvent()
     * @see #unsetTriggeredByEvent()
     * @see #isTriggeredByEvent()
     * @generated
     */
    void setTriggeredByEvent(boolean value);

    /**
     * Unsets the value of the '{@link org.omg.spec.bpmn.model.TSubProcess#isTriggeredByEvent <em>Triggered By Event</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetTriggeredByEvent()
     * @see #isTriggeredByEvent()
     * @see #setTriggeredByEvent(boolean)
     * @generated
     */
    void unsetTriggeredByEvent();

    /**
     * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TSubProcess#isTriggeredByEvent <em>Triggered By Event</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Triggered By Event</em>' attribute is set.
     * @see #unsetTriggeredByEvent()
     * @see #isTriggeredByEvent()
     * @see #setTriggeredByEvent(boolean)
     * @generated
     */
    boolean isSetTriggeredByEvent();

} // TSubProcess
