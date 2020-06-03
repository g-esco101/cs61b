package tests.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import src.BSTMapRecursive;

import java.util.Map;
import java.util.Random;
import java.util.StringJoiner;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Given a BSTMapRecursive")
public class BSTMapRecursiveRandomTest {

    BSTMapRecursive<Integer, Integer> bstMap;
    TreeMap<Integer, Integer> treeMap;
    Random randomDbl;
    Random randomInt;
    StringJoiner stackTrace;
    int iterations;

    @BeforeEach
    public void init() {
        bstMap = new BSTMapRecursive<>();
        treeMap = new TreeMap<>();
        randomDbl = new Random();
        randomInt = new Random();
        stackTrace = new StringJoiner("\n");
        iterations = 100;
    }

    @Test
    @DisplayName("Items are added and removed randomly and the results are compared java.util.TreeMap.")
    public void compareBSTMapToJavaUtilTreeMap() {
        double variateDbl = randomDbl.nextDouble();
        int variateInt = randomInt.nextInt(100);
        int count = 0;
        Integer exptected = 0;
        Integer actual = 0;
        stackTrace.add("");
        while (count < iterations) {
            if (variateDbl < 0.5 && !bstMap.isEmpty() && !treeMap.isEmpty())  {
                actual = bstMap.remove(variateInt);
                exptected = treeMap.remove(variateInt);
                stackTrace.add(String.format("remove(%d)", variateInt));
                assertEquals(exptected, actual, stackTrace.toString());
                assertTrue(containSameKeys());
                count++;
            } else {
                treeMap.put(variateInt, variateInt);
                bstMap.put(variateInt, variateInt);
                stackTrace.add(String.format("put(%d, %d)", variateInt, variateInt));
                assertEquals(treeMap.size(), bstMap.size(), stackTrace.toString());
                assertTrue(containSameKeys());
                count++;
            }
            variateDbl = randomDbl.nextDouble();
            variateInt = randomInt.nextInt(100);
        }
    }

    private boolean containSameKeys() {
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            if (!bstMap.containsKey(entry.getKey())) {
                return false;
            }
        }
        for (Integer i : bstMap) {
            if (!treeMap.containsKey(i)) {
                return false;
            }
        }
        return true;
    }
}
