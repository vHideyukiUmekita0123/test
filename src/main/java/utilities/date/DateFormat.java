package utilities.date;

public enum DateFormat {
    /** yyyyMMのフォーマット */
    yyyyMM("yyyyMM"),
    /** yyyyMMddのフォーマット */
    yyyyMMdd("yyyyMMdd"),
    /** yyyyMMddHHのフォーマット */
    yyyyMMddHH("yyyyMMddHH"),
    /** yyyyMMddHHmmのフォーマット */
    yyyyMMddHHmm("yyyyMMddHHmm"),
    /** yyyyMMddHHmmのフォーマット */
    yyyyMMddHHmmss("yyyyMMddHHmmss"),
    /** yyyy/MM/ddのフォーマット */
    slash_yyyyMMdd("yyyy/MM/dd"),
    /** yyMMddのフォーマット */
    yyMMdd("yyMMdd"),
    /** MMddのフォーマット */
    MMdd("MMdd"),
    /** MMのフォーマット */
    MM("MM"),
    /** HH:mmのフォーマット */
    colon_HHmm("HH:mm"),
    /** HHmmssのフォーマット */
    HHmmss("HHmmss"),
    /** yyyy/M/dのフォーマット */
    slash_yyyyMd("yyyy/M/d");

    /** 日付のフォーマットタイプ */
    private final String format;

    /**
     * コンストラクタ
     *
     * @param format
     *            日付のフォーマットタイプ
     */
    DateFormat(final String format) {
        this.format = format;
    }

    /**
     * 日付フォーマットタイプを取得する
     *
     * @return 日付のフォーマットタイプ
     */
    public String getFormat() {
        return this.format;
    }

}
