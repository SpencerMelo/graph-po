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
    public GoogleResults(WebDriver webDriver) {
        super(webDriver);
    }

    @Edge(weight = 50)
    public GoogleImages openImages() {
        webDriver.findElement(By.xpath("//*[text()='Imagens']")).click();
        return new GoogleImages(webDriver);
    }
}
