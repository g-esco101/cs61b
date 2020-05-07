/** Tests the Palindrome class. */
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(palindrome.isPalindrome("RACECAR"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertFalse(palindrome.isPalindrome("Racecar"));
        assertFalse(palindrome.isPalindrome("RACERCAr"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("RANCOR"));
    }

    @Test
    public void testIsPalindromeCharacterComparator() {
        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("a", obo));
        assertTrue(palindrome.isPalindrome("", obo));
        assertTrue(palindrome.isPalindrome("sbdecar", obo));
        assertTrue(palindrome.isPalindrome("THUGS", obo));
        assertTrue(palindrome.isPalindrome("REMINDS", obo));

        assertFalse(palindrome.isPalindrome("Racecar", obo));
        assertFalse(palindrome.isPalindrome("RACERCAr", obo));
        assertFalse(palindrome.isPalindrome("RANCOR", obo));
    }
} 
