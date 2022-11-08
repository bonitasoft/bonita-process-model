/**
 * Copyright (C) 2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.bpm.model.edit.custom.process;


import static org.junit.Assert.assertTrue;

import org.bonitasoft.bpm.model.edit.custom.i18n.Messages;
import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.expression.ExpressionFactory;
import org.bonitasoft.bpm.model.process.FormMapping;
import org.bonitasoft.bpm.model.process.FormMappingType;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.bpm.model.process.provider.ProcessItemProviderAdapterFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class CustomFormMappingItemProviderTest {

    private CustomFormMappingItemProvider customFormMappingItemProvider;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        customFormMappingItemProvider = new CustomFormMappingItemProvider(new ProcessItemProviderAdapterFactory());
    }

    @Test
    public void should_display_internal_mode_form_mapping() throws Exception {
        //Given
    	final Expression expression = ExpressionFactory.eINSTANCE.createExpression();
    	expression.setName("Step1 form");
        final FormMapping internalFormMapping = ProcessFactory.eINSTANCE.createFormMapping();
        internalFormMapping.setType(FormMappingType.INTERNAL);
        internalFormMapping.setTargetForm(expression);

        //When
        final String text = customFormMappingItemProvider.getText(internalFormMapping);

        //The
        assertTrue(text.equals("Form Mapping INTERNAL"));
    }

    @Test
    public void should_display_url_for_url_form_mapping() throws Exception {
        //Given
        final FormMapping urlFormMapping = ProcessFactory.eINSTANCE.createFormMapping();
        urlFormMapping.setType(FormMappingType.URL);
        urlFormMapping.setUrl("http://www.myUrl.com");
        
        //When
        final String text = customFormMappingItemProvider.getText(urlFormMapping);

        //Then
        assertTrue(text.equals(Messages.bind(Messages.urlFormMapping, "http://www.myUrl.com")));
    }

    @Test
    public void should_display_when_url_form_mapping_is_for_overview() throws Exception {
        //Given
        final Pool pool = ProcessFactory.eINSTANCE.createPool();
        final FormMapping overviewFormMapping = ProcessFactory.eINSTANCE.createFormMapping();
        overviewFormMapping.setType(FormMappingType.URL);
        overviewFormMapping.setUrl("http://www.myUrl.com");
        pool.setOverviewFormMapping(overviewFormMapping);

        //When
        final String text = customFormMappingItemProvider.getText(pool.getOverviewFormMapping());

        //Then
        assertTrue(text.equals(Messages.bind(Messages.overviewUrlFormMapping, "http://www.myUrl.com")));
    }

}
