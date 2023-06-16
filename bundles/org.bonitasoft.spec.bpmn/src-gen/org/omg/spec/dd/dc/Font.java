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
package org.omg.spec.dd.dc;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Font</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.dd.dc.Font#isIsBold <em>Is Bold</em>}</li>
 *   <li>{@link org.omg.spec.dd.dc.Font#isIsItalic <em>Is Italic</em>}</li>
 *   <li>{@link org.omg.spec.dd.dc.Font#isIsStrikeThrough <em>Is Strike Through</em>}</li>
 *   <li>{@link org.omg.spec.dd.dc.Font#isIsUnderline <em>Is Underline</em>}</li>
 *   <li>{@link org.omg.spec.dd.dc.Font#getName <em>Name</em>}</li>
 *   <li>{@link org.omg.spec.dd.dc.Font#getSize <em>Size</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.dd.dc.DcPackage#getFont()
 * @model extendedMetaData="name='Font' kind='empty'"
 * @generated
 */
public interface Font extends EObject {

    /**
     * Returns the value of the '<em><b>Is Bold</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Bold</em>' attribute.
     * @see #isSetIsBold()
     * @see #unsetIsBold()
     * @see #setIsBold(boolean)
     * @see org.omg.spec.dd.dc.DcPackage#getFont_IsBold()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='isBold'"
     * @generated
     */
    boolean isIsBold();

    /**
     * Sets the value of the '{@link org.omg.spec.dd.dc.Font#isIsBold <em>Is Bold</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Bold</em>' attribute.
     * @see #isSetIsBold()
     * @see #unsetIsBold()
     * @see #isIsBold()
     * @generated
     */
    void setIsBold(boolean value);

    /**
     * Unsets the value of the '{@link org.omg.spec.dd.dc.Font#isIsBold <em>Is Bold</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetIsBold()
     * @see #isIsBold()
     * @see #setIsBold(boolean)
     * @generated
     */
    void unsetIsBold();

    /**
     * Returns whether the value of the '{@link org.omg.spec.dd.dc.Font#isIsBold <em>Is Bold</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Is Bold</em>' attribute is set.
     * @see #unsetIsBold()
     * @see #isIsBold()
     * @see #setIsBold(boolean)
     * @generated
     */
    boolean isSetIsBold();

    /**
     * Returns the value of the '<em><b>Is Italic</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Italic</em>' attribute.
     * @see #isSetIsItalic()
     * @see #unsetIsItalic()
     * @see #setIsItalic(boolean)
     * @see org.omg.spec.dd.dc.DcPackage#getFont_IsItalic()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='isItalic'"
     * @generated
     */
    boolean isIsItalic();

    /**
     * Sets the value of the '{@link org.omg.spec.dd.dc.Font#isIsItalic <em>Is Italic</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Italic</em>' attribute.
     * @see #isSetIsItalic()
     * @see #unsetIsItalic()
     * @see #isIsItalic()
     * @generated
     */
    void setIsItalic(boolean value);

    /**
     * Unsets the value of the '{@link org.omg.spec.dd.dc.Font#isIsItalic <em>Is Italic</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetIsItalic()
     * @see #isIsItalic()
     * @see #setIsItalic(boolean)
     * @generated
     */
    void unsetIsItalic();

    /**
     * Returns whether the value of the '{@link org.omg.spec.dd.dc.Font#isIsItalic <em>Is Italic</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Is Italic</em>' attribute is set.
     * @see #unsetIsItalic()
     * @see #isIsItalic()
     * @see #setIsItalic(boolean)
     * @generated
     */
    boolean isSetIsItalic();

    /**
     * Returns the value of the '<em><b>Is Strike Through</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Strike Through</em>' attribute.
     * @see #isSetIsStrikeThrough()
     * @see #unsetIsStrikeThrough()
     * @see #setIsStrikeThrough(boolean)
     * @see org.omg.spec.dd.dc.DcPackage#getFont_IsStrikeThrough()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='isStrikeThrough'"
     * @generated
     */
    boolean isIsStrikeThrough();

    /**
     * Sets the value of the '{@link org.omg.spec.dd.dc.Font#isIsStrikeThrough <em>Is Strike Through</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Strike Through</em>' attribute.
     * @see #isSetIsStrikeThrough()
     * @see #unsetIsStrikeThrough()
     * @see #isIsStrikeThrough()
     * @generated
     */
    void setIsStrikeThrough(boolean value);

    /**
     * Unsets the value of the '{@link org.omg.spec.dd.dc.Font#isIsStrikeThrough <em>Is Strike Through</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetIsStrikeThrough()
     * @see #isIsStrikeThrough()
     * @see #setIsStrikeThrough(boolean)
     * @generated
     */
    void unsetIsStrikeThrough();

    /**
     * Returns whether the value of the '{@link org.omg.spec.dd.dc.Font#isIsStrikeThrough <em>Is Strike Through</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Is Strike Through</em>' attribute is set.
     * @see #unsetIsStrikeThrough()
     * @see #isIsStrikeThrough()
     * @see #setIsStrikeThrough(boolean)
     * @generated
     */
    boolean isSetIsStrikeThrough();

    /**
     * Returns the value of the '<em><b>Is Underline</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Underline</em>' attribute.
     * @see #isSetIsUnderline()
     * @see #unsetIsUnderline()
     * @see #setIsUnderline(boolean)
     * @see org.omg.spec.dd.dc.DcPackage#getFont_IsUnderline()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='isUnderline'"
     * @generated
     */
    boolean isIsUnderline();

    /**
     * Sets the value of the '{@link org.omg.spec.dd.dc.Font#isIsUnderline <em>Is Underline</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Is Underline</em>' attribute.
     * @see #isSetIsUnderline()
     * @see #unsetIsUnderline()
     * @see #isIsUnderline()
     * @generated
     */
    void setIsUnderline(boolean value);

    /**
     * Unsets the value of the '{@link org.omg.spec.dd.dc.Font#isIsUnderline <em>Is Underline</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetIsUnderline()
     * @see #isIsUnderline()
     * @see #setIsUnderline(boolean)
     * @generated
     */
    void unsetIsUnderline();

    /**
     * Returns whether the value of the '{@link org.omg.spec.dd.dc.Font#isIsUnderline <em>Is Underline</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Is Underline</em>' attribute is set.
     * @see #unsetIsUnderline()
     * @see #isIsUnderline()
     * @see #setIsUnderline(boolean)
     * @generated
     */
    boolean isSetIsUnderline();

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.omg.spec.dd.dc.DcPackage#getFont_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='attribute' name='name'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.omg.spec.dd.dc.Font#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Size</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Size</em>' attribute.
     * @see #isSetSize()
     * @see #unsetSize()
     * @see #setSize(double)
     * @see org.omg.spec.dd.dc.DcPackage#getFont_Size()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double"
     *        extendedMetaData="kind='attribute' name='size'"
     * @generated
     */
    double getSize();

    /**
     * Sets the value of the '{@link org.omg.spec.dd.dc.Font#getSize <em>Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Size</em>' attribute.
     * @see #isSetSize()
     * @see #unsetSize()
     * @see #getSize()
     * @generated
     */
    void setSize(double value);

    /**
     * Unsets the value of the '{@link org.omg.spec.dd.dc.Font#getSize <em>Size</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetSize()
     * @see #getSize()
     * @see #setSize(double)
     * @generated
     */
    void unsetSize();

    /**
     * Returns whether the value of the '{@link org.omg.spec.dd.dc.Font#getSize <em>Size</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Size</em>' attribute is set.
     * @see #unsetSize()
     * @see #getSize()
     * @see #setSize(double)
     * @generated
     */
    boolean isSetSize();

} // Font
