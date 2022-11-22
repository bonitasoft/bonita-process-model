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
package org.bonitasoft.bpm.model.kpi.provider;


import java.util.Collection;
import java.util.List;

import org.bonitasoft.bpm.model.edit.ProcessEditPlugin;

import org.bonitasoft.bpm.model.kpi.AbstractKPIBinding;
import org.bonitasoft.bpm.model.kpi.KpiFactory;
import org.bonitasoft.bpm.model.kpi.KpiPackage;

import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.bpm.model.process.ProcessPackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.bonitasoft.bpm.model.kpi.AbstractKPIBinding} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AbstractKPIBindingItemProvider 
    extends ItemProviderAdapter
    implements
        IEditingDomainItemProvider,
        IStructuredItemContentProvider,
        ITreeItemContentProvider,
        IItemLabelProvider,
        IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AbstractKPIBindingItemProvider(AdapterFactory adapterFactory) {
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

            addDocumentationPropertyDescriptor(object);
            addNamePropertyDescriptor(object);
            addKpiDefinitionNamePropertyDescriptor(object);
            addEventPropertyDescriptor(object);
            addIgnoreErrorPropertyDescriptor(object);
            addUseGraphicalEditorPropertyDescriptor(object);
            addRequestPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Documentation feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addDocumentationPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Element_documentation_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Element_documentation_feature", "_UI_Element_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ProcessPackage.Literals.ELEMENT__DOCUMENTATION,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Name feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Element_name_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_Element_name_feature", "_UI_Element_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 ProcessPackage.Literals.ELEMENT__NAME,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Kpi Definition Name feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addKpiDefinitionNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AbstractKPIBinding_kpiDefinitionName_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_AbstractKPIBinding_kpiDefinitionName_feature", "_UI_AbstractKPIBinding_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 KpiPackage.Literals.ABSTRACT_KPI_BINDING__KPI_DEFINITION_NAME,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Event feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addEventPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AbstractKPIBinding_event_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_AbstractKPIBinding_event_feature", "_UI_AbstractKPIBinding_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 KpiPackage.Literals.ABSTRACT_KPI_BINDING__EVENT,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Ignore Error feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addIgnoreErrorPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AbstractKPIBinding_ignoreError_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_AbstractKPIBinding_ignoreError_feature", "_UI_AbstractKPIBinding_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 KpiPackage.Literals.ABSTRACT_KPI_BINDING__IGNORE_ERROR,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Use Graphical Editor feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addUseGraphicalEditorPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AbstractKPIBinding_useGraphicalEditor_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_AbstractKPIBinding_useGraphicalEditor_feature", "_UI_AbstractKPIBinding_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 KpiPackage.Literals.ABSTRACT_KPI_BINDING__USE_GRAPHICAL_EDITOR,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
                 null,
                 null));
    }

    /**
     * This adds a property descriptor for the Request feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addRequestPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AbstractKPIBinding_request_feature"), //$NON-NLS-1$
                 getString("_UI_PropertyDescriptor_description", "_UI_AbstractKPIBinding_request_feature", "_UI_AbstractKPIBinding_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                 KpiPackage.Literals.ABSTRACT_KPI_BINDING__REQUEST,
                 true,
                 false,
                 false,
                 null,
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
            childrenFeatures.add(ProcessPackage.Literals.ELEMENT__TEXT_ANNOTATION_ATTACHMENT);
            childrenFeatures.add(KpiPackage.Literals.ABSTRACT_KPI_BINDING__PARAMETERS);
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
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((AbstractKPIBinding)object).getName();
        return label == null || label.length() == 0 ?
            getString("_UI_AbstractKPIBinding_type") : //$NON-NLS-1$
            getString("_UI_AbstractKPIBinding_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
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

        switch (notification.getFeatureID(AbstractKPIBinding.class)) {
            case KpiPackage.ABSTRACT_KPI_BINDING__DOCUMENTATION:
            case KpiPackage.ABSTRACT_KPI_BINDING__NAME:
            case KpiPackage.ABSTRACT_KPI_BINDING__KPI_DEFINITION_NAME:
            case KpiPackage.ABSTRACT_KPI_BINDING__EVENT:
            case KpiPackage.ABSTRACT_KPI_BINDING__IGNORE_ERROR:
            case KpiPackage.ABSTRACT_KPI_BINDING__USE_GRAPHICAL_EDITOR:
            case KpiPackage.ABSTRACT_KPI_BINDING__REQUEST:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case KpiPackage.ABSTRACT_KPI_BINDING__TEXT_ANNOTATION_ATTACHMENT:
            case KpiPackage.ABSTRACT_KPI_BINDING__PARAMETERS:
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
                (ProcessPackage.Literals.ELEMENT__TEXT_ANNOTATION_ATTACHMENT,
                 ProcessFactory.eINSTANCE.createTextAnnotationAttachment()));

        newChildDescriptors.add
            (createChildParameter
                (KpiPackage.Literals.ABSTRACT_KPI_BINDING__PARAMETERS,
                 KpiFactory.eINSTANCE.createKPIParameterMapping()));
    }

    /**
     * Return the resource locator for this item provider's resources.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return ProcessEditPlugin.INSTANCE;
    }

}
