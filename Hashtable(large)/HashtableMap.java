import java.util.LinkedList;
import java.util.NoSuchElementException;


/**
 * This class creates a hash table that is used to store key-value pairs. This allows us to modify
 * and manage the data within the hash table
 *
 * @author Michael Song
 *
 * @param <KeyType>
 * @param <ValueType>
 */
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

    /**
     * This protected class is used to group key value pairs stored in the hash table
     */
    protected class KeyValuePairs<KeyType, ValueType> {
        private KeyType key; // instance field of key
        private ValueType value; // instance field of value

        /**
         * Constructor that initializes the key and value variables with the arguments that were passed
         * in.
         *
         * @param key   - key that needs to be hashed
         * @param value - value that needs to be stored in the hash table
         */
        public KeyValuePairs(KeyType key, ValueType value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Accessor method that returns the key in a key-value pair
         *
         * @return key - KeyType
         */
        public KeyType getKey() {
            return this.key;
        }

        /**
         * Accessor method that returns the value in a key-value pair
         *
         * @return value - ValueType
         */
        public ValueType getValue() {
            return this.value;
        }
    }

    protected LinkedList<KeyValuePairs<KeyType, ValueType>>[] hashtable;
    private int size; // instance field to store hashtable size
    public int capacity; // instance field to store hashtable capacity

    /**
     * Constructor that defines a new hashtable map with desired capacity
     *
     * @param capacity - capacity of the array
     */
    @SuppressWarnings("unchecked")
    public HashtableMap(int capacity) {
        this.capacity = capacity;
        hashtable = new LinkedList[this.capacity];
        for (int i = 0; i < capacity; i++) {
            hashtable[i] = new LinkedList();
        }
        size = 0;
    }

    /**
     * Constructor that defines a new hashtable map with default capacity of 15
     */
    @SuppressWarnings("unchecked")
    public HashtableMap() { // with default capacity = 15
        this(15);
    }

    /**
     * Returns the absolute value of key's hashCode() modulus the hashtable map's current capacity
     *
     * @param key       - key used to find index
     * @return the index in the hashtable
     */
    private int hashFunction(KeyType key) {
        return Math.abs(key.hashCode() % capacity);
    }

    /**
     * Method that creates and rehashes a new hashtable with double its capacity whenever its load
     * factor becomes greater than or equal to 70%.
     *
     * @return new hashtable map with double the capacity
     */
    private LinkedList<KeyValuePairs<KeyType, ValueType>>[] rehash() {
        capacity = capacity * 2;
        LinkedList<KeyValuePairs<KeyType, ValueType>>[] rehashtable = new LinkedList[capacity];
        for (int i = 0; i < rehashtable.length; i++) {
            rehashtable[i] = new LinkedList();
        }
        for (int i = 0; i < hashtable.length; i++) {
            if (hashtable[i] != null) {
                for (int j = 0; j < hashtable[i].size(); j++) {
                    KeyValuePairs<KeyType, ValueType> kvPair = hashtable[i].get(j);
                    int index = hashFunction(kvPair.getKey());
                    rehashtable[index].add(kvPair);
                }
            }
        }
        return rehashtable;
    }

    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }

    /**
     * Inserts a new (key, value) pair into the map if the map does not contain a value mapped to key
     * yet.
     *
     * @param key   the key of the (key, value) pair to store
     * @param value the value that the key will map to
     * @return true if the (key, value) pair was inserted into the map, false if a mapping for key
     *         already exists and the new (key, value) pair could not be inserted
     */
    @Override
    public boolean put(KeyType key, ValueType value) {
        if (key == null || containsKey(key) == true) {
            return false;
        } else {
            int index = hashFunction(key);
            KeyValuePairs<KeyType, ValueType> newPair = new KeyValuePairs<KeyType, ValueType>(key, value);
            hashtable[index].add(newPair);
            size++;
            if (Double.valueOf(size) / capacity >= 0.7) {
                hashtable = rehash();
            }
            return true;
        }
    }

    /**
     * Returns the value mapped to a key if the map contains such a mapping.
     *
     * @param key the key for which to look up the value
     * @return the value mapped to the key
     * @throws NoSuchElementException if the map does not contain a mapping for the key
     */
    @Override
    public ValueType get(KeyType key) throws NoSuchElementException {
        if (containsKey(key) == false || key == null) {
            throw new NoSuchElementException();
        } else {
            int index = hashFunction(key);
            for (int i = 0; i < hashtable[index].size(); i++) {
                if (hashtable[index].get(i).getKey().equals(key)) {
                    return (ValueType) hashtable[index].get(i).getValue();
                }
            }
            return null;
        }
    }

    /**
     * Removes a key and its value from the map.
     *
     * @param key the key for the (key, value) pair to remove
     * @return the value for the (key, value) pair that was removed, or null if the map did not
     *         contain a mapping for key
     */
    @Override
    public ValueType remove(KeyType key) {
        if (containsKey(key) == false || key == null) {
            return null;
        } else {
            int index = hashFunction(key);
            for (int i = 0; i < hashtable[index].size(); i++) {
                if (hashtable[index].get(i).getKey().equals(key)) {
                    ValueType value = (ValueType) hashtable[index].remove(i).getValue();
                    size--;
                    return value;
                }
            }
            return null;
        }
    }


    /**
     * Returns the number of (key, value) pairs stored in the map.
     *
     * @return the number of (key, value) pairs stored in the map
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all (key, value) pairs from the map.
     */
    @Override
    public void clear() {
        this.hashtable = new LinkedList[capacity];
        this.size = 0;
    }

    /**
     * Checks if a key is stored in the map.
     *
     * @param key the key to check for
     * @return true if the key is stored (mapped to a value) by the map and false otherwise
     */
    @Override
    public boolean containsKey(KeyType key) {
        int index = hashFunction(key);
        if (hashtable[index] == null || hashtable[index].size() == 0) {
            return false;
        } else {
            for (int i = 0; i < hashtable[index].size(); i++) {
                if (hashtable[index].get(i).getKey().equals(key)) {
                    return true;
                }
            }
            return false;
        }
    }
}