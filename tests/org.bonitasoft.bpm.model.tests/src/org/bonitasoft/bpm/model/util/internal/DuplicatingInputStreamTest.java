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
package org.bonitasoft.bpm.model.util.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

/**
 * Test duplicated stream reading
 */
public class DuplicatingInputStreamTest {

    @Test
    public void testDuplicatedStreamReading() throws IOException {
        // create original input stream
        String azString = IntStream.rangeClosed('a', 'z').mapToObj(c -> Character.valueOf((char) c).toString())
                .collect(Collectors.joining());
        assertEquals(26, azString.length());
        ByteArrayInputStream in = new ByteArrayInputStream(azString.getBytes());
        // test duplication
        try (DuplicatingInputStream tested = new DuplicatingInputStream(in)) {
            // create 1st copy and read a few bytes
            try (InputStream copy = tested.getNonClosingStreamCopy()) {
                assertEquals('a', copy.read());
                assertEquals('b', copy.read());
                assertEquals('c', copy.read());
            }
            // create 2nd copy
            try (InputStream copy = tested.getNonClosingStreamCopy()) {
                assertEquals('a', copy.read());
                assertEquals('b', copy.read());
                assertEquals('c', copy.read());
            }
            // create a 3rd copy read simultaneously with master
            try (InputStream copy = tested.getNonClosingStreamCopy()) {
                try (InputStream master = tested.getClosingMasterStreamCopy()) {
                    assertEquals('a', master.read());
                    assertEquals('a', copy.read());
                    assertEquals('b', master.read());
                    assertEquals('b', copy.read());
                    assertEquals('c', copy.read());
                    assertEquals('d', copy.read());
                    assertEquals('c', master.read());
                    assertEquals('d', master.read());
                    assertEquals('e', master.read());
                    master.skip(20);
                    assertEquals('z', master.read());
                    assertEquals(0, master.available());
                }
                // copy should no longer be able to read, because master has closed
                assertThrows(IOException.class, copy::read);
            }
        }
    }

    @Test
    public void testDuplicatedStreamArrayReading() throws IOException {
        // create original input stream
        String azString = IntStream.rangeClosed('a', 'z').mapToObj(c -> Character.valueOf((char) c).toString())
                .collect(Collectors.joining());
        assertEquals(26, azString.length());
        ByteArrayInputStream in = new ByteArrayInputStream(azString.getBytes());
        // test duplication
        try (DuplicatingInputStream tested = new DuplicatingInputStream(in)) {
            // create a copy read simultaneously with master
            try (InputStream copy = tested.getNonClosingStreamCopy()) {
                try (InputStream master = tested.getClosingMasterStreamCopy()) {
                    // read with array
                    byte[] arr = new byte[5];
                    assertEquals(2, master.read(arr, 0, 2));
                    assertEquals("ab", new String(arr, 0, 2));
                    assertEquals(3, copy.read(arr, 0, 3));
                    assertEquals("abc", new String(arr, 0, 3));
                    assertEquals(3, master.read(arr, 2, 3));
                    assertEquals("abcde", new String(arr, 0, 5));
                    assertEquals(2, copy.read(arr, 3, 2));
                    assertEquals("abcde", new String(arr, 0, 5));
                }
                // copy should no longer be able to read, because master has closed
                assertThrows(IOException.class, copy::read);
            }
        }
    }

    @Test
    public void testDuplicatedStreamFullReading() throws IOException {
        // create original input stream
        String azString = IntStream.rangeClosed('a', 'z').mapToObj(c -> Character.valueOf((char) c).toString())
                .collect(Collectors.joining());
        assertEquals(26, azString.length());
        ByteArrayInputStream in = new ByteArrayInputStream(azString.getBytes());
        // test duplication
        try (DuplicatingInputStream tested = new DuplicatingInputStream(in)) {
            // create a copy read simultaneously with master
            try (InputStream copy = tested.getNonClosingStreamCopy()) {
                try (InputStream master = tested.getClosingMasterStreamCopy()) {
                    // read all bytes from the copy and from master
                    assertEquals(azString, new String(copy.readAllBytes()));
                    assertEquals(azString, new String(master.readAllBytes()));
                }
                // copy should no longer be able to read, because master has closed
                assertThrows(IOException.class, copy::read);
            }
        }
    }

}
