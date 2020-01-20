package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public final class EditCsv {
    /** テストデータ読み書き時の文字コード */
    private static final String WINDOWS_31J = "UTF8";

    /**
     * コンストラクタ
     */
    private EditCsv() {
    }

    /**
     * CSVを読み込み二次元配列で返却
     *
     * @param csvFilePath
     *            CSVファイルのパス
     * @throws IOException
     *             CSVファイルの読み込みに失敗した場合
     * @return array CSV二次元配列
     *
     */
    public static String[][] readCsv(String csvFilePath) throws IOException {
        File file = new File(csvFilePath);
        if (!file.exists()) {
            throw new IllegalStateException("CSVファイルが存在しません。CSVファイル：" + file.getAbsolutePath());
        }
        if (!file.isFile()) {
            throw new IllegalStateException("指定されたパスはファイルではありません。パス：" + file.getAbsolutePath());
        }
        if (!file.canRead()) {
            throw new IllegalStateException("CSVファイルが読み込み不可能です。CSVファイル：" + file.getAbsolutePath());
        }

        List<String> csvRowList = new ArrayList<String>();
        try (FileInputStream fs = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fs, WINDOWS_31J);
                BufferedReader br = new BufferedReader(isr);) {
            while (br.ready()) {
                csvRowList.add(br.readLine());
            }
        } catch (IOException e) {
            throw new IOException("CSVファイルの読み込みに失敗しました。CSVファイル：" + csvFilePath, e);
        }

        // リストに入れた各行を","でsplitして2次元配列にして返す
        return csvRowList.stream().map(l -> l.split(",")).toArray(String[][]::new);
    }

    /**
     * CSV二次元配列の値を上書きして保存
     *
     * @param csvFilePath
     *            CSVファイルのパス
     * @param csvArray
     *            CSV二次元配列
     * @throws IOException
     *             CSVファイルの書き込みに失敗した場合
     * @return boolean
     */
    public static void writeCsv(String csvFilePath, String[][] csvArray) throws IOException {
        File file = new File(csvFilePath);
        if (!file.exists()) {
            throw new IllegalStateException("CSVファイルが存在しません。CSVファイル：" + file.getAbsolutePath());
        }
        if (!file.isFile()) {
            throw new IllegalStateException("指定されたパスはファイルではありません。パス：" + file.getAbsolutePath());
        }
        if (!file.canWrite()) {
            throw new IllegalStateException("CSVファイルが書き込み不可能です。パス：" + file.getAbsolutePath());
        }

        final int maxColumnSize = getMaxColumnSize(csvArray);
        try (FileOutputStream fo = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(fo, WINDOWS_31J);
                BufferedWriter bw = new BufferedWriter(osw);) {
            for (int lineNumber = 0; lineNumber < csvArray.length; lineNumber++) {
                for (int columnNumber = 0; columnNumber < maxColumnSize; columnNumber++) {
                    if (columnNumber < csvArray[lineNumber].length) {
                        bw.write(csvArray[lineNumber][columnNumber]);
                    }
                    // カンマはCSVの最大要素に合わせる
                    if (columnNumber < maxColumnSize - 1) {
                        bw.write(",");
                    }
                }
                bw.newLine();
            }
        } catch (IOException e) {
            throw new IOException("CSVファイルの書き込み失敗しました。CSVファイル：" + file.getAbsolutePath(), e);
        }
    }

    /**
     * CSVの最大要素数を取得する.
     *
     * @param csvArray
     *            2次元配列化したCSV
     * @return CSVの最大要素数
     */
    private static Integer getMaxColumnSize(String[][] csvArray) {
        int maxLength = 0;
        for (int i = 0; i < csvArray.length; i++) {
            if (csvArray[i].length > maxLength) {
                maxLength = csvArray[i].length;
            }
        }
        return maxLength;
    }

}
