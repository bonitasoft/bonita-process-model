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
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import org.bonitasoft.bpm.model.util.FileUtil;
import org.junit.jupiter.api.Test;

/**
 * Test FileUtil
 * 
 * @author Vincent Hemery
 */
class FileUtilTest {

    /**
     * Test temp dir operation
     * 
     * @throws Exception
     */
    @Test
    void testTempDir() throws Exception {
        AtomicReference<File> file = new AtomicReference<>();
        FileUtil.withTempDir("pre", dir -> {
            file.set(dir.toFile());
            assertThat(file.get()).exists();
            assertThat(file.get().getName()).startsWith("pre");
        });
        // file must be deleted
        assertThat(file.get()).doesNotExist();
    }

    /**
     * Test temp dir operation throwing exception
     * 
     * @throws Exception
     */
    @Test
    void testTempDirException() throws Exception {
        String msg = "test IOException";
        try {
            FileUtil.withTempDir("pre", dir -> {
                throw new IOException(msg);
            });
            fail();
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo(msg);
        }
    }

    /**
     * Test temp file operation
     * 
     * @throws Exception
     */
    @Test
    void testTempFile() throws Exception {
        AtomicReference<File> file = new AtomicReference<>();
        FileUtil.withTempFile("file.ext", temp -> {
            file.set(temp.toFile());
            assertThat(file.get()).exists();
            assertThat(file.get().getName()).startsWith("file");
            assertThat(file.get().getName()).endsWith(".ext");
        });
        // file must be deleted
        assertThat(file.get()).doesNotExist();
    }

    /**
     * Test temp file operation throwing exception
     * 
     * @throws Exception
     */
    @Test
    void testTempFileException() throws Exception {
        String msg = "test IOException";
        try {
            FileUtil.withTempFile("file.ext", temp -> {
                throw new IOException(msg);
            });
            fail();
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo(msg);
        }
    }

}
