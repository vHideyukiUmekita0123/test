package screenShot;

import org.openqa.selenium.WebDriver;

public final class IgnoreToScreenShot extends ScreenShot {

    public IgnoreToScreenShot(WebDriver driver) {
        super(driver);
    }

    @Override
    public void shot() {
        // 他ケース呼び出しの時に使用する。
        // 他ケース呼び出しの時はScreenShotは不要なので処理を空にするようにoverrideする。
        return;
    }

}