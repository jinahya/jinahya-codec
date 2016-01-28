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


import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import static java.nio.ByteBuffer.allocate;
import static java.nio.ByteBuffer.wrap;
import java.nio.charset.Charset;
import static java.util.Objects.requireNonNull;
import java.util.function.IntConsumer;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public abstract class AbstractEncoder implements Encoder {


    public AbstractEncoder(final Specification specification,
                           final UnitEncoder encoder) {

        super();

        this.specification = requireNonNull(specification);
        this.encoder = requireNonNull(encoder);
    }


    @Override
    public int encode(final ByteBuffer decoded, final ByteBuffer encoded) {

        int count = 0;

        while (decoded.remaining() >= specification.minDecodedUnitBytes()
               && encoded.remaining() >= specification.minEncodedUnitBytes()) {

            final int decodedPosition = decoded.position();
            final int encodedPosition = encoded.position();
            try {
                encoder.encode(decoded, encoded);
            } catch (BufferUnderflowException | BufferOverflowException e) { // NOSONAR
                decoded.position(decodedPosition);
                encoded.position(encodedPosition);
                break;
            }

            count++;
        }

        return count;
    }


    @Override
    public ByteBuffer encode(final ByteBuffer decoded,
                             final IntConsumer consumer) {

        final int capacity = specification.maxEncodedBytes(decoded.remaining());
        final ByteBuffer encoded = allocate(capacity);

        final int count = encode(decoded, encoded);
        if (consumer != null) {
            consumer.accept(count);
        }

        encoded.flip();
        return encoded;
    }


    @Override
    public String encode(final String decoded, final Charset decodedCharset,
                         final Charset encodedCharset) {

        final byte[] decodedBytes = decoded.getBytes(decodedCharset);
        final int encodedCapacity
            = specification.maxEncodedBytes(decodedBytes.length);
        final ByteBuffer encodedBuffer = ByteBuffer.allocate(encodedCapacity);

        final int units = encode(wrap(decodedBytes), encodedBuffer);

        final String encoded = new String(
            encodedBuffer.array(), 0, encodedBuffer.position(), encodedCharset);
        return encoded;
    }


    private final Specification specification;


    private final UnitEncoder encoder;

}

