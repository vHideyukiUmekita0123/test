package utilities.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtils {

    private DateUtils() {
    }

    public static String getCurrentLocalDateTime(DateFormat format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format.getFormat()));
    }

    public static LocalDateTime toLocalDateTime(DateFormat format, String strDate) {
        return LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern(format.getFormat()));
    }

    public static String getAddDate(LocalDateTime beforeDate, int addDate, DateFormat afterFormat) {
        return beforeDate.plusDays(addDate).format(DateTimeFormatter.ofPattern(afterFormat.getFormat()));
    }

    public static String getAddMonth(LocalDateTime beforeDate, int addDate, DateFormat afterFormat) {
        return beforeDate.plusMonths(addDate).format(DateTimeFormatter.ofPattern(afterFormat.getFormat()));
    }

    public static String getAddYear(LocalDateTime beforeDate, int addDate, DateFormat afterFormat) {
        return beforeDate.plusYears(addDate).format(DateTimeFormatter.ofPattern(afterFormat.getFormat()));
    }

    public static String getFirstDate(LocalDateTime date, DateFormat format) {
        final int firstDay = 1;
        return date.toLocalDate().withDayOfMonth(firstDay)
                .format(DateTimeFormatter.ofPattern(format.getFormat()));
    }

    public static String getLastDate(LocalDateTime date, DateFormat format) {
        return date.toLocalDate().withDayOfMonth(date.toLocalDate().lengthOfMonth())
                .format(DateTimeFormatter.ofPattern(format.getFormat()));
    }

    public static String changeFormat(String date, DateFormat before, DateFormat after) {
        return toLocalDateTime(before, date).format(DateTimeFormatter.ofPattern(after.getFormat()));
    }

}
