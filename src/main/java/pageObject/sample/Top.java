package pageObject.sample;

import org.openqa.selenium.WebDriver;

import exception.IllegalPageException;
import pageObject.AbstractPageObject;

public class Top extends AbstractPageObject {

    private static final String URL = "https://www.veriserve.co.jp/";
    private static final String HEADER_TITLE = "ソフトウェアテスト・第三者検証のベリサーブ";

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

    @Override
    public void waitUntilDisplayed() {
        // TODO
    }

    public static String getUrl() {
        return URL;
    }

    protected static String getHeaderTitle() {
        return HEADER_TITLE;
    }

}
