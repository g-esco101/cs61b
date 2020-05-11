package old;

import src.LinkedListDeque;

/** Tests functianality that is specific to src.LinkedListDeque */
public class TestLinkedListDeque {

	/** Creates a list and a deep copy of it, removes two elements from one, and then compares them. */
	public static void linkedListDequeConstructorTest() {
		System.out.println("***** Running linkedListDequeConstructorTest *****");

		LinkedListDeque<String> lld1 = new LinkedListDeque<>();
		boolean passed = TestUtility.checkEmpty(true, lld1.isEmpty());

		String expect = "data structures is the new skateboarding true false";
		lld1.addFirst("false");
		lld1.addFirst("true");
		lld1.addFirst("skateboarding");
		lld1.addFirst("new");
		lld1.addFirst("the");
		lld1.addFirst("is");
		lld1.addFirst("structures");
		lld1.addFirst("data");
		passed = TestUtility.checkEmpty(false, lld1.isEmpty()) && passed;
		passed = TestUtility.checkSize(8, lld1.size()) && passed;
		passed = TestUtility.checkString(expect, lld1.toString()) && passed;
		LinkedListDeque<String> lld2 = new LinkedListDeque<>(lld1);
		lld1.removeFirst();
		lld1.removeLast();
		passed = TestUtility.checkEmpty(false, lld2.isEmpty()) && passed;
		passed = TestUtility.checkSize(8, lld2.size()) && passed;
		passed = TestUtility.checkString(expect, lld2.toString()) && passed;
		TestUtility.printTestStatus(passed);

		expect = "structures is the new skateboarding true";
		passed = TestUtility.checkEmpty(false, lld1.isEmpty()) && passed;
		passed = TestUtility.checkSize(6, lld1.size()) && passed;
		passed = TestUtility.checkString(expect, lld1.toString()) && passed;
		TestUtility.printTestStatus(passed);

		LinkedListDeque<String> lld3 = new LinkedListDeque<>(lld1);
		passed = TestUtility.checkEmpty(false, lld3.isEmpty()) && passed;
		passed = TestUtility.checkSize(6, lld3.size()) && passed;
		passed = TestUtility.checkString(expect, lld3.toString()) && passed;
		TestUtility.printTestStatus(passed);

		try {
			LinkedListDeque<String> lld0 = new LinkedListDeque<>(null);
			passed = false;
		} catch (NullPointerException e) {
			passed = TestUtility.checkString("other cannot be null.", e.getMessage()) && passed;
		}
		TestUtility.printTestStatus(passed);

		expect = "";
		LinkedListDeque<String> lld5 = new LinkedListDeque<>(new LinkedListDeque<>());
		passed = TestUtility.checkEmpty(true, lld5.isEmpty()) && passed;
		passed = TestUtility.checkSize(0, lld5.size()) && passed;
		passed = expect.equals(lld5.toString()) && passed;
		TestUtility.printTestStatus(passed);
	}

	/** Tests getRecursive.
	 *
	 * @param list1 an empty src.LinkedListDeque.
	 * @param list2 an empty src.LinkedListDeque.
	 * @param list3 an empty src.LinkedListDeque.
	 */
	public static void getRecursiveTest(LinkedListDeque<String> list1, LinkedListDeque<String> list2, LinkedListDeque<String> list3) {
		System.out.println("***** Running getRecursiveTest *****");

		boolean passed = TestUtility.checkEmpty(true, list1.isEmpty());
		passed = TestUtility.checkSize(0, list1.size()) && passed;
		passed = list1.get(0) == null && passed;

		LinkedListDeque<String> list = TestUtility.deque2(list1);
		passed = TestUtility.checkEmpty(false, list.isEmpty()) && passed;
		passed = TestUtility.checkSize(8, list.size()) && passed;
		passed = TestUtility.checkString("data", list.getRecursive(0)) && passed;
		passed = TestUtility.checkString("the", list.getRecursive(3)) && passed;
		passed = TestUtility.checkString("new", list.getRecursive(4)) && passed;
		passed = TestUtility.checkString("skateboarding", list.getRecursive(5)) && passed;
		passed = TestUtility.checkString("false", list.getRecursive(7)) && passed;
		TestUtility.printTestStatus(passed);

		passed = null == list.get(-1) && passed;
		passed = null == list.get(8) && passed;
		TestUtility.printTestStatus(passed);

		passed = TestUtility.checkEmpty(true, list2.isEmpty()) && passed;
		passed = TestUtility.checkSize(0, list2.size()) && passed;
		list = list2;
		list.addFirst("first");
		passed = TestUtility.checkEmpty(false, list.isEmpty()) && passed;
		passed = TestUtility.checkSize(1, list.size()) && passed;
		passed = TestUtility.checkString("first", list.getRecursive(0)) && passed;
		list.addLast("second");
		passed = TestUtility.checkEmpty(false, list.isEmpty()) && passed;
		passed = TestUtility.checkSize(2, list.size()) && passed;
		passed = TestUtility.checkString("first", list.getRecursive(0)) && passed;
		passed = TestUtility.checkString("second", list.getRecursive(1)) && passed;
		list.addFirst("zero");
		passed = TestUtility.checkEmpty(false, list.isEmpty()) && passed;
		passed = TestUtility.checkSize(3, list.size()) && passed;
		passed = TestUtility.checkString("zero", list.getRecursive(0)) && passed;
		passed = TestUtility.checkString("first", list.getRecursive(1)) && passed;
		passed = TestUtility.checkString("second", list.getRecursive(2)) && passed;
		TestUtility.printTestStatus(passed);

		passed = TestUtility.checkEmpty(true, list3.isEmpty()) && passed;
		passed = TestUtility.checkSize(0, list3.size()) && passed;
		list = TestUtility.dequeFirstThenAllLast(list3);
		list.removeFirst();
		passed = TestUtility.checkSize(7, list.size()) && passed;
		passed = TestUtility.checkEmpty(false, list.isEmpty()) && passed;
		passed = TestUtility.checkString("structures", list.getRecursive(0)) && passed;
		passed = TestUtility.checkString("new", list.getRecursive(3)) && passed;
		passed = TestUtility.checkString("skateboarding", list.getRecursive(4)) && passed;
		passed = TestUtility.checkString("false", list.getRecursive(6)) && passed;
		TestUtility.printTestStatus(passed);

		list = new LinkedListDeque<>();
		passed = TestUtility.checkEmpty(true, list.isEmpty()) && passed;
		passed = TestUtility.checkSize(0, list.size()) && passed;
		passed = null == list.getRecursive(0) && passed;
		TestUtility.printTestStatus(passed);
	}

}
