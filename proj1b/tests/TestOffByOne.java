package tests; /** Tests the src.OffByOne class. */
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.CharacterComparator;
import src.OffByOne;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestOffByOne {
    // You must use this src.CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testEqualChars() {
        Assertions.assertFalse(offByOne.equalChars('a', 'e'));
        Assertions.assertFalse(offByOne.equalChars('z', 'a'));
        Assertions.assertFalse(offByOne.equalChars('a', 'a'));
        Assertions.assertFalse(offByOne.equalChars('c', 'C'));
        Assertions.assertFalse(offByOne.equalChars('+', '-'));
        Assertions.assertFalse(offByOne.equalChars('{', '}'));

        Assertions.assertTrue(offByOne.equalChars('&', '%'));
        Assertions.assertTrue(offByOne.equalChars('a', 'b'));
        Assertions.assertTrue(offByOne.equalChars('r', 'q'));
        Assertions.assertTrue(offByOne.equalChars('Z', 'Y'));
        Assertions.assertTrue(offByOne.equalChars(':', ';'));

    }
}
