package google.test;

import google.graph.Graph;
import google.graph.GraphVertex;
import google.manager.DriverChain;
import google.manager.IdBrowsers;
import google.manager.gecko.GeckoDriverManager;
import google.page.GoogleImages;
import google.page.GooglePage;
import google.page.GoogleResults;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;

public class GoogleSearchTest {

    private static DriverChain driverChain;
    private WebDriver webDriver;

    @BeforeAll
    public static void chainSetup() {
        driverChain = new GeckoDriverManager();
    }

    @BeforeEach
    public void setup() throws Exception {
        webDriver = driverChain.getWebDriver(IdBrowsers.FIREFOX);
    }

    @Test
    public void launchGoogleTest() {
        GraphVertex googlePage = new GraphVertex(new GooglePage(webDriver));
        GraphVertex googleResults = new GraphVertex(new GoogleResults(webDriver));
        GraphVertex googleImages = new GraphVertex(new GoogleImages(webDriver));

        Graph pageObjectGraph = new Graph();

        pageObjectGraph.addVertex(googlePage);
        pageObjectGraph.addVertex(googleResults);
        pageObjectGraph.addVertex(googleImages);

        //Testing it...
        webDriver.get("https://www.google.com");

        String currentPage = "GooglePage";
        String targetPage = "GoogleImages";

        pageObjectGraph.goTo(currentPage, targetPage);

        currentPage = "GoogleImages";
        targetPage = "GoogleResults";

        pageObjectGraph.goTo(currentPage, targetPage);
    }

    private void printShortestPath(Graph graph, String start, String end) {
        for (GraphVertex v : graph.bfs(start, end))
            System.out.print("(" + v.getLabel() + ") --> ");
        System.out.println("(" + end + ")");
    }

    @AfterEach
    public void tearDown() {
        driverChain.quitDriver(webDriver);
    }
}
