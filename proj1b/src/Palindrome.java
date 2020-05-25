package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Palindrome {

    /**
     * Given a String, wordToDeque should return a src.Deque where the characters appear
     * in the same order as in the String. For example, if the word is “persiflage”,
     * then the returned src.Deque should have ‘p’ at the front, followed by ‘e’, and
     * so forth.
     */
    public static Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    /**
     * Checks if a word is a palindrome
     *
     * @param word to determine if it is a palindrome.
     * @return true if word is a palindrome, and false otherwise.
     */
    public static boolean isPalindrome(String word) {
        return isPalindrome(wordToDeque(word));
    }

    /** Checks if a Character src.Deque is a palindrome.
     *
     * @param deque to determine if it is a palindrome.
     * @return true if the Character src.Deque contains a palindrome, and false otherwise.
     */
    private static boolean isPalindrome(Deque<Character> deque) {
        if (deque.isEmpty() || deque.size() == 1) {
            return true;
        }
        return deque.removeLast() == deque.removeFirst() && isPalindrome(deque);
    }

    /** Checks if a word is a palindrome by the rules of the src.CharacterComparator.
     *
     * @param word to determine if it is a palindrome.
     * @param cc src.CharacterComparator used to determine if word is a palindrome.
     * @return true if word is a palindrome by the rules of the src.CharacterComparator, and false otherwise.
     */
    public static boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindrome(wordToDeque(word), cc);
    }

    /** Checks if a deque is a palindrome by the rules of the src.CharacterComparator.
     *
     * @param deque to determine if it is a palindrome.
     * @param cc src.CharacterComparator used to determine if word is a palindrome.
     * @return true if deque is a palindrome by the rules of the src.CharacterComparator, and false otherwise.
     */
    private static boolean isPalindrome(Deque<Character> deque, CharacterComparator cc) {
        if (deque.isEmpty() || deque.size() == 1) {
            return true;
        }
        return cc.equalChars(deque.removeLast(), deque.removeFirst()) && isPalindrome(deque, cc);
    }

    /** List of offByN palindromes for specified N: 5.
     *
     * @param file the file containing the words.
     * @param N the off by value of the palindromes.
     * @return a list of off by N palindromes.
     */
    public static List<String> palindromesOffByN(String file, int N) {
        try (Stream<String> stream = Files.lines(Paths.get(file))) {
            return stream.filter(word -> isPalindrome(word, new OffByN(N))).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /** Determines what off by value (N) has the most palindromes.
     *
     * @param file the file contains the words.
     * @return an array that contains the off by value (N) that has the most palindromes (result[0])
     * and the number of palindromes for that N (result[1]).
     */
    public static int[] mostPalindromes(String file) {
        int count;
        int[] result = new int[] {0, 0};
        for (int i = 0; i <= 25; i++) {
            count = palindromeCount(file, i);
            if (count > result[1]) {
                result[1] = count;
                result[0] = i;
            }
        }
        return result;
    }

    /** Counts the number of palindromes in a file that are a off by the specified off by value (N).
     *
     * @param file the file that contains the words.
     * @param N the off by value of the palindromes.
     * @return the number of palindromes in the file for with an off by value of N.
     */
    private static int palindromeCount(String file, int N) {
        try (Stream<String> stream = Files.lines(Paths.get(file))) {
            return (int) stream.filter(word -> isPalindrome(word, new OffByN(N))).count();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /** Finds the longest palindrome in a file for any off by value (N).
     *
     * @param file that contains the words.
     * @return an array that contains the value N that has the longest palindrome (result[0])
     * and the longest palindrome for that N (result[1]).
     */
    public static Object[] longestPalindrome(String file) {
        Optional<String> optionalWord;
        String word = "";
        Object[] result = new Object[2];
        result[0] = Integer.valueOf(0);
        result[1] = word;
        for (int i = 0; i <= 25; i++) {
            optionalWord = longestOffByNPalindrome(file, i);
            if (optionalWord.isPresent()) {
                word = optionalWord.get();
                if (word.length() > ((String) result[1]).length()) {
                    result[1] = word;
                    result[0] = i;
                }
            }
        }
        return result;
    }

    /** Finds the longest palindrome in a file for the specified off by value (N).
     *
     * @param file that contains the words.
     * @param N the off by value.
     * @return an array that contains the value N that has the longest palindrome (result[0])
     * and the longest palindrome for that N (result[1]).
     */
    private static Optional<String> longestOffByNPalindrome(String file, int N) {
        try (Stream<String> stream = Files.lines(Paths.get(file))) {
            return stream.filter(word -> isPalindrome(word, new OffByN(N))).max(Comparator.comparingInt(String::length));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Prints the palindromes with the specified minimum length.
     *
     * @param file that contains the words.
     * @param minLength the minimum length of the palindromes.
     */
    public static void palindromesWithMinimumLength(String file, int minLength) {
        try (Stream<String> stream = Files.lines(Paths.get(file))) {
            stream.filter(word -> word.length() >= minLength && isPalindrome(word)).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
