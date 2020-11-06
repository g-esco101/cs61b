package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
import edu.princeton.cs.introcs.Stopwatch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class PercolationStats {
    /** The percentage of open sites when percolation occurs.
     *
     */
    private double[] thresholds;

    /** Perform T independent experiments (i.e. monte carlo simulations) on an N-by-N grid. T should be at least 30.
     *
     * @param N the size of the grid.
     * @param T the number of simulations.
     * @param pf the PercolationFactory.
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must be greater than 0");
        }
        thresholds = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation percolation = pf.make(N);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                percolation.open(row, col);
            }
            thresholds[i] = (double) percolation.numberOfOpenSites() / (N * N);
        }
    }

    /** The sample mean provides an estimate of the percolation threshold.
     *
     * @return mean of the percolation threshold.
     */
    public double mean() {
        return StdStats.mean(thresholds);
    }

    /** The sample standard deviation σ measures the sharpness of the percolation threshold.
     *
     * @return standard deviation of percolation threshold.
     */
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    /** The low end of a 95% confidence interval for the percolation threshold, assuming T is sufficiently large (at least 30).
     *
     * @return low endpoint of 95% confidence interval.
     */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(thresholds.length);
    }

    /** The high end of a 95% confidence interval for the percolation threshold, assuming T is sufficiently large (at least 30).
     *
     * @return high endpoint of 95% confidence interval.
     */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(thresholds.length);
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {
        PercolationFactory percolationFactory = new PercolationFactory();
        int simulationCount = 100;
        for (int gridSize = 100; gridSize <= 1600; gridSize = gridSize * 2) {
            Stopwatch timer = new Stopwatch();
            PercolationStats ps = new PercolationStats(gridSize, simulationCount, percolationFactory);
            double runtime = timer.elapsedTime();

            String experiment = "*** EXPERIMENT *** \n  Using UnionFind - a Weighted Quick Union with path compression that I implemented in lab 6";

            String conditions = String.format("\n simulations ran: %d \n grid size: %d \n total spaces: %d"
                    , simulationCount, gridSize, gridSize * gridSize);

            String results = String.format("\n\t runtime: %.2f seconds \n\t mean: %f \n\t standard deviation: %f \n\t confidence interval: [%f, %f].\n\n"
                    , runtime, ps.mean(), ps.stddev(), ps.confidenceLow(), ps.confidenceHigh());

            experiment = experiment + conditions + results;
            Path filePath = Paths.get("hw2", "results.txt");
            try
            {
                //Write content to file
                Files.writeString(filePath, experiment, StandardOpenOption.APPEND);

                //Verify file content
                String content = Files.readString(filePath);

                System.out.println(content);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
