/**
 *  Tests hashCode
 */
public class TestHashCode {

    /**
     *  Tests hashCode.
     */
    public static void main(String[] args) {
        checkHashCode();
    }

    /**
     *  Checks whether or not two Doubles are equal and prints the result.
     *
     *  @param  hash1    return value of hashCode of an instance of Body
     *  @param  hash2    return value of hashCode of an instance of Body 
     *  @param  label    Label for the 'test' case
     *  @param  equal    indicates if hash1 is supposed to be equal to hash2.
     */
    private static void checkEquals(int hash1, int hash2, String label, boolean equal) {
        if (equal && hash1 == hash2) {
            System.out.println("PASS: " + label
                    + ": Expected hash1, " + hash1 + ", to be equal to hash2,  " + hash2);
        } else if (!equal && hash1 != hash2) {
            System.out.println("PASS: " + label
                    + ": Expected hash1, " + hash1 + ", to be unequal to hash2,  " + hash2);
        } else {
            System.out.println("FAIL: " + label
                    + ": hash1 is " + hash1 + ", hash2 is " + hash2 + ", & equal is " + equal);
        }
    }

    /**
     *  Checks the Body class to make sure hashCode works.
     */
    private static void checkHashCode() {
        System.out.println("Checking hashCode...");

        Body b1 = new Body(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Body b2 = new Body(2.0, 1.0, 3.0, 4.0, 4e11, "jupiter.gif");
        Body b3 = new Body(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Body b4 = b1;

        checkEquals(b1.hashCode(), b2.hashCode(), "hashCode()", false);
        checkEquals(b1.hashCode(), b3.hashCode(), "hashCode()", true);
        checkEquals(b1.hashCode(), b4.hashCode(), "hashCode()", true);
    }
}
