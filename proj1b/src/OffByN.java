package src;

/** A class used to determine if the difference between two chars is N */
public class OffByN implements  CharacterComparator{

    /** An int that is equal to the difference between two chars for them to be considered equal */
    private final int N;

    /** Creats an src.OffByN object.
     *
     * @param N is the difference between two chars that determines if they are equal.
     */
    public OffByN(int N) {
        this.N = N;
    }

    /**
     * Returns true if the difference in the chars is equal to one. Otherwise returns false.
     *
     * @param x the first char object to be compared.
     * @param y the second object to be compared.
     * @return true if the difference in the chars is equal to one. Otherwise returns false.
     */
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == N;
    }
}
