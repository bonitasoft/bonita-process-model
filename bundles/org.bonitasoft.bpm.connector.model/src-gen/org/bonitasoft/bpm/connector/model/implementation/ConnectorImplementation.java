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
package org.bonitasoft.bpm.connector.model.implementation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connector Implementation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation#getImplementationId <em>Implementation Id</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation#getImplementationVersion <em>Implementation Version</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation#getDefinitionId <em>Definition Id</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation#getDefinitionVersion <em>Definition Version</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation#getImplementationClassname <em>Implementation Classname</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation#isHasSources <em>Has Sources</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation#getJarDependencies <em>Jar Dependencies</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementationPackage#getConnectorImplementation()
 * @model extendedMetaData="name='ConnectorImplementation' kind='elementOnly'"
 * @generated
 */
public interface ConnectorImplementation extends EObject {
    /**
     * Returns the value of the '<em><b>Implementation Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Implementation Id</em>' attribute.
     * @see #setImplementationId(String)
     * @see org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementationPackage#getConnectorImplementation_ImplementationId()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='implementationId'"
     * @generated
     */
    String getImplementationId();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation#getImplementationId <em>Implementation Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Implementation Id</em>' attribute.
     * @see #getImplementationId()
     * @generated
     */
    void setImplementationId(String value);

    /**
     * Returns the value of the '<em><b>Implementation Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Implementation Version</em>' attribute.
     * @see #setImplementationVersion(String)
     * @see org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementationPackage#getConnectorImplementation_ImplementationVersion()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='implementationVersion'"
     * @generated
     */
    String getImplementationVersion();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation#getImplementationVersion <em>Implementation Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Implementation Version</em>' attribute.
     * @see #getImplementationVersion()
     * @generated
     */
    void setImplementationVersion(String value);

    /**
     * Returns the value of the '<em><b>Definition Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Definition Id</em>' attribute.
     * @see #setDefinitionId(String)
     * @see org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementationPackage#getConnectorImplementation_DefinitionId()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='definitionId'"
     * @generated
     */
    String getDefinitionId();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation#getDefinitionId <em>Definition Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Definition Id</em>' attribute.
     * @see #getDefinitionId()
     * @generated
     */
    void setDefinitionId(String value);

    /**
     * Returns the value of the '<em><b>Definition Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Definition Version</em>' attribute.
     * @see #setDefinitionVersion(String)
     * @see org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementationPackage#getConnectorImplementation_DefinitionVersion()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='definitionVersion'"
     * @generated
     */
    String getDefinitionVersion();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation#getDefinitionVersion <em>Definition Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Definition Version</em>' attribute.
     * @see #getDefinitionVersion()
     * @generated
     */
    void setDefinitionVersion(String value);

    /**
     * Returns the value of the '<em><b>Implementation Classname</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Implementation Classname</em>' attribute.
     * @see #setImplementationClassname(String)
     * @see org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementationPackage#getConnectorImplementation_ImplementationClassname()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='implementationClassname'"
     * @generated
     */
    String getImplementationClassname();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation#getImplementationClassname <em>Implementation Classname</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Implementation Classname</em>' attribute.
     * @see #getImplementationClassname()
     * @generated
     */
    void setImplementationClassname(String value);

    /**
     * Returns the value of the '<em><b>Has Sources</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Has Sources</em>' attribute.
     * @see #isSetHasSources()
     * @see #unsetHasSources()
     * @see #setHasSources(boolean)
     * @see org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementationPackage#getConnectorImplementation_HasSources()
     * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='element' name='hasSources'"
     * @generated
     */
    boolean isHasSources();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation#isHasSources <em>Has Sources</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Has Sources</em>' attribute.
     * @see #isSetHasSources()
     * @see #unsetHasSources()
     * @see #isHasSources()
     * @generated
     */
    void setHasSources(boolean value);

    /**
     * Unsets the value of the '{@link org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation#isHasSources <em>Has Sources</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetHasSources()
     * @see #isHasSources()
     * @see #setHasSources(boolean)
     * @generated
     */
    void unsetHasSources();

    /**
     * Returns whether the value of the '{@link org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation#isHasSources <em>Has Sources</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Has Sources</em>' attribute is set.
     * @see #unsetHasSources()
     * @see #isHasSources()
     * @see #setHasSources(boolean)
     * @generated
     */
    boolean isSetHasSources();

    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementationPackage#getConnectorImplementation_Description()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='description'"
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Jar Dependencies</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Jar Dependencies</em>' containment reference.
     * @see #setJarDependencies(JarDependencies)
     * @see org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementationPackage#getConnectorImplementation_JarDependencies()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='jarDependencies'"
     * @generated
     */
    JarDependencies getJarDependencies();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation#getJarDependencies <em>Jar Dependencies</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Jar Dependencies</em>' containment reference.
     * @see #getJarDependencies()
     * @generated
     */
    void setJarDependencies(JarDependencies value);

} // ConnectorImplementation
