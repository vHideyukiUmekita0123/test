package utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * EditCsvクラスのテストクラス
 *
 * @author Veriserve)yuuto suzuki
 *
 */
public class EditCsvTest {

    private static final String TEST_CSV_PATH="./target/classes/utilities/";
    private static final String TEST_CSV_NAME="EditCsvTest.csv";
    private static final String NEW_CSV_NAME="NewEditCsvTest.csv";
    private static String[][] csvArray;

    @BeforeAll
    public static void beforeAll() throws IOException {
            csvArray = EditCsv.readCsv(TEST_CSV_PATH + TEST_CSV_NAME);
    }

    @Test
    public void 一行一列目の値が1であること() {
        assertEquals("1", csvArray[0][0]);
    }

    @Test
    public void 一行二列目の値が2であること() {
        assertEquals("2", csvArray[0][1]);
    }

    @Test
    public void 一行三列目の値が3であること() {
        assertEquals("3", csvArray[0][2]);
    }

    @Test
    public void 一行四列目の値が4であること() {
        assertEquals("4", csvArray[0][3]);
    }

    @Test
    public void 一行５列目の値が5であること() {
        assertEquals("5", csvArray[0][4]);
    }

    @Test
    public void 二行一列目の値があであること() {
        assertEquals("あ", csvArray[1][0]);
    }

    @Test
    public void 二行二列目の値がいであること() {
        assertEquals("い", csvArray[1][1]);
    }

    @Test
    public void 二行三列目の値がうであること() {
        assertEquals("う", csvArray[1][2]);
    }

    @Test
    public void 三行一列目の値がｱであること() {
        assertEquals("ｱ", csvArray[2][0]);
    }

    @Test
    public void 三行二列目の値がｲであること() {
        assertEquals("ｲ", csvArray[2][1]);
    }

    @Test
    public void 三行三列目の値がｳであること() {
        assertEquals("ｳ", csvArray[2][2]);
    }

    @Test
    public void 三行四列目の値がｴであること() {
        assertEquals("ｴ", csvArray[2][3]);
    }

    @Test
    public void 四行一列目の値がaであること() {
        assertEquals("a", csvArray[3][0]);
    }

    @Test
    public void 四行二列目の値がbであること() {
        assertEquals("b", csvArray[3][1]);
    }

    @Test
    public void csvに書き込みできること() throws IOException {
        final String expected = "hoge";
        final int targetLine = 2;
        final int targetRow = 1;

        String[][] newCsvArray = EditCsv.readCsv(TEST_CSV_PATH + TEST_CSV_NAME);
        newCsvArray[targetLine][targetRow] = expected;

        File newCsv = File.createTempFile(NEW_CSV_NAME, "", new File(TEST_CSV_PATH));
        newCsv.createNewFile();

        EditCsv.writeCsv(newCsv.getAbsolutePath(), newCsvArray);
        try {
            newCsvArray = null;
            newCsvArray = EditCsv.readCsv(newCsv.getAbsolutePath());
            assertEquals(expected, newCsvArray[targetLine][targetRow]);
        } finally {
            newCsv.delete();
        }
    }

}
