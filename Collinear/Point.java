/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new BySlope();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    private class BySlope implements Comparator<Point> {
        public int compare (Point p1, Point p2)
        {
            double s1 = slopeTo(p1);
            double s2 = slopeTo(p2);
            if (s1 < s2)
                return -1;
            if (s1 > s2)
                return 1;
            return 0;
        }
    }

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (that == null) {
            throw new NullPointerException();
        }

        if (this.y == that.y && this.x != that.x) {
            return 0.0;
        }

        if (this.x == that.x) {
            if (this.y != that.y)
                return Double.POSITIVE_INFINITY;

            return Double.NEGATIVE_INFINITY;
        }

        return (that.y - this.y) / (double) (that.x - this.x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y < that.y) {
            return -1;
        }

        if (this.y == that.y) {
            if (this.x < that.x) {
                return -1;
            }
            if (this.x == that.x) {
                return 0;
            }
        }
        return 1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        Point p = new Point(5, 4);
        Point q = new Point(5, 1);

        StdOut.println(p.slopeTo(q));
    }
}
