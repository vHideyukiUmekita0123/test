package pageObject.sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import exception.IllegalPageException;
import pageObject.AbstractPageObject;

public class HeaderArea extends AbstractPageObject {

    /** 実績・強み */
    @FindBy(css = "#pagetop > header > div.g-siteHeader_inner > div > div.g-headerMenu > div > div > nav > ul > li:nth-child(2) > a")
    private WebElement jissekiTsuyomi;
    /** 実績・強みのドロップダウン内になる「強み」ボタン */
    @FindBy(xpath = "(//a[@href='/asset/advantage/'])[1]")
    private WebElement tsuyomi;

    public HeaderArea(WebDriver driver) throws IllegalPageException {
        super(driver);
    }

    @Override
    public boolean isDisplayed() {
        if (super.getWebDriver().findElements(By.tagName("header")).size() == 1) {
            return true;
        }
        return false;
    }

    /**
     * 「実績・強み」項目を押下。
     */
    public void clickJissekiTsuyomi() {
        jissekiTsuyomi.click();
    }

    /**
     * 実績・強みのドロップダウン内になる「強み」ボタンを押下。
     */
    public void clickTsuyomi() {
        WebDriverWait wait = new WebDriverWait(getWebDriver(), 5);
        wait.until(ExpectedConditions.elementToBeClickable(tsuyomi));
        tsuyomi.click();
    }

}
