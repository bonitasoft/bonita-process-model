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

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * An {@link InputStream} that can duplicate itself while not necessarily closing the original {@link InputStream}.
 * <p>Duplicates are a way to get copies of this stream, which will let bytes from the buffer available to other copies and not fully consume and close the
 * whole stream.</p>
 * <p>They are intended for premature ending. They can not read further back than this stream's buffer, so they are fit only for short consumption (shorter than
 * the <code>size</code> argument)</p>
 * <p>If buffered has been fully filled, behavior is not guaranteed.</p>
 * 
 * @author Vincent Hemery
 */
public class DuplicatingInputStream extends BufferedInputStream {

    /**
     * Creates a <code>DuplicatingInputStream</code> and saves its argument, the input stream <code>in</code>, for later use. An internal buffer array is
     * created and stored in <code>buf</code>.
     *
     * @param in the underlying input stream.
     */
    public DuplicatingInputStream(InputStream in) {
        super(in);
    }

    /**
     * Creates a <code>DuplicatingInputStream</code> with the specified buffer size, and saves its argument, the input stream <code>in</code>, for later use. An
     * internal buffer array of length <code>size</code> is created and stored in <code>buf</code>.
     *
     * @param in the underlying input stream.
     * @param size the buffer size.
     * @exception IllegalArgumentException if {@code size <= 0}.
     */
    public DuplicatingInputStream(InputStream in, int size) {
        super(in, size);
    }

    /**
     * Get a duplicate of the input stream, which does not close the original input stream so other duplicates can consume it too.
     * <p>First, duplicates will try and get input from the buffer.</p>
     * <p>Then, duplicates will consume this stream as they are consumed, but are not consumed by other duplicates created alongside.</p>
     * <p>It is also recommended closing this stream before opening another copy.</p>
     * 
     * @return an {@link InputStream} that will not close the initial input stream
     */
    public InputStream getNonClosingStreamCopy() {
        final ByteArrayInputStream streamOnBuffer = new ByteArrayInputStream(buf);
        return new InputStream() {

            @Override
            public int available() throws IOException {
                return getAvailableForCopy(streamOnBuffer);
            }

            @Override
            public int read() throws IOException {
                return readForCopy(streamOnBuffer);
            }

            @Override
            public void close() throws IOException {
                streamOnBuffer.close();
                // do not close the real stuff
            }
        };
    }

    /**
     * Get a duplicate of the input stream, which will close this and the original input stream.
     * <p>Like duplicates, the master copy will consume this stream as it is consumed, but it is not consumed by duplicates created alongside.</p>
     * 
     * @return an {@link InputStream} that will not close the input stream
     */
    public InputStream getClosingMasterStreamCopy() {
        final ByteArrayInputStream streamOnBuffer = new ByteArrayInputStream(buf);
        return new InputStream() {

            @Override
            public int available() throws IOException {
                return getAvailableForCopy(streamOnBuffer);
            }

            @Override
            public int read() throws IOException {
                return readForCopy(streamOnBuffer);
            }

            @Override
            public void close() throws IOException {
                streamOnBuffer.close();
                // and close the real stuff
                DuplicatingInputStream.this.close();
            }
        };
    }

    /**
     * Get the result for available method for an input stream copy
     * 
     * @param streamOnBuffer the buffer's input stream which the copy relies on
     * @return an estimation of available bits
     * @exception IOException if this input stream has been closed by invoking its {@link #close()} method, or an I/O error occurs.
     */
    private synchronized int getAvailableForCopy(ByteArrayInputStream streamOnBuffer) throws IOException {
        /*
         * It's hard to know how much of the buffer or original stream has been consumed.
         * So we'll make simple under-estimations.
         */
        int notConsumed = DuplicatingInputStream.this.available();
        if (notConsumed > 0) {
            // at least this amount is not consumed
            return notConsumed;
        } else {
            // everything should have been buffered, let's see how much of the filled buffer is left
            return streamOnBuffer.available() - getBufferPortionNotToRead();
        }
    }

    /**
     * Get the size of the buffer portion which must not be read because it has not been filled this this stream.
     * 
     * @return size of buffer not assigned
     * @throws IOException when buffer has been nullified / stream was closed
     */
    private synchronized int getBufferPortionNotToRead() throws IOException {
        return Optional.ofNullable(buf).orElseThrow(IOException::new).length - pos;
    }

    /**
     * @param streamOnBuffer
     * @return
     * @throws IOException
     */
    private synchronized int readForCopy(final ByteArrayInputStream streamOnBuffer) throws IOException {
        // read on buffer while we can
        if (streamOnBuffer.available() > getBufferPortionNotToRead()) {
            return streamOnBuffer.read();
        } else {
            // buffer consumed, start consuming the real stuff
            if (streamOnBuffer.available() > 0) {
                // but do not consume it again later from buffer
                long skip1 = streamOnBuffer.skip(1);
                if (skip1 != 1L) {
                    throw new IOException();
                }
            }
            return read();
        }
    }

}
