import java.util.HashMap;

public interface PathFinder {
    HashMap<Integer, Integer> computeShortestPaths(WeightedGraph graph, int startVertex);
}