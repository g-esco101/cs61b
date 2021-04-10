package bearmaps;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class ArrayHeapMinPQ<T extends Comparable<T>> implements ExtrinsicMinPQ<T> {

    /** The number of elements in the heap.
     *
     */
    private int size;

    /** Factor used to calculate the capaclity of the heap.
     *
     */
    private static final int RESIZE_FACTOR = 2;

    /** Factor used to determine whether or not to resize.
     *
     */
    private static final double EMPTY_FACTOR = 0.25;

    /** A representation of minHeap that is in ascending order by their priority.
     *
     */
    @Getter
    public bearmaps.ArrayHeapMinPQ.Node<T>[] minHeap;

    /** Maps an item to its index in minHeap.
     *
     */
    private Map<T, Integer> items;

    public ArrayHeapMinPQ() {
        this.size = 0;
        items = new HashMap<>();
        minHeap = new bearmaps.ArrayHeapMinPQ.Node[8];
    }

    public ArrayHeapMinPQ(int initialCapacity) {
        this.size = 0;
        items = new HashMap<>();
        minHeap = new bearmaps.ArrayHeapMinPQ.Node[initialCapacity + 1];
    }

    /** Adds an item of type T with the given priority. Assumes that item is never null.
     *
     * @param item - item to add to minHeap
     * @param priority - the priority of the add item.
     * @throws IllegalArgumentException - if the item is already in the heap.
     */
    @Override
    public void add(T item, double priority) throws IllegalArgumentException {
        bearmaps.ArrayHeapMinPQ.Node node = new bearmaps.ArrayHeapMinPQ.Node(item, priority);
        if (items.containsKey(item)) {
            throw new IllegalArgumentException("That item already exists.");
        }
        if (size == minHeap.length - 1) {
            resize(minHeap.length * RESIZE_FACTOR);
        }
        minHeap[size + 1] = node;
        items.put(item, size + 1);
        swim(size + 1);
        size++;
    }

    /** Returns true if the PQ contains the given item.
     *
     * @param item - item to find.
     * @return true if the Heap contains the item. Otherwise, returns false.
     */
    @Override
    public boolean contains(T item) {
        return items.containsKey(item);
    }

    /**  Returns the item with smallest priority. If no items exist, throw a NoSuchElementException.
     *
     * @return - the item with the smallest priority.
     */
    @Override
    public T getSmallest() throws NoSuchElementException {
        if (size == 0) {
            throw new NoSuchElementException("The Heap is empty.");
        }
        T smallest = (T) minHeap[1].item;
//        if (smallest == null) {
//            throw new NoSuchElementException("Cannot find smallest.");
//        }
        return smallest;
    }

    @Override
    public T removeSmallest() throws NoSuchElementException{
        if (size == 0) {
            throw new NoSuchElementException("The Heap is empty.");
        }
        T smallest = minHeap[1].getItem();
        double emptyFactor = (double) (size - 1) / (minHeap.length - 1);
        if (emptyFactor < EMPTY_FACTOR) {
            resize(minHeap.length / RESIZE_FACTOR);
        }
        minHeap[1] = (bearmaps.ArrayHeapMinPQ.Node<T>) minHeap[size];
        size--;
        sink(1);
        items.remove(smallest);
        return smallest;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) throws IllegalArgumentException {
        if (!items.containsKey(item)) {
            throw new IllegalArgumentException("Item not found.");
        }
        Integer index = items.get(item);
        int cmp = minHeap[index].comparePriority(priority);
        minHeap[index].setPriority(priority);
        if (cmp > 0) {
            swim(index);
        } else if (cmp < 0) {
            sink(index);
        }
    }

    private void resize(int capacity) {
        bearmaps.ArrayHeapMinPQ.Node<T>[] heap = new bearmaps.ArrayHeapMinPQ.Node[capacity + 1];
        if (size() + 1 >= 0) System.arraycopy(minHeap, 0, heap, 0, size() + 1);
        minHeap = heap;
    }

    private int parent(int k) {
        return k / 2;
    }

    private int leftChild(int k) {
        return k * 2;
    }

    private void swim(int k) {
        while (k > 1 && greater(parent(k), k)) {
            exch(k, parent(k));
            k = parent(k);
        }
    }

    private void sink(int k) {
        while (leftChild(k) <= size()) {
            int left = leftChild(k);
            if (left < size() && greater(left, left + 1)) left++;
            if (!greater(k, left)) break;
            exch(k, left);
            k = left;
        }
    }

    private boolean greater(int i, int j) {
        return minHeap[i].comparePriority(minHeap[j]) > 0;
    }

    private void exch(int i, int j) {
        items.put(minHeap[i].getItem(), j);
        items.put(minHeap[j].getItem(), i);
        bearmaps.ArrayHeapMinPQ.Node<T> temp = minHeap[i];
        minHeap[i] = minHeap[j];
        minHeap[j] = temp;
    }

    @Getter @Setter
    private static class Node<T extends Comparable<T>> {
        private double priority;
        private T item;

        public Node(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            }
            T obj = ((bearmaps.ArrayHeapMinPQ.Node<T>) o).getItem();
            return this.item.equals(obj);
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }

        @Override
        public String toString() {
            return item.toString();
        }

        public int comparePriority(bearmaps.ArrayHeapMinPQ.Node<T> o) {
            return Double.compare(getPriority(), o.getPriority());
        }

        public int comparePriority(double priority) {
            return Double.compare(getPriority(), priority);
        }

//        @Override
//        public int compareTo(bearmaps.ArrayHeapMinPQ.Node<T> o) {
//            return item.compareTo(o.getItem());
//        }
    }
}