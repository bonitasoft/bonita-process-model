/**
 * Copyright (C) 2009-2022 BonitaSoft S.A.
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
package org.bonitasoft.bpm.model.decision.impl;

import org.bonitasoft.bpm.model.actormapping.ActorMappingPackage;

import org.bonitasoft.bpm.model.actormapping.impl.ActorMappingPackageImpl;

import org.bonitasoft.bpm.model.configuration.ConfigurationPackage;

import org.bonitasoft.bpm.model.configuration.impl.ConfigurationPackageImpl;

import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorConfigurationPackage;

import org.bonitasoft.bpm.model.connectorconfiguration.impl.ConnectorConfigurationPackageImpl;

import org.bonitasoft.bpm.model.decision.DecisionFactory;
import org.bonitasoft.bpm.model.decision.DecisionPackage;
import org.bonitasoft.bpm.model.decision.DecisionTable;
import org.bonitasoft.bpm.model.decision.DecisionTableAction;
import org.bonitasoft.bpm.model.decision.DecisionTableLine;

import org.bonitasoft.bpm.model.expression.ExpressionPackage;

import org.bonitasoft.bpm.model.expression.impl.ExpressionPackageImpl;

import org.bonitasoft.bpm.model.form.FormPackage;

import org.bonitasoft.bpm.model.form.impl.FormPackageImpl;

import org.bonitasoft.bpm.model.kpi.KpiPackage;

import org.bonitasoft.bpm.model.kpi.impl.KpiPackageImpl;

import org.bonitasoft.bpm.model.parameter.ParameterPackage;

import org.bonitasoft.bpm.model.parameter.impl.ParameterPackageImpl;

import org.bonitasoft.bpm.model.process.ProcessPackage;

import org.bonitasoft.bpm.model.process.impl.ProcessPackageImpl;

import org.bonitasoft.bpm.model.simulation.SimulationPackage;

import org.bonitasoft.bpm.model.simulation.impl.SimulationPackageImpl;

import org.bonitasoft.bpm.model.transitions.TransitionsPackage;

import org.bonitasoft.bpm.model.transitions.impl.TransitionsPackageImpl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DecisionPackageImpl extends EPackageImpl implements DecisionPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass decisionTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass decisionTableLineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass decisionTableActionEClass = null;

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
	 * @see org.bonitasoft.bpm.model.decision.DecisionPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DecisionPackageImpl() {
		super(eNS_URI, DecisionFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link DecisionPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DecisionPackage init() {
		if (isInited) return (DecisionPackage)EPackage.Registry.INSTANCE.getEPackage(DecisionPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredDecisionPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		DecisionPackageImpl theDecisionPackage = registeredDecisionPackage instanceof DecisionPackageImpl ? (DecisionPackageImpl)registeredDecisionPackage : new DecisionPackageImpl();

		isInited = true;

		// Obtain or create and register interdependencies
		Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ActorMappingPackage.eNS_URI);
		ActorMappingPackageImpl theActorMappingPackage = (ActorMappingPackageImpl)(registeredPackage instanceof ActorMappingPackageImpl ? registeredPackage : ActorMappingPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ConfigurationPackage.eNS_URI);
		ConfigurationPackageImpl theConfigurationPackage = (ConfigurationPackageImpl)(registeredPackage instanceof ConfigurationPackageImpl ? registeredPackage : ConfigurationPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ConnectorConfigurationPackage.eNS_URI);
		ConnectorConfigurationPackageImpl theConnectorConfigurationPackage = (ConnectorConfigurationPackageImpl)(registeredPackage instanceof ConnectorConfigurationPackageImpl ? registeredPackage : ConnectorConfigurationPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ExpressionPackage.eNS_URI);
		ExpressionPackageImpl theExpressionPackage = (ExpressionPackageImpl)(registeredPackage instanceof ExpressionPackageImpl ? registeredPackage : ExpressionPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(KpiPackage.eNS_URI);
		KpiPackageImpl theKpiPackage = (KpiPackageImpl)(registeredPackage instanceof KpiPackageImpl ? registeredPackage : KpiPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ParameterPackage.eNS_URI);
		ParameterPackageImpl theParameterPackage = (ParameterPackageImpl)(registeredPackage instanceof ParameterPackageImpl ? registeredPackage : ParameterPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ProcessPackage.eNS_URI);
		ProcessPackageImpl theProcessPackage = (ProcessPackageImpl)(registeredPackage instanceof ProcessPackageImpl ? registeredPackage : ProcessPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FormPackage.eNS_URI);
		FormPackageImpl theFormPackage = (FormPackageImpl)(registeredPackage instanceof FormPackageImpl ? registeredPackage : FormPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(SimulationPackage.eNS_URI);
		SimulationPackageImpl theSimulationPackage = (SimulationPackageImpl)(registeredPackage instanceof SimulationPackageImpl ? registeredPackage : SimulationPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(TransitionsPackage.eNS_URI);
		TransitionsPackageImpl theTransitionsPackage = (TransitionsPackageImpl)(registeredPackage instanceof TransitionsPackageImpl ? registeredPackage : TransitionsPackage.eINSTANCE);

		// Create package meta-data objects
		theDecisionPackage.createPackageContents();
		theActorMappingPackage.createPackageContents();
		theConfigurationPackage.createPackageContents();
		theConnectorConfigurationPackage.createPackageContents();
		theExpressionPackage.createPackageContents();
		theKpiPackage.createPackageContents();
		theParameterPackage.createPackageContents();
		theProcessPackage.createPackageContents();
		theFormPackage.createPackageContents();
		theSimulationPackage.createPackageContents();
		theTransitionsPackage.createPackageContents();

		// Initialize created meta-data
		theDecisionPackage.initializePackageContents();
		theActorMappingPackage.initializePackageContents();
		theConfigurationPackage.initializePackageContents();
		theConnectorConfigurationPackage.initializePackageContents();
		theExpressionPackage.initializePackageContents();
		theKpiPackage.initializePackageContents();
		theParameterPackage.initializePackageContents();
		theProcessPackage.initializePackageContents();
		theFormPackage.initializePackageContents();
		theSimulationPackage.initializePackageContents();
		theTransitionsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDecisionPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DecisionPackage.eNS_URI, theDecisionPackage);
		return theDecisionPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDecisionTable() {
		return decisionTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDecisionTable_Lines() {
		return (EReference)decisionTableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDecisionTable_DefaultAction() {
		return (EReference)decisionTableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDecisionTableLine() {
		return decisionTableLineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDecisionTableLine_Conditions() {
		return (EReference)decisionTableLineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDecisionTableLine_Action() {
		return (EReference)decisionTableLineEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDecisionTableAction() {
		return decisionTableActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DecisionFactory getDecisionFactory() {
		return (DecisionFactory)getEFactoryInstance();
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
		decisionTableEClass = createEClass(DECISION_TABLE);
		createEReference(decisionTableEClass, DECISION_TABLE__LINES);
		createEReference(decisionTableEClass, DECISION_TABLE__DEFAULT_ACTION);

		decisionTableLineEClass = createEClass(DECISION_TABLE_LINE);
		createEReference(decisionTableLineEClass, DECISION_TABLE_LINE__CONDITIONS);
		createEReference(decisionTableLineEClass, DECISION_TABLE_LINE__ACTION);

		decisionTableActionEClass = createEClass(DECISION_TABLE_ACTION);
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
		ExpressionPackage theExpressionPackage = (ExpressionPackage)EPackage.Registry.INSTANCE.getEPackage(ExpressionPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(decisionTableEClass, DecisionTable.class, "DecisionTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getDecisionTable_Lines(), this.getDecisionTableLine(), null, "lines", null, 0, -1, DecisionTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDecisionTable_DefaultAction(), this.getDecisionTableAction(), null, "defaultAction", null, 0, 1, DecisionTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(decisionTableLineEClass, DecisionTableLine.class, "DecisionTableLine", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getDecisionTableLine_Conditions(), theExpressionPackage.getExpression(), null, "conditions", null, 0, -1, DecisionTableLine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDecisionTableLine_Action(), this.getDecisionTableAction(), null, "action", null, 0, 1, DecisionTableLine.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(decisionTableActionEClass, DecisionTableAction.class, "DecisionTableAction", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/edapt
		createEdaptAnnotations();
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
			   "historyURI", "process.history" //$NON-NLS-1$ //$NON-NLS-2$
		   });
	}

} //DecisionPackageImpl
