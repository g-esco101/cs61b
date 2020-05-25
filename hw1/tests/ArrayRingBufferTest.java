package tests;

import es.datastructur.synthesizer.ArrayRingBuffer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;


import java.util.ArrayDeque;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Given an ArrayRingBuffer")
class ArrayRingBufferTest {

    ArrayRingBuffer<Integer> arrayRingBuffer;

    @Test
    @DisplayName("items are added and removed and fillCount updates accordingly")
    void addAndRemoveItems() {
        arrayRingBuffer = new ArrayRingBuffer<>(4);
        arrayRingBuffer.enqueue(1);
        assertEquals(1, arrayRingBuffer.fillCount());
        arrayRingBuffer.enqueue(2);
        assertEquals(2, arrayRingBuffer.fillCount());

        assertEquals(1, arrayRingBuffer.dequeue());
        assertEquals(1, arrayRingBuffer.fillCount());
        assertEquals(2, arrayRingBuffer.dequeue());
        assertEquals(0, arrayRingBuffer.fillCount());

        arrayRingBuffer.enqueue(3);
        assertEquals(1, arrayRingBuffer.fillCount());
        arrayRingBuffer.enqueue(4);
        assertEquals(2, arrayRingBuffer.fillCount());
    }

    @Test
    @DisplayName("equal to other ArrayRingBuffer")
    void equalToOtherObject() {
        arrayRingBuffer = new ArrayRingBuffer<>(4);
        ArrayRingBuffer other = new ArrayRingBuffer<>(4);

        arrayRingBuffer.enqueue(1);
        arrayRingBuffer.enqueue(2);
        arrayRingBuffer.enqueue(3);
        arrayRingBuffer.enqueue(4);
        other.enqueue(1);
        other.enqueue(2);
        other.enqueue(3);
        other.enqueue(4);
        assertTrue(arrayRingBuffer.equals(other));
    }

    @Test
    @DisplayName("not equal to other ArrayRingBuffer")
    void notEqualToOtherObject() {
        arrayRingBuffer = new ArrayRingBuffer<>(4);
        ArrayDeque other1 = new ArrayDeque<>(4);

        assertFalse(arrayRingBuffer.equals(other1));


        arrayRingBuffer = new ArrayRingBuffer<>(4);
        ArrayRingBuffer other = new ArrayRingBuffer<>(4);
        arrayRingBuffer.enqueue(1);
        arrayRingBuffer.enqueue(2);
        other.enqueue(1);
        other.enqueue(4);

        assertFalse(arrayRingBuffer.equals(other));


        other.dequeue();

        assertFalse(arrayRingBuffer.equals(other));
    }

    @Nested
    @DisplayName("When at capacity")
    class WhenAtCapacity {

        @BeforeEach
        void init() {
            arrayRingBuffer = new ArrayRingBuffer(3);
            arrayRingBuffer.enqueue(1);
            arrayRingBuffer.enqueue(2);
            arrayRingBuffer.enqueue(3);
        }

        @Test
        @DisplayName("retrieve first item without removing it")
        void retrieveFirstItemWithoutRemovingIt() {
            assertEquals(1, arrayRingBuffer.peek());
        }

        @Test
        @DisplayName("capacity is correct")
        void capacity() {
            assertEquals(3, arrayRingBuffer.capacity());
        }

        @Test
        @DisplayName("throws RuntimeException when item is added")
        void throwsExceptionWhenItemAdded() {
            Exception exception = assertThrows(RuntimeException.class, () ->
                    arrayRingBuffer.enqueue(4));
            assertEquals("Ring buffer overflow", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("When empty")
    class WhenEmpty {

        @BeforeEach
        void init() {
            arrayRingBuffer = new ArrayRingBuffer(3);
        }

        @Test
        @DisplayName("throws RuntimeException when item is removed")
        void throwsExceptionWhenItemRemoved() {
            Exception exception = assertThrows(RuntimeException.class, () ->
                    arrayRingBuffer.dequeue());
            assertEquals("Ring buffer underflow", exception.getMessage());
        }

        @Test
        @DisplayName("throws RuntimeException when first item is retrieved")
        void throwsExceptionWhenFirstItemRetrieved() {
            Exception exception = assertThrows(RuntimeException.class, () ->
                    arrayRingBuffer.peek());
            assertEquals("Ring buffer underflow", exception.getMessage());
        }
    }
}
