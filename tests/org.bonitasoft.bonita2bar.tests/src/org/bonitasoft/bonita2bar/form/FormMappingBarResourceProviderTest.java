/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.bonita2bar.form;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.bonitasoft.bpm.model.expression.builders.ExpressionBuilder.anExpression;
import static org.bonitasoft.bpm.model.process.builders.FormMappingBuilder.aFormMapping;
import static org.bonitasoft.bpm.model.process.builders.PoolBuilder.aPool;
import static org.bonitasoft.bpm.model.process.builders.TaskBuilder.aTask;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.nio.file.Path;

import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.engine.bpm.bar.form.model.FormMappingDefinition;
import org.bonitasoft.engine.bpm.bar.form.model.FormMappingModel;
import org.bonitasoft.engine.form.FormMappingTarget;
import org.bonitasoft.engine.form.FormMappingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FormMappingBarResourceProviderTest {

    @Mock
    private BusinessArchiveBuilder builder;

    @Mock
    private CustomPageBarResourceBuilder customPageBarResourceBuilder;

    @Mock
    private BarResource processFormCustomPage;

    @Mock
    private BarResource taskFormCustomPage;

    private FormMappingBarResourceProvider formMappingBarResourceProvider;

    @BeforeEach
    void setUp(@TempDir Path formsFolder) throws Exception {
        formMappingBarResourceProvider = spy(
                new FormMappingBarResourceProvider(customPageBarResourceBuilder, formsFolder, true));
    }

    @Test
    void should_throw_a_NullPointerException_if_process_is_null() throws Exception {
        var builder = new BusinessArchiveBuilder();
        assertThrows(NullPointerException.class,
                () -> formMappingBarResourceProvider.addResourcesForConfiguration(builder, null));
    }

    @Test
    void should_add_formMapping_resource_in_bar() throws Exception {
        // Given
        doReturn("id").when(formMappingBarResourceProvider).resolveUUID(any());
        final Pool aPoolAndTaskWithFormMappings = aPoolAndTaskWithAllTypeOfFormMappings();
        final FormMappingModel formMappingModel = formMappingBarResourceProvider.newFormMappingModel(builder,
                aPoolAndTaskWithFormMappings);

        // When
        formMappingBarResourceProvider.addResourcesForConfiguration(builder, aPoolAndTaskWithFormMappings);

        // Then
        verify(builder).setFormMappings(formMappingModel);
        assertThat(formMappingModel.getFormMappings()).hasSize(2);
        assertThat(formMappingModel.getFormMappings()).extracting("target", "form", "type", "taskname").contains(
                tuple(FormMappingTarget.URL, "http://www.bonitasoft.com", FormMappingType.PROCESS_OVERVIEW, null),
                tuple(FormMappingTarget.INTERNAL, "custompage_StepForm", FormMappingType.TASK, "Step1"));
    }

    @Test
    void should_not_add_formMapping_resource_in_bar_if_mapping_is_invalid() throws Exception {
        // Given
        doReturn("id").when(formMappingBarResourceProvider).resolveUUID(any());
        final Pool pool = aPoolWithInvalidFormMapping();
        final FormMappingModel formMappingModel = formMappingBarResourceProvider.newFormMappingModel(builder, pool);

        // When
        formMappingBarResourceProvider.addResourcesForConfiguration(builder, pool);

        // Then
        verify(builder).setFormMappings(formMappingModel);
        assertThat(formMappingModel.getFormMappings()).hasSize(3);
        assertThat(formMappingModel.getFormMappings()).extracting("target", "form", "type", "taskname").contains(
                tuple(FormMappingTarget.INTERNAL, null, FormMappingType.PROCESS_START, null),
                tuple(FormMappingTarget.URL, null, FormMappingType.PROCESS_OVERVIEW, null),
                tuple(FormMappingTarget.INTERNAL, "custompage_Step1", FormMappingType.TASK, "Step1"));
    }

    @Test
    void should_add_form_custom_page_as_a_bar_resource() throws Exception {
        // Given
        doReturn("step-form-id").when(formMappingBarResourceProvider).resolveUUID("step-form-id");
        doReturn(taskFormCustomPage).when(customPageBarResourceBuilder).newBarResource("custompage_StepForm",
                "step-form-id");

        // When
        formMappingBarResourceProvider.addResourcesForConfiguration(builder, aPoolAndTaskWithAllTypeOfFormMappings());

        // Then
        verify(builder).addExternalResource(taskFormCustomPage);
    }

    @Test
    void should_not_add_form_custom_page_if_target_form_is_empty() throws Exception {
        // When
        doReturn("").when(formMappingBarResourceProvider).resolveUUID("");
        formMappingBarResourceProvider.addResourcesForConfiguration(builder, aPoolWithEmptyFormMappings());

        // Then
        verify(builder, never()).addExternalResource(any(BarResource.class));
    }

    @Test
    void should_throw_InternalFormNotFoundExceptionn_if_target_form_is_not_empty_but_not_resolved() throws Exception {
        // Given
        doReturn(null).when(formMappingBarResourceProvider).resolveUUID("step-form-id");

        // Expect
        assertThrows(InternalFormNotFoundException.class, () -> formMappingBarResourceProvider
                .addResourcesForConfiguration(builder, aPoolAndTaskWithAllTypeOfFormMappings()));
    }

    @Test
    void should_create_a_mapping_for_empty_internal_overview() throws Exception {
        final FormMappingModel formMappingModel = formMappingBarResourceProvider.newFormMappingModel(builder,
                aPoolWithEmptyOverviewInternalFormMappings());

        assertThat(formMappingModel.getFormMappings()).hasSize(1);
        assertThat(formMappingModel.getFormMappings().get(0)).usingRecursiveComparison()
                .isEqualTo(new FormMappingDefinition("custompage_caseoverview", FormMappingType.PROCESS_OVERVIEW,
                        FormMappingTarget.INTERNAL));
    }

    @Test
    void should_throw_an_InternalFormNotFoundExecption_when_creating_an_internal_mapping_without_custom_page_and_mapping_is_forced(
            @TempDir Path formsFolder) throws Exception {
        formMappingBarResourceProvider = spy(
                new FormMappingBarResourceProvider(customPageBarResourceBuilder, formsFolder, false));

        assertThrows(InternalFormNotFoundException.class,
                () -> formMappingBarResourceProvider.newFormMappingModel(builder, aPoolWithEmptyFormMappings()));
    }

    private Pool aPoolAndTaskWithAllTypeOfFormMappings() {
        return aPool().withName("Pool1").withVersion("1.0")
                .havingOverviewFormMapping(aFormMapping().withType(org.bonitasoft.bpm.model.process.FormMappingType.URL)
                        .withURL("http://www.bonitasoft.com"))
                .havingElements(
                        aTask().withName("Step1")
                                .havingFormMapping(aFormMapping().havingTargetForm(
                                        anExpression().withName("StepForm").withContent("step-form-id"))),
                        aTask().withName("Step2").havingFormMapping(
                                aFormMapping().withType(org.bonitasoft.bpm.model.process.FormMappingType.NONE)))
                .build();
    }

    private Pool aPoolWithEmptyFormMappings() {
        return aPool().withName("Pool1").withVersion("1.0")
                .havingFormMapping(aFormMapping().withType(org.bonitasoft.bpm.model.process.FormMappingType.INTERNAL)
                        .havingTargetForm(anExpression().withContent("")))
                .build();
    }

    private Pool aPoolWithEmptyOverviewInternalFormMappings() {
        return aPool().withName("Pool1").withVersion("1.0")
                .havingOverviewFormMapping(
                        aFormMapping().withType(org.bonitasoft.bpm.model.process.FormMappingType.INTERNAL)
                                .havingTargetForm(anExpression().withContent("")))
                .build();
    }

    private Pool aPoolWithInvalidFormMapping() {
        return aPool().withName("Pool2").withVersion("2.0")
                .havingOverviewFormMapping(
                        aFormMapping().withType(org.bonitasoft.bpm.model.process.FormMappingType.URL).withURL(""))
                .havingFormMapping(aFormMapping().havingTargetForm(anExpression().withContent(null)))
                .havingElements(aTask().withName("Step1").havingFormMapping(
                        aFormMapping().havingTargetForm(anExpression().withName("Step1").withContent("step-form-id"))))
                .build();
    }

}
