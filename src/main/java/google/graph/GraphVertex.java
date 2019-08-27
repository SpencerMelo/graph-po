package google.graph;

import google.page.Base;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphVertex {
    private Base pageObject;
    private Set<GraphEdge> graphEdges;

    public GraphVertex(Base pageObject) {
        this.pageObject = pageObject;
        graphEdges = new HashSet<>();
    }

    public String getLabel() {
        return this.pageObject.getClass().getSimpleName();
    }

    List<GraphEdge> getGraphEdges() {
        return new ArrayList<>(graphEdges);
    }

    public int getNeighborDistance(GraphVertex v) {
        for (GraphEdge graphEdge : getGraphEdges()) {
            if (graphEdge.getDestination().equals(v)) {
                return graphEdge.getWeight();
            }
        }
        throw new IllegalArgumentException("No neighbor found.");
    }

    long goTo(GraphVertex v) {
        //On the current component or page object you will 'navigate' to the next one, example: A to B.
        for (GraphEdge graphEdge : getGraphEdges()) {
            if (graphEdge.getDestination().getClass().equals(v.getClass())) {
                long startingTime = System.currentTimeMillis();
                invokeMethod(v);
                long endingTime = System.currentTimeMillis();
                return endingTime - startingTime;
            }
        }
        return -1;
    }

    GraphVertex getEdgeTo(String targetPage) {
        for (GraphEdge graphEdge : getGraphEdges()) {
            if (graphEdge.getDestination().getLabel().equals(targetPage)) {
                return graphEdge.getDestination();
            }
        }
        throw new IllegalArgumentException("No neighbor found.");
    }

    void mapEdges() {
        String pageObjectType = pageObject.getClass().getName();

        try {
            for (Method method : Class.forName(pageObjectType).getDeclaredMethods()) {
                Edge edge = method.getAnnotation(Edge.class);
                if (edge != null) {
                    Base vertex = (Base) method
                            .getReturnType()
                            .getConstructor(WebDriver.class)
                            .newInstance(pageObject.getWebDriver());

                    graphEdges.add(new GraphEdge(edge.weight(), new GraphVertex(vertex)));
                }
            }
        } catch (Exception e) {
            System.out.println("Error while mapping edges from page object.");
        }
    }

    private void invokeMethod(GraphVertex graphVertex) {
        String pageObjectType = pageObject.getClass().getName();

        try {
            for (Method method : Class.forName(pageObjectType).getDeclaredMethods()) {
                Edge edge = method.getAnnotation(Edge.class);
                if (edge != null) {
                    Class<?> vertex = method.getReturnType();
                    if (vertex.getName().equals(graphVertex.pageObject.getClass().getName())) {
                        method.invoke(pageObject);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error navigating through edges from page object.");
        }
    }
}
