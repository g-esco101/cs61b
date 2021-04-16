package tests.map;

import com.sun.source.tree.Tree;
import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import src.BSTMapRecursive;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Given a BSTMapRecursive")
class BSTMapRecursiveTest {

    @Nested
    @DisplayName("that is empty")
    class EmptyBSTMapRecursiveTest {

        @Test
        @DisplayName("string representation of empty string")
        public void stringRepresentationEmpty() {
            String expected = "[]";
            BSTMapRecursive<Integer, Integer> bstMap = new BSTMapRecursive<>();
            String actual = bstMap.toString();
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("is empty and has empty key set")
        public void isEmpty() {
            BSTMapRecursive<Integer, Integer> bstMap = new BSTMapRecursive<>();
            assertTrue(bstMap.isEmpty());
            assertEquals(0, bstMap.size());
            Set<Integer> set = bstMap.keySet();
            assertEquals(0, set.size());
        }

        @Test
        @DisplayName("contains no keys")
        public void containsNoKeys() {
            BSTMapRecursive<Integer, Integer> bstMap = new BSTMapRecursive<>();
            assertFalse(bstMap.containsKey(5));
            assertFalse(bstMap.containsKey(null));
        }

        @Test
        @DisplayName("gets null")
        public void getEmpty() {
            BSTMapRecursive<Integer, Integer> bstMap = new BSTMapRecursive<>();
            assertEquals(null, bstMap.get(1));
            assertEquals(0, bstMap.size());
            bstMap.put(1, 2);
            assertEquals(null, bstMap.get(null));
            assertEquals(1, bstMap.size());
        }

        @Test
        @DisplayName("has an empty iterator")
        public void emptyIterator() {
            String expected = "[]";
            BSTMapRecursive<Integer, Integer> bstMap = new BSTMapRecursive<>();
            assertTrue(orderMaintained(bstMap, expected));
            assertEquals(0, bstMap.size());
        }

        @Test
        @DisplayName("is instantiated with a key-value")
        public void instantated() {
            String expected = "[(5, 5)]";
            BSTMapRecursive<Integer, Integer> bstMap = new BSTMapRecursive<>(5, 5);
            assertTrue(orderMaintained(bstMap, expected));
            assertEquals(1, bstMap.size());
            assertFalse(bstMap.isEmpty());
        }

        @Test
        @DisplayName("does not remove any keys")
        public void nonRemoval() {
            String expected = "[]";
            BSTMapRecursive<Integer, Integer> bstMap = new BSTMapRecursive<>();
            assertEquals(null, bstMap.remove(1, 1));
            assertEquals(0, bstMap.size());
            assertTrue(orderMaintained(bstMap, expected));

            assertEquals(null, bstMap.remove(0));
            assertEquals(0, bstMap.size());
            assertTrue(orderMaintained(bstMap, expected));

            assertEquals(null, bstMap.remove(null));
            assertEquals(0, bstMap.size());
            assertTrue(orderMaintained(bstMap, expected));

            expected = "[(4, 4)]";
            bstMap.put(4, 4);
            assertEquals(null, bstMap.remove(4, 1));
            assertEquals(1, bstMap.size());
            assertTrue(orderMaintained(bstMap, expected));

            expected = "[(4, 4)]";
            assertEquals(null, bstMap.remove(5, 1));
            assertEquals(1, bstMap.size());
            assertTrue(orderMaintained(bstMap, expected));
        }

        @Test
        @DisplayName("null key is not")
        public void nullKeyAdded() {
            String expected = "[]";
            BSTMapRecursive<Integer, Integer> bstMap = new BSTMapRecursive<>(null, null);
            assertEquals(0, bstMap.size());
            assertTrue(orderMaintained(bstMap, expected));

            bstMap = new BSTMapRecursive<>(null, 1);
            assertEquals(0, bstMap.size());
            assertTrue(orderMaintained(bstMap, expected));

            bstMap = new BSTMapRecursive<>();
            bstMap.put(null, null);
            assertEquals(0, bstMap.size());
            assertTrue(orderMaintained(bstMap, expected));

            bstMap = new BSTMapRecursive<>();
            bstMap.put(null, 1);
            assertEquals(0, bstMap.size());
            assertTrue(orderMaintained(bstMap, expected));
        }

        @Test
        @DisplayName("null value is added and then removed")
        public void nullValueAddedThenRemoved() {
            String expected = "[(6, null)]";
            BSTMapRecursive<Integer, Integer> bstMap = new BSTMapRecursive<>(6, null);
            assertEquals(1, bstMap.size());
            assertTrue(orderMaintained(bstMap, expected));

            bstMap = new BSTMapRecursive<>();
            bstMap.put(6, null);
            assertEquals(1, bstMap.size());
            assertTrue(orderMaintained(bstMap, expected));
        }

        @Test
        @DisplayName("is instantiated with a key-value and then key-values are added")
        public void instantatedThenPuts() {
            String expected = "[(1, 1), (3, 3), (4, 4), (5, 5), (6, 6), (7, 7), (8, 8), (9, 9)]";
            BSTMapRecursive<Integer, Integer> bstMap = new BSTMapRecursive<>(6, 6);
            bstMap.put(5, 5);
            bstMap.put(3, 3);
            bstMap.put(1, 1);
            bstMap.put(4, 4);
            bstMap.put(8, 8);
            bstMap.put(7, 7);
            bstMap.put(9, 9);
            assertEquals(8, bstMap.size());
            assertTrue(orderMaintained(bstMap, expected));
        }
    }

