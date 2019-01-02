package com.raju.javabaseproject.utlities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Rajashekhar Vanahalli on 11/06/18.
 */
public class DateTimeUtil {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DD_MM_YY_HH_MM = "dd/MM/yyyy hh:mm";
    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    public static final String DD_MM_YY = "dd/MM/yy";
    public static final String HH_MM = "hh:mm";

    public static String getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS, Locale.US);
        calendar.setTime(new Date());
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(calendar.getTime());
    }

    // 25/03/18 11:30
    public static String formatDateTime(String inFormat, String outFormat, String inputDate) {
        String time = "";
        try {
            SimpleDateFormat sd = new SimpleDateFormat(inFormat, Locale.US);
            Date date = sd.parse(inputDate);
            SimpleDateFormat sdf = new SimpleDateFormat(outFormat, Locale.US);
            time = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    public static String formatBirthDate(String inFormat, String outFormat, String inputDate) {
        String time = "";
        try {
            SimpleDateFormat sd = new SimpleDateFormat(inFormat, Locale.US);
            Date date = sd.parse(inputDate);
            SimpleDateFormat sdf = new SimpleDateFormat(outFormat, Locale.US);
            time = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }
}
