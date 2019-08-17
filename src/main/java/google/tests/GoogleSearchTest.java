package google.tests;

import google.manager.DriverChain;
import google.manager.IdBrowsers;
import google.manager.chrome.ChromeDriverManager;
import google.manager.gecko.GeckoDriverManager;
import google.manager.internetexplorer.InternetExplorerDriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class GoogleSearchTest {

    private static DriverChain driverChain;
    private WebDriver webDriver;

    @BeforeClass
    public static void chainSetup() {
        //Creating the chain
        driverChain = new GeckoDriverManager();
        driverChain.setNext(new InternetExplorerDriverManager());
        driverChain.setNext(new ChromeDriverManager());
    }

    @Before
    public void setup() throws Exception {
        //Asking for Internet Explorer driver.
        webDriver = driverChain.getWebDriver(IdBrowsers.IEXPLORER);
    }

    @After
    public void tearDown() {
        driverChain.quitDriver(webDriver);
    }

    @Test
    public void launchGoogleTest() {
        webDriver.get("https://www.google.com");
        assertEquals("Google", webDriver.getTitle());
    }
}