    @Nested
    @DisplayName("that is complete")
    class CompleteBSTMapParentTest {
        @Test
        @DisplayName("string representation - in order")
        public void stringRepresentationFull() {
            String expected = "[(1, 1), (3, 3), (4, 4), (5, 5), (7, 7), (8, 8), (9, 9)]";
            BSTMapRecursive<Integer, Integer> bstMap = makeCompleteBSTMap();
            String actual = bstMap.toString();
            assertEquals(expected, actual);
            assertEquals(7, bstMap.size());
            assertTrue(orderMaintained(bstMap, expected));
        }

        @Test
        @DisplayName("the key set contains all the keys")
        public void keySetHasAllKeys() {
            String expected = "[(1, 1), (3, 3), (4, 4), (5, 5), (7, 7), (8, 8), (9, 9)]";
            BSTMapRecursive<Integer, Integer> bstMap = makeCompleteBSTMap();
            TreeMap<Integer, Integer> treeMap = makeCompleteTreeMap();
            Set<Integer> set = bstMap.keySet();
            Set<Integer> treeMapSet = treeMap.keySet();

            assertTrue(set.containsAll(treeMapSet));
            assertTrue(treeMapSet.containsAll(set));
        }

        @Test
        @DisplayName("does not remove a value it does not contain")
        public void nonRemovalOfNonKey() {
            String expected = "[(1, 1), (3, 3), (4, 4), (5, 5), (7, 7), (8, 8), (9, 9)]";
            BSTMapRecursive<Integer, Integer> bstMap = makeCompleteBSTMap();
            assertEquals(null, bstMap.remove(10));
            assertEquals(null, bstMap.remove(null));
            assertEquals(null, bstMap.remove(1, 3));
            assertEquals(null, bstMap.remove(null, null));
            assertEquals(null, bstMap.remove(1, null));
            assertEquals(null, bstMap.remove(null, 1));

            assertEquals(7, bstMap.size());
            assertTrue(orderMaintained(bstMap, expected));
        }

        @Test
        @DisplayName("updates a key-value pair with a null value")
        public void updateExistingKeyWithNull() {
            String expected = "[(1, 1), (3, null), (4, 4), (5, 5), (7, 7), (8, 8), (9, 9)]";
            BSTMapRecursive<Integer, Integer> bstMap = makeCompleteBSTMap();
            bstMap.put(3, null);
            assertEquals(7, bstMap.size());
            assertTrue(orderMaintained(bstMap, expected));
        }

        @Test
        @DisplayName("updates value of existing key")
        public void updateExistingKey() {
            String expected = "[(1, 1), (3, 3), (4, 4), (5, 5), (7, 7), (8, 8), (9, 9)]";
            BSTMapRecursive<Integer, Integer> bstMap = makeCompleteBSTMap();
            assertEquals(7, bstMap.size());
            assertTrue(orderMaintained(bstMap, expected));

            expected = "[(1, 1), (3, 3), (4, 4), (5, 25), (7, 7), (8, 8), (9, 9)]";
            bstMap.put(5, 25);
            assertEquals(7, bstMap.size());
            assertTrue(orderMaintained(bstMap, expected));
        }

