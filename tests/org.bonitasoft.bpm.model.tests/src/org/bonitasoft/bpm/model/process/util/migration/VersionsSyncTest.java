/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.bpm.model.process.util.migration;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.junit.Test;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.Version;

/**
 * This test ensures the model version is consistent with the model version.
 * 
 * @author Vincent Hemery
 */
public class VersionsSyncTest {

    @Test
    public void testModelVersionAgainstComponent() throws Exception {
        // get model version from history
        String modelVersion = HistoryUtils.CURRENT_MODEL_VERSION;
        // model version must be just the major ! No minor allowed.
        int intVersion = Integer.parseInt(modelVersion, 10);
        assertThat(intVersion).isPositive();
        // now, find the component version
        Version componentVersion = FrameworkUtil.getBundle(ProcessPackage.class).getVersion();
        // and ensure the model version matches the major digit
        assertThat(intVersion).isEqualTo(componentVersion.getMajor());
    }

}
