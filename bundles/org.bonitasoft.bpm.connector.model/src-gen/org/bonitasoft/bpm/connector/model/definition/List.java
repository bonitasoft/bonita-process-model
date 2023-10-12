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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.List#isShowDocuments <em>Show Documents</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getList()
 * @model extendedMetaData="name='List' kind='empty'"
 * @generated
 */
public interface List extends Widget {
    /**
     * Returns the value of the '<em><b>Show Documents</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Show Documents</em>' attribute.
     * @see #setShowDocuments(boolean)
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getList_ShowDocuments()
     * @model default="false"
     *        extendedMetaData="kind='attribute' name='showDocuments'"
     * @generated
     */
    boolean isShowDocuments();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.definition.List#isShowDocuments <em>Show Documents</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Show Documents</em>' attribute.
     * @see #isShowDocuments()
     * @generated
     */
    void setShowDocuments(boolean value);

} // List
