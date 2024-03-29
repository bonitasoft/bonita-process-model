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
package org.bonitasoft.bpm.model.process.util.migration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edapt.internal.common.ResourceUtils;
import org.eclipse.emf.edapt.spi.history.History;
import org.eclipse.emf.edapt.spi.history.HistoryPackage;
import org.eclipse.emf.edapt.spi.history.Release;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.osgi.framework.Version;

class ModelHistoryTest {

    private History history;

    @BeforeEach
    void setUp() throws Exception {
        final URL resource = Platform.getBundle("org.bonitasoft.bpm.model").getResource("model/process.history");
        if (resource == null) {
            fail("Cannot find process.histroy resource in rg.bonitasoft.bpm.model bundle.");
        }
        final URI historyURI = URI.createFileURI(new File(FileLocator.toFileURL(resource).getFile()).getAbsolutePath());
        HistoryPackage.eINSTANCE.getHistory();
        history = ResourceUtils.loadElement(historyURI);
    }

    @Test
    void should_history_last_release_has_no_name_and_date() throws Exception {
        final Release release = history.getLastRelease();
        assertThat(release.getLabel()).isNull();
        assertThat(release.getDate()).isNull();
    }

    @Test
    void should_history_latest_release_label_equals_current_model_version() throws Exception {
        final Release release = history.getLatestRelease();
        assertThat(release.getLabel()).isEqualTo(HistoryUtils.CURRENT_MODEL_VERSION);
    }

    @Test
    void keepSyncBetweenModelAndComponentVersion() throws Exception {
        Version componentVersion = Platform.getBundle("org.bonitasoft.bpm.model").getVersion();
        assertThat(Integer.toString(componentVersion.getMajor())).isEqualTo(HistoryUtils.CURRENT_MODEL_VERSION);
    }

}
