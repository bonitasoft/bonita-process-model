package $package;

import static ${package}.ModelDisplayer.DisplayTreeIn.BOTH;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bonitasoft.bpm.model.process.MainProcess;
import org.bonitasoft.bpm.model.util.ModelLoader;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import ${package}.ModelDisplayer.DisplayTreeIn;

/**
 * A simple application reading a sample model
 */
public class App {

    /** The system-dependent line separator */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /** The URL of model to load */
    private static URL MODEL_URL = App.class.getClassLoader().getResource("MyDiagram-1.0.proc");

    /** key for <code>-in fileURL</code> argument */
    private static String IN_ARG = "-in";
    /** key for <code>-tree CONSOLE|HTML|BOTH</code> argument */
    private static String TREE_ARG = "-tree";
    /** key for <code>-out fileURL</code> argument */
    private static String OUT_ARG = "-out";

    /** The program arguments documentation */
    private static String ARGS_DOC = String.join(LINE_SEPARATOR,
            "All arguments are optional. You may use:",
            " '-in fileURL': for the URL of input .proc file to read",
            "     (by default, the embedded MyDiagram-1.0.proc file)",
            " '-tree CONSOLE|HTML|BOTH': to choose where the tree will be displayed",
            "     (by default, 'BOTH')",
            " '-out fileURL': for the URL of ouput .html file to read",
            "     (by default, a generated file in temp folder)",
            "     (useless when '-tree CONSOLE' is passed)",
            "E.g. '-in C:\\Users\\Me\\Documents\\MyProcess.proc -tree HTML -out C:\\Users\\Me\\Documents\\MyProcessView.html'",
            "  will read MyProcess.proc file in Documents, and generate the HTML tree output to MyProcessView.html file in Documents.",
            "");

    /**
     * Read the sample model
     * 
     * @param args optional command line arguments:<ul>
     *        <li><code>-in fileURL</code>: for the URL of input .proc file to read (by default, the embedded MyDiagram-1.0.proc file)</li>
     *        <li><code>-tree CONSOLE|HTML|BOTH</code>: to choose where the tree will be displayed (by default, <code>BOTH</code>)</li>
     *        <li><code>-out fileURL</code>: for the URL of ouput .html file to read (by default, a generated file in temp folder)
     *        (useless when '-tree CONSOLE' is passed)</li></ul>
     *        E.g. <code>-in C:\Users\Me\Documents\MyProcess.proc -tree HTML -out C:\Users\Me\Documents\MyProcessView.html</code>
     *        will read MyProcess.proc file in Documents, and generate the HTML tree output to MyProcessView.html file in Documents.
     */
    public static void main(String[] args) {
        // parse command line arguments
        URL inURL = MODEL_URL;
        URL outURL = null;
        DisplayTreeIn displayTree = BOTH;
        Optional<String> readArg = Optional.empty();
        if (args.length == 0) {
            // display arguments documentation just in case
            System.out.println(ARGS_DOC);
        }
        for (String arg : args) {
            if (readArg.filter(IN_ARG::equals).isPresent()) {
                //reset readArg
                readArg = Optional.empty();
                // reading -in argument
                try {
                    if (!arg.endsWith(".proc")) {
                        String msg = MessageFormat.format("The {0} argument must be a URL pointing to a .proc file.",
                                IN_ARG);
                        System.err.println(msg);
                        System.out.println(ARGS_DOC);
                        return;
                    }
                    inURL = new URL(arg);
                } catch (MalformedURLException e) {
                    String msg = MessageFormat.format("The {0} argument must be a well formed file URL.", IN_ARG);
                    System.err.println(msg);
                    System.out.println(ARGS_DOC);
                    return;
                }
            } else if (readArg.filter(TREE_ARG::equals).isPresent()) {
                //reset readArg
                readArg = Optional.empty();
                // reading -tree argument
                try {
                    displayTree = DisplayTreeIn.valueOf(arg);
                } catch (IllegalArgumentException | NullPointerException e) {
                    String validValues = Stream.of(DisplayTreeIn.values()).map(Object::toString)
                            .collect(Collectors.joining(", "));
                    String msg = MessageFormat.format("The {0} argument should be one of {1}.", TREE_ARG, validValues);
                    System.err.println(msg);
                    System.out.println(ARGS_DOC);
                    return;
                }
            } else if (readArg.filter(OUT_ARG::equals).isPresent()) {
                //reset readArg
                readArg = Optional.empty();
                // reading -out argument
                try {
                    if (!arg.endsWith(".html")) {
                        String msg = MessageFormat.format("The {0} argument must be a URL pointing to a .html file.",
                                OUT_ARG);
                        System.err.println(msg);
                        System.out.println(ARGS_DOC);
                        return;
                    }
                    outURL = new URL(arg);
                } catch (MalformedURLException e) {
                    String msg = MessageFormat.format("The {0} argument must be a well formed file URL.", OUT_ARG);
                    System.err.println(msg);
                    System.out.println(ARGS_DOC);
                    return;
                }
            } else {
                if (List.of(IN_ARG, TREE_ARG, OUT_ARG).contains(arg)) {
                    // reading a well known argument
                    readArg = Optional.of(arg);
                } else {
                    String msg = MessageFormat.format("Argument ''{0}'' is not understood.", arg);
                    System.err.println(msg);
                    System.out.println(ARGS_DOC);
                    return;
                }
            }
        }

        // load the model from file URL
        Resource model = ModelLoader.getInstance().loadModel(inURL);
        /*
         * Get the model content at index 0.
         * Notation is at index 1.
         */
        EObject firstContent = model.getContents().get(0);
        // now exploit the model
        if (firstContent instanceof MainProcess) {
            MainProcess mainProcess = (MainProcess) firstContent;
            new ModelDisplayer(mainProcess).displayTreeIn(displayTree).setOutputHtmlURL(outURL).display();
        } else {
            System.err.println("Process model could not be loaded correctly.");
        }
    }

}
