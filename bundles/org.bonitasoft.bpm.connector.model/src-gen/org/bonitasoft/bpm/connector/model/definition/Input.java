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
 * A representation of the model object '<em><b>Input</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.Input#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.Input#isMandatory <em>Mandatory</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.Input#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.Input#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getInput()
 * @model extendedMetaData="name='Input' kind='empty'"
 * @generated
 */
public interface Input extends EObject {
    /**
     * Returns the value of the '<em><b>Default Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Default Value</em>' attribute.
     * @see #setDefaultValue(String)
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getInput_DefaultValue()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='defaultValue'"
     * @generated
     */
    String getDefaultValue();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.definition.Input#getDefaultValue <em>Default Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Value</em>' attribute.
     * @see #getDefaultValue()
     * @generated
     */
    void setDefaultValue(String value);

    /**
     * Returns the value of the '<em><b>Mandatory</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Mandatory</em>' attribute.
     * @see #isSetMandatory()
     * @see #unsetMandatory()
     * @see #setMandatory(boolean)
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getInput_Mandatory()
     * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='mandatory'"
     * @generated
     */
    boolean isMandatory();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.definition.Input#isMandatory <em>Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Mandatory</em>' attribute.
     * @see #isSetMandatory()
     * @see #unsetMandatory()
     * @see #isMandatory()
     * @generated
     */
    void setMandatory(boolean value);

    /**
     * Unsets the value of the '{@link org.bonitasoft.bpm.connector.model.definition.Input#isMandatory <em>Mandatory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetMandatory()
     * @see #isMandatory()
     * @see #setMandatory(boolean)
     * @generated
     */
    void unsetMandatory();

    /**
     * Returns whether the value of the '{@link org.bonitasoft.bpm.connector.model.definition.Input#isMandatory <em>Mandatory</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Mandatory</em>' attribute is set.
     * @see #unsetMandatory()
     * @see #isMandatory()
     * @see #setMandatory(boolean)
     * @generated
     */
    boolean isSetMandatory();

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getInput_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.definition.Input#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see #setType(String)
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getInput_Type()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='type'"
     * @generated
     */
    String getType();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.definition.Input#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see #getType()
     * @generated
     */
    void setType(String value);

} // Input
