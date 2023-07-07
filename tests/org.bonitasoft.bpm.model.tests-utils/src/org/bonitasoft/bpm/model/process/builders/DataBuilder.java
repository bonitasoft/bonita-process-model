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

import org.bonitasoft.bpm.model.Buildable;
import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder;
import org.bonitasoft.bpm.model.process.Data;
import org.bonitasoft.bpm.model.process.DataAware;
import org.bonitasoft.bpm.model.process.DataType;
import org.bonitasoft.bpm.model.process.ProcessFactory;

/**
 * @author Romain Bioteau
 */
public class DataBuilder<T extends Data, B extends DataBuilder<T, B>> extends ElementBuilder<T, B> {

    public static <B extends DataBuilder<Data, B>> DataBuilder<Data, B> aData() {
        return new DataBuilder<Data, B>();
    }

    public B withDatasourceId(final String datasourceId) {
        getBuiltInstance().setDatasourceId(datasourceId);
        return getThis();
    }

    public B isTransient() {
        getBuiltInstance().setTransient(true);
        return getThis();
    }

    public B notTransient() {
        getBuiltInstance().setTransient(false);
        return getThis();
    }

    public B generated() {
        getBuiltInstance().setGenerated(true);
        return getThis();
    }

    public B notGenerated() {
        getBuiltInstance().setGenerated(false);
        return getThis();
    }

    public B multiple() {
        getBuiltInstance().setMultiple(true);
        return getThis();
    }

    public B single() {
        getBuiltInstance().setMultiple(false);
        return getThis();
    }

    public B havingDefaultValue(final ExpressionBuilder defaultValueExpression) {
        getBuiltInstance().setDefaultValue(defaultValueExpression.build());
        return getThis();
    }

    public B havingDefaultValue(final Expression defaultValueExpression) {
        getBuiltInstance().setDefaultValue(defaultValueExpression);
        return getThis();
    }

    public B havingDataType(final DataTypeBuilder<?, ?> dataType) {
        getBuiltInstance().setDataType(dataType.build());
        return getThis();
    }

    public B havingDataType(final DataType dataType) {
        getBuiltInstance().setDataType(dataType);
        return getThis();
    }

    public B in(final Buildable<? extends DataAware> dataAwareBuildable) {
        dataAwareBuildable.build().getData().add(getBuiltInstance());
        return getThis();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected T newInstance() {
        return (T) ProcessFactory.eINSTANCE.createData();
    }

}
