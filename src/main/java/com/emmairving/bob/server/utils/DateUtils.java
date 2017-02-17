package com.emmairving.bob.server.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by irving on 17/2/16.
 */
public class DateUtils {
    public static Calendar getCalendar_By_String_MySql_DateTime(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( sdf.parse(date) );
        calendar.set(Calendar.SECOND, 0);
        return calendar;
    }
}
