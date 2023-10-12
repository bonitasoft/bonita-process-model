/**
 * Copyright (C) 2023 Bonitasoft S.A.
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
package org.bonitasoft.bonita2bpmn.transfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.bonita2bpmn.transfo.data.DataScope;
import org.bonitasoft.bpm.model.process.LinkEvent;
import org.omg.spec.bpmn.model.TDefinitions;
import org.omg.spec.bpmn.model.TLinkEventDefinition;
import org.omg.spec.bpmn.model.TSignal;

public class ModelRegistry {

    private final Set<String> messages = new HashSet<>();
    private final DataScope dataScope;
    private final Set<TSignal> signals = new HashSet<>();
    private final Map<String, TSignal> signalCode = new HashMap<>();
    private final TDefinitions definitions;
    private final List<String> errors = new ArrayList<>();
    private final Map<LinkEvent, TLinkEventDefinition> linkEvents = new HashMap<>();

    public ModelRegistry(TDefinitions definitions, DataScope dataScope) {
        this.definitions = definitions;
        this.dataScope = dataScope;
    }

    public DataScope getDataScope() {
        return dataScope;
    }

    public TDefinitions getDefinitions() {
        return definitions;
    }

    public List<String> getErrors() {
        return errors;
    }

    public Map<LinkEvent, TLinkEventDefinition> getLinkEvents() {
        return linkEvents;
    }

    public Set<TSignal> getSignals() {
        return signals;
    }

    public Set<String> getMessages() {
        return messages;
    }

    public void addError(String message) {
        errors.add(message);
    }

    public void addMessage(String eventId) {
        messages.add(eventId);
    }

    public void putLink(LinkEvent linkEvent, TLinkEventDefinition eventDef) {
        linkEvents.put(linkEvent, eventDef);
    }

    public Map<String, TSignal> getSignalCode() {
        return signalCode;
    }

    public void addSignal(TSignal tSignal) {
        signals.add(tSignal);
    }

    public void putSignalCode(String code, TSignal tSignal) {
        signalCode.put(code, tSignal);
    }
}
