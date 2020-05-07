package src;

/** This interface defines a method for determining equality of characters. */
public interface CharacterComparator {
    /** Returns true if characters are equal by the rules of the implementing class.
     *
     * @param x the first char object to be compared.
     * @param y the second object to be compared.
     * @return true if the x and y are equal by the rules of the implementing class. Otherwise returns false.
     */
    boolean equalChars(char x, char y);
}
