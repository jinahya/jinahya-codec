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


/**
 *
 * @author Jin Kwon &lt;jinahya_at_gmail.com&gt;
 */
public class Specification {


    public Specification(final int minDecodedUnitBytes,
                         final int maxDecodedUnitBytes,
                         final int minEncodedUnitBytes,
                         final int maxEncodedUnitBytes) {

        super();

        if (minDecodedUnitBytes <= 0) {
            throw new IllegalArgumentException(
                "minDecodedUnitBytes(" + minDecodedUnitBytes + ") <= 0");
        }

        if (maxDecodedUnitBytes <= 0) {
            throw new IllegalArgumentException(
                "maxDecodedUnitBytes(" + maxDecodedUnitBytes + ") <= 0");
        }

        if (minEncodedUnitBytes <= 0) {
            throw new IllegalArgumentException(
                "minEncodedUnitBytes(" + minEncodedUnitBytes + ") <= 0");
        }

        if (maxEncodedUnitBytes <= 0) {
            throw new IllegalArgumentException(
                "maxEncodedUnitBytes(" + maxEncodedUnitBytes + ") <= 0");
        }

        this.minDecodedUnitBytes = minDecodedUnitBytes;
        this.maxDecodedUnitBytes = maxDecodedUnitBytes;
        this.minEncodedUnitBytes = minEncodedUnitBytes;
        this.maxEncodedUnitBytes = maxEncodedUnitBytes;
    }


    public Specification(final int decodedUnitBytes,
                         final int encodedUnitBytes) {

        this(decodedUnitBytes, decodedUnitBytes, encodedUnitBytes,
             encodedUnitBytes);
    }


    public int minDecodedUnitBytes() {

        return minDecodedUnitBytes;
    }


    public int maxDecodedUnitBytes() {

        return maxDecodedUnitBytes;
    }


    public int minEncodedUnitBytes() {

        return minEncodedUnitBytes;
    }


    public int maxEncodedUnitBytes() {

        return maxEncodedUnitBytes;
    }


    public int maxEncodedBytes(final int decodedBytes) {

        return decodedBytes / minDecodedUnitBytes() * maxEncodedUnitBytes();
    }


    public int maxDecodedBytes(final int encodedBytes) {

        return encodedBytes / minEncodedUnitBytes() * maxDecodedUnitBytes();
    }


    private final int minDecodedUnitBytes;


    private final int maxDecodedUnitBytes;


    private final int minEncodedUnitBytes;


    private final int maxEncodedUnitBytes;

}

