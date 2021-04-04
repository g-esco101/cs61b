package hw3.hash;

import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        Map<Integer, Integer> bucketCount = new HashMap<>();
        int bucketNum;
        for (Oomage oomage: oomages) {
            bucketNum = (oomage.hashCode() & 0x7FFFFFFF) % M;
            bucketCount.put(bucketNum, bucketCount.getOrDefault(bucketNum, 0) + 1);
        }
        for(int value: bucketCount.values()) {
            if (value < (oomages.size() / 50) || value > (oomages.size() / 2.5)) {
                return false;
            }
        }
        return true;
    }
}