        @Test
        @DisplayName("iterator - in order")
        public void iterator() {
            String expected = "[(1, 1), (3, 3), (4, 4), (5, 5), (7, 7), (8, 8), (9, 9)]";
            BSTMapRecursive<Integer, Integer> bstMap = makeCompleteBSTMap();
            Iterator<Integer> iter = bstMap.iterator();
            assertTrue(iter.hasNext());
            assertEquals(1, iter.next());
            assertTrue(iter.hasNext());
            assertEquals(3, iter.next());
            assertTrue(iter.hasNext());
            assertEquals(4, iter.next());
            assertTrue(iter.hasNext());
            assertEquals(5, iter.next());
            assertTrue(iter.hasNext());
            assertEquals(7, iter.next());
            assertTrue(iter.hasNext());
            assertEquals(8, iter.next());
            assertTrue(iter.hasNext());
            assertEquals(9, iter.next());
            assertFalse(iter.hasNext());

            assertEquals(7, bstMap.size());
            assertTrue(orderMaintained(bstMap, expected));
        }

        /* Remove Test 2
         * test the 3 different cases of remove
         */
        @Test
        @DisplayName("remove a leaf")
        public void removeLeaf() {
            String expectedOrder = "[(1, 1), (3, 3), (4, 4), (5, 5), (8, 8), (9, 9)]";
            BSTMapRecursive<Integer, Integer> bstMap = makeCompleteBSTMap();
            TreeMap<Integer, Integer> treeMap = makeCompleteTreeMap();
            int actual = bstMap.remove(7);
            int expected = treeMap.remove(7);
            assertEquals(expected, actual);
            assertEquals(null, bstMap.remove(7));
            assertTrue(containSameKeys(bstMap, treeMap));
            assertTrue(sameSize(bstMap, treeMap));
            assertEquals(6, bstMap.size());
            assertTrue(orderMaintained(bstMap, expectedOrder));
        }

        @Test
        @DisplayName("remove the root and then get children of former root")
        public void removeRoot() {
            BSTMapRecursive<Integer, Integer> bstMap = makeCompleteBSTMap();
            TreeMap<Integer, Integer> treeMap = makeCompleteTreeMap();
            int actual = bstMap.remove(5);
            int expected = treeMap.remove(5);
            assertEquals(expected, actual);
            assertFalse(bstMap.containsKey(5));
            assertEquals(null, bstMap.remove(5));
            assertTrue(containSameKeys(bstMap, treeMap));
            assertTrue(sameSize(bstMap, treeMap));

            actual = bstMap.get(3);
            expected = treeMap.get(3);
            assertEquals(expected, actual);
            assertTrue(containSameKeys(bstMap, treeMap));
            assertTrue(sameSize(bstMap, treeMap));

            actual = bstMap.get(8);
            expected = treeMap.get(8);
            assertEquals(expected, actual);
            assertTrue(containSameKeys(bstMap, treeMap));
            assertTrue(sameSize(bstMap, treeMap));

            String expectedOrder = "[(1, 1), (3, 3), (4, 4), (7, 7), (8, 8), (9, 9)]";
            assertEquals(6, bstMap.size());
            assertTrue(orderMaintained(bstMap, expectedOrder));
        }

        @Test
        @DisplayName("remove an inner node and then get the children of the former inner node")
        public void removeInner() {
            BSTMapRecursive<Integer, Integer> bstMap = makeCompleteBSTMap();
            TreeMap<Integer, Integer> treeMap = makeCompleteTreeMap();
            int actual = bstMap.remove(8, 8);
            int expected = treeMap.remove(8);
            assertEquals(expected, actual);
            assertEquals(null, bstMap.remove(8));
            assertTrue(containSameKeys(bstMap, treeMap));
            assertTrue(sameSize(bstMap, treeMap));

            actual = bstMap.get(7);
            expected = treeMap.get(7);
            assertEquals(expected, actual);
            assertTrue(containSameKeys(bstMap, treeMap));
            assertTrue(sameSize(bstMap, treeMap));

            actual = bstMap.get(9);
            expected = treeMap.get(9);
            assertEquals(expected, actual);
            assertTrue(containSameKeys(bstMap, treeMap));
            assertTrue(sameSize(bstMap, treeMap));

            String expectedOrder = "[(1, 1), (3, 3), (4, 4), (5, 5), (7, 7), (9, 9)]";
            assertEquals(6, bstMap.size());
            assertTrue(orderMaintained(bstMap, expectedOrder));
        }

