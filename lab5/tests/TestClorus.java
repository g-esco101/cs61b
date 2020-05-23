package tests;

import creatures.Clorus;
import creatures.Plip;
import huglife.Action;
import huglife.Empty;
import huglife.Impassible;
import huglife.Occupant;
import huglife.Direction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Given a Clorus with energy two")
class TestClorus {

    private Clorus clorus;

    @BeforeEach
    private void instantiate() {
        clorus = new Clorus(2);
    }

    @Test
    public void instantiated_with_energy_is_two() {
        assertEquals(new Color(34, 0, 231), clorus.color());
        assertEquals("clorus", clorus.name());
    }

    @Test
    public void instantiated_without_args() {
        Clorus clorus = new Clorus();
        assertEquals(1, clorus.energy(), 0.001);
        assertEquals(new Color(34, 0, 231), clorus.color());
        assertEquals("clorus", clorus.name());
    }

    @Test
    public void loses_energy_of_one_hundredth_when_stays() {
        clorus.stay();
        assertEquals(1.99, clorus.energy(), 0.001);
    }

    @Test
    public void loses_energy_of_three_hundredth_when_moves() {
        clorus.move();
        assertEquals(1.97, clorus.energy(), 0.001);
    }

    @Test
    public void gains_energy_of_attacked_creature_when_attacks() {
        Plip plip = new Plip(1);
        clorus.attack(plip);
        assertEquals(3, clorus.energy(), 0.001);
    }

    @Test
    public void half_of_energy_goes_to_replicate_when_replicates() {
        Clorus child = clorus.replicate();
        assertEquals(1, clorus.energy(), 0.001);
        assertEquals(1, child.energy(), 0.01);
        assertTrue(child.getClass().equals(Clorus.class));
        assertFalse(clorus == child);
    }

    // If there are no empty squares, the Clorus will STAY (even if there are Plips nearby
    // they could attack since plip squares do not count as empty squares).
    @Test
    public void stays_when_no_empty_spaces() {
        HashMap<Direction, Occupant> allPlips = new HashMap<>();

        allPlips.put(Direction.TOP, new Plip());
        allPlips.put(Direction.BOTTOM, new Plip());
        allPlips.put(Direction.LEFT, new Plip());
        allPlips.put(Direction.RIGHT, new Plip());

        Action actual = clorus.chooseAction(allPlips);
        Action expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);
    }

    // If any Plips are seen, the Clorus will ATTACK one of them randomly.
    @Test
    public void attacks_neighboring_plip_when_space_is_empty() {
        HashMap<Direction, Occupant> plipBottom = new HashMap<>();

        plipBottom.put(Direction.TOP, new Empty());
        plipBottom.put(Direction.BOTTOM, new Plip());
        plipBottom.put(Direction.LEFT, new Empty());
        plipBottom.put(Direction.RIGHT, new Empty());

        Action actual = clorus.chooseAction(plipBottom);
        Action expected = new Action(Action.ActionType.ATTACK, Direction.BOTTOM);
        assertEquals(expected, actual);
    }

    // If the Clorus has energy greater than or equal to one, it will
    // REPLICATE to a random empty square.
    @Test
    public void replicates_when_space_is_empty_and_no_plips_and_energy_is_one_or_greater() {
        HashMap<Direction, Occupant> topLeftEmpty = new HashMap<>();
        List<Action> possibilities = new ArrayList<>();

        topLeftEmpty.put(Direction.TOP, new Empty());
        topLeftEmpty.put(Direction.BOTTOM, new Impassible());
        topLeftEmpty.put(Direction.LEFT, new Empty());
        topLeftEmpty.put(Direction.RIGHT, new Impassible());
        possibilities.add(new Action(Action.ActionType.REPLICATE, Direction.LEFT));
        possibilities.add(new Action(Action.ActionType.REPLICATE, Direction.TOP));

        Action actual = clorus.chooseAction(topLeftEmpty);
        assertTrue(possibilities.contains(actual));
    }

    // the Clorus will MOVE to a random empty square when energy is less than one.
    @Test
    public void moves_when_space_is_empty_and_no_plips_and_energy_is_less_than_one() {
        Clorus clorus = new Clorus(0.5);
        HashMap<Direction, Occupant> bottomRightEmpty = new HashMap<>();
        List<Action> possibilities = new ArrayList<>();

        bottomRightEmpty.put(Direction.TOP, new Impassible());
        bottomRightEmpty.put(Direction.BOTTOM, new Empty());
        bottomRightEmpty.put(Direction.LEFT, new Impassible());
        bottomRightEmpty.put(Direction.RIGHT, new Empty());
        possibilities.add(new Action(Action.ActionType.MOVE, Direction.BOTTOM));
        possibilities.add(new Action(Action.ActionType.MOVE, Direction.RIGHT));

        Action actual = clorus.chooseAction(bottomRightEmpty);
        assertTrue(possibilities.contains(actual));
    }
}
