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
 * A representation of the model object '<em><b>TItem Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TItemDefinition#isIsCollection <em>Is Collection</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TItemDefinition#getItemKind <em>Item Kind</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TItemDefinition#getStructureRef <em>Structure Ref</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTItemDefinition()
 * @model extendedMetaData="name='tItemDefinition' kind='elementOnly'"
 * @generated
 */
public interface TItemDefinition extends TRootElement {

    /**
     * Returns the value of the '<em><b>Is Collection</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Collection</em>' attribute.
     * @see #isSetIsCollection()
     * @see #unsetIsCollection()
     * @see #setIsCollection(boolean)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTItemDefinition_IsCollection()
     * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='isCollection'"
     * @generated
     */
    boolean isIsCollection();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TItemDefinition#isIsCollection <em>Is Collection</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Collection</em>' attribute.
     * @see #isSetIsCollection()
     * @see #unsetIsCollection()
     * @see #isIsCollection()
     * @generated
     */
    void setIsCollection(boolean value);

    /**
     * Unsets the value of the '{@link org.omg.spec.bpmn.model.TItemDefinition#isIsCollection <em>Is Collection</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetIsCollection()
     * @see #isIsCollection()
     * @see #setIsCollection(boolean)
     * @generated
     */
    void unsetIsCollection();

    /**
     * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TItemDefinition#isIsCollection <em>Is Collection</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Is Collection</em>' attribute is set.
     * @see #unsetIsCollection()
     * @see #isIsCollection()
     * @see #setIsCollection(boolean)
     * @generated
     */
    boolean isSetIsCollection();

    /**
     * Returns the value of the '<em><b>Item Kind</b></em>' attribute.
     * The default value is <code>"Information"</code>.
     * The literals are from the enumeration {@link org.omg.spec.bpmn.model.TItemKind}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Item Kind</em>' attribute.
     * @see org.omg.spec.bpmn.model.TItemKind
     * @see #isSetItemKind()
     * @see #unsetItemKind()
     * @see #setItemKind(TItemKind)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTItemDefinition_ItemKind()
     * @model default="Information" unsettable="true"
     *        extendedMetaData="kind='attribute' name='itemKind'"
     * @generated
     */
    TItemKind getItemKind();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TItemDefinition#getItemKind <em>Item Kind</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Item Kind</em>' attribute.
     * @see org.omg.spec.bpmn.model.TItemKind
     * @see #isSetItemKind()
     * @see #unsetItemKind()
     * @see #getItemKind()
     * @generated
     */
    void setItemKind(TItemKind value);

    /**
     * Unsets the value of the '{@link org.omg.spec.bpmn.model.TItemDefinition#getItemKind <em>Item Kind</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetItemKind()
     * @see #getItemKind()
     * @see #setItemKind(TItemKind)
     * @generated
     */
    void unsetItemKind();

    /**
     * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TItemDefinition#getItemKind <em>Item Kind</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Item Kind</em>' attribute is set.
     * @see #unsetItemKind()
     * @see #getItemKind()
     * @see #setItemKind(TItemKind)
     * @generated
     */
    boolean isSetItemKind();

    /**
     * Returns the value of the '<em><b>Structure Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Structure Ref</em>' attribute.
     * @see #setStructureRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTItemDefinition_StructureRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='structureRef'"
     * @generated
     */
    QName getStructureRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TItemDefinition#getStructureRef <em>Structure Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Structure Ref</em>' attribute.
     * @see #getStructureRef()
     * @generated
     */
    void setStructureRef(QName value);

} // TItemDefinition
