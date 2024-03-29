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

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.di.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.DocumentRoot#getBPMNDiagram <em>BPMN Diagram</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.DocumentRoot#getBPMNEdge <em>BPMN Edge</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.DocumentRoot#getBPMNLabel <em>BPMN Label</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.DocumentRoot#getBPMNLabelStyle <em>BPMN Label Style</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.DocumentRoot#getBPMNPlane <em>BPMN Plane</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.DocumentRoot#getBPMNShape <em>BPMN Shape</em>}</li>
 * </ul>
 *
 * @see org.omg.spec.bpmn.di.DiPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {

    /**
     * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
     * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Mixed</em>' attribute list.
     * @see org.omg.spec.bpmn.di.DiPackage#getDocumentRoot_Mixed()
     * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
     *        extendedMetaData="kind='elementWildcard' name=':mixed'"
     * @generated
     */
    FeatureMap getMixed();

    /**
     * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>XMLNS Prefix Map</em>' map.
     * @see org.omg.spec.bpmn.di.DiPackage#getDocumentRoot_XMLNSPrefixMap()
     * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;" transient="true"
     *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
     * @generated
     */
    EMap<String, String> getXMLNSPrefixMap();

    /**
     * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
     * The key is of type {@link java.lang.String},
     * and the value is of type {@link java.lang.String},
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>XSI Schema Location</em>' map.
     * @see org.omg.spec.bpmn.di.DiPackage#getDocumentRoot_XSISchemaLocation()
     * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;" transient="true"
     *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
     * @generated
     */
    EMap<String, String> getXSISchemaLocation();

    /**
     * Returns the value of the '<em><b>BPMN Diagram</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>BPMN Diagram</em>' containment reference.
     * @see #setBPMNDiagram(BPMNDiagram)
     * @see org.omg.spec.bpmn.di.DiPackage#getDocumentRoot_BPMNDiagram()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='BPMNDiagram' namespace='##targetNamespace'"
     * @generated
     */
    BPMNDiagram getBPMNDiagram();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.di.DocumentRoot#getBPMNDiagram <em>BPMN Diagram</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BPMN Diagram</em>' containment reference.
     * @see #getBPMNDiagram()
     * @generated
     */
    void setBPMNDiagram(BPMNDiagram value);

    /**
     * Returns the value of the '<em><b>BPMN Edge</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>BPMN Edge</em>' containment reference.
     * @see #setBPMNEdge(BPMNEdge)
     * @see org.omg.spec.bpmn.di.DiPackage#getDocumentRoot_BPMNEdge()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='BPMNEdge' namespace='##targetNamespace' affiliation='http://www.omg.org/spec/DD/20100524/DI#DiagramElement'"
     * @generated
     */
    BPMNEdge getBPMNEdge();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.di.DocumentRoot#getBPMNEdge <em>BPMN Edge</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BPMN Edge</em>' containment reference.
     * @see #getBPMNEdge()
     * @generated
     */
    void setBPMNEdge(BPMNEdge value);

    /**
     * Returns the value of the '<em><b>BPMN Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>BPMN Label</em>' containment reference.
     * @see #setBPMNLabel(BPMNLabel)
     * @see org.omg.spec.bpmn.di.DiPackage#getDocumentRoot_BPMNLabel()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='BPMNLabel' namespace='##targetNamespace'"
     * @generated
     */
    BPMNLabel getBPMNLabel();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.di.DocumentRoot#getBPMNLabel <em>BPMN Label</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BPMN Label</em>' containment reference.
     * @see #getBPMNLabel()
     * @generated
     */
    void setBPMNLabel(BPMNLabel value);

    /**
     * Returns the value of the '<em><b>BPMN Label Style</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>BPMN Label Style</em>' containment reference.
     * @see #setBPMNLabelStyle(BPMNLabelStyle)
     * @see org.omg.spec.bpmn.di.DiPackage#getDocumentRoot_BPMNLabelStyle()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='BPMNLabelStyle' namespace='##targetNamespace'"
     * @generated
     */
    BPMNLabelStyle getBPMNLabelStyle();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.di.DocumentRoot#getBPMNLabelStyle <em>BPMN Label Style</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BPMN Label Style</em>' containment reference.
     * @see #getBPMNLabelStyle()
     * @generated
     */
    void setBPMNLabelStyle(BPMNLabelStyle value);

    /**
     * Returns the value of the '<em><b>BPMN Plane</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>BPMN Plane</em>' containment reference.
     * @see #setBPMNPlane(BPMNPlane)
     * @see org.omg.spec.bpmn.di.DiPackage#getDocumentRoot_BPMNPlane()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='BPMNPlane' namespace='##targetNamespace'"
     * @generated
     */
    BPMNPlane getBPMNPlane();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.di.DocumentRoot#getBPMNPlane <em>BPMN Plane</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BPMN Plane</em>' containment reference.
     * @see #getBPMNPlane()
     * @generated
     */
    void setBPMNPlane(BPMNPlane value);

    /**
     * Returns the value of the '<em><b>BPMN Shape</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>BPMN Shape</em>' containment reference.
     * @see #setBPMNShape(BPMNShape)
     * @see org.omg.spec.bpmn.di.DiPackage#getDocumentRoot_BPMNShape()
     * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
     *        extendedMetaData="kind='element' name='BPMNShape' namespace='##targetNamespace' affiliation='http://www.omg.org/spec/DD/20100524/DI#DiagramElement'"
     * @generated
     */
    BPMNShape getBPMNShape();

    /**
     * Sets the value of the '{@link org.omg.spec.bpmn.di.DocumentRoot#getBPMNShape <em>BPMN Shape</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BPMN Shape</em>' containment reference.
     * @see #getBPMNShape()
     * @generated
     */
    void setBPMNShape(BPMNShape value);

} // DocumentRoot
