import java.util.Comparator;
import java.util.HashMap;

public class DijkstraPathFinder implements PathFinder {

    static class Node {
        int vertex;
        int distance;

        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    public HashMap<Integer, Integer> computeShortestPaths(WeightedGraph graph, int startVertex) {
        HashMap<Integer, Integer> shortestPaths = new HashMap<>();
        java.util.PriorityQueue<Node> queue = new java.util.PriorityQueue<>(Comparator.comparingInt(n -> n.distance));
        queue.add(new Node(startVertex, 0));
        shortestPaths.put(startVertex, 0);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            // Skip node if it was already processed with a shorter distance
            if (currentNode.distance > shortestPaths.get(currentNode.vertex)) {
                continue;
            }
            
            int currentDistance = shortestPaths.get(currentNode.vertex);

            for (Edge edge : graph.getNeighbourEdges(currentNode.vertex)) {
                int neighbor = edge.destination;
                int newDistance = currentDistance + edge.weight;

                if (!shortestPaths.containsKey(neighbor)
                        || newDistance < shortestPaths.get(neighbor)) {
                    shortestPaths.put(neighbor, newDistance);
                    // neighbor might already be in the queue with a longer distance
                    // what can be done about that?
                    // a) use other data structure with decrease-key support
                    // b) Remove the old node - might cause queue rebalancing so might be inefficient
                    // c) Simply add the new node; the old one will be ignored when processed
                    //   - proof of correctness for c): path consists of prior path and future path                   
                    //   - since the vertex is the same the future optimal path will be the same for old and new nodes
                    //   - therefore the old node with the longer prior path will always be suboptimal and can be ignored
                    queue.add(new Node(neighbor, newDistance));
                }
            }
        }

        return shortestPaths;
    }
}
