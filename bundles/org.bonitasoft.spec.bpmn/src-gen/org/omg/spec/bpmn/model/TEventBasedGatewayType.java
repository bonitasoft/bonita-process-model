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
 * A representation of the literals of the enumeration '<em><b>TEvent Based Gateway Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.omg.spec.bpmn.model.ModelPackage#getTEventBasedGatewayType()
 * @model extendedMetaData="name='tEventBasedGatewayType'"
 * @generated
 */
public enum TEventBasedGatewayType implements Enumerator {

    /**
     * The '<em><b>Exclusive</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EXCLUSIVE_VALUE
     * @generated
     * @ordered
     */
    EXCLUSIVE(0, "Exclusive", "Exclusive"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Parallel</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PARALLEL_VALUE
     * @generated
     * @ordered
     */
    PARALLEL(1, "Parallel", "Parallel"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Exclusive</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EXCLUSIVE
     * @model name="Exclusive"
     * @generated
     * @ordered
     */
    public static final int EXCLUSIVE_VALUE = 0;

    /**
     * The '<em><b>Parallel</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PARALLEL
     * @model name="Parallel"
     * @generated
     * @ordered
     */
    public static final int PARALLEL_VALUE = 1;

    /**
     * An array of all the '<em><b>TEvent Based Gateway Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final TEventBasedGatewayType[] VALUES_ARRAY = new TEventBasedGatewayType[] {
            EXCLUSIVE,
            PARALLEL,
    };

    /**
     * A public read-only list of all the '<em><b>TEvent Based Gateway Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<TEventBasedGatewayType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>TEvent Based Gateway Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param literal the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static TEventBasedGatewayType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            TEventBasedGatewayType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>TEvent Based Gateway Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param name the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static TEventBasedGatewayType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            TEventBasedGatewayType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>TEvent Based Gateway Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static TEventBasedGatewayType get(int value) {
        switch (value) {
            case EXCLUSIVE_VALUE:
                return EXCLUSIVE;
            case PARALLEL_VALUE:
                return PARALLEL;
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
    private TEventBasedGatewayType(int value, String name, String literal) {
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

} //TEventBasedGatewayType
