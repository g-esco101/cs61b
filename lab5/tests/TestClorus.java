package creatures;

import huglife.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class TestClorus {

    @Test
    public void testBasics() {
        Clorus clorus = new Clorus(2);
        assertEquals("clorus", clorus.name());
        assertEquals(2, clorus.energy(), 0.001);
        assertEquals(new Color(34, 0, 231), clorus.color());
        clorus.move();
        assertEquals(1.97, clorus.energy(), 0.001);
        clorus.move();
        assertEquals(1.94, clorus.energy(), 0.001);
        clorus.stay();
        assertEquals(1.93, clorus.energy(), 0.001);
        clorus.stay();
        assertEquals(1.92, clorus.energy(), 0.001);
        assertEquals(new Color(34, 0, 231), clorus.color());
    }

    @Test
    void attack() {
        Clorus clorus = new Clorus(0.5);
        Plip plip = new Plip(2);
        assertEquals(0.5, clorus.energy(), 0.001);
        assertEquals(2, plip.energy(), 0.01);
        clorus.attack(plip);
        assertEquals(2.5, clorus.energy(), 0.001);
        assertEquals(2, plip.energy(), 0.01);

        clorus = new Clorus();
        plip = new Plip(0);
        assertEquals(1, clorus.energy(), 0.001);
        assertEquals(0, plip.energy(), 0.01);
        clorus.attack(plip);
        assertEquals(1, clorus.energy(), 0.001);
        assertEquals(0, plip.energy(), 0.01);
    }

    @Disabled
    @Test
    void replicate() {
        Clorus clorus = new Clorus(2);
        assertEquals(2, clorus.energy());

        Clorus child = clorus.replicate();
        assertEquals(1, clorus.energy(), 0.001);
        assertEquals(1, child.energy(), 0.001);
        assertTrue(child.getClass().equals(Clorus.class));
        assertFalse(clorus == child);

        child = clorus.replicate();
        assertEquals(0.5, clorus.energy());
        assertEquals(0.5, child.energy(), 0.001);
        assertTrue(child.getClass().equals(Clorus.class));
        assertFalse(clorus == child);

        child = clorus.replicate();
        assertEquals(0.25, clorus.energy(), 0.001);
        assertEquals(0.25, child.energy(), 0.001);
        assertTrue(child.getClass().equals(Clorus.class));
        assertFalse(clorus == child);

        clorus = new Clorus(0);
        child = clorus.replicate();
        assertEquals(0, clorus.energy());
        assertEquals(0, child.energy());
        assertTrue(child.getClass().equals(Clorus.class));
        assertFalse(clorus == child);
    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus clorus = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = clorus.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        clorus = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = clorus.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        clorus = new Clorus(1.2);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());

        actual = clorus.chooseAction(allEmpty);
        Action unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);


        // Energy < 1; stay.
        clorus = new Clorus(.99);

        actual = clorus.chooseAction(allEmpty);
        expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // Energy < 1; stay.
        clorus = new Clorus(.99);

        actual = clorus.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // We don't have Cloruses yet, so we can't test behavior for when they are nearby right now.
    }
}
