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
 * A representation of the model object '<em><b>Script Editor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.ScriptEditor#getInterpreter <em>Interpreter</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getScriptEditor()
 * @model extendedMetaData="name='ScriptEditor' kind='elementOnly'"
 * @generated
 */
public interface ScriptEditor extends Widget {
    /**
     * Returns the value of the '<em><b>Interpreter</b></em>' attribute.
     * The default value is <code>"GROOVY"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Interpreter</em>' attribute.
     * @see #isSetInterpreter()
     * @see #unsetInterpreter()
     * @see #setInterpreter(String)
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#getScriptEditor_Interpreter()
     * @model default="GROOVY" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='attribute' name='interpreter'"
     * @generated
     */
    String getInterpreter();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.connector.model.definition.ScriptEditor#getInterpreter <em>Interpreter</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Interpreter</em>' attribute.
     * @see #isSetInterpreter()
     * @see #unsetInterpreter()
     * @see #getInterpreter()
     * @generated
     */
    void setInterpreter(String value);

    /**
     * Unsets the value of the '{@link org.bonitasoft.bpm.connector.model.definition.ScriptEditor#getInterpreter <em>Interpreter</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetInterpreter()
     * @see #getInterpreter()
     * @see #setInterpreter(String)
     * @generated
     */
    void unsetInterpreter();

    /**
     * Returns whether the value of the '{@link org.bonitasoft.bpm.connector.model.definition.ScriptEditor#getInterpreter <em>Interpreter</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Interpreter</em>' attribute is set.
     * @see #unsetInterpreter()
     * @see #getInterpreter()
     * @see #setInterpreter(String)
     * @generated
     */
    boolean isSetInterpreter();

} // ScriptEditor
