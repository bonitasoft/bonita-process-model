/**
 * Copyright (C) 2009-2023 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.bpm.connector.model.definition.impl;

import java.util.Collection;

import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.bpm.connector.model.definition.Orientation;
import org.bonitasoft.bpm.connector.model.definition.RadioGroup;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Radio Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.impl.RadioGroupImpl#getChoices <em>Choices</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.connector.model.definition.impl.RadioGroupImpl#getOrientation <em>Orientation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RadioGroupImpl extends WidgetImpl implements RadioGroup {
    /**
     * The cached value of the '{@link #getChoices() <em>Choices</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChoices()
     * @generated
     * @ordered
     */
    protected EList<String> choices;

    /**
     * The default value of the '{@link #getOrientation() <em>Orientation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOrientation()
     * @generated
     * @ordered
     */
    protected static final Orientation ORIENTATION_EDEFAULT = Orientation.HORIZONTAL;

    /**
     * The cached value of the '{@link #getOrientation() <em>Orientation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOrientation()
     * @generated
     * @ordered
     */
    protected Orientation orientation = ORIENTATION_EDEFAULT;

    /**
     * This is true if the Orientation attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean orientationESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RadioGroupImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ConnectorDefinitionPackage.Literals.RADIO_GROUP;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<String> getChoices() {
        if (choices == null) {
            choices = new EDataTypeEList<String>(String.class, this, ConnectorDefinitionPackage.RADIO_GROUP__CHOICES);
        }
        return choices;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setOrientation(Orientation newOrientation) {
        Orientation oldOrientation = orientation;
        orientation = newOrientation == null ? ORIENTATION_EDEFAULT : newOrientation;
        boolean oldOrientationESet = orientationESet;
        orientationESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectorDefinitionPackage.RADIO_GROUP__ORIENTATION, oldOrientation, orientation, !oldOrientationESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetOrientation() {
        Orientation oldOrientation = orientation;
        boolean oldOrientationESet = orientationESet;
        orientation = ORIENTATION_EDEFAULT;
        orientationESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ConnectorDefinitionPackage.RADIO_GROUP__ORIENTATION, oldOrientation, ORIENTATION_EDEFAULT, oldOrientationESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetOrientation() {
        return orientationESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ConnectorDefinitionPackage.RADIO_GROUP__CHOICES:
                return getChoices();
            case ConnectorDefinitionPackage.RADIO_GROUP__ORIENTATION:
                return getOrientation();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ConnectorDefinitionPackage.RADIO_GROUP__CHOICES:
                getChoices().clear();
                getChoices().addAll((Collection<? extends String>)newValue);
                return;
            case ConnectorDefinitionPackage.RADIO_GROUP__ORIENTATION:
                setOrientation((Orientation)newValue);
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
            case ConnectorDefinitionPackage.RADIO_GROUP__CHOICES:
                getChoices().clear();
                return;
            case ConnectorDefinitionPackage.RADIO_GROUP__ORIENTATION:
                unsetOrientation();
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
            case ConnectorDefinitionPackage.RADIO_GROUP__CHOICES:
                return choices != null && !choices.isEmpty();
            case ConnectorDefinitionPackage.RADIO_GROUP__ORIENTATION:
                return isSetOrientation();
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
        if (eIsProxy()) return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (choices: "); //$NON-NLS-1$
        result.append(choices);
        result.append(", orientation: "); //$NON-NLS-1$
        if (orientationESet) result.append(orientation); else result.append("<unset>"); //$NON-NLS-1$
        result.append(')');
        return result.toString();
    }

} //RadioGroupImpl
