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
 * A representation of the model object '<em><b>TSequence Flow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TSequenceFlow#getConditionExpression <em>Condition Expression</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TSequenceFlow#isIsImmediate <em>Is Immediate</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TSequenceFlow#getSourceRef <em>Source Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TSequenceFlow#getTargetRef <em>Target Ref</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTSequenceFlow()
 * @model extendedMetaData="name='tSequenceFlow' kind='elementOnly'"
 * @generated
 */
public interface TSequenceFlow extends TFlowElement {

    /**
     * Returns the value of the '<em><b>Condition Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Condition Expression</em>' containment reference.
     * @see #setConditionExpression(TExpression)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTSequenceFlow_ConditionExpression()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='conditionExpression' namespace='##targetNamespace'"
     * @generated
     */
    TExpression getConditionExpression();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TSequenceFlow#getConditionExpression <em>Condition Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Condition Expression</em>' containment reference.
     * @see #getConditionExpression()
     * @generated
     */
    void setConditionExpression(TExpression value);

    /**
     * Returns the value of the '<em><b>Is Immediate</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Immediate</em>' attribute.
     * @see #isSetIsImmediate()
     * @see #unsetIsImmediate()
     * @see #setIsImmediate(boolean)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTSequenceFlow_IsImmediate()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='isImmediate'"
     * @generated
     */
    boolean isIsImmediate();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TSequenceFlow#isIsImmediate <em>Is Immediate</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Immediate</em>' attribute.
     * @see #isSetIsImmediate()
     * @see #unsetIsImmediate()
     * @see #isIsImmediate()
     * @generated
     */
    void setIsImmediate(boolean value);

    /**
     * Unsets the value of the '{@link org.omg.spec.bpmn.model.TSequenceFlow#isIsImmediate <em>Is Immediate</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetIsImmediate()
     * @see #isIsImmediate()
     * @see #setIsImmediate(boolean)
     * @generated
     */
    void unsetIsImmediate();

    /**
     * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TSequenceFlow#isIsImmediate <em>Is Immediate</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Is Immediate</em>' attribute is set.
     * @see #unsetIsImmediate()
     * @see #isIsImmediate()
     * @see #setIsImmediate(boolean)
     * @generated
     */
    boolean isSetIsImmediate();

    /**
     * Returns the value of the '<em><b>Source Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Ref</em>' attribute.
     * @see #setSourceRef(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTSequenceFlow_SourceRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF" required="true"
     *        extendedMetaData="kind='attribute' name='sourceRef'"
     * @generated
     */
    String getSourceRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TSequenceFlow#getSourceRef <em>Source Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Ref</em>' attribute.
     * @see #getSourceRef()
     * @generated
     */
    void setSourceRef(String value);

    /**
     * Returns the value of the '<em><b>Target Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Ref</em>' attribute.
     * @see #setTargetRef(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTSequenceFlow_TargetRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF" required="true"
     *        extendedMetaData="kind='attribute' name='targetRef'"
     * @generated
     */
    String getTargetRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TSequenceFlow#getTargetRef <em>Target Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Ref</em>' attribute.
     * @see #getTargetRef()
     * @generated
     */
    void setTargetRef(String value);

} // TSequenceFlow
