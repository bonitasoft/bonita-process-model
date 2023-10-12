/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.bonita2bar.resources;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.net.URLDecoder;

import org.bonitasoft.bonita2bar.SourcePathProvider;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.engine.bpm.bar.BusinessArchive;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.engine.bpm.process.impl.ProcessDefinitionBuilder;
import org.eclipse.core.runtime.FileLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdditionalResourcesArtifactProviderTest {

    private Pool pool;
    private Configuration configuration;
    private BusinessArchiveBuilder businessArchiveBuilder;
    private File repositoryFolder;

    @BeforeEach
    void before() throws Exception {
        repositoryFolder = new File(URLDecoder
                .decode(FileLocator.toFileURL(AdditionalResourcesArtifactProviderTest.class
                        .getResource("/test-repository")).getFile(), "UTF-8"));
        pool = ProcessFactory.eINSTANCE.createPool();
        pool.setName("ProcessName");
        pool.setVersion("1.0");
        businessArchiveBuilder = new BusinessArchiveBuilder().createNewBusinessArchive();
        businessArchiveBuilder.setProcessDefinition(
                new ProcessDefinitionBuilder().createNewInstance("ProcessName", "1.0").addAutomaticTask("step1")
                        .getProcess());
    }

    @Test
    void should_export_additional_resource_in_bar() throws Exception {
        //given
        var provider = new AdditionalResourcesArtifactProvider(
                SourcePathProvider.of(repositoryFolder.toPath().resolve("app")).getResources());

        //when
        provider.build(businessArchiveBuilder, pool, configuration);

        //then
        BusinessArchive businessArchive = businessArchiveBuilder.done();
        assertThat(new String(businessArchive.getResource("resources/resource.txt"))).isEqualTo("processVersion");
        assertThat(new String(businessArchive.getResource("resources/resource2.txt"))).isEqualTo("Salut");
        assertThat(businessArchive.getResource("resources/notExistingResource.txt")).isNull();
    }

    @Test
    void should_not_export_empty_additional_resource_in_bar() throws Exception {
        //given
        var provider = new AdditionalResourcesArtifactProvider(
                SourcePathProvider.of(repositoryFolder.toPath().resolve("app")).getResources());

        //when
        provider.build(businessArchiveBuilder, pool, configuration);

        //then
        BusinessArchive businessArchive = businessArchiveBuilder.done();
        assertThat(businessArchive.getResource("resources/empty")).isNull();
    }

}
