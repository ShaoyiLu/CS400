public interface IterableMapADT<KeyType, ValueType> extends MapADT<KeyType, ValueType>, Iterable<ValueType> {

    // this interface combines MapADT with Iterable and defines no additional methods
    // in addition to the ones it inherits from both these interfaces

}