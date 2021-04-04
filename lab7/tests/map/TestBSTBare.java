package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.BSTBare;

/** Tests by Brendan Hu, Spring 2015, revised for 2016 by Josh Hug */
@DisplayName("Given a BSTBare - given sanity checks")
public class TestBSTBare {

    @Test
    @DisplayName("it can handle generics")
    public void sanityGenericsTest() {
        try {
            BSTBare<String, String> a = new BSTBare<String, String>();
            BSTBare<String, Integer> b = new BSTBare<String, Integer>();
            BSTBare<Integer, String> c = new BSTBare<Integer, String>();
            BSTBare<Boolean, Integer> e = new BSTBare<Boolean, Integer>();
        } catch (Exception e) {
            fail();
        }
    }

    //assumes put/size/containsKey/get work
    @Test
    @DisplayName("it can clear")
    public void sanityClearTest() {
        BSTBare<String, Integer> b = new BSTBare<String, Integer>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1+i);
            //make sure put is working via containsKey and get
            assertTrue( null != b.get("hi" + i) && (b.get("hi"+i).equals(1+i))
                    && b.containsKey("hi" + i));
        }
        Assertions.assertEquals(455, b.size());
        b.clear();
        Assertions.assertEquals(0, b.size());
        for (int i = 0; i < 455; i++) {
            assertTrue(null == b.get("hi" + i) && !b.containsKey("hi" + i));
        }
    }

    // assumes put works
    @Test
    @DisplayName("it can check if it contains a mapping")
    public void sanityContainsKeyTest() {
        BSTBare<String, Integer> b = new BSTBare<String, Integer>();
        Assertions.assertFalse(b.containsKey("waterYouDoingHere"));
        b.put("waterYouDoingHere", 0);
        Assertions.assertTrue(b.containsKey("waterYouDoingHere"));
    }

    // assumes put works
    @Test
    @DisplayName("it can get values")
    public void sanityGetTest() {
        BSTBare<String, Integer> b = new BSTBare<String, Integer>();
        Assertions.assertEquals(null,b.get("starChild"));
        Assertions.assertEquals(0, b.size());
        b.put("starChild", 5);
        assertTrue(((Integer) b.get("starChild")).equals(5));
        b.put("KISS", 5);
        assertTrue(((Integer) b.get("KISS")).equals(5));
        Assertions.assertNotEquals(null,b.get("starChild"));
        Assertions.assertEquals(2, b.size());
    }

    // assumes put works
    @Test
    @DisplayName("it can get its size")
    public void sanitySizeTest() {
        BSTBare<String, Integer> b = new BSTBare<String, Integer>();
        Assertions.assertEquals(0, b.size());
        b.put("hi", 1);
        Assertions.assertEquals(1, b.size());
        for (int i = 0; i < 455; i++)
            b.put("hi" + i, 1);
        Assertions.assertEquals(456, b.size());
    }

    //assumes get/containskey work
    @Test
    @DisplayName("it can put key-value mappings")
    public void sanityPutTest() {
        BSTBare<String, Integer> b = new BSTBare<String, Integer>();
        b.put("hi", 1);
        assertTrue(b.containsKey("hi") && b.get("hi") != null);
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestBSTBare.class);
    }
}
