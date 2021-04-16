package src;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Map;
import java.util.Random;
import java.util.StringJoiner;
import java.util.TreeMap;

/** An autograder that compares a student's implementation of an ArrayDeque to the solution.
 *
 * @source https://junit.org/junit5/docs/current/user-guide/#writing-tests-test-instance-lifecycle
 * The instructions state to implement one test method, but does not cover the importance of this in regard to Junit.
 * Junit instantiates a new test class for each test method, so any instance variables will be reset. This can avoided
 * by using the @Before or the @BeforeEach annotations in junit4 and junit5, respectively. Another option is to change
 * this default setting to instantiate a new test class "per-class" by using this annotation
 * at the class level: @TestInstance(Lifecycle.PER_CLASS). The purpose of the "per-method" lifecycle is to allow
 * individual test methods to be executed in isolation and to avoid any unforeseen behavior due to mutable test
 * instance state.
 */

//@TestInstance(Lifecycle.PER_CLASS)
public class TestArrayDequeGold {

    ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
    StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
    Random random = new Random();
    StringJoiner stackTrace = new StringJoiner("");
    private final int iterations = 100000;

    @Test
    @DisplayName("Items are added and removed randomly and the results are compared.")
    public void randomlyCompareStudentArrayDequeToSolution() {
        double variate;
        int key;
        int count = 0;
        Integer exptected = 0;
        Integer actual = 0;
        stackTrace.add("\n"); // Ensures first invocation is on a new line.
        while (count < iterations) {
            variate = random.nextDouble();
            key = random.nextInt(100);
            count++;
            if (variate < 0.25)  {
                actual = student.removeLast();
                exptected = solution.removeLast();
                stackTrace.add("removeLast()\n");
                assertEquals(exptected, actual, stackTrace.toString());
            } else if (0.26 <= variate && variate < 0.5) {
                solution.addFirst(key);
                student.addFirst(key);
                stackTrace.add(String.format("addFirst(%d)\n", key));
                assertEquals(solution.size(), student.size(), stackTrace.toString());
            } else if (0.5 <= variate && variate < 0.75) {
                actual = student.removeFirst();
                exptected = solution.removeFirst();
                stackTrace.add("removeFirst()\n");
                assertEquals(exptected, actual, stackTrace.toString());
            } else {
                solution.addLast(key);
                student.addLast(key);
                stackTrace.add(String.format("addLast(%d)\n", key));
                assertEquals(solution.size(), student.size(), stackTrace.toString());
            }
        }
    }
}

