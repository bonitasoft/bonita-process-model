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
package org.bonitasoft.bpm.model.edit.process;

import org.bonitasoft.bpm.model.process.MainProcess;
import org.bonitasoft.bpm.model.process.provider.MainProcessItemProvider;
import org.eclipse.emf.common.notify.AdapterFactory;

/**
 * @author Romain Bioteau
 */
public class CustomMainProcessItemProvider extends MainProcessItemProvider {

    public CustomMainProcessItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public String getText(Object object) {
        MainProcess p = (MainProcess) object;
        return p.getName() + " (" + p.getVersion() + ")";
    }

}
