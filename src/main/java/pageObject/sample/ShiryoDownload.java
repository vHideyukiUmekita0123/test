package pageObject.sample;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import exception.IllegalPageException;
import pageObject.AbstractPageObject;

public class ShiryoDownload extends AbstractPageObject {

    private static final String HEADER_TITLE = "資料ダウンロード";
    /** テスト自動化ソリューションボタン */
    @FindBy(xpath = "//a[contains(text(), 'テスト自動化ソリューション')]")
    private WebElement testJidokaSolution;

    public ShiryoDownload(WebDriver driver) throws IllegalPageException {
        super(driver);
    }

    @Override
    public boolean isDisplayed() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 5);
        try {
            wait.until(ExpectedConditions.titleContains(HEADER_TITLE));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    /**
     * 「テスト自動化ソリューションボタン」リンクを押下。
     */
    public void clickTestJidokaSolution() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 5);
        wait.until(ExpectedConditions.elementToBeClickable(testJidokaSolution));
        testJidokaSolution.click();
    }

}
