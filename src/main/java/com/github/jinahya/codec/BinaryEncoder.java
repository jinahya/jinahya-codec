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
public interface BinaryEncoder {

    /**
     * Encodes data from specified input buffer to specified output buffer.
     *
     * @param decoded the input buffer from which decoded data are read.
     * @param encoded the output buffer to which encoded data are written.
     * @return the number of units processed.
     */
    int encodeUpdate(ByteBuffer decoded, ByteBuffer encoded);

    /**
     * Encodes data in a single-part operation, or finishes a multiple-part
     * operation. The {@code encodeFinal} method of {@code BinaryEncoder}
     * interface repeatedly invokes
     * {@link #encodeUpdate(java.nio.ByteBuffer, java.nio.ByteBuffer)} while the
     * result is greater than zero and returns the total number of units
     * encoded. Override this method if more finalization is required.
     *
     * @param decoded the input buffer from which decoded bytes are read.
     * @param encoded the output buffer from which encoded bytes are written.
     * @return the number of units encoded.
     */
    default int encodeFinal(final ByteBuffer decoded,
                            final ByteBuffer encoded) {
        if (decoded == null) {
            throw new NullPointerException("decoded is null");
        }
        if (encoded == null) {
            throw new NullPointerException("encoded is null");
        }
        int total = 0;
        for (int c; (c = encodeUpdate(decoded, encoded)) > 0; total += c) {
            // empty
        }
        return total;
    }
}
