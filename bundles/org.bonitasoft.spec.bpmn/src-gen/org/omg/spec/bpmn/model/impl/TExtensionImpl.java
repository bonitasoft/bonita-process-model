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

import java.util.Collection;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TDocumentation;
import org.omg.spec.bpmn.model.TExtension;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TExtension</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TExtensionImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TExtensionImpl#getDefinition <em>Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TExtensionImpl#isMustUnderstand <em>Must Understand</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TExtensionImpl extends EObjectImpl implements TExtension {

    /**
     * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected EList<TDocumentation> documentation;

    /**
     * The default value of the '{@link #getDefinition() <em>Definition</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefinition()
     * @generated
     * @ordered
     */
    protected static final QName DEFINITION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDefinition() <em>Definition</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDefinition()
     * @generated
     * @ordered
     */
    protected QName definition = DEFINITION_EDEFAULT;

    /**
     * The default value of the '{@link #isMustUnderstand() <em>Must Understand</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isMustUnderstand()
     * @generated
     * @ordered
     */
    protected static final boolean MUST_UNDERSTAND_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isMustUnderstand() <em>Must Understand</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isMustUnderstand()
     * @generated
     * @ordered
     */
    protected boolean mustUnderstand = MUST_UNDERSTAND_EDEFAULT;

    /**
     * This is true if the Must Understand attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean mustUnderstandESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TExtensionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TEXTENSION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TDocumentation> getDocumentation() {
        if (documentation == null) {
            documentation = new EObjectContainmentEList<TDocumentation>(TDocumentation.class, this,
                    ModelPackage.TEXTENSION__DOCUMENTATION);
        }
        return documentation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public QName getDefinition() {
        return definition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setDefinition(QName newDefinition) {
        QName oldDefinition = definition;
        definition = newDefinition;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TEXTENSION__DEFINITION, oldDefinition,
                    definition));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isMustUnderstand() {
        return mustUnderstand;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setMustUnderstand(boolean newMustUnderstand) {
        boolean oldMustUnderstand = mustUnderstand;
        mustUnderstand = newMustUnderstand;
        boolean oldMustUnderstandESet = mustUnderstandESet;
        mustUnderstandESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TEXTENSION__MUST_UNDERSTAND,
                    oldMustUnderstand, mustUnderstand, !oldMustUnderstandESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetMustUnderstand() {
        boolean oldMustUnderstand = mustUnderstand;
        boolean oldMustUnderstandESet = mustUnderstandESet;
        mustUnderstand = MUST_UNDERSTAND_EDEFAULT;
        mustUnderstandESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TEXTENSION__MUST_UNDERSTAND,
                    oldMustUnderstand, MUST_UNDERSTAND_EDEFAULT, oldMustUnderstandESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetMustUnderstand() {
        return mustUnderstandESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ModelPackage.TEXTENSION__DOCUMENTATION:
                return ((InternalEList<?>) getDocumentation()).basicRemove(otherEnd, msgs);
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
            case ModelPackage.TEXTENSION__DOCUMENTATION:
                return getDocumentation();
            case ModelPackage.TEXTENSION__DEFINITION:
                return getDefinition();
            case ModelPackage.TEXTENSION__MUST_UNDERSTAND:
                return isMustUnderstand();
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
            case ModelPackage.TEXTENSION__DOCUMENTATION:
                getDocumentation().clear();
                getDocumentation().addAll((Collection<? extends TDocumentation>) newValue);
                return;
            case ModelPackage.TEXTENSION__DEFINITION:
                setDefinition((QName) newValue);
                return;
            case ModelPackage.TEXTENSION__MUST_UNDERSTAND:
                setMustUnderstand((Boolean) newValue);
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
            case ModelPackage.TEXTENSION__DOCUMENTATION:
                getDocumentation().clear();
                return;
            case ModelPackage.TEXTENSION__DEFINITION:
                setDefinition(DEFINITION_EDEFAULT);
                return;
            case ModelPackage.TEXTENSION__MUST_UNDERSTAND:
                unsetMustUnderstand();
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
            case ModelPackage.TEXTENSION__DOCUMENTATION:
                return documentation != null && !documentation.isEmpty();
            case ModelPackage.TEXTENSION__DEFINITION:
                return DEFINITION_EDEFAULT == null ? definition != null : !DEFINITION_EDEFAULT.equals(definition);
            case ModelPackage.TEXTENSION__MUST_UNDERSTAND:
                return isSetMustUnderstand();
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
        result.append(" (definition: "); //$NON-NLS-1$
        result.append(definition);
        result.append(", mustUnderstand: "); //$NON-NLS-1$
        if (mustUnderstandESet)
            result.append(mustUnderstand);
        else
            result.append("<unset>"); //$NON-NLS-1$
        result.append(')');
        return result.toString();
    }

} //TExtensionImpl
