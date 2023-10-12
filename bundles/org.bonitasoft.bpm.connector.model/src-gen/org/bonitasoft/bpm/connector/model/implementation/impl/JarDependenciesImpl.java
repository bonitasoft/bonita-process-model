/**
 * Copyright (C) 2009-2023 BonitaSoft S.A.
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
package org.bonitasoft.bpm.connector.model.implementation.impl;

import java.util.Collection;

import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementationPackage;
import org.bonitasoft.bpm.connector.model.implementation.JarDependencies;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Jar Dependencies</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.connector.model.implementation.impl.JarDependenciesImpl#getJarDependency <em>Jar Dependency</em>}</li>
 * </ul>
 *
 * @generated
 */
public class JarDependenciesImpl extends EObjectImpl implements JarDependencies {
    /**
     * The cached value of the '{@link #getJarDependency() <em>Jar Dependency</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getJarDependency()
     * @generated
     * @ordered
     */
    protected EList<String> jarDependency;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected JarDependenciesImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ConnectorImplementationPackage.Literals.JAR_DEPENDENCIES;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<String> getJarDependency() {
        if (jarDependency == null) {
            jarDependency = new EDataTypeEList<String>(String.class, this, ConnectorImplementationPackage.JAR_DEPENDENCIES__JAR_DEPENDENCY);
        }
        return jarDependency;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ConnectorImplementationPackage.JAR_DEPENDENCIES__JAR_DEPENDENCY:
                return getJarDependency();
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
            case ConnectorImplementationPackage.JAR_DEPENDENCIES__JAR_DEPENDENCY:
                getJarDependency().clear();
                getJarDependency().addAll((Collection<? extends String>)newValue);
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
            case ConnectorImplementationPackage.JAR_DEPENDENCIES__JAR_DEPENDENCY:
                getJarDependency().clear();
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
            case ConnectorImplementationPackage.JAR_DEPENDENCIES__JAR_DEPENDENCY:
                return jarDependency != null && !jarDependency.isEmpty();
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
        if (eIsProxy()) return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (jarDependency: "); //$NON-NLS-1$
        result.append(jarDependency);
        result.append(')');
        return result.toString();
    }

} //JarDependenciesImpl
