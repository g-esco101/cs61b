package bearmaps;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class ArrayHeapMinPQ<T extends Comparable<T>> implements ExtrinsicMinPQ<T> {

    private int size;

    private static final int RESIZE_FACTOR = 2;

    private static final double EMPTY_FACTOR = 0.25;

    /** A representation of minHeap that is in ascending order by their priority.
     *
     */
    @Getter
    public Node<T>[] minHeap;

    /** Items in the heap and they are ordered naturally.
     *
     */
    private TreeSet<T> items;

    public ArrayHeapMinPQ() {
        this.size = 0;
        items = new TreeSet<>();
        minHeap = new ArrayHeapMinPQ.Node[10];
    }

    public ArrayHeapMinPQ(Node<T>[] nodes) {
        this.size = nodes.length;
        items = new TreeSet<>();
        minHeap = new ArrayHeapMinPQ.Node[size + 1];
        Arrays.sort(nodes);
        for (int i = 0; i < nodes.length; i++) {
            minHeap[i + 1] = nodes[i];
            items.add(nodes[i].getItem());
        }
    }

    public ArrayHeapMinPQ(int initialCapacity) {
        this.size = 0;
        items = new TreeSet<>();
        minHeap = new ArrayHeapMinPQ.Node[initialCapacity + 1];
    }

    /** Adds an item of type T with the given priority. Assumes that item is never null.
     *
     * @param item - item to add to minHeap
     * @param priority - the priority of the add item.
     * @throws IllegalArgumentException - if the item is already in the heap.
     */
    @Override
    public void add(T item, double priority) throws IllegalArgumentException {
        Node node = new Node(item, priority);
        if (items.contains(item)) {
            throw new IllegalArgumentException("That item already exists.");
        }
        if (size == minHeap.length - 1) {
            resize(minHeap.length * RESIZE_FACTOR);
        }
        minHeap[size + 1] = node;
        swim(size + 1);
        items.add(item);
        size++;
    }

    /** Returns true if the PQ contains the given item.
     *
     * @param item - item to find.
     * @return true if the Heap contains the item. Otherwise, returns false.
     */
    @Override
    public boolean contains(T item) {
        return items.contains(item);
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
        if (smallest == null) {
            throw new NoSuchElementException("Cannot find smallest.");
        }
        return smallest;
    }

    @Override
    public T removeSmallest() throws NoSuchElementException{
        if (size == 0) {
            throw new NoSuchElementException("The Heap is empty.");
        }
        T smallest = (T) minHeap[1].item;
        if (smallest == null) {
            throw new NoSuchElementException("Cannot find smallest.");
        }
        double emptyFactor = (double) (size - 1) / (minHeap.length - 1);
        if (emptyFactor < EMPTY_FACTOR) {
            resize(minHeap.length / RESIZE_FACTOR);
        }
        minHeap[1] = (Node<T>) minHeap[size];
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
//        Node node = getNode(item);
//        if (node == null) {
//            throw new IllegalArgumentException("Cannot change priority of item that cannot be found.");
//        }
    }

    private void resize(int capacity) {
        Node[] heap = new ArrayHeapMinPQ.Node[capacity + 1];
        for (int i = 0; i <= size(); i++) {
            heap[i] = minHeap[i];
        }
        minHeap = heap;
        System.out.printf("new capacity: %d. size: %d \n", minHeap.length, size());
    }

    private int parent(int k) {
        return k / 2;
    }

    private int leftChild(int k) {
        return k * 2;
    }

    private int rightChild(int k) {
        return k * 2 + 1;
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
            if (left < size() && greater(left, left+1)) left++;
            if (!greater(k, left)) break;
            exch(k, left);
            k = left;
        }
    }

    private boolean greater(int i, int j) {
        return minHeap[i].compareTo(minHeap[j]) > 0;
    }

    private void exch(int i, int j) {
        Node<T> temp = minHeap[i];
        minHeap[i] = minHeap[j];
        minHeap[j] = temp;
    }

    public void printSimpleHeapDrawing() {
        int depth = ((int) (Math.log(size()) / Math.log(2)));
        int level = 0;
        int itemsUntilNext = (int) Math.pow(2, level);
        for (int j = 0; j < depth; j++) {
            System.out.print(" ");
        }

        for (int i = 1; i <= size(); i++) {
            System.out.printf("%s ", minHeap[i].toString());
            if (i == itemsUntilNext) {
                System.out.println();
                level++;
                itemsUntilNext += Math.pow(2, level);
                depth--;
                for (int j = 0; j < depth; j++) {
                    System.out.print(" ");
                }
            }
        }
        System.out.println();
    }

    public void printFancyHeapDrawing() {
        String drawing = fancyHeapDrawingHelper(1, "");
        System.out.println(drawing);
    }

    /* Recursive helper method for toString. */
    private String fancyHeapDrawingHelper(int index, String soFar) {
        if (index >= size() + 1 || minHeap[index] == null) {
            return "";
        } else {
            String toReturn = "";
            int rightIndex = 2 * index + 1;
            toReturn += fancyHeapDrawingHelper(rightIndex, "        " + soFar);
            if (rightIndex < size() + 1 && minHeap[rightIndex] != null) {
                toReturn += soFar + "    /";
            }
            toReturn += "\n" + soFar + minHeap[index] + "\n";
            int leftIndex = 2 * index;
            if (leftIndex < size() + 1 && minHeap[leftIndex] != null) {
                toReturn += soFar + "    \\";
            }
            toReturn += fancyHeapDrawingHelper(leftIndex, "        " + soFar);
            return toReturn;
        }
    }

    @Getter @Setter
    private static class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
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
            T obj = ((ArrayHeapMinPQ.Node<T>) o).getItem();
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

        @Override
        public int compareTo(Node<T> o) {
            return Double.compare(getPriority(), o.getPriority());
        }
    }
}

//    private int getMinHeapIndex(Node<T> node, int low, int high) {
//        while (low <= high) {
//            int mid = (low + high) / 2;
//            int cmp = node.compareTo(minHeap[mid]);
//            if (cmp == 0) {
//                int parent = parent
//                T item = (T) minHeap[mid];
//                cmp = node.getItem().compareTo(item);
//                if (cmp == 0) {
//                    return mid;
//                }
//
//                return mid;
//            } else if (cmp < 0) {
//                high = mid - 1;
//            } else {
//                low = mid + 1;
//            }
//        }
//        return -1;
//    }
//    private int getMinHeapIndexHelper(int index, T item, double priority) {
//        int parentIndex = parent(index);
//        Node<T> parent = minHeap[parentIndex];
//        Node<T> left = minHeap[leftChild(parentIndex)];
//        Node<T> right = minHeap[rightChild(parentIndex)];
//
//        if (leftChild(parent))
//        while (low <= high) {
//            int mid = (low + high) / 2;
//            int cmp = node.compareTo(minHeap[mid]);
//            if (cmp == 0) {
//                int parent = parent
//                T item = (T) minHeap[mid];
//                cmp = node.getItem().compareTo(item);
//                if (cmp == 0) {
//                    return mid;
//                }
//
//                return mid;
//            } else if (cmp < 0) {
//                high = mid - 1;
//            } else {
//                low = mid + 1;
//            }
//        }
//        return -1;
//    }