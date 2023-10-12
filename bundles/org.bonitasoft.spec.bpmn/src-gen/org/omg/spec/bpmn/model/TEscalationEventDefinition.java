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
 * A representation of the model object '<em><b>TEscalation Event Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TEscalationEventDefinition#getEscalationRef <em>Escalation Ref</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTEscalationEventDefinition()
 * @model extendedMetaData="name='tEscalationEventDefinition' kind='elementOnly'"
 * @generated
 */
public interface TEscalationEventDefinition extends TEventDefinition {

    /**
     * Returns the value of the '<em><b>Escalation Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Escalation Ref</em>' attribute.
     * @see #setEscalationRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTEscalationEventDefinition_EscalationRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='escalationRef'"
     * @generated
     */
    QName getEscalationRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TEscalationEventDefinition#getEscalationRef <em>Escalation Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Escalation Ref</em>' attribute.
     * @see #getEscalationRef()
     * @generated
     */
    void setEscalationRef(QName value);

} // TEscalationEventDefinition
