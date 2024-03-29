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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contract Input</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.model.process.ContractInput#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.model.process.ContractInput#getType <em>Type</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.model.process.ContractInput#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.model.process.ContractInput#isMultiple <em>Multiple</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.model.process.ContractInput#getMapping <em>Mapping</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.model.process.ContractInput#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.model.process.ContractInput#getDataReference <em>Data Reference</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.model.process.ContractInput#isCreateMode <em>Create Mode</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.bpm.model.process.ProcessPackage#getContractInput()
 * @model
 * @generated
 */
public interface ContractInput extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.bonitasoft.bpm.model.process.ProcessPackage#getContractInput_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.model.process.ContractInput#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * The literals are from the enumeration {@link org.bonitasoft.bpm.model.process.ContractInputType}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see org.bonitasoft.bpm.model.process.ContractInputType
     * @see #setType(ContractInputType)
     * @see org.bonitasoft.bpm.model.process.ProcessPackage#getContractInput_Type()
     * @model
     * @generated
     */
    ContractInputType getType();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.model.process.ContractInput#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see org.bonitasoft.bpm.model.process.ContractInputType
     * @see #getType()
     * @generated
     */
    void setType(ContractInputType value);

    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see org.bonitasoft.bpm.model.process.ProcessPackage#getContractInput_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.model.process.ContractInput#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Multiple</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Multiple</em>' attribute.
     * @see #setMultiple(boolean)
     * @see org.bonitasoft.bpm.model.process.ProcessPackage#getContractInput_Multiple()
     * @model
     * @generated
     */
    boolean isMultiple();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.model.process.ContractInput#isMultiple <em>Multiple</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Multiple</em>' attribute.
     * @see #isMultiple()
     * @generated
     */
    void setMultiple(boolean value);

    /**
     * Returns the value of the '<em><b>Mapping</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Mapping</em>' containment reference.
     * @see #setMapping(ContractInputMapping)
     * @see org.bonitasoft.bpm.model.process.ProcessPackage#getContractInput_Mapping()
     * @model containment="true"
     * @generated
     */
    ContractInputMapping getMapping();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.model.process.ContractInput#getMapping <em>Mapping</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Mapping</em>' containment reference.
     * @see #getMapping()
     * @generated
     */
    void setMapping(ContractInputMapping value);

    /**
     * Returns the value of the '<em><b>Inputs</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.bpm.model.process.ContractInput}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Inputs</em>' containment reference list.
     * @see org.bonitasoft.bpm.model.process.ProcessPackage#getContractInput_Inputs()
     * @model containment="true"
     * @generated
     */
    EList<ContractInput> getInputs();

    /**
     * Returns the value of the '<em><b>Data Reference</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Reference</em>' attribute.
     * @see #setDataReference(String)
     * @see org.bonitasoft.bpm.model.process.ProcessPackage#getContractInput_DataReference()
     * @model
     * @generated
     */
    String getDataReference();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.model.process.ContractInput#getDataReference <em>Data Reference</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Reference</em>' attribute.
     * @see #getDataReference()
     * @generated
     */
    void setDataReference(String value);

    /**
     * Returns the value of the '<em><b>Create Mode</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Create Mode</em>' attribute.
     * @see #setCreateMode(boolean)
     * @see org.bonitasoft.bpm.model.process.ProcessPackage#getContractInput_CreateMode()
     * @model default="true"
     * @generated
     */
    boolean isCreateMode();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.model.process.ContractInput#isCreateMode <em>Create Mode</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Create Mode</em>' attribute.
     * @see #isCreateMode()
     * @generated
     */
    void setCreateMode(boolean value);

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model kind="operation"
     * @generated
     */
    String getJavaType();

} // ContractInput
