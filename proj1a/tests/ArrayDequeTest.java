package tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.StringJoiner;

import src.ArrayDeque;

@DisplayName("An ArrayDeque")
class ArrayDequeTest {

    ArrayDeque<Object> arrayDeque;

    private final String item = "skateboarding";

    @Nested
    @DisplayName("when new")
    class WhenNew {

        @BeforeEach
        void createNewLArrayDeque() {
            arrayDeque = new ArrayDeque<>();
        }

        @Test
        @DisplayName("is empty")
        void isEmpty() {
            assertTrue(arrayDeque.isEmpty());
        }

        @Test
        @DisplayName("has size zero")
        void sizeIsZero() {
            assertEquals(0, arrayDeque.size());
        }

        @Test
        @DisplayName("has null in the front")
        void retrievesNullWhenFirstItemIsRemoved() {
            assertNull(arrayDeque.removeFirst());
        }

        @Test
        @DisplayName("has null in the back")
        void retrievesNullWhenLastItemIsRemoved() {
            assertNull(arrayDeque.removeLast());
        }

        @Test
        @DisplayName("retrieves null when getting an item")
        void retrievesNullWhenGettingItem() {
            assertNull(arrayDeque.get(0));
        }

        @Test
        @DisplayName("is represented by the string []")
        void representedByEmptyString() {
            assertEquals("[]", arrayDeque.toString());
        }

        @Nested
        @DisplayName("an item is added to the front")
        class AfterAddingItemToFront {

            @BeforeEach
            void addItemToFront() {
                arrayDeque.addFirst(item);
            }

            @Test
            @DisplayName("is not empty")
            void isNotEmpty() {
                assertFalse(arrayDeque.isEmpty());
            }

            @Test
            @DisplayName("has size one")
            void sizeIsOne() {
                assertEquals(1, arrayDeque.size());
            }

            @Test
            @DisplayName("retrieves and removes the first item")
            void retrievesAndRemovesItemWhenFirstItemIsRemoved() {
                assertEquals(item, arrayDeque.removeFirst());
                assertTrue(arrayDeque.isEmpty());
            }

            @Test
            @DisplayName("retrieves and removes the last item")
            void retrievesAndRemovesItemWhenLastItemIsRemoved() {
                assertEquals(item, arrayDeque.removeLast());
                assertTrue(arrayDeque.isEmpty());
            }

            @Test
            @DisplayName("retrieves the item when getting it")
            void retrieveItemIteratively() {
                assertEquals(item, arrayDeque.get(0));
            }

            @Test
            @DisplayName("retrieves null when getting negative index")
            void retrieveNullIterativelyWhenIndexIsNegative() {
                assertNull(arrayDeque.get(-1));
            }

            @Test
            @DisplayName("is represented by the string [item]")
            void representedByEmptyString() {
                String actual = arrayDeque.toString();
                StringJoiner joiner = new StringJoiner(", ", "[", "]");
                joiner.add(item);
                assertEquals(joiner.toString(), arrayDeque.toString());
            }
        }

        @Nested
        @DisplayName("an item is added to the back")
        class AfterAddingItemToBack {

            @BeforeEach
            void addItemToBack() {
                arrayDeque.addLast(item);
            }

            @Test
            @DisplayName("is not empty")
            void isNotEmpty() {
                assertFalse(arrayDeque.isEmpty());
            }

            @Test
            @DisplayName("has size one")
            void sizeIsOne() {
                assertEquals(1, arrayDeque.size());
            }

            @Test
            @DisplayName("retrieves and removes the first item")
            void retrievesAndRemovesItemWhenFirstItemIsRemoved() {
                assertEquals(item, arrayDeque.removeFirst());
                assertTrue(arrayDeque.isEmpty());
            }

            @Test
            @DisplayName("retrieves and removes the last item")
            void retrievesAndRemovesItemWhenLastItemIsRemoved() {
                assertEquals(item, arrayDeque.removeLast());
                assertTrue(arrayDeque.isEmpty());
            }

            @Test
            @DisplayName("retrieves the item when getting it")
            void retrieveItemIteratively() {
                assertEquals(item, arrayDeque.get(0));
            }

            @Test
            @DisplayName("retrieves null when getting index that is equal to size")
            void retrieveNullIterativelyWhenIndexIsNegative() {
                assertNull(arrayDeque.get(1));
            }

            @Test
            @DisplayName("is represented by the string [item]")
            void representedByEmptyString() {
                String actual = arrayDeque.toString();
                StringJoiner joiner = new StringJoiner(", ", "[", "]");
                joiner.add(item);
                assertEquals(joiner.toString(), arrayDeque.toString());
            }
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("When added and removed items compared against java.util.ArrayDeque")
    class RandomizedAddRemoveTests {
        java.util.ArrayDeque<Integer> javaArrayDeque;
        ArrayDeque<Integer> arrayDeque;
        Random random;
        StringJoiner queue;
        int iterations;

