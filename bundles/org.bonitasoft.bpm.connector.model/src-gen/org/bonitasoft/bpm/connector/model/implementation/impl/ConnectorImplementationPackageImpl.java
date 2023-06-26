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
package org.bonitasoft.bpm.connector.model.implementation.impl;

import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage;

import org.bonitasoft.bpm.connector.model.definition.impl.ConnectorDefinitionPackageImpl;

import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementationFactory;
import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementationPackage;
import org.bonitasoft.bpm.connector.model.implementation.DocumentRoot;
import org.bonitasoft.bpm.connector.model.implementation.JarDependencies;
import org.bonitasoft.bpm.connector.model.implementation.UnloadableConnectorImplementation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
public class ConnectorImplementationPackageImpl extends EPackageImpl implements ConnectorImplementationPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass connectorImplementationEClass = null;

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
    private EClass jarDependenciesEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass unloadableConnectorImplementationEClass = null;

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
     * @see org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementationPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ConnectorImplementationPackageImpl() {
        super(eNS_URI, ConnectorImplementationFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link ConnectorImplementationPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ConnectorImplementationPackage init() {
        if (isInited) return (ConnectorImplementationPackage)EPackage.Registry.INSTANCE.getEPackage(ConnectorImplementationPackage.eNS_URI);

        // Obtain or create and register package
        Object registeredConnectorImplementationPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        ConnectorImplementationPackageImpl theConnectorImplementationPackage = registeredConnectorImplementationPackage instanceof ConnectorImplementationPackageImpl ? (ConnectorImplementationPackageImpl)registeredConnectorImplementationPackage : new ConnectorImplementationPackageImpl();

        isInited = true;

        // Initialize simple dependencies
        XMLTypePackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ConnectorDefinitionPackage.eNS_URI);
        ConnectorDefinitionPackageImpl theConnectorDefinitionPackage = (ConnectorDefinitionPackageImpl)(registeredPackage instanceof ConnectorDefinitionPackageImpl ? registeredPackage : ConnectorDefinitionPackage.eINSTANCE);

        // Create package meta-data objects
        theConnectorImplementationPackage.createPackageContents();
        theConnectorDefinitionPackage.createPackageContents();

        // Initialize created meta-data
        theConnectorImplementationPackage.initializePackageContents();
        theConnectorDefinitionPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theConnectorImplementationPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ConnectorImplementationPackage.eNS_URI, theConnectorImplementationPackage);
        return theConnectorImplementationPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getConnectorImplementation() {
        return connectorImplementationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getConnectorImplementation_ImplementationId() {
        return (EAttribute)connectorImplementationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getConnectorImplementation_ImplementationVersion() {
        return (EAttribute)connectorImplementationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getConnectorImplementation_DefinitionId() {
        return (EAttribute)connectorImplementationEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getConnectorImplementation_DefinitionVersion() {
        return (EAttribute)connectorImplementationEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getConnectorImplementation_ImplementationClassname() {
        return (EAttribute)connectorImplementationEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getConnectorImplementation_HasSources() {
        return (EAttribute)connectorImplementationEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getConnectorImplementation_Description() {
        return (EAttribute)connectorImplementationEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getConnectorImplementation_JarDependencies() {
        return (EReference)connectorImplementationEClass.getEStructuralFeatures().get(7);
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
    public EReference getDocumentRoot_ConnectorImplementation() {
        return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getJarDependencies() {
        return jarDependenciesEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getJarDependencies_JarDependency() {
        return (EAttribute)jarDependenciesEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getUnloadableConnectorImplementation() {
        return unloadableConnectorImplementationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ConnectorImplementationFactory getConnectorImplementationFactory() {
        return (ConnectorImplementationFactory)getEFactoryInstance();
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
        connectorImplementationEClass = createEClass(CONNECTOR_IMPLEMENTATION);
        createEAttribute(connectorImplementationEClass, CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_ID);
        createEAttribute(connectorImplementationEClass, CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_VERSION);
        createEAttribute(connectorImplementationEClass, CONNECTOR_IMPLEMENTATION__DEFINITION_ID);
        createEAttribute(connectorImplementationEClass, CONNECTOR_IMPLEMENTATION__DEFINITION_VERSION);
        createEAttribute(connectorImplementationEClass, CONNECTOR_IMPLEMENTATION__IMPLEMENTATION_CLASSNAME);
        createEAttribute(connectorImplementationEClass, CONNECTOR_IMPLEMENTATION__HAS_SOURCES);
        createEAttribute(connectorImplementationEClass, CONNECTOR_IMPLEMENTATION__DESCRIPTION);
        createEReference(connectorImplementationEClass, CONNECTOR_IMPLEMENTATION__JAR_DEPENDENCIES);

        documentRootEClass = createEClass(DOCUMENT_ROOT);
        createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
        createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
        createEReference(documentRootEClass, DOCUMENT_ROOT__CONNECTOR_IMPLEMENTATION);

        jarDependenciesEClass = createEClass(JAR_DEPENDENCIES);
        createEAttribute(jarDependenciesEClass, JAR_DEPENDENCIES__JAR_DEPENDENCY);

        unloadableConnectorImplementationEClass = createEClass(UNLOADABLE_CONNECTOR_IMPLEMENTATION);
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
        unloadableConnectorImplementationEClass.getESuperTypes().add(this.getConnectorImplementation());

        // Initialize classes and features; add operations and parameters
        initEClass(connectorImplementationEClass, ConnectorImplementation.class, "ConnectorImplementation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getConnectorImplementation_ImplementationId(), theXMLTypePackage.getString(), "implementationId", null, 1, 1, ConnectorImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getConnectorImplementation_ImplementationVersion(), theXMLTypePackage.getString(), "implementationVersion", null, 1, 1, ConnectorImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getConnectorImplementation_DefinitionId(), theXMLTypePackage.getString(), "definitionId", null, 1, 1, ConnectorImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getConnectorImplementation_DefinitionVersion(), theXMLTypePackage.getString(), "definitionVersion", null, 1, 1, ConnectorImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getConnectorImplementation_ImplementationClassname(), theXMLTypePackage.getString(), "implementationClassname", null, 1, 1, ConnectorImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEAttribute(getConnectorImplementation_HasSources(), theXMLTypePackage.getBoolean(), "hasSources", "false", 0, 1, ConnectorImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
        initEAttribute(getConnectorImplementation_Description(), theXMLTypePackage.getString(), "description", null, 0, 1, ConnectorImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getConnectorImplementation_JarDependencies(), this.getJarDependencies(), null, "jarDependencies", null, 1, 1, ConnectorImplementation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
        initEReference(getDocumentRoot_ConnectorImplementation(), this.getConnectorImplementation(), null, "connectorImplementation", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(jarDependenciesEClass, JarDependencies.class, "JarDependencies", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getJarDependencies_JarDependency(), theXMLTypePackage.getString(), "jarDependency", null, 0, -1, JarDependencies.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

        initEClass(unloadableConnectorImplementationEClass, UnloadableConnectorImplementation.class, "UnloadableConnectorImplementation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

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
          (connectorImplementationEClass,
           source,
           new String[] {
               "name", "ConnectorImplementation", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getConnectorImplementation_ImplementationId(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "implementationId" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getConnectorImplementation_ImplementationVersion(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "implementationVersion" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getConnectorImplementation_DefinitionId(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "definitionId" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getConnectorImplementation_DefinitionVersion(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "definitionVersion" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getConnectorImplementation_ImplementationClassname(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "implementationClassname" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getConnectorImplementation_HasSources(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "hasSources" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getConnectorImplementation_Description(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "description" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getConnectorImplementation_JarDependencies(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "jarDependencies" //$NON-NLS-1$ //$NON-NLS-2$
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
          (getDocumentRoot_ConnectorImplementation(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "connectorImplementation", //$NON-NLS-1$ //$NON-NLS-2$
               "namespace", "##targetNamespace" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (jarDependenciesEClass,
           source,
           new String[] {
               "name", "jarDependencies", //$NON-NLS-1$ //$NON-NLS-2$
               "kind", "elementOnly" //$NON-NLS-1$ //$NON-NLS-2$
           });
        addAnnotation
          (getJarDependencies_JarDependency(),
           source,
           new String[] {
               "kind", "element", //$NON-NLS-1$ //$NON-NLS-2$
               "name", "jarDependency" //$NON-NLS-1$ //$NON-NLS-2$
           });
    }

} //ConnectorImplementationPackageImpl
