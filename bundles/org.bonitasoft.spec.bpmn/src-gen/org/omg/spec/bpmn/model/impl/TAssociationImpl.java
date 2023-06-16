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

import javax.xml.namespace.QName;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TAssociation;
import org.omg.spec.bpmn.model.TAssociationDirection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TAssociation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TAssociationImpl#getAssociationDirection <em>Association Direction</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TAssociationImpl#getSourceRef <em>Source Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TAssociationImpl#getTargetRef <em>Target Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TAssociationImpl extends TArtifactImpl implements TAssociation {

    /**
     * The default value of the '{@link #getAssociationDirection() <em>Association Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAssociationDirection()
     * @generated
     * @ordered
     */
    protected static final TAssociationDirection ASSOCIATION_DIRECTION_EDEFAULT = TAssociationDirection.NONE;

    /**
     * The cached value of the '{@link #getAssociationDirection() <em>Association Direction</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAssociationDirection()
     * @generated
     * @ordered
     */
    protected TAssociationDirection associationDirection = ASSOCIATION_DIRECTION_EDEFAULT;

    /**
     * This is true if the Association Direction attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean associationDirectionESet;

    /**
     * The default value of the '{@link #getSourceRef() <em>Source Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceRef()
     * @generated
     * @ordered
     */
    protected static final QName SOURCE_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSourceRef() <em>Source Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSourceRef()
     * @generated
     * @ordered
     */
    protected QName sourceRef = SOURCE_REF_EDEFAULT;

    /**
     * The default value of the '{@link #getTargetRef() <em>Target Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetRef()
     * @generated
     * @ordered
     */
    protected static final QName TARGET_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTargetRef() <em>Target Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetRef()
     * @generated
     * @ordered
     */
    protected QName targetRef = TARGET_REF_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TAssociationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TASSOCIATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TAssociationDirection getAssociationDirection() {
        return associationDirection;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setAssociationDirection(TAssociationDirection newAssociationDirection) {
        TAssociationDirection oldAssociationDirection = associationDirection;
        associationDirection = newAssociationDirection == null ? ASSOCIATION_DIRECTION_EDEFAULT
                : newAssociationDirection;
        boolean oldAssociationDirectionESet = associationDirectionESet;
        associationDirectionESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TASSOCIATION__ASSOCIATION_DIRECTION,
                    oldAssociationDirection, associationDirection, !oldAssociationDirectionESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetAssociationDirection() {
        TAssociationDirection oldAssociationDirection = associationDirection;
        boolean oldAssociationDirectionESet = associationDirectionESet;
        associationDirection = ASSOCIATION_DIRECTION_EDEFAULT;
        associationDirectionESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TASSOCIATION__ASSOCIATION_DIRECTION,
                    oldAssociationDirection, ASSOCIATION_DIRECTION_EDEFAULT, oldAssociationDirectionESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetAssociationDirection() {
        return associationDirectionESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getSourceRef() {
        return sourceRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSourceRef(QName newSourceRef) {
        QName oldSourceRef = sourceRef;
        sourceRef = newSourceRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TASSOCIATION__SOURCE_REF, oldSourceRef,
                    sourceRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getTargetRef() {
        return targetRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTargetRef(QName newTargetRef) {
        QName oldTargetRef = targetRef;
        targetRef = newTargetRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TASSOCIATION__TARGET_REF, oldTargetRef,
                    targetRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ModelPackage.TASSOCIATION__ASSOCIATION_DIRECTION:
                return getAssociationDirection();
            case ModelPackage.TASSOCIATION__SOURCE_REF:
                return getSourceRef();
            case ModelPackage.TASSOCIATION__TARGET_REF:
                return getTargetRef();
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
            case ModelPackage.TASSOCIATION__ASSOCIATION_DIRECTION:
                setAssociationDirection((TAssociationDirection) newValue);
                return;
            case ModelPackage.TASSOCIATION__SOURCE_REF:
                setSourceRef((QName) newValue);
                return;
            case ModelPackage.TASSOCIATION__TARGET_REF:
                setTargetRef((QName) newValue);
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
            case ModelPackage.TASSOCIATION__ASSOCIATION_DIRECTION:
                unsetAssociationDirection();
                return;
            case ModelPackage.TASSOCIATION__SOURCE_REF:
                setSourceRef(SOURCE_REF_EDEFAULT);
                return;
            case ModelPackage.TASSOCIATION__TARGET_REF:
                setTargetRef(TARGET_REF_EDEFAULT);
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
            case ModelPackage.TASSOCIATION__ASSOCIATION_DIRECTION:
                return isSetAssociationDirection();
            case ModelPackage.TASSOCIATION__SOURCE_REF:
                return SOURCE_REF_EDEFAULT == null ? sourceRef != null : !SOURCE_REF_EDEFAULT.equals(sourceRef);
            case ModelPackage.TASSOCIATION__TARGET_REF:
                return TARGET_REF_EDEFAULT == null ? targetRef != null : !TARGET_REF_EDEFAULT.equals(targetRef);
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
        result.append(" (associationDirection: "); //$NON-NLS-1$
        if (associationDirectionESet)
            result.append(associationDirection);
        else
            result.append("<unset>"); //$NON-NLS-1$
        result.append(", sourceRef: "); //$NON-NLS-1$
        result.append(sourceRef);
        result.append(", targetRef: "); //$NON-NLS-1$
        result.append(targetRef);
        result.append(')');
        return result.toString();
    }

} //TAssociationImpl
