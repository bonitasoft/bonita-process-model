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
package org.bonitasoft.bonita2bar.actor;

import org.bonitasoft.bonita2bar.BarArtifactProvider;
import org.bonitasoft.bonita2bar.BuildBarException;
import org.bonitasoft.bpm.model.actormapping.ActorMapping;
import org.bonitasoft.bpm.model.actormapping.ActorMappingsType;
import org.bonitasoft.bpm.model.actormapping.Groups;
import org.bonitasoft.bpm.model.actormapping.Membership;
import org.bonitasoft.bpm.model.actormapping.MembershipType;
import org.bonitasoft.bpm.model.actormapping.Roles;
import org.bonitasoft.bpm.model.actormapping.Users;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.engine.bpm.bar.actorMapping.Actor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Baptiste Mesta
 */
public class ActorMappingArtifactProvider implements BarArtifactProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActorMappingArtifactProvider.class);

    private boolean containsActorMapping(final Configuration configuration) {
        return configuration.getActorMappings() != null
                && configuration.getActorMappings().getActorMapping() != null
                && !configuration.getActorMappings().getActorMapping().isEmpty();
    }

    private void cleanMapping(final ActorMappingsType mapping) {
        for (final ActorMapping m : mapping.getActorMapping()) {
            final Groups groups = m.getGroups();
            if (groups != null && groups.getGroup().isEmpty()) {
                m.setGroups(null);
            }
            final Membership memberships = m.getMemberships();
            if (memberships != null && memberships.getMembership().isEmpty()) {
                m.setMemberships(null);
            }
            final Roles roles = m.getRoles();
            if (roles != null && roles.getRole().isEmpty()) {
                m.setRoles(null);
            }
            final Users users = m.getUsers();
            if (users != null && users.getUser().isEmpty()) {
                m.setUsers(null);
            }
        }
    }

    @Override
    public void build(BusinessArchiveBuilder builder,
            Pool process,
            Configuration configuration)
            throws BuildBarException {
        if (configuration == null || process.getActors().isEmpty()) {
            return;
        }
        if (!containsActorMapping(configuration)) {
            LOGGER.warn("No actor mapping found for environment {}", configuration.getName());
            return;
        }
        LOGGER.info("Adding actor mapping...");
        final ActorMappingsType mapping = EcoreUtil.copy(configuration.getActorMappings());
        cleanMapping(mapping);
        org.bonitasoft.engine.bpm.bar.actorMapping.ActorMapping actorMapping = new org.bonitasoft.engine.bpm.bar.actorMapping.ActorMapping();
        addActorMappings(configuration, actorMapping);
        builder.setActorMapping(actorMapping);
    }

    private void addActorMappings(Configuration configuration,
            org.bonitasoft.engine.bpm.bar.actorMapping.ActorMapping actorMapping) {
        for (ActorMapping actorMappingDev : configuration.getActorMappings().getActorMapping()) {
            actorMapping.addActor(createActor(actorMappingDev));
        }
    }

    private Actor createActor(ActorMapping actorMappingDev) {
        Actor actor = new Actor(actorMappingDev.getName());
        Users users = actorMappingDev.getUsers();
        if (users != null) {
            actor.addUsers(users.getUser());
        }
        Groups groups = actorMappingDev.getGroups();
        if (groups != null) {
            actor.addGroups(groups.getGroup());
        }
        Roles roles = actorMappingDev.getRoles();
        if (roles != null) {
            actor.addRoles(roles.getRole());
        }
        Membership memberships = actorMappingDev.getMemberships();
        if (memberships != null) {
            for (MembershipType membershipType : memberships.getMembership()) {
                actor.addMembership(membershipType.getGroup(), membershipType.getRole());
            }
        }
        return actor;
    }
}
