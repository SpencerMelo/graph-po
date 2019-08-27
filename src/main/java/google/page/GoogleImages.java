package google.page;

import google.graph.Edge;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleImages extends Base {
    private WebDriver webDriver;

    @FindBy(css = "#hdtb-msb")
    private WebElement optionsBar;

    public GoogleImages(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @Edge(weight = 50)
    public GoogleResults openResults() {
        optionsBar.findElement(By.cssSelector("div[role=tab] a")).click();
        return new GoogleResults(webDriver);
    }

    @Override
    public WebDriver getWebDriver() {
        return this.webDriver;
    }
}
