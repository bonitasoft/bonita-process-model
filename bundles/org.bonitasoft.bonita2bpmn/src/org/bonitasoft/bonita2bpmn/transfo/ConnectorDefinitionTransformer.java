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

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.xml.namespace.QName;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.omg.spec.bpmn.model.ModelFactory;
import org.omg.spec.bpmn.model.TImport;
import org.omg.spec.bpmn.model.TInterface;
import org.omg.spec.bpmn.model.TItemDefinition;
import org.omg.spec.bpmn.model.TMessage;
import org.omg.spec.bpmn.model.TOperation;

public class ConnectorDefinitionTransformer {

    static final String XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION = "bonitaConnector";

    private Path connectorDefFolder;
    private Templates xslTemplate;
    private Supplier<List<ConnectorDefinition>> connectorDefContextProvider;
    private ConnectorTransformationXSLProvider connectorXSLProvider;
    private ModelRegistry modelRegistry;

    ConnectorDefinitionTransformer(Path connectorDefFolder,
            Supplier<List<ConnectorDefinition>> connectorDefContextProvider,
            ConnectorTransformationXSLProvider connectorXSLProvider, ModelRegistry modelRegistry) {
        this.connectorDefFolder = connectorDefFolder;
        this.connectorDefContextProvider = connectorDefContextProvider;
        this.connectorXSLProvider = connectorXSLProvider;
        this.modelRegistry = modelRegistry;
    }

    public String generateConnectorInputItemDef(final String connectorDefId) {
        return connectorDefId + "ConnectorInput";
    }

    public String generateConnectorOutputItemDef(final String connectorDefId) {
        return connectorDefId + "ConnectorOutput";
    }

    public void handleBonitaConnectorDefinition(final String connectorDefId) {
        try {
            Path connectorDefFile = createXSDForConnectorDef(connectorDefId);
            addConnectorDefInXsdIfNotYetIncluded(connectorDefFile);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to create xsd for connector.", e);
        }

        /* Handle input */
        final TMessage tMessageInputBonitaConnector = createConnectorDefInput(connectorDefId);
        /* handle output */
        final TMessage tMessageOutputBonitaConnector = createConnectorDefOutput(connectorDefId);
        /* Create interface with it operation */
        var interfaceId = connectorDefId + "_Bonita_Connector_Interface";
        modelRegistry.getDefinitions().getRootElement().stream().filter(TInterface.class::isInstance)
                .map(TInterface.class::cast).filter(i -> interfaceId.equals(i.getId())).findFirst()
                .ifPresentOrElse(id -> {
                }, () -> {
                    final TInterface tInterfaceBonitaConnector = ModelFactory.eINSTANCE.createTInterface();
                    tInterfaceBonitaConnector.setName(interfaceId);
                    tInterfaceBonitaConnector.setId(interfaceId);
                    final TOperation tOperationConnector = ModelFactory.eINSTANCE.createTOperation();
                    tOperationConnector.setId("Exec" + connectorDefId);
                    tOperationConnector.setName("Exec" + connectorDefId);
                    tOperationConnector.setInMessageRef(QName.valueOf(tMessageInputBonitaConnector.getId()));
                    tOperationConnector.setOutMessageRef(QName.valueOf(tMessageOutputBonitaConnector.getId()));
                    tInterfaceBonitaConnector.getOperation().add(tOperationConnector);
                    modelRegistry.getDefinitions().getRootElement().add(tInterfaceBonitaConnector);
                });
    }

    private Path generateXSDForConnector(Path connectorToTransformWC, Path xsdFile) throws IOException {
        if (xslTemplate == null) {
            final TransformerFactory transFact = TransformerFactory.newInstance();
            final File xsltFileoriginal = connectorXSLProvider.getConnectorXSLFile();
            final Source xsltSource = new StreamSource(xsltFileoriginal);
            try {
                xslTemplate = transFact.newTemplates(xsltSource);
            } catch (TransformerConfigurationException e) {
                throw new IOException(e);
            }
        }

        var content = Files.readString(connectorToTransformWC, StandardCharsets.UTF_8);
        content = content.replaceAll("xmlns:definition=\"http://www.bonitasoft.org/ns/connector/definition/6.1\"",
                "xmlns=\"http://www.bonitasoft.org/ns/connector/definition/6.1\" xmlns:definition=\"http://www.bonitasoft.org/ns/connector/definition/6.1\"");
        Files.writeString(connectorToTransformWC, content);

        final Source xmlSource = new StreamSource(connectorToTransformWC.toFile());
        if (!Files.exists(connectorDefFolder)) {
            Files.createDirectories(connectorDefFolder);
        }
        try (final OutputStream stream = Files.newOutputStream(xsdFile)) {
            final Result result = new StreamResult(stream);
            final Transformer transformer = xslTemplate.newTransformer();
            transformer.setParameter("indent", true);
            transformer.transform(xmlSource, result);
            return xsdFile;
        } catch (TransformerException e) {
            throw new IOException(e);
        } finally {
            Files.delete(connectorToTransformWC);
        }
    }

    private Path createXSDForConnectorDef(final String connectorDefId) throws IOException {
        /* Export the xsd */
        Path tmpDir = Files.createDirectories(Paths.get("2bpmnExport"));
        Path connectorDefFile = connectorDefContextProvider.get().stream()
                .filter(def -> Objects.equals(def.getId(), connectorDefId)).map(def -> {
                    try {
                        return createTmpDefinitionFile(def, tmpDir);
                    } catch (IOException e) {
                        modelRegistry.addError(e.getMessage());
                        return null;
                    }
                }).filter(Objects::nonNull).findFirst().orElse(null);
        try {
            if (connectorDefFile != null) {
                return generateXSDForConnector(connectorDefFile, connectorDefFolder.resolve(connectorDefId + ".xsd"));
            } else {
                modelRegistry.addError("The connector with id " + connectorDefId + " was not found.");
                return null;
            }
        } finally {
            deleteDir(tmpDir);
        }
    }

