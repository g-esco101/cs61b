package src;
/** A data structure that represents a disjoint set. */
public class UnionFind {

    /** An array whose index represents an element in the set and whose value represents its parent,
     *  unless it is a negative. A negative value indicates that the element is a root and the value
     *  is the negative of the size of that set.
     */
    private int[] sets;

    /** Creates a src.UnionFind data structure holding n vertices. Initially, all vertices are in disjoint sets.
     *
     * @param n the number of elements
     */
    public UnionFind(int n) {
        if (n < 1) {
            n = 8;
        }
        sets = new int[n];
        for (int i = 0; i < n; i++) {
            sets[i] = -1;
        }
    }

    /** Throws an exception if v1 is not a valid index.
     *
     * @param vertex the element to validate
     */
    private void validate(int vertex) {
        if (vertex < 0 || vertex >= sets.length) {
            throw new IllegalArgumentException("vertex is invalid");
        }
    }

    /** Returns the size of the set v1 belongs to.
     *
     * @param v1 the element in a set.
     * @return the size of the set.
     */
    public int sizeOf(int v1) {
        validate(v1);
        int myParent = parent(v1);
        while (myParent > 0) {
            v1 = myParent;
            myParent = parent(v1);
        }
        return -myParent;
    }

    /** Returns the parent of v1. If v1 is the root of a tree, returns the negative size of the tree for which v1 is the root.
     *
     * @param v1 the element whose parent is returned.
     * @return the parent of v1.
     */
    public int parent(int v1) {
        validate(v1);
        return sets[v1];
    }

    /** Returns true if nodes v1 and v2 are connected.
     *
     * @param v1 an element in a set.
     * @param v2 another element in a set.
     * @return true if they are connected. Otherwise, return false.
     */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /** Connects two elements v1 and v2 together. v1 and v2 can be any valid elements, and a union-by-size
     * heuristic is used. If the sizes of the sets are equal, tie break by connecting v1's root to v2's root.
     * Unioning an element with itself or elements that are already connected should not change the sets but
     * may alter the internal structure of the data.
     *
     * @param v1 an element in a set.
     * @param v2 another element in a set.
     */

    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int v1Root = find(v1);
        int v2Root = find(v2);
        if (v1Root == v2Root) {
            return;
        }
        if (sets[v1Root] < sets[v2Root]) {
            sets[v1Root] += sets[v2Root];
            sets[v2Root] = v1Root;
        } else {
            sets[v2Root] += sets[v1Root];
            sets[v1Root] = v2Root;
        }
    }

    /** Returns the root of the set V belongs to. Path-compression is employed allowing for fast search-time.
     *
     * @param vertex the element whose root is retrieved.
     * @return the root of vertex.
     */
    public int find(int vertex) {
        validate(vertex);
        if (sets[vertex] < 0) {
            return vertex;
        }
        if (sets[parent(vertex)] < 0) {
            return parent(vertex);
        }
        sets[vertex] = find(parent(vertex));
        return parent(vertex);
    }

}
