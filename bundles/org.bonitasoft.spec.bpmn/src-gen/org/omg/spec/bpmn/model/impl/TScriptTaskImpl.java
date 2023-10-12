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
package org.omg.spec.bpmn.model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TScript;
import org.omg.spec.bpmn.model.TScriptTask;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TScript Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TScriptTaskImpl#getScript <em>Script</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TScriptTaskImpl#getScriptFormat <em>Script Format</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TScriptTaskImpl extends TTaskImpl implements TScriptTask {

    /**
     * The cached value of the '{@link #getScript() <em>Script</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getScript()
     * @generated
     * @ordered
     */
    protected TScript script;

    /**
     * The default value of the '{@link #getScriptFormat() <em>Script Format</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getScriptFormat()
     * @generated
     * @ordered
     */
    protected static final String SCRIPT_FORMAT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getScriptFormat() <em>Script Format</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getScriptFormat()
     * @generated
     * @ordered
     */
    protected String scriptFormat = SCRIPT_FORMAT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TScriptTaskImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TSCRIPT_TASK;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TScript getScript() {
        return script;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetScript(TScript newScript, NotificationChain msgs) {
        TScript oldScript = script;
        script = newScript;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TSCRIPT_TASK__SCRIPT, oldScript, newScript);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setScript(TScript newScript) {
        if (newScript != script) {
            NotificationChain msgs = null;
            if (script != null)
                msgs = ((InternalEObject) script).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ModelPackage.TSCRIPT_TASK__SCRIPT, null, msgs);
            if (newScript != null)
                msgs = ((InternalEObject) newScript).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ModelPackage.TSCRIPT_TASK__SCRIPT, null, msgs);
            msgs = basicSetScript(newScript, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TSCRIPT_TASK__SCRIPT, newScript,
                    newScript));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getScriptFormat() {
        return scriptFormat;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setScriptFormat(String newScriptFormat) {
        String oldScriptFormat = scriptFormat;
        scriptFormat = newScriptFormat;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TSCRIPT_TASK__SCRIPT_FORMAT,
                    oldScriptFormat, scriptFormat));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ModelPackage.TSCRIPT_TASK__SCRIPT:
                return basicSetScript(null, msgs);
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
            case ModelPackage.TSCRIPT_TASK__SCRIPT:
                return getScript();
            case ModelPackage.TSCRIPT_TASK__SCRIPT_FORMAT:
                return getScriptFormat();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ModelPackage.TSCRIPT_TASK__SCRIPT:
                setScript((TScript) newValue);
                return;
            case ModelPackage.TSCRIPT_TASK__SCRIPT_FORMAT:
                setScriptFormat((String) newValue);
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
            case ModelPackage.TSCRIPT_TASK__SCRIPT:
                setScript((TScript) null);
                return;
            case ModelPackage.TSCRIPT_TASK__SCRIPT_FORMAT:
                setScriptFormat(SCRIPT_FORMAT_EDEFAULT);
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
            case ModelPackage.TSCRIPT_TASK__SCRIPT:
                return script != null;
            case ModelPackage.TSCRIPT_TASK__SCRIPT_FORMAT:
                return SCRIPT_FORMAT_EDEFAULT == null ? scriptFormat != null
                        : !SCRIPT_FORMAT_EDEFAULT.equals(scriptFormat);
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
        if (eIsProxy())
            return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (scriptFormat: "); //$NON-NLS-1$
        result.append(scriptFormat);
        result.append(')');
        return result.toString();
    }

} //TScriptTaskImpl
