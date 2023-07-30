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
package org.bonitasoft.bpm.migration.custom.migration.multiinstance;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.bonitasoft.bpm.migration.utils.StringToExpressionConverter;
import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.bpm.model.util.ExpressionConstants;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.Model;

/**
 * @author Romain Bioteau
 */
public class CallActivityInputMappingCustomMigration extends CustomMigration {

    private final Map<String, Instance> dataInstances = new HashMap<>();

    @Override
    public void migrateBefore(final Model model, final Metamodel metamodel) throws MigrationException {
        final Iterable<Instance> instancesWithProcessSource = model.getAllInstances("process.InputMapping").stream()
                .filter(withProcessSource()).collect(Collectors.toList());
        for (final Instance instance : instancesWithProcessSource) {
            dataInstances.put(instance.getUuid(),
                    ((Instance) instance.get(ProcessPackage.Literals.INPUT_MAPPING__PROCESS_SOURCE.getName())).copy());
        }
    }

    private Predicate<Instance> withProcessSource() {
        return inputMappingInstance -> inputMappingInstance.get("processSource") instanceof Instance;
    }

    protected Map<String, Instance> getDataInstances() {
        return dataInstances;
    }

    @Override
    public void migrateAfter(final Model model, final Metamodel metamodel) throws MigrationException {
        final Iterable<Instance> instancesToMigrate = model.getAllInstances("process.InputMapping").stream().filter(
                matchingUUID()).collect(Collectors.toList());
        for (final Instance instance : instancesToMigrate) {
            convertDataToExpression(model, instance);
        }
    }

    private void convertDataToExpression(final Model model, final Instance inputMappingInstance) {
        final Instance dataInstance = dataInstances.get(inputMappingInstance.getUuid());
        final String name = dataInstance.get("name");
        final Instance defaultValueExpression = dataInstance.get("defaultValue");
        if (defaultValueExpression != null) {
            model.delete(defaultValueExpression);
        }
        final Instance expressionInstance = StringToExpressionConverter.createExpressionInstanceWithDependency(model,
                name,
                name,
                StringToExpressionConverter.getDataReturnType(dataInstance),
                ExpressionConstants.VARIABLE_TYPE,
                false,
                dataInstance);
        model.delete(dataInstance);
        inputMappingInstance.set("processSource", expressionInstance);
    }

    private Predicate<Instance> matchingUUID() {
        return inputMappingInstance -> dataInstances.containsKey(inputMappingInstance.getUuid());
    }

}
