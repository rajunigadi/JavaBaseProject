package com.raju.javabaseproject.utlities

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateTimeUtil {

    val YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"
    val DD_MM_YY_HH_MM = "dd/MM/yyyy hh:mm"
    val DD_MM_YYYY = "dd/MM/yyyy"
    val DD_MM_YY = "dd/MM/yy"
    val HH_MM = "hh:mm"

    val currentDateTime: String
        get() {
            val calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat(YYYY_MM_DD_HH_MM_SS, Locale.US)
            calendar.time = Date()
            sdf.timeZone = TimeZone.getDefault()
            return sdf.format(calendar.time)
        }

    // 25/03/18 11:30
    fun formatDateTime(inFormat: String, outFormat: String, inputDate: String): String {
        var time = ""
        try {
            val sd = SimpleDateFormat(inFormat, Locale.US)
            val date = sd.parse(inputDate)
            val sdf = SimpleDateFormat(outFormat, Locale.US)
            time = sdf.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return time
    }

    fun formatBirthDate(inFormat: String, outFormat: String, inputDate: String): String {
        var time = ""
        try {
            val sd = SimpleDateFormat(inFormat, Locale.US)
            val date = sd.parse(inputDate)
            val sdf = SimpleDateFormat(outFormat, Locale.US)
            time = sdf.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return time
    }
}
