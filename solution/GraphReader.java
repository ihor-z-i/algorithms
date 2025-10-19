import java.io.BufferedReader;
import java.io.FileReader;

public class GraphReader {

    public WeightedGraph readGraph(String filename) {
        WeightedGraph graph = new WeightedGraph();
        // Read the file line by line
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");

                if (parts.length < 1) {
                    continue; // Skip invalid lines
                }

                int source = Integer.parseInt(parts[0]);

                for (int i = 1; i < parts.length; i++) {
                    String[] edgeParts = parts[i].split(",");
                    int destination = Integer.parseInt(edgeParts[0]);
                    int weight = Integer.parseInt(edgeParts[1]);
                    graph.addEdge(source, destination, weight);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return graph;
    }
}
