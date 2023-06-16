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

import javax.xml.namespace.QName;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TCorrelation Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TCorrelationProperty#getCorrelationPropertyRetrievalExpression <em>Correlation Property Retrieval Expression</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCorrelationProperty#getName <em>Name</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCorrelationProperty#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTCorrelationProperty()
 * @model extendedMetaData="name='tCorrelationProperty' kind='elementOnly'"
 * @generated
 */
public interface TCorrelationProperty extends TRootElement {

    /**
     * Returns the value of the '<em><b>Correlation Property Retrieval Expression</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.bpmn.model.TCorrelationPropertyRetrievalExpression}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Correlation Property Retrieval Expression</em>' containment reference list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTCorrelationProperty_CorrelationPropertyRetrievalExpression()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='correlationPropertyRetrievalExpression' namespace='##targetNamespace'"
     * @generated
     */
    EList<TCorrelationPropertyRetrievalExpression> getCorrelationPropertyRetrievalExpression();

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTCorrelationProperty_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TCorrelationProperty#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see #setType(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTCorrelationProperty_Type()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='type'"
     * @generated
     */
    QName getType();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TCorrelationProperty#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see #getType()
     * @generated
     */
    void setType(QName value);

} // TCorrelationProperty
