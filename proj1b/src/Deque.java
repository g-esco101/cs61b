package src;

/** The src.Deque interface.
 *
 * @param <T>
 */
public interface Deque<T> {

    /**
     * Adds an item of type T to the front of the deque.
     *
     * @param item the item to be added.
     */
    void addFirst(T item);

    /**
     * Adds an item of type T to the back of the deque.
     *
     * @param item the item to be added.
     */
    void addLast(T item);

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    T removeFirst();

    /**
     * Removes and returns the item at the end of the deque.
     * If no such item exists, returns null.
     */
    T removeLast();

    /**
     * Returns the number of items in the deque.
     */
    int size();

    /**
     * Returns true if deque is empty, false otherwise.
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    void printDeque();

    /**
     *   Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     *   If no such item exists, returns null. Must not alter the deque!
     *
     *  @param index is the position of the item to retrieve.
     */
    T get(int index);
}
