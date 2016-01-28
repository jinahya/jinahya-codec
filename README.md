# jinahya-codec


## examples
### hex-codec
```java
class HexSpecification extends Specification {
    public HexSpecification() {
        super(1, 2);
    }
}

class HexUnitEncoder implements UnitEncoder {
    @Override
    public void encode(final ByteBuffer decoded, final ByteBuffer encoded) {
        // @todo
        // encode one octet from decoded
        // and put two hex characters into encoded
    }
}

class HexEncoder extends AbstractEncoder {
    public HexEncoder() {
        super(HexSpecification.getInstance(), HexUnitEncoder.getInstance());
    }
}

class HexUnitDecoder implements UnitDecoder {
    @Override
    public void decode(final ByteBuffer encoded, final ByteBuffer decoded) {
        // @todo
        // decode two characters from encoded
        // and put one octet into decoded
        return 0;
    }
}

class HexDecoder extends AbstractDecoder {
    public HexDecoder() {
        super(HexSpecification.getInstance(), HexUnitDecoder.getInstance());
    }
}
```
### percent-codec
```java
class PercentSpecification extends Specification {
    public HexSpecification() {
        super(1, 1, 1, 3);
    }
}

class HexUnitEncoder implements UnitEncoder {
    @Override
    public void encode(final ByteBuffer decoded, final ByteBuffer encoded) {
        // @todo
        // encode one octet from decoded
        // and put one or three characters into encoded
    }
}

class HexUnitDecoder implements UnitDecoder {
    @Override
    public void decode(final ByteBuffer encoded, final ByteBuffer decoded) {
        // @todo
        // decode one or three characters from encoded
        // and put one octet into decoded
        return 0;
    }
}
```
