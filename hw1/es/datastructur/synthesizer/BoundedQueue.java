package es.datastructur.synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {

    /** Retrieves the buffer capacity.
     *
     * @return the buffer capacity.
     */
    int capacity();

    /** Retrieves the number of items currently in the buffer.
     *
     * @return number of items in the buffer.
     */
    int fillCount();

    /** Adds item to the end.
     *
     * @param x the item to add.
     */
    void enqueue(T x);

    /** Remove and return item from the front.
     *
     * @return item from the front.
     */
    T dequeue();

    /** Return (but do not remove) item from the front
     *
     * @return item from the front.
     */
    T peek();

    /**
     * Returns an iterator over elements of type T.
     *
     * @return an Iterator.
     */
    @Override
    Iterator<T> iterator();

    /** Empty when fillCount is zero
     *
     * @return true if fillCount is zero. Otherwise returns false.
     */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /** Full when fillCount is equal to capacity
     *
     * @return true if fillCount is equal to capacity. Otherwise, returns false.
     */
    default boolean isFull() {
        return fillCount() == capacity();
    }
}
