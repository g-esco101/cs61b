package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Color;
import java.util.List;

import creatures.Clorus;
import creatures.Plip;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Tests the plip class
 *  @authr FIXME
 */
@DisplayName("Given a Plip with energy one")
public class TestPlip {

    private Plip plip;

    @BeforeEach
    private void instantiate() {
        plip = new Plip();
    }

    @Test
    public void energy_is_two_when_constructor_arg_is_greater_than_two() {
        Plip plip = new Plip(5);
        assertEquals(2, plip.energy(), 0.01);
        assertEquals(new Color(99, 255, 76), plip.color());
        assertEquals("plip", plip.name());
    }

    @Test
    public void energy_is_zero_when_constructor_arg_is_less_than_zero() {
        Plip plip = new Plip(-5);
        assertEquals(0, plip.energy(), 0.01);
        assertEquals(new Color(99, 63, 76), plip.color());
        assertEquals("plip", plip.name());
    }

    @Test
    public void instantiated_without_args() {
        Plip plip = new Plip();
        assertEquals(1, plip.energy(), 0.01);
        assertEquals(new Color(99, 159, 76), plip.color());
        assertEquals("plip", plip.name());
    }

    @Test
    public void gains_energy_of_two_tenths_when_stays() {
        plip.stay();
        assertEquals(1.2, plip.energy(), 0.01);
    }

    @Test
    public void loses_energy_of_fifteen_hundredths_when_moves() {
        plip.move();
        assertEquals(0.85, plip.energy(), 0.01);
    }

    @Test
    public void half_of_energy_goes_to_replicate() {
        Plip child = plip.replicate();

        assertEquals(0.5, plip.energy());
        assertEquals(new Color(99, 63, 76), plip.color());
        assertEquals(0.5, child.energy());
        assertEquals(new Color(99, 63, 76), child.color());
        assertTrue(child.getClass().equals(Plip.class));
        assertFalse(plip == child);
    }

    // If there are no empty spaces, the Plip should STAY.
    @Test
    public void stays_when_no_empty_spaces() {
        HashMap<Direction, Occupant> surrounded = new HashMap<>();

        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = plip.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);
    }

    // If space is empty and the Plip has energy greater than or equal to 1.0, it should
    // replicate to an available space.
    @Test
    public void replicates_when_space_is_empty_and_energy_is_one_or_greater() {
        HashMap<Direction, Occupant> topEmpty = new HashMap<>();

        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        Action actual = plip.chooseAction(topEmpty);
        Action expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);
        assertEquals(expected, actual);
    }

    // If space is empty and the Plip has energy less than 1.0 and its neighbor
    // is a clorus, it will move to any available empty square with probability 50%.
    // It should choose the empty square randomly.
    @Test
    public void moves_or_stays_when_space_is_empty_and_energy_is_less_than_one_and_neighbor_is_clorus() {
        Plip plip = new Plip(0.5);
        HashMap<Direction, Occupant> rightEmpty = new HashMap<>();
        List<Action> possibilities = new ArrayList<>();

        rightEmpty.put(Direction.TOP, new Clorus());
        rightEmpty.put(Direction.BOTTOM, new Clorus());
        rightEmpty.put(Direction.LEFT, new Clorus());
        rightEmpty.put(Direction.RIGHT, new Empty());
        possibilities.add(new Action(Action.ActionType.MOVE, Direction.RIGHT));
        possibilities.add(new Action(Action.ActionType.STAY));

        Action actual = plip.chooseAction(rightEmpty);
        assertTrue(possibilities.contains(actual));
        actual = plip.chooseAction(rightEmpty);
        assertTrue(possibilities.contains(actual));
        actual = plip.chooseAction(rightEmpty);
        assertTrue(possibilities.contains(actual));
        actual = plip.chooseAction(rightEmpty);
        assertTrue(possibilities.contains(actual));
    }

    // If space is available and the Plip has energy less than 1.0 and its neighbor
    // is not a clorus, it will stay.
    @Test
    public void stays_when_space_is_empty_and_energy_is_less_than_one_and_no_clorus() {
        Plip plip = new Plip(0.5);
        HashMap<Direction, Occupant> topEmpty = new HashMap<>();

        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        Action actual = plip.chooseAction(topEmpty);
        Action expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);
    }
}
