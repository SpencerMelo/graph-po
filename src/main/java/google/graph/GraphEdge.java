package google.graph;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GraphEdge {
    private long weight;
    private GraphVertex destination;

    GraphEdge(int weight, GraphVertex destination) {
        this.weight = weight;
        this.destination = destination;
    }

    @JsonProperty("Weight")
    public long getWeight() {
        return this.weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    @JsonProperty("Destination")
    public GraphVertex getDestination() {
        return destination;
    }

    public void setDestination(GraphVertex destination) {
        this.destination = destination;
    }
}
