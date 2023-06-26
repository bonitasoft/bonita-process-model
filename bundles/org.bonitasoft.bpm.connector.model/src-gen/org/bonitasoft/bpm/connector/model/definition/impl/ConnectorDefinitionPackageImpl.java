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
package org.bonitasoft.bpm.connector.model.definition.impl;

import org.bonitasoft.bpm.connector.model.definition.Array;
import org.bonitasoft.bpm.connector.model.definition.Category;
import org.bonitasoft.bpm.connector.model.definition.Checkbox;
import org.bonitasoft.bpm.connector.model.definition.Component;
import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.bpm.connector.model.definition.DocumentRoot;
import org.bonitasoft.bpm.connector.model.definition.Group;
import org.bonitasoft.bpm.connector.model.definition.Input;
import org.bonitasoft.bpm.connector.model.definition.List;
import org.bonitasoft.bpm.connector.model.definition.Orientation;
import org.bonitasoft.bpm.connector.model.definition.Output;
import org.bonitasoft.bpm.connector.model.definition.Page;
import org.bonitasoft.bpm.connector.model.definition.Password;
import org.bonitasoft.bpm.connector.model.definition.RadioGroup;
import org.bonitasoft.bpm.connector.model.definition.ScriptEditor;
import org.bonitasoft.bpm.connector.model.definition.Select;
import org.bonitasoft.bpm.connector.model.definition.Text;
import org.bonitasoft.bpm.connector.model.definition.TextArea;
import org.bonitasoft.bpm.connector.model.definition.UnloadableConnectorDefinition;
import org.bonitasoft.bpm.connector.model.definition.Widget;
import org.bonitasoft.bpm.connector.model.definition.WidgetComponent;

import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementationPackage;

