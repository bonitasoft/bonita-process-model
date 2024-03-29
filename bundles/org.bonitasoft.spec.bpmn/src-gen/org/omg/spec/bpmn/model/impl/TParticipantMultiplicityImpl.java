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
package org.omg.spec.bpmn.model.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TParticipantMultiplicity;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TParticipant Multiplicity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TParticipantMultiplicityImpl#getMaximum <em>Maximum</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TParticipantMultiplicityImpl#getMinimum <em>Minimum</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TParticipantMultiplicityImpl extends TBaseElementImpl implements TParticipantMultiplicity {

    /**
     * The default value of the '{@link #getMaximum() <em>Maximum</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMaximum()
     * @generated
     * @ordered
     */
    protected static final int MAXIMUM_EDEFAULT = 1;

    /**
     * The cached value of the '{@link #getMaximum() <em>Maximum</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMaximum()
     * @generated
     * @ordered
     */
    protected int maximum = MAXIMUM_EDEFAULT;

    /**
     * This is true if the Maximum attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean maximumESet;

    /**
     * The default value of the '{@link #getMinimum() <em>Minimum</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMinimum()
     * @generated
     * @ordered
     */
    protected static final int MINIMUM_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getMinimum() <em>Minimum</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMinimum()
     * @generated
     * @ordered
     */
    protected int minimum = MINIMUM_EDEFAULT;

    /**
     * This is true if the Minimum attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean minimumESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TParticipantMultiplicityImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TPARTICIPANT_MULTIPLICITY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getMaximum() {
        return maximum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setMaximum(int newMaximum) {
        int oldMaximum = maximum;
        maximum = newMaximum;
        boolean oldMaximumESet = maximumESet;
        maximumESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TPARTICIPANT_MULTIPLICITY__MAXIMUM,
                    oldMaximum, maximum, !oldMaximumESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetMaximum() {
        int oldMaximum = maximum;
        boolean oldMaximumESet = maximumESet;
        maximum = MAXIMUM_EDEFAULT;
        maximumESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TPARTICIPANT_MULTIPLICITY__MAXIMUM,
                    oldMaximum, MAXIMUM_EDEFAULT, oldMaximumESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetMaximum() {
        return maximumESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getMinimum() {
        return minimum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setMinimum(int newMinimum) {
        int oldMinimum = minimum;
        minimum = newMinimum;
        boolean oldMinimumESet = minimumESet;
        minimumESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TPARTICIPANT_MULTIPLICITY__MINIMUM,
                    oldMinimum, minimum, !oldMinimumESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetMinimum() {
        int oldMinimum = minimum;
        boolean oldMinimumESet = minimumESet;
        minimum = MINIMUM_EDEFAULT;
        minimumESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TPARTICIPANT_MULTIPLICITY__MINIMUM,
                    oldMinimum, MINIMUM_EDEFAULT, oldMinimumESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetMinimum() {
        return minimumESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ModelPackage.TPARTICIPANT_MULTIPLICITY__MAXIMUM:
                return getMaximum();
            case ModelPackage.TPARTICIPANT_MULTIPLICITY__MINIMUM:
                return getMinimum();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ModelPackage.TPARTICIPANT_MULTIPLICITY__MAXIMUM:
                setMaximum((Integer) newValue);
                return;
            case ModelPackage.TPARTICIPANT_MULTIPLICITY__MINIMUM:
                setMinimum((Integer) newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case ModelPackage.TPARTICIPANT_MULTIPLICITY__MAXIMUM:
                unsetMaximum();
                return;
            case ModelPackage.TPARTICIPANT_MULTIPLICITY__MINIMUM:
                unsetMinimum();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case ModelPackage.TPARTICIPANT_MULTIPLICITY__MAXIMUM:
                return isSetMaximum();
            case ModelPackage.TPARTICIPANT_MULTIPLICITY__MINIMUM:
                return isSetMinimum();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (maximum: "); //$NON-NLS-1$
        if (maximumESet)
            result.append(maximum);
        else
            result.append("<unset>"); //$NON-NLS-1$
        result.append(", minimum: "); //$NON-NLS-1$
        if (minimumESet)
            result.append(minimum);
        else
            result.append("<unset>"); //$NON-NLS-1$
        result.append(')');
        return result.toString();
    }

} //TParticipantMultiplicityImpl
