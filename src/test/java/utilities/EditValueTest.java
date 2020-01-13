package utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * EditValueのテストクラス
 *
 * @author ベリサーブ)yuuto suzuki
 *
 */
public final class EditValueTest {

    @Test
    public void 文字列前後のスペースがトリムされること() {
        assertEquals(EditValue.trimWhiteSpace(" 　　   あ1　 アｱ　  　"), "あ1　 アｱ");
    }

    @Test
    public void 全角英字が半角英字に変換されること() {
        assertEquals(EditValue.changeNumFullToHalf("AあｂいC"), "AあbいC");
    }

    @Test
    public void テキストを抽出できること() {
        assertEquals(EditValue.getSpecifiedCharacterBetweenText("あいうえお", "い","え"), "う");
    }

    @Test
    public void 対象文字列がnullのため例外がスローされること() {
        assertThrows(IllegalArgumentException.class, () -> EditValue.getSpecifiedCharacterBetweenText(null, "あ", "お"));
    }

    @Test
    public void 開始文字がnullのため例外がスローされること() {
        assertThrows(IllegalArgumentException.class, () -> EditValue.getSpecifiedCharacterBetweenText("あいうえお", null, "お"));
    }

    @Test
    public void 終了文字がnullのため例外がスローされること() {
        assertThrows(IllegalArgumentException.class, () -> EditValue.getSpecifiedCharacterBetweenText("あいうえお", "あ", null));
    }

    @Test
    public void 開始文字がないため例外がスローされること() {
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> EditValue.getSpecifiedCharacterBetweenText("あいうえお", "か", "お"));
        assertEquals(e.getMessage(), "テキストに指定された文字が見つかりませんでした。テキスト：あいうえお, 指定文字：か");
    }

    @Test
    public void 終了文字がないため例外がスローされること() {
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> EditValue.getSpecifiedCharacterBetweenText("あいうえお", "あ", "か"));
        assertEquals(e.getMessage(), "テキストに指定された文字が見つかりませんでした。テキスト：あいうえお, 指定文字：か");
    }

    @Test
    public void 開始文字が複数あるため例外がスローされること() {
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> EditValue.getSpecifiedCharacterBetweenText("ああうえお", "あ", "お"));
        assertEquals(e.getMessage(), "テキストに指定された文字が複数存在しました。テキスト：ああうえお, 指定文字：あ");
    }

    @Test
    public void 終了文字が複数あるため例外がスローされること() {
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> EditValue.getSpecifiedCharacterBetweenText("あいうおお", "あ", "お"));
        assertEquals(e.getMessage(), "テキストに指定された文字が複数存在しました。テキスト：あいうおお, 指定文字：お");
    }

    @Test
    public void 開始文字と終了文字の位置不正の例外がスローされること() {
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> EditValue.getSpecifiedCharacterBetweenText("あいうえお", "え", "い"));
        assertEquals(e.getMessage(), "指定された開始文字と終了文字の位置が不正です。テキスト：あいうえお, 開始文字：え, 終了文字：い");
    }

    @Test
    public void 小数点の切り捨てが行われていること() {
        double testValue = 1.2345;
        assertEquals(EditValue.decimalPointRoundDown(testValue, 3), "1.23");
    }
}
