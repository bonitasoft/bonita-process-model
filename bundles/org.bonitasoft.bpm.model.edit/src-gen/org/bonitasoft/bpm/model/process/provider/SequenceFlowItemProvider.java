/**
 * Copyright (C) 2009-2022 BonitaSoft S.A.
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
package org.bonitasoft.bpm.model.process.provider;


import java.util.Collection;
import java.util.List;

import org.bonitasoft.bpm.model.expression.ExpressionFactory;

import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.bpm.model.process.SequenceFlow;

import org.bonitasoft.bpm.model.process.decision.DecisionFactory;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.bonitasoft.bpm.model.process.SequenceFlow} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SequenceFlowItemProvider extends ConnectionItemProvider {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SequenceFlowItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addIsDefaultPropertyDescriptor(object);
            addConditionTypePropertyDescriptor(object);
            addConditionPropertyDescriptor(object);
            addPathTokenPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Is Default feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addIsDefaultPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_SequenceFlow_isDefault_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_SequenceFlow_isDefault_feature", "_UI_SequenceFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ProcessPackage.Literals.SEQUENCE_FLOW__IS_DEFAULT,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Condition Type feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addConditionTypePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_SequenceFlow_conditionType_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_SequenceFlow_conditionType_feature", "_UI_SequenceFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ProcessPackage.Literals.SEQUENCE_FLOW__CONDITION_TYPE,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Condition feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addConditionPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_SequenceFlow_condition_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_SequenceFlow_condition_feature", "_UI_SequenceFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ProcessPackage.Literals.SEQUENCE_FLOW__CONDITION,
                 true,
                 false,
                 false,
                 null,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Path Token feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addPathTokenPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_SequenceFlow_pathToken_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_SequenceFlow_pathToken_feature", "_UI_SequenceFlow_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ProcessPackage.Literals.SEQUENCE_FLOW__PATH_TOKEN,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
     * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(ProcessPackage.Literals.SEQUENCE_FLOW__DECISION_TABLE);
            childrenFeatures.add(ProcessPackage.Literals.SEQUENCE_FLOW__CONDITION);
        }
        return childrenFeatures;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EStructuralFeature getChildFeature(Object object, Object child) {
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

    /**
     * This returns SequenceFlow.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/SequenceFlow")); //$NON-NLS-1$
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((SequenceFlow)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_SequenceFlow_type") : //$NON-NLS-1$
            getString("_UI_SequenceFlow_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
    }


    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(SequenceFlow.class)) {
            case ProcessPackage.SEQUENCE_FLOW__IS_DEFAULT:
            case ProcessPackage.SEQUENCE_FLOW__CONDITION_TYPE:
            case ProcessPackage.SEQUENCE_FLOW__PATH_TOKEN:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case ProcessPackage.SEQUENCE_FLOW__DECISION_TABLE:
            case ProcessPackage.SEQUENCE_FLOW__CONDITION:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
                return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
     * that can be created under this object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.SEQUENCE_FLOW__DECISION_TABLE,
                 DecisionFactory.eINSTANCE.createDecisionTable()));

        newChildDescriptors.add
            (createChildParameter
                (ProcessPackage.Literals.SEQUENCE_FLOW__CONDITION,
                 ExpressionFactory.eINSTANCE.createExpression()));
    }

}
