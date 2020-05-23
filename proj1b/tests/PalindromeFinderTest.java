package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import src.PalindromeFinder;

@DisplayName("Given a PalindromeFinder and a file containing words")
class PalindromeFinderTest {

    PalindromeFinder palindromeFinder;
    String file;

    @BeforeEach
    public void init() {
        palindromeFinder = new PalindromeFinder();
        file = "../library-sp19/data/words.txt";
    }

    @Test
    @DisplayName("finds the value of N that has the most off by N palindromes")
    void findNWithMostPalindromes() {
        int[] mostPals = palindromeFinder.mostPalindromes(file);
        assertEquals(4, mostPals[0]);
        assertEquals(122, mostPals[1]);
    }

    @Test
    @DisplayName("finds the value of N that has the longest off by N palindrome")
    void findNWithLongestPalindrome() {
        Object[] longestPal = palindromeFinder.longestPalindrome(file);
        assertEquals(3, longestPal[0]);
        assertEquals("purveyors", longestPal[1]);
    }
}