package tests;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.TreeMap;
import java.io.IOException;
import java.util.Scanner;

import edu.princeton.cs.algs4.Stopwatch;

import src.*;

/**
 * Performs a timing test on three different set implementations.
 * For src.BSTMap purposes assumes that <K,V> are <String, Integer> pairs.
 *
 * @author Josh Hug
 * @author Brendan Hu
 */
public class InsertInOrderSpeedTest {

    private static StringBuilder result = new StringBuilder("\n\t\tInsertInOrderSpeedTest\n");

    /**
     * Requests user input and performs tests of three different set
     * implementations. ARGS is unused.
     */
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        // borrow waitForPositiveInt(Scanner input) from tests.InsertRandomSpeedTest
        InsertRandomSpeedTest i = new InsertRandomSpeedTest();

        System.out.println("\nThis program inserts lexicographically increasing Strings into Maps as <String, Integer> pairs.");

        String repeat = "y";
        do {
            System.out.print("\nEnter # strings to insert into the maps: ");
            int N = i.waitForPositiveInt(input);
            result.append(String.format("\t%d strings inserted\n", N));
            timeInOrderMap61B(new ULLMap<>(), N);
            timeInOrderMap61B(new BSTMap<>(), N);
            timeInOrderMap61B(new BSTMapParent<>(), N);
            timeInOrderMap61B(new BSTMapRecursive<>(), N);
            timeInOrderTreeMap(new TreeMap<>(), N);
            timeInOrderHashMap(new HashMap<>(), N);

            System.out.print("\nWould you like to try more timed-tests? (y/n): ");
            repeat = input.nextLine();
        } while (!repeat.equalsIgnoreCase("n") && !repeat.equalsIgnoreCase("no"));
        input.close();

        Path paths = Paths.get("speedTestResults.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(paths, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND)) {
            writer.append(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns time needed to put N strings into a src.Map61B in increasing order.
     * makes use of tests.StringUtils.nextString(String s)
     */
    public static double insertInOrder(Map61B<String, Integer> map61B, int N) {
        Stopwatch sw = new Stopwatch();
        String s = "cat";
        for (int i = 0; i < N; i++) {
            s = StringUtils.nextString(s);
            map61B.put(s, i);
        }
        return sw.elapsedTime();
    }

    /**
     * Returns time needed to put N strings into TreeMap in increasing order.
     */
    public static double insertInOrder(TreeMap<String, Integer> ts, int N) {
        Stopwatch sw = new Stopwatch();
        String s = "cat";
        for (int i = 0; i < N; i++) {
            s = StringUtils.nextString(s);
            ts.put(s, i);
        }
        return sw.elapsedTime();
    }

    public static double insertInOrder(HashMap<String, Integer> ts, int N) {
        Stopwatch sw = new Stopwatch();
        String s = "cat";
        for (int i = 0; i < N; i++) {
            s = StringUtils.nextString(s);
            ts.put(s, i);
        }
        return sw.elapsedTime();
    }

    /**
     * Attempts to insert N in-order strings of length L into map,
     * Prints time of the N insert calls, otherwise
     * Prints a nice message about the error
     */
    public static void timeInOrderMap61B(Map61B<String, Integer> map, int N) {
        try {
            double mapTime = insertInOrder(map, N);
            String str = String.format("%s: %.2f sec\n", map.getClass().toString(), mapTime);
            result.append(str);
            System.out.println(str);
        } catch (StackOverflowError e) {
            printInfoOnStackOverflow(map.getClass().toString(), N);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Attempts to insert N in-order strings of length L into TreeMap,
     * Prints time of the N insert calls, otherwise
     * Prints a nice message about the error
     */
    public static void timeInOrderTreeMap(TreeMap<String, Integer> treeMap, int N) {
        try {
            double javaTime = insertInOrder(treeMap, N);
            String str = String.format("Java's Built-in TreeMap: %.2f sec\n", javaTime);
            result.append(str);
            System.out.println(str);
        } catch (StackOverflowError e) {
            printInfoOnStackOverflow(treeMap.getClass().toString(), N);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Attempts to insert N in-order strings of length L into HashMap,
     * Prints time of the N insert calls, otherwise
     * Prints a nice message about the error
     */
    public static void timeInOrderHashMap(HashMap<String, Integer> hashMap, int N) {
        try {
            double javaTime = insertInOrder(hashMap, N);
            String str = String.format("Java's Built-in HashMap: %.2f sec\n", javaTime);
            result.append(str);
            System.out.println(str);
        } catch (StackOverflowError e) {
            printInfoOnStackOverflow(hashMap.getClass().toString(), N);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /* ------------------------------- Private methods ------------------------------- */

    /**
     * To be called after catching a StackOverflowError
     * Prints the error with corresponding N and L
     */
    private static void printInfoOnStackOverflow(String type, int N) {
        String str = String.format("%s - Stack Overflow - could not add %d strings", type, N);
        result.append(str);
        System.out.println(str);
    }
}