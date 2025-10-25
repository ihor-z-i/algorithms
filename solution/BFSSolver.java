public class BFSSolver {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java BFSSolver <graph_file_path>");
            return;
        }
        Solver solver = new Solver(new BFSPathFinder());
        solver.Solve(args[0]);
    }
}
