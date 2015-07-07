public class Percolation {

    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;
    private boolean[] opened;
    private int size;
    private int vTop;
    private int vBottom;

    public Percolation(int N)
    {
        if (N <= 0)
            throw new IllegalArgumentException(N + "");

        uf = new WeightedQuickUnionUF(N * N + 2);
        uf2 = new WeightedQuickUnionUF(N * N + 1);
        opened = new boolean[N * N + 2];
        size = N;
        vBottom = N * N + 1;
        vTop = N * N;
        opened[vBottom] = true;
        opened[vTop] = true;
    }

    public void open(int i, int j)
    {
        validate(i, j);
        opened[index(i, j)] = true;
        if (j > 1) {
            if (isOpen(i, j - 1)) {
                uf.union(index(i, j), index(i, j - 1));
                uf2.union(index(i, j), index(i, j - 1));
            }
        }

        if (j < size) {
            if (isOpen(i, j + 1)) {
                uf.union(index(i, j), index(i, j + 1));
                uf2.union(index(i, j), index(i, j + 1));
            }
        }

        if (i > 1) {
            if (isOpen(i - 1, j)) {
                uf.union(index(i, j), index(i - 1, j));
                uf2.union(index(i, j), index(i - 1, j));
            }
        } else {
            uf.union(vTop, index(i, j));
            uf2.union(vTop, index(i, j));
        }

        if (i < size) {
            if (isOpen(i + 1, j)) {
                uf.union(index(i, j), index(i + 1, j));
                uf2.union(index(i, j), index(i + 1, j));
            }
        } else {
            uf.union(vBottom, index(i, j));
        }

    }

    public boolean isOpen(int i, int j)
    {
        validate(i, j);
        return opened[index(i, j)];
    }

    public boolean isFull(int i, int j)
    {
        validate(i, j);
        if (!isOpen(i, j))
            return false;

        return uf2.connected(index(i, j), vTop);
    }

    public boolean percolates()
    {
        return uf.connected(vTop, vBottom);
    }

    private int index(int i, int j)
    { return (i - 1) * size + j - 1; }

    private void validate(int i, int j)
    {
        if (i <= 0 || i > size || j <= 0 || j > size)
            throw new IndexOutOfBoundsException();
    }

    public static void main(String[] args)
    {
        //System.out.print(p.uf.find(n*n) + " ");
        //System.out.print(p.uf.find(n*n+1) + " ");
        //System.out.print('\n');
        //for (i = 1; i <= n; i++) {
        //    for (j = 1; j <= n; j++){
        //        System.out.print(p.opened[p.index(i, j)] + " ");
        //    }
        //    System.out.print('\n');
        //}
        //System.out.print(p.opened[n*n] + " ");
        //System.out.print(p.opened[n*n+1] + " ");
        //System.out.print("\n\n");
    }
}
