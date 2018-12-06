package com.android.myapplication.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static final String DATE_FORMAT ="MMM dd yyyy HH:mm:ss";

    public static String getMillisToHumanRedableTime2(long milliSecond) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy HH:mm:ss");
        Date resultdate = new Date(milliSecond);
        return sdf.format(resultdate);
    }


    public static Date getDateFromString(String strDate, String inputDateFormat) {
        try {
            java.text.DateFormat format = new SimpleDateFormat(inputDateFormat, Locale.ENGLISH);
            return format.parse(strDate);

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
