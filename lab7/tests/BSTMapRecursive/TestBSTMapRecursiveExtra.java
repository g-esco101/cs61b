package tests.BSTMapRecursive;

import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.BSTMap;
import src.BSTMapRecursive;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/** Tests of optional parts of lab 8. */
@DisplayName("Given a BSTMapRecursive - given extra sanity checks")
public class TestBSTMapRecursiveExtra {

    /*
     * Sanity test for keySet, only here because it's optional
     */
    @Test
    public void sanityKeySetTest() {
        BSTMapRecursive<String, Integer> b = new BSTMapRecursive<String, Integer>();
        HashSet<String> values = new HashSet<String>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
            values.add("hi" + i);
        }
        Assertions.assertEquals(455, b.size()); //keys are there
        Set<String> keySet = b.keySet();
        assertTrue(values.containsAll(keySet));
        assertTrue(keySet.containsAll(values));
    }

    /* Remove Test
     *
     * Note for testRemoveRoot:
     *
     * Just checking that c is gone (perhaps incorrectly)
     * assumes that remove is BST-structure preserving.
     *
     * More exhaustive tests could be done to verify
     * implementation of remove, but that would require doing
     * things like checking for inorder vs. preorder swaps,
     * and is unnecessary in this simple BST implementation.
     */
    @Test
    public void testRemoveRoot() {
        BSTMapRecursive<String,String> q = new BSTMapRecursive<String,String>();
        String expected;
        q.put("c","a");
        q.put("b","a");
        q.put("a","a");
        q.put("d","a");
        q.put("e","a"); // a b c d e
        expected = q.remove("c");
        assertTrue(null != expected);
        assertEquals("a", expected);
        assertEquals(4, q.size());
        Assertions.assertFalse(q.containsKey("c"));
        Assertions.assertTrue(q.containsKey("a"));
        Assertions.assertTrue(q.containsKey("b"));
        Assertions.assertTrue(q.containsKey("d"));
        Assertions.assertTrue(q.containsKey("e"));
    }

    /* Remove Test 2
     * test the 3 different cases of remove
     */
    @Test
    public void testRemoveThreeCases() {
        BSTMapRecursive<String,String> q = new BSTMapRecursive<String,String>();
        q.put("c","a");
        q.put("b","a");
        q.put("a","a");
        q.put("d","a");
        q.put("e","a");                         // a b c d e
        assertTrue(null != q.remove("e"));      // a b c d
        Assertions.assertTrue(q.containsKey("a"));
        Assertions.assertTrue(q.containsKey("b"));
        Assertions.assertTrue(q.containsKey("c"));
        Assertions.assertTrue(q.containsKey("d"));
        assertTrue(null != q.remove("c"));      // a b d
        Assertions.assertTrue(q.containsKey("a"));
        Assertions.assertTrue(q.containsKey("b"));
        Assertions.assertTrue(q.containsKey("d"));
        q.put("f","a");                         // a b d f
        assertTrue(null != q.remove("d"));      // a b f
        Assertions.assertTrue(q.containsKey("a"));
        Assertions.assertTrue(q.containsKey("b"));
        Assertions.assertTrue(q.containsKey("f"));
    }

    /* Remove Test 3
     *  Checks that remove works correctly on root nodes
     *  when the node has only 1 or 0 children on either side. */
    @Test
    public void testRemoveRootEdge() {
        int expected;
        int actual;
        BSTMapRecursive rightChild = new BSTMapRecursive();
        rightChild.put('A', 1);
        rightChild.put('B', 2);
        expected = (int) rightChild.remove('A');
        actual = 1;
        assertEquals(expected, actual);
        assertEquals(1, rightChild.size());
        for (int i = 0; i < 10; i++) {
            rightChild.put((char) ('C'+i), 3+i);
        }
        rightChild.put('A', 100);
        assertTrue((int) rightChild.remove('D') == 4);
        assertTrue((int) rightChild.remove('G') == 7);
        assertTrue((int) rightChild.remove('A') == 100);


        assertTrue(rightChild.size()==9);

        BSTMapRecursive leftChild = new BSTMapRecursive();
        leftChild.put('B', 1);
        leftChild.put('A', 2);
        assertTrue(((Integer) leftChild.remove('B')).equals(1));
        Assertions.assertEquals(1, leftChild.size());
        Assertions.assertEquals(null, leftChild.get('B'));

        BSTMapRecursive noChild = new BSTMapRecursive();
        noChild.put('Z', 15);
        assertTrue(((Integer) noChild.remove('Z')).equals(15));
        Assertions.assertEquals(0, noChild.size());
        Assertions.assertEquals(null, noChild.get('Z'));
    }

    @Test
    public void removeNonExistentKey1() {
        Integer expected = 64;
        Integer actual;
        BSTMapRecursive<Integer, Integer> node = new BSTMapRecursive();
        node.put(93, 93);
        node.put(8, 8);
        node.remove(82);
        node.remove(75);
        node.remove(5);
        node.put(64, 64);
        node.remove(36);
        node.put(75, 75);
        node.put(48, 48);
        node.remove(36);
        node.remove(4);
        node.put(21, 21);
        actual = node.remove(64);
        assertEquals(expected, actual);
        assertEquals(5, node.size());
    }

    @Test
    public void removeRoot() {
        Integer expected = 22;
        Integer actual;
        BSTMapRecursive<Integer, Integer> node = new BSTMapRecursive();
        node.put(22, 22);
        actual = node.remove(22);
        assertEquals(expected, actual);
        assertEquals(0, node.size());
    }

    @Test
    public void removeNodes() {
        Integer expected = 28;
        Integer actual;
        BSTMapRecursive<Integer, Integer> node = new BSTMapRecursive();
        node.put(86, 86);
        node.remove(70);
        node.put(0, 0);
        node.put(11, 11);
        node.put(28, 28);
        node.remove(22);
        node.put(48, 48);
        node.remove(18);
        node.put(70, 70);
        node.remove(92);
        actual = node.remove(28);
        assertEquals(expected, actual);
        assertEquals(5, node.size());

    }

    @Test
    public void removeNodes1() {
        Integer expected = 66;
        Integer actual;
        BSTMapRecursive<Integer, Integer> node = new BSTMapRecursive();
        node.put(13, 13);
        node.put(32, 32);
        node.put(18, 18);
        node.put(65, 65);
        node.put(66, 66);
        node.put(47, 47);
        node.put(21, 21);
        node.put(49, 49);
        actual = node.remove(13);
        assertEquals(13, actual);
        assertEquals(7, node.size());
        node.put(73, 73);
        actual = node.remove(66);
        assertEquals(expected, actual);
        assertEquals(7, node.size());

    }

    @Test
    public void iterator() {
//        put(77, 77)
//        iterator()
//                ==> expected: <true> but was: <false>
        Integer expected = 66;
        Integer actual;
        BSTMapRecursive<Integer, Integer> node = new BSTMapRecursive();
        node.put(77, 77);
        Iterator<Integer> iter = node.iterator();
        assertTrue(iter.hasNext());
        assertEquals(77, iter.next());
        assertFalse(iter.hasNext());
    }
    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(tests.BSTMapRecursive.TestBSTMapRecursiveExtra.class);
    }
}