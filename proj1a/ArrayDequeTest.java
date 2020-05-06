public class ArrayDequeTest {

    /** Creates a list and a deep copy of it, removes two elements from one, and then compares them. */
    public static void arrayDequeConstructorTest() {
        System.out.println("***** Running arrayDequeConstructorTest *****");

        ArrayDeque<String> array = new ArrayDeque<>();
        boolean passed = TestUtility.checkEmpty(true, array.isEmpty());

        String expect = "data structures is the new skateboarding true false";
        array.addFirst("false");
        array.addFirst("true");
        array.addFirst("skateboarding");
        array.addFirst("new");
        array.addFirst("the");
        array.addFirst("is");
        array.addFirst("structures");
        array.addFirst("data");
        passed = TestUtility.checkEmpty(false, array.isEmpty()) && passed;
        passed = TestUtility.checkSize(8, array.size()) && passed;
//        passed = TestUtility.checkString(expect, array.toString()) && passed;
        String result = array.toString();
        passed = expect.equals(array.toString()) && passed;

        ArrayDeque<String> array2 = new ArrayDeque<>(array);
        array.removeFirst();
        array.removeLast();
        passed = TestUtility.checkEmpty(false, array2.isEmpty()) && passed;
        passed = TestUtility.checkSize(8, array2.size()) && passed;
        passed = expect.equals(array2.toString()) && passed;


        expect = "structures is the new skateboarding true";

        passed = TestUtility.checkEmpty(false, array.isEmpty()) && passed;
        passed = TestUtility.checkSize(6, array.size()) && passed;
        passed = TestUtility.checkString(expect, array.toString()) && passed;
        TestUtility.printTestStatus(passed);

        Deque<String> array3 = new ArrayDeque<>(array);
        passed = TestUtility.checkEmpty(false, array3.isEmpty()) && passed;
        passed = TestUtility.checkSize(6, array3.size()) && passed;
//        passed = TestUtility.checkString(expect, array3.toString()) && passed;
        passed = expect.equals(array3.toString()) && passed;
        TestUtility.printTestStatus(passed);

        String msg = "";
        try {
            array = new ArrayDeque<>(null);
            passed = false;
        } catch (NullPointerException e) {
            passed = TestUtility.checkString("other cannot be null.", e.getMessage()) && passed;
        }
        TestUtility.printTestStatus(passed);
    }
}
