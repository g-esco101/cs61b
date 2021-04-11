package bearmaps;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

@DisplayName("Given a NaivePointSetTest - sanity checks")
public class NaivePointSetTest {

    @Test
    @DisplayName("it finds the nearest point")
    public void sanityNearestTest() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        assertEquals(p2, ret);
        assertEquals(p2.getX(), ret.getX());
        assertEquals(p2.getY(), ret.getY());
    }
}
