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
import java.util.Optional;

import org.bonitasoft.bpm.connector.model.ConnectorModelRegistration;
import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.bpm.connector.model.definition.Output;
import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementationPackage;
import org.bonitasoft.bpm.model.expression.Expression;
import org.bonitasoft.bpm.model.expression.ExpressionPackage;
import org.bonitasoft.bpm.model.process.MainProcess;
import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.bpm.model.process.ServiceTask;
import org.bonitasoft.bpm.model.util.ModelLoader;
import org.bonitasoft.bpm.model.util.ModelLoader.Prerequisite;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xmi.XMLResource;
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
        Resource model = ModelLoader.create().enablePartial().loadModel(MODEL_URL);
        assertThat(model.isLoaded());
        // model is interpreted
        assertThat(model.getContents().get(0)).isInstanceOf(MainProcess.class);
        // diagram is loaded but not interpreted
        assertThat(model.getContents().get(1)).isInstanceOf(AnyType.class);
    }

    /**
     * Test that default model is correctly loaded and partially interpreted.
     */
    @Test
    public void defaultModelIsLoadedWithoutDefinitionPackage() {
        // in case ConnectorDefinitionPackage and ConnectorImplementationPackage where initialized, we need to deregister them
        Optional<Object> connDef = Optional
                .ofNullable(EPackage.Registry.INSTANCE.remove(ConnectorDefinitionPackage.eNS_URI));
        Optional<Object> connImpl = Optional
                .ofNullable(EPackage.Registry.INSTANCE.remove(ConnectorImplementationPackage.eNS_URI));
        try {
            // load the model from file URL
            Resource model = ModelLoader.create().enablePartial().loadModel(MODEL_URL);
            assertThat(model.isLoaded());
            // check that connector definition is not interpreted but available in the logout task
            EObject lane = model.getContents().get(0).eContents().get(0).eContents().get(0);
            EObject logoutTask = lane.eContents().get(4);
            assertThat(logoutTask).isInstanceOf(ServiceTask.class)
                    .hasFieldOrPropertyWithValue(ProcessPackage.Literals.ELEMENT__NAME.getName(), "AutoLogout");
            Expression rightOperand = ((ServiceTask) logoutTask).getConnectors().get(0).getOutputs().get(0)
                    .getRightOperand();
            EList<EObject> referencedElements = rightOperand.getReferencedElements();
            // connector definition is not loaded, so referencedElements is not populated, but values are accessible with getEObjectToExtensionMap
            assertThat(referencedElements).isEmpty();
            AnyType bodyAsStringEntry = ((XMLResource) model).getEObjectToExtensionMap().get(rightOperand);
            assertThat(bodyAsStringEntry).isNotNull();
            FeatureMap featMap = bodyAsStringEntry.getMixed();
            assertThat(featMap.size()).isEqualTo(1);
            assertThat(featMap.getEStructuralFeature(0).getName())
                    .isEqualTo(ExpressionPackage.Literals.EXPRESSION__REFERENCED_ELEMENTS.getName());
            Object bodyAsString = featMap.getValue(0);
            assertThat(bodyAsString).isInstanceOf(AnyType.class);
            assertThat(((AnyType) bodyAsString).eClass().getName())
                    .isEqualTo(ConnectorDefinitionPackage.Literals.OUTPUT.getName());
        } finally {
            // restore entries in the registry (accessing to package initialized it anyway)
            connDef.ifPresent(p -> EPackage.Registry.INSTANCE.put(ConnectorDefinitionPackage.eNS_URI, p));
            connImpl.ifPresent(p -> EPackage.Registry.INSTANCE.put(ConnectorImplementationPackage.eNS_URI, connImpl));
        }
    }

    /**
     * Test that default model is correctly loaded and partially interpreted.
     */
    @Test
    public void defaultModelIsLoadedWithDefinitionPackage() {
        // load the model from file URL
        Resource model = ModelLoader.create()
                .withPrerequisite(Prerequisite.fromRunnableWhenNotInOSGi(ConnectorModelRegistration.REGISTER))
                .enablePartial().loadModel(MODEL_URL);
        assertThat(model.isLoaded());
        // check that connector definition is fully interpreted in the logout task
        EObject lane = model.getContents().get(0).eContents().get(0).eContents().get(0);
        EObject logoutTask = lane.eContents().get(4);
        assertThat(logoutTask).isInstanceOf(ServiceTask.class)
                .hasFieldOrPropertyWithValue(ProcessPackage.Literals.ELEMENT__NAME.getName(), "AutoLogout");
        EList<EObject> referencedElements = ((ServiceTask) logoutTask).getConnectors().get(0).getOutputs().get(0)
                .getRightOperand()
                .getReferencedElements();
        // connector definition is loaded, so referencedElements is populated
        assertThat(referencedElements).isNotEmpty();
        EObject bodyAsString = referencedElements.get(0);
        assertThat(bodyAsString).isInstanceOf(Output.class);
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
        ModelLoader loader = ModelLoader.create().enablePartial()
                .withPrerequisite(Prerequisite.fromRunnableWhenNotInOSGi(ConnectorModelRegistration.REGISTER));
        Resource oldModel = loader.loadModel(copyFile.toURI().toURL());
        assertThat(oldModel.isLoaded()).isTrue();
        Resource latestModel = loader.loadModel(MODEL_URL);
        assertThat(oldModel.isLoaded()).isTrue();

        // and test that content is similar
        assertThat(EcoreUtil.equals(oldModel.getContents().get(0), latestModel.getContents().get(0))).isTrue();
    }

}
