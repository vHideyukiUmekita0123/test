package screenShot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import utilities.date.DateFormat;
import utilities.date.DateUtils;

// TODO:スクリーンショットはテスト対象ごとに実装する必要がある
public class ScreenShot {
    /** WebDriver */
    private WebDriver driver;
    /** ファイルの保存先 */
    private String folderPath;
    /** ビルド番号 */
    private String buildNum;

    /**
     * コンストラクタ
     *
     * @param driver
     *            driver
     */
    public ScreenShot(WebDriver driver) {
        this.driver = driver;

        if (System.getenv("BUILD_NUMBER") != null) {
            buildNum = "_" + System.getenv("BUILD_NUMBER");
        } else {
            buildNum = "";
        }
    }

    /**
     * 全画面のスクリーンショットを撮る
     *
     * @param filename
     *            ファイル名
     */
    public void shot(String filename) {

        // スクリーンショットを撮る前に1秒待機する
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        // スクロール処理の待ち時間(単位:ms)
        final int waitTimeScr = 200;

        // 現在の日付を取得
        String sysDate = DateUtils.getCurrentLocalDateTime(DateFormat.yyyyMMdd);

        StackTraceElement[] ste = new Throwable().getStackTrace();
        // 2行目のスタックトレースを取得する
        String ste1 = ste[1].toString();

        // "."と"("毎に分割する [1]…パッケージ名、[2]…画面名、[3]…ケース名
        String[] folder = ste1.split("[.(]");

        // 指定のフォルダに渡されたファイル名でスクリーンショットを保存する
        Path path = null;
        path = Paths.get(folderPath, sysDate + buildNum, folder[1], folder[2], folder[3], folder[4], filename + ".jpg");

        TakesScreenshot ts = (TakesScreenshot) driver;

        // JS実行用のExecuter
        JavascriptExecutor jexec = (JavascriptExecutor) driver;

        // 必要な画面サイズを取得
        // 画面の高さ
        int innerH = Integer.parseInt(String.valueOf(jexec.executeScript("return window.innerHeight")));

        // 画面の幅
        int innerW = Integer.parseInt(String.valueOf(jexec.executeScript("return window.innerWidth")));

        // ページの高さ
        int scrollH = Integer.parseInt(String.valueOf(jexec.executeScript("return document.documentElement.scrollHeight")));

        // 該当ページの幅
        int scrollW = Integer.parseInt(String.valueOf(jexec.executeScript("return document.documentElement.scrollWidth")));

        // 画像の加工準備(イメージ領域)
        BufferedImage img = new BufferedImage(Integer.max(scrollW, innerW) - 16, scrollH, BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();

        // ダイアログが出ていた場合と、スクロールが無かった場合は全体を撮る
        if (innerH == scrollH || innerW < 1200) { // 画面幅が1200より小さければ、ダイアログが出現しているものと考える
            // File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // try {
            // FileUtils.copyFile(screenshot, path.toFile());
            // } catch (IOException e) {
            // e.printStackTrace();
            // }

            BufferedImage imageParts = null;
            try {
                imageParts = ImageIO.read(ts.getScreenshotAs(OutputType.FILE));
            } catch (WebDriverException | IOException e) {
                e.printStackTrace();
            }

            // 画面の画像を下から結合する。
            g.drawImage(imageParts, 0, 0, null);

            // 結合画面を保管
            path.toFile().getParentFile().mkdirs();

            try (FileImageOutputStream output = new FileImageOutputStream(path.toFile())) {
                ImageWriter writeImage = ImageIO.getImageWritersByFormatName("png").next();
                ImageWriteParam writeParam = writeImage.getDefaultWriteParam();
                writeImage.setOutput(output);
                writeImage.write(null, new IIOImage(img, null, null), writeParam);
                writeImage.dispose();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

            // ヘッダの高さを取得
            // +4 はドロップシャドウ分
            int headerHeight = driver.findElement(By.id("main-header")).getSize().height + 10;

            // 固定エリアの高さを取得
            // ドロップシャドウが入っているのでバッファとして10px余分に高さをもうける
            if (driver.findElements(By.className("gyomu-header1")).size() > 0) {
                headerHeight = (driver.findElement(By.className("gyomu-header1")).getSize().height
                        + Integer.max(headerHeight, Integer.parseInt(driver.findElement(By.className("gyomu-header1")).getCssValue("top").replace("px", "")))) + 10;
            } else if (driver.findElements(By.className("gyomu-header")).size() > 0) {
                headerHeight = (driver.findElement(By.className("gyomu-header")).getSize().height
                        + Integer.max(headerHeight, Integer.parseInt(driver.findElement(By.className("gyomu-header")).getCssValue("top").replace("px", "")))) + 10;
            }

            // スクロール回数
            int scrollNum = (scrollH - headerHeight) / (innerH - headerHeight) + 1;

            for (int i = 0; i <= scrollNum; i++) {

                int topX = Integer.max(0, scrollH - (innerH - headerHeight) * (i + 1));

                jexec.executeScript("window.scrollTo(0, " + topX + ")");

                try {
                    Thread.sleep(waitTimeScr);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                BufferedImage imageParts = null;
                try {
                    imageParts = ImageIO.read(ts.getScreenshotAs(OutputType.FILE));
                } catch (WebDriverException | IOException e) {
                    e.printStackTrace();
                }

                // 画面の画像を下から結合する。
                g.drawImage(imageParts, 0, Integer.max(0, topX - headerHeight), null);
            }

            // 結合画面を保管
            path.toFile().getParentFile().mkdirs();

            try (FileImageOutputStream output = new FileImageOutputStream(path.toFile())) {
                ImageWriter writeImage = ImageIO.getImageWritersByFormatName("png").next();
                ImageWriteParam writeParam = writeImage.getDefaultWriteParam();
                writeImage.setOutput(output);
                writeImage.write(null, new IIOImage(img, null, null), writeParam);
                writeImage.dispose();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
