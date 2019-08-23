package google.graph;

import google.page.Base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Vertex {
    private Base pageObject;
    private Set<Edge> edges;

    public Vertex(Base pageObject) {
        this.pageObject = pageObject;
        edges = new HashSet<>();
    }

    public Base getPageObject() {
        return this.pageObject;
    }

    public String getLabel() {
        return this.pageObject.getClass().getSimpleName();
    }

    public boolean addEdge(Edge edge) {
        return edges.add(edge);
    }

    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }

    public int getNeighborDistance(Vertex v) {
        for (Edge edge : getEdges()) {
            if (edge.getDestination().equals(v)) {
                return edge.getWeight();
            }
        }
        throw new IllegalArgumentException("No neighbor found.");
    }

    public void goTo(Vertex v) {
        //On the current page object you will navigate to the next one. A to B.
        for (Edge edge : getEdges()) {
            if (edge.getDestination().equals(v)) {
                getPageObject().navigateTo(edge.getDestination().getPageObject());
            }
        }
    }

    public Vertex getEdgeTo(String targetPage) {
        for (Edge edge : getEdges()) {
            if (edge.getDestination().getLabel().equals(targetPage)) {
                return edge.getDestination();
            }
        }
        throw new IllegalArgumentException("No neighbor found.");
    }
}
