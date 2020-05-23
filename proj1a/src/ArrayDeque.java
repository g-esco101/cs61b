package src;

import java.util.StringJoiner;

/** A circular src.ArrayDeque data structure. nextFirst points to the index in the items array
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

    /** A pointer to the empty location that is before the first item in the src.ArrayDeque.
     * It is always initialized to zero. */
    private int nextFirst;

    /** A pointer to the empty location that is after the last item in the src.ArrayDeque.
     * It is always initialized ot one.*/
    private int nextLast;

    /** The array of items. */
    private T[] items;

    /** Creates a circular src.ArrayDeque with a capacity of 8. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /** Creates a circular src.ArrayDeque that is a deep copy of other.
     *
     * @param other is a circular src.ArrayDeque.
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
     * to a size equal to the length of the items array divided by the load factor. If the capacity
     * is two, then it is not down sized.
     */
    private void downsize() {
        if (items.length == 2) {
            return;
        }
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
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line. If src.ArrayDeque
     * is empty it prints nothing.
     */
    @Override
    public void printDeque() {
        System.out.println(this.toString());
    }

    /**
     * Creates a string that represent this src.ArrayDeque
     * @return a string that represents this src.ArrayDeque
     */
    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
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

    //
//    /**
//     * Determines if this LinkedListDeque is equal to the one specified.
//     *
//     * @param o is an Object that may be a LinkedListDeque.
//     */
//    @Override
//    public boolean equals(Object o) {
//        if (o == this) {
//            return true;
//        }
//        if (o == null || !(o instanceof ArrayDeque)) {
//            return false;
//        }
//        ArrayDeque<?> other = (ArrayDeque<?>) o;
//        if (size() != other.size()) {
//            return false;
//        }
////        if (size() == 0) {
////            return true;
////        }
//        for (int i = increment(nextFirst), j = increment(other.nextFirst); i < size; i = increment(i), j = increment(j)) {
//            if (!items[i].equals(other.items[j])) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    /**
//     * Calculates the hash code for this list.
//     *
//     * @return the hash code value for this list
//     */
//    @Override
//    public int hashCode() {
//        int hashCode = 1;
//        for (int i = increment(nextFirst); i < size; i = increment(i)) {
//            hashCode = 31*hashCode + (items[i] == null ? 0 : items[i].hashCode());
//        }
//        return hashCode;
//    }
}
