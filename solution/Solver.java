import java.util.List;

public class Solver {
    private final static List<Integer> verticesToReport = List.of(7, 37, 59, 82, 99, 115, 133, 165, 188, 197);
    private PathFinder pathFinder;

    public Solver(PathFinder pathFinder) {
        this.pathFinder = pathFinder;
    }

    public void Solve(String fileName) {
        WeightedGraph graph = new GraphReader().readGraph(fileName);
        var shortestPaths = pathFinder.computeShortestPaths(graph, 1);
        List<Integer> distances = verticesToReport.stream().map(v -> shortestPaths.getOrDefault(v, 1000000)).toList();
        String result = String.join(",", distances.stream().map(String::valueOf).toArray(String[]::new));
        System.out.println(result);
    }
}
