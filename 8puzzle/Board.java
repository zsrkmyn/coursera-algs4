import java.lang.Math;

public class Board {

    private int[] puzzle;
    private int dim;

    public Board(int[][] blocks)           // construct a board from an N-by-N array of blocks
    {                                      // (where blocks[i][j] = block in row i, column j)
        int c = 0;
        dim = blocks.length;
        puzzle = new int[dim * dim];
        for (int[] i : blocks) {
            for (int j : i)
                puzzle[c++] = j;
        }
    }

    private Board(int[] blocks, int dim)
    { puzzle = blocks; this.dim = dim; }

    public int dimension()                 // board dimension N
    { return dim; }

    public int hamming()                   // number of blocks out of place
    {
        int ret = 0;
        for (int i = 0; i < dim * dim; i++) {
            if (puzzle[i] != i + 1 && puzzle[i] != 0) {
                ret++;
            }
        }

        return ret;
    }

    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        int ret = 0;
        for (int i = 0; i < dim * dim; i++) {
            if (puzzle[i] != 0) {
                ret += Math.abs(i / dim - (puzzle[i] - 1) / dim) +
                       Math.abs(i % dim - (puzzle[i] - 1) % dim);
            }
        }

        return ret;
    }

    public boolean isGoal()                // is this board the goal board?
    { return hamming() == 0; }

    public Board twin()                    // a board that is obtained by exchanging two adjacent blocks in the same row
    {
        int[] blocks = puzzle.clone();

        for (int i = 0; i < dim * dim - 1; i += dim) {
            if (blocks[i] != 0 && blocks[i + 1] != 0) {
                swap(blocks, i, i + 1);
                return new Board(blocks, dim);
            }
        }
        return null;
    }

    public boolean equals(Object y)        // does this board equal y?
    {
        if (y == this)
            return true;
        if (y == null)
            return false;
        if (y.getClass() != this.getClass())
            return false;

        Board that = (Board) y;

        for (int i = 0; i < dim * dim; i++) {
            if (puzzle[i] != that.puzzle[i])
                return false;
        }

        return true;
    }

    public Iterable<Board> neighbors()     // all neighboring boards
    {
        Queue<Board> ret = new Queue<Board>();

        for (int i = 0; i < dim * dim; i++) {
            if (puzzle[i] == 0) {
                if (i >= dim) {
                    ret.enqueue(neighbor(i, i - dim));
                }

                if (i < dim * (dim - 1)) {
                    ret.enqueue(neighbor(i, i + dim));
                }

                if (i % dim != 0) {
                    ret.enqueue(neighbor(i, i - 1));
                }

                if (i % dim != dim - 1) {
                    ret.enqueue(neighbor(i, i + 1));
                }
                break;
            }
        }

        return ret;
    }

    public String toString()               // string representation of this board (in the output format specified below)
    {
        StringBuilder s = new StringBuilder();
        s.append(dim + "\n");
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                s.append(String.format("%2d ", puzzle[i * dim + j]));
            }
            s.append("\n");
        }

        return s.toString();
    }

    private static final void swap(int[] arr, int i, int j)
    {
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    private Board neighbor(int i, int j)
    {
        int[] blocks = puzzle.clone();
        swap(blocks, i, j);
        return new Board(blocks, dim);
    }

    public static void main(String[] args) // unit tests (not graded)
    {}
}
