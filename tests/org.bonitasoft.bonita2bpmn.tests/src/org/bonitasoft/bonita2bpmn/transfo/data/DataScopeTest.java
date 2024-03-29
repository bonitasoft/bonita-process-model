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
package org.bonitasoft.bonita2bpmn.transfo.data;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.bpm.model.process.builders.DataBuilder.aData;
import static org.bonitasoft.bpm.model.process.builders.PoolBuilder.aPool;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.bonitasoft.bpm.model.process.Data;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.omg.spec.bpmn.model.ModelFactory;
import org.omg.spec.bpmn.model.TItemDefinition;

@ExtendWith(MockitoExtension.class)
class DataScopeTest {

    private DataScope dataScope;
    @Mock
    private ItemDefinitionFunction itemDefinitionTransformer;
    private TItemDefinition itemDefinition;
    private Data data;
    private Pool pool;

    @BeforeEach
    void setUp() throws Exception {
        dataScope = new DataScope(itemDefinitionTransformer);

        ProcessFactory.eINSTANCE.createPool();

        pool = aPool().havingData(aData()).build();
        data = pool.getData().get(0);

    }

    @Test
    void should_get_a_TItemDefinition_for_a_given_data() throws Exception {
        itemDefinition = ModelFactory.eINSTANCE.createTItemDefinition();
        when(itemDefinitionTransformer.apply(data)).thenReturn(itemDefinition);

        dataScope.initializeContext(pool);

        assertThat(dataScope.get(data)).isEqualTo(itemDefinition);
    }

    @Test
    void should_get_null_if_no_item_exists_for_a_given_data() throws Exception {
        itemDefinition = ModelFactory.eINSTANCE.createTItemDefinition();
        when(itemDefinitionTransformer.apply(data)).thenReturn(itemDefinition);

        dataScope.initializeContext(pool);

        assertThat(dataScope.get(aData().build())).isNull();
    }

    @Test
    void should_get_throw_a_NullPointerException_if_scope_is_not_initialized() throws Exception {
        var data = aData().build();
        assertThrows(NullPointerException.class, () -> dataScope.get(data));
    }
}
