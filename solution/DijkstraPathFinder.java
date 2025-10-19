import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class DijkstraPathFinder {

    static class Node {
        int vertex;
        int distance;

        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    HashMap<Integer, Integer> computeShortestPaths(WeightedGraph graph, int startVertex) {
        HashMap<Integer, Integer> shortestPaths = new HashMap<>();
        java.util.PriorityQueue<Node> queue = new java.util.PriorityQueue<>(Comparator.comparingInt(n -> n.distance));
        queue.add(new Node(startVertex, 0));
        shortestPaths.put(startVertex, 0);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int currentDistance = shortestPaths.get(currentNode.vertex);

            for (Edge edge : graph.getNeighbourEdges(currentNode.vertex)) {
                int neighbor = edge.destination;
                int newDistance = currentDistance + edge.weight;

                if (!shortestPaths.containsKey(neighbor)
                        || newDistance < shortestPaths.get(neighbor)) {
                    shortestPaths.put(neighbor, newDistance);
                    queue.add(new Node(neighbor, newDistance));
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
