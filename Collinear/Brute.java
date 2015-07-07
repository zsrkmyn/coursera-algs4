import java.util.Arrays;

public class Brute {
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        int n = in.readInt();

        Point [] points = new Point[n];

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }


        Arrays.sort(points);

        for (int i = 0; i < n - 3; i++)
            for (int j = i + 1; j < n - 2; j++)
                for (int k = j + 1; k < n - 1; k++)
                    for (int l = k + 1; l < n; l++)
                        if (points[i].slopeTo(points[j]) ==
                                points[i].slopeTo(points[k]) &&
                            points[i].slopeTo(points[j]) ==
                                points[i].slopeTo(points[l])) {

                            StdOut.print(points[i].toString());
                            StdOut.print(" -> ");
                            StdOut.print(points[j].toString());
                            StdOut.print(" -> ");
                            StdOut.print(points[k].toString());
                            StdOut.print(" -> ");
                            StdOut.println(points[l].toString());
                            points[i].drawTo(points[l]);
                        }
    }
}
