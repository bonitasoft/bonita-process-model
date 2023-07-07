/** 
 * Copyright (C) 2014 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.bpm.migration.custom.migration.form;

import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 */
public class FormMappingCustomMigration extends CustomMigration {

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel) throws MigrationException {
        var pageFlows = model.getAllInstances("process.PageFlow").stream().filter(
                withoutFormMapping(ProcessPackage.Literals.PAGE_FLOW__FORM_MAPPING)).collect(Collectors.toList());
        for (final Instance instance : pageFlows) {
            instantiateFormMapping(instance, model, "formMapping");
        }

        var recapFlows = model.getAllInstances("process.RecapFlow").stream().filter(
                withoutFormMapping(ProcessPackage.Literals.RECAP_FLOW__OVERVIEW_FORM_MAPPING))
                .collect(Collectors.toList());
        for (final Instance instance : recapFlows) {
            instantiateFormMapping(instance, model, "overviewFormMapping");
        }
    }

    private Predicate<Instance> withoutFormMapping(final EStructuralFeature feature) {
        return input -> !input.isSet(feature);
    }

    private void instantiateFormMapping(final Instance input, final Model model, final String featureName) {
        final Instance newInstance = model.newInstance("process.FormMapping");
        final Instance targetFormExpression = newTargetFormExpressionInstance(model);
        newInstance.set("targetForm", targetFormExpression);
        newInstance.set("type", model.getMetamodel().getEEnum("process.FormMappingType").getEEnumLiteral("INTERNAL"));
        input.set(featureName, newInstance);
    }

    private Instance newTargetFormExpressionInstance(final Model model) {
        final Instance targetFormExpression = model.newInstance("expression.Expression");
        targetFormExpression.set("name", "");
        targetFormExpression.set("content", "");
        targetFormExpression.set("returnType", String.class.getName());
        targetFormExpression.set("type", ExpressionConstants.FORM_REFERENCE_TYPE);
        targetFormExpression.set("returnTypeFixed", true);
        return targetFormExpression;
    }

}
