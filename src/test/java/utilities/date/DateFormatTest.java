package utilities.date;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DateFormatTest {

    protected static final String testTimeString = "20200131223344";
    protected static final LocalDateTime testTime;

    static {
        testTime = LocalDateTime.parse(testTimeString, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    @Tag("AutoFW")
    @Test
    public void yyyyMMの形式になること() {
        assertEquals("202001", changeFormat(DateFormat.yyyyMM));
    }

    @Tag("AutoFW")
    @Test
    public void yyyyMMddの形式になること() {
        assertEquals("20200131", changeFormat(DateFormat.yyyyMMdd));
    }

    @Tag("AutoFW")
    @Test
    public void yyyyMMddHHの形式になること() {
        assertEquals("2020013122", changeFormat(DateFormat.yyyyMMddHH));
    }

    @Tag("AutoFW")
    @Test
    public void yyyyMMddHHmmの形式になること() {
        assertEquals("202001312233", changeFormat(DateFormat.yyyyMMddHHmm));
    }

    @Tag("AutoFW")
    @Test
    public void yyyyMMddHHmmssの形式になること() {
        assertEquals("20200131223344", changeFormat(DateFormat.yyyyMMddHHmmss));
    }

    @Tag("AutoFW")
    @Test
    public void slash_yyyyMMddの形式になること() {
        assertEquals("2020/01/31", changeFormat(DateFormat.slash_yyyyMMdd));
    }

    @Tag("AutoFW")
    @Test
    public void yyMMddの形式になること() {
        assertEquals("200131", changeFormat(DateFormat.yyMMdd));
    }

    @Tag("AutoFW")
    @Test
    public void MMddの形式になること() {
        assertEquals("0131", changeFormat(DateFormat.MMdd));
    }

    @Tag("AutoFW")
    @Test
    public void MMの形式になること() {
        assertEquals("01", changeFormat(DateFormat.MM));
    }

    @Tag("AutoFW")
    @Test
    public void colon_HHmmの形式になること() {
        assertEquals("22:33", changeFormat(DateFormat.colon_HHmm));
    }

    @Tag("AutoFW")
    @Test
    public void HHmmssの形式になること() {
        assertEquals("223344", changeFormat(DateFormat.HHmmss));
    }

    @Tag("AutoFW")
    @Test
    public void slash_yyyyMdの形式になること() {
        assertEquals("2020/1/31", changeFormat(DateFormat.slash_yyyyMd));
    }

    private String changeFormat(DateFormat format) {
        return testTime.format(DateTimeFormatter.ofPattern(format.getFormat()));
    }

}
