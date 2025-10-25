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
            // going through the vertex again in the same path
            // might still produce a better path
            // if road around has less weight than direct edge
            // so we allow revisiting vertices
            // but going through the same edge again will always produce a cycle
            if (hasWentThroughEdge(currentVertex, vertex)) {
                return null;
            }
            return new Path(this, vertex, weight);
        }

        public int getCurrentVertex() {
            return this.currentVertex;
        }

        public boolean hasWentThroughEdge(int from, int to) {
            // Walk up the chain to check if edge exists - derive edge from vertices!
            Path current = this;
            while (current != null && current.parent != null) {
                if (current.parent.currentVertex == from && current.currentVertex == to) {
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
                    // Edge has already been traversed in this path; skip to avoid cycles
                    continue;
                }
                int newDistance = newPath.totalWeight;
                if (!shortestPaths.containsKey(neighbor)
                        || newDistance < shortestPaths.get(neighbor)) {
                    shortestPaths.put(neighbor, newDistance);

                    // keeping it inside discards paths that are obviously worse
                    // if this is moved out of the condition
                    // it uses lots of memory and never ends
                    queue.add(newPath);
                }

                // complete brute force would be achieved by
                // exploring all possible paths without pruning
                // queue.add(newPath);
            }
        }

        return shortestPaths;
    }
}
