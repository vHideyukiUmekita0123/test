package pageObject.sample;

import org.openqa.selenium.WebDriver;

import exception.IllegalPageException;
import pageObject.AbstractPageObject;

public class TestJidokaSolution extends AbstractPageObject {

    private static final String PART_OF_URL = "test-automation";

    public TestJidokaSolution(WebDriver driver) throws IllegalPageException {
        super(driver);
    }

    @Override
    public boolean isDisplayed() {
        if (super.getWebDriver().getCurrentUrl().contains(PART_OF_URL)) {
            return true;
        }
        return false;
    }

    @Override
    public void waitUntilDisplayed() {
        // TODO
    }

}
