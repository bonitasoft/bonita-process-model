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
 * A representation of the model object '<em><b>TGlobal Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TGlobalTask#getResourceRoleGroup <em>Resource Role Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TGlobalTask#getResourceRole <em>Resource Role</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTGlobalTask()
 * @model extendedMetaData="name='tGlobalTask' kind='elementOnly'"
 * @generated
 */
public interface TGlobalTask extends TCallableElement {

    /**
     * Returns the value of the '<em><b>Resource Role Group</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resource Role Group</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTGlobalTask_ResourceRoleGroup()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='group' name='resourceRole:group' namespace='##targetNamespace'"
     * @generated
     */
    FeatureMap getResourceRoleGroup();

    /**
     * Returns the value of the '<em><b>Resource Role</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.bpmn.model.TResourceRole}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resource Role</em>' containment reference list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTGlobalTask_ResourceRole()
     * @model containment="true" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='resourceRole' namespace='##targetNamespace' group='resourceRole:group'"
     * @generated
     */
    EList<TResourceRole> getResourceRole();

} // TGlobalTask
