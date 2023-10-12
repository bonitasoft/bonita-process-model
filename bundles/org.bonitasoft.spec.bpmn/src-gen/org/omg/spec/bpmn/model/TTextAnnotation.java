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
 * A representation of the model object '<em><b>TText Annotation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TTextAnnotation#getText <em>Text</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TTextAnnotation#getTextFormat <em>Text Format</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTTextAnnotation()
 * @model extendedMetaData="name='tTextAnnotation' kind='elementOnly'"
 * @generated
 */
public interface TTextAnnotation extends TArtifact {

    /**
     * Returns the value of the '<em><b>Text</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Text</em>' containment reference.
     * @see #setText(TText)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTTextAnnotation_Text()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='text' namespace='##targetNamespace'"
     * @generated
     */
    TText getText();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TTextAnnotation#getText <em>Text</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text</em>' containment reference.
     * @see #getText()
     * @generated
     */
    void setText(TText value);

    /**
     * Returns the value of the '<em><b>Text Format</b></em>' attribute.
     * The default value is <code>"text/plain"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Text Format</em>' attribute.
     * @see #isSetTextFormat()
     * @see #unsetTextFormat()
     * @see #setTextFormat(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTTextAnnotation_TextFormat()
     * @model default="text/plain" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='textFormat'"
     * @generated
     */
    String getTextFormat();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TTextAnnotation#getTextFormat <em>Text Format</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Text Format</em>' attribute.
     * @see #isSetTextFormat()
     * @see #unsetTextFormat()
     * @see #getTextFormat()
     * @generated
     */
    void setTextFormat(String value);

    /**
     * Unsets the value of the '{@link org.omg.spec.bpmn.model.TTextAnnotation#getTextFormat <em>Text Format</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetTextFormat()
     * @see #getTextFormat()
     * @see #setTextFormat(String)
     * @generated
     */
    void unsetTextFormat();

    /**
     * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TTextAnnotation#getTextFormat <em>Text Format</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Text Format</em>' attribute is set.
     * @see #unsetTextFormat()
     * @see #getTextFormat()
     * @see #setTextFormat(String)
     * @generated
     */
    boolean isSetTextFormat();

} // TTextAnnotation
