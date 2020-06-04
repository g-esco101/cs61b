package src;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringJoiner;

/** A Binary Search Tree with key-value mappings. Operations are implemented recursively.
 *
 * @param <K> the key of the mapping.
 * @param <V>the value of the mapping.
 */
public class BSTMapRecursive<K extends Comparable<K>, V> implements Map61B<K, V> {

    /** Represents a key-value mapping in the TreeMap.
     *
     * @param <K> the key of the mapping.
     * @param <V>the value of the mapping.
     */
    public static class Entry<K extends Comparable<K>, V> {

        private K key;
        private V value;
        private Entry left;
        private Entry right;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    /** The root of the TreeMap.
     *
     */
    private Entry<K, V> root;

    /** the size of the TreeMap.
     *
     */
    private int size;

    /** Constructor that instantiates an empty TreeMap.
     *
     */
    public BSTMapRecursive() {
        size = 0;
    }

    /** Constructor that instantiates a TreeMap with the specified key-value mapping.
     * Null keys or value are prohibited.
     *
     * @param key the key of the mapping.
     * @param value the value of the mapping.
     */
    public BSTMapRecursive(K key, V value) {
        if (key != null) {
            this.root = new Entry<>(key, value );
            size = 1;
        } else {
            size = 0;
        }
    }

    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns true if this map contains a mapping for the specified key.
     *
     * @param key to check if it is contained.
     * @return true if the key is contained. Otherwise, false.
     */
    @Override
    public boolean containsKey(K key) {
        if (key == null || root == null) {
            return false;
        }
        return getEntry(key, root) != null;
    }

    /** Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key of the associated value.
     * @return the value associated with the key.
     */
    @Override
    public V get(K key) {
        if (root == null || key == null) {
            return null;
        }
        Entry entry = getEntry(key, root);
        if (entry == null) {
            return null;
        }
        return (V) entry.value;
    }

    /** Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key of the associated value.
     * @param entry the root of the subtree to search.
     * @return the entry of the key-value mapping.
     */
    private Entry getEntry(K key, Entry entry) {
        if (entry == null) {
            return null;
        }
        int compare = entry.key.compareTo(key);
        if (compare == 0) {
            return entry;
        } else if (compare > 0) {
            return getEntry(key, entry.left);
        } else {
            return getEntry(key, entry.right);
        }
    }

    /** Returns the number of key-value mappings in this map.
     *
     * @return the number of k-value mappings.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Determines if the BSTMap is empty
     *
     * @return true if there are no key-value mappings. Otherwise, returns false.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Associates the specified value with the specified key in this map. Null keys
     * are prohibited.
     *
     * @param key of the mapping.
     * @param value the associated value of the key.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        root = put(key, value, root);
    }

    /** Associates the specified value with the specified key in this map. Null keys
     * are prohibited.
     *
     * @param key of the mapping.
     * @param value the associated value of the key.
     * @param entry subtree to put the key-value mapping.
     * @param value the associated value of the key.
     */
    public Entry put(K key, V value, Entry entry) {
        if (entry == null) {
            size++;
            return new Entry<>(key, value);
        } if (entry.key.compareTo(key) == 0) {
            entry.value = value;
            return entry;
        } else if (entry.key.compareTo(key) > 0) {
            entry.left = put(key, value, entry.left);
        } else {
            entry.right = put(key, value, entry.right);
        }
        return entry; // returns if key is in BST.
    }

    /** Returns a Set view of the keys contained in this map.
     *
     * @return a set of the keys.
     */
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        if (root != null) {
            return keySet(root, set);
        }
        return set;
    }

    /** Returns a Set view of the keys contained in this map.
     *
     * @param entry entry and its children to include in the set
     * @param set the set of keys.
     * @return a set of the keys.
     */
    private Set<K> keySet(Entry entry, Set<K> set) {
        if (entry != null) {
            keySet(entry.left, set);
            set.add((K) entry.key);
            keySet(entry.right, set);
        }
        return set;
    }

    /** Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     *
     * @param key of the mapping to be removed.
     * @param value the associated value of the key.
     */
    @Override
    public V remove(K key, V value) {
        if (root == null || key == null) {
            return null;
        }
        Entry entry = getEntry(key, root);
        if (entry != null && entry.value == value) {
            return remove(key);
        }
        return null;
    }

    /** Removes the mapping for the specified key from this map if present. Returns null if root is null
     * or if specified key is null.
     * Not required for Lab. If you don't implement this, throw an UnsupportedOperationException.
     *
     * @param key of the mapping to be removed.
     */
    @Override
    public V remove(K key) {
        if (root == null || key == null) {
            return null;
        }
        Entry entry = getEntry(key, root);
        if (entry == null) {
            return null;
        }
        root = remove(key, root);
        size--;
        return (V) entry.value;
    }

    /** Removes the mapping for the specified key from this map if present. Returns null if root is null
     * or if specified key is null.
     * Not required for Lab. If you don't implement this, throw an UnsupportedOperationException.
     *
     * @param key of the mapping to be removed.
     * @param entry root of subtree containing the key.
     */
    private Entry remove(K key, Entry entry) {
        int cmp = key.compareTo((K) entry.key);
        if (cmp < 0) entry.left  = remove(key, entry.left);
        else if (cmp > 0) entry.right = remove(key, entry.right);
        else {
            if (entry.right == null) return entry.left;
            if (entry.left  == null) return entry.right;
            Entry t = entry;
            entry = min(t.right);
            entry.right = removeMin(t.right);
            entry.left = t.left;
        }
        return entry;
    }

    /** Gets the entry in a subtree with the minimum key.
     *
     * @param entry root of subtree to get minimum key.
     * @return the entry in the subtree with the minimum key.
     */
    private Entry min(Entry entry) {
        if (entry.left == null) {
            return entry;
        } else {
            return min(entry.left);
        }
    }

    /** Removes the entry in a subtree with the minimum key.
     *
     * @param entry root of subtree to remove the minimum key.
     * @return the entry that is removed in the subtree.
     */
    private Entry removeMin(Entry entry) {
        if (entry.left == null) {
            return entry.right;
        }
        entry.left = removeMin(entry.left);
        return entry;
    }

    /** Prints the key-value mappings in order.
     *
     */
    public void printInOrder() {
        System.out.println(toString());
    }

    /**
     * Returns an iterator over elements of type.
     *
     * @return an inorder representation of the key-value mappings.
     */
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        if (root != null) {
            return toString(root, joiner);
        }
        return joiner.toString();
    }

    /**
     * Returns an iterator over elements of type.
     *
     * @param entry subtree to represent the key-value mappings.
     * @param joiner the StringJoiner containing the string representation.
     * @return an inorder representation of the key-value mappings.
     */
    public String toString(BSTMapRecursive.Entry entry, StringJoiner joiner) {
        if (entry != null) {
            toString(entry.left, joiner);
            joiner.add(String.format("(%s, %s)", entry.key, entry.value));
            toString(entry.right, joiner);
        }
        return joiner.toString();
    }

    /**
     * Returns an iterator over elements of type.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        Iterator<K> iter = keySet().iterator();
        return  iter;
    }
}