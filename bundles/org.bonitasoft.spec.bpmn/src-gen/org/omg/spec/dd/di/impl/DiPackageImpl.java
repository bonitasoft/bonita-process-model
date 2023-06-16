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
package org.omg.spec.dd.di.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.omg.spec.bpmn.model.ModelPackage;

import org.omg.spec.bpmn.model.impl.ModelPackageImpl;

import org.omg.spec.dd.dc.DcPackage;

import org.omg.spec.dd.dc.impl.DcPackageImpl;

import org.omg.spec.dd.di.DiFactory;
import org.omg.spec.dd.di.DiPackage;
import org.omg.spec.dd.di.Diagram;
import org.omg.spec.dd.di.DiagramElement;
import org.omg.spec.dd.di.DocumentRoot;
import org.omg.spec.dd.di.Edge;
import org.omg.spec.dd.di.ExtensionType;
import org.omg.spec.dd.di.Label;
import org.omg.spec.dd.di.LabeledEdge;
import org.omg.spec.dd.di.LabeledShape;
import org.omg.spec.dd.di.Node;
import org.omg.spec.dd.di.Plane;
import org.omg.spec.dd.di.Shape;
import org.omg.spec.dd.di.Style;

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
    private EClass diagramEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass diagramElementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass edgeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass extensionTypeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass labelEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass labeledEdgeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass labeledShapeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass nodeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass planeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass shapeEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass styleEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass documentRootEClass = null;

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
     * @see org.omg.spec.dd.di.DiPackage#eNS_URI
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
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(org.omg.spec.bpmn.di.DiPackage.eNS_URI);
        org.omg.spec.bpmn.di.impl.DiPackageImpl theDiPackage_1 = (org.omg.spec.bpmn.di.impl.DiPackageImpl) (registeredPackage instanceof org.omg.spec.bpmn.di.impl.DiPackageImpl
                ? registeredPackage : org.omg.spec.bpmn.di.DiPackage.eINSTANCE);

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
    public EClass getDiagram() {
        return diagramEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDiagram_Documentation() {
        return (EAttribute) diagramEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDiagram_Id() {
        return (EAttribute) diagramEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDiagram_Name() {
        return (EAttribute) diagramEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDiagram_Resolution() {
        return (EAttribute) diagramEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getDiagramElement() {
        return diagramElementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDiagramElement_Extension() {
        return (EReference) diagramElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDiagramElement_Id() {
        return (EAttribute) diagramElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getDiagramElement_AnyAttribute() {
        return (EAttribute) diagramElementEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getEdge() {
        return edgeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getEdge_Waypoint() {
        return (EReference) edgeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getExtensionType() {
        return extensionTypeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getExtensionType_Any() {
        return (EAttribute) extensionTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getLabel() {
        return labelEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getLabel_Bounds() {
        return (EReference) labelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getLabeledEdge() {
        return labeledEdgeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getLabeledShape() {
        return labeledShapeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getNode() {
        return nodeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getPlane() {
        return planeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getPlane_DiagramElementGroup() {
        return (EAttribute) planeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getPlane_DiagramElement() {
        return (EReference) planeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getShape() {
        return shapeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getShape_Bounds() {
        return (EReference) shapeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getStyle() {
        return styleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getStyle_Id() {
        return (EAttribute) styleEClass.getEStructuralFeatures().get(0);
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
    public EReference getDocumentRoot_DiagramElement() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Diagram() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Edge() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Label() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDocumentRoot_LabeledEdge() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDocumentRoot_LabeledShape() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Node() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Plane() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Shape() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDocumentRoot_Style() {
        return (EReference) documentRootEClass.getEStructuralFeatures().get(12);
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
        diagramEClass = createEClass(DIAGRAM);
        createEAttribute(diagramEClass, DIAGRAM__DOCUMENTATION);
        createEAttribute(diagramEClass, DIAGRAM__ID);
        createEAttribute(diagramEClass, DIAGRAM__NAME);
        createEAttribute(diagramEClass, DIAGRAM__RESOLUTION);

        diagramElementEClass = createEClass(DIAGRAM_ELEMENT);
        createEReference(diagramElementEClass, DIAGRAM_ELEMENT__EXTENSION);
        createEAttribute(diagramElementEClass, DIAGRAM_ELEMENT__ID);
        createEAttribute(diagramElementEClass, DIAGRAM_ELEMENT__ANY_ATTRIBUTE);

        edgeEClass = createEClass(EDGE);
        createEReference(edgeEClass, EDGE__WAYPOINT);

        extensionTypeEClass = createEClass(EXTENSION_TYPE);
        createEAttribute(extensionTypeEClass, EXTENSION_TYPE__ANY);

        labelEClass = createEClass(LABEL);
        createEReference(labelEClass, LABEL__BOUNDS);

        labeledEdgeEClass = createEClass(LABELED_EDGE);

        labeledShapeEClass = createEClass(LABELED_SHAPE);

        nodeEClass = createEClass(NODE);

        planeEClass = createEClass(PLANE);
        createEAttribute(planeEClass, PLANE__DIAGRAM_ELEMENT_GROUP);
        createEReference(planeEClass, PLANE__DIAGRAM_ELEMENT);

        shapeEClass = createEClass(SHAPE);
        createEReference(shapeEClass, SHAPE__BOUNDS);

        styleEClass = createEClass(STYLE);
        createEAttribute(styleEClass, STYLE__ID);

        documentRootEClass = createEClass(DOCUMENT_ROOT);
        createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__DIAGRAM_ELEMENT);
        createEReference(documentRootEClass, DOCUMENT_ROOT__DIAGRAM);
        createEReference(documentRootEClass, DOCUMENT_ROOT__EDGE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__LABEL);
        createEReference(documentRootEClass, DOCUMENT_ROOT__LABELED_EDGE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__LABELED_SHAPE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__NODE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__PLANE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__SHAPE);
        createEReference(documentRootEClass, DOCUMENT_ROOT__STYLE);
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
        XMLTypePackage theXMLTypePackage = (XMLTypePackage) EPackage.Registry.INSTANCE
                .getEPackage(XMLTypePackage.eNS_URI);
        DcPackage theDcPackage = (DcPackage) EPackage.Registry.INSTANCE.getEPackage(DcPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        edgeEClass.getESuperTypes().add(this.getDiagramElement());
        labelEClass.getESuperTypes().add(this.getNode());
        labeledEdgeEClass.getESuperTypes().add(this.getEdge());
        labeledShapeEClass.getESuperTypes().add(this.getShape());
        nodeEClass.getESuperTypes().add(this.getDiagramElement());
        planeEClass.getESuperTypes().add(this.getNode());
        shapeEClass.getESuperTypes().add(this.getNode());

        // Initialize classes and features; add operations and parameters
        initEClass(diagramEClass, Diagram.class, "Diagram", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDiagram_Documentation(), theXMLTypePackage.getString(), "documentation", null, 0, 1, //$NON-NLS-1$
                Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDiagram_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, Diagram.class, !IS_TRANSIENT, //$NON-NLS-1$
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDiagram_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, Diagram.class, //$NON-NLS-1$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDiagram_Resolution(), theXMLTypePackage.getDouble(), "resolution", null, 0, 1, Diagram.class, //$NON-NLS-1$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(diagramElementEClass, DiagramElement.class, "DiagramElement", IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDiagramElement_Extension(), this.getExtensionType(), null, "extension", null, 0, 1, //$NON-NLS-1$
                DiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDiagramElement_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, DiagramElement.class, //$NON-NLS-1$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDiagramElement_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, //$NON-NLS-1$
                -1, DiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(edgeEClass, Edge.class, "Edge", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getEdge_Waypoint(), theDcPackage.getPoint(), null, "waypoint", null, 2, -1, Edge.class, //$NON-NLS-1$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(extensionTypeEClass, ExtensionType.class, "ExtensionType", !IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getExtensionType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, //$NON-NLS-1$
                ExtensionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(labelEClass, Label.class, "Label", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getLabel_Bounds(), theDcPackage.getBounds(), null, "bounds", null, 0, 1, Label.class, //$NON-NLS-1$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(labeledEdgeEClass, LabeledEdge.class, "LabeledEdge", IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
                IS_GENERATED_INSTANCE_CLASS);

        initEClass(labeledShapeEClass, LabeledShape.class, "LabeledShape", IS_ABSTRACT, !IS_INTERFACE, //$NON-NLS-1$
                IS_GENERATED_INSTANCE_CLASS);

        initEClass(nodeEClass, Node.class, "Node", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(planeEClass, Plane.class, "Plane", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getPlane_DiagramElementGroup(), ecorePackage.getEFeatureMapEntry(), "diagramElementGroup", null, //$NON-NLS-1$
                0, -1, Plane.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getPlane_DiagramElement(), this.getDiagramElement(), null, "diagramElement", null, 0, -1, //$NON-NLS-1$
                Plane.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

        initEClass(shapeEClass, Shape.class, "Shape", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getShape_Bounds(), theDcPackage.getBounds(), null, "bounds", null, 1, 1, Shape.class, //$NON-NLS-1$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(styleEClass, Style.class, "Style", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getStyle_Id(), theXMLTypePackage.getID(), "id", null, 0, 1, Style.class, !IS_TRANSIENT, //$NON-NLS-1$
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
        initEReference(getDocumentRoot_DiagramElement(), this.getDiagramElement(), null, "diagramElement", null, 0, -2, //$NON-NLS-1$
                null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Diagram(), this.getDiagram(), null, "diagram", null, 0, -2, null, IS_TRANSIENT, //$NON-NLS-1$
                IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
                IS_ORDERED);
        initEReference(getDocumentRoot_Edge(), this.getEdge(), null, "edge", null, 0, -2, null, IS_TRANSIENT, //$NON-NLS-1$
                IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
                IS_ORDERED);
        initEReference(getDocumentRoot_Label(), this.getLabel(), null, "label", null, 0, -2, null, IS_TRANSIENT, //$NON-NLS-1$
                IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
                IS_ORDERED);
        initEReference(getDocumentRoot_LabeledEdge(), this.getLabeledEdge(), null, "labeledEdge", null, 0, -2, null, //$NON-NLS-1$
                IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
                IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_LabeledShape(), this.getLabeledShape(), null, "labeledShape", null, 0, -2, null, //$NON-NLS-1$
                IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
                IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentRoot_Node(), this.getNode(), null, "node", null, 0, -2, null, IS_TRANSIENT, //$NON-NLS-1$
                IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
                IS_ORDERED);
        initEReference(getDocumentRoot_Plane(), this.getPlane(), null, "plane", null, 0, -2, null, IS_TRANSIENT, //$NON-NLS-1$
                IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
                IS_ORDERED);
        initEReference(getDocumentRoot_Shape(), this.getShape(), null, "shape", null, 0, -2, null, IS_TRANSIENT, //$NON-NLS-1$
                IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
                IS_ORDERED);
        initEReference(getDocumentRoot_Style(), this.getStyle(), null, "style", null, 0, -2, null, IS_TRANSIENT, //$NON-NLS-1$
                IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
                IS_ORDERED);

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
        addAnnotation(diagramEClass,
                source,
                new String[] {
                        "name", "Diagram", //$NON-NLS-1$ //$NON-NLS-2$
                        "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDiagram_Documentation(),
                source,
                new String[] {
                        "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "documentation" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDiagram_Id(),
                source,
                new String[] {
                        "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDiagram_Name(),
                source,
                new String[] {
                        "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "name" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDiagram_Resolution(),
                source,
                new String[] {
                        "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "resolution" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(diagramElementEClass,
                source,
                new String[] {
                        "name", "DiagramElement", //$NON-NLS-1$ //$NON-NLS-2$
                        "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDiagramElement_Extension(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "extension", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDiagramElement_Id(),
                source,
                new String[] {
                        "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDiagramElement_AnyAttribute(),
                source,
                new String[] {
                        "kind", "attributeWildcard", //$NON-NLS-1$ //$NON-NLS-2$
                        "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", ":2", //$NON-NLS-1$ //$NON-NLS-2$
                        "processing", "lax" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(edgeEClass,
                source,
                new String[] {
                        "name", "Edge", //$NON-NLS-1$ //$NON-NLS-2$
                        "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getEdge_Waypoint(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "waypoint", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(extensionTypeEClass,
                source,
                new String[] {
                        "name", "extension_._type", //$NON-NLS-1$ //$NON-NLS-2$
                        "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getExtensionType_Any(),
                source,
                new String[] {
                        "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
                        "wildcards", "##other", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", ":0", //$NON-NLS-1$ //$NON-NLS-2$
                        "processing", "strict" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(labelEClass,
                source,
                new String[] {
                        "name", "Label", //$NON-NLS-1$ //$NON-NLS-2$
                        "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getLabel_Bounds(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "Bounds", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "http://www.omg.org/spec/DD/20100524/DC" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(labeledEdgeEClass,
                source,
                new String[] {
                        "name", "LabeledEdge", //$NON-NLS-1$ //$NON-NLS-2$
                        "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(labeledShapeEClass,
                source,
                new String[] {
                        "name", "LabeledShape", //$NON-NLS-1$ //$NON-NLS-2$
                        "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(nodeEClass,
                source,
                new String[] {
                        "name", "Node", //$NON-NLS-1$ //$NON-NLS-2$
                        "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(planeEClass,
                source,
                new String[] {
                        "name", "Plane", //$NON-NLS-1$ //$NON-NLS-2$
                        "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getPlane_DiagramElementGroup(),
                source,
                new String[] {
                        "kind", "group", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "DiagramElement:group", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getPlane_DiagramElement(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "DiagramElement", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace", //$NON-NLS-1$ //$NON-NLS-2$
                        "group", "DiagramElement:group" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(shapeEClass,
                source,
                new String[] {
                        "name", "Shape", //$NON-NLS-1$ //$NON-NLS-2$
                        "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getShape_Bounds(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "Bounds", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "http://www.omg.org/spec/DD/20100524/DC" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(styleEClass,
                source,
                new String[] {
                        "name", "Style", //$NON-NLS-1$ //$NON-NLS-2$
                        "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getStyle_Id(),
                source,
                new String[] {
                        "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
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
        addAnnotation(getDocumentRoot_DiagramElement(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "DiagramElement", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDocumentRoot_Diagram(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "Diagram", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDocumentRoot_Edge(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "Edge", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDocumentRoot_Label(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "Label", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDocumentRoot_LabeledEdge(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "LabeledEdge", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDocumentRoot_LabeledShape(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "LabeledShape", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDocumentRoot_Node(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "Node", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDocumentRoot_Plane(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "Plane", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDocumentRoot_Shape(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "Shape", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                });
        addAnnotation(getDocumentRoot_Style(),
                source,
                new String[] {
                        "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
                        "name", "Style", //$NON-NLS-1$ //$NON-NLS-2$
                        "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
                });
    }

} //DiPackageImpl
