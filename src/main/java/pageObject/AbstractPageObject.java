package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.IllegalPageException;

public abstract class AbstractPageObject implements PageObjectInterface {

    /** ロガー */
    private static Logger LOGGER;
    /** 画面表示が完了するまで待機する最大時間(秒) */
    private static final int MAX_WAIT_SECOND_UNTIL_DISPLAYED_COMPLETE = 5;
    /** WebDriver */
    private WebDriver driver;

    public AbstractPageObject(WebDriver driver) throws IllegalPageException {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        LOGGER =  LoggerFactory.getLogger(this.getClass());

        waitUntilDisplayed();
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

    protected static int getMaxWaitTime() {
        return MAX_WAIT_SECOND_UNTIL_DISPLAYED_COMPLETE;
    }

    public abstract boolean isDisplayed();

    public abstract void waitUntilDisplayed();
}
