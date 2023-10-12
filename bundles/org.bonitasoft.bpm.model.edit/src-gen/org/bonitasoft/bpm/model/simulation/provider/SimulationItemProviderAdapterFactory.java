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
package org.bonitasoft.bpm.model.simulation.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.bonitasoft.bpm.model.simulation.util.SimulationAdapterFactory;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SimulationItemProviderAdapterFactory extends SimulationAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
    /**
     * This keeps track of the root adapter factory that delegates to this adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ComposedAdapterFactory parentAdapterFactory;

    /**
     * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IChangeNotifier changeNotifier = new ChangeNotifier();

    /**
     * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Collection<Object> supportedTypes = new ArrayList<Object>();

    /**
     * This constructs an instance.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SimulationItemProviderAdapterFactory() {
        supportedTypes.add(IEditingDomainItemProvider.class);
        supportedTypes.add(IStructuredItemContentProvider.class);
        supportedTypes.add(ITreeItemContentProvider.class);
        supportedTypes.add(IItemLabelProvider.class);
        supportedTypes.add(IItemPropertySource.class);
    }

    /**
     * This keeps track of the one adapter used for all {@link org.bonitasoft.bpm.model.simulation.SimulationElement} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SimulationElementItemProvider simulationElementItemProvider;

    /**
     * This creates an adapter for a {@link org.bonitasoft.bpm.model.simulation.SimulationElement}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createSimulationElementAdapter() {
        if (simulationElementItemProvider == null) {
            simulationElementItemProvider = new SimulationElementItemProvider(this);
        }

        return simulationElementItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.bonitasoft.bpm.model.simulation.SimulationTransition} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SimulationTransitionItemProvider simulationTransitionItemProvider;

    /**
     * This creates an adapter for a {@link org.bonitasoft.bpm.model.simulation.SimulationTransition}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createSimulationTransitionAdapter() {
        if (simulationTransitionItemProvider == null) {
            simulationTransitionItemProvider = new SimulationTransitionItemProvider(this);
        }

        return simulationTransitionItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.bonitasoft.bpm.model.simulation.ResourceUsage} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ResourceUsageItemProvider resourceUsageItemProvider;

    /**
     * This creates an adapter for a {@link org.bonitasoft.bpm.model.simulation.ResourceUsage}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createResourceUsageAdapter() {
        if (resourceUsageItemProvider == null) {
            resourceUsageItemProvider = new ResourceUsageItemProvider(this);
        }

        return resourceUsageItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.bonitasoft.bpm.model.simulation.InjectionPeriod} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected InjectionPeriodItemProvider injectionPeriodItemProvider;

    /**
     * This creates an adapter for a {@link org.bonitasoft.bpm.model.simulation.InjectionPeriod}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createInjectionPeriodAdapter() {
        if (injectionPeriodItemProvider == null) {
            injectionPeriodItemProvider = new InjectionPeriodItemProvider(this);
        }

        return injectionPeriodItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.bonitasoft.bpm.model.simulation.SimulationBoolean} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SimulationBooleanItemProvider simulationBooleanItemProvider;

    /**
     * This creates an adapter for a {@link org.bonitasoft.bpm.model.simulation.SimulationBoolean}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createSimulationBooleanAdapter() {
        if (simulationBooleanItemProvider == null) {
            simulationBooleanItemProvider = new SimulationBooleanItemProvider(this);
        }

        return simulationBooleanItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.bonitasoft.bpm.model.simulation.SimulationNumberData} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SimulationNumberDataItemProvider simulationNumberDataItemProvider;

    /**
     * This creates an adapter for a {@link org.bonitasoft.bpm.model.simulation.SimulationNumberData}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createSimulationNumberDataAdapter() {
        if (simulationNumberDataItemProvider == null) {
            simulationNumberDataItemProvider = new SimulationNumberDataItemProvider(this);
        }

        return simulationNumberDataItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.bonitasoft.bpm.model.simulation.SimulationLiteralData} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SimulationLiteralDataItemProvider simulationLiteralDataItemProvider;

    /**
     * This creates an adapter for a {@link org.bonitasoft.bpm.model.simulation.SimulationLiteralData}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createSimulationLiteralDataAdapter() {
        if (simulationLiteralDataItemProvider == null) {
            simulationLiteralDataItemProvider = new SimulationLiteralDataItemProvider(this);
        }

        return simulationLiteralDataItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.bonitasoft.bpm.model.simulation.SimulationLiteral} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SimulationLiteralItemProvider simulationLiteralItemProvider;

    /**
     * This creates an adapter for a {@link org.bonitasoft.bpm.model.simulation.SimulationLiteral}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createSimulationLiteralAdapter() {
        if (simulationLiteralItemProvider == null) {
            simulationLiteralItemProvider = new SimulationLiteralItemProvider(this);
        }

        return simulationLiteralItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.bonitasoft.bpm.model.simulation.SimulationNumberRange} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SimulationNumberRangeItemProvider simulationNumberRangeItemProvider;

    /**
     * This creates an adapter for a {@link org.bonitasoft.bpm.model.simulation.SimulationNumberRange}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createSimulationNumberRangeAdapter() {
        if (simulationNumberRangeItemProvider == null) {
            simulationNumberRangeItemProvider = new SimulationNumberRangeItemProvider(this);
        }

        return simulationNumberRangeItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.bonitasoft.bpm.model.simulation.DataChange} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DataChangeItemProvider dataChangeItemProvider;

    /**
     * This creates an adapter for a {@link org.bonitasoft.bpm.model.simulation.DataChange}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createDataChangeAdapter() {
        if (dataChangeItemProvider == null) {
            dataChangeItemProvider = new DataChangeItemProvider(this);
        }

        return dataChangeItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.bonitasoft.bpm.model.simulation.SimulationCalendar} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SimulationCalendarItemProvider simulationCalendarItemProvider;

    /**
     * This creates an adapter for a {@link org.bonitasoft.bpm.model.simulation.SimulationCalendar}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createSimulationCalendarAdapter() {
        if (simulationCalendarItemProvider == null) {
            simulationCalendarItemProvider = new SimulationCalendarItemProvider(this);
        }

        return simulationCalendarItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.bonitasoft.bpm.model.simulation.DayPeriod} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DayPeriodItemProvider dayPeriodItemProvider;

    /**
     * This creates an adapter for a {@link org.bonitasoft.bpm.model.simulation.DayPeriod}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createDayPeriodAdapter() {
        if (dayPeriodItemProvider == null) {
            dayPeriodItemProvider = new DayPeriodItemProvider(this);
        }

        return dayPeriodItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.bonitasoft.bpm.model.simulation.ModelVersion} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ModelVersionItemProvider modelVersionItemProvider;

    /**
     * This creates an adapter for a {@link org.bonitasoft.bpm.model.simulation.ModelVersion}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createModelVersionAdapter() {
        if (modelVersionItemProvider == null) {
            modelVersionItemProvider = new ModelVersionItemProvider(this);
        }

        return modelVersionItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.bonitasoft.bpm.model.simulation.LoadProfile} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected LoadProfileItemProvider loadProfileItemProvider;

    /**
     * This creates an adapter for a {@link org.bonitasoft.bpm.model.simulation.LoadProfile}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createLoadProfileAdapter() {
        if (loadProfileItemProvider == null) {
            loadProfileItemProvider = new LoadProfileItemProvider(this);
        }

        return loadProfileItemProvider;
    }

    /**
     * This keeps track of the one adapter used for all {@link org.bonitasoft.bpm.model.simulation.Resource} instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ResourceItemProvider resourceItemProvider;

    /**
     * This creates an adapter for a {@link org.bonitasoft.bpm.model.simulation.Resource}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter createResourceAdapter() {
        if (resourceItemProvider == null) {
            resourceItemProvider = new ResourceItemProvider(this);
        }

        return resourceItemProvider;
    }

    /**
     * This returns the root adapter factory that contains this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ComposeableAdapterFactory getRootAdapterFactory() {
        return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
    }

    /**
     * This sets the composed adapter factory that contains this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
        this.parentAdapterFactory = parentAdapterFactory;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object type) {
        return supportedTypes.contains(type) || super.isFactoryForType(type);
    }

    /**
     * This implementation substitutes the factory itself as the key for the adapter.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Adapter adapt(Notifier notifier, Object type) {
        return super.adapt(notifier, this);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object adapt(Object object, Object type) {
        if (isFactoryForType(type)) {
            Object adapter = super.adapt(object, type);
            if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
                return adapter;
            }
        }

        return null;
    }

    /**
     * This adds a listener.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void addListener(INotifyChangedListener notifyChangedListener) {
        changeNotifier.addListener(notifyChangedListener);
    }

    /**
     * This removes a listener.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void removeListener(INotifyChangedListener notifyChangedListener) {
        changeNotifier.removeListener(notifyChangedListener);
    }

    /**
     * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void fireNotifyChanged(Notification notification) {
        changeNotifier.fireNotifyChanged(notification);

        if (parentAdapterFactory != null) {
            parentAdapterFactory.fireNotifyChanged(notification);
        }
    }

    /**
     * This disposes all of the item providers created by this factory. 
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void dispose() {
        if (simulationElementItemProvider != null) simulationElementItemProvider.dispose();
        if (simulationTransitionItemProvider != null) simulationTransitionItemProvider.dispose();
        if (resourceUsageItemProvider != null) resourceUsageItemProvider.dispose();
        if (injectionPeriodItemProvider != null) injectionPeriodItemProvider.dispose();
        if (simulationBooleanItemProvider != null) simulationBooleanItemProvider.dispose();
        if (simulationNumberDataItemProvider != null) simulationNumberDataItemProvider.dispose();
        if (simulationLiteralDataItemProvider != null) simulationLiteralDataItemProvider.dispose();
        if (simulationLiteralItemProvider != null) simulationLiteralItemProvider.dispose();
        if (simulationNumberRangeItemProvider != null) simulationNumberRangeItemProvider.dispose();
        if (dataChangeItemProvider != null) dataChangeItemProvider.dispose();
        if (simulationCalendarItemProvider != null) simulationCalendarItemProvider.dispose();
        if (dayPeriodItemProvider != null) dayPeriodItemProvider.dispose();
        if (modelVersionItemProvider != null) modelVersionItemProvider.dispose();
        if (loadProfileItemProvider != null) loadProfileItemProvider.dispose();
        if (resourceItemProvider != null) resourceItemProvider.dispose();
    }

}
