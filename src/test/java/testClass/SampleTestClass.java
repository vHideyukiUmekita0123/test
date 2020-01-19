package testClass;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.WebDriver;

import pageObject.sample.HeaderArea;
import pageObject.sample.ShiryoDownload;
import pageObject.sample.TestJidokaSolution;
import pageObject.sample.Top;
import pageObject.sample.Tsuyomi;
import utilities.WebDriverBuilder;

@TestInstance(Lifecycle.PER_CLASS)
public final class SampleTestClass extends TemplateTestClass {

    private WebDriver driver;

    public SampleTestClass() {
    }

    @Override
    @BeforeEach
    public void beforeAll(TestInfo testInfo) throws Exception {
        driver = WebDriverBuilder.create("C:\\AutoDownload");
    }

    // beforeEachはOverrideしない

    @RepeatedTest(3)
    public void test_01() throws Exception {
        driver.get(Top.getUrl());
        Top topPage = new Top(driver);
        assertTrue(topPage.isDisplayed(), "トップページが表示されていること");

        HeaderArea headerArea = new HeaderArea(driver);
        headerArea.clickJissekiTsuyomi();
        headerArea.clickTsuyomi();
        Tsuyomi tsuyomiPage = new Tsuyomi(driver);
        assertTrue(tsuyomiPage.isDisplayed(), "強みページが表示されていること");

        tsuyomiPage.clickShiryoDownload();
        ShiryoDownload shiryoDownloadPage = new ShiryoDownload(driver);
        assertTrue(shiryoDownloadPage.isDisplayed(), "資料ダウンロードページが表示されていること");

        shiryoDownloadPage.clickTestJidokaSolution();
        TestJidokaSolution testJidokaSolutionPage = new TestJidokaSolution(driver);
        assertTrue(testJidokaSolutionPage.isDisplayed(), "テスト自動化ソリューションページが表示されていること");
    }

    @Override
    @AfterEach
    public void afterAll(TestInfo testInfo) throws Exception {
        driver.quit();
    }

}
