package src;


import lombok.Setter;
import lombok.Getter;

import java.lang.reflect.Array;
import java.util.*;

@Getter @Setter
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    /** The root of the TreeMap.
     *
     */
    private Entry root;

    /** the size of the TreeMap.
     *
     */
    private int size;

    /** Represents a key-value mapping in the TreeMap.
     *

     */
    @Getter @Setter
    private class Entry {

        private K key;
        private V value;
        private Entry left, right;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            }
            return getKey().equals(((Entry) o).getKey());
        }

        /** Returns the successor of the root of the subtree entry.
         *
         * @return the entry successor.
         */
        private Entry successor() {
            Entry entry = this;
            if (entry.getRight() != null) {
                entry = entry.getRight();
                return entry.minimum();
            }
            entry = entry.getLeft();
            while (entry.getRight() != null) {
                entry = entry.getRight();
            }
            return entry;
        }

        /** Finds the entry containing the minimum key in the subtree.
         *
         * @return the entry in the subtree with the minimum key.
         */
        private Entry minimum() {
            Entry entry = this;
            while (entry.getLeft() != null) {
                entry = entry.getLeft();
            }
            return entry;
        }

        public boolean isLeaf() {
            return getLeft() == null && getRight() == null;
        }

        public boolean hasBothChildren() {
            return getLeft() != null && getRight() != null;
        }

        public boolean isLeftChild(Entry entry) {
            if (getLeft() != null && entry != null) {
                return getLeft().equals(entry);
            }
            return false;
        }

        public boolean isRightChild(Entry entry) {
            if (getRight() != null && entry != null) {
                return getRight().equals(entry);
            }
            return false;
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
            this.root = new Entry(key, value);
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
        Entry node = root;
        int cmp;
        while (node != null) {
            cmp = node.getKey().compareTo(key);
            if (cmp == 0) {
                return true;
            } else if (cmp > 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return false;
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
            int cmp = entry.getKey().compareTo(key);
            if (cmp == 0) {
                return (V) entry.getValue();
            } else if (cmp > 0) {
                entry = entry.getLeft();
            } else {
                entry = entry.getRight();
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
        Entry parent = null;
        Entry node = root;
        while (node != null) {
            int cmp = node.getKey().compareTo(key);
            if (cmp == 0) {
                node.setValue(value);
                return;
            } else if (cmp > 0) {
                parent = node;
                node = node.getLeft();
            } else {
                parent = node;
                node = node.getRight();
            }
        }
        Entry entry = new Entry(key, value);
        if (parent == null) {
            root = entry; // tree was empty
        } else if (parent.getKey().compareTo(key) > 0) {
            parent.setLeft(entry);
        } else {
            parent.setRight(entry);
        }
        size++;
    }

    /** Returns a Set view of the keys contained in this map.
     *
     * @return a set of the keys.
     */
    @Override
    public Set<K> keySet() {
        if (root == null) {
            return new TreeSet<>();
        }
        Set<K> set = new TreeSet<>();
        Queue<Entry> q = new LinkedList<>();
        Entry entry = root;
        q.offer(entry);
        while (!q.isEmpty()) {
            entry = q.poll();
            set.add(entry.getKey());
            if (entry.getLeft() != null) {
                q.offer(entry.getLeft());
            }
            if (entry.getRight() != null) {
                q.offer(entry.getRight());
            }
        }
        return set;
    }
//        Set<K> set = new TreeSet<>();
//        Queue<Entry> queue = new LinkedList<>();
//        Entry entry = root;
//        if (entry == null) {
//            return set;
//        }
//        queue.offer(entry);
//        while (!queue.isEmpty()) {
//            entry = queue.poll();
//            set.add(entry.getKey());
//            if (entry.getLeft() != null) {
//                queue.offer(entry.getLeft());
//            }
//            if (entry.getRight() != null) {
//                queue.offer(entry.getRight());
//            }
//        }
//        return set;
//    }

    @Override
    public V remove(K key) {
        if (key == null || root == null) {
            return null;
        }
        return removeEntry(root, key, null);
    }

    @Override
    public V remove(K key, V value) {
        if (key == null || root == null) {
            return null;
        }
        return removeEntry(root, key, value);
    }
    /**
     * The helper function of remove
     */
    private V removeEntry(Entry entry, K key, V val) {
        int cmp;
        V value = null;
        Entry parent = null;
        Entry successor = null;
        Entry successorParent = null;
        // Find the node with the specified key.
        while (entry != null) {
            cmp = entry.getKey().compareTo(key);
            if (cmp < 0) {
                parent = entry;
                entry = entry.getRight();
            } else if (cmp > 0) {
                parent = entry;
                entry = entry.getLeft();
            } else {
                break;
            }
        }
        if (entry == null) {
            return null;
        }
        if (val != null && !entry.getValue().equals(val)) {
            return null;
        }
        size--;
        // 3 cases to consider: node has both children; node has one child; and node is a leaf (i.e. no children).
        //Case 1: node has both children.
        if (entry.hasBothChildren()) {
            successor = entry;
            // Find successor.
            if (successor.getRight() != null) {
                successorParent = successor;
                successor = successor.getRight();
                while (successor.getLeft() != null) {
                    successorParent = successor;
                    successor = successor.getLeft();
                }
            } else {
                successorParent = successor;
                successor = successor.getLeft();
                while (successor.getRight() != null) {
                    successorParent = successor;
                    successor = successor.getRight();
                }
            }
            // Switch key-value pair with successor.
            value = entry.getValue();
            K tempKey = entry.getKey();
            entry.setKey(successor.getKey());
            entry.setValue(successor.getValue());
            successor.setKey(tempKey);
            successor.setValue(value);
            // Successor will always have one child or none (leaf).
            entry = successor;
            parent = successorParent;
        }
        if (value == null) {
            value = entry.getValue();
        }
        // 3 cases to consider: node has both children; node is a leaf (i.e. no children); and node has one child.
        //Case 2: node is a leaf (i.e. no children).
        if (entry.isLeaf()) {
            if (parent != null && parent.isLeftChild(entry)) {
                parent.setLeft(null);
            } else if (parent != null) {
                parent.setRight(null);
            } else {
                root = null;
            }
        } else { // Case 3: node has one child.
            if (parent != null && parent.isLeftChild(entry)) {
                if (entry.getLeft() != null) {
                    parent.setLeft(entry.getLeft());
                } else {
                    parent.setLeft(entry.getRight());
                }
            } else if (parent != null && parent.isRightChild(entry)){
                if (entry.getLeft() != null) {
                    parent.setRight(entry.getLeft());
                } else {
                    parent.setRight(entry.getRight());
                }
            } else {
                if (root.getLeft() != null) {
                    root = root.getLeft();
                } else {
                    root = root.getRight();
                }
            }
        }
        return value;
    }

    /**
     * Returns an iterator over the keys, K.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return new EntryIterator();
    }

    public void printInOrder() {
        if (root == null) {
            return;
        }
        PriorityQueue<K> pq = new PriorityQueue<>();
        Queue<Entry> q = new LinkedList<>();
        Entry entry = root;
        q.offer(entry);
        while (!q.isEmpty()) {
            entry = q.poll();
            pq.offer(entry.getKey());
            if (entry.getLeft() != null) {
                q.offer(entry.getLeft());
            }
            if (entry.getRight() != null) {
                q.offer(entry.getRight());
            }
        }
        while (!pq.isEmpty()) {
            System.out.println(pq.poll().toString());
        }
    }
}
