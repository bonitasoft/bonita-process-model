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
package org.bonitasoft.bpm.model.kpi.util;

import org.bonitasoft.bpm.model.kpi.*;

import org.bonitasoft.bpm.model.process.Element;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.bpm.model.kpi.KpiPackage
 * @generated
 */
public class KpiAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static KpiPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public KpiAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = KpiPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KpiSwitch<Adapter> modelSwitch =
        new KpiSwitch<Adapter>() {
            @Override
            public Adapter caseAbstractKPIDefinition(AbstractKPIDefinition object) {
                return createAbstractKPIDefinitionAdapter();
            }
            @Override
            public Adapter caseAbstractKPIBinding(AbstractKPIBinding object) {
                return createAbstractKPIBindingAdapter();
            }
            @Override
            public Adapter caseKPIParameterMapping(KPIParameterMapping object) {
                return createKPIParameterMappingAdapter();
            }
            @Override
            public Adapter caseDatabaseKPIBinding(DatabaseKPIBinding object) {
                return createDatabaseKPIBindingAdapter();
            }
            @Override
            public Adapter caseDatabaseKPIDefinition(DatabaseKPIDefinition object) {
                return createDatabaseKPIDefinitionAdapter();
            }
            @Override
            public Adapter caseKPIField(KPIField object) {
                return createKPIFieldAdapter();
            }
            @Override
            public Adapter caseElement(Element object) {
                return createElementAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.bpm.model.kpi.AbstractKPIDefinition <em>Abstract KPI Definition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.bpm.model.kpi.AbstractKPIDefinition
     * @generated
     */
    public Adapter createAbstractKPIDefinitionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.bpm.model.kpi.AbstractKPIBinding <em>Abstract KPI Binding</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.bpm.model.kpi.AbstractKPIBinding
     * @generated
     */
    public Adapter createAbstractKPIBindingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.bpm.model.kpi.KPIParameterMapping <em>KPI Parameter Mapping</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.bpm.model.kpi.KPIParameterMapping
     * @generated
     */
    public Adapter createKPIParameterMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.bpm.model.kpi.DatabaseKPIBinding <em>Database KPI Binding</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.bpm.model.kpi.DatabaseKPIBinding
     * @generated
     */
    public Adapter createDatabaseKPIBindingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.bpm.model.kpi.DatabaseKPIDefinition <em>Database KPI Definition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.bpm.model.kpi.DatabaseKPIDefinition
     * @generated
     */
    public Adapter createDatabaseKPIDefinitionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.bpm.model.kpi.KPIField <em>KPI Field</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.bpm.model.kpi.KPIField
     * @generated
     */
    public Adapter createKPIFieldAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.bpm.model.process.Element <em>Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.bpm.model.process.Element
     * @generated
     */
    public Adapter createElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //KpiAdapterFactory
