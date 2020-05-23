package tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.CharacterComparator;
import src.OffByOne;

/** Tests the src.OffByOne class. */
@DisplayName("Given an OffByOne")
public class TestOffByOne {
    // You must use this src.CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    @DisplayName("Two characters have a difference of one")
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
