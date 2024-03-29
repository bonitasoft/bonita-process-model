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
 * A representation of the literals of the enumeration '<em><b>TGateway Direction</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.omg.spec.bpmn.model.ModelPackage#getTGatewayDirection()
 * @model extendedMetaData="name='tGatewayDirection'"
 * @generated
 */
public enum TGatewayDirection implements Enumerator {

    /**
     * The '<em><b>Unspecified</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #UNSPECIFIED_VALUE
     * @generated
     * @ordered
     */
    UNSPECIFIED(0, "Unspecified", "Unspecified"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Converging</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CONVERGING_VALUE
     * @generated
     * @ordered
     */
    CONVERGING(1, "Converging", "Converging"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Diverging</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DIVERGING_VALUE
     * @generated
     * @ordered
     */
    DIVERGING(2, "Diverging", "Diverging"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Mixed</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MIXED_VALUE
     * @generated
     * @ordered
     */
    MIXED(3, "Mixed", "Mixed"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Unspecified</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #UNSPECIFIED
     * @model name="Unspecified"
     * @generated
     * @ordered
     */
    public static final int UNSPECIFIED_VALUE = 0;

    /**
     * The '<em><b>Converging</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CONVERGING
     * @model name="Converging"
     * @generated
     * @ordered
     */
    public static final int CONVERGING_VALUE = 1;

    /**
     * The '<em><b>Diverging</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DIVERGING
     * @model name="Diverging"
     * @generated
     * @ordered
     */
    public static final int DIVERGING_VALUE = 2;

    /**
     * The '<em><b>Mixed</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MIXED
     * @model name="Mixed"
     * @generated
     * @ordered
     */
    public static final int MIXED_VALUE = 3;

    /**
     * An array of all the '<em><b>TGateway Direction</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final TGatewayDirection[] VALUES_ARRAY = new TGatewayDirection[] {
            UNSPECIFIED,
            CONVERGING,
            DIVERGING,
            MIXED,
    };

    /**
     * A public read-only list of all the '<em><b>TGateway Direction</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<TGatewayDirection> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>TGateway Direction</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param literal the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static TGatewayDirection get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            TGatewayDirection result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>TGateway Direction</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param name the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static TGatewayDirection getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            TGatewayDirection result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>TGateway Direction</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static TGatewayDirection get(int value) {
        switch (value) {
            case UNSPECIFIED_VALUE:
                return UNSPECIFIED;
            case CONVERGING_VALUE:
                return CONVERGING;
            case DIVERGING_VALUE:
                return DIVERGING;
            case MIXED_VALUE:
                return MIXED;
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
    private TGatewayDirection(int value, String name, String literal) {
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

} //TGatewayDirection
