package tests; /** Tests the src.OffByN class. */
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.CharacterComparator;
import src.OffByN;

public class TestOffByN {

    @Test
    public void testEqualChars() {
        CharacterComparator OffByN = new OffByN(6);
        Assertions.assertTrue(OffByN.equalChars('a', 'g'));
        Assertions.assertTrue(OffByN.equalChars('[', 'a'));
        Assertions.assertTrue(OffByN.equalChars('{', 'u'));
        Assertions.assertTrue(OffByN.equalChars('m', 'g'));
        Assertions.assertFalse(OffByN.equalChars('a', 'a'));
        Assertions.assertFalse(OffByN.equalChars('-', 'C'));
        Assertions.assertFalse(OffByN.equalChars('+', '-'));

    }
}
