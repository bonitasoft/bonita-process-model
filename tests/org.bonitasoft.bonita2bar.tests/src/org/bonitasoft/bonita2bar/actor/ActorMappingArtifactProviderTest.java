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

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.bpm.model.actormapping.ActorMappingFactory.eINSTANCE;

import org.bonitasoft.bpm.model.actormapping.ActorMappingsType;
import org.bonitasoft.bpm.model.actormapping.Groups;
import org.bonitasoft.bpm.model.actormapping.Membership;
import org.bonitasoft.bpm.model.actormapping.MembershipType;
import org.bonitasoft.bpm.model.actormapping.Roles;
import org.bonitasoft.bpm.model.actormapping.Users;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.ConfigurationFactory;
import org.bonitasoft.bpm.model.process.Pool;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.engine.bpm.bar.actorMapping.Actor;
import org.bonitasoft.engine.bpm.bar.actorMapping.ActorMapping;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ActorMappingArtifactProviderTest {

    private ActorMappingArtifactProvider actorMappingArtifactProvider = new ActorMappingArtifactProvider();
    private Pool pool;
    private Configuration configuration;
    private BusinessArchiveBuilder businessArchiveBuilder;

    @BeforeEach
    public void before() throws Exception {
        pool = ProcessFactory.eINSTANCE.createPool();
        pool.getActors().add(ProcessFactory.eINSTANCE.createActor());
        configuration = ConfigurationFactory.eINSTANCE.createConfiguration();
        businessArchiveBuilder = new BusinessArchiveBuilder().createNewBusinessArchive();
    }

    @Test
    void should_export_actor_mapping_from_configuration() throws Exception {
        //given
        configuration.setActorMappings(createActorMapping(ActorBuilder.createActor("employee").addUser("john").done()));
        //when
        actorMappingArtifactProvider.build(businessArchiveBuilder, pool, configuration);
        //then
        ActorMapping actorMappingRuntime = new ActorMapping();
        Actor john = new Actor("employee");
        john.addUser("john");
        actorMappingRuntime.addActor(john);
        assertThat(businessArchiveBuilder.getActorMapping()).isEqualTo(actorMappingRuntime);
    }

    @Test
    void should_export_group_of_actor_mapping() throws Exception {
        //given
        configuration
                .setActorMappings(createActorMapping(ActorBuilder.createActor("employee").addGroup("myGroup").done()));
        //when
        actorMappingArtifactProvider.build(businessArchiveBuilder, pool, configuration);
        //then
        ActorMapping actorMappingRuntime = new ActorMapping();
        Actor john = new Actor("employee");
        john.addGroup("myGroup");
        actorMappingRuntime.addActor(john);
        assertThat(businessArchiveBuilder.getActorMapping()).isEqualTo(actorMappingRuntime);
    }

    @Test
    void should_export_role_of_actor_mapping() throws Exception {
        //given
        configuration
                .setActorMappings(createActorMapping(ActorBuilder.createActor("employee").addRole("myRole").done()));
        //when
        actorMappingArtifactProvider.build(businessArchiveBuilder, pool, configuration);
        //then
        ActorMapping actorMappingRuntime = new ActorMapping();
        Actor john = new Actor("employee");
        john.addRole("myRole");
        actorMappingRuntime.addActor(john);
        assertThat(businessArchiveBuilder.getActorMapping()).isEqualTo(actorMappingRuntime);
    }

    @Test
    void should_export_membership_of_actor_mapping() throws Exception {
        //given
        configuration.setActorMappings(
                createActorMapping(ActorBuilder.createActor("employee").addMembership("myGroup", "myRole").done()));
        //when
        actorMappingArtifactProvider.build(businessArchiveBuilder, pool, configuration);
        //then
        ActorMapping actorMappingRuntime = new ActorMapping();
        Actor john = new Actor("employee");
        john.addMembership("myGroup", "myRole");
        actorMappingRuntime.addActor(john);
        assertThat(businessArchiveBuilder.getActorMapping()).isEqualTo(actorMappingRuntime);
    }

    @Test
    void should_export_empty_actor_mapping_with_no_mapping_defined() throws Exception {
        //given
        //when
        actorMappingArtifactProvider.build(businessArchiveBuilder, pool, configuration);
        //then
        assertThat(businessArchiveBuilder.getActorMapping()).isNull();
    }

    private ActorMappingsType createActorMapping(org.bonitasoft.bpm.model.actormapping.ActorMapping actorMappingDev) {
        ActorMappingsType actorMappingsType = eINSTANCE.createActorMappingsType();
        actorMappingsType.getActorMapping().add(actorMappingDev);
        return actorMappingsType;
    }

    private static class ActorBuilder {

        private org.bonitasoft.bpm.model.actormapping.ActorMapping actor;

        static ActorBuilder createActor(String employee) {
            ActorBuilder actorBuilder = new ActorBuilder();
            actorBuilder.actor = eINSTANCE.createActorMapping();
            actorBuilder.actor.setName(employee);
            return actorBuilder;
        }

        ActorBuilder addUser(String user) {
            Users users = eINSTANCE.createUsers();
            users.getUser().add(user);
            actor.setUsers(users);
            return this;
        }

        ActorBuilder addGroup(String group) {
            Groups groups = eINSTANCE.createGroups();
            groups.getGroup().add(group);
            actor.setGroups(groups);
            return this;
        }

        ActorBuilder addRole(String role) {
            Roles roles = eINSTANCE.createRoles();
            roles.getRole().add(role);
            actor.setRoles(roles);
            return this;
        }

        ActorBuilder addMembership(String group, String role) {
            MembershipType membershipType = eINSTANCE.createMembershipType();
            membershipType.setGroup(group);
            membershipType.setRole(role);
            Membership membership = eINSTANCE.createMembership();
            membership.getMembership().add(membershipType);
            actor.setMemberships(membership);
            return this;
        }

        org.bonitasoft.bpm.model.actormapping.ActorMapping done() {
            return actor;
        }

    }
}
