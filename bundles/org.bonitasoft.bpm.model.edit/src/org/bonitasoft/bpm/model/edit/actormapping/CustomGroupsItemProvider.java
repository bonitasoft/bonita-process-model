/**
 * Copyright (C) 2009-2011 Bonitasoft S.A.
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
package org.bonitasoft.bpm.model.edit.actormapping;

import java.util.Collection;

import org.bonitasoft.bpm.model.actormapping.Groups;
import org.bonitasoft.bpm.model.actormapping.provider.GroupsItemProvider;
import org.eclipse.emf.common.notify.AdapterFactory;

public class CustomGroupsItemProvider extends GroupsItemProvider {

    public CustomGroupsItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public Collection<?> getChildren(Object object) {
        return ((Groups) object).getGroup();
    }

}
