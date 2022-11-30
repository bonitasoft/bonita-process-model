package $package;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.bonitasoft.bpm.model.process.MainProcess;
import org.bonitasoft.bpm.model.util.ModelLoader;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.junit.jupiter.api.Test;

/**
 * Unit test for the simple application reading a sample model.
 */
public class AppTest {

    /** The URL of model to load */
    private static URL MODEL_URL = AppTest.class.getClassLoader().getResource("MyDiagram-8.proc");

    /** The URL of model to load */
    private static URL MIGRATION_MODEL_URL = AppTest.class.getClassLoader().getResource("MyDiagram-7.12.proc");

    /** The URL of reference HTML output */
    private static URL REFERENCE_HTML = AppTest.class.getClassLoader().getResource("referenceOuput.html");

    /** The URL of reference console output */
    private static URL REFERENCE_CONSOLE = AppTest.class.getClassLoader().getResource("referenceOuput.txt");

    /**
     * Test that default model is correctly loaded and partially interpreted.
     */
    @Test
    public void defaultModelIsLoadedCorrectly() {
        // load the model from file URL
        Resource model = ModelLoader.getInstance().loadModel(MODEL_URL);
        assertThat(model.isLoaded());
        // model is interpreted
        assertThat(model.getContents().get(0)).isInstanceOf(MainProcess.class);
        // diagram is loaded but not interpreted
        assertThat(model.getContents().get(1)).isInstanceOf(AnyType.class);
    }

    /**
     * Test that the HTML output is correct
     * 
     * @throws IOException io exception
     */
    @Test
    public void testHtmlOutput() throws IOException {
        File outFile = File.createTempFile("test", ".html");
        URL outUrl = outFile.toURI().toURL();
        App.main(new String[] { "-in", MODEL_URL.toString(), "-out", outUrl.toString(), "-tree", "HTML" });
        String outputContent = contentOf(outUrl);
        assertThat(outputContent).isNotEmpty();
        assertThat(outputContent).isEqualTo(contentOf(REFERENCE_HTML));
    }

    /**
     * Test that the console output is correct
     * 
     * @throws IOException io exception
     */
    @Test
    public void testConsoleOutput() throws IOException {
        PrintStream stdout = System.out;
        try {
            File logFile = File.createTempFile("log", ".txt");
            System.setOut(new PrintStream(logFile, StandardCharsets.UTF_8));
            App.main(new String[] { "-in", MODEL_URL.toString(), "-tree", "CONSOLE" });
            String logContent = contentOf(logFile);
            assertThat(logContent).isNotEmpty();
            assertThat(logContent).isEqualTo(contentOf(REFERENCE_CONSOLE));
        } finally {
            //restore original output
            System.setOut(stdout);
        }
    }

    /**
     * Test importing a model from Studio model version 7.12.0
     * 
     * @throws Exception
     */
    @Test
    public void testModelMigration_7_12_0() throws Exception {
        // make a temp copy of the model to migrate not to corrupt it for other tests when not read-only
        Path pathToMigrate = Path.of(MIGRATION_MODEL_URL.toURI());
        File copyFile = File.createTempFile("MyDiagram-7.12-", ".proc");
        Files.copy(pathToMigrate, copyFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        // load both models
        Resource oldModel = ModelLoader.getInstance().loadModel(copyFile.toURI().toURL());
        assertThat(oldModel.isLoaded()).isTrue();
        Resource latestModel = ModelLoader.getInstance().loadModel(MODEL_URL);
        assertThat(oldModel.isLoaded()).isTrue();

        // and test that content is similar
        assertThat(EcoreUtil.equals(oldModel.getContents().get(0), latestModel.getContents().get(0))).isTrue();
    }

}
