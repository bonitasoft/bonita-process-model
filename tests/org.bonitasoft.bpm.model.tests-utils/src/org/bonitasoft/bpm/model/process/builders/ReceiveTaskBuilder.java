/**
 * Copyright (C) 2014 Bonitasoft S.A.
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

import org.bonitasoft.bpm.model.expression.TableExpression;
import org.bonitasoft.bpm.model.expression.builders.OperationBuilder;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.bpm.model.process.ReceiveTask;

public class ReceiveTaskBuilder extends ActivityBuilder<ReceiveTask, ReceiveTaskBuilder> {

    public static ReceiveTaskBuilder aReceiveTask() {
        return new ReceiveTaskBuilder();
    }

    public ReceiveTaskBuilder catchingMessage(String message) {
        getBuiltInstance().setEvent(message);
        return this;
    }

    public ReceiveTaskBuilder withCorrelation(TableExpression correlation) {
        getBuiltInstance().setCorrelation(correlation);
        return this;
    }

    public ReceiveTaskBuilder havingMessageContentMappings(OperationBuilder... operations) {
        if (operations != null) {
            for (var operationBuilder : operations) {
                getBuiltInstance().getMessageContent().add(operationBuilder.build());
            }
        }
        return this;
    }

    @Override
    protected ReceiveTask newInstance() {
        return ProcessFactory.eINSTANCE.createReceiveTask();
    }

}
