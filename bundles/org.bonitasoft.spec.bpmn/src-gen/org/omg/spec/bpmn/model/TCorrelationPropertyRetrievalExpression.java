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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TCorrelation Property Retrieval Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TCorrelationPropertyRetrievalExpression#getMessagePath <em>Message Path</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCorrelationPropertyRetrievalExpression#getMessageRef <em>Message Ref</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTCorrelationPropertyRetrievalExpression()
 * @model extendedMetaData="name='tCorrelationPropertyRetrievalExpression' kind='elementOnly'"
 * @generated
 */
public interface TCorrelationPropertyRetrievalExpression extends TBaseElement {

    /**
     * Returns the value of the '<em><b>Message Path</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Message Path</em>' containment reference.
     * @see #setMessagePath(TFormalExpression)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTCorrelationPropertyRetrievalExpression_MessagePath()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='messagePath' namespace='##targetNamespace'"
     * @generated
     */
    TFormalExpression getMessagePath();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TCorrelationPropertyRetrievalExpression#getMessagePath <em>Message Path</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Message Path</em>' containment reference.
     * @see #getMessagePath()
     * @generated
     */
    void setMessagePath(TFormalExpression value);

    /**
     * Returns the value of the '<em><b>Message Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Message Ref</em>' attribute.
     * @see #setMessageRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTCorrelationPropertyRetrievalExpression_MessageRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
     *        extendedMetaData="kind='attribute' name='messageRef'"
     * @generated
     */
    QName getMessageRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TCorrelationPropertyRetrievalExpression#getMessageRef <em>Message Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Message Ref</em>' attribute.
     * @see #getMessageRef()
     * @generated
     */
    void setMessageRef(QName value);

} // TCorrelationPropertyRetrievalExpression
