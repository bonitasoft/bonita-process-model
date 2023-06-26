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
 * A representation of the model object '<em><b>Widget Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.WidgetComponent#getInputName <em>Input Name</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getWidgetComponent()
 * @model extendedMetaData="name='WidgetComponent' kind='empty'"
 * @generated
 */
public interface WidgetComponent extends Component {
    /**
     * Returns the value of the '<em><b>Input Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Input Name</em>' attribute.
     * @see #setInputName(String)
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getWidgetComponent_InputName()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='inputName'"
     * @generated
     */
    String getInputName();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.definition.WidgetComponent#getInputName <em>Input Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Input Name</em>' attribute.
     * @see #getInputName()
     * @generated
     */
    void setInputName(String value);

} // WidgetComponent
