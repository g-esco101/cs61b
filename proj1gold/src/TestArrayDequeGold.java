package src;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;

import java.util.Random;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** An autograder that compares a student's implementation of an ArrayDeque to the solution.
 *
 * @source https://junit.org/junit5/docs/current/user-guide/#writing-tests-test-instance-lifecycle
 * The instructions state to implement one test method, but does not cover the importance of this in regard to Junit.
 * Junit instantiates a new test class for each test method, so any instance variables will be reset. This can avoided
 * by using the @Before or the @BeforeEach annotations in junit4 and junit5, respectively. Another option is to change
 * this default setting to instantiate a new test class "per-class" by using this annotation
 * at the class level: @TestInstance(Lifecycle.PER_CLASS). The purpose of the "per-method" lifecycle is to allow
 * individual test methods to be executed in isolation and to avoid any unforseen behavior due to mutable test
 * instance state.
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestArrayDequeGold {

    ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
    StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
    Random random = new Random();
    StringJoiner queue = new StringJoiner("");
    private final int iterations = 100;

    @Test
    @DisplayName("Items are added and removed randomly and the results are compared.")
    public void randomlyCompareStudentArrayDequeToSolution() {
        double variate = random.nextDouble();
        int count = 0;
        Integer exptected = 0;
        Integer actual = 0;
        queue.add("\n"); // Ensures first invocation is on a new line.
        while (count < iterations) {
            if (variate < 0.25)  {
                if (!student.isEmpty() && !solution.isEmpty()) {
                    actual = student.removeLast();
                    exptected = solution.removeLast();
                    queue.add("removeLast()\n");
                    assertEquals(exptected, actual, queue.toString());
                }
            } else if (0.26 <= variate && variate < 0.5) {
                solution.addFirst(count);
                student.addFirst(count);
                queue.add(String.format("addFirst(%d)\n", count));
                assertEquals(solution.size(), student.size(),queue.toString());
            } else if (0.5 <= variate && variate < 0.75) {
                if ((!student.isEmpty() && !solution.isEmpty())) {
                    actual = student.removeFirst();
                    exptected = solution.removeFirst();
                    queue.add("removeFirst()\n");
                    assertEquals(exptected, actual, queue.toString());
                }
            } else {
                solution.addLast(count);
                student.addLast(count);
                queue.add(String.format("addLast(%d)\n", count));
                assertEquals(solution.size(), student.size(),queue.toString());
            }
            variate = random.nextDouble();
            if (!student.isEmpty() && !solution.isEmpty()) {
                count++; // increments when methods are invoked.
            }
        }
    }
}

