/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.bpm.model.configuration.builders;

import org.bonitasoft.bpm.model.Buildable;
import org.bonitasoft.bpm.model.actormapping.ActorMappingsType;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.ConfigurationFactory;
import org.bonitasoft.bpm.model.configuration.DefinitionMapping;
import org.bonitasoft.bpm.model.configuration.FragmentContainer;
import org.bonitasoft.bpm.model.parameter.Parameter;

/**
 * @author Romain Bioteau
 */
public class ConfigurationBuilder {

    private final Configuration configuration;

    private ConfigurationBuilder(final Configuration configuration) {
        this.configuration = configuration;
    }

    public static ConfigurationBuilder aConfiguration() {
        return new ConfigurationBuilder(ConfigurationFactory.eINSTANCE.createConfiguration());
    }

    public ConfigurationBuilder withName(final String name) {
        configuration.setName(name);
        return this;
    }

    public ConfigurationBuilder withDescription(final String description) {
        configuration.setDescription(description);
        return this;
    }

    public ConfigurationBuilder withVersion(final String version) {
        configuration.setVersion(version);
        return this;
    }

    public ConfigurationBuilder withPassword(final String password) {
        configuration.setPassword(password);
        return this;
    }

    public ConfigurationBuilder withUsername(final String username) {
        configuration.setUsername(username);
        return this;
    }

    public ConfigurationBuilder withAnonymousUsername(final String anonymousUserName) {
        configuration.setAnonymousUserName(anonymousUserName);
        return this;
    }

    public ConfigurationBuilder withAnonymousPassword(final String anonymousPassword) {
        configuration.setAnonymousPassword(anonymousPassword);
        return this;
    }

    @SafeVarargs
    public final ConfigurationBuilder havingDefinitionMappings(
            final Buildable<? extends DefinitionMapping>... defMappings) {
        for (final Buildable<? extends DefinitionMapping> def : defMappings) {
            configuration.getDefinitionMappings().add(def.build());
        }
        return this;
    }

    @SafeVarargs
    public final ConfigurationBuilder havingProcessDependencies(
            final Buildable<? extends FragmentContainer>... dependencies) {
        for (final Buildable<? extends FragmentContainer> dep : dependencies) {
            configuration.getProcessDependencies().add(dep.build());
        }
        return this;
    }

    @SafeVarargs
    public final ConfigurationBuilder havingParameters(final Buildable<? extends Parameter>... parameters) {
        for (final Buildable<? extends Parameter> parameter : parameters) {
            configuration.getParameters().add(parameter.build());
        }
        return this;
    }

    public ConfigurationBuilder havingActorMapping(final Buildable<? extends ActorMappingsType> actorMapping) {
        configuration.setActorMappings(actorMapping.build());
        return this;
    }

    public Configuration build() {
        return configuration;
    }

}
