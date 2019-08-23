package google.page;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GooglePage extends Base{
    private WebDriver webDriver;

    @FindBy(css = "input[title='Pesquisar']")
    private WebElement searchInput;
    @FindBy(css = ".FPdoLc input[value='Pesquisa Google']")
    private WebElement searchButton;

    public GooglePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public GooglePage searchBy(String criteria) {
        searchInput.sendKeys(criteria);
        return this;
    }

    public GoogleResults submit() {
        try {
            searchButton.click();
        } catch (ElementClickInterceptedException ex) {
            searchInput.sendKeys(Keys.ENTER);
        }
        return new GoogleResults(webDriver);
    }

    @Override
    public GoogleResults navigateTo(Base base) {
        //hardcoded stuff for testing.
        return searchBy("Testing").submit();
    }
}
