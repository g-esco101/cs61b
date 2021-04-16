package tests.BSTMapRecursive;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.BSTMapRecursive;
import src.Map61B;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBSTMapRecursiveRandom {

    Map<Integer, Integer> solution = new TreeMap<>();
    Map61B<Integer, Integer> student = new BSTMapRecursive<>();
    Random random = new Random();
    StringJoiner stackTrace = new StringJoiner("");
    private final int testCount = 100000;
    private final int sampleRange = 100;

    @Test
    @DisplayName("Items are added and removed randomly and the results are compared.")
    public void randomlyCompareStudentMap61BToSolution() {
        double variate;
        int test = 0;
        Integer keyValue, exptected, actual;
        Set<Integer> actualSet, expectedSet;
        Iterator<Integer> actualIter, expectedIter;
        stackTrace.add("\n"); // Ensures first invocation is on a new line.
        while (test < testCount) {
            keyValue = random.nextInt(sampleRange);
            variate = random.nextDouble();
            test++;
            if (variate < 0.125)  {
                // Removes a key.
                stackTrace.add(String.format("remove(%d)\n", keyValue));
                exptected = solution.remove(keyValue);
                actual = student.remove(keyValue);
                assertEquals(exptected, actual, stackTrace.toString());
                assertEquals(solution.size(), student.size(), stackTrace.toString());
            } else if (0.125 <= variate && variate < 0.25) {
                // Does not remove key with different values.
                stackTrace.add(String.format("remove(%d, %d)\n", keyValue, keyValue + 1));
                exptected = null;
                actual = student.remove(keyValue, keyValue + 1);
                assertEquals(exptected, actual, stackTrace.toString());
                assertEquals(solution.size(), student.size(), stackTrace.toString());
            } else if (0.25 <= variate && variate < 0.375) {
                // Removes key with same value.
                stackTrace.add(String.format("remove(%d, %d)\n", keyValue, keyValue));
                exptected = solution.remove(keyValue);
                actual = student.remove(keyValue, keyValue);
                assertEquals(exptected, actual, stackTrace.toString());
                assertEquals(solution.size(), student.size(), stackTrace.toString());
            } else if (0.375 <= variate && variate < 0.50) {
                // Adds key and value
                stackTrace.add(String.format("put(%d, %d)\n", keyValue, keyValue));
                solution.put(keyValue, keyValue);
                student.put(keyValue, keyValue);
                assertEquals(solution.size(), student.size(), stackTrace.toString());
            } else if (0.50 <= variate && variate < 0.625) {
                // Gets value.
                stackTrace.add(String.format("get(%d)\n", keyValue));
                exptected = solution.get(keyValue);
                actual = student.get(keyValue);
                assertEquals(exptected, actual, stackTrace.toString());
            } else if (0.625 <= variate && variate < 0.75) {
                // Gets value.
                stackTrace.add(String.format("keySet()\n"));
                expectedSet = solution.keySet();
                actualSet = student.keySet();
                assertEquals(expectedSet.size(), actualSet.size(), stackTrace.toString());
                expectedIter = expectedSet.iterator();
                actualIter = actualSet.iterator();
                while (expectedIter.hasNext()) {
                    assertEquals(expectedIter.next(), actualIter.next(), stackTrace.toString());
                }
            } else if (0.75 <= variate && variate < 87.5) {
                // Gets value.
                stackTrace.add(String.format("iterator()\n"));
                expectedSet = solution.keySet();
                actualSet = student.keySet();
                expectedIter = expectedSet.iterator();
                actualIter = student.iterator();
                exptected = expectedSet.size();
                actual = 0;
                while (actualIter.hasNext()) {
                    actual++;
                    assertEquals(expectedIter.next(), actualIter.next(), stackTrace.toString());
                }
                assertEquals(exptected, actual, stackTrace.toString());
            } else {
                // Contains key.
                stackTrace.add(String.format("containsKey(%d)\n", keyValue));
                assertEquals(solution.containsKey(keyValue), student.containsKey(keyValue), stackTrace.toString());
            }
        }
    }
}
