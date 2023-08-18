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
package org.bonitasoft.bonita2bar.process;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.bonita2bar.ProcessRegistry;
import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementationPackage;
import org.bonitasoft.bpm.model.process.MainProcess;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.util.migration.MigrationPolicy;
import org.bonitasoft.bpm.model.util.EnvironmentUtil;
import org.bonitasoft.bpm.model.util.ModelLoader;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmf.runtime.notation.NotationPackage;

public class ProcessRegistryImpl implements ProcessRegistry {

    private List<Pool> processes;
    private MigrationPolicy migrationPolicy;
    private List<Path> procFiles;

    public ProcessRegistryImpl(List<Path> procFiles, MigrationPolicy policy) {
        this.procFiles = procFiles;
        this.migrationPolicy = policy;
    }

    public ProcessRegistryImpl(Path diagramsFolder, MigrationPolicy policy) {
        this(listProceFiles(diagramsFolder), policy);
    }

    private static List<Path> listProceFiles(Path folder) {
        try (var files = Files.walk(folder)) {
            return files.filter(file -> file.getFileName().toString().endsWith(".proc")).collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public List<Pool> getProcesses() {
        if (processes == null) {
            if (!EnvironmentUtil.isOSGi()) {
                EPackage.Registry.INSTANCE.put(NotationPackage.eNS_URI, NotationPackage.eINSTANCE);
                EPackage.Registry.INSTANCE.put("http://www.bonitasoft.org/ns/connector/definition/6.1",
                        ConnectorDefinitionPackage.eINSTANCE);
                EPackage.Registry.INSTANCE.put(ConnectorDefinitionPackage.eNS_URI,
                        ConnectorDefinitionPackage.eINSTANCE);
                EPackage.Registry.INSTANCE.put(ConnectorImplementationPackage.eNS_URI,
                        ConnectorImplementationPackage.eINSTANCE);
            }
            processes = new ArrayList<>();
            procFiles.stream().filter(file -> file.getFileName().toString().endsWith(".proc")).forEach(procFile -> {
                var resource = ModelLoader.getInstance()
                        .loadModel(URI.createFileURI(procFile.toAbsolutePath().toString()), migrationPolicy);
                var mainProcess = resource.getContents().stream().filter(MainProcess.class::isInstance)
                        .map(MainProcess.class::cast).findFirst().orElseThrow(() -> new IllegalStateException(
                                String.format("No MainProcess found in file %s", procFile.toAbsolutePath())));

                mainProcess.getElements().stream()
                        .filter(Pool.class::isInstance)
                        .map(Pool.class::cast)
                        .forEach(processes::add);
            });
        }
        return processes;
    }

    @Override
    public Optional<Pool> getProcess(String name, String version) {
        return getProcesses().stream().filter(Pool.class::isInstance).map(Pool.class::cast)
                .filter(process -> Objects.equals(process.getName(), name))
                .filter(process -> Objects.equals(process.getVersion(), version)).findFirst();
    }

    @Override
    public MigrationPolicy getMigrationPolicy() {
        return migrationPolicy;
    }

}
