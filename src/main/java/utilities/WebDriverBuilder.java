package utilities;

import java.util.Collections;
import java.util.HashMap;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class WebDriverBuilder {

    // option key
    private static final String CHROME_DRIVER_PATH_KEY = "webdriver.chrome.driver";
    private static final String AUTOMATION_EXTENSION_KEY = "useAutomationExtension";
    private static final String CHROME_INFO_BAR_KEY = "excludeSwitches";
    private static final String CHROME_PREFS_KEY = "prefs";
    private static final String CHROME_POPUP_CONTROL_KEY = "profile.default_content_settings.popups";
    private static final String DOWNLOAD_PATH_KEY = "download.default_directory";

    // option value
    private static final String WEB_DRIVER_PATH;
    private static final boolean USE_EXTENSION;
    private static final boolean DISPLAY_INFO_BAR;
    private static final int CONTROL_POPUP;
    private static final int WINDOW_WIDTH;
    private static final int WINDOW_HIGHT;
    private static final int WINDOW_VARTICAL_POINT;
    private static final int WINDOW_HORIZONTAL_POINT;

    private WebDriverBuilder() {
    }

    static {
        WEB_DRIVER_PATH                     = Property.getSettingsProperty("webDriverPath");
        USE_EXTENSION                          = Boolean.valueOf(Property.getSettingsProperty("useExtension"));
        DISPLAY_INFO_BAR                    = Boolean.valueOf(Property.getSettingsProperty("displayInfoBar"));
        CONTROL_POPUP                         = Integer.valueOf(Property.getSettingsProperty("controlPopup"));
        WINDOW_WIDTH                         = Integer.valueOf(Property.getSettingsProperty("windowWidth"));
        WINDOW_HIGHT                          = Integer.valueOf(Property.getSettingsProperty("windowHight"));
        WINDOW_VARTICAL_POINT       = Integer.valueOf(Property.getSettingsProperty("verticalPosition"));
        WINDOW_HORIZONTAL_POINT = Integer.valueOf(Property.getSettingsProperty("horizontalPosition"));
    }

    public static WebDriver create(String downloadPath) {
        System.setProperty(CHROME_DRIVER_PATH_KEY, WEB_DRIVER_PATH);

        // 拡張機能を設定
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption(AUTOMATION_EXTENSION_KEY, USE_EXTENSION);
        // FIXME:v77以降で非表示にする方法が不明
        if (!DISPLAY_INFO_BAR) {
            options.setExperimentalOption(CHROME_INFO_BAR_KEY, Collections.singletonList("enable-automation"));
        }

        // ダウンロード先を設定
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put(CHROME_POPUP_CONTROL_KEY, CONTROL_POPUP);
        chromePrefs.put(DOWNLOAD_PATH_KEY, downloadPath);
        options.setExperimentalOption(CHROME_PREFS_KEY, chromePrefs);

        // 指定のウィンドウサイズに変更
        WebDriver webDriver = new ChromeDriver(options);
        webDriver.manage().window().setSize(new Dimension(WINDOW_WIDTH, WINDOW_HIGHT));
        webDriver.manage().window().setPosition(new Point(WINDOW_VARTICAL_POINT, WINDOW_HORIZONTAL_POINT));

        return webDriver;
    }

}
