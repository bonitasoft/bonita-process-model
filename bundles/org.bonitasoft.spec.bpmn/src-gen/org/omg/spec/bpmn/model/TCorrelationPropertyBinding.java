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
 * A representation of the model object '<em><b>TCorrelation Property Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TCorrelationPropertyBinding#getDataPath <em>Data Path</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCorrelationPropertyBinding#getCorrelationPropertyRef <em>Correlation Property Ref</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTCorrelationPropertyBinding()
 * @model extendedMetaData="name='tCorrelationPropertyBinding' kind='elementOnly'"
 * @generated
 */
public interface TCorrelationPropertyBinding extends TBaseElement {

    /**
     * Returns the value of the '<em><b>Data Path</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Path</em>' containment reference.
     * @see #setDataPath(TFormalExpression)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTCorrelationPropertyBinding_DataPath()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='dataPath' namespace='##targetNamespace'"
     * @generated
     */
    TFormalExpression getDataPath();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TCorrelationPropertyBinding#getDataPath <em>Data Path</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Path</em>' containment reference.
     * @see #getDataPath()
     * @generated
     */
    void setDataPath(TFormalExpression value);

    /**
     * Returns the value of the '<em><b>Correlation Property Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Correlation Property Ref</em>' attribute.
     * @see #setCorrelationPropertyRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTCorrelationPropertyBinding_CorrelationPropertyRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName" required="true"
     *        extendedMetaData="kind='attribute' name='correlationPropertyRef'"
     * @generated
     */
    QName getCorrelationPropertyRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TCorrelationPropertyBinding#getCorrelationPropertyRef <em>Correlation Property Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Correlation Property Ref</em>' attribute.
     * @see #getCorrelationPropertyRef()
     * @generated
     */
    void setCorrelationPropertyRef(QName value);

} // TCorrelationPropertyBinding
