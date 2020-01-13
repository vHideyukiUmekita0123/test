package screenShot;

import org.openqa.selenium.WebDriver;

public final class IgnoreToScreenShot extends ScreenShot {

    /**
     * コンストラクタ
     *
     * @param driver
     *            driver
     */
    public IgnoreToScreenShot(WebDriver driver) {
        super(driver);
    }

    /**
     * 表示されている画面のみスクリーンショットを撮る(例:shot("Case01-01");)
     *
     * @param filename
     *            スクリーンショットのファイル名
     */
    public void shot(String filename) {
        return;
    }

}