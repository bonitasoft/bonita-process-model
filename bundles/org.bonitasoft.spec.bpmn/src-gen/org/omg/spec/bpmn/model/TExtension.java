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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TExtension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TExtension#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TExtension#getDefinition <em>Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TExtension#isMustUnderstand <em>Must Understand</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTExtension()
 * @model extendedMetaData="name='tExtension' kind='elementOnly'"
 * @generated
 */
public interface TExtension extends EObject {

    /**
     * Returns the value of the '<em><b>Documentation</b></em>' containment reference list.
     * The list contents are of type {@link org.omg.spec.bpmn.model.TDocumentation}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Documentation</em>' containment reference list.
     * @see org.omg.spec.bpmn.model.ModelPackage#getTExtension_Documentation()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='documentation' namespace='##targetNamespace'"
     * @generated
     */
    EList<TDocumentation> getDocumentation();

    /**
     * Returns the value of the '<em><b>Definition</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Definition</em>' attribute.
     * @see #setDefinition(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTExtension_Definition()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='definition'"
     * @generated
     */
    QName getDefinition();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TExtension#getDefinition <em>Definition</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Definition</em>' attribute.
     * @see #getDefinition()
     * @generated
     */
    void setDefinition(QName value);

    /**
     * Returns the value of the '<em><b>Must Understand</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Must Understand</em>' attribute.
     * @see #isSetMustUnderstand()
     * @see #unsetMustUnderstand()
     * @see #setMustUnderstand(boolean)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTExtension_MustUnderstand()
     * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='mustUnderstand'"
     * @generated
     */
    boolean isMustUnderstand();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TExtension#isMustUnderstand <em>Must Understand</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Must Understand</em>' attribute.
     * @see #isSetMustUnderstand()
     * @see #unsetMustUnderstand()
     * @see #isMustUnderstand()
     * @generated
     */
    void setMustUnderstand(boolean value);

    /**
     * Unsets the value of the '{@link org.omg.spec.bpmn.model.TExtension#isMustUnderstand <em>Must Understand</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetMustUnderstand()
     * @see #isMustUnderstand()
     * @see #setMustUnderstand(boolean)
     * @generated
     */
    void unsetMustUnderstand();

    /**
     * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TExtension#isMustUnderstand <em>Must Understand</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Must Understand</em>' attribute is set.
     * @see #unsetMustUnderstand()
     * @see #isMustUnderstand()
     * @see #setMustUnderstand(boolean)
     * @generated
     */
    boolean isSetMustUnderstand();

} // TExtension
