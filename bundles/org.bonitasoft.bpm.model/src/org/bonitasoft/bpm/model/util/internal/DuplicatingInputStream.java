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
     * A copy input stream
     */
    private class InputStreamCopy extends InputStream {

        /** The input stream working on buffer */
        private final ByteArrayInputStream streamOnBuffer;
        /** <code>true</code> when this is the master copy */
        private boolean isMaster;

        /**
         * Default Constructor.
         * 
         * @param streamOnBuffer input stream working on buffer
         * @param isMaster <code>true</code> when this is the master copy
         */
        private InputStreamCopy(ByteArrayInputStream streamOnBuffer, boolean isMaster) {
            this.streamOnBuffer = streamOnBuffer;
            this.isMaster = isMaster;
        }

        @Override
        public int available() throws IOException {
            synchronized (DuplicatingInputStream.this) {
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
        }

        @Override
        public int read() throws IOException {
            synchronized (DuplicatingInputStream.this) {
                // read on buffer while we can
                int res;
                if (streamOnBuffer.available() > getBufferPortionNotToRead()) {
                    res = streamOnBuffer.read();
                } else {
                    // buffer consumed, start consuming the real stuff
                    if (streamOnBuffer.available() > 0) {
                        // but do not consume it again later from buffer
                        long skip1 = streamOnBuffer.skip(1);
                        if (skip1 != 1L) {
                            throw new IOException();
                        }
                    }
                    res = DuplicatingInputStream.this.read();
                }
                // update buffer position which we will not loose
                updateBufferPosition();
                return res;
            }
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            synchronized (DuplicatingInputStream.this) {
                // read on buffer while we can
                int readableOnBuffer = streamOnBuffer.available() - getBufferPortionNotToRead();
                int res;
                if (len == 0) {
                    res = 0;
                } else if (readableOnBuffer >= len) {
                    res = streamOnBuffer.read(b, off, len);
                } else {
                    final int readOnBuffer;
                    final int toReadOnStream;
                    if (readableOnBuffer > 0) {
                        readOnBuffer = streamOnBuffer.read(b, off, Math.min(len, readableOnBuffer));
                        toReadOnStream = len - readOnBuffer;
                    } else {
                        readOnBuffer = 0;
                        toReadOnStream = len;
                    }
                    // buffer consumed, start consuming the real stuff
                    if (streamOnBuffer.available() > 0) {
                        // but do not consume it again later from buffer
                        long skipN = streamOnBuffer.skip(toReadOnStream);
                        if (skipN > toReadOnStream) {
                            throw new IOException();
                        }
                    }
                    /*
                     * If the requested length is at least as large as the buffer,
                     * buffer won't get filled and this will break duplication.
                     * Keep length small enough.
                     */
                    int readFromStream = paginatedReadArrayFromStream(b, off, readOnBuffer, toReadOnStream);
                    res = readOnBuffer + readFromStream;
                }
                // update buffer position which we will not loose
                updateBufferPosition();
                return res;
            }
        }

        /**
         * Read array of bytes from stream with pagination
         * 
         * @param b destination buffer.
         * @param off offset at which to start storing bytes.
         * @param readOnBuffer number of bytes already read on buffer
         * @param toReadOnStream maximum number of bytes to read on stream
         * @return number of bytes read on stream or <code>-1</code> if the end of the stream has been reached
         * @exception IOException if this input stream has been closed by invoking its {@link #close()} method, or an I/O error occurs.
         */
        private int paginatedReadArrayFromStream(byte[] b, int off, final int readOnBuffer, final int toReadOnStream)
                throws IOException {
            int readFromStream = 0;
            int stillToRead = toReadOnStream;
            while (stillToRead > 0) {
                int justRead;
                if (stillToRead >= DuplicatingInputStream.this.buf.length) {
                    // only read a page
                    int pageToRead = DuplicatingInputStream.this.buf.length - 1;
                    justRead = DuplicatingInputStream.this.read(b, readOnBuffer + off, pageToRead);
                } else {
                    justRead = DuplicatingInputStream.this.read(b, readOnBuffer + off, toReadOnStream);
                }
                stillToRead -= justRead;
                if (justRead < 0) {
                    // end of stream
                    stillToRead = 0;
                    if (readOnBuffer == 0 && readFromStream == 0) {
                        readFromStream = -1;
                    }
                } else {
                    readFromStream += justRead;
                    updateBufferPosition();
                }
            }
            return readFromStream;
        }

        @Override
        public void close() throws IOException {
            streamOnBuffer.close();
            // do not close the real stuff, unless this is the master copy
            if (isMaster) {
                DuplicatingInputStream.this.close();
            }
        }

        /**
         * Update buffer position, but do not loose it when stream reaches end
         */
        private void updateBufferPosition() {
            if (DuplicatingInputStream.this.pos != 0) {
                bufferPosition = DuplicatingInputStream.this.pos;
            }
        }

        /**
         * Get the size of the buffer portion which must not be read because it has not been filled this this stream.
         * 
         * @return size of buffer not assigned
         * @throws IOException when buffer has been nullified / stream was closed
         */
        private int getBufferPortionNotToRead() throws IOException {
            return Optional.ofNullable(buf).orElseThrow(IOException::new).length - bufferPosition;
        }
    }

    /** The current position in buffer, not thrown away at ending */
    private int bufferPosition = 0;

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
        return new InputStreamCopy(streamOnBuffer, false);
    }

    /**
     * Get a duplicate of the input stream, which will close this and the original input stream.
     * <p>Like duplicates, the master copy will consume this stream as it is consumed, but it is not consumed by duplicates created alongside.</p>
     * 
     * @return an {@link InputStream} that will not close the input stream
     */
    public InputStream getClosingMasterStreamCopy() {
        final ByteArrayInputStream streamOnBuffer = new ByteArrayInputStream(buf);
        return new InputStreamCopy(streamOnBuffer, true);
    }

}
