package google.tests;

import google.graph.Edge;
import google.graph.Graph;
import google.graph.Vertex;
import google.manager.DriverChain;
import google.manager.IdBrowsers;
import google.manager.chrome.ChromeDriverManager;
import google.manager.gecko.GeckoDriverManager;
import google.manager.internetexplorer.InternetExplorerDriverManager;
import google.page.GoogleImages;
import google.page.GooglePage;
import google.page.GoogleResults;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;

import java.util.LinkedList;

public class GoogleSearchTest {

    private static DriverChain driverChain;
    private WebDriver webDriver;

    @BeforeAll
    public static void chainSetup() {
        //Building a chain of drivers, in case you want to use all :D
        driverChain = new GeckoDriverManager();
        driverChain.setNext(new InternetExplorerDriverManager());
        driverChain.setNext(new ChromeDriverManager());
    }

    @BeforeEach
    public void setup() throws Exception {
        //Getting just chrome.
        webDriver = driverChain.getWebDriver(IdBrowsers.CHROME);
    }

    @AfterEach
    public void tearDown() {
        driverChain.quitDriver(webDriver);
    }

    @Test
    public void launchGoogleTest() throws InterruptedException {
        //Creating the Graph stuff.
        Vertex googlePage = new Vertex(new GooglePage(webDriver));
        Vertex googleResults = new Vertex(new GoogleResults(webDriver));
        Vertex googleImages = new Vertex(new GoogleImages(webDriver));

        googlePage.addEdge(new Edge(25, googleResults));
        googlePage.addEdge(new Edge(150, googleImages));

        googleResults.addEdge(new Edge(25, googlePage));
        googleResults.addEdge(new Edge(50, googleImages));

        googleImages.addEdge(new Edge(50, googleResults));
        googleImages.addEdge(new Edge(150, googlePage));

        Graph pageObjectGraph = new Graph();
        pageObjectGraph.addVertex(googlePage);
        pageObjectGraph.addVertex(googleResults);
        pageObjectGraph.addVertex(googleImages);

        //Testing it...
        webDriver.get("https://www.google.com");

        String currentPage = "GooglePage";
        String targetPage = "GoogleImages";

        LinkedList<Vertex> shortestPath = pageObjectGraph.BFS(currentPage, targetPage); //Weight will be 75, longest path will be 150+.
        pageObjectGraph.goTo(currentPage, targetPage);

        System.out.print("Shortest path: ");
        for (Vertex v : shortestPath) {
            System.out.print("(" + v.getLabel() + ") --> ");
        }
        System.out.println("(" + targetPage + ")");
    }
}
