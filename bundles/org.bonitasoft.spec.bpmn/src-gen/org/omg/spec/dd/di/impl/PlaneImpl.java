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

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.dd.di.DiPackage;
import org.omg.spec.dd.di.DiagramElement;
import org.omg.spec.dd.di.Plane;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Plane</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.dd.di.impl.PlaneImpl#getDiagramElementGroup <em>Diagram Element Group</em>}</li>
 *   <li>{@link org.omg.spec.dd.di.impl.PlaneImpl#getDiagramElement <em>Diagram Element</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class PlaneImpl extends NodeImpl implements Plane {

    /**
     * The cached value of the '{@link #getDiagramElementGroup() <em>Diagram Element Group</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDiagramElementGroup()
     * @generated
     * @ordered
     */
    protected FeatureMap diagramElementGroup;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PlaneImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiPackage.Literals.PLANE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FeatureMap getDiagramElementGroup() {
        if (diagramElementGroup == null) {
            diagramElementGroup = new BasicFeatureMap(this, DiPackage.PLANE__DIAGRAM_ELEMENT_GROUP);
        }
        return diagramElementGroup;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<DiagramElement> getDiagramElement() {
        return getDiagramElementGroup().list(DiPackage.Literals.PLANE__DIAGRAM_ELEMENT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case DiPackage.PLANE__DIAGRAM_ELEMENT_GROUP:
                return ((InternalEList<?>) getDiagramElementGroup()).basicRemove(otherEnd, msgs);
            case DiPackage.PLANE__DIAGRAM_ELEMENT:
                return ((InternalEList<?>) getDiagramElement()).basicRemove(otherEnd, msgs);
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
            case DiPackage.PLANE__DIAGRAM_ELEMENT_GROUP:
                if (coreType)
                    return getDiagramElementGroup();
                return ((FeatureMap.Internal) getDiagramElementGroup()).getWrapper();
            case DiPackage.PLANE__DIAGRAM_ELEMENT:
                return getDiagramElement();
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
            case DiPackage.PLANE__DIAGRAM_ELEMENT_GROUP:
                ((FeatureMap.Internal) getDiagramElementGroup()).set(newValue);
                return;
            case DiPackage.PLANE__DIAGRAM_ELEMENT:
                getDiagramElement().clear();
                getDiagramElement().addAll((Collection<? extends DiagramElement>) newValue);
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
            case DiPackage.PLANE__DIAGRAM_ELEMENT_GROUP:
                getDiagramElementGroup().clear();
                return;
            case DiPackage.PLANE__DIAGRAM_ELEMENT:
                getDiagramElement().clear();
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
            case DiPackage.PLANE__DIAGRAM_ELEMENT_GROUP:
                return diagramElementGroup != null && !diagramElementGroup.isEmpty();
            case DiPackage.PLANE__DIAGRAM_ELEMENT:
                return !getDiagramElement().isEmpty();
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
        result.append(" (diagramElementGroup: "); //$NON-NLS-1$
        result.append(diagramElementGroup);
        result.append(')');
        return result.toString();
    }

} //PlaneImpl
