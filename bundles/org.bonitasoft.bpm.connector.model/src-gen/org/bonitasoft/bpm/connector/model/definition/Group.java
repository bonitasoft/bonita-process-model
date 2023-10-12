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
 * A representation of the model object '<em><b>Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.Group#getWidget <em>Widget</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.Group#isOptional <em>Optional</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getGroup()
 * @model extendedMetaData="name='Group' kind='elementOnly'"
 * @generated
 */
public interface Group extends Component {
    /**
     * Returns the value of the '<em><b>Widget</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.bpm.connector.model.definition.Component}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Widget</em>' containment reference list.
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getGroup_Widget()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='widget'"
     * @generated
     */
    EList<Component> getWidget();

    /**
     * Returns the value of the '<em><b>Optional</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Optional</em>' attribute.
     * @see #isSetOptional()
     * @see #unsetOptional()
     * @see #setOptional(boolean)
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getGroup_Optional()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='optional'"
     * @generated
     */
    boolean isOptional();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.definition.Group#isOptional <em>Optional</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Optional</em>' attribute.
     * @see #isSetOptional()
     * @see #unsetOptional()
     * @see #isOptional()
     * @generated
     */
    void setOptional(boolean value);

    /**
     * Unsets the value of the '{@link org.bonitasoft.bpm.connector.model.definition.Group#isOptional <em>Optional</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetOptional()
     * @see #isOptional()
     * @see #setOptional(boolean)
     * @generated
     */
    void unsetOptional();

    /**
     * Returns whether the value of the '{@link org.bonitasoft.bpm.connector.model.definition.Group#isOptional <em>Optional</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Optional</em>' attribute is set.
     * @see #unsetOptional()
     * @see #isOptional()
     * @see #setOptional(boolean)
     * @generated
     */
    boolean isSetOptional();

} // Group
