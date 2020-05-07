/**
 * Created by jug on 1/22/18.
 */

public class DebugExercise3 {
    public static int countTurnips(In in) {
        int totalTurnips = 0;
        while (!in.isEmpty()) {
            // No try catch finally, because "foods.csv"is hardcoded - and it works.
            String vendor = in.readString();
            String foodType = in.readString();
            double cost = in.readDouble();
            int numAvailable = in.readInt();
            if (foodType.equals("turnip")) {
                int newTotal = totalTurnips + numAvailable;
                totalTurnips = newTotal;
            }
            in.readLine();
        }
        return totalTurnips;
    }

    public static void main(String[] args) {
        // No try catch finally, because "foods.csv"is hardcoded - and it works. 
        // Also, no try with resources, because In implements neither Closeable nor AutoCloseable.
        In in = new In("foods.csv");
        System.out.println(countTurnips(in));
        in.close();
    }
}
