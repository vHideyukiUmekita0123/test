package pageObject.sample;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import exception.IllegalPageException;
import pageObject.AbstractPageObject;

public class Tsuyomi extends AbstractPageObject {

    private static final String HEADER_TITLE = "強み｜実績・強み｜株式会社ベリサーブ";
    /** 資料ダウンロードボタン */
    @FindBy(xpath = "(//a[contains(text(), '資料ダウンロード')])[1]")
    private WebElement shiryoDonload;

    public Tsuyomi(WebDriver driver) throws IllegalPageException {
        super(driver);
    }

    @Override
    public boolean isDisplayed() {
        if (getWebDriver().getTitle().equals(HEADER_TITLE)) {
            return true;
        }
        return false;
    }

    /** 「資料ダウンロードボタン」を押下 */
    public void clickShiryoDownload() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 5);
        wait.until(ExpectedConditions.elementToBeClickable(shiryoDonload));
        shiryoDonload.click();
    }

}
