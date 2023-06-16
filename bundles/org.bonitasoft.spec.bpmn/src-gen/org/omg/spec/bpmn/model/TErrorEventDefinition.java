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
 * A representation of the model object '<em><b>TError Event Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TErrorEventDefinition#getErrorRef <em>Error Ref</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTErrorEventDefinition()
 * @model extendedMetaData="name='tErrorEventDefinition' kind='elementOnly'"
 * @generated
 */
public interface TErrorEventDefinition extends TEventDefinition {

    /**
     * Returns the value of the '<em><b>Error Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Error Ref</em>' attribute.
     * @see #setErrorRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTErrorEventDefinition_ErrorRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='errorRef'"
     * @generated
     */
    QName getErrorRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TErrorEventDefinition#getErrorRef <em>Error Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Error Ref</em>' attribute.
     * @see #getErrorRef()
     * @generated
     */
    void setErrorRef(QName value);

} // TErrorEventDefinition
