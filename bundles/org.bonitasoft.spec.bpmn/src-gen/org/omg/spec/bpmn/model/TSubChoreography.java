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
 * A representation of the model object '<em><b>TSub Choreography</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TSubChoreography#getFlowElementGroup <em>Flow Element Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TSubChoreography#getFlowElement <em>Flow Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TSubChoreography#getArtifactGroup <em>Artifact Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TSubChoreography#getArtifact <em>Artifact</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTSubChoreography()
 * @model extendedMetaData="name='tSubChoreography' kind='elementOnly'"
 * @generated
 */
public interface TSubChoreography extends TChoreographyActivity {

    /**
     * Returns the value of the '<em><b>Flow Element Group</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Flow Element Group</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTSubChoreography_FlowElementGroup()
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
     * @see org.omg.spec.bpmn.model.ModelPackage#getTSubChoreography_FlowElement()
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
     * @see org.omg.spec.bpmn.model.ModelPackage#getTSubChoreography_ArtifactGroup()
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
     * @see org.omg.spec.bpmn.model.ModelPackage#getTSubChoreography_Artifact()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='artifact' namespace='##targetNamespace' group='artifact:group'"
     * @generated
     */
    EList<TArtifact> getArtifact();

} // TSubChoreography
