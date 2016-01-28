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
import static java.util.Objects.requireNonNull;
import java.util.function.IntConsumer;


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public abstract class AbstractDecoder implements Decoder {


    public AbstractDecoder(final Specification specification,
                           final UnitDecoder decoder) {

        super();

        this.specification = requireNonNull(specification);
        this.decoder = requireNonNull(decoder);
    }


    @Override
    public int decode(final ByteBuffer encoded, final ByteBuffer decoded) {

        int units = 0;

        for (int encodedPosition, decodedPosition;
             encoded.remaining() >= specification.minDecodedUnitBytes()
             && decoded.remaining() >= specification.minEncodedUnitBytes();) {

            encodedPosition = encoded.position();
            decodedPosition = decoded.position();
            try {
                decoder.decode(encoded, decoded);
            } catch (BufferUnderflowException | BufferOverflowException e) { // NOSONAR
                encoded.position(encodedPosition);
                decoded.position(decodedPosition);
                break;
            }

            units++;
        }

        return units;
    }


    @Override
    public ByteBuffer decode(final ByteBuffer encoded,
                             final IntConsumer consumer) {

        final int capacity = specification.maxEncodedBytes(encoded.remaining());
        final ByteBuffer decoded = allocate(capacity);

        final int consumee = decode(encoded, decoded);
        if (consumer != null) {
            consumer.accept(consumee);
        }

        decoded.flip();
        return decoded;
    }


    private final Specification specification;


    private final UnitDecoder decoder;

}

