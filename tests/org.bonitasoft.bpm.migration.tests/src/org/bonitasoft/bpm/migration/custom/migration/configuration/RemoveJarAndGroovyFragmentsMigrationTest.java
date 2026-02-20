/**
 * Copyright (C) 2026 Bonitasoft S.A.
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
package org.bonitasoft.bpm.migration.custom.migration.configuration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.impl.MetamodelImpl;
import org.eclipse.emf.edapt.spi.migration.impl.ModelImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RemoveJarAndGroovyFragmentsMigrationTest {

    private RemoveJarAndGroovyFragmentsMigration migrationUnderTest;

    @Mock
    private ModelImpl model;

    @Mock
    private MetamodelImpl metamodel;

    @BeforeEach
    void setUp() {
        migrationUnderTest = new RemoveJarAndGroovyFragmentsMigration();
    }

    @Test
    void should_delete_groovy_script_fragments() throws Exception {
        Instance groovyFragment = mock(Instance.class);
        when(groovyFragment.get("type")).thenReturn("GROOVY_SCRIPT");

        EList<Instance> fragments = new BasicEList<>();
        fragments.add(groovyFragment);
        when(model.getAllInstances("configuration.Fragment")).thenReturn(fragments);
        when(model.getAllInstances("configuration.FragmentContainer")).thenReturn(new BasicEList<>());

        migrationUnderTest.migrateBefore(model, metamodel);

        verify(model).delete(groovyFragment);
    }

    @Test
    void should_delete_groovy_script_container() throws Exception {
        when(model.getAllInstances("configuration.Fragment")).thenReturn(new BasicEList<>());

        Instance groovyContainer = mock(Instance.class);
        when(groovyContainer.get("id")).thenReturn("GROOVY_SCRIPT");

        EList<Instance> containers = new BasicEList<>();
        containers.add(groovyContainer);
        when(model.getAllInstances("configuration.FragmentContainer")).thenReturn(containers);

        migrationUnderTest.migrateBefore(model, metamodel);

        verify(model).delete(groovyContainer);
    }

    @Test
    void should_delete_jar_fragments_not_in_other_container() throws Exception {
        Instance jarFragment = mock(Instance.class);
        Instance connectorContainer = mock(Instance.class);
        when(jarFragment.get("type")).thenReturn("JAR");
        when(jarFragment.getContainer()).thenReturn(connectorContainer);
        when(connectorContainer.get("id")).thenReturn("CONNECTOR");

        EList<Instance> fragments = new BasicEList<>();
        fragments.add(jarFragment);
        when(model.getAllInstances("configuration.Fragment")).thenReturn(fragments);
        when(model.getAllInstances("configuration.FragmentContainer")).thenReturn(new BasicEList<>());

        migrationUnderTest.migrateBefore(model, metamodel);

        verify(model).delete(jarFragment);
    }

    @Test
    void should_preserve_jar_fragments_in_other_container() throws Exception {
        Instance jarFragmentInOther = mock(Instance.class);
        Instance otherContainer = mock(Instance.class);
        when(jarFragmentInOther.get("type")).thenReturn("JAR");
        when(jarFragmentInOther.getContainer()).thenReturn(otherContainer);
        when(otherContainer.get("id")).thenReturn("OTHER");

        EList<Instance> fragments = new BasicEList<>();
        fragments.add(jarFragmentInOther);
        when(model.getAllInstances("configuration.Fragment")).thenReturn(fragments);
        when(model.getAllInstances("configuration.FragmentContainer")).thenReturn(new BasicEList<>());

        migrationUnderTest.migrateBefore(model, metamodel);

        verify(model, never()).delete(jarFragmentInOther);
    }

    @Test
    void should_not_delete_other_container() throws Exception {
        when(model.getAllInstances("configuration.Fragment")).thenReturn(new BasicEList<>());

        Instance otherContainer = mock(Instance.class);
        when(otherContainer.get("id")).thenReturn("OTHER");

        EList<Instance> containers = new BasicEList<>();
        containers.add(otherContainer);
        when(model.getAllInstances("configuration.FragmentContainer")).thenReturn(containers);

        migrationUnderTest.migrateBefore(model, metamodel);

        verify(model, never()).delete(otherContainer);
    }

    @Test
    void should_handle_mixed_fragments_correctly() throws Exception {
        // JAR fragment in OTHER container - should be preserved
        Instance jarInOther = mock(Instance.class);
        Instance otherContainer = mock(Instance.class);
        when(jarInOther.get("type")).thenReturn("JAR");
        when(jarInOther.getContainer()).thenReturn(otherContainer);
        when(otherContainer.get("id")).thenReturn("OTHER");

        // JAR fragment in CONNECTOR container - should be deleted
        Instance jarInConnector = mock(Instance.class);
        Instance connectorContainer = mock(Instance.class);
        when(jarInConnector.get("type")).thenReturn("JAR");
        when(jarInConnector.getContainer()).thenReturn(connectorContainer);
        when(connectorContainer.get("id")).thenReturn("CONNECTOR");

        // GROOVY_SCRIPT fragment - should be deleted
        Instance groovyFragment = mock(Instance.class);
        when(groovyFragment.get("type")).thenReturn("GROOVY_SCRIPT");

        EList<Instance> fragments = new BasicEList<>();
        fragments.add(jarInOther);
        fragments.add(jarInConnector);
        fragments.add(groovyFragment);
        when(model.getAllInstances("configuration.Fragment")).thenReturn(fragments);

        // GROOVY_SCRIPT container - should be deleted
        Instance groovyContainer = mock(Instance.class);
        when(groovyContainer.get("id")).thenReturn("GROOVY_SCRIPT");

        EList<Instance> containers = new BasicEList<>();
        containers.add(groovyContainer);
        when(model.getAllInstances("configuration.FragmentContainer")).thenReturn(containers);

        migrationUnderTest.migrateBefore(model, metamodel);

        verify(model, never()).delete(jarInOther);
        verify(model).delete(jarInConnector);
        verify(model).delete(groovyFragment);
        verify(model).delete(groovyContainer);
    }

}
