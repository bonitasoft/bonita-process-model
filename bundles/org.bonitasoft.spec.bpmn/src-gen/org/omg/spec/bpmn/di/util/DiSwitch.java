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
package org.omg.spec.bpmn.di.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.omg.spec.bpmn.di.*;

import org.omg.spec.dd.di.Diagram;
import org.omg.spec.dd.di.DiagramElement;
import org.omg.spec.dd.di.Edge;
import org.omg.spec.dd.di.Label;
import org.omg.spec.dd.di.LabeledEdge;
import org.omg.spec.dd.di.LabeledShape;
import org.omg.spec.dd.di.Node;
import org.omg.spec.dd.di.Plane;
import org.omg.spec.dd.di.Shape;
import org.omg.spec.dd.di.Style;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.omg.spec.bpmn.di.DiPackage
 * @generated
 */
public class DiSwitch<T> extends Switch<T> {

    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static DiPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DiSwitch() {
        if (modelPackage == null) {
            modelPackage = DiPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case DiPackage.BPMN_DIAGRAM: {
                BPMNDiagram bpmnDiagram = (BPMNDiagram) theEObject;
                T result = caseBPMNDiagram(bpmnDiagram);
                if (result == null)
                    result = caseDiagram(bpmnDiagram);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case DiPackage.BPMN_EDGE: {
                BPMNEdge bpmnEdge = (BPMNEdge) theEObject;
                T result = caseBPMNEdge(bpmnEdge);
                if (result == null)
                    result = caseLabeledEdge(bpmnEdge);
                if (result == null)
                    result = caseEdge(bpmnEdge);
                if (result == null)
                    result = caseDiagramElement(bpmnEdge);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case DiPackage.BPMN_LABEL: {
                BPMNLabel bpmnLabel = (BPMNLabel) theEObject;
                T result = caseBPMNLabel(bpmnLabel);
                if (result == null)
                    result = caseLabel(bpmnLabel);
                if (result == null)
                    result = caseNode(bpmnLabel);
                if (result == null)
                    result = caseDiagramElement(bpmnLabel);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case DiPackage.BPMN_LABEL_STYLE: {
                BPMNLabelStyle bpmnLabelStyle = (BPMNLabelStyle) theEObject;
                T result = caseBPMNLabelStyle(bpmnLabelStyle);
                if (result == null)
                    result = caseStyle(bpmnLabelStyle);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case DiPackage.BPMN_PLANE: {
                BPMNPlane bpmnPlane = (BPMNPlane) theEObject;
                T result = caseBPMNPlane(bpmnPlane);
                if (result == null)
                    result = casePlane(bpmnPlane);
                if (result == null)
                    result = caseNode(bpmnPlane);
                if (result == null)
                    result = caseDiagramElement(bpmnPlane);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case DiPackage.BPMN_SHAPE: {
                BPMNShape bpmnShape = (BPMNShape) theEObject;
                T result = caseBPMNShape(bpmnShape);
                if (result == null)
                    result = caseLabeledShape(bpmnShape);
                if (result == null)
                    result = caseShape(bpmnShape);
                if (result == null)
                    result = caseNode(bpmnShape);
                if (result == null)
                    result = caseDiagramElement(bpmnShape);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case DiPackage.DOCUMENT_ROOT: {
                DocumentRoot documentRoot = (DocumentRoot) theEObject;
                T result = caseDocumentRoot(documentRoot);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            default:
                return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>BPMN Diagram</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>BPMN Diagram</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPMNDiagram(BPMNDiagram object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>BPMN Edge</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>BPMN Edge</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPMNEdge(BPMNEdge object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>BPMN Label</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>BPMN Label</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPMNLabel(BPMNLabel object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>BPMN Label Style</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>BPMN Label Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPMNLabelStyle(BPMNLabelStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>BPMN Plane</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>BPMN Plane</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPMNPlane(BPMNPlane object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>BPMN Shape</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>BPMN Shape</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBPMNShape(BPMNShape object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDocumentRoot(DocumentRoot object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Diagram</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Diagram</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDiagram(Diagram object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Diagram Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Diagram Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDiagramElement(DiagramElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Edge</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Edge</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEdge(Edge object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Labeled Edge</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Labeled Edge</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabeledEdge(LabeledEdge object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Node</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNode(Node object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Label</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Label</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabel(Label object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Style</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStyle(Style object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Plane</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Plane</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePlane(Plane object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Shape</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Shape</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseShape(Shape object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Labeled Shape</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Labeled Shape</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabeledShape(LabeledShape object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(EObject object) {
        return null;
    }

} //DiSwitch
