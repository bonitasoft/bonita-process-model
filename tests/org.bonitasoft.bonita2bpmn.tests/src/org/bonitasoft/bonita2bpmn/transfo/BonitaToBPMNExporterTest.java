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
package org.bonitasoft.bonita2bpmn.transfo;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;

import org.bonitasoft.bonita2bpmn.extension.BonitaModelExporterImpl;
import org.bonitasoft.bpm.model.process.util.migration.MigrationPolicy;
import org.bonitasoft.bpm.model.util.IModelSearch;
import org.bonitasoft.bpm.model.util.ModelLoader;
import org.bonitasoft.bpm.model.util.ModelSearch;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BonitaToBPMNExporterTest {

    private BonitaToBPMNExporter bonitaToBPMNExporter;
    private IModelSearch modelSearch;
    private File destFile;

    @BeforeEach
    void setup(@TempDir Path outputFolder) throws Exception {
        destFile = outputFolder.resolve("process.bpmmn").toFile();
        bonitaToBPMNExporter = new BonitaToBPMNExporter();
        modelSearch = new ModelSearch(Collections::emptyList);
    }

    @ParameterizedTest
    @CsvSource(value = { "/diagramToTestConnectorBPMNImportExport-1.0.proc",
            "/diagramWithEventSubProcess-1.0.proc",
            "/MessageDataTestValue-1.0.proc",
            "/MyDiagramToTestDefaultFlowInBPMN-1.0.proc",
            "/Request_For_Advance_Payment-6.0.proc",
            "/testBPMNDataMapping-1.0.proc",
            "/TestExportToBPMNDiagram_1_0.proc" })
    void exportProcToBpmn(String procResource) throws Exception {

        var resource = ModelLoader.create().withPolicy(MigrationPolicy.SOFT_MIGRATE_POLICY)
                .loadModel(createURI(procResource));

        bonitaToBPMNExporter.export(new BonitaModelExporterImpl(resource, modelSearch), modelSearch,
                Collections::emptyList, destFile, ConnectorTransformationXSLProvider.DEFAULT, "");

        assertThat(destFile).exists();
    }

    private static URI createURI(String resourcePath) throws IOException {
        return URI.createFileURI(
                new File(FileLocator.toFileURL(BonitaToBPMNExporterTest.class.getResource(resourcePath)).getFile())
                        .getAbsolutePath());
    }
}