        @BeforeAll
        public void initAll() {
            javaArrayDeque = new java.util.ArrayDeque<>();
            arrayDeque = new ArrayDeque<>();
            random = new Random();
            queue = new StringJoiner("\n");
            iterations = 100;
        }

        @Test
        @DisplayName("Items are added and removed randomly and the results are compared.")
        public void compareArrayDequeToJavaUtilArrayDeque() {
            double variate = random.nextDouble();
            int count = 0;
            Integer exptected = 0;
            Integer actual = 0;
            int testCapacity = arrayDeque.getCapacity();
            int testSize = arrayDeque.size();
            while (count < iterations) {
                testCapacity = arrayDeque.getCapacity();
                testSize = arrayDeque.size();
                if (variate < 0.25)  {
                    if (!javaArrayDeque.isEmpty() && !arrayDeque.isEmpty()) {
                        actual = javaArrayDeque.removeLast();
                        exptected = arrayDeque.removeLast();
                        queue.add("removeLast()");
                        assertEquals(exptected, actual, queue.toString());
                    }
                } else if (0.26 <= variate && variate < 0.5) {
                    arrayDeque.addFirst(count);
                    javaArrayDeque.addFirst(count);
                    queue.add(String.format("addFirst(%d)", count));
                    assertEquals(arrayDeque.size(), javaArrayDeque.size(),queue.toString());
                } else if (0.5 <= variate && variate < 0.75) {
                    if ((!javaArrayDeque.isEmpty() && !arrayDeque.isEmpty())) {
                        actual = javaArrayDeque.removeFirst();
                        exptected = arrayDeque.removeFirst();
                        queue.add("removeFirst()");
                        assertEquals(exptected, actual, queue.toString());
                    }
                } else {
                    arrayDeque.addLast(count);
                    javaArrayDeque.addLast(count);
                    queue.add(String.format("addLast(%d)", count));
                    assertEquals(arrayDeque.size(), javaArrayDeque.size(),queue.toString());
                }
                variate = random.nextDouble();
                if (!javaArrayDeque.isEmpty() && !arrayDeque.isEmpty()) {
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
        java.util.LinkedList<Integer> linkedList;
        ArrayDeque<Integer> arrayDeque;
        Random random;
        StringJoiner queue;
        int iterations;

        @BeforeAll
        public void initAll() {
            linkedList = new java.util.LinkedList<>();
            arrayDeque = new ArrayDeque<>();
            random = new Random();
            queue = new StringJoiner("\n");
            iterations = 100;

            for (int i = 0; i < iterations; i++) {
                linkedList.addLast(i);
                arrayDeque.addLast(i);
            }
        }

        @Test
        @Order(1)
        @DisplayName("ArrayDeque and java.util.LinkedList are initialized")
        public void initializedCorrectly() {
            assertEquals(100, linkedList.size(), "java.util.LinkList initialization error");
            assertEquals(100, arrayDeque.size(), "ArrayDeque initialization error");
        }

        @Test
        @Order(2)
        @DisplayName("Items are retrieved randomly and the results are compared.")
        public void compareRetrievedItemsFromArrayDequeAndJavaUtilLinkedList() {
            Integer exptected = 0;
            Integer actual = 0;
            for (int i = 0; i < iterations; i++) {
                int variate = random.nextInt(100);
                actual = linkedList.get(variate);
                exptected = arrayDeque.get(variate);
                queue.add(String.format("get(%d)", variate));
                assertEquals(exptected, actual, queue.toString());
            }
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("When items are added and removed from ArrayDeque")
    class AddRemoveTests {

        ArrayDeque<String> arrayDeque;
        String expect;

        @BeforeAll
        public void initALL() {
            arrayDeque = new ArrayDeque<>();
            expect = new String("[data, structures, is, the, new, skateboarding, true, false]");

        }

        @Test
        @Order(1)
        @DisplayName("initially has size zero and capacity is eight")
        public void initiallySizeIsZero() {
            assertEquals(0, arrayDeque.size());
            assertEquals(8, arrayDeque.getCapacity());
            assertEquals(0, arrayDeque.getNextFirst());
            assertEquals(1, arrayDeque.getNextLast());

        }

        @Test
        @Order(2)
        @DisplayName("add items in expected string and then add one")
        public void addExpectedStringItemsAndOne() {
            arrayDeque = deque2(arrayDeque);
            assertEquals(8, arrayDeque.size());
            assertEquals(8, arrayDeque.getCapacity());
            assertEquals(3, arrayDeque.getNextFirst());
            assertEquals(4, arrayDeque.getNextLast());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[one, data, structures, is, the, new, skateboarding, true, false]");
            arrayDeque.addFirst("one");
            assertEquals(9, arrayDeque.size());
            assertEquals(16, arrayDeque.getCapacity());
            assertEquals(14, arrayDeque.getNextFirst());
            assertEquals(8, arrayDeque.getNextLast());
            assertEquals(expect, arrayDeque.toString());
        }

        @Test
        @Order(3)
        @DisplayName("removes items until size is zero")
        public void removeItemsUntilSizeIsZero() {
            expect = new String("[data, structures, is, the, new, skateboarding, true, false]");
            assertEquals("one", arrayDeque.removeFirst()); // Capacity will be 16 here.
            assertEquals(8, arrayDeque.size());
            assertEquals(16, arrayDeque.getCapacity());
            assertEquals(15, arrayDeque.getNextFirst());
            assertEquals(8, arrayDeque.getNextLast());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[structures, is, the, new, skateboarding, true, false]");
            assertEquals("data", arrayDeque.removeFirst()); // Capacity will be 16 here.
            assertEquals(7, arrayDeque.size());
            assertEquals(16, arrayDeque.getCapacity());
            assertEquals(0, arrayDeque.getNextFirst());
            assertEquals(8, arrayDeque.getNextLast());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[is, the, new, skateboarding, true, false]");
            assertEquals("structures", arrayDeque.removeFirst()); // Capacity will be 16 here.
            assertEquals(6, arrayDeque.size());
            assertEquals(16, arrayDeque.getCapacity());
            assertEquals(1, arrayDeque.getNextFirst());
            assertEquals(8, arrayDeque.getNextLast());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[is, the, new, skateboarding, true]");
            assertEquals("false", arrayDeque.removeLast());
            assertEquals(5, arrayDeque.size());
            assertEquals(16, arrayDeque.getCapacity());
            assertEquals(1, arrayDeque.getNextFirst());
            assertEquals(7, arrayDeque.getNextLast());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[the, new, skateboarding, true]");
            assertEquals("is", arrayDeque.removeFirst());
            assertEquals(4, arrayDeque.size());
            assertEquals(16, arrayDeque.getCapacity());
            assertEquals(2, arrayDeque.getNextFirst());
            assertEquals(7, arrayDeque.getNextLast());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[new, skateboarding, true]");
            assertEquals("the", arrayDeque.removeFirst());
            assertEquals(3, arrayDeque.size());
            assertEquals(8, arrayDeque.getCapacity());
            assertEquals(7, arrayDeque.getNextFirst());
            assertEquals(3, arrayDeque.getNextLast());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[skateboarding, true]");
            assertEquals("new", arrayDeque.removeFirst());
            assertEquals(2, arrayDeque.size());
            assertEquals(8, arrayDeque.getCapacity());
            assertEquals(0, arrayDeque.getNextFirst());
            assertEquals(3, arrayDeque.getNextLast());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[skateboarding]");
            assertEquals("true", arrayDeque.removeLast());
            assertEquals(1, arrayDeque.size());
            assertEquals(4, arrayDeque.getCapacity());
            assertEquals(3, arrayDeque.getNextFirst());
            assertEquals(1, arrayDeque.getNextLast());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[]");
            assertEquals("skateboarding", arrayDeque.removeFirst());
            assertEquals(0, arrayDeque.size());
            assertEquals(2, arrayDeque.getCapacity());
            assertEquals(0, arrayDeque.getNextFirst());
            assertEquals(1, arrayDeque.getNextLast());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[]");
            assertEquals(null, arrayDeque.removeFirst());
            assertEquals(0, arrayDeque.size());
            assertEquals(2, arrayDeque.getCapacity());
            assertEquals(0, arrayDeque.getNextFirst());
            assertEquals(1, arrayDeque.getNextLast());
            assertEquals(expect, arrayDeque.toString());
        }

        @Test
        @Order(4)
        @DisplayName("add items after all the items have been removed")
        public void addItemsAfterAllItemsRemoved() {
            expect = new String("[two]");
            arrayDeque.addFirst("two");
            assertEquals(1, arrayDeque.size());
            assertEquals(2, arrayDeque.getCapacity());
            assertEquals(1, arrayDeque.getNextFirst());
            assertEquals(1, arrayDeque.getNextLast());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[two, three]");
            arrayDeque.addLast("three");
            assertEquals(2, arrayDeque.size());
            assertEquals(2, arrayDeque.getCapacity());
            assertEquals(1, arrayDeque.getNextFirst());
            assertEquals(0, arrayDeque.getNextLast());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[two, three, four]");
            arrayDeque.addLast("four");
            assertEquals(3, arrayDeque.size());
            assertEquals(4, arrayDeque.getCapacity());
            assertEquals(3, arrayDeque.getNextFirst());
            assertEquals(3, arrayDeque.getNextLast());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[one, two, three, four]");
            arrayDeque.addFirst("one");
            assertEquals(4, arrayDeque.size());
            assertEquals(4, arrayDeque.getCapacity());
            assertEquals(2, arrayDeque.getNextFirst());
            assertEquals(3, arrayDeque.getNextLast());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[zero, one, two, three, four]");
            arrayDeque.addFirst("zero");
            assertEquals(5, arrayDeque.size());
            assertEquals(8, arrayDeque.getCapacity());
            assertEquals(6, arrayDeque.getNextFirst());
            assertEquals(4, arrayDeque.getNextLast());
            assertEquals(expect, arrayDeque.toString());
        }


        @Test
        @Order(5)
        @DisplayName("remove all added items")
        public void removeAllItems() {
            ArrayDeque<String> arrayDeque = new ArrayDeque<>();
            arrayDeque = deque2(arrayDeque);
            assertEquals(8, arrayDeque.size());
            assertEquals(8, arrayDeque.getCapacity());
            assertEquals(3, arrayDeque.getNextFirst());
            assertEquals(4, arrayDeque.getNextLast());

            arrayDeque.removeLast();
            arrayDeque.removeLast();
            arrayDeque.removeLast();
            arrayDeque.removeLast();
            arrayDeque.removeLast();
            arrayDeque.removeLast();

            assertEquals(2, arrayDeque.size());
            assertEquals(8, arrayDeque.getCapacity());
            assertEquals(3, arrayDeque.getNextFirst());
            assertEquals(6, arrayDeque.getNextLast());

            arrayDeque.removeLast();

            assertEquals(1, arrayDeque.size());
            assertEquals(4, arrayDeque.getCapacity());
            assertEquals(3, arrayDeque.getNextFirst());
            assertEquals(1, arrayDeque.getNextLast());

            arrayDeque.removeLast();

            assertEquals(0, arrayDeque.size());
            assertEquals(2, arrayDeque.getCapacity());
            assertEquals(0, arrayDeque.getNextFirst());
            assertEquals(1, arrayDeque.getNextLast());

            arrayDeque.removeLast();

            assertEquals(0, arrayDeque.size());
            assertEquals(2, arrayDeque.getCapacity());
            assertEquals(0, arrayDeque.getNextFirst());
            assertEquals(1, arrayDeque.getNextLast());
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DisplayName("when created from other ArrayDeque")
    class WhenNewCreatedFromOther {
        ArrayDeque<String> other;
        ArrayDeque<String> arrayDeque;


        @Test
        @Order(1)
        @DisplayName("throws NullPointerException when other is null")
        void throwsExceptionWhenConstructedWithNull() {
            other = null;
            Exception exception = assertThrows(NullPointerException.class, () ->
                    new ArrayDeque(other));
            assertEquals("other cannot be null.", exception.getMessage());
        }

        @Test
        @Order(2)
        @DisplayName("creates an ArrayDeque")
        void createsArrayDequeFromOtherArrayDeque() {
            other = deque2(new ArrayDeque<>());
            arrayDeque = new ArrayDeque<>(other);
            assertEquals(other.size(), arrayDeque.size());
            assertEquals(other.toString(), arrayDeque.toString());
        }

        @Test
        @Order(3)
        @DisplayName("show created ArrayDeque and other are different by removing item from other")
        void removeItemFromOther() {
            other.removeFirst();
            assertEquals(other.size(), arrayDeque.size() - 1);
            assertNotEquals(other.toString(), arrayDeque.toString());
        }

        @Test
        @Order(4)
        @DisplayName("creates an ArrayDeque from empty ArrayDeque")
        void createsArrayDequeFromEmptyArrayDeque() {
            other = new ArrayDeque<>();
            arrayDeque = new ArrayDeque<>(other);
            assertEquals(other.size(), arrayDeque.size());
            assertEquals(other.toString(), arrayDeque.toString());
        }

        @Test
        @Order(5)
        @DisplayName("show created empty ArrayDeque and empty other are different by adding item to other")
        void addItemToEmptyOther() {
            other.addFirst("first");
            assertNotEquals(other.size(), arrayDeque.size());
            assertNotEquals(other.toString(), arrayDeque.toString());
        }
    }

    /** Creates a src.ArrayDeque and adds items to it.
     *
     * @param deque an implementation of src.ArrayDeque.
     * @return a src.ArrayDeque with items added to it.
     */
    private static ArrayDeque<String> deque2(ArrayDeque<String> deque) {
        ArrayDeque<String> aDeque = deque;
        aDeque.addFirst("new");
        aDeque.addFirst("the");
        aDeque.addFirst("is");
        aDeque.addFirst("structures");
        aDeque.addFirst("data");
        aDeque.addLast("skateboarding");
        aDeque.addLast("true");
        aDeque.addLast("false");
        return aDeque;
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
