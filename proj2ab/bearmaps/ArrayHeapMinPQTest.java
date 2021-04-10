package bearmaps;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;

@DisplayName("Given an ArrayHeapMinPQ of items with unique priority")
public class ArrayHeapMinPQTest {


    @Test
    @DisplayName("the size is maintained when items are added")
    void size() {
        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
        heap.add("dog", 5);
        heap.add("cat", 10);
        heap.add("bird", 1);
        assertEquals(3, heap.size());
    }

    @Test
    @DisplayName("the size is maintained when items are added")
    void contains() {
        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
        heap.add("dog", 5);
        heap.add("cat", 10);
        heap.add("bruin", 100);
        heap.add("sundevil", 100);
        heap.add("bird", 1);

        assertTrue(heap.contains("dog"));
        assertTrue(heap.contains("cat"));
        assertTrue(heap.contains("bruin"));
        assertTrue(heap.contains("sundevil"));
        assertTrue(heap.contains("bird"));
        assertFalse(heap.contains("rat"));
    }

    @Test
    @DisplayName("the element with the min priority is returned")
    void getSmallest() {

        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
        heap.add("cat", 10);
        assertEquals("cat", heap.getSmallest());

        heap.add("dog", 5);
        assertEquals("dog", heap.getSmallest());

        heap.add("bruin", 100);
        heap.add("sundevil", 100);
        assertEquals("dog", heap.getSmallest());

        heap.add("bird", 1);
        assertEquals("bird", heap.getSmallest());
    }

    @Test
    @DisplayName("the element with the min priority is removed")
    void removeSmallest() {

        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
        heap.add("cat", 10);
        heap.add("dog", 5);
        heap.add("bruin", 100);
        heap.add("sundevil", 100);
        heap.add("bird", 1);

        assertEquals(5, heap.size());
        assertEquals("bird", heap.removeSmallest());
        assertEquals(4, heap.size());
        assertEquals("dog", heap.removeSmallest());
        assertEquals(3, heap.size());
        assertEquals("cat", heap.removeSmallest());
        assertEquals(2, heap.size());
        assertEquals("bruin", heap.removeSmallest());
        assertEquals(1, heap.size());
        assertEquals("sundevil", heap.removeSmallest());
        assertEquals(0, heap.size());
    }

    @Test
    @DisplayName("can increase prioirty.")
    void increasePriorty() {

        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
        heap.add("cat", 50);
        heap.add("dog", 25);
        heap.add("bruin", 100);
        heap.add("sundevil", 100);
        heap.add("bird", 7);

        heap.changePriority("sundevil", 4);
        assertEquals("sundevil", heap.getSmallest());

        heap.changePriority("bruin", 2);
        assertEquals("bruin", heap.getSmallest());

        heap.changePriority("bird", 1);
        assertEquals("bird", heap.getSmallest());

        assertEquals("bird", heap.removeSmallest());
        assertEquals("bruin", heap.removeSmallest());
        assertEquals("sundevil", heap.removeSmallest());
        assertEquals("dog", heap.removeSmallest());
        assertEquals("cat", heap.removeSmallest());
    }

    @Test
    @DisplayName("can decrease prioirty.")
    void decreasePriorty() {

        ArrayHeapMinPQ<String> heap = new ArrayHeapMinPQ<>();
        heap.add("cat", 50);
        heap.add("dog", 25);
        heap.add("bruin", 100);
        heap.add("sundevil", 100);
        heap.add("bird", 7);

        heap.changePriority("bird", 35);
        assertEquals("dog", heap.getSmallest());

        heap.changePriority("dog", 75);
        assertEquals("bird", heap.getSmallest());

        heap.changePriority("bird", 100);
        assertEquals("cat", heap.getSmallest());

        heap.changePriority("dog", 200);
        assertEquals("cat", heap.getSmallest());

        assertEquals("cat", heap.removeSmallest());
        assertEquals("sundevil", heap.removeSmallest());
        assertEquals("bird", heap.removeSmallest());
        assertEquals("bruin", heap.removeSmallest());
        assertEquals("dog", heap.removeSmallest());
    }
}
