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
package org.omg.spec.bpmn.model;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TScript Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TScriptTask#getScript <em>Script</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TScriptTask#getScriptFormat <em>Script Format</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTScriptTask()
 * @model extendedMetaData="name='tScriptTask' kind='elementOnly'"
 * @generated
 */
public interface TScriptTask extends TTask {

    /**
     * Returns the value of the '<em><b>Script</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Script</em>' containment reference.
     * @see #setScript(TScript)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTScriptTask_Script()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='script' namespace='##targetNamespace'"
     * @generated
     */
    TScript getScript();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TScriptTask#getScript <em>Script</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Script</em>' containment reference.
     * @see #getScript()
     * @generated
     */
    void setScript(TScript value);

    /**
     * Returns the value of the '<em><b>Script Format</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Script Format</em>' attribute.
     * @see #setScriptFormat(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTScriptTask_ScriptFormat()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='scriptFormat'"
     * @generated
     */
    String getScriptFormat();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TScriptTask#getScriptFormat <em>Script Format</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Script Format</em>' attribute.
     * @see #getScriptFormat()
     * @generated
     */
    void setScriptFormat(String value);

} // TScriptTask
