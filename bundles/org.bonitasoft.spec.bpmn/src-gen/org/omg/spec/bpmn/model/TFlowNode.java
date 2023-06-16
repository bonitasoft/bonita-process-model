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
 * A representation of the model object '<em><b>TFlow Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TFlowNode#getIncoming <em>Incoming</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TFlowNode#getOutgoing <em>Outgoing</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTFlowNode()
 * @model abstract="true"
 *        extendedMetaData="name='tFlowNode' kind='elementOnly'"
 * @generated
 */
public interface TFlowNode extends TFlowElement {

    /**
     * Returns the value of the '<em><b>Incoming</b></em>' attribute list.
     * The list contents are of type {@link javax.xml.namespace.QName}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Incoming</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTFlowNode_Incoming()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='element' name='incoming' namespace='##targetNamespace'"
     * @generated
     */
    EList<QName> getIncoming();

    /**
     * Returns the value of the '<em><b>Outgoing</b></em>' attribute list.
     * The list contents are of type {@link javax.xml.namespace.QName}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Outgoing</em>' attribute list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTFlowNode_Outgoing()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='element' name='outgoing' namespace='##targetNamespace'"
     * @generated
     */
    EList<QName> getOutgoing();

} // TFlowNode
