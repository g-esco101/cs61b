/** Tests the OffByN class. */
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestOffByN {

    @Test
    public void testEqualChars() {
        CharacterComparator OffByN = new OffByN(6);
        assertTrue(OffByN.equalChars('a', 'g'));
        assertTrue(OffByN.equalChars('[', 'a'));
        assertTrue(OffByN.equalChars('{', 'u'));
        assertTrue(OffByN.equalChars('m', 'g'));
        assertFalse(OffByN.equalChars('a', 'a'));
        assertFalse(OffByN.equalChars('-', 'C'));
        assertFalse(OffByN.equalChars('+', '-'));

    }
}
