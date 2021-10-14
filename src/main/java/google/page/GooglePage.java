package google.page;

import google.graph.Edge;
import google.graph.Vertex;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Vertex
public class GooglePage extends Base{
    @FindBy(css = "input[title='Pesquisar']")
    private WebElement searchInput;
    @FindBy(css = ".FPdoLc input[value='Pesquisa Google']")
    private WebElement searchButton;

    public GooglePage(WebDriver webDriver) {
        super(webDriver);
    }

    public GooglePage searchBy(String criteria) {
        searchInput.sendKeys(criteria);
        return this;
    }

    @Edge(weight = 5)
    public GoogleResults submit() {
        //This searchBy is here just for testing purposes!
        searchBy("Testing");
        try {
            searchButton.click();
        } catch (ElementClickInterceptedException ex) {
            searchInput.sendKeys(Keys.ENTER);
        }
        return new GoogleResults(webDriver);
    }
}
