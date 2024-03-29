/**
 * Copyright (C) 2009-2022 BonitaSoft S.A.
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
package org.bonitasoft.bpm.model.process;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pool</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.model.process.Pool#getDocuments <em>Documents</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.model.process.Pool#getSearchIndexes <em>Search Indexes</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.model.process.Pool#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.model.process.Pool#getAdditionalResources <em>Additional Resources</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.bpm.model.process.ProcessPackage#getPool()
 * @model
 * @generated
 */
public interface Pool extends AbstractProcess, ContractContainer {
    /**
     * Returns the value of the '<em><b>Documents</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.bpm.model.process.Document}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Documents</em>' containment reference list.
     * @see org.bonitasoft.bpm.model.process.ProcessPackage#getPool_Documents()
     * @model containment="true"
     * @generated
     */
    EList<Document> getDocuments();

    /**
     * Returns the value of the '<em><b>Search Indexes</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.bpm.model.process.SearchIndex}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Search Indexes</em>' containment reference list.
     * @see org.bonitasoft.bpm.model.process.ProcessPackage#getPool_SearchIndexes()
     * @model containment="true"
     * @generated
     */
    EList<SearchIndex> getSearchIndexes();

    /**
     * Returns the value of the '<em><b>Display Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Display Name</em>' attribute.
     * @see #setDisplayName(String)
     * @see org.bonitasoft.bpm.model.process.ProcessPackage#getPool_DisplayName()
     * @model
     * @generated
     */
    String getDisplayName();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.model.process.Pool#getDisplayName <em>Display Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Display Name</em>' attribute.
     * @see #getDisplayName()
     * @generated
     */
    void setDisplayName(String value);

    /**
     * Returns the value of the '<em><b>Additional Resources</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.bpm.model.process.AdditionalResource}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Additional Resources</em>' containment reference list.
     * @see org.bonitasoft.bpm.model.process.ProcessPackage#getPool_AdditionalResources()
     * @model containment="true"
     * @generated
     */
    EList<AdditionalResource> getAdditionalResources();

} // Pool
