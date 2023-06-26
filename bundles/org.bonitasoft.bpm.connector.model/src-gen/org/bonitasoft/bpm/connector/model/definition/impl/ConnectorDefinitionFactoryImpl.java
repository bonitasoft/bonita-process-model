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

import org.bonitasoft.bpm.connector.model.definition.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConnectorDefinitionFactoryImpl extends EFactoryImpl implements ConnectorDefinitionFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ConnectorDefinitionFactory init() {
        try {
            ConnectorDefinitionFactory theConnectorDefinitionFactory = (ConnectorDefinitionFactory)EPackage.Registry.INSTANCE.getEFactory(ConnectorDefinitionPackage.eNS_URI);
            if (theConnectorDefinitionFactory != null) {
                return theConnectorDefinitionFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ConnectorDefinitionFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConnectorDefinitionFactoryImpl() {
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
            case ConnectorDefinitionPackage.ARRAY: return createArray();
            case ConnectorDefinitionPackage.CATEGORY: return createCategory();
            case ConnectorDefinitionPackage.CHECKBOX: return createCheckbox();
            case ConnectorDefinitionPackage.CONNECTOR_DEFINITION: return createConnectorDefinition();
            case ConnectorDefinitionPackage.DOCUMENT_ROOT: return createDocumentRoot();
            case ConnectorDefinitionPackage.GROUP: return createGroup();
            case ConnectorDefinitionPackage.INPUT: return createInput();
            case ConnectorDefinitionPackage.LIST: return createList();
            case ConnectorDefinitionPackage.OUTPUT: return createOutput();
            case ConnectorDefinitionPackage.PAGE: return createPage();
            case ConnectorDefinitionPackage.PASSWORD: return createPassword();
            case ConnectorDefinitionPackage.RADIO_GROUP: return createRadioGroup();
            case ConnectorDefinitionPackage.SELECT: return createSelect();
            case ConnectorDefinitionPackage.TEXT: return createText();
            case ConnectorDefinitionPackage.TEXT_AREA: return createTextArea();
            case ConnectorDefinitionPackage.WIDGET_COMPONENT: return createWidgetComponent();
            case ConnectorDefinitionPackage.UNLOADABLE_CONNECTOR_DEFINITION: return createUnloadableConnectorDefinition();
            case ConnectorDefinitionPackage.SCRIPT_EDITOR: return createScriptEditor();
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
            case ConnectorDefinitionPackage.ORIENTATION:
                return createOrientationFromString(eDataType, initialValue);
            case ConnectorDefinitionPackage.ORIENTATION_OBJECT:
                return createOrientationObjectFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
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
            case ConnectorDefinitionPackage.ORIENTATION:
                return convertOrientationToString(eDataType, instanceValue);
            case ConnectorDefinitionPackage.ORIENTATION_OBJECT:
                return convertOrientationObjectToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Array createArray() {
        ArrayImpl array = new ArrayImpl();
        return array;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Category createCategory() {
        CategoryImpl category = new CategoryImpl();
        return category;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Checkbox createCheckbox() {
        CheckboxImpl checkbox = new CheckboxImpl();
        return checkbox;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ConnectorDefinition createConnectorDefinition() {
        ConnectorDefinitionImpl connectorDefinition = new ConnectorDefinitionImpl();
        return connectorDefinition;
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
    @Override
    public Group createGroup() {
        GroupImpl group = new GroupImpl();
        return group;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Input createInput() {
        InputImpl input = new InputImpl();
        return input;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public List createList() {
        ListImpl list = new ListImpl();
        return list;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Output createOutput() {
        OutputImpl output = new OutputImpl();
        return output;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Page createPage() {
        PageImpl page = new PageImpl();
        return page;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Password createPassword() {
        PasswordImpl password = new PasswordImpl();
        return password;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public RadioGroup createRadioGroup() {
        RadioGroupImpl radioGroup = new RadioGroupImpl();
        return radioGroup;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Select createSelect() {
        SelectImpl select = new SelectImpl();
        return select;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Text createText() {
        TextImpl text = new TextImpl();
        return text;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TextArea createTextArea() {
        TextAreaImpl textArea = new TextAreaImpl();
        return textArea;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public WidgetComponent createWidgetComponent() {
        WidgetComponentImpl widgetComponent = new WidgetComponentImpl();
        return widgetComponent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public UnloadableConnectorDefinition createUnloadableConnectorDefinition() {
        UnloadableConnectorDefinitionImpl unloadableConnectorDefinition = new UnloadableConnectorDefinitionImpl();
        return unloadableConnectorDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ScriptEditor createScriptEditor() {
        ScriptEditorImpl scriptEditor = new ScriptEditorImpl();
        return scriptEditor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Orientation createOrientationFromString(EDataType eDataType, String initialValue) {
        Orientation result = Orientation.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertOrientationToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Orientation createOrientationObjectFromString(EDataType eDataType, String initialValue) {
        return createOrientationFromString(ConnectorDefinitionPackage.Literals.ORIENTATION, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertOrientationObjectToString(EDataType eDataType, Object instanceValue) {
        return convertOrientationToString(ConnectorDefinitionPackage.Literals.ORIENTATION, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ConnectorDefinitionPackage getConnectorDefinitionPackage() {
        return (ConnectorDefinitionPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ConnectorDefinitionPackage getPackage() {
        return ConnectorDefinitionPackage.eINSTANCE;
    }

} //ConnectorDefinitionFactoryImpl
