import java.util.Arrays;

public class Fast {
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        int n = in.readInt();

        Point[] points = new Point[n];
        Point[] points_aux = new Point[n];

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            points_aux[i] = points[i];
            points[i].draw();
        }

        Arrays.sort(points);

        for (int i = 0; i < n - 3; i++) {
            Point pivot = points[i];

            Arrays.sort(points_aux, pivot.SLOPE_ORDER);

            for (int j = 1, k = j + 1; j < n - 2; j = k, k = j + 1) {
                double sij = points[i].slopeTo(points_aux[j]);
                while (k < n  && sij == points[i].slopeTo(points_aux[k]))
                    k++;

                if (k - j >= 3) {
                    Arrays.sort(points_aux, j, k);
                    if (pivot.compareTo(points_aux[j]) >= 0) {
                        continue;
                    }

                    StdOut.print(pivot + " -> ");
                    int ii;
                    for (ii = j; ii < k - 1; ii++) {
                        StdOut.print(points_aux[ii] + " -> ");
                    }
                    StdOut.println(points_aux[ii]);
                    pivot.drawTo(points_aux[ii]);
                }
            }
        }
    }
}
