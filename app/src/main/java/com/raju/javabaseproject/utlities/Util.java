package com.raju.javabaseproject.utlities;

import android.content.res.Resources;

/**
 * Created by Rajashekhar Vanahalli on 20/07/18.
 */
public class Util {

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
