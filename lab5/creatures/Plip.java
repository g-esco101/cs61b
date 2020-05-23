package creatures;

import hllib.HugLifeUtils;
import huglife.Action;
import huglife.Occupant;
import huglife.Direction;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

    /**
     * red color is a constant.
     */
    private static final int RED = 99;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color is a constant.
     */
    private static final int BLUE = 76;

    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super("plip");
        g = 0;
        energy = normalizeEnergy(e);
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        g = 96 * (int) energy + 63;
        return color(RED, g, BLUE);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy = normalizeEnergy(energy - 0.15);
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        energy = normalizeEnergy(energy + 0.2);
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        energy = energy / 2;
        return new Plip(energy);
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        boolean anyClorus = false;
        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            if (entry.getValue().name().equals("empty")) {
                emptyNeighbors.add(entry.getKey());
            } else {
                if (entry.getValue().name().equals("clorus")) {
                    anyClorus = true;
                }
            }
        }
        if (emptyNeighbors.isEmpty()) {
            // If there are no empty spaces, the Plip should STAY.
            return new Action(Action.ActionType.STAY);
        } else if (energy >= 1) {
            // Otherwise, if the Plip has energy greater than or equal to 1.0,
            // it should replicate to an available space.
            return new Action(Action.ActionType.REPLICATE, HugLifeUtils.randomEntry(emptyNeighbors));
        } else if (anyClorus && Math.random() < 0.50) {
            // Otherwise, if it sees a neighbor with name() equal to “clorus”, it will move to any
            // available empty square with probability 50%. It should choose the empty square randomly.
            // As an example, if it sees a Clorus to the BOTTOM, and “empty” to the TOP , LEFT, and RIGHT,
            // there is a 50% chance it will move (due to fear of Cloruses), and if it does move, it will
            // pick uniformly at random between TOP, LEFT, and RIGHT.
            return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry(emptyNeighbors));
        }
        return new Action(Action.ActionType.STAY);
    }

    /** If the energy is less than 0, return 0. If energy is greater than 2,
     * return 2. Otherwise, return energy.
     * @param energy a possible energy state for the plip.
     * @return the energy state of the plip.
     */
    private double normalizeEnergy(double energy) {
        if (energy < 0) {
            return 0;
        } else if (energy > 2) {
            return  2;
        } else {
            return energy;
        }
    }
}
