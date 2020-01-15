package pageObject.sample;

import org.openqa.selenium.WebDriver;

import exception.IllegalPageException;
import pageObject.AbstractPageObject;

public class Top extends AbstractPageObject {

    private static final String HEADER_TITLE = "株式会社ベリサーブ";

    public Top(WebDriver driver) throws IllegalPageException {
        super(driver);
    }

    @Override
    public boolean isDisplayed() {
        if (super.getWebDriver().getTitle().equals(HEADER_TITLE)) {
            return true;
        }
        return false;
    }

}