import org.bonitasoft.bpm.connector.model.implementation.impl.ConnectorImplementationPackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConnectorDefinitionPackageImpl extends EPackageImpl implements ConnectorDefinitionPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass arrayEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass categoryEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass checkboxEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass componentEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass connectorDefinitionEClass = null;

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
    private EClass groupEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass inputEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass listEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass outputEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass pageEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass passwordEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass radioGroupEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass selectEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass textEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass textAreaEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass widgetEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass widgetComponentEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass unloadableConnectorDefinitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scriptEditorEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum orientationEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType orientationObjectEDataType = null;

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
     * @see org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ConnectorDefinitionPackageImpl() {
        super(eNS_URI, ConnectorDefinitionFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link ConnectorDefinitionPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ConnectorDefinitionPackage init() {
        if (isInited) return (ConnectorDefinitionPackage)EPackage.Registry.INSTANCE.getEPackage(ConnectorDefinitionPackage.eNS_URI);

        // Obtain or create and register package
        Object registeredConnectorDefinitionPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        ConnectorDefinitionPackageImpl theConnectorDefinitionPackage = registeredConnectorDefinitionPackage instanceof ConnectorDefinitionPackageImpl ? (ConnectorDefinitionPackageImpl)registeredConnectorDefinitionPackage : new ConnectorDefinitionPackageImpl();

        isInited = true;

        // Initialize simple dependencies
        XMLTypePackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ConnectorImplementationPackage.eNS_URI);
        ConnectorImplementationPackageImpl theConnectorImplementationPackage = (ConnectorImplementationPackageImpl)(registeredPackage instanceof ConnectorImplementationPackageImpl ? registeredPackage : ConnectorImplementationPackage.eINSTANCE);

        // Create package meta-data objects
        theConnectorDefinitionPackage.createPackageContents();
        theConnectorImplementationPackage.createPackageContents();

        // Initialize created meta-data
        theConnectorDefinitionPackage.initializePackageContents();
        theConnectorImplementationPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theConnectorDefinitionPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ConnectorDefinitionPackage.eNS_URI, theConnectorDefinitionPackage);
        return theConnectorDefinitionPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getArray() {
        return arrayEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getArray_ColsCaption() {
        return (EAttribute)arrayEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getArray_Cols() {
        return (EAttribute)arrayEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getArray_FixedCols() {
        return (EAttribute)arrayEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getArray_FixedRows() {
        return (EAttribute)arrayEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getArray_Rows() {
        return (EAttribute)arrayEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getCategory() {
        return categoryEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getCategory_Icon() {
        return (EAttribute)categoryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getCategory_Id() {
        return (EAttribute)categoryEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getCategory_ParentCategoryId() {
        return (EAttribute)categoryEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getCheckbox() {
        return checkboxEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getComponent() {
        return componentEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getComponent_Id() {
        return (EAttribute)componentEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getConnectorDefinition() {
        return connectorDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getConnectorDefinition_Id() {
        return (EAttribute)connectorDefinitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getConnectorDefinition_Version() {
        return (EAttribute)connectorDefinitionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getConnectorDefinition_Icon() {
        return (EAttribute)connectorDefinitionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getConnectorDefinition_Category() {
        return (EReference)connectorDefinitionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getConnectorDefinition_Input() {
        return (EReference)connectorDefinitionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getConnectorDefinition_Output() {
        return (EReference)connectorDefinitionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getConnectorDefinition_Page() {
        return (EReference)connectorDefinitionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getConnectorDefinition_JarDependency() {
        return (EAttribute)connectorDefinitionEClass.getEStructuralFeatures().get(7);
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
        return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDocumentRoot_XMLNSPrefixMap() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDocumentRoot_XSISchemaLocation() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getDocumentRoot_ConnectorDefinition() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getGroup() {
        return groupEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getGroup_Widget() {
        return (EReference)groupEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getGroup_Optional() {
        return (EAttribute)groupEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getInput() {
        return inputEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getInput_DefaultValue() {
        return (EAttribute)inputEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getInput_Mandatory() {
        return (EAttribute)inputEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getInput_Name() {
        return (EAttribute)inputEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getInput_Type() {
        return (EAttribute)inputEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getList() {
        return listEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getList_ShowDocuments() {
        return (EAttribute)listEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getOutput() {
        return outputEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getOutput_Name() {
        return (EAttribute)outputEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getOutput_Type() {
        return (EAttribute)outputEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getPage() {
        return pageEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getPage_Widget() {
        return (EReference)pageEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getPage_Id() {
        return (EAttribute)pageEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getPassword() {
        return passwordEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getRadioGroup() {
        return radioGroupEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getRadioGroup_Choices() {
        return (EAttribute)radioGroupEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getRadioGroup_Orientation() {
        return (EAttribute)radioGroupEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getSelect() {
        return selectEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getSelect_Items() {
        return (EAttribute)selectEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getSelect_ReadOnly() {
        return (EAttribute)selectEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getText() {
        return textEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getText_ShowDocuments() {
        return (EAttribute)textEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getTextArea() {
        return textAreaEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getWidget() {
        return widgetEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getWidgetComponent() {
        return widgetComponentEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getWidgetComponent_InputName() {
        return (EAttribute)widgetComponentEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getUnloadableConnectorDefinition() {
        return unloadableConnectorDefinitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getScriptEditor() {
        return scriptEditorEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getScriptEditor_Interpreter() {
        return (EAttribute)scriptEditorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EEnum getOrientation() {
        return orientationEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EDataType getOrientationObject() {
        return orientationObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ConnectorDefinitionFactory getConnectorDefinitionFactory() {
        return (ConnectorDefinitionFactory)getEFactoryInstance();
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
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        arrayEClass = createEClass(ARRAY);
        createEAttribute(arrayEClass, ARRAY__COLS_CAPTION);
        createEAttribute(arrayEClass, ARRAY__COLS);
        createEAttribute(arrayEClass, ARRAY__FIXED_COLS);
        createEAttribute(arrayEClass, ARRAY__FIXED_ROWS);
        createEAttribute(arrayEClass, ARRAY__ROWS);

        categoryEClass = createEClass(CATEGORY);
        createEAttribute(categoryEClass, CATEGORY__ICON);
        createEAttribute(categoryEClass, CATEGORY__ID);
        createEAttribute(categoryEClass, CATEGORY__PARENT_CATEGORY_ID);

        checkboxEClass = createEClass(CHECKBOX);

        componentEClass = createEClass(COMPONENT);
        createEAttribute(componentEClass, COMPONENT__ID);

        connectorDefinitionEClass = createEClass(CONNECTOR_DEFINITION);
        createEAttribute(connectorDefinitionEClass, CONNECTOR_DEFINITION__ID);
        createEAttribute(connectorDefinitionEClass, CONNECTOR_DEFINITION__VERSION);
        createEAttribute(connectorDefinitionEClass, CONNECTOR_DEFINITION__ICON);
        createEReference(connectorDefinitionEClass, CONNECTOR_DEFINITION__CATEGORY);
        createEReference(connectorDefinitionEClass, CONNECTOR_DEFINITION__INPUT);
        createEReference(connectorDefinitionEClass, CONNECTOR_DEFINITION__OUTPUT);
        createEReference(connectorDefinitionEClass, CONNECTOR_DEFINITION__PAGE);
        createEAttribute(connectorDefinitionEClass, CONNECTOR_DEFINITION__JAR_DEPENDENCY);

        documentRootEClass = createEClass(DOCUMENT_ROOT);
        createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__CONNECTOR_DEFINITION);

        groupEClass = createEClass(GROUP);
        createEReference(groupEClass, GROUP__WIDGET);
        createEAttribute(groupEClass, GROUP__OPTIONAL);

        inputEClass = createEClass(INPUT);
        createEAttribute(inputEClass, INPUT__DEFAULT_VALUE);
        createEAttribute(inputEClass, INPUT__MANDATORY);
        createEAttribute(inputEClass, INPUT__NAME);
        createEAttribute(inputEClass, INPUT__TYPE);

        listEClass = createEClass(LIST);
        createEAttribute(listEClass, LIST__SHOW_DOCUMENTS);

        outputEClass = createEClass(OUTPUT);
        createEAttribute(outputEClass, OUTPUT__NAME);
        createEAttribute(outputEClass, OUTPUT__TYPE);

        pageEClass = createEClass(PAGE);
        createEReference(pageEClass, PAGE__WIDGET);
        createEAttribute(pageEClass, PAGE__ID);

        passwordEClass = createEClass(PASSWORD);

        radioGroupEClass = createEClass(RADIO_GROUP);
        createEAttribute(radioGroupEClass, RADIO_GROUP__CHOICES);
        createEAttribute(radioGroupEClass, RADIO_GROUP__ORIENTATION);

        selectEClass = createEClass(SELECT);
        createEAttribute(selectEClass, SELECT__ITEMS);
        createEAttribute(selectEClass, SELECT__READ_ONLY);

        textEClass = createEClass(TEXT);
        createEAttribute(textEClass, TEXT__SHOW_DOCUMENTS);

        textAreaEClass = createEClass(TEXT_AREA);

        widgetEClass = createEClass(WIDGET);

        widgetComponentEClass = createEClass(WIDGET_COMPONENT);
        createEAttribute(widgetComponentEClass, WIDGET_COMPONENT__INPUT_NAME);

        unloadableConnectorDefinitionEClass = createEClass(UNLOADABLE_CONNECTOR_DEFINITION);

        scriptEditorEClass = createEClass(SCRIPT_EDITOR);
        createEAttribute(scriptEditorEClass, SCRIPT_EDITOR__INTERPRETER);

        // Create enums
        orientationEEnum = createEEnum(ORIENTATION);

        // Create data types
        orientationObjectEDataType = createEDataType(ORIENTATION_OBJECT);
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
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        arrayEClass.getESuperTypes().add(this.getWidget());
        checkboxEClass.getESuperTypes().add(this.getWidget());
        groupEClass.getESuperTypes().add(this.getComponent());
        listEClass.getESuperTypes().add(this.getWidget());
        passwordEClass.getESuperTypes().add(this.getText());
        radioGroupEClass.getESuperTypes().add(this.getWidget());
        selectEClass.getESuperTypes().add(this.getWidget());
        textEClass.getESuperTypes().add(this.getWidget());
        textAreaEClass.getESuperTypes().add(this.getWidget());
        widgetEClass.getESuperTypes().add(this.getWidgetComponent());
        widgetComponentEClass.getESuperTypes().add(this.getComponent());
        unloadableConnectorDefinitionEClass.getESuperTypes().add(this.getConnectorDefinition());
        scriptEditorEClass.getESuperTypes().add(this.getWidget());

        // Initialize classes and features; add operations and parameters
        initEClass(arrayEClass, Array.class, "Array", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getArray_ColsCaption(), theXMLTypePackage.getString(), "colsCaption", null, 0, -1, Array.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getArray_Cols(), theXMLTypePackage.getInteger(), "cols", null, 0, 1, Array.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getArray_FixedCols(), theXMLTypePackage.getBoolean(), "fixedCols", "true", 0, 1, Array.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getArray_FixedRows(), theXMLTypePackage.getBoolean(), "fixedRows", null, 0, 1, Array.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getArray_Rows(), theXMLTypePackage.getInteger(), "rows", null, 0, 1, Array.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(categoryEClass, Category.class, "Category", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getCategory_Icon(), theXMLTypePackage.getString(), "icon", null, 0, 1, Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getCategory_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getCategory_ParentCategoryId(), theXMLTypePackage.getString(), "parentCategoryId", null, 0, 1, Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(checkboxEClass, Checkbox.class, "Checkbox", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(componentEClass, Component.class, "Component", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getComponent_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(connectorDefinitionEClass, ConnectorDefinition.class, "ConnectorDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getConnectorDefinition_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, ConnectorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getConnectorDefinition_Version(), theXMLTypePackage.getString(), "version", null, 1, 1, ConnectorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getConnectorDefinition_Icon(), theXMLTypePackage.getString(), "icon", null, 1, 1, ConnectorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getConnectorDefinition_Category(), this.getCategory(), null, "category", null, 0, -1, ConnectorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getConnectorDefinition_Input(), this.getInput(), null, "input", null, 0, -1, ConnectorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getConnectorDefinition_Output(), this.getOutput(), null, "output", null, 0, -1, ConnectorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getConnectorDefinition_Page(), this.getPage(), null, "page", null, 0, -1, ConnectorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getConnectorDefinition_JarDependency(), theXMLTypePackage.getString(), "jarDependency", null, 0, -1, ConnectorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ConnectorDefinition(), this.getConnectorDefinition(), null, "connectorDefinition", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(groupEClass, Group.class, "Group", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getGroup_Widget(), this.getComponent(), null, "widget", null, 0, -1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getGroup_Optional(), theXMLTypePackage.getBoolean(), "optional", null, 0, 1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(inputEClass, Input.class, "Input", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getInput_DefaultValue(), theXMLTypePackage.getString(), "defaultValue", null, 0, 1, Input.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getInput_Mandatory(), theXMLTypePackage.getBoolean(), "mandatory", "false", 0, 1, Input.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getInput_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, Input.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getInput_Type(), theXMLTypePackage.getString(), "type", null, 1, 1, Input.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(listEClass, List.class, "List", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getList_ShowDocuments(), ecorePackage.getEBoolean(), "showDocuments", "false", 0, 1, List.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(outputEClass, Output.class, "Output", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getOutput_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, Output.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getOutput_Type(), theXMLTypePackage.getString(), "type", null, 1, 1, Output.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(pageEClass, Page.class, "Page", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getPage_Widget(), this.getComponent(), null, "widget", null, 0, -1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getPage_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(passwordEClass, Password.class, "Password", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(radioGroupEClass, RadioGroup.class, "RadioGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getRadioGroup_Choices(), theXMLTypePackage.getString(), "choices", null, 0, -1, RadioGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getRadioGroup_Orientation(), this.getOrientation(), "orientation", "HORIZONTAL", 0, 1, RadioGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(selectEClass, Select.class, "Select", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getSelect_Items(), theXMLTypePackage.getString(), "items", null, 0, -1, Select.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getSelect_ReadOnly(), theXMLTypePackage.getBoolean(), "readOnly", null, 1, 1, Select.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(textEClass, Text.class, "Text", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getText_ShowDocuments(), ecorePackage.getEBoolean(), "showDocuments", "false", 0, 1, Text.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        initEClass(textAreaEClass, TextArea.class, "TextArea", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(widgetEClass, Widget.class, "Widget", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(widgetComponentEClass, WidgetComponent.class, "WidgetComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getWidgetComponent_InputName(), theXMLTypePackage.getString(), "inputName", null, 1, 1, WidgetComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(unloadableConnectorDefinitionEClass, UnloadableConnectorDefinition.class, "UnloadableConnectorDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(scriptEditorEClass, ScriptEditor.class, "ScriptEditor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getScriptEditor_Interpreter(), theXMLTypePackage.getString(), "interpreter", "GROOVY", 1, 1, ScriptEditor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

        // Initialize enums and add enum literals
        initEEnum(orientationEEnum, Orientation.class, "Orientation"); //$NON-NLS-1$
        addEEnumLiteral(orientationEEnum, Orientation.HORIZONTAL);
        addEEnumLiteral(orientationEEnum, Orientation.VERTICAL);

        // Initialize data types
        initEDataType(orientationObjectEDataType, Orientation.class, "OrientationObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http://www.eclipse.org/edapt
        createEdaptAnnotations();
        // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
        createExtendedMetaDataAnnotations();
    }

    /**
     * Initializes the annotations for <b>http://www.eclipse.org/edapt</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void createEdaptAnnotations() {
        String source = "http://www.eclipse.org/edapt"; //$NON-NLS-1$
        addAnnotation
          (this,
           source,
           new String[] {
               "historyURI", "connector.history" //$NON-NLS-1$ //$NON-NLS-2$
           });
    }

    /**
     * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void createExtendedMetaDataAnnotations() {
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData"; //$NON-NLS-1$
        addAnnotation
          (arrayEClass,
           source,
           new String[] {
               "name", "Array", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getArray_ColsCaption(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "colsCaption" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getArray_Cols(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "cols" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getArray_FixedCols(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "fixedCols" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getArray_FixedRows(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "fixedRows" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getArray_Rows(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "rows" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (categoryEClass,
           source,
           new String[] {
               "name", "Category", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getCategory_Icon(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "icon" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getCategory_Id(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getCategory_ParentCategoryId(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "parentCategoryId" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (checkboxEClass,
           source,
           new String[] {
               "name", "Checkbox", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (componentEClass,
           source,
           new String[] {
               "name", "Component", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getComponent_Id(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (connectorDefinitionEClass,
           source,
           new String[] {
               "name", "ConnectorDefinition", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getConnectorDefinition_Id(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getConnectorDefinition_Version(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "version" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getConnectorDefinition_Icon(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "icon" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getConnectorDefinition_Category(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "category" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getConnectorDefinition_Input(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "input" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getConnectorDefinition_Output(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "output" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getConnectorDefinition_Page(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "page" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getConnectorDefinition_JarDependency(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "jarDependency" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (documentRootEClass,
           source,
           new String[] {
               "name", "", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "mixed" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getDocumentRoot_Mixed(),
           source,
           new String[] {
               "kind", "elementWildcard", //$NON-NLS-1$ //$NON-NLS-2$
               "name", ":mixed" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getDocumentRoot_XMLNSPrefixMap(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "xmlns:prefix" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getDocumentRoot_XSISchemaLocation(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "xsi:schemaLocation" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getDocumentRoot_ConnectorDefinition(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "ConnectorDefinition", //$NON-NLS-1$ //$NON-NLS-2$
               "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (groupEClass,
           source,
           new String[] {
               "name", "Group", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getGroup_Widget(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "widget" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getGroup_Optional(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "optional" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (inputEClass,
           source,
           new String[] {
               "name", "Input", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getInput_DefaultValue(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "defaultValue" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getInput_Mandatory(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "mandatory" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getInput_Name(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "name" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getInput_Type(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "type" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (listEClass,
           source,
           new String[] {
               "name", "List", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getList_ShowDocuments(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "showDocuments" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (orientationEEnum,
           source,
           new String[] {
               "name", "Orientation" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (orientationObjectEDataType,
           source,
           new String[] {
               "name", "Orientation:Object", //$NON-NLS-1$ //$NON-NLS-2$
               "baseType", "Orientation" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (outputEClass,
           source,
           new String[] {
               "name", "Output", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getOutput_Name(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "name" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getOutput_Type(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "type" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (pageEClass,
           source,
           new String[] {
               "name", "Page", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getPage_Widget(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "widget" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getPage_Id(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "id" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (passwordEClass,
           source,
           new String[] {
               "name", "Password", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (radioGroupEClass,
           source,
           new String[] {
               "name", "RadioGroup", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getRadioGroup_Choices(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "choices" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getRadioGroup_Orientation(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "orientation" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (selectEClass,
           source,
           new String[] {
               "name", "Select", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getSelect_Items(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "items" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getSelect_ReadOnly(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "readOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (textEClass,
           source,
           new String[] {
               "name", "Text", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getText_ShowDocuments(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "showDocuments" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (textAreaEClass,
           source,
           new String[] {
               "name", "TextArea", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (widgetEClass,
           source,
           new String[] {
               "name", "Widget", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (widgetComponentEClass,
           source,
           new String[] {
               "name", "WidgetComponent", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "empty" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getWidgetComponent_InputName(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "inputName" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (scriptEditorEClass,
           source,
           new String[] {
               "name", "ScriptEditor", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getScriptEditor_Interpreter(),
           source,
           new String[] {
               "kind", "attribute", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "interpreter" //$NON-NLS-1$ //$NON-NLS-2$
           });
    }

} //ConnectorDefinitionPackageImpl
