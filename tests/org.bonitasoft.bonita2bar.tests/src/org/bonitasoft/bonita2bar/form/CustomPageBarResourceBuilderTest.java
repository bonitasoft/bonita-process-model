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
package org.bonitasoft.bonita2bar.form;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomPageBarResourceBuilderTest {

    @Mock
    private FormBuilder formBuilder;

    @Test
    void should_create_bar_resource_for_custompage() throws Exception {
        var customPageBarResourceBuilder = new CustomPageBarResourceBuilder(formBuilder);
        final BarResource processFormCustomPage = customPageBarResourceBuilder.newBarResource("Pool1--1.0--processForm",
                "process-form-id");
        final BarResource taskFormCustomPage = customPageBarResourceBuilder.newBarResource("Pool1--1.0--StepForm",
                "step-form-id");

        assertThat(processFormCustomPage.getName()).isEqualTo("customPages/Pool1--1.0--processForm.zip");
        assertThat(taskFormCustomPage.getName()).isEqualTo("customPages/Pool1--1.0--StepForm.zip");
        verify(formBuilder, times(2)).export(anyString());
    }
}
