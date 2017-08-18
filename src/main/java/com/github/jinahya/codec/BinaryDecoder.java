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
     * Decodes byte from specified input buffer to specified output buffer.
     *
     * @param encoded the input buffer from which encoded bytes are read.
     * @param decoded the output buffer to which decoded bytes are written.
     * @return the number of units decoded
     */
    int decode(ByteBuffer encoded, ByteBuffer decoded);

    default int decodeFinal(final ByteBuffer encoded,
                            final ByteBuffer decoded) {
        return 0;
    }
}
