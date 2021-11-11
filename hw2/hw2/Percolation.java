package hw2;

import java.util.HashSet;
import java.util.Set;

/** the data structure used to model the percolation system. */
public class Percolation {

    /** Size of the N-by-N grid.
     *
     */
    private final int N;

    /** Number of open sites on the grid.
     *
     */
    private int numberOfOpenSites;

    /** Indicates whether or not the system is percolating.
     *
     */
    private boolean percolates;

    /** Indicates if a site on the grid is open.
     *
     */
    private final boolean[][] open;

    /** Represents the grid as a disjoint set.
     *
     */
    private final UnionFind unionFind;

    /** The set of roots that are connected to the top.
     *
     */
    private final Set<Integer> virtualTop;

    /** The set of roots that are connected to the bottom.
     *
     */
    private final Set<Integer> virtualBottom;

    /** create N-by-N grid, with all sites initially blocked
     *
     * @param N is the size of the grid.
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be greater than 0");
        }
        this.N = N;
        numberOfOpenSites = 0;
        percolates = false;
        open = new boolean[N][N];
        unionFind = new UnionFind(N * N);
        virtualTop = new HashSet<>();
        virtualBottom = new HashSet<>();
    }

    /** Open the site (row, col) if it is not open already, connects it to adjacent site,
     * and checks if the system percolates.
     *
     * @param row position of the row.
     * @param col position of the column.
     */
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            open[row][col] = true;
            numberOfOpenSites += 1;
            int site = rowColTo1D(row, col);

            // If the site is at the top, then add it to the virtualTop.
            // If the site is at the bottom, then add it to the virtualBottom.
            if (site <= rowColTo1D(0, N - 1)) {
                virtualTop.add(site);
            } else if (site <= rowColTo1D(N - 1, N - 1) && site >= rowColTo1D(N - 1, 0)) {
                virtualBottom.add(site);
            }

            // Connect site to adjacent sites.
            connect(site, row + 1, col);
            connect(site, row - 1, col);
            connect(site, row, col - 1);
            connect(site, row, col + 1);

            // Determine if system is percolating.
            int root = unionFind.find(site);
            if (virtualBottom.contains(root) && virtualTop.contains(root)) {
                percolates = true;
            }
        }
    }

    /** Connects the site to the adjacent site if it is valid and open, and then replaces the roots of the site
     * and the adjacent site with the new root in virtualTop and virtualBottom.
     *
     * @param site location that is being opened.
     * @param adjacentRow row of adjacent site.
     * @param adjacentCol column of adjacent site.
     */
    private void connect(int site, int adjacentRow, int adjacentCol) {

        if (adjacentValid(adjacentRow, adjacentCol) && isOpen(adjacentRow, adjacentCol)) {
            int root = unionFind.find(site);
            int adjacent = rowColTo1D(adjacentRow, adjacentCol);
            int adjacentRoot = unionFind.find(adjacent);
            unionFind.union(site, adjacent);
            int newAdjacentRoot = unionFind.find(adjacent);

            if (virtualTop.contains(root)) {
                virtualTop.remove(root);
                virtualTop.add(newAdjacentRoot);
            }
            if (virtualTop.contains(adjacentRoot)) {
                virtualTop.remove(adjacentRoot);
                virtualTop.add(newAdjacentRoot);
            }
            if (virtualBottom.contains(root)) {
                virtualBottom.remove(root);
                virtualBottom.add(newAdjacentRoot);
            }
            if (virtualBottom.contains(adjacentRoot)) {
                virtualBottom.remove(adjacentRoot);
                virtualBottom.add(newAdjacentRoot);
            }
        }
    }

    /** indicates whether or not a site on the grid is open.
     *
     * @param row position of the row.
     * @param col position of the column.
     * @return true if the site is open and false if is not.
     */
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return open[row][col];
    }

    /** indicates whether or not a site on the grid is full.
     *
     * @param row position of the row.
     * @param col position of the column.
     * @return true if the site is full and false if is not.
     */
    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) {
            return false;
        }
        int site = rowColTo1D(row, col);
        int root = unionFind.find(site);
        if (virtualTop.contains(root)) {
            return true;
        }
        return false;
    }

    /** Gets the number of open sites.
     *
     * @return the number of open sites.
     */
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /** indicates whether or not the system percolates.
     *
     * @return true if the system percolates and false if it is not.
     */
    public boolean percolates() {
        return percolates;
    }

    private int rowColTo1D(int row, int col) {
        return row * N + col;
    }

    private void validate(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("enter row and column are not between 0 and " + (N - 1));
        }
    }

    private boolean adjacentValid(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            return false;
        }
        return true;
    }
}
