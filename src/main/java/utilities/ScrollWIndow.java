package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class ScrollWIndow {

    private static final String SCROLL_COMMAND = "arguments[0].scrollIntoView(true)";

    private ScrollWIndow() {
        // nothing to do.
    }

    public static void move(WebElement element) {
        element.getLocation().moveBy(0, 0);
    }

    public static void toElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript(SCROLL_COMMAND, element);
    }

}