    private Path createTmpDefinitionFile(ConnectorDefinition definition, Path tmpDir) throws IOException {
        final Map<String, String> options = new HashMap<>();
        options.put(XMLResource.OPTION_ENCODING, "UTF-8");
        options.put(XMLResource.OPTION_XML_VERSION, "1.0");
        Path tmpFile = Files.createTempFile(tmpDir, definition.getId(), ".def");
        var resource = definition.eResource();
        var docRoot = (org.bonitasoft.bpm.connector.model.definition.DocumentRoot) resource.getContents().get(0);
        docRoot.getXMLNSPrefixMap().clear();
        try (OutputStream os = Files.newOutputStream(tmpFile)) {
            resource.save(os, options);
        }
        return tmpFile;
    }

    private void addConnectorDefInXsdIfNotYetIncluded(Path connectorDefFile) {
        if (connectorDefFile != null) {
            boolean alreadyImported = false;
            final String locationImport = connectorDefFolder.getFileName() + "/"
                    + connectorDefFile.getFileName().toString();
            for (final TImport imported : modelRegistry.getDefinitions().getImport()) {
                if (imported.getLocation().equals(locationImport)) {
                    alreadyImported = true;
                    break;
                }
            }
            if (!alreadyImported) {
                final TImport tImportBonitaConnector = ModelFactory.eINSTANCE.createTImport();
                tImportBonitaConnector.setImportType("http://www.w3.org/2001/XMLSchema");
                tImportBonitaConnector.setLocation(locationImport);
                tImportBonitaConnector.setNamespace("http://www.bonitasoft.org/studio/connector/definition/6.1");
                modelRegistry.getDefinitions().getImport().add(tImportBonitaConnector);
            }
        }
    }

    private TMessage createConnectorDefOutput(final String connectorDefId) {
        final String itemDefConnectorOutput = generateConnectorOutputItemDef(connectorDefId);
        modelRegistry.getDefinitions().getRootElement().stream().filter(e -> itemDefConnectorOutput.equals(e.getId()))
                .findFirst().ifPresentOrElse(e -> {
                }, () -> {
                    final TItemDefinition tItemDefinitionBonitaConnectorOutput = ModelFactory.eINSTANCE
                            .createTItemDefinition();
                    tItemDefinitionBonitaConnectorOutput
                            .setStructureRef(QName.valueOf(XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION + ":"
                                    + connectorDefId + "OutputType"));

                    tItemDefinitionBonitaConnectorOutput.setId(itemDefConnectorOutput);
                    modelRegistry.getDefinitions().getRootElement().add(tItemDefinitionBonitaConnectorOutput);
                });

        var outputId = connectorDefId + "ConnectorMessageOutput";
        return modelRegistry.getDefinitions().getRootElement().stream().filter(TMessage.class::isInstance)
                .map(TMessage.class::cast).filter(e -> outputId.equals(e.getId())).findFirst().orElseGet(() -> {
                    final TMessage tMessageOutputBonitaConnector = ModelFactory.eINSTANCE.createTMessage();
                    tMessageOutputBonitaConnector.setItemRef(QName.valueOf(itemDefConnectorOutput));
                    tMessageOutputBonitaConnector.setId(outputId);
                    modelRegistry.getDefinitions().getRootElement().add(tMessageOutputBonitaConnector);
                    return tMessageOutputBonitaConnector;
                });

    }

    private TMessage createConnectorDefInput(final String connectorDefId) {
        final String itemDefConnectorInput = generateConnectorInputItemDef(connectorDefId);
        modelRegistry.getDefinitions().getRootElement().stream().filter(e -> itemDefConnectorInput.equals(e.getId()))
                .findFirst().ifPresentOrElse(e -> {
                }, () -> {
                    final TItemDefinition tItemDefinitionBonitaConnectorInput = ModelFactory.eINSTANCE
                            .createTItemDefinition();
                    tItemDefinitionBonitaConnectorInput
                            .setStructureRef(QName.valueOf(XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION + ":"
                                    + connectorDefId + "InputType"));
                    tItemDefinitionBonitaConnectorInput.setId(itemDefConnectorInput);
                    modelRegistry.getDefinitions().getRootElement().add(tItemDefinitionBonitaConnectorInput);
                });
        var id = connectorDefId + "ConnectorMessageInput";
        return modelRegistry.getDefinitions().getRootElement().stream().filter(TMessage.class::isInstance)
                .map(TMessage.class::cast).filter(e -> id.equals(e.getId())).findFirst().orElseGet(() -> {
                    final TMessage tMessageInputBonitaConnector = ModelFactory.eINSTANCE.createTMessage();
                    tMessageInputBonitaConnector.setItemRef(QName.valueOf(itemDefConnectorInput));
                    tMessageInputBonitaConnector.setId(id);
                    modelRegistry.getDefinitions().getRootElement().add(tMessageInputBonitaConnector);
                    return tMessageInputBonitaConnector;
                });
    }

    private static void deleteDir(Path directory) throws IOException {
        try (Stream<Path> pathStream = Files.walk(directory)) {
            pathStream.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
        }
    }
}
