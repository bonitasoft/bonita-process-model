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
package org.bonitasoft.bpm.model.kpi;

import org.bonitasoft.bpm.model.process.Element;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract KPI Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.model.kpi.AbstractKPIDefinition#getVersion <em>Version</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.model.kpi.AbstractKPIDefinition#getFields <em>Fields</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.bpm.model.kpi.KpiPackage#getAbstractKPIDefinition()
 * @model abstract="true"
 * @generated
 */
public interface AbstractKPIDefinition extends Element {
    /**
     * Returns the value of the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Version</em>' attribute.
     * @see #setVersion(String)
     * @see org.bonitasoft.bpm.model.kpi.KpiPackage#getAbstractKPIDefinition_Version()
     * @model
     * @generated
     */
    String getVersion();

    /**
     * Sets the value of the '{@link org.bonitasoft.bpm.model.kpi.AbstractKPIDefinition#getVersion <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Version</em>' attribute.
     * @see #getVersion()
     * @generated
     */
    void setVersion(String value);

    /**
     * Returns the value of the '<em><b>Fields</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.bpm.model.kpi.KPIField}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Fields</em>' containment reference list.
     * @see org.bonitasoft.bpm.model.kpi.KpiPackage#getAbstractKPIDefinition_Fields()
     * @model containment="true"
     * @generated
     */
    EList<KPIField> getFields();

} // AbstractKPIDefinition
