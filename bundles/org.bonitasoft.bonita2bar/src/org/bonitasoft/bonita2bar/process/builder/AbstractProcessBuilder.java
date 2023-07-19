/**
 * Copyright (C) 2012-2015 Bonitasoft S.A.
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

import org.bonitasoft.bonita2bar.process.expression.EngineExpressionUtil;
import org.bonitasoft.bonita2bar.process.expression.ExpressionHelper;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.bpm.model.expression.Operation;
import org.bonitasoft.bpm.model.kpi.AbstractKPIBinding;
import org.bonitasoft.bpm.model.kpi.DatabaseKPIBinding;
import org.bonitasoft.bpm.model.parameter.Parameter;
import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.bpm.model.process.Actor;
import org.bonitasoft.bpm.model.process.BusinessObjectData;
import org.bonitasoft.bpm.model.process.ConnectableElement;
import org.bonitasoft.bpm.model.process.Connector;
import org.bonitasoft.bpm.model.process.Data;
import org.bonitasoft.bpm.model.process.DataAware;
import org.bonitasoft.bpm.model.process.Document;
import org.bonitasoft.bpm.model.process.Element;
import org.bonitasoft.bpm.model.process.MainProcess;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.SearchIndex;
import org.bonitasoft.bpm.model.process.Task;
import org.bonitasoft.bpm.model.process.util.ProcessSwitch;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.bonitasoft.bpm.model.util.IModelSearch;
import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.engine.bpm.process.impl.ActorDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ConnectorDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.DataDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.DescriptionBuilder;
import org.bonitasoft.engine.bpm.process.impl.FlowElementBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.UserTaskDefinitionBuilder;
import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.engine.expression.ExpressionBuilder;
import org.bonitasoft.engine.expression.InvalidExpressionException;

public abstract class AbstractProcessBuilder extends ProcessSwitch<Element> {

    protected IModelSearch modelSearch;

    public static final String DB_CONNECTOR_FOR_KPI_ID = "database-jdbc";
    public static final String DB_CONNECTOR_VERSION = "1.0.0";
    public static final String DB_DRIVER = "driver";
    public static final String DB_URL = "url";
    public static final String DB_QUERY = "script";
    public static final String DB_USER = "username";
    public static final String DB_PASSWORD = "password";

    private static final String SUFFIX_CONTEXT = "_ref";

    protected AbstractProcessBuilder(IModelSearch modelSearch) {
        this.modelSearch = modelSearch;
    }

    protected void addActors(final ProcessDefinitionBuilder builder, final AbstractProcess process) {
        for (final Actor a : process.getActors()) {
            final ActorDefinitionBuilder actorBuilder = builder.addActor(a.getName(), a.isInitiator());
            if (a.getDocumentation() != null) {
                actorBuilder.addDescription(a.getDocumentation());
            }
        }
    }

    protected void addParameters(final ProcessDefinitionBuilder builder, final AbstractProcess process) {
        for (final Parameter p : process.getParameters()) {
            final String description = p.getDescription();
            builder.addParameter(p.getName(), p.getTypeClassname())
                    .addDescription(description == null ? "" : description);
        }
    }

    protected void addSearchIndex(final ProcessDefinitionBuilder builder, final AbstractProcess process)
            throws InvalidExpressionException {
        if (process instanceof Pool) {
            final Pool pool = (Pool) process;
            int i = 1;
            for (final SearchIndex searchIndex : pool.getSearchIndexes()) {
                final Expression expr = EngineExpressionUtil.createExpression(searchIndex.getValue());
                if (searchIndex.getName().hasContent()) {
                    builder.setStringIndex(i, searchIndex.getName().getContent(), expr);
                }
                i++;
            }
        }
    }

    protected void addConnector(final FlowElementBuilder builder, final ConnectableElement element)
            throws InvalidExpressionException {
        for (final Connector connector : element.getConnectors()) {
            final GroovyConnectorConfigurationConverter groovyConnectorConfigurationConverter = new GroovyConnectorConfigurationConverter();
            ConnectorConfiguration configuration = connector.getConfiguration();
            if (configuration == null) {
                throw new MissingConnectorConfigurationException(connector, element);
            }
            if (groovyConnectorConfigurationConverter.appliesTo(configuration)) {
                configuration = groovyConnectorConfigurationConverter.convert(connector.getConfiguration());
            }
            final ConnectorDefinitionBuilder connectorBuilder = builder.addConnector(connector.getName(),
                    connector.getDefinitionId(), connector.getDefinitionVersion(),
                    ConnectorEvent.valueOf(connector.getEvent()));
            handleConnectorBehaviorOnFailure(connector, connectorBuilder);
            handleConnectorInputs(configuration, connectorBuilder);
            handleConnectorOutputs(connector, connectorBuilder);
        }
    }

    private void handleConnectorBehaviorOnFailure(final Connector connector,
            final ConnectorDefinitionBuilder connectorBuilder) {
        if (connector.isIgnoreErrors()) {
            connectorBuilder.ignoreError();
        } else if (connector.isThrowErrorEvent()) {
            connectorBuilder.throwErrorEventWhenFailed(connector.getNamedError());
        }
    }

    private void handleConnectorInputs(final ConnectorConfiguration configuration,
            final ConnectorDefinitionBuilder connectorBuilder) throws InvalidExpressionException {
        for (final ConnectorParameter parameter : configuration.getParameters()) {
            final Expression inputExpression = EngineExpressionUtil.createExpression(parameter.getExpression());
            if (inputExpression != null) {
                connectorBuilder.addInput(parameter.getKey(), inputExpression);
            }
        }
    }

    private void handleConnectorOutputs(final Connector connector, final ConnectorDefinitionBuilder connectorBuilder)
            throws InvalidExpressionException {
        for (final Operation outputOperation : connector.getOutputs()) {
            if (outputOperation.getLeftOperand() != null && outputOperation.getLeftOperand().getContent() != null
                    && !outputOperation.getLeftOperand().getContent().isEmpty()
                    && outputOperation.getRightOperand() != null
                    && outputOperation.getRightOperand().getContent() != null) {
                connectorBuilder.addOutput(EngineExpressionUtil.createOperation(outputOperation));
            }
        }
    }

    protected void addKPIBinding(final FlowElementBuilder builder, final ConnectableElement element)
            throws InvalidExpressionException {
        for (final AbstractKPIBinding kpiBinding : element.getKpis()) {
            if (kpiBinding instanceof DatabaseKPIBinding) {
                final ConnectorDefinitionBuilder connectorBuilder = builder.addConnector(kpiBinding.getName(),
                        DB_CONNECTOR_FOR_KPI_ID, DB_CONNECTOR_VERSION, ConnectorEvent.valueOf(kpiBinding.getEvent()));
                if (kpiBinding.isIgnoreError()) {
                    connectorBuilder.ignoreError();
                }
                final DatabaseKPIBinding dbKPI = (DatabaseKPIBinding) kpiBinding;
                connectorBuilder.addInput(DB_DRIVER, EngineExpressionUtil.createExpression(dbKPI.getDriverclassName()));
                connectorBuilder.addInput(DB_URL, EngineExpressionUtil.createExpression(dbKPI.getJdbcUrl()));
                connectorBuilder.addInput(DB_QUERY, EngineExpressionUtil.createExpression(dbKPI.getRequest()));
                final Expression dbUserExpression = EngineExpressionUtil.createExpression(dbKPI.getUser());
                if (dbUserExpression != null) {
                    connectorBuilder.addInput(DB_USER, dbUserExpression);
                }
                final Expression dbPasswordExpression = EngineExpressionUtil.createExpression(dbKPI.getPassword());
                if (dbPasswordExpression != null) {
                    connectorBuilder.addInput(DB_PASSWORD, dbPasswordExpression);
                }
            }
        }
    }

    protected void addData(final FlowElementBuilder dataContainerBuilder, final DataAware dataAwareContainer) {
        for (final Data data : dataAwareContainer.getData()) {
            final ProcessSwitch<DataDefinitionBuilder> dataSwitch = getDataSwitch(dataContainerBuilder, data);
            final DataDefinitionBuilder dataBuilder = dataSwitch.doSwitch(data.getDataType());
            if (data.isTransient() && dataBuilder != null) {
                dataBuilder.isTransient();
            }
        }
    }

    protected EngineDataBuilder getDataSwitch(final FlowElementBuilder dataContainerBuilder, final Data data) {
        return new EngineDataBuilder(data, dataContainerBuilder);
    }

    protected void addDescription(final DescriptionBuilder builder, final String description) {
        if (description != null && !description.isEmpty()) {
            builder.addDescription(description);
        }
    }

    protected void addContext(final Object contextBuilder, final Task task) throws InvalidExpressionException {
        final Pool pool = modelSearch.getDirectParentOfType(task, Pool.class);
        addContext(contextBuilder, pool);
        addIteratorToContext(contextBuilder, task);
    }

    private void addIteratorToContext(final Object contextBuilder, final Task task) throws InvalidExpressionException {
        final org.bonitasoft.bpm.model.expression.Expression iteratorExpression = task.getIteratorExpression();
        if (iteratorExpression != null
                && ExpressionConstants.MULTIINSTANCE_ITERATOR_TYPE.equals(iteratorExpression.getType())
                && iteratorExpression.getName() != null && !iteratorExpression.getName().isEmpty()
                && task instanceof DataAware) {
            final String referenceName = iteratorExpression.getName() + SUFFIX_CONTEXT;
            final Data data = ExpressionHelper.dataFromIteratorExpression(task, iteratorExpression,
                    modelSearch.getDirectParentOfType(task, MainProcess.class));
            if (data instanceof BusinessObjectData) {
                final Expression expression = createBusinessObjectDataReferenceExpression((BusinessObjectData) data);
                addContextEntry(contextBuilder, referenceName, expression);
            }
        }
    }

    protected void addContext(final Object contextBuilder, final Pool pool) throws InvalidExpressionException {
        addBusinessDataInContext(contextBuilder, pool);
        addDocumentInContext(contextBuilder, pool);
    }

    private void addDocumentInContext(final Object contextBuilder, final Pool pool) throws InvalidExpressionException {
        for (final Document document : pool.getDocuments()) {
            final String referenceName = document.getName() + SUFFIX_CONTEXT;
            final Expression documentReferenceExpression = createDocumentExpression(document);
            addContextEntry(contextBuilder, referenceName, documentReferenceExpression);
        }
    }

    private static Expression createDocumentExpression(final Document document) throws InvalidExpressionException {
        var expressionBuilder = new ExpressionBuilder();
        return document.isMultiple() ? expressionBuilder.createDocumentListExpression(document.getName())
                : expressionBuilder.createDocumentReferenceExpression(document.getName());
    }

    private static Expression createBusinessObjectDataReferenceExpression(final BusinessObjectData data)
            throws InvalidExpressionException {
        return new ExpressionBuilder().createBusinessDataReferenceExpression(data.getName());
    }

    private void addBusinessDataInContext(final Object contextBuilder, final Pool pool)
            throws InvalidExpressionException {
        for (final Data data : pool.getData()) {
            if (data instanceof BusinessObjectData) {
                final String referenceName = data.getName() + SUFFIX_CONTEXT;
                final Expression referenceExpression = createBusinessObjectDataReferenceExpression(
                        (BusinessObjectData) data);
                addContextEntry(contextBuilder, referenceName, referenceExpression);
            }
        }
    }

    private void addContextEntry(final Object contextBuilder, final String referenceName,
            final org.bonitasoft.engine.expression.Expression referenceExpression) {
        if (contextBuilder instanceof UserTaskDefinitionBuilder) {
            ((UserTaskDefinitionBuilder) contextBuilder).addContextEntry(referenceName, referenceExpression);
        } else if (contextBuilder instanceof ProcessDefinitionBuilder) {
            ((ProcessDefinitionBuilder) contextBuilder).addContextEntry(referenceName, referenceExpression);
        }
    }

}
