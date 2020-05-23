package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;



import src.ArrayDeque;
import java.util.Random;
import java.util.StringJoiner;

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
    @TestInstance(Lifecycle.PER_CLASS)
    @DisplayName("When added and removed items compared against java.util.ArrayDeque")
    class RandomizedAddRemoveTests {
        java.util.ArrayDeque<Integer> javaArrayDeque;
        ArrayDeque<Integer> arrayDeque;
        Random random;
        StringJoiner stackTrace;
        int iterations;

        @BeforeAll
        public void initAll() {
            javaArrayDeque = new java.util.ArrayDeque<>();
            arrayDeque = new ArrayDeque<>();
            random = new Random();
            stackTrace = new StringJoiner("\n");
            iterations = 100;
        }

        @Test
        @DisplayName("Items are added and removed randomly and the results are compared.")
        public void compareArrayDequeToJavaUtilArrayDeque() {
            double variate = random.nextDouble();
            int count = 0;
            Integer exptected = 0;
            Integer actual = 0;
            while (count < iterations) {
                if (variate < 0.25)  {
                    if (!javaArrayDeque.isEmpty() && !arrayDeque.isEmpty()) {
                        actual = javaArrayDeque.removeLast();
                        exptected = arrayDeque.removeLast();
                        stackTrace.add("removeLast()");
                        assertEquals(exptected, actual, stackTrace.toString());
                    }
                } else if (0.26 <= variate && variate < 0.5) {
                    arrayDeque.addFirst(count);
                    javaArrayDeque.addFirst(count);
                    stackTrace.add(String.format("addFirst(%d)", count));
                    assertEquals(arrayDeque.size(), javaArrayDeque.size(), stackTrace.toString());
                } else if (0.5 <= variate && variate < 0.75) {
                    if ((!javaArrayDeque.isEmpty() && !arrayDeque.isEmpty())) {
                        actual = javaArrayDeque.removeFirst();
                        exptected = arrayDeque.removeFirst();
                        stackTrace.add("removeFirst()");
                        assertEquals(exptected, actual, stackTrace.toString());
                    }
                } else {
                    arrayDeque.addLast(count);
                    javaArrayDeque.addLast(count);
                    stackTrace.add(String.format("addLast(%d)", count));
                    assertEquals(arrayDeque.size(), javaArrayDeque.size(), stackTrace.toString());
                }
                variate = random.nextDouble();
                if (!javaArrayDeque.isEmpty() && !arrayDeque.isEmpty()) {
                    count++;
                }
            }
        }
    }

    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    @TestMethodOrder(OrderAnnotation.class)
    @DisplayName("When retrieved items are compared against java.util.LinkedList")
    class RandomizedGetTests {
        java.util.LinkedList<Integer> linkedList;
        ArrayDeque<Integer> arrayDeque;
        Random random;
        StringJoiner stackTrace;
        int iterations;

        @BeforeAll
        public void initAll() {
            linkedList = new java.util.LinkedList<>();
            arrayDeque = new ArrayDeque<>();
            random = new Random();
            stackTrace = new StringJoiner("\n");
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
                stackTrace.add(String.format("get(%d)", variate));
                assertEquals(exptected, actual, stackTrace.toString());
            }
        }
    }

    @Nested
    @TestMethodOrder(OrderAnnotation.class)
    @TestInstance(Lifecycle.PER_CLASS)
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
        }

        @Test
        @Order(2)
        @DisplayName("add items in expected string and then add one")
        public void addExpectedStringItemsAndOne() {
            arrayDeque = deque2(arrayDeque);
            assertEquals(8, arrayDeque.size());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[one, data, structures, is, the, new, skateboarding, true, false]");
            arrayDeque.addFirst("one");
            assertEquals(9, arrayDeque.size());
            assertEquals(expect, arrayDeque.toString());
        }

        @Test
        @Order(3)
        @DisplayName("removes items until size is zero")
        public void removeItemsUntilSizeIsZero() {
            expect = new String("[data, structures, is, the, new, skateboarding, true, false]");
            assertEquals("one", arrayDeque.removeFirst()); // Capacity will be 16 here.
            assertEquals(8, arrayDeque.size());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[structures, is, the, new, skateboarding, true, false]");
            assertEquals("data", arrayDeque.removeFirst()); // Capacity will be 16 here.
            assertEquals(7, arrayDeque.size());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[is, the, new, skateboarding, true, false]");
            assertEquals("structures", arrayDeque.removeFirst()); // Capacity will be 16 here.
            assertEquals(6, arrayDeque.size());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[is, the, new, skateboarding, true]");
            assertEquals("false", arrayDeque.removeLast());
            assertEquals(5, arrayDeque.size());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[the, new, skateboarding, true]");
            assertEquals("is", arrayDeque.removeFirst());
            assertEquals(4, arrayDeque.size());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[new, skateboarding, true]");
            assertEquals("the", arrayDeque.removeFirst());
            assertEquals(3, arrayDeque.size());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[skateboarding, true]");
            assertEquals("new", arrayDeque.removeFirst());
            assertEquals(2, arrayDeque.size());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[skateboarding]");
            assertEquals("true", arrayDeque.removeLast());
            assertEquals(1, arrayDeque.size());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[]");
            assertEquals("skateboarding", arrayDeque.removeFirst());
            assertEquals(0, arrayDeque.size());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[]");
            assertEquals(null, arrayDeque.removeFirst());
            assertEquals(0, arrayDeque.size());
            assertEquals(expect, arrayDeque.toString());
        }

        @Test
        @Order(4)
        @DisplayName("add items after all the items have been removed")
        public void addItemsAfterAllItemsRemoved() {
            expect = new String("[two]");
            arrayDeque.addFirst("two");
            assertEquals(1, arrayDeque.size());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[two, three]");
            arrayDeque.addLast("three");
            assertEquals(2, arrayDeque.size());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[two, three, four]");
            arrayDeque.addLast("four");
            assertEquals(3, arrayDeque.size());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[one, two, three, four]");
            arrayDeque.addFirst("one");
            assertEquals(4, arrayDeque.size());
            assertEquals(expect, arrayDeque.toString());

            expect = new String("[zero, one, two, three, four]");
            arrayDeque.addFirst("zero");
            assertEquals(5, arrayDeque.size());
            assertEquals(expect, arrayDeque.toString());
        }
    }

    @Nested
    @TestMethodOrder(OrderAnnotation.class)
    @TestInstance(Lifecycle.PER_CLASS)
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
