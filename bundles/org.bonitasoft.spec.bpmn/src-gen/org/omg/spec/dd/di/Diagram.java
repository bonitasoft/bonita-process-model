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
package org.omg.spec.dd.di;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.dd.di.Diagram#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.omg.spec.dd.di.Diagram#getId <em>Id</em>}</li>
 *   <li>{@link org.omg.spec.dd.di.Diagram#getName <em>Name</em>}</li>
 *   <li>{@link org.omg.spec.dd.di.Diagram#getResolution <em>Resolution</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.dd.di.DiPackage#getDiagram()
 * @model abstract="true"
 *        extendedMetaData="name='Diagram' kind='empty'"
 * @generated
 */
public interface Diagram extends EObject {

    /**
     * Returns the value of the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Documentation</em>' attribute.
     * @see #setDocumentation(String)
     * @see org.omg.spec.dd.di.DiPackage#getDiagram_Documentation()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='documentation'"
     * @generated
     */
    String getDocumentation();

    /**
     * Sets the value of the '{@link org.omg.spec.dd.di.Diagram#getDocumentation <em>Documentation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Documentation</em>' attribute.
     * @see #getDocumentation()
     * @generated
     */
    void setDocumentation(String value);

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see org.omg.spec.dd.di.DiPackage#getDiagram_Id()
     * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
     *        extendedMetaData="kind='attribute' name='id'"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link org.omg.spec.dd.di.Diagram#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.omg.spec.dd.di.DiPackage#getDiagram_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.omg.spec.dd.di.Diagram#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Resolution</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resolution</em>' attribute.
     * @see #isSetResolution()
     * @see #unsetResolution()
     * @see #setResolution(double)
     * @see org.omg.spec.dd.di.DiPackage#getDiagram_Resolution()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double"
     *        extendedMetaData="kind='attribute' name='resolution'"
     * @generated
     */
    double getResolution();

    /**
     * Sets the value of the '{@link org.omg.spec.dd.di.Diagram#getResolution <em>Resolution</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Resolution</em>' attribute.
     * @see #isSetResolution()
     * @see #unsetResolution()
     * @see #getResolution()
     * @generated
     */
    void setResolution(double value);

    /**
     * Unsets the value of the '{@link org.omg.spec.dd.di.Diagram#getResolution <em>Resolution</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetResolution()
     * @see #getResolution()
     * @see #setResolution(double)
     * @generated
     */
    void unsetResolution();

    /**
     * Returns whether the value of the '{@link org.omg.spec.dd.di.Diagram#getResolution <em>Resolution</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Resolution</em>' attribute is set.
     * @see #unsetResolution()
     * @see #getResolution()
     * @see #setResolution(double)
     * @generated
     */
    boolean isSetResolution();

} // Diagram
