package utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public final class EditValue {

    private EditValue() {
    }

    /**
     * 文字列の前後から全角、半角スペースを取り除く.
     *
     * @param value
     *            文字列
     * @return 文字列の前後から全角、半角スペースを取り除かれた文字列
     */
    public static String trimWhiteSpace(String value) {
        final char halfSpace = '\u0020';
        final char nbsp = '\u00A0';
        final char fullSpace = '\u3000';

        if (value == null || value.length() == 0) {
            return value;
        }

        int beginingIndex = 0; // 最初にスペース以外から始まる文字のインデックス
        int endIndex = value.length(); // 最後にスペース以外で終わる文字のインデックス
        char[] val = value.toCharArray();

        while ((beginingIndex < endIndex) && ((val[beginingIndex] <= halfSpace) || (val[beginingIndex] == nbsp) || (val[beginingIndex] == fullSpace))) {
            beginingIndex++;
        }
        while ((beginingIndex < endIndex) && ((val[endIndex - 1] <= halfSpace) || (val[endIndex - 1] == nbsp) || (val[endIndex - 1] == fullSpace))) {
            endIndex--;
        }

        if ((beginingIndex > 0) || (endIndex < value.length())) {
            value = value.substring(beginingIndex, endIndex);
        }
        return value;
    }

    /**
     * 全角英字を半角英字へ変換する.
     *
     * @param value
     *            文字列
     * @return 全角英字を半角英字に変換後の文字列
     */
    public static String changeNumFullToHalf(String value) {
        if (Objects.isNull(value) || value.isEmpty()) {
            return value;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if ('ａ' <= c && c <= 'ｚ') {
                c = (char) (c - 'ａ' + 'a');
            } else if ('Ａ' <= c && c <= 'Ｚ') {
                c = (char) (c - 'Ａ' + 'A');
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     *
     * 指定した文字の間にある値を返す.
     *
     * @param value
     *            テキスト
     * @param start
     *            開始位置
     * @param end
     *            終了位置
     * @return 間にあるテキスト
     */
    public static String getSpecifiedCharacterBetweenText(String value, String start, String end) {
        final String argNullMessage = "引数がnullです。";
        final String notFoundMessage = "テキストに指定された文字が見つかりませんでした。テキスト：%s, 指定文字：%s";
        final String foundSomeMessage = "テキストに指定された文字が複数存在しました。テキスト：%s, 指定文字：%s";
        final String incorrectedOrderMessage = "指定された開始文字と終了文字の位置が不正です。テキスト：%s, 開始文字：%s, 終了文字：%s";

        if (Objects.isNull(value) || Objects.isNull(start) || Objects.isNull(end)) {
            throw new IllegalArgumentException(argNullMessage);
        }
        if (!value.contains(start)) {
            throw new IllegalStateException(String.format(notFoundMessage, value, start));
        }
        if (!value.contains(end)) {
            throw new IllegalStateException(String.format(notFoundMessage, value, end));
        }
        if (value.indexOf(start) != value.lastIndexOf(start)) {
            throw new IllegalStateException(String.format(foundSomeMessage, value, start));
        }
        if (value.indexOf(end) != value.lastIndexOf(end)) {
            throw new IllegalStateException(String.format(foundSomeMessage, value, end));
        }
        if (value.indexOf(start) > value.indexOf(end)) {
            throw new IllegalStateException(String.format(incorrectedOrderMessage, value, start, end));
        }

        return value.substring(value.indexOf(start) + start.length(), value.lastIndexOf(end));
    }

    /**
     * 値から指定した小数点の位を切り捨てする.
     *
     * @param doubleValue
     *            対象の値
     * @param downPoint
     *            切捨てする位
     * @return 指定した小数点の位で切り捨てした値
     */
    public static String decimalPointRoundDown(double doubleValue, int downPoint) {
        return String.valueOf(
                        new BigDecimal(String.valueOf(doubleValue))
                                .setScale(downPoint - 1, RoundingMode.DOWN)
                );
    }

}
