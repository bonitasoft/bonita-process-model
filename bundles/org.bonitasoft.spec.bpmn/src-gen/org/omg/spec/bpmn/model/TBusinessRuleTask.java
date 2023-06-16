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
package org.omg.spec.bpmn.model;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TBusiness Rule Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TBusinessRuleTask#getImplementation <em>Implementation</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTBusinessRuleTask()
 * @model extendedMetaData="name='tBusinessRuleTask' kind='elementOnly'"
 * @generated
 */
public interface TBusinessRuleTask extends TTask {

    /**
     * Returns the value of the '<em><b>Implementation</b></em>' attribute.
     * The default value is <code>"##unspecified"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Implementation</em>' attribute.
     * @see #isSetImplementation()
     * @see #unsetImplementation()
     * @see #setImplementation(Object)
     * @see org.omg.spec.bpmn.model.ModelPackage#getTBusinessRuleTask_Implementation()
     * @model default="##unspecified" unsettable="true" dataType="org.omg.spec.bpmn.model.TImplementation"
     *        extendedMetaData="kind='attribute' name='implementation'"
     * @generated
     */
    Object getImplementation();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.model.TBusinessRuleTask#getImplementation <em>Implementation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Implementation</em>' attribute.
     * @see #isSetImplementation()
     * @see #unsetImplementation()
     * @see #getImplementation()
     * @generated
     */
    void setImplementation(Object value);

    /**
     * Unsets the value of the '{@link org.omg.spec.bpmn.model.TBusinessRuleTask#getImplementation <em>Implementation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetImplementation()
     * @see #getImplementation()
     * @see #setImplementation(Object)
     * @generated
     */
    void unsetImplementation();

    /**
     * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TBusinessRuleTask#getImplementation <em>Implementation</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Implementation</em>' attribute is set.
     * @see #unsetImplementation()
     * @see #getImplementation()
     * @see #setImplementation(Object)
     * @generated
     */
    boolean isSetImplementation();

} // TBusinessRuleTask
