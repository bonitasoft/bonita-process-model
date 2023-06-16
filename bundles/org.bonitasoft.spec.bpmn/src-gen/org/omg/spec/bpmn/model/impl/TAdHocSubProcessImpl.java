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
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TAdHocOrdering;
import org.omg.spec.bpmn.model.TAdHocSubProcess;
import org.omg.spec.bpmn.model.TExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TAd Hoc Sub Process</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TAdHocSubProcessImpl#getCompletionCondition <em>Completion Condition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TAdHocSubProcessImpl#isCancelRemainingInstances <em>Cancel Remaining Instances</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TAdHocSubProcessImpl#getOrdering <em>Ordering</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TAdHocSubProcessImpl extends TSubProcessImpl implements TAdHocSubProcess {

    /**
     * The cached value of the '{@link #getCompletionCondition() <em>Completion Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCompletionCondition()
     * @generated
     * @ordered
     */
    protected TExpression completionCondition;

    /**
     * The default value of the '{@link #isCancelRemainingInstances() <em>Cancel Remaining Instances</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isCancelRemainingInstances()
     * @generated
     * @ordered
     */
    protected static final boolean CANCEL_REMAINING_INSTANCES_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isCancelRemainingInstances() <em>Cancel Remaining Instances</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isCancelRemainingInstances()
     * @generated
     * @ordered
     */
    protected boolean cancelRemainingInstances = CANCEL_REMAINING_INSTANCES_EDEFAULT;

    /**
     * This is true if the Cancel Remaining Instances attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean cancelRemainingInstancesESet;

    /**
     * The default value of the '{@link #getOrdering() <em>Ordering</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOrdering()
     * @generated
     * @ordered
     */
    protected static final TAdHocOrdering ORDERING_EDEFAULT = TAdHocOrdering.PARALLEL;

    /**
     * The cached value of the '{@link #getOrdering() <em>Ordering</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOrdering()
     * @generated
     * @ordered
     */
    protected TAdHocOrdering ordering = ORDERING_EDEFAULT;

    /**
     * This is true if the Ordering attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean orderingESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TAdHocSubProcessImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TAD_HOC_SUB_PROCESS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TExpression getCompletionCondition() {
        return completionCondition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCompletionCondition(TExpression newCompletionCondition, NotificationChain msgs) {
        TExpression oldCompletionCondition = completionCondition;
        completionCondition = newCompletionCondition;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TAD_HOC_SUB_PROCESS__COMPLETION_CONDITION, oldCompletionCondition,
                    newCompletionCondition);
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
    public void setCompletionCondition(TExpression newCompletionCondition) {
        if (newCompletionCondition != completionCondition) {
            NotificationChain msgs = null;
            if (completionCondition != null)
                msgs = ((InternalEObject) completionCondition).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ModelPackage.TAD_HOC_SUB_PROCESS__COMPLETION_CONDITION, null, msgs);
            if (newCompletionCondition != null)
                msgs = ((InternalEObject) newCompletionCondition).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ModelPackage.TAD_HOC_SUB_PROCESS__COMPLETION_CONDITION, null, msgs);
            msgs = basicSetCompletionCondition(newCompletionCondition, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TAD_HOC_SUB_PROCESS__COMPLETION_CONDITION, newCompletionCondition,
                    newCompletionCondition));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isCancelRemainingInstances() {
        return cancelRemainingInstances;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCancelRemainingInstances(boolean newCancelRemainingInstances) {
        boolean oldCancelRemainingInstances = cancelRemainingInstances;
        cancelRemainingInstances = newCancelRemainingInstances;
        boolean oldCancelRemainingInstancesESet = cancelRemainingInstancesESet;
        cancelRemainingInstancesESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TAD_HOC_SUB_PROCESS__CANCEL_REMAINING_INSTANCES, oldCancelRemainingInstances,
                    cancelRemainingInstances, !oldCancelRemainingInstancesESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetCancelRemainingInstances() {
        boolean oldCancelRemainingInstances = cancelRemainingInstances;
        boolean oldCancelRemainingInstancesESet = cancelRemainingInstancesESet;
        cancelRemainingInstances = CANCEL_REMAINING_INSTANCES_EDEFAULT;
        cancelRemainingInstancesESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET,
                    ModelPackage.TAD_HOC_SUB_PROCESS__CANCEL_REMAINING_INSTANCES, oldCancelRemainingInstances,
                    CANCEL_REMAINING_INSTANCES_EDEFAULT, oldCancelRemainingInstancesESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetCancelRemainingInstances() {
        return cancelRemainingInstancesESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TAdHocOrdering getOrdering() {
        return ordering;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setOrdering(TAdHocOrdering newOrdering) {
        TAdHocOrdering oldOrdering = ordering;
        ordering = newOrdering == null ? ORDERING_EDEFAULT : newOrdering;
        boolean oldOrderingESet = orderingESet;
        orderingESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TAD_HOC_SUB_PROCESS__ORDERING,
                    oldOrdering, ordering, !oldOrderingESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetOrdering() {
        TAdHocOrdering oldOrdering = ordering;
        boolean oldOrderingESet = orderingESet;
        ordering = ORDERING_EDEFAULT;
        orderingESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TAD_HOC_SUB_PROCESS__ORDERING,
                    oldOrdering, ORDERING_EDEFAULT, oldOrderingESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetOrdering() {
        return orderingESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ModelPackage.TAD_HOC_SUB_PROCESS__COMPLETION_CONDITION:
                return basicSetCompletionCondition(null, msgs);
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
            case ModelPackage.TAD_HOC_SUB_PROCESS__COMPLETION_CONDITION:
                return getCompletionCondition();
            case ModelPackage.TAD_HOC_SUB_PROCESS__CANCEL_REMAINING_INSTANCES:
                return isCancelRemainingInstances();
            case ModelPackage.TAD_HOC_SUB_PROCESS__ORDERING:
                return getOrdering();
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
            case ModelPackage.TAD_HOC_SUB_PROCESS__COMPLETION_CONDITION:
                setCompletionCondition((TExpression) newValue);
                return;
            case ModelPackage.TAD_HOC_SUB_PROCESS__CANCEL_REMAINING_INSTANCES:
                setCancelRemainingInstances((Boolean) newValue);
                return;
            case ModelPackage.TAD_HOC_SUB_PROCESS__ORDERING:
                setOrdering((TAdHocOrdering) newValue);
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
            case ModelPackage.TAD_HOC_SUB_PROCESS__COMPLETION_CONDITION:
                setCompletionCondition((TExpression) null);
                return;
            case ModelPackage.TAD_HOC_SUB_PROCESS__CANCEL_REMAINING_INSTANCES:
                unsetCancelRemainingInstances();
                return;
            case ModelPackage.TAD_HOC_SUB_PROCESS__ORDERING:
                unsetOrdering();
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
            case ModelPackage.TAD_HOC_SUB_PROCESS__COMPLETION_CONDITION:
                return completionCondition != null;
            case ModelPackage.TAD_HOC_SUB_PROCESS__CANCEL_REMAINING_INSTANCES:
                return isSetCancelRemainingInstances();
            case ModelPackage.TAD_HOC_SUB_PROCESS__ORDERING:
                return isSetOrdering();
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
        result.append(" (cancelRemainingInstances: "); //$NON-NLS-1$
        if (cancelRemainingInstancesESet)
            result.append(cancelRemainingInstances);
        else
            result.append("<unset>"); //$NON-NLS-1$
        result.append(", ordering: "); //$NON-NLS-1$
        if (orderingESet)
            result.append(ordering);
        else
            result.append("<unset>"); //$NON-NLS-1$
        result.append(')');
        return result.toString();
    }

} //TAdHocSubProcessImpl
