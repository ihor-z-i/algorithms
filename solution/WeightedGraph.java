import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedGraph {
    private Map<Integer, List<Edge>> vertices = new HashMap<>();

    public void addEdge(int source, int destination, int weight) {
        vertices.putIfAbsent(source, new java.util.ArrayList<>());
        vertices.get(source).add(new Edge(destination, weight));
    }

    public List<Edge> getNeighbourEdges(int source) {
        return vertices.getOrDefault(source, java.util.Collections.emptyList());
    }    
}