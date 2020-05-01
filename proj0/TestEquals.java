/**
 *  Tests equals
 */
public class TestEquals {

    /**
     *  Tests equals.
     */
    public static void main(String[] args) {
        checkEquals();
    }

    /**
     *  Checks whether or not two Body instances are equal and prints the result.
     *
     *  @param  expected    Expected boolean
     *  @param  actual      Boolean received
     *  @param  label       Label for the 'test' case
     */
    private static void checkEquals(boolean actual, boolean expected, String label) {
        if (actual == expected) {
            System.out.println("PASS: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        }
    }


    /**
     *  Checks the Body class to make sure equals works.
     */
    private static void checkEquals() {
        System.out.println("Checking checkEquals...");

        Body b1 = new Body(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Body b2 = new Body(2.0, 1.0, 3.0, 4.0, 4e11, "jupiter.gif");
        Body b3 = new Body(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Body b4 = b1;

        checkEquals(b1.equals(b2), false, "equals()");
        checkEquals(b1.equals(b3), true, "equals()");
        checkEquals(b1.equals(b4), true, "equals()");
    }
}
