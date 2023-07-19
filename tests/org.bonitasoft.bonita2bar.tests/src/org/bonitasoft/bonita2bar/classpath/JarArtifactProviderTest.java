/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.bonita2bar.classpath;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.bonitasoft.bonita2bar.ClasspathResolver;
import org.bonitasoft.bpm.model.MavenUtil;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.ConfigurationFactory;
import org.bonitasoft.bpm.model.configuration.Fragment;
import org.bonitasoft.bpm.model.configuration.FragmentContainer;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.bpm.model.util.FragmentTypes;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class JarArtifactProviderTest {

    private JarArtifactProvider jarArtifactProvider;
    private Pool pool;
    private Configuration configuration;
    private BusinessArchiveBuilder businessArchiveBuilder;
    private Path lib;

    @BeforeEach
    public void before(@TempDir Path repositoryFolder) throws Exception {
        var appFolder = Files.createDirectory(repositoryFolder.resolve("app"));
        try (InputStream is = FileLocator.toFileURL(JarArtifactProviderTest.class.getResource("/pom.xml.example"))
                .openStream()) {
            Files.copy(is, appFolder.resolve("pom.xml"));
        }
        pool = ProcessFactory.eINSTANCE.createPool();
        pool.setName("P");
        pool.setVersion("1");
        configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        configuration.setName("Dev");
        businessArchiveBuilder = new BusinessArchiveBuilder().createNewBusinessArchive();
        businessArchiveBuilder.setProcessDefinition(
                new ProcessDefinitionBuilder().createNewInstance("P", "1").addAutomaticTask("step1").getProcess());
        lib = repositoryFolder.resolve("lib");
        Files.createDirectories(lib);
        var classpathFile = MavenUtil.buildClasspath(repositoryFolder,
                Platform.getOS().contains("win") ? "mvn.cmd" : "mvn");

        jarArtifactProvider = new JarArtifactProvider(ClasspathResolver.of(classpathFile));
    }

    @Test
    void should_export_jar_from_configuration_as_classpath_resource() throws Exception {
        //given
        configuration.getProcessDependencies()
                .add(fragmentContainer(
                        fragment(FragmentTypes.JAR, true, "commons-text-1.9.jar", "commons-text-1.9.jar")));

        //when
        jarArtifactProvider.build(businessArchiveBuilder, pool,
                configuration);

        //then
        assertThat(businessArchiveBuilder.done().getResources()).containsKeys("classpath/commons-text-1.9.jar");
    }

    @Test
    void should_not_export_other_kind_of_fragment() throws Exception {
        //given
        configuration.getProcessDependencies()
                .add(fragmentContainer(fragment("NOT_JAR", true, "myJar.jar", "myJar.jar")));
        Files.write(lib.resolve("myJar.jar"), "the jar content".getBytes());
        //when
        jarArtifactProvider.build(businessArchiveBuilder, pool,
                configuration);
        //then
        assertThat(businessArchiveBuilder.done().getResources()).isEmpty();
    }

    @Test
    void should_log_when_jar_does_not_exists() throws Exception {
        //given
        configuration.getProcessDependencies().add(fragmentContainer(fragment("JAR", true, "myJar.jar", "myJar.jar")));
        //when
        jarArtifactProvider.build(businessArchiveBuilder, pool,
                configuration);
        //then
        assertThat(businessArchiveBuilder.done().getResources()).isEmpty();
    }

    @Test
    void should_not_export_unexported_fragments() throws Exception {
        //given
        configuration.getProcessDependencies().add(fragmentContainer(fragment("JAR", false, "myJar.jar", "myJar.jar")));
        Files.write(lib.resolve("myJar.jar"), "the jar content".getBytes());
        //when
        jarArtifactProvider.build(businessArchiveBuilder, pool,
                configuration);
        //then
        assertThat(businessArchiveBuilder.done().getResources()).isEmpty();
    }

    private FragmentContainer fragmentContainer(Fragment... fragments) {
        FragmentContainer fragmentContainer = ConfigurationFactory.eINSTANCE.createFragmentContainer();
        for (Fragment fragment : fragments) {
            fragmentContainer.getFragments().add(fragment);
        }
        return fragmentContainer;
    }

    private Fragment fragment(String type, boolean exported, String name, String value) {
        Fragment fragment = ConfigurationFactory.eINSTANCE.createFragment();
        fragment.setType(type);
        fragment.setExported(exported);
        fragment.setKey(name);
        fragment.setValue(value);
        return fragment;
    }

}
