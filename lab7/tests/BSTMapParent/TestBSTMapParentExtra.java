package tests.BSTMapParent;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.BSTMapParent;

import java.util.HashSet;
import java.util.Set;

/** Tests of optional parts of lab 8. */
@DisplayName("Given a BSTMap - given extra sanity checks")
public class TestBSTMapParentExtra {

    /*
    * Sanity test for keySet, only here because it's optional
    */
    @Test
    public void sanityKeySetTest() {
    	BSTMapParent<String, Integer> b = new BSTMapParent<String, Integer>();
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
        BSTMapParent<String,String> q = new BSTMapParent<String,String>();
        q.put("c","a");
        q.put("b","a");
        q.put("a","a");
        q.put("d","a");
        q.put("e","a"); // a b c d e
        assertTrue(null != q.remove("c"));
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
        BSTMapParent<String,String> q = new BSTMapParent<String,String>();
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
        BSTMapParent rightChild = new BSTMapParent();
        rightChild.put('A', 1);
        rightChild.put('B', 2);
        Integer result = (Integer) rightChild.remove('A');
        assertTrue(result.equals(new Integer(1)));
        for (int i = 0; i < 10; i++) {
            rightChild.put((char) ('C'+i), 3+i);
        }
        rightChild.put('A', 100);
        assertTrue(((Integer) rightChild.remove('D')).equals(new Integer(4)));
        assertTrue(((Integer) rightChild.remove('G')).equals(new Integer(7)));
        assertTrue(((Integer) rightChild.remove('A')).equals(new Integer(100)));
        assertTrue(rightChild.size()==9);

        BSTMapParent leftChild = new BSTMapParent();
        leftChild.put('B', 1);
        leftChild.put('A', 2);
        assertTrue(((Integer) leftChild.remove('B')).equals(1));
        Assertions.assertEquals(1, leftChild.size());
        Assertions.assertEquals(null, leftChild.get('B'));

        BSTMapParent noChild = new BSTMapParent();
        noChild.put('Z', 15);
        assertTrue(((Integer) noChild.remove('Z')).equals(15));
        Assertions.assertEquals(0, noChild.size());
        Assertions.assertEquals(null, noChild.get('Z'));
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestBSTMapParentExtra.class);
    }
}
