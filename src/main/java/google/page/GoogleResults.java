package google.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleResults extends Base {

    private WebDriver webDriver;

    @FindBy(css = "#hdtb-msb")
    private WebElement optionsBar;

    public GoogleResults(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public GoogleImages openImages() {
        optionsBar.findElement(By.cssSelector("div[role=tab] a")).click();
        return new GoogleImages(webDriver);
    }

    //It can be done via reflection, were it get the type of the method above and internally call the method.
    @Override
    public Base navigateTo(Base base) {
        if (base instanceof GoogleImages)
            return openImages();
        throw new IllegalArgumentException("Invalid instance of " + base.getClass().getSimpleName());
    }
}
