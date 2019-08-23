package google.graph;

import java.util.*;
import java.util.Map.Entry;

public class Graph {
    private Set<Vertex> vertices;

    public Graph() {
        vertices = new HashSet<>();
    }

    public List<Vertex> getVertices() {
        return new ArrayList<>(vertices);
    }

    public boolean addVertex(Vertex vertex) {
        return vertices.add(vertex);
    }

    // Perform BFS -- CAN BE OPTIMIZED!!!
    public LinkedList<Vertex> BFS(String source, String dest) {
        Map<String, Integer> dist = new HashMap<>();
        Map<String, Vertex> prev = new HashMap<>();
        LinkedList<Vertex> shortestDistance = new LinkedList<>();
        Map<String, Vertex> queue = new HashMap();

        for (Vertex vertex : vertices) {
            dist.put(vertex.getLabel(), Integer.MAX_VALUE);
            queue.put(vertex.getLabel(), vertex);
        }
        dist.put(source, 0);

        while (!queue.isEmpty()) {
            Map<String, Integer> altDist = new HashMap<>(dist);
            altDist.keySet().retainAll(queue.keySet());

            Entry<String, Integer> min = Collections.min(altDist.entrySet(), Comparator.comparing(Entry::getValue));
            Vertex curr = queue.get(min.getKey());
            queue.remove(min.getKey());

            if (curr.getLabel().equals(dest)) {
                String target = dest;

                while (prev.get(target) != null) {
                    shortestDistance.add(0, prev.get(target));
                    target = prev.get(target).getLabel();
                }
            }

            for (Edge edge : curr.getEdges()) {
                int alt = dist.get(curr.getLabel()) + edge.getWeight();

                if (alt < dist.get(edge.getDestination().getLabel())) {
                    dist.put(edge.getDestination().getLabel(), alt);
                    prev.put(edge.getDestination().getLabel(), curr);
                }
            }
        }
        return shortestDistance;
    }

    public void printGraph() {
        for (Vertex vertex : vertices) {
            for (Edge edge : vertex.getEdges()) {
                System.out.println(vertex.getLabel()
                        + " --> " + edge.getDestination().getLabel()
                        + " with weight: " + edge.getWeight());
            }
        }
    }

    public void goTo(String currentPage, String targetPage) {
        LinkedList<Vertex> shortestPath = BFS(currentPage, targetPage);

        //Adding the target to the end of the graph (Need to modify BFS to return it, it will be easier).
        shortestPath.add(shortestPath.get(shortestPath.size() - 1).getEdgeTo(targetPage));

        //From the current vertex, go to the next one of the list.
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            Vertex currentVertex = shortestPath.get(i);
            currentVertex.goTo(shortestPath.get(i + 1));
        }
    }
}
