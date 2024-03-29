/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.bpm.model.process.builders;

import java.util.Arrays;

import org.bonitasoft.bpm.model.Buildable;
import org.bonitasoft.bpm.model.process.ContractConstraint;
import org.bonitasoft.bpm.model.process.ProcessFactory;

/**
 * @author Romain Bioteau
 */
public class ContractConstraintBuilder implements Buildable<ContractConstraint> {

    public static ContractConstraintBuilder aContractConstraint() {
        return new ContractConstraintBuilder(ProcessFactory.eINSTANCE.createContractConstraint());
    }

    private final ContractConstraint contractConstraint;

    private ContractConstraintBuilder(final ContractConstraint contractConstraint) {
        this.contractConstraint = contractConstraint;
    }

    public ContractConstraintBuilder withName(final String name) {
        contractConstraint.setName(name);
        return this;
    }

    public ContractConstraintBuilder withErrorMessage(final String errorMessage) {
        contractConstraint.setErrorMessage(errorMessage);
        return this;
    }

    public ContractConstraintBuilder withExpression(final String expression) {
        contractConstraint.setExpression(expression);
        return this;
    }

    public ContractConstraintBuilder havingInput(final String... inputNames) {
        contractConstraint.getInputNames().addAll(Arrays.asList(inputNames));
        return this;
    }

    @Override
    public ContractConstraint build() {
        return contractConstraint;
    }
}
