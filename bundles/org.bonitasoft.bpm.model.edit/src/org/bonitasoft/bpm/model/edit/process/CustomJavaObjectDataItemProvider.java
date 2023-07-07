/** 
 * Copyright (C) 2011 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.bpm.model.edit.process;

import java.util.List;
import java.util.Optional;

import org.bonitasoft.bpm.model.edit.ProcessEditPlugin;
import org.bonitasoft.bpm.model.edit.provider.BottomLeftDecoratedImage;
import org.bonitasoft.bpm.model.process.Data;
import org.bonitasoft.bpm.model.process.JavaObjectData;
import org.bonitasoft.bpm.model.process.provider.JavaObjectDataItemProvider;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
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
                String className = ((JavaObjectData) object).getClassName();
                if (className != null) {
                    try {
                        Optional<IJavaProject> javaProj = getJavaProjectIfPossible(
                                ((JavaObjectData) object).eResource());
                        IType t = javaProj.isPresent() ? javaProj.get().findType(className) : null;
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
                        // class loading failed, just use the default icon
                    }
                }
            }
        }
        return icon;
    }

    /**
     * Get the java project containing the model resource
     * 
     * @param eResource the model resource
     * @return the java project when there is one
     */
    private Optional<IJavaProject> getJavaProjectIfPossible(Resource eResource) {
        URI uri = eResource.getResourceSet().getURIConverter().normalize(eResource.getURI());
        if (uri.isFile()) {
            String fileString = uri.toFileString();
            IPath path = Path.fromOSString(fileString);
            IFile iFile = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path);
            return Optional.ofNullable(JavaCore.create(iFile.getProject()));
        } else if (uri.isPlatformResource()) {
            String platformString = uri.toPlatformString(true);
            IPath path = Path.fromPortableString(platformString);
            IFile iFile = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
            return Optional.ofNullable(JavaCore.create(iFile.getProject()));
        } else {
            return Optional.empty();
        }
    }

}
