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
package org.bonitasoft.bonita2bar.process.builder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder.aGroovyScriptExpression;
import static org.bonitasoft.bpm.model.process.builders.BooleanDataTypeBuilder.aBooleanDataType;
import static org.bonitasoft.bpm.model.process.builders.BusinessObjectDataBuilder.aBusinessData;
import static org.bonitasoft.bpm.model.process.builders.DataBuilder.aData;
import static org.bonitasoft.bpm.model.process.builders.DateDataTypeBuilder.aDateDataType;
import static org.bonitasoft.bpm.model.process.builders.DoubleDataTypeBuilder.aDoubleDataType;
import static org.bonitasoft.bpm.model.process.builders.IntegerDataTypeBuilder.anIntegerDataType;
import static org.bonitasoft.bpm.model.process.builders.JavaObjectDataBuilder.aJavaObjectData;
import static org.bonitasoft.bpm.model.process.builders.LongDataTypeBuilder.aLongDataType;
import static org.bonitasoft.bpm.model.process.builders.StringDataTypeBuilder.aStringDataType;
import static org.bonitasoft.bpm.model.process.builders.XMLDataBuilder.anXMLData;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.bonitasoft.bonita2bar.process.expression.EngineExpressionUtil;
import org.bonitasoft.bpm.model.process.BusinessObjectData;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.bpm.model.process.builders.EnumDataTypeBuilder;
import org.bonitasoft.bpm.model.process.builders.XMLDataTypeBuilder;
import org.bonitasoft.engine.bpm.businessdata.BusinessDataDefinition;
import org.bonitasoft.engine.bpm.data.TextDataDefinition;
import org.bonitasoft.engine.bpm.data.XMLDataDefinition;
import org.bonitasoft.engine.bpm.flownode.FlowElementContainerDefinition;
import org.bonitasoft.engine.bpm.process.impl.ActivityDefinitionBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EngineDataBuilderTest {

    private ProcessDefinitionBuilder builder;

    @BeforeEach
    public void setUp() throws Exception {
        builder = new ProcessDefinitionBuilder().createNewInstance("p1", "1.0");
    }

    @Test
    void caseStringType() throws Exception {
        var data = aData()
                .havingDataType(aStringDataType())
                .withName("myText")
                .build();

        var dataBuilder = new EngineDataBuilder(data, builder);

        dataBuilder.doSwitch(data.getDataType());

        var myText = builder.getProcess().getFlowElementContainer().getDataDefinition("myText");
        assertThat(myText).isInstanceOf(TextDataDefinition.class);
        assertThat(((TextDataDefinition) myText).isLongText()).isTrue();
        assertThat(myText.getClassName()).isEqualTo(String.class.getName());
    }

    @Test
    void caseMultipleStringWithDefaultExpression() throws Exception {
        var data = aData()
                .havingDataType(aStringDataType())
                .multiple()
                .withName("myList")
                .havingDefaultValue(aGroovyScriptExpression()
                        .withName("initialValue")
                        .withContent("['hello', 'world']"))
                .build();

        var dataBuilder = new EngineDataBuilder(data, builder);

        dataBuilder.doSwitch(data.getDataType());

        var myList = builder.getProcess().getFlowElementContainer().getDataDefinition("myList");
        assertThat(myList).isNotNull();
        assertThat(myList.getClassName()).isEqualTo(Collection.class.getName());
        assertThat(myList.getDefaultValueExpression()).isNotNull();
        assertThat(myList.getDefaultValueExpression().getContent()).isEqualTo("['hello', 'world']");
    }

    @Test
    void caseMultipleStringWithoutDefaultExpression() throws Exception {
        var data = aData()
                .havingDataType(aStringDataType())
                .multiple()
                .withName("myList")
                .build();

        var dataBuilder = new EngineDataBuilder(data, builder);

        dataBuilder.doSwitch(data.getDataType());

        var myList = builder.getProcess().getFlowElementContainer().getDataDefinition("myList");
        assertThat(myList).isNotNull();
        assertThat(myList.getClassName()).isEqualTo(Collection.class.getName());
        assertThat(myList.getDefaultValueExpression()).isNotNull();
        assertThat(myList.getDefaultValueExpression().getContent()).isEqualTo("[]");
    }

    @Test
    void caseLongType() throws Exception {
        var data = aData()
                .havingDataType(aLongDataType())
                .withName("myId")
                .build();

        var dataBuilder = new EngineDataBuilder(data, builder);

        dataBuilder.doSwitch(data.getDataType());

        var myId = builder.getProcess().getFlowElementContainer().getDataDefinition("myId");
        assertThat(myId).isNotNull();
        assertThat(myId.getClassName()).isEqualTo(Long.class.getName());
    }

    @Test
    void caseMultipleLongType() throws Exception {
        var data = aData()
                .havingDataType(aLongDataType())
                .withName("myIds")
                .multiple()
                .build();

        var dataBuilder = new EngineDataBuilder(data, builder);

        dataBuilder.doSwitch(data.getDataType());

        var myId = builder.getProcess().getFlowElementContainer().getDataDefinition("myIds");
        assertThat(myId).isNotNull();
        assertThat(myId.getClassName()).isEqualTo(Collection.class.getName());
    }

    @Test
    void caseDoubleType() throws Exception {
        var data = aData()
                .havingDataType(aDoubleDataType())
                .withName("myValue")
                .build();

        var dataBuilder = new EngineDataBuilder(data, builder);

        dataBuilder.doSwitch(data.getDataType());

        var myValue = builder.getProcess().getFlowElementContainer().getDataDefinition("myValue");
        assertThat(myValue).isNotNull();
        assertThat(myValue.getClassName()).isEqualTo(Double.class.getName());
    }

    @Test
    void caseMultipleDoubleType() throws Exception {
        var data = aData()
                .havingDataType(aDoubleDataType())
                .withName("myValues")
                .multiple()
                .build();

        var dataBuilder = new EngineDataBuilder(data, builder);

        dataBuilder.doSwitch(data.getDataType());

        var myValues = builder.getProcess().getFlowElementContainer().getDataDefinition("myValues");
        assertThat(myValues).isNotNull();
        assertThat(myValues.getClassName()).isEqualTo(Collection.class.getName());
    }

    @Test
    void caseIntegerType() throws Exception {
        var data = aData()
                .havingDataType(anIntegerDataType())
                .withName("myValue")
                .build();

        var dataBuilder = new EngineDataBuilder(data, builder);

        dataBuilder.doSwitch(data.getDataType());

        var myValue = builder.getProcess().getFlowElementContainer().getDataDefinition("myValue");
        assertThat(myValue).isNotNull();
        assertThat(myValue.getClassName()).isEqualTo(Integer.class.getName());
    }

    @Test
    void caseMultipleIntegerType() throws Exception {
        var data = aData()
                .havingDataType(anIntegerDataType())
                .withName("myValues")
                .multiple()
                .build();

        var dataBuilder = new EngineDataBuilder(data, builder);

        dataBuilder.doSwitch(data.getDataType());

        var myValues = builder.getProcess().getFlowElementContainer().getDataDefinition("myValues");
        assertThat(myValues).isNotNull();
        assertThat(myValues.getClassName()).isEqualTo(Collection.class.getName());
    }

    @Test
    void caseBooleanType() throws Exception {
        var data = aData()
                .havingDataType(aBooleanDataType())
                .withName("myValue")
                .build();

        var dataBuilder = new EngineDataBuilder(data, builder);

        dataBuilder.doSwitch(data.getDataType());

        var myValue = builder.getProcess().getFlowElementContainer().getDataDefinition("myValue");
        assertThat(myValue).isNotNull();
        assertThat(myValue.getClassName()).isEqualTo(Boolean.class.getName());
    }

    @Test
    void caseMultipleBooleanType() throws Exception {
        var data = aData()
                .havingDataType(aBooleanDataType())
                .withName("myValues")
                .multiple()
                .build();

        var dataBuilder = new EngineDataBuilder(data, builder);

        dataBuilder.doSwitch(data.getDataType());

        var myValues = builder.getProcess().getFlowElementContainer().getDataDefinition("myValues");
        assertThat(myValues).isNotNull();
        assertThat(myValues.getClassName()).isEqualTo(Collection.class.getName());
    }

    @Test
    void caseFloatType() throws Exception {
        var floatType = ProcessFactory.eINSTANCE.createFloatType();
        var data = aData()
                .havingDataType(floatType)
                .withName("myValue")
                .build();

        var dataBuilder = new EngineDataBuilder(data, builder);

        assertThrows(UnsupportedOperationException.class, () -> dataBuilder.doSwitch(floatType));
    }

    @Test
    void caseEnumType() throws Exception {
        var data = aData()
                .havingDataType(EnumDataTypeBuilder.create()
                        .havingLiterals("VALID", "INVALID"))
                .withName("myValue")
                .build();

        var dataBuilder = new EngineDataBuilder(data, builder);

        dataBuilder.doSwitch(data.getDataType());

        var myValue = builder.getProcess().getFlowElementContainer().getDataDefinition("myValue");
        assertThat(myValue).isInstanceOf(TextDataDefinition.class);
        assertThat(((TextDataDefinition) myValue).isLongText()).isFalse();
        assertThat(myValue.getClassName()).isEqualTo(String.class.getName());
    }

    @Test
    void caseMultipleEnumType() throws Exception {
        var data = aData()
                .havingDataType(EnumDataTypeBuilder.create()
                        .havingLiterals("VALID", "INVALID"))
                .withName("myValue")
                .multiple()
                .build();

        var dataBuilder = new EngineDataBuilder(data, builder);

        dataBuilder.doSwitch(data.getDataType());

        var myValue = builder.getProcess().getFlowElementContainer().getDataDefinition("myValue");
        assertThat(myValue).isNotNull();
        assertThat(myValue.getClassName()).isEqualTo(Collection.class.getName());
    }

    @Test
    void caseJavaType() throws Exception {
        var data = aJavaObjectData()
                .withName("myValue")
                .withClassname(BigDecimal.class.getName())
                .build();

        var dataBuilder = new EngineDataBuilder(data, builder);

        dataBuilder.doSwitch(data.getDataType());

        var myValue = builder.getProcess().getFlowElementContainer().getDataDefinition("myValue");
        assertThat(myValue).isNotNull();
        assertThat(myValue.getClassName()).isEqualTo(BigDecimal.class.getName());
    }

    @Test
    void caseMultipleJavaType() throws Exception {
        var data = aJavaObjectData()
                .withName("myValues")
                .withClassname(BigDecimal.class.getName())
                .multiple()
                .build();

        var dataBuilder = new EngineDataBuilder(data, builder);

        dataBuilder.doSwitch(data.getDataType());

        var myValues = builder.getProcess().getFlowElementContainer().getDataDefinition("myValues");
        assertThat(myValues).isNotNull();
        assertThat(myValues.getClassName()).isEqualTo(Collection.class.getName());
    }

    @Test
    void caseXMLType() throws Exception {
        var data = anXMLData()
                .havingDataType(XMLDataTypeBuilder.create())
                .withName("myValue")
                .build();

        var dataBuilder = new EngineDataBuilder(data, builder);

        dataBuilder.doSwitch(data.getDataType());

        var myValue = builder.getProcess().getFlowElementContainer().getDataDefinition("myValue");
        assertThat(myValue).isInstanceOf(XMLDataDefinition.class);
        assertThat(myValue.getClassName()).isEqualTo(String.class.getName());
    }

    @Test
    void caseMultipleXMLType() throws Exception {
        var data = anXMLData()
                .havingDataType(XMLDataTypeBuilder.create())
                .withName("myValues")
                .multiple()
                .build();

        var dataBuilder = new EngineDataBuilder(data, builder);

        dataBuilder.doSwitch(data.getDataType());

        var myValues = builder.getProcess().getFlowElementContainer().getDataDefinition("myValues");
        assertThat(myValues).isNotNull();
        assertThat(myValues.getClassName()).isEqualTo(Collection.class.getName());
    }

    @Test
    void caseDateType() throws Exception {
        var data = aData()
                .havingDataType(aDateDataType())
                .withName("myValue")
                .build();

        var dataBuilder = new EngineDataBuilder(data, builder);

        dataBuilder.doSwitch(data.getDataType());

        var myValue = builder.getProcess().getFlowElementContainer().getDataDefinition("myValue");
        assertThat(myValue).isNotNull();
        assertThat(myValue.getClassName()).isEqualTo(Date.class.getName());
    }

    @Test
    void caseMultipleDateType() throws Exception {
        var data = aData()
                .havingDataType(aDateDataType())
                .withName("myValues")
                .multiple()
                .build();

        var dataBuilder = new EngineDataBuilder(data, builder);

        dataBuilder.doSwitch(data.getDataType());

        var myValues = builder.getProcess().getFlowElementContainer().getDataDefinition("myValues");
        assertThat(myValues).isNotNull();
        assertThat(myValues.getClassName()).isEqualTo(Collection.class.getName());
    }

    @Test
    void caseBusinessObjectType() throws Exception {
        final BusinessObjectData data = aBusinessData()
                .withName("myLeaveRequest")
                .withClassname("org.bonitasoft.hr.LeaveRequest")
                .withDocumentation("Some doc")
                .havingDefaultValue(
                        aGroovyScriptExpression().withName("init").withContent("new LeaveRequest()")
                                .withReturnType("org.bonitasoft.hr.LeaveRequest"))
                .build();
        final EngineDataBuilder switchUnderTest = new EngineDataBuilder(data, builder);

        switchUnderTest.doSwitch(data.getDataType());

        final FlowElementContainerDefinition flowElementContainerDefinition = builder.getProcess()
                .getFlowElementContainer();
        final List<BusinessDataDefinition> businessDataDefinitions = flowElementContainerDefinition
                .getBusinessDataDefinitions();
        assertThat(businessDataDefinitions).hasSize(1);
        assertThat(businessDataDefinitions).extracting("name", "className", "description", "multiple").contains(
                tuple("myLeaveRequest", "org.bonitasoft.hr.LeaveRequest", "Some doc", false));
        assertThat(businessDataDefinitions.get(0).getDefaultValueExpression())
                .usingRecursiveComparison(RecursiveComparisonConfiguration.builder().withIgnoredFields("id").build())
                .isEqualTo(EngineExpressionUtil.createExpression(data.getDefaultValue()));
    }

    @Test
    void caseMultipleBusinessObjectType() throws Exception {
        final BusinessObjectData data = aBusinessData().withName("myLeaveRequest")
                .withClassname("org.bonitasoft.hr.LeaveRequest").multiple().build();
        final EngineDataBuilder switchUnderTest = new EngineDataBuilder(data, builder);

        switchUnderTest.doSwitch(data.getDataType());

        final List<BusinessDataDefinition> businessDataDefinitions = builder.getProcess().getFlowElementContainer()
                .getBusinessDataDefinitions();
        assertThat(businessDataDefinitions).hasSize(1);
        assertThat(businessDataDefinitions).extracting("multiple").contains(true);
    }

    @Test
    void caseBusinessObjectTypeWithInvalidBuilder() throws Exception {
        final BusinessObjectData data = aBusinessData().withName("myLeaveRequest")
                .withClassname("org.bonitasoft.hr.LeaveRequest").multiple().build();
        final EngineDataBuilder switchUnderTest = new EngineDataBuilder(data,
                mock(ActivityDefinitionBuilder.class));

        var dataBuilder = switchUnderTest.doSwitch(data.getDataType());

        assertThat(dataBuilder).isNull();
    }

}
