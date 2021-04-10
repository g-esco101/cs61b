package bearmaps;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerformanceTest {

    private final int iterations = 4000;
    private final int size = 5000000;

    @Test
    @DisplayName("Performance test for the solution")
    public void solutionPerformance() {
        int randomInt;
        double randomDouble;
        Random random = new Random(48000);
        NaiveMinPQ<Integer> solution = new NaiveMinPQ<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i += 1) {
            solution.add(i, random.nextDouble());
        }
        for (int i = 0; i < iterations; i += 1) {
            randomInt = random.nextInt(size);
            randomDouble = random.nextDouble();
            solution.changePriority(randomInt, randomDouble);
        }
        long end = System.currentTimeMillis();
        System.out.printf("NaiveMinPQ - total time elapsed (s): %f\n", (end - start)/1000.0);
    }

    @Test
    @DisplayName("Performance test for my implementation.")
    public void myHeapPerformance() {
        int randomInt;
        double randomDouble;
        Random random = new Random(48000);
        ArrayHeapMinPQ<Integer> myHeap = new ArrayHeapMinPQ<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i += 1) {
            myHeap.add(i, random.nextDouble());
        }
        for (int i = 0; i < iterations; i += 1) {
            randomInt = random.nextInt(size);
            randomDouble = random.nextDouble();
            myHeap.changePriority(randomInt, randomDouble);
        }
        long end = System.currentTimeMillis();
        System.out.println("ArrayHeapMinPQ - total time elapsed (s): " + (end - start)/1000.0);
    }

    @Test
    @DisplayName("Checks the performance test for correctness.")
    public void randomCorrectness() {
        int randomInt;
        double randomDouble;
        Random random = new Random(48000);
        NaiveMinPQ<Integer> solution = new NaiveMinPQ<>();
        ArrayHeapMinPQ<Integer> myHeap = new ArrayHeapMinPQ<>();

        for (int i = 0; i < size; i += 1) {
            randomDouble = random.nextDouble();
            solution.add(i, randomDouble);
            myHeap.add(i, randomDouble);
        }
        for (int i = 0; i < iterations; i += 1) {
            randomInt = random.nextInt(size);
            randomDouble = random.nextDouble();
            solution.changePriority(randomInt, randomDouble);
            myHeap.changePriority(randomInt, randomDouble);

            int actual = myHeap.getSmallest();
            int exptected = solution.getSmallest();
            assertEquals(exptected, actual);
        }
    }
}
