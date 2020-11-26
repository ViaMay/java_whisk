package core.pages;

import core.WebDriverService;
import core.modules.Navigation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    // Page Elements.
    @FindBy(xpath = "//*[@data-testid='home-fab-button']")
    public WebElement addButton;

    // WebDriver instance.
    private WebDriver driver;

    public HomePage() {
        this.driver = WebDriverService.getDriver();
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void load() {
        driver.get(APP_URL);
        waiter(this.addButton);
    }

    @Override
    protected void isLoaded() {
        waiter(this.addButton);
    }

    public Navigation navigateTo() {
        return new Navigation();
    }
}
