package pageObject.sample;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import exception.AutomatedTestException;
import exception.IllegalPageException;
import pageObject.AbstractPageObject;

public class TestJidokaSolution extends AbstractPageObject {

    public TestJidokaSolution(WebDriver driver) throws IllegalPageException {
        super(driver);
    }

    @Override
    public boolean isDisplayed() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 5);
        try {
            wait.until(ExpectedConditions.urlMatches(".*test-automation.*"));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public void downloadShiryo() throws AutomatedTestException {
        getWebDriver().get(getWebDriver().getCurrentUrl());
    }
}
