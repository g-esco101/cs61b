package src;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTBare<K extends Comparable<K>, V> implements Map61B<K, V> {

    /** Represents a key-value mapping in the TreeMap.
     *
     * @param <K> the key of the mapping.
     * @param <V>the value of the mapping.
     */
    public static class Entry<K extends Comparable<K>, V> {

        private K key;
        private V value;
        public BSTBare.Entry<K, V> left, right;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    class EntryIterator<T> implements Iterator<T> {
        BSTBare.Entry<K,V> nextEntry;
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
            BSTBare.Entry<K,V> entry = nextEntry;
            nextEntry = successor(entry);
            count++;
            return (T) entry.key;
        }
    }

    /** The root of the TreeMap.
     *
     */
    private BSTBare.Entry<K, V> root;

    /** the size of the TreeMap.
     *
     */
    private int size;

    /** Constructor that instantiates an empty TreeMap.
     *
     */
    public BSTBare() {
        size = 0;
    }

    /** Constructor that instantiates a TreeMap with the specified key-value mapping.
     * Null keys are prohibited.
     *
     * @param key the key of the mapping.
     * @param value the value of the mapping.
     */
    public BSTBare(K key, V value) {
        if (key != null) {
            this.root = new BSTBare.Entry<>(key, value);
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
        return get(key) != null;
    }

    /** Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key of the associated value.
     * @return the value associated with the key.
     */
    @Override
    public V get(K key) {
        Entry entry = root;
        while (entry != null) {
            int value = entry.key.compareTo(key);
            if (value == 0) {
                return (V) entry.value;
            } else if (value > 0) {
                entry = entry.left;
            } else {
                entry = entry.right;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        BSTBare.Entry<K, V> parent = null;
        BSTBare.Entry<K, V> node = root;
        while (node != null) {
            parent = node;
            int comparison = node.key.compareTo(key);
            if (comparison == 0) {
                node.value = value;
                return;
            } else if (comparison > 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        BSTBare.Entry<K, V> entry = new BSTBare.Entry(key, value);
        if (size == 0) {
            root = entry; // tree was empty
        } else if (parent.key.compareTo(key) > 0) {
            parent.left = entry;
        } else {
            parent.right = entry;
        }
        size++;
    }

    /** Returns a Set view of the keys contained in this map.
     *
     * @return a set of the keys.
     */
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        BSTBare.Entry<K, V> entry = root;
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
    private Set<K> keySet(BSTBare.Entry entry, Set<K> set) {
        if (entry != null) {
            keySet(entry.left, set);
            set.add((K) entry.key);
            keySet(entry.right, set);
        }
        return set;
    }

    @Override
    public V remove(K key) {
        if (key == null || root == null) {
            return null;
        }
        return removeEntry(root, key).value;
    }

    @Override
    public V remove(K key, V value) {
        if (key == null || root == null) {
            return null;
        }
        return removeEntry(root, key).value;
    }
    /**
     * The helper function of remove
     */
    private BSTBare.Entry<K, V> removeEntry(BSTBare.Entry<K, V> entry, K key) {
        if (entry == null) {
            return null;
        }
        int cmp = entry.key.compareTo(key);
        if (cmp > 0) {
            entry.right = removeEntry(entry.right, key);
        } else if (cmp < 0) {
            entry.left = removeEntry(entry.left, key);
        } else {
            if (entry.left == null) {
                return entry.right;
            }
            if (entry.right == null) {
                return entry.left;
            }
            BSTBare.Entry<K, V> temp = entry;
            entry = min(temp.right);
            entry.right = removeMin(temp.right);
            entry.left = temp.left;
        }
        size--;
        return entry;
    }

    /**
     * The helper function of private remove
     */
    private BSTBare.Entry<K, V> removeMin(BSTBare.Entry<K, V> entry) {
        if (entry.left == null) {
            return entry.right;
        }
        entry.left = removeMin(entry.left);
        return entry;
    }

    /**
     * The helper function of remove
     */
    private BSTBare.Entry<K, V> min(BSTBare.Entry<K, V> entry) {
        if (entry.left == null) {
            return entry;
        }
        return min(entry.left);
    }
    /** Removes the mapping for the specified entry from this map if present. Returns null if enry is null.
     *
     * @param entry - the mapping to be removed.
     */
//    private V removeEntry(BSTBare.Entry<K, V> entry) {
//        if (entry == null) {
//            return null;
//        }
//        size--;
//        BSTBare.Entry<K, V> parent = getParent(entry.key);
//        if (entry.left == null && entry.right == null && parent != null) {
//            if (parent.left != null && parent.left.key.equals(entry.key)) {
//                parent.left = null;
//            } else {
//                parent.right = null;
//            }
//            return entry.value;
//        }
//        if ((entry.left == null && entry.right != null) && parent != null) {
//            if (parent.left != null && parent.left.key.equals(entry.key)) {
//                parent.left = entry.right;
//            } else {
//                parent.right = entry.right;
//            }
//            return entry.value;
//        }
//        if ((entry.left != null && entry.right == null) && parent != null) {
//            if (parent.left != null && parent.left.key.equals(entry.key)) {
//                parent.left = entry.left;
//            } else {
//                parent.right = entry.left;
//            }
//            return entry.value;
//        }
//        BSTBare.Entry<K, V> successor = successor(entry);
//        V value = entry.value;
//        entry.value = successor.value;
//        BSTBare.Entry<K, V> successorParent = getParent(successor.key);
//        if (successor.right != null && successorParent != null) {
//            if (successorParent.left != null && successorParent.left.key.equals(successor.key)) {
//                successorParent.left = successor.right;
//            } else {
//                successorParent.right = successor.right;
//            }
//            return value;
//        }
//
//        if (successor.left != null && successorParent != null) {
//            if (successorParent.left != null && successorParent.left.key.equals(successor.key)) {
//                successorParent.left = successor.left;
//            } else {
//                successorParent.right = successor.left;
//            }
//            return value;
//        }
//        if (successor.left == null && successor.right == null) {
//            if (successorParent.left != null && successorParent.left.key.equals(successor.key)) {
//                successorParent.left = null;
//            } else {
//                successorParent.right = null;
//            }
//            return value;
//        }
//        return value;
//    }

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
    private BSTBare.Entry<K, V> successor(BSTBare.Entry<K, V> entry) {
        if (entry.right != null) {
            return minimum((entry.right));
        }
        entry = entry.left;
        while (entry.right != null) {
            entry = entry.right;
        }
        return entry;
    }

    /** Finds the entry containing the minimum key in the subtree.
     *
     * @param entry the subtree to search for the entry with the minimum key.
     * @return the entry in the subtree with the minimum key.
     */
    private BSTBare.Entry<K, V> minimum(BSTBare.Entry<K, V> entry) {
        while (entry.left != null) {
            entry = entry.left;
        }
        return entry;
    }

    /** Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key of the associated value.
     * @return the entry of the key-value mapping.
     */
    private BSTBare.Entry<K, V> getEntry(K key) {
        BSTBare.Entry<K, V> entry = root;
        while (entry != null) {
            int comparison = entry.key.compareTo(key);
            if (comparison == 0) {
                return entry;
            } else if (comparison < 0) {
                entry = entry.right;
            } else {
                entry = entry.left;
            }
        }
        return null;
    }

    /** Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key of the associated value.
     * @return the entry of the key-value mapping.
     */
    private BSTBare.Entry<K, V> getParent(K key) {
        BSTBare.Entry<K, V> entry = root;
        int comparison;
        while (entry != null) {
            if (entry.left != null) {
                comparison = entry.left.key.compareTo(key);
                if (comparison == 0) {
                    return entry;
                }
            }
            if (entry.right != null) {
                comparison = entry.right.key.compareTo(key);
                if (comparison == 0) {
                    return entry;
                }
            }
            comparison = entry.key.compareTo(key);
            if (comparison == 0) {
                return null;
            } else if (comparison < 0) {
                entry = entry.right;
            } else {
                entry = entry.left;
            }
        }
        return null;
    }
}
