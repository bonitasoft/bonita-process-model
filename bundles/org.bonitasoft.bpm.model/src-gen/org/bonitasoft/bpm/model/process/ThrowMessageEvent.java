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
package org.bonitasoft.bpm.model.process;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Throw Message Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.bpm.model.process.ThrowMessageEvent#getEvents <em>Events</em>}</li>
 *   <li>{@link org.bonitasoft.bpm.model.process.ThrowMessageEvent#getOutgoingMessages <em>Outgoing Messages</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.bpm.model.process.ProcessPackage#getThrowMessageEvent()
 * @model
 * @generated
 */
public interface ThrowMessageEvent extends MessageEvent {
    /**
     * Returns the value of the '<em><b>Events</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.bpm.model.process.Message}.
     * It is bidirectional and its opposite is '{@link org.bonitasoft.bpm.model.process.Message#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Events</em>' containment reference list.
     * @see org.bonitasoft.bpm.model.process.ProcessPackage#getThrowMessageEvent_Events()
     * @see org.bonitasoft.bpm.model.process.Message#getSource
     * @model opposite="source" containment="true"
     * @generated
     */
    EList<Message> getEvents();

    /**
     * Returns the value of the '<em><b>Outgoing Messages</b></em>' reference list.
     * The list contents are of type {@link org.bonitasoft.bpm.model.process.MessageFlow}.
     * It is bidirectional and its opposite is '{@link org.bonitasoft.bpm.model.process.MessageFlow#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Outgoing Messages</em>' reference list.
     * @see org.bonitasoft.bpm.model.process.ProcessPackage#getThrowMessageEvent_OutgoingMessages()
     * @see org.bonitasoft.bpm.model.process.MessageFlow#getSource
     * @model opposite="source"
     * @generated
     */
    EList<MessageFlow> getOutgoingMessages();

} // ThrowMessageEvent
