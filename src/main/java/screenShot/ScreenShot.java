package screenShot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import evidence.EvidencePath;
import evidence.EvidencePathBuilder;
import exception.IllegalPageException;
import exception.ScreenShotException;
import pageObject.sample.HeaderArea;
import utilities.Property;
import utilities.date.DateFormat;
import utilities.date.DateUtils;

// TODO:スクリーンショットはテスト対象ごとに実装する必要がある
public class ScreenShot {

    /** エビデンスを保存する親パス */
    private static final String PARENT_EVIDENCE_PATH = Property.getSettingsProperty("evidencePath");
    /** スクロースバーの幅 */
    private static final int SCROLL_BAR_WIDTH = 18;
    /** ヘッダーエリアにあるドロップシャドウの幅*/
    private static final int DROP_SHADOW_WIDTH = 10;
    /** 拡張子 */
    private static final String EXTENSION = ".jpg";
    /** 実行日の日付 */
    private static final String TODAY = DateUtils.getCurrentLocalDateTime(DateFormat.yyyyMMdd);
    /** WebDriver */
    private WebDriver driver;
    /** ヘッダーエリアの高さ */
    private int headerAreaHeight;
    /** 画面の高さ */
    int innerH;
    /** 画面の幅 */
    int innerW;
    /** ページの高さ */
    int scrollH;
    /** 該当ページの幅 */
    int scrollW;
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
     */
    public void shot() {
        // 必要な画面サイズを取得
        JavascriptExecutor jexec = (JavascriptExecutor) driver;
        innerH = Integer.parseInt(String.valueOf(jexec.executeScript("return window.innerHeight")));
        innerW = Integer.parseInt(String.valueOf(jexec.executeScript("return window.innerWidth")));
        scrollH = Integer.parseInt(String.valueOf(jexec.executeScript("return document.documentElement.scrollHeight")));
        scrollW = Integer.parseInt(String.valueOf(jexec.executeScript("return document.documentElement.scrollWidth")));

        // 画像の加工準備(イメージ領域)
        BufferedImage img = new BufferedImage(
                Integer.max(scrollW, innerW) - SCROLL_BAR_WIDTH,
                scrollH,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();

        if (!scrollNeeded()) {
            takeVisiblePartScreenShot(g, 0);
            writeImage(img);
            return;
        }

        // スクロールするときにHeaderAreaがついてくるのでその分をImageに描写しないようにする
        try {
            HeaderArea headerArea = new HeaderArea(driver);
            headerAreaHeight  = headerArea.getHeaderAreaHeight() + DROP_SHADOW_WIDTH;
        } catch (IllegalPageException e) {
            headerAreaHeight = 0;
        }

        int topX = scrollH - innerH;
        for (int i = 0; i <= scrollTimes(); i++) {
            jexec.executeScript("window.scrollTo(0, " + topX + ")");
            takeVisiblePartScreenShot(g, topX);
            topX = Integer.max(0, topX - innerH + headerAreaHeight + SCROLL_BAR_WIDTH);
        }
        writeImage(img);
    }

    private void takeVisiblePartScreenShot(Graphics graphics, int currentTop) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        BufferedImage imageParts = null;
        try {
            imageParts = ImageIO.read(ts.getScreenshotAs(OutputType.FILE));
        } catch (WebDriverException | IOException e) {
            throw new ScreenShotException("スクリーンショットの取得に失敗しました。", e);
        }

        // 画面の画像を下から結合する。
        graphics.drawImage(imageParts,
                0,
                Integer.max(0, currentTop),
                innerW,
                Integer.max(0, currentTop + innerH) - SCROLL_BAR_WIDTH,
                0,
                0,
                imageParts.getWidth(),
                imageParts.getHeight() - SCROLL_BAR_WIDTH,
                null);
    }
    private Path createEvidencePath() {
        // src/test/java配下のパスが"."区切りで表現される。
        // ディレクトリ構造表現にするために"."を"/"に置換する。
        final String dot = ".";
        final String slash = "/";

        EvidencePath evidencePath = EvidencePathBuilder.create();
        return Paths.get(
                PARENT_EVIDENCE_PATH,
                TODAY + buildNum,
                evidencePath.getTestClassName().replaceAll(Pattern.quote(dot), slash),
                evidencePath.getTestCaseName(),
                ScreenShotInfo.getSingleton().getCaptureNumber(evidencePath) + EXTENSION);
    }

    private void writeImage(BufferedImage image) {
        Path writedPath = createEvidencePath();
        writedPath.toFile().getParentFile().mkdirs();

        try (FileImageOutputStream output = new FileImageOutputStream(writedPath.toFile())) {
            ImageWriter writeImage = ImageIO.getImageWritersByFormatName("png").next();
            ImageWriteParam writeParam = writeImage.getDefaultWriteParam();
            writeImage.setOutput(output);
            writeImage.write(null, new IIOImage(image, null, null), writeParam);
            writeImage.dispose();
        } catch (FileNotFoundException e) {
            throw new ScreenShotException("イメージを書き込むパスが存在しません。", e);
        } catch (IOException e) {
            throw new ScreenShotException("イメージの書き込むに失敗しました。", e);
        }
    }

    private boolean scrollNeeded() {
        return innerH != scrollH;
    }

    private int scrollTimes() {
        return (scrollH - headerAreaHeight - SCROLL_BAR_WIDTH) / (innerH - headerAreaHeight - SCROLL_BAR_WIDTH) + 1;
    }

}
