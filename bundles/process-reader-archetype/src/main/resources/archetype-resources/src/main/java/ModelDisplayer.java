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
package $package;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.bonitasoft.bpm.model.edit.AdapterFactoryBuilder;
import org.bonitasoft.bpm.model.process.Lane;
import org.bonitasoft.bpm.model.process.MainProcess;
import org.bonitasoft.bpm.model.process.Pool;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * Displays the model in different manners.
 */
public class ModelDisplayer {

    /** Where to display the tree */
    public static enum DisplayTreeIn {

        /** Display in console only */
        CONSOLE {

            @Override
            public boolean inHtml() {
                return false;
            }
        },
        /** Display in HTML file only */
        HTML {

            @Override
            public boolean inConsole() {
                return false;
            }
        },
        /** Display in console and in HTML file */
        BOTH;

        /**
         * Test whether information is displayed in console
         * 
         * @return true when in console
         */
        public boolean inConsole() {
            return true;
        }

        /**
         * Test whether information is displayed in html
         * 
         * @return true when in html
         */
        public boolean inHtml() {
            return true;
        }
    }

    /** The adapter adapterFactory useful for adapting model elements */
    private ComposedAdapterFactory adapterFactory = new AdapterFactoryBuilder().createAdapterFactory();
    /** The model root to display */
    private MainProcess modelRoot;

    /** Where to display the tree */
    private DisplayTreeIn displayTree = DisplayTreeIn.BOTH;
    /** The URL to HTML file to generate */
    private URL outHtmlURL;
    /** The output stream used to ouput HTML */
    private Optional<FileOutputStream> outputStreamToHtml = Optional.empty();

    /**
     * Default Constructor.
     */
    public ModelDisplayer(MainProcess modelRoot) {
        this.modelRoot = modelRoot;
    }

    /**
     * Set where to display the tree
     * 
     * @param displayTree where to display the tree
     * @return itself for convenience
     */
    public ModelDisplayer displayTreeIn(DisplayTreeIn displayTree) {
        this.displayTree = displayTree;
        return this;
    }

    /**
     * Set the URL to HTML file to generate
     * 
     * @param outURL the output URL for HTML or null to use a default temp file
     * @return itself for convenience
     */
    public ModelDisplayer setOutputHtmlURL(URL outURL) {
        this.outHtmlURL = outURL;
        return this;
    }

    /**
     * Display the model.
     * Some basic information is displayed in the console, more complete information is logged in the console and/or an HTML file.
     */
    public void display() {
        displayBasicInformation();
        displayModelTree();
    }

    /**
     * Display basic general information about the model
     */
    private void displayBasicInformation() {
        String msg = MessageFormat.format("Reading model with version {0}", modelRoot.getBonitaModelVersion());
        System.out.println(msg);
        // inspect children of model root, looking for Pools
        List<Pool> pools = modelRoot.getElements().stream().filter(Pool.class::isInstance).map(Pool.class::cast)
                .collect(Collectors.toList());
        // look for actors, in the model root and in Pools
        int numberOfActors = modelRoot.getActors().size()
                + pools.stream().collect(Collectors.summingInt(p -> p.getActors().size()));
        // inspect children of Pools, looking for Lanes
        List<Lane> lanes = pools.stream().flatMap(p -> p.getElements().stream()).filter(Lane.class::isInstance)
                .map(Lane.class::cast).collect(Collectors.toList());
        // make message with general information
        String detailsMsg = MessageFormat.format("Diagram {0} has {1} pool(s), {2} actor(s) and {3} lane(s).",
                modelRoot.getName(), pools.size(), numberOfActors, lanes.size());
        System.out.println(detailsMsg);
    }

