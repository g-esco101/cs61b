
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RabinKarpAlgorithmTests {
    @Test
    public void basic() {
        String input = "hello";
        String pattern = "ell";
        assertEquals(1, RabinKarpAlgorithm.rabinKarp(input, pattern));

        input = "hello";
        pattern = "world";
        assertEquals(-1, RabinKarpAlgorithm.rabinKarp(input, pattern));

        input = "bonjour";
        pattern = "onj";
        assertEquals(1, RabinKarpAlgorithm.rabinKarp(input, pattern));

        input = "physics";
        pattern = "physics";
        assertEquals(0, RabinKarpAlgorithm.rabinKarp(input, pattern));
    }
}