        @Test
        @DisplayName("remove left child of inner node, remove inner node, and then get right child of former inner")
        public void removeInnerLeftInner() {
            BSTMapRecursive<Integer, Integer> bstMap = makeCompleteBSTMap();
            TreeMap<Integer, Integer> treeMap = makeCompleteTreeMap();
            int actual = bstMap.remove(1);
            int expected = treeMap.remove(1);
            assertEquals(expected, actual);
            assertEquals(null, bstMap.remove(1));
            assertTrue(containSameKeys(bstMap, treeMap));
            assertTrue(sameSize(bstMap, treeMap));

            String expectedOrder = "[(3, 3), (4, 4), (5, 5), (7, 7), (8, 8), (9, 9)]";
            assertEquals(6, bstMap.size());
            assertTrue(orderMaintained(bstMap, expectedOrder));

            actual = bstMap.remove(3);
            expected = treeMap.remove(3);
            assertEquals(expected, actual);
            assertEquals(null, bstMap.remove(3));
            assertTrue(containSameKeys(bstMap, treeMap));
            assertTrue(sameSize(bstMap, treeMap));
            expectedOrder = "[(4, 4), (5, 5), (7, 7), (8, 8), (9, 9)]";
            assertEquals(5, bstMap.size());
            assertTrue(orderMaintained(bstMap, expectedOrder));

            actual = bstMap.get(4);
            expected = treeMap.get(4);
            assertEquals(expected, actual);
            assertTrue(containSameKeys(bstMap, treeMap));
            assertTrue(sameSize(bstMap, treeMap));
        }

        @Test
        @DisplayName("remove right child of inner node, remove inner node, and then get left child of former inner")
        public void removeInnerRightInner() {
            BSTMapRecursive<Integer, Integer> bstMap = makeCompleteBSTMap();
            TreeMap<Integer, Integer> treeMap = makeCompleteTreeMap();
            int actual = bstMap.remove(9);
            int expected = treeMap.remove(9);
            assertEquals(expected, actual);
            assertEquals(null, bstMap.remove(9));
            assertTrue(containSameKeys(bstMap, treeMap));
            assertTrue(sameSize(bstMap, treeMap));
            String expectedOrder = "[(1, 1), (3, 3), (4, 4), (5, 5), (7, 7), (8, 8)]";
            assertEquals(6, bstMap.size());
            assertTrue(orderMaintained(bstMap, expectedOrder));

            actual = bstMap.remove(8);
            expected = treeMap.remove(8);
            assertEquals(expected, actual);
            assertEquals(null, bstMap.remove(8));
            assertTrue(containSameKeys(bstMap, treeMap));
            assertTrue(sameSize(bstMap, treeMap));
            expectedOrder = "[(1, 1), (3, 3), (4, 4), (5, 5), (7, 7)]";
            assertEquals(5, bstMap.size());
            assertTrue(orderMaintained(bstMap, expectedOrder));

            actual = bstMap.get(7);
            expected = treeMap.get(7);
            assertEquals(expected, actual);
            assertTrue(containSameKeys(bstMap, treeMap));
            assertTrue(sameSize(bstMap, treeMap));
        }

        @Test
        @DisplayName("gets null it does not have a key")
        public void getNonExistentKey() {
            BSTMapRecursive<Integer, Integer> bstMap = makeCompleteBSTMap();
            TreeMap<Integer, Integer> treeMap = makeCompleteTreeMap();
            Integer actual = bstMap.get(20);
            Integer expected = treeMap.get(20);
            assertEquals(expected, actual);
            assertEquals(null, actual);
            assertTrue(containSameKeys(bstMap, treeMap));
            assertTrue(sameSize(bstMap, treeMap));
            String expectedOrder = "[(1, 1), (3, 3), (4, 4), (5, 5), (7, 7), (8, 8), (9, 9)]";
            assertEquals(7, bstMap.size());
            assertTrue(orderMaintained(bstMap, expectedOrder));
        }

        @Test
        @DisplayName("clears it")
        public void clears() {
            BSTMapRecursive<Integer, Integer> bstMap = makeCompleteBSTMap();
            bstMap.clear();
            assertEquals(0, bstMap.size());
            String expectedOrder = "[]";
            assertTrue(orderMaintained(bstMap, expectedOrder));
        }
    }

    @Test
    @DisplayName("removes an inner node that is a child of the root and has two children")
    public void removeRootChildTwoChildren(){
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        BSTMapRecursive<Integer, Integer> q = new BSTMapRecursive<>();
        q.put(45, 45);
        q.put(88, 88);
        q.put(97, 97);
        q.put(54, 54);
        q.put(48, 48);
        q.put(90, 90);
        q.put(42, 42);
        q.put(105, 105);
        q.put(102, 102);
        q.put(103, 103);

        treeMap.put(45, 45);
        treeMap.put(88, 88);
        treeMap.put(97, 97);
        treeMap.put(54, 54);
        treeMap.put(48, 48);
        treeMap.put(90, 90);
        treeMap.put(42, 42);
        treeMap.put(105, 105);
        treeMap.put(102, 102);
        treeMap.put(103, 103);
        assertTrue(containSameKeys(q, treeMap));
        Integer actual = q.remove(88);
        Integer expected = treeMap.remove(88);
        assertTrue(containSameKeys(q, treeMap));
    }

