package google.graph;

public class GraphEdge {
    private int weight;
    private GraphVertex destination;

    GraphEdge(int weight, GraphVertex destination) {
        this.weight = weight;
        this.destination = destination;
    }

    int getWeight() {
        return this.weight;
    }

    GraphVertex getDestination() {
        return destination;
    }
}
