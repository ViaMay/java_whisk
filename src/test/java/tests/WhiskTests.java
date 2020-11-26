package tests;

import core.WebDriverService;
import core.pages.BasePage;
import core.pages.HomePage;
import core.pages.LoginPage;
import core.pages.ShoppingPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class WhiskTests extends BasePage {
    protected WebDriver driver;

    @Test(description = "delete shopping list test")
    public void delete_shopping_list_test() {
        String soppingListName = "test_delete";
        LoginPage loginPage = new LoginPage();
//        1. Navigate to ​https://my.whisk-dev.com/
        loginPage.isLoaded();
//        2. Sign in
        HomePage homePage = loginPage.loginIn(LOGIN, PASSWORD);
//        3. Navigate to the Shopping tab
        ShoppingPage shoppingPage = homePage.navigateTo().shoppingListButtonClick();
        shoppingPage.deleteShopByName(soppingListName);
//        4. Create a Shopping list
        shoppingPage.addNewShop(soppingListName);
//        5. Delete Shopping list
        shoppingPage.deleteShopByName(soppingListName);
//        6. Check that user doesn't have Shopping lists
        assertEquals(false, shoppingPage.listShopHaveName(soppingListName));
    }

    @DataProvider
    public Object[][] items(){
        return new Object[][]{
                {"Milk"},
                {"Bread"},
                {"Onions"},
                {"Eggs"},
                {"Potatoes"}
        };
    }

    @Test(dataProvider = "items")
    public void add_item_test(Object value) {
        String soppingListName = "test_add_items";
        LoginPage loginPage = new LoginPage();
//        1. Navigate to ​https://my.whisk-dev.com/
        loginPage.isLoaded();
//        2. Sign in
        HomePage homePage = loginPage.loginIn(LOGIN, PASSWORD);
//        3. Navigate to the Shopping tab
        ShoppingPage shoppingPage = homePage.navigateTo().shoppingListButtonClick();
        shoppingPage.deleteShopByName(soppingListName);
//        4. Create a Shopping list
        shoppingPage.addNewShop(soppingListName);
//        5. Add 5 items
        shoppingPage.selectListShopByName(soppingListName);
        shoppingPage.addItemToShopList(value.toString());
//        6. Check by Name that 5 items are added to the Shopping list (check each name like a single test)
        shoppingPage.selectListShopByName(soppingListName);
        shoppingPage.checkShopListHadItem(value.toString());
        shoppingPage.deleteShopByName(soppingListName);
    }

    @Test(description = "add 5 items to shopping list")
    public void add_five_items_test() {
        String soppingListName = "test_add_items";
        String[] addItems = new String[] {
                "Milk",
                "Bread",
                "Onions",
                "Eggs",
                "Potatoes"};

        LoginPage loginPage = new LoginPage();
//        1. Navigate to ​https://my.whisk-dev.com/
        loginPage.isLoaded();
//        2. Sign in
        HomePage homePage = loginPage.loginIn(LOGIN, PASSWORD);
//        3. Navigate to the Shopping tab
        ShoppingPage shoppingPage = homePage.navigateTo().shoppingListButtonClick();
        shoppingPage.deleteShopByName(soppingListName);
//        4. Create a Shopping list
        shoppingPage.addNewShop(soppingListName);
//        5. Add 5 items
        shoppingPage.selectListShopByName(soppingListName);
        shoppingPage.addItemToShopList(addItems[0]);
        shoppingPage.addItemToShopList(addItems[1]);
        shoppingPage.addItemToShopList(addItems[2]);
        shoppingPage.addItemToShopList(addItems[3]);
        shoppingPage.addItemToShopList(addItems[4]);
//        6. Check by Name that 5 items are added to the Shopping list (check each name like a single test)
        shoppingPage.selectListShopByName(soppingListName);
        shoppingPage.checkShopListHadItem(addItems[0]);
        shoppingPage.checkShopListHadItem(addItems[1]);
        shoppingPage.checkShopListHadItem(addItems[2]);
        shoppingPage.checkShopListHadItem(addItems[3]);
        shoppingPage.checkShopListHadItem(addItems[4]);
        shoppingPage.deleteShopByName(soppingListName);
    }

    @BeforeMethod(description = "Start the Browser", alwaysRun = true)
    public void startBrowser() {
        driver = WebDriverService.startDriver();
        driver.get(APP_URL);
    }

    @AfterMethod(description = "Stop the Browser", alwaysRun = true)
    public void stopBrowser() {
        WebDriverService.stopDriver();
    }
}