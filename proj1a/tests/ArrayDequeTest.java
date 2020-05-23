package tests;

import old.TestUtility;
import org.junit.jupiter.api.Test;
import src.Deque;
import src.LinkedListDeque;

import static org.junit.jupiter.api.Assertions.*;

class ArrayDequeTest {

//    @Test
//    void isEmpty() {
//    }
//
//    @Test
//    void addFirst() {
//    }
//
//    @Test
//    void addLast() {
//    }
//
//    @Test
//    void removeFirst() {
//    }
//
//    @Test
//    void removeLast() {
//    }
//
//    @Test
//    void get() {
//    }
//
//    @Test
//    void size() {
//    }
//
//    @Test
//    void printDeque() {
//    }
//
//    @Test
//    void testToString() {
//    }
//
//    @Test
//    void testEquals() {
//    }
//
//    @Test
//    void testHashCode() {
//    }


    @Test
    void addRemoveTest() {
        String expect = new String("one data structures is the new skateboarding true false");
        Deque<String> deque = TestUtility.deque2(new LinkedListDeque<>());

        assertEquals(8, deque.size());
        deque.addFirst("one");              // Capacity will be 16 here.
        assertEquals(9, deque.size());
        assertEquals(expect, deque.toString());


        expect = new String("data structures is the new skateboarding true false");
        assertEquals("one", deque.removeFirst()); // Capacity will be 16 here.
        assertEquals(8, deque.size());
        assertEquals(expect, deque.toString());

        expect = new String("structures is the new skateboarding true false");
        assertEquals("data", deque.removeFirst()); // Capacity will be 16 here.
        assertEquals(7, deque.size());
        assertEquals(expect, deque.toString());

        expect = new String("is the new skateboarding true false");
        assertEquals("structures", deque.removeFirst()); // Capacity will be 16 here.
        assertEquals(6, deque.size());
        assertEquals(expect, deque.toString());

        expect = new String("is the new skateboarding true");
        assertEquals("false", deque.removeLast());
        assertEquals(5, deque.size()); // Capacity will be 16 here.
        assertEquals(expect, deque.toString());

        expect = new String("the new skateboarding true");
        assertEquals("is", deque.removeFirst());
        assertEquals(4, deque.size()); // Capacity will be 16 here.
        assertEquals(expect, deque.toString());

        expect = new String("new skateboarding true");
        assertEquals("the", deque.removeFirst());
        assertEquals(3, deque.size()); // Capacity will be 8 here.
        assertEquals(expect, deque.toString());

        expect = new String("skateboarding true");
        assertEquals("new", deque.removeFirst());
        assertEquals(2, deque.size()); // Capacity will be 8 here.
        assertEquals(expect, deque.toString());

        expect = new String("skateboarding");
        assertEquals("true", deque.removeLast());
        assertEquals(1, deque.size()); // Capacity will be 4 here.
        assertEquals(expect, deque.toString());

        expect = new String("skateboarding");
        assertEquals("skateboarding", deque.removeFirst());
        assertEquals(0, deque.size()); // Capacity will be 4 here.
        assertEquals(expect, deque.toString());

        expect = new String("");
        assertEquals(null, deque.removeFirst());
        assertEquals(0, deque.size()); // Capacity will be 2 here.
        assertEquals(expect, deque.toString());

        expect = new String("two");
        deque.addFirst("two");
        assertEquals(1, deque.size()); // Capacity will be 2 here.
        assertEquals(expect, deque.toString());

        expect = new String("three");
        deque.addLast("three");
        assertEquals(2, deque.size()); // Capacity will be 2 here.
        assertEquals(expect, deque.toString());

        expect = new String("four");
        deque.addLast("four");
        assertEquals(3, deque.size()); // Capacity will be 4 here.
        assertEquals(expect, deque.toString());

        expect = new String("one");
        deque.addFirst("one");
        assertEquals(4, deque.size()); // Capacity will be 8 here.
        assertEquals(expect, deque.toString());
    }
}
