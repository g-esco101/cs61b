/** An Integer tester created by Flik Enterprises. */
public class Flik {
    // Bug was in this method. "==" checks if two reference variables point to the same object.
    public static boolean isSameNumber(Integer a, Integer b) {
        return a.equals(b);
//        return a == b;
    }
}
