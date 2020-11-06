package tests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


import src.UnionFind;

@DisplayName("Given a set")
class UnionFindTest {

    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    @TestMethodOrder(OrderAnnotation.class)
    @DisplayName("When elements are unioned")
    class UnionedSets {

        UnionFind unionFind;

        @BeforeAll
        void initAll() {
            unionFind = new UnionFind(7);
        }

        @Test
        @Order(1)
        @DisplayName("the union-by-size property is obeyed and size is equal to the element count")
        void unionFourElements() {
            unionFind.union(0,2);

            // Verifying the parent first after each union, shows the union-by-size property is obeyed,
            // because connected can change the underlying structure of the data structure.
            assertEquals(2, unionFind.parent(0));
            assertEquals(2,unionFind.sizeOf(0));


            unionFind.union(2,6);

            assertEquals(2, unionFind.parent(6));
            assertEquals(3,unionFind.sizeOf(6));


            unionFind.union(0,3);

            assertEquals(2, unionFind.parent(3));
            assertEquals(4,unionFind.sizeOf(3));
        }

        @Test
        @Order(2)
        @DisplayName("they are connected and size equal to the element count")
        void unionThreeElements() {
            unionFind.union(1,4);

            assertTrue(unionFind.connected(1, 4));
            assertEquals(4, unionFind.parent(1));
            assertEquals(-2, unionFind.parent(4));
            assertEquals(2,unionFind.sizeOf(4));
            assertEquals(2,unionFind.sizeOf(1));



            unionFind.union(4, 5);

            assertEquals(4, unionFind.parent(1));
            assertEquals(-3, unionFind.parent(4));
            assertEquals(4, unionFind.parent(5));
            assertEquals(3,unionFind.sizeOf(1));
            assertEquals(3,unionFind.sizeOf(4));
            assertEquals(3,unionFind.sizeOf(5));

            assertTrue(unionFind.connected(1, 5));

            unionFind.union(5,6);

            assertEquals(2, unionFind.parent(4));
            assertTrue(unionFind.connected(6, 5));
            assertEquals(7,unionFind.sizeOf(5));
        }

        @Test
        @Order(3)
        @DisplayName("Unioning an element with itself does not change the sets")
        void selfUnion() {
            assertEquals(7,unionFind.sizeOf(5));
            assertEquals(4, unionFind.parent(1));

            unionFind.union(1, 1);
            assertEquals(7,unionFind.sizeOf(5));
            assertNotEquals(4, unionFind.parent(1));
            assertEquals(2, unionFind.parent(1));
        }
    }

    @Nested
    @DisplayName("When all elements are disjoint")
    class AllElementsDisjoint {
        UnionFind unionFind;

        @BeforeEach
        void init() {
            unionFind = new UnionFind(3);
        }

        @Test
        @DisplayName("size of each set is one")
        void sizeOfDisjointElementIsOne() {
            assertEquals(1, unionFind.sizeOf(0));
            assertEquals(1, unionFind.sizeOf(1));
            assertEquals(1, unionFind.sizeOf(2));
        }

        @Test
        @DisplayName("each parent is negative one")
        void parentIsNegativeOne() {
            assertEquals(-1, unionFind.parent(0));
            assertEquals(-1, unionFind.parent(1));
            assertEquals(-1, unionFind.parent(2));
        }

        @Test
        @DisplayName("no elements are connected")
        void noConnectedElements() {
            assertFalse(unionFind.connected(0, 1));
            assertFalse(unionFind.connected(0, 2));
            assertFalse(unionFind.connected(2, 1));
        }

        @Test
        @DisplayName("each element is its own root")
        void eachElementIsRoot() {
            assertEquals(0, unionFind.find(0));
            assertEquals(1, unionFind.find(1));
            assertEquals(2, unionFind.find(2));
        }
    }

    @Nested
    @DisplayName("When passed invalid arguments")
    class InvalidArugments {
        UnionFind unionFind;

        @Test
        @DisplayName("to the constructor, a set is still created")
        void constructorInvalidArguments() {
            UnionFind unionFind = new UnionFind(-5);
            assertEquals(1, unionFind.sizeOf(1));
        }

