package core.pages;

import core.WebDriverService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    // Page Elements.
    @FindBy(xpath = "//*[@data-testid='UI_KIT_INPUT']")
    public WebElement emailInput;

    @FindBy(xpath = "//*[@data-testid='auth-continue-button']")
    public WebElement continueButton;

    @FindBy(xpath = "//*[@data-testid='auth-login-button']")
    public WebElement loginButton;

    @FindBy( xpath = "//*[@data-testid='UI_KIT_INPUT' and @name='password']")
    public WebElement passwordInput;

    // Driver instance.
    private WebDriver driver;

    public LoginPage() {
        this.driver = WebDriverService.getDriver();;
        PageFactory.initElements(driver, this);
    }

    @Override
    public void load() {
        driver.get(APP_URL);
        waiter(this.emailInput);
        waiter(this.continueButton);
    }

    @Override
    public void isLoaded() {
        waiter(this.emailInput);
        waiter(this.continueButton);
    }

    public HomePage loginIn(String email, String password) {
        this.emailInput.sendKeys(email);
        this.continueButton.click();
        waiter(passwordInput);
        this.passwordInput.sendKeys(password);
        this.loginButton.click();

        //        переделать не стабильно
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new HomePage();
    }
}
