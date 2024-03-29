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
package org.omg.spec.bpmn.di;

import javax.xml.namespace.QName;

import org.omg.spec.dd.di.LabeledEdge;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>BPMN Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.di.BPMNEdge#getBPMNLabel <em>BPMN Label</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.BPMNEdge#getBpmnElement <em>Bpmn Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.BPMNEdge#getMessageVisibleKind <em>Message Visible Kind</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.BPMNEdge#getSourceElement <em>Source Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.BPMNEdge#getTargetElement <em>Target Element</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.di.DiPackage#getBPMNEdge()
 * @model extendedMetaData="name='BPMNEdge' kind='elementOnly'"
 * @generated
 */
public interface BPMNEdge extends LabeledEdge {

    /**
     * Returns the value of the '<em><b>BPMN Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>BPMN Label</em>' containment reference.
     * @see #setBPMNLabel(BPMNLabel)
     * @see org.omg.spec.bpmn.di.DiPackage#getBPMNEdge_BPMNLabel()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='BPMNLabel' namespace='##targetNamespace'"
     * @generated
     */
    BPMNLabel getBPMNLabel();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.di.BPMNEdge#getBPMNLabel <em>BPMN Label</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BPMN Label</em>' containment reference.
     * @see #getBPMNLabel()
     * @generated
     */
    void setBPMNLabel(BPMNLabel value);

    /**
     * Returns the value of the '<em><b>Bpmn Element</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Bpmn Element</em>' attribute.
     * @see #setBpmnElement(QName)
     * @see org.omg.spec.bpmn.di.DiPackage#getBPMNEdge_BpmnElement()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='bpmnElement'"
     * @generated
     */
    QName getBpmnElement();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.di.BPMNEdge#getBpmnElement <em>Bpmn Element</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bpmn Element</em>' attribute.
     * @see #getBpmnElement()
     * @generated
     */
    void setBpmnElement(QName value);

    /**
     * Returns the value of the '<em><b>Message Visible Kind</b></em>' attribute.
     * The literals are from the enumeration {@link org.omg.spec.bpmn.di.MessageVisibleKind}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Message Visible Kind</em>' attribute.
     * @see org.omg.spec.bpmn.di.MessageVisibleKind
     * @see #isSetMessageVisibleKind()
     * @see #unsetMessageVisibleKind()
     * @see #setMessageVisibleKind(MessageVisibleKind)
     * @see org.omg.spec.bpmn.di.DiPackage#getBPMNEdge_MessageVisibleKind()
     * @model unsettable="true"
     *        extendedMetaData="kind='attribute' name='messageVisibleKind'"
     * @generated
     */
    MessageVisibleKind getMessageVisibleKind();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.di.BPMNEdge#getMessageVisibleKind <em>Message Visible Kind</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Message Visible Kind</em>' attribute.
     * @see org.omg.spec.bpmn.di.MessageVisibleKind
     * @see #isSetMessageVisibleKind()
     * @see #unsetMessageVisibleKind()
     * @see #getMessageVisibleKind()
     * @generated
     */
    void setMessageVisibleKind(MessageVisibleKind value);

    /**
     * Unsets the value of the '{@link org.omg.spec.bpmn.di.BPMNEdge#getMessageVisibleKind <em>Message Visible Kind</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetMessageVisibleKind()
     * @see #getMessageVisibleKind()
     * @see #setMessageVisibleKind(MessageVisibleKind)
     * @generated
     */
    void unsetMessageVisibleKind();

    /**
     * Returns whether the value of the '{@link org.omg.spec.bpmn.di.BPMNEdge#getMessageVisibleKind <em>Message Visible Kind</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Message Visible Kind</em>' attribute is set.
     * @see #unsetMessageVisibleKind()
     * @see #getMessageVisibleKind()
     * @see #setMessageVisibleKind(MessageVisibleKind)
     * @generated
     */
    boolean isSetMessageVisibleKind();

    /**
     * Returns the value of the '<em><b>Source Element</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Element</em>' attribute.
     * @see #setSourceElement(QName)
     * @see org.omg.spec.bpmn.di.DiPackage#getBPMNEdge_SourceElement()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='sourceElement'"
     * @generated
     */
    QName getSourceElement();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.di.BPMNEdge#getSourceElement <em>Source Element</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Element</em>' attribute.
     * @see #getSourceElement()
     * @generated
     */
    void setSourceElement(QName value);

    /**
     * Returns the value of the '<em><b>Target Element</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Element</em>' attribute.
     * @see #setTargetElement(QName)
     * @see org.omg.spec.bpmn.di.DiPackage#getBPMNEdge_TargetElement()
     * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
     *        extendedMetaData="kind='attribute' name='targetElement'"
     * @generated
     */
    QName getTargetElement();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.di.BPMNEdge#getTargetElement <em>Target Element</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Element</em>' attribute.
     * @see #getTargetElement()
     * @generated
     */
    void setTargetElement(QName value);

} // BPMNEdge
