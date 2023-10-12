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

import java.math.BigInteger;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TStandard Loop Characteristics</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TStandardLoopCharacteristics#getLoopCondition <em>Loop Condition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TStandardLoopCharacteristics#getLoopMaximum <em>Loop Maximum</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TStandardLoopCharacteristics#isTestBefore <em>Test Before</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTStandardLoopCharacteristics()
 * @model extendedMetaData="name='tStandardLoopCharacteristics' kind='elementOnly'"
 * @generated
 */
public interface TStandardLoopCharacteristics extends TLoopCharacteristics {

    /**
     * Returns the value of the '<em><b>Loop Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Loop Condition</em>' containment reference.
     * @see #setLoopCondition(TExpression)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTStandardLoopCharacteristics_LoopCondition()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='loopCondition' namespace='##targetNamespace'"
     * @generated
     */
    TExpression getLoopCondition();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TStandardLoopCharacteristics#getLoopCondition <em>Loop Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Loop Condition</em>' containment reference.
     * @see #getLoopCondition()
     * @generated
     */
    void setLoopCondition(TExpression value);

    /**
     * Returns the value of the '<em><b>Loop Maximum</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Loop Maximum</em>' attribute.
     * @see #setLoopMaximum(BigInteger)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTStandardLoopCharacteristics_LoopMaximum()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Integer"
     *        extendedMetaData="kind='attribute' name='loopMaximum'"
     * @generated
     */
    BigInteger getLoopMaximum();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TStandardLoopCharacteristics#getLoopMaximum <em>Loop Maximum</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Loop Maximum</em>' attribute.
     * @see #getLoopMaximum()
     * @generated
     */
    void setLoopMaximum(BigInteger value);

    /**
     * Returns the value of the '<em><b>Test Before</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Test Before</em>' attribute.
     * @see #isSetTestBefore()
     * @see #unsetTestBefore()
     * @see #setTestBefore(boolean)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTStandardLoopCharacteristics_TestBefore()
     * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='testBefore'"
     * @generated
     */
    boolean isTestBefore();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TStandardLoopCharacteristics#isTestBefore <em>Test Before</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Test Before</em>' attribute.
     * @see #isSetTestBefore()
     * @see #unsetTestBefore()
     * @see #isTestBefore()
     * @generated
     */
    void setTestBefore(boolean value);

    /**
     * Unsets the value of the '{@link org.omg.spec.bpmn.model.TStandardLoopCharacteristics#isTestBefore <em>Test Before</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetTestBefore()
     * @see #isTestBefore()
     * @see #setTestBefore(boolean)
     * @generated
     */
    void unsetTestBefore();

    /**
     * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TStandardLoopCharacteristics#isTestBefore <em>Test Before</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Test Before</em>' attribute is set.
     * @see #unsetTestBefore()
     * @see #isTestBefore()
     * @see #setTestBefore(boolean)
     * @generated
     */
    boolean isSetTestBefore();

} // TStandardLoopCharacteristics
