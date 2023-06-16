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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.omg.spec.bpmn.di.BPMNDiagram;
import org.omg.spec.bpmn.di.BPMNEdge;
import org.omg.spec.bpmn.di.BPMNLabel;
import org.omg.spec.bpmn.di.BPMNLabelStyle;
import org.omg.spec.bpmn.di.BPMNPlane;
import org.omg.spec.bpmn.di.BPMNShape;
import org.omg.spec.bpmn.di.DiFactory;
import org.omg.spec.bpmn.di.DiPackage;
import org.omg.spec.bpmn.di.DocumentRoot;
import org.omg.spec.bpmn.di.MessageVisibleKind;
import org.omg.spec.bpmn.di.ParticipantBandKind;

import org.omg.spec.bpmn.model.ModelPackage;

import org.omg.spec.bpmn.model.impl.ModelPackageImpl;

import org.omg.spec.dd.dc.DcPackage;

import org.omg.spec.dd.dc.impl.DcPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DiPackageImpl extends EPackageImpl implements DiPackage {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass bpmnDiagramEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass bpmnEdgeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass bpmnLabelEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass bpmnLabelStyleEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass bpmnPlaneEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass bpmnShapeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass documentRootEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum messageVisibleKindEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum participantBandKindEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType messageVisibleKindObjectEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType participantBandKindObjectEDataType = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.omg.spec.bpmn.di.DiPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private DiPackageImpl() {
        super(eNS_URI, DiFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     *
     * <p>This method is used to initialize {@link DiPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static DiPackage init() {
        if (isInited)
            return (DiPackage) EPackage.Registry.INSTANCE.getEPackage(DiPackage.eNS_URI);

        // Obtain or create and register package
        Object registeredDiPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        DiPackageImpl theDiPackage = registeredDiPackage instanceof DiPackageImpl ? (DiPackageImpl) registeredDiPackage
                : new DiPackageImpl();

        isInited = true;

        // Initialize simple dependencies
        XMLTypePackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI);
        ModelPackageImpl theModelPackage = (ModelPackageImpl) (registeredPackage instanceof ModelPackageImpl
                ? registeredPackage : ModelPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DcPackage.eNS_URI);
        DcPackageImpl theDcPackage = (DcPackageImpl) (registeredPackage instanceof DcPackageImpl ? registeredPackage
                : DcPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(org.omg.spec.dd.di.DiPackage.eNS_URI);
        org.omg.spec.dd.di.impl.DiPackageImpl theDiPackage_1 = (org.omg.spec.dd.di.impl.DiPackageImpl) (registeredPackage instanceof org.omg.spec.dd.di.impl.DiPackageImpl
                ? registeredPackage : org.omg.spec.dd.di.DiPackage.eINSTANCE);

        // Load packages
        theModelPackage.loadPackage();

        // Create package meta-data objects
        theDiPackage.createPackageContents();
        theDcPackage.createPackageContents();
        theDiPackage_1.createPackageContents();

        // Initialize created meta-data
        theDiPackage.initializePackageContents();
        theDcPackage.initializePackageContents();
        theDiPackage_1.initializePackageContents();

        // Fix loaded packages
        theModelPackage.fixPackageContents();

        // Mark meta-data to indicate it can't be changed
        theDiPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(DiPackage.eNS_URI, theDiPackage);
        return theDiPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getBPMNDiagram() {
        return bpmnDiagramEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getBPMNDiagram_BPMNPlane() {
        return (EReference) bpmnDiagramEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getBPMNDiagram_BPMNLabelStyle() {
        return (EReference) bpmnDiagramEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getBPMNEdge() {
        return bpmnEdgeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getBPMNEdge_BPMNLabel() {
        return (EReference) bpmnEdgeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPMNEdge_BpmnElement() {
        return (EAttribute) bpmnEdgeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPMNEdge_MessageVisibleKind() {
        return (EAttribute) bpmnEdgeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPMNEdge_SourceElement() {
        return (EAttribute) bpmnEdgeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPMNEdge_TargetElement() {
        return (EAttribute) bpmnEdgeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getBPMNLabel() {
        return bpmnLabelEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPMNLabel_LabelStyle() {
        return (EAttribute) bpmnLabelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getBPMNLabelStyle() {
        return bpmnLabelStyleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getBPMNLabelStyle_Font() {
        return (EReference) bpmnLabelStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getBPMNPlane() {
        return bpmnPlaneEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPMNPlane_BpmnElement() {
        return (EAttribute) bpmnPlaneEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getBPMNShape() {
        return bpmnShapeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getBPMNShape_BPMNLabel() {
        return (EReference) bpmnShapeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPMNShape_BpmnElement() {
        return (EAttribute) bpmnShapeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPMNShape_ChoreographyActivityShape() {
        return (EAttribute) bpmnShapeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPMNShape_IsExpanded() {
        return (EAttribute) bpmnShapeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPMNShape_IsHorizontal() {
        return (EAttribute) bpmnShapeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPMNShape_IsMarkerVisible() {
        return (EAttribute) bpmnShapeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPMNShape_IsMessageVisible() {
        return (EAttribute) bpmnShapeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getBPMNShape_ParticipantBandKind() {
        return (EAttribute) bpmnShapeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getDocumentRoot() {
        return documentRootEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDocumentRoot_Mixed() {
        return (EAttribute) documentRootEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDocumentRoot_XMLNSPrefixMap() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDocumentRoot_XSISchemaLocation() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDocumentRoot_BPMNDiagram() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDocumentRoot_BPMNEdge() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDocumentRoot_BPMNLabel() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDocumentRoot_BPMNLabelStyle() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDocumentRoot_BPMNPlane() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDocumentRoot_BPMNShape() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EEnum getMessageVisibleKind() {
        return messageVisibleKindEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EEnum getParticipantBandKind() {
        return participantBandKindEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EDataType getMessageVisibleKindObject() {
        return messageVisibleKindObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EDataType getParticipantBandKindObject() {
        return participantBandKindObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public DiFactory getDiFactory() {
        return (DiFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        bpmnDiagramEClass = createEClass(BPMN_DIAGRAM);
        createEReference(bpmnDiagramEClass, BPMN_DIAGRAM__BPMN_PLANE);
        createEReference(bpmnDiagramEClass, BPMN_DIAGRAM__BPMN_LABEL_STYLE);

        bpmnEdgeEClass = createEClass(BPMN_EDGE);
        createEReference(bpmnEdgeEClass, BPMN_EDGE__BPMN_LABEL);
        createEAttribute(bpmnEdgeEClass, BPMN_EDGE__BPMN_ELEMENT);
        createEAttribute(bpmnEdgeEClass, BPMN_EDGE__MESSAGE_VISIBLE_KIND);
        createEAttribute(bpmnEdgeEClass, BPMN_EDGE__SOURCE_ELEMENT);
        createEAttribute(bpmnEdgeEClass, BPMN_EDGE__TARGET_ELEMENT);

        bpmnLabelEClass = createEClass(BPMN_LABEL);
        createEAttribute(bpmnLabelEClass, BPMN_LABEL__LABEL_STYLE);

        bpmnLabelStyleEClass = createEClass(BPMN_LABEL_STYLE);
        createEReference(bpmnLabelStyleEClass, BPMN_LABEL_STYLE__FONT);

        bpmnPlaneEClass = createEClass(BPMN_PLANE);
        createEAttribute(bpmnPlaneEClass, BPMN_PLANE__BPMN_ELEMENT);

        bpmnShapeEClass = createEClass(BPMN_SHAPE);
        createEReference(bpmnShapeEClass, BPMN_SHAPE__BPMN_LABEL);
        createEAttribute(bpmnShapeEClass, BPMN_SHAPE__BPMN_ELEMENT);
        createEAttribute(bpmnShapeEClass, BPMN_SHAPE__CHOREOGRAPHY_ACTIVITY_SHAPE);
        createEAttribute(bpmnShapeEClass, BPMN_SHAPE__IS_EXPANDED);
        createEAttribute(bpmnShapeEClass, BPMN_SHAPE__IS_HORIZONTAL);
        createEAttribute(bpmnShapeEClass, BPMN_SHAPE__IS_MARKER_VISIBLE);
        createEAttribute(bpmnShapeEClass, BPMN_SHAPE__IS_MESSAGE_VISIBLE);
        createEAttribute(bpmnShapeEClass, BPMN_SHAPE__PARTICIPANT_BAND_KIND);

        documentRootEClass = createEClass(DOCUMENT_ROOT);
        createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__BPMN_DIAGRAM);
        createEReference(documentRootEClass, DOCUMENT_ROOT__BPMN_EDGE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__BPMN_LABEL);
        createEReference(documentRootEClass, DOCUMENT_ROOT__BPMN_LABEL_STYLE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__BPMN_PLANE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__BPMN_SHAPE);

        // Create enums
        messageVisibleKindEEnum = createEEnum(MESSAGE_VISIBLE_KIND);
        participantBandKindEEnum = createEEnum(PARTICIPANT_BAND_KIND);

        // Create data types
        messageVisibleKindObjectEDataType = createEDataType(MESSAGE_VISIBLE_KIND_OBJECT);
        participantBandKindObjectEDataType = createEDataType(PARTICIPANT_BAND_KIND_OBJECT);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        org.omg.spec.dd.di.DiPackage theDiPackage_1 = (org.omg.spec.dd.di.DiPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.omg.spec.dd.di.DiPackage.eNS_URI);
        XMLTypePackage theXMLTypePackage = (XMLTypePackage) EPackage.Registry.INSTANCE
                .getEPackage(XMLTypePackage.eNS_URI);
        DcPackage theDcPackage = (DcPackage) EPackage.Registry.INSTANCE.getEPackage(DcPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        bpmnDiagramEClass.getESuperTypes().add(theDiPackage_1.getDiagram());
        bpmnEdgeEClass.getESuperTypes().add(theDiPackage_1.getLabeledEdge());
        bpmnLabelEClass.getESuperTypes().add(theDiPackage_1.getLabel());
        bpmnLabelStyleEClass.getESuperTypes().add(theDiPackage_1.getStyle());
        bpmnPlaneEClass.getESuperTypes().add(theDiPackage_1.getPlane());
        bpmnShapeEClass.getESuperTypes().add(theDiPackage_1.getLabeledShape());

        // Initialize classes and features; add operations and parameters
        initEClass(bpmnDiagramEClass, BPMNDiagram.class, "BPMNDiagram", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBPMNDiagram_BPMNPlane(), this.getBPMNPlane(), null, "bPMNPlane", null, 1, 1, //$NON-NLS-1$
                BPMNDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBPMNDiagram_BPMNLabelStyle(), this.getBPMNLabelStyle(), null, "bPMNLabelStyle", null, 0, -1, //$NON-NLS-1$
                BPMNDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(bpmnEdgeEClass, BPMNEdge.class, "BPMNEdge", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBPMNEdge_BPMNLabel(), this.getBPMNLabel(), null, "bPMNLabel", null, 0, 1, BPMNEdge.class, //$NON-NLS-1$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBPMNEdge_BpmnElement(), theXMLTypePackage.getQName(), "bpmnElement", null, 0, 1, //$NON-NLS-1$
                BPMNEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBPMNEdge_MessageVisibleKind(), this.getMessageVisibleKind(), "messageVisibleKind", null, 0, 1, //$NON-NLS-1$
                BPMNEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBPMNEdge_SourceElement(), theXMLTypePackage.getQName(), "sourceElement", null, 0, 1, //$NON-NLS-1$
                BPMNEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBPMNEdge_TargetElement(), theXMLTypePackage.getQName(), "targetElement", null, 0, 1, //$NON-NLS-1$
                BPMNEdge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(bpmnLabelEClass, BPMNLabel.class, "BPMNLabel", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBPMNLabel_LabelStyle(), theXMLTypePackage.getQName(), "labelStyle", null, 0, 1, //$NON-NLS-1$
                BPMNLabel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(bpmnLabelStyleEClass, BPMNLabelStyle.class, "BPMNLabelStyle", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBPMNLabelStyle_Font(), theDcPackage.getFont(), null, "font", null, 1, 1, BPMNLabelStyle.class, //$NON-NLS-1$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(bpmnPlaneEClass, BPMNPlane.class, "BPMNPlane", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBPMNPlane_BpmnElement(), theXMLTypePackage.getQName(), "bpmnElement", null, 0, 1, //$NON-NLS-1$
                BPMNPlane.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(bpmnShapeEClass, BPMNShape.class, "BPMNShape", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBPMNShape_BPMNLabel(), this.getBPMNLabel(), null, "bPMNLabel", null, 0, 1, BPMNShape.class, //$NON-NLS-1$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBPMNShape_BpmnElement(), theXMLTypePackage.getQName(), "bpmnElement", null, 0, 1, //$NON-NLS-1$
                BPMNShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBPMNShape_ChoreographyActivityShape(), theXMLTypePackage.getQName(),
                "choreographyActivityShape", null, 0, 1, BPMNShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBPMNShape_IsExpanded(), theXMLTypePackage.getBoolean(), "isExpanded", null, 0, 1, //$NON-NLS-1$
                BPMNShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBPMNShape_IsHorizontal(), theXMLTypePackage.getBoolean(), "isHorizontal", null, 0, 1, //$NON-NLS-1$
                BPMNShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBPMNShape_IsMarkerVisible(), theXMLTypePackage.getBoolean(), "isMarkerVisible", null, 0, 1, //$NON-NLS-1$
                BPMNShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBPMNShape_IsMessageVisible(), theXMLTypePackage.getBoolean(), "isMessageVisible", null, 0, 1, //$NON-NLS-1$
                BPMNShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBPMNShape_ParticipantBandKind(), this.getParticipantBandKind(), "participantBandKind", null, //$NON-NLS-1$
                0, 1, BPMNShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, //$NON-NLS-1$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);
        initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null,
                "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, //$NON-NLS-1$
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null,
                "xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, //$NON-NLS-1$
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_BPMNDiagram(), this.getBPMNDiagram(), null, "bPMNDiagram", null, 0, -2, null, //$NON-NLS-1$
                IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
                IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_BPMNEdge(), this.getBPMNEdge(), null, "bPMNEdge", null, 0, -2, null, //$NON-NLS-1$
                IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
                IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_BPMNLabel(), this.getBPMNLabel(), null, "bPMNLabel", null, 0, -2, null, //$NON-NLS-1$
                IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
                IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_BPMNLabelStyle(), this.getBPMNLabelStyle(), null, "bPMNLabelStyle", null, 0, -2, //$NON-NLS-1$
                null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_BPMNPlane(), this.getBPMNPlane(), null, "bPMNPlane", null, 0, -2, null, //$NON-NLS-1$
                IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
                IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_BPMNShape(), this.getBPMNShape(), null, "bPMNShape", null, 0, -2, null, //$NON-NLS-1$
                IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
                IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(messageVisibleKindEEnum, MessageVisibleKind.class, "MessageVisibleKind"); //$NON-NLS-1$
        addEEnumLiteral(messageVisibleKindEEnum, MessageVisibleKind.INITIATING);
        addEEnumLiteral(messageVisibleKindEEnum, MessageVisibleKind.NON_INITIATING);

        initEEnum(participantBandKindEEnum, ParticipantBandKind.class, "ParticipantBandKind"); //$NON-NLS-1$
        addEEnumLiteral(participantBandKindEEnum, ParticipantBandKind.TOP_INITIATING);
        addEEnumLiteral(participantBandKindEEnum, ParticipantBandKind.MIDDLE_INITIATING);
        addEEnumLiteral(participantBandKindEEnum, ParticipantBandKind.BOTTOM_INITIATING);
        addEEnumLiteral(participantBandKindEEnum, ParticipantBandKind.TOP_NON_INITIATING);
        addEEnumLiteral(participantBandKindEEnum, ParticipantBandKind.MIDDLE_NON_INITIATING);
        addEEnumLiteral(participantBandKindEEnum, ParticipantBandKind.BOTTOM_NON_INITIATING);

        // Initialize data types
        initEDataType(messageVisibleKindObjectEDataType, MessageVisibleKind.class, "MessageVisibleKindObject", //$NON-NLS-1$
                IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(participantBandKindObjectEDataType, ParticipantBandKind.class, "ParticipantBandKindObject", //$NON-NLS-1$
                IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
        createExtendedMetaDataAnnotations();
    }

    /**
     * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void createExtendedMetaDataAnnotations() {
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData"; //$NON-NLS-1$
        addAnnotation(bpmnDiagramEClass,
                source,
                new String[] {
                        "name", "BPMNDiagram", //$NON-NLS-1$ //$NON-NLS-2$
                        "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getBPMNDiagram_BPMNPlane(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "BPMNPlane", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getBPMNDiagram_BPMNLabelStyle(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "BPMNLabelStyle", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(bpmnEdgeEClass,
                source,
                new String[] {
                        "name", "BPMNEdge", //$NON-NLS-1$ //$NON-NLS-2$
                        "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getBPMNEdge_BPMNLabel(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "BPMNLabel", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getBPMNEdge_BpmnElement(),
                source,
                new String[] {
                        "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "bpmnElement" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getBPMNEdge_MessageVisibleKind(),
                source,
                new String[] {
                        "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "messageVisibleKind" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getBPMNEdge_SourceElement(),
                source,
                new String[] {
                        "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "sourceElement" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getBPMNEdge_TargetElement(),
                source,
                new String[] {
                        "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "targetElement" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(bpmnLabelEClass,
                source,
                new String[] {
                        "name", "BPMNLabel", //$NON-NLS-1$ //$NON-NLS-2$
                        "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getBPMNLabel_LabelStyle(),
                source,
                new String[] {
                        "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "labelStyle" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(bpmnLabelStyleEClass,
                source,
                new String[] {
                        "name", "BPMNLabelStyle", //$NON-NLS-1$ //$NON-NLS-2$
                        "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getBPMNLabelStyle_Font(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "Font", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "http://www.omg.org/spec/DD/20100524/DC" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(bpmnPlaneEClass,
                source,
                new String[] {
                        "name", "BPMNPlane", //$NON-NLS-1$ //$NON-NLS-2$
                        "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getBPMNPlane_BpmnElement(),
                source,
                new String[] {
                        "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "bpmnElement" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(bpmnShapeEClass,
                source,
                new String[] {
                        "name", "BPMNShape", //$NON-NLS-1$ //$NON-NLS-2$
                        "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getBPMNShape_BPMNLabel(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "BPMNLabel", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getBPMNShape_BpmnElement(),
                source,
                new String[] {
                        "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "bpmnElement" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getBPMNShape_ChoreographyActivityShape(),
                source,
                new String[] {
                        "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "choreographyActivityShape" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getBPMNShape_IsExpanded(),
                source,
                new String[] {
                        "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "isExpanded" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getBPMNShape_IsHorizontal(),
                source,
                new String[] {
                        "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "isHorizontal" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getBPMNShape_IsMarkerVisible(),
                source,
                new String[] {
                        "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "isMarkerVisible" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getBPMNShape_IsMessageVisible(),
                source,
                new String[] {
                        "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "isMessageVisible" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getBPMNShape_ParticipantBandKind(),
                source,
                new String[] {
                        "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "participantBandKind" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(documentRootEClass,
                source,
                new String[] {
                        "name", "", //$NON-NLS-1$ //$NON-NLS-2$
                        "kind", "mixed" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDocumentRoot_Mixed(),
                source,
                new String[] {
                        "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", ":mixed" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDocumentRoot_XMLNSPrefixMap(),
                source,
                new String[] {
                        "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "xmlns:prefix" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDocumentRoot_XSISchemaLocation(),
                source,
                new String[] {
                        "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "xsi:schemaLocation" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDocumentRoot_BPMNDiagram(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "BPMNDiagram", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDocumentRoot_BPMNEdge(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "BPMNEdge", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
                        "affiliation", "http://www.omg.org/spec/DD/20100524/DI#DiagramElement" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDocumentRoot_BPMNLabel(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "BPMNLabel", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDocumentRoot_BPMNLabelStyle(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "BPMNLabelStyle", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDocumentRoot_BPMNPlane(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "BPMNPlane", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDocumentRoot_BPMNShape(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "BPMNShape", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
                        "affiliation", "http://www.omg.org/spec/DD/20100524/DI#DiagramElement" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(messageVisibleKindEEnum,
                source,
                new String[] {
                        "name", "MessageVisibleKind" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(messageVisibleKindObjectEDataType,
                source,
                new String[] {
                        "name", "MessageVisibleKind:Object", //$NON-NLS-1$ //$NON-NLS-2$
                        "baseType", "MessageVisibleKind" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(participantBandKindEEnum,
                source,
                new String[] {
                        "name", "ParticipantBandKind" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(participantBandKindObjectEDataType,
                source,
                new String[] {
                        "name", "ParticipantBandKind:Object", //$NON-NLS-1$ //$NON-NLS-2$
                        "baseType", "ParticipantBandKind" //$NON-NLS-1$ //$NON-NLS-2$
                });
    }

} //DiPackageImpl
