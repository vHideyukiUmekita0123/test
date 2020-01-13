package utilities.date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DateUtilsTest {

    @Tag("AutoFW")
    @Test
    public void 現在日付を取得できること() {
        Date now = new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat(DateFormat.yyyyMMdd.getFormat());
        assertEquals(dateFormat.format(now), DateUtils.getCurrentLocalDateTime(DateFormat.yyyyMMdd));
    }

    @Tag("AutoFW")
    @Test
    public void LocalDateTimeで取得できること() {
        assertTrue(DateFormatTest.testTime.equals(
                DateUtils.toLocalDateTime(DateFormat.yyyyMMddHHmmss, DateFormatTest.testTimeString)));
    }

    @Tag("AutoFW")
    @Test
    public void 年月日の日が足されること() {
        assertEquals("20200201", DateUtils.getAddDate(DateFormatTest.testTime, 1, DateFormat.yyyyMMdd));
    }

    @Tag("AutoFW")
    @Test
    public void 年月日の月が足されること() {
        assertEquals("20200229", DateUtils.getAddMonth(DateFormatTest.testTime, 1, DateFormat.yyyyMMdd));
    }

    @Tag("AutoFW")
    @Test
    public void 日付が足されること() {
        assertEquals("20210131", DateUtils.getAddYear(DateFormatTest.testTime, 1, DateFormat.yyyyMMdd));
    }

    @Tag("AutoFW")
    @Test
    public void 月初が足されること() {
        assertEquals("20200101", DateUtils.getFirstDate(DateFormatTest.testTime, DateFormat.yyyyMMdd));
    }

    @Tag("AutoFW")
    @Test
    public void 月末が足されること() {
        assertEquals("20200131", DateUtils.getLastDate(DateFormatTest.testTime, DateFormat.yyyyMMdd));
    }

}
