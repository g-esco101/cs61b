package es.datastructur.synthesizer;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayRingBuffer<T>  implements BoundedQueue<T> {

    private class ArrayRingBufferIterator<T> implements Iterator<T> {

        private int next;

        public ArrayRingBufferIterator() {
            next = first;
        }
        /**
         * Returns true if the iteration has more elements.
         * (In other words, returns true if next would
         * return an element rather than throwing an exception.)
         *
         * @return true if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return next != last;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            T item = (T) rb[next];
            next = increment(next);
            return item;
        }
    }

    /** Index for the next dequeue or peek. */
    private int first;
    /** Index for the next enqueue. */
    private int last;
    /** Variable for the fillCount. */
    private int fillCount;
    /** Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        fillCount = 0;
        first = 0;
        last = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        fillCount++;
        last = increment(last);
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T item = rb[first];
        first = increment(first);
        fillCount--;
        return item;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    /**
     * retrieves the buffer capacity.
     *
     * @return the buffer capacity.
     */
    @Override
    public int capacity() {
        return rb.length;
    }

    /**
     * Retrieves the number of items currently in the buffer.
     *
     * @return number of items in the buffer.
     */
    @Override
    public int fillCount() {
        return fillCount;
    }

    /** Adds one and if the result is greater than the length of the rb array it returns 0.
     *
     * @param augend the integer to increment.
     * @return augend plus one or 0 if the result is greater than the length of the items array.
     */
    private int increment(int augend) {
        return (augend + 1) % rb.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    // Instructed not to override hashcode for now.
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        ArrayRingBuffer other = (ArrayRingBuffer) o;
        if (capacity() != other.capacity() || fillCount() != other.fillCount()) {
            return false;
        }
        for (Iterator iter = iterator(), oIter = other.iterator(); iter.hasNext(); ) {
            if (!iter.next().equals(oIter.next())) {
                return false;
            }
        }
        return true;
    }
}
