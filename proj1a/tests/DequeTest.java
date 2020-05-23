package tests;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import src.Deque;
import src.LinkedListDeque;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {



    @Test
    void addFirst() {
    }

    @Test
    void addLast() {
    }

    @Test
    void removeFirst() {
    }

    @Test
    void removeLast() {
    }

    @Test
    void size() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void printDeque() {
    }

    @Test
    void get() {
    }

    @Test
    void equals() {
        LinkedListDeque<String> deque = new LinkedListDeque<>();
        deque.addLast("data");
        deque.addLast("structures");
        deque.addLast("is");
        deque.addLast("the");
        deque.addLast("new");
        deque.addLast("skateboarding");
        deque.addLast("true");
        deque.addLast("false");
        LinkedListDeque<String> deque1 = new LinkedListDeque<>();
        deque1.addLast("data");
        deque1.addLast("structures");
        deque1.addLast("is");
        deque1.addLast("the");
        deque1.addLast("new");
        deque1.addLast("skateboarding");
        deque1.addLast("true");
        deque1.addLast("false");

        assertTrue(deque.equals(deque1));
    }

    @Test
    void equals1() {
        LinkedListDeque<String> deque = new LinkedListDeque<>();
        deque.addLast("data");
        deque.addLast("structures");
        deque.addLast("is");
        deque.addLast("the");
        deque.addLast("new");
        deque.addLast("skateboarding");
        deque.addLast("true");
        deque.addLast("false");
        LinkedListDeque<String> deque1 = new LinkedListDeque<>();
        deque1.addLast("structures");
        deque1.addLast("is");
        deque1.addLast("the");
        deque1.addLast("new");
        deque1.addLast("skateboarding");
        deque1.addLast("true");
        deque1.addLast("false");

        assertFalse(deque.equals(deque1));
    }
}
