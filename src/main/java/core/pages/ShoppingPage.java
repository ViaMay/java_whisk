package core.pages;

import core.WebDriverService;
import core.modules.Navigation;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ShoppingPage extends BasePage {
    // Web Elements.
    @FindBy( xpath = "//*[@data-testid='create-new-shopping-list-button']")
    public WebElement addShoppingListButton;

    @FindBy( xpath = "//*[@data-testid='UI_KIT_INPUT' and @name='name']")
    public WebElement newShoppingListInput;

    @FindBy( xpath = "//*[@data-testid='create-new-shopping-list-create-button']")
    public WebElement createShoppingListButton;

    @FindAll({@FindBy(xpath = "//*[@data-testid='shopping-lists-list-name']")})
    public List<WebElement> shoppingList;

    @FindAll({@FindBy(xpath = "//*[./*[./*[@data-testid='shopping-lists-list-name']]]//button")})
    public List<WebElement> shoppingMenuButtons;

    @FindBy( xpath = "//*[@data-testid='shopping-list-delete-menu-button']")
    public WebElement deleteMenuButton;

    @FindBy( xpath = "//*[@data-testid='confirm-delete-button']")
    public WebElement confirmDeleteButton;

    @FindBy( xpath = "//*[@data-testid='desktop-add-item-autocomplete']")
    public WebElement addItemAutocomplete;

    @FindAll({@FindBy(xpath = "shopping-list-item-name")})
    public List<WebElement> shoppingListItemName;

    private WebDriver driver;

    public ShoppingPage() {
        this.driver = WebDriverService.getDriver();
        PageFactory.initElements(driver, this);
    }

    @Override
    public void load() {
        driver.get(APP_URL + "/shopping-list/");
        waiter(this.addShoppingListButton);
    }

    @Override
    protected void isLoaded() {
        waiter(this.addShoppingListButton);
    }


    public void addNewShop(String name) {
        this.addShoppingListButton.click();
        waiter(this.newShoppingListInput);
        waiter(this.createShoppingListButton);
        newShoppingListInput.sendKeys(Keys.CONTROL + "A");
        newShoppingListInput.sendKeys(Keys.BACK_SPACE);
        this.newShoppingListInput.sendKeys(name);
        this.createShoppingListButton.click();
        waiter(this.addShoppingListButton);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selectListShopByName(String name) {
        for (int i = 0; i < this.shoppingList.size(); i=i+1) {
            if (this.shoppingList.get(i).getText().equals(name))
                this.shoppingList.get(i).click();
            break;
        }
    }

    public void addItemToShopList(String name) {
        waiter(this.addItemAutocomplete);
        this.addItemAutocomplete.click();
        WebElement item = driver.findElement(By.xpath("//*[@data-testid='autocomplete-item']//*[text()='" + name + "']"));
        waiter(item);
        item.click();
    }

    public void checkShopListHadItem(String name) {
        waiter(this.addItemAutocomplete);
        WebElement item = driver.findElement(By.xpath("//*[@data-testid='shopping-list-item-name' and text()='" + name + "']"));
        waiter(item);
    }

    public void deleteShopByName(String name) {
        for (int i = 0; i < this.shoppingList.size(); i=i+1) {
            if (this.shoppingList.get(i).getText().equals(name)) {
                waiter(this.shoppingMenuButtons.get(i));
                this.shoppingMenuButtons.get(i).click();
                waiter(this.deleteMenuButton);
                this.deleteMenuButton.click();
                waiter(this.confirmDeleteButton);
                confirmDeleteButton.click();
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean listShopHaveName(String name) {
        for (int i = 0; i < this.shoppingList.size(); i=i+1) {
            if (this.shoppingList.get(i).getText().equals(name))
                return true;
        }
        return false;
    }

    public Navigation navigateTo() {
        return new Navigation();
    }
}
