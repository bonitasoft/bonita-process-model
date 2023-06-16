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
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TDataOutput;
import org.omg.spec.bpmn.model.TDataState;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TData Output</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDataOutputImpl#getDataState <em>Data State</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDataOutputImpl#isIsCollection <em>Is Collection</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDataOutputImpl#getItemSubjectRef <em>Item Subject Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TDataOutputImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TDataOutputImpl extends TBaseElementImpl implements TDataOutput {

    /**
     * The cached value of the '{@link #getDataState() <em>Data State</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataState()
     * @generated
     * @ordered
     */
    protected TDataState dataState;

    /**
     * The default value of the '{@link #isIsCollection() <em>Is Collection</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsCollection()
     * @generated
     * @ordered
     */
    protected static final boolean IS_COLLECTION_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isIsCollection() <em>Is Collection</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isIsCollection()
     * @generated
     * @ordered
     */
    protected boolean isCollection = IS_COLLECTION_EDEFAULT;

    /**
     * This is true if the Is Collection attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean isCollectionESet;

    /**
     * The default value of the '{@link #getItemSubjectRef() <em>Item Subject Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getItemSubjectRef()
     * @generated
     * @ordered
     */
    protected static final QName ITEM_SUBJECT_REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getItemSubjectRef() <em>Item Subject Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getItemSubjectRef()
     * @generated
     * @ordered
     */
    protected QName itemSubjectRef = ITEM_SUBJECT_REF_EDEFAULT;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TDataOutputImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TDATA_OUTPUT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TDataState getDataState() {
        return dataState;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDataState(TDataState newDataState, NotificationChain msgs) {
        TDataState oldDataState = dataState;
        dataState = newDataState;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TDATA_OUTPUT__DATA_STATE, oldDataState, newDataState);
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
    public void setDataState(TDataState newDataState) {
        if (newDataState != dataState) {
            NotificationChain msgs = null;
            if (dataState != null)
                msgs = ((InternalEObject) dataState).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ModelPackage.TDATA_OUTPUT__DATA_STATE, null, msgs);
            if (newDataState != null)
                msgs = ((InternalEObject) newDataState).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ModelPackage.TDATA_OUTPUT__DATA_STATE, null, msgs);
            msgs = basicSetDataState(newDataState, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TDATA_OUTPUT__DATA_STATE, newDataState,
                    newDataState));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isIsCollection() {
        return isCollection;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setIsCollection(boolean newIsCollection) {
        boolean oldIsCollection = isCollection;
        isCollection = newIsCollection;
        boolean oldIsCollectionESet = isCollectionESet;
        isCollectionESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TDATA_OUTPUT__IS_COLLECTION,
                    oldIsCollection, isCollection, !oldIsCollectionESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetIsCollection() {
        boolean oldIsCollection = isCollection;
        boolean oldIsCollectionESet = isCollectionESet;
        isCollection = IS_COLLECTION_EDEFAULT;
        isCollectionESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TDATA_OUTPUT__IS_COLLECTION,
                    oldIsCollection, IS_COLLECTION_EDEFAULT, oldIsCollectionESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetIsCollection() {
        return isCollectionESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getItemSubjectRef() {
        return itemSubjectRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setItemSubjectRef(QName newItemSubjectRef) {
        QName oldItemSubjectRef = itemSubjectRef;
        itemSubjectRef = newItemSubjectRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TDATA_OUTPUT__ITEM_SUBJECT_REF,
                    oldItemSubjectRef, itemSubjectRef));
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
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TDATA_OUTPUT__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ModelPackage.TDATA_OUTPUT__DATA_STATE:
                return basicSetDataState(null, msgs);
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
            case ModelPackage.TDATA_OUTPUT__DATA_STATE:
                return getDataState();
            case ModelPackage.TDATA_OUTPUT__IS_COLLECTION:
                return isIsCollection();
            case ModelPackage.TDATA_OUTPUT__ITEM_SUBJECT_REF:
                return getItemSubjectRef();
            case ModelPackage.TDATA_OUTPUT__NAME:
                return getName();
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
            case ModelPackage.TDATA_OUTPUT__DATA_STATE:
                setDataState((TDataState) newValue);
                return;
            case ModelPackage.TDATA_OUTPUT__IS_COLLECTION:
                setIsCollection((Boolean) newValue);
                return;
            case ModelPackage.TDATA_OUTPUT__ITEM_SUBJECT_REF:
                setItemSubjectRef((QName) newValue);
                return;
            case ModelPackage.TDATA_OUTPUT__NAME:
                setName((String) newValue);
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
            case ModelPackage.TDATA_OUTPUT__DATA_STATE:
                setDataState((TDataState) null);
                return;
            case ModelPackage.TDATA_OUTPUT__IS_COLLECTION:
                unsetIsCollection();
                return;
            case ModelPackage.TDATA_OUTPUT__ITEM_SUBJECT_REF:
                setItemSubjectRef(ITEM_SUBJECT_REF_EDEFAULT);
                return;
            case ModelPackage.TDATA_OUTPUT__NAME:
                setName(NAME_EDEFAULT);
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
            case ModelPackage.TDATA_OUTPUT__DATA_STATE:
                return dataState != null;
            case ModelPackage.TDATA_OUTPUT__IS_COLLECTION:
                return isSetIsCollection();
            case ModelPackage.TDATA_OUTPUT__ITEM_SUBJECT_REF:
                return ITEM_SUBJECT_REF_EDEFAULT == null ? itemSubjectRef != null
                        : !ITEM_SUBJECT_REF_EDEFAULT.equals(itemSubjectRef);
            case ModelPackage.TDATA_OUTPUT__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
        result.append(" (isCollection: "); //$NON-NLS-1$
        if (isCollectionESet)
            result.append(isCollection);
        else
            result.append("<unset>"); //$NON-NLS-1$
        result.append(", itemSubjectRef: "); //$NON-NLS-1$
        result.append(itemSubjectRef);
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(')');
        return result.toString();
    }

} //TDataOutputImpl
