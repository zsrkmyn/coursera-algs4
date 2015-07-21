public class Solver {

    private class SNode implements Comparable<SNode> {
        public Board board;
        public SNode prev;
        public int moves;
        private int manh;

        public SNode(Board board, int moves, SNode prev)
        {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            manh = board.manhattan();
        }

        public int priority()
        { return moves + manh; }

        public int compareTo(SNode that)
        { return priority() - that.priority(); }
    }

    private Stack<Board> solution;
    private boolean solvable;
    private int moves;

    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
        if (initial == null)
            throw new java.lang.NullPointerException();

        MinPQ<SNode> queue = new MinPQ<SNode>(),
                     tqueue = new MinPQ<SNode>();

        queue.insert(new SNode(initial, 0, null));
        tqueue.insert(new SNode(initial.twin(), 0, null));
        while (true) {
            SNode node = queue.delMin();
            if (node.board.isGoal()) {
                solvable = true;
                moves = node.moves;
                solution = new Stack<Board>();

                while(node != null) {
                    solution.push(node.board);
                    node = node.prev;
                }

                break;
            } else {
                for (Board b : node.board.neighbors()) {
                    if (node.prev == null || !b.equals(node.prev.board))
                        queue.insert(new SNode(b, node.moves + 1, node));
                }
            }

            node = tqueue.delMin();
            if(node.board.isGoal()) {
                solvable = false;
                moves = -1;
                break;
            } else {
                for (Board b : node.board.neighbors()) {
                    if (node.prev == null || !b.equals(node.prev.board))
                        tqueue.insert(new SNode(b, node.moves + 1, node));
                }
            }
        }
    }
    public boolean isSolvable()            // is the initial board solvable?
    { return solvable; }

    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    { return moves; }

    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    { return solution; }

    public static void main(String[] args) // solve a slider puzzle (given below)
    {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
