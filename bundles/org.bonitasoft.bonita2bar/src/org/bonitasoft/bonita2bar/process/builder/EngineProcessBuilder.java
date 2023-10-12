/**
 * Copyright (C) 2012 Bonitasoft S.A.
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
package org.bonitasoft.bonita2bar.process.builder;

import org.bonitasoft.bpm.model.process.Document;
import org.bonitasoft.bpm.model.process.Element;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.util.IModelSearch;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.engine.expression.InvalidExpressionException;

public class EngineProcessBuilder extends AbstractProcessBuilder {

    protected final ProcessDefinitionBuilder builder;
    private ProcessContractEngineBuilder processContractBuilder;
    private DocumentEngineDefinitionBuilder documentBuilder;

    public EngineProcessBuilder(final ProcessDefinitionBuilder processBuilder,
            ProcessContractEngineBuilder processContractBuilder, DocumentEngineDefinitionBuilder documentBuilder,
            IModelSearch modelSearch) {
        super(modelSearch);
        builder = processBuilder;
        this.processContractBuilder = processContractBuilder;
        this.documentBuilder = documentBuilder;
    }

    @Override
    public Element casePool(final Pool pool) {
        if (pool.getDisplayName() != null) {
            builder.addDisplayName(pool.getDisplayName());
        }
        try {
            addDocuments(builder, pool);
            addActors(builder, pool);
            addData(builder, pool);
            addParameters(builder, pool);
            addSearchIndex(builder, pool);
            addConnector(builder, pool);
            addKPIBinding(builder, pool);
            addInstantiationContract(builder, pool);
            addContext(builder, pool);
        } catch (InvalidExpressionException e) {
            throw new ProcessBuilderException(e);
        }
        return pool;
    }

    private void addInstantiationContract(ProcessDefinitionBuilder builder, Pool pool) {
        if (pool != null && pool.getContract() != null) {
            processContractBuilder.setEngineBuilder(builder);
            try {
                processContractBuilder.build(pool.getContract());
            } catch (BuildProcessDefinitionException e) {
                throw new ProcessBuilderException(e);
            }
        }
    }

    private void addDocuments(final ProcessDefinitionBuilder builder, final Pool pool) {
        if (pool != null) {
            for (final Document document : pool.getDocuments()) {
                try {
                    documentBuilder.setEngineBuilder(builder);
                    documentBuilder.build(document);
                } catch (BuildProcessDefinitionException e) {
                    throw new ProcessBuilderException(
                            "Failed to export document definition for " + ((Element) pool).getName(), e);
                }
            }
        }
    }

    public ProcessDefinitionBuilder getProcessBuilder() {
        return builder;
    }
}
