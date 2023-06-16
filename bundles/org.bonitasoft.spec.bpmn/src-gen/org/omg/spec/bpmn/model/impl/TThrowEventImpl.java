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

import java.util.Collection;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TDataInput;
import org.omg.spec.bpmn.model.TDataInputAssociation;
import org.omg.spec.bpmn.model.TEventDefinition;
import org.omg.spec.bpmn.model.TInputSet;
import org.omg.spec.bpmn.model.TThrowEvent;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TThrow Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TThrowEventImpl#getDataInput <em>Data Input</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TThrowEventImpl#getDataInputAssociation <em>Data Input Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TThrowEventImpl#getInputSet <em>Input Set</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TThrowEventImpl#getEventDefinitionGroup <em>Event Definition Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TThrowEventImpl#getEventDefinition <em>Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TThrowEventImpl#getEventDefinitionRef <em>Event Definition Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class TThrowEventImpl extends TEventImpl implements TThrowEvent {

    /**
     * The cached value of the '{@link #getDataInput() <em>Data Input</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataInput()
     * @generated
     * @ordered
     */
    protected EList<TDataInput> dataInput;

    /**
     * The cached value of the '{@link #getDataInputAssociation() <em>Data Input Association</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDataInputAssociation()
     * @generated
     * @ordered
     */
    protected EList<TDataInputAssociation> dataInputAssociation;

    /**
     * The cached value of the '{@link #getInputSet() <em>Input Set</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInputSet()
     * @generated
     * @ordered
     */
    protected TInputSet inputSet;

    /**
     * The cached value of the '{@link #getEventDefinitionGroup() <em>Event Definition Group</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEventDefinitionGroup()
     * @generated
     * @ordered
     */
    protected FeatureMap eventDefinitionGroup;

    /**
     * The cached value of the '{@link #getEventDefinitionRef() <em>Event Definition Ref</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEventDefinitionRef()
     * @generated
     * @ordered
     */
    protected EList<QName> eventDefinitionRef;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TThrowEventImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TTHROW_EVENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TDataInput> getDataInput() {
        if (dataInput == null) {
            dataInput = new EObjectContainmentEList<TDataInput>(TDataInput.class, this,
                    ModelPackage.TTHROW_EVENT__DATA_INPUT);
        }
        return dataInput;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TDataInputAssociation> getDataInputAssociation() {
        if (dataInputAssociation == null) {
            dataInputAssociation = new EObjectContainmentEList<TDataInputAssociation>(TDataInputAssociation.class, this,
                    ModelPackage.TTHROW_EVENT__DATA_INPUT_ASSOCIATION);
        }
        return dataInputAssociation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TInputSet getInputSet() {
        return inputSet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetInputSet(TInputSet newInputSet, NotificationChain msgs) {
        TInputSet oldInputSet = inputSet;
        inputSet = newInputSet;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TTHROW_EVENT__INPUT_SET, oldInputSet, newInputSet);
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
    public void setInputSet(TInputSet newInputSet) {
        if (newInputSet != inputSet) {
            NotificationChain msgs = null;
            if (inputSet != null)
                msgs = ((InternalEObject) inputSet).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ModelPackage.TTHROW_EVENT__INPUT_SET, null, msgs);
            if (newInputSet != null)
                msgs = ((InternalEObject) newInputSet).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ModelPackage.TTHROW_EVENT__INPUT_SET, null, msgs);
            msgs = basicSetInputSet(newInputSet, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TTHROW_EVENT__INPUT_SET, newInputSet,
                    newInputSet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FeatureMap getEventDefinitionGroup() {
        if (eventDefinitionGroup == null) {
            eventDefinitionGroup = new BasicFeatureMap(this, ModelPackage.TTHROW_EVENT__EVENT_DEFINITION_GROUP);
        }
        return eventDefinitionGroup;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TEventDefinition> getEventDefinition() {
        return getEventDefinitionGroup().list(ModelPackage.Literals.TTHROW_EVENT__EVENT_DEFINITION);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<QName> getEventDefinitionRef() {
        if (eventDefinitionRef == null) {
            eventDefinitionRef = new EDataTypeEList<QName>(QName.class, this,
                    ModelPackage.TTHROW_EVENT__EVENT_DEFINITION_REF);
        }
        return eventDefinitionRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ModelPackage.TTHROW_EVENT__DATA_INPUT:
                return ((InternalEList<?>) getDataInput()).basicRemove(otherEnd, msgs);
            case ModelPackage.TTHROW_EVENT__DATA_INPUT_ASSOCIATION:
                return ((InternalEList<?>) getDataInputAssociation()).basicRemove(otherEnd, msgs);
            case ModelPackage.TTHROW_EVENT__INPUT_SET:
                return basicSetInputSet(null, msgs);
            case ModelPackage.TTHROW_EVENT__EVENT_DEFINITION_GROUP:
                return ((InternalEList<?>) getEventDefinitionGroup()).basicRemove(otherEnd, msgs);
            case ModelPackage.TTHROW_EVENT__EVENT_DEFINITION:
                return ((InternalEList<?>) getEventDefinition()).basicRemove(otherEnd, msgs);
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
            case ModelPackage.TTHROW_EVENT__DATA_INPUT:
                return getDataInput();
            case ModelPackage.TTHROW_EVENT__DATA_INPUT_ASSOCIATION:
                return getDataInputAssociation();
            case ModelPackage.TTHROW_EVENT__INPUT_SET:
                return getInputSet();
            case ModelPackage.TTHROW_EVENT__EVENT_DEFINITION_GROUP:
                if (coreType)
                    return getEventDefinitionGroup();
                return ((FeatureMap.Internal) getEventDefinitionGroup()).getWrapper();
            case ModelPackage.TTHROW_EVENT__EVENT_DEFINITION:
                return getEventDefinition();
            case ModelPackage.TTHROW_EVENT__EVENT_DEFINITION_REF:
                return getEventDefinitionRef();
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
            case ModelPackage.TTHROW_EVENT__DATA_INPUT:
                getDataInput().clear();
                getDataInput().addAll((Collection<? extends TDataInput>) newValue);
                return;
            case ModelPackage.TTHROW_EVENT__DATA_INPUT_ASSOCIATION:
                getDataInputAssociation().clear();
                getDataInputAssociation().addAll((Collection<? extends TDataInputAssociation>) newValue);
                return;
            case ModelPackage.TTHROW_EVENT__INPUT_SET:
                setInputSet((TInputSet) newValue);
                return;
            case ModelPackage.TTHROW_EVENT__EVENT_DEFINITION_GROUP:
                ((FeatureMap.Internal) getEventDefinitionGroup()).set(newValue);
                return;
            case ModelPackage.TTHROW_EVENT__EVENT_DEFINITION:
                getEventDefinition().clear();
                getEventDefinition().addAll((Collection<? extends TEventDefinition>) newValue);
                return;
            case ModelPackage.TTHROW_EVENT__EVENT_DEFINITION_REF:
                getEventDefinitionRef().clear();
                getEventDefinitionRef().addAll((Collection<? extends QName>) newValue);
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
            case ModelPackage.TTHROW_EVENT__DATA_INPUT:
                getDataInput().clear();
                return;
            case ModelPackage.TTHROW_EVENT__DATA_INPUT_ASSOCIATION:
                getDataInputAssociation().clear();
                return;
            case ModelPackage.TTHROW_EVENT__INPUT_SET:
                setInputSet((TInputSet) null);
                return;
            case ModelPackage.TTHROW_EVENT__EVENT_DEFINITION_GROUP:
                getEventDefinitionGroup().clear();
                return;
            case ModelPackage.TTHROW_EVENT__EVENT_DEFINITION:
                getEventDefinition().clear();
                return;
            case ModelPackage.TTHROW_EVENT__EVENT_DEFINITION_REF:
                getEventDefinitionRef().clear();
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
            case ModelPackage.TTHROW_EVENT__DATA_INPUT:
                return dataInput != null && !dataInput.isEmpty();
            case ModelPackage.TTHROW_EVENT__DATA_INPUT_ASSOCIATION:
                return dataInputAssociation != null && !dataInputAssociation.isEmpty();
            case ModelPackage.TTHROW_EVENT__INPUT_SET:
                return inputSet != null;
            case ModelPackage.TTHROW_EVENT__EVENT_DEFINITION_GROUP:
                return eventDefinitionGroup != null && !eventDefinitionGroup.isEmpty();
            case ModelPackage.TTHROW_EVENT__EVENT_DEFINITION:
                return !getEventDefinition().isEmpty();
            case ModelPackage.TTHROW_EVENT__EVENT_DEFINITION_REF:
                return eventDefinitionRef != null && !eventDefinitionRef.isEmpty();
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
        result.append(" (eventDefinitionGroup: "); //$NON-NLS-1$
        result.append(eventDefinitionGroup);
        result.append(", eventDefinitionRef: "); //$NON-NLS-1$
        result.append(eventDefinitionRef);
        result.append(')');
        return result.toString();
    }

} //TThrowEventImpl
