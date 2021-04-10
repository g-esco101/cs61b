import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter @Setter
public class MyHashMap<K, V> implements Map61B<K, V> {

    private int size;
    private Entry[] hashTable;

    /** The load factor is a measure of how full the hash table is allowed to get before its capacity is automatically
     * increased. When the number of entries in the hash table exceeds the product of the load factor and the current
     * capacity, the hash table is rehashed (that is, internal data structures are rebuilt) so that the hash table has
     * approximately twice the number of buckets.
     *
     */
    private final double LOAD_FACTOR;
    private final int RESIZE_FACTOR = 2;

    public MyHashMap() {
        size = 0;
        LOAD_FACTOR = 0.75;
        hashTable = new MyHashMap.Entry[16];
    }

    public MyHashMap(int initialSize) {
        size = 0;
        LOAD_FACTOR = 0.75;
        hashTable = new MyHashMap.Entry[initialSize];

    }
    public MyHashMap(int initialSize, double loadFactor) {
        size = 0;
        LOAD_FACTOR = loadFactor;
        hashTable = new MyHashMap.Entry[initialSize];
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        size = 0;
        hashTable = new MyHashMap.Entry[hashTable.length];
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        int index = hash(key);
        Entry entry = hashTable[index];
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return true;
            }
            entry = entry.getNext();
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int index = hash(key);
        Entry entry = hashTable[index];
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return entry.getVal();
            }
            entry = entry.getNext();
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    /** If the same key is inserted more than once, the value should be updated each time. You can assume null keys will never be inserted.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        if ((double) (size() + 1) / hashTable.length > LOAD_FACTOR) {
            resize();
        }
        int index = hash(key, hashTable.length);
        Entry entry = hashTable[index];
        if (entry == null) {
            entry = new Entry(key, value, null);
            hashTable[index] = entry;
            size++;
            return;
        }
        entry = entry.get(key);
        if (entry != null) {
            entry.setVal(value);
        } else {
            entry = new Entry(key, value, hashTable[index]);
            hashTable[index] = entry;
            size++;
        }
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for(Entry entry : hashTable) {
            while (entry != null) {
                keys.add(entry.getKey());
                entry = entry.getNext();
            }
        }
        return keys;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * For remove, you should return null if the argument key does not exist in the MyHashMap. Otherwise, delete the key-value pair (key, value) and return value.
     */
    @Override
    public V remove(K key) {
        int index = hash(key);
        V val = null;
        Entry entry = hashTable[index];
        if (entry == null) {
            return val;
        }
        if (entry.getKey().equals(key)) {
            val = entry.getVal();
            hashTable[index] = entry.getNext();
            size--;
            return val;
        }
        Entry prev = entry;
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                val = entry.getVal();
                prev.setNext(entry.getNext());
                size--;
                return val;
            }
            prev = entry;
            entry = entry.getNext();
        }
        return val;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        int index = hash(key);
        V val = null;
        Entry entry = hashTable[index];
        if (entry == null) {
            return val;
        }
        if (entry.getKey().equals(key) && entry.getVal().equals(value)) {
            val = entry.getVal();
            hashTable[index] = entry.getNext();
            size--;
            return val;
        }
        Entry prev = entry;
        while (entry != null) {
            if (entry.getKey().equals(key) && entry.getVal().equals(value)) {
                val = entry.getVal();
                prev.setNext(entry.getNext());
                size--;
                return val;
            }
            prev = entry;
            entry = entry.getNext();
        }
        return val;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    private int hash(K key, int buckets) {
        return (key.hashCode() & 0x7fffffff) % buckets;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % hashTable.length;
    }

    private void resize() {
        ArrayList<Entry> temp = new ArrayList<>();
        for (K key : keySet()) {
            temp.add(new Entry(key, get(key), null));
        }
        hashTable = new MyHashMap.Entry[hashTable.length * RESIZE_FACTOR];
        setSize(0);
        for (Entry entry : temp) {
            put(entry.getKey(), entry.getVal());
        }
    }

    /**
     * Represents one node in the linked list that stores the key-value pairs
     * in the dictionary.
     */
    @Getter @Setter
    class Entry {
        /** Stores the key of the key-value pair of this node in the list. */
        private K key;
        /** Stores the value of the key-value pair of this node in the list. */
        private V val;
        /** Stores the next Entry in the linked list. */
        private Entry next;

        /**
         * Stores KEY as the key in this key-value pair, VAL as the value, and
         * NEXT as the next node in the linked list.
         */
        private Entry(K k, V v, Entry n) {
            key = k;
            val = v;
            next = n;
        }

        /**
         * Returns the Entry in this linked list of key-value pairs whose key
         * is equal to KEY, or null if no such Entry exists.
         */
        private Entry get(K k) {
            Entry entry = this;
            while (entry != null) {
                if (entry.getKey().equals(k)) {
                    return entry;
                }
                entry = entry.getNext();
            }
            return null;
        }
    }
}