package core.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;

public class BasePage extends LoadableComponent<BasePage> {
    public static final String APP_URL = "https://my.whisk-dev.com/" ;
    public static final String ID_TEST = "data-testid" ;
    public static final String LOGIN = "vi-113@yandex.ru" ;
    public static final String PASSWORD = "!qW963852" ;
    public static final int WAIT_FOR_POST_UPDATE = 20;

    protected void load() {
    }

    protected void isLoaded() throws Error {
    }

    protected void waiter(WebElement element){
        int wait = 0;
        while (wait <= WAIT_FOR_POST_UPDATE && !element.isEnabled()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wait = wait + 1;
        }
    }
}
