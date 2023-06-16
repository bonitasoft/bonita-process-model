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
 * A representation of the model object '<em><b>TGroup</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TGroup#getCategoryValueRef <em>Category Value Ref</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTGroup()
 * @model extendedMetaData="name='tGroup' kind='elementOnly'"
 * @generated
 */
public interface TGroup extends TArtifact {

    /**
     * Returns the value of the '<em><b>Category Value Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Category Value Ref</em>' attribute.
     * @see #setCategoryValueRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTGroup_CategoryValueRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='categoryValueRef'"
     * @generated
     */
    QName getCategoryValueRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TGroup#getCategoryValueRef <em>Category Value Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Category Value Ref</em>' attribute.
     * @see #getCategoryValueRef()
     * @generated
     */
    void setCategoryValueRef(QName value);

} // TGroup
