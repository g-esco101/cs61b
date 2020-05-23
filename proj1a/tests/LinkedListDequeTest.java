package tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.Random;
import java.util.StringJoiner;

import src.LinkedListDeque;

@DisplayName("A LinkedListDeque")
class LinkedListDequeTest {

    LinkedListDeque<Object> linkedListDeque;

    private final String item = "skateboarding";

    @Nested
    @DisplayName("when new")
    class WhenNew {

        @BeforeEach
        void createNewLinkedListDeque() {
            linkedListDeque = new LinkedListDeque<>();
        }

        @Test
        @DisplayName("is empty")
        void isEmpty() {
            assertTrue(linkedListDeque.isEmpty());
        }

        @Test
        @DisplayName("has size zero")
        void sizeIsZero() {
            assertEquals(0, linkedListDeque.size());
        }

        @Test
        @DisplayName("has null in the front")
        void retrievesNullWhenFirstItemIsRemoved() {
            assertNull(linkedListDeque.removeFirst());
        }

        @Test
        @DisplayName("has null in the back")
        void retrievesNullWhenLastItemIsRemoved() {
            assertNull(linkedListDeque.removeLast());
        }

        @Test
        @DisplayName("retrieves null when iteratively getting a value")
        void retrievesNullWhenGettingIteratively() {
            assertNull(linkedListDeque.get(0));
        }

        @Test
        @DisplayName("retrieves null when recursively getting a value")
        void retrievesNullWhenGettingRecursively() {
            assertNull(linkedListDeque.getRecursive(0));
        }

        @Test
        @DisplayName("is represented by the string []")
        void representedByEmptyString() {
            assertEquals("[]", linkedListDeque.toString());
        }

        @Nested
        @DisplayName("an item is added to the front")
        class AfterAddingItemToFront {

            @BeforeEach
            void addItemToFront() {
                linkedListDeque.addFirst(item);
            }

            @Test
            @DisplayName("is not empty")
            void isNotEmpty() {
                assertFalse(linkedListDeque.isEmpty());
            }

            @Test
            @DisplayName("has size one")
            void sizeIsOne() {
                assertEquals(1, linkedListDeque.size());
            }

            @Test
            @DisplayName("retrieves and removes the first item")
            void retrievesAndRemovesItemWhenFirstItemIsRemoved() {
                assertEquals(item, linkedListDeque.removeFirst());
                assertTrue(linkedListDeque.isEmpty());
            }

            @Test
            @DisplayName("retrieves and removes the last item")
            void retrievesAndRemovesItemWhenLastItemIsRemoved() {
                assertEquals(item, linkedListDeque.removeLast());
                assertTrue(linkedListDeque.isEmpty());
            }

            @Test
            @DisplayName("retrieves the item when iteratively getting it")
            void retrieveItemIteratively() {
                assertEquals(item, linkedListDeque.get(0));
            }

            @Test
            @DisplayName("retrieves the item when recursively getting it")
            void retrieveItemRecursively() {
                assertEquals(item, linkedListDeque.getRecursive(0));
            }

            @Test
            @DisplayName("retrieves null when iteratively getting negative index")
            void retrieveNullIterativelyWhenIndexIsNegative() {
                assertNull(linkedListDeque.get(-1));
            }

            @Test
            @DisplayName("retrieves null when recursively getting negative index")
            void retrieveNullRecursivelyWhenIndexIsNegative() {
                assertNull(linkedListDeque.getRecursive(-1));
            }

            @Test
            @DisplayName("is represented by the string [item]")
            void representedByEmptyString() {
                String actual = linkedListDeque.toString();
                StringJoiner joiner = new StringJoiner(", ", "[", "]");
                joiner.add(item);
                assertEquals(joiner.toString(), linkedListDeque.toString());
            }
        }

        @Nested
        @DisplayName("an item is added to the back")
        class AfterAddingItemToBack {

            @BeforeEach
            void addItemToBack() {
                linkedListDeque.addLast(item);
            }

            @Test
            @DisplayName("is not empty")
            void isNotEmpty() {
                assertFalse(linkedListDeque.isEmpty());
            }

            @Test
            @DisplayName("has size one")
            void sizeIsOne() {
                assertEquals(1, linkedListDeque.size());
            }

            @Test
            @DisplayName("retrieves and removes the first item")
            void retrievesAndRemovesItemWhenFirstItemIsRemoved() {
                assertEquals(item, linkedListDeque.removeFirst());
                assertTrue(linkedListDeque.isEmpty());
            }

            @Test
            @DisplayName("retrieves and removes the last item")
            void retrievesAndRemovesItemWhenLastItemIsRemoved() {
                assertEquals(item, linkedListDeque.removeLast());
                assertTrue(linkedListDeque.isEmpty());
            }

