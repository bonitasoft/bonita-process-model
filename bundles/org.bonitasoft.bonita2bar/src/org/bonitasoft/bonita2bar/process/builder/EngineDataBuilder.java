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

import java.util.Collection;

import org.bonitasoft.bonita2bar.process.expression.EngineExpressionUtil;
import org.bonitasoft.bpm.model.process.BooleanType;
import org.bonitasoft.bpm.model.process.BusinessObjectData;
import org.bonitasoft.bpm.model.process.BusinessObjectType;
import org.bonitasoft.bpm.model.process.Data;
import org.bonitasoft.bpm.model.process.DateType;
import org.bonitasoft.bpm.model.process.DoubleType;
import org.bonitasoft.bpm.model.process.EnumType;
import org.bonitasoft.bpm.model.process.FloatType;
import org.bonitasoft.bpm.model.process.IntegerType;
import org.bonitasoft.bpm.model.process.JavaObjectData;
import org.bonitasoft.bpm.model.process.JavaType;
import org.bonitasoft.bpm.model.process.LongType;
import org.bonitasoft.bpm.model.process.StringType;
import org.bonitasoft.bpm.model.process.XMLType;
import org.bonitasoft.bpm.model.process.util.ProcessSwitch;
import org.bonitasoft.engine.bpm.process.impl.BusinessDataDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.DataDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.FlowElementBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.bonitasoft.engine.expression.Expression;
import org.bonitasoft.engine.expression.InvalidExpressionException;

public class EngineDataBuilder extends ProcessSwitch<DataDefinitionBuilder> {

    private final Data data;
    private final FlowElementBuilder builder;
    private Expression expr;

    public EngineDataBuilder(final Data data, final FlowElementBuilder flowElementBuilder) {
        builder = flowElementBuilder;
        this.data = data;
        this.expr = createExpression(data);
    }

    private Expression createExpression(final Data data) {
        try {
            var expression = EngineExpressionUtil.createExpression(data.getDefaultValue());
            if (expression == null && data.isMultiple()) {
                return EngineExpressionUtil.createEmptyListExpression();
            }
            return expression;
        } catch (InvalidExpressionException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Expression getDefaultValueExpression() {
        return expr;
    }

    public Data getData() {
        return data;
    }

    @Override
    public DataDefinitionBuilder caseStringType(final StringType type) {
        if (data.isMultiple()) {
            return addCollectionData(data.getName(), expr);
        }
        return builder.addLongTextData(data.getName(), expr);
    }

    protected DataDefinitionBuilder addCollectionData(final String name, final Expression defaultValue) {
        return builder.addData(name, Collection.class.getName(), defaultValue);
    }

    @Override
    public DataDefinitionBuilder caseLongType(final LongType object) {
        if (data.isMultiple()) {
            return addCollectionData(data.getName(), expr);
        }
        return builder.addLongData(data.getName(), expr);
    }

    @Override
    public DataDefinitionBuilder caseDoubleType(final DoubleType object) {
        if (data.isMultiple()) {
            return addCollectionData(data.getName(), expr);
        }
        return builder.addDoubleData(data.getName(), expr);
    }

    @Override
    public DataDefinitionBuilder caseIntegerType(final IntegerType type) {
        if (data.isMultiple()) {
            return addCollectionData(data.getName(), expr);
        }
        return builder.addIntegerData(data.getName(), expr);
    }

    @Override
    public DataDefinitionBuilder caseBooleanType(final BooleanType type) {
        if (data.isMultiple()) {
            return addCollectionData(data.getName(), expr);
        }
        return builder.addBooleanData(data.getName(), expr);
    }

    @Override
    public DataDefinitionBuilder caseFloatType(final FloatType type) {
        throw new UnsupportedOperationException("FloatType is not supported");
    }

    @Override
    public DataDefinitionBuilder caseEnumType(final EnumType type) {
        if (data.isMultiple()) {
            return addCollectionData(data.getName(), expr);
        }
        return builder.addShortTextData(data.getName(), expr);
    }

    @Override
    public DataDefinitionBuilder caseJavaType(final JavaType type) {
        if (data.isMultiple()) {
            return addCollectionData(data.getName(), expr);
        }
        return builder.addData(data.getName(), ((JavaObjectData) data).getClassName(), expr);
    }

    @Override
    public DataDefinitionBuilder caseXMLType(final XMLType type) {
        if (data.isMultiple()) {
            return addCollectionData(data.getName(), expr);
        }
        return builder.addXMLData(data.getName(), expr);
    }

    @Override
    public DataDefinitionBuilder caseDateType(final DateType type) {
        if (data.isMultiple()) {
            return addCollectionData(data.getName(), expr);
        }
        return builder.addDateData(data.getName(), expr);
    }

    @Override
    public DataDefinitionBuilder caseBusinessObjectType(final BusinessObjectType object) {
        final BusinessObjectData bod = (BusinessObjectData) getData();
        if (builder instanceof ProcessDefinitionBuilder) {
            final BusinessDataDefinitionBuilder businessDataBuilder = ((ProcessDefinitionBuilder) builder)
                    .addBusinessData(bod.getName(),
                            bod.getClassName(), getDefaultValueExpression());
            businessDataBuilder.setMultiple(bod.isMultiple());
            businessDataBuilder.addDescription(bod.getDocumentation());
        }
        return null;
    }
}
