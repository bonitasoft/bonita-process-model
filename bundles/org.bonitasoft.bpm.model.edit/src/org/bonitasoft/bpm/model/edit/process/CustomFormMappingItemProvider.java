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
package org.bonitasoft.bpm.model.edit.process;

import org.bonitasoft.bpm.model.edit.i18n.Messages;
import org.bonitasoft.bpm.model.process.FormMapping;
import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.bpm.model.process.provider.FormMappingItemProvider;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.osgi.util.NLS;

/**
 * @author Romain Bioteau
 */
public class CustomFormMappingItemProvider extends FormMappingItemProvider {

    public CustomFormMappingItemProvider(final AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public String getText(final Object object) {
        final FormMapping mapping = (FormMapping) object;
        switch (mapping.getType()) {
            case URL:
                final String url = mapping.getUrl();
                return isOverview(mapping) ? NLS.bind(Messages.overviewUrlFormMapping, url)
                        :  NLS.bind(Messages.urlFormMapping, url);
            case INTERNAL:
            default:
                return super.getText(object);
        }
    }

    private boolean isOverview(final FormMapping mapping) {
        return ProcessPackage.Literals.RECAP_FLOW__OVERVIEW_FORM_MAPPING.equals(mapping.eContainingFeature());
    }

}
