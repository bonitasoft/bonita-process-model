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
package org.bonitasoft.bonita2bpmn.transfo.data;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.omg.spec.bpmn.model.ModelFactory;
import org.omg.spec.bpmn.model.TActivity;
import org.omg.spec.bpmn.model.TCallableElement;
import org.omg.spec.bpmn.model.TDataInput;
import org.omg.spec.bpmn.model.TInputOutputSpecification;
import org.omg.spec.bpmn.model.TInputSet;

public class DataIOTransformer {

    private DataIOTransformer() {
        // private constructor
    }

    public static TDataInput fillIOSpecification(final TCallableElement callableElement,
            final QName dataItemDefinitionIdAsQname) {
        TInputOutputSpecification tInputOutputAssociation = callableElement.getIoSpecification();
        if (tInputOutputAssociation == null) {
            tInputOutputAssociation = ModelFactory.eINSTANCE.createTInputOutputSpecification();
            tInputOutputAssociation.setId(EcoreUtil.generateUUID());
            callableElement.setIoSpecification(tInputOutputAssociation);
        }
        return fillIOSpecificationWithNewDataInput(dataItemDefinitionIdAsQname, tInputOutputAssociation);
    }

    public static TDataInput fillIOSpecificationWithNewDataInput(final TActivity tActivity,
            final QName dataItemDefinitionIdAsQname) {
        TInputOutputSpecification tInputOutputAssociation = tActivity.getIoSpecification();
        if (tInputOutputAssociation == null) {
            tInputOutputAssociation = ModelFactory.eINSTANCE.createTInputOutputSpecification();
            tInputOutputAssociation.setId(EcoreUtil.generateUUID());
            tActivity.setIoSpecification(tInputOutputAssociation);
        }
        return fillIOSpecificationWithNewDataInput(dataItemDefinitionIdAsQname, tInputOutputAssociation);
    }

    private static TDataInput fillIOSpecificationWithNewDataInput(final QName dataItemDefinitionIdAsQname,
            final TInputOutputSpecification tInputOutputAssociation) {
        final TDataInput tDataInput = ModelFactory.eINSTANCE.createTDataInput();
        tDataInput.setItemSubjectRef(dataItemDefinitionIdAsQname);
        tDataInput.setId(EcoreUtil.generateUUID());

        final TInputSet tInputSet = ModelFactory.eINSTANCE.createTInputSet();
        tInputSet.setId(EcoreUtil.generateUUID());
        tInputSet.getDataInputRefs().add(tDataInput.getId());
        final EList<TDataInput> dataInput = tInputOutputAssociation.getDataInput();
        final EList<TInputSet> inputSet = tInputOutputAssociation.getInputSet();
        inputSet.add(tInputSet);
        dataInput.add(tDataInput);
        return tDataInput;
    }

}
