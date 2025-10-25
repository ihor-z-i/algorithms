public class BruteForceSolver {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java BruteForceSolver <graph_file_path>");
            return;
        }
        Solver solver = new Solver(new BruteForcePathFinder());
        solver.Solve(args[0]);
    }
}
