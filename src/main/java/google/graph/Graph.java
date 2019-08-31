package google.graph;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class Graph {
    private Set<GraphVertex> vertices;

    public Graph() {
        vertices = new HashSet<>();
    }

    @JsonProperty("Graph")
    public Set<GraphVertex> getVertices(){
        return vertices;
    }

    public void addVertex(GraphVertex graphVertex) {
        graphVertex.mapEdges();
        vertices.add(graphVertex);
    }

    public GraphVertex getVertex(GraphVertex graphVertex) {
        for (GraphVertex vertex : vertices) {
            if (vertex == graphVertex) {
                return vertex;
            }
        }
        return null;
    }

    // Perform BFS -- CAN BE OPTIMIZED!!!
    public LinkedList<GraphVertex> bfs(String source, String dest) {
        Map<String, Long> dist = new HashMap<>();
        Map<String, GraphVertex> prev = new HashMap<>();
        Map<String, GraphVertex> queue = new HashMap<>();

        for (GraphVertex graphVertex : vertices) {
            dist.put(graphVertex.getLabel(), Long.MAX_VALUE);
            queue.put(graphVertex.getLabel(), graphVertex);
        }
        dist.put(source, 0L);

        //While we the queue is not empty, continue the search.
        while (!queue.isEmpty()) {
            Map<String, Long> altDist = new HashMap<>(dist);
            altDist.keySet().retainAll(queue.keySet());

            Entry<String, Long> min = Collections.min(altDist.entrySet(), Comparator.comparing(Entry::getValue));
            GraphVertex current = queue.get(min.getKey());
            queue.remove(min.getKey());

            //If the destiny were found, return the shortest path list.
            if (current.getLabel().equals(dest)) {
                LinkedList<GraphVertex> shortestDistance = new LinkedList<>();
                String target = dest;
                while (prev.get(target) != null) {
                    shortestDistance.add(0, prev.get(target));
                    target = prev.get(target).getLabel();
                }
                return shortestDistance;
            }

            for (GraphEdge graphEdge : current.getGraphEdges()) {
                long alt = dist.get(current.getLabel()) + graphEdge.getWeight();

                if (alt < dist.get(graphEdge.getDestination().getLabel())) {
                    dist.put(graphEdge.getDestination().getLabel(), alt);
                    prev.put(graphEdge.getDestination().getLabel(), current);
                }
            }
        }
        return null;
    }

    public void goTo(String currentPage, String targetPage) {
        LinkedList<GraphVertex> shortestPath = bfs(currentPage, targetPage);

        //Adding the target to the end of the graph (Need to modify BFS to return it, it will be easier).
        shortestPath.add(shortestPath.get(shortestPath.size() - 1).getVertexTo(targetPage));

        //From the current vertex, go to the next one of the list.
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            GraphVertex currentGraphVertex = shortestPath.get(i);
            GraphVertex nextGraphVertex = shortestPath.get(i + 1);

            long edgeWeight = currentGraphVertex.goTo(nextGraphVertex);
            updateEdgesWeight(currentGraphVertex, nextGraphVertex, edgeWeight);
        }

        try {
            saveChanges();
        } catch (IOException ioe) {
            System.out.println("Failed to save");
        }
    }

    private void updateEdgesWeight(GraphVertex source, GraphVertex target, long weight){
        System.out.println("(" + source.getLabel() + ") --" + weight + "ms--> (" + target.getLabel() + ")");
        getVertex(source).getEdgeTo(target.getLabel()).setWeight(weight);
    }

    private void saveChanges() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        System.out.println(json);
        //update the source and target weight on the json file.
    }
}
