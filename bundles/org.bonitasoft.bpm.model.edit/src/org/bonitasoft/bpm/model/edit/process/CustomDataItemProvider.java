/**
 * Copyright (C) 2011 Bonitasoft S.A.
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

import java.util.List;

import org.bonitasoft.bpm.model.edit.ProcessEditPlugin;
import org.bonitasoft.bpm.model.edit.provider.BottomLeftDecoratedImage;
import org.bonitasoft.bpm.model.process.Data;
import org.bonitasoft.bpm.model.process.provider.DataItemProvider;
import org.eclipse.emf.common.notify.AdapterFactory;

/**
 * @author Romain Bioteau
 */
public class CustomDataItemProvider extends DataItemProvider {

    public CustomDataItemProvider(final AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public String getText(final Object object) {
        final Data d = (Data) object;
        return "Data " + d.getName();
    }

    @Override
    public Object getImage(final Object object) {
        final Object icon = super.getImage(object);
        if (object instanceof Data && icon != null) {
            final boolean formTransient = ((Data) object).getDatasourceId() != null
                    && ((Data) object).getDatasourceId().equals("PAGEFLOW");
            if (formTransient) {
                Object formDeco = ProcessEditPlugin.INSTANCE.getImage("form_decorator.png");
                return new BottomLeftDecoratedImage(List.of(icon, formDeco));
            }
        }
        return icon;
    }

}
