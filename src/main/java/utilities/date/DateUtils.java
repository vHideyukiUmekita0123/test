package utilities.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtils {

    private DateUtils() {
    }

    /**
    @param format
    @return 現在日時
    */
    public static String getCurrentLocalDateTime(DateFormat format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format.getFormat()));
    }

    /**
     *
     * @param format
     * @param strDate
     * @return 文字列を解析して日付形式に変換する
     */
    public static LocalDateTime toLocalDateTime(DateFormat format, String strDate) {
        return LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern(format.getFormat()));
    }

    /**
     *
     * @param beforeDate
     * @param addDate
     * @param afterFormat
     * @return 日数を足した日付
     */
    public static String getAddDate(LocalDateTime beforeDate, int addDate, DateFormat afterFormat) {
        return beforeDate.plusDays(addDate).format(DateTimeFormatter.ofPattern(afterFormat.getFormat()));
    }

    /**
     *
     * @param beforeDate
     * @param addDate
     * @param afterFormat
     * @return 月数を足した日付
     */
    public static String getAddMonth(LocalDateTime beforeDate, int addDate, DateFormat afterFormat) {
        return beforeDate.plusMonths(addDate).format(DateTimeFormatter.ofPattern(afterFormat.getFormat()));
    }

    /**
     *
     * @param beforeDate
     * @param addDate
     * @param afterFormat
     * @return 年数を足した日付
     */
    public static String getAddYear(LocalDateTime beforeDate, int addDate, DateFormat afterFormat) {
        return beforeDate.plusYears(addDate).format(DateTimeFormatter.ofPattern(afterFormat.getFormat()));
    }

    /**
     *
     * @param date
     * @param format
     * @return 開始日
     */
    public static String getFirstDate(LocalDateTime date, DateFormat format) {
        final int firstDay = 1;
        return date.toLocalDate().withDayOfMonth(firstDay)
                .format(DateTimeFormatter.ofPattern(format.getFormat()));
    }

    /**
     *
     * @param date
     * @param format
     * @return 終了日
     */
    public static String getLastDate(LocalDateTime date, DateFormat format) {
        return date.toLocalDate().withDayOfMonth(date.toLocalDate().lengthOfMonth())
                .format(DateTimeFormatter.ofPattern(format.getFormat()));
    }

    /**
     *
     * @param date
     * @param before
     * @param after
     * @return 日付形式を変更する
     */
    public static String changeFormat(String date, DateFormat before, DateFormat after) {
        return toLocalDateTime(before, date).format(DateTimeFormatter.ofPattern(after.getFormat()));
    }

}
