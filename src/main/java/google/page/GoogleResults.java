package google.page;

import google.graph.Edge;
import google.graph.Vertex;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Vertex
public class GoogleResults extends Base {
    private WebDriver webDriver;

    @FindBy(css = "#hdtb-msb")
    private WebElement optionsBar;

    public GoogleResults(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @Edge(weight = 50)
    public GoogleImages openImages() {
        optionsBar.findElement(By.cssSelector("div[role=tab] a")).click();
        return new GoogleImages(webDriver);
    }

    public WebDriver getWebDriver() {
        return this.webDriver;
    }
}
