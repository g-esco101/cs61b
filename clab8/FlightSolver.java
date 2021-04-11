import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
@Getter @Setter
public class FlightSolver {

    private int maxPassengers = 0;


    public FlightSolver(ArrayList<Flight> flights) {
        Comparator<Flight> startTime = Comparator.comparingInt((Flight arg) -> arg.startTime());
        Comparator<Flight> endTime = Comparator.comparingInt((Flight arg) -> arg.endTime());
        PriorityQueue<Flight> queueStart = new PriorityQueue<>(startTime);
        PriorityQueue<Flight> queueEnd = new PriorityQueue<>(endTime);
        queueStart.addAll(flights);
        queueEnd.addAll(flights);

        int count = 0;
        while (queueStart.size() > 0) {
            if (queueEnd.peek().endTime() >= queueStart.peek().startTime()) {
                count += queueStart.poll().passengers();
                maxPassengers = Math.max(count, maxPassengers);
            } else {
                count -= queueEnd.poll().passengers();
            }
        }
    }

    public int solve() {
        return maxPassengers;
    }
}
