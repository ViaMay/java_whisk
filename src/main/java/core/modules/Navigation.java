package core.modules;

import core.WebDriverService;
import core.pages.BasePage;
import core.pages.HomePage;
import core.pages.ShoppingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Navigation extends BasePage {
    // Web Elements.
    @FindBy(xpath = "//*[@data-testid='shopping-list-nav-link']")
    private WebElement shoppingListButton;

    @FindBy(xpath = "//*[@data-testid='home-nav-link']")
    private WebElement homeButton;
    // Driver instance.
    private WebDriver driver;

    public Navigation() {
        this.driver = WebDriverService.getDriver();
        PageFactory.initElements(driver, this);
    }

    public ShoppingPage shoppingListButtonClick() {
        waiter(this.shoppingListButton);
        this.shoppingListButton.click();
        return new ShoppingPage();
    }

    public HomePage homeButtonClick() {
        waiter(this.homeButton);
        this.homeButton.click();
        return new HomePage();
    }
}
