package com.itfly.beijingair.twitter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by fezho on 11/12/2016.
 */
public class DateTimeUtils {

    private static DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");

    public static LocalDateTime fromStr(String str) {
        return LocalDateTime.parse(str, Formatter);
    }

}
