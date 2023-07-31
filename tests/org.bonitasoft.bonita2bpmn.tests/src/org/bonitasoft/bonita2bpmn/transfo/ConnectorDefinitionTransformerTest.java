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
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.bonitasoft.bonita2bpmn.transfo.data.DataScope;
import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.bpm.connector.model.definition.util.ConnectorDefinitionResourceFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.omg.spec.bpmn.model.ModelFactory;
import org.omg.spec.bpmn.model.TImport;

class ConnectorDefinitionTransformerTest {

    private Supplier<List<ConnectorDefinition>> connectorDefProvier;
    private ModelRegistry modelRegistry;

    @BeforeEach
    void setup(@TempDir Path tmpFolder) {
        connectorDefProvier = () -> List.of(simpleConnector("my-connector", "1.0", tmpFolder));
        modelRegistry = new ModelRegistry(ModelFactory.eINSTANCE.createTDefinitions(), mock(DataScope.class));
    }

    @Test
    void generateXsd(@TempDir Path tmpFolder) throws Exception {
        var transformer = new ConnectorDefinitionTransformer(tmpFolder,
                connectorDefProvier,
                ConnectorTransformationXSLProvider.DEFAULT,
                modelRegistry);

        transformer.handleBonitaConnectorDefinition("my-connector");

        Path location = tmpFolder.resolve("my-connector.xsd");
        assertThat(location).exists();
        TImport timport = modelRegistry.getDefinitions().getImport().get(0);
        assertThat(timport.getLocation())
                .isEqualTo(location.getParent().getFileName().toString() + "/" + location.getFileName().toString());
        assertThat(timport.getNamespace()).isEqualTo("http://www.bonitasoft.org/studio/connector/definition/6.1");
    }

    private static ConnectorDefinition simpleConnector(String id, String version, Path tmpFolder) {
        var definition = ConnectorDefinitionFactory.eINSTANCE.createConnectorDefinition();
        definition.setId(id);
        definition.setVersion(version);
        var resource = new ConnectorDefinitionResourceFactoryImpl()
                .createResource(URI.createFileURI(tmpFolder.resolve(tmpFolder + "-" + version + ".def").toString()));
        var root = ConnectorDefinitionFactory.eINSTANCE.createDocumentRoot();
        root.setConnectorDefinition(definition);
        resource.getContents().add(root);
        try {
            resource.save(Collections.emptyMap());
        } catch (IOException e) {
            fail(() -> e.getMessage());
        }
        return definition;
    }
}
