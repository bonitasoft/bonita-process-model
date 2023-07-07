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
package org.bonitasoft.bonita2bpmn.extension;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.bpm.model.process.Activity;
import org.bonitasoft.bpm.model.process.Container;
import org.bonitasoft.bpm.model.process.FlowElement;
import org.bonitasoft.bpm.model.process.Gateway;
import org.bonitasoft.bpm.model.process.Lane;
import org.bonitasoft.bpm.model.process.MainProcess;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.SubProcessEvent;
import org.bonitasoft.bpm.model.process.TextAnnotation;
import org.bonitasoft.bpm.model.util.EnvironmentUtil;
import org.bonitasoft.bpm.model.util.IModelSearch;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;

/**
 * @author Romain Bioteau
 */
public class BonitaModelExporterImpl implements IBonitaModelExporter {

    private Resource resource;
    private IModelSearch modelSearch;

    public BonitaModelExporterImpl(Resource resource, IModelSearch modelSearch) {
        this.resource = requireNonNull(resource);
        if (!resource.isLoaded()) {
            throw new IllegalStateException("Resource must be loaded first before export.");
        } else if (!EnvironmentUtil.isOSGi() && getDiagramOpt().isEmpty() && resource.getContents().stream()
                .anyMatch(AnyType.class::isInstance)) {
            throw new IllegalStateException(
                    "The GMF Notation package was not registered before model was loaded.\n" +
                            "Execute 'EPackage.Registry.INSTANCE.put(NotationPackage.eNS_URI, NotationPackage.eINSTANCE);' before loading the model with org.bonitasoft.bpm.model.util.ModelLoader.");
        }
        this.modelSearch = modelSearch;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.bonita2bpmn.extension.IBonitaModelExporter#getDiagram()
     */
    public MainProcess getMainProcess() {
        return resource.getContents().stream()
                .filter(MainProcess.class::isInstance)
                .map(MainProcess.class::cast)
                .findFirst()
                .orElseThrow(
                        () -> new IllegalStateException(
                                String.format("No MainProcess found in resource %s", resource)));
    }

    public Diagram getDiagram() {
        return getDiagramOpt().orElseThrow(
                () -> new IllegalStateException(String.format("No Diagram found in resource %s", resource)));
    }

    private Optional<Diagram> getDiagramOpt() {
        return resource.getContents().stream()
                .filter(Diagram.class::isInstance)
                .map(Diagram.class::cast)
                .findFirst();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.bonita2bpmn.extension.IBonitaModelExporter#getPools()
     */
    public List<Pool> getPools() {
        return getMainProcess().getElements().stream()
                .filter(Pool.class::isInstance)
                .map(Pool.class::cast)
                .collect(Collectors.toList());
    }

    public List<FlowElement> getFlowElements(Container container) {
        return modelSearch.getAllItemsOfType(container, FlowElement.class);
    }

    public List<Lane> getLanes(Pool pool) {
        return modelSearch.getAllItemsOfType(pool, Lane.class);
    }

    public Node getElementNotationNode(EObject modelElement) {
        List<Node> views = new ArrayList<>();
        TreeIterator<EObject> iterator = getDiagram().eAllContents();
        iterator.forEachRemaining(notationElement -> {
            if (notationElement instanceof Node
                    && Objects.equals(((Node) notationElement).getElement(), modelElement)) {
                views.add((Node) notationElement);
                iterator.prune();
            }
        });
        if (views.isEmpty()) {
            throw new IllegalStateException(String.format("No view found for %s", modelElement));
        }
        return views.get(0);
    }

    @Override
    public Bounds getBounds(Node view) {
        LayoutConstraint layoutConstraint = view.getLayoutConstraint();
        if (layoutConstraint instanceof Bounds) {
            Bounds bounds = NotationFactory.eINSTANCE.createBounds();
            bounds.setHeight(((Bounds) layoutConstraint).getHeight());
            bounds.setWidth(((Bounds) layoutConstraint).getWidth());
            bounds.setX(((Bounds) layoutConstraint).getX());
            bounds.setY(((Bounds) layoutConstraint).getY());
            Dimension defaultSize = getDefaultSize(view);
            if (bounds.getHeight() == -1) {
                bounds.setHeight(defaultSize.height);
            }
            if (bounds.getWidth() == -1) {
                bounds.setWidth(defaultSize.width);
            }
            return bounds;
        }
        return null;
    }

    private Dimension getDefaultSize(Node node) {
        EObject modelElement = node.getElement();
        if (modelElement instanceof Pool) {
            return new Dimension(1000, 250);
        } else if (modelElement instanceof Lane) {
            return new Dimension(975, 250);
        } else if (modelElement instanceof Activity || modelElement instanceof TextAnnotation
                || modelElement instanceof SubProcessEvent) {
            // activities are 100*50, see org.bonitasoft.studio.common.diagram.tools.FiguresHelper
            return new Dimension(100, 50);
        } else if (modelElement instanceof Gateway) {
            // gateways are 43*43, see org.bonitasoft.studio.common.diagram.tools.FiguresHelper
            return new Dimension(43, 43);
        } else {
            // events are 30*30, see org.bonitasoft.studio.common.diagram.tools.FiguresHelper
            return new Dimension(30, 30);
        }
        /**
         * Actually, we do not need the edit parts definition, just the model element...
         * Previous implementation was :
         * <code>
         * switch (Integer.valueOf(node.getType())) {
         * case PoolEditPart.VISUAL_ID:
         * return new Dimension(1000, 250);
         * case LaneEditPart.VISUAL_ID:
         * return new Dimension(975, 250);
         * case Activity2EditPart.VISUAL_ID:
         * case Task2EditPart.VISUAL_ID:
         * case ScriptTask2EditPart.VISUAL_ID:
         * case ServiceTask2EditPart.VISUAL_ID:
         * case SendTask2EditPart.VISUAL_ID:
         * case ReceiveTask2EditPart.VISUAL_ID:
         * case CallActivity2EditPart.VISUAL_ID:
         * case TextAnnotation2EditPart.VISUAL_ID:
         * case SubProcessEvent2EditPart.VISUAL_ID:
         * return new Dimension(FiguresHelper.ACTIVITY_WIDTH, FiguresHelper.ACTIVITY_HEIGHT);
         * case XORGateway2EditPart.VISUAL_ID:
         * case ANDGateway2EditPart.VISUAL_ID:
         * case InclusiveGateway2EditPart.VISUAL_ID:
         * return new Dimension(FiguresHelper.GATEWAY_WIDTH, FiguresHelper.GATEWAY_WIDTH);
         * default:
         * return new Dimension(FiguresHelper.EVENT_WIDTH, FiguresHelper.EVENT_WIDTH);
         * }
         * </code>
         */
    }

    @Override
    public Location getLocation(Node view) {
        LayoutConstraint layoutConstraint = view.getLayoutConstraint();
        if (layoutConstraint instanceof Location) {
            return (Location) layoutConstraint;
        }
        return null;
    }

    @Override
    public Edge getElementNotationEdge(EObject connection) {
        return (Edge) getDiagram().getPersistedEdges().stream()
                .filter(edge -> Objects.equals(((Edge) edge).getElement(), connection))
                .findFirst()
                .orElse(null);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.bonita2bpmn.extension.IBonitaModelExporter#getEObjectID(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public String getEObjectID(EObject eObject) {
        return modelSearch.getEObjectID(eObject);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.bonita2bpmn.extension.IBonitaModelExporter#getParentPool(org.bonitasoft.studio.model.process.Lane)
     */
    @Override
    public Pool getParentPool(Lane lane) {
        return modelSearch.getDirectParentOfType(lane, Pool.class);
    }

}
