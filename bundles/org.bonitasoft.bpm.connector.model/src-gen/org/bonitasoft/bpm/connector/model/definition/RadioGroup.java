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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Radio Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.RadioGroup#getChoices <em>Choices</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.RadioGroup#getOrientation <em>Orientation</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getRadioGroup()
 * @model extendedMetaData="name='RadioGroup' kind='elementOnly'"
 * @generated
 */
public interface RadioGroup extends Widget {
    /**
     * Returns the value of the '<em><b>Choices</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Choices</em>' attribute list.
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getRadioGroup_Choices()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='choices'"
     * @generated
     */
    EList<String> getChoices();

    /**
     * Returns the value of the '<em><b>Orientation</b></em>' attribute.
     * The default value is <code>"HORIZONTAL"</code>.
     * The literals are from the enumeration {@link org.bonitasoft.bpm.connector.model.definition.Orientation}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Orientation</em>' attribute.
     * @see org.bonitasoft.bpm.connector.model.definition.Orientation
     * @see #isSetOrientation()
     * @see #unsetOrientation()
     * @see #setOrientation(Orientation)
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getRadioGroup_Orientation()
     * @model default="HORIZONTAL" unsettable="true"
     *        extendedMetaData="kind='attribute' name='orientation'"
     * @generated
     */
    Orientation getOrientation();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.definition.RadioGroup#getOrientation <em>Orientation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Orientation</em>' attribute.
     * @see org.bonitasoft.bpm.connector.model.definition.Orientation
     * @see #isSetOrientation()
     * @see #unsetOrientation()
     * @see #getOrientation()
     * @generated
     */
    void setOrientation(Orientation value);

    /**
     * Unsets the value of the '{@link org.bonitasoft.bpm.connector.model.definition.RadioGroup#getOrientation <em>Orientation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetOrientation()
     * @see #getOrientation()
     * @see #setOrientation(Orientation)
     * @generated
     */
    void unsetOrientation();

    /**
     * Returns whether the value of the '{@link org.bonitasoft.bpm.connector.model.definition.RadioGroup#getOrientation <em>Orientation</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Orientation</em>' attribute is set.
     * @see #unsetOrientation()
     * @see #getOrientation()
     * @see #setOrientation(Orientation)
     * @generated
     */
    boolean isSetOrientation();

} // RadioGroup
