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
package org.omg.spec.bpmn.di.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.omg.spec.bpmn.di.BPMNLabelStyle;
import org.omg.spec.bpmn.di.DiPackage;

import org.omg.spec.dd.dc.Font;

import org.omg.spec.dd.di.impl.StyleImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>BPMN Label Style</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.di.impl.BPMNLabelStyleImpl#getFont <em>Font</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BPMNLabelStyleImpl extends StyleImpl implements BPMNLabelStyle {

    /**
     * The cached value of the '{@link #getFont() <em>Font</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFont()
     * @generated
     * @ordered
     */
    protected Font font;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BPMNLabelStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiPackage.Literals.BPMN_LABEL_STYLE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Font getFont() {
        return font;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFont(Font newFont, NotificationChain msgs) {
        Font oldFont = font;
        font = newFont;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    DiPackage.BPMN_LABEL_STYLE__FONT, oldFont, newFont);
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
    public void setFont(Font newFont) {
        if (newFont != font) {
            NotificationChain msgs = null;
            if (font != null)
                msgs = ((InternalEObject) font).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - DiPackage.BPMN_LABEL_STYLE__FONT, null, msgs);
            if (newFont != null)
                msgs = ((InternalEObject) newFont).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - DiPackage.BPMN_LABEL_STYLE__FONT, null, msgs);
            msgs = basicSetFont(newFont, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiPackage.BPMN_LABEL_STYLE__FONT, newFont, newFont));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case DiPackage.BPMN_LABEL_STYLE__FONT:
                return basicSetFont(null, msgs);
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
            case DiPackage.BPMN_LABEL_STYLE__FONT:
                return getFont();
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
            case DiPackage.BPMN_LABEL_STYLE__FONT:
                setFont((Font) newValue);
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
            case DiPackage.BPMN_LABEL_STYLE__FONT:
                setFont((Font) null);
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
            case DiPackage.BPMN_LABEL_STYLE__FONT:
                return font != null;
        }
        return super.eIsSet(featureID);
    }

} //BPMNLabelStyleImpl
