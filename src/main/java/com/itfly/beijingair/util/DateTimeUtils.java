package com.itfly.beijingair.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Created by fezho on 11/12/2016.
 */
public class DateTimeUtils {

    private static DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");

    public static final int SECONDS_PER_MINUTE = 60;
    public static final int MINUTES_PER_HOUR = 60;
    public static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;

    public static int StartTS;
    static {
        LocalDateTime dateTime = fromStr("01-01-2000 00:00");
        int StartTS = (int) (dateTime.toEpochSecond(ZoneOffset.UTC) / SECONDS_PER_HOUR);
    }

    public static LocalDateTime fromStr(String str) {
        return LocalDateTime.parse(str, Formatter);
    }

    public static int toTS(String str) {
        return toTS(fromStr(str));
    }

    public static int toTS(LocalDateTime dateTime) {
        long epoch = dateTime.toEpochSecond(ZoneOffset.UTC);
        return (int) (epoch / SECONDS_PER_HOUR) - StartTS;
    }

    // TODO
/*    public static LocalDateTime fromTS(int timestamp) {
        LocalDateTime dateTime = new LocalDateTime(0);
    }*/

    public static void main(String[] args) {
        LocalDateTime dateTime = fromStr("01-01-2000 00:00");
        long epoch = dateTime.toEpochSecond(ZoneOffset.UTC);
        System.out.print(epoch);
    }
}
