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
package org.omg.spec.bpmn.di.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.omg.spec.bpmn.di.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DiFactoryImpl extends EFactoryImpl implements DiFactory {

    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static DiFactory init() {
        try {
            DiFactory theDiFactory = (DiFactory) EPackage.Registry.INSTANCE.getEFactory(DiPackage.eNS_URI);
            if (theDiFactory != null) {
                return theDiFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new DiFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DiFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case DiPackage.BPMN_DIAGRAM:
                return createBPMNDiagram();
            case DiPackage.BPMN_EDGE:
                return createBPMNEdge();
            case DiPackage.BPMN_LABEL:
                return createBPMNLabel();
            case DiPackage.BPMN_LABEL_STYLE:
                return createBPMNLabelStyle();
            case DiPackage.BPMN_PLANE:
                return createBPMNPlane();
            case DiPackage.BPMN_SHAPE:
                return createBPMNShape();
            case DiPackage.DOCUMENT_ROOT:
                return createDocumentRoot();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case DiPackage.MESSAGE_VISIBLE_KIND:
                return createMessageVisibleKindFromString(eDataType, initialValue);
            case DiPackage.PARTICIPANT_BAND_KIND:
                return createParticipantBandKindFromString(eDataType, initialValue);
            case DiPackage.MESSAGE_VISIBLE_KIND_OBJECT:
                return createMessageVisibleKindObjectFromString(eDataType, initialValue);
            case DiPackage.PARTICIPANT_BAND_KIND_OBJECT:
                return createParticipantBandKindObjectFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException(
                        "The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case DiPackage.MESSAGE_VISIBLE_KIND:
                return convertMessageVisibleKindToString(eDataType, instanceValue);
            case DiPackage.PARTICIPANT_BAND_KIND:
                return convertParticipantBandKindToString(eDataType, instanceValue);
            case DiPackage.MESSAGE_VISIBLE_KIND_OBJECT:
                return convertMessageVisibleKindObjectToString(eDataType, instanceValue);
            case DiPackage.PARTICIPANT_BAND_KIND_OBJECT:
                return convertParticipantBandKindObjectToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException(
                        "The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPMNDiagram createBPMNDiagram() {
        BPMNDiagramImpl bpmnDiagram = new BPMNDiagramImpl();
        return bpmnDiagram;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPMNEdge createBPMNEdge() {
        BPMNEdgeImpl bpmnEdge = new BPMNEdgeImpl();
        return bpmnEdge;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPMNLabel createBPMNLabel() {
        BPMNLabelImpl bpmnLabel = new BPMNLabelImpl();
        return bpmnLabel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPMNLabelStyle createBPMNLabelStyle() {
        BPMNLabelStyleImpl bpmnLabelStyle = new BPMNLabelStyleImpl();
        return bpmnLabelStyle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPMNPlane createBPMNPlane() {
        BPMNPlaneImpl bpmnPlane = new BPMNPlaneImpl();
        return bpmnPlane;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public BPMNShape createBPMNShape() {
        BPMNShapeImpl bpmnShape = new BPMNShapeImpl();
        return bpmnShape;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public DocumentRoot createDocumentRoot() {
        DocumentRootImpl documentRoot = new DocumentRootImpl();
        return documentRoot;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MessageVisibleKind createMessageVisibleKindFromString(EDataType eDataType, String initialValue) {
        MessageVisibleKind result = MessageVisibleKind.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException(
                    "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertMessageVisibleKindToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParticipantBandKind createParticipantBandKindFromString(EDataType eDataType, String initialValue) {
        ParticipantBandKind result = ParticipantBandKind.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException(
                    "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertParticipantBandKindToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MessageVisibleKind createMessageVisibleKindObjectFromString(EDataType eDataType, String initialValue) {
        return createMessageVisibleKindFromString(DiPackage.Literals.MESSAGE_VISIBLE_KIND, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertMessageVisibleKindObjectToString(EDataType eDataType, Object instanceValue) {
        return convertMessageVisibleKindToString(DiPackage.Literals.MESSAGE_VISIBLE_KIND, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ParticipantBandKind createParticipantBandKindObjectFromString(EDataType eDataType, String initialValue) {
        return createParticipantBandKindFromString(DiPackage.Literals.PARTICIPANT_BAND_KIND, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertParticipantBandKindObjectToString(EDataType eDataType, Object instanceValue) {
        return convertParticipantBandKindToString(DiPackage.Literals.PARTICIPANT_BAND_KIND, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public DiPackage getDiPackage() {
        return (DiPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static DiPackage getPackage() {
        return DiPackage.eINSTANCE;
    }

} //DiFactoryImpl
