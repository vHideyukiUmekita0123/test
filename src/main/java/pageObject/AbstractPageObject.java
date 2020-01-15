package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.IllegalPageException;

public abstract class AbstractPageObject implements PageObjectInterface {

    /** ロガー */
    private static Logger LOGGER;
    /** WebDriver */
    private WebDriver driver;

    public AbstractPageObject(WebDriver driver) throws IllegalPageException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // nothing to do.
        }

        this.driver = driver;
        PageFactory.initElements(driver, this);

        LOGGER =  LoggerFactory.getLogger(this.getClass());

        if (!isDisplayed()) {
            throw new IllegalPageException("This page is not '" + this.getClass().getSimpleName() + "'.");
        }
    }

    public Logger getLogger() {
        return LOGGER;
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public abstract boolean isDisplayed();
}
