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
package org.bonitasoft.bonita2bar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.bpm.model.process.MainProcess;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.util.EnvironmentUtil;
import org.bonitasoft.bpm.model.util.ModelLoader;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmf.runtime.notation.NotationPackage;

public interface ProcessRegistry {

    List<AbstractProcess> getProcesses();

    Optional<Pool> getProcess(String name, String version);

    /**
     * {@link ProcessRegistry} factory method.
     * 
     * @param diagramsFolder The {@link Path} on the filesystem containing proc files
     * @return A default implementation of {@link ProcessRegistry} that loads all the processes from the proc files found in
     *         diagramsFolder. Once loaded, the processes are cached in memory.
     */
    static ProcessRegistry of(Path diagramsFolder) {
        return new ProcessRegistry() {

            private List<AbstractProcess> processes;

            @Override
            public List<AbstractProcess> getProcesses() {
                if (processes == null) {
                    if (!EnvironmentUtil.isOSGi()) {
                        EPackage.Registry.INSTANCE.put(NotationPackage.eNS_URI, NotationPackage.eINSTANCE);
                    }
                    processes = new ArrayList<>();
                    try (var files = Files.walk(diagramsFolder)) {
                        files.filter(file -> file.getFileName().toString().endsWith(".proc")).forEach(procFile -> {
                            var resource = ModelLoader.getInstance()
                                    .loadModel(URI.createFileURI(procFile.toAbsolutePath().toString()));
                            final EList<EObject> contents = resource.getContents();
                            if (resource.getContents().isEmpty()
                                    || !(resource.getContents().get(0) instanceof MainProcess)) {
                                throw new IllegalStateException(
                                        String.format("No MainProcess found in file %s", procFile.toAbsolutePath()));
                            }
                            var mainProcess = (MainProcess) contents.get(0);
                            mainProcess.getElements().stream().filter(AbstractProcess.class::isInstance)
                                    .map(AbstractProcess.class::cast).forEach(processes::add);
                        });
                    } catch (IOException e) {
                        throw new IllegalArgumentException(e);
                    }
                }
                return processes;
            }

            @Override
            public Optional<Pool> getProcess(String name, String version) {
                return getProcesses().stream().filter(Pool.class::isInstance).map(Pool.class::cast)
                        .filter(process -> Objects.equals(process.getName(), name))
                        .filter(process -> Objects.equals(process.getVersion(), version)).findFirst();
            }
        };
    }

}
