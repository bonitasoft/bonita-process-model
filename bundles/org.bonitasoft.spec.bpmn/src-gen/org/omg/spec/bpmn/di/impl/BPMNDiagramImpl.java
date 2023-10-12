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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.di.BPMNDiagram;
import org.omg.spec.bpmn.di.BPMNLabelStyle;
import org.omg.spec.bpmn.di.BPMNPlane;
import org.omg.spec.bpmn.di.DiPackage;

import org.omg.spec.dd.di.impl.DiagramImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>BPMN Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.di.impl.BPMNDiagramImpl#getBPMNPlane <em>BPMN Plane</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.impl.BPMNDiagramImpl#getBPMNLabelStyle <em>BPMN Label Style</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BPMNDiagramImpl extends DiagramImpl implements BPMNDiagram {

    /**
     * The cached value of the '{@link #getBPMNPlane() <em>BPMN Plane</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBPMNPlane()
     * @generated
     * @ordered
     */
    protected BPMNPlane bPMNPlane;

    /**
     * The cached value of the '{@link #getBPMNLabelStyle() <em>BPMN Label Style</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBPMNLabelStyle()
     * @generated
     * @ordered
     */
    protected EList<BPMNLabelStyle> bPMNLabelStyle;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BPMNDiagramImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiPackage.Literals.BPMN_DIAGRAM;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPMNPlane getBPMNPlane() {
        return bPMNPlane;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetBPMNPlane(BPMNPlane newBPMNPlane, NotificationChain msgs) {
        BPMNPlane oldBPMNPlane = bPMNPlane;
        bPMNPlane = newBPMNPlane;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    DiPackage.BPMN_DIAGRAM__BPMN_PLANE, oldBPMNPlane, newBPMNPlane);
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
    public void setBPMNPlane(BPMNPlane newBPMNPlane) {
        if (newBPMNPlane != bPMNPlane) {
            NotificationChain msgs = null;
            if (bPMNPlane != null)
                msgs = ((InternalEObject) bPMNPlane).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - DiPackage.BPMN_DIAGRAM__BPMN_PLANE, null, msgs);
            if (newBPMNPlane != null)
                msgs = ((InternalEObject) newBPMNPlane).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - DiPackage.BPMN_DIAGRAM__BPMN_PLANE, null, msgs);
            msgs = basicSetBPMNPlane(newBPMNPlane, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiPackage.BPMN_DIAGRAM__BPMN_PLANE, newBPMNPlane,
                    newBPMNPlane));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<BPMNLabelStyle> getBPMNLabelStyle() {
        if (bPMNLabelStyle == null) {
            bPMNLabelStyle = new EObjectContainmentEList<BPMNLabelStyle>(BPMNLabelStyle.class, this,
                    DiPackage.BPMN_DIAGRAM__BPMN_LABEL_STYLE);
        }
        return bPMNLabelStyle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case DiPackage.BPMN_DIAGRAM__BPMN_PLANE:
                return basicSetBPMNPlane(null, msgs);
            case DiPackage.BPMN_DIAGRAM__BPMN_LABEL_STYLE:
                return ((InternalEList<?>) getBPMNLabelStyle()).basicRemove(otherEnd, msgs);
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
            case DiPackage.BPMN_DIAGRAM__BPMN_PLANE:
                return getBPMNPlane();
            case DiPackage.BPMN_DIAGRAM__BPMN_LABEL_STYLE:
                return getBPMNLabelStyle();
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
            case DiPackage.BPMN_DIAGRAM__BPMN_PLANE:
                setBPMNPlane((BPMNPlane) newValue);
                return;
            case DiPackage.BPMN_DIAGRAM__BPMN_LABEL_STYLE:
                getBPMNLabelStyle().clear();
                getBPMNLabelStyle().addAll((Collection<? extends BPMNLabelStyle>) newValue);
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
            case DiPackage.BPMN_DIAGRAM__BPMN_PLANE:
                setBPMNPlane((BPMNPlane) null);
                return;
            case DiPackage.BPMN_DIAGRAM__BPMN_LABEL_STYLE:
                getBPMNLabelStyle().clear();
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
            case DiPackage.BPMN_DIAGRAM__BPMN_PLANE:
                return bPMNPlane != null;
            case DiPackage.BPMN_DIAGRAM__BPMN_LABEL_STYLE:
                return bPMNLabelStyle != null && !bPMNLabelStyle.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //BPMNDiagramImpl
