package com.raju.javabaseproject.utlities

import android.content.res.Resources

object Util {

    val screenWidth: Int
        get() = Resources.getSystem().displayMetrics.widthPixels

    val screenHeight: Int
        get() = Resources.getSystem().displayMetrics.heightPixels

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}
