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
 * A representation of the model object '<em><b>Bounds</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.dd.dc.Bounds#getHeight <em>Height</em>}</li>
 *   <li>{@link org.omg.spec.dd.dc.Bounds#getWidth <em>Width</em>}</li>
 *   <li>{@link org.omg.spec.dd.dc.Bounds#getX <em>X</em>}</li>
 *   <li>{@link org.omg.spec.dd.dc.Bounds#getY <em>Y</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.dd.dc.DcPackage#getBounds()
 * @model extendedMetaData="name='Bounds' kind='empty'"
 * @generated
 */
public interface Bounds extends EObject {

    /**
     * Returns the value of the '<em><b>Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Height</em>' attribute.
     * @see #isSetHeight()
     * @see #unsetHeight()
     * @see #setHeight(double)
     * @see org.omg.spec.dd.dc.DcPackage#getBounds_Height()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
     *        extendedMetaData="kind='attribute' name='height'"
     * @generated
     */
    double getHeight();

    /**
     * Sets the value of the '{@link org.omg.spec.dd.dc.Bounds#getHeight <em>Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Height</em>' attribute.
     * @see #isSetHeight()
     * @see #unsetHeight()
     * @see #getHeight()
     * @generated
     */
    void setHeight(double value);

    /**
     * Unsets the value of the '{@link org.omg.spec.dd.dc.Bounds#getHeight <em>Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetHeight()
     * @see #getHeight()
     * @see #setHeight(double)
     * @generated
     */
    void unsetHeight();

    /**
     * Returns whether the value of the '{@link org.omg.spec.dd.dc.Bounds#getHeight <em>Height</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Height</em>' attribute is set.
     * @see #unsetHeight()
     * @see #getHeight()
     * @see #setHeight(double)
     * @generated
     */
    boolean isSetHeight();

    /**
     * Returns the value of the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Width</em>' attribute.
     * @see #isSetWidth()
     * @see #unsetWidth()
     * @see #setWidth(double)
     * @see org.omg.spec.dd.dc.DcPackage#getBounds_Width()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
     *        extendedMetaData="kind='attribute' name='width'"
     * @generated
     */
    double getWidth();

    /**
     * Sets the value of the '{@link org.omg.spec.dd.dc.Bounds#getWidth <em>Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Width</em>' attribute.
     * @see #isSetWidth()
     * @see #unsetWidth()
     * @see #getWidth()
     * @generated
     */
    void setWidth(double value);

    /**
     * Unsets the value of the '{@link org.omg.spec.dd.dc.Bounds#getWidth <em>Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetWidth()
     * @see #getWidth()
     * @see #setWidth(double)
     * @generated
     */
    void unsetWidth();

    /**
     * Returns whether the value of the '{@link org.omg.spec.dd.dc.Bounds#getWidth <em>Width</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Width</em>' attribute is set.
     * @see #unsetWidth()
     * @see #getWidth()
     * @see #setWidth(double)
     * @generated
     */
    boolean isSetWidth();

    /**
     * Returns the value of the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>X</em>' attribute.
     * @see #isSetX()
     * @see #unsetX()
     * @see #setX(double)
     * @see org.omg.spec.dd.dc.DcPackage#getBounds_X()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
     *        extendedMetaData="kind='attribute' name='x'"
     * @generated
     */
    double getX();

    /**
     * Sets the value of the '{@link org.omg.spec.dd.dc.Bounds#getX <em>X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>X</em>' attribute.
     * @see #isSetX()
     * @see #unsetX()
     * @see #getX()
     * @generated
     */
    void setX(double value);

    /**
     * Unsets the value of the '{@link org.omg.spec.dd.dc.Bounds#getX <em>X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetX()
     * @see #getX()
     * @see #setX(double)
     * @generated
     */
    void unsetX();

    /**
     * Returns whether the value of the '{@link org.omg.spec.dd.dc.Bounds#getX <em>X</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>X</em>' attribute is set.
     * @see #unsetX()
     * @see #getX()
     * @see #setX(double)
     * @generated
     */
    boolean isSetX();

    /**
     * Returns the value of the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Y</em>' attribute.
     * @see #isSetY()
     * @see #unsetY()
     * @see #setY(double)
     * @see org.omg.spec.dd.dc.DcPackage#getBounds_Y()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
     *        extendedMetaData="kind='attribute' name='y'"
     * @generated
     */
    double getY();

    /**
     * Sets the value of the '{@link org.omg.spec.dd.dc.Bounds#getY <em>Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Y</em>' attribute.
     * @see #isSetY()
     * @see #unsetY()
     * @see #getY()
     * @generated
     */
    void setY(double value);

    /**
     * Unsets the value of the '{@link org.omg.spec.dd.dc.Bounds#getY <em>Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetY()
     * @see #getY()
     * @see #setY(double)
     * @generated
     */
    void unsetY();

    /**
     * Returns whether the value of the '{@link org.omg.spec.dd.dc.Bounds#getY <em>Y</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Y</em>' attribute is set.
     * @see #unsetY()
     * @see #getY()
     * @see #setY(double)
     * @generated
     */
    boolean isSetY();

} // Bounds
