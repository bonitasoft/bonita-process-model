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
 * A representation of the literals of the enumeration '<em><b>TChoreography Loop Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.omg.spec.bpmn.model.ModelPackage#getTChoreographyLoopType()
 * @model extendedMetaData="name='tChoreographyLoopType'"
 * @generated
 */
public enum TChoreographyLoopType implements Enumerator {

    /**
     * The '<em><b>None</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NONE_VALUE
     * @generated
     * @ordered
     */
    NONE(0, "None", "None"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Standard</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #STANDARD_VALUE
     * @generated
     * @ordered
     */
    STANDARD(1, "Standard", "Standard"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Multi Instance Sequential</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MULTI_INSTANCE_SEQUENTIAL_VALUE
     * @generated
     * @ordered
     */
    MULTI_INSTANCE_SEQUENTIAL(2, "MultiInstanceSequential", "MultiInstanceSequential"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Multi Instance Parallel</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MULTI_INSTANCE_PARALLEL_VALUE
     * @generated
     * @ordered
     */
    MULTI_INSTANCE_PARALLEL(3, "MultiInstanceParallel", "MultiInstanceParallel"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>None</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NONE
     * @model name="None"
     * @generated
     * @ordered
     */
    public static final int NONE_VALUE = 0;

    /**
     * The '<em><b>Standard</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #STANDARD
     * @model name="Standard"
     * @generated
     * @ordered
     */
    public static final int STANDARD_VALUE = 1;

    /**
     * The '<em><b>Multi Instance Sequential</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MULTI_INSTANCE_SEQUENTIAL
     * @model name="MultiInstanceSequential"
     * @generated
     * @ordered
     */
    public static final int MULTI_INSTANCE_SEQUENTIAL_VALUE = 2;

    /**
     * The '<em><b>Multi Instance Parallel</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MULTI_INSTANCE_PARALLEL
     * @model name="MultiInstanceParallel"
     * @generated
     * @ordered
     */
    public static final int MULTI_INSTANCE_PARALLEL_VALUE = 3;

    /**
     * An array of all the '<em><b>TChoreography Loop Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final TChoreographyLoopType[] VALUES_ARRAY = new TChoreographyLoopType[] {
            NONE,
            STANDARD,
            MULTI_INSTANCE_SEQUENTIAL,
            MULTI_INSTANCE_PARALLEL,
    };

    /**
     * A public read-only list of all the '<em><b>TChoreography Loop Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<TChoreographyLoopType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>TChoreography Loop Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param literal the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static TChoreographyLoopType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            TChoreographyLoopType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>TChoreography Loop Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param name the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static TChoreographyLoopType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            TChoreographyLoopType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>TChoreography Loop Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static TChoreographyLoopType get(int value) {
        switch (value) {
            case NONE_VALUE:
                return NONE;
            case STANDARD_VALUE:
                return STANDARD;
            case MULTI_INSTANCE_SEQUENTIAL_VALUE:
                return MULTI_INSTANCE_SEQUENTIAL;
            case MULTI_INSTANCE_PARALLEL_VALUE:
                return MULTI_INSTANCE_PARALLEL;
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
    private TChoreographyLoopType(int value, String name, String literal) {
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

} //TChoreographyLoopType
