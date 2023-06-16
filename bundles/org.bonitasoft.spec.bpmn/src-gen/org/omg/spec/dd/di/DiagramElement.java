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

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.dd.di.DiagramElement#getExtension <em>Extension</em>}</li>
 *   <li>{@link org.omg.spec.dd.di.DiagramElement#getId <em>Id</em>}</li>
 *   <li>{@link org.omg.spec.dd.di.DiagramElement#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.dd.di.DiPackage#getDiagramElement()
 * @model abstract="true"
 *        extendedMetaData="name='DiagramElement' kind='elementOnly'"
 * @generated
 */
public interface DiagramElement extends EObject {

    /**
     * Returns the value of the '<em><b>Extension</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Extension</em>' containment reference.
     * @see #setExtension(ExtensionType)
     * @see org.omg.spec.dd.di.DiPackage#getDiagramElement_Extension()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='extension' namespace='##targetNamespace'"
     * @generated
     */
    ExtensionType getExtension();

    /**
     * Sets the value of the '{@link org.omg.spec.dd.di.DiagramElement#getExtension <em>Extension</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Extension</em>' containment reference.
     * @see #getExtension()
     * @generated
     */
    void setExtension(ExtensionType value);

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see org.omg.spec.dd.di.DiPackage#getDiagramElement_Id()
     * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
     *        extendedMetaData="kind='attribute' name='id'"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link org.omg.spec.dd.di.DiagramElement#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Any Attribute</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Any Attribute</em>' attribute list.
     * @see org.omg.spec.dd.di.DiPackage#getDiagramElement_AnyAttribute()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='attributeWildcard' wildcards='##other' name=':2' processing='lax'"
     * @generated
     */
    FeatureMap getAnyAttribute();

} // DiagramElement
