package src;

import java.util.*;

/** A Binary Search Tree with key-value mappings. Most operations are implemented iteratively.
 *
 * @param <K> the key of the mapping.
 * @param <V>the value of the mapping.
 */
public class BSTMapParent<K extends Comparable<K>, V> implements Map61B<K, V> {

    /** Represents a key-value mapping in the TreeMap.
     *
     */
    private class Entry {

        private K key;
        private V value;
        private Entry left, right, parent;

        Entry(K key, V value, Entry parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            left = null;
            right = null;
        }
    }

     class EntryIterator implements Iterator<K> {
         Iterator<K> keys;

        EntryIterator() {
            if (root != null) {
                keys = keySet().iterator();
            }
        }

        public boolean hasNext() {
            return keys != null && keys.hasNext();
       }

        public K next() {
            return keys.next();
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
    public BSTMapParent() {
        size = 0;
    }

    /** Constructor that instantiates a TreeMap with the specified key-value mapping.
     * Null keys are prohibited.
     *
     * @param key the key of the mapping.
     * @param value the value of the mapping.
     */
    public BSTMapParent(K key, V value) {
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
        return getEntry(key) != null;
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
        Entry  entry = getEntry(key);
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
    private Entry  getEntry(K key) {
        Entry  entry = root;
        while (entry != null) {
            int compare = entry.key.compareTo(key);
            if (compare == 0) {
                return entry;
            } else if (compare > 0) {
                entry = entry.left;
            } else {
                entry = entry.right;
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
            return removeEntry(entry);
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
        return removeEntry(removed);
    }

    /** Removes the mapping for the specified entry from this map if present. Returns null if enry is null.
     *
     * @param entry - the mapping to be removed.
     */
    private V removeEntry(Entry entry) {
        if (entry == null) {
            return null;
        }
        if (entry.left == null) {
            transplant(entry, entry.right);
        } else if (entry.right == null) {
            transplant(entry, entry.left);
        } else {
            Entry successor = minimum(entry.right);
            if (successor.parent != entry) {
                transplant(successor, successor.right);
                successor.right = entry.right;
                successor.right.parent = successor;
            }
            transplant(entry, successor);
            successor.left = entry.left;
            successor.left.parent = successor;
        }
        size--;
        return (V) entry.value;
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

    /**
     * Determines if the BSTMap is empty
     *
     * @return true if there are no key-value mappings. Otherwise, returns false.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns a Set view of the keys contained in this map.
     *
     * @return a set of the keys.
     */
    @Override
    public Set<K> keySet() {
        Set<K> set = new TreeSet<>();
        Queue<Entry> queue = new LinkedList<>();
        Entry entry = root;
        if (root == null) {
            return set;
        }
        queue.offer(entry);
        while (!queue.isEmpty()) {
            entry = queue.poll();
            set.add(entry.key);
            if (entry.left != null) {
                queue.offer(entry.left);
            }
            if (entry.right != null) {
                queue.offer(entry.right);
            }
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
        return new EntryIterator();
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
