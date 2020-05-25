package tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.CharacterComparator;
import src.OffByN;

/** Tests the src.OffByN class. */
@DisplayName("Given an OffByN")
public class TestOffByN {

    @Test
    @DisplayName("verifies that two characters have a difference of N")
    public void charactersOffByOne() {
        CharacterComparator OffByN = new OffByN(6);
        Assertions.assertTrue(OffByN.equalChars('a', 'g'));
        Assertions.assertTrue(OffByN.equalChars('[', 'a'));
        Assertions.assertTrue(OffByN.equalChars('{', 'u'));
        Assertions.assertTrue(OffByN.equalChars('m', 'g'));
    }

    @Test
    @DisplayName("verifies that two characters do not have a difference of N")
    public void charactersNotOffByOne() {
        CharacterComparator OffByN = new OffByN(6);
        Assertions.assertFalse(OffByN.equalChars('a', 'a'));
        Assertions.assertFalse(OffByN.equalChars('-', 'C'));
        Assertions.assertFalse(OffByN.equalChars('+', '-'));
    }
}
