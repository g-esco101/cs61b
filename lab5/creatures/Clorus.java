package creatures;

import hllib.HugLifeUtils;
import huglife.Action;
import huglife.Occupant;
import huglife.Direction;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;


public class Clorus extends Creature {

    /**
     * red color.
     */
    private static final int RED = 34;
    /**
     * green color.
     */
    private static final int GREEN = 0;
    /**
     * blue color.
     */
    private static final int BLUE = 231;

    /**
     * Creates a clorus with energy equal to 1.
     *
     */
    public Clorus() {
        this(1);
    }

    /**
     * Creates a creature with the name clorus with energy e. The intention is that this
     * name should be shared between all creatures of the same type.
     *
     * @param e the initial energy of the clorus.
     */
    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    /**
     * Called when this creature chooses stay.
     */
    @Override
    public void stay() {
        energy -= 0.01;
    }

    /**
     * Called when this creature moves.
     */
    @Override
    public void move() {
        energy -= 0.03;
    }

    /**
     * Called when this creature attacks C.
     *
     * @param c the creature to attack.
     */
    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    /**
     * Called when this creature chooses replicate.
     * Must return a creature of the same type.
     */
    @Override
    public Clorus replicate() {
        energy = energy / 2;
        return new Clorus(energy);
    }

    /**
     * Returns an action. The creature is provided information about its
     * immediate NEIGHBORS with which to make a decision.
     *
     * @param neighbors contains information about immediate neighbors.
     */
    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plips = new ArrayDeque<>();
        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            if (entry.getValue().name().equals("empty")) {
                emptyNeighbors.add(entry.getKey());
            } else {
                if (entry.getValue().name().equals("plip")) {
                    plips.add(entry.getKey());
                }
            }
        }
        if (emptyNeighbors.isEmpty()) {
            // If there are no empty squares, the Clorus will STAY (even if there are Plips
            // nearby they could attack since plip squares do not count as empty squares).
            return new Action(Action.ActionType.STAY);
        } else if (plips.size() > 0) {
            // Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
            return new Action(Action.ActionType.ATTACK, HugLifeUtils.randomEntry(plips));
        } else if (energy >= 1) {
            // Otherwise, if the Clorus has energy greater than or equal to one,
            // it will REPLICATE to a random empty square.
            return new Action(Action.ActionType.REPLICATE, HugLifeUtils.randomEntry(emptyNeighbors));
        }
        return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry(emptyNeighbors));
    }

    /**
     * Required method that returns a color.
     */
    @Override
    public Color color() {
        return new Color(RED, GREEN, BLUE);
    }
}
