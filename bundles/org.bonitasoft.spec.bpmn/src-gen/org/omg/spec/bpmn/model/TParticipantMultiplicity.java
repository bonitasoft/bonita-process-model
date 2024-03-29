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
 * A representation of the model object '<em><b>TParticipant Multiplicity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TParticipantMultiplicity#getMaximum <em>Maximum</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TParticipantMultiplicity#getMinimum <em>Minimum</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTParticipantMultiplicity()
 * @model extendedMetaData="name='tParticipantMultiplicity' kind='elementOnly'"
 * @generated
 */
public interface TParticipantMultiplicity extends TBaseElement {

    /**
     * Returns the value of the '<em><b>Maximum</b></em>' attribute.
     * The default value is <code>"1"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Maximum</em>' attribute.
     * @see #isSetMaximum()
     * @see #unsetMaximum()
     * @see #setMaximum(int)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTParticipantMultiplicity_Maximum()
     * @model default="1" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
     *        extendedMetaData="kind='attribute' name='maximum'"
     * @generated
     */
    int getMaximum();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TParticipantMultiplicity#getMaximum <em>Maximum</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Maximum</em>' attribute.
     * @see #isSetMaximum()
     * @see #unsetMaximum()
     * @see #getMaximum()
     * @generated
     */
    void setMaximum(int value);

    /**
     * Unsets the value of the '{@link org.omg.spec.bpmn.model.TParticipantMultiplicity#getMaximum <em>Maximum</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetMaximum()
     * @see #getMaximum()
     * @see #setMaximum(int)
     * @generated
     */
    void unsetMaximum();

    /**
     * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TParticipantMultiplicity#getMaximum <em>Maximum</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Maximum</em>' attribute is set.
     * @see #unsetMaximum()
     * @see #getMaximum()
     * @see #setMaximum(int)
     * @generated
     */
    boolean isSetMaximum();

    /**
     * Returns the value of the '<em><b>Minimum</b></em>' attribute.
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Minimum</em>' attribute.
     * @see #isSetMinimum()
     * @see #unsetMinimum()
     * @see #setMinimum(int)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTParticipantMultiplicity_Minimum()
     * @model default="0" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
     *        extendedMetaData="kind='attribute' name='minimum'"
     * @generated
     */
    int getMinimum();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TParticipantMultiplicity#getMinimum <em>Minimum</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Minimum</em>' attribute.
     * @see #isSetMinimum()
     * @see #unsetMinimum()
     * @see #getMinimum()
     * @generated
     */
    void setMinimum(int value);

    /**
     * Unsets the value of the '{@link org.omg.spec.bpmn.model.TParticipantMultiplicity#getMinimum <em>Minimum</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetMinimum()
     * @see #getMinimum()
     * @see #setMinimum(int)
     * @generated
     */
    void unsetMinimum();

    /**
     * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TParticipantMultiplicity#getMinimum <em>Minimum</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Minimum</em>' attribute is set.
     * @see #unsetMinimum()
     * @see #getMinimum()
     * @see #setMinimum(int)
     * @generated
     */
    boolean isSetMinimum();

} // TParticipantMultiplicity
