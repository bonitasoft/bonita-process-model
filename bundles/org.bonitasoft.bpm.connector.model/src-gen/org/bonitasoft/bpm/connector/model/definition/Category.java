/**
 * Copyright (C) 2009-2023 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.bpm.connector.model.definition;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Category</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.Category#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.Category#getId <em>Id</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.Category#getParentCategoryId <em>Parent Category Id</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getCategory()
 * @model extendedMetaData="name='Category' kind='empty'"
 * @generated
 */
public interface Category extends EObject {
    /**
     * Returns the value of the '<em><b>Icon</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Icon</em>' attribute.
     * @see #setIcon(String)
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getCategory_Icon()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='icon'"
     * @generated
     */
    String getIcon();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.definition.Category#getIcon <em>Icon</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Icon</em>' attribute.
     * @see #getIcon()
     * @generated
     */
    void setIcon(String value);

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getCategory_Id()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='id'"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.definition.Category#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Parent Category Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parent Category Id</em>' attribute.
     * @see #setParentCategoryId(String)
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getCategory_ParentCategoryId()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='parentCategoryId'"
     * @generated
     */
    String getParentCategoryId();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.definition.Category#getParentCategoryId <em>Parent Category Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent Category Id</em>' attribute.
     * @see #getParentCategoryId()
     * @generated
     */
    void setParentCategoryId(String value);

} // Category
