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
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Tests the plip class
 *  @authr FIXME
 */
public class TestPlip {

    private Plip plip;

    @Nested
    @DisplayName("Given a Plip with energy one")
    class PlipEnergyOne {
        @BeforeEach
        private void instantiate() {
            plip = new Plip();
        }

        @Test
        @DisplayName("gains 0.2 energy when it stays")
        public void gainsEnergyOfTwoTenthsWhenStays() {
            plip.stay();
            assertEquals(1.2, plip.energy(), 0.01);
        }

        @Test
        @DisplayName("loses 0.15 energy when it moves")
        public void losesEnergyOfFifteenHundredthsWhenMoves() {
            plip.move();
            assertEquals(0.85, plip.energy(), 0.01);
        }

        @Test
        @DisplayName("loses half of energy to replicate when it replicates")
        public void halfOfEnergyGoesToReplicateWhenReplicates() {
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
        @DisplayName("stays when there is no empty space")
        public void staysWhenNoEmptySpace() {
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
        @DisplayName("replicates to an empty space when space is empty and energy is one or greater")
        public void replicatesWhenSpaceIsEmptyAndEnergyIsOneOrGreater() {
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
        @DisplayName("moves or stays when space is empty, energy is less than one, and there are neighboring clorus")
        public void movesOrStaysWhenSpaceIsEmptyAndEnergyIsLessThanOneAndNeighborclorus() {
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
        @DisplayName("stays when space is empty, energy is less than one, and there are no neighboring clorus")
        public void staysWhenSpaceIsEmptyAndEnergyIsLessThanOneAndNoClorus() {
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

    @Nested
    @DisplayName("A Plip is instantiated")
    class ConstructorTests {

        @Test
        @DisplayName("with energy greater than two - has energy of two and the proper name and color")
        public void energyIsTwoWhenConstructorArgIsGreaterThanTwo() {
            Plip plip = new Plip(5);
            assertEquals(2, plip.energy(), 0.01);
            assertEquals(new Color(99, 255, 76), plip.color());
            assertEquals("plip", plip.name());
        }

        @Test
        @DisplayName("with energy less than zero - has energy of zero and the proper name and color")
        public void energyIsZeroWhenConstructorArgIsLessThanZero() {
            Plip plip = new Plip(-5);
            assertEquals(0, plip.energy(), 0.01);
            assertEquals(new Color(99, 63, 76), plip.color());
            assertEquals("plip", plip.name());
        }

        @Test
        @DisplayName("without constructor arguments - has energy of one and the proper name and color")
            public void instantiatedWithoutArgs() {
            Plip plip = new Plip();
            assertEquals(1, plip.energy(), 0.01);
            assertEquals(new Color(99, 159, 76), plip.color());
            assertEquals("plip", plip.name());
        }
    }
}
