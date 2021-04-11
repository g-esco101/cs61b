import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A String-like class that allows users to add and remove characters in the String
 * in constant time and have a constant-time hash function. Used for the Rabin-Karp
 * string-matching algorithm.
 */
class RollingString{

    private Queue<Character> rolledStr;
//    private final char[] rolledStr;

    private long hashValue;

    /**
     * Number of total possible int values a character can take on.
     * DO NOT CHANGE THIS.
     */
    static final int UNIQUECHARS = 128;

    /**
     * The prime base that we are using as our mod space. Happens to be 61B. :)
     * DO NOT CHANGE THIS.
     */
    static final int PRIMEBASE = 6113;

    /**
     * Initializes a RollingString with a current value of String s.
     * s must be the same length as the maximum length.
     */
    public RollingString(String s, int length) {
        assert(s.length() == length);
        char[] sChars = s.toCharArray();
        rolledStr = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            rolledStr.add(sChars[i]);
        }
        this.hashValue = hashValue();
    }

    private int hashValue() {
        int hashValue = 0;
        char ch;
        for(int i = 0; i < length(); i++) {
            ch = rolledStr.poll();
            hashValue += ch * Math.pow(UNIQUECHARS, length() - i -1);
            rolledStr.offer(ch);
        }
        return hashValue;
    }

    /**
     * Adds a character to the back of the stored "string" and 
     * removes the first character of the "string". 
     * Should be a constant-time operation.
     */
    public void addChar(char c) {
        /* FIX ME */
        char front = rolledStr.poll();
        double hashDifference = front * Math.pow(UNIQUECHARS, length() -1);
        int hashAugmend = c * UNIQUECHARS;
        hashValue = Math.round(hashValue + hashAugmend - hashDifference);
        rolledStr.offer(c);
    }

    /**
     * Returns the "string" stored in this RollingString, i.e. materializes
     * the String. Should take linear time in the number of characters in
     * the string.
     */
    public String toString() {
        /* FIX ME */
        StringBuilder sb = new StringBuilder();
        rolledStr.forEach(sb::append);
        return sb.toString();
    }

    /**
     * Returns the fixed length of the stored "string".
     * Should be a constant-time operation.
     */
    public int length() {
        /* FIX ME */
        return rolledStr.size();
    }


    /**
     * Checks if two RollingStrings are equal.
     * Two RollingStrings are equal if they have the same characters in the same
     * order, i.e. their materialized strings are the same.
     */
    @Override
    public boolean equals(Object o) {
        /* FIX ME */
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        RollingString obj = (RollingString) o;
        return length() == ((RollingString) o).length() && toString().equals(o.toString());
    }

    /**
     * Returns the hashcode of the stored "string".
     * Should take constant time.
     */
    @Override
    public int hashCode() {
        /* FIX ME */
        return Math.floorMod(hashValue, PRIMEBASE);
    }
}
