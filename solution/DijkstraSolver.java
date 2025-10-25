public class DijkstraSolver {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java DijkstraSolver <graph_file_path>");
            return;
        }
        Solver solver = new Solver(new DijkstraPathFinder());
        solver.Solve(args[0]);
    }
}
