import java.util.HashMap;

public class BruteForcePathFinder implements PathFinder {

    private static class Path {
        private final Path parent;
        private final int currentVertex;
        private final int totalWeight;

        Path(int startVertex) {
            this.parent = null;
            this.currentVertex = startVertex;
            this.totalWeight = 0;
        }

        private Path(Path parent, int vertex, int weight) {
            this.parent = parent;
            this.currentVertex = vertex;
            this.totalWeight = parent.totalWeight + weight;
        }

        public Path tryExtend(int vertex, int weight) {
            // going through the vertex again in the same path produces a cycle
            // we don't allow cycles
            if (hasWentThroughVertex(vertex)) {
                return null;
            }
            return new Path(this, vertex, weight);
        }

        public int getCurrentVertex() {
            return this.currentVertex;
        }

        public boolean hasWentThroughVertex(int vertex) {
            Path current = this;
            while (current != null) {
                if (current.currentVertex == vertex) {
                    return true;
                }
                current = current.parent;
            }
            return false;
        }
    }

    public HashMap<Integer, Integer> computeShortestPaths(WeightedGraph graph, int startVertex) {
        HashMap<Integer, Integer> shortestPaths = new HashMap<>();
        java.util.Queue<Path> queue = new java.util.LinkedList<>();
        queue.add(new Path(startVertex));
        shortestPaths.put(startVertex, 0);

        while (!queue.isEmpty()) {
            Path currentPath = queue.poll();
            int currentVertex = currentPath.getCurrentVertex();

            for (Edge edge : graph.getNeighbourEdges(currentVertex)) {
                int neighbor = edge.destination;

                var newPath = currentPath.tryExtend(neighbor, edge.weight);
                if (newPath == null) {
                    // A vertex has been traversed in this path; skip to avoid cycles
                    continue;
                }
                int newDistance = newPath.totalWeight;
                if (!shortestPaths.containsKey(neighbor)
                        || newDistance < shortestPaths.get(neighbor)) {
                    shortestPaths.put(neighbor, newDistance);

                    // keeping it inside if discards paths that are obviously worse
                    queue.add(newPath);
                }

                // complete brute force is achieved by
                // exploring all possible paths without pruning
                // queue.add(newPath);
            }
        }

        return shortestPaths;
    }
}
