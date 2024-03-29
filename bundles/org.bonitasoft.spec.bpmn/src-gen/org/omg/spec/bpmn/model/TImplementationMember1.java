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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>TImplementation Member1</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.omg.spec.bpmn.model.ModelPackage#getTImplementationMember1()
 * @model extendedMetaData="name='tImplementation_._member_._1'"
 * @generated
 */
public enum TImplementationMember1 implements Enumerator {

    /**
     * The '<em><b>Unspecified</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #UNSPECIFIED_VALUE
     * @generated
     * @ordered
     */
    UNSPECIFIED(0, "unspecified", "##unspecified"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Web Service</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #WEB_SERVICE_VALUE
     * @generated
     * @ordered
     */
    WEB_SERVICE(1, "WebService", "##WebService"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Unspecified</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #UNSPECIFIED
     * @model name="unspecified" literal="##unspecified"
     * @generated
     * @ordered
     */
    public static final int UNSPECIFIED_VALUE = 0;

    /**
     * The '<em><b>Web Service</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #WEB_SERVICE
     * @model name="WebService" literal="##WebService"
     * @generated
     * @ordered
     */
    public static final int WEB_SERVICE_VALUE = 1;

    /**
     * An array of all the '<em><b>TImplementation Member1</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final TImplementationMember1[] VALUES_ARRAY = new TImplementationMember1[] {
            UNSPECIFIED,
            WEB_SERVICE,
    };

    /**
     * A public read-only list of all the '<em><b>TImplementation Member1</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<TImplementationMember1> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>TImplementation Member1</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param literal the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static TImplementationMember1 get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            TImplementationMember1 result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>TImplementation Member1</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param name the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static TImplementationMember1 getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            TImplementationMember1 result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>TImplementation Member1</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static TImplementationMember1 get(int value) {
        switch (value) {
            case UNSPECIFIED_VALUE:
                return UNSPECIFIED;
            case WEB_SERVICE_VALUE:
                return WEB_SERVICE;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private TImplementationMember1(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getLiteral() {
        return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string representation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }

} //TImplementationMember1
