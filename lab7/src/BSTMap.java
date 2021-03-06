package src;

import java.util.*;

/** A Binary Search Tree with key-value mappings. Most operations are implemented iteratively.
 *
 * @param <K> the key of the mapping.
 * @param <V>the value of the mapping.
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    /** Represents a key-value mapping in the TreeMap.
     *
     * @param <K> the key of the mapping.
     * @param <V>the value of the mapping.
     */
    public static class Entry<K extends Comparable<K>, V> {

        private K key;
        private V value;
        private Entry left, right, parent;

        public Entry(K key, V value, Entry<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            left = null;
            right = null;
        }
    }

     class EntryIterator<T> implements Iterator<T> {
         Entry<K,V> nextEntry;
         T next;
         int count;

        EntryIterator() {
            nextEntry = minimum(root);
            next = (T) nextEntry.key;
            count = 0;
        }

        public boolean hasNext() {
            return count != size;
       }

        public T next() {
            Entry<K,V> entry = nextEntry;
            nextEntry = successor(entry);
            count++;
            return (T) entry.key;
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
    public BSTMap() {
        size = 0;
    }

    /** Constructor that instantiates a TreeMap with the specified key-value mapping.
     * Null keys are prohibited.
     *
     * @param key the key of the mapping.
     * @param value the value of the mapping.
     */
    public BSTMap(K key, V value) {
        if (key != null) {
            this.root = new Entry<>(key, value, null);
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
        Entry entry = getEntry(key);
        if (entry == null) {
            return false;
        }
        return true;
    }

    /** Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key of the associated value.
     * @return the value associated with the key.
     */
    @Override
    public V get(K key) {
        if (key == null || root == null) {
            return null;
        }
        Entry entry = getEntry(key);
        if (entry == null) {
            return null;
        }
        return (V) entry.value;
    }

    /** Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key of the associated value.
     * @return the entry of the key-value mapping.
     */
    private Entry getEntry(K key) {
        Entry netry = root;
        while (netry != null) {
            int compare = netry.key.compareTo(key);
            if (compare == 0) {
                return netry;
            } else if (compare > 0) {
                netry = netry.left;
            } else {
                netry = netry.right;
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map.
     *
     * @return the number of k-value mappings.
     */
    @Override
    public int size() {
        return size;
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
        Entry parent = null;
        Entry node = root;
        while (node != null) {
            parent = node;
            int compare = node.key.compareTo(key);
            if (compare == 0) {
                node.value = value;
                return;
            } else if (compare > 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        Entry entry = new Entry(key, value, parent);
        if (parent == null) {
            root = entry; // tree was empty
        } else if (parent.key.compareTo(key) > 0) {
            parent.left = entry;
        } else {
            parent.right = entry;
        }
        size++;
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
        if (key == null || root == null) {
            return null;
        }
        Entry entry = getEntry(key);
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
        if (key == null || root == null) {
            return null;
        }
        Entry removed = getEntry(key);
        if (removed == null) {
            return null;
        }
        if (removed.left == null) {
            transplant(removed, removed.right);
        } else if (removed.right == null) {
            transplant(removed, removed.left);
        } else {
            Entry<K, V> successor = minimum(removed.right);
            if (successor.parent != removed) {
                transplant(successor, successor.right);
                successor.right = removed.right;
                successor.right.parent = successor;
            }
            transplant(removed, successor);
            successor.left = removed.left;
            successor.left.parent = successor;
        }
        size--;
        return (V) removed.value;
    }

    /** Replaces the subtree rooted at entry u with the subtree rooted at entry v, entry u's
     * parent becomes entry v's parent and entry v's parent, and u's parent gets v as a child.
     *
     * @param u subtree root that is to be swapped.
     * @param v subtree root that is to be swapped.
     */
    private void transplant(Entry u, Entry v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    /** Prints the key-value mappings in order.
     *
     */
    public void printInOrder() {
        System.out.println(toString());
    }

    /**
     * Determines if the BSTMap is empty
     *
     * @return true if there are no key-value mappings. Otherwise, returns false.
     */
    public boolean isEmpty() {
        return size == 0;
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

    /**
     * Returns an iterator over elements of type.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        Iterator<K> iter = new EntryIterator<K>();
        return  iter;
    }

    /** Returns the successor of the root of the subtree entry.
     *
     * @param entry the root of the subtree to find its successor.
     * @return the entry successor.
     */
    private Entry<K, V> successor(Entry<K, V> entry) {
        if (entry.right != null) {
            return minimum((entry.right));
        }
        Entry<K, V> parent = entry.parent;
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
    private Entry<K, V> minimum(Entry<K, V> entry) {
        while (entry.left != null) {
            entry = entry.left;
        }
        return entry;
    }
}
