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
package org.bonitasoft.bpm.model.process.impl;

import java.util.Collection;

import org.bonitasoft.bpm.model.process.MainProcess;
import org.bonitasoft.bpm.model.process.MessageFlow;
import org.bonitasoft.bpm.model.process.ProcessPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Main Process</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.model.process.impl.MainProcessImpl#getBonitaModelVersion <em>Bonita Model Version</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.model.process.impl.MainProcessImpl#getIncludedEntries <em>Included Entries</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.model.process.impl.MainProcessImpl#getMessageConnections <em>Message Connections</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.model.process.impl.MainProcessImpl#getGeneratedLibs <em>Generated Libs</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.model.process.impl.MainProcessImpl#isEnableValidation <em>Enable Validation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MainProcessImpl extends AbstractProcessImpl implements MainProcess {
    /**
     * The default value of the '{@link #getBonitaModelVersion() <em>Bonita Model Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBonitaModelVersion()
     * @generated
     * @ordered
     */
    protected static final String BONITA_MODEL_VERSION_EDEFAULT = "5.0"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getBonitaModelVersion() <em>Bonita Model Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBonitaModelVersion()
     * @generated
     * @ordered
     */
    protected String bonitaModelVersion = BONITA_MODEL_VERSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getIncludedEntries() <em>Included Entries</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIncludedEntries()
     * @generated
     * @ordered
     */
    protected EList<String> includedEntries;

    /**
     * The cached value of the '{@link #getMessageConnections() <em>Message Connections</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMessageConnections()
     * @generated
     * @ordered
     */
    protected EList<MessageFlow> messageConnections;

    /**
     * The cached value of the '{@link #getGeneratedLibs() <em>Generated Libs</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGeneratedLibs()
     * @generated
     * @ordered
     */
    protected EList<String> generatedLibs;

    /**
     * The default value of the '{@link #isEnableValidation() <em>Enable Validation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isEnableValidation()
     * @generated
     * @ordered
     */
    protected static final boolean ENABLE_VALIDATION_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isEnableValidation() <em>Enable Validation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isEnableValidation()
     * @generated
     * @ordered
     */
    protected boolean enableValidation = ENABLE_VALIDATION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MainProcessImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ProcessPackage.Literals.MAIN_PROCESS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getBonitaModelVersion() {
        return bonitaModelVersion;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setBonitaModelVersion(String newBonitaModelVersion) {
        String oldBonitaModelVersion = bonitaModelVersion;
        bonitaModelVersion = newBonitaModelVersion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.MAIN_PROCESS__BONITA_MODEL_VERSION, oldBonitaModelVersion, bonitaModelVersion));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<String> getIncludedEntries() {
        if (includedEntries == null) {
            includedEntries = new EDataTypeUniqueEList<String>(String.class, this, ProcessPackage.MAIN_PROCESS__INCLUDED_ENTRIES);
        }
        return includedEntries;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<MessageFlow> getMessageConnections() {
        if (messageConnections == null) {
            messageConnections = new EObjectContainmentEList<MessageFlow>(MessageFlow.class, this, ProcessPackage.MAIN_PROCESS__MESSAGE_CONNECTIONS);
        }
        return messageConnections;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<String> getGeneratedLibs() {
        if (generatedLibs == null) {
            generatedLibs = new EDataTypeUniqueEList<String>(String.class, this, ProcessPackage.MAIN_PROCESS__GENERATED_LIBS);
        }
        return generatedLibs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isEnableValidation() {
        return enableValidation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setEnableValidation(boolean newEnableValidation) {
        boolean oldEnableValidation = enableValidation;
        enableValidation = newEnableValidation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.MAIN_PROCESS__ENABLE_VALIDATION, oldEnableValidation, enableValidation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ProcessPackage.MAIN_PROCESS__MESSAGE_CONNECTIONS:
                return ((InternalEList<?>)getMessageConnections()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ProcessPackage.MAIN_PROCESS__BONITA_MODEL_VERSION:
                return getBonitaModelVersion();
            case ProcessPackage.MAIN_PROCESS__INCLUDED_ENTRIES:
                return getIncludedEntries();
            case ProcessPackage.MAIN_PROCESS__MESSAGE_CONNECTIONS:
                return getMessageConnections();
            case ProcessPackage.MAIN_PROCESS__GENERATED_LIBS:
                return getGeneratedLibs();
            case ProcessPackage.MAIN_PROCESS__ENABLE_VALIDATION:
                return isEnableValidation();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ProcessPackage.MAIN_PROCESS__BONITA_MODEL_VERSION:
                setBonitaModelVersion((String)newValue);
                return;
            case ProcessPackage.MAIN_PROCESS__INCLUDED_ENTRIES:
                getIncludedEntries().clear();
                getIncludedEntries().addAll((Collection<? extends String>)newValue);
                return;
            case ProcessPackage.MAIN_PROCESS__MESSAGE_CONNECTIONS:
                getMessageConnections().clear();
                getMessageConnections().addAll((Collection<? extends MessageFlow>)newValue);
                return;
            case ProcessPackage.MAIN_PROCESS__GENERATED_LIBS:
                getGeneratedLibs().clear();
                getGeneratedLibs().addAll((Collection<? extends String>)newValue);
                return;
            case ProcessPackage.MAIN_PROCESS__ENABLE_VALIDATION:
                setEnableValidation((Boolean)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case ProcessPackage.MAIN_PROCESS__BONITA_MODEL_VERSION:
                setBonitaModelVersion(BONITA_MODEL_VERSION_EDEFAULT);
                return;
            case ProcessPackage.MAIN_PROCESS__INCLUDED_ENTRIES:
                getIncludedEntries().clear();
                return;
            case ProcessPackage.MAIN_PROCESS__MESSAGE_CONNECTIONS:
                getMessageConnections().clear();
                return;
            case ProcessPackage.MAIN_PROCESS__GENERATED_LIBS:
                getGeneratedLibs().clear();
                return;
            case ProcessPackage.MAIN_PROCESS__ENABLE_VALIDATION:
                setEnableValidation(ENABLE_VALIDATION_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case ProcessPackage.MAIN_PROCESS__BONITA_MODEL_VERSION:
                return BONITA_MODEL_VERSION_EDEFAULT == null ? bonitaModelVersion != null : !BONITA_MODEL_VERSION_EDEFAULT.equals(bonitaModelVersion);
            case ProcessPackage.MAIN_PROCESS__INCLUDED_ENTRIES:
                return includedEntries != null && !includedEntries.isEmpty();
            case ProcessPackage.MAIN_PROCESS__MESSAGE_CONNECTIONS:
                return messageConnections != null && !messageConnections.isEmpty();
            case ProcessPackage.MAIN_PROCESS__GENERATED_LIBS:
                return generatedLibs != null && !generatedLibs.isEmpty();
            case ProcessPackage.MAIN_PROCESS__ENABLE_VALIDATION:
                return enableValidation != ENABLE_VALIDATION_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (bonitaModelVersion: "); //$NON-NLS-1$
        result.append(bonitaModelVersion);
        result.append(", includedEntries: "); //$NON-NLS-1$
        result.append(includedEntries);
        result.append(", generatedLibs: "); //$NON-NLS-1$
        result.append(generatedLibs);
        result.append(", enableValidation: "); //$NON-NLS-1$
        result.append(enableValidation);
        result.append(')');
        return result.toString();
    }

} //MainProcessImpl
