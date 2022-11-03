/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

import java.util.List;

import org.bonitasoft.bpm.model.edit.ProcessEditPlugin;
import org.bonitasoft.bpm.model.edit.custom.provider.BottomLeftDecoratedImage;
import org.bonitasoft.bpm.model.process.Data;
import org.bonitasoft.bpm.model.process.JavaObjectData;
import org.bonitasoft.bpm.model.process.provider.JavaObjectDataItemProvider;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;

/**
 * @author Romain Bioteau
 */
public class CustomJavaObjectDataItemProvider extends JavaObjectDataItemProvider {

    public CustomJavaObjectDataItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public String getText(Object object) {
        Data d = (Data) object;
        return "Data " + d.getName();
    }

    @Override
    public Object getImage(Object object) {
        Object icon = super.getImage(object);
        if (object instanceof Data) {
            boolean formTransient = ((Data) object).getDatasourceId() != null
                    && ((Data) object).getDatasourceId().equals("PAGEFLOW");
            if (object instanceof JavaObjectData) {
                if (((JavaObjectData) object).getClassName() != null) {
                    try {
                        IType t = RepositoryManager.getInstance().getCurrentRepository().orElseThrow().getJavaProject()
                                .findType(((JavaObjectData) object).getClassName());
                        if (t != null && t.isInterface()) {
                            Object mainImage = ProcessEditPlugin.INSTANCE.getImage("int_obj.gif");
                            if (formTransient) {
                                Object formDeco = ProcessEditPlugin.INSTANCE.getImage("form_decorator.png");
                                return new BottomLeftDecoratedImage(List.of(mainImage, formDeco));
                            }
                            return mainImage;
                        } else {
                            Object mainImage = ProcessEditPlugin.INSTANCE.getImage("class_obj.gif");
                            if (formTransient) {
                                Object formDeco = ProcessEditPlugin.INSTANCE.getImage("form_decorator.png");
                                return new BottomLeftDecoratedImage(List.of(mainImage, formDeco));
                            }
                            return mainImage;
                        }
                    } catch (JavaModelException e) {

                    }
                }
            }
        }
        return icon;
    }

}
