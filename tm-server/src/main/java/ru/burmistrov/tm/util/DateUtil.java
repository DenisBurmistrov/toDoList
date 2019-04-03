package ru.burmistrov.tm.util;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date parseDate(String date) throws ParseException {
        @NotNull final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        @NotNull final Date dateEnd = simpleDateFormat.parse(date);
        return dateEnd;
    }
}
