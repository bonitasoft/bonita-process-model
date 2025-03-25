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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.bonita2bar.BarArtifactProvider;
import org.bonitasoft.bonita2bar.BuildBarException;
import org.bonitasoft.bonita2bar.ClasspathResolver;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.Fragment;
import org.bonitasoft.bpm.model.configuration.FragmentContainer;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.util.IModelSearch;
import org.bonitasoft.bpm.model.util.ModelSearch;
import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @deprecated use {@link DependenciesArtifactProvider} instead.
 *             Candidate for removal.
 */
@Deprecated
public class JarArtifactProvider implements BarArtifactProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JarArtifactProvider.class);

    private IModelSearch modelSearch = new ModelSearch(Collections::emptyList);

    private ClasspathResolver classpathResolver;

    /**
     * Replaces the org.bonitasoft.bpm.model.util.FragmentTypes.JAR constant until removal.
     * 
     * @deprecated
     */
    @Deprecated
    public static String FRAGMENT_TYPE_JAR = "JAR";

    public JarArtifactProvider(ClasspathResolver classpathResolver) {
        this.classpathResolver = classpathResolver;
    }

    @Override
    public void build(BusinessArchiveBuilder builder,
            Pool process,
            Configuration configuration)
            throws BuildBarException {
        if (configuration == null) {
            return;
        }
        for (final FragmentContainer fc : configuration.getProcessDependencies()) {
            final List<Fragment> fragments = modelSearch.getAllItemsOfType(fc, Fragment.class);
            for (final Fragment fragment : fragments) {
                if (fragment.getType().equals(FRAGMENT_TYPE_JAR) && fragment.isExported()) {
                    try {
                        addJarToBusinessArchive(builder, fragment, configuration);
                    } catch (IOException e) {
                        throw new BuildBarException(e);
                    }
                }
            }
        }
    }

    private void addJarToBusinessArchive(BusinessArchiveBuilder builder, Fragment fragment, Configuration configuration)
            throws BuildBarException, IOException {
        File jar = classpathResolver.findJarFile(fragment.getValue());
        if (jar == null || !jar.isFile()) {
            LOGGER.warn("{} dependency is missing but defined for {} environment", fragment.getValue(),
                    configuration.getName());
            return;
        }
        try {
            LOGGER.info("Adding {} to classpath...", jar.getName());
            builder.addClasspathResource(new BarResource(jar.getName(), Files.readAllBytes(jar.toPath())));
        } catch (final IOException e) {
            throw new BuildBarException(String.format("Unable to get content of the %s ", jar), e);
        }
    }

}
