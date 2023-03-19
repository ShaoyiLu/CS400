import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
    protected LinkedList<Node>[] table;

    class Node{
        public KeyType key;
        public ValueType value;
        public Node(KeyType key, ValueType value){
            this.key = key;
            this.value = value;
        }
    }

    /**
     *
     * @param capacity
     */
    public HashtableMap(int capacity){
        table = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++){
            table[i] = new LinkedList<>();
        }
    }

    /**
     * with default capacity = 15
     */
    public HashtableMap() {
        this(15);
    }

    /**
     * Inserts a new (key, value) pair into the map if the map does not
     * contain a value mapped to key yet.
     *
     * @param key   the key of the (key, value) pair to store
     * @param value the value that the key will map to
     * @return true if the (key, value) pair was inserted into the map,
     * false if a mapping for key already exists and the
     * new (key, value) pair could not be inserted
     */
    @Override
    public boolean put(KeyType key, ValueType value) {
        if (key == null || containsKey(key)){
            return false;
        }
        int i = Math.abs(key.hashCode()) % table.length;
        LinkedList<Node> newTable = table[i];
        for (int j = 0; j < newTable.size(); j++){
            Node newNode = newTable.get(j);
            if (newNode.key.equals(key)){
                return false;
            }
        }
        table[Math.abs(key.hashCode())% table.length].add(new Node(key,value));
        if(1.0*size() / table.length >= 0.7){
            resize();
        }
        return true;
    }

    private void resize(){
        LinkedList<Node>[] resizeTable = table;
        table = new LinkedList[resizeTable.length * 2];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<Node>();
        }

        for(int i = 0; i < resizeTable.length; i++){
            LinkedList<Node> newTable = resizeTable[i];
            for(int j = 0; i < newTable.size(); j++){
                Node newNode = newTable.get(j);
                this.put(newNode.key, newNode.value);
            }
        }
    }

    /**
     * Returns the value mapped to a key if the map contains such a mapping.
     *
     * @param key the key for which to look up the value
     * @return the value mapped to the key
     * @throws NoSuchElementException if the map does not contain a mapping
     *                                for the key
     */
    @Override
    public ValueType get(KeyType key) throws NoSuchElementException {
        if (key == null){
            throw new NoSuchElementException("key has nothing");
        }
        int i = Math.abs(key.hashCode()) % table.length;
        LinkedList<Node> newTable2 = table[i];
        for (int j = 0; j < newTable2.size(); j++){
            Node newNode2 = newTable2.get(j);
            if (newNode2.key.equals(key)){
                return newNode2.value;
            }
        }
        throw new NoSuchElementException("nothing in it");
    }

    /**
     * Checks if a key is stored in the map.
     *
     * @param key the key to check for
     * @return true if the key is stored (mapped to a value) by the map
     *         and false otherwise
     */
    @Override
    public boolean containsKey(KeyType key) {
        if(key == null){
            return false;
        }
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[i].size(); j++){
                if(table[i].get(j).equals(key)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the number of (key, value) pairs stored in the map.
     *
     * @return the number of (key, value) pairs stored in the map
     */
    @Override
    public int size() {
        int totalSize = 0;
        for (int i = 0; i < table.length; i++){
            if (table[i] != null){
                totalSize += table[i].size();
            }
        }
        return totalSize;
    }

    /**
     * Removes a key and its value from the map.
     *
     * @param key the key for the (key, value) pair to remove
     * @return the value for the (key, value) pair that was removed,
     *         or null if the map did not contain a mapping for key
     */
    @Override
    public ValueType remove(KeyType key) {
        if (key == null){
            return null;
        }
        try {
            int i = Math.abs(key.hashCode()) % table.length;
            LinkedList<Node> newTable = table[i];
            for (int j = 0; j < newTable.size(); j++) {
                Node newNode = newTable.get(j);
                if (newNode.key.equals(key)) {
                    ValueType value = newNode.value;
                    newTable.remove();
                    return value;
                }
            }
        }catch (NoSuchElementException e) {
            return null;
        }
        return null;
    }

    /**
     * Removes all (key, value) pairs from the map.
     */
    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++){
            table[i].clear();
        }
    }
}
