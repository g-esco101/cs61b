package src;

public class Palindrome {

    /**
     * Given a String, wordToDeque should return a src.Deque where the characters appear
     * in the same order as in the String. For example, if the word is “persiflage”,
     * then the returned src.Deque should have ‘p’ at the front, followed by ‘e’, and
     * so forth.
     */
    public Deque<Character> wordToDeque(String word) {
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
    public boolean isPalindrome(String word) {
        return isPalindrome(wordToDeque(word));
    }

    /** Checks if a Character src.Deque is a palindrome.
     *
     * @param deque to determine if it is a palindrome.
     * @return true if the Character src.Deque contains a palindrome, and false otherwise.
     */
    private boolean isPalindrome(Deque<Character> deque) {
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
    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindrome(wordToDeque(word), cc);
    }

    /** Checks if a deque is a palindrome by the rules of the src.CharacterComparator.
     *
     * @param deque to determine if it is a palindrome.
     * @param cc src.CharacterComparator used to determine if word is a palindrome.
     * @return true if deque is a palindrome by the rules of the src.CharacterComparator, and false otherwise.
     */
    private boolean isPalindrome(Deque<Character> deque, CharacterComparator cc) {
        if (deque.isEmpty() || deque.size() == 1) {
            return true;
        }
        return cc.equalChars(deque.removeLast(), deque.removeFirst()) && isPalindrome(deque, cc);
    }
}