    @Test
    @DisplayName("removes an inner node that has two children")
    public void removesInnerWithChildren(){
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        BSTMapRecursive<Integer, Integer> q = new BSTMapRecursive<>();

        q.put(45, 45);
        q.remove(15);
        q.put(88, 88);
        q.put(97, 97);
        q.put(54, 54);
        q.remove(26);
        q.put(48, 48);
        q.put(2, 2);
        q.put(42, 42);

        treeMap.put(45, 45);
        treeMap.remove(15);
        treeMap.put(88, 88);
        treeMap.put(97, 97);
        treeMap.put(54, 54);
        treeMap.remove(26);
        treeMap.put(48, 48);
        treeMap.put(2, 2);
        treeMap.put(42, 42);

        assertTrue(containSameKeys(q, treeMap));
        Integer actual = q.remove(54);
        Integer expected = treeMap.remove(54);
        assertTrue(containSameKeys(q, treeMap));
    }

    @Test
    public void puts2(){
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        BSTMapRecursive<Integer, Integer> q = new BSTMapRecursive<>();

        // This was made from a test case of a random test.
        treeMap.put(5, 5);
        treeMap.remove(9);
        treeMap.put(57, 57);
        treeMap.remove(37);
        treeMap.remove(53);
        treeMap.remove(40);
        treeMap.put(69, 69);
        treeMap.remove(5);
        treeMap.put(30, 30);
        treeMap.put(13, 13);
        treeMap.put(58, 58);
        treeMap.put(12, 12);
        treeMap.remove(28);
        treeMap.remove(54);
        treeMap.put(16, 16);
        treeMap.remove(43);
        treeMap.put(52, 52);
        treeMap.put(45, 45);
        treeMap.remove(91);
        treeMap.put(73, 73);
        treeMap.remove(35);
        treeMap.put(38, 38);
        treeMap.remove(33);
        treeMap.remove(11);
        treeMap.remove(90);
        treeMap.remove(65);
        treeMap.put(36, 36);
        treeMap.put(99, 99);

        q.put(5, 5);
        q.remove(9);
        q.put(57, 57);
        q.remove(37);
        q.remove(53);
        q.remove(40);
        q.put(69, 69);
        q.remove(5);
        q.put(30, 30);
        q.put(13, 13);
        q.put(58, 58);
        q.put(12, 12);
        q.remove(28);
        q.remove(54);
        q.put(16, 16);
        q.remove(43);
        q.put(52, 52);
        q.put(45, 45);
        q.remove(91);
        q.put(73, 73);
        q.remove(35);
        q.put(38, 38);
        q.remove(33);
        q.remove(11);
        q.remove(90);
        q.remove(65);
        q.put(36, 36);
        q.put(99, 99);

        assertTrue(containSameKeys(q, treeMap));
        Integer actual = q.remove(69);
        Integer expected = treeMap.remove(69);
        assertTrue(containSameKeys(q, treeMap));
    }


    private BSTMapRecursive<Integer, Integer> makeCompleteBSTMap() {
        BSTMapRecursive<Integer, Integer> q = new BSTMapRecursive<>();
        q.put(5, 5);
        q.put(3, 3);
        q.put(1, 1);
        q.put(4, 4);
        q.put(8, 8);
        q.put(7, 7);
        q.put(9, 9);
        return q;
    }

    private TreeMap<Integer, Integer> makeCompleteTreeMap() {
        TreeMap<Integer, Integer> q = new TreeMap<Integer, Integer>();
        q.put(5, 5);
        q.put(3, 3);
        q.put(1, 1);
        q.put(4, 4);
        q.put(8, 8);
        q.put(7, 7);
        q.put(9, 9);
        return q;
    }

    private boolean containSameKeys(BSTMapRecursive<Integer, Integer> bstMap, TreeMap<Integer, Integer> treeMap) {
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            if (!bstMap.containsKey(entry.getKey())) {
                return false;
            }
        }
        Iterator<Integer> iter = bstMap.iterator();
        for (Integer i = iter.next(); iter.hasNext(); i = iter.next()) {
            if (!treeMap.containsKey(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean sameSize(BSTMapRecursive<Integer, Integer> bstMap, TreeMap<Integer, Integer> treeMap) {
        return bstMap.size() == treeMap.size();
    }

    private boolean orderMaintained(BSTMapRecursive<Integer, Integer> bstMap, String inOrder) {
        return inOrder.equals(bstMap.toString());
    }
}
