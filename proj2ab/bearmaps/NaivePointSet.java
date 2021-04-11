package bearmaps;

import bearmaps.Point;
import bearmaps.PointSet;

import java.util.List;

public class NaivePointSet implements PointSet {
    //Note that your bearmaps.NaivePointSet class should be immutable, meaning that you cannot add or or remove points
    // from it. You do not need to do anything special to guarantee this.

    private List<Point> points;
    /** You can assume points has at least size 1.
     *
     * @param points
     */
    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    /**  Returns the closest point to the inputted coordinates. This should take \(\theta(N)\) time where \(N\) is the number of points.
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public Point nearest(double x, double y) {
        Point bestP = null;
        double best = Double.MAX_VALUE;
        double distance;
        for(Point p: points) {
            distance = distance(x, p.getX(), y, p.getY());
            if (distance < best) {
                best = distance;
                bestP = p;
            }
        }
        return bestP;
    }

    private double distance(double x1, double x2, double y1, double y2) {
        return Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
    }
}
