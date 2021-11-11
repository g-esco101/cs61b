package src;

import java.util.StringJoiner;

/** A Doubly Linked List with circular sentinel topology */
public class LinkedListDeque<T> implements Deque<T> {

    /** an inner class used to store items */
    private static class Node<T> {
        public T item;
        public Node<T> next;
        public Node<T> prev;

        public Node(T item) {
            this.item = item;
            next = null;
            prev = null;
        }
    }

    /** The number of items in the list */
    private int size;

    private final Node<T> sentinel;

    /** Creates a Doubly Linked List with circular sentinel topology. */
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /** Creates a deep copy of other, i.e a Doubly Linked List with circular sentinel topology.
     *
     * @Param other the src.LinkedListDeque to deep copy.
     * @exception NullPointerException when other is null.
     */
    public LinkedListDeque(LinkedListDeque<T> other) {
        if (other == null) {
            throw new NullPointerException("other cannot be null.");
        }
        sentinel = new Node(null);
        size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        Node<T> otherPtr = other.sentinel;
        otherPtr = otherPtr.next;
        while (otherPtr != other.sentinel) {
            addLast(otherPtr.item);
            otherPtr = otherPtr.next;
        }
    }

    /**
     * Adds an item of type T to the front of the deque.
     *
     * @param item the item to be added.
     */
    @Override
    public void addFirst(T item) {
        Node<T> node = new Node<T>(item);
        if (isEmpty()) {
            sentinel.next = node;
            sentinel.prev = node;
            node.prev = sentinel;
            node.next = sentinel;
        } else {
            node.next = sentinel.next;
            sentinel.next.prev = node;
            node.prev = sentinel;
            sentinel.next= node;
        }
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     *
     * @param item the item to be added.
     */
    @Override
    public void addLast(T item) {
        Node<T> node = new Node<T>(item);
        if (isEmpty()) {
            sentinel.next = node;
            sentinel.prev = node;
            node.prev = sentinel;
            node.next = sentinel;
        } else {
            node.prev = sentinel.prev;
            node.next = sentinel;
            sentinel.prev.next = node;
            sentinel.prev = node;
        }
        size++;
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     *
     * @return the item being removed.
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     *
     * @return the item being removed.
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return item;
    }

    /**
     *   Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     *   If no such item exists, returns null. Must not alter the deque!
     *
     *  (This does not change the value of Big O - and probably should have not been implemented, since it creates more
     *  code to maintain) It determines if it is more efficient to traverse the list beginning
     *  from the front or back by comparing the index to the size. If the index is in the first half of the list,
     *  it begins traversing from the front. If the index is in the back half of the list, it begins traversal
     *  from the back.
     *
     *  @param index is the position of the item to retrieve.
     *  @return the item at the specified index.
     */
    @Override
    public T get(int index) {
        if (isEmpty() || index < 0 || index >= size) {
            return null;
        }
        Node<T> node;

        if (index <= (size / 2)) {
            node = sentinel.next;
            while (index > 0) {
                node = node.next;
                index--;
            }
        } else {
            node = sentinel.prev;
            index = size - index;
            while (index > 1) {
                node = node.prev;
                index--;
            }
        }
        return node.item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     *
     * @param index is the position of the item to retrieve.
     * @return the item at the specified index.
     */
    public T getRecursive(int index) {
        if (isEmpty() || index < 0 || index >= size) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     *
     * @param index is the position of the item to retrieve.
     * @param node a node in the src.LinkedListDeque.
     */
    private T getRecursive(Node<T> node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursive(node.next, index - 1);
    }

    /**
     * Returns the number of items in the deque.
     *
     * @return the number of items
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque() {
        System.out.println(this.toString());
    }

    /**
     * Creates a string that represent this src.LinkedListDeque
     * @return a string that represents this src.LinkedListDeque
     */
    @Override
    public String toString() {
        Node<T> node = sentinel.next;
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        while (node != sentinel) {
            joiner.add(node.item.toString());
            node = node.next;
        }
        return joiner.toString();
    }
}
