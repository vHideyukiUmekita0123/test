package utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Objects;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.WebDriver;

@TestInstance(Lifecycle.PER_CLASS)
public final class WebDriverBuilderTest {

    private WebDriver driver;
    private String downloadPath = "test";

    public WebDriverBuilderTest() {
    }

    @BeforeAll
    public void beforeAll() {
        driver = WebDriverBuilder.create(downloadPath);
    }

    @Test
    @Disabled
    public void ダウンロード先が設定されていること() {
        // TODO:chromeのダウンロードパス確認方法が現状なさそう
    }

    @Test
    @Disabled
    public void 自動制御中のダイアログが非表示であること() {
        // TODO:現状非表示にできないため、必ずfailする。
    }

    @Test
    public void ウィンドウの横幅が設定どおりであること() {
        assertEquals(driver.manage().window().getSize().getWidth(), 1280);
    }

    @Test
    public void ウィンドウの縦幅が設定どおりであること() {
        assertEquals(driver.manage().window().getSize().getHeight(), 768);
    }

    @Test
    public void ウィンドウの水平方向の起点が設定どおりであること() {
        assertEquals(driver.manage().window().getPosition().getX(), 0);
    }

    @Test
    public void ウィンドウの垂直方向の起点が設定どおりであること() {
        assertEquals(driver.manage().window().getPosition().getY(), 0);
    }

    @AfterAll
    public void afterAll() {
        if (!Objects.isNull(driver)) {
            driver.quit();
        }
    }

}