    /**
     * Display the whole model as a tree structure
     */
    private void displayModelTree() {
        // the operation will run only once, either with or without html file...
        Supplier<Optional<IOException>> displayOperationRunner = new Supplier<>() {

            private AtomicBoolean alreadyExecuted = new AtomicBoolean(false);

            @Override
            public Optional<IOException> get() {
                if (!alreadyExecuted.getAndSet(true)) {
                    // display eventually with console and/or html
                    try {
                        displayElement(modelRoot, 0);
                    } catch (IOException e) {
                        return Optional.of(e);
                    }
                }
                return Optional.empty();
            }
        };
        if (displayTree.inHtml()) {
            File htmlFile;
            try {
                if (outHtmlURL == null) {
                    // use a generated temp file
                    htmlFile = File.createTempFile("out", ".html");
                } else {
                    htmlFile = new File(outHtmlURL.toURI());
                    htmlFile.createNewFile();
                }
            } catch (IOException | SecurityException | URISyntaxException e) {
                System.err.println("Output HTML file could not be created.");
                e.printStackTrace();
                htmlFile = null;
            }
            if (htmlFile != null) {
                try (FileOutputStream out = new FileOutputStream(htmlFile)) {
                    outputStreamToHtml = Optional.of(out);
                    HtmlTreeGenerator.prefix(out);
                    // run the operation to display with html and eventually console
                    Optional<IOException> exception = displayOperationRunner.get();
                    if (exception.isPresent()) {
                        throw exception.get();
                    } else {
                        System.out.println("Find generated HTML file at " + htmlFile.getAbsolutePath());
                    }
                    HtmlTreeGenerator.suffix(out);
                } catch (IOException e) {
                    System.err.println("Error occurred while writing to output HTML file.");
                    e.printStackTrace();
                }
            }
        }
        // in case there was no html, just run display the operation
        displayOperationRunner.get();
    }

    /**
     * Display a model element
     * 
     * @param modelElement the model element to display
     * @param childLevel its depth level
     * @throws IOException exception while writing file
     */
    private void displayElement(EObject modelElement, int childLevel) throws IOException {
        // model element is adapted to label provider thanks to the adapter factory
        IItemLabelProvider labelProvider = (IItemLabelProvider) adapterFactory.adapt(modelElement,
                IItemLabelProvider.class);
        // model element is adapted to tree provider thanks to the adapter factory
        ITreeItemContentProvider treeProvider = (ITreeItemContentProvider) adapterFactory.adapt(modelElement,
                ITreeItemContentProvider.class);
        // get information to display the tree entry
        String label = labelProvider.getText(modelElement);
        boolean hasChildren = treeProvider.hasChildren(modelElement);

        // try and display tree entry in console
        if (displayTree.inConsole()) {
            if (childLevel > 0) {
                String indent = IntStream.range(0, childLevel - 1).mapToObj(i -> "   ").collect(Collectors.joining(""));
                String prefix = indent + "└─ ";
                System.out.println(prefix + label);
            } else {
                System.out.println(label);
            }
        }
        // try and display tree entry in html
        if (outputStreamToHtml.isPresent()) {
            URL image = (URL) labelProvider.getImage(modelElement);
            HtmlTreeGenerator.writeTreeEntry(outputStreamToHtml.get(), image, label, hasChildren);
        }
        // display children's information
        if (hasChildren) {
            Collection<?> children = treeProvider.getChildren(modelElement);
            for (Object c : children) {
                if (c instanceof EObject) {
                    // display model element recursively
                    displayElement((EObject) c, childLevel + 1);
                } else {
                    // tree may eventually have non-model objects
                    if (displayTree.inConsole()) {
                        // indent is (childLevel +1) -1
                        String indent = IntStream.range(0, childLevel).mapToObj(i -> "   ")
                                .collect(Collectors.joining(""));
                        String prefix = indent + "└─ ";
                        System.out.println(prefix + c.toString());
                    }
                    if (outputStreamToHtml.isPresent()) {
                        HtmlTreeGenerator.writeTreeEntry(outputStreamToHtml.get(), c.toString());
                    }
                }
            }
            if (outputStreamToHtml.isPresent()) {
                HtmlTreeGenerator.endTreeEntryChildren(outputStreamToHtml.get());
            }
        }
    }

}
