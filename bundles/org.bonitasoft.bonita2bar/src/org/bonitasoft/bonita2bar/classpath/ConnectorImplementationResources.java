/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.bonita2bar.classpath;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.engine.bpm.bar.BarResource;

public class ConnectorImplementationResources {

    private BarResource implemetation;

    List<BarResource> dependencies = new ArrayList<>();

    public BarResource getImplemetation() {
        return implemetation;
    }

    public void setImplemetation(BarResource implemetation) {
        this.implemetation = implemetation;
    }

    public void addDependency(BarResource dependency) {
        dependencies.add(dependency);
    }

    public List<BarResource> getDependencies() {
        return dependencies;
    }
}
