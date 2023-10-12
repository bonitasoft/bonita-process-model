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
 * A representation of the model object '<em><b>TFormal Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TFormalExpression#getEvaluatesToTypeRef <em>Evaluates To Type Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TFormalExpression#getLanguage <em>Language</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTFormalExpression()
 * @model extendedMetaData="name='tFormalExpression' kind='mixed'"
 * @generated
 */
public interface TFormalExpression extends TExpression {

    /**
     * Returns the value of the '<em><b>Evaluates To Type Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Evaluates To Type Ref</em>' attribute.
     * @see #setEvaluatesToTypeRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTFormalExpression_EvaluatesToTypeRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='evaluatesToTypeRef'"
     * @generated
     */
    QName getEvaluatesToTypeRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TFormalExpression#getEvaluatesToTypeRef <em>Evaluates To Type Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Evaluates To Type Ref</em>' attribute.
     * @see #getEvaluatesToTypeRef()
     * @generated
     */
    void setEvaluatesToTypeRef(QName value);

    /**
     * Returns the value of the '<em><b>Language</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Language</em>' attribute.
     * @see #setLanguage(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTFormalExpression_Language()
     * @model dataType="org.eclipse.emf.ecore.xml.type.AnyURI"
     *        extendedMetaData="kind='attribute' name='language'"
     * @generated
     */
    String getLanguage();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TFormalExpression#getLanguage <em>Language</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Language</em>' attribute.
     * @see #getLanguage()
     * @generated
     */
    void setLanguage(String value);

} // TFormalExpression
