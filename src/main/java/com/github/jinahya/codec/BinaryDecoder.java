/*
 * Copyright 2016 Jin Kwon &lt;jinahya_at_gmail.com&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jinahya.codec;

import java.nio.ByteBuffer;

/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public interface BinaryDecoder {

    /**
     * Decodes bytes from specified input buffer to specified output buffer.
     *
     * @param encoded the input buffer from which encoded bytes are read.
     * @param decoded the output buffer to which decoded bytes are written.
     * @return the number of units decoded
     */
    int decodeUpdate(ByteBuffer encoded, ByteBuffer decoded);

    /**
     * Decodes data in a single-part operation, or finishes a multiple-part
     * operation. The {@code decodeFinal} method of {@code BinaryDecoder}
     * interface invokes
     * {@link #decodeUpdate(jafva.nio.ByteBuffer, java.nio.ByteBuffer)} while
     * the result is greater than zero and returns the total number of units
     * processed. Override this method if more finalization is required.
     *
     * @param encoded the input buffer from which encoded bytes are read.
     * @param decoded the output buffer to which decoded bytes are written.
     * @return the number of units decoded.
     */
    default int decodeFinal(final ByteBuffer encoded,
                            final ByteBuffer decoded) {
        if (encoded == null) {
            throw new NullPointerException("encoded is null");
        }
        if (decoded == null) {
            throw new NullPointerException("decoded is null");
        }
        int total = 0;
        for (int c; (c = decodeUpdate(encoded, decoded)) > 0; total += c) {
        }
        return total;
    }
}
