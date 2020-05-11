package old;

import src.*;

/** Tests implementations of src.Deque */
public class TestDeque {

    /** Tests the addFirst, addLast, removeFirst, and removeLast
     *
     * @param deque1 an implementation of src.Deque
     * @param deque2 an implementation of src.Deque
     * @param deque3 an implementation of src.Deque
     * @param deque4 an implementation of src.Deque
     */
    private static void addRemoveTest(Deque<String> deque1, Deque<String> deque2, Deque<String> deque3, Deque<String> deque4) {
        System.out.println("***** Running addRemoveTest. *****");

        boolean passed = TestUtility.checkEmpty(true, deque1.isEmpty());
        Deque<String> deque = TestUtility.deque2(deque1);
        String expect = "";

        expect = new String("one data structures is the new skateboarding true false");
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(8, deque.size()) && passed;
        deque.addFirst("one");
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(9, deque.size()) && passed;
        passed = TestUtility.checkString(expect, deque.toString()) && passed;
        TestUtility.printTestStatus(passed);

        expect = new String("one data structures is the new skateboarding true");
        passed = TestUtility.checkString("false", deque.removeLast());
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(8, deque.size()) && passed;
        passed = expect.equals(deque.toString()) && passed;
        TestUtility.printTestStatus(passed);

        expect = new String("one data structures is the new skateboarding");
        passed = TestUtility.checkString("true", deque.removeLast());
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(7, deque.size()) && passed;
        passed = expect.equals(deque.toString()) && passed;
        TestUtility.printTestStatus(passed);

        expect = new String("data structures is the new skateboarding");
        passed = TestUtility.checkString("one", deque.removeFirst());
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(6, deque.size()) && passed;
        passed = expect.equals(deque.toString()) && passed;
        TestUtility.printTestStatus(passed);

        expect = new String("data structures is the new");
        passed = TestUtility.checkString("skateboarding", deque.removeLast());
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(5, deque.size()) && passed;
        passed = expect.equals(deque.toString()) && passed;
        TestUtility.printTestStatus(passed);

        expect = new String("first data structures is the new");
        deque.addFirst("first");
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(6, deque.size()) && passed;
        passed = expect.equals(deque.toString()) && passed;
        TestUtility.printTestStatus(passed);

        expect = new String("first data structures is the new last");
        deque.addLast("last");
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(7, deque.size()) && passed;
        passed = expect.equals(deque.toString()) && passed;
        TestUtility.printTestStatus(passed);

        passed = TestUtility.checkEmpty(true, deque2.isEmpty()) && passed;
        deque = TestUtility.deque2(deque2);

        expect = new String("one data structures is the new skateboarding true false");
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(8, deque.size()) && passed;
        deque.addFirst("one");
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(9, deque.size()) && passed;
        passed = expect.equals(deque.toString()) && passed;
        TestUtility.printTestStatus(passed);

        passed = TestUtility.checkEmpty(true, deque3.isEmpty()) && passed;
        deque = TestUtility.dequeAddLast(deque3);

        expect = new String("data structures is the new skateboarding true false one");
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(8, deque.size()) && passed;
        deque.addLast("one");
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(9, deque.size()) && passed;
        passed = expect.equals(deque.toString()) && passed;
        TestUtility.printTestStatus(passed);

        passed = TestUtility.checkEmpty(true, deque4.isEmpty()) && passed;
        deque = TestUtility.dequeFirstThenAllLast(deque4);

        expect = new String("one data structures is the new skateboarding true false");
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(8, deque.size()) && passed;
        deque.addFirst("one");
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(9, deque.size()) && passed;
        passed = expect.equals(deque.toString()) && passed;
        TestUtility.printTestStatus(passed);

        expect = new String("new skateboarding true false");
        passed = TestUtility.checkString("one", deque.removeFirst()) && passed;
        passed = TestUtility.checkString("data", deque.removeFirst()) && passed;
        passed = TestUtility.checkString("structures", deque.removeFirst()) && passed;
        passed = TestUtility.checkString("is", deque.removeFirst()) && passed;
        passed = TestUtility.checkString("the", deque.removeFirst()) && passed;
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(4, deque.size()) && passed;
        passed = expect.equals(deque.toString()) && passed;
        TestUtility.printTestStatus(passed);

        expect = new String("skateboarding true false");
        passed = TestUtility.checkString("new", deque.removeFirst()) && passed;
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(3, deque.size()) && passed;
        passed = expect.equals(deque.toString()) && passed;
        TestUtility.printTestStatus(passed);

        expect = new String("skateboarding true");
        passed = TestUtility.checkString("false", deque.removeLast()) && passed;
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(2, deque.size()) && passed;
        passed = expect.equals(deque.toString()) && passed;
        TestUtility.printTestStatus(passed);

        expect = new String("skateboarding");
        passed = TestUtility.checkString("true", deque.removeLast()) && passed;
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(1, deque.size()) && passed;
        passed = expect.equals(deque.toString()) && passed;
        TestUtility.printTestStatus(passed);

        expect = new String("");
        passed = TestUtility.checkString("skateboarding", deque.removeFirst()) && passed;
        passed = TestUtility.checkEmpty(true, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(0, deque.size()) && passed;
        passed = expect.equals(deque.toString()) && passed;
        TestUtility.printTestStatus(passed);
        passed = deque.removeFirst() == null && passed;
        TestUtility.printTestStatus(passed);

        expect = new String("two");
        deque.addFirst("two");
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(1, deque.size()) && passed;
        passed = expect.equals(deque.toString()) && passed;
        TestUtility.printTestStatus(passed);

        expect = new String("");
        passed = TestUtility.checkString("two", deque.removeLast()) && passed;
        passed = TestUtility.checkEmpty(true, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(0, deque.size()) && passed;
        passed = expect.equals(deque.toString()) && passed;
        TestUtility.printTestStatus(passed);
        passed = null == deque.removeLast() && passed;
        TestUtility.printTestStatus(passed);
    }

    /** Tests get.
     *
     * @param deque1 an implementation of src.Deque
     * @param deque2 an implementation of src.Deque
     * @param deque3 an implementation of src.Deque
     */
	private static void getTest(Deque<String> deque1, Deque<String> deque2, Deque<String> deque3) {
		System.out.println("***** Running getTest *****");

        boolean passed = TestUtility.checkEmpty(true, deque1.isEmpty());
        passed = TestUtility.checkSize(0, deque1.size()) && passed;
        passed = deque1.get(0) == null && passed;

        Deque<String> deque = TestUtility.deque2(deque1);
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(8, deque.size()) && passed;
		passed = TestUtility.checkString("data", deque.get(0)) && passed;
        passed = TestUtility.checkString("the", deque.get(3)) && passed;
        passed = TestUtility.checkString("new", deque.get(4)) && passed;
        passed = TestUtility.checkString("skateboarding", deque.get(5)) && passed;
        passed = TestUtility.checkString("false", deque.get(7)) && passed;
        TestUtility.printTestStatus(passed);

        passed = null == deque.get(-1) && passed;
        passed = null == deque.get(8) && passed;
        TestUtility.printTestStatus(passed);

        passed = TestUtility.checkEmpty(true, deque2.isEmpty()) && passed;
        passed = TestUtility.checkSize(0, deque2.size()) && passed;
        deque = deque2;
        deque.addFirst("first");
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(1, deque.size()) && passed;
        passed = TestUtility.checkString("first", deque.get(0)) && passed;
        deque.addLast("second");
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(2, deque.size()) && passed;
        passed = TestUtility.checkString("first", deque.get(0)) && passed;
        passed = TestUtility.checkString("second", deque.get(1)) && passed;
        deque.addFirst("zero");
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(3, deque.size()) && passed;
        passed = TestUtility.checkString("zero", deque.get(0)) && passed;
        passed = TestUtility.checkString("first", deque.get(1)) && passed;
        passed = TestUtility.checkString("second", deque.get(2)) && passed;
        TestUtility.printTestStatus(passed);

        passed = TestUtility.checkEmpty(true, deque3.isEmpty()) && passed;
        passed = TestUtility.checkSize(0, deque3.size()) && passed;
		deque = TestUtility.dequeFirstThenAllLast(deque3);
		deque.removeFirst();
		passed = TestUtility.checkSize(7, deque.size()) && passed;
		passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkString("structures", deque.get(0)) && passed;
        passed = TestUtility.checkString("new", deque.get(3)) && passed;
        passed = TestUtility.checkString("skateboarding", deque.get(4)) && passed;
        passed = TestUtility.checkString("false", deque.get(6)) && passed;
		TestUtility.printTestStatus(passed);
	}

    /** Tests printDeque
     *
     * @param deque1 an implementation of src.Deque
     * @param deque2 an implementation of src.Deque
     * @param deque3 an implementation of src.Deque
     * @param deque4 an implementation of src.Deque
     */
	private static void printDequeTest(Deque<String> deque1, Deque<String> deque2, Deque<String> deque3, Deque<String> deque4) {
		System.out.println("***** Running printDequeTest test. *****");

        boolean passed = TestUtility.checkEmpty(true, deque1.isEmpty());
        passed = TestUtility.checkSize(0, deque1.size()) && passed;
        System.out.print("\tPrinting out deque (empty): ");
        deque1.printDeque();
		Deque<String> deque = TestUtility.deque2(deque1);
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(8, deque.size()) && passed;
		System.out.print("\tPrinting out deque: ");
		deque.printDeque();
        TestUtility.printTestStatus(passed);

        passed = TestUtility.checkEmpty(true, deque2.isEmpty());
        passed = TestUtility.checkSize(0, deque2.size()) && passed;
		deque = TestUtility.dequeAddLast(deque2);
        passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
        passed = TestUtility.checkSize(8, deque.size()) && passed;
		System.out.print("\tPrinting out deque: ");
		deque.printDeque();
		TestUtility.printTestStatus(passed);

        passed = TestUtility.checkEmpty(true, deque3.isEmpty());
        passed = TestUtility.checkSize(0, deque3.size()) && passed;
		deque = TestUtility.dequeFirstThenAllLast(deque3);
		passed = TestUtility.checkSize(8, deque.size()) && passed;
		passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
		System.out.print("\tPrinting out arrayAddLast: ");
		deque.printDeque();
		TestUtility.printTestStatus(passed);

        passed = TestUtility.checkEmpty(true, deque4.isEmpty());
        passed = TestUtility.checkSize(0, deque4.size()) && passed;
		deque = TestUtility.deque(deque4);
		passed = TestUtility.checkSize(5, deque.size()) && passed;
		passed = TestUtility.checkEmpty(false, deque.isEmpty()) && passed;
		System.out.print("\tPrinting out deque: ");
		deque.printDeque();
		TestUtility.printTestStatus(passed);
	}

    public static void main(String[] args) {
        System.out.println("Running tests on src.LinkedListDeque.\n");
        TestLinkedListDeque.linkedListDequeConstructorTest();
		printDequeTest(new LinkedListDeque<>(), new LinkedListDeque<>(), new LinkedListDeque<>(), new LinkedListDeque<>());
		getTest(new LinkedListDeque<>(), new LinkedListDeque<>(), new LinkedListDeque<>());
        TestLinkedListDeque.getRecursiveTest(new LinkedListDeque<>(), new LinkedListDeque<>(), new LinkedListDeque<>());
        addRemoveTest(new LinkedListDeque<>(), new LinkedListDeque<>(), new LinkedListDeque<>(), new LinkedListDeque<>());

        System.out.println("Running tests on src.ArrayDeque.\n");
		TestArrayDeque.arrayDequeConstructorTest();
		printDequeTest(new ArrayDeque<>(), new ArrayDeque<>(), new ArrayDeque<>(), new ArrayDeque<>());
		getTest(new ArrayDeque<>(), new ArrayDeque<>(), new ArrayDeque<>());
		addRemoveTest(new ArrayDeque<>(), new ArrayDeque<>(), new ArrayDeque<>(), new ArrayDeque<>());
    }
}
