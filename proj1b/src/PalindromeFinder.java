package src;

import java.util.List;

/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {

    public static void main(String[] args) {
//        Palindrome palindrome = new Palindrome();
        String file = "../library-sp19/data/words.txt";
        int[] mostPals = Palindrome.mostPalindromes(file);
        Object[] longestPal = Palindrome.longestPalindrome(file);
        System.out.printf("OffBy%d has the most palindromes: %d.\n", mostPals[0], mostPals[1]);
        System.out.printf("OffBy%d has the longest palindrome: %s.\n", longestPal[0], longestPal[1]);

        System.out.println();
        System.out.println("Palindromes with a minimum length of four:");
        Palindrome.palindromesWithMinimumLength(file, 4);

        int N = 22;
        List<String> list = Palindrome.palindromesOffByN(file, N);
        System.out.printf("List of offByN palindromes for specified N: %d.\n", N);
        list.forEach(System.out::println);

    }
} 
