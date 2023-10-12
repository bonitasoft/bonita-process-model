/**
 * Copyright (C) 2023 Bonitasoft S.A.
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
package org.bonitasoft.bpm.model.process.util.migration.internal;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edapt.spi.migration.impl.InstanceImpl;

public class CustomInstanceImpl extends InstanceImpl {

    @Override
    public void set(EStructuralFeature feature, Object newValue) {
        if (feature.isMany()) {
            final Collection<?> oldValues = (Collection<?>) this.get(feature);
            for (final Object value : oldValues) {
                this.remove(feature, value);
            }
            final Collection<?> newValues = (Collection<?>) newValue;
            for (final Object value : newValues) {
                this.add(feature, value);
            }
        } else {
            addSingleValue(feature, newValue);
        }
    }

    private void addSingleValue(EStructuralFeature feature, Object newValue) {
        //Bonita Patch for Notation model
        if (newValue instanceof List<?> && feature.getEType() != null
                && feature.getEType().getInstanceClass() != null
                && Collection.class.isAssignableFrom(feature.getEType().getInstanceClass())) {
            addNewValue(feature, newValue);
        } else if (newValue instanceof List<?> && feature.getEType() != null
                && (feature.getEType().isInstance(EcorePackage.Literals.EJAVA_OBJECT)
                        || feature.getEType() instanceof EEnum)) {
            addNewValue(feature, newValue);
        } else {
            addValue(feature, newValue);
        }
    }

    private void addValue(EStructuralFeature feature, Object newValue) {
        if (newValue instanceof List<?>) {
            throw new IllegalArgumentException("Single value expected, but list found"); //$NON-NLS-1$
        }
        final Object oldValue = this.get(feature);
        if (oldValue != newValue || feature.isUnsettable()) {
            if (isSet(feature) && oldValue != null) {
                this.remove(feature, oldValue);
            }
            if (newValue != null || feature.isUnsettable()) {
                this.add(feature, newValue);
            }
        }
    }

    private void addNewValue(EStructuralFeature feature, Object newValue) {
        final Object oldValue = this.get(feature);
        if (oldValue != newValue) {
            if (isSet(feature) && oldValue != null) {
                this.remove(feature, oldValue);
            }
            this.add(feature, newValue);
        }
    }

}
