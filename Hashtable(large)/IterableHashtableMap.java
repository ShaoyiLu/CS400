import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class allows for an iterator to go through the contents of a Hashtable
 *
 * @param <KeyType>
 * @param <ValueType>
 */
public class IterableHashtableMap<KeyType, ValueType> extends HashtableMap<KeyType, ValueType>
        implements IterableMapADT<KeyType, ValueType> {


    /**
     * This class functions an iterator that is able to iterate through a hashtable
     */
    class HashIterator implements Iterator<ValueType> {

        private IterableHashtableMap<KeyType, ValueType> ithashtable;
        // the current index of the outer linkedlist
        private int currentindex = 0;

        // the total elements of the hashtable
        private int total;

        // to determine if the listSize should be renew
        private boolean mark = true;

        // with "count" below, it can ensure at one time, the next() method only get one element
        private int listSize = 0;


        /**
         * Constructor of class that creates an iterator object
         *
         * @param hashtable
         */
        public HashIterator(IterableHashtableMap<KeyType, ValueType> hashtable) {
            this.total = 0;
            this.ithashtable = hashtable;
        }

        /**
         * Returns true if the iteration has more elements.
         *
         * @return true if the iteration has more elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            if (ithashtable.size() <= total) {
                return false;
            }

            return true;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration.
         * @throws NoSuchElementException with a descriptive error message if the iteration has no more
         *                                elements
         */
        @Override
        public ValueType next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            //iterate through the outer linkedlist, if the currnet inner linkedlist corresponding to the currentindex is null
            //move to next index
            if (ithashtable.hashtable[currentindex] == null) {
                currentindex++;
            }

            //with the listSize, it can ensure that the index of the inner linkedlist can be go through
            //from the first to the last element once a time when the function is called
            int count = ithashtable.hashtable[currentindex].size() - 1;

            //to see if we need to renew the listSize (next inner linkedlist)
            if (mark) {
                listSize = ithashtable.hashtable[currentindex].size() - 1;

            }

            //return the object
            if (listSize >= 0) {
                ValueType temp = ithashtable.hashtable[currentindex].get((count - listSize)).getValue();
                total++;
                listSize--;
                mark = false;
                return temp;
            }

            //move to next inner linkedlist (the current is done)
            mark = true;
            currentindex++;
            return next();

        }

    }


    /**
     * Calls and creates an iterator to iterate through a hashtable
     */
    @Override
    public Iterator<ValueType> iterator() {
        return new HashIterator(this);

    }
}
