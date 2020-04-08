package pageObject.sample;

import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import exception.IllegalPageException;
import pageObject.AbstractPageObject;
import utilities.ScrollWIndow;

public class ShiryoDownload extends AbstractPageObject {

    private static final String HEADER_TITLE = "資料ダウンロード｜" + Top.getHeaderTitle();
    /** テスト自動化ソリューションボタン */
    @FindBy(xpath = "//a[contains(text(), 'テスト自動化ソリューション')]")
    private WebElement testJidokaSolution;

    public ShiryoDownload(WebDriver driver) throws IllegalPageException {
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
        WebDriverWait wait = new WebDriverWait(getWebDriver(), getMaxWaitTime());
        wait.until(ExpectedConditions.visibilityOfAllElements(testJidokaSolution));
    }

    /**
     * 「テスト自動化ソリューションボタン」リンクを押下。
     */
    public void clickTestJidokaSolution() {
        ScrollWIndow.toElement(getWebDriver(), testJidokaSolution);
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 5);
        wait.until(ExpectedConditions.elementToBeClickable(testJidokaSolution));

        testJidokaSolution.click();

        // リンクを押下すると新しいウィンドウで開く
       wait.until(windowToBeAvailableAndSwitchTo());
    }

    private static ExpectedCondition<WebDriver> windowToBeAvailableAndSwitchTo() {
        return new ExpectedCondition<WebDriver>() {
            @Override
            public WebDriver apply(WebDriver driver) {
                try {
                    Set<String> windowHandles = driver.getWindowHandles();
                    windowHandles.remove(driver.getWindowHandle());
                    return driver.switchTo().window(windowHandles.stream().findFirst().get());
                } catch (NoSuchElementException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "window to be available";
            }
        };
    }

}
