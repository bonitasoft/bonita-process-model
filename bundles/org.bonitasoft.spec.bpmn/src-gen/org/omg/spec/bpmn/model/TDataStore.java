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

import javax.xml.namespace.QName;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TData Store</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TDataStore#getDataState <em>Data State</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDataStore#getCapacity <em>Capacity</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDataStore#isIsUnlimited <em>Is Unlimited</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDataStore#getItemSubjectRef <em>Item Subject Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TDataStore#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTDataStore()
 * @model extendedMetaData="name='tDataStore' kind='elementOnly'"
 * @generated
 */
public interface TDataStore extends TRootElement {

    /**
     * Returns the value of the '<em><b>Data State</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data State</em>' containment reference.
     * @see #setDataState(TDataState)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTDataStore_DataState()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='dataState' namespace='##targetNamespace'"
     * @generated
     */
    TDataState getDataState();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TDataStore#getDataState <em>Data State</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data State</em>' containment reference.
     * @see #getDataState()
     * @generated
     */
    void setDataState(TDataState value);

    /**
     * Returns the value of the '<em><b>Capacity</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Capacity</em>' attribute.
     * @see #setCapacity(BigInteger)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTDataStore_Capacity()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Integer"
     *        extendedMetaData="kind='attribute' name='capacity'"
     * @generated
     */
    BigInteger getCapacity();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TDataStore#getCapacity <em>Capacity</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Capacity</em>' attribute.
     * @see #getCapacity()
     * @generated
     */
    void setCapacity(BigInteger value);

    /**
     * Returns the value of the '<em><b>Is Unlimited</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Unlimited</em>' attribute.
     * @see #isSetIsUnlimited()
     * @see #unsetIsUnlimited()
     * @see #setIsUnlimited(boolean)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTDataStore_IsUnlimited()
     * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='isUnlimited'"
     * @generated
     */
    boolean isIsUnlimited();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TDataStore#isIsUnlimited <em>Is Unlimited</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Unlimited</em>' attribute.
     * @see #isSetIsUnlimited()
     * @see #unsetIsUnlimited()
     * @see #isIsUnlimited()
     * @generated
     */
    void setIsUnlimited(boolean value);

    /**
     * Unsets the value of the '{@link org.omg.spec.bpmn.model.TDataStore#isIsUnlimited <em>Is Unlimited</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetIsUnlimited()
     * @see #isIsUnlimited()
     * @see #setIsUnlimited(boolean)
     * @generated
     */
    void unsetIsUnlimited();

    /**
     * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TDataStore#isIsUnlimited <em>Is Unlimited</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Is Unlimited</em>' attribute is set.
     * @see #unsetIsUnlimited()
     * @see #isIsUnlimited()
     * @see #setIsUnlimited(boolean)
     * @generated
     */
    boolean isSetIsUnlimited();

    /**
     * Returns the value of the '<em><b>Item Subject Ref</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Item Subject Ref</em>' attribute.
     * @see #setItemSubjectRef(QName)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTDataStore_ItemSubjectRef()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='itemSubjectRef'"
     * @generated
     */
    QName getItemSubjectRef();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TDataStore#getItemSubjectRef <em>Item Subject Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Item Subject Ref</em>' attribute.
     * @see #getItemSubjectRef()
     * @generated
     */
    void setItemSubjectRef(QName value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTDataStore_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TDataStore#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // TDataStore