        @Test
        @DisplayName("to a method, an IllegalArgumentException is thrown")
        void methodInvalidArugments() {
            UnionFind unionFind = new UnionFind(4);
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    unionFind.sizeOf(-2));
            assertEquals("v1 is invalid", exception.getMessage());

            exception = assertThrows(IllegalArgumentException.class, () ->
                    unionFind.find(10));
            assertEquals("v1 is invalid", exception.getMessage());
        }
    }

    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    @TestMethodOrder(OrderAnnotation.class)
    @DisplayName("When all elements are connected")
    class AllElementsConnected {

        UnionFind unionFind;

        @BeforeAll
        void initAll() {
            unionFind = new UnionFind(16);
        }

        @Test
        @Order(1)
        @DisplayName("path compression occurs for union")
        void unionElements() {
            unionFind.union(12,5);
            unionFind.union(15,11);
            unionFind.union(11,5);
            unionFind.union(13,6);
            unionFind.union(7,1);
            unionFind.union(6,1);
            unionFind.union(14,8);
            unionFind.union(9,2);
            unionFind.union(8,2);
            unionFind.union(10,3);
            unionFind.union(4,0);
            unionFind.union(3,0);
            unionFind.union(2,0);
            unionFind.union(5,1);
            unionFind.union(1,0);

            assertEquals(-16, unionFind.parent(0));
            assertEquals(0,unionFind.parent(1));
            assertEquals(0,unionFind.parent(2));
            assertEquals(0,unionFind.parent(3));
            assertEquals(0,unionFind.parent(4));

            assertEquals(1,unionFind.parent(5));
            assertEquals(1,unionFind.parent(6));
            assertEquals(1,unionFind.parent(7));

            assertEquals(2,unionFind.parent(8));
            assertEquals(2,unionFind.parent(9));

            assertEquals(3,unionFind.parent(10));

            assertEquals(5,unionFind.parent(11));
            assertEquals(5,unionFind.parent(12));

            assertEquals(6,unionFind.parent(13));
            assertEquals(8,unionFind.parent(14));

            assertEquals(11,unionFind.parent(15));
        }

        @Test
        @Order(2)
        @DisplayName("path compression occurs for connecting 15 and 10")
        void elements15And10Connected() {
            unionFind.connected(15,10);

            assertEquals(-16, unionFind.parent(0));
            assertEquals(0,unionFind.parent(1));
            assertEquals(0,unionFind.parent(2));
            assertEquals(0,unionFind.parent(3));
            assertEquals(0,unionFind.parent(4));

            assertEquals(0,unionFind.parent(5));
            assertEquals(1,unionFind.parent(6));
            assertEquals(1,unionFind.parent(7));

            assertEquals(2,unionFind.parent(8));
            assertEquals(2,unionFind.parent(9));

            assertEquals(0,unionFind.parent(10));

            assertEquals(0,unionFind.parent(11));
            assertEquals(5,unionFind.parent(12));

            assertEquals(6,unionFind.parent(13));
            assertEquals(8,unionFind.parent(14));

            assertEquals(0,unionFind.parent(15));
        }

        @Test
        @Order(3)
        @DisplayName("path compression occurs for connecting 14 and 13")
        void elements14And13Connected() {
            unionFind.connected(14,13);

            assertEquals(-16, unionFind.parent(0));
            assertEquals(0,unionFind.parent(1));
            assertEquals(0,unionFind.parent(2));
            assertEquals(0,unionFind.parent(3));
            assertEquals(0,unionFind.parent(4));

            assertEquals(0,unionFind.parent(5));
            assertEquals(0,unionFind.parent(6));
            assertEquals(1,unionFind.parent(7));

            assertEquals(0,unionFind.parent(8));
            assertEquals(2,unionFind.parent(9));

            assertEquals(0,unionFind.parent(10));

            assertEquals(0,unionFind.parent(11));
            assertEquals(5,unionFind.parent(12));

            assertEquals(0,unionFind.parent(13));
            assertEquals(0,unionFind.parent(14));

            assertEquals(0,unionFind.parent(15));
        }

    }
}