            @Test
            @DisplayName("retrieves the item when iteratively getting it")
            void retrieveItemIteratively() {
                assertEquals(item, linkedListDeque.get(0));
            }

            @Test
            @DisplayName("retrieves the item when recursively getting it")
            void retrieveItemRecursively() {
                assertEquals(item, linkedListDeque.getRecursive(0));
            }

            @Test
            @DisplayName("retrieves null when iteratively getting index that is equal to size")
            void retrieveNullIterativelyWhenIndexIsNegative() {
                assertNull(linkedListDeque.get(1));
            }

            @Test
            @DisplayName("retrieves null when recursively getting index that is equal to size")
            void retrieveNullRecursivelyWhenIndexIsNegative() {
                assertNull(linkedListDeque.getRecursive(1));
            }

            @Test
            @DisplayName("is represented by the string [item]")
            void representedByEmptyString() {
                String actual = linkedListDeque.toString();
                StringJoiner joiner = new StringJoiner(", ", "[", "]");
                joiner.add(item);
                assertEquals(joiner.toString(), linkedListDeque.toString());
            }
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("When added and removed items compared against java.util.LinkedList")
    class RandomizedAddRemoveTests {
        LinkedList<Integer> linkedList;
        LinkedListDeque<Integer> linkedListDeque;
        Random random;
        StringJoiner queue;
        int iterations;

        @BeforeAll
        public void initAll() {
            linkedList = new LinkedList<>();
            linkedListDeque = new LinkedListDeque<>();
            random = new Random();
            queue = new StringJoiner("\n");
            iterations = 100;
        }

