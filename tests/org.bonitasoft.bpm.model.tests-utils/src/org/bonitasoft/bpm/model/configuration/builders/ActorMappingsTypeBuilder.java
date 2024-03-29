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
import org.bonitasoft.bpm.model.actormapping.ActorMapping;
import org.bonitasoft.bpm.model.actormapping.ActorMappingFactory;
import org.bonitasoft.bpm.model.actormapping.ActorMappingsType;

/**
 * @author Romain Bioteau
 */
public class ActorMappingsTypeBuilder implements Buildable<ActorMappingsType> {

    private final ActorMappingsType actorMappingsType;

    private ActorMappingsTypeBuilder(final ActorMappingsType actorMapping) {
        this.actorMappingsType = actorMapping;
    }

    public static ActorMappingsTypeBuilder anActorMappingsType() {
        return new ActorMappingsTypeBuilder(ActorMappingFactory.eINSTANCE.createActorMappingsType());
    }

    @SafeVarargs
    public final ActorMappingsTypeBuilder havingMapping(final Buildable<? extends ActorMapping>... mappings) {
        for (final Buildable<? extends ActorMapping> mapping : mappings) {
            actorMappingsType.getActorMapping().add(mapping.build());
        }
        return this;
    }

    public ActorMappingsType build() {
        return actorMappingsType;
    }

}
