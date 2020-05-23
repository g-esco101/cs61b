package tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.OffByOne;
import src.Palindrome;

import src.Deque;

import static org.junit.jupiter.api.Assertions.*;

/** Tests the src.Palindrome class. */
@DisplayName("Given a Palindrome class")
public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    @DisplayName("it converts a word to a deque")
    public void convertWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    @DisplayName("it checks if a word is a palindrome")
    public void wordIsPalindrome() {
        Assertions.assertTrue(palindrome.isPalindrome("RACECAR"));
        Assertions.assertTrue(palindrome.isPalindrome("racecar"));
        Assertions.assertFalse(palindrome.isPalindrome("Racecar"));
        Assertions.assertFalse(palindrome.isPalindrome("RACERCAr"));
        Assertions.assertTrue(palindrome.isPalindrome("a"));
        Assertions.assertTrue(palindrome.isPalindrome(""));
        Assertions.assertFalse(palindrome.isPalindrome("RANCOR"));
    }

    @Test
    @DisplayName("it checks if a word is a palindrome off by one")
    public void testIsPalindromeCharacterComparator() {
        OffByOne offByOne = new OffByOne();
        Assertions.assertTrue(palindrome.isPalindrome("flake", offByOne));
        Assertions.assertTrue(palindrome.isPalindrome("a", offByOne));
        Assertions.assertTrue(palindrome.isPalindrome("", offByOne));
        Assertions.assertTrue(palindrome.isPalindrome("sbdecar", offByOne));
        Assertions.assertTrue(palindrome.isPalindrome("THUGS", offByOne));
        Assertions.assertTrue(palindrome.isPalindrome("REMINDS", offByOne));

        Assertions.assertFalse(palindrome.isPalindrome("Racecar", offByOne));
        Assertions.assertFalse(palindrome.isPalindrome("RACERCAr", offByOne));
        Assertions.assertFalse(palindrome.isPalindrome("RANCOR", offByOne));
    }
} 
