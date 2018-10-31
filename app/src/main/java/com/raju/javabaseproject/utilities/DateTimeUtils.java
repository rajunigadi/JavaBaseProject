package com.raju.javabaseproject.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeUtils {

    public static final String FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_3 = "dd MMM yyyy";
    public static final String FORMAT_2 = "yyyy-MM-dd HH:mm";
    private static final SimpleDateFormat formatterEEEDDMMMYYYY = new SimpleDateFormat("EEE, dd MMM yyyy");

    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_3, Locale.US);
        calendar.setTime(new Date());
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(calendar.getTime());
    }

    public static String getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_1, Locale.US);
        calendar.setTime(new Date());
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(calendar.getTime());
    }

    public static String getLocalDateTime(String inputDate) {
        String outputDate = inputDate;
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_1, Locale.US);
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            calendar.setTime(sdf.parse(inputDate));

            //Here you set to local device timezone
            sdf.setTimeZone(TimeZone.getDefault());

            //Will print on your default Timezone
            outputDate = sdf.format(calendar.getTime());
        } catch (Exception ex) {
            ex.printStackTrace();
            return outputDate;
        }
        return outputDate;
    }

    public static String getDay(String inputDate) {
        String day = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_1, Locale.US);
            Date date = sdf.parse(inputDate);
            sdf.applyPattern("EEEE");
            day = sdf.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return day;
    }

    public static long getLocalDateTime(long timeStamp) {
        long outputTimeStamp = timeStamp;
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_1, Locale.US);
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            calendar.setTime(new Date(timeStamp));

            //Here you set to local device timezone
            sdf.setTimeZone(TimeZone.getDefault());

            //Will print on your default Timezone
            String outputDate = sdf.format(calendar.getTime());

            outputTimeStamp = sdf.parse(outputDate).getTime();
        } catch (Exception ex) {
            ex.printStackTrace();
            return outputTimeStamp;
        }
        return outputTimeStamp;
    }

    public static String getDateKey(String inputDate) {
        String tempTime = inputDate;
        try {
            SimpleDateFormat sd = new SimpleDateFormat(FORMAT_1, Locale.US);
            Date date = sd.parse(inputDate);
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.US);
            tempTime = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempTime;
    }

    public static String formatTime(String inputDate, boolean is24HourFormat) {
        String time = "";
        try {
            SimpleDateFormat sd = new SimpleDateFormat(FORMAT_1, Locale.US);
            Date date = sd.parse(inputDate);
            SimpleDateFormat sdf;
            if (is24HourFormat) {
                sdf = new SimpleDateFormat("HH:mm", Locale.US);
            } else {
                sdf = new SimpleDateFormat("hh:mm a", Locale.US);
            }
            time = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    public static String formatDate(String inputDate) {
        String time = "";
        try {
            SimpleDateFormat sd = new SimpleDateFormat(FORMAT_1, Locale.US);
            Date date = sd.parse(inputDate);
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_3, Locale.US);
            time = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    public static String formatDateMonth(String inputDate) {
        String time = "";
        try {
            SimpleDateFormat sd = new SimpleDateFormat(FORMAT_1, Locale.US);
            Date date = sd.parse(inputDate);
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, MM yyyy", Locale.US);
            time = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    public static boolean isLessThanCurrentDate(String dt) {
        SimpleDateFormat format1 = new SimpleDateFormat(FORMAT_1, Locale.US);
        try {
            Date date = format1.parse(dt);
            Date curDate = format1.parse(getCurrentDateTime());
            if (date.after(curDate)) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public static long getDateTimeInMillis(String strDate, boolean isEnd) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(FORMAT_1, Locale.US);
            Calendar calendar = Calendar.getInstance();
            Date date = df.parse(strDate);
            calendar.setTime(date);
            if (isEnd) {
                calendar.add(Calendar.HOUR, 2);
            }
            return calendar.getTimeInMillis();
        } catch (Exception ex) {
            return 0;
        }
    }

    public static long getDateKeyInMillis(String strDate) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy", Locale.US);
            Calendar calendar = Calendar.getInstance();
            Date date = df.parse(strDate);
            calendar.setTime(date);
            return calendar.getTimeInMillis();
        } catch (Exception ex) {
            return 0;
        }
    }

    public static long getDateTimeKeyInMillis(String strDate) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(FORMAT_1, Locale.US);
            Calendar calendar = Calendar.getInstance();
            Date date = df.parse(strDate);
            calendar.setTime(date);
            return calendar.getTimeInMillis();
        } catch (Exception ex) {
            return 0;
        }
    }

    public static long getDateInMillis(String strDate) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(FORMAT_1, Locale.US);
            Calendar calendar = Calendar.getInstance();
            Date date = df.parse(strDate);
            calendar.setTime(date);
            return calendar.getTimeInMillis();
        } catch (Exception ex) {
            return 0;
        }
    }

    public static String formatToYesterdayOrToday(String date) {
        try {
            Date dateTime = new SimpleDateFormat("dd MMM yyyy", Locale.US).parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateTime);

            Calendar today = Calendar.getInstance();

            Calendar yesterday = Calendar.getInstance();
            yesterday.add(Calendar.DATE, -1);

            Calendar tomorrow = Calendar.getInstance();
            tomorrow.add(Calendar.DATE, 1);

            if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
                return "Today";
            } else if (calendar.get(Calendar.YEAR) == yesterday.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == yesterday.get(Calendar.DAY_OF_YEAR)) {
                return "Yesterday";
            } else if (calendar.get(Calendar.YEAR) == tomorrow.get(Calendar.YEAR) && calendar.get(Calendar.DAY_OF_YEAR) == tomorrow.get(Calendar.DAY_OF_YEAR)) {
                return "Tomorrow";
            } else {
                return date;
            }
        } catch (Exception ex) {
            return date;
        }
    }


    public static String getStartEndDate(boolean isStart) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_1, Locale.US);
        try {
            Calendar calendar = Calendar.getInstance();
            if(isStart) {
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
            } else {
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
            }
            return sdf.format(calendar.getTime());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "2018-02-28 00:00:00";
    }

    public static String formatCompleteDate(long time) {
        return formatDate(formatterEEEDDMMMYYYY, time);
    }

    public static String formatDate(SimpleDateFormat formatter, long time) {
        formatter.setTimeZone(getCurrentTimeZone());
        return formatter.format(new Date(time));
    }

    private static TimeZone getCurrentTimeZone() {
        return TimeZone.getDefault();
    }

}
