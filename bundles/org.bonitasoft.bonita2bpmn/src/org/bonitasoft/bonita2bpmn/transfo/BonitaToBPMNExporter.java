/**
 * Copyright (C) 2010-2012 Bonitasoft S.A.
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
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.xml.namespace.QName;

import org.bonitasoft.bonita2bpmn.extension.IBonitaModelExporter;
import org.bonitasoft.bonita2bpmn.transfo.data.DataIOTransformer;
import org.bonitasoft.bonita2bpmn.transfo.data.DataScope;
import org.bonitasoft.bonita2bpmn.transfo.data.ItemDefinitionFunction;
import org.bonitasoft.bonita2bpmn.transfo.data.XMLNamespaceResolver;
import org.bonitasoft.bonita2bpmn.transfo.expression.FormalExpressionFunctionFactory;
import org.bonitasoft.bonita2bpmn.util.Strings;
import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.process.AbstractTimerEvent;
import org.bonitasoft.bpm.model.process.Activity;
import org.bonitasoft.bpm.model.process.Actor;
import org.bonitasoft.bpm.model.process.BoundaryEvent;
import org.bonitasoft.bpm.model.process.BoundaryMessageEvent;
import org.bonitasoft.bpm.model.process.BoundarySignalEvent;
import org.bonitasoft.bpm.model.process.BoundaryTimerEvent;
import org.bonitasoft.bpm.model.process.CatchLinkEvent;
import org.bonitasoft.bpm.model.process.Data;
import org.bonitasoft.bpm.model.process.DataAware;
import org.bonitasoft.bpm.model.process.Element;
import org.bonitasoft.bpm.model.process.FlowElement;
import org.bonitasoft.bpm.model.process.IntermediateErrorCatchEvent;
import org.bonitasoft.bpm.model.process.Lane;
import org.bonitasoft.bpm.model.process.LinkEvent;
import org.bonitasoft.bpm.model.process.MainProcess;
import org.bonitasoft.bpm.model.process.MessageFlow;
import org.bonitasoft.bpm.model.process.NonInterruptingBoundaryTimerEvent;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.SequenceFlow;
import org.bonitasoft.bpm.model.process.SignalEvent;
import org.bonitasoft.bpm.model.process.SubProcessEvent;
import org.bonitasoft.bpm.model.process.TextAnnotation;
import org.bonitasoft.bpm.model.process.TextAnnotationAttachment;
import org.bonitasoft.bpm.model.process.ThrowLinkEvent;
import org.bonitasoft.bpm.model.process.XMLData;
import org.bonitasoft.bpm.model.process.XMLType;
import org.bonitasoft.bpm.model.process.util.DataUtil;
import org.bonitasoft.bpm.model.util.IModelSearch;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.ElementHandlerImpl;
import org.omg.spec.bpmn.di.BPMNEdge;
import org.omg.spec.bpmn.di.BPMNPlane;
import org.omg.spec.bpmn.di.BPMNShape;
import org.omg.spec.bpmn.di.DiFactory;
import org.omg.spec.bpmn.model.DocumentRoot;
import org.omg.spec.bpmn.model.ModelFactory;
import org.omg.spec.bpmn.model.TActivity;
import org.omg.spec.bpmn.model.TAssignment;
import org.omg.spec.bpmn.model.TAssociation;
import org.omg.spec.bpmn.model.TBaseElement;
import org.omg.spec.bpmn.model.TBoundaryEvent;
import org.omg.spec.bpmn.model.TCollaboration;
import org.omg.spec.bpmn.model.TComplexGateway;
import org.omg.spec.bpmn.model.TDataInput;
import org.omg.spec.bpmn.model.TDataInputAssociation;
import org.omg.spec.bpmn.model.TDataObject;
import org.omg.spec.bpmn.model.TDefinitions;
import org.omg.spec.bpmn.model.TDocumentation;
import org.omg.spec.bpmn.model.TErrorEventDefinition;
import org.omg.spec.bpmn.model.TExclusiveGateway;
import org.omg.spec.bpmn.model.TFlowElement;
import org.omg.spec.bpmn.model.TFormalExpression;
import org.omg.spec.bpmn.model.TInclusiveGateway;
import org.omg.spec.bpmn.model.TInputOutputSpecification;
import org.omg.spec.bpmn.model.TInputSet;
import org.omg.spec.bpmn.model.TItemDefinition;
import org.omg.spec.bpmn.model.TLane;
import org.omg.spec.bpmn.model.TLaneSet;
import org.omg.spec.bpmn.model.TLinkEventDefinition;
import org.omg.spec.bpmn.model.TMessageFlow;
import org.omg.spec.bpmn.model.TOutputSet;
import org.omg.spec.bpmn.model.TParticipant;
import org.omg.spec.bpmn.model.TProcess;
import org.omg.spec.bpmn.model.TProperty;
import org.omg.spec.bpmn.model.TSequenceFlow;
import org.omg.spec.bpmn.model.TSignal;
import org.omg.spec.bpmn.model.TSignalEventDefinition;
import org.omg.spec.bpmn.model.TSubProcess;
import org.omg.spec.bpmn.model.TText;
import org.omg.spec.bpmn.model.TTextAnnotation;
import org.omg.spec.bpmn.model.TTimerEventDefinition;
import org.omg.spec.bpmn.model.util.ModelResourceFactoryImpl;
import org.omg.spec.dd.dc.Bounds;

@Creatable
public class BonitaToBPMNExporter {

    private static final String JAVA_XMLNS = "java";
    private final Map<Element, TFlowElement> mapping = new HashMap<>();
    private final Map<Actor, TParticipant> participantMapping = new HashMap<>();
    private BPMNPlane bpmnPlane;
    private TCollaboration collaboration;
    private DocumentRoot root;
    private XMLNamespaceResolver xmlNamespaceResolver;
    private final FormalExpressionFunctionFactory formalExpressionTransformerFactory = new FormalExpressionFunctionFactory();
    private IModelSearch modelSearch;
    private MultiStatus status;
    private TFlowElementSwitch flowElementSwitch;
    private ModelRegistry modelRegistry;

    public void export(final IBonitaModelExporter modelExporter, IModelSearch modelSearch,
            Supplier<List<ConnectorDefinition>> connectorDefContextProvider, final File destFile,
            ConnectorTransformationXSLProvider connectorXSLProvider, String currentVersion) {
        this.modelSearch = modelSearch;
        final MainProcess mainProcess = modelExporter.getMainProcess();

        initializeDocumentRoot();

        var definitions = ModelFactory.eINSTANCE.createTDefinitions();
        definitions.setExpressionLanguage("http://groovy.apache.org/");
        collaboration = ModelFactory.eINSTANCE.createTCollaboration();
        setCommonAttributes(mainProcess, collaboration);
        definitions.getRootElement().add(collaboration);
        var bpmnDiagram = DiFactory.eINSTANCE.createBPMNDiagram();
        bpmnDiagram.setName(mainProcess.getName());
        bpmnPlane = DiFactory.eINSTANCE.createBPMNPlane();
        bpmnDiagram.setBPMNPlane(bpmnPlane);
        final QName rootQNameIDValue = QName.valueOf(collaboration.getId());
        bpmnPlane.setBpmnElement(rootQNameIDValue);
        bpmnPlane.setId("plane_" + collaboration.getId());
        definitions.getBPMNDiagram().add(bpmnDiagram);
        definitions.setTargetNamespace("http://bonitasoft.com/" + rootQNameIDValue);
        definitions.setExporter("BonitaSoft");
        definitions.setExporterVersion(currentVersion);
        var dataScope = new DataScope(new ItemDefinitionFunction(definitions, xmlNamespaceResolver, modelSearch));
        modelRegistry = new ModelRegistry(definitions, dataScope);
        modelRegistry.addError("Forms and other resources are not exported.");
        var connectorDefinitionTransformer = new ConnectorDefinitionTransformer(
                destFile.toPath().getParent().resolve("connectorDefs"), connectorDefContextProvider,
                connectorXSLProvider, modelRegistry);
        configureNamespaces();
        flowElementSwitch = new TFlowElementSwitch(modelSearch, connectorDefinitionTransformer,
                formalExpressionTransformerFactory, modelRegistry);

        BPMNShapeFactory shapeFactory = new BPMNShapeFactory(modelExporter, bpmnDiagram);
        modelExporter.getPools().stream().forEach(pool -> processPool(shapeFactory, pool, definitions, collaboration));

        populateWithMessageFlow(mainProcess);
        populateWithSignals(definitions);
        handleLinkEvents();
        handleMessageEvents();

        root.setDefinitions(definitions);
        final ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(".bpmn",
                new ModelResourceFactoryImpl());
        if (destFile.exists()) {
            try {
                Files.delete(destFile.toPath());
            } catch (IOException e) {
                status = createErrorStatus(e);
                return;
            }
        }
        Resource resource = new org.omg.spec.bpmn.di.util.DiResourceFactoryImpl()
                .createResource(URI.createFileURI(destFile.getAbsolutePath()));
        resourceSet.getResources().add(resource);
        resource.getContents().add(root);
        try {
            final Map<Object, Object> saveOptions = new HashMap<>();
            saveOptions.put(XMLResource.OPTION_ELEMENT_HANDLER, new ElementHandlerImpl(false));
            saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
            resource.save(saveOptions);

            status = createOKStatus();
        } catch (final Exception ex) {
            status = createErrorStatus(ex);
        } finally {
            resource.unload();
        }
    }

    private void handleMessageEvents() {
        modelRegistry.getMessages().stream().map(event -> {
            var message = ModelFactory.eINSTANCE.createTMessageEventDefinition();
            message.setId(event);
            return message;
        }).forEach(modelRegistry.getDefinitions().getRootElement()::add);
    }

    private void handleLinkEvents() {
        for (Entry<LinkEvent, TLinkEventDefinition> entry : modelRegistry.getLinkEvents().entrySet()) {
            if (entry.getKey() instanceof ThrowLinkEvent) {
                ThrowLinkEvent throwLink = (ThrowLinkEvent) entry.getKey();
                var eventDefinition = entry.getValue();
                if (throwLink.getTo() != null) {
                    eventDefinition.setName(Strings.slugify(throwLink.getTo().getName()));
                }
            } else if (entry.getKey() instanceof CatchLinkEvent) {
                CatchLinkEvent catchLink = (CatchLinkEvent) entry.getKey();
                var eventDefinition = entry.getValue();
                for (final ThrowLinkEvent from : catchLink.getFrom()) {
                    var sourceEvent = modelRegistry.getLinkEvents().get(from);
                    eventDefinition.getSource().add(QName.valueOf(sourceEvent.getId()));
                }
            }
        }
    }

    private void configureNamespaces() {
        root.getXMLNSPrefixMap().put(JAVA_XMLNS, "http://jcp.org/en/jsr/detail?id=270");
        root.getXMLNSPrefixMap().put(
                ConnectorDefinitionTransformer.XMLNS_HTTP_BONITASOFT_COM_BONITA_CONNECTOR_DEFINITION,
                "http://www.bonitasoft.org/studio/connector/definition/6.0");
        /*
         * Two lines only for Bruce validation tools conformance... but it currently
         * doesn't work either...
         */
        root.getXMLNSPrefixMap().put("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        root.getXSISchemaLocation().put("schemaLocation",
                "http://www.omg.org/spec/BPMN/20100524/MODEL schemas/BPMN20.xsd");// http://www.omg.org/spec/BPMN/20100501/BPMN20.xsd
    }

    private void initializeDocumentRoot() {
        root = ModelFactory.eINSTANCE.createDocumentRoot();
        xmlNamespaceResolver = new XMLNamespaceResolver(root);
    }

    private void setCommonAttributes(final Element bonitaElement, final TBaseElement bpmnElement) {
        bpmnElement.setId(modelSearch.getEObjectID(bonitaElement));
        if (bpmnElement instanceof TFlowElement) {
            ((TFlowElement) bpmnElement).setName(bonitaElement.getName());
        } else if (bpmnElement instanceof TProcess) {
            ((TProcess) bpmnElement).setName(bonitaElement.getName());
        } else if (bpmnElement instanceof TParticipant) {
            ((TParticipant) bpmnElement).setName(bonitaElement.getName());
        }
        final String documentation = bonitaElement.getDocumentation();
        if (documentation != null && !documentation.isEmpty()) {
            final TDocumentation doc = ModelFactory.eINSTANCE.createTDocumentation();
            FeatureMapUtil.addCDATA(doc.getMixed(), documentation);
            bpmnElement.getDocumentation().add(doc);
        }
    }

    private void populateWithMessageFlow(final MainProcess mainProcess) {
        for (final MessageFlow messageFlow : mainProcess.getMessageConnections()) {
            final TMessageFlow bpmnMessageFlow = ModelFactory.eINSTANCE.createTMessageFlow();
            setCommonAttributes(messageFlow, bpmnMessageFlow);
            final TFlowElement source = mapping.get(messageFlow.getSource());
            bpmnMessageFlow.setSourceRef(QName.valueOf(source.getId()));
            final TFlowElement target = mapping.get(messageFlow.getTarget());
            bpmnMessageFlow.setTargetRef(QName.valueOf(target.getId()));
            collaboration.getMessageFlow().add(bpmnMessageFlow);
        }
    }

    private void processPool(BPMNShapeFactory shapeFactory, Pool pool, TDefinitions definitions,
            final TCollaboration collaboration) {
        // create semantic process
        final TProcess bpmnProcess = ModelFactory.eINSTANCE.createTProcess();
        setCommonAttributes(pool, bpmnProcess);
        definitions.getRootElement().add(bpmnProcess);
        final TParticipant participant = ModelFactory.eINSTANCE.createTParticipant();
        participant.setProcessRef(QName.valueOf(bpmnProcess.getId()));
        participant.setName(bpmnProcess.getName());// should be done after populate test Common attributes
        participant.setId(EcoreUtil.generateUUID());
        collaboration.getParticipant().add(participant);

        for (final Actor actor : pool.getActors()) {
            final TParticipant actorParticipant = ModelFactory.eINSTANCE.createTParticipant();
            setCommonAttributes(actor, actorParticipant);
            collaboration.getParticipant().add(actorParticipant);
            participantMapping.put(actor, actorParticipant);
        }

        // create graphic process
        BPMNShape processShape = shapeFactory.createPool(participant.getId(), pool);
        bpmnPlane.getDiagramElement().add(processShape);

        populateWithData(pool, bpmnProcess);// create data before in order to have them accessible after
        populate(shapeFactory, pool, modelSearch.getAllItemsOfType(pool, Lane.class), bpmnProcess,
                processShape.getBounds());
        populateWithSequenceFlow(shapeFactory, pool, bpmnProcess);
        populateWithTextAnnotation(shapeFactory, pool, bpmnProcess, processShape.getBounds());
    }

    private void populateWithTextAnnotation(final BPMNShapeFactory shapeFactory, final Pool pool,
            final TProcess bpmnProcess, Bounds processBounds) {
        bpmnProcess.getArtifact()
                .addAll(modelSearch.getAllItemsOfType(pool, TextAnnotation.class).stream()
                        .map(source -> createAnnotation(shapeFactory, pool, bpmnProcess, processBounds, source))
                        .collect(Collectors.toList()));
    }

    private TTextAnnotation createAnnotation(final BPMNShapeFactory shapeFactory, final Pool pool,
            final TProcess bpmnProcess, Bounds processBounds, TextAnnotation source) {
        final TTextAnnotation annotation = ModelFactory.eINSTANCE.createTTextAnnotation();
        final TText text = ModelFactory.eINSTANCE.createTText();
        FeatureMapUtil.addCDATA(text.getMixed(), source.getText());
        annotation.setText(text);
        annotation.setId(EcoreUtil.generateUUID());
        final List<TextAnnotationAttachment> textAnnotationAttachments = modelSearch.getAllItemsOfType(pool,
                TextAnnotationAttachment.class);
        EObject eContainer = source.eContainer() instanceof SubProcessEvent
                ? source.eContainer().eContainer()
                : source.eContainer();
        var containerId = modelSearch.getEObjectID(eContainer);
        var containerBounds = bpmnPlane.getDiagramElement().stream().filter(BPMNShape.class::isInstance)
                .map(BPMNShape.class::cast)
                .filter(shape -> Objects.equals(shape.getBpmnElement().getLocalPart(), containerId))
                .findFirst().map(BPMNShape::getBounds).orElseGet(() -> processBounds);

        BPMNShape elementShape = shapeFactory.create(source, annotation.getId(), containerBounds);
        bpmnPlane.getDiagramElement().add(elementShape);

        for (final TextAnnotationAttachment attachement : textAnnotationAttachments) {
            if (Objects.equals(attachement.getSource(), source)) {
                final TFlowElement tFlowElement = mapping.get(attachement.getTarget());
                if (tFlowElement != null && Strings.hasText(tFlowElement.getId())) {
                    final TAssociation association = ModelFactory.eINSTANCE.createTAssociation();
                    association.setId(EcoreUtil.generateUUID());
                    association.setSourceRef(QName.valueOf(annotation.getId()));
                    association.setTargetRef(QName.valueOf(mapping.get(attachement.getTarget()).getId()));
                    bpmnProcess.getArtifact().add(association);

                    final BPMNEdge edge = shapeFactory.createBPMNEdge(association.getId(), attachement);
                    if (edge != null) {
                        bpmnPlane.getDiagramElement().add(edge);
                    }
                }
            }
        }
        return annotation;
    }

    private void populateWithSignals(final TDefinitions definitions) {
        definitions.getRootElement().addAll(modelRegistry.getSignals());
    }

    private void populateWithData(final Pool pool, final TProcess bpmnProcess) {
        var dataScope = modelRegistry.getDataScope();
        dataScope.initializeContext(pool);
        for (var bonitaData : pool.getData()) {
            /* Create the itemDefinition */
            final TItemDefinition dataItemDefinition = dataScope.get(bonitaData);
            final QName dataItemDefinitionIdAsQname = QName.valueOf(dataItemDefinition.getId());

            /* Add the dataObject using the reference */
            var dataObject = createDataObject(bpmnProcess, bonitaData, dataItemDefinitionIdAsQname);

            /* Define required data on the process, facultative? */
            final TDataInput tDataInput = DataIOTransformer.fillIOSpecification(bpmnProcess,
                    dataItemDefinitionIdAsQname);

            /* Add default value as input data on the process */
            final Expression defaultValue = bonitaData.getDefaultValue();
            if (defaultValue != null) {
                final TDataInputAssociation tDataInputAssociation = createDataInputAssociation(tDataInput,
                        defaultValue);
                dataObject.setItemSubjectRef(dataItemDefinitionIdAsQname);
                tDataInputAssociation.getAnyAttribute();
            }
        }

        TInputOutputSpecification ioSpecification = bpmnProcess.getIoSpecification();
        if (ioSpecification == null) {
            ioSpecification = ModelFactory.eINSTANCE.createTInputOutputSpecification();
            ioSpecification.setId(EcoreUtil.generateUUID());
            bpmnProcess.setIoSpecification(ioSpecification);
        }
        if (ioSpecification.getInputSet().isEmpty()) {
            final TInputSet createTInputSet = ModelFactory.eINSTANCE.createTInputSet();
            createTInputSet.setId(EcoreUtil.generateUUID());
            ioSpecification.getInputSet().add(createTInputSet);
        }
        if (ioSpecification.getOutputSet().isEmpty()) {
            final TOutputSet createTOutputSet = ModelFactory.eINSTANCE.createTOutputSet();
            createTOutputSet.setId(EcoreUtil.generateUUID());
            ioSpecification.getOutputSet().add(createTOutputSet);
        }
    }

    private TDataInputAssociation createDataInputAssociation(final TDataInput tDataInput,
            final Expression defaultValue) {
        final TDataInputAssociation tDataInputAssociation = ModelFactory.eINSTANCE.createTDataInputAssociation();
        tDataInputAssociation.setId(EcoreUtil.generateUUID());
        tDataInputAssociation.setTargetRef(tDataInput.getId());
        final EList<TAssignment> assignment = tDataInputAssociation.getAssignment();
        final TAssignment tAssignment = ModelFactory.eINSTANCE.createTAssignment();

        final TFormalExpression toExpression = ModelFactory.eINSTANCE.createTFormalExpression();
        toExpression.setId(EcoreUtil.generateUUID());
        FeatureMapUtil.addText(toExpression.getMixed(), /* "getDataInput('"+ */tDataInput.getId()/* +"')" */);
        tAssignment.setTo(toExpression);

        final TFormalExpression fromExpression = convertExpression(defaultValue);
        tAssignment.setFrom(fromExpression);

        assignment.add(tAssignment);
        return tDataInputAssociation;
    }

    private TDataObject createDataObject(final TProcess bpmnProcess, final Data bonitaData,
            final QName dataItemDefinitionIdAsQname) {
        final TDataObject bpmnData = ModelFactory.eINSTANCE.createTDataObject();
        bpmnData.setItemSubjectRef(dataItemDefinitionIdAsQname);
        setCommonAttributes(bonitaData, bpmnData);
        bpmnData.setName(bonitaData.getName());
        bpmnData.setId("DataObject" + EcoreUtil.generateUUID() + bpmnData.getId());// avoid to have duplicate id for
                                                                                   // dataobject and itemDefinition
        bpmnProcess.getFlowElement().add(bpmnData);
        bpmnData.setIsCollection(bonitaData.isMultiple());
        return bpmnData;
    }

    private TItemDefinition createDataItemDefinition(final Data bonitaData) {
        final TItemDefinition dataItemDefinition = ModelFactory.eINSTANCE.createTItemDefinition();
        setCommonAttributes(bonitaData, dataItemDefinition);
        dataItemDefinition.setStructureRef(getStructureRef(bonitaData));
        modelRegistry.getDefinitions().getRootElement().add(dataItemDefinition);
        return dataItemDefinition;
    }

    private TFormalExpression convertExpression(final Expression bonitaExpression) {
        return formalExpressionTransformerFactory
                .newFormalExpressionFunction(modelRegistry.getDataScope(), bonitaExpression.getType(), modelSearch)
                .apply(bonitaExpression);
    }

    private QName getStructureRef(final Data data) {
        if (data.getDataType() instanceof XMLType) {
            final String xmlnsDataType = xmlNamespaceResolver.resolveNamespacePrefix((XMLData) data);
            return QName.valueOf(xmlnsDataType + ":" + ((XMLData) data).getType());
        } else {
            final String technicalTypeFor = DataUtil.getTechnicalTypeFor(data);
            return QName.valueOf(JAVA_XMLNS + ":" + technicalTypeFor);
        }
    }

    private void populateWithSequenceFlow(final BPMNShapeFactory shapeFactory, Pool pool, final TProcess bpmnProcess) {
        modelSearch.getAllItemsOfType(pool, SequenceFlow.class).stream()
                .forEach(bonitaFlow -> handleSequenceFlow(shapeFactory, bpmnProcess, bonitaFlow));
    }

    private void handleSequenceFlow(final BPMNShapeFactory shapeFactory, final TProcess bpmnProcess,
            SequenceFlow bonitaFlow) {
        final TSequenceFlow bpmnFlow = ModelFactory.eINSTANCE.createTSequenceFlow();
        setCommonAttributes(bonitaFlow, bpmnFlow);
        if (!bonitaFlow.isIsDefault() && bonitaFlow.getCondition() != null && bonitaFlow.getCondition().hasContent()) {
            bpmnFlow.setConditionExpression(convertExpression(bonitaFlow.getCondition()));
        }
        final TFlowElement source = mapping.get(bonitaFlow.getSource());
        if (source != null) {
            bpmnFlow.setSourceRef(source.getId());
            final TFlowElement target = mapping.get(bonitaFlow.getTarget());
            if (target != null) {
                createSequenceFlow(shapeFactory, bpmnProcess, bonitaFlow, bpmnFlow, source, target);
            }
        }
    }

    private void createSequenceFlow(final BPMNShapeFactory shapeFactory, final TProcess bpmnProcess,
            SequenceFlow bonitaFlow, final TSequenceFlow bpmnFlow, final TFlowElement source,
            final TFlowElement target) {
        bpmnFlow.setTargetRef(target.getId());
        bpmnProcess.getFlowElement().add(bpmnFlow);
        if (bonitaFlow.isIsDefault() && bonitaFlow instanceof SequenceFlow) {
            if (source instanceof TInclusiveGateway) {
                ((TInclusiveGateway) source).setDefault(bpmnFlow.getId());
            } else if (source instanceof TExclusiveGateway) {
                ((TExclusiveGateway) source).setDefault(bpmnFlow.getId());
            } else if (source instanceof TComplexGateway) {
                ((TComplexGateway) source).setDefault(bpmnFlow.getId());
            } else if (source instanceof TActivity) {
                ((TActivity) source).setDefault(bpmnFlow.getId());
            }
        }
        // graphic
        final BPMNEdge edge = shapeFactory.createBPMNEdge(bpmnFlow.getId(), bonitaFlow);
        if (edge != null) {
            bpmnPlane.getDiagramElement().add(edge);
        }
    }

    private void populate(BPMNShapeFactory shapeFactory, Pool pool, List<Lane> lanes, final TProcess bpmnProcess,
            Bounds poolBounds) {
        if (lanes.isEmpty()) {
            populateContainer(shapeFactory, pool, bpmnProcess, null, poolBounds);
        } else {
            TLaneSet laneSet = ModelFactory.eINSTANCE.createTLaneSet();
            laneSet.setId(Strings.slugify(bpmnProcess.getName()) + "_laneSet");
            bpmnProcess.getLaneSet().add(laneSet);
            lanes.stream().forEach(lane -> {
                final TLane bpmnLane = ModelFactory.eINSTANCE.createTLane();
                bpmnLane.setName(lane.getName());
                bpmnLane.setId(lane.getName());
                setCommonAttributes(lane, bpmnLane);
                laneSet.getLane().add(bpmnLane);
                // graphic
                final BPMNShape laneShape = shapeFactory.createLane(bpmnLane.getId(), lane, poolBounds);
                bpmnPlane.getDiagramElement().add(laneShape);
                populateContainer(shapeFactory, lane, bpmnProcess, bpmnLane, laneShape.getBounds());
            });

        }
    }

    private void populateContainer(BPMNShapeFactory shapeFactory, EObject container, TProcess bpmnProcess,
            TLane bpmnParentLane, Bounds parentBounds) {
        final Map<Data, TItemDefinition> localDataMap = new HashMap<>();
        container.eAllContents().forEachRemaining(element -> {
            if (element instanceof FlowElement && Objects.equals(container, element.eContainer())) {
                final FlowElement bonitaElement = (FlowElement) element;
                // semantic
                final TFlowElement bpmnElement = createTFlowElement(bonitaElement);
                bpmnProcess.getFlowElement().add(bpmnElement);
                if (bpmnParentLane != null) {
                    bpmnParentLane.getFlowNodeRef().add(bpmnElement.getId());
                }
                mapping.put(bonitaElement, bpmnElement);

                populateDataOnActivity(bpmnProcess, localDataMap, bonitaElement, bpmnElement);

                // graphic
                BPMNShape elementShape = shapeFactory.create(bonitaElement, bpmnElement.getId(), parentBounds);
                bpmnPlane.getDiagramElement().add(elementShape);
                if (bonitaElement instanceof Activity) {
                    createBoundaries(shapeFactory, bpmnProcess, (Activity) bonitaElement, bpmnElement, elementShape);
                }
            } else if (element instanceof SubProcessEvent) {
                // semantic
                final SubProcessEvent subProcEvent = (SubProcessEvent) element;
                final TSubProcess bpmnSubProcess = (TSubProcess) createTFlowElement(subProcEvent);
                bpmnProcess.getFlowElement().add(bpmnSubProcess);
                if (bpmnParentLane != null) {
                    bpmnParentLane.getFlowNodeRef().add(bpmnSubProcess.getId());
                }
                mapping.put(subProcEvent, bpmnSubProcess);

                // graphic
                final BPMNShape elementShape = shapeFactory.create(subProcEvent, bpmnSubProcess.getId(), parentBounds);
                bpmnPlane.getDiagramElement().add(elementShape);

                populateContainer(shapeFactory, subProcEvent, bpmnSubProcess, parentBounds);
            }
        });
    }

    private void createBoundaries(BPMNShapeFactory shapeFactory, TProcess bpmnProcess, Activity activity,
            final TFlowElement bpmnElement, BPMNShape parentShape) {
        for (final BoundaryEvent boundaryEvent : activity.getBoundaryIntermediateEvents()) {
            createBoundaryEvent(shapeFactory, bpmnProcess, bpmnElement, parentShape, boundaryEvent);
        }
    }

    private void createBoundaryEvent(BPMNShapeFactory shapeFactory, TProcess bpmnProcess,
            final TFlowElement bpmnElement, BPMNShape parentShape, final BoundaryEvent boundaryEvent) {
        final TBoundaryEvent bpmnBoundary = ModelFactory.eINSTANCE.createTBoundaryEvent();
        setCommonAttributes(boundaryEvent, bpmnBoundary);
        if (boundaryEvent instanceof IntermediateErrorCatchEvent) {
            final TErrorEventDefinition errorventDef = ModelFactory.eINSTANCE.createTErrorEventDefinition();
            errorventDef
                    .setId(TFlowElementSwitch.EVENTDEF_PREFIX + boundaryEvent.getName() + EcoreUtil.generateUUID());
            final String errorCode = ((IntermediateErrorCatchEvent) boundaryEvent).getErrorCode();
            if (errorCode != null && errorCode.length() != 0) {
                errorventDef.setErrorRef(QName.valueOf(errorCode));
            }
            bpmnBoundary.getEventDefinition().add(errorventDef);
        } else if (boundaryEvent instanceof BoundarySignalEvent) {
            final TSignalEventDefinition eventDef = ModelFactory.eINSTANCE.createTSignalEventDefinition();
            eventDef.setId(TFlowElementSwitch.EVENTDEF_PREFIX + boundaryEvent.getName() + EcoreUtil.generateUUID());
            final TSignal tSignal = flowElementSwitch.getOrCreateTSignal((SignalEvent) boundaryEvent);
            if (tSignal != null) {
                eventDef.setSignalRef(QName.valueOf(tSignal.getId()));
            }
            bpmnBoundary.getEventDefinition().add(eventDef);
        } else if (boundaryEvent instanceof BoundaryMessageEvent) {
            final String eventCaught = ((BoundaryMessageEvent) boundaryEvent).getEvent();
            var eventId = eventCaught != null && !eventCaught.isBlank() ? eventCaught : EcoreUtil.generateUUID();
            bpmnBoundary.getEventDefinitionRef().add(QName.valueOf(eventId));
            modelRegistry.addMessage(eventId);
        } else if (boundaryEvent instanceof BoundaryTimerEvent) {
            final TTimerEventDefinition eventDef = flowElementSwitch
                    .createTimerEventDef((AbstractTimerEvent) boundaryEvent);
            bpmnBoundary.getEventDefinition().add(eventDef);
        }
        bpmnBoundary.setCancelActivity(!(boundaryEvent instanceof NonInterruptingBoundaryTimerEvent));
        bpmnBoundary.setAttachedToRef(QName.valueOf(bpmnElement.getId()));
        bpmnProcess.getFlowElement().add(bpmnBoundary);
        mapping.put(boundaryEvent, bpmnBoundary);

        // graphic
        final BPMNShape boundaryShape = shapeFactory.createBoundary(boundaryEvent, bpmnBoundary,
                parentShape.getBounds());
        bpmnPlane.getDiagramElement().add(boundaryShape);
    }

    private void populateContainer(final BPMNShapeFactory shapeFactory, SubProcessEvent container,
            TSubProcess bpmnSubProcess, Bounds parentBounds) {
        container.eAllContents().forEachRemaining(element -> {
            if (element instanceof FlowElement) {
                final FlowElement bonitaElement = (FlowElement) element;
                // semantic
                final TFlowElement bpmnElement = createTFlowElement(bonitaElement);
                bpmnSubProcess.getFlowElement().add(bpmnElement);
                mapping.put(bonitaElement, bpmnElement);

                // graphic
                BPMNShape elementShape = shapeFactory.create(bonitaElement, bpmnElement.getId(), parentBounds);
                bpmnPlane.getDiagramElement().add(elementShape);
            }
        });
    }

    private void populateDataOnActivity(final TProcess bpmnProcess, final Map<Data, TItemDefinition> localDataMap,
            final EObject resolvedSemanticElement, final TFlowElement bpmnElement) {
        if (resolvedSemanticElement instanceof DataAware && bpmnElement instanceof TActivity) {
            for (final Data bonitaData : ((DataAware) resolvedSemanticElement).getData()) {
                /* Create the itemDefinition */
                final TItemDefinition dataItemDefinition = createDataItemDefinition(bonitaData);
                localDataMap.put(bonitaData, dataItemDefinition);
                final QName dataItemDefinitionIdAsQname = QName.valueOf(dataItemDefinition.getId());
                if (!bonitaData.isTransient()) {
                    /* Add the dataObject using the reference */
                    createDataObject(bpmnProcess, bonitaData, dataItemDefinitionIdAsQname);
                } else {
                    /* it is a BPMN Property */
                    final TProperty tProperty = ModelFactory.eINSTANCE.createTProperty();
                    tProperty.setId(EcoreUtil.generateUUID());
                    tProperty.setName(bonitaData.getName());
                    tProperty.setItemSubjectRef(dataItemDefinitionIdAsQname);
                    ((TActivity) bpmnElement).getProperty().add(tProperty);
                }
                /* Define required data on the process, facultative? */
                final TDataInput tDataInput = DataIOTransformer
                        .fillIOSpecificationWithNewDataInput((TActivity) bpmnElement, dataItemDefinitionIdAsQname);

                if (bonitaData.getDefaultValue() != null && bonitaData.getDefaultValue().getName() != null) {
                    final List<TDataInputAssociation> dataInputAssociation = ((TActivity) bpmnElement)
                            .getDataInputAssociation();
                    dataInputAssociation.add(createDataInputAssociation(tDataInput, bonitaData.getDefaultValue()));
                }
            }
        }
    }

    private TFlowElement createTFlowElement(final Element child) {
        var flowElement = flowElementSwitch.doSwitch(child);
        if (flowElement == null) {
            modelRegistry.addError(String
                    .format("Could not find a valuable replacement for %s. Put an activity instead.", child.getName()));
            flowElement = ModelFactory.eINSTANCE.createTTask();
        }
        setCommonAttributes(child, flowElement);
        return flowElement;
    }

    private MultiStatus createErrorStatus(Throwable t) {
        MultiStatus multiStatus = new MultiStatus(BonitaToBPMNExporter.class, 0, null, null);
        multiStatus.add(new Status(IStatus.ERROR, BonitaToBPMNExporter.class, t.getMessage(), t));
        exportLimitations().stream().forEach(multiStatus::add);
        return multiStatus;
    }

    private MultiStatus createOKStatus() {
        MultiStatus multiStatus = new MultiStatus(BonitaToBPMNExporter.class, 0, null, null);
        exportLimitations().stream().forEach(multiStatus::add);
        return multiStatus;
    }

    private List<IStatus> exportLimitations() {
        final List<IStatus> result = new ArrayList<>();
        modelRegistry.getErrors().stream().map(message -> new Status(IStatus.INFO, BonitaToBPMNExporter.class, message))
                .forEach(result::add);
        return result;
    }

    public MultiStatus getStatus() {
        return status;
    }

}
