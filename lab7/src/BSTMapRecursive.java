package src;

import java.util.*;

/** A Binary Search Tree with key-value mappings. Operations are implemented recursively.
 *
 * @param <K> the key of the mapping.
 * @param <V>the value of the mapping.
 */
public class BSTMapRecursive<K extends Comparable<K>, V> implements Map61B<K, V> {

    /** Represents a key-value mapping in the TreeMap.
     *
     */
    private class Entry {

        private K key;
        private V value;
        private Entry left, right, parent;

        public Entry(K key, V value, Entry parent) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            this.parent = parent;
        }
    }

    class EntryIterator implements Iterator<K> {
        Entry next;
        Iterator<K> keyIter;

        EntryIterator() {
            if (root != null) {
                keyIter = keySet().iterator();
            }
        }

        public boolean hasNext() {
            return root != null && keyIter.hasNext();
        }

        public K next() {
            return keyIter.next();
        }
    }

    /** The root of the TreeMap.
     *
     */
    private Entry root;

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
            this.root = new Entry(key, value, null);
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
        return entry.value;
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
        root = put(key, value, root, null);
    }

    /** Associates the specified value with the specified key in this map. Null keys
     * are prohibited.
     *
     * @param key of the mapping.
     * @param value the associated value of the key.
     * @param entry subtree to put the key-value mapping.
     * @param value the associated value of the key.
     */
    public Entry put(K key, V value, Entry entry, Entry parent) {
        if (entry == null) {
            size++;
            return new Entry(key, value, parent);
        } if (entry.key.compareTo(key) == 0) {
            entry.value = value;
        } else if (entry.key.compareTo(key) > 0) {
            entry.left = put(key, value, entry.left, entry);
        } else {
            entry.right = put(key, value, entry.right, entry);
        }
        return entry; // returns if key is in BST.
    }

    /** Returns a Set view of the keys contained in this map.
     *
     * @return a set of the keys.
     */
    @Override
    public Set<K> keySet() {
        Set<K> set = new TreeSet<>();
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
        if (entry == null) {
            return set;
        }
        keySet(entry.left, set);
        set.add(entry.key);
        keySet(entry.right, set);
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
        if (entry != null && entry.value.equals(value)) {
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
            if (entry.left  == null) {
                if (entry.right != null) {
                    entry.right.parent = entry.parent;
                }
                return entry.right;
            }
            if (entry.right == null) {
                entry.left.parent = entry.parent;
                return entry.left;
            }
            Entry successor = successor(entry);
            if (successor.key.compareTo((K) entry.right.key) == 0) {
                successor.parent = entry.parent;
                entry.left.parent = successor;
                successor.left = entry.left;
                return successor;
            } else {
                if (successor.right != null) {
                    successor.right.parent = successor.parent;
                }
                successor.parent.left = successor.right;
                successor.parent = entry.parent;
                successor.right = entry.right;
                successor.left = entry.left;
                entry.right.parent = successor;
                entry.left.parent = successor;
                return successor;
            }
        }
        return entry;
    }

    /**
     * Returns an iterator over elements of type.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return (Iterator<K>) new EntryIterator();
    }

    /** Returns the successor of the root of the subtree entry.
     *
     * @param entry the root of the subtree to find its successor.
     * @return the entry successor.
     */
    private Entry successor(Entry entry) {
        if (entry.right != null) {
            return minimum((entry.right));
        }
        Entry parent = entry.parent;
        while (parent != null && entry.equals(parent.right)) {
            entry = parent;
            parent = parent.parent;
        }
        return parent;
    }

    /** Finds the entry containing the minimum key in the subtree.
     *
     * @param entry the subtree to search for the entry with the minimum key.
     * @return the entry in the subtree with the minimum key.
     */
    private Entry minimum(Entry entry) {
        while (entry.left != null) {
            entry = entry.left;
        }
        return entry;
    }

    public void printInOrder() {
        printInOrder(root);
    }

    public void printInOrder(Entry entry) {
        if (entry == null) {
            return;
        }
        printInOrder(entry.left);
        System.out.println(entry.value);
        printInOrder(entry.right);
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
    public String toString(Entry entry, StringJoiner joiner) {
        if (entry != null) {
            toString(entry.left, joiner);
            joiner.add(String.format("(%s, %s)", entry.key, entry.value));
            toString(entry.right, joiner);
        }
        return joiner.toString();
    }
}
