package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public final class WebDriverService {
    private static WebDriver driver;

    private WebDriverService() {
    }

    // Driver executable files directory.
    private static final String DRIVERS_DIR = System.getProperty("user.dir") + File.separator + "src" +  File.separator + "drivers";

    public static WebDriver startDriver() {
        if (driver != null) {
            return driver;
        }
        System.setProperty("webdriver.chrome.driver",
                    DRIVERS_DIR + File.separator + "chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60L, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.manage().deleteAllCookies();
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void stopDriver() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.close();
            driver.quit();
            driver = null;
        }

    }
}
