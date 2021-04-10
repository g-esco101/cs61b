package bearmaps;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomTest {

    NaiveMinPQ<Integer> solution = new NaiveMinPQ<>();
    ArrayHeapMinPQ<Integer> myHeap = new ArrayHeapMinPQ<>();
    List<Integer> inHeap = new ArrayList<>();
    Random variate = new Random(25000);
    Random priority = new Random(1200);
    StringJoiner stackTrace = new StringJoiner("");
    private final int iterations = 5000;

    @Test
    @DisplayName("Items are added and removed and priorities are changed randomly and the results are compared.")
    public void randomlyCompareMyHeapToSolutionAddRemoveChangePriority() {
        double variate = this.variate.nextDouble();
        double priority;
        int item;
        int count = 0;
        Integer exptected = 0;
        Integer actual = 0;
        stackTrace.add("\n"); // Ensures first invocation is on a new line.
        while (count < iterations) {
            if (variate < 0.33)  {
                if (myHeap.size() > 0 && solution.size() > 0) {
                    actual = myHeap.removeSmallest();
                    exptected = solution.removeSmallest();
                    inHeap.remove(actual);
                    stackTrace.add("removeSmallest()\n");
                    assertEquals(exptected, actual, stackTrace.toString());
                    count++;
                }
            } else if (variate >= 0.33 && variate < 0.66) {
                item = this.priority.nextInt();
                priority = this.priority.nextDouble();
                solution.add(item, priority);
                myHeap.add(item, priority);
                inHeap.add(item);
                stackTrace.add(String.format("add(%d, %f)\n", item, priority));
                assertEquals(solution.size(), myHeap.size(), stackTrace.toString());

                actual = myHeap.getSmallest();
                exptected = solution.getSmallest();
                stackTrace.add("getSmallest()\n");
                assertEquals(exptected, actual, stackTrace.toString());
                count++;
            } else {
                if (solution.size() > 1) {
                    int index = this.priority.nextInt(solution.size());
                    item = inHeap.get(index);
                    priority = this.priority.nextDouble();

                    solution.changePriority(item, priority);
                    myHeap.changePriority(item, priority);

                    actual = myHeap.getSmallest();
                    exptected = solution.getSmallest();

                    stackTrace.add(String.format("changePriority(%d, %f)\n", item, priority));
                    assertEquals(exptected, actual, stackTrace.toString());
                    count++;
                }
            }
            variate = this.variate.nextDouble();
        }
    }
}
