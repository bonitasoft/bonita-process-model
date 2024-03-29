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
package org.omg.spec.dd.di.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.omg.spec.dd.dc.Bounds;

import org.omg.spec.dd.di.DiPackage;
import org.omg.spec.dd.di.Label;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Label</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.dd.di.impl.LabelImpl#getBounds <em>Bounds</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class LabelImpl extends NodeImpl implements Label {

    /**
     * The cached value of the '{@link #getBounds() <em>Bounds</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBounds()
     * @generated
     * @ordered
     */
    protected Bounds bounds;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected LabelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiPackage.Literals.LABEL;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Bounds getBounds() {
        return bounds;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetBounds(Bounds newBounds, NotificationChain msgs) {
        Bounds oldBounds = bounds;
        bounds = newBounds;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiPackage.LABEL__BOUNDS,
                    oldBounds, newBounds);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setBounds(Bounds newBounds) {
        if (newBounds != bounds) {
            NotificationChain msgs = null;
            if (bounds != null)
                msgs = ((InternalEObject) bounds).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiPackage.LABEL__BOUNDS,
                        null, msgs);
            if (newBounds != null)
                msgs = ((InternalEObject) newBounds).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiPackage.LABEL__BOUNDS,
                        null, msgs);
            msgs = basicSetBounds(newBounds, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiPackage.LABEL__BOUNDS, newBounds, newBounds));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case DiPackage.LABEL__BOUNDS:
                return basicSetBounds(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case DiPackage.LABEL__BOUNDS:
                return getBounds();
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
            case DiPackage.LABEL__BOUNDS:
                setBounds((Bounds) newValue);
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
            case DiPackage.LABEL__BOUNDS:
                setBounds((Bounds) null);
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
            case DiPackage.LABEL__BOUNDS:
                return bounds != null;
        }
        return super.eIsSet(featureID);
    }

} //LabelImpl
