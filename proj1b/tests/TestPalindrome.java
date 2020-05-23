package tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.OffByOne;
import src.Palindrome;

import src.Deque;

import static org.junit.jupiter.api.Assertions.*;

/** Tests the src.Palindrome class. */
public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        Assertions.assertTrue(palindrome.isPalindrome("RACECAR"));
        Assertions.assertTrue(palindrome.isPalindrome("racecar"));
        Assertions.assertFalse(palindrome.isPalindrome("Racecar"));
        Assertions.assertFalse(palindrome.isPalindrome("RACERCAr"));
        Assertions.assertTrue(palindrome.isPalindrome("a"));
        Assertions.assertTrue(palindrome.isPalindrome(""));
        Assertions.assertFalse(palindrome.isPalindrome("RANCOR"));
    }

    @Test
    public void testIsPalindromeCharacterComparator() {
        OffByOne obo = new OffByOne();
        Assertions.assertTrue(palindrome.isPalindrome("flake", obo));
        Assertions.assertTrue(palindrome.isPalindrome("a", obo));
        Assertions.assertTrue(palindrome.isPalindrome("", obo));
        Assertions.assertTrue(palindrome.isPalindrome("sbdecar", obo));
        Assertions.assertTrue(palindrome.isPalindrome("THUGS", obo));
        Assertions.assertTrue(palindrome.isPalindrome("REMINDS", obo));

        Assertions.assertFalse(palindrome.isPalindrome("Racecar", obo));
        Assertions.assertFalse(palindrome.isPalindrome("RACERCAr", obo));
        Assertions.assertFalse(palindrome.isPalindrome("RANCOR", obo));
    }
} 