        @Test
        @DisplayName("Items are added and removed randomly and the results are compared.")
        public void compareLinkedListDequeToJavaUtilLinkedList() {
            double number = random.nextDouble();
            int count = 0;
            Integer exptected = 0;
            Integer actual = 0;
            while (count < iterations) {
                if (number < 0.25)  {
                    if (!linkedList.isEmpty() && !linkedListDeque.isEmpty()) {
                        actual = linkedList.removeLast();
                        exptected = linkedListDeque.removeLast();
                        queue.add("removeLast()");
                        assertEquals(exptected, actual, queue.toString());
                    }
                } else if (0.26 <= number && number < 0.5) {
                    linkedListDeque.addFirst(count);
                    linkedList.addFirst(count);
                    queue.add(String.format("addFirst(%d)", count));
                    assertEquals(linkedListDeque.size(), linkedList.size(),queue.toString());
                } else if (0.5 <= number && number < 0.75) {
                    if ((!linkedList.isEmpty() && !linkedListDeque.isEmpty())) {
                        actual = linkedList.removeFirst();
                        exptected = linkedListDeque.removeFirst();
                        queue.add("removeFirst()");
                        assertEquals(exptected, actual, queue.toString());
                    }
                } else {
                    linkedListDeque.addLast(count);
                    linkedList.addLast(count);
                    queue.add(String.format("addLast(%d)", count));
                    assertEquals(linkedListDeque.size(), linkedList.size(),queue.toString());
                }
                number = random.nextDouble();
                if (!linkedList.isEmpty() && !linkedListDeque.isEmpty()) {
                    count++;
                }
            }
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("When retrieved items are compared against java.util.LinkedList")
    class RandomizedGetTests {
        LinkedList<Integer> linkedList;
        LinkedListDeque<Integer> linkedListDeque;
        Random random;
        StringJoiner queue;
        int iterations;

        @BeforeAll
        public void initAll() {
            linkedList = new LinkedList<>();
            linkedListDeque = new LinkedListDeque<>();
            random = new Random();
            queue = new StringJoiner("\n");
            iterations = 100;

            for (int i = 0; i < iterations; i++) {
                linkedList.addLast(i);
                linkedListDeque.addLast(i);
            }
        }

        @Test
        @Order(1)
        @DisplayName("LinkedListDeque and java.util.LinkedList are initialized")
        public void initializedCorrectly() {
            assertEquals(100, linkedList.size(), "LinkedList initialization error");
            assertEquals(100, linkedListDeque.size(), "LinkedListDeque initialization error");
        }

        @Test
        @Order(2)
        @DisplayName("Items are retrieved randomly both iteratively & recursively and the results are compared.")
        public void compareRetrievedItemsFromLinkedListDequeAndJavaUtilLinkedList() {
            Integer exptected = 0;
            Integer actualRecursive = 0;
            Integer actual = 0;
            for (int i = 0; i < iterations; i++) {
                int variate = random.nextInt(100);
                actual = linkedList.get(variate);
                exptected = linkedListDeque.get(variate);
                actualRecursive = linkedListDeque.getRecursive(variate);
                queue.add(String.format("get(%d)", variate));
                assertEquals(exptected, actual, queue.toString());

                queue.add(String.format("getRecursive(%d)", variate));
                assertEquals(exptected, actualRecursive, queue.toString());
            }
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("When items are added and removed from LinkListDeque")
    class AddRemoveTests {

        LinkedListDeque<String> linkedListDeque;
        String expect;

        @BeforeAll
        public void initALL() {
            linkedListDeque = new LinkedListDeque<>();
            expect = new String("[data, structures, is, the, new, skateboarding, true, false]");

        }

        @Test
        @Order(1)
        @DisplayName("initially has size zero")
        public void initiallySizeIsZero() {
            assertEquals(0, linkedListDeque.size());
        }

        @Test
        @Order(2)
        @DisplayName("add items in expected string and then add one")
        public void addExpectedStringItemsAndOne() {
            linkedListDeque = deque2(linkedListDeque);
            assertEquals(8, linkedListDeque.size());
            assertEquals(expect, linkedListDeque.toString());

            expect = new String("[one, data, structures, is, the, new, skateboarding, true, false]");
            linkedListDeque.addFirst("one");
            assertEquals(9, linkedListDeque.size());
            assertEquals(expect, linkedListDeque.toString());
        }

        @Test
        @Order(3)
        @DisplayName("removes items until size is zero")
        public void removeItemsUntilSizeIsZero() {
            expect = new String("[data, structures, is, the, new, skateboarding, true, false]");
            assertEquals("one", linkedListDeque.removeFirst()); // Capacity will be 16 here.
            assertEquals(8, linkedListDeque.size());
            assertEquals(expect, linkedListDeque.toString());

            expect = new String("[structures, is, the, new, skateboarding, true, false]");
            assertEquals("data", linkedListDeque.removeFirst()); // Capacity will be 16 here.
            assertEquals(7, linkedListDeque.size());
            assertEquals(expect, linkedListDeque.toString());

            expect = new String("[is, the, new, skateboarding, true, false]");
            assertEquals("structures", linkedListDeque.removeFirst()); // Capacity will be 16 here.
            assertEquals(6, linkedListDeque.size());
            assertEquals(expect, linkedListDeque.toString());

            expect = new String("[is, the, new, skateboarding, true]");
            assertEquals("false", linkedListDeque.removeLast());
            assertEquals(5, linkedListDeque.size());
            assertEquals(expect, linkedListDeque.toString());

            expect = new String("[the, new, skateboarding, true]");
            assertEquals("is", linkedListDeque.removeFirst());
            assertEquals(4, linkedListDeque.size());
            assertEquals(expect, linkedListDeque.toString());

            expect = new String("[new, skateboarding, true]");
            assertEquals("the", linkedListDeque.removeFirst());
            assertEquals(3, linkedListDeque.size());
            assertEquals(expect, linkedListDeque.toString());

            expect = new String("[skateboarding, true]");
            assertEquals("new", linkedListDeque.removeFirst());
            assertEquals(2, linkedListDeque.size());
            assertEquals(expect, linkedListDeque.toString());

            expect = new String("[skateboarding]");
            assertEquals("true", linkedListDeque.removeLast());
            assertEquals(1, linkedListDeque.size());
            assertEquals(expect, linkedListDeque.toString());

            expect = new String("[]");
            assertEquals("skateboarding", linkedListDeque.removeFirst());
            assertEquals(0, linkedListDeque.size());
            assertEquals(expect, linkedListDeque.toString());

            expect = new String("[]");
            assertEquals(null, linkedListDeque.removeFirst());
            assertEquals(0, linkedListDeque.size());
            assertEquals(expect, linkedListDeque.toString());
        }

        @Test
        @Order(4)
        @DisplayName("add items after all the items have been removed")
        public void addItemsAfterAllItemsRemoved() {
            expect = new String("[two]");
            linkedListDeque.addFirst("two");
            assertEquals(1, linkedListDeque.size());
            assertEquals(expect, linkedListDeque.toString());

            expect = new String("[two, three]");
            linkedListDeque.addLast("three");
            assertEquals(2, linkedListDeque.size());
            assertEquals(expect, linkedListDeque.toString());

            expect = new String("[two, three, four]");
            linkedListDeque.addLast("four");
            assertEquals(3, linkedListDeque.size());
            assertEquals(expect, linkedListDeque.toString());

            expect = new String("[one, two, three, four]");
            linkedListDeque.addFirst("one");
            assertEquals(4, linkedListDeque.size());
            assertEquals(expect, linkedListDeque.toString());
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("when created from other LinkedListDeque")
    class WhenNewCreatedFromOther {
        LinkedListDeque<String> other;
        LinkedListDeque<String> linkedListDeque;


        @Test
        @Order(1)
        @DisplayName("throws NullPointerException when other is null")
        void throwsExceptionWhenConstructedWithNull() {
            other = null;
            Exception exception = assertThrows(NullPointerException.class, () ->
                    new LinkedListDeque(other));
            assertEquals("other cannot be null.", exception.getMessage());
        }

        @Test
        @Order(2)
        @DisplayName("creates a LinkedListDeque")
        void createsLinkedListDequeFromOtherLinkedListDeque() {
            other = deque2(new LinkedListDeque<>());
            linkedListDeque = new LinkedListDeque<>(other);
            assertEquals(other.size(), linkedListDeque.size());
            assertEquals(other.toString(), linkedListDeque.toString());
        }

        @Test
        @Order(3)
        @DisplayName("show created LinkedListDeque and other are different by removing item from other")
        void removeItemFromOther() {
            other.removeFirst();
            assertEquals(other.size(), linkedListDeque.size() - 1);
            assertNotEquals(other.toString(), linkedListDeque.toString());
        }
    }

    /** Creates a src.Deque and adds items to it.
     *
     * @param deque an implementation of src.Deque.
     * @return a src.Deque is items added to it.
     */
    private static LinkedListDeque<String> deque2(LinkedListDeque<String> deque) {
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
}

//    private static void addRemoveTest(Deque<String> deque1, Deque<String> deque2, Deque<String> deque3, Deque<String> deque4) {
//        System.out.println("***** Running addRemoveTest. *****");
//
//        String expect = new String("one data structures is the new skateboarding true false");
//        boolean passed = TestUtility.checkEmpty(true, deque1.isEmpty());
//        Deque<String> deque = TestUtility.deque2(new LinkedListDeque<>());
//
//        assertEquals(8, deque.size());
//        deque.addFirst("one");              // Capacity will be 16 here.
//        assertEquals(9, deque.size());
//        assertEquals(expect, deque.toString());
//
//
//        expect = new String("data structures is the new skateboarding true false");
//        assertEquals("one", deque.removeFirst()); // Capacity will be 16 here.
//        assertEquals(8, deque.size());
//        assertEquals(expect, deque.toString());
//
//        expect = new String("structures is the new skateboarding true false");
//        assertEquals("data", deque.removeFirst()); // Capacity will be 16 here.
//        assertEquals(7, deque.size());
//        assertEquals(expect, deque.toString());
//
//        expect = new String("is the new skateboarding true false");
//        assertEquals("structures", deque.removeFirst()); // Capacity will be 16 here.
//        assertEquals(6, deque.size());
//        assertEquals(expect, deque.toString());
//
//        expect = new String("is the new skateboarding true");
//        assertEquals("false", deque.removeLast());
//        assertEquals(5, deque.size()); // Capacity will be 16 here.
//        assertEquals(expect, deque.toString());
//
//        expect = new String("the new skateboarding true");
//        assertEquals("is", deque.removeFirst());
//        assertEquals(4, deque.size()); // Capacity will be 16 here.
//        assertEquals(expect, deque.toString());
//
//        expect = new String("new skateboarding true");
//        assertEquals("the", deque.removeFirst());
//        assertEquals(3, deque.size()); // Capacity will be 8 here.
//        assertEquals(expect, deque.toString());
//
//        expect = new String("skateboarding true");
//        assertEquals("new", deque.removeFirst());
//        assertEquals(2, deque.size()); // Capacity will be 8 here.
//        assertEquals(expect, deque.toString());
//
//        expect = new String("skateboarding");
//        assertEquals("true", deque.removeLast());
//        assertEquals(1, deque.size()); // Capacity will be 4 here.
//        assertEquals(expect, deque.toString());
//
//        expect = new String("skateboarding");
//        assertEquals("skateboarding", deque.removeFirst());
//        assertEquals(0, deque.size()); // Capacity will be 4 here.
//        assertEquals(expect, deque.toString());
//
//        expect = new String("");
//        assertEquals(null, deque.removeFirst());
//        assertEquals(0, deque.size()); // Capacity will be 2 here.
//        assertEquals(expect, deque.toString());
//
//        expect = new String("two");
//        deque.addFirst("two");
//        assertEquals(1, deque.size()); // Capacity will be 2 here.
//        assertEquals(expect, deque.toString());
//
//        expect = new String("three");
//        deque.addLast("three");
//        assertEquals(2, deque.size()); // Capacity will be 2 here.
//        assertEquals(expect, deque.toString());
//
//        expect = new String("four");
//        deque.addLast("four");
//        assertEquals(3, deque.size()); // Capacity will be 4 here.
//        assertEquals(expect, deque.toString());
//
//        expect = new String("one");
//        deque.addFirst("one");
//        assertEquals(4, deque.size()); // Capacity will be 8 here.
//        assertEquals(expect, deque.toString());
//    }
