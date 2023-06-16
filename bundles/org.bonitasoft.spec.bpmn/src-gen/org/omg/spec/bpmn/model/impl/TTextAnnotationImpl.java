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
import org.omg.spec.bpmn.model.TText;
import org.omg.spec.bpmn.model.TTextAnnotation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TText Annotation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TTextAnnotationImpl#getText <em>Text</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TTextAnnotationImpl#getTextFormat <em>Text Format</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TTextAnnotationImpl extends TArtifactImpl implements TTextAnnotation {

    /**
     * The cached value of the '{@link #getText() <em>Text</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getText()
     * @generated
     * @ordered
     */
    protected TText text;

    /**
     * The default value of the '{@link #getTextFormat() <em>Text Format</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTextFormat()
     * @generated
     * @ordered
     */
    protected static final String TEXT_FORMAT_EDEFAULT = "text/plain"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getTextFormat() <em>Text Format</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTextFormat()
     * @generated
     * @ordered
     */
    protected String textFormat = TEXT_FORMAT_EDEFAULT;

    /**
     * This is true if the Text Format attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean textFormatESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TTextAnnotationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ModelPackage.Literals.TTEXT_ANNOTATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TText getText() {
        return text;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetText(TText newText, NotificationChain msgs) {
        TText oldText = text;
        text = newText;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ModelPackage.TTEXT_ANNOTATION__TEXT, oldText, newText);
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
    public void setText(TText newText) {
        if (newText != text) {
            NotificationChain msgs = null;
            if (text != null)
                msgs = ((InternalEObject) text).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ModelPackage.TTEXT_ANNOTATION__TEXT, null, msgs);
            if (newText != null)
                msgs = ((InternalEObject) newText).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ModelPackage.TTEXT_ANNOTATION__TEXT, null, msgs);
            msgs = basicSetText(newText, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TTEXT_ANNOTATION__TEXT, newText,
                    newText));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getTextFormat() {
        return textFormat;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTextFormat(String newTextFormat) {
        String oldTextFormat = textFormat;
        textFormat = newTextFormat;
        boolean oldTextFormatESet = textFormatESet;
        textFormatESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TTEXT_ANNOTATION__TEXT_FORMAT,
                    oldTextFormat, textFormat, !oldTextFormatESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetTextFormat() {
        String oldTextFormat = textFormat;
        boolean oldTextFormatESet = textFormatESet;
        textFormat = TEXT_FORMAT_EDEFAULT;
        textFormatESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TTEXT_ANNOTATION__TEXT_FORMAT,
                    oldTextFormat, TEXT_FORMAT_EDEFAULT, oldTextFormatESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetTextFormat() {
        return textFormatESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ModelPackage.TTEXT_ANNOTATION__TEXT:
                return basicSetText(null, msgs);
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
            case ModelPackage.TTEXT_ANNOTATION__TEXT:
                return getText();
            case ModelPackage.TTEXT_ANNOTATION__TEXT_FORMAT:
                return getTextFormat();
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
            case ModelPackage.TTEXT_ANNOTATION__TEXT:
                setText((TText) newValue);
                return;
            case ModelPackage.TTEXT_ANNOTATION__TEXT_FORMAT:
                setTextFormat((String) newValue);
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
            case ModelPackage.TTEXT_ANNOTATION__TEXT:
                setText((TText) null);
                return;
            case ModelPackage.TTEXT_ANNOTATION__TEXT_FORMAT:
                unsetTextFormat();
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
            case ModelPackage.TTEXT_ANNOTATION__TEXT:
                return text != null;
            case ModelPackage.TTEXT_ANNOTATION__TEXT_FORMAT:
                return isSetTextFormat();
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
        result.append(" (textFormat: "); //$NON-NLS-1$
        if (textFormatESet)
            result.append(textFormat);
        else
            result.append("<unset>"); //$NON-NLS-1$
        result.append(')');
        return result.toString();
    }

} //TTextAnnotationImpl
