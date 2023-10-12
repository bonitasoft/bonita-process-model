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
 * A representation of the model object '<em><b>TCorrelation Subscription</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TCorrelationSubscription#getCorrelationPropertyBinding <em>Correlation Property Binding</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCorrelationSubscription#getCorrelationKeyRef <em>Correlation Key Ref</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTCorrelationSubscription()
 * @model extendedMetaData="name='tCorrelationSubscription' kind='elementOnly'"
 * @generated
 */
public interface TCorrelationSubscription extends TBaseElement {

    /**
     * Returns the value of the '<em><b>Correlation Property Binding</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.bpmn.model.TCorrelationPropertyBinding}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Correlation Property Binding</em>' containment reference list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTCorrelationSubscription_CorrelationPropertyBinding()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='correlationPropertyBinding' namespace='##targetNamespace'"
     * @generated
     */
    EList<TCorrelationPropertyBinding> getCorrelationPropertyBinding();

    /**
     * Returns the value of the '<em><b>Correlation Key Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Correlation Key Ref</em>' attribute.
     * @see #setCorrelationKeyRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTCorrelationSubscription_CorrelationKeyRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
     *        extendedMetaData="kind='attribute' name='correlationKeyRef'"
     * @generated
     */
    QName getCorrelationKeyRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TCorrelationSubscription#getCorrelationKeyRef <em>Correlation Key Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Correlation Key Ref</em>' attribute.
     * @see #getCorrelationKeyRef()
     * @generated
     */
    void setCorrelationKeyRef(QName value);

} // TCorrelationSubscription
