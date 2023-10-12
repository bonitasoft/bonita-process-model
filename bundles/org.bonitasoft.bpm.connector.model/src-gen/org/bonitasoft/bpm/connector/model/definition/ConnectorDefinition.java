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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connector Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition#getId <em>Id</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition#getVersion <em>Version</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition#getCategory <em>Category</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition#getInput <em>Input</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition#getOutput <em>Output</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition#getPage <em>Page</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition#getJarDependency <em>Jar Dependency</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getConnectorDefinition()
 * @model extendedMetaData="name='ConnectorDefinition' kind='elementOnly'"
 * @generated
 */
public interface ConnectorDefinition extends EObject {
    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getConnectorDefinition_Id()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='id'"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Version</em>' attribute.
     * @see #setVersion(String)
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getConnectorDefinition_Version()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='version'"
     * @generated
     */
    String getVersion();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition#getVersion <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Version</em>' attribute.
     * @see #getVersion()
     * @generated
     */
    void setVersion(String value);

    /**
     * Returns the value of the '<em><b>Icon</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Icon</em>' attribute.
     * @see #setIcon(String)
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getConnectorDefinition_Icon()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='icon'"
     * @generated
     */
    String getIcon();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition#getIcon <em>Icon</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Icon</em>' attribute.
     * @see #getIcon()
     * @generated
     */
    void setIcon(String value);

    /**
     * Returns the value of the '<em><b>Category</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.bpm.connector.model.definition.Category}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Category</em>' containment reference list.
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getConnectorDefinition_Category()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='category'"
     * @generated
     */
    EList<Category> getCategory();

    /**
     * Returns the value of the '<em><b>Input</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.bpm.connector.model.definition.Input}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Input</em>' containment reference list.
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getConnectorDefinition_Input()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='input'"
     * @generated
     */
    EList<Input> getInput();

    /**
     * Returns the value of the '<em><b>Output</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.bpm.connector.model.definition.Output}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Output</em>' containment reference list.
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getConnectorDefinition_Output()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='output'"
     * @generated
     */
    EList<Output> getOutput();

    /**
     * Returns the value of the '<em><b>Page</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.bpm.connector.model.definition.Page}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Page</em>' containment reference list.
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getConnectorDefinition_Page()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='page'"
     * @generated
     */
    EList<Page> getPage();

    /**
     * Returns the value of the '<em><b>Jar Dependency</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Jar Dependency</em>' attribute list.
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getConnectorDefinition_JarDependency()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='jarDependency'"
     * @generated
     */
    EList<String> getJarDependency();

} // ConnectorDefinition
