
public class TestUtility {

    /** Creates a Deque and adds items to it.
     *
     * @param deque an implementation of Deque.
     * @return a Deque is items added to it.
     */
    public static Deque<String> dequeFirstThenAllLast(Deque<String> deque) {
        Deque<String> lld4 = deque;
        lld4.addFirst("data");
        lld4.addLast("structures");
        lld4.addLast("is");
        lld4.addLast("the");
        lld4.addLast("new");
        lld4.addLast("skateboarding");
        lld4.addLast("true");
        lld4.addLast("false");
        return lld4;
    }

    /** Creates a Deque and adds items to it.
     *
     * @param deque an implementation of Deque.
     * @return a Deque is items added to it.
     */
    public static LinkedListDeque<String> dequeFirstThenAllLast(LinkedListDeque<String> deque) {
        LinkedListDeque<String> lld4 = deque;
        lld4.addFirst("data");
        lld4.addLast("structures");
        lld4.addLast("is");
        lld4.addLast("the");
        lld4.addLast("new");
        lld4.addLast("skateboarding");
        lld4.addLast("true");
        lld4.addLast("false");
        return lld4;
    }

    /** Creates a Deque and adds items to it.
     *
     * @param deque an implementation of Deque.
     * @return a Deque is items added to it.
     */
    public static Deque<String> deque(Deque<String> deque) {
        Deque<String> lld1 = deque;
        lld1.addFirst("is");
        lld1.addFirst("structures");
        lld1.addFirst("data");
        lld1.addLast("the");
        lld1.addLast("new");
        return lld1;
    }

    /** Creates a Deque and adds items to it.
     *
     * @param deque an implementation of Deque.
     * @return a Deque is items added to it.
     */
    public static Deque<String> dequeAddLast(Deque<String> deque) {
        Deque<String> lld1 = deque;
        lld1.addLast("data");
        lld1.addLast("structures");
        lld1.addLast("is");
        lld1.addLast("the");
        lld1.addLast("new");
        lld1.addLast("skateboarding");
        lld1.addLast("true");
        lld1.addLast("false");
        return lld1;
    }

    /** Creates a Deque and adds items to it.
     *
     * @param deque an implementation of Deque.
     * @return a Deque is items added to it.
     */
    public static Deque<String> deque2(Deque<String> deque) {
        Deque<String> lld1 = deque;
        lld1.addFirst("new");
        lld1.addFirst("the");
        lld1.addFirst("is");
        lld1.addFirst("structures");
        lld1.addFirst("data");
        lld1.addLast("skateboarding");
        lld1.addLast("true");
        lld1.addLast("false");
        return lld1;
    }

    /** Creates a Deque and adds items to it.
     *
     * @param deque an implementation of Deque.
     * @return a Deque is items added to it.
     */
    public static LinkedListDeque<String> deque2(LinkedListDeque<String> deque) {
        LinkedListDeque<String> lld1 = deque;
        lld1.addFirst("new");
        lld1.addFirst("the");
        lld1.addFirst("is");
        lld1.addFirst("structures");
        lld1.addFirst("data");
        lld1.addLast("skateboarding");
        lld1.addLast("true");
        lld1.addLast("false");
        return lld1;
    }

    /** Utility method for printing out empty checks.
     *
     * @param expected result of invoking isEmpty.
     * @param actual result of invoking isEmpty.
     * @return a boolean that indicates if they are are equal
     */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /** Utility method for printing out empty checks.
     *
     * @param expected size
     * @param actual size
     * @return a boolean that indicates if they are are equal
     */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /** Utility method for checking strings.
     *
     * @param expected string value
     * @param actual string value
     * @return a boolean that indicates if they are are equal
     */
    public static boolean checkString(String expected, String actual) {
        if (!expected.equals(actual)) {
            System.out.println("\t String returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /** Prints a nice message based on whether a test passed.
     *
     * @param passed the result of a test.
     */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }
}
