package src;

/** A class used to determine if the difference between two chars is one */
public class OffByOne implements CharacterComparator {

    /**
     * Returns true if the difference in the chars is equal to one. Otherwise, returns false.
     *
     * @param x the first char to be compared.
     * @param y the second char to be compared.
     * @return true if the difference in the chars is equal to one. Otherwise, returns false.
     */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == 1;
    }
}
