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
package org.bonitasoft.bpm.model.process.builders;

import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.expression.TableExpression;
import org.bonitasoft.bpm.model.process.Correlation;
import org.bonitasoft.bpm.model.process.Message;
import org.bonitasoft.bpm.model.process.ProcessFactory;

public class MessageBuilder extends ElementBuilder<Message, MessageBuilder> {

    public static MessageBuilder aMessage() {
        return new MessageBuilder();
    }

    public MessageBuilder withTargetProcess(Expression processName) {
        getBuiltInstance().setTargetProcessExpression(processName);
        return getThis();
    }

    public MessageBuilder withTargetFloweLement(Expression flowElement) {
        getBuiltInstance().setTargetElementExpression(flowElement);
        return getThis();
    }

    public MessageBuilder withContent(TableExpression content) {
        getBuiltInstance().setMessageContent(content);
        return getThis();
    }

    public MessageBuilder withCorrelation(Correlation correlation) {
        getBuiltInstance().setCorrelation(correlation);
        return getThis();
    }

    @Override
    protected Message newInstance() {
        return ProcessFactory.eINSTANCE.createMessage();
    }

}
