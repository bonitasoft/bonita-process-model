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
package org.bonitasoft.bpm.model.process.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.atomic.AtomicInteger;

import org.bonitasoft.bpm.model.util.ModelLoader.Prerequisite;
import org.junit.jupiter.api.Test;

/**
 * Test Prerequisite execution
 * 
 * @author Vincent Hemery
 */
class PrerequisiteTest {

    /**
     * Test that Prerequisite executes only once when executed twice
     * 
     * @throws Exception
     */
    @Test
    void testPrerequisiteExecutesOnceWhenReexecuted() throws Exception {
        AtomicInteger i = new AtomicInteger(0);
        Prerequisite req = new Prerequisite() {

            /*
             * (non-Javadoc)
             * @see org.bonitasoft.bpm.model.util.ModelLoader.Prerequisite#doRun()
             */
            @Override
            public void doRun() {
                i.incrementAndGet();
            }
        };
        req.run();
        req.run();
        assertThat(i.get()).isEqualTo(1);
    }

    /**
     * Test that the same Prerequisite is return when built from runnable several times
     * 
     * @throws Exception
     */
    @Test
    void testSamePrerequisiteWhenRunnableReused() throws Exception {
        AtomicInteger i = new AtomicInteger(0);
        i.getClass().getMethod("incrementAndGet");
        Runnable run = i::incrementAndGet;
        Prerequisite req1 = Prerequisite.fromRunnableWhenNotInOSGi(run);
        Prerequisite req2 = Prerequisite.fromRunnableWhenNotInOSGi(run);
        // but if you use Prerequisite.fromRunnableWhenNotInOSGi(i::incrementAndGet), that's a different runnable and a different prerequisite...
        assertThat(req1).isEqualTo(req2);
    }

}
