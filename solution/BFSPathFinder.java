import java.util.HashMap;
import java.util.List;

public class BFSPathFinder {

    HashMap<Integer, Integer> computeShortestPaths(WeightedGraph graph, int startVertex) {
        HashMap<Integer, Integer> shortestPaths = new HashMap<>();
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        queue.add(startVertex);
        shortestPaths.put(startVertex, 0);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            int currentDistance = shortestPaths.get(currentVertex);

            for (Edge edge : graph.getNeighbourEdges(currentVertex)) {
                int neighbor = edge.destination;
                int newDistance = currentDistance + edge.weight;
                // Usually in BFS for graphs without weights 
                // we would mark nodes as visited and check if they were visited.
                // That won't work for weighted graphs
                // because a shortest path may be not the direct neighbor.
                // so neighbor is considered when one of the following is true:
                // a) it hasn't been reached yet (shortestPaths doesn't contain neighbor)
                // b) a shorter path is found
                if (!shortestPaths.containsKey(neighbor)
                        || newDistance < shortestPaths.get(neighbor)) {
                    shortestPaths.put(neighbor, newDistance);
                    queue.add(neighbor);
                }
            }
        }

        return shortestPaths;
    }

    public List<Integer> getDistances(WeightedGraph graph, int startVertex, List<Integer> verticesToReport) {
        HashMap<Integer, Integer> shortestPaths = computeShortestPaths(graph, startVertex);
        return verticesToReport.stream()
                .map(v -> shortestPaths.getOrDefault(v, 1000000))
                .toList();
    }
}
