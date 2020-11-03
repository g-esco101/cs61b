package src;

/** A class used to determine if the difference between two chars is N */
public class OffByN implements  CharacterComparator{

    /** The difference between two chars for them to be considered equal */
    private final int N;

    /** Creates an OffByN object.
     *
     * @param N is the difference between two chars that determines if they are equal.
     */
    public OffByN(int N) {
        this.N = N;
    }

    /**
     * Returns true if the difference in the chars is equal to N. Otherwise, returns false.
     *
     * @param x the first char to be compared.
     * @param y the second char to be compared.
     * @return true if the difference in the chars is equal to one. Otherwise returns false.
     */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == N;
    }
}
