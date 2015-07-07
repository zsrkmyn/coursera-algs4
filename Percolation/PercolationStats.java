public class PercolationStats {

    private double prmean;
    private double prstddev;
    private int prtimes;

    public PercolationStats(int N, int T)
    {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException();

        int t, count;
        double[] counts = new double[T];
        Percolation pl;

        int[] pos = new int[N * N];
        for (t = 0; t < N * N; t++) {
            pos[t] = t;
        }

        prtimes = T;

        for (t = 0; t < T; t++) {
            pl = new Percolation(N);
            count = 0;
            while (!pl.percolates()) {
                int tmp = StdRandom.uniform(N * N - count);
                int x = pos[tmp] % N;
                int y = pos[tmp] / N;
                swap(pos, tmp, N * N - 1 - count);
                pl.open(x + 1, y + 1);
                count++;
            }

            counts[t] = (double) count / (N * N);
        }

        prmean = StdStats.mean(counts);
        prstddev = StdStats.stddev(counts);
    }

    public double mean()
    { return prmean; }

    public double stddev()
    { return prstddev; }

    public double confidenceLo()
    { return prmean - 1.96 * prstddev / Math.sqrt(prtimes); }

    public double confidenceHi()
    { return prmean + 1.96 * prstddev / Math.sqrt(prtimes); }

    private static void swap(int[] arr, int x, int y)
    {
        int tmp;
        tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    public static void main(String[] args)
    {
        PercolationStats ps =
            new PercolationStats(Integer.parseInt(args[0]),
                                 Integer.parseInt(args[1]));
        StdOut.println(ps.mean());
        StdOut.println(ps.stddev());
        StdOut.println(ps.confidenceLo());
        StdOut.println(ps.confidenceHi());
    }
}
