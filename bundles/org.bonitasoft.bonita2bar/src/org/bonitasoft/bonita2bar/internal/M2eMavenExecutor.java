/**
 * Copyright (C) 2025 Bonitasoft S.A.
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
package org.bonitasoft.bonita2bar.internal;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Supplier;

import org.apache.maven.execution.BuildSuccess;
import org.apache.maven.execution.BuildSummary;
import org.bonitasoft.bonita2bar.BuildBarException;
import org.bonitasoft.bonita2bar.MavenExecutor;
import org.bonitasoft.bpm.model.util.EnvironmentUtil;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.IMaven;

/**
 * {@link MavenExecutor} using the M2E maven plugin.
 * This class should be used only in a P2 context and not even loaded without the org.eclipse.m2e.core plugin.
 */
public class M2eMavenExecutor implements MavenExecutor {

    /**
     * Default Constructor.
     */
    public M2eMavenExecutor() {
        if (!EnvironmentUtil.isOSGi()) {
            throw new IllegalStateException("Use only in a P2 context with the org.eclipse.m2e.core plugin.");
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.bonita2bar.MavenExecutor#execute(java.io.File, java.util.List, java.util.Map, java.util.List, java.util.function.Supplier)
     */
    @Override
    public void execute(File pomFile, List<String> goals, Map<String, String> properties, List<String> activeProfiles,
            Supplier<String> errorMessageBase) throws BuildBarException {
        try {
            IMaven maven = Optional.ofNullable(MavenPlugin.getMaven()).orElseGet(() -> {
                // workaround mentioned on https://github.com/eclipse-m2e/m2e-core/issues/966
                // just the class dependency should be enough to start the plugin early
                ResourcesPlugin.getPlugin();
                return Optional.ofNullable(MavenPlugin.getMaven())
                        .orElseThrow(() -> new IllegalStateException("Maven is not available."));
            });
            var ctx = maven.createExecutionContext();
            var request = ctx.getExecutionRequest();
            request.setGoals(goals);
            var userProperties = new Properties();
            properties.forEach(userProperties::setProperty);
            request.setUserProperties(userProperties);
            request.setActiveProfiles(activeProfiles);
            request.setPom(pomFile);
            // Run the request through the m2e execution context so that the Maven session is fully
            // set up (projects, workspace reader, event spies) before the build starts. Looking up and
            // executing Maven ourselves leaves the session incompletely initialized and fails with a
            // ReactorReader NPE on MavenSession.getProjects() with maven-core 3.9.x.
            var executionResult = ctx.execute(request);
            BuildSummary buildSummary = executionResult.getBuildSummary(executionResult.getProject());
            if (!(buildSummary instanceof BuildSuccess)) {
                var exceptions = executionResult.getExceptions();
                throw new BuildBarException(errorMessageBase.get(),
                        exceptions.isEmpty() ? null : exceptions.get(0));
            }
        } catch (CoreException e) {
            throw new BuildBarException(errorMessageBase.get(), e);
        }
    }
}
