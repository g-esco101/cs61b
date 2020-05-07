import java.util.StringJoiner;

/** A circular ArrayDeque data structure. nextFirst points to the index in the items array
 * where an item will be added when addFirst is invoked. nextFirst points to the index in the
 *  items array where an item will be added when addLast is invoked.
 * */
public class ArrayDeque<T> implements Deque<T> {

    /** The resizing factor. If items is at full capacity and an item is added,
     * the array will resize to a size of FACTOR multiplied by the length of the array items.
     */
    private static final int FACTOR = 2;

    /** The load factor of the array items. If size divided by capacity is less than LOAD_FACTOR,
     * the array is resized to a size of the length of the array items divided by FACTOR.
     */
    private static final double LOAD_FACTOR = 0.25;

    /** The number of items in the deque */
    private int size;

    /** The array of items. */
    private T[] items;

    /** A pointer to the empty location that is before the first item in the ArrayDeque.
     * It is always initialized to zero. */
    private int nextFirst;

    /** A pointer to the empty location that is after the last item in the ArrayDeque.
     * It is always initialized ot one.*/
    private int nextLast;

    /** Creates a circular ArrayDeque with a capacity of 8. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /** Creates a circular ArrayDeque that is a deep copy of other.
     *
     * @param other is a circular ArrayDeque.
     * @exception NullPointerException when other is null.
     */
    public ArrayDeque(ArrayDeque<T> other) {
        if (other == null) {
            throw new NullPointerException("other cannot be null.");
        }
        items = (T[]) new Object[other.items.length];
        size = other.size;
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        if (other.isEmpty()) {
            return;
        }
        System.arraycopy(other.items, 0, items, 0, items.length);
    }

    /**
     * Adds an item of type T to the front of the deque.
     *
     * @param item the item to be added.
     */
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * FACTOR);
        }
        items[nextFirst] = item;
        nextFirst = decrement(nextFirst);
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     *
     * @param item the item to be added.
     */
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = increment(nextLast);
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
        nextFirst = increment(nextFirst);
        T item = items[nextFirst];
        items[nextFirst] = null;
        size--;
        downsize();
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
        nextLast = decrement(nextLast);
        T item = items[nextLast];
        items[nextLast] = null;
        size--;
        downsize();
        return item;
    }

    /** Resizes the underlying array to the target capacity.
     *
     * @param capacity the size of the new items array.
     */
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        for (int i = 0, first = increment(nextFirst); i < size; i++, first = increment(first)) {
            newItems[i] = items[first];
        }
        items = newItems;
        if (isEmpty()) {
            nextFirst = 0;
            nextLast = 1;
        } else {
            nextFirst = capacity - 1;
            nextLast = size;
        }
    }

    /** If the size of the items array is less than size divided by the lenth, the array is resized
     * to a size equal to the length of the items array divided by the load factor.
     */
    private void downsize() {
        double usage = (double) size / items.length;
        if (usage < LOAD_FACTOR) {
            resize(items.length / FACTOR);
        }
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     *
     * @param index is the position of the item to retrieve.
     */
    @Override
    public T get(int index) {
        if (isEmpty() || index < 0 || index >= size) {
            return null;
        }
        int first = increment(nextFirst);
        return items[(first + index) % items.length];
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
     * Returns true if deque is empty, false otherwise.
     *
     * @return true if it is empty and false if it is not empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line. If ArrayDeque
     * is empty it prints nothing.
     */
    @Override
    public void printDeque() {
        if (!isEmpty()) {
            int i = increment(nextFirst);
            do {
                System.out.printf("%s ", items[i].toString());
                i = increment(i);
            } while (i != nextLast);
        }
        System.out.println();
    }

    /**
     * Creates a string that represent this ArrayDeque
     * @return a string that represents this ArrayDeque
     */
    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(" ");
        if (!isEmpty()) {
            int i = increment(nextFirst);
            do {
                joiner.add(items[i].toString());
                i = increment(i);
            } while (i != nextLast);
        }
        return joiner.toString();
    }

    /** Adds one and if the result is greater than the length of the items array it returns 0.
     *
     * @param augend the integer to increment.
     * @return augend plus one or 0 if the result is greater than the length of the items array.
     */
    private int increment(int augend) {
        return (augend + 1) % items.length;
    }

    /** subtracts one and if the result is less than zero, returns the position af the end of
     * the array items.
     *
     * @param minuend the integer to decrement.
     * @return minuend minus one or the last position in the array items.
     */
    private int decrement(int minuend) {
        return (minuend - 1 + items.length) % items.length;
    }
}
