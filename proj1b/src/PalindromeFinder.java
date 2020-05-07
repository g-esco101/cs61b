package src;


import edu.princeton.cs.algs4.In;

/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {

    /** Palindrome used to represent a word as a Deque and check if it is a palindrome */
    private static final Palindrome palindrome = new Palindrome();

    private static CharacterComparator cc;

    /** Determines what off by value (N) has the most palindromes.
     *
     * @param file the file contains the words.
     * @return an array that contains the off by value (N) that has the most palindromes (result[0])
     * and the number of palindromes for that N (result[1]).
     */
    private static int[] mostPalindromes(String file) {
        int count;
        int[] result = new int[] {0, 0};
        for (int i = 0; i <= 25; i++) {
            count = palindromeCount(i, file);
            if (count > result[1]) {
                result[1] = count;
                result[0] = i;
            }
        }
        return result;
    }

    /** Counts the number of palindromes in a file that are a off by the specified off by value (N).
     *
     * @param N the off by value of the palindromes.
     * @param file the file that contains the words.
     * @return the number of palindromes in the file for with an off by value of N.
     */
    private static int palindromeCount(int N, String file) {
        cc = new OffByN(N);
        int count = 0;
        // No try catch finally, because "foods.csv"is hardcoded - and it works.
        // Also, no try with resources, because In implements neither Closeable nor AutoCloseable.
        In in = new In(file);
        while (!in.isEmpty()) {
            String word = in.readString();
            if (palindrome.isPalindrome(word, cc)) {
                count++;
            }
        }
        in.close();
        return count;
    }

    /** Finds the longest palindrome in a file for any off by value (N).
     *
     * @param file that contains the words.
     * @return an array that contains the value N that has the longest palindrome (result[0])
     * and the longest palindrome for that N (result[1]).
     */
    private static Object[] longestPalindrome(String file) {
        String word = "";
        Object[] result = new Object[2];
        result[0] = Integer.valueOf(0);
        result[1] = word;
        for (int i = 0; i <= 25; i++) {
            word = longestOffByNPalindrome(i, file);
            if (word.length() > ((String) result[1]).length()) {
                result[1] = word;
                result[0] = i;
            }
        }
        return result;
    }

    /** Finds the longest palindrome in a file for the specified off by value (N).
     *
     * @param N the off by value.
     * @param file that contains the words.
     * @return an array that contains the value N that has the longest palindrome (result[0])
     * and the longest palindrome for that N (result[1]).
     */
    private static String longestOffByNPalindrome(int N, String file) {
        cc = new OffByN(N);
        String max = "";
        // No try catch finally, because "foods.csv"is hardcoded - and it works.
        // Also, no try with resources, because In implements neither Closeable nor AutoCloseable.
        In in = new In(file);
        while (!in.isEmpty()) {
            String word = in.readString();
            if (palindrome.isPalindrome(word, cc)) {
                if (word.length() > max.length()) {
                    max = word;
                }
            }
        }
        in.close();
        return max;
    }
    public static void main(String[] args) {
        String file = "../library-sp19/data/words.txt";
        int[] mostPals = mostPalindromes(file);
        Object[] longestPal = longestPalindrome(file);
        System.out.printf("OffBy%d has the most palindromes: %d.\n", mostPals[0], mostPals[1]);
        System.out.printf("OffBy%d has the longest palindrome: %s.\n", longestPal[0], longestPal[1]);
    }
} 
