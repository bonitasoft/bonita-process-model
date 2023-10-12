/**
 * Copyright (C) 2013 Bonitasoft S.A.
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
package org.bonitasoft.bpm.migration.utils;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edapt.spi.migration.Instance;

/**
 * @author Florine Boudin
 */
public class CustomMigrationUtil {

    /**
     * Private util constructor
     */
    private CustomMigrationUtil() {
        // do nothing
    }

    public static Instance deepCopy(Instance instance) {
        // mapping of originals to copies
        final Map<Instance, Instance> map = new HashMap<>();

        // copy tree structure
        return copyTree(instance, map);
    }

    /** Copy the tree structure with an instance as root. */
    private static Instance copyTree(Instance original, Map<Instance, Instance> map) {
        final EClass eClass = original.getEClass();
        final Instance copi = original.getType().getModel().newInstance(eClass);
        for (final EReference reference : eClass.getEAllReferences()) {
            if (reference.isContainment()) {
                if (reference.isMany()) {
                    for (final Instance child : original.getLinks(reference)) {
                        copi.add(reference, copyTree(child, map));
                    }
                } else {
                    final Instance child = original.get(reference);
                    if (child != null) {
                        copi.set(reference, copyTree(child, map));
                    }
                }
            } else {
                if (reference.isMany()) {
                    if (reference.getEOpposite() == null
                            || reference.getEOpposite().isMany()) {
                        for (Instance ref : original.getLinks(reference)) {
                            if (map.get(ref) != null) {
                                ref = map.get(ref);
                            }
                            copi.add(reference, ref);
                        }
                    }
                } else {
                    if (reference.getEOpposite() == null
                            || !reference.getEOpposite().isContainment()) {
                        Instance ref = original.get(reference);
                        if (ref != null) {
                            if (map.get(ref) != null) {
                                ref = map.get(ref);
                            }
                            copi.set(reference, ref);
                        }
                    }
                }
            }
        }
        for (final EAttribute attribute : eClass.getEAllAttributes()) {
            copi.set(attribute, original.get(attribute));
        }
        map.put(original, copi);
        return copi;
    }

}
