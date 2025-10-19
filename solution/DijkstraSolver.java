import java.util.List;

public class DijkstraSolver {
    private final static List<Integer> verticesToReport = List.of(7, 37, 59, 82, 99, 115, 133, 165, 188, 197);

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java DijkstraSolver <graph_file_path>");
            return;
        }
        DijkstraPathFinder pathFinder = new DijkstraPathFinder();
        WeightedGraph graph = new GraphReader().readGraph(args[0]);
        List<Integer> distances = pathFinder.getDistances(graph, 1, verticesToReport);
        String result = String.join(",", distances.stream().map(String::valueOf).toArray(String[]::new));
        System.out.println(result);
    }
}
