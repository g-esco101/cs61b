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
    @DisplayName("converts a word to a deque")
    public void convertWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    @DisplayName("verifies that palindromes are palindromes")
    public void wordIsPalindrome() {
        Assertions.assertTrue(palindrome.isPalindrome("rACeCAr"));
        Assertions.assertTrue(palindrome.isPalindrome("racEcar"));
        Assertions.assertTrue(palindrome.isPalindrome("a"));
        Assertions.assertTrue(palindrome.isPalindrome(""));
    }
    @Test
    @DisplayName("verifies that non-palindromes are not palindromes")
    public void wordIsNotPalindrome() {
        Assertions.assertFalse(palindrome.isPalindrome("Racecar"));
        Assertions.assertFalse(palindrome.isPalindrome("RACERCAr"));
        Assertions.assertFalse(palindrome.isPalindrome("RANCOR"));
    }

    @Test
    @DisplayName("verifies that a word is a palindromes off by one")
    public void wordIsPalindromeOffByOne() {
        OffByOne offByOne = new OffByOne();

        Assertions.assertTrue(palindrome.isPalindrome("flake", offByOne));
        Assertions.assertTrue(palindrome.isPalindrome("a", offByOne));
        Assertions.assertTrue(palindrome.isPalindrome("", offByOne));
        Assertions.assertTrue(palindrome.isPalindrome("sbdecar", offByOne));
    }

    @Test
    @DisplayName("verifies that a word is not a palindromes off by one")
    public void wordIsNotPalindromeOffByOne() {
        OffByOne offByOne = new OffByOne();

        Assertions.assertFalse(palindrome.isPalindrome("Racecar", offByOne));
        Assertions.assertFalse(palindrome.isPalindrome("RANCOR", offByOne));
        Assertions.assertFalse(palindrome.isPalindrome("aa", offByOne));
        Assertions.assertFalse(palindrome.isPalindrome("aBa", offByOne));

    }
} 
